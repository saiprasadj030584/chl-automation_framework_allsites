package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.TrailerContentsDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.VehicleUnloadingPage;

import cucumber.api.java.en.When;

public class VehicleUnloadingStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	private final VehicleUnloadingPage vehicleUnloadingPage;
	private final Context context;

	@Inject
	public VehicleUnloadingStepDefs(JdaHomePage jdaHomePage, JDAFooter jdafooter,VehicleUnloadingPage vehicleUnloadingPage,Context context) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
		this.vehicleUnloadingPage = vehicleUnloadingPage;
		this.context = context;
	}
	
	@When("^I enter the siteId,consignment as \"([^\"]*)\" and pallet as \"([^\"]*)\"$")
	public void i_enter_the_siteId_consignment_as_and_pallet_as(String consignment, String pallet) throws Throwable {
		context.setSTOConsignment(consignment);
		context.setPalletID(pallet);
		
		vehicleUnloadingPage.enterSiteId();
		vehicleUnloadingPage.enterConsignment(consignment);
		vehicleUnloadingPage.enterPallet(pallet);
		jdafooter.clickNextButton();
	}
	
	@When("^I select the pallet to unload$")
	public void i_select_the_pallets_to_unload() throws Throwable {
		vehicleUnloadingPage.selectPallet();
	}

	


}
