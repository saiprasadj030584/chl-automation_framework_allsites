package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JdaHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class JDAHomeStepDefs {
	private final JdaHomePage jdaHomePage;

	@Inject
	public JDAHomeStepDefs(JdaHomePage jdaHomePage) {
		this.jdaHomePage = jdaHomePage;
	}

	@When("^I navigate to order header$")
	public void i_navigate_to_order_header() throws Throwable {
		jdaHomePage.navigateToOrderHeader();
	}

	@Given("^I am on supplier SKU maintenance page$")
	public void i_am_on_supplier_SKU_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.clickSupplierSKU();
	}

	@When("^I navigate to address maintenance page$")
	public void i_navigate_to_address_maintenance_page() throws Throwable {
		Thread.sleep(10000);
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverGeneral();
		jdaHomePage.hoverSetup();
		jdaHomePage.clickAddress();

	}

}
