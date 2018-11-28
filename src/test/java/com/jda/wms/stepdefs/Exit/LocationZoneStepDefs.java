package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.LocationZonePage;

import cucumber.api.java.en.Given;

public class LocationZoneStepDefs {

	private final LocationZonePage locationZonePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public LocationZoneStepDefs(LocationZonePage locationZonePage) {
		this.locationZonePage = locationZonePage;
	}


	@Given("^Verify the LocationZone \"([^\"]*)\" displayed$")
	public void verify_the_LocationZone_displayed(String locationZone) throws Throwable {
		String currentLocationZone = locationZonePage.getLocationZone();
		System.out.println("Location is : " + locationZone);
		Assert.assertEquals("Location is as expected: ", locationZone,currentLocationZone);
	}
	@Given("^Enter the LocationZone \"([^\"]*)\"$")
	public void Enter_the_LocationZone(String locationZone) throws Throwable {
		locationZonePage.enterLocationZone(locationZone);
	}
	
}
