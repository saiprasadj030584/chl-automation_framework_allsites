package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.DeleteDataFromDB;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.dataload.exit.InsertDataIntoDB;
import com.jda.wms.dataload.exit.SelectDataFromDB;
import com.jda.wms.dataload.exit.UpdateDataFromDB;
import com.jda.wms.db.Exit.AddressDB;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderMaintenanceStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private SystemAllocationPage systemAllocationPage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private Context context;
	private AddressMaintenancePage addressMaintenancePage;
	private Verification verification;
	private OrderHeaderDB orderHeaderDB;
	private AddressDB addressDB;
	private Hooks hooks;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private OrderHeaderContext orderHeaderContext;
	private OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs;
	private MoveTaskStepDefs moveTaskStepDefs;
	private SystemAllocationStepDefs systemAllocationStepDefs;
	private ClusteringStepDefs clusteringStepDefs;
	private OrderPreparationStepDefs orderPreparationStepDefs;
	private DataSetupRunner dataSetupRunner;
	private GetTCData getTCData;
	private UpdateDataFromDB updateDataFromDB;
	private JDALoginStepDefs jdaLoginStepDefs;
	private MoveTaskUpdateStepDefs moveTaskUpdateStepDefs;
//	private PuttyFunctionsStepDefs  puttyFunctionsStepDefs;
//	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;
//	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private MoveTaskDB  moveTaskDB;
	private MoveTaskUpdatePage moveTaskUpdatePage;
	private MoveTaskListGenerationPage moveTaskListGenerationPage;
	private JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;

	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage, Verification verification, OrderHeaderDB orderHeaderDB,
			AddressDB addressDB, Hooks hooks, InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, OrderHeaderContext orderHeaderContext,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs,
			SystemAllocationStepDefs systemAllocationStepDefs, ClusteringStepDefs clusteringStepDefs,
			OrderPreparationStepDefs orderPreparationStepDefs, DataSetupRunner dataSetupRunner, GetTCData getTCData,
			UpdateDataFromDB updateDataFromDB, JDALoginStepDefs jdaLoginStepDefs,MoveTaskUpdateStepDefs moveTaskUpdateStepDefs,
			  MoveTaskDB  moveTaskDB,MoveTaskUpdatePage moveTaskUpdatePage,
			MoveTaskListGenerationPage moveTaskListGenerationPage,JdaHomePage jdaHomePage,InventoryDB inventoryDB) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.moveTaskDB=moveTaskDB;
//		this.purchaseOrderReceivingStepDefs=purchaseOrderReceivingStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.addressMaintenancePage = addressMaintenancePage;
		this.verification = verification;
		this.orderHeaderDB = orderHeaderDB;
		this.addressDB = addressDB;
		this.hooks = hooks;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.orderHeaderContext = orderHeaderContext;
		this.orderLineMaintenanceStepDefs = orderLineMaintenanceStepDefs;
		this.moveTaskStepDefs = moveTaskStepDefs;
		this.systemAllocationStepDefs = systemAllocationStepDefs;
		this.clusteringStepDefs = clusteringStepDefs;
		this.orderPreparationStepDefs = orderPreparationStepDefs;
		this.dataSetupRunner = dataSetupRunner;
		this.getTCData = getTCData;
		this.updateDataFromDB = updateDataFromDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.moveTaskUpdateStepDefs=moveTaskUpdateStepDefs;
//		this.puttyFunctionsStepDefs =puttyFunctionsStepDefs;
		this.moveTaskUpdatePage=moveTaskUpdatePage;
		this.moveTaskListGenerationPage=moveTaskListGenerationPage;
		this.jdaHomePage=jdaHomePage;
		this.inventoryDB=inventoryDB;
	}
	
	@Given("^the STO should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header table for customer \"([^\"]*)\"$")
	public void the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer(String status,
			String type, String customer) throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insertOrderData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderstatus);
       jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		if(orderstatus.equals(status))
		{
			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status1"+orderstatus1);
				systemAllocationStepDefs.i_system_allocate_the_order();
			}
		
		}
		
	    clusteringStepDefs.i_create_list_ids_manually_in_clustering();
	    orderPreparationStepDefs.i_create_consignment();
	    String statusBeforePck = moveTaskDB.getStatus(context.getOrderId());
	    System.out.println("statusBeforePck=" +statusBeforePck);	    
		if (statusBeforePck.equals("Hold")) {
			jdaHomePage.navigateToMoveTaskUpdate();
			i_enter_TaskId_in_move_task_update_for_sto();
			jdaFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickReleaseButton();
			jdaFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
			Thread.sleep(3000);
			jdaFooter.clickDoneButton();
//			 moveTaskUpdateDB.releaseTagId(tag);
//			 String updatedStatus=moveTaskDB.getStatus(orderID);
//			// System.out.println("status="+updatedStatus);
			

		}
		// ------------------Data Set up Modified---------------
		
		////context.setOrderId(orderID);
