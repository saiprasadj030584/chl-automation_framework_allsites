package com.jda.wms.stepdefs.Exit;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.CEConsignmentMaintenancePage;
import com.jda.wms.pages.Exit.LocationMaintenancePage;
import com.jda.wms.pages.Exit.TrailerMaintenancePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class TrailerMaintenanceStepDefs {
	private final TrailerMaintenancePage trailerMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public TrailerMaintenanceStepDefs(TrailerMaintenancePage trailerMaintenancePage) {
		this.trailerMaintenancePage = trailerMaintenancePage;
	}


	@And("^Enter Trailer number$")
	public void Enter_Trailer_number() throws Throwable {
	
		trailerMaintenancePage.enterTrailerNumber();
	}

	@And("^Select Trailer Type$")
	public void Select_Trailer_Type() throws Throwable {
	
		trailerMaintenancePage.selectTrailerType();
	}
	@And("^Select Trailer$")
	public void Select_Trailer() throws Throwable {
	
		trailerMaintenancePage.selectTrailer();
	}
	@And("^validate error message is displayed$")
	public void validate_error_message_is_displayed() throws Throwable {
	
		trailerMaintenancePage.validateErrorMsg();
	}
	
}
