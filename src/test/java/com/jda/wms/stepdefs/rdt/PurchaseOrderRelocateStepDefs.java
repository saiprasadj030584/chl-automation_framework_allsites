package com.jda.wms.stepdefs.rdt;

import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PurchaseOrderRelocatePage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.When;

public class PurchaseOrderRelocateStepDefs {
	private PurchaseOrderRelocatePage purchaseOrderRelocatePage;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private Verification verification;
	private InventoryDB inventoryDB;
	private LocationDB locationDB;
	private Hooks hooks;
	private JDAFooter jdaFooter;
	private PuttyFunctionsPage puttyFunctionsPage;
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;

	@Inject
	public PurchaseOrderRelocateStepDefs(PurchaseOrderRelocatePage purchaseOrderRelocatePage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,PurchaseOrderPutawayPage purchaseOrderPutawayPage) {
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderRelocatePage = purchaseOrderRelocatePage;
		this.purchaseOrderPutawayPage=purchaseOrderPutawayPage;
	}

	@When("^I choose existing relocate$")
	public void i_choose_existing_relocate() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderRelocatePage.selectRelocateMenu();
		purchaseOrderRelocatePage.selectExistingRelocateMenu();

	}

	@When("^I proceed with entering the palletid$")
	public void i_proceed_with_entering_the_palletid() throws Throwable {
		// purchaseOrderRelocatePage.enterPalletId("00050456000253606176");
		purchaseOrderRelocatePage.enterPalletId(context.getUpiId());
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}
	
	@When("^I proceed with entering the upc and location$")
	public void i_proceed_with_entering_the_upc_and_location() throws Throwable {
		
		for (int i = 1; i <= context.getNoOfLines(); i++) {
		//for (int i = 1; i <=1; i++) {
		context.setSkuId((String) context.getSkuFromPO().get(i - 1));
		String upc=context.getUPIMap().get(context.getSkuId()).get("UPC");
			//context.setLocation("REC002");
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		purchaseOrderRelocatePage.enterLocation(context.getLocation());
		jdaFooter.pressTab();
		purchaseOrderRelocatePage.enterUPC(upc);
		jdaFooter.PressEnter();
		Assert.assertTrue("RecPalCmp page not displayed",
				purchaseOrderRelocatePage.isRelPalCmpDisplayed());
		context.setToLocation(purchaseOrderPutawayPage.getToLocation());
		context.setFromLocation(context.getToLocation());
		jdaFooter.PressEnter();
		Assert.assertTrue("ChkTo page not displayed",
				purchaseOrderRelocatePage.isChkToDisplayed());
		purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
		jdaFooter.PressEnter();
		Assert.assertTrue("RelEnt page not displayed",
				purchaseOrderRelocatePage.isRelEntDisplayed());
		}
		

	hooks.logoutPutty();
}
}