//		context.setTask(task);
		//context.setOrderId("8943916823");
		//context.setStoType(type);
		ArrayList<String> failureList = new ArrayList<String>();
		//verification.verifyData("Order status", status, orderHeaderDB.getStatus(context.getOrderId()), failureList);

		verification.verifyData("Order date", "Not Null", orderHeaderDB.getOrderDate(context.getOrderId()),
				failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderDB.getCreatedBy(context.getOrderId()),
				failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderDB.getCreationDate(context.getOrderId()),
				failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderDB.getMoveTaskStatus(context.getOrderId()),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderDB.getFromSiteId(context.getOrderId()),
				failureList);
		verification.verifyData("Type", type, orderHeaderDB.getType(context.getOrderId()), failureList);
		int noOfLines = Utilities.convertStringToInteger(orderHeaderDB.getNumberOfLines(context.getOrderId()));
		verification.verifyData("Number of lines", "Not Null", String.valueOf(noOfLines), failureList);
		context.setNoOfLines(noOfLines);
		Assert.assertTrue("Order  details are not as expected in Order Header" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	
	@Given("^the STO should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header table for customer \"([^\"]*)\" for partial shipment$")
	public void the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer_for_partial_shipment(String status,
			String type, String customer) throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insert2OrderData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderstatus);
       jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		if(!orderstatus.equals(status))
		{
			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(orderstatus1.equals("Released"))
			{
				System.out.println("status1"+orderstatus1);
				systemAllocationStepDefs.i_system_allocate_the_order();
			}
		
		}
		
	    clusteringStepDefs.i_create_list_ids_manually_in_clustering();
	    orderPreparationStepDefs.i_create_consignment();
	    String statusBeforePck = moveTaskDB.getStatus(context.getOrderId());
	    System.out.println("statusBeforePck=" +statusBeforePck);	    
		if (statusBeforePck.equals("Hold")) {
			jdaHomePage.navigateToMoveTaskUpdate();
			i_enter_TaskId_in_move_task_update_for_sto();
			jdaFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickReleaseButton();
			jdaFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
			Thread.sleep(3000);
			jdaFooter.clickDoneButton();
//			 moveTaskUpdateDB.releaseTagId(tag);
//			 String updatedStatus=moveTaskDB.getStatus(orderID);
//			// System.out.println("status="+updatedStatus);
			

		}
		// ------------------Data Set up Modified---------------
		
		////context.setOrderId(orderID);
