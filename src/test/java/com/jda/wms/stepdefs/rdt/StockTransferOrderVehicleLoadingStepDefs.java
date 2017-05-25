package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.InventoryTransactionDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.Verification;
/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.StockTransferOrderVehicleLoadingPage;*/
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.pages.rdt.StockTransferOrderVehicleLoadingPage;
import com.jda.wms.stepdefs.foods.JDAHomeStepDefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockTransferOrderVehicleLoadingStepDefs {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final PuttyFunctionsPage puttyFunctionsPage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final JDAFooter jdaFooter;
	private Context context;
	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private Verification verification;
	private JdaHomePage jdaHomePage;
	private StockTransferOrderVehicleLoadingPage stockTransferOrderVehicleLoadingPage;
	private ArrayList<String> palletIDList;
	private InventoryTransactionDB inventoryTransactionDB;

	@Inject
	public StockTransferOrderVehicleLoadingStepDefs(
			StockTransferOrderVehicleLoadingPage stockTransferOrderVehicleLoadingPage, Context context,
			JdaHomePage jdaHomePage, PuttyFunctionsPage puttyFunctionsPage, JDAHomeStepDefs jdaHomeStepDefs,
			JDAFooter jdaFooter, OrderHeaderMaintenancePage orderHeaderMaintenancePage, Verification verification,
			InventoryTransactionDB inventoryTransactionDB) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.verification = verification;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.stockTransferOrderVehicleLoadingPage = stockTransferOrderVehicleLoadingPage;
		this.inventoryTransactionDB = inventoryTransactionDB;
	}

	/*
	 * @When(
	 * "^I navigate to Order Header Page and search using Order id \"([^\"]*)\"$"
	 * ) public void
	 * i_navigate_to_Order_Header_Page_and_search_using_Order_id(String orderID)
	 * throws Throwable { jdaHomeStepDefs.i_navigate_to_order_header();
	 * jdaFooter.clickQueryButton();
	 * orderHeaderMaintenancePage.enterOrderNo(orderID);
	 * context.setOrderId(orderID); jdaFooter.clickExecuteButton(); }
	 */

	@When("^I navigate to load menu$")
	public void i_navigate_to_load_menu() throws Throwable {
		stockTransferOrderVehicleLoadingPage.navigateToLoadMenu();
	}

	@When("^I perform vehicle loading for all the pallets$")
	public void i_perform_vehicle_loading_for_all_the_pallets() throws Throwable {
		ArrayList<String> palletIDList = new ArrayList<String>();
		// context.getTrailer(); //TODO to update after Swetha completes
		// ShipDock and Trailer creation
		String trailer = "1701";
		palletIDList = context.getPalletIDList();

		stockTransferOrderVehicleLoadingPage.navigateToVehicleLoad();

		for (int i = 0; i < palletIDList.size(); i++) {
			stockTransferOrderVehicleLoadingPage.enterPalletID(palletIDList.get(i));
			Thread.sleep(2000);

			if (i < 1) {
				stockTransferOrderVehicleLoadingPage.enterTrailer(trailer);
				Thread.sleep(3000);
				puttyFunctionsPage.pressEnter();
			}

			puttyFunctionsPage.pressEnter();
			Thread.sleep(2000);
		}
		Thread.sleep(3000);
	}

	@Then("^the vehicle loading should be completed$")
	public void the_vehicle_loading_should_be_completed() throws Throwable {
		logger.debug(" Vehicle Loading Completed");
		Assert.assertTrue("Vehicle loading not completed as expected ",
				stockTransferOrderVehicleLoadingPage.isVehicleLoadComplete());
	}

	@Then("^the inventory transaction should be generated for vehicle load$")
	public void the_inventory_transaction_should_be_generated_for_vehicle_load() throws Throwable {
		Assert.assertEquals("Vehicle Load ITLs generated not as expected ", context.getMoveTaskRecordCount(),
				inventoryTransactionDB.getVehicleLoadITLRecords().size());
	}
}