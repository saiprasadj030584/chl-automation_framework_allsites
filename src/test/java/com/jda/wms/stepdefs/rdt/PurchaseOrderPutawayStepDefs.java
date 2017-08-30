package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PurchaseOrderRelocatePage;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPutawayStepDefs {
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private Verification verification;
	private InventoryDB inventoryDB;
	private InventoryTransactionDB inventoryTransactionDB;
	private LocationDB locationDB;
	private Hooks hooks;
	private JDAFooter jdaFooter;
	private PurchaseOrderRelocatePage purchaseOrderRelocatePage;

	@Inject
	public PurchaseOrderPutawayStepDefs(PurchaseOrderPutawayPage purchaseOrderPutawayPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter,PurchaseOrderRelocatePage purchaseOrderRelocatePage,InventoryTransactionDB inventoryTransactionDB) {
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.purchaseOrderRelocatePage=purchaseOrderRelocatePage;
		this.inventoryTransactionDB=inventoryTransactionDB;
	}

	@When("^I select normal putaway$")
	public void i_select_normal_putaway() throws Throwable {
		purchaseOrderPutawayPage.selectPutawayMenu();
		purchaseOrderPutawayPage.selectNormalPutawayMenu();
	}

	@When("^I do normal putaway for all tags received$")
	public void i_do_normal_putaway_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			i_enter_urn_id_in_putaway();
			the_tag_details_for_putaway_should_be_displayed();
			context.setToLocation(inventoryDB.getPutawayLocation(context.getSkuId(), context.getLocation()));
			i_enter_to_location(context.getToLocation());
			i_enter_the_check_string();
			if (!purchaseOrderPutawayPage.isPutEntDisplayed()) {
				failureList.add("Putaway not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}
	
//	@When("^I choose normal putaway$")
//	public void i_choose_normal_putaway() throws Throwable {
//		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
//		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
//		i_select_normal_putaway();
//		i_should_be_directed_to_putent_page();
//	}
	
//	@When("^I select normal putaway$")
//	public void i_select_normal_putaway() throws Throwable {
//		purchaseOrderPutawayPage.selectPutawayMenu();
//		purchaseOrderPutawayPage.selectNormalPutawayMenu();
//	}
	
//	@Then("^I should be directed to putent page$")
//	public void i_should_be_directed_to_putent_page() throws Throwable {
//		Assert.assertTrue("Putaway Home page not displayed.", purchaseOrderPutawayPage.isPutEntDisplayed());
//	}
	
	

	@When("^I choose normal putaway$")
	public void i_choose_normal_putaway() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			i_enter_urn_id_in_putaway();
			jdaFooter.PressEnter();
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed();
			}
			jdaFooter.PressEnter();
		}
	}
	
	
	
	@When("^I perform normal putaway after relocation for FSV PO$")
	public void i_perform_normal_putaway_after_relocation_for_fsv_po() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagID(context.getPreAdviceId(),"Receipt",context.getSkuId(), date));
			context.setUpiId(context.getTagId());
			i_enter_urn_id_in_putaway_for_fsv_po(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();
				
			}
			jdaFooter.PressEnter();
			Assert.assertTrue("ChkTo page not displayed",
					purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			i_should_be_directed_to_putent_page();
			
		}
	}

	
	@When("^I perform normal putaway$")
	public void i_perform_normal_putaway() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagID(context.getPreAdviceId(),"Receipt",context.getSkuId(), date));
			context.setUpiId(context.getTagId());
			i_enter_urn_id_in_putaway(context.getTagId());
			
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();
				
			}
			jdaFooter.PressEnter();
			if(purchaseOrderRelocatePage.isChkToDisplayed())
			{
			Assert.assertTrue("ChkTo page not displayed",
					purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			}
			else
			{
				
			}
			i_should_be_directed_to_putent_page();
			
		}
	}
	
	@When("^I perform normal putaway after relocation$")
	public void i_perform_normal_putaway_after_relocation() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagID(context.getPreAdviceId(),"Receipt",context.getSkuId(), date));
			context.setUpiId(context.getTagId());
			i_enter_urn_id_in_putaway(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();
				
			}
			jdaFooter.PressEnter();
			if(purchaseOrderRelocatePage.isChkToDisplayed())
			{
			Assert.assertTrue("ChkTo page not displayed",
					purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			}
			else
			{
				
			}
			i_should_be_directed_to_putent_page();
			
		}
	}
	
	@When("^I perform normal returns putaway after relocation$")
	public void i_perform_normal_returns_putaway_after_relocation() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		//poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(context.getSkuFromUPI().get(i-1));
			System.out.println(context.getSkuId());
			context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(),"Receipt",context.getSkuId(), date));
			i_enter_urn_id_in_putaway(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();
				
			}
			jdaFooter.PressEnter();
			if(purchaseOrderRelocatePage.isChkToDisplayed())
			{
			Assert.assertTrue("ChkTo page not displayed",
					purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			}
			else
			{
				
			}
			i_should_be_directed_to_putent_page();
			
		}
	}

	

	
	@When("^I perform normal returns putaway$")
	public void i_perform_normal_returns_putaway() throws Throwable {
		System.out.println(context.getUpiId());
		
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(context.getSkuFromUPI().get(i-1));
			System.out.println(context.getSkuId());
			context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(),"Receipt",context.getSkuId(), date));
			i_enter_urn_id_in_putaway(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed();
				
			}
			jdaFooter.PressEnter();
			if(purchaseOrderRelocatePage.isChkToDisplayed())
			{
			Assert.assertTrue("ChkTo page not displayed",
					purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			}
			i_should_be_directed_to_putent_page();
			
		}
	}
	

	@When("^I should not be able to putaway locked PO$")
	public void i_should_not_be_able_to_putaway_locked_po() throws Throwable {
		i_choose_normal_putaway();
		Assert.assertFalse("Putaway details page should not be displayed as the PO is locked for putaway",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
	}

	@When("^I proceed without entering location$")
	public void i_proceed_without_entering_location() throws InterruptedException {
		jdaFooter.PressEnter();
	}

	@When("^the error message should be displayed as cannot find putaway location$")
	public void the_error_message_should_be_displayed_as_cannot_find_putaway_location() throws InterruptedException {
		Assert.assertTrue("Error message:Cannot find putaway location not displayed as expected",
				purchaseOrderPutawayPage.isLocationErrorDisplayed());
		jdaFooter.PressEnter();
	}

	@When("^I proceed without entering quantity$")
	public void i_proceed_without_entering_quantity() throws InterruptedException {
		jdaFooter.pressTab();
		for (int i = 0; i < 25; i++) {
			jdaFooter.pressBackSpace();
		}
		jdaFooter.PressEnter();
	}

	@When("^the error message should be displayed as invalid quantity exception$")
	public void the_error_message_should_be_displayed_as_invalid_quantity_exception() throws InterruptedException {
		Assert.assertTrue("Error message:Invalid Quantity Exception not displayed as expected",
				purchaseOrderPutawayPage.isQuantityErrorDisplayed());
		jdaFooter.PressEnter();
	}

	@When("^I enter to location$")
	public void i_enter_to_location(String location) throws InterruptedException {
		purchaseOrderPutawayPage.enterLocation(location);
	}

	@Then("^I should be directed to putent page$")
	public void i_should_be_directed_to_putent_page() throws Throwable {
		Assert.assertTrue("Putaway Home page not displayed.", purchaseOrderPutawayPage.isPutEntDisplayed());
	}

	@Then("^I enter the check string$")
	public void i_enter_the_check_string() throws Throwable {
		Assert.assertTrue("Chk To Page not displayed to enter check string",
				purchaseOrderPutawayPage.isChkToDisplayed());
		purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));
	}

	@When("^I enter urn id in putaway$")
	public void i_enter_urn_id_in_putaway() throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(context.getUpiId());
	}
	
	public void i_enter_urn_id_in_putaway(String tagId) throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(tagId);
		
	}
	
	@When("^I enter urn id in putaway for FSV PO$")
	public void i_enter_urn_id_in_putaway_for_fsv_po() throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(context.getUpiId());
	}
	
	public void i_enter_urn_id_in_putaway_for_fsv_po(String tagId) throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(tagId);
	}

	@When("^the tag details for putaway should be displayed after relocation$")
	public void the_tag_details_for_putaway_should_be_displayed_after_relocation() throws FindFailed, InterruptedException {
		//context.setFromLocation("MEZF2"); //remove
		ArrayList failureList = new ArrayList();
//		Assert.assertTrue("PutCmp page not displayed to enter To Location",
//				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
//		verification.verifyData("From Location", context.getFromLocation(), purchaseOrderPutawayPage.getFromLocation(),
//				failureList);
//		verification.verifyData("Tag ID", context.getUpiId(), purchaseOrderPutawayPage.getTagId(), failureList);
		if(purchaseOrderPutawayPage.getToLocation()!=null)
		{
		context.setToLocation(purchaseOrderPutawayPage.getToLocation());
		}
		else
		{
			context.setToLocation("REC003");
			purchaseOrderPutawayPage.enterToLocation("REC003");
		}
		Assert.assertTrue("SKU Attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@When("^the tag details for putaway should be displayed$")
	public void the_tag_details_for_putaway_should_be_displayed() throws FindFailed, InterruptedException {
		//context.setFromLocation("MEZF2"); //remove
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("PutCmp page not displayed to enter To Location",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		verification.verifyData("From Location", context.getlocationID(), purchaseOrderPutawayPage.getFromLocation(),
				failureList);
		context.setFromLocation(context.getlocationID());
		verification.verifyData("Tag ID", context.getTagId(), purchaseOrderPutawayPage.getTagId(), failureList);
		context.setToLocation(purchaseOrderPutawayPage.getToLocation());
		Assert.assertTrue("SKU Attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the  error message should be displayed$")
	public void the_error_message_should_be_displayed() throws Throwable {
		Assert.assertTrue(
				" error message is not displayed. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}
}
