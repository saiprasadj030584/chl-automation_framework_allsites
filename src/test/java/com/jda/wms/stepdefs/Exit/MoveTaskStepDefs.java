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
			
			OrderLineMaintenanceStepDefs  orderLineMaintenanceStepDefs) {
	
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
	@And ("^Validation of List Id generated with prefix as MANH$")
	public void Validation_of_List_Id_generated_with_prefic_as_MANH()throws Throwable{
			
			moveTaskManagementPage.validateListID();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as MANH", "MANH", prefixlist);
			logger.debug("List Id generated with prefix as MANH is : " + actuallist);
			System.out.println("List Id generated with prefix as MANH is : " + actuallist);
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
			Assert.assertEquals("List Id generated with prefix as IDT", "IDT", prefixlist);
			logger.debug("List Id generated with prefix as IDT is : " + actuallist);
			System.out.println("List Id generated with prefix as IDT is : " + actuallist);
		}
	
	@And ("^Validation of List Id generated with prefix as IDT for hanging$")
	public void Validation_of_List_Id_generated_with_prefic_as_IDT_for_hanging()throws Throwable{
			
			moveTaskManagementPage.validateListIDforIDT();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as IDT", "IDTH", prefixlist);
			logger.debug("List Id generated with prefix as IDT is : " + actuallist);
			System.out.println("List Id generated with prefix as IDT is : " + actuallist);
		}
	
	@And ("^Validation of List Id generated with prefix as IDT for hanging$")
	public void Validation_of_List_Id_generated_with_prefic_as_IDT_for_hanging()throws Throwable{
			
			moveTaskManagementPage.validateListIDforIDT();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as IDT", "IDTH", prefixlist);
			logger.debug("List Id generated with prefix as IDT is : " + actuallist);
			System.out.println("List Id generated with prefix as IDT is : " + actuallist);
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




	 
}
