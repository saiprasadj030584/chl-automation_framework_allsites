package com.jda.wms.stepdefs.Exit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.pages.Exit.InventoryQueryPage;
import com.jda.wms.pages.Exit.InventoryTransactionQueryPage;
import com.jda.wms.pages.Exit.InventoryUpdatePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.SKUMaintenancePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryUpdateStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryUpdatePage inventoryUpdatePage;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private final Context context;
	private final SKUMaintenancePage skuMaintenancePage;
	private final InventoryQueryPage inventoryQueryPage;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jDAFooter;
	private final InventoryDB inventoryDB;

	@Inject
	public InventoryUpdateStepDefs(SKUMaintenancePage skuMaintenancePage, InventoryQueryPage inventoryQueryPage,
			JDAFooter jDAFooter, InventoryTransactionQueryPage inventoryTransactionQueryPage, Context context,
			JdaHomePage jdaHomePage, InventoryUpdatePage inventoryUpdatePage,InventoryDB inventoryDB) {
		this.skuMaintenancePage = skuMaintenancePage;
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaHomePage = jdaHomePage;
		this.inventoryUpdatePage = inventoryUpdatePage;
		this.jDAFooter = jDAFooter;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
		this.inventoryDB=inventoryDB;
	}

	@When("^I enter ABV value as \"([^\"]*)\"$")
	public void i_enter_ABV_value_as(String abv) throws Throwable {
		jDAFooter.clickNextButton();
		context.setABV(abv);
		inventoryUpdatePage.enterABV(abv);
	}

	@When("^I select the reason code as \"([^\"]*)\"$")
	public void i_select_the_reason_code_as(String reasonCode) throws Throwable {
		inventoryUpdatePage.enterReasonCode(reasonCode);
	}

	@Given("^the quantity on hand should be more than allocated quantity for tag id \"([^\"]*)\"$")
	public void the_quantity_on_hand_should_be_more_than_allocated_quantity_for_tag_id(String tagId) throws Throwable {
		jdaHomePage.navigateToInventoryQueryPage();
		// inventoryQueryPage.searchTagId(tagId);
		int qtyOnHand = inventoryQueryPage.getQtyOnhand();
		int qtyAllocated = inventoryQueryPage.getQtyAllocated();
		if (qtyOnHand < qtyAllocated) {
			Assert.fail("Quantity Allocated should not be greater than quantity on hand. Quanitity on hand: "
					+ qtyOnHand + ", Quantity Allocated: " + qtyAllocated);
		}
	}

	@Given("^I select the type of invetnotry status as \"([^\"]*)\"$")
	public void i_select_the_type_of_invetnotry_status_as(String inventoryStatus) throws Throwable {
		inventoryUpdatePage.enterExpiryDateUpdate(inventoryStatus);
	}
	
	
	@Given("^I proceed to next$")
	public void i_proceed_to_next() throws Throwable {
		jDAFooter.clickNextButton();
		//jDAFooter.clickNextButtonImg();
		Thread.sleep(2000);
	}
	
	
	
	@When("^I select the pack configuration type as \"([^\"]*)\" and reason code as \"([^\"]*)\"$")
	public void I_select_the_pack_configuration_type_as_and_reason_code_as(String PackConfig, String reasoncode) throws Throwable {
		
		String PackConfigFromInventory=inventoryDB.getPackConfig(context.getTagId());
		if(PackConfigFromInventory.equals(PackConfig))
		{
			PackConfig="20001498O02";
		}
		inventoryUpdatePage.selectPackConfigurationType(PackConfig);	
		inventoryUpdatePage.selectReasonCode(reasoncode);
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String TransactionTime=sdf.format(cal.getTime());
        context.setTime(TransactionTime);
        System.out.println("time="+TransactionTime);	
	}

	@Given("^I select the expiry date in future and reason code as \"([^\"]*)\"$")
	public void i_select_the_expiry_date_in_future_and_reason_code_as(String reasonCode) throws Throwable {
		jDAFooter.clickNextButton();
		String FutureDate = inventoryUpdatePage.enterFutureDate();
		context.setFutureExpiryDate(FutureDate);
		jdaHomePage.enterTabKey();
//		jdaHomePage.enterTabKey();
		inventoryUpdatePage.selectReasonCode(reasonCode);
//		jDAFooter.clickDoneButton();
	}

	@Then("^the inventory update home page should be displayed$")
	public void the_inventory_update_home_page_should_be_displayed() throws Throwable {
		Assert.assertTrue("Inventory update home page is not displayed", inventoryUpdatePage.isHomePage());
	}

	@Then("^I should see the future expiry date and reason code in the miscellaneous tab$")
	public void i_should_see_the_future_expiry_date_and_reason_code_in_the_miscellaneous_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
