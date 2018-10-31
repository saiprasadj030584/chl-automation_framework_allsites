package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.LocationDB;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.MoveTaskUpdateDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.db.Exit.OrderLineDB;
import com.jda.wms.db.Exit.PickFaceTableDB;
import com.jda.wms.db.Exit.SkuConfigDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.JdaLoginPage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskManagementPage;
import com.jda.wms.pages.Exit.MoveTaskQueryPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.PickFaceMaintenancePage;
import com.jda.wms.pages.Exit.StockCheckListGenerationPage;
import com.jda.wms.pages.Exit.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import edu.emory.mathcs.backport.java.util.Arrays;

public class MoveTaskStepDefs {
	private final JdaHomePage jdaHomePage;
	private MoveTaskDB moveTaskDB;
	private Context context;
	private final JdaLoginPage jdaLoginPage;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private SkuConfigDB skuConfigDB;
	private OrderHeaderContext orderHeaderContext;
	private final JDAFooter jDAFooter;
	Map<Integer, Map<String, String>> replenishmentDetailsMap;
	private LocationDB locationDB;
	private MoveTaskQueryPage moveTaskQueryPage;
	private MoveTaskUpdatePage moveTaskUpdatePage;
	private final Hooks hooks;
//	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
//	private PuttyFunctionsPage puttyFunctionsPage;
	private static Utilities utilities;
	private final InventoryDB inventoryDB;
	private Verification verification;
	private OrderHeaderDB orderHeaderDB;
	private DataSetupRunner dataSetupRunner;
	private GetTCData getTCData;
	private OrderLineDB orderLineDB;
	private SkuDB skuDB;
	private Map<Integer, Map<String, String>> stockTransferOrderMap;
	private JDALoginStepDefs jdaLoginStepDefs;
//	private SystemAllocationStepDefs systemAllocationStepDefs;
	private OrderPreparationStepDefs orderPreparationStepDefs;
	private final StockCheckListGenerationPage stockCheckListGenerationPage;
	private final MoveTaskListGenerationPage moveTaskListGenerationPage;
	private final MoveTaskUpdateDB moveTaskUpdateDB;
	private final PickFaceMaintenancePage pickFaceMaintenancPage;
	private final PickFaceTableDB pickFaceTableDB;

//	private final PickFaceMaintenanceStepDefs pickFaceMaintenanceStepDefs;
	private OrderHeaderMaintenanceStepDefs  orderHeaderMaintenanceStepDefs;
	private final OrderLineMaintenanceStepDefs  orderLineMaintenanceStepDefs;
	private JDAFooter jdaFooter;
	private MoveTaskManagementPage moveTaskManagementPage;
	
//	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;
//	private final PurchaseOrderReceivingStepDefs  purchaseOrderReceivingStepDefs;
	
	

	//private final PickFaceMaintenanceStepDefs pickFaceMaintenanceStepDefs;



	@Inject
	public MoveTaskStepDefs(Verification verification, StockCheckListGenerationPage stockCheckListGenerationPage,
			JDALoginStepDefs jdaLoginStepDefs, OrderHeaderDB orderHeaderDB, GetTCData getTCData,
			OrderLineDB orderLineDB, SkuDB skuDB,
			OrderPreparationStepDefs orderPreparationStepDefs,
			DataSetupRunner dataSetupRunner, Utilities utilities,
			 MoveTaskQueryPage moveTaskQueryPage, Hooks hooks,
			MoveTaskDB moveTaskDB, Context context, JDAFooter jDAFooter, SkuConfigDB skuConfigDB,
			OrderHeaderContext orderHeaderContext, LocationDB locationDB, InventoryDB inventoryDB,
			PickFaceTableDB pickFaceTableDB, PickFaceMaintenancePage pickFaceMaintenancPage,
			MoveTaskListGenerationPage moveTaskListGenerationPage, JdaLoginPage jdaLoginPage,

			MoveTaskUpdatePage moveTaskUpdatePage, JdaHomePage jdaHomePage, MoveTaskUpdateDB moveTaskUpdateDB,
			JDAFooter jdaFooter,MoveTaskManagementPage moveTaskManagementPage,
			
			OrderLineMaintenanceStepDefs  orderLineMaintenanceStepDefs,OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepDefs) {
	
		this.moveTaskUpdateDB = moveTaskUpdateDB;
		this.moveTaskUpdatePage = moveTaskUpdatePage;
		this.pickFaceMaintenancPage = pickFaceMaintenancPage;
		this.jdaHomePage = jdaHomePage;
		this.jdaLoginPage = jdaLoginPage;
		this.stockCheckListGenerationPage = stockCheckListGenerationPage;
		this.moveTaskListGenerationPage = moveTaskListGenerationPage;
		this.orderPreparationStepDefs = orderPreparationStepDefs;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.verification = verification;
		this.skuDB = skuDB;
		this.orderHeaderDB = orderHeaderDB;
		this.dataSetupRunner = dataSetupRunner;
		this.getTCData = getTCData;
//		this.puttyFunctionsPage = puttyFunctionsPage;
		this.moveTaskQueryPage = moveTaskQueryPage;
		this.moveTaskDB = moveTaskDB;
		this.pickFaceTableDB = pickFaceTableDB;
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.skuConfigDB = skuConfigDB;
		this.orderHeaderContext = orderHeaderContext;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.inventoryDB = inventoryDB;
		this.orderLineDB = orderLineDB;
		this.orderHeaderMaintenanceStepDefs=orderHeaderMaintenanceStepDefs;
		this.orderLineMaintenanceStepDefs=orderLineMaintenanceStepDefs;
		this.jdaFooter=jdaFooter;
		this.moveTaskManagementPage=moveTaskManagementPage;
		}
	@Given ("^Navigate to Move Task management Screen to verify Order Allocated status$")
	public void Navigate_to_Move_Task_management_Screen_to_verify_Order_Allocated_status() throws Throwable{
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		Thread.sleep(3000);
		jdaHomePage.navigateToMoveTaskListManagementPage();
		Thread.sleep(3000);
		moveTaskListGenerationPage.enterTaskIdInMoveTaskUpdate(context.getOrderId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}
	@And ("^Validation of List Id generated with prefix as MANB$")
	public void Validation_of_List_Id_generated_with_prefic_as_MANB()throws Throwable{
			
			moveTaskManagementPage.validateListID();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as MANB", "MANB", prefixlist);
			logger.debug("List Id generated with prefix as MANB is : " + actuallist);
			System.out.println("List Id generated with prefix as MANB is : " + actuallist);
		}
	@Given ("^Navigate to Move Task management Screen to verify Order Allocated status for IDT$")
	public void Navigate_to_Move_Task_management_Screen_to_verify_Order_Allocated_status_for_IDT() throws Throwable{
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		Thread.sleep(3000);
		jdaHomePage.navigateToMoveTaskListManagementPage();
		Thread.sleep(3000);
		moveTaskListGenerationPage.enterTaskIdInMoveTaskUpdate(context.getOrderId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}
	@And ("^Validation of List Id generated with prefix as IDT$")
	public void Validation_of_List_Id_generated_with_prefic_as_IDT()throws Throwable{
			
			moveTaskManagementPage.validateListIDforIDT();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as IDT", "IDTB", prefixlist);
			logger.debug("List Id generated with prefix as IDT is : " + actuallist);
			System.out.println("List Id generated with prefix as IDT is : " + actuallist);
		}
	
	
		

	@Given("^the tagid, quantity to move details should be displayed for the sku \"([^\"]*)\" with \"([^\"]*)\" tasks$")
	public void the_tagid_quantity_to_move_details_should_be_displayed_for_the_sku_with_tasks(String sku, String taskId)
			throws Throwable {
		ArrayList<String> qtyToMove = new ArrayList<String>();
		ArrayList<String> tagID = new ArrayList<String>();
		Map<Integer, Map<String, String>> replenishmentDetailsMap = new HashMap<Integer, Map<String, String>>();

		context.setSkuId(sku);
		context.setTaskId(taskId);
		qtyToMove = moveTaskDB.getReplenishQtyToMoveList(sku);
		tagID = moveTaskDB.getReplenishTagIDList(sku);

		System.out.println(sku + taskId + qtyToMove + tagID);

		for (int i = 0; i < tagID.size(); i++) {
			HashMap<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("QtyToMove", qtyToMove.get(i));
			listDetailsMap.put("TagID", tagID.get(i));
			listDetailsMap.put("ListID", "");
			// FIXME why does key contains serial sequence number in the map?
			replenishmentDetailsMap.put(i + 1, listDetailsMap);
		}
		context.setReplenishmentDetailsMap(replenishmentDetailsMap);
	}

	@Given("^I have the tagId, quantity to move, list Id for the sku \"([^\"]*)\" with \"([^\"]*)\" task$")
	public void i_have_the_tagid_quantity_to_move_list_Id_for_the_sku_with_task(String sku, String taskId)
			throws Throwable {
		context.setSkuId(sku);
		context.setTaskId(taskId);
		String tagID = moveTaskDB.getReplenishTagID(sku);
		context.setTagId(tagID);
		String qtyToMove = moveTaskDB.getReplenishQtyToMove(sku,tagID);	
		context.setQtyToMove(qtyToMove);
		String listId=moveTaskDB.getReplenishList(sku,tagID);
		if (null == listId) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_the_move_task_list_Generation_screen();
			i_select_the_mode_of_list_generation_as_create_list();
			i_enter_tagId();
			i_proceed_with_execute_button();
			the_available_list_should_be_displayed_as();
			i_select_the_record_from_available_list();
			the_record_should_be_added_in_the_selected_list_for_task();
			jDAFooter.clickNextButton();
			// i_should_see_the_confirmation_for_the_number_of_location_checked();
			// i_should_see_the_confirmation_for_the_number_of_items_checked();
			// i_proceed_to_display_the_list();
			i_proceed_to_generate_stock_check_list();
			i_should_see_the_created_list_as();
			String ListIDGenerated = moveTaskDB.getListForReleasedStatus(context.getTagId());
			System.out.println("list id after gen" + ListIDGenerated);
			context.setListID(ListIDGenerated);
		} else {
			context.setListID(listId);
		}
	}
	
	@Given("^the tagId is in released status$")
		public void the_tagId_is_in_released_status() throws Throwable {
		String status = moveTaskDB.getReplenishStatus(context.getTagId());
	System.out.println("status=" + status);
	if (status.equals("Hold")) {
		i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
		i_navigate_to_move_task_update();
		i_enter_Tagid_in_move_task_update();
		jDAFooter.clickNextButton();
		Thread.sleep(4000);
		moveTaskUpdatePage.clickReleaseButton();
		jDAFooter.clickNextButton();
		Thread.sleep(3000);
		Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
		Thread.sleep(3000);
		jDAFooter.clickDoneButton();
	}
	}
	
	@Then("^the list ids should be generated$")
	public void the_list_ids_should_be_generated() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		replenishmentDetailsMap = context.getReplenishmentDetailsMap();

		for (int r = 1; r <= replenishmentDetailsMap.size(); r++) {
			// FIXME this context contains only last value of tag id and sku id
			// in the context, why do you need this?
			// context.setTagId(replenishmentDetailsMap.get(r).get("TagID"));
			// context.setSkuId(replenishmentDetailsMap.get(r).get("SkuID"));
			String listID = moveTaskDB.getListID(replenishmentDetailsMap.get(r).get("TagID"), context.getSkuId());

			if (listID.equals(null)) {
				failureList.add("List ID not displayed as expected for Tag " + context.getTagId()
						+ " Expected [NOT Null] but was " + listID);
			} else {
				replenishmentDetailsMap.get(r).replace("ListID", listID);
			}
		}
		Assert.assertTrue("Replenish List IDs are not as expected. [" + Arrays.asList(failureList.toString()) + "].",
				failureList.isEmpty());
	}

