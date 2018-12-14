package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.OrderHeaderPage;
import com.jda.wms.pages.Exit.PickFaceMaintenancePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class JDAHomeStepDefs {
	private final JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private OrderHeaderPage orderheaderpage;
	

	@Inject
	public JDAHomeStepDefs(JdaHomePage jdaHomePage,JDAFooter jdaFooter,OrderHeaderPage orderheaderpage) {
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter=jdaFooter;
		this.orderheaderpage=orderheaderpage;
	}

	@When("^I navigate to order header$")
	public void i_navigate_to_order_header() throws Throwable {
		jdaHomePage.navigateToOrderHeader();
	}
	@And("^Go to Data-GENERAL-SETUP-SITE & Click$")
	public void go_to_Data_GENERAL_SETUP_SITE_Click() throws Throwable {
		 jdaHomePage.navigateToSite();                                                                
	} 
	
	@And("^Go to Data-LOCATION-Location & Click$")
	public void go_to_Data_LOCATION_Location_Click() throws Throwable {
		 jdaHomePage.navigateTolocation();                                                                
	} 
	
	@And("^Go to Data-LOCATION-LocationZone & Click$")
	public void go_to_Data_LOCATION_LocationZone_Click() throws Throwable {
		 jdaHomePage.navigateTolocationZoneG();                                                                
	} 
	@And("^Go to Data-GENERAL_SETUP_Address & Click$")
	public void go_to_Data_GENERAL_SETUP_Address_Click() throws Throwable {
		 jdaHomePage.navigateToAddress();                                                                
	} 
	@And("^Go to Data-SKU-SKU & Click$")
	public void go_to_Data_SKU_SKU_Click() throws Throwable {
		 jdaHomePage.navigateToSKU();                                                                
	} 
	@And("^Go to Data-SKU-SKUmaintenance & Click$")
	public void go_to_Data_SKU_SKUmaintenance_Click() throws Throwable {
		 jdaHomePage.navigateToSKUMaintenance();                                                                
	} 
	@And("^Go to Data-SKU-SupplierSKU & Click$")
	public void go_to_Data_SKU_SupplierSKU_Click() throws Throwable {
		 jdaHomePage.navigateToSupplierSKUHome();                                                                
	} 
	@And("^Go to Data-order_orderline & Click$")
	public void go_to_Data_order_orderline_Click() throws Throwable {
		 jdaHomePage.navigateToorderlineHome();                                                                
	} 
	@And("^Go to Admin_Setup_Scheduler_Schedulerprograms & Click$")
	public void go_to_Admin_Setup_Scheduler_Schedulerprograms_Click() throws Throwable {
		 jdaHomePage.navigateToSchedulerprgm();                                                                
	} 
	@And("^Go to Admin-User-UserGroup & click$")
	public void Go_to_Admin_User_UserGroup_click() throws Throwable {
		 jdaHomePage.navigateToAdmin();                                                                
	} 
	@And("^Go to Data-UPI-UPIheader& click$")
	public void Go_to_Data_UPI_UPIheader_click() throws Throwable {
		 jdaHomePage.navigateToUPIheader();                                                                
	} 
	@And("^Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click$")
	public void Go_to_Admin_ACCESS_CNT_USER_GROUP_FUNCTION_ACCESS_Click() throws Throwable {
		 jdaHomePage.navigateToAccesscontrol();                                                                
	} 
	@And("^Go to Admin>ACCESS CNT>Global FUNCTION ACCESS & Click$")
	public void Go_to_Admin_ACCESS_CNT_Global_FUNCTION_ACCESS_Click() throws Throwable {
		 jdaHomePage.navigateToGloabalAccesscontrol();                                                                
	} 
	
	@And("^Go to Admin>ACCESS CNT>Workstation access control & Click$")
	public void Go_to_Admin_ACCESS_CNT_Workstation_access_control_Click() throws Throwable {
		 jdaHomePage.navigateToWorkstation();                                                                
	} 
	@And("^Go to Inventory Transaction & Click$")
	public void Go_to_Inventory_Transaction_Click() throws Throwable {
		 jdaHomePage.navigateToInventory();                                                                
	} 
	@And("^Go to Reports Selection and click$")
	public void go_to_reports_Selection_and_Click() throws Throwable {
		 jdaHomePage.navigateToReportSelectionMenu();                                                                
	} 
	
	@And("^Quering it using SiteID and Executing$")
	public void Quering_it_using_SiteID_and_Executing() throws Throwable {
		Thread.sleep(3000);
		jdaFooter.clickQueryButton();
		String Location="AA001";
		orderheaderpage.enterLocation(Location);
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
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
	@When("^I navigate to Trailer mainteinance page$")
	public void i_navigate_to_Trailer_mainteinance_page() throws Throwable {
		jdaHomePage.navigateToTrailerMainteinancePage();
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

	
	@Given("^I am on inventory query page$")
	public void i_am_on_inventory_query_page() throws Throwable {
		/*
		 * jdaHomePage.clickDataMenu(); jdaHomePage.hoverDataInventory();
		 * jdaHomePage.clickInventory();
		 */
		// jdaHomePage.navigateToInventoryQueryPage();
		i_navigate_to_inventory_query_page();
	}

	@When("^I navigate to stock adjustments page$")
	public void i_navigate_to_stock_adjustments_page() throws Throwable {
		/*
		 * jdaHomePage.clickOperations();
		 * jdaHomePage.hoverOperationsInventory();
		 * jdaHomePage.clickStockAdjustment();
		 */
		jdaHomePage.navigateToStockAdjustment();
	}

	@When("^I navigate to inventory transaction query$")
	public void i_navigate_to_inventory_transaction_query() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
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
		jdaHomePage.navigateToSystemAllocationPage();
		Thread.sleep(6000);
	}
	
	@When("^I navigate to mannual clustering screen$")
	public void i_navigate_to_mannual_clustering_screen() throws Throwable {
		jdaHomePage.navigateToMannualClusteringPage();
	}
	@When("^I navigate to container checking page$")
	public void i_navigate_to_container_checking_page() throws Throwable {
		jdaHomePage.navigateToConatinerCheckingPage();
	}

	@When("^I navigate to stock Relocation page$")
	public void i_navigate_to_stock_Relocation_page() throws Throwable {
		jdaHomePage.navigateToStockRelocatioPage();
	}
	
	@When("^I navigate to move task query page$")
	public void i_navigate_to_move_task_query_page() throws Throwable {
		jdaHomePage.navigateToMoveTaskQueryPage();
	}
	@And("^Click on Query$")
	public void click_on_Query() throws Throwable {
		Thread.sleep(2000);
		jdaFooter.clickQueryButton();
		Thread.sleep(2000);
	}
	@And("^click execute$")
	public void click_execute() throws Throwable {
		Thread.sleep(2000);
		jdaFooter.clickExecuteButton();
		Thread.sleep(2000);
	}
	@And("^Go to consignment maintainance$")
	public void Go_to_consignment_maintainance() throws Throwable {
		jdaHomePage.navigateToConsignmentMaintenance();
	}
	@And("^Go to consignment drop maintainance screen$")
	public void Go_to_consignment_drop_maintainance_screen() throws Throwable {
		jdaHomePage.navigateToConsignmentDropMaintenance();
	}
	@And("^Click search$")
	public void click_search() throws Throwable {
		jdaFooter.clickSearch();
		Thread.sleep(2000);
	}
	@And("^Go to Consignment Trailer Linking$")
	public void Go_to_Consignment_Trailer_Linking() throws Throwable {
		jdaHomePage.navigateToConsignmentTrailerLinking();
	}
	@And("^Click next$")
	public void click_next() throws Throwable {
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}
}
