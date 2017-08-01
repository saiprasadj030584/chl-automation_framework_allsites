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
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

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
	private JDAFooter jdaFooter;
	private PuttyFunctionsPage puttyFunctionsPage;

	@Inject
	public PurchaseOrderPutawayStepDefs(PurchaseOrderPutawayPage purchaseOrderPutawayPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage) {
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
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

	@When("^I choose normal putaway$")
	public void i_choose_normal_putaway() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
	}

	@When("^I should not be able to putaway locked PO$")
	public void i_should_not_be_able_to_putaway_locked_po() throws Throwable {
		i_choose_normal_putaway();
		Assert.assertFalse("Putaway details page should not be displayed as the PO is locked for putaway",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
	}

	@When("^I proceed without entering location$")
	public void i_proceed_without_entering_location() throws InterruptedException, FindFailed {
		ArrayList failureList1 = new ArrayList();
		poMap = context.getPOMap();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			if (context.getsupplierType().equalsIgnoreCase("FSV")) {
				i_enter_pallet_id_in_putaway(context.getPalletIDList().get(i - 1));
				jdaFooter.PressEnter();
				jdaFooter.PressEnter();
				if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
					System.out.println("Check");
					failureList1.add("Error message:Cannot find putaway location not displayed as expected for pallet"
							+ context.getPalletIDList().get(i - 1));
				} else {
					i_enter_urn_id_in_putaway();
					if (null == context.getLockCode()) {
						the_tag_details_for_putaway_should_be_displayed();
						jdaFooter.PressEnter();
						if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
							failureList1
									.add("Error message:Cannot find putaway location not displayed as expected for UPI"
											+ context.getUpiId());
						}
					}
				}

				jdaFooter.PressEnter();
				purchaseOrderPutawayPage.navigateToBackScreen();
			}
			context.setFailureList(failureList1);
			System.out.println(context.getFailureList());
		}
	}

	@When("^the error message should be displayed as cannot find putaway location$")
	public void the_error_message_should_be_displayed_as_cannot_find_putaway_location() throws InterruptedException {
		Assert.assertTrue("Error message:Cannot find putaway location not displayed. ["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
	}

	@When("^I proceed without entering quantity$")
	public void i_proceed_without_entering_quantity() throws InterruptedException {
		jdaFooter.pressTab();
		for (int i = 0; i < 25; i++) {
			jdaFooter.pressBackSpace();
		}
		jdaFooter.PressEnter();
	}

	@When("^I proceed without entering quantity for returns$")
	public void i_proceed_without_entering_quantity_for_returns() throws InterruptedException, FindFailed {
		i_enter_pallet_id_in_putaway(context.getTagId());
		jdaFooter.PressEnter();
		jdaFooter.pressTab();
		for (int t = 0; t < 7; t++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int t = 0; t < 7; t++) {
			puttyFunctionsPage.backspace();
		}
		jdaFooter.PressEnter();
	}

	@When("^the error message should be displayed as invalid quantity exception$")
	public void the_error_message_should_be_displayed_as_invalid_quantity_exception() throws InterruptedException {
		Assert.assertTrue("Error message:Invalid Quantity Exception not displayed as expected",
				purchaseOrderPutawayPage.isQuantityErrorDisplayed());
		jdaFooter.PressEnter();
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

	@When("^I enter pallet id in putaway$")
	public void i_enter_pallet_id_in_putaway(String palletId) throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(palletId);
	}

	@When("^the tag details for putaway should be displayed$")
	public void the_tag_details_for_putaway_should_be_displayed() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("PutCmp page not displayed to enter To Location",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		verification.verifyData("From Location", context.getLocation(), purchaseOrderPutawayPage.getFromLocation(),
				failureList);
		verification.verifyData("Tag ID", context.getUpiId(), purchaseOrderPutawayPage.getTagId(), failureList);
		Assert.assertTrue("SKU Attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the  error message should be displayed$")
	public void the_error_message_should_be_displayed() throws Throwable {
		Assert.assertTrue(
				" error message is not displayed. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	@When("^I proceed by entering less quantity$")
	public void i_proceed_by_entering_less_quantity() throws Throwable {
		purchaseOrderPutawayPage.enterURNID(context.getUpiId());
		jdaFooter.PressEnter();
		String quantity = null;
		context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
		quantity = String.valueOf(context.getRcvQtyDue() - 2);
		purchaseOrderPutawayPage.enterQuantity(quantity);
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.rightArrow();
		purchaseOrderPutawayPage.enterQuantity(quantity);
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.selectLocationFullMenu();
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));
		hooks.logoutPutty();

	}
}
