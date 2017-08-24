package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.pages.gm.JdaHomePage;

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
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.clickSKUSubmenu();
	}

	@When("^I navigate to Trailer Shipping page$")
	public void i_navigate_to_Trailer_Shipping_page() throws Throwable {
		jdaHomePage.navigateToTrailerShippingPage();
	}

	@Given("^I am on supplier SKU maintenance page$")
	public void i_am_on_supplier_SKU_maintenance_page() throws Throwable {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		jdaHomePage.clickSupplierSKU();
	}

	@When("^I navigate to inventory query$")
	public void i_navigate_to_inventory_query() throws Throwable {
		jdaHomePage.clickInventorytab();
	}

	@When("^I navigate to address maintenance page$")
	public void i_navigate_to_address_maintenance_page() throws Throwable {
		jdaHomePage.navigateToAddressMaintenancePage();
	}

	@Given("^I am on pack config maintenance page$")
	public void i_am_on_pack_config_maintenance_page() throws Throwable {
		jdaHomePage.navigateToPackConfigPage();
	}

	@Given("^I am on to pre-advice header maintenance page$")
	public void i_am_on_to_pre_advice_header_maintenance_page() throws Throwable {
		jdaHomePage.navigateToPreAdviceHeaderMaintenance();
	}

	@Given("^I am on inventory query page$")
	public void i_am_on_inventory_query_page() throws Throwable {
		/*
		 * jdaHomePage.clickDataMenu(); jdaHomePage.hoverDataInventory();
		 * jdaHomePage.clickInventory();
		 */
		i_navigate_to_inventory_query_page();
	}

	@When("^I navigate to stock adjustments page$")
	public void i_navigate_to_stock_adjustments_page() throws Throwable {
		jdaHomePage.navigateToStockAdjustment();
	}

	@When("^I navigate to inventory transaction query$")
	public void i_navigate_to_inventory_transaction_query() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		Thread.sleep(2000);
	}
	
	@When("^I navigate to UPI Management screen$")
	public void i_navigate_to_UPI_Management_screen() throws Throwable {
		jdaHomePage.navigateToUpiManagementPage();
		Thread.sleep(2000);
	}

	@When("^I navigate to SKU page$")
	public void i_navigate_to_SKU_page() throws Throwable {
		jdaHomePage.navigateToSKUMaintanence();
	}

	@When("^I navigate to inventory query page$")
	public void i_navigate_to_inventory_query_page() throws Throwable {
		Thread.sleep(2000);
		jdaHomePage.navigateToInventoryQueryPage();
	}

	@When("^I navigate to inventory update page$")
	public void i_navigate_to_inventory_update_page() throws Throwable {
		jdaHomePage.navigateToInventoryUpdate();
	}

	@Given("^I am on to pre-advice line maintenance page$")
	public void i_am_on_to_pre_advice_line_maintenance_page() throws Throwable {
		jdaHomePage.navigateToPreAdviceLineMaintenance();
	}

	@When("^I navigate to receipt reversal page$")
	public void i_navigate_to_receipt_reversal_page() throws Throwable {
		jdaHomePage.navigateToReceiptReversalPage();
	}
	

	@Given("^I am on to pick face maintenance page$")
	public void i_am_on_to_pick_face_maintenance_page() throws Throwable {
		jdaHomePage.navigateToPickFaceMaintenance();
	}

	@Given("^I navigate to order Line Maintenance Page$")
	public void i_navigate_to_order_Line_Maintenance_Page() throws Throwable {
		jdaHomePage.navigateToOrderLineMaintenance();
	}

	@When("^I navigate to ship dock reassignment$")
	public void i_navigate_to_ship_dock_reassignment() throws Throwable {
		jdaHomePage.navigateToShipDockReassignment();
	}

	@When("^i navigate to order header maintenance$")
	public void i_navigate_to_order_header_maintenance() throws Throwable {
		jdaHomePage.navigateToOrderHeaderMaintenance();

	}

	@When("^I navigate to move task update$")
	public void i_navigate_to_move_task_update() throws Throwable {
		jdaHomePage.navigateToMoveTaskUpdate();
	}

	@When("^I navigate to location page$")
	public void i_navigate_to_location_page() throws Throwable {
		jdaHomePage.navigateToLocationPage();
	}

	@When("^I navigate to order preparation page$")
	public void i_navigate_to_order_preparation_page() throws Throwable {
		jdaHomePage.navigateToOrderPreparationPage();
	}

	@When("^I navigate to vehicle unloading page$")
	public void i_navigate_to_vehicle_unloading_page() throws Throwable {
		jdaHomePage.navigateToVehicleUnloadingPage();
	}

	@When("^I navigate to move task list generation page$")
	public void i_navigate_to_move_task_list_generation_page() throws Throwable {
		jdaHomePage.navigateToMoveTaskListGenerationPage();
		Thread.sleep(3000);
	}

	@When("^I navigate to order management page$")
	public void i_navigate_to_order_management_page() throws Throwable {
		jdaHomePage.navigateToOrderManagementPage();
	}

	@When("^I navigate to system allocation page$")
	public void i_navigate_to_system_allocation_page() throws Throwable {
		jdaHomePage.navigateToOrderContainerPage();
		Thread.sleep(6000);
	}
	
	

	@When("^I navigate to unpicking and unshipping page$")
	public void i_navigate_to_unpicking_and_unshipping_page() throws Throwable {
		jdaHomePage.navigateToUnpickingAndUnshippingPage();
		Thread.sleep(6000);
	}
	
	@When("^I navigate to order container page$")
	public void i_navigate_to_order_container_page() throws Throwable {
		jdaHomePage.navigateToSystemAllocationPage();
		Thread.sleep(6000);
	}

	@When("^I navigate to mannual clustering screen$")
	public void i_navigate_to_mannual_clustering_screen() throws Throwable {
		jdaHomePage.navigateToMannualClusteringPage();
	}

	@When("^I navigate to dock scheduler start page$")
	public void i_navigate_to_dock_scheduler_start_page() throws Throwable {
		jdaHomePage.navigateToDockSchedulerPage();
	}
	
	
}
