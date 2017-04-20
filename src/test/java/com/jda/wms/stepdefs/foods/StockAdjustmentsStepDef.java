package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.StockAdjustmentsPage;
import cucumber.api.java.en.*;
import edu.emory.mathcs.backport.java.util.Arrays;

public class StockAdjustmentsStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockAdjustmentsPage stockAdjustmentsPage;
	private Context context;
	private final InventoryQueryPage inventoryQueryPage;

	@Inject
	public StockAdjustmentsStepDef(StockAdjustmentsPage stockAdjustmentsPage, Context context,
			InventoryQueryPage inventoryQueryPage) {
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.context = context;
		this.inventoryQueryPage = inventoryQueryPage;
	}

	@When("^I search the inventory details$")
	public void i_search_the_inventory_details() throws Throwable {
		stockAdjustmentsPage.clickNext();
		// Accessing tag id from Inventory Query page- reuse function
		inventoryQueryPage.enterTagId(context.getTagId());
		stockAdjustmentsPage.clickNext();
	}

	@Then("^the record should be displayed in the results$")
	public void the_record_should_be_displayed_in_the_results() throws Throwable {
		boolean isRecordExists = stockAdjustmentsPage.isRecordExists();
		Assert.assertTrue("Results Record not displayed as expected.", isRecordExists);
		logger.debug("Record Exists?: " + isRecordExists);
	}

	@When("^I navigate to create or modify tab$")
	public void i_navigate_to_create_or_modify_tab() throws Throwable {
		stockAdjustmentsPage.clickNext();
	}

	@Then("^the product details should be displayed as expected from inventory$")
	public void the_product_details_should_be_displayed_as_expected_from_inventory() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String skuId = stockAdjustmentsPage.getSkuId();
		if (!skuId.equalsIgnoreCase(context.getSkuId())) {
			failureList.add("Stock Adjustment - SKU ID is not displayed as expected. Expected [" + context.getSkuId()
					+ "] but was [" + skuId + "]");
		}
		logger.debug("Stock Adjustment - SKU ID: " + skuId);

		// Accessing Inventory screen - status function
		String status = inventoryQueryPage.getStatus();
		if (!status.equalsIgnoreCase(context.getStatus())) {
			failureList.add("Status is not displayed as expected. Expected [" + context.getStatus() + "] but was ["
					+ status + "]");
		}
		logger.debug("Stock Adjustment - Status: " + status);

		Assert.assertTrue(
				"Stock Adjustment attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I \"([^\"]*)\" the quantity on hand$")
	public void i_the_quantity_on_hand(String adjustmentType) throws Throwable {
		context.setAdjustmentType(adjustmentType);
		if (adjustmentType.equalsIgnoreCase("Decrement")) {
			int decrementQty = Integer.parseInt(context.getqtyOnHandBfrAdjustment())
					- Integer.parseInt(context.getCaseRatio());
			logger.debug("Quantity to update for adjustment " + decrementQty);
			stockAdjustmentsPage.updateQtyOnHand(Integer.toString(decrementQty));

		} else if (adjustmentType.equalsIgnoreCase("Increment")) {
			int incrementQty = Integer.parseInt(context.getqtyOnHandBfrAdjustment())
					+ Integer.parseInt(context.getCaseRatio());
			logger.debug("Quantity to update for adjustment " + incrementQty);
			stockAdjustmentsPage.updateQtyOnHand(Integer.toString(incrementQty));
		}
	}

	@Given("^I choose the reason code as \"([^\"]*)\"$")
	public void i_choose_the_reason_code_as(String reasonCode) throws Throwable {
		stockAdjustmentsPage.chooseReasonCode(reasonCode);
	}

	@Then("^the stock adjustments home page should be displayed$")
	public void the_stock_adjustments_home_page_should_be_displayed() throws Throwable {
		boolean isStockAdjustmentHomeDisplayed = stockAdjustmentsPage.isStockAdjustmentHomeDisplayed();
		Assert.assertTrue("Stock Adjustment Home page not displayed as expected.", isStockAdjustmentHomeDisplayed);
		logger.debug("Stock Adjustment Home page displayed?: " + isStockAdjustmentHomeDisplayed);
	}
}
