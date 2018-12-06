package com.jda.wms.stepdefs.Exit;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.CEConsignmentMaintenancePage;
import com.jda.wms.pages.Exit.LocationMaintenancePage;

import cucumber.api.java.en.Given;
public class ConsignmentMaintenanceStepDefs {
	private final CEConsignmentMaintenancePage consignmentMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public ConsignmentMaintenanceStepDefs(CEConsignmentMaintenancePage consignmentMaintenancePage) {
		this.consignmentMaintenancePage = consignmentMaintenancePage;
	}


	@Given("^Verify the Locations \"([^\"]*)\" displayed$")
	public void verify_the_Locations_displayed(String location) throws Throwable {
		
	}
}
