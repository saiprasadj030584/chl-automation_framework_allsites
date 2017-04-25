package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class InventoryQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private final Context context;
	private final JDAFooter jdaFooter;

	@Inject
	public InventoryQueryStepDefs(InventoryQueryPage inventoryQueryPage, Context context, JDAFooter jdaFooter) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.context = context;
		this.jdaFooter = jdaFooter;
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		String istatus = inventoryQueryPage.getStatus();
		if (!istatus.equals(status)) {
			failureList.add("Status is not unlocked.");
		}
		logger.debug("Status in Inventory screen : " + istatus);
	}

	@Then("^the I should see the status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the inventory query$")
	public void the_I_should_see_the_status_as_and_lock_code_as_in_the_inventory_query(String status, String lockCode)
			throws Throwable {
		inventoryQueryPage.refreshInventoryQueryPage();
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

	@Given("^I have SKU id, product group and ABV for the tag id \"([^\"]*)\"$")
	public void i_have_SKU_id_product_group_and_ABV_for_the_tag_id(String tagId) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		String skuId = inventoryQueryPage.getSkuId();
		if (skuId.equals(null)) {
			failureList.add("Sku Id is not as expected. Expected [Not NULL] but was [" + skuId + "]");
		}

		inventoryQueryPage.clickUserDefinedTab();
		String productGroup = inventoryQueryPage.getProductGroup();
		if (!(productGroup.equals("F20") || productGroup.equals("F21") || productGroup.equals("F22"))) {
			failureList.add(
					"Product Group is not as expected. Expected [F20 or F21 or F21] but was [" + productGroup + "]");
		}

		String abv = inventoryQueryPage.getABV();
		if (abv.equals(null)) {
			failureList.add("ABV is not as expected. Expected [NOT NULL] but was [" + abv + "]");
		}
	}

	@Then("^I should see the updated ABV in the inventory query page$")
	public void i_should_see_the_updated_ABV_in_the_inventory_query_page() throws Throwable {
		inventoryQueryPage.refreshInventoryQueryPage();
		Assert.assertEquals("ABV is not as expected.", context.getABV(), inventoryQueryPage.getUpdatedABV());
	}

}
