package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.ShipDockReassignmentPage;

import cucumber.api.java.en.When;

public class ShipDockReassignmentStepDefs {
	private ShipDockReassignmentPage shipDockReassignmentPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;

	@Inject
	public void ShipDockReassignmentStepDefs(ShipDockReassignmentPage shipDockReassignmentPage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs,OrderHeaderMaintenancePage orderHeaderMaintenancePage) {

		this.shipDockReassignmentPage = shipDockReassignmentPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;

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
	}