package com.jda.wms.stepdefs;

import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.LocationMaintenancePage;

import cucumber.api.java.en.Given;

public class LocationMaintenanceStepDefs {

	private final LocationMaintenancePage locationMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public LocationMaintenanceStepDefs(LocationMaintenancePage locationMaintenancePage) {
		this.locationMaintenancePage = locationMaintenancePage;
	}


	@Given("^Verify the Locations displayed$")
	public void verify_the_Locations_displayed() throws Throwable {
		String currentLocation = locationMaintenancePage.getLocation();
		//System.out.println("Location is : " + location);
		Assert.assertNotNull("Location is as not expected", currentLocation);
	}
	
}
