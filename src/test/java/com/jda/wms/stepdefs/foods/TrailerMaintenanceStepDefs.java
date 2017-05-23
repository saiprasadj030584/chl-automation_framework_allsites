package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.TrailerDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.TrailerMaintenancePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TrailerMaintenanceStepDefs {

	private final TrailerMaintenancePage trailerMaintenancePage;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final Context context;
	private final TrailerDB trailerDB;

	@Inject
	public TrailerMaintenanceStepDefs(TrailerMaintenancePage trailerMaintenancePage, JdaHomePage jdaHomePage,
			JDAFooter jdaFooter,Context context, TrailerDB trailerDB) {
		this.trailerMaintenancePage = trailerMaintenancePage;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.trailerDB = trailerDB;
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
		Assert.assertEquals("Trailer details are not as expected","TRAILER",trailerDB.getTrailerDetails(context.getTrailerNo()));
	}
}
