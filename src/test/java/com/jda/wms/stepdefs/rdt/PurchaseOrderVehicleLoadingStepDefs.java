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
import com.jda.wms.pages.rdt.PurchaseOrderVehicleLoadingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PurchaseOrderVehicleLoadingStepDefs {
	private PurchaseOrderPickingPage purchaseOrderPickingPage;
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

	@Inject
	public PurchaseOrderVehicleLoadingStepDefs(PurchaseOrderPickingPage purchaseOrderPickingPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			MoveTaskDB moveTaskDB, OrderHeaderDB orderHeaderDB, AddressDB addressDB, MoveTaskUpdateDB moveTaskUpdateDB,
			PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage, BookingInDiary bookingInDiary) {
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

	}

	@Then("^Trailer should be loaded$")
	public void Trailer_should_be_loaded() throws Throwable {
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", "Complete", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^Trailer should be unload$")
	public void Trailer_should_be_unload() throws Throwable {
		String siteid = "5649";
		purchaseOrderVehicleLoadingPage.enterSiteId(siteid);
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		context.setConsignmentID("CON1");
		purchaseOrderVehicleLoadingPage.enterConsignment(context.getConsignmentID());
		jdaFooter.clickNextButton();
		purchaseOrderVehicleLoadingPage.selectRecord();
		jdaFooter.clickDoneButton();
		context.setOrderId("5104628650");
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
}
