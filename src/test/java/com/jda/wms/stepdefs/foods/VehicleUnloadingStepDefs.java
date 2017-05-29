package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.db.TrailerContentsDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.VehicleUnloadingPage;

import cucumber.api.java.en.When;

public class VehicleUnloadingStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	private final VehicleUnloadingPage vehicleUnloadingPage;

	@Inject
	public VehicleUnloadingStepDefs(JdaHomePage jdaHomePage, JDAFooter jdafooter,VehicleUnloadingPage vehicleUnloadingPage) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
		this.vehicleUnloadingPage = vehicleUnloadingPage;
	}
	
	@When("^I enter the siteId,consignment as \"([^\"]*)\" and pallet as \"([^\"]*)\"$")
	public void i_enter_the_siteId_consignment_as_and_pallet_as(String consignment, String pallet) throws Throwable {
		vehicleUnloadingPage.enterSiteId();
		vehicleUnloadingPage.enterConsignment();
		vehicleUnloadingPage.enterPallet();
		jdafooter.clickNextButton();
	}
	
//	@When("^I select the pallets to unload$")
//	public void i_select_the_pallets_to_unload() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^vehicle unload ITL should be generated$")
//	public void vehicle_unload_ITL_should_be_generated() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}


}
