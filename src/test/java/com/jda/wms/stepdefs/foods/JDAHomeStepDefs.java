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

	@Given("^I am on inventory query page$")
	public void i_am_on_inventor_query_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverDataInventory();
		jdaHomePage.clickInventory();
	}

	@When("^I navigate to inventory query$")
	public void i_navigate_to_inventory_query() throws Throwable {
		jdaHomePage.clickInventorytab();
	}

	// @When("^I navigate to inventory update page$")
	// public void i_navigate_to_inventory_update_page() throws Throwable {
	// jdaHomePage.clickOperationsMenu();
	// jdaHomePage.hoverOperationsInventory();
	// jdaHomePage.clickInventoryUpdate();
	// }

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

	// @When("^I navigate to inventory transaction query$")
	// public void i_navigate_to_inventory_transaction_query() throws Throwable
	// {
	// jdaHomePage.clickDataMenu();
	// jdaHomePage.hoverDataInventory();
	// jdaHomePage.clickInventoryTransaction();
	// }

	@When("^I navigate to inventory transaction query$")
	public void i_navigate_to_inventory_transaction_query() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
	}

	@When("^I navigate to SKU page$")
	public void i_navigate_to_SKU_page() throws Throwable {
		jdaHomePage.navigateToSKUMaintanence();
	}

	@When("^I navigate to inventory query page$")
	public void i_navigate_to_inventory_query_page() throws Throwable {
		jdaHomePage.navigateToInventoryQueryPage();
	}
}
