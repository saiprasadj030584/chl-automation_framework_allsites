package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.pages.gm.InventoryUpdatePage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryUpdateStepDefs {
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JDAFooter jdafooter;
	private InventoryUpdatePage inventoryUpdatePage;

	@Inject
	public InventoryUpdateStepDefs(Context context, JDAFooter jdafooter, InventoryDB inventoryDB,
			InventoryUpdatePage inventoryUpdatePage) {
		this.context = context;
		this.jdafooter = jdafooter;
		this.inventoryUpdatePage = inventoryUpdatePage;

	}

	@When("^i enter the tagID, SKU,location$")
	public void i_enter_the_tagID_SKU_location() throws Throwable {
		jdafooter.clickQueryButton();
		InventoryUpdatePage.entertagID(context.getTagId());
		InventoryUpdatePage.entersku(context.getSkuId());
		InventoryUpdatePage.enterLocation(context.getlocationID());
		jdafooter.clickNextButton();

	}

	@Then("^the record should be displayed$")
	public void the_record_should_be_displayed() throws Throwable {
		InventoryUpdatePage.isRecorddisplayed();
		Assert.assertTrue("record not displayed as expected.InventoryUpdatePage.isRecorddisplayed()", false);
		jdafooter.clickNextButton();

	}

	@Then("^I enter the status as \"([^\"]*)\"$")
	public void i_enter_the_status_as(String status) throws Throwable {
		InventoryUpdatePage.enterStatus(status);
		jdafooter.clickDoneButton();

	}
}