	@When("^I get the pallet ids from the move task for all orders$")
	public void i_get_the_pallet_ids_from_the_move_task_for_all_orders() throws Throwable {
		ArrayList<String> palletListId = new ArrayList<String>();

		DataTable orderIDDatatable = orderHeaderContext.getOrderIDDataTable();
		for (Map<String, String> dataRow : orderIDDatatable.asMaps(String.class, String.class)) {
			palletListId.addAll(moveTaskDB.getPalletIdList(dataRow.get("OrderID")));

		}
		context.setPalletIDList(palletListId);
		logger.debug(" Pallet ID List : " + context.getPalletIDList());
	}

	
	// FIXME to update after merging with Kiruthika's code
	/*
	 * @Given(
	 * "^the STO \"([^\"]*)\" should have list id, quantity to move,to pallet, to container details$"
	 * ) public void
	 * the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details
	 * (String orderID) throws Throwable { Map<Integer, Map<String, String>>
	 * listIDMap = new HashMap<Integer, Map<String, String>>();
	 * ArrayList<String> listID = new ArrayList<String>(); ArrayList<String>
	 * qtyToMove = new ArrayList<String>(); ArrayList<String> toPalletID = new
	 * ArrayList<String>(); ArrayList<String> toContainerID = new
	 * ArrayList<String>(); context.setOrderId(orderID); listID =
	 * moveTaskDB.getListId(orderID); qtyToMove =
	 * moveTaskDB.getQtyToMove(orderID); toPalletID =
	 * moveTaskDB.getToPalletID(orderID); toContainerID =
	 * moveTaskDB.getToContainerID(orderID);
	 * 
	 * for (int i = 0; i < listID.size(); i++) { Map<String, String>
	 * listDetailsMap = new HashMap<String, String>();
	 * listDetailsMap.put("ListID", listID.get(i));
	 * listDetailsMap.put("QtyToMove", qtyToMove.get(i));
	 * listDetailsMap.put("ToPalletID", toPalletID.get(i));
	 * listDetailsMap.put("ToContainerID", toContainerID.get(i));
	 * listIDMap.put(i + 1, listDetailsMap); } context.setListIDMap(listIDMap);
	 */

	@Given("^the STO should have list id, quantity to move,to pallet, to container details from move task table and location in \"([^\"]*)\" status$")
	public void the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table_and_location_in_status(
			String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
//		*priyanka
//		ArrayList<String> qtyToMoveList = new ArrayList<String>();
//		ArrayList<String> toPalletIDList = new ArrayList<String>();
//		ArrayList<String> toContainerIDList = new ArrayList<String>();
//		ArrayList<String> skuIDList = new ArrayList<String>();
//		ArrayList<String> locationList = new ArrayList<String>();
//		ArrayList<String> toLocationList = new ArrayList<String>();
//		ArrayList<String> finalLocationList = new ArrayList<String>();
		listIDList = moveTaskDB.getListId(context.getOrderId());

		for (int l = 0; l < listIDList.size(); l++) {
			if (listIDList.get(l) == null) {
				failureList.add("List ID not generated as expected : List id " + l + "is null");
			}
		}
		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toString()) + "].",
				failureList.isEmpty());
//by priyanka
//		qtyToMoveList = moveTaskDB.getQtyToMoveList(context.getOrderId());
//		toPalletIDList = moveTaskDB.getToPalletIDList(context.getOrderId());
//		toContainerIDList = moveTaskDB.getToContainerIDList(context.getOrderId());
//		skuIDList = moveTaskDB.getSkuIDList(context.getOrderId());
//		locationList = moveTaskDB.getLocationList(context.getOrderId());
//		toLocationList = moveTaskDB.getToLocationList(context.getOrderId());
//		finalLocationList = moveTaskDB.getFinalLocationList(context.getOrderId());
		// fromLocationList=moveTaskDB.getFromLocationList(context.getOrderId());
		// System.out.println("location"+fromLocationList);
		// String newlocation=fromLocationList.toString();
		// locationDB.updateLocation(newlocation, status);

		ArrayList<String> location = moveTaskDB.getFromLocationList(context.getOrderId());
		String LocationArray[] = new String[location.size()];
		for (int j = 0; j < location.size(); j++) {
			LocationArray[j] = location.get(j);
		}
		for (String k : LocationArray) {
			System.out.println(k);
			locationDB.updateLocation(k, status);
		}
		System.out.println("location" + LocationArray);
		// fromLocationList=moveTaskDB.getFromLocationList(context.getOrderId());

		// String fromLocation=fromLocationList.toString();
		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			//By priyanka
