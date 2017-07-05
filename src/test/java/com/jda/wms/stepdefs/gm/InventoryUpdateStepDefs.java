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

	@When("^I select the update type as \"([^\"]*)\"$")
	public void i_select_the_update_type_as(String updateType) throws Throwable {
		inventoryUpdatePage.enterselectType(updateType);
		jdafooter.clickNextButton();

	}

	@When("^I search the inventory for locked tag$")
	public void i_search_the_inventory_for_locked_tag() throws Throwable {
		inventoryUpdatePage.entertagID(context.getTagId());
		inventoryUpdatePage.entersku(context.getSkuId());
		inventoryUpdatePage.enterLocation(context.getlocationID());
		jdafooter.clickNextButton();

	}

	@Then("^the tag details should be displayed$")
	public void the_tag_details_should_be_displayed() throws Throwable {
		Assert.assertTrue("Tag Details not displayed as expected", inventoryUpdatePage.isRecordDdisplayed());
		jdafooter.clickNextButton();
	}

	@Then("^I select the status as \"([^\"]*)\"$")
	public void i_select_the_status_as(String status) throws Throwable {
		inventoryUpdatePage.enterStatus(status);
		jdafooter.clickDoneButton();

	}
}
