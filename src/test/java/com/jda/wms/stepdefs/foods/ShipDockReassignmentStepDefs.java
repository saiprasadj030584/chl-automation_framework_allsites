package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.ShipDockReassignmentPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShipDockReassignmentStepDefs {
	private ShipDockReassignmentPage shipDockReassignmentPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;

	public void ShipDockReassignmentStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			ShipDockReassignmentPage shipDockReassignmentPage) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.shipDockReassignmentPage = shipDockReassignmentPage;
	}	
		
		@When("^I navigate to order header maintenance table$")
		public void i_navigate_to_order_header_maintenance_table() throws Throwable {
			jdaHomeStepDefs.i_navigate_to_order_header();
		}
			
			
			@When("^I search the order Id \"([^\"]*)\"$")
			public void i_search_the_order_Id(String orderId) throws Throwable {
				jdaFooter.clickQueryButton();
				orderHeaderMaintenancePage.enterOrderNo(orderId);
				jdaFooter.clickExecuteButton();
			}
			
			@Then("^the ship dock should be displayed as \"([^\"]*)\"$")
			public void the_ship_dock_should_be_displayed_as(String dEFAULTSD) throws Throwable {
				String shipDock =orderHeaderMaintenancePage.getshipDock();
				Assert.assertEquals("Ship Dock is not displayed as expected", dEFAULTSD,
						orderHeaderMaintenancePage.getshipDock());
			}
			@Then("^I enter the site id \"([^\"]*)\" and order id \"([^\"]*)\"$")
			public void i_enter_the_site_id_and_order_id(String siteId, String orderId) throws Throwable {
				shipDockReassignmentPage.enterSiteID(siteId);
				shipDockReassignmentPage.enterOrderNo(orderId);
				jdaFooter.clickNextButton();
				
			}
			@Then("^I should find one record$")
			public void i_should_find_one_record() throws Throwable {
				if (shipDockReassignmentPage.is1RecordDisplayed())
					jdaFooter.clickNextButton();
			}
			@Then("^I enter the \"([^\"]*)\" in new shipdock reassignment$")
			public void i_enter_the_in_new_shipdock_reassignment(String shipDockName) throws Throwable {
				shipDockReassignmentPage.enterNewShipDockName(shipDockName);
				jdaFooter.clickNextButton();
			
			}
			@Then("^the shipdock should be displayed as \"([^\"]*)\"$")
			public void the_shipdock_should_be_displayed_as(String shipDockName, String orderId) throws Throwable {
				jdaHomeStepDefs.i_navigate_to_order_header();
				jdaFooter.clickQueryButton();
				orderHeaderMaintenancePage.enterOrderNo(orderId);
				jdaFooter.clickExecuteButton();
				Assert.assertEquals("Ship Dock is not displayed as expected", shipDockName ,
						orderHeaderMaintenancePage.getshipDock());
			}
}
				
				
						
			
			

