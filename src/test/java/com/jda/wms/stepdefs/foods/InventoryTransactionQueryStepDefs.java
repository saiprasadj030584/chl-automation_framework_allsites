package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.InventoryTransactionDB;
import com.jda.wms.db.InventoryDB;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.SKUMaintenancePage;
import com.jda.wms.pages.foods.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryTransactionQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private final InventoryTransactionDB inventoryTransactionDB;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final SKUMaintenancePage sKUMaintenancePage;
	Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, ArrayList<String>> tagIDMap;
	private Verification verification;
	private InventoryDB inventoryDB;

	@Inject
	public InventoryTransactionQueryStepDefs(InventoryTransactionQueryPage inventoryTransactionQueryPage,
			Context context, JDAFooter jdaFooter, JdaHomePage jdaHomePage, SKUMaintenancePage sKUMaintenancePage,
			InventoryTransactionDB inventoryTransactionDB, 
			Verification verification, InventoryDB inventoryDB) {
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.sKUMaintenancePage = sKUMaintenancePage;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
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
	public void i_search_tag_id_with_transaction_code_as(String tagId, String code) throws Throwable {
		i_search_tag_id_with_transaction_code_as_and_transaction_date_as_current_date(tagId, code);
	}

	@When("^I search tag id \"([^\"]*)\" with transaction code as \"([^\"]*)\" and transaction date as current date$")
	public void i_search_tag_id_with_transaction_code_as_and_transaction_date_as_current_date(String tagId, String code)
			throws Throwable {
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterTagId(tagId);
		inventoryTransactionQueryPage.enterCode(code);
		inventoryTransactionQueryPage.enterTransactionDate();
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
				inventoryTransactionQueryPage.getStatus());
	}

	@When("^I navigate to miscellaneous tab$")
	public void i_navigate_to_miscellaneous_tab() throws Throwable {
		inventoryTransactionQueryPage.clickMiscellaneousTab();
	}

	@Then("^I should see the updated ABV in the inventory transaction query page$")
	public void i_should_see_the_updated_ABV_in_the_inventory_transaction_query_page() throws Throwable {
		Assert.assertEquals("ABV is not as expected.", context.getABV(), inventoryTransactionQueryPage.getABV());
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
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();
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
		} else if (uploadedValue.equalsIgnoreCase("Y") && (uploadedFileName.equals(null))) {
			Assert.assertNotNull("Uploaded File Name is not displayed as expected. Expected [Not NULL] but was ["
					+ uploadedFileName + "]", uploadedFileName);
		}
		logger.debug("Uploaded File Name: " + uploadedFileName);
	}

	@Given("^I select the code as \"([^\"]*)\" and enter the tag id \"([^\"]*)\"$")
	public void i_select_the_code_as_and_enter_the_tag_id(String code, String tagId) throws Throwable {
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the SKU Id and Reference should be displayed$")
	public void the_SKU_Id_and_Reference_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		boolean isNoRecordExists = inventoryTransactionQueryPage.isNoRecordsExists();
		if (!isNoRecordExists) {
			String skuId = inventoryTransactionQueryPage.getSkuId();
			if (skuId.equals(null)) {
				failureList.add("SKU id is not as expected. Expected [Not NULL] but was [" + skuId + "]");
			}

			String reference = inventoryTransactionQueryPage.getReference();
			if (reference.equals(null)) {
				failureList.add("Reference is not as expected. Expected [Not NULL] but was [" + reference + "]");
			}
		} else {
			failureList
					.add("Record is not found. Expected [Record should be found] but was [" + isNoRecordExists + "]");
		}
		Assert.assertTrue("Values are not found. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

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
		String lockStatus = inventoryTransactionQueryPage.getStatus();
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
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();

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

	/*
	 * @Then("^the ABV should be displayed$") public void
	 * the_ABV_should_be_displayed() throws Throwable { Assert.assertEquals(
	 * "ABV values are not matched", "value from context",
	 * inventoryTransactionQueryPage.getABV()); }
	 */

	@Then("^I should see the from location, to location, final location, uploaded status and uploaded file name for the tags$")
	public void i_should_see_the_from_location_to_location_final_location_uploaded_status_and_uploaded_file_name_for_the_tags()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<String, String> locationTagMap = context.getLocationForTagMap();

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

			inventoryTransactionQueryPage.clickMiscellaneous2Tab();

			String uploadedStatus = inventoryTransactionQueryPage.getUploaded();
			if (!uploadedStatus.equals("Y")) {
				failureList
						.add("Uploaded status not displayed as expected. Expected [Y] but was " + uploadedStatus + "]");
			}

			String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
			if (!uploadedFileName.contains("I0809")) {
				failureList.add("Uploaded File Name not displayed as expected. Expected [I0809] but was ["
						+ uploadedFileName + "]");
			}

			inventoryTransactionQueryPage.clickGeneralTab();
		}

		Assert.assertTrue("Inventory transanction details  not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^I should see the from location, to location, final location, uploaded status and uploaded file name$")
	public void i_should_see_the_from_location_to_location_final_location_uploaded_status_and_uploaded_file_name()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<String, String> locationForTagMap = context.getLocationForTagMap();

		for (String tagId : locationForTagMap.keySet()) {
			HashMap<String, String> inventoryTransactionDBDetails = inventoryTransactionDB
					.getInventoryTransactionDetails(tagId, "Putaway");
			
			String fromLocation = inventoryTransactionDBDetails.get("From Location");
			if (!fromLocation.equals("REC002")) {
				failureList.add("From Location not displayed as expected for " + tagId + ". Expected [REC002] but was ["
						+ fromLocation + "]");
			}

			String finalLocation = inventoryTransactionDBDetails.get("Final Location");
			String toLocation = inventoryTransactionDBDetails.get("To Location");
			if (!toLocation.equals(finalLocation)) {
				failureList.add("To location not  displayed as expected for " + tagId + ". Expected [" + finalLocation
						+ "]   but was [" + toLocation + "]");
			}

			String uploadedStatus = inventoryTransactionDBDetails.get("Uploaded Status");
			if (!uploadedStatus.equals("Y")) {
				failureList.add("Uploaded status not displayed as expected for " + tagId + ". Expected [Y] but was "
						+ uploadedStatus + "]");
			}

			String uploadedFileName = inventoryTransactionDBDetails.get("Uploaded File Name");
			if (!uploadedFileName.contains("I0809itl")) {
				failureList.add("Uploaded File Name not displayed as expected for " + tagId
						+ ". Expected [I0809] but was [" + uploadedFileName + "]");
			}
		}

		Assert.assertTrue("Inventory transanction details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I navigate to settings 2 tab in the user defined tab$")
	public void i_navigate_to_settings_2_tab_in_the_user_defined_tab() throws Throwable {
		inventoryTransactionQueryPage.clickSettings2Tab();
	}

	@Then("^the URN child should be displayed$")
	public void the_URN_child_should_be_displayed() throws Throwable {
		Assert.assertEquals("URN Child is not as expected.", inventoryTransactionQueryPage.getTagId(),
				inventoryTransactionQueryPage.getURNChild());
		logger.debug("URN Child: " + inventoryTransactionQueryPage.getURNChild());
	}

	@Then("^the URN child should be checked$")
	public void the_URN_child_should_be_checked() throws Throwable {
		HashMap<String, String> preAdviceHeaderDbDetails = inventoryTransactionDB
				.getInventoryTransactionUrn(context.getTagId(), "Receipt");

		Assert.assertEquals("URN Child is not as expected.", preAdviceHeaderDbDetails.get("Tag Id"),
				preAdviceHeaderDbDetails.get("URN Child"));
	}

	@Then("^the storage location, base UOM, case ratio ,into destination date should be displayed$")
	public void the_storage_location_base_UOM_case_ratio_into_destination_date_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		HashMap<String, String> inventoryTransactionCEDetails = inventoryTransactionDB
				.getInventoryTransactionCEUDDetails(context.getTagId(), "Receipt");

		// String storageLocation =
		// inventoryTransactionQueryPage.getStorageLocation();
		String storageLocation = inventoryTransactionCEDetails.get("Storage Location");

		if (!storageLocation.equals("0001")) {
			failureList.add("Storage Location is not as expected. Expected [0001] but was [" + storageLocation + "]");
		}
		logger.debug("storageLocation: " + storageLocation);

		// String baseUOM = inventoryTransactionQueryPage.getBaseUOM();
		String baseUOM = inventoryTransactionCEDetails.get("Base UOM");
		if (!baseUOM.equals("EA")) {
			failureList.add("Base UOM is not as expected. Expected [EA] but was [" + baseUOM + "]");
		}
		logger.debug("baseUOM: " + baseUOM);

		// String intoDestinationDate =
		// inventoryTransactionQueryPage.getIntoDestinationDate();
		String intoDestinationDate = inventoryTransactionCEDetails.get("Into Destination Date");
		if (intoDestinationDate.equals(null)) {
			failureList.add("Into Destination Date is not as expected. Expected [Not NULL] but was ["
					+ intoDestinationDate + "]");
		}
		logger.debug("intoDestinationDate: " + intoDestinationDate);

		// int caseRatio =
		// Integer.parseInt(inventoryTransactionQueryPage.getCaseRatio());
		int caseRatio = Integer.parseInt(inventoryTransactionCEDetails.get("Case Ratio"));
		if (!(caseRatio > 0)) {
			failureList.add("Case Ratio is not as expected. Expected [Not NULL] but was [" + caseRatio + "]");
		}
		logger.debug("caseRatio: " + caseRatio);

		Assert.assertTrue("Inventory transaction query user defined details are not as expected."
				+ Arrays.asList(failureList.toString()), failureList.isEmpty());
	}

	/*
	 * @Then(
	 * "^the original rotation id, rotation id, CE receipt type and under bond should be displayed$"
	 * ) public void
	 * the_original_rotation_id_rotation_id_CE_receipt_type_and_under_bond_should_be_displayed
	 * () throws Throwable { ArrayList<String> failureList = new
	 * ArrayList<String>();
	 * 
	 * String originalRotationId =
	 * inventoryTransactionQueryPage.getOriginalRotationId(); if
	 * (originalRotationId.equals(null)) { failureList.add(
	 * "Original Rotation ID is not as expected. Expected [Not Null] but was ["
	 * + originalRotationId + "]"); } logger.debug("originalRotationId: " +
	 * originalRotationId);
	 * 
	 * String rotationId = inventoryTransactionQueryPage.getRotationId(); if
	 * (rotationId.equals(null)) { failureList.add(
	 * "Rotation ID is not as expected. Expected [Not Null] but was [" +
	 * rotationId + "]"); } logger.debug("rotationId: " + rotationId);
	 * 
	 * String ceReceiptType = inventoryTransactionQueryPage.getCEReceiptType();
	 * if (ceReceiptType.equals(null)) { failureList.add(
	 * "CE Receipt Type is not as expected. Expected [Not NULL] but was [" +
	 * ceReceiptType + "]"); } logger.debug("ceReceiptType: " + ceReceiptType);
	 * 
	 * String underBond = inventoryTransactionQueryPage.getUnderBond(); if
	 * (underBond.equals(null)) { failureList.add(
	 * "Under Bond is not as expected. Expected [Not NULL] but was [" +
	 * underBond + "]"); } logger.debug("underBond: " + underBond);
	 * 
	 * Assert.assertTrue(
	 * "Inventory transaction query Customs & Excise tab details are not as expected."
	 * + Arrays.asList(failureList.toString()), failureList.isEmpty()); }
	 */

	@Then("^the original rotation id, rotation id, CE receipt type and under bond should be displayed$")
	public void the_original_rotation_id_rotation_id_CE_receipt_type_and_under_bond_should_be_displayed()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		HashMap<String, String> inventoryTransactionCEDetails = inventoryTransactionDB
				.getInventoryTransactionCEUDDetails(context.getTagId(), "Receipt");

		String originalRotationId = inventoryTransactionCEDetails.get("Original Rotation Id");
		if (originalRotationId.equals(null)) {
			failureList.add("Original Rotation ID is not as expected. Expected [Not Null] but was ["
					+ originalRotationId + "]");
		}
		logger.debug("originalRotationId: " + originalRotationId);

		String rotationId = inventoryTransactionCEDetails.get("Rotation Id");
		if (rotationId.equals(null)) {
			failureList.add("Rotation ID is not as expected. Expected [Not Null] but was [" + rotationId + "]");
		}
		logger.debug("rotationId: " + rotationId);

		String ceReceiptType = inventoryTransactionCEDetails.get("Receipt Type");
		if (ceReceiptType.equals(null)) {
			failureList.add("CE Receipt Type is not as expected. Expected [Not NULL] but was [" + ceReceiptType + "]");
		}
		logger.debug("ceReceiptType: " + ceReceiptType);

		String underBond = inventoryTransactionCEDetails.get("Under Bond");
		if (underBond.equals(null)) {
			failureList.add("Under Bond is not as expected. Expected [Not NULL] but was [" + underBond + "]");
		}
		logger.debug("underBond: " + underBond);

		Assert.assertTrue("Inventory transaction query Customs & Excise tab details are not as expected."
				+ Arrays.asList(failureList.toString()), failureList.isEmpty());
	}

	@Then("^the description, from location, to location, update qty, reference and SKU should be displayed in the general tab$")
	public void the_description_from_location_to_location_update_qty_reference_and_SKU_should_be_displayed_in_the_general_tab()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		if (inventoryTransactionQueryPage.isNoRecordsExists() == false) {
			String description = inventoryTransactionQueryPage.getDescription();
			if (description.equals(null)) {
				failureList.add(
						"Description in ITL is not as expected. Expected [Not Null] but was [" + description + "]");
			}
			logger.debug("description: " + description);

			String fromLocation = inventoryTransactionQueryPage.getFromLocation();
			if (fromLocation.equals(null)) {
				failureList.add("From Location is not as expected. Expected [Not Null] but was [" + fromLocation + "]");
			}
			logger.debug("fromLocation: " + fromLocation);

			String toLocation = inventoryTransactionQueryPage.getToLocation();
			if (toLocation.equals(null)) {
				failureList.add("To Location is not as expected. Expected [Not NULL] but was [" + toLocation + "]");
			}
			logger.debug("toLocation: " + toLocation);

			Integer updateQty = Integer.parseInt(inventoryTransactionQueryPage.getUpdateQty());
			if (!(updateQty > 0)) {
				failureList.add("Update Quantity is not as expected. Expected [Not NULL] but was [" + updateQty + "]");
			}
			logger.debug("updateQty: " + updateQty);

			String reference = inventoryTransactionQueryPage.getReference();
			if (reference.equals(null)) {
				failureList.add(
						"Reference (PO Number) is not as expected. Expected [Not NULL] but was [" + reference + "]");
			}
			logger.debug("reference: " + reference);

			Assert.assertTrue("Inventory transaction General tab details are not as expected."
					+ Arrays.asList(failureList.toString()), failureList.isEmpty());
		} else {
			logger.debug("No Records exists for the query");
			Assert.fail("No Records exists for the query");
		}
	}

	@Then("^ the inventory transaction details should be checked in inventory transaction table$")
	public void the_inventory_transaction_details_should_be_checked_in_inventory_transaction_table() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		HashMap<String, String> InventoryTransactionDbDetails = inventoryTransactionDB
				.getInventoryTransactionDetails(context.getTagId(), "Receipt");

		String description = inventoryTransactionDB.getDescription(context.getTagId(), "Receipt", context.getSkuId());
		if (description.equals(null)) {
			failureList.add("Description in ITL is not as expected. Expected [Not Null] but was [" + description + "]");
		}
		logger.debug("description: " + description);

		String fromLocation = InventoryTransactionDbDetails.get("From Location");
		if (fromLocation.equals(null)) {
			failureList.add("From Location is not as expected. Expected [Not Null] but was [" + fromLocation + "]");
		}
		logger.debug("fromLocation: " + fromLocation);

		String toLocation = InventoryTransactionDbDetails.get("To Location");
		if (toLocation.equals(null)) {
			failureList.add("To Location is not as expected. Expected [Not NULL] but was [" + toLocation + "]");
		}
		logger.debug("toLocation: " + toLocation);

		Integer updateQty = Integer.parseInt(InventoryTransactionDbDetails.get("Update Qty"));
		if (!(updateQty > 0)) {
			failureList.add("Update Quantity is not as expected. Expected [Not NULL] but was [" + updateQty + "]");
		}
		logger.debug("updateQty: " + updateQty);

		String reference = InventoryTransactionDbDetails.get("Reference");
		if (reference.equals(null)) {
			failureList
					.add("Reference (PO Number) is not as expected. Expected [Not NULL] but was [" + reference + "]");
		}
		logger.debug("reference: " + reference);

		if (context.getAllocationGroup().equals("EXPIRY")) {
			String expiryDate = InventoryTransactionDbDetails.get("Expiry Date");
			if (expiryDate.equals(null)) {
				failureList.add("Expiry Date is not as expected. Expected [Not Null] but was [" + expiryDate + "]");
			}
			logger.debug("expiryDate: " + expiryDate);
		}

		String userId = InventoryTransactionDbDetails.get("User Id");
		if (userId.equals(null)) {
			failureList.add("User ID is not as expected. Expected [Not Null] but was [" + userId + "]");
		}
		logger.debug("userId: " + userId);

		String workstation = inventoryTransactionQueryPage.getWorkstation();
		if (!(workstation.contains("RDT"))) {
			failureList.add("Workstation is not as expected. Expected [RDT*] but was [" + workstation + "]");
		}
		logger.debug("workstation: " + workstation);

		String rdtUserMode = InventoryTransactionDbDetails.get("RDT User Mode");
		if (!rdtUserMode.equals("Pre-Advice Receive")) {
			failureList.add(
					"RDT User Mode is not as expected. Expected [Pre-Advice Receive] but was [" + rdtUserMode + "]");
		}
		logger.debug("rDTUserMode: " + rdtUserMode);

		String supplier = InventoryTransactionDbDetails.get("Supplier");
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}
		logger.debug("supplier: " + supplier);

		String palletType = InventoryTransactionDbDetails.get("Pallet Type");
		if (palletType.equals(null)) {
			failureList.add("Pallet Type is not as expected. Expected [0001] but was [" + palletType + "]");
		}
		logger.debug("palletType: " + palletType);

		String packConfig = InventoryTransactionDbDetails.get("Pack Config");
		String skuId = InventoryTransactionDbDetails.get("Sku Id");
		if (!packConfig.contains(skuId)) {
			failureList.add("Pack Config is not as expected. Expected [" + skuId + "*] but was [" + packConfig + "]");
		}
		logger.debug("packConfig: " + packConfig);

		String uploaded = InventoryTransactionDbDetails.get("Uploaded Status");
		String uploadedFileName = InventoryTransactionDbDetails.get("Uploaded File Name");
		if ((uploaded.equals("Y")) || (uploaded.equalsIgnoreCase("Yes"))) {
			if (!uploadedFileName.contains("I0808itl")) {
				failureList.add("Upload file name is not as expected. Expected [I0808itl*.txt] but was ["
						+ uploadedFileName + "]");
			}
		}
		logger.debug("uploaded: " + uploaded);
		logger.debug("uploadedFileName: " + uploadedFileName);

		String uploadedDate = InventoryTransactionDbDetails.get("Uploaded Date");
		if (uploadedDate.equals(null)) {
			failureList.add("Uploaded Date is not as expected. Expected [Not Null] but was [" + uploadedDate + "]");
		}
		logger.debug("uploadedDate: " + uploadedDate);

		String uploadedTime = InventoryTransactionDbDetails.get("Uploaded Time");
		if (uploadedTime.equals(null)) {
			failureList.add("Uploaded Time is not as expected. Expected [Not Null] but was [" + uploadedTime + "]");
		}
		logger.debug("uploadedTime: " + uploadedTime);

		Assert.assertTrue("Inventory transaction are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the expiry date,user id, workstation, RDT user mode and supplier details should be displayed$")
	public void the_expiry_date_user_id_workstation_RDT_user_mode_and_supplier_details_should_be_displayed()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		if (context.getAllocationGroup().equals("EXPIRY")) {
			String expiryDate = inventoryTransactionQueryPage.getExpiryDate();
			if (expiryDate.equals(null)) {
				failureList.add("Expiry Date is not as expected. Expected [Not Null] but was [" + expiryDate + "]");
			}
			logger.debug("expiryDate: " + expiryDate);
		}

		String userId = inventoryTransactionQueryPage.getUserId();
		if (userId.equals(null)) {
			failureList.add("User ID is not as expected. Expected [Not Null] but was [" + userId + "]");
		}
		logger.debug("userId: " + userId);

		String workstation = inventoryTransactionQueryPage.getWorkstation();
		if (!(workstation.contains("RDT"))) {
			failureList.add("Workstation is not as expected. Expected [RDT*] but was [" + workstation + "]");
		}
		logger.debug("workstation: " + workstation);

		String rdtUserMode = inventoryTransactionQueryPage.getRDTUserMode();
		if (!rdtUserMode.equals("Pre-Advice Receive")) {
			failureList.add(
					"RDT User Mode is not as expected. Expected [Pre-Advice Receive] but was [" + rdtUserMode + "]");
		}
		logger.debug("rDTUserMode: " + rdtUserMode);

		String supplier = inventoryTransactionQueryPage.getSupplier();
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}
		logger.debug("supplier: " + supplier);

		Assert.assertTrue("Inventory transaction query miscellaneous tab details are not as expected."
				+ Arrays.asList(failureList.toString()), failureList.isEmpty());
	}

	@Then("^the pallet type, pack config, uploaded status, uploaded filename, uploaded date and uploaded time should be displayed$")
	public void the_pallet_type_pack_config_uploaded_status_uploaded_filename_uploaded_date_and_uploaded_time_should_be_displayed()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String palletType = inventoryTransactionQueryPage.getPalletType();
		if (palletType.equals(null)) {
			failureList.add("Pallet Type is not as expected. Expected [0001] but was [" + palletType + "]");
		}
		logger.debug("palletType: " + palletType);

		String packConfig = inventoryTransactionQueryPage.getPackConfig();
		String skuId = inventoryTransactionQueryPage.getSkuId();
		if (!packConfig.contains(skuId)) {
			failureList.add("Pack Config is not as expected. Expected [" + skuId + "*] but was [" + packConfig + "]");
		}
		logger.debug("packConfig: " + packConfig);

		String uploaded = inventoryTransactionQueryPage.getUploaded();

		if (!((uploaded.equalsIgnoreCase("No")) || (uploaded.equals("N")))) {
			String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
			if ((uploaded.equals("Y")) || (uploaded.equalsIgnoreCase("Yes"))) {
				if (!uploadedFileName.contains("I0808itl")) {
					failureList.add("Upload file name is not as expected. Expected [I0808itl*.txt] but was ["
							+ uploadedFileName + "]");
				}
			}
			logger.debug("uploadedFileName: " + uploadedFileName);

			String uploadedDate = inventoryTransactionQueryPage.getUploadedDate();
			if (uploadedDate.equals(null)) {
				failureList.add("Uploaded Date is not as expected. Expected [Not Null] but was [" + uploadedDate + "]");
			}
			logger.debug("uploadedDate: " + uploadedDate);

			String uploadedTime = inventoryTransactionQueryPage.getUploadedTime();
			if (uploadedTime.equals(null)) {
				failureList.add("Uploaded Time is not as expected. Expected [Not Null] but was [" + uploadedTime + "]");
			}
			logger.debug("uploadedTime: " + uploadedTime);

		}
		logger.debug("uploaded: " + uploaded);

		Assert.assertTrue("Inventory transaction query miscellaneous2 tab details are not as expected."
				+ Arrays.asList(failureList.toString()), failureList.isEmpty());
	}

	/*
	 * @Then(
	 * "^the originator, originator reference, CE consignment id, document date, document time should be displayed for BWS$"
	 * ) public void
	 * the_originator_originator_reference_CE_consignment_id_document_date_document_time_should_be_displayed_for_BWS
	 * () throws Throwable {
	 * 
	 * ArrayList<String> failureList = new ArrayList<String>();
	 * 
	 * String originator = inventoryTransactionQueryPage.getOriginator(); if
	 * (originator.equals(null)) { failureList.add(
	 * "originator is not as expected. Expected [Not Null] but was [" +
	 * originator + "]"); } logger.debug("originator: " + originator);
	 * 
	 * String originatorReference =
	 * inventoryTransactionQueryPage.getOriginatorReference(); if
	 * (originatorReference.equals(null)) { failureList.add(
	 * "originator Reference is not as expected. Expected [Not Null] but was ["
	 * + originatorReference + "]"); } logger.debug("originatorReference: " +
	 * originatorReference);
	 * 
	 * String ceConsignmentId =
	 * inventoryTransactionQueryPage.getCEConsignmentId(); if
	 * (ceConsignmentId.equals(null)) { failureList .add(
	 * "CE consignment id is not as expected. Expected [Not Null] but was [" +
	 * ceConsignmentId + "]"); } logger.debug("CE consignment id: " +
	 * ceConsignmentId);
	 * 
	 * String documentDate = inventoryTransactionQueryPage.getDocumentDate(); if
	 * (documentDate.equals(null)) { failureList.add(
	 * "Document Date is not as expected. Expected [Not Null] but was [" +
	 * documentDate + "]"); } logger.debug("Document Date: " + documentDate);
	 * 
	 * String documentTime = inventoryTransactionQueryPage.getDocumentTime(); if
	 * (documentTime.equals(null)) { failureList.add(
	 * "Document Time is not as expected. Expected [Not Null] but was [" +
	 * documentTime + "]"); } logger.debug("Document Time: " + documentTime);
	 * 
	 * Assert.assertTrue(
	 * "Inventory transaction query Customs & Excise tab details are not as expected for BWS."
	 * + Arrays.asList(failureList.toString()), failureList.isEmpty()); }
	 */

	@Then("^the originator, originator reference, CE consignment id, document date, document time should be displayed for BWS$")
	public void the_originator_originator_reference_CE_consignment_id_document_date_document_time_should_be_displayed_for_BWS()
			throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		HashMap<String, String> inventoryTransactionCEDetails = inventoryTransactionDB
				.getInventoryTransactionCEUDDetails(context.getTagId(), "Receipt");

		String originator = inventoryTransactionCEDetails.get("Originator");
		if (originator.equals(null)) {
			failureList.add("originator is not as expected. Expected [Not Null] but was [" + originator + "]");
		}
		logger.debug("originator: " + originator);

		String originatorReference = inventoryTransactionCEDetails.get("Originator Reference");
		if (originatorReference.equals(null)) {
			failureList.add("originator Reference is not as expected. Expected [Not Null] but was ["
					+ originatorReference + "]");
		}
		logger.debug("originatorReference: " + originatorReference);

		String ceConsignmentId = inventoryTransactionCEDetails.get("Consignment Id");
		if (ceConsignmentId.equals(null)) {
			failureList
					.add("CE consignment id is not as expected. Expected [Not Null] but was [" + ceConsignmentId + "]");
		}
		logger.debug("CE consignment id: " + ceConsignmentId);

		String documentDate = inventoryTransactionCEDetails.get("Document DueDate");
		if (documentDate.equals(null)) {
			failureList.add("Document Date is not as expected. Expected [Not Null] but was [" + documentDate + "]");
		}
		logger.debug("Document Date: " + documentDate);

		Assert.assertTrue("Inventory transaction query Customs & Excise tab details are not as expected for BWS."
				+ Arrays.asList(failureList.toString()), failureList.isEmpty());
	}

	@Then("^ABV percentage and vintage should be displayed for BWS$")
	public void abv_percentage_and_vintage_should_be_displayed_for_BWS() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		HashMap<String, String> inventoryTransactionCEDetails = inventoryTransactionDB
				.getInventoryTransactionCEUDDetails(context.getTagId(), "Receipt");
		// String abvPercentage = inventoryTransactionQueryPage.getABV();
		String abvPercentage = inventoryTransactionCEDetails.get("ABV");
		if (abvPercentage.equals(null)) {
			failureList.add("ABV Value is not as expected. Expected [Not Null] but was [" + abvPercentage + "]");
		}
		logger.debug("abvPercentage: " + abvPercentage);

		// String vintage = inventoryTransactionQueryPage.getVintage();
		String vintage = inventoryTransactionCEDetails.get("Vintage");
		if (vintage.equals(null)) {
			failureList.add("vintage is not as expected. Expected [Not Null] but was [" + vintage + "]");
		}
		logger.debug("vintage: " + vintage);

		Assert.assertTrue("Inventory transaction ABV and Vinatge values are not as expected for BWS."
				+ Arrays.asList(failureList.toString()), failureList.isEmpty());
	}

	@Then("^the ITL should be generated for the code \"([^\"]*)\"$")
	public void the_ITL_should_be_generated_for_the_code(String code) throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jdaFooter.clickExecuteButton();
		Assert.assertTrue("Record in ITL screen is not as expected", inventoryTransactionQueryPage.isRecordfound());
		Assert.assertEquals("Update Qty is not as expected", "-" + context.getQtyReverse(),
				inventoryTransactionQueryPage.getUpdateQty());
	}

	@Then("^the uploaded filename should be displayed$")
	public void the_uploaded_filename_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String uploadedValue = inventoryTransactionQueryPage.getUploaded();
		String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();

		if (uploadedValue.equalsIgnoreCase("N")) {
			failureList.add("Uploaded File Name is not displayed as expected. Expected [NULL] but was ["
					+ uploadedFileName + "]");
		} else if (uploadedValue.equalsIgnoreCase("Y") && (uploadedFileName.equals(null))) {
			failureList.add("Uploaded File Name is not displayed as expected. Expected [Not NULL] but was ["
					+ uploadedFileName + "]");
		}

		if (!uploadedFileName.contains("I0808itl")) {
			failureList.add(
					"Upload file name is not as expected. Expected [I0808itl*.txt] but was [" + uploadedFileName + "]");
		}

		logger.debug("Uploaded File Name: " + uploadedFileName);
		Assert.assertTrue("uploaded file name details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for the received stock in inventory transaction table$")
	public void the_goods_receipt_should_be_generated_for_the_received_stock_in_inventory_transaction_table()
			throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();

		String tagID = null;
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			context.setAllocationGroup(purchaseOrderMap.get(key).get("AllocationGroup"));

			for (int s = 0; s < tagIDMap.get(sku).size(); s++) {
				tagID = tagIDMap.get(sku).get(s);
				jdaFooter.clickQueryButton();

				i_select_the_code_as_and_enter_the_tag_id("Receipt", tagID);

				the_description_from_location_to_location_update_qty_reference_and_SKU_should_be_displayed_in_the_general_tab();
				i_navigate_to_miscellaneous_tab();
				the_expiry_date_user_id_workstation_RDT_user_mode_and_supplier_details_should_be_displayed();
				i_navigate_to_miscellaneous2_tab();
				the_pallet_type_pack_config_uploaded_status_uploaded_filename_uploaded_date_and_uploaded_time_should_be_displayed();
				sKUMaintenancePage.clickCustomsAndExcise();

				if ((!context.getProductCategory().contains("Non-Bonded"))
						&& (!context.getProductCategory().contains("Ambient"))) {
					the_originator_originator_reference_CE_consignment_id_document_date_document_time_should_be_displayed_for_BWS();
				}

				the_original_rotation_id_rotation_id_CE_receipt_type_and_under_bond_should_be_displayed();
				sKUMaintenancePage.clickUserDefined();
				if (!context.getProductCategory().contains("Ambient")) {
					abv_percentage_and_vintage_should_be_displayed_for_BWS();
				}

				the_storage_location_base_UOM_case_ratio_into_destination_date_should_be_displayed();
				i_navigate_to_settings_2_tab_in_the_user_defined_tab();
				the_URN_child_should_be_displayed();
				inventoryTransactionQueryPage.clickUserDefinedSettings1Tab();
				inventoryTransactionQueryPage.clickGeneralTab();
			}
		}
	}

	@Then("^the goods receipt should be generate for the received stock in inventory transaction table$")
	public void the_goods_receipt_should_be_generate_for_the_received_stock_in_inventory_transaction_table()
			throws Throwable {

		String tagID = null;
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			context.setSkuId(sku);
			context.setAllocationGroup(purchaseOrderMap.get(key).get("Allocation Group"));

			for (int s = 0; s < tagIDMap.get(sku).size(); s++) {
				tagID = tagIDMap.get(sku).get(s);
				context.setTagId(tagID);
				the_inventory_transaction_details_should_be_checked_in_inventory_transaction_table();

				if ((!context.getProductCategory().contains("Non-Bonded"))
						&& (!context.getProductCategory().contains("Ambient"))) {
					the_originator_originator_reference_CE_consignment_id_document_date_document_time_should_be_displayed_for_BWS();
				}

				the_original_rotation_id_rotation_id_CE_receipt_type_and_under_bond_should_be_displayed();
				if (!context.getProductCategory().contains("Ambient")) {
					abv_percentage_and_vintage_should_be_displayed_for_BWS();
				}

				the_storage_location_base_UOM_case_ratio_into_destination_date_should_be_displayed();
				the_URN_child_should_be_checked();
			}
		}
	}

	@Then("^I should see the from location, to location and final location for the tag$")
	public void i_should_see_the_from_location_to_location_and_final_location_for_the_tag() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Putaway");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jdaFooter.clickExecuteButton();

		String fromLocation = inventoryTransactionQueryPage.getFromLocation();
		if (!fromLocation.equals("REC002")) {
			failureList.add("Uploaded File Name not displayed as expected for " + ". Expected [REC002] but was ["
					+ fromLocation + "]");
		}
		String finalLocation = inventoryTransactionQueryPage.getFinalLocation();
		String toLocation = inventoryTransactionQueryPage.getToLocation();

		if (!toLocation.equals(finalLocation)) {
			failureList.add("Uploaded File Name not displayed as expected for " + ". Expected [" + finalLocation
					+ "]   but was [" + toLocation + "]");
		}

		Assert.assertTrue(
				"Inventory general tab  not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		the_uploaded_status_and_uploaded_file_should_be_displayed();
	}

	@Given("^the order receipt should be generated in the inventory	for note \"([^\"]*)\"$")
	public void the_order_reciept_should_be_generated_in_inventory_for_note(String notes) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String reference = "6666164300";
		context.setCustomer("0437");
		context.setConsignmentID("WAVE30----1520170504");

		verification.verifyData("Customer", context.getCustomer(), inventoryDB.getCustomer(reference, notes),
				failureList);
		verification.verifyData("Consignment", context.getConsignmentID(), inventoryDB.getConsignment(reference, notes),
				failureList);

		verification.verifyData("FromStatus", "Complete", inventoryDB.getFromStatus(reference, notes), failureList);
		verification.verifyData("ToStatus", "Shipped", inventoryDB.getToStatus(reference, notes), failureList);
		verification.verifyData("UploadedDate", "Not NULL", inventoryDB.getUploadedDate(reference,  notes),
				failureList);
		if (inventoryDB.getUploadedFileName(reference, notes) != null) {
			verification.verifyData("Uploaded", "Y", inventoryDB.getUploaded(reference, notes), failureList);
		} else {
			verification.verifyData("Uploaded", "N", inventoryDB.getUploaded(reference, notes), failureList);
		}

		verification.verifyData("DwsPalletRef", "Not NULL", inventoryDB.getDwsPalletRef(reference, notes),
				failureList);
		verification.verifyData("IntoDestinationDate", "Not NULL",
				inventoryDB.getIntoDestinationDate(reference, notes), failureList);
		verification.verifyData("IfosOrderNum", "Not NULL", inventoryDB.getIfosOrderNum(reference, notes),
				failureList);

		Assert.assertTrue(
				"Inventory transaction query details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

}