//		inventoryTransactionQueryPage.eitherSingleOrMultipleTagId();
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		// Get Expiry Date
		String expiryDate = inventoryTransactionQueryPage.getExpiryDate();
		if (!context.getFutureExpiryDate().equals(expiryDate)) {
			failureList.add("Expiry Date is not displayed as expected. Expected [" + context.getFutureExpiryDate()
					+ "] but was [" + expiryDate + "]");
		}

		String reasonCode = inventoryTransactionQueryPage.getReasonCode();
		if (!reasonCode.equals("MINEXPIRY")) {
			failureList
					.add("Reason Code is not displayed as expected. Expected [MINEXPIRY] but was [" + reasonCode + "]");
		}

		Assert.assertTrue(
				"Inventory Transaction details are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I navigate to search tab$")
	public void i_navigate_to_search_tab() throws Throwable {
		jDAFooter.clickNextButton();
	}
	
	@When("^I search the tagId \"([^\"]*)\"$")
    public void i_search_the_tagIid(String tagId) throws Throwable {
		context.setTagId(tagId);
		inventoryUpdatePage.enterTagId(context.getTagId());
	}
	
	@When("^I search the tag id$")
	public void i_search_the_tag_id() throws Throwable {
		
		inventoryUpdatePage.enterTagId(context.getTagId());
	}

	@When("^I proceed to lock the record$")
	public void i_proceed_to_lock_the_record() throws Throwable {
		jDAFooter.clickNextButton();
	}

	@When("^I choose the type of inventory property as \"([^\"]*)\"$")
	public void i_choose_the_type_of_inventory_property_as(String selectType) throws Throwable {
		inventoryUpdatePage.selectType(selectType);
		jDAFooter.clickNextButton();
	}

	@When("^I search the tag ID with location$")
	public void i_search_the_tag_ID_with_location() throws Throwable {
		inventoryUpdatePage.enterTagId(context.getTagId());
		inventoryUpdatePage.enterLocation(context.getLocation());
		jDAFooter.clickNextButton();
	}

	@Then("^the record should be displayed in the search result$")
	public void the_record_should_be_displayed_in_the_search_result() throws Throwable {
		jDAFooter.clickNextButton();
		Assert.assertTrue("No records are present for this tag Id", inventoryUpdatePage.isRecordExists());
	}

	@When("^I select the status as \"([^\"]*)\", lock code as \"([^\"]*)\" and reason code as \"([^\"]*)\"$")
	public void i_select_the_status_as_lock_code_as_and_reason_code_as(String status, String lockCode,
			String reasonCode) throws Throwable {
		inventoryUpdatePage.selectStatus(status);
		inventoryUpdatePage.selectLockCode(lockCode);
		inventoryUpdatePage.selectReasonCode(reasonCode);
	}
	
	@When("^I select the pallet type as \"([^\"]*)\" and reason code as \"([^\"]*)\"$")
	public void i_select_the_pallet_type_as_and_reason_code_as(String pallettype, String reasoncode) throws Throwable {
		String existingPallet=inventoryDB.getPallet(context.getTagId());
		if(existingPallet.equals(pallettype))
		{
			pallettype="CAGE";
		}
		inventoryUpdatePage.selectPalletType(pallettype);
		inventoryUpdatePage.selectReasonCode(reasoncode);
	}
	
	@When("^I proceed to complete the process$")
	public void i_proceed_to_complete_the_process() throws Throwable {
		Thread.sleep(5000);
		jDAFooter.clickDoneButton();
	}
	
	
}