//		context.setTask(task);
		//context.setOrderId("8943916823");
		//context.setStoType(type);
		ArrayList<String> failureList = new ArrayList<String>();
		//verification.verifyData("Order status", status, orderHeaderDB.getStatus(context.getOrderId()), failureList);

		verification.verifyData("Order date", "Not Null", orderHeaderDB.getOrderDate(context.getOrderId()),
				failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderDB.getCreatedBy(context.getOrderId()),
				failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderDB.getCreationDate(context.getOrderId()),
				failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderDB.getMoveTaskStatus(context.getOrderId()),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderDB.getFromSiteId(context.getOrderId()),
				failureList);
		verification.verifyData("Type", type, orderHeaderDB.getType(context.getOrderId()), failureList);
		int noOfLines = Utilities.convertStringToInteger(orderHeaderDB.getNumberOfLines(context.getOrderId()));
		verification.verifyData("Number of lines", "Not Null", String.valueOf(noOfLines), failureList);
		context.setNoOfLines(noOfLines);
		Assert.assertTrue("Order  details are not as expected in Order Header" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	
	@Given("^the STO should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header table for customer for consolidation \"([^\"]*)\"$")
	public void the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer_for_consolidation(String status,
			String type, String customer) throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insertOrderDataForConsolidation();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderstatus);
       jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		if(!orderstatus.equals(status))
		{
			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(orderstatus1.equals("Released"))
			{
				System.out.println("status"+orderstatus1);
				systemAllocationStepDefs.i_system_allocate_the_order();
			}
		
		}
		
	    clusteringStepDefs.i_create_list_ids_manually_in_clustering();
	    orderPreparationStepDefs.i_create_consignment();
	    String statusBeforePck = moveTaskDB.getStatus(context.getOrderId());
	    System.out.println("statusBeforePck=" +statusBeforePck);	    
		if (statusBeforePck.equals("Hold")) {
			jdaHomePage.navigateToMoveTaskUpdate();
			i_enter_TaskId_in_move_task_update_for_sto();
			jdaFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickReleaseButton();
			jdaFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
			Thread.sleep(3000);
			jdaFooter.clickDoneButton();

		}

		
		ArrayList<String> failureList = new ArrayList<String>();
		//verification.verifyData("Order status", status, orderHeaderDB.getStatus(context.getOrderId()), failureList);

		verification.verifyData("Order date", "Not Null", orderHeaderDB.getOrderDate(context.getOrderId()),
				failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderDB.getCreatedBy(context.getOrderId()),
				failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderDB.getCreationDate(context.getOrderId()),
				failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderDB.getMoveTaskStatus(context.getOrderId()),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderDB.getFromSiteId(context.getOrderId()),
				failureList);
		verification.verifyData("Type", type, orderHeaderDB.getType(context.getOrderId()), failureList);
		int noOfLines = Utilities.convertStringToInteger(orderHeaderDB.getNumberOfLines(context.getOrderId()));
		verification.verifyData("Number of lines", "Not Null", String.valueOf(noOfLines), failureList);
		context.setNoOfLines(noOfLines);
		Assert.assertTrue("Order  details are not as expected in Order Header" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	public void i_enter_TaskId_in_move_task_update_for_sto() throws FindFailed, InterruptedException {
		moveTaskListGenerationPage.enterTaskIdInMoveTaskUpdate(context.getOrderId());
	}
	

	@Given("^the STO should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header table for customer \"([^\"]*)\" for container check$")
	public void the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer_for_container_check(String status,
			String type, String customer) throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insertOrderDataForContainer();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		Thread.sleep(10000);
		inventoryDB.updateUserDefType(context.getSkuId());
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderstatus);
       jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		if(orderstatus.equals(status))
		{
			Thread.sleep(8000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status: "+orderstatus1);
				systemAllocationStepDefs.i_system_allocate_the_order();
			}
		
		}
		
	    clusteringStepDefs.i_create_list_ids_manually_in_clustering();
	    orderPreparationStepDefs.i_create_consignment();
	    String statusBeforePck = moveTaskDB.getStatus(context.getOrderId());
	    System.out.println("statusBeforePck=" +statusBeforePck);	    
		if (statusBeforePck.equals("Hold")) {
			jdaHomePage.navigateToMoveTaskUpdate();
			i_enter_TaskId_in_move_task_update_for_sto();
			jdaFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickReleaseButton();
			jdaFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
			Thread.sleep(3000);
			jdaFooter.clickDoneButton();
		}
		
		ArrayList<String> failureList = new ArrayList<String>();
		//verification.verifyData("Order status", status, orderHeaderDB.getStatus(context.getOrderId()), failureList);

		verification.verifyData("Order date", "Not Null", orderHeaderDB.getOrderDate(context.getOrderId()),
				failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderDB.getCreatedBy(context.getOrderId()),
				failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderDB.getCreationDate(context.getOrderId()),
				failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderDB.getMoveTaskStatus(context.getOrderId()),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderDB.getFromSiteId(context.getOrderId()),
				failureList);
		verification.verifyData("Type", type, orderHeaderDB.getType(context.getOrderId()), failureList);
		int noOfLines = Utilities.convertStringToInteger(orderHeaderDB.getNumberOfLines(context.getOrderId()));
		verification.verifyData("Number of lines", "Not Null", String.valueOf(noOfLines), failureList);
		context.setNoOfLines(noOfLines);
		Assert.assertTrue("Order  details are not as expected in Order Header" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		
	}
	
	@Given("^the STO should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header table for customer \"([^\"]*)\" for locking location$")
	public void the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer_locking_location(String status,
			String type, String customer) throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insertOrderDataForReplenish();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status"+orderstatus);
		
		
	   
		// ------------------Data Set up Modified---------------

		context.setOrderId(orderID);
		//context.setOrderId("8943916823");
		context.setStoType(type);
		ArrayList<String> failureList = new ArrayList<String>();
		//verification.verifyData("Order status", status, orderHeaderDB.getStatus(context.getOrderId()), failureList);
		verification.verifyData("Order date", "Not Null", orderHeaderDB.getOrderDate(context.getOrderId()),
				failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderDB.getCreatedBy(context.getOrderId()),
				failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderDB.getCreationDate(context.getOrderId()),
				failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderDB.getMoveTaskStatus(context.getOrderId()),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderDB.getFromSiteId(context.getOrderId()),
				failureList);
		verification.verifyData("Type", type, orderHeaderDB.getType(context.getOrderId()), failureList);
		int noOfLines = Utilities.convertStringToInteger(orderHeaderDB.getNumberOfLines(context.getOrderId()));
		verification.verifyData("Number of lines", "Not Null", String.valueOf(noOfLines), failureList);
		context.setNoOfLines(noOfLines);
		Assert.assertTrue("Order  details are not as expected in Order Header" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	

	@Given("^the order should have delivery details$")
	public void the_order_should_have_delivery_details() throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();

		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		verification.verifyData("Customer", "Not Null", customer, failureList);
		verification.verifyData("Name", "Not Null", orderHeaderDB.getName(context.getOrderId()), failureList);
		verification.verifyData("Address1", "Not Null", orderHeaderDB.getAddress1(context.getOrderId()), failureList);
		verification.verifyData("Country", "Not Null", orderHeaderDB.getCountry(context.getOrderId()), failureList);

		/*
		 * verification.verifyData("Ship By Date - Delivery Details", "Not Null"
		 * , orderHeaderDB.getShipByDate(context.getOrderId()), failureList);
		 * verification.verifyData("Deliver By Date - Delivery Details",
		 * "Not Null", orderHeaderDB.getDeliverByDate(context.getOrderId()),
		 * failureList);
		 * 
		 * verification.verifyData("Deliver By Date - Delivery Details",
		 * "Not Null", orderHeaderDB.getDeliverByDate(context.getOrderId()),
		 * failureList);
		 * 
		 * verification.verifyData("Deliver By Date - UserDefined", "Not Null",
		 * orderHeaderDB.getDeliveryByDateUserDefinedTab(context.getOrderId()),
		 * failureList); verification.verifyData("Delivery Type", "Not Null",
		 * orderHeaderDB.getDeliveryType(context.getOrderId()), failureList);
		 * verification.verifyData("IFOS OrderNum", "Not Null",
		 * orderHeaderDB.getIfosOrderNum(context.getOrderId()), failureList);
		 */

		Assert.assertTrue(
				"Delivery details are not as expected in Order Header" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the bulk pick order \"([^\"]*)\" should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header maintenance table$")
	public void the_bulk_pick_order_should_be_status_type_order_details_in_the_order_header_maintenance_table(
			String orderID, String status, String type) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		context.setOrderId(orderID);
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		verification.verifyData("Bulk pick order status", status, orderHeaderMaintenancePage.getStatus(), failureList);
		verification.verifyData("Order date", "Not Null", orderHeaderMaintenancePage.getOrderDate(), failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderMaintenancePage.getCreatedBy(), failureList);
		verification.verifyData("Order time", "Not Null", orderHeaderMaintenancePage.getOrderTime(), failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderMaintenancePage.getCreationDate(), failureList);
		verification.verifyData("Creation time", "Not Null", orderHeaderMaintenancePage.getCreationTime(), failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderMaintenancePage.getMoveTaskStatus(),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderMaintenancePage.getFromSiteId(), failureList);
		verification.verifyData("Type", "Not Null", orderHeaderMaintenancePage.getType(), failureList);
		verification.verifyData("Number of lines", "Not Null", orderHeaderMaintenancePage.getNumberOfLines(),
				failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have delivery address details in delivery address tab$")
	public void the_order_should_have_delivery_address_details_in_delivery_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryAddressTab();

		String customer = orderHeaderMaintenancePage.getCustomer();
		context.setCustomer(customer);
		verification.verifyData("Customer", "Not Null", customer, failureList);
		verification.verifyData("Name", "Not Null", orderHeaderMaintenancePage.getName(), failureList);
		verification.verifyData("Address1", "Not Null", orderHeaderMaintenancePage.getAddress1(), failureList);
		verification.verifyData("Country", "Not Null", orderHeaderMaintenancePage.getCountry(), failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have delivery details in delivery details and user defined tabs$")
	public void the_order_should_have_delivery_details_in_delivery_details_and_user_defined_tabs() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryDetailsTab();
		verification.verifyData("ShipByDate", "Not Null", orderHeaderMaintenancePage.getShipByDate(), failureList);
		verification.verifyData("DeliveryByTimeFromDeliveryDetailsTab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByTimeFromDeliveryDetailsTab(), failureList);
		verification.verifyData("DeliveryByDateFromDeliveryDetailsTab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByDateFromDeliveryDetailsTab(), failureList);
		verification.verifyData("ShipByTime", "Not Null", orderHeaderMaintenancePage.getShipByTime(), failureList);

		orderHeaderMaintenancePage.clickUserDefinedTab();
		verification.verifyData("DeliveryType", "Not Null", orderHeaderMaintenancePage.getDeliveryType(), failureList);
		verification.verifyData("DeliveryByTimeFromUserDefinedTab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByTimeFromUserDefinedTab(), failureList);
		verification.verifyData("DeliveryByDateFromUserDefinedtab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByDateFromUserDefinedtab(), failureList);
		verification.verifyData("IfosOrderNum", "Not Null", orderHeaderMaintenancePage.getIfosOrderNum(), failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the customer should have CSSM flag updated in address maintenance table$")
	public void the_customer_should_have_CSSM_flag_updated_in_address_maintenance_table() throws Throwable {

		jdaHomeStepDefs.i_navigate_to_address_maintenance_page();
		jdaFooter.clickQueryButton();
		addressMaintenancePage.enterAddressID(context.getCustomer());
		jdaFooter.clickExecuteButton();

		addressMaintenancePage.clickUserDefinedTab();
		Assert.assertTrue("The address Id is not a STR. Expected [checkbox selected] but was [not selected]",
				addressMaintenancePage.isCSSMChecked());
	}

	@Given("^the customer should have CSSM flag updated in address table$")
	public void the_customer_should_have_CSSM_flag_updated_in_address_table() throws Throwable {
		String cssmCheckedval = addressDB.CSSMCheckedValue(context.getCustomer());
		Assert.assertEquals("The address Id is not a STR. Expected [ CSSM checkbox selected] but was [not selected]",
				"Y", cssmCheckedval);
	}

	@Then("^the summary records page should be displayed$")
	public void the_summary_records_should_be_displayed() throws Throwable {
		/*Assert.assertTrue("Order Managements Summary records page not displayed as expected.",
				orderHeaderMaintenancePage.isSummaryRecordsDisplayed());
		orderHeaderMaintenancePage.clickSummaryRecord();*/
		
	}

	@Given("^the order should have hub details in hub address tab$")
	public void the_order_should_have_hub_details_in_hub_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickHubAddressTab();
		verification.verifyData("Hub", "Not Null", orderHeaderMaintenancePage.getHub(), failureList);
		verification.verifyData("HubName", "Not Null", orderHeaderMaintenancePage.getHubName(), failureList);
		verification.verifyData("HubCountry", "Not Null", orderHeaderMaintenancePage.getHubCountry(), failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have hub details$")
	public void the_order_should_have_hub_details() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData("Hub", "Not Null", orderHeaderDB.getHub(context.getOrderId()), failureList);
		verification.verifyData("Hub Name", "Not Null", orderHeaderDB.getHubName(context.getOrderId()), failureList);
		verification.verifyData("Hub Country", "Not Null", orderHeaderDB.getHubCountry(context.getOrderId()),
				failureList);

		Assert.assertTrue("Hub details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should be \"([^\"]*)\" status$")
	public void the_order_should_be_status(String orderStatus) throws Throwable {
		String actualOrderStatus = orderHeaderDB.getStatus(context.getOrderId());
		context.setOrderStatus(actualOrderStatus);
		logger.debug("Order ID : " + context.getOrderId());
		logger.debug("Order status from DB : " + actualOrderStatus);

		Assert.assertEquals("Order Status is not displayed as expected ", orderStatus, actualOrderStatus);
	}

	@Then("^the vehicle loading should be completed and the status should be \"([^\"]*)\" in order header$")
	public void the_vehicle_loading_should_be_completed_and_the__status_should_be_in_order_header(String orderStatus)
			throws Throwable {
		hooks.logoutPutty();
		Assert.assertEquals("Order Status is not displayed as expected ", orderStatus,
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@When("^the order id should have ship dock and consignment$")
	public void the_order_id_should_have_ship_dock_and_consignment() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String shipdock = orderHeaderDB.getShipdock(context.getOrderId());
		String consignment = orderHeaderDB.getConsignment(context.getOrderId());
		context.setSTOConsignment(consignment);
		if ((shipdock == null || shipdock.equals("DEFAULTSD")) || consignment == null) {
			HashMap<String, String> orderGroupDetails = orderHeaderDB.getGroupDetails(context.getOrderId());
			verification.verifyData("Work Group", "Not Null", orderGroupDetails.get("WORKGROUP"), failureList);
			verification.verifyData("Group ID", "Not Null", orderGroupDetails.get("ORDERGROUPINGID"), failureList);
			verification.verifyData("Consignment Group ID", "Not Null", orderGroupDetails.get("CONSIGNMENTGROUPINGID"),
					failureList);
		}
		Assert.assertTrue(
				"Shipdock and consignment detailes are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^I enter the Order id in order header\"([^\"]*)\"$")
	public void i_enter_the_Order_id_in_order_header(String orderID) throws Throwable {
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the ship dock should be updated for an order$")
	public void the_ship_dock_should_be_updated_for_an_order() throws Throwable {
		Assert.assertEquals("Ship Dock is not displayed as expected", context.getNewShipDock(),
				orderHeaderDB.getShipdock(context.getOrderId()));
	}

	@Then("^the order status should be \"([^\"]*)\" in order header$")
	public void the_order_status_should_be_in_order_header(String orderStatus) throws Throwable {
		Assert.assertEquals("Order Status is not displayed as expected ", orderStatus,
				orderHeaderDB.getStatus(context.getOrderId()));
	}
//Modified by Bhuban
	@Given("^the order of type \"([^\"]*)\" should be \"([^\"]*)\" status for customer \"([^\"]*)\"$")
	public void the_order_should_be_status_for_customer(String type, String orderStatus, String customer)
			throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insertOrderData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID " + orderID);
		context.setTaskId(orderID);
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		systemAllocationStepDefs.i_system_allocate_the_order();
		clusteringStepDefs.i_create_list_ids_manually_in_clustering();
		/*jdaHomeStepDefs.i_navigate_to_move_task_update();
		moveTaskUpdateStepDefs.the_task_id_should_be_released(orderID);*/
		orderPreparationStepDefs.i_create_consignment();
		// ------------------Data Set up Modified---------------

		// // ------------Data Setup-----------
		// deleteDataFromDB.deleteOrderHeader(orderID);
		// insertDataIntoDB.insertOrderHeader(orderID, "STR", customer);
		// insertDataIntoDB.insertOrderLine(orderID);
		// Assert.assertTrue("Test Data not available - Issue in Data loading",
		// selectDataFromDB.isPreAdviceRecordExists(orderID));
		// systemAllocationStepDefs.i_system_allocate_the_order();
		// clusteringStepDefs.i_create_list_ids_manually_in_clustering();
		// orderPreparationStepDefs.i_create_consignment();
		//
		// // ------------Data Setup-----------

		String actualOrderStatus = orderHeaderDB.getStatus(orderID);
		context.setOrderId(orderID);
		context.setOrderStatus(actualOrderStatus);
		logger.debug("Order ID : " + orderID);
		logger.debug("Order status from DB : " + actualOrderStatus);

		//Assert.assertEquals("Order Status is not displayed as expected ", orderStatus, actualOrderStatus);
	}
	
	@Then("^I navigate to system Allocation page and enter order \"([^\"]*)\" $")
	public void i_navigate_to_system_allocation_page_and_enter_order(String OrderId) throws Throwable{
		
	}

	@Then("^the consignment should be generated in the order header maintenance$")
	public void the_consignment_should_be_generated_in_the_order_header_maintenance() throws Throwable {
		logger.debug("Consignment: " + orderHeaderDB.getConsignment(context.getOrderId()));
		Assert.assertNotNull("consignment is not displayed as expected",
				orderHeaderDB.getConsignment(context.getOrderId()));
	}

	@Given("^the order should be in \"([^\"]*)\" status$")
	public void the_order_should_be_in_status(String status) throws Throwable {
		String orderStatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("Status after System Allocation:  "+orderStatus);
		

	Assert.assertEquals("status is not as expected", status, orderStatus);
	}

	@Given("^the multiple order ids should be \"([^\"]*)\" status for customer \"([^\"]*)\"$")
	public void the_multiple_order_should_be_in_ready_to_load_status_for_customer(String status, String customer)
			throws Throwable {
		// orderHeaderContext.setOrderIDDataTable(dataTable);

		Map<String, Map<Integer, Map<String, String>>> multipleOrderListIDMap = new HashMap<String, Map<Integer, Map<String, String>>>();
		// for (Map<String, String> dataRow : dataTable.asMaps(String.class,
		// String.class)) {
		for (int i = 0; i < 2; i++) {
			// String orderIds = dataRow.get("OrderID");
			// context.setOrderId(orderIds);

			the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer(status, "RDC", customer);
			the_order_should_have_delivery_details();
			the_customer_should_have_CSSM_flag_updated_in_address_table();
			orderLineMaintenanceStepDefs
					.the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items_from_order_line_table();
			the_order_should_be_in_status("Allocated");
			orderLineMaintenanceStepDefs.the_quantity_tasked_should_be_updated_for_each_order_lines();
			the_order_id_should_have_ship_dock_and_consignment();
			moveTaskStepDefs
					.the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table();
			multipleOrderListIDMap.put(context.getOrderId(), context.getListIDMap());

		}
		context.setMultipleOrderListIDMap(multipleOrderListIDMap);
	}

	@Then("^all the orders should be in \"([^\"]*)\" status in order header$")
	public void all_the_orders_should_be_in_status_in_order_header(String orderStatus) throws Throwable {
		DataTable orderIDDatatable = orderHeaderContext.getOrderIDDataTable();
		for (Map<String, String> dataRow : orderIDDatatable.asMaps(String.class, String.class)) {
			String orderIds = dataRow.get("OrderID");
			Assert.assertEquals("Order status does not match", orderStatus, orderHeaderDB.getStatus(orderIds));
		}
	}

	@Then("^the given order ids should be displayed as \"([^\"]*)\" status$")
	public void the_given_order_ids_should_be_dispalyed_as_status(String status) throws Throwable {
		DataTable orderIDDatatable = orderHeaderContext.getOrderIDDataTable();
		for (Map<String, String> dataRow : orderIDDatatable.asMaps(String.class, String.class)) {
			String orderIds = dataRow.get("OrderID");
			String orderStatus = orderHeaderDB.getStatus(orderIds);
			Assert.assertEquals("Status is not as expected for Order : " + orderIds + "", status, orderStatus);
		}
	}
	@Given("^I have the Location and Container for the task id generated$")
	public void i_have_the_Location_and_Container_for_the_task_id_generated() throws Throwable {
		the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer("Released","RDC","3942");
		the_order_should_have_delivery_details();
		orderLineMaintenanceStepDefs.the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items_from_order_line_table();
		the_order_should_be_in_status("Allocated");
		orderLineMaintenanceStepDefs.the_quantity_tasked_should_be_updated_for_each_order_lines();
		the_order_id_should_have_ship_dock_and_consignment();
		moveTaskStepDefs.the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table();
//		puttyFunctionsStepDefs.i_login_as_warehouse_user_in_putty();
//		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
//		storeTrackingOrderPickingStepDefs.i_select_picking_with_container_pick();
//		storeTrackingOrderPickingStepDefs.i_should_be_directed_to_pick_entry_page();
//		storeTrackingOrderPickingStepDefs.i_pick_all_the_list_ids_for_the_store_tracking_order();
	}
	
	
}
