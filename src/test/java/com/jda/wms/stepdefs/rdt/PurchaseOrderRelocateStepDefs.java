package com.jda.wms.stepdefs.rdt;

import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
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

	@Inject
	public PurchaseOrderRelocateStepDefs(PurchaseOrderRelocatePage purchaseOrderRelocatePage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			SupplierSkuDB supplierSkuDb) {
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
}
