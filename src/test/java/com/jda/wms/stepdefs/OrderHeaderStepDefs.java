package com.jda.wms.stepdefs;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.OrderHeaderMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepDefs {
	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAFooter jdaFooter;

	@Inject
	public OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage, JDAFooter jdaFooter) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaFooter = jdaFooter;
	}

	@When("^I navigate to orderline for the order number \"([^\"]*)\"$")
	public void i_navigate_to_orderline_for_the_order_number(String orderNumber) throws Throwable {
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderNumber);
		jdaFooter.clickExecuteButton();
		orderHeaderMaintenancePage.clickLinesButton();
	}

	@Then("^the order status should be updated as \"(.*?)\"$")
	public void the_order_status_should_updated_as(String orderStatus) throws Throwable {
		Assert.assertEquals("Order status does not match", "Allocated", orderHeaderMaintenancePage.getOrderStatus());
	}

}
