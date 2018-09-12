package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.TrailerDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.TrailerMaintenancePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TrailerMaintenanceStepDefs {

	private final TrailerMaintenancePage trailerMaintenancePage;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final Context context;
	private final TrailerDB trailerDB;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private TrailerShippingStepDefs trailerShippingStepDefs;
	private InventoryUpdateStepDefs inventoryUpdateStepDefs;

	@Inject
	public TrailerMaintenanceStepDefs(TrailerMaintenancePage trailerMaintenancePage, JdaHomePage jdaHomePage,
			JDAFooter jdaFooter, Context context, TrailerDB trailerDB,JDAHomeStepDefs jdaHomeStepDefs,TrailerShippingStepDefs trailerShippingStepDefs,InventoryUpdateStepDefs inventoryUpdateStepDefs) {
		this.trailerMaintenancePage = trailerMaintenancePage;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.trailerDB = trailerDB;
		this.jdaHomeStepDefs=jdaHomeStepDefs;
		this.trailerShippingStepDefs = trailerShippingStepDefs;
		this.inventoryUpdateStepDefs = inventoryUpdateStepDefs;
	}

	@When("^I create a trailer in trailer Maintenance page$")
	public void i_create_a_trailer_in_trailer_Maintenance_page() throws Throwable {
		jdaHomePage.navigateToTrailerMaintanencePage();
		jdaFooter.clickAddButton();
		
		String trailerNo = Utilities.getFiveDigitRandomNumber();
		trailerMaintenancePage.enterTrailerNo(trailerNo);
		trailerMaintenancePage.enterTrailerType();
		jdaFooter.clickExecuteButton();
		jdaFooter.PressEnter();
		context.setTrailerNo(trailerNo);
	}

	@Then("^the trailer should be created$")
	public void the_trailer_should_be_created() throws Throwable {
		Assert.assertEquals("Trailer details are not as expected", "TRAILER",
				trailerDB.getTrailerDetails(context.getTrailerNo()));
	}
	
	@Given("^the trailer has been created$")
	public void the_trailer_has_been_created() throws Throwable {
		i_create_a_trailer_in_trailer_Maintenance_page();
		the_trailer_should_be_created();
	} 
	
	@When("^I ship the trailer with site id, trailer number and seal number$")
	public void i_ship_the_trailer_with_site_id_trailer_number_and_seal_number() throws Throwable {
		Thread.sleep(5000);
		jdaHomeStepDefs.i_navigate_to_Trailer_Shipping_page();
		trailerShippingStepDefs.I_enter_the_site_id_and_trailer_number();
		trailerShippingStepDefs.i_enter_the_seal_number();
		inventoryUpdateStepDefs.i_proceed_to_complete_the_process();
	}
}
