package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JdaHomePage;
import cucumber.api.java.en.*;

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
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.clickSKUSubmenu();
	}

	@Given("^I am on supplier SKU maintenance page$")
	public void i_am_on_supplier_SKU_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.clickSupplierSKU();
	}
	@Given("^I am on pack config maintenance page$")
	public void i_am_on_pack_config_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.hoverPackConfig();
		jdaHomePage.clickPackConfig();
	}
	
	@Given("^I am on inventory query page$")
	public void i_am_on_inventory_query_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverDataInventory();
		jdaHomePage.clickInventory();
	}
	
	@When("^I navigate to stock adjustments page$")
	public void i_navigate_to_stock_adjustments_page() throws Throwable {
		jdaHomePage.clickOperations();
		jdaHomePage.hoverOperationsInventory();
		jdaHomePage.clickStockAdjustment();
	}
	
	@When("^I navigate to inventory transaction query$")
	public void i_navigate_to_inventory_transaction_query() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverDataInventory();
		jdaHomePage.clickInventoryTransaction();
	}
}
