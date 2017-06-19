package com.jda.wms.stepdefs.foods;

import java.util.List;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.foods.DeleteDataFromDB;
import com.jda.wms.dataload.foods.InsertDataIntoDB;
import com.jda.wms.dataload.foods.SelectDataFromDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.VehicleUnloadingPage;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;
import com.jda.wms.stepdefs.rdt.StockTransferOrderVehicleLoadingStepDefs;
import com.jda.wms.stepdefs.rdt.StoreTrackingOrderPickingStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class VehicleUnloadingStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	private final VehicleUnloadingPage vehicleUnloadingPage;
	private final Context context;
	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;
	private OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepDefs;
	private MoveTaskStepDefs moveTaskStepDefs;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private StockTransferOrderVehicleLoadingStepDefs stockTransferOrderVehicleLoadingStepDefs;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDALoginStepDefs jdaLoginStepDefs;
	private SystemAllocationStepDefs systemAllocationStepDefs;
	private ClusteringStepDefs clusteringStepDefs;
	private OrderPreparationStepDefs orderPreparationStepDefs;

	@Inject
	public VehicleUnloadingStepDefs(JdaHomePage jdaHomePage, JDAFooter jdafooter,VehicleUnloadingPage vehicleUnloadingPage,Context context,StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs,OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepDefs,MoveTaskStepDefs moveTaskStepDefs,PuttyFunctionsStepDefs puttyFunctionsStepDefs,PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs,StockTransferOrderVehicleLoadingStepDefs stockTransferOrderVehicleLoadingStepDefs,InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB,JDALoginStepDefs jdaLoginStepDefs) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
		this.vehicleUnloadingPage = vehicleUnloadingPage;
		this.context = context;
		this.storeTrackingOrderPickingStepDefs = storeTrackingOrderPickingStepDefs;
		this.orderHeaderMaintenanceStepDefs = orderHeaderMaintenanceStepDefs;
		this.moveTaskStepDefs =moveTaskStepDefs;
		this.puttyFunctionsStepDefs= puttyFunctionsStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.stockTransferOrderVehicleLoadingStepDefs = stockTransferOrderVehicleLoadingStepDefs;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.storeTrackingOrderPickingStepDefs = storeTrackingOrderPickingStepDefs;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
	}
	
	@Given("^the vehicle loading has been done for order \"([^\"]*)\"$")
	public void the_vehicle_loading_has_been_done_for_order(String orderId) throws Throwable {
		// ------------Data Setup-----------
				deleteDataFromDB.deleteOrderHeader(orderId);
				insertDataIntoDB.insertOrderHeader(orderId,"STR","0437");
				insertDataIntoDB.insertOrderLine(orderId);
				Assert.assertTrue("Test Data not available - Issue in Data loading",
						selectDataFromDB.isPreAdviceRecordExists(orderId));
				jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
				systemAllocationStepDefs.i_system_allocate_the_order();
				clusteringStepDefs.i_create_list_ids_manually_in_clustering();
				orderPreparationStepDefs.i_create_consignment();
				
				// ------------Data Setup-----------
		
		context.setOrderId(orderId);
		storeTrackingOrderPickingStepDefs.the_sto_of_type_should_contain_order_details_and_be_container_picked_for_customer(orderId,"STR","completely","0437");
		orderHeaderMaintenanceStepDefs.the_order_should_be_status("Ready to Load");
		storeTrackingOrderPickingStepDefs.the_trailer_and_dock_scheduling_should_be_done();
		moveTaskStepDefs.i_get_the_pallet_ids_from_the_move_task();
		moveTaskStepDefs.the_pallet_id_should_be_displayed();
		puttyFunctionsStepDefs.i_login_as_warehouse_user_in_putty();
		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
		stockTransferOrderVehicleLoadingStepDefs.i_navigate_to_load_menu();
		stockTransferOrderVehicleLoadingStepDefs.i_perform_vehicle_loading_for_all_the_pallets();
		orderHeaderMaintenanceStepDefs.the_vehicle_loading_should_be_completed_and_the__status_should_be_in_order_header("Complete");
	}
	
	@When("^I enter the siteId,consignment and pallet$")
	public void i_enter_the_siteId_consignment_and_pallet() throws Throwable {
		List<String> palletIDList = context.getPalletIDList(); 
		
		String consignment = context.getSTOConsignment();
		String palletID = palletIDList.get(0);
		
		vehicleUnloadingPage.enterSiteId();
		vehicleUnloadingPage.enterConsignment(consignment);
		vehicleUnloadingPage.enterPallet(palletID);
		jdafooter.clickNextButton();
	}
	
	@When("^I select the pallet to unload$")
	public void i_select_the_pallets_to_unload() throws Throwable {
		vehicleUnloadingPage.selectPallet();
	}

}
