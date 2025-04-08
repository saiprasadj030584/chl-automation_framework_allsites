package com.jda.wms.stepdefs;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.InventoryQueryPage;
import com.jda.wms.UI.pages.JDAFooter;
import com.jda.wms.UI.pages.JdaHomePage;
import com.jda.wms.UI.pages.StockCheckListGenerationPage;
import com.jda.wms.UI.pages.StockCheckTaskQueryPage;
import com.jda.wms.context.Context;
import com.jda.wms.db.InventoryDB;
import com.jda.wms.db.LocationDB;
import com.jda.wms.db.StockCheckDB;
import com.jda.wms.stepdefs.InventoryTransactionStepDefs;
import com.jda.wms.stepdefs.JDAExitUpiHeader;
import com.jda.wms.stepdefs.JDAExitputtyfunctionsStepDef;
import com.jda.wms.stepdefs.JDAHomeStepDefs;
//import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
//import com.jda.wms.pages.rdt.PuttyFunctionsPage;
//import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckListGenerationStepDefs {
	private static final String WrkZne = null;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockCheckListGenerationPage stockCheckListGenerationPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;
	private Context context;
	private LocationDB locationDB;
//	private InventoryUpdateStepDefs inventoryUpdateStepDefs;
//	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private InventoryQueryPage inventoryQueryPage;
	private StockCheckTaskQueryPage stockCheckTaskQueryPage;
	private StockCheckDB stockCheckDB;
//	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
//	private PuttyFunctionsPage puttyFunctionsPage;
	private JDAExitUpiHeader JDAExitUpiHeader;
	private JDAExitputtyfunctionsStepDef jDAExitputtyfunctionsStepDef;
	private InventoryTransactionStepDefs inventoryTransactionStepDefs;
	private JDALoginStepDefs jDALoginStepDefs;
	private JDAHomeStepDefs jDAHomeStepDefs;

	@Inject
	public StockCheckListGenerationStepDefs(StockCheckListGenerationPage stockCheckListGenerationPage,
			JDAFooter jdaFooter, JdaHomePage jdaHomePage, StockCheckTaskQueryPage stockCheckTaskQueryPage,
			InventoryQueryPage inventoryQueryPage, InventoryDB inventoryDB, Context context, LocationDB locationDB,
			JDAExitUpiHeader jDAExitUpiHeader,JDAExitputtyfunctionsStepDef jDAExitputtyfunctionsStepDef,JDAHomeStepDefs jDAHomeStepDefs,
			StockCheckDB stockCheckDB,InventoryTransactionStepDefs InventoryTransactionStepDefs,JDALoginStepDefs jDALoginStepDefs) {
		this.stockCheckListGenerationPage = stockCheckListGenerationPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.inventoryDB = inventoryDB;
		this.context = context;
		this.locationDB = locationDB;
		this.inventoryQueryPage = inventoryQueryPage;
		this.stockCheckTaskQueryPage = stockCheckTaskQueryPage;
		this.stockCheckDB = stockCheckDB;
		this.JDAExitUpiHeader=jDAExitUpiHeader;
		this.jDAExitputtyfunctionsStepDef=jDAExitputtyfunctionsStepDef;
		this.inventoryTransactionStepDefs=inventoryTransactionStepDefs;
		this.jDALoginStepDefs=jDALoginStepDefs;
		this.jDAHomeStepDefs=jDAHomeStepDefs;
	}

