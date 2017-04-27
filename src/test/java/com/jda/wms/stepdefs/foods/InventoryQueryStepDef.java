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
import com.jda.wms.pages.foods.JdaHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryQueryStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private Context context;

	@Inject
	public InventoryQueryStepDef(InventoryQueryPage inventoryQueryPage, Context context, JdaHomePage jdaHomePage,
			JDAFooter jdaFooter) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		// context.setTagId(tagId);
		// context.setStatus(status);

		inventoryQueryPage.searchTagId(tagId);
		logger.debug("Tag ID: " + tagId);

		String inventorySKUId = inventoryQueryPage.getInventorySKUId();
		context.setSkuId(inventorySKUId);
		logger.debug("SKU ID: " + inventorySKUId);

		String productStatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("Status is not displayed as expected", status, productStatus);
		logger.debug("Inventory Query - Status: " + productStatus);

		String qtyOnHandBfrAdjustment = inventoryQueryPage.getQtyOnHand();
		// context.setqtyOnHandBfrAdjustment(qtyOnHandBfrAdjustment);
		logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

		String caseRatio = inventoryQueryPage.getcaseRatio();
		// context.setCaseRatio(caseRatio);
		logger.debug("Case Ratio: " + caseRatio);
	}

	@Then("^the general details should be displayed after receiving$")
	public void the_general_details_should_be_displayed_after_receiving() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String skuId = inventoryQueryPage.getInventorySKUId();
		if (skuId.equals(null)) {
			failureList.add("SKU Id is not displayed as expected. Expected [Not NULL value] but was [" + skuId + "]");
		}

		String siteId = inventoryQueryPage.getSiteId();
		if (siteId.equals(null)) {
			failureList.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + siteId + "]");
		}

		String location = inventoryQueryPage.getLocation();
		if (location.equals(null)) {
			failureList
					.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + location + "]");
		}

		String description = inventoryQueryPage.getDescription();
		if (description.equals(null)) {
			failureList.add("Description is not displayed as expected. Expected [Not NULL value] but was ["
					+ description + "]");
		}

		String packConfig = inventoryQueryPage.getPackConfig();
		if (packConfig.equals(null)) {
			failureList.add(
					"Pack Config is not displayed as expected. Expected [Not NULL value] but was [" + packConfig + "]");
		}

		String status = inventoryQueryPage.getStatus();
		if (status.equals(null)) {
			failureList.add("Status is not displayed as expected. Expected [Not NULL value] but was [" + status + "]");
		}

		String trackingLevel = inventoryQueryPage.getTrackingLevel();
		if (!trackingLevel.equals("EA")) {
			failureList
					.add("Tracking Level is not displayed as expected. Expected [EA] but was [" + trackingLevel + "]");
		}

		String caseRatio = inventoryQueryPage.getCaseRatio();
		int qtyOnHand = inventoryQueryPage.getQtyOnhand();
		int qtyReceived = 10;// context.setQtyReceivedfromPutty();
		int calQtyonHand = qtyReceived * Integer.parseInt(caseRatio);
		if (calQtyonHand != qtyOnHand) {
			Assert.fail("Quantities are not matched while received from putty. Quanitity on hand: " + qtyOnHand
					+ ", Calculated Quantity: " + calQtyonHand);
		}

		Assert.assertTrue(
				"Inventory query general details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the miscellaneous details should be displayed after receiving$")
	public void the_miscellaneous_details_should_be_displayed_after_receiving() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String palletType = inventoryQueryPage.getPalletType();
		if (palletType.equals(null)) {
			failureList.add("Pallet Type is not displayed as expected. Expected [Not as NULL value] but was ["
					+ palletType + "]");
		}

		String receiptID = inventoryQueryPage.getReceiptId();
		if (receiptID.equals(null)) {
			failureList.add("Receipt Id is not displayed as expected. Expected [Not as NULL value] but was ["
					+ receiptID + "]");
		}

		String supplier = inventoryQueryPage.getSupplier();
		if (supplier.equals(null)) {
			failureList.add(
					"Supplier is not displayed as expected. Expected [Not as NULL value] but was [" + supplier + "]");
		}

		String inventoryCreatedBy = inventoryQueryPage.getInventoryCreatedBy();
		if (inventoryCreatedBy.equals(null)) {
			failureList.add("Inventory Created By is not displayed as expected. Expected [Not as NULL value] but was ["
					+ inventoryCreatedBy + "]");
		}

		Assert.assertTrue(
				"Inventory query Miscellaneous details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the user defined details should be displayed after receiving$")
	public void the_user_defined_details_should_be_displayed_after_receiving() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String storageLocation = inventoryQueryPage.getStorageLocation();
		if (!storageLocation.equals("0001")) {
			failureList.add(
					"Storage Location is not displayed as expected. Expected [0001] but was [" + storageLocation + "]");
		}

		String baseUOM = inventoryQueryPage.getBaseUOM();
		if (!baseUOM.equals("EA")) {
			failureList.add("Base UOM is not displayed as expected. Expected [EA] but was [" + baseUOM + "]");
		}

		String productGroup = inventoryQueryPage.getStatus();
		if (productGroup.equals(null)) {
			failureList.add("Product Group is not displayed as expected. Expected [Not NULL value] but was ["
					+ productGroup + "]");
		}

		Assert.assertTrue(
				"Inventory query user defined details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^enter tag id value receiving from putty \"([^\"]*)\"$")
	public void enter_tag_id_value_receiving_from_putty(String tagId) throws Throwable {
		inventoryQueryPage.enterTagId(tagId);
	}

	@Given("^I navigate to inventory query miscellaneous tab$")
	public void i_navigate_to_inventory_query_miscellaneous_tab() throws Throwable {
		inventoryQueryPage.navigateToMiscellaneousTab();
	}

	@Given("^I navigate to inventory query user defined tab$")
	public void i_navigate_to_inventory_query_user_defined_tab() throws Throwable {
		inventoryQueryPage.navigateToMiscellaneousTab();
	}

	@Given("^the iventory query details are displayed for given tag Id \"([^\"]*)\"$")
	public void the_iventory_query_details_are_displayed_for_given_tag_Id(String tagId) throws Throwable {
		inventoryQueryPage.enterTagId(tagId);
		ArrayList<String> failureList = new ArrayList<String>();
		String skuId = inventoryQueryPage.getInventorySKUId();
		if (skuId.equals(null)) {
			failureList.add("SKU Id is not displayed as expected. Expected [Not NULL value] but was [" + skuId + "]");
		}

		String siteId = inventoryQueryPage.getSiteId();
		if (siteId.equals(null)) {
			failureList.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + siteId + "]");
		}

		String location = inventoryQueryPage.getLocation();
		if (location.equals(null)) {
			failureList
					.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + location + "]");
		}

		String description = inventoryQueryPage.getDescription();
		if (description.equals(null)) {
			failureList.add("Description is not displayed as expected. Expected [Not NULL value] but was ["
					+ description + "]");
		}

		String packConfig = inventoryQueryPage.getPackConfig();
		if (packConfig.equals(null)) {
			failureList.add(
					"Pack Config is not displayed as expected. Expected [Not NULL value] but was [" + packConfig + "]");
		}

		String status = inventoryQueryPage.getStatus();
		if (status.equals(null)) {
			failureList.add("Status is not displayed as expected. Expected [Not NULL value] but was [" + status + "]");
		}

		String trackingLevel = inventoryQueryPage.getTrackingLevel();
		if (!trackingLevel.equals("EA")) {
			failureList
					.add("Tracking Level is not displayed as expected. Expected [EA] but was [" + trackingLevel + "]");
		}

		String caseRatio = inventoryQueryPage.getCaseRatio();
		int qtyOnHand = inventoryQueryPage.getQtyOnhand();
		int qtyReceived = 10;// context.setQtyReceivedfromPutty();
		int calQtyonHand = qtyReceived * Integer.parseInt(caseRatio);
		if (calQtyonHand != qtyOnHand) {
			Assert.fail("Quantities are not matched while received from putty. Quanitity on hand: " + qtyOnHand
					+ ", Calculated Quantity: " + calQtyonHand);
		}

		String storageLocation = inventoryQueryPage.getStorageLocation();
		if (!storageLocation.equals("0001")) {
			failureList.add(
					"Storage Location is not displayed as expected. Expected [0001] but was [" + storageLocation + "]");
		}

		String baseUOM = inventoryQueryPage.getBaseUOM();
		if (!baseUOM.equals("EA")) {
			failureList.add("Base UOM is not displayed as expected. Expected [EA] but was [" + baseUOM + "]");
		}

		String productGroup = inventoryQueryPage.getStatus();
		if (productGroup.equals(null)) {
			failureList.add("Product Group is not displayed as expected. Expected [Not NULL value] but was ["
					+ productGroup + "]");
		}

		Assert.assertTrue("Inventory query details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

	}

	@Then("^the status should be verified on preadvice header page for po Id \"([^\"]*)\"$")
	public void the_status_should_be_verified_on_preadvice_header_page_for_po_Id(String preAdviceHeaderId)
			throws Throwable {
		// preAdviceHeaderMaintenancePage.enterPreAdviceHeaderId(preAdviceId);
		// Assert.assertEquals("Pre advice Header status does not match",
		// "Complete", preAdviceHeaderMaintenancePage.getStatus());
	}

	@When("^I navigate to inventory query page and search the tag id \"([^\"]*)\"$")
	public void i_navigate_to_inventory_query_page_and_search_the_tag_id(String tagId) throws Throwable {
		jdaHomePage.navigateToInventoryQueryPage();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the quantity on hand, location, site id and status should be displayed in the general tab$")
	public void the_quantity_on_hand_location_site_id_and_status_should_be_displayed_in_the_general_tab()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String siteId = inventoryQueryPage.getSiteId();
		if (siteId.equals(null)) {
			failureList.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + siteId + "]");
		}

		String location = inventoryQueryPage.getLocation();
		if (location.equals(null)) {
			failureList
					.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + location + "]");
		}

		String status = inventoryQueryPage.getStatus();
		// TODO expected value from context
		if (!status.equals("Unlocked")) {
			failureList.add("Status is not displayed as expected. Expected [Not NULL value] but was [" + status + "]");
		}

		String caseRatio = inventoryQueryPage.getCaseRatio();
		String qtyOnHand = inventoryQueryPage.getQtyOnHand();
		// context.setQtyReceivedfromPutty();
		int qtyReceived = 10;
		int expectedQtyonHand = qtyReceived * Integer.parseInt(caseRatio);
		if (expectedQtyonHand != Integer.parseInt(qtyOnHand)) {
			Assert.fail("Quantity on hand is not expected. Expected [" + qtyOnHand + "] but was [" + expectedQtyonHand
					+ "]");
		}

		Assert.assertTrue(
				"Inventory query general details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the expiry date, pallet id, receipt id and supplier details should be displayed in the miscellaneous tab$")
	public void the_expiry_date_pallet_id_receipt_id_and_supplier_details_should_be_displayed_in_the_miscellaneous_tab()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		inventoryQueryPage.navigateToMiscellaneousTab();
		String expiryDate = inventoryQueryPage.getExpiryDate();
		// TODO get expected expiry date from context
		if (!expiryDate.equals("26/04/2019")) {
			failureList.add("Expiry Date is not as expected. Expected [ ] but was [" + expiryDate + "]");
		}

		String palletType = inventoryQueryPage.getPalletType();
		if (palletType.equals(null)) {
			failureList.add("Pallet Type is not as expected. Expected [Not NULL] but was [" + palletType + "]");
		}

		String receiptID = inventoryQueryPage.getReceiptId();
		if (receiptID.equals(null)) {
			failureList.add("Receipt Id is not as expected. Expected [Not NULL] but was [" + receiptID + "]");
		}

		String supplier = inventoryQueryPage.getSupplier();
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}

		String inventoryCreatedBy = inventoryQueryPage.getInventoryCreatedBy();
		if (inventoryCreatedBy.equals(null)) {
			failureList.add("Inventory Created By is not as expected. Expected [Not NULL] but was ["
					+ inventoryCreatedBy + "]");
		}

		Assert.assertTrue(
				"Inventory query miscellaneous details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the storage location, base UOM and product groud should be displayed$")
	public void the_storage_location_base_UOM_and_product_groud_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		inventoryQueryPage.navigateToUserDefinedTab();

		String storageLocation = inventoryQueryPage.getStorageLocation();
		if (!storageLocation.equals("0001")) {
			failureList.add("Storage Location is not as expected. Expected [0001] but was [" + storageLocation + "]");
		}

		String baseUOM = inventoryQueryPage.getBaseUOM();
		if (!baseUOM.equals("EA")) {
			failureList.add("Base UOM is not as expected. Expected [EA] but was [" + baseUOM + "]");
		}

		String productGroup = inventoryQueryPage.getProductGroup();
		if (productGroup.equals(null)) {
			failureList.add("Product Group is not as expected. Expected [Not NULL] but was [" + productGroup + "]");
		}

		Assert.assertTrue(
				"Inventory query user defined details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@When("^I navigate to pre-advice header maintenance page$")
	public void i_navigate_to_pre_advice_header_maintenance_page() throws Throwable {
		jdaHomePage.navigateToPreAdviceHeaderPage();
		jdaFooter.clickQueryButton();
		// TODO get PO id from context
		inventoryQueryPage.enterPOId("8050004526");
		jdaFooter.clickExecuteButton();
	}

	@Then("^the status should be displayed as \"([^\"]*)\"$")
	public void the_status_should_be_displayed_as(String status) throws Throwable {
		Assert.assertEquals("PO Status does not match", status, inventoryQueryPage.getPreAdviceStatus());
	}

}
