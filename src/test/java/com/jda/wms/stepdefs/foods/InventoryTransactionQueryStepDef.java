package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryTransactionQueryStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private JDAFooter jdaFooter;

	@Inject
	public InventoryTransactionQueryStepDef(InventoryTransactionQueryPage inventoryTransactionQueryPage,
			Context context, JDAFooter jdaFooter) {
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
		this.jdaFooter = jdaFooter;
	}

	@When("^I search tag id \"([^\"]*)\" and code as \"([^\"]*)\"$")
	public void i_search_tag_id_and_code_as(String tagId, String code) throws Throwable {
		context.setCode(code);
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
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

	@When("^I navigate to miscellaneous tab$")
	public void i_navigate_to_miscellaneous_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}

	@Then("^I should see the readon code as \"([^\"]*)\"$")
	public void i_should_see_the_readon_code_as(String reasonCode) throws Throwable {
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
}
