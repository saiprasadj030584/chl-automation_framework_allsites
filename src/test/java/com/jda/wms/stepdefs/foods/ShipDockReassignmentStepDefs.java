package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
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

	@Inject
	public ShipDockReassignmentStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
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
		context.setOrderNo(orderId);
		orderHeaderMaintenancePage.enterOrderNo(orderId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the ship dock should be displayed as \"([^\"]*)\"$")
	public void the_ship_dock_should_be_displayed_as(String DEFAULTSD) throws Throwable {
		Assert.assertEquals("Ship Dock is not displayed as expected", DEFAULTSD,
				orderHeaderMaintenancePage.getshipDock());
	}

	@When("^I enter the from site id \"([^\"]*)\" and order id \"([^\"]*)\"$")
	public void i_enter_the_from_site_id_and_order_id(String siteId, String orderId) throws Throwable {
		shipDockReassignmentPage.enterSiteID(siteId);
		shipDockReassignmentPage.enterOrderNo(orderId);
	}

	@Then("^the order list should be displayed$")
	public void the_order_list_should_be_displayed() throws Throwable {
		Assert.assertTrue("1 Record is not displayed.",shipDockReassignmentPage.is1RecordDisplayed());
		Thread.sleep(1000);
			
	}

	@When("^I enter the new ship dock \"([^\"]*)\"$")
	public void i_enter_the_new_ship_dock(String shipDockName) throws Throwable {
		context.setNewShipDock(shipDockName);
		shipDockReassignmentPage.enterNewShipDockName(shipDockName);
		jdaFooter.clickDoneButton();
	}

	@Then("^the ship dock should be updated for an order$")
	public void the_ship_dock_should_be_updated_for_an_order() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(context.getorderId());
		jdaFooter.clickExecuteButton();
		Assert.assertEquals("Ship Dock is not displayed as expected", context.getNewShipDock(),
				orderHeaderMaintenancePage.getshipDock());
	}
}
