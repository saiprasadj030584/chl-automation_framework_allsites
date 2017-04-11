package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.OrderHeaderPage;
import com.jda.wms.pages.foods.OrderLineMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepDefs {
	private final OrderHeaderPage orderHeaderPage;

	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private final OrderLineMaintenancePage orderLineMaintenancePage;
	private final JdaHomePage jdaHomePage;

	@Inject
	public OrderHeaderStepDefs(OrderHeaderPage orderHeaderPage, JdaHomePage jdaHomePage,
			OrderHeaderMaintenancePage orderHeaderMaintenancePage, OrderLineMaintenancePage orderLineMaintenancePage) {
		this.orderHeaderPage = orderHeaderPage;
		this.jdaHomePage = jdaHomePage;
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.orderLineMaintenancePage = orderLineMaintenancePage;
	}

	@When("^I navigate to order header$")
	public void i_navigate_to_order_header() throws Throwable {
		jdaHomePage.navigateToOrderHeader();
	}

	@When("^I navigate to orderline for the order number \"(.*?)\"$")
	public void i_navigate_to_orderline_for_the_order_number(String orderNumber) throws Throwable {
		orderHeaderMaintenancePage.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderNumber);
		orderHeaderMaintenancePage.clickExecuteButton();
		orderHeaderMaintenancePage.clickLinesButton();
	}

	@When("^I select the SKU line$")
	public void i_select_the_SKU_line() throws Throwable {
		orderHeaderPage.navigateToOrderLineList();
	}

	@When("^I allocate the product$")
	public void i_allocate_the_product() throws Throwable {
		orderHeaderPage.allocateOrder();
	}

	@Then("^the order status should be updated as \"(.*?)\"$")
	public void the_order_status_should_updated_as(String orderStatus) throws Throwable {
		Assert.assertEquals("Order status does not match.", orderStatus, orderHeaderPage.getOrderStatus());
	}
}