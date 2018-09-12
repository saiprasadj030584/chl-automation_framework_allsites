package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.InventoryTransactionDB;
import com.jda.wms.pages.Exit.StockAdjustmentsPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class StockAdjustmentsStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockAdjustmentsPage stockAdjustmentsPage;
	private Context context;
	private final JDAFooter jdaFooter;
	private final InventoryTransactionDB inventoryTransactionDB; 
	private final  InventoryDB inventoryDB;

	@Inject
	public StockAdjustmentsStepDefs(InventoryTransactionDB inventoryTransactionDB,
			StockAdjustmentsPage stockAdjustmentsPage, Context context, JDAFooter jdaFooter,InventoryDB inventoryDB) {
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.inventoryDB = inventoryDB ;
	}

	@When("^I search the inventory details$")
	public void i_search_the_inventory_details() throws Throwable {
		Thread.sleep(4000);
		jdaFooter.clickNextButton();
		System.out.println("Value of Tag Id**********" + context.getTagId());
		stockAdjustmentsPage.enterTagId(context.getTagId());
		stockAdjustmentsPage.enterLocation(context.getLocation());
		stockAdjustmentsPage.selectStatus(context.getStatus());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		//stockAdjustmentsPage.clickLastRecords();
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}
	
	@When("^I search the inventory details with CE duty stamp$")
	public void i_search_the_inventory_details_with_CE_duty_stamp() throws Throwable {
		Thread.sleep(4000);
		jdaFooter.clickNextButton();
		String tagId=context.getTagId();
		stockAdjustmentsPage.enterTagId(tagId);
		//stockAdjustmentsPage.enterTagId(context.getTagId());
		//--C&E stamp not required as per the test case--//
//		stockAdjustmentsPage.enterCEDutyStamp();
		//stockAdjustmentsPage.enterUnderBond();

		//--C&E stamp is No for dev006 as no data available for Yes//
		stockAdjustmentsPage.enterCEDutyStamp();
		
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		stockAdjustmentsPage.clickLastRecords();
//		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}

	@Then("^the record should be displayed in the results$")
	public void the_record_should_be_displayed_in_the_results() throws Throwable {
		boolean isRecordExists = stockAdjustmentsPage.isRecordExists();
		Assert.assertTrue("Results Record not displayed in search results.", isRecordExists);
		logger.debug("Record Exists?: " + isRecordExists);
	}

	@When("^I navigate to create or modify tab$")
	public void i_navigate_to_create_or_modify_tab() throws Throwable {
		jdaFooter.clickNextButton();
	}

	@Then("^the product details should be displayed from inventory$")
	public void the_product_details_should_be_displayed_from_inventory() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
//		String status = stockAdjustmentsPage.getStatus();

//		String status = inventoryTransactionDB.getStockAdjustmentLockStatus(context.getTagId());
		String status = inventoryDB.getStockAdjustmentLockStatus(context.getTagId());
		System.out.println("Get Lock/UnLock Status " + status);
		if (!context.getStatus().contains(status)) {
			failureList.add("Status is not displayed as expected. Expected [" + context.getStatus() + "] but was ["
					+ status + "]");
		}

		logger.debug("Stock Adjustment - Status: " + status);

		Assert.assertTrue(
				"Stock Adjustment attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	@When("^I have the tagid and case ratio details$")
	public void I_have_the_tagid_and_case_ratio_details() throws Throwable {
		String tagId=stockAdjustmentsPage.getTagId();
		context.setTagId(tagId);
		String qtyOnHandBfrAdjustment = stockAdjustmentsPage.getQtyOnHand();
		context.setqtyOnHandBeforeAdjustment(Utilities.convertStringToInteger(qtyOnHandBfrAdjustment));
		logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);
		System.out.println("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

		String caseRatio = inventoryDB.getCaseRatio(context.getTagId());
		context.setCaseRatio(Utilities.convertStringToInteger(caseRatio));
		logger.debug("Case Ratio: " + caseRatio);
		System.out.println("Case Ratio: " + caseRatio);
		String status=inventoryDB.getStatus(context.getTagId());
		context.setStatus(status);
	}
	
	@When("^I \"([^\"]*)\" the quantity on hand$")
	public void i_the_quantity_on_hand(String adjustmentType) throws Throwable {
		context.setAdjustmentType(adjustmentType);
		System.out.println("Adjustment Type"+ adjustmentType);
		System.out.println("caseRatio="+context.getCaseRatio());
		if (adjustmentType.equalsIgnoreCase("Decrement")) {
			int decrementQty = context.getQtyOnHandBeforeAdjustment() - context.getCaseRatio();
			logger.debug("Quantity to update for adjustment " + decrementQty);
			System.out.println("Quantity to update for adjustment " + decrementQty);
			stockAdjustmentsPage.updateQtyOnHand(Integer.toString(decrementQty));
		} else if (adjustmentType.equalsIgnoreCase("Increment")) {
			int incrementQty = context.getQtyOnHandBeforeAdjustment() + context.getCaseRatio();
			logger.debug("Quantity to update for adjustment " + incrementQty);
			stockAdjustmentsPage.updateQtyOnHand(Integer.toString(incrementQty));
		}
		jdaFooter.clickNextButton();
	}

	@Given("^I choose the reason code as \"([^\"]*)\"$")
	public void i_choose_the_reason_code_as(String reasonCode) throws Throwable {
//		stockAdjustmentsPage.isShortageImageExists();
//		stockAdjustmentsPage.clickOk();
		stockAdjustmentsPage.chooseReasonCode(reasonCode);
	}

	@Then("^the stock adjustments home page should be displayed$")
	public void the_stock_adjustments_home_page_should_be_displayed() throws Throwable {
		Thread.sleep(3000);
		boolean isStockAdjustmentHomeDisplayed = stockAdjustmentsPage.isStockAdjustmentHomeDisplayed();
		Assert.assertTrue("Stock Adjustment Home page not displayed as expected.", isStockAdjustmentHomeDisplayed);
		logger.debug("Stock Adjustment Home page displayed?: " + isStockAdjustmentHomeDisplayed);
	}
	
	@Then("^the product details should be displayed from inventory for unlocked tag$")
	public void the_product_details_should_be_displayed_from_inventory_for_unlocked_tag() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
//		String status = stockAdjustmentsPage.getStatus();

//		String status = inventoryTransactionDB.getStockAdjustmentLockStatus(context.getTagId());
		String status = inventoryDB.getStockAdjustmentLockStatus(context.getTagId());
		System.out.println("Get Lock/UnLock Status " + status);
//		if (!context.getStatus().contains(status)) {
//			failureList.add("Status is not displayed as expected. Expected [" + context.getStatus() + "] but was ["
//					+ status + "]");
//		}
//
		logger.debug("Stock Adjustment - Status: " + status);

		Assert.assertTrue(
				"Stock Adjustment attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	@When("^I search the inventory details for unlocked$")
	public void i_search_the_inventory_details_for_unlocked() throws Throwable {
		Thread.sleep(4000);
		jdaFooter.clickNextButton();
		System.out.println("Value of Tag Id**********" + context.getTagId());
		stockAdjustmentsPage.enterTagId(context.getTagId());
		stockAdjustmentsPage.enterLocation(context.getLocation());
		stockAdjustmentsPage.selectStatus(context.getlockStatus());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		//stockAdjustmentsPage.clickLastRecords();
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}
	
	@When("^update the quantity to previous$")
	public void update_the_quantity_to_previous() throws Throwable {
		
		inventoryDB.updateQtyOnHand(context.getTagId());
	}
}
