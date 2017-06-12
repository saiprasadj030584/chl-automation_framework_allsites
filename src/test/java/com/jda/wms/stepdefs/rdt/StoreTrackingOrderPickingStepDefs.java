package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.dataload.foods.DeleteDataFromDB;
import com.jda.wms.dataload.foods.InsertDataIntoDB;
import com.jda.wms.dataload.foods.SelectDataFromDB;
import com.jda.wms.db.InventoryDB;
import com.jda.wms.db.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.pages.rdt.StoreTrackingOrderPickingPage;
import com.jda.wms.stepdefs.foods.ClusteringStepDefs;
import com.jda.wms.stepdefs.foods.DockSchedulerStepDefs;
import com.jda.wms.stepdefs.foods.InventoryUpdateStepDefs;
import com.jda.wms.stepdefs.foods.JDAHomeStepDefs;
import com.jda.wms.stepdefs.foods.MoveTaskStepDefs;
import com.jda.wms.stepdefs.foods.OrderHeaderMaintenanceStepDefs;
import com.jda.wms.stepdefs.foods.OrderLineMaintenanceStepDefs;
import com.jda.wms.stepdefs.foods.OrderPreparationStepDefs;
import com.jda.wms.stepdefs.foods.SystemAllocationStepDefs;
import com.jda.wms.stepdefs.foods.TrailerMaintenanceStepDefs;
import com.jda.wms.utils.DateUtils;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class StoreTrackingOrderPickingStepDefs {

	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private InventoryDB inventoryDB;
	private PuttyFunctionsPage puttyFunctionsPage;
	private Map<Integer, Map<String, String>> listIDMap;
	private Map<Integer, Map<String, String>> stockTransferOrderMap;
	private OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepDefs;
	private OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs;
	private Context context;
	private LocationDB locationDB;
	private Hooks hooks;
	private OrderHeaderContext orderHeaderContext;
	private MoveTaskStepDefs moveTaskStepDefs;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private TrailerMaintenanceStepDefs trailerMaintenanceStepDefs;
	private DockSchedulerStepDefs dockSchedulerStepDefs;
	private InventoryUpdateStepDefs inventoryUpdateStepDefs;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jDAFooter;
	private SystemAllocationStepDefs systemAllocationStepDefs;
	private ClusteringStepDefs clusteringStepDefs;
	private OrderPreparationStepDefs orderPreparationStepDefs;
	private Map<String, Map<Integer, Map<String, String>>> multipleOrderListIDMap;

	@Inject
	public StoreTrackingOrderPickingStepDefs(StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			Context context, InventoryDB inventoryDB, PuttyFunctionsPage puttyFunctionsPage, LocationDB locationDB,
			Hooks hooks, OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepDefs,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs, MoveTaskStepDefs moveTaskStepDefs,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs,
			TrailerMaintenanceStepDefs trailerMaintenanceStepDefs, DockSchedulerStepDefs dockSchedulerStepDefs,
			InventoryUpdateStepDefs inventoryUpdateStepDefs,InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB,JDAHomeStepDefs jdaHomeStepDefs,JDAFooter jDAFooter,SystemAllocationStepDefs systemAllocationStepDefs,ClusteringStepDefs clusteringStepDefs,OrderPreparationStepDefs orderPreparationStepDefs) {
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.orderHeaderMaintenanceStepDefs = orderHeaderMaintenanceStepDefs;
		this.orderLineMaintenanceStepDefs = orderLineMaintenanceStepDefs;
		this.moveTaskStepDefs = moveTaskStepDefs;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.trailerMaintenanceStepDefs = trailerMaintenanceStepDefs;
		this.dockSchedulerStepDefs = dockSchedulerStepDefs;
		this.inventoryUpdateStepDefs = inventoryUpdateStepDefs;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.jdaHomeStepDefs =jdaHomeStepDefs;
		this.jDAFooter= jDAFooter;
		this.systemAllocationStepDefs = systemAllocationStepDefs;
		this.clusteringStepDefs = clusteringStepDefs;
		this.orderPreparationStepDefs =orderPreparationStepDefs;
	}

	@Given("^I select picking with container pick$")
	public void i_select_picking_with_container_pick() throws Throwable {
		storeTrackingOrderPickingPage.selectPickingMenu();
		Assert.assertTrue("Picking Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickMenuDisplayed());

		storeTrackingOrderPickingPage.selectPickingInPickMenu();
		Assert.assertTrue("Pick Task Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickTaskMenuDisplayed());

		storeTrackingOrderPickingPage.selectContainerPickMenu();
	}

	@Given("^I select picking with replenish pick$")
	public void i_select_picking_with_replenish_pick() throws Throwable {
		storeTrackingOrderPickingPage.selectPickingMenu();
		Assert.assertTrue("Picking Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickMenuDisplayed());

		storeTrackingOrderPickingPage.selectPickingInPickMenu();
		Assert.assertTrue("Pick Task Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickTaskMenuDisplayed());

		storeTrackingOrderPickingPage.selectReplenishPickMenu();
	}

	@Then("^I should be directed to pick entry page$")
	public void i_should_be_directed_to_pick_entry_page() throws Throwable {
		Assert.assertTrue("Pick entry not displayed as expected.",
				storeTrackingOrderPickingPage.isPickEntryDisplayed());
	}

	@When("^I pick all the list ids for the store tracking order$")
	public void i_pick_all_the_list_ids_for_the_store_tracking_order() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		listIDMap = context.getListIDMap();
		stockTransferOrderMap = context.getStockTransferOrderMap();

		for (int i = 1; i <= context.getListIDMap().size(); i++) {

			context.setListID(listIDMap.get(i).get("ListID"));
			i_enter_task_id_and_list_id();
			the_list_id_should_be_displayed();
			puttyFunctionsPage.pressEnter();

			String skuId = storeTrackingOrderPickingPage.getSkuId();
			Assert.assertNotNull("SKU ID is not displayed as expected", skuId);
			context.setSkuId(skuId);

			for (int j = 1; j <= context.getListIDMap().size(); j++) {
				if (listIDMap.get(j).get("SkuId").equals(context.getSkuId())) {
					context.setLocation(listIDMap.get(j).get("Location"));
					context.setToPallet((listIDMap.get(j).get("ToPalletID")));
					context.setToLocation((listIDMap.get(j).get("ToLocation")));
					context.setFinalLocation((listIDMap.get(j).get("FinalLocation")));
					context.setQtyToMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
					context.setContainerId(listIDMap.get(j).get("ToContainerID"));
				}
			}
			the_location_should_be_displayed();
			i_enter_SKU_id_quantity_and_stock_details();

			listIDMap.get(i).replace("TagID", context.getTagId());
			context.setListIDMap(listIDMap);

			the_to_pallet_to_location_and_destination_should_be_displayed();
			Assert.assertTrue("Container ID entry page is not displayed as expected",
					storeTrackingOrderPickingPage.isContainerIDDisplayed());
			i_enter_container_id_and_check_strings();

			if (!storeTrackingOrderPickingPage.isPickEntryDisplayed()) {
				failureList.add("Picking not completed and Home page not displayed for List ID " + context.getListID());
				context.setFailureList(failureList);
			}
			context.setPickedRecords(context.getPickedRecords() + 1);
			// System.out.println("Record picked "+context.getPickedRecords());
		}
		puttyFunctionsPage.minimisePutty();
		// System.out.println("Total picked "+context.getPickedRecords());
	}

	@When("^the list id should be displayed$")
	public void the_list_id_should_be_displayed() throws Throwable {
		String listId = storeTrackingOrderPickingPage.getListIDDisplayed();
		Assert.assertEquals("List ID in trolley Pick info is not displayed as expected.", context.getListID(), listId);
	}

	@When("^I enter container id and check strings$")
	public void i_enter_container_id_and_check_strings() throws Throwable {
		storeTrackingOrderPickingPage.enterContainerID(context.getContainerId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();

		String chkStrings = locationDB.getCheckString(context.getToLocation());
		storeTrackingOrderPickingPage.enterCheckStrings(chkStrings);
		puttyFunctionsPage.pressEnter();
	}

	@When("^I enter task id and list id$")
	public void i_enter_task_id_and_list_id() throws Throwable {
		storeTrackingOrderPickingPage.enterTaskID(context.getOrderId());
		storeTrackingOrderPickingPage.enterListID(context.getListID());
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the location should be displayed$")
	public void the_location_should_be_displayed() throws Throwable {
		String location = storeTrackingOrderPickingPage.getLocation();
		Assert.assertEquals("Location is not displayed as expected.", context.getLocation(), location);
	}

	@Given("^the STO \"([^\"]*)\" of \"([^\"]*)\" type should contain order details and be \"([^\"]*)\" container picked for customer \"([^\"]*)\"$")
	public void the_sto_of_type_should_contain_order_details_and_be_container_picked_for_customer(String orderId,
			String type, String pickingType, String customer) throws Throwable {
		// ------------Data Setup-----------
		deleteDataFromDB.deleteOrderHeader(orderId);
		insertDataIntoDB.insertOrderHeader(orderId,type,customer);
		insertDataIntoDB.insertOrderLine(orderId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(orderId));
		i_system_allocate_the_order();
		i_create_list_ids_manually_in_clustering();
		i_create_consignment();
		
		// ------------Data Setup-----------

		context.setOrderId(orderId);
		context.setStoType(type);
		orderHeaderMaintenanceStepDefs.the_sto_should_be_status_type_order_details_in_the_order_header_table(orderId,
				"Allocated", type);
		orderHeaderMaintenanceStepDefs.the_order_should_have_delivery_details();
		if (context.getStoType().equals("STR")) {
			orderHeaderMaintenanceStepDefs.the_customer_should_have_CSSM_flag_updated_in_address_table();
		}
		if (context.getStoType().equals("INTSEA")) {
			orderHeaderMaintenanceStepDefs.the_order_should_have_hub_details();
		}
		orderLineMaintenanceStepDefs
				.the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items_from_order_line_table();
		orderHeaderMaintenanceStepDefs.the_order_should_be_in_status("Allocated");
		orderLineMaintenanceStepDefs.the_quantity_tasked_should_be_updated_for_each_order_lines();
		orderHeaderMaintenanceStepDefs.the_order_id_should_have_ship_dock_and_consignment();
		moveTaskStepDefs
				.the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table();
		the_sto_should_be_container_picked(pickingType);
	}

	@When("^I create consignment$")
	public void i_create_consignment() throws Throwable {
		// TODO Auto-generated method stub
		jdaHomeStepDefs.i_navigate_to_order_preparation_page();
		orderPreparationStepDefs.i_select_the_group_type_as("Consignment");
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		orderPreparationStepDefs.i_enter_the_order_id(context.getOrderId());
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		orderPreparationStepDefs.i_select_the_record();
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		orderPreparationStepDefs.the_total_orders_should_be_displayed_as("1");
		orderPreparationStepDefs.i_select_the_trailer_type_as_("TRAILER");
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		orderPreparationStepDefs.the_record_should_be_displayed_for_consignment_preparation_process();
		orderPreparationStepDefs.i_proceed_to_complete_the_process();
		orderHeaderMaintenanceStepDefs.the_consignment_should_be_generated_in_the_order_header_maintenance();
	}

	@When("^I create list ids manually in clustering$")
	public void i_create_list_ids_manually_in_clustering() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
		clusteringStepDefs.i_enter_the_site_id_and_group_id("9771",context.getStoType());
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		clusteringStepDefs.the_record_should_be_displayed_in_clustering();
	}
	

	@When("^I enter SKU id, quantity and stock details$")
	public void i_enter_SKU_id_quantity_and_stock_details() throws Throwable {
		int caseRatio = 0, qtyToPick = 0;
		storeTrackingOrderPickingPage.enterSkuId(context.getSkuId());
		puttyFunctionsPage.nextScreen();

		String quantity = storeTrackingOrderPickingPage.getQuantity();
		stockTransferOrderMap = context.getStockTransferOrderMap();
		for (int s = 1; s <= stockTransferOrderMap.size(); s++) {
			if (context.getSkuId().equals(stockTransferOrderMap.get(s).get("SKU"))) {
				caseRatio = Integer.parseInt(stockTransferOrderMap.get(s).get("CaseRatio"));
				break;
			}
		}

		qtyToPick = context.getQtyToMove() / caseRatio;
		Assert.assertEquals("Quantity to pick is not displayed as expected.", String.valueOf(qtyToPick) + "C",
				quantity);

		puttyFunctionsPage.nextScreen();
		Thread.sleep(1000);

		String tagId = storeTrackingOrderPickingPage.getTagId();
		context.setTagId(tagId);

		puttyFunctionsPage.nextScreen();
		puttyFunctionsPage.pressEnter();

		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();

		storeTrackingOrderPickingPage.enterQuantity(String.valueOf(qtyToPick) + "C");

		stockTransferOrderMap = context.getStockTransferOrderMap();
		String allocationGroup = null;
		for (int s = 1; s <= stockTransferOrderMap.size(); s++) {
			if (context.getSkuId().equals(stockTransferOrderMap.get(s).get("SKU"))) {
				allocationGroup = stockTransferOrderMap.get(s).get("AllocationGroup");
				break;
			}
		}

		if (allocationGroup.equalsIgnoreCase("Expiry")) {
			puttyFunctionsPage.pressTab();
			String manufactureDate = DateUtils.getPrevSystemYear();
			storeTrackingOrderPickingPage.enterManufactureDate(manufactureDate);
			// TODO Convert date from DB
			String expDate = inventoryDB.getExpDate(context.getSkuId(), context.getTagId(), context.getLocation());
			storeTrackingOrderPickingPage.enterExpiryDate(expDate);
		}

		puttyFunctionsPage.nextScreen();
		Thread.sleep(1000);
		puttyFunctionsPage.nextScreen();
	}

	@Then("^the to pallet, to location and destination should be displayed$")
	public void the_to_pallet_to_location_and_destination_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String destination = storeTrackingOrderPickingPage.getDestination();
		String[] dest = destination.split("_");
		destination = dest[0];

		if (!context.getFinalLocation().equals(destination)) {
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getFinalLocation()
					+ "] but was [" + destination + "]");
		}

		String toPallet = storeTrackingOrderPickingPage.getToPallet();
		if (!context.getToPallet().equals(toPallet)) {
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToPallet() + "] but was ["
					+ toPallet + "]");
		}

		String toLocation = storeTrackingOrderPickingPage.getToLocation();
		if (!context.getToLocation().equals(toLocation)) {
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToLocation()
					+ "] but was [" + toLocation + "]");
		}

		storeTrackingOrderPickingPage.enterDestination(destination);

		puttyFunctionsPage.pressEnter();

		Assert.assertTrue(
				"Picking - List Id, SKU, Location are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^I should see the picking completion$")
	public void i_should_see_the_picking_completion() throws Throwable {
		Assert.assertTrue(
				"Picking not completed and Home page not displayed.["
						+ Arrays.asList(orderHeaderContext.getFailureList().toArray()) + "].",
				orderHeaderContext.getFailureList().isEmpty());
	}
	
	@When("^I system allocate the order$")
	public void i_system_allocate_the_order() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_system_allocation_page();
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		systemAllocationStepDefs.i_have_order_id_with_soft_allocated_status(context.getOrderId());
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		systemAllocationStepDefs.the_record_should_be_displayed_in_system_allocation();
		systemAllocationStepDefs.i_proceed_to_allocate_the_record();
	}

	@When("^I pick all the list ids for the replenish task$")
	public void i_pick_all_the_list_ids_for_the_replenish_task() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		listIDMap = context.getListIDMap();

		for (int i = 1; i <= context.getListIDMap().size(); i++) {
			context.setListID(listIDMap.get(i).get("ListID"));

			i_enter_task_id_and_list_id();

			for (int j = 1; j <= context.getListIDMap().size(); j++) {
				if (listIDMap.get(j).get("ListID").equals(context.getListID())) {
					context.setLocation(listIDMap.get(j).get("Location"));
					context.setTagId(listIDMap.get(j).get("TagID"));
					context.setToLocation((listIDMap.get(j).get("ToLocation")));
					context.setFinalLocation((listIDMap.get(j).get("FinalLocation")));
					context.setQtyToMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
				}
			}
			Assert.assertEquals("Location is not displayed as expected.", context.getLocation(),
					storeTrackingOrderPickingPage.getLocationInReplenishPick());
			puttyFunctionsPage.nextScreen();

			Assert.assertEquals("SKU ID is not displayed as expected", context.getSkuId(),
					storeTrackingOrderPickingPage.getSkuId());
			the_quantity_and_to_and_destination_location_should_be_displayed();

			String chkStrings = locationDB.getCheckString(context.getToLocation());
			storeTrackingOrderPickingPage.enterCheckStrings(chkStrings);
			puttyFunctionsPage.pressEnter();

			if (!storeTrackingOrderPickingPage.isPickEntryDisplayed()) {
				failureList.add("Picking not completed and home page not displayed for List ID " + context.getListID());
				orderHeaderContext.setFailureList(failureList);
			}
		}
	}

	@When("^the quantity and to and destination location should be dispalyed$")
	public void the_quantity_and_to_and_destination_location_should_be_displayed() throws Throwable {
		int qtyToMove = 0;
		listIDMap = context.getListIDMap();

		String quantity = storeTrackingOrderPickingPage.getReplenishQuantity();
		String[] quantitySplit = quantity.split("_");
		quantity = quantitySplit[0];

		for (int s = 1; s <= listIDMap.size(); s++) {
			if (context.getListID().equals(listIDMap.get(s).get("ListID"))) {
				qtyToMove = Integer.parseInt(listIDMap.get(s).get("QtyToMove"));
				context.setQtyToMove(qtyToMove);
				break;
			}
		}

		qtyToMove = qtyToMove / context.getCaseRatio();
		Assert.assertEquals("Quantity to pick is not displayed as expected.", String.valueOf(qtyToMove) + "C",
				quantity);

		String toLocation = storeTrackingOrderPickingPage.getToLocationInReplenishPick();
		String[] location = toLocation.split("_");
		toLocation = location[0];
		Assert.assertEquals("To Location is not displayed as expected.", context.getToLocation(), toLocation);

		Assert.assertEquals("Destination is not displayed as expected.", context.getFinalLocation(),
				storeTrackingOrderPickingPage.getDestinationInReplenishpick());

		puttyFunctionsPage.pressEnter();
		storeTrackingOrderPickingPage.enterTagId(context.getTagId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the STO should be container picked$")
	public void the_sto_should_be_container_picked(String pickingType) throws Throwable {
		puttyFunctionsStepDefs.i_login_as_warehouse_user_in_putty();
		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_picking_with_container_pick();
		i_should_be_directed_to_pick_entry_page();
		if (pickingType.equals("partially")) {
			i_partially_pick_the_store_tracking_order();
		} else if (pickingType.equals("completely")) {
			i_pick_all_the_list_ids_for_the_store_tracking_order();
		}
		i_should_see_the_picking_completion();
	}

	@Then("^i partially pick the store tracking order$")
	public void i_partially_pick_the_store_tracking_order() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		listIDMap = context.getListIDMap();
		stockTransferOrderMap = context.getStockTransferOrderMap();

		context.setListID(listIDMap.get(1).get("ListID"));
		i_enter_task_id_and_list_id();
		the_list_id_should_be_displayed();
		puttyFunctionsPage.pressEnter();

		String skuId = storeTrackingOrderPickingPage.getSkuId();
		Assert.assertNotNull("SKU ID is not displayed as expected", skuId);
		context.setSkuId(skuId);

		for (int j = 1; j <= context.getListIDMap().size(); j++) {
			if (listIDMap.get(j).get("SkuId").equals(context.getSkuId())) {
				context.setLocation(listIDMap.get(j).get("Location"));
				context.setToPallet((listIDMap.get(j).get("ToPalletID")));
				context.setToLocation((listIDMap.get(j).get("ToLocation")));
				context.setFinalLocation((listIDMap.get(j).get("FinalLocation")));
				context.setQtyToMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
				context.setContainerId(listIDMap.get(j).get("ToContainerID"));
			}
		}
		the_location_should_be_displayed();
		i_enter_SKU_id_quantity_and_stock_details();

		listIDMap.get(1).replace("TagID", context.getTagId());
		context.setListIDMap(listIDMap);

		the_to_pallet_to_location_and_destination_should_be_displayed();
		Assert.assertTrue("Container ID entry page is not displayed as expected",
				storeTrackingOrderPickingPage.isContainerIDDisplayed());
		i_enter_container_id_and_check_strings();
		Assert.assertTrue("Picking not completed and Home page not displayed for List ID " + context.getListID(),
				storeTrackingOrderPickingPage.isPickEntryDisplayed());
	}

	@Then("^the trailer and dock scheduling should be done$")
	public void the_trailer_and_dock_scheduling_should_be_done() throws Throwable {
		trailerMaintenanceStepDefs.i_create_a_trailer_in_trailer_Maintenance_page();
		trailerMaintenanceStepDefs.the_trailer_should_be_created();
		dockSchedulerStepDefs.i_create_new_dock_booking();
		dockSchedulerStepDefs.i_select_booking_type_and_consignment();
		dockSchedulerStepDefs.i_select_the_slot();
		dockSchedulerStepDefs.i_enter_booking_details();
		inventoryUpdateStepDefs.i_proceed_to_complete_the_process();
		dockSchedulerStepDefs.the_booking_details_should_be_appeared_in_the_dock_scheduler_booking();
	}
	
	@When("^I pick all the list ids for multiple store tracking order$")
	public void i_pick_all_the_list_ids_for_multiple_store_tracking_order(String orderID) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		multipleOrderListIDMap = context.getMultipleOrderListIDMap();
		stockTransferOrderMap = context.getStockTransferOrderMap(); 
		
		context.setListIDMap(multipleOrderListIDMap.get(orderID));
		listIDMap = context.getListIDMap();
		for (int i = 1; i <= listIDMap.size(); i++) {
			context.setListID(listIDMap.get(i).get("ListID"));
			i_enter_task_and_list_id(orderID,listIDMap.get(i).get("ListID"));
			the_list_id_should_be_displayed();
			puttyFunctionsPage.pressEnter();

			String skuId = storeTrackingOrderPickingPage.getSkuId();
			Assert.assertNotNull("SKU ID is not displayed as expected", skuId);
			context.setSkuId(skuId);

			for (int j = 1; j <= context.getListIDMap().size(); j++) {
				if (listIDMap.get(j).get("SkuId").equals(context.getSkuId())) {
					context.setLocation(listIDMap.get(j).get("Location"));
					context.setToPallet((listIDMap.get(j).get("ToPalletID")));
					context.setToLocation((listIDMap.get(j).get("ToLocation")));
					context.setFinalLocation((listIDMap.get(j).get("FinalLocation")));
					context.setQtyToMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
					context.setContainerId(listIDMap.get(j).get("ToContainerID"));
				}
			}
			the_location_should_be_displayed();
			i_enter_SKU_id_quantity_and_stock_details();

			listIDMap.get(i).replace("TagID", context.getTagId());
			context.setListIDMap(listIDMap);

			the_to_pallet_to_location_and_destination_should_be_displayed();
			Assert.assertTrue("Container ID entry page is not displayed as expected",
					storeTrackingOrderPickingPage.isContainerIDDisplayed());
			i_enter_container_id_and_check_strings();

			if (!storeTrackingOrderPickingPage.isPickEntryDisplayed()) {
				failureList.add("Picking not completed and Home page not displayed for List ID " + context.getListID());
				context.setFailureList(failureList);
			}
		}

	}
	
	@When("^I enter task and list id$")
	public void i_enter_task_and_list_id(String taskId, String listId) throws Throwable {
		storeTrackingOrderPickingPage.enterTaskID(taskId);
		storeTrackingOrderPickingPage.enterListID(listId);
		puttyFunctionsPage.pressEnter();
	}
	
	@Then("^the STO should be container picked for multiple order ids$")
	public void the_sto_should_be_container_picked_for_multiple_order_ids() throws Throwable {
		puttyFunctionsStepDefs.i_login_as_warehouse_user_in_putty();
		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_picking_with_container_pick();
		i_should_be_directed_to_pick_entry_page();

		DataTable orderIDDatatable = orderHeaderContext.getOrderIDDataTable();
		for (Map<String, String> dataRow : orderIDDatatable.asMaps(String.class, String.class)) {
			String orderIds = dataRow.get("OrderID");
//			i_pick_all_the_list_ids_for_the_store_tracking_order();
			i_pick_all_the_list_ids_for_multiple_store_tracking_order(orderIds);
		}
		//i_should_see_the_picking_completion();
		hooks.logoutPutty();
	}
}
