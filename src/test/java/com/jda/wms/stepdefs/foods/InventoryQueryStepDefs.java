package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.InventoryQueryPage;

import cucumber.api.java.en.Given;

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
}