//			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
//			listDetailsMap.put("ToPalletID", toPalletIDList.get(i));
//			listDetailsMap.put("ToContainerID", toContainerIDList.get(i));
//			listDetailsMap.put("SkuId", skuIDList.get(i));
//			listDetailsMap.put("Location", locationList.get(i));
//			listDetailsMap.put("ToLocation", toLocationList.get(i));
//			listDetailsMap.put("FinalLocation", finalLocationList.get(i));

			listDetailsMap.put("TagID", "");
			listIDMap.put(i + 1, listDetailsMap);
			// listDetailsMap.put("FromLocation", fromLocationList.get(i));
			// listDetailsMap.put("FromLocation",
			// fromLocationList.get(LocationArray));
		}

		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}
	
	@Given("^the STO should have list id, quantity to move,to pallet, to container details from move task table and location in \"([^\"]*)\" status for consolidation$")
	public void the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table_and_location_in_status_for_consolidation(
			String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
		ArrayList<String> qtyToMoveList = new ArrayList<String>();
		ArrayList<String> toPalletIDList = new ArrayList<String>();
		ArrayList<String> toContainerIDList = new ArrayList<String>();
		ArrayList<String> skuIDList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> toLocationList = new ArrayList<String>();
		ArrayList<String> finalLocationList = new ArrayList<String>();
		listIDList = moveTaskDB.getListId(context.getTaskId());

		for (int l = 0; l < listIDList.size(); l++) {
			if (listIDList.get(l) == null) {
				failureList.add("List ID not generated as expected : List id " + l + "is null");
			}
		}
		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toString()) + "].",
				failureList.isEmpty());

		qtyToMoveList = moveTaskDB.getQtyToMoveList(context.getTaskId());
		toPalletIDList = moveTaskDB.getToPalletIDList(context.getTaskId());
		toContainerIDList = moveTaskDB.getToContainerIDList(context.getTaskId());
		skuIDList = moveTaskDB.getSkuIDList(context.getTaskId());
		locationList = moveTaskDB.getLocationList(context.getTaskId());
		toLocationList = moveTaskDB.getToLocationList(context.getTaskId());
		finalLocationList = moveTaskDB.getFinalLocationList(context.getTaskId());
		// fromLocationList=moveTaskDB.getFromLocationList(context.getOrderId());
		// System.out.println("location"+fromLocationList);
		// String newlocation=fromLocationList.toString();
		// locationDB.updateLocation(newlocation, status);

		ArrayList<String> location = moveTaskDB.getFromLocationList(context.getTaskId());
		String LocationArray[] = new String[location.size()];
		for (int j = 0; j < location.size(); j++) {
			LocationArray[j] = location.get(j);
		}
		for (String k : LocationArray) {
			System.out.println(k);
			locationDB.updateLocation(k, status);
		}
		System.out.println("location" + LocationArray);
		// fromLocationList=moveTaskDB.getFromLocationList(context.getOrderId());

		// String fromLocation=fromLocationList.toString();
		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
			listDetailsMap.put("ToPalletID", toPalletIDList.get(i));
			listDetailsMap.put("ToContainerID", toContainerIDList.get(i));
			listDetailsMap.put("SkuId", skuIDList.get(i));
			listDetailsMap.put("Location", locationList.get(i));
			listDetailsMap.put("ToLocation", toLocationList.get(i));
			listDetailsMap.put("FinalLocation", finalLocationList.get(i));

			listDetailsMap.put("TagID", "");
			listIDMap.put(i + 1, listDetailsMap);
			// listDetailsMap.put("FromLocation", fromLocationList.get(i));
			// listDetailsMap.put("FromLocation",
			// fromLocationList.get(LocationArray));
		}

		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}
	@Given("^the list id is generated$")
	public void the_list_id_is_generated() throws Throwable {
		i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
		i_navigate_to_the_move_task_list_Generation_screen();
		i_select_the_mode_of_list_generation_as_create_list();
//		i_enter_tagId();
		i_enter_taskId();
		i_proceed_with_execute_button();
		i_proceed_with_add_button();
//		the_available_list_should_be_displayed_as();
//		i_select_the_record_from_available_list();
//		the_record_should_be_added_in_the_selected_list_for_task();
		jDAFooter.clickNextButton();
		// i_should_see_the_confirmation_for_the_number_of_location_checked();
		// i_should_see_the_confirmation_for_the_number_of_items_checked();
		// i_proceed_to_display_the_list();
		i_proceed_to_generate_stock_check_list();
		i_should_see_the_created_list_as();
//		String ListIDGenerated = moveTaskDB.getListForReleasedStatus(context.getTagId());
		String ListIDGenerated = moveTaskDB.getListForReleasedStatus1(context.getOrderId());
		System.out.println("list id after gen" + ListIDGenerated);
		context.setListID(ListIDGenerated);	
	}
	@Given("^the STO should have list id, quantity to move,to pallet, to container details from move task table$")
	public void the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
		ArrayList<String> qtyToMoveList = new ArrayList<String>();
		ArrayList<String> toPalletIDList = new ArrayList<String>();
		ArrayList<String> toContainerIDList = new ArrayList<String>();
		ArrayList<String> skuIDList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> toLocationList = new ArrayList<String>();
		ArrayList<String> finalLocationList = new ArrayList<String>();
		ArrayList<String> fromLocationList = new ArrayList<String>();
		//listIDList = moveTaskDB.getListId(context.getOrderId());
		String listID = moveTaskDB.getList(context.getOrderId());

//		if (null == listID) {
//			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
//			i_navigate_to_the_move_task_list_Generation_screen();
//			i_select_the_mode_of_list_generation_as_create_list();
//			i_enter_taskId();
//			i_proceed_with_execute_button();
//			i_proceed_with_add_button();
//			jDAFooter.clickNextButton();
//			i_proceed_to_generate_stock_check_list();
//			i_should_see_the_created_list_as();
//			String ListIDGenerated = moveTaskDB.getListForReleasedStatus1(context.getOrderId());
//			System.out.println("list id after gen" + ListIDGenerated);
//			context.setListID(ListIDGenerated);
//			
//		} else {
//			context.setListID(listID);
//			
//		}
		qtyToMoveList = moveTaskDB.getQtyToMoveList(context.getOrderId());
		toPalletIDList = moveTaskDB.getToPalletIDList(context.getOrderId());
		toContainerIDList = moveTaskDB.getToContainerIDList(context.getOrderId());
		skuIDList = moveTaskDB.getSkuIDList(context.getOrderId());
		locationList = moveTaskDB.getLocationList(context.getOrderId());
		toLocationList = moveTaskDB.getToLocationList(context.getOrderId());
		finalLocationList = moveTaskDB.getFinalLocationList(context.getOrderId());
		fromLocationList=moveTaskDB.getFromLocationList(context.getOrderId());
		
		String tagid=moveTaskDB.getTag(context.getOrderId());
		context.setTagId(tagid);
		String toLocation=moveTaskDB.getToLocation(context.getOrderId());
		context.setToLocation(toLocation);
//		String fromLocation=moveTaskDB.getFromLocation(context.getOrderId());
//		context.setFromLocation(fromLocation);
		
		
		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
			listDetailsMap.put("ToPalletID", toPalletIDList.get(i));
			listDetailsMap.put("ToContainerID", toContainerIDList.get(i));
			listDetailsMap.put("SkuId", skuIDList.get(i));
			listDetailsMap.put("Location", locationList.get(i));
			listDetailsMap.put("ToLocation", toLocationList.get(i));
			listDetailsMap.put("FinalLocation", finalLocationList.get(i));
			listDetailsMap.put("FromLocation", fromLocationList.get(i));
			listDetailsMap.put("TagID", "");
			listIDMap.put(i + 1, listDetailsMap);
		}

		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}
	
	
	@Given("^the STO should have list id, quantity to move,to pallet, to container details from move task table for despatch$")
	public void the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table_for_despatch() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
		ArrayList<String> qtyToMoveList = new ArrayList<String>();
		ArrayList<String> toPalletIDList = new ArrayList<String>();
		ArrayList<String> toContainerIDList = new ArrayList<String>();
		ArrayList<String> skuIDList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> toLocationList = new ArrayList<String>();
		ArrayList<String> finalLocationList = new ArrayList<String>();
		ArrayList<String> fromLocationList = new ArrayList<String>();
		//listIDList = moveTaskDB.getListId(context.getOrderId());
		String listID = moveTaskDB.getList(context.getOrderId());
		
		if (null == listID) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_the_move_task_list_Generation_screen();
			i_select_the_mode_of_list_generation_as_create_list();
			i_enter_tagId();
			i_proceed_with_execute_button();
			the_available_list_should_be_displayed_as();
			i_select_the_record_from_available_list();
			the_record_should_be_added_in_the_selected_list_for_task();
			jDAFooter.clickNextButton();
			// i_should_see_the_confirmation_for_the_number_of_location_checked();
			// i_should_see_the_confirmation_for_the_number_of_items_checked();
			// i_proceed_to_display_the_list();
			i_proceed_to_generate_stock_check_list();
			i_should_see_the_created_list_as();
			String ListIDGenerated =moveTaskDB.getList(context.getOrderId());
			System.out.println("list id after gen" + ListIDGenerated);
			context.setListID(ListIDGenerated);		
		} else {
			context.setListID(listID);
		}
