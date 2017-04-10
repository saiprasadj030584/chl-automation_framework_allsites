package com.jda.wms.stepdefs.foods;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.OrderHeaderPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepDefs {
	private final OrderHeaderPage orderHeaderPage;

	@Inject
	public OrderHeaderStepDefs(OrderHeaderPage orderHeaderPage) {
		this.orderHeaderPage = orderHeaderPage;
	}

	@When("^I navigate to order header$")
	public void i_navigate_to_order_header() throws FindFailed, InterruptedException {
		orderHeaderPage.navigateToOrderHeader();
		Thread.sleep(3000);
	}

	@When("^I search order number \"(.*?)\"$")
	public void i_search_order_number(String orderNumber) throws FindFailed, InterruptedException {
		orderHeaderPage.enterOrderNo(orderNumber);
		Thread.sleep(1000);
	}

	@When("^I select the SKU line$")
	public void i_select_the_SKU_line() throws FindFailed, InterruptedException {
		orderHeaderPage.navigateToOrderLineList();
	}

	@When("^I allocate the product$")
	public void i_allocate_the_product() throws FindFailed, InterruptedException {
		orderHeaderPage.allocateOrder();
	}

	@Then("^the order status should be updated as \"(.*?)\"$")
	public void the_order_status_should_updated_as(String orderStatus) throws Throwable {
		Assert.assertEquals("Order status does not match", "Allocated", orderHeaderPage.getOrderStatus());
	}
}