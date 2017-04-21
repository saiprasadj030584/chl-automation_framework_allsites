package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.InventoryQueryPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class InventoryQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;

	@Inject
	public InventoryQueryStepDefs(InventoryQueryPage inventoryQueryPage) {
		this.inventoryQueryPage = inventoryQueryPage;
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		inventoryQueryPage.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		inventoryQueryPage.clickExecuteButton();

		String istatus = inventoryQueryPage.getStatus();
		if (!istatus.equals(status)) {
			failureList.add("Status is not unlocked.");
		}
		logger.debug("Status in Inventory screen : " + istatus);
	}

	@Then("^the I should see the status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the inventory query$")
	public void the_I_should_see_the_status_as_and_lock_code_as_in_the_inventory_query(String status, String lockCode)
			throws Throwable {
		inventoryQueryPage.refreshInventoryQuery();
		ArrayList<String> failureList1 = new ArrayList<String>();
		String istatus = inventoryQueryPage.getStatus();
		String ilockCode = inventoryQueryPage.getLockCode();
		if (!istatus.equals(status)) {
			failureList1.add("Status is not updated to locked.");
		}
		if (!ilockCode.equals(lockCode)) {
			failureList1.add("Lock Code is not updated to CODEAPP.");
		}

		logger.debug("Status in Inventory screen : " + istatus);
		logger.debug("Lock Code in Inventory screen : " + ilockCode);
		Assert.assertTrue("Inventory details are not as expected." + Arrays.asList(failureList1.toString()),
				failureList1.isEmpty());
	}
}