//		
//		for (int l = 0; l < listIDList.size(); l++) {
//			if (listIDList.get(l) == null) {
//				failureList.add("List ID not generated as expected : List id " + l + "is null");
//			}
//		}
//		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toString()) + "].",
//				failureList.isEmpty());
		qtyToMoveList = moveTaskDB.getQtyToMoveList(context.getOrderId());
		toPalletIDList = moveTaskDB.getToPalletIDList(context.getOrderId());
		toContainerIDList = moveTaskDB.getToContainerIDList(context.getOrderId());
		skuIDList = moveTaskDB.getSkuIDList(context.getOrderId());
		locationList = moveTaskDB.getLocationList(context.getOrderId());
		toLocationList = moveTaskDB.getToLocationList(context.getOrderId());
		finalLocationList = moveTaskDB.getFinalLocationList(context.getOrderId());
		fromLocationList=moveTaskDB.getFromLocationList(context.getOrderId());
		
		String tagid=moveTaskDB.getTag(context.getOrderId());
		context.setTagId(tagid);
		String toLocation=moveTaskDB.getToLocation(context.getOrderId());
		context.setToLocation(toLocation);
//		String fromLocation=moveTaskDB.getFromLocation(context.getOrderId());
//		context.setFromLocation(fromLocation);
		
		
		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
			listDetailsMap.put("ToPalletID", toPalletIDList.get(i));
			listDetailsMap.put("ToContainerID", toContainerIDList.get(i));
			listDetailsMap.put("SkuId", skuIDList.get(i));
			listDetailsMap.put("Location", locationList.get(i));
			listDetailsMap.put("ToLocation", toLocationList.get(i));
			listDetailsMap.put("FinalLocation", finalLocationList.get(i));
			listDetailsMap.put("FromLocation", fromLocationList.get(i));
			listDetailsMap.put("TagID", "");
			listIDMap.put(i + 1, listDetailsMap);
		}

		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}


	@When("^I get the pallet ids from the move task$")
	public void i_get_the_pallet_ids_from_the_move_task() throws Throwable {
		ArrayList<String> palletIDList = new ArrayList<String>();
		Integer moveTaskRecordCount = moveTaskDB.getRecordCountByTaskID(context.getOrderId());
		context.setMoveTaskRecordCount(moveTaskRecordCount);
		String PalletId = moveTaskDB.getPallet(context.getOrderId());
		context.setPalletID(PalletId);
		palletIDList = moveTaskDB.getPalletIdList(context.getOrderId());
		context.setPalletIDList(palletIDList);
		logger.debug("No of Pallets to load : " + palletIDList.size());
		logger.debug("Move task Rec Count before loading : " + moveTaskRecordCount);
	}

	@Then("^the pallet id should be displayed$")
	public void the_pallet_id_should_be_displayed() throws Throwable {
		Assert.assertNotNull("No Pallet IDs were retrieved from DB", context.getPalletIDList());
	}

	@Then("^no record should exist for the Order ID$")
	public void no_record_should_exist_for_the_Order_ID() throws Throwable {
		Assert.assertEquals("Result is not as expected. ", "0",
				Integer.toString(moveTaskDB.getRecordCountByTaskID(context.getOrderId())));
	}

	@Then("^the replenish STO should have list id,quantity to move, tagid, location details and case ratio$")
	public void the_replenish_STO_should_have_list_id_quantity_to_move_tagid_location_details_and_case_ratio()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
		ArrayList<String> qtyToMoveList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> toLocationList = new ArrayList<String>();
		ArrayList<String> finalLocationList = new ArrayList<String>();
		ArrayList<String> tagIDList = new ArrayList<String>();

		listIDList = moveTaskDB.getReplenishListId(context.getSkuId(),context.getTagId());

		for (int j = 0; j < listIDList.size(); j++) {
			if (null == listIDList.get(j)) {
				failureList.add("List ID not generated as expected : List id " + j + "is null");
			}
		}

		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toString()) + "].",
				failureList.isEmpty());

		qtyToMoveList = moveTaskDB.getReplenishQtyToMoveList(context.getSkuId());
		tagIDList = moveTaskDB.getReplenishTagIDList(context.getSkuId());
		locationList = moveTaskDB.getReplenishLocationList(context.getSkuId());
		toLocationList = moveTaskDB.getReplenishToLocationList(context.getSkuId());
		finalLocationList = moveTaskDB.getReplenishFinalLocationList(context.getSkuId());

		context.setCaseRatio(Integer.parseInt(skuConfigDB.getRatio1To2(moveTaskDB.getPackConfig(context.getSkuId()))));

		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
			listDetailsMap.put("Location", locationList.get(i));
			listDetailsMap.put("ToLocation", toLocationList.get(i));
			listDetailsMap.put("FinalLocation", finalLocationList.get(i));
			listDetailsMap.put("TagID", tagIDList.get(i));
			listIDMap.put(i + 1, listDetailsMap);
		}
		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}

	@When("^I select Replenishments option$")
	public void i_select_Replenishments_option() throws Throwable {
		moveTaskQueryPage.selectReplenishMenu();
		moveTaskQueryPage.selectReplenishMenuForTaskId();
		moveTaskQueryPage.selectReplenishMenuForTaskIdreplenish();

	}

	@When("^I select consolidation option$")
	public void i_select_consolidation_option() throws Throwable {
		moveTaskQueryPage.selectPickingMenu();
		moveTaskQueryPage.selectSortingMenu();
	}

	@Given("^I give container id where task \"([^\"]*)\" and type Y in new pallet$")
	public void i_give_container_id_where_task_and_type_N_in_new_pallet(String taskId) throws Throwable {
		// String Pallet=moveTaskDB.getPalletId(context.getTaskId());
		// context.setPalletID(Pallet);
		// moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterYforPallet();
		moveTaskQueryPage.nextTab();
		context.setTaskId(taskId);
		String Container = moveTaskDB.getContainerid(context.getTaskId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		moveTaskQueryPage.PressEnter();
	}
	@Given("^I give container id where task and type Y in new pallet$")
	public void i_give_container_id_where_task_and_type_N_in_new_pallet() throws Throwable {
		// String Pallet=moveTaskDB.getPalletId(context.getTaskId());
		// context.setPalletID(Pallet);
		// moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.nextTab();
		/*---new-sagorika----*/
//		 String Pallet=moveTaskDB.getPalletForTask(context.getOrderId());
//				 context.setPalletID(Pallet);
//				 moveTaskQueryPage.enterPallet(Pallet);
				 moveTaskQueryPage.PressEnter();
				 
		moveTaskQueryPage.enterYforPallet();
		moveTaskQueryPage.nextTab();
//		context.setTaskId(taskId);
		String Container = moveTaskDB.getContainerid(context.getOrderId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		moveTaskQueryPage.PressEnter();
	}

	@Then("^I validate that pallet gets auto populated and the record checked$")
	public void i_validate_that_pallet_gets_auto_populated_and_the_combination_error() throws Throwable {
		Assert.assertTrue("pallet does not get auto populated as expected", moveTaskQueryPage.isPalletAutoPopulated());
		String Pallet = moveTaskQueryPage.getPallet();
		System.out.println("pallet" + Pallet);
		context.setPalletID(Pallet);
		Thread.sleep(1000);
		moveTaskQueryPage.PressEnter();
		Thread.sleep(1000);
		moveTaskQueryPage.PressEnter();
		Thread.sleep(1000);
		moveTaskQueryPage.PressEnter();
		Thread.sleep(1000);
		moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.PressF11();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.PressEnter();
		Thread.sleep(5000);
		moveTaskQueryPage.enterPrinter1();
		String Record = moveTaskDB.getRecordForPallet(context.getPalletID());
		context.setRecordForPallet(Record);
		System.out.println("record =" + context.getRecordForPallet(context.getPalletID()));
		hooks.logoutPutty();
	}

	@When("^I enter list id for the task id \"([^\"]*)\" and status \"([^\"]*)\"$")
	public void i_enter_list_id_for_this_task_id_and_status(String taskId, String status) throws Throwable {
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.nextTab();
		context.setTaskId(taskId);
		context.setStatus(status);
		String ListID = moveTaskDB.getList(context.getTaskId(), context.getStatus());
		context.setListID(ListID);
		moveTaskQueryPage.enterList(ListID);
		moveTaskQueryPage.PressEnter();

	}

	@Then("^I validate the error no valid move task found$")
	public void i_validate_the_error_no_valid_move_task_found() throws Throwable {
		Assert.assertTrue("error no valid move task found is not displayed as expected",
				moveTaskQueryPage.isnoValidMoveTaskFound());
		hooks.logoutPutty();
	}

	@When("^I enter list id for the task id \"([^\"]*)\"$")
	public void i_enter_list_id_for_this_task_id(String taskId) throws Throwable {
		moveTaskQueryPage.nextTab();
		context.setTaskId(taskId);
		String ListID = moveTaskDB.getList(context.getTaskId());
		context.setListID(ListID);
		moveTaskQueryPage.enterList(ListID);
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.selectAnyOption();
	}

	@Given("^I enter an invalid tag id$")
	public void i_enter_an_invalid_tag_id() throws Throwable {
		moveTaskQueryPage.nextTab();
		String tag = Utilities.getTenDigitRandomNumber();
		context.setTagId(tag);
		moveTaskQueryPage.enterInvalidTagId(tag);
		moveTaskQueryPage.PressEnter();
	}
	
	

	@Then("^I validate the error invalid tag id$")
	public void i_validate_the_error_invalid_tag_id() throws Throwable {
		Assert.assertTrue("error invalid tag id not displayed as expected", moveTaskQueryPage.isinvalidTagId());
		hooks.logoutPutty();
	}

	@When("^I enter list id for the task \"([^\"]*)\" , status \"([^\"]*)\"  and update the quantity$")
	public void i_enter_list_id_for_this_task_and_update_the_quantity(String tagId, String status) throws Throwable {
		context.setTagId(tagId);
		moveTaskQueryPage.nextTab();
		context.setTagId(tagId);
		context.setStatus(status);
		String locationID = inventoryDB.getLocation(context.getTagId());
		context.setlocationID(locationID);
		String ListID = moveTaskDB.getListForReleasedStatus(context.getTagId(), context.getStatus());
		context.setListID(ListID);
		moveTaskQueryPage.enterList(ListID);
		moveTaskQueryPage.PressEnter();

		String quantity = moveTaskQueryPage.getQuantity();
		String[] quantitySplit = quantity.split("_");
		quantity = quantitySplit[0];
		System.out.println("qunatity" + quantity);

		String Nwquantity = quantity.substring(0, quantity.length() - 1);
		int Quantity = Integer.parseInt(Nwquantity);
		int ReducedQuantity = Quantity - 1;
		String FinalQuantity = Integer.toString(ReducedQuantity);
		System.out.println("final qty" + FinalQuantity);
		context.setQuantity(FinalQuantity);
		moveTaskQueryPage.enterQuantity(FinalQuantity);
		moveTaskQueryPage.PressEnter();
	}

	@Given("^I have the list id with task id \"([^\"]*)\"$")
	public void i_have_the_list_id_with_task_id(String task) throws Throwable {

		context.setTask(task);
	//	String tag = moveTaskDB.getTag(task);
		//context.setTagId(tag);
		//System.out.println("tag=" + tag);
		//String status = moveTaskDB.getStatus(task, tag);

 		i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
 		jdaHomePage.navigateToPickFaceMaintenance();
// 		pickFaceMaintenanceStepDefs.i_add_the_location_Id_with_face_sku_site_id_and_trigger_qty("Fixed", "21036245",
// 				"9771");
 		Thread.sleep(4000);
// 		systemAllocationStepDefs.i_system_allocate_the_order_for_sku("21036245");
 		the_replenish_pick_is_created();
// 		context.setTask(task);
// 		// String tag=moveTaskDB.getTag(task);
// 		// context.setTagId(tag);
// 		// System.out.println("tag="+tag);
// 		String status = moveTaskDB.getStatus(task,context.getTagId());
 		String status = moveTaskDB.getStatus(task, context.getTagId());
    System.out.println("status=" + status);
		if (status.equals("Hold")) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_move_task_update();
			i_enter_Tagid_in_move_task_update();
			jDAFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickReleaseButton();
			jDAFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
			Thread.sleep(3000);
			jDAFooter.clickDoneButton();
			// moveTaskUpdateDB.releaseTagId(tag);
			// String updatedStatus=moveTaskDB.getStatus(task,tag);
			// System.out.println("status="+updatedStatus);
		}

		//context.setTagId(tag);

		String ListID = moveTaskDB.getListForReleasedStatus(context.getTagId());
		System.out.println("list" + ListID);

		if (null==ListID) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_the_move_task_list_Generation_screen();
			i_select_the_mode_of_list_generation_as_create_list();
			i_enter_tagId();
			i_proceed_with_execute_button();
			the_available_list_should_be_displayed_as();
			i_select_the_record_from_available_list();
			the_record_should_be_added_in_the_selected_list_for_task();
			jDAFooter.clickNextButton();
			// i_should_see_the_confirmation_for_the_number_of_location_checked();
			// i_should_see_the_confirmation_for_the_number_of_items_checked();
			// i_proceed_to_display_the_list();
			i_proceed_to_generate_stock_check_list();
			i_should_see_the_created_list_as();
			String ListIDGenerated = moveTaskDB.getListForReleasedStatus(context.getTagId());
			System.out.println("list id after gen" + ListIDGenerated);
			context.setListID(ListIDGenerated);
		} else {
			System.out.println("else part in list id gen");
			context.setListID(ListID);
		}
	}
	@Given("^I have the list id with task id \"([^\"]*)\" for sto replenish pick$")
	public void i_have_the_list_id_with_task_id_for_sto_replenish_pick(String task) throws Throwable {

		context.setTask(task);
		String tag = moveTaskDB.getTag(task);
		context.setTagId(tag);
		System.out.println("tag=" + tag);
		String status = moveTaskDB.getStatus(task, tag);

// 		context.setTask(task);
// 		// String tag=moveTaskDB.getTag(task);
// 		// context.setTagId(tag);
// 		// System.out.println("tag="+tag);
// 		String status = moveTaskDB.getStatus(task,context.getTagId());

    System.out.println("status=" + status);
		if (status.equals("Hold")) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_move_task_update();
			i_enter_Tagid_in_move_task_update();
			jDAFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickReleaseButton();
			jDAFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not released", moveTaskUpdatePage.istaskReleased());
			Thread.sleep(3000);
			jDAFooter.clickDoneButton();
			// moveTaskUpdateDB.releaseTagId(tag);
			// String updatedStatus=moveTaskDB.getStatus(task,tag);
			// System.out.println("status="+updatedStatus);
		}

		context.setTagId(tag);

		String ListID = moveTaskDB.getListForReleasedStatus(context.getTagId());
		System.out.println("list" + ListID);

		if (null == ListID) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_the_move_task_list_Generation_screen();
			i_select_the_mode_of_list_generation_as_create_list();
			i_enter_tagId();
			i_proceed_with_execute_button();
			the_available_list_should_be_displayed_as();
			i_select_the_record_from_available_list();
			the_record_should_be_added_in_the_selected_list_for_task();
			jDAFooter.clickNextButton();
			// i_should_see_the_confirmation_for_the_number_of_location_checked();
			// i_should_see_the_confirmation_for_the_number_of_items_checked();
			// i_proceed_to_display_the_list();
			i_proceed_to_generate_stock_check_list();
			i_should_see_the_created_list_as();
			String ListIDGenerated = moveTaskDB.getListForReleasedStatus(context.getTagId());
			System.out.println("list id after gen" + ListIDGenerated);
			context.setListID(ListIDGenerated);
		} else {
			context.setListID(ListID);
		}
	}
	
	

	@Given("^I have the list id with task id \"([^\"]*)\" and status \"([^\"]*)\"$")
	public void i_have_the_list_id_with_task_id_and_status(String task, String requiredStatus) throws Throwable {
		context.setTask(task);

		String tag = moveTaskDB.getTag(task);
		context.setTagId(tag);
		System.out.println("tag=" + tag);
		String status = moveTaskDB.getStatus(task, tag);
		System.out.println("status=" + status);
		
		context.setTagId(tag);
		String ListID = moveTaskDB.getListUsingStatus(context.getTagId(), requiredStatus);
		System.out.println("list" + ListID);

		if (null == ListID) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_the_move_task_list_Generation_screen();
			i_select_the_mode_of_list_generation_as_create_list();
			i_enter_tagId();
			i_proceed_with_execute_button();
			the_available_list_should_be_displayed_as();
			i_select_the_record_from_available_list();
			the_record_should_be_added_in_the_selected_list_for_task();
			jDAFooter.clickNextButton();
			// i_should_see_the_confirmation_for_the_number_of_location_checked();
			// i_should_see_the_confirmation_for_the_number_of_items_checked();
			// i_proceed_to_display_the_list();
			i_proceed_to_generate_stock_check_list();
			i_should_see_the_created_list_as();
			String ListIDGenerated = moveTaskDB.getListForReleasedStatus(context.getTagId());
			System.out.println("list id after gen" + ListIDGenerated);
			context.setListID(ListIDGenerated);
		} else {
			context.setListID(ListID);
		}
		
		if (status.equals("Released")) {
			i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application();
			i_navigate_to_move_task_update();
			i_enter_Tagid_in_move_task_update();
			jDAFooter.clickNextButton();
			Thread.sleep(4000);
			moveTaskUpdatePage.clickHoldButton();
			jDAFooter.clickNextButton();
			Thread.sleep(3000);
			Assert.assertTrue("Task is not set to Hold", moveTaskUpdatePage.istaskSelected());
			Thread.sleep(3000);
			jDAFooter.clickDoneButton();
			// moveTaskUpdateDB.releaseTagId(tag);
//			/ String updatedStatus=moveTaskDB.getStatus(task,tag);
			// System.out.println("status="+updatedStatus);
		}
	}

	


	private void i_enter_Tagid_in_move_task_update() throws FindFailed, InterruptedException {
		moveTaskListGenerationPage.enterTagIdInMoveTaskUpdate(context.getTagId());
	}

	@When("^I enter list id for the task as \"([^\"]*)\" and update the quantity$") // task=replenish
	public void i_enter_list_id_for_the_task_as_and_update_the_quantity(String task) throws Throwable {
		moveTaskQueryPage.nextTab();
		System.out.println("list id" + context.getListID());
		moveTaskQueryPage.enterList(context.getListID());
		moveTaskQueryPage.PressEnter();

		String quantity = moveTaskQueryPage.getQuantity();
		String[] quantitySplit = quantity.split("_");
		quantity = quantitySplit[0];
		System.out.println("qunatity" + quantity);

		String Nwquantity = quantity.substring(0, quantity.length() - 1);
		int Quantity = Integer.parseInt(Nwquantity);
		int ReducedQuantity = Quantity - 1;
		String FinalQuantity = Integer.toString(ReducedQuantity);
		System.out.println("final qty" + FinalQuantity);
		context.setQuantity(FinalQuantity);
		moveTaskQueryPage.enterQuantity(FinalQuantity);
		moveTaskQueryPage.PressEnter();
//		moveTaskQueryPage.selectAnyOption();
	}

	@When("^I navigate to move task update page$")
	public void i_navigate_to_move_task_update() throws Throwable {
		Thread.sleep(5000);
		jdaHomePage.navigateToMoveTaskUpdate();
		Thread.sleep(3000);
	}

	@Given("^I have logged in as a warehouse user in JDA dispatcher food application$")
	public void i_have_logged_in_as_a_warehouse_user_in_JDA_dispatcher_food_application() throws Throwable {
		jdaLoginPage.login();
	}

	@When("^I navigate to the move task list Generation page$")
	public void i_navigate_to_the_move_task_list_Generation_screen() throws Throwable {
		Thread.sleep(3000);
		jdaHomePage.navigateToMoveTaskListGenerationPage();
		Thread.sleep(3000);
	}

	@When("^I select the mode of list generation as create list$")
	public void i_select_the_mode_of_list_generation_as_create_list() throws Throwable {
		moveTaskListGenerationPage.selectCreate();
		Thread.sleep(1000);
	}

	@When("^I enter tagId$")
	public void i_enter_tagId() throws Throwable {
		// String fromLocation = locationDB.getLocationResv("UnLocked");
		// System.out.println("from loc" + fromLocation);
		// context.setLocation(fromLocation);
		context.getTagId();
		moveTaskListGenerationPage.enterTagID(context.getTagId());
		// moveTaskListGenerationPage.enterLocation(context.getLocation());
		Thread.sleep(1000);
	}
	
	@When("^I enter taskId$")
	public void i_enter_taskId() throws Throwable {
		// String fromLocation = locationDB.getLocationResv("UnLocked");
		// System.out.println("from loc" + fromLocation);
		// context.setLocation(fromLocation);
		context.getTagId();
		moveTaskListGenerationPage.enterTaskIdInMoveTaskUpdate(context.getOrderId());
		// moveTaskListGenerationPage.enterLocation(context.getLocation());
		Thread.sleep(1000);
	}

	@When("^I proceed with execute button$")
	public void i_proceed_with_execute_button() throws Throwable {
		jDAFooter.clickExecuteButton();
		Thread.sleep(1000);
		
	}
	@When("^I proceed with add button$")
	public void i_proceed_with_add_button() throws Throwable {
		moveTaskListGenerationPage.clickAddButton();
		Thread.sleep(1000);
		
	}

	@Then("^the available list should be displayed as$")
	public void the_available_list_should_be_displayed_as() throws Throwable {
		Assert.assertTrue("Available List is not displayed.", moveTaskListGenerationPage.isListAvailable());
		Thread.sleep(1000);
	}

	@When("^I select the record from available list$")
	public void i_select_the_record_from_available_list() throws Throwable {
		moveTaskListGenerationPage.getLatestTask();	
		Thread.sleep(1000);
		moveTaskListGenerationPage.clickAddButton();
		Thread.sleep(1000);
//		moveTaskListGenerationPage.clickAddReplenishButton();
		
		logger.debug("Record added to generate list");
		Thread.sleep(1000);
	}

	@Then("^the record should be added in the selected list for task$")
	public void the_record_should_be_added_in_the_selected_list_for_task() throws Throwable {
		moveTaskListGenerationPage.navigateToSelectedTab();
		Assert.assertTrue("Move task List not generated.", moveTaskListGenerationPage.isSelectedListAvailable());
		Thread.sleep(1000);
		
	}

	@Then("^I should see the confirmation for the number of location checked$")
	public void i_should_see_the_confirmation_for_the_number_of_location_checked() throws Throwable {
		String getSelectedListConfirmationText = moveTaskListGenerationPage.getSelectedListConfirmationText();
		Assert.assertTrue("Move task List not generated as expected. " + getSelectedListConfirmationText,
				getSelectedListConfirmationText.contains("You have selected:"));
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for the number of items checked$")
	public void i_should_see_the_confirmation_for_the_number_of_items_checked() throws Throwable {
		String getSelectedListConfirmationText = moveTaskListGenerationPage.getSelectedListConfirmationText();
		ArrayList<String> failureList = new ArrayList<String>();
		if (getSelectedListConfirmationText.contains("You have not selected any tasks")) {
			failureList.add("Stock Check list confirmation text not displayed");
		} else {
			logger.debug("Stock Check list Confirmation : " + getSelectedListConfirmationText);
		}
		Assert.assertTrue("Stock Check List not generated as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		Thread.sleep(1000);
	}

	@When("^I proceed to display the list$")
	public void i_proceed_to_display_the_list() throws Throwable {
		moveTaskListGenerationPage.selectDisplay();
		Thread.sleep(1000);
	}

	@When("^I proceed to generate stock check list$")
	public void i_proceed_to_generate_stock_check_list() throws Throwable {
		jDAFooter.clickDoneButton();
		Thread.sleep(1000);
	}

	@Then("^I should see the created list as$")
	public void i_should_see_the_created_list_as() throws Throwable {
		Assert.assertTrue("Stock Check List not generated as expected.",
				moveTaskListGenerationPage.isListIdPopupDisplayed());
		Thread.sleep(1000);
	}

	@Given("^I enter not enough option and enter tag id for the task \"([^\"]*)\"$")
	public void i_enter_not_enough_option(String taskId) throws Throwable {
		moveTaskQueryPage.NotEnough();
		context.setTaskId(taskId);
		context.setTaskId(taskId);
		// String tag=moveTaskDB.getTag(context.getTaskId());
		// context.setTagId(tag);
		moveTaskQueryPage.enterTag(context.getTagId());
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF11();

		Assert.assertTrue("error now completing task not displayed as expected",
				moveTaskQueryPage.isnowCompletingTaskAvailable());
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressEnter();

	}

	@Given("^I enter damaged stock option and enter tag id for the task \"([^\"]*)\"$")
	public void i_enter_damaged_stock_option(String taskId) throws Throwable {
		moveTaskQueryPage.DamagedStock();
		context.setTaskId(taskId);
		context.setTaskId(taskId);
		String tag = moveTaskDB.getTag(context.getTaskId());
		context.setTagId(tag);
		moveTaskQueryPage.enterTag(tag);
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF11();
		Assert.assertTrue("error now completing task not displayed as expected",
				moveTaskQueryPage.isnowCompletingTaskAvailable());
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressEnter();
	}

	@Given("^I enter split pick option and enter tag id for the task \"([^\"]*)\"$")
	public void i_enter_split_pick_option(String taskId) throws Throwable {
		moveTaskQueryPage.SplitPick();
		context.setTaskId(taskId);
		context.setTaskId(taskId);
		String tag = moveTaskDB.getTag(context.getTaskId());
		context.setTagId(tag);
		moveTaskQueryPage.enterTag(tag);
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF11();
		Assert.assertTrue("error now completing task not displayed as expected",
				moveTaskQueryPage.isnowCompletingTaskAvailable());
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressEnter();
	}

	@Given("^I enter checkString and TagId for the task \"([^\"]*)\"$")
	public void i_enter_checkString_and_TagId(String taskId) throws Throwable {
		// context.setTaskId(taskId);
		// String tag=moveTaskDB.getTag(context.getTaskId());
		// context.setTagId(tag);
		// moveTaskQueryPage.enterTag(tag);
		// moveTaskQueryPage.PressEnter();
		String toLocation = moveTaskQueryPage.getLocation();
		context.setlocationID(toLocation);
		String checkString = locationDB.getCheckString(toLocation);
		moveTaskQueryPage.enterCheckString(checkString);
		// moveTaskQueryPage.enterLocation(toLocation);
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.enterTag(context.getTagId());
		moveTaskQueryPage.PressEnter();
		hooks.logoutPutty();
		//
	}

	@Given("^I validate the location in inventory$")
	public void I_validate_the_location_in_inventory() throws Throwable {
		String location = inventoryDB.getLocation(context.getTagId());
		Assert.assertEquals("location is not displayed as expected", context.getlocationID(), location);

	}

	@Given("^I have the task id$")
	public void i_have_task_id() throws Throwable {
		
//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		
//		systemAllocationStepDefs.the_location_should_be_changed_to_for_the_sku("UnLocked","21036013");
		orderHeaderMaintenanceStepDefs.the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer_for_consolidation("Released","RDC","3942");
		orderHeaderMaintenanceStepDefs.the_order_should_have_delivery_details();
		orderLineMaintenanceStepDefs.the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items_from_order_line_table();
		orderHeaderMaintenanceStepDefs.the_order_should_be_in_status("Allocated");
		orderLineMaintenanceStepDefs.the_quantity_tasked_should_be_updated_for_each_order_lines();
		orderHeaderMaintenanceStepDefs.the_order_id_should_have_ship_dock_and_consignment();
		the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table_and_location_in_status("Locked");
//		puttyFunctionsStepDefs.i_login_as_warehouse_user_in_putty();
//		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
//		storeTrackingOrderPickingStepDefs.i_select_picking_with_container_pick();
//		storeTrackingOrderPickingStepDefs.i_should_be_directed_to_pick_entry_page();
//		storeTrackingOrderPickingStepDefs.i_pick_all_the_list_ids_for_the_store_tracking_order_for_consolidation();
	}
	
	
	@Given("^I have the order id for consolidation$")
	public void i_have_order_id_for_consolidation() throws Throwable {
		
//		systemAllocationStepDefs.the_location_should_be_changed_to_for_the_sku("UnLocked","21036013");
		orderHeaderMaintenanceStepDefs.the_sto_should_be_status_type_order_details_in_the_order_header_table_for_customer_for_container_check("Released","RDC","3942");
		orderHeaderMaintenanceStepDefs.the_order_should_have_delivery_details();
		orderLineMaintenanceStepDefs.the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items_from_order_line_table();
		orderHeaderMaintenanceStepDefs.the_order_should_be_in_status("Allocated");
		orderLineMaintenanceStepDefs.the_quantity_tasked_should_be_updated_for_each_order_lines();
		orderHeaderMaintenanceStepDefs.the_order_id_should_have_ship_dock_and_consignment();
		the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table();
//		puttyFunctionsStepDefs.i_login_as_warehouse_user_in_putty();
//		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
//		storeTrackingOrderPickingStepDefs.i_select_picking_with_container_pick();
//		storeTrackingOrderPickingStepDefs.i_should_be_directed_to_pick_entry_page();
//		storeTrackingOrderPickingStepDefs.i_pick_all_the_list_ids_for_the_store_tracking_order_for_container();
		
	}
	
	
	@When("^I give pallet id and container id where task \"([^\"]*)\" of different consignments$")
	public void i_give_pallet_id_and_container_id_where_task_of_different_consignments(String taskId) throws Throwable {
		// context.getOrderId();
		// System.out.println("taskId =" +context.getOrderId());

		String Pallet = moveTaskDB.getPalletId(context.getTaskId());
		context.setPalletID(Pallet);
		moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.nextTab();
		context.setTaskId(taskId);
		String Container = moveTaskDB.getContainerid(context.getTaskId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		// if(Container!=moveTaskDB.getContainerid(context.getTaskId()))
		// {
		// moveTaskQueryPage.enterContainerID(Container);
		// }
		moveTaskQueryPage.PressEnter();
	}
	@When("^I give pallet id and container id where task of different consignments$")
	public void i_give_pallet_id_and_container_id_where_task_of_different_consignments() throws Throwable {
		// context.getOrderId();
		// System.out.println("taskId =" +context.getOrderId());
		/*
		String Pallet = moveTaskDB.getPalletId(context.getOrderId());
		context.setPalletID(Pallet);
		moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.nextTab();

		String Container = moveTaskDB.getContainerid(context.getOrderId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		moveTaskQueryPage.PressEnter();*//*changes*/
		
		moveTaskQueryPage.nextTab();
		String Pallet = moveTaskDB.getPalletId(context.getOrderId());
		context.setPalletID(Pallet);
		moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.PressEnter();
	}

	@When("^I give pallet id where task \"([^\"]*)\" and container id where task \"([^\"]*)\" of same consignments$")
	public void i_give_pallet_id_where_task_and_container_id_where_task_of_same_consignments(String taskId, String task)
			throws Throwable {
		// context.getOrderId();
		// System.out.println("taskId =" +context.getOrderId());
		System.out.println("pallsssss task" + context.getTaskId());
		context.setTaskId(taskId);
		String Pallet = moveTaskDB.getPalletForTask(context.getTaskId());
		context.setPalletID(Pallet);
		moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.nextTab();
	context.setTask(task);
		System.out.println("cont task" + context.getTask());
		String Container = moveTaskDB.getContainerid(context.getTask());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		// if(Container!=moveTaskDB.getContainerid(context.getTaskId()))
		// {
		// moveTaskQueryPage.enterContainerID(Container);
		// }
		moveTaskQueryPage.PressEnter();
	}
	@When("^I give pallet id  and container id of different task of same consignments$")
	public void i_give_pallet_id_and_container_id_of_different_task_of_same_consignments()
			throws Throwable {
		// context.getOrderId();
		// System.out.println("taskId =" +context.getOrderId());
		System.out.println("pallsssss task" + context.getOrderId());
//		context.setTaskId(taskId);
		String Pallet = moveTaskDB.getPalletForTask(context.getOrderId());
		context.setPalletID(Pallet);
		moveTaskQueryPage.enterPallet(Pallet);
		moveTaskQueryPage.nextTab();
//		context.setTask(task);
		System.out.println("cont task" + context.getTaskId());
		String Container = moveTaskDB.getContainerid(context.getTaskId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		// if(Container!=moveTaskDB.getContainerid(context.getTaskId()))
		// {
		// moveTaskQueryPage.enterContainerID(Container);
		// }
		moveTaskQueryPage.PressEnter();
	}
	@When("^I give pallet id  and container id where task \"([^\"]*)\" of same consignments$")
	public void i_give_pallet_id_and_container_id_where_task_of_same_consignments(String task)
			throws Throwable {
		
		System.out.println("pallsssss task" + context.getOrderId());
		String Pallet = moveTaskDB.getPalletForTask(context.getOrderId());
		context.setPalletID(Pallet);
		moveTaskQueryPage.nextTab();/*newly added-sagorika*/
		moveTaskQueryPage.enterPallet(Pallet);
//		moveTaskQueryPage.nextTab();
//		moveTaskQueryPage.enterYforPallet(); /*not reqd as per new screen in putty---*/
	moveTaskQueryPage.PressEnter();
	
	/*----not reqd as per new screen in putty---*/
	/*			context.setTask(task);
		System.out.println("cont task" + context.getTask());
		String Container = moveTaskDB.getContainerid(context.getTask());
		context.setContainerId(Container);
		moveTaskQueryPage.enterContainerID(Container);
		// if(Container!=moveTaskDB.getContainerid(context.getTaskId()))
		// {
		// moveTaskQueryPage.enterContainerID(Container);
		// }
		moveTaskQueryPage.PressEnter();*/
	}
	


	@When("^I enter the container id with task \"([^\"]*)\" for merging$")
	public void i_enter_the_from_pallet_id_and_container_id_for_merging(String taskId) throws Throwable {
		// context.setTaskId(taskId);
		context.setTaskId(taskId);
		String Container = moveTaskDB.getContainer(context.getTaskId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterFrPl(Container);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterFromContainerID(Container);

		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.enterPrinter1();

	}
	@When("^I enter the container id with task for merging$")
	public void i_enter_the_container_id_with_task_for_merging() throws Throwable {
		
		moveTaskQueryPage.nextTab();
		String Container = moveTaskDB.getContainer(context.getOrderId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterFrPl(Container);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterFromContainerID(Container);

		moveTaskQueryPage.PressEnter();
		Thread.sleep(5000);
		i_validate_the_error_Consolidation_not_allowed();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.PressEnter();
		Thread.sleep(5000);
//		moveTaskQueryPage.enterPrinter1();	/*--as per dev006 not reqd--*/
		moveTaskQueryPage.PressF10();
		Thread.sleep(1000);
		hooks.logoutPutty();

	}

	@When("^I enter the container id with task \"([^\"]*)\" for merging and give N$")
	public void i_enter_the_from_pallet_id_and_container_id_for_merging_and_give_N(String taskId) throws Throwable {
		// context.setTaskId(taskId);
		 context.setTask(taskId);
		String Container = moveTaskDB.getContainer(context.getTask());
		context.setContainerId(Container);
		moveTaskQueryPage.enterFrPl(Container);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterFromContainerID(Container);
//		moveTaskQueryPage.enterNforCombine(); /*--not reqd as per neww screen*/

		moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.enterNforCombine();
		// Thread.sleep(1000);
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF11();
		moveTaskQueryPage.enterPrinter1();
		hooks.logoutPutty();

	}
	@When("^I enter the container id with task for merging and give N$")
	public void i_enter_the_container_id_for_merging_and_give_N(String taskId) throws Throwable {
		// context.setTaskId(taskId);
		// context.setTaskId(taskId);
		String Container = moveTaskDB.getContainer(context.getTask());
		context.setContainerId(Container);
		moveTaskQueryPage.enterFrPl(Container);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterFromContainerID(Container);
		moveTaskQueryPage.enterNforCombine();

		moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.enterNforCombine();
		// Thread.sleep(1000);
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF11();
		moveTaskQueryPage.enterPrinter1();
		hooks.logoutPutty();

	}

	@When("^I enter the container id with task \"([^\"]*)\" for merging and give Y$")
	public void i_enter_container_id_with_task_for_merging_and_give_Y(String taskId) throws Throwable {
		// context.setTaskId(taskId);
		context.setTaskId(taskId);
		String Container = moveTaskDB.getContainer(context.getTaskId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterFrPl(Container);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterFromContainerID(Container);
//		moveTaskQueryPage.enterYforPallet();

		moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.enterYforCombine();
		// Thread.sleep(1000);
		// moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF10();
//		moveTaskQueryPage.enterPrinter1();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.PressEnter();
//		moveTaskQueryPage.enterPrinter1ForConsolidation();
		hooks.logoutPutty();

	}
	@When("^I enter the container id with task for merging and give Y$")
	public void i_enter_the_container_id_for_merging_and_give_Y() throws Throwable {
		// context.setTaskId(taskId);
//		context.setTaskId(taskId);
		String Container = moveTaskDB.getContainer(context.getTaskId());
		context.setContainerId(Container);
		moveTaskQueryPage.enterFrPl(Container);
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterFromContainerID(Container);
		moveTaskQueryPage.enterYforPallet();

		moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.PressEnter();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.nextTab();
		// moveTaskQueryPage.enterYforCombine();
		// Thread.sleep(1000);
		// moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.enterPrinter1();
		hooks.logoutPutty();

	}
//	@When("^I enter the container id with task for merging and give Y$")
//	public void i_enter_the_from_pallet_id_and_container_id_for_merging_and_give_Y() throws Throwable {
//		// context.setTaskId(taskId);
////		context.setTaskId(taskId);
//		String Container = moveTaskDB.getContainer(context.getTaskId());
//		context.setContainerId(Container);
//		moveTaskQueryPage.enterFrPl(Container);
//		moveTaskQueryPage.nextTab();
//		moveTaskQueryPage.enterFromContainerID(Container);
//		moveTaskQueryPage.enterYforPallet();
//
//		moveTaskQueryPage.PressEnter();
//		moveTaskQueryPage.PressF10();
//		moveTaskQueryPage.enterPrinter1();
//		hooks.logoutPutty();
//
//	}

	@Then("^I validate the error Consolidation not allowed$")
	public void i_validate_the_error_Consolidation_not_allowed() throws Throwable {
		Assert.assertTrue("error Consolidation not allowed is not displayed as expected",
				moveTaskQueryPage.isconsolidationAllowed());
//		hooks.logoutPutty();
	}

	@When("^I enter the task id \"([^\"]*)\" and execute$")
	public void i_enter_the_task_id_and_execute(String taskId) throws Throwable {

		jDAFooter.clickQueryButton();
		context.setTaskId(taskId);
		moveTaskQueryPage.enterTaskId(context.getTaskId());
		jDAFooter.clickExecuteButton();
	}
	
	@When("^I enter the task id and execute$")
	public void i_enter_the_task_id_and_execute() throws Throwable {

		jDAFooter.clickQueryButton();
//		context.setTaskId(taskId);
		moveTaskQueryPage.enterTaskId(context.getTaskId());
		jDAFooter.clickExecuteButton();
	}

	@When("^I take the corresponding pallet id for the given task \"([^\"]*)\", query and check the status$")
	public void i_take_the_corresponding_pallet_id_for_the_given_task_and_query_and_check_the_status(String taskId)
			throws Throwable {
		context.setTaskId(taskId);
		moveTaskQueryPage.navigateToMiscellaneousTab();
		String Pallet = moveTaskDB.getPallet(context.getTaskId());
		System.out.println("pallet=" + Pallet);
		jDAFooter.clickQueryButton();
		moveTaskQueryPage.enterPalletId(Pallet);
		jDAFooter.clickExecuteButton();
		moveTaskQueryPage.selectTaskID();
		moveTaskQueryPage.getcontainer();
		moveTaskQueryPage.clickGeneralTab();
		moveTaskQueryPage.getTaskId();
		moveTaskQueryPage.getLocation1();
		moveTaskQueryPage.getList();
	}

	@When("^I take the corresponding pallet id for the given task query and check the status$")
	public void i_take_the_corresponding_pallet_id_for_the_given_task_and_query_and_check_the_status()
			throws Throwable {
//		context.setTaskId(taskId);
		moveTaskQueryPage.navigateToMiscellaneousTab();
		String Pallet = moveTaskDB.getPallet(context.getTaskId());
		System.out.println("pallet=" + Pallet);
		jDAFooter.clickQueryButton();
		moveTaskQueryPage.enterPalletId(Pallet);
		jDAFooter.clickExecuteButton();
		moveTaskQueryPage.selectTaskID();
		moveTaskQueryPage.getcontainer();
		moveTaskQueryPage.clickGeneralTab();
		moveTaskQueryPage.getTaskId();
		moveTaskQueryPage.getLocation1();
		moveTaskQueryPage.getList();
	}

	@Given("^change the status to hold$")
	public void change_the_status_to_hold() throws Throwable {
		String List = moveTaskQueryPage.getList();
		System.out.println("list" + List);
		context.setListID(List);
		moveTaskUpdatePage.enterListId(List);
		jDAFooter.clickNextButton();
		moveTaskUpdatePage.clickHoldButton();
		jDAFooter.clickNextButton();
		Assert.assertTrue("Task Selected", moveTaskUpdatePage.istaskSelected());

	}

	@Then("^the replenish pick is created$")
	public void the_replenish_pick_is_created() throws Throwable {
		i_navigate_to_the_move_task_query_page();
		jDAFooter.clickQueryButton();
		moveTaskQueryPage.enterTaskId();
		moveTaskQueryPage.enterDate();
		moveTaskQueryPage.enterTime(context.getTime());
		System.out.println("date=" + moveTaskQueryPage.getDate());
		jDAFooter.clickExecuteButton();
		
		String tagId=moveTaskQueryPage.getTagIdForReplenishTask();
		System.out.println("tag id"+tagId);
		context.setTagId(tagId);
		Assert.assertNotNull("Replenish task is not created", moveTaskQueryPage.getTagIdForReplenishTask());
		
		
//		pickFaceTableDB.deleteRecord(context.getSkuId());


	}

	@When("^I navigate to the move task query page$")
	public void i_navigate_to_the_move_task_query_page() throws Throwable {
		Thread.sleep(3000);
		jdaHomePage.navigateToMoveTaskQuery();
		Thread.sleep(3000);
	}
	
	@Given("^I enter tag id and enter checkstring$")
	public void i_enter_tag_id() throws Throwable {
		moveTaskQueryPage.nextTab();
		moveTaskQueryPage.enterTagId(context.getTagId());
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressF10();
		moveTaskQueryPage.PressEnter();
		moveTaskQueryPage.PressEnter();
		String toLocation = moveTaskQueryPage.getLocation();
		context.setlocationID(toLocation);
		String checkString = locationDB.getCheckString(toLocation);
		moveTaskQueryPage.enterCheckString(checkString);
		moveTaskQueryPage.PressEnter();
		
	}


	 
}
