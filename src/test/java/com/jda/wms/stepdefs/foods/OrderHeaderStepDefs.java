package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepDefs {

	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;

	@Inject
	public OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
	}

	@When("^I navigate to orderline for the order number \"([^\"]*)\"$")
	public void i_navigate_to_orderline_for_the_order_number(String orderNumber) throws Throwable {
		orderHeaderMaintenancePage.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderNumber);
		orderHeaderMaintenancePage.clickExecuteButton();
		orderHeaderMaintenancePage.clickLinesButton();
	}

	@Then("^the order status should be updated as \"(.*?)\"$")
	public void the_order_status_should_updated_as(String orderStatus) throws Throwable {
		Assert.assertEquals("Order status does not match", "Allocated", orderHeaderMaintenancePage.getOrderStatus());
	}
}