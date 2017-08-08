package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.pages.gm.InventoryUpdatePage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.gm.WarningPopUpPage;
import com.jda.wms.utils.DateUtils;
import org.sikuli.script.Screen;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryUpdateStepDefs {
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JDAFooter jdafooter;
	private InventoryUpdatePage inventoryUpdatePage;
	private DateUtils dateUtils;
	private WarningPopUpPage warningPopUpPage;
	private StockAdjustmentsPage stockAdjustmentsPage;
	Screen screen = new Screen();

	@Inject
	public InventoryUpdateStepDefs(Context context, JDAFooter jdafooter, InventoryDB inventoryDB,
			InventoryUpdatePage inventoryUpdatePage, DateUtils dateUtils, WarningPopUpPage warningPopUpPage,StockAdjustmentsPage stockAdjustmentsPage) {
		this.context = context;
		this.jdafooter = jdafooter;
		this.inventoryUpdatePage = inventoryUpdatePage;
		this.dateUtils = dateUtils;
		this.warningPopUpPage = warningPopUpPage;
		this.stockAdjustmentsPage=stockAdjustmentsPage;

	}

	@When("^I select the update type as \"([^\"]*)\"$")
	public void i_select_the_update_type_as(String updateType) throws Throwable {
		inventoryUpdatePage.enterSelectType(updateType);
		jdafooter.clickNextButton();
	}

	@When("^I search the inventory for the tag$")
	public void i_search_the_inventory_for_the_tag() throws Throwable {
		inventoryUpdatePage.enterTagID(context.getTagId());
		inventoryUpdatePage.enterSku(context.getSkuId());
		inventoryUpdatePage.enterLocation(context.getLocation());
		jdafooter.clickNextButton();
	}

	@Then("^the tag details should be displayed$")
	public void the_tag_details_should_be_displayed() throws Throwable {
		Assert.assertTrue("Tag Details not displayed as expected", inventoryUpdatePage.isRecordDisplayed());
		jdafooter.clickNextButton();
	}

	@Then("^I select the status as \"([^\"]*)\"$")
	public void i_select_the_status_as(String status) throws Throwable {
		inventoryUpdatePage.enterStatus(status);
		jdafooter.clickDoneButton();
		if (inventoryUpdatePage.isWarningPopUpPageExist()) {
			warningPopUpPage.clickYes();
		}
		context.setStatus(status);
	}

	@Then("^I select the future date$")
	public void i_select_the_future_date() throws Throwable {
		String futuredate = DateUtils.getAddedSystemYear();
		inventoryUpdatePage.enterExpiryDate(futuredate);
		jdafooter.clickDoneButton();
		context.setFutureExpiryDate(futuredate);

	}

	@Then("^I select the condition as \"([^\"]*)\"$")
	public void i_select_the_condition_as(String condition) throws Throwable {
		inventoryUpdatePage.enterCondition(condition);
		jdafooter.clickDoneButton();
		context.setCondition(condition);
	}

	@Then("^I select the pallet type as \"([^\"]*)\"$")
	public void i_select_the_pallet_type_as(String pallet) throws Throwable {
		inventoryUpdatePage.enterCondition(pallet);
		jdafooter.clickDoneButton();
		context.setPalletType(pallet);
	}

	@Then("^I select the owner type as \"([^\"]*)\"$")
	public void i_select_the_owner_type_as(String owner) throws Throwable {
		inventoryUpdatePage.enterOwner(owner);
		jdafooter.clickDoneButton();
		context.setOwner(owner);
	}
	
	@Then("^I change the lock status to unlocked$")
	public void i_change_the_lock_status_to_unlocked() throws Throwable {
		
		stockAdjustmentsPage.enterSkuId(context.getSkuId());
		stockAdjustmentsPage.enterReceiptId(context.getUpiId());
		jdafooter.clickNextButton();
		jdafooter.clickNextButton();
		screen.type("Unlocked ");
		jdafooter.clickDoneButton();
		
	}
	
}
