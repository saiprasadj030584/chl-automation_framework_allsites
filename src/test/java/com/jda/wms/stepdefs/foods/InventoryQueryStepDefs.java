package com.jda.wms.stepdefs.foods;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class InventoryQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private JDAFooter jdaFooter;

	@Inject
	public InventoryQueryStepDefs(InventoryQueryPage inventoryQueryPage,JDAFooter jdaFooter) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		String actualstatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("Tag id is not in unlocked status", status, actualstatus);
		logger.debug("Status in Inventory screen : " + actualstatus);
	}

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
}
