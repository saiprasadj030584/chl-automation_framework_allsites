package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.LocationMaintenancePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LocationMaintenanceStepDefs {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final LocationMaintenancePage locationMaintenancePage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private Context context;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public LocationMaintenanceStepDefs(LocationMaintenancePage locationMaintenancePage, Context context,
			JDAFooter jdaFooter, JdaHomePage jdaHomePage) {
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.locationMaintenancePage = locationMaintenancePage;
		this.context = context;
	}

	@Given("^I navigate to Location Maintenance Page$")
	public void i_navigate_to_Location_Maintenance_Page() throws Throwable {
		Thread.sleep(3000);
		jdaHomePage.navigateToLocationMaintanence();
	}

	@Given("^I search with location ID \"([^\"]*)\"$")
	public void i_search_with_location_ID(String location) throws Throwable {
		jdaFooter.clickQueryButton();
		locationMaintenancePage.enterLocation(location);
		jdaFooter.clickExecuteButton();
		context.setlocationID(location);
	}

	@Then("^the location record should be displayed$")
	public void the_location_record_should_be_displayed() throws Throwable {
		Assert.assertTrue("Location details are not as expected.", locationMaintenancePage.isRecordfound());
		logger.debug(" Location :  " + context.getlocationID());
	}

	@When("^I update the location lock status as \"([^\"]*)\"$")
	public void i_update_the_location_lock_status_as(String lockstatus) throws Throwable {
		context.setlocationLockStatus(lockstatus);
		jdaFooter.clickUpdateButton();
		locationMaintenancePage.selectLockStatus(lockstatus);
		jdaFooter.clickExecuteButton();
		// TODO add clickYesButton()
	}

	@Then("^the updated lock status should be displayed$")
	public void the_updated_lock_status_should_be_displayed() throws Throwable {
		String currentLockStatus = locationMaintenancePage.getLocationLockStatus();
		logger.debug("Expected Lock Status: " + context.getlocationLockStatus());
		logger.debug("Acutal Lock Status: " + currentLockStatus);

		Assert.assertEquals("REC Lane location lock status not changed as expected.", context.getlocationLockStatus(),
				currentLockStatus);
	}

}
