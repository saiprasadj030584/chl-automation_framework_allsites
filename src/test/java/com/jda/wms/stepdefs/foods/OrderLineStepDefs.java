package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.OrderLineMaintenancePage;

import cucumber.api.java.en.When;

public class OrderLineStepDefs {
	private final OrderLineMaintenancePage orderLineMaintenancePage;

	@Inject
	public OrderLineStepDefs(OrderLineMaintenancePage orderLineMaintenancePage) {
		this.orderLineMaintenancePage = orderLineMaintenancePage;
	}

	@When("^I select the SKU line$")
	public void i_select_the_SKU_line() throws Throwable {
		orderLineMaintenancePage.navigatedOrderLinepage();
	}

	@When("^I allocate the product$")
	public void i_allocate_the_product() throws Throwable {
		orderLineMaintenancePage.allocateOrder();
	}
}
