package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryTransactionQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private final JDAFooter jdaFooter;

	@Inject
	public InventoryTransactionQueryStepDefs(InventoryTransactionQueryPage inventoryTransactionQueryPage,
			Context context, JDAFooter jdaFooter) {
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
		this.jdaFooter = jdaFooter;
	}

	@When("^I search tag id \"([^\"]*)\", code as \"([^\"]*)\" and lock code as \"([^\"]*)\"$")
	public void i_search_tag_id_and_code_as(String tagId, String code, String lockCode) throws Throwable {
		context.setCode(code);
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterLockCode(lockCode);
		jdaFooter.clickExecuteButton();
	}
	
	@When("^I search tag id \"([^\"]*)\" and code as \"([^\"]*)\"$")
	public void i_search_tag_id_and_code_as(String tagId, String code) throws Throwable {
		context.setCode(code);
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
	}
	@When("^I search tag id \"([^\"]*)\" with transaction code as \"([^\"]*)\"$")
	public void i_search_tag_id_with_transaction_code_as(String tagId, String selectType) throws Throwable {
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterTagId(tagId);
		inventoryTransactionQueryPage.enterCode(selectType);
		inventoryTransactionQueryPage.enterTransactionDate();
		jdaFooter.clickExecuteButton();
	}


	@When("^I select the adjusted stock from the results$")
	public void i_select_the_adjusted_stock_from_the_results() throws Throwable {
		boolean isOneOrNoRecord = inventoryTransactionQueryPage.isOneOrNoTransactionDisplayed();
		Thread.sleep(2000);
		if (isOneOrNoRecord==false){
			inventoryTransactionQueryPage.selectRequiredRecord();	
		}
	}
	
	@Then("^I should see the status as \"([^\"]*)\" in the transaction query$")
	public void the_I_should_see_the_status_as_and_lock_code_as_in_the_transaction_query(String status)
			throws Throwable {
		Assert.assertEquals("Status in Inventory Transaction screen is not as expected", status,
				inventoryTransactionQueryPage.getStatus());
	}

	@When("^I navigate to miscellaneous tab$")
	public void i_navigate_to_miscellaneous_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}
	
	@Then("^I should see the updated ABV in the inventory transaction query page$")
	public void i_should_see_the_updated_ABV_in_the_inventory_transaction_query_page() throws Throwable {
		Assert.assertEquals("ABV is not as expected.", context.getABV(),inventoryTransactionQueryPage.getAbv());
	}

	@Then("^I should see the reason code as \"([^\"]*)\"$")
	public void i_should_see_the_reason_code_as(String reasonCode) throws Throwable {
		String actualReasonCode = inventoryTransactionQueryPage.getReasonCode();

		switch (reasonCode) {
		case "Damaged by Warehouse":
			Assert.assertEquals("Reason Code not displayed as expected", "DW", actualReasonCode);
			break;
		case "Expired Stock":
			Assert.assertEquals("Reason Code not displayed as expected", "EX", actualReasonCode);
			break;
		case "Head Office":
			Assert.assertEquals("Reason Code not displayed as expected", "HO", actualReasonCode);
			break;
		case "Hampers Stock":
			Assert.assertEquals("Reason Code not displayed as expected", "HS", actualReasonCode);
			break;
		case "Receiving Correction":
			Assert.assertEquals("Reason Code not displayed as expected", "IE", actualReasonCode);
			break;
		case "Infestation":
			Assert.assertEquals("Reason Code not displayed as expected", "IF", actualReasonCode);
			break;
		case "Outlets Stock":
			Assert.assertEquals("Reason Code not displayed as expected", "OS", actualReasonCode);
			break;
		case "Returns from RDC":
			Assert.assertEquals("Reason Code not displayed as expected", "RT", actualReasonCode);
			break;
		case "Stock Count":
			Assert.assertEquals("Reason Code not displayed as expected", "SC", actualReasonCode);
			break;
		case "Returns to Supplier":
			Assert.assertEquals("Reason Code not displayed as expected", "ST", actualReasonCode);
			break;
		}
		logger.debug("Reason code: " + actualReasonCode);
	}

	@When("^I navigate to miscellaneous2 tab$")
	public void i_navigate_to_miscellaneous2_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneous2Tab();
	}
	
	@Then("^I should see the readon code as \"([^\"]*)\"$")
	public void i_should_see_the_readon_code_as(String reasonCode) throws Throwable {
		String actualReasonCode = inventoryTransactionQueryPage.getReasonCode();
		Assert.assertEquals("Reason Code not displayed as expected", reasonCode, actualReasonCode);
		logger.debug("Reason code: " + actualReasonCode);
	}

	@Then("^I should see the uploaded filename$")
	public void i_should_see_the_uploaded_filename() throws Throwable {
		String uploadedValue = inventoryTransactionQueryPage.getUploaded();
		String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
		if (uploadedValue.equalsIgnoreCase("N")) {
			Assert.assertNull("Uploaded File Name is not displayed as expected. Expected [NULL] but was ["
					+ uploadedFileName + "]", uploadedFileName);
		} else if (uploadedValue.equalsIgnoreCase("Y") && (!uploadedFileName.equals(null))) {
			Assert.assertNotNull("Uploaded File Name is not displayed as expected. Expected [Not NULL] but was ["
					+ uploadedFileName + "]", uploadedFileName);
		}
		logger.debug("Uploaded File Name: " + uploadedFileName);
	}

	@Given("^I select the code as \"([^\"]*)\" and enter the tag id \"([^\"]*)\"$")
	public void i_select_the_code_as_and_enter_the_tag_id(String code, String tagId) throws Throwable {
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}
	@Then("^the SKU Id and Reference should be displayed$")
	public void the_SKU_Id_and_Reference_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		boolean isNoRecordExists = inventoryTransactionQueryPage.isNoRecordsExists();
		if (!isNoRecordExists){
		String skuId = inventoryTransactionQueryPage.getSkuId();
		if (skuId.equals(null)) {
			failureList.add("SKU id is not as expected. Expected [Not NULL] but was [" + skuId + "]");
		}

		String reference = inventoryTransactionQueryPage.getReference();
		if (reference.equals(null)) {
			failureList.add("Reference is not as expected. Expected [Not NULL] but was [" + reference + "]");
		}
		}
		else {
			failureList.add("Record is not found. Expected [Record should be found] but was [" + isNoRecordExists + "]");
		}
		Assert.assertTrue(
				"Values are not found. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
		
	}

	
	@Then("^I should see the original quantity and updated quantity in the general tab$")
	public void i_should_see_the_original_quantity_and_updated_quantity_in_the_general_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String originalQty = inventoryTransactionQueryPage.getOriginalQty();
		String qtytoCheck = String.valueOf(context.getQtyOnHandBeforeAdjustment());
		if (!originalQty.equals(qtytoCheck)) {
			failureList.add("Original Quantity before Adjustment is not displayed as expected. Expected ["
					+ qtytoCheck + "] but was [" + originalQty + "]");
		}
		logger.debug("Original Quantity: " + originalQty);

		String updateQty = inventoryTransactionQueryPage.getUpdateQty();
		String updateQtyToCheck = String.valueOf(context.getCaseRatio());
		if (context.getAdjustmentType().equalsIgnoreCase("Decrement")) {
			if (!updateQty.equals("-" + updateQtyToCheck)) {
				failureList.add("Update Quantity is not displayed as expected. Expected [" + "-"
						+ updateQtyToCheck + "] but was [" + updateQty + "]");
			}
		} else if (context.getAdjustmentType().equalsIgnoreCase("Increment")) {

			if (!updateQty.equals(updateQtyToCheck)) {
				failureList.add("Update Quantity is not displayed as expected. Expected [" +updateQtyToCheck + "] but was [" + updateQty + "]");
			}
		}

		logger.debug("Update Quantity: " + updateQty);
		Assert.assertTrue(
				"Stock Adjustment attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
}
