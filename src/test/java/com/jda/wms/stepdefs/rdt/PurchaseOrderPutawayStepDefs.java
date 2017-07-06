package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPutawayStepDefs {
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private Verification verification;
	private InventoryDB inventoryDB;
	private LocationDB locationDB;
	private Hooks hooks;

	@Inject
	public PurchaseOrderPutawayStepDefs(PurchaseOrderPutawayPage purchaseOrderPutawayPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks) {
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
	}

	@When("^I select normal putaway$")
	public void i_select_normal_putaway() throws Throwable {
		purchaseOrderPutawayPage.selectPutawayMenu();
		purchaseOrderPutawayPage.selectNormalPutawayMenu();
	}

	@When("^I do normal putaway for all tags received$")
	public void i_do_normal_putaway_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			i_enter_urn_id_in_putaway();
			the_tag_details_for_putaway_should_be_displayed();
			context.setToLocation(inventoryDB.getPutawayLocation(context.getSkuId(), context.getLocation()));
			i_enter_to_location(context.getToLocation());
			i_enter_the_check_string();
			if (!purchaseOrderPutawayPage.isPutEntDisplayed()) {
				failureList.add("Putaway not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@When("^I enter to location$")
	public void i_enter_to_location(String location) throws InterruptedException {
		purchaseOrderPutawayPage.enterLocation(location);
	}

	@Then("^I should be directed to putent page$")
	public void i_should_be_directed_to_putent_page() throws Throwable {
		Assert.assertTrue("Putaway Home page not displayed.", purchaseOrderPutawayPage.isPutEntDisplayed());
	}

	@Then("^I enter the check string$")
	public void i_enter_the_check_string() throws Throwable {
		Assert.assertTrue("Chk To Page not displayed to enter check string",
				purchaseOrderPutawayPage.isChkToDisplayed());
		purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));
	}

	@When("^I enter urn id in putaway$")
	public void i_enter_urn_id_in_putaway() throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(context.getUpiId());
	}

	@When("^the tag details for putaway should be displayed$")
	public void the_tag_details_for_putaway_should_be_displayed() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("PutCmp page not displayed to enter To Location",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		verification.verifyData("From Location", context.getLocation(), purchaseOrderPutawayPage.getFromLocation(),
				failureList);
		verification.verifyData("Tag ID", context.getUpiId(), purchaseOrderPutawayPage.getTagId(), failureList);
		 Assert.assertTrue("SKU Attributes are not as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());
	}
}