//	@When("^I navigate to stock check list Generation page$")
//	public void i_navigate_to_stock_check_list_Generation_page() throws Throwable {
//		Thread.sleep(3000);
//		jdaHomePage.navigateToStockCheckListGeneration();
//		Thread.sleep(3000);
//	}

	@When("^I select mode of list generation as 'Generate by location'$")
	public void i_select_mode_of_list_generation_as_Generate_by_location() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByLocation();
		Thread.sleep(1000);
	}

	@When("^I select the site ID as \"([^\"]*)\" and from location on location tab$")
	public void i_select_the_site_ID_as_and_from_location_as_on_location_tab(String siteId) throws Throwable {
		String fromLocation = locationDB.getLocationResv("UnLocked");
		System.out.println("from loc" + fromLocation);
		context.setLocation(fromLocation);
		stockCheckListGenerationPage.selectSiteId(siteId);
		stockCheckListGenerationPage.enterLocation(context.getLocation());
		Thread.sleep(1000);
	}

	@When("^I proceed to next tab$")
	public void i_proceed_to_next_tab() throws Throwable {
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}

	@Then("^the available list should be displayed$")
	public void the_available_list_should_be_displayed() throws Throwable {
		Assert.assertTrue("Available List is not displayed.", stockCheckListGenerationPage.isListAvailable());
		Thread.sleep(1000);
	}

	@When("^I select the record from the available list$")
	public void i_select_the_record_from_the_available_list() throws Throwable {
		stockCheckListGenerationPage.clickAddButton();
		logger.debug("Record added to generate list");
		Thread.sleep(1000);
	}

	@Then("^the record should be added in the selected list$")
	public void the_record_should_be_added_in_the_selected_list() throws Throwable {
		stockCheckListGenerationPage.navigateToSelectedTab();
		Assert.assertTrue("Stock Check List not generated.", stockCheckListGenerationPage.isSelectedListAvailable());
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for number of location checked$")
	public void i_should_see_the_confirmation_for_number_of_location_checked() throws Throwable {
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		Assert.assertTrue("Stock Check List not generated as expected. " + getSelectedListConfirmationText,
				getSelectedListConfirmationText.contains("You have selected:"));
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for number of items checked$")
	public void i_should_see_the_confirmation_for_number_of_items_checked() throws Throwable {
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		ArrayList<String> failureList = new ArrayList<String>();
		if (getSelectedListConfirmationText.contains("You have not selected any tasks")) {
			failureList.add("Stock Check list confirmation text not displayed");
		} else {
			logger.debug("Stock Check list Confirmation : " + getSelectedListConfirmationText);
		}
		Assert.assertTrue("Stock Check List not generated as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		Thread.sleep(1000);
	}

	@When("^I proceed to generate the stock check list$")
	public void i_proceed_to_generate_the_stock_check_list() throws Throwable {
		jdaFooter.clickDoneButton();
		Thread.sleep(1000);
	}

	@Then("^I should see the created list$")
	public void i_should_see_the_created_list() throws Throwable {
		Assert.assertTrue("Stock Check List not generated as expected.",
				stockCheckListGenerationPage.isListIdPopupDisplayed());
		Thread.sleep(1000);
	}

	@When("^I select mode of list generation as 'Generate by inventory'$")
	public void i_select_mode_of_list_generation_as_Generate_by_inventory() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByInventory();
	}

	@When("^I enter the Tag ID as on inventory tab$")
	public void i_enter_the_Tag_ID_as_on_inventory_tab() throws Throwable {
		System.out.println("tag="+context.getTagId());
		stockCheckListGenerationPage.enterTagId(context.getTagId());

		
	}
	@When("^I enter the sku as on inventory tab$")
	public void i_enter_the_sku_as_on_inventory_tab() throws Throwable {
		System.out.println("tag="+context.getTagId());
		stockCheckListGenerationPage.EnterSkuAfterpicking(context.getSKUHang());

		
	}



	@When("^I enter listID and workZone$")
	public void i_enter_listID_and_workZone() throws Throwable {
		stockCheckListGenerationPage.enterListID(context.getListID());
		stockCheckListGenerationPage.nextTab();
		stockCheckListGenerationPage.enterworkZone(context.getworkZone());
		stockCheckListGenerationPage.PressEnter();
	}

	@When("^I enter listId and invalid workZone$")
	public void i_enter_listId_and_invalid_workZone() throws Throwable {
		Thread.sleep(1000);
		stockCheckListGenerationPage.enterListID(context.getListID());
		stockCheckListGenerationPage.nextTab();
		System.out.println("wrkzone"+context.getworkZone());
		String Zone=context.getworkZone();
		System.out.println("zone"+Zone);
		String WorkZone = stockCheckDB.getWorkZone(Zone);
		System.out.println("wrk"+WorkZone);
		Thread.sleep(1000);
		stockCheckListGenerationPage.enterworkZone(WorkZone);
		stockCheckListGenerationPage.PressEnter();
	}

	@Then("^I enter the tagId and location$")
	public void i_enter_the_tagId_and_location() throws Throwable {
		//String tagId = inventoryDB.getTagIdResvLoc();
		//System.out.println("tag id" + tagId);
//		context.setTagId(tagId);
		System.out.println("context" + context.getTagId());
		String Location = inventoryDB.getLocation(context.getTagId());
		jdaFooter.pressTab();
		stockCheckListGenerationPage.enterTag_IdInPutty(context.getTagId());
		jdaFooter.pressTab();
		stockCheckListGenerationPage.enterLocationInPutty(Location);
		//stockCheckListGenerationPage.enterTag_IdInPutty(context.getTagId());
		jdaFooter.PressEnter();
	}


	@Then("^I enter the listId$")
	public void i_enter_the_listId() throws Throwable {
		String listId = stockCheckTaskQueryPage.getListId();
		context.setListID(listId);
		Assert.assertNotNull("List ID is not generated as expected. Expected [not Null] but was [" + listId + "]",
				listId);
		logger.debug("List ID: " + listId);
		Thread.sleep(3000);

	}

	@Then("^I enter the quantity and sku")
	public void i_enter_the_quantity_and_sku() throws Throwable {
//
//		String QtyOnHand_tag = inventoryDB.getQtyOnHandTag(context.getTagId());
//		int QtyOnHandInt = Utilities.convertStringToInteger(QtyOnHand_tag);
//
//		String caseRatio = inventoryDB.getCaseRatio(context.getTagId());
//		int CaseRatioInt = Utilities.convertStringToInteger(caseRatio);
//		int qty = QtyOnHandInt + CaseRatioInt;
//		context.setqtyOnHandBeforeAdjustment(qty);
//		String IncreasedQty = Integer.toString(qty)+"E";

		String QtyOnHand_tag = inventoryDB.getQtyOnHandTag(context.getTagId());
		String TrimmedQuantity = QtyOnHand_tag.substring(0, QtyOnHand_tag.length() - 1);
		System.out.println("TrimmedQuantity="+TrimmedQuantity);
		int QtyOnHandInt = Utilities.convertStringToInteger(TrimmedQuantity);
		//int ChangedQuantity= (QtyOnHandInt/100)*100;
		 
		 
		 
		String caseRatio = inventoryDB.getCaseRatio(context.getTagId());
		 
		int CaseRatioInt = Utilities.convertStringToInteger(caseRatio);
		int ChangedQuantity= (QtyOnHandInt/CaseRatioInt)*CaseRatioInt;
		int qty = ChangedQuantity + CaseRatioInt;
		context.setqtyOnHandBeforeAdjustment(qty);
		String IncreasedQty = Integer.toString(qty)+ "E";
		
		
		jdaFooter.pressTab();
		
		stockCheckListGenerationPage.enterQty(IncreasedQty);
		Thread.sleep(3000);
		jdaFooter.PressEnter();
		Thread.sleep(3000);
		jdaFooter.PressEnter();
		Thread.sleep(2000);
//		puttyFunctionsPage.enterF11();
//		Thread.sleep(2000);
		jdaFooter.pressTab();
		stockCheckListGenerationPage.enterQty(IncreasedQty);
		jdaFooter.PressEnter();
		String sku = inventoryDB.getSkuId(context.getTagId());
		stockCheckListGenerationPage.entersku(sku);
		jdaFooter.PressEnter();
	}

	@Then("^I enter the checkstring,quantity and sku")
	public void I_enter_the_checkstring_quantity_and_sku() throws Throwable {

	
		String QtyOnHand_tag = inventoryDB.getQtyOnHandTag(context.getTagId());
		String TrimmedQuantity = QtyOnHand_tag.substring(0, QtyOnHand_tag.length() - 1);
		int QtyOnHandInt = Utilities.convertStringToInteger(TrimmedQuantity);
		//int ChangedQuantity= (QtyOnHandInt/100)*100;
		 
		 
		 
		String caseRatio = inventoryDB.getCaseRatio(context.getTagId());
		 
		int CaseRatioInt = Utilities.convertStringToInteger(caseRatio);
		int ChangedQuantity= (QtyOnHandInt/CaseRatioInt)*CaseRatioInt;
		int qty = ChangedQuantity + CaseRatioInt;
		context.setqtyOnHandBeforeAdjustment(qty);
		String IncreasedQty = Integer.toString(qty)+ "E";
		
		
		
jdaFooter.pressTab();
		
		stockCheckListGenerationPage.enterQty(IncreasedQty);
		Thread.sleep(3000);
		jdaFooter.PressEnter();
		Thread.sleep(3000);
		jdaFooter.PressEnter();
		Thread.sleep(2000);
		jdaFooter.pressTab();
		
		stockCheckListGenerationPage.enterQty(IncreasedQty);
		jdaFooter.PressEnter();
		String sku = inventoryDB.getSkuId(context.getTagId());
		stockCheckListGenerationPage.entersku(sku);
		jdaFooter.PressEnter();
	}

	@Given("^I proceed to complete the process for location \"([^\"]*)\"$")
	public void i_proceed_to_complete_the_process(String Location) throws Throwable {
		context.setlocationID(Location);
		stockCheckListGenerationPage.enterLocation(context.getlocationID());
		String toLoccheckString = locationDB.getCheckString(Location);
		System.out.println("checkstring" + toLoccheckString);
		stockCheckListGenerationPage.enterCheckString(toLoccheckString);
		// Assert.assertTrue("Relocation task in not completed",
		// stockRelocation.isRelocationCompleted());

	}

	@Given("^I proceed to next$")
	public void i_proceed_to_next() throws Throwable {
		stockCheckListGenerationPage.clickNextButton();
		//jDAFooter.clickNextButtonImg();
		Thread.sleep(1000);
	}
	@Given("^The Sku \"([^\"]*)\" is already picked$")
	public void the_sku_is_already_picked(String sku) throws Throwable{
		JDAExitUpiHeader.Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_with_for("Released","NONRETAIL","5542",sku);
		jDAExitputtyfunctionsStepDef.i_login_as_warehouse_user_in_putty();
		jDAExitputtyfunctionsStepDef.i_select_user_directed_option_in_main_menu();
		jDAExitputtyfunctionsStepDef.I_select_Receiving_menu();
		jDAExitputtyfunctionsStepDef.I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_ASN_with_batch_and_expiry_date();
		jDALoginStepDefs.login_to_JDA_Dispatcher_web_screen();
		jDAHomeStepDefs.Go_to_Inventory_Transaction_Click();
		jDAHomeStepDefs.click_on_Query();
		inventoryTransactionStepDefs.enter_container_id();
		jDAHomeStepDefs.click_execute();
		inventoryTransactionStepDefs.get_the_tag_id();
		inventoryTransactionStepDefs.check_the_inventory_transaction_for_receipt_allocate_pick();
	}
	
	
}
