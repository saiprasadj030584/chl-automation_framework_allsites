package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.ShipDockReassignmentPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShipDockReassignmentStepDefs {
	private ShipDockReassignmentPage shipDockReassignmentPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private Context context;

	@Inject
	public void ShipDockReassignmentStepDefs(ShipDockReassignmentPage shipDockReassignmentPage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs, OrderHeaderMaintenancePage orderHeaderMaintenancePage, Context context) {

		this.shipDockReassignmentPage = shipDockReassignmentPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.context = context;

	}

	@When("^I navigate to order header maintenance table$")
	public void i_navigate_to_order_header_maintenance_table() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_order_header();
	}

	@When("^I search the order Id \"([^\"]*)\"$")
	public void i_search_the_order_Id(String orderId) throws Throwable {
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderId);
		context.setOrderNo(orderId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the ship dock should be displayed as \"([^\"]*)\"$")
	public void the_ship_dock_should_be_displayed_as(String DEFAULTSD) throws Throwable {
		Assert.assertEquals("Ship Dock is not displayed as expected", "DEFAULTSD",
				orderHeaderMaintenancePage.getShipDock());

	}

	@Then("^I enter the site id \"([^\"]*)\" and order id \"([^\"]*)\"$")
	public void i_enter_the_site_id_and_order_id(String siteId, String orderId) throws Throwable {
		shipDockReassignmentPage.enterSiteID(siteId);
		shipDockReassignmentPage.enterOrderNo(orderId);
		jdaFooter.clickNextButton();
	}

	@Then("^I should find one record$")
	public void i_should_find_one_record() throws Throwable {
		Assert.assertTrue("1 record message exists", shipDockReassignmentPage.is1Record());
		jdaFooter.clickNextButton();

	}

	@Then("^I enter the \"([^\"]*)\" in new shipdock reassignment$")
	public void i_enter_the_in_new_shipdock_reassignment(String shipdockName) throws Throwable {
		context.setShipDock(shipdockName);
		shipDockReassignmentPage.enterNewShopDock(shipdockName);
		jdaFooter.clickDoneButton();
	}

	@Then("^the shipdock should be displayed as \"([^\"]*)\"$")
	public void the_shipdock_should_be_displayed_as(String arg1) throws Throwable {
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(context.getorderId());
		jdaFooter.clickExecuteButton();
		String shipdockName = orderHeaderMaintenancePage.getShipDock();
		Assert.assertEquals("Ship Dock  is not displayed as expected. Expected [" + context.getShipDock()
				+ "] but was [" + shipdockName + "]", context.getShipDock(), shipdockName);
	}
}
