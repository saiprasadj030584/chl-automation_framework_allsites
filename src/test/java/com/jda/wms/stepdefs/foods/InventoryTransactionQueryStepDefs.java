package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Map;

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

	@When("^I select the adjusted stock from the results$")
	public void i_select_the_adjusted_stock_from_the_results() throws Throwable {
		boolean isOneOrNoRecord = inventoryTransactionQueryPage.isOneOrNoTransactionDisplayed();
		Thread.sleep(2000);
		if (isOneOrNoRecord == false) {
			inventoryTransactionQueryPage.selectRequiredRecord();
		}
	}

	@Then("^I should see the status as \"([^\"]*)\" in the transaction query$")
	public void the_I_should_see_the_status_as_and_lock_code_as_in_the_transaction_query(String status)
			throws Throwable {
		Assert.assertEquals("Status in Inventory Transaction screen is not as expected", status,
				inventoryTransactionQueryPage.getLockStatus());
	}

	@When("^I navigate to miscellaneous tab$")
	public void i_navigate_to_miscellaneous_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
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

	@Then("^I should see the original quantity and updated quantity in the general tab$")
	public void i_should_see_the_original_quantity_and_updated_quantity_in_the_general_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String originalQty = inventoryTransactionQueryPage.getOriginalQty();
		String qtytoCheck = String.valueOf(context.getQtyOnHandBeforeAdjustment());
		if (!originalQty.equals(qtytoCheck)) {
			failureList.add("Original Quantity before Adjustment is not displayed as expected. Expected [" + qtytoCheck
					+ "] but was [" + originalQty + "]");
		}
		logger.debug("Original Quantity: " + originalQty);

		String updateQty = inventoryTransactionQueryPage.getUpdateQty();
		String updateQtyToCheck = String.valueOf(context.getCaseRatio());
		if (context.getAdjustmentType().equalsIgnoreCase("Decrement")) {
			if (!updateQty.equals("-" + updateQtyToCheck)) {
				failureList.add("Update Quantity is not displayed as expected. Expected [" + "-" + updateQtyToCheck
						+ "] but was [" + updateQty + "]");
			}
		} else if (context.getAdjustmentType().equalsIgnoreCase("Increment")) {

			if (!updateQty.equals(updateQtyToCheck)) {
				failureList.add("Update Quantity is not displayed as expected. Expected [" + updateQtyToCheck
						+ "] but was [" + updateQty + "]");
			}
		}

		logger.debug("Update Quantity: " + updateQty);
		Assert.assertTrue(
				"Stock Adjustment attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^I search tag id \"([^\"]*)\" and select the code as \"([^\"]*)\"$")
	public void i_search_tag_id_and_select_the_code_as(String tagId, String code) throws Throwable {
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
		// inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}

	@Then("^the  description,lock status, reference and lock code should be displayed$")
	public void the_description_lock_status_reference_and_lock_code_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String description = inventoryTransactionQueryPage.getDescription();
		if (description.equals(null)) {
			failureList.add("Description is not as expected. Expectecd [Not NULL] but was " + description + "]");
		}
		String lockStatus = inventoryTransactionQueryPage.getLockStatus();
		if (lockStatus.equals(null)) {
			failureList.add("Lock Status is not as expected. Expectecd [Not NULL] but was " + lockStatus + "]");
		}
		String reference = inventoryTransactionQueryPage.getReference();
		if (reference.equals(null)) {
			failureList.add("Reference is not as expected. Expectecd [Not NULL] but was " + reference + "]");
		}
		String lockCode = inventoryTransactionQueryPage.getDescription();
		if (lockCode.equals(null)) {
			failureList.add("Lock Code is not as expected. Expectecd [Not NULL] but was " + lockCode + "]");
		}
		Assert.assertTrue("ITL General details are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the reason code, supplier and RDT user mode should be displayed$")
	public void the_reason_code_supplier_and_RDT_user_mode_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String reasonCode = inventoryTransactionQueryPage.getReasonCode();
		if (reasonCode.equals(null)) {
			failureList.add("Reason Code is not as expected. Expectecd [Not NULL] but was " + reasonCode + "]");
		}

		String supplier = inventoryTransactionQueryPage.getSupplier();
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expectecd [Not NULL] but was " + supplier + "]");
		}

		String rdtUserMode = inventoryTransactionQueryPage.getRDTUserMode();
		if (rdtUserMode.equals(null)) {
			failureList.add("Supplier is not as expected. Expectecd [Not NULL] but was " + rdtUserMode + "]");
		}
		Assert.assertTrue(
				"ITL miscellaneous details are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	@Then("^the uploaded status and uploaded file should be displayed$")
	public void the_uploaded_status_and_uploaded_file_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		inventoryTransactionQueryPage.navigateToMiscellaneous2Tab();

		String uploadedStatus = inventoryTransactionQueryPage.getUploaded();
		if (!uploadedStatus.equals("Y")) {
			failureList.add("Uploaded status not displayed as expected. Expected [Y] but was " + uploadedStatus + "]");
		}

		String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
		if (!uploadedFileName.contains("I0809")) {
			failureList.add("Uploaded File Name not displayed as expected. Expected [I0809] but was ["
					+ uploadedFileName + "]");
		}

		Assert.assertTrue("Inventory miscellaneous2 tab  not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^i navigate to user defined tab$")
	public void i_navigate_to_user_defined_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToUserDefinedTab();
	}

	@Then("^the ABV should be displayed$")
	public void the_ABV_should_be_displayed() throws Throwable {
		// TODO get abv value from context
		Assert.assertEquals("ABV values are not matched", "value from context", inventoryTransactionQueryPage.getABV());
	}

	@Then("^I should see the from location, to location and final location for the tag$")
	public void i_should_see_the_from_location_to_location_and_final_location_for_the_tag() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<String, String> locationTagMap = context.getLocationPerTagMap();
		for (String tagId : locationTagMap.keySet()) {
			jdaFooter.clickQueryButton();
			inventoryTransactionQueryPage.selectCode("Putaway");
			inventoryTransactionQueryPage.enterTagId(tagId);
			jdaFooter.clickExecuteButton();

			String fromLocation = inventoryTransactionQueryPage.getFromLocation();
			if (!fromLocation.equals("REC002")) {
				failureList.add("Uploaded File Name not displayed as expected for " + tagId
						+ ". Expected [REC002] but was [" + fromLocation + "]");
			}
			String finalLocation = inventoryTransactionQueryPage.getFinalLocation();
			String toLocation = inventoryTransactionQueryPage.getToLocation();

			if (!toLocation.equals(finalLocation)) {
				failureList.add("Uploaded File Name not displayed as expected for " + tagId + ". Expected ["
						+ finalLocation + "]   but was [" + toLocation + "]");
			}
		}
		Assert.assertTrue(
				"Inventory general tab  not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		the_uploaded_status_and_uploaded_file_should_be_displayed();
	}

}
