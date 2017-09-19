package com.jda.wms.stepdefs.rdt;

import java.util.Map;
import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PurchaseOrderRelocatePage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
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
	private SupplierSkuDB supplierSkuDb;
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;

	@Inject
	public PurchaseOrderRelocateStepDefs(PurchaseOrderRelocatePage purchaseOrderRelocatePage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			SupplierSkuDB supplierSkuDb, PurchaseOrderPutawayPage purchaseOrderPutawayPage) {
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderRelocatePage = purchaseOrderRelocatePage;
		this.supplierSkuDb = supplierSkuDb;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
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
		purchaseOrderRelocatePage.enterPalletId(context.getUpiId());
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}

	@Given("^I proceed with entering the location and upc$")
	public void i_proceed_with_entering_the_location_and_upc() throws Throwable {
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		purchaseOrderRelocatePage.enterlocation(context.getLocation());
		jdaFooter.pressTab();
		purchaseOrderRelocatePage.enterUPC(context.getUPC());
		jdaFooter.PressEnter();
		String[] relocateLocation = purchaseOrderRelocatePage.getRelocateLocation().split("_");
		String relLocation = relocateLocation[0];
		context.setRelocateLoctn(relLocation);
		jdaFooter.PressEnter();
		hooks.logoutPutty();

	}

	@When("^I proceed with entering the upc and location$")
	public void i_proceed_with_entering_the_upc_and_location() throws Throwable {

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) context.getSkuFromPO().get(i - 1));
			String upc = context.getUPIMap().get(context.getSkuId()).get("UPC");
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterLocation(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterUPC(upc);
			jdaFooter.PressEnter();
			Assert.assertTrue("RecPalCmp page not displayed", purchaseOrderRelocatePage.isRelPalCmpDisplayed());
			context.setToLocation(purchaseOrderPutawayPage.getToLocation());
			context.setFromLocation(context.getToLocation());
			jdaFooter.PressEnter();
			if (purchaseOrderRelocatePage.isChkToDisplayed()) {
				Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
				purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
				jdaFooter.PressEnter();
			}
			Assert.assertTrue("RelEnt page not displayed", purchaseOrderRelocatePage.isRelEntDisplayed());
		}

		hooks.logoutPutty();
	}

	@When("^I proceed with entering the returns upc and location$")
	public void i_proceed_with_entering_the_returns_upc_and_location() throws Throwable {

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) context.getSkuFromUPI().get(i - 1));
			String upc = context.getUPIMap().get(context.getSkuId()).get("UPC");
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterLocation(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterUPC(upc);
			jdaFooter.PressEnter();
			if (!purchaseOrderRelocatePage.isNoRelocationDisplayed()) {
				System.out.println("Inside Loop");
				Assert.assertTrue("RecPalCmp page not displayed", purchaseOrderRelocatePage.isRelPalCmpDisplayed());
				context.setToLocation(purchaseOrderPutawayPage.getToLocation());
				context.setFromLocation(context.getToLocation());
				jdaFooter.PressEnter();
				if (purchaseOrderRelocatePage.isChkToDisplayed()) {
					Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
					purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
					jdaFooter.PressEnter();
				}
				Assert.assertTrue("RelEnt page not displayed", purchaseOrderRelocatePage.isRelEntDisplayed());
			} else {
				context.setFromLocation(context.getLocation());
			}
		}

		hooks.logoutPutty();
	}

	@When("^I proceed with entering the upc and location of FSV PO$")
	public void i_proceed_with_entering_the_upc_and_location_of_FSV_PO() throws Throwable {

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) context.getSkuFromPO().get(i - 1));
			String upc = context.getPOMap().get(i).get("UPC");
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterLocation(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterUPC(upc);
			jdaFooter.PressEnter();
			if (!purchaseOrderRelocatePage.isNoRelocationDisplayed()) {
			Assert.assertTrue("RecPalCmp page not displayed", purchaseOrderRelocatePage.isRelPalCmpDisplayed());
			context.setToLocation(purchaseOrderPutawayPage.getToLocation());
			context.setFromLocation(context.getToLocation());
			jdaFooter.PressEnter();
			if (purchaseOrderRelocatePage.isChkToDisplayed()) {
				Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
				purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
				jdaFooter.PressEnter();
			} else {
			}
			Assert.assertTrue("RelEnt page not displayed", purchaseOrderRelocatePage.isRelEntDisplayed());
			}
		}

		hooks.logoutPutty();
	}

	@When("^I proceed with entering the pallet id,upc and location$")
	public void i_proceed_with_entering_the_pallet_id() throws Throwable {

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) context.getSkuFromUPI().get(i - 1));
			String upc = context.getUPIMap().get(context.getSkuId()).get("UPC");
			purchaseOrderRelocatePage.enterPalletId(context.getUpiId());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterLocation(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderRelocatePage.enterUPC(upc);
			jdaFooter.PressEnter();
			Assert.assertTrue("RecPalCmp page not displayed", purchaseOrderRelocatePage.isRelPalCmpDisplayed());
			context.setToLocation(purchaseOrderPutawayPage.getToLocation());
			context.setFromLocation(context.getToLocation());
			jdaFooter.PressEnter();
			Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			Assert.assertTrue("RelEnt page not displayed", purchaseOrderRelocatePage.isRelEntDisplayed());
		}

		hooks.logoutPutty();
	}

}