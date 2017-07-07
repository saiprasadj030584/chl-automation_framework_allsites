package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.pages.gm.InventoryUpdatePage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.gm.WarningPopUpPage;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryUpdateStepDefs {
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JDAFooter jdafooter;
	private InventoryUpdatePage inventoryUpdatePage;
	private DateUtils dateUtils;
	private WarningPopUpPage warningPopUpPage;

	@Inject
	public InventoryUpdateStepDefs(Context context, JDAFooter jdafooter, InventoryDB inventoryDB,
			InventoryUpdatePage inventoryUpdatePage, DateUtils dateUtils, WarningPopUpPage warningPopUpPage) {
		this.context = context;
		this.jdafooter = jdafooter;
		this.inventoryUpdatePage = inventoryUpdatePage;
		this.dateUtils = dateUtils;
		this.warningPopUpPage = warningPopUpPage;

	}

	@When("^I select the update type as \"([^\"]*)\"$")
	public void i_select_the_update_type_as(String updateType) throws Throwable {
		inventoryUpdatePage.enterselectType(updateType);
		jdafooter.clickNextButton();

	}

	@When("^I search the inventory for the tag$")
	public void i_search_the_inventory_for_the_tag() throws Throwable {
		inventoryUpdatePage.entertagID(context.getTagId());
		inventoryUpdatePage.entersku(context.getSkuId());
		inventoryUpdatePage.enterLocation(context.getLocation());

		System.out.println(context.getlocationID());
		jdafooter.clickNextButton();

	}

	@Then("^the tag details should be displayed$")
	public void the_tag_details_should_be_displayed() throws Throwable {
		Assert.assertFalse("Tag Details not displayed as expected", inventoryUpdatePage.isRecordDdisplayed());
		jdafooter.clickNextButton();
	}

	@Then("^I select the status as \"([^\"]*)\"$")
	public void i_select_the_status_as(String status) throws Throwable {
		inventoryUpdatePage.enterStatus(status);
		jdafooter.clickDoneButton();
		if (inventoryUpdatePage.isWarningPopUpPageExist()) {
			warningPopUpPage.clickYes();

		}

		context.setStatus(status);

	}

	@Then("^I select the future date$")
	public void i_select_the_future_date() throws Throwable {
		String futuredate = DateUtils.getAddedSystemYear();
		inventoryUpdatePage.enterExpiryDate(futuredate);
		jdafooter.clickDoneButton();
		context.setFutureExpiryDate(futuredate);

	}

}
