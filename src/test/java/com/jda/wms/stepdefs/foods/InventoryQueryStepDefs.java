package com.jda.wms.stepdefs.foods;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.InventoryDB;
import com.jda.wms.db.LocationDB;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.LocationPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final LocationPage locationPage;
	private final LocationDB locationDB;
	private final Context context;
	private final InventoryDB inventoryDB;
	Map<String, Integer> qtyReceivedPerTagMap;
	Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, ArrayList<String>> tagIDMap;

	@Inject
	public InventoryQueryStepDefs(InventoryQueryPage inventoryQueryPage, JDAFooter jdaFooter, LocationPage locationPage,
			Context context, JdaHomePage jdaHomePage, InventoryDB inventoryDB, LocationDB locationDB) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.locationPage = locationPage;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
	}

	@Given("^I have tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_tag_id_with_status(String tagId, String status) throws Throwable {
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

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

		String actualstatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("Tag id is not in unlocked status", status, actualstatus);
		logger.debug("Status in Inventory screen : " + actualstatus);
	}

	@Given("^I have tag id \"([^\"]*)\" with the \"([^\"]*)\" status$")
	public void i_have_tag_id_with__the_status(String tagId, String status) throws Throwable {
		context.setTagId(tagId);
		String inventorySKUId = inventoryDB.getInventorySKUId(tagId);
		context.setSkuId(inventorySKUId);
		logger.debug("SKU ID: " + inventorySKUId);

		String productStatus = inventoryDB.getStatus(tagId);
		Assert.assertEquals("Status is not displayed as expected", status, productStatus);
		logger.debug("Inventory Query - Status: " + productStatus);

		String qtyOnHandBfrAdjustment = inventoryDB.getQtyOnHand(tagId);
		// context.setqtyOnHandBfrAdjustment(qtyOnHandBfrAdjustment);
		logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

		String caseRatio = inventoryDB.getCaseRatio(tagId);
		// context.setCaseRatio(caseRatio);
		logger.debug("Case Ratio: " + caseRatio);

		String actualstatus = inventoryDB.getStatus(tagId);
		Assert.assertEquals("Tag id is not in unlocked status", status, actualstatus);
		logger.debug("Status in Inventory screen : " + actualstatus);

		String location = inventoryDB.getLocation(tagId);
		context.setLocation(location);
		logger.debug("Status in Inventory screen : " + actualstatus);
	}

	@Given("^I have a tag id with the \"([^\"]*)\" status in inventory$")
	public void i_have_a_tag_id_with_the_status_in_inventory(String status) throws Throwable {
		ArrayList failureList = new ArrayList();
		String tagId = inventoryDB.getTagIdWithStatus(status);
		if (!tagId.contains("Exhausted Resultset")){
			context.setTagId(tagId);
			String location = inventoryDB.getLocation(tagId);
			context.setLocation(location);
		}
		else{
			failureList.add("No Tag ID found for "+status+" status");
		}
		Assert.assertTrue(
				"Test Data Issue. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^I should see the updated status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the inventory query page$")
	public void I_should_see_the_updated_status_and_lock_code_in_the_inventory_query_page(String status,
			String lockCode) throws Throwable {
		inventoryQueryPage.refreshInventoryQueryPage();
		ArrayList<String> failureList1 = new ArrayList<String>();
		String istatus = inventoryQueryPage.getStatus();
		String ilockCode = inventoryQueryPage.getLockCode();
		if (!istatus.equals(status)) {
			failureList1.add("Status is not updated to locked.");
		}
		if (!ilockCode.equals(lockCode)) {
			failureList1.add("Lock Code is not updated to CODEAPP.");
			String mStatus = inventoryQueryPage.getStatus();
			Assert.assertEquals("Status is not updated to locked", status, mStatus);
			String actualLockCode = inventoryQueryPage.getLockCode();

			switch (lockCode) {
			case "Code Approval":
				Assert.assertEquals("Lock Code not displayed as expected", "CODEAPP", actualLockCode);
				break;
			case "Components Stock":
				Assert.assertEquals("Lock Code not displayed as expected", "CS", actualLockCode);
				break;
			case "1Damaged":
				Assert.assertEquals("Lock Code not displayed as expected", "DMGD", actualLockCode);
				break;
			case "EVENTS":
				Assert.assertEquals("Lock Code not displayed as expected", "EVENT", actualLockCode);
				break;
			case "Pick exception lock code":
				Assert.assertEquals("Lock Code not displayed as expected", "EXCEPT", actualLockCode);
				break;
			case "1Expired":
				Assert.assertEquals("Lock Code not displayed as expected", "EXPD", actualLockCode);
				break;
			case "Head Office Request":
				Assert.assertEquals("Lock Code not displayed as expected", "HOREQ", actualLockCode);
				break;
			case "Lock code for new vintage or new wine":
				Assert.assertEquals("Lock Code not displayed as expected", "NV", actualLockCode);
				break;
			case "Outlets Stock":
				Assert.assertEquals("Lock Code not displayed as expected", "OS", actualLockCode);
				break;
			case "Product Recall":
				Assert.assertEquals("Lock Code not displayed as expected", "PRODRECALL", actualLockCode);
				break;
			case "Return from RDC":
				Assert.assertEquals("Lock Code not displayed as expected", "RDCRETURNS", actualLockCode);
				break;
			case "Supplier Damage":
				Assert.assertEquals("Lock Code not displayed as expected", "SUDMG", actualLockCode);
				break;
			case "Return to Supplier":
				Assert.assertEquals("Lock Code not displayed as expected", "SUPPRETURN", actualLockCode);
				break;
			case "Warehouse Damage":
				Assert.assertEquals("Lock Code not displayed as expected", "WHDMG", actualLockCode);
				break;
			case "Hampers Stock":
				Assert.assertEquals("Lock Code not displayed as expected", "HS", actualLockCode);
				break;
			case "Incubation lock code":
				Assert.assertEquals("Lock Code not displayed as expected", "INCUB", actualLockCode);
				break;
			}
			logger.debug("Lock Code: " + actualLockCode);
		}
	}

	@Then("^I should see the updated status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the inventory query$")
	public void I_should_see_the_updated_status_and_lock_code_in_the_inventory_query(String status, String lockCode)
			throws Throwable {
		String istatus = inventoryDB.getStatus(context.getTagId());
		String actualLockCode = inventoryDB.getLockCode(context.getTagId());
		context.setLockCode(actualLockCode);

		Assert.assertEquals("Status is not displayed as expecetd", status, istatus);

		switch (lockCode) {
		case "Code Approval":
			Assert.assertEquals("Lock Code not displayed as expected", "CODEAPP", actualLockCode);
			break;
		case "Components Stock":
			Assert.assertEquals("Lock Code not displayed as expected", "CS", actualLockCode);
			break;
		case "1Damaged":
			Assert.assertEquals("Lock Code not displayed as expected", "DMGD", actualLockCode);
			break;
		case "EVENTS":
			Assert.assertEquals("Lock Code not displayed as expected", "EVENT", actualLockCode);
			break;
		case "Pick exception lock code":
			Assert.assertEquals("Lock Code not displayed as expected", "EXCEPT", actualLockCode);
			break;
		case "1Expired":
			Assert.assertEquals("Lock Code not displayed as expected", "EXPD", actualLockCode);
			break;
		case "Head Office Request":
			Assert.assertEquals("Lock Code not displayed as expected", "HOREQ", actualLockCode);
			break;
		case "Lock code for new vintage or new wine":
			Assert.assertEquals("Lock Code not displayed as expected", "NV", actualLockCode);
			break;
		case "Outlets Stock":
			Assert.assertEquals("Lock Code not displayed as expected", "OS", actualLockCode);
			break;
		case "Product Recall":
			Assert.assertEquals("Lock Code not displayed as expected", "PRODRECALL", actualLockCode);
			break;
		case "Return from RDC":
			Assert.assertEquals("Lock Code not displayed as expected", "RDCRETURNS", actualLockCode);
			break;
		case "Supplier Damage":
			Assert.assertEquals("Lock Code not displayed as expected", "SUDMG", actualLockCode);
			break;
		case "Return to Supplier":
			Assert.assertEquals("Lock Code not displayed as expected", "SUPPRETURN", actualLockCode);
			break;
		case "Warehouse Damage":
			Assert.assertEquals("Lock Code not displayed as expected", "WHDMG", actualLockCode);
			break;
		case "Hampers Stock":
			Assert.assertEquals("Lock Code not displayed as expected", "HS", actualLockCode);
			break;
		case "Incubation lock code":
			Assert.assertEquals("Lock Code not displayed as expected", "INCUB", actualLockCode);
			break;
		}
		context.setLockCode(actualLockCode);
		logger.debug("Lock Code: " + actualLockCode);
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		context.setTagId(tagId);
		context.setStatus(status);

		// inventoryQueryPage.searchTagId(tagId);
		logger.debug("Tag ID: " + tagId);

		String inventorySKUId = inventoryQueryPage.getInventorySKUId();
		context.setSkuId(inventorySKUId);
		logger.debug("SKU ID: " + inventorySKUId);

		String productStatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("the given TAG id is not in " + status + " status", status, productStatus);
		logger.debug("Inventory Query - Status: " + productStatus);

		String qtyOnHandBfrAdjustment = inventoryQueryPage.getQtyOnHand();
		context.setqtyOnHandBeforeAdjustment(Utilities.convertStringToInteger(qtyOnHandBfrAdjustment));
		logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

		String caseRatio = inventoryQueryPage.getcaseRatio();
		context.setCaseRatio(Utilities.convertStringToInteger(caseRatio));
		logger.debug("Case Ratio: " + caseRatio);
	}

	@Given("^I have a tag id with the \"([^\"]*)\" status$")
	public void i_have_a_tag_id_with_the_status(String status) throws Throwable {
		ArrayList failureList = new ArrayList();
		String tagId = inventoryDB.getTagIdWithStatus(status);
		if (!tagId.contains("Exhausted Resultset")){
			context.setTagId(tagId);
			context.setStatus(status);
			logger.debug("Tag ID: " + tagId);

			String inventorySKUId = inventoryDB.getInventorySKUId(tagId);
			context.setSkuId(inventorySKUId);
			logger.debug("SKU ID: " + inventorySKUId);

			String qtyOnHandBfrAdjustment = inventoryDB.getQtyOnHand(tagId);
			context.setqtyOnHandBeforeAdjustment(Utilities.convertStringToInteger(qtyOnHandBfrAdjustment));
			logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

			String caseRatio = inventoryDB.getCaseRatio(tagId);
			context.setCaseRatio(Utilities.convertStringToInteger(caseRatio));
			logger.debug("Case Ratio: " + caseRatio);
		}
		else{
			failureList.add("No Tag ID found for "+status+" status");
		}
		Assert.assertTrue(
				"Test Data Issue. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^I should see the location zone in inventory page$")
	public void i_should_see_the_location_zone_in_inventory_page() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<String, String> locationTagMap = context.getLocationForTagMap();

		for (String tagId : locationTagMap.keySet()) {
			jdaHomePage.navigateToLocationPage();
			String location = locationTagMap.get(tagId);
			jdaFooter.clickQueryButton();
			locationPage.enterLocation(location);
			jdaFooter.clickExecuteButton();
			String locationZone = locationPage.getLocationZone();

			jdaHomePage.navigateToInventoryQueryPage();
			jdaFooter.clickQueryButton();
			inventoryQueryPage.enterTagId(tagId);
			jdaFooter.clickExecuteButton();
			String invLocationZone = inventoryQueryPage.getLocationZone();

			if (!locationZone.equals(invLocationZone)) {
				failureList.add("Location Zone does  not displayed as expected for" + tagId + ". Expected ["
						+ locationZone + "] but was [" + invLocationZone + "]");
			}
		}
	}

	@Then("^I should see the location zone in inventory query$")
	public void i_should_see_the_location_zone_in_inventory_query() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<String, String> locationForTagMap = context.getLocationForTagMap();

		for (String tagId : locationForTagMap.keySet()) {
			String location = locationForTagMap.get(tagId);
			String locationZone = locationDB.getLocationZone(location);

			HashMap<String, String> inventoryQueryDetails = inventoryDB.getInventoryQueryDetails(tagId);
			String inventoryLocationZone = inventoryQueryDetails.get("Location Zone");

			if (!locationZone.equals(inventoryLocationZone)) {
				failureList.add("Location Zone does not displayed as expected for" + tagId + ". Expected ["
						+ locationZone + "] but was [" + inventoryLocationZone + "]");
			}
		}

		Assert.assertTrue("Inventory location zone are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^I have SKU id, product group and ABV for the tag id$")
	public void i_have_SKU_id_product_group_and_ABV_for_the_tag_id() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaFooter.clickQueryButton();
		
		String tagId = inventoryDB.getTagIdWithBWSSku();
		context.setTagId(tagId);
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		String skuId = inventoryDB.getSkuId(tagId);
		if (skuId.equals(null)) {
			failureList.add("Sku Id is not as expected. Expected [Not NULL] but was [" + skuId + "]");
		}

		inventoryQueryPage.clickUserDefinedTab();
		String productGroup = inventoryQueryPage.getProductGroup();
		if (!(productGroup.equals("F20") || productGroup.equals("F21") || productGroup.equals("F22"))) {
			failureList.add(
					"Product Group is not as expected. Expected [F20 or F21 or F21] but was [" + productGroup + "]");
		}

		String abv = inventoryDB.getABV(tagId);
		if (abv.equals(null)) {
			failureList.add("ABV is not as expected. Expected [NOT NULL] but was [" + abv + "]");
		}
	}

	@Then("^I should see the updated ABV in the inventory query page$")
	public void i_should_see_the_updated_ABV_in_the_inventory_query_page() throws Throwable {
		inventoryQueryPage.refreshUserDefinedTab();
		Assert.assertEquals("ABV is not as expected.", context.getABV(), inventoryQueryPage.getUpdatedABV());
	}

	@When("^I navigate to inventory query page and search the tag id \"([^\"]*)\"$")
	public void i_navigate_to_inventory_query_page_and_search_the_tag_id(String tagId) throws Throwable {
		jdaHomePage.navigateToInventoryQueryPage();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
	}

	@When("^the inventory details should be displayed for all the tag id$")
	public void the_inventory_details_should_be_displayed_for_all_the_tag_id() throws Throwable {
		String tagID = null, allocationGroup = null;
		int qtyReceivedPerTag = 0, caseRatio = 0;

		purchaseOrderMap = context.getPurchaseOrderMap();
		qtyReceivedPerTagMap = context.getQtyReceivedPerTagMap();
		tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			caseRatio = Integer.parseInt(purchaseOrderMap.get(key).get("CaseRatio"));
			context.setAllocationGroup(purchaseOrderMap.get(key).get("AllocationGroup"));
			for (int s = 0; s < tagIDMap.get(sku).size(); s++) {
				tagID = tagIDMap.get(sku).get(s);
				qtyReceivedPerTag = qtyReceivedPerTagMap.get(tagID);
				context.setTagId(tagID);
				context.setQtyReceivedPerTag(qtyReceivedPerTag * caseRatio);
				jdaFooter.clickQueryButton();
				inventoryQueryPage.enterTagId(tagID);
				jdaFooter.clickExecuteButton();
				the_quantity_on_hand_location_site_id_and_status_should_be_displayed_in_the_general_tab();
				the_expiry_date_pallet_id_receipt_id_and_supplier_details_should_be_displayed_in_the_miscellaneous_tab();
				the_storage_location_base_UOM_and_product_groud_should_be_displayed();
			}
		}
	}

	@When("^the inventory details should be displayed for all the tag ids$")
	public void the_inventory_details_should_be_displayed_for_all_the_tag_ids() throws Throwable {
		String tagID = null, allocationGroup = null;
		int qtyReceivedPerTag = 0, caseRatio = 0;

		purchaseOrderMap = context.getPurchaseOrderMap();
		qtyReceivedPerTagMap = context.getQtyReceivedPerTagMap();
		tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			caseRatio = Integer.parseInt(purchaseOrderMap.get(key).get("CaseRatio"));
			context.setAllocationGroup(purchaseOrderMap.get(key).get("Allocation Group"));
			for (int s = 0; s < tagIDMap.get(sku).size(); s++) {
				tagID = tagIDMap.get(sku).get(s);
				qtyReceivedPerTag = qtyReceivedPerTagMap.get(tagID);
				context.setTagId(tagID);
				context.setQtyReceivedPerTag(qtyReceivedPerTag * caseRatio);
				the_inventory_query_details_should_be_checked_in_inventory_table();
			}
		}
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
		if (!status.equals("Unlocked") && (context.getProductCategory().contains("Ambient"))) {
			failureList.add("Status is not displayed as expected. Expected [Unlocked] but was [" + status + "]");
		} else if ((status.equals(null)) && (context.getProductCategory().contains("BWS"))) {
			failureList.add("Status is not displayed as expected. Expected [Not NULL value] but was [" + status + "]");
		}

		String qtyOnHand = inventoryQueryPage.getQtyOnHand();
		if (context.getQtyReceivedPerTag() != Integer.parseInt(qtyOnHand)) {
			Assert.fail("Quantity on hand is not expected. Expected [" + context.getQtyReceivedPerTag() + "] but was ["
					+ qtyOnHand + "]");
		}

		String locationZone = inventoryQueryPage.getLocationZone();
		if (!locationZone.equals("INBOUND")) {
			Assert.fail("Location Zone is not expected. Expected [INBOUND] +  but was [" + locationZone + "]");
		}

		Assert.assertTrue(
				"Inventory query general details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the inventory query details should be checked in inventory table$")
	public void the_inventory_query_details_should_be_checked_in_inventory_table() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		HashMap<String, String> inventoryQueryDetails = inventoryDB.getInventoryQueryDetails(context.getTagId());

		String siteId = inventoryQueryDetails.get("SiteId");
		if (siteId.equals(null)) {
			failureList.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + siteId + "]");
		}

		String location = inventoryQueryDetails.get("Location");
		if (location.equals(null)) {
			failureList
					.add("Site Id is not displayed as expected. Expected [Not NULL value] but was [" + location + "]");
		}

		String status = inventoryQueryDetails.get("Lock Status");
		if (!status.equalsIgnoreCase("Unlocked") && (context.getProductCategory().contains("Ambient"))) {
			failureList.add("Status is not displayed as expected. Expected [Unlocked] but was [" + status + "]");
		} else if ((status.equals(null)) && (context.getProductCategory().contains("BWS"))) {
			failureList.add("Status is not displayed as expected. Expected [Not NULL value] but was [" + status + "]");
		}

		String qtyOnHand = inventoryQueryDetails.get("QtyOnHand");
		if (context.getQtyReceivedPerTag() != Integer.parseInt(qtyOnHand)) {
			Assert.fail("Quantity on hand is not expected. Expected [" + context.getQtyReceivedPerTag() + "] but was ["
					+ qtyOnHand + "]");
		}

		String locationZone = inventoryQueryDetails.get("Location Zone");
		if (!locationZone.equals("INBOUND")) {
			Assert.fail("Location Zone is not expected. Expected [INBOUND] +  but was [" + locationZone + "]");
		}

		if (context.getAllocationGroup().equalsIgnoreCase("Expiry")) {
			String  expDate = inventoryQueryDetails.get("Expiry Date");
			String []  futureDate =  expDate.split(" "); 
			TemporalAccessor tempDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(futureDate[0]);
			String expiryDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(tempDate);
			
			if (!expiryDate.equals(context.getFutureExpiryDate())) {
				failureList.add("Expiry Date is not as expected. Expected [" + context.getFutureExpiryDate()
						+ "] but was [" + expiryDate + "]");
			}
		}

		String palletType = inventoryQueryDetails.get("Pallet Type");
		;
		if (palletType.equals(null)) {
			failureList.add("Pallet Type is not as expected. Expected [Not NULL] but was [" + palletType + "]");
		}

		String receiptID = inventoryQueryDetails.get("Receipt Id");
		;
		if (receiptID.equals(null)) {
			failureList.add("Receipt Id is not as expected. Expected [Not NULL] but was [" + receiptID + "]");
		}

		String supplier = inventoryQueryDetails.get("Supplier Id");
		;
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}

		String inventoryCreatedBy = inventoryQueryDetails.get("Created By");
		if (inventoryCreatedBy.equals(null)) {
			failureList.add("Inventory Created By is not as expected. Expected [Not NULL] but was ["
					+ inventoryCreatedBy + "]");
		}

		String storageLocation = inventoryQueryDetails.get("Storage Location");
		;
		if (!storageLocation.equals("0001")) {
			failureList.add("Storage Location is not as expected. Expected [0001] but was [" + storageLocation + "]");
		}

		String baseUOM = inventoryQueryDetails.get("Base UOM");
		;
		if (!baseUOM.equals("EA")) {
			failureList.add("Base UOM is not as expected. Expected [EA] but was [" + baseUOM + "]");
		}

		String productGroup = inventoryQueryDetails.get("Product Group");
		if (productGroup.equals(null)) {
			failureList.add("Product Group is not as expected. Expected [Not NULL] but was [" + productGroup + "]");
		}

		Assert.assertTrue("Inventory query details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the expiry date, pallet id, receipt id and supplier details should be displayed in the miscellaneous tab$")
	public void the_expiry_date_pallet_id_receipt_id_and_supplier_details_should_be_displayed_in_the_miscellaneous_tab()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		inventoryQueryPage.navigateToMiscellaneousTab();
		if (context.getAllocationGroup().equalsIgnoreCase("Expiry")) {
			String expiryDate = inventoryQueryPage.getExpiryDate();
			if (!expiryDate.equals(context.getFutureExpiryDate())) {
				failureList.add("Expiry Date is not as expected. Expected [" + context.getFutureExpiryDate()
						+ "] but was [" + expiryDate + "]");
			}
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

		inventoryQueryPage.clickGeneralTab();

		Assert.assertTrue(
				"Inventory query user defined details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@When("^I navigate to pre-advice header maintenance page$")
	public void i_navigate_to_pre_advice_header_maintenance_page() throws Throwable {
		jdaHomePage.navigateToPreAdviceHeaderPage();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterPOId(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();
	}
	
	@When("^the inventory should be expired for a SKU id and with qty on hand more than allocated qty for the tag$")
	public void the_inventory_should_be_expired_for_a_sku_id_and_with_qty_on_hand_more_than_allocated_qty_for_the_tag() throws Throwable {
		String tagId = inventoryDB.getTagForExpiredSkuWithQtyOnHandMoreThanAllocatedQty();
		context.setTagId(tagId);
	}

	@Then("^the status should be displayed as \"([^\"]*)\"$")
	public void the_status_should_be_displayed_as(String status) throws Throwable {
		Assert.assertEquals("PO Status does not match", status, inventoryQueryPage.getPreAdviceStatus());
	}

	@Then("^the ABV should be updated for all the Tag Id$")
	public void the_ABV_should_be_updated_for_all_the_Tag_Id() throws Throwable {
		String tagID = null, expectedAbv = null;
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			expectedAbv = context.getABV();
			for (int skuIndex = 0; skuIndex < tagIDMap.get(sku).size(); skuIndex++) {
				tagID = tagIDMap.get(sku).get(skuIndex);
				Thread.sleep(3000);
				Assert.assertEquals("ABV is not as expected.", expectedAbv, inventoryDB.getABV(tagID));
			}
		}
	}

	@Then("^the Inventory should be updated with the new updated quantity$")
	public void inventory_should_be_updated_with_the_new_updated_quantity() throws Throwable {
		jdaHomePage.clickInventorytab();
		inventoryQueryPage.refreshInventoryQueryPage();
		int expectedQtyOnHand = context.getQtyOnHand() - context.getQtyReverse();
		Assert.assertEquals("Qty on Hand does not match", expectedQtyOnHand, inventoryQueryPage.getQtyOnhand());
	}
	
	@Then("^the inventory should be updated with new quantity$")
	public void the_inventory_should_be_updated_with_new_updated_quantity() throws Throwable {
		int expectedQtyOnHand = context.getQtyOnHand() - context.getQtyReverse();
		Assert.assertEquals("Qty on Hand does not match", expectedQtyOnHand, inventoryDB.getQtyOnHand(context.getTagId()));
	}

	@Given("^I enter the tagId \"([^\"]*)\"$")
	public void i_enter_the_tagId(String tagId) throws Throwable {
		context.setTagId(tagId);
		inventoryQueryPage.enterTagId(tagId);
	}

	@Then("^I should see the location zone in inventory query page$")
	public void i_should_see_the_location_zone_in_inventory_query_page() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		jdaHomePage.navigateToLocationPage();
		jdaFooter.clickQueryButton();
		locationPage.enterLocation(context.getLocation());
		jdaFooter.clickExecuteButton();
		String locationZone = locationPage.getLocationZone();
		jdaHomePage.navigateToInventoryQueryPage();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(context.getTagId());
		jdaFooter.clickExecuteButton();
		String invLocationZone = inventoryQueryPage.getLocationZone();

		if (!locationZone.equals(invLocationZone)) {
			failureList.add("Location Zone does  not displayed as expected for" + ". Expected [" + locationZone
					+ "] but was [" + invLocationZone + "]");
		}

	}

	@Then("^I should see the status$")
	public void i_should_see_the_status() throws Throwable {
	}

	@Then("^qty on hand should be updated in inventory query page$")
	public void qty_on_hand_should_be_updated_in_inventory_query_page() throws Throwable {
		// context.setSkuId("20001920");
		// context.setLocation("AB06E01");
		// context.setToLocation("AB03A02");
		// context.setQtyToMove(240);
		// context.setTagId("1000000169");
		String qtyOnHand = inventoryDB.getQtyOnHand(context.getSkuId(), context.getToLocation(),
				context.getTagId());
		Assert.assertEquals("Qty On Hand is not updated ", String.valueOf(context.getQtyToMove()), qtyOnHand);
	}
}
