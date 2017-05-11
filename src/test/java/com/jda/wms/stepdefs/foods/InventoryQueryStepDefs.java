package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.LocationPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class InventoryQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final LocationPage locationPage;
	private final Context context;

	@Inject
	public InventoryQueryStepDefs(InventoryQueryPage inventoryQueryPage, JDAFooter jdaFooter, LocationPage locationPage,
			Context context, JdaHomePage jdaHomePage) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.locationPage = locationPage;
		this.context = context;
	}

	@Given("^I have tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_tag_id_with_status(String tagId, String status) throws Throwable {

		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		String actualstatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("Tag id is not in unlocked status", status, actualstatus);
		logger.debug("Status in Inventory screen : " + actualstatus);
	}

	// @And("^I have tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	// public void i_have_tag_id_with_status(String tagId, String status) throws
	// Throwable {
	// jdaFooter.clickQueryButton();
	// inventoryQueryPage.enterTagId(tagId);
	// jdaFooter.clickExecuteButton();
	//
	// String actualstatus = inventoryQueryPage.getStatus();
	// Assert.assertEquals("Tag id is not in unlocked status", status,
	// actualstatus);
	// logger.debug("Status in Inventory screen : " + actualstatus);
	// }

	@Then("^I should see the updated status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the inventory query$")
	public void I_should_see_the_updated_status_and_lock_code_in_the_inventory_query(String status, String lockCode)
			throws Throwable {
		inventoryQueryPage.refreshInventoryQueryPage();
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

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		context.setTagId(tagId);
		context.setStatus(status);

		inventoryQueryPage.searchTagId(tagId);
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

	@Then("^I should see the location zone in inventory page$")
	public void i_should_see_the_location_zone_in_inventory_page() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<String, String> locationTagMap = context.getLocationPerTagMap();

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
}
