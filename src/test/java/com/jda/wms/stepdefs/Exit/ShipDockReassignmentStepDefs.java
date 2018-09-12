package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.DeleteDataFromDB;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.dataload.exit.InsertDataIntoDB;
import com.jda.wms.dataload.exit.SelectDataFromDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.pages.Exit.ShipDockReassignmentPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShipDockReassignmentStepDefs {
	private ShipDockReassignmentPage shipDockReassignmentPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private JDALoginStepDefs jdaLoginStepDefs;
	private SystemAllocationStepDefs systemAllocationStepDefs;
	private ClusteringStepDefs clusteringStepDefs;
	private OrderPreparationStepDefs orderPreparationStepDefs;
	private DataSetupRunner dataSetupRunner;
	private GetTCData getTCData;
	private OrderHeaderDB orderHeaderDB;

	@Inject
	public ShipDockReassignmentStepDefs(
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			ShipDockReassignmentPage shipDockReassignmentPage,InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB,JDALoginStepDefs jdaLoginStepDefs,SystemAllocationStepDefs systemAllocationStepDefs,ClusteringStepDefs clusteringStepDefs,OrderPreparationStepDefs orderPreparationStepDefs,DataSetupRunner dataSetupRunner,GetTCData getTCData,OrderHeaderDB orderHeaderDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.shipDockReassignmentPage = shipDockReassignmentPage;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.systemAllocationStepDefs = systemAllocationStepDefs;
		this.clusteringStepDefs = clusteringStepDefs;
		this.orderPreparationStepDefs = orderPreparationStepDefs;
		this.dataSetupRunner = dataSetupRunner;
		this.getTCData = getTCData;
		this.orderHeaderDB=orderHeaderDB;
	}

	@When("^I enter the from site id \"([^\"]*)\" and order id of type \"([^\"]*)\" for customer \"([^\"]*)\"$")
	public void i_enter_the_from_site_id_and_order_id_of_type(String siteId,String type,String customer) throws Throwable {
		
		// ------------------Data Set up Modified---------------
				context.setStoType(type);
				context.setCustomer(customer);
				dataSetupRunner.insertOrderData();
				String orderId = getTCData.getSto();
				System.out.println("New Order ID " + orderId);
				String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
				System.out.println("status"+orderstatus);
				jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
				if(orderstatus.equals("Released"))
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
				// ------------------Data Set up Modified---------------
//		// ------------Data Setup-----------
//				deleteDataFromDB.deleteOrderHeader(orderId);
//				insertDataIntoDB.insertOrderHeader(orderId,"STR","0437");
//				insertDataIntoDB.insertOrderLine(orderId);
//				Assert.assertTrue("Test Data not available - Issue in Data loading",
//						selectDataFromDB.isPreAdviceRecordExists(orderId));
//				jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
//				systemAllocationStepDefs.i_system_allocate_the_order();
//				clusteringStepDefs.i_create_list_ids_manually_in_clustering();
//				orderPreparationStepDefs.i_create_consignment();
//				
//				// ------------Data Setup-----------
				jdaHomeStepDefs.i_navigate_to_ship_dock_reassignment();
		shipDockReassignmentPage.enterSiteID(siteId);
		shipDockReassignmentPage.enterOrderNo(orderId);
		context.setOrderId(orderId);
	}

	@Then("^the order list should be displayed$")
	public void the_order_list_should_be_displayed() throws Throwable {
		Assert.assertTrue("order list is not displayed.", shipDockReassignmentPage.is1RecordDisplayed());
		Thread.sleep(1000);

	}

	@When("^I enter the new ship dock \"([^\"]*)\"$")
	public void i_enter_the_new_ship_dock(String shipDockName) throws Throwable {
		context.setNewShipDock(shipDockName);
		shipDockReassignmentPage.enterNewShipDockName(shipDockName);
		jdaFooter.clickDoneButton();
	}

}
