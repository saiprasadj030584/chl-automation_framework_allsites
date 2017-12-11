package com.jda.wms.pages.rdt;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;

import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;

import cucumber.api.java.en.Then;

public class PurchaseOrderStockCheckPage {


Screen screen = new Screen();
	Context context = new Context();
	int timeoutInSec = 20;
	private PuttyFunctionsPage puttyFunctionsPage;


	private JDAFooter jdaFooter;

	private Hooks hooks;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private SupplierSkuDB supplierSkuDb;
	private LocationDB locationDB;

	@Inject
	public PurchaseOrderStockCheckPage(PuttyFunctionsPage puttyFunctionsPage, Context context, JDAFooter jdaFooter,
			Hooks hooks, PuttyFunctionsStepDefs puttyFunctionsStepDefs, SupplierSkuDB supplierSkuDB,
			LocationDB locationDB) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.context = context;

		this.jdaFooter = jdaFooter;

		this.hooks = hooks;
		this.supplierSkuDb = supplierSkuDb;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.locationDB = locationDB;
	}

	public void selectInventoryMenu() throws FindFailed, InterruptedException {
		screen.type("5");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectNewStockCheck() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isNewStockCheckEntPageDisplayed() {
		if (screen.exists("images/Putty/StockCheck/NewStockCheck/StkNewEnt.png") != null)
			return true;
		else
			return false;
	}

	public void i_enter_location(String location) throws FindFailed, InterruptedException {
		screen.type(location);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void i_enter_no_or_yes(String asn) throws InterruptedException {
		screen.type(asn);
		Thread.sleep(2000);
	}

	public void i_enter_supplier(String supplier) throws InterruptedException {
		screen.type(supplier);
		Thread.sleep(2000);
	}

	public void i_enter_upc(String upc) throws InterruptedException {
		screen.type(upc);
		Thread.sleep(2000);
	}

	public void i_enter_quantity(String qty) throws InterruptedException {
		screen.type(qty);
		Thread.sleep(2000);
	}

	public void i_do_new_stock_check_at_location_with_quantity(String location, String qty) throws Throwable {
		context.setLocationID(location);
		context.setQtyOrdered(Integer.parseInt(qty));
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		puttyFunctionsPage.nextScreen();
		selectInventoryMenu();
		selectNewStockCheck();
		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());
		i_do_new_stock_check();
	}

	public void i_do_new_stock_check_at_location_with_quantity_and_supplier(String location, String qty,
			String supplier) throws Throwable {
		context.setLocationID(location);
		context.setQtyOrdered(Integer.parseInt(qty));
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId(), supplier));
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		puttyFunctionsPage.nextScreen();
		selectInventoryMenu();
		selectNewStockCheck();
		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());

		i_do_new_stock_check_with_supplier(supplier);
		hooks.logoutPutty();
	}

	public void i_do_new_stock_check_with_supplier(String supplier) throws Throwable {
		i_enter_location(context.getLocationID());// valid location that should
													// nt be inventory
		context.setToLocation(context.getLocationID());

		jdaFooter.PressEnter();
		i_enter_no_or_yes("Y");
		jdaFooter.PressEnter();
		jdaFooter.pressTab();
		i_enter_upc(context.getUPC());
		jdaFooter.pressTab();
		i_enter_supplier(supplier);
		jdaFooter.pressTab();
		i_enter_quantity(String.valueOf(context.getQtyOrdered()));
		jdaFooter.PressEnter();
		if (isChkToDisplayed()) {
			i_enter_the_check_string();
		}
		jdaFooter.PressEnter();
		i_enter_no_or_yes("N");
		jdaFooter.PressEnter();

		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());
	}

	public void i_do_new_stock_check() throws Throwable {
		i_enter_location(context.getLocationID());
		if (isChkToDisplayed()) {
			i_enter_the_check_string();
		}
		i_enter_quantity(String.valueOf(context.getQtyOrdered()));
		jdaFooter.PressEnter();
		i_enter_quantity(String.valueOf(context.getQtyOrdered()));
		i_enter_no_or_yes("N");
		jdaFooter.PressEnter();
		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());
	}

	public boolean isChkToDisplayed() {
		if (screen.exists("images/Putty/Putaway/ChkTo.png") != null)
			return true;
		else
			return false;
	}

	public void i_enter_the_check_string() throws Throwable {
		Assert.assertTrue("Chk To Page not displayed to enter check string", isChkToDisplayed());
		enterCheckString(locationDB.getCheckString(context.getToLocation()));

	}

	public void enterCheckString(String chkString) throws InterruptedException {
		screen.type(chkString);
		Thread.sleep(2000);
	}
}

