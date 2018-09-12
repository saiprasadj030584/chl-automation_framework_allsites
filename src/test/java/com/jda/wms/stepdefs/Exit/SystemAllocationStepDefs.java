package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.LocationDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.db.Exit.OrderLineDB;
import com.jda.wms.pages.Exit.SystemAllocationPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class SystemAllocationStepDefs {
	// private static final String = null;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private SystemAllocationPage systemAllocationPage;
	private JDAFooter jDAFooter;
	private OrderHeaderDB orderHeaderDB;
	private Context context;
	private JDAHomeStepDefs jdaHomeStepDefs;
	// private OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepDefs;
	private OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs;
	private OrderLineDB orderLineDB;
	private InventoryDB inventoryDB;
	private LocationDB locationDB;
	private DataSetupRunner dataSetupRunner;
	private GetTCData getTCData;
//	private SystemAllocationStepDefs systemAllocationStepDefs;
	private JDALoginStepDefs jdaLoginStepDefs;
	@Inject
	public SystemAllocationStepDefs(SystemAllocationPage systemAllocationPage, JDAFooter jDAFooter,
			OrderHeaderDB orderHeaderDB, Context context, JDAHomeStepDefs jdaHomeStepDefs,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs, OrderLineDB orderLineDB, InventoryDB inventoryDB,
			LocationDB locationDB,DataSetupRunner dataSetupRunner,GetTCData getTCData,JDALoginStepDefs jdaLoginStepDefs) {
		this.systemAllocationPage = systemAllocationPage;
		this.dataSetupRunner=dataSetupRunner;
		this.getTCData=getTCData;
		this.jDAFooter = jDAFooter;
		this.orderHeaderDB = orderHeaderDB;
		this.context = context;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
//		this.systemAllocationStepDefs=systemAllocationStepDefs;
		this.jdaLoginStepDefs=jdaLoginStepDefs;
		// this.orderHeaderMaintenanceStepDefs=orderHeaderMaintenanceStepDefs;
		this.orderLineMaintenanceStepDefs = orderLineMaintenanceStepDefs;
		this.orderLineDB = orderLineDB;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
	}

	@When("^I have order id \"([^\"]*)\" with soft allocated status$")
	public void i_have_order_id_with_soft_allocated_status(String orderId) throws Throwable {
		String softAllocated = orderHeaderDB.getSoftAllocated(orderId);
		if (softAllocated == null) {
			orderHeaderDB.updateSoftAllocated(orderId);
		}
		systemAllocationPage.enterOrderId(orderId);
	}

	@When("^the record should be displayed in system allocation$")
	public void the_record_should_be_displayed_in_system_allocation() throws Throwable {
		Assert.assertTrue("Record is not displayed for the order in System allocation",
				systemAllocationPage.isRecordExist());
	}

	@When("^I system allocate the order$")
	public void i_system_allocate_the_order() throws Throwable {
		Thread.sleep(3000);
		jdaHomeStepDefs.i_navigate_to_system_allocation_page();
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		i_have_order_id_with_soft_allocated_status(context.getOrderId());
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		the_record_should_be_displayed_in_system_allocation();
		i_proceed_to_allocate_the_record();
	}
	
	@When("^I system allocate the order for locking location$")
	public void i_system_allocate_the_order_for_locking_location() throws Throwable {
		Thread.sleep(5000);
		jdaHomeStepDefs.i_navigate_to_system_allocation_page();
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		i_have_order_id_with_soft_allocated_status(context.getOrderId());
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		jDAFooter.clickDoneButton();
		Thread.sleep(10000);
		
	}


	@When("^I validate the shortages tab$")
	public void i_validate_the_shortages_tab() throws Throwable {
		Thread.sleep(5000);
		Assert.assertTrue("Shortages is not displayed for the order in System allocation",
				systemAllocationPage.isShortagesTabDisplayed());
		//String getShortagesTabDisplayed = systemAllocationPage.getShortagesTabDisplayed();
		//Assert.assertTrue("Move task List not generated as expected. " + getShortagesTabDisplayed,
				//getShortagesTabDisplayed.contains("shortages"));
		Thread.sleep(1000);
		jDAFooter.clickDoneButton();
		Thread.sleep(5000);
//		the_order_should_be_in_this_status("Released");
	}
	
	
	@When("^I proceed to allocate the record$")
	public void i_proceed_to_allocate_the_record() throws Throwable {
		jDAFooter.clickNextButton();
		Thread.sleep(10000);
		jDAFooter.clickDoneButton();
		Thread.sleep(10000);
		jDAFooter.clickDoneButton();
		Thread.sleep(10000);
	}

	
	@Given("^i have the orderId for allocation using the sku \"([^\"]*)\" and location in \"([^\"]*)\" status$")
	public void i_have_the_orderId_for_allocation_using_the_sku_and_location_in_status(String sku,String status) throws Throwable {
		
		context.setStatus(status);
		ArrayList<String> skuID = orderLineDB.getskuList(context.getOrderId());
		Map<Integer, Map<String, String>> LocationlistIDMap = new HashMap<Integer, Map<String, String>>();
		// Map<Integer, Map<String, String>> FinalLocationlistIDMap = new
		// HashMap<Integer, Map<String, String>>();
		String[] skuSize = new String[skuID.size()];

		// ArrayList<String> location =
		// moveTaskDB.getFromLocationList(context.getOrderId());
		// String LocationArray[] = new String[location.size()];
		for (int j = 0; j < skuID.size(); j++) {
			skuSize[j] = skuID.get(j);
		}
		//String sku1= skuID.get(0);
		//System.out.println("sku id" + sku1);
		ArrayList<String> initialLocation = inventoryDB.getLocationID(sku);
		
		System.out.println("loc" + context.getListIDMap());
		
		//locationDB.updateLocation(initialLocation1,status);
		for (int j = 0; j < initialLocation.size(); j++) {
		locationDB.updateLocation(initialLocation.get(j), status);
		}
		
//		for (String sku : skuSize) {
//			System.out.println("sku id" + sku);
//			ArrayList<String> initialLocation = inventoryDB.getLocationID(sku);
//			//String initialLocation=inventoryDB.getLocationID(sku);
//			context.setPalletIDList(initialLocation);
//			for (int j = 0; j < initialLocation.size(); j++) {
//				Map<String, String> LocationListMap = new HashMap<String, String>();
//				LocationListMap.put(initialLocation.get(j), locationDB.getStatus(initialLocation.get(j)));
//				System.out.println("loc" + context.getListIDMap());
//				// String[] location=new String[initialLocation.size()];
//				LocationlistIDMap.put(j + 1, LocationListMap);
//				// locationDB.get
//				locationDB.updateLocation(initialLocation.get(j), status);
//			}
//		}
		Thread.sleep(5000);
	}

	@When("^the location should be changed to \"([^\"]*)\" for the sku \"([^\"]*)\"$")
	public void the_location_should_be_changed_to_for_the_sku(String status,String sku) throws Throwable {
		context.setSkuId(sku);
		
		ArrayList<String> initialLocation = inventoryDB.getLocationID(sku);
//System.out.println("loc" + context.getListIDMap());
		for (int j = 0; j < initialLocation.size(); j++) {
		locationDB.updateLocation(initialLocation.get(j),status);
		}
		
	}

	@When("^the order should be allocated$")
	public void the_order_should_be_allocated() throws Throwable {
		Thread.sleep(30000);
		String orderStatus = orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderStatus);
		if (orderStatus.equals("Released")) {
			jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
			i_system_allocate_the_order();
		}
		String status = orderHeaderDB.getStatus(context.getOrderId());
		Assert.assertEquals("status is not as expected", "Allocated", status);
	}
	
	@When("^I system allocate the order for the sku \"([^\"]*)\"$")
	public void i_system_allocate_the_order_for_sku(String sku) throws Throwable {
		Thread.sleep(5000);
		Thread.sleep(3000);
		i_have_the_order_for_replenish_task_with_status_type_order_details_in_the_order_header_table_for_customer("Released","RDC","3942");
		Thread.sleep(20000);
	}

	@When("^I have the order for replenish task with \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header table for customer \"([^\"]*)\"")
	public void i_have_the_order_for_replenish_task_with_status_type_order_details_in_the_order_header_table_for_customer(String status,
			String type, String customer) throws Throwable {
		context.setStoType(type);
		context.setCustomer(customer);
	//	dataSetupRunner.insertOrderData();
		dataSetupRunner.insertOrderDataForReplenish();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderstatus);
		context.setOrderId(orderID);
	//	systemAllocationPage.enterOrderId(orderID);
		
	}
}
