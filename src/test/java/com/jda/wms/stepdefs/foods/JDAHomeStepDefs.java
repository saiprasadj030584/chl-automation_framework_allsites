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

	@When("^I navigate to SKU maintenance page$")
	public void i_navigate_to_SKU_maintenance_page() throws Throwable {
		jdaHomePage.navigateToSKUMaintanence();
	}

	@Given("^I am on supplier SKU maintenance page$")
	public void i_am_on_supplier_SKU_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.clickSupplierSKU();
	}

	@When("^I navigate to address maintenance page$")
	public void i_navigate_to_address_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverGeneral();
		jdaHomePage.hoverSetup();
		jdaHomePage.clickAddress();
		Thread.sleep(5000);
	}

	@Given("^I am on pack config maintenance page$")
	public void i_am_on_pack_config_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.hoverPackConfig();
		jdaHomePage.clickPackConfig();
	}
}
