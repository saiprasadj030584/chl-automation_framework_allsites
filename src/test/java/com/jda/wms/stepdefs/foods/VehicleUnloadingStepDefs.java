package com.jda.wms.stepdefs.foods;

import java.util.List;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.VehicleUnloadingPage;
import com.jda.wms.stepdefs.rdt.StoreTrackingOrderPickingStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class VehicleUnloadingStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	private final VehicleUnloadingPage vehicleUnloadingPage;
	private final Context context;
	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;

	@Inject
	public VehicleUnloadingStepDefs(JdaHomePage jdaHomePage, JDAFooter jdafooter,VehicleUnloadingPage vehicleUnloadingPage,Context context,StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
		this.vehicleUnloadingPage = vehicleUnloadingPage;
		this.context = context;
		this.storeTrackingOrderPickingStepDefs = storeTrackingOrderPickingStepDefs;
	}
	
	@Given("^the vehicle loading has been done for order \"([^\"]*)\"$")
	public void the_vehicle_loading_has_been_done_for_order(String orderId) throws Throwable {
		context.setOrderId(orderId);
		storeTrackingOrderPickingStepDefs.the_sto_of_type_should_contain_order_details_and_be_container_picked(orderId,"STR","completely");
		the_order_should_be_status("Ready to Load");
	}
	
	@When("^I enter the siteId,consignment and pallet$")
	public void i_enter_the_siteId_consignment_and_pallet() throws Throwable {
		List<String> palletIDList = context.getPalletIDList(); 
		
		String consignment = context.getSTOConsignment();
		String palletID = palletIDList.get(0);
		
		vehicleUnloadingPage.enterSiteId();
		vehicleUnloadingPage.enterConsignment(consignment);
		vehicleUnloadingPage.enterPallet(palletID);
		jdafooter.clickNextButton();
	}
	
	@When("^I select the pallet to unload$")
	public void i_select_the_pallets_to_unload() throws Throwable {
		vehicleUnloadingPage.selectPallet();
	}

}
