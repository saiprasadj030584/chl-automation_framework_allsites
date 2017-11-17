package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.MoveTaskUpdateDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;

import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPickingPage;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PurchaseOrderStockCheckPage;
import com.jda.wms.pages.rdt.PurchaseOrderVehicleLoadingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PurchaseOrderStockCheckStepDefs {
	private PurchaseOrderPickingPage purchaseOrderPickingPage;
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
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
	private MoveTaskDB moveTaskDB;
	private OrderHeaderDB orderHeaderDB;
	private AddressDB addressDB;
	private MoveTaskUpdateDB moveTaskUpdateDB;
	private PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage;
	private BookingInDiary bookingInDiary;
	private PurchaseOrderStockCheckPage purchaseOrderStockCheckPage;
	private PurchaseOrderPutawayStepDefs purchaseOrderPutawayStepDefs;

	@Inject
	public PurchaseOrderStockCheckStepDefs(PurchaseOrderPickingPage purchaseOrderPickingPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			MoveTaskDB moveTaskDB, OrderHeaderDB orderHeaderDB, AddressDB addressDB, MoveTaskUpdateDB moveTaskUpdateDB,
			PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage, BookingInDiary bookingInDiary,PurchaseOrderStockCheckPage purchaseOrderStockCheckPage,PurchaseOrderPutawayPage purchaseOrderPutawayPage) {
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderPickingPage = purchaseOrderPickingPage;
		this.moveTaskDB = moveTaskDB;
		this.orderHeaderDB = orderHeaderDB;
		this.addressDB = addressDB;
		this.moveTaskUpdateDB = moveTaskUpdateDB;
		this.purchaseOrderVehicleLoadingPage = purchaseOrderVehicleLoadingPage;
		this.bookingInDiary = bookingInDiary;
		this.purchaseOrderStockCheckPage=purchaseOrderStockCheckPage;
		this.purchaseOrderPutawayPage=purchaseOrderPutawayPage;

	}

	@Then("^I do new stock check at location \"([^\"]*)\" with quantity \"([^\"]*)\"$")
	public void i_do_new_stock_check_at_location_with_quantity(String location,String qty) throws Throwable {
		context.setLocationID(location);
		context.setQtyOrdered(Integer.parseInt(qty));
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		puttyFunctionsPage.nextScreen();
		i_select_inventory();
		i_select_new_stock_check();
		i_do_new_stock_check();
	}
	
	@Given("^I select inventory$")
	public void i_select_inventory() throws Throwable {
		purchaseOrderStockCheckPage.selectInventoryMenu();
		
	}

	@Given("^I select new stock check$")
	public void i_select_new_stock_check() throws Throwable {
		purchaseOrderStockCheckPage.selectNewStockCheck();
		Assert.assertTrue("Stock Check Menu not displayed as expected",
				purchaseOrderStockCheckPage.isNewStockCheckEntPageDisplayed());
		
	}
	
	@Given("^I enter location \"([^\"]*)\"$")
	public void i_enter_location(String location) throws Throwable {
		purchaseOrderStockCheckPage.i_enter_location(location);
	}
	
	@Given("^I enter quantity \"([^\"]*)\"$")
	public void i_enter_quantity(String qty) throws Throwable {
		purchaseOrderStockCheckPage.i_enter_location(qty);
	}
	
	@Given("^I do new stock check$")
	public void i_do_new_stock_check() throws Throwable {
	i_enter_location(context.getLocationID());
	if(purchaseOrderPutawayPage.isChkToDisplayed()){
		purchaseOrderPutawayStepDefs.i_enter_the_check_string();
	}
	i_enter_quantity(String.valueOf(context.getQtyOrdered()));
	jdaFooter.PressEnter();
	i_enter_quantity(String.valueOf(context.getQtyOrdered()));
	i_enter_no_or_yes("N");
	jdaFooter.PressEnter();
	Assert.assertTrue("Stock Check Menu not displayed as expected",
			purchaseOrderStockCheckPage.isNewStockCheckEntPageDisplayed());
	}
	
	@Given("^I enter NO or YES \"([^\"]*)\"")
	public void i_enter_no_or_yes(String value) throws Throwable {
		purchaseOrderStockCheckPage.i_enter_no_or_yes(value);
	}
	
	
	
	

	
}
