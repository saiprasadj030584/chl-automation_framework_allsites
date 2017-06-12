package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.foods.DeleteDataFromDB;
import com.jda.wms.dataload.foods.InsertDataIntoDB;
import com.jda.wms.dataload.foods.SelectDataFromDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.ShipDockReassignmentPage;
import com.jda.wms.stepdefs.rdt.StoreTrackingOrderPickingStepDefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShipDockReassignmentStepDefs {
	private ShipDockReassignmentPage shipDockReassignmentPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;

	@Inject
	public ShipDockReassignmentStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			ShipDockReassignmentPage shipDockReassignmentPage,InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB,StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.shipDockReassignmentPage = shipDockReassignmentPage;
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.storeTrackingOrderPickingStepDefs = storeTrackingOrderPickingStepDefs;
	}

	@When("^I enter the from site id \"([^\"]*)\" and order id \"([^\"]*)\"$")
	public void i_enter_the_from_site_id_and_order_id(String siteId, String orderId) throws Throwable {
		// ------------Data Setup-----------
				deleteDataFromDB.deleteOrderHeader(orderId);
				insertDataIntoDB.insertOrderHeader(orderId,"STR","0437");
				insertDataIntoDB.insertOrderLine(orderId);
				Assert.assertTrue("Test Data not available - Issue in Data loading",
						selectDataFromDB.isPreAdviceRecordExists(orderId));
				storeTrackingOrderPickingStepDefs.i_system_allocate_the_order();
				storeTrackingOrderPickingStepDefs.i_create_list_ids_manually_in_clustering();
				storeTrackingOrderPickingStepDefs.i_create_consignment();
				
				// ------------Data Setup-----------
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
