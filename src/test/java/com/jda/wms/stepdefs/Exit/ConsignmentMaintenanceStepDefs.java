package com.jda.wms.stepdefs.Exit;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.CEConsignmentMaintenancePage;
import com.jda.wms.pages.Exit.LocationMaintenancePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class ConsignmentMaintenanceStepDefs {
	private final CEConsignmentMaintenancePage consignmentMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public ConsignmentMaintenanceStepDefs(CEConsignmentMaintenancePage consignmentMaintenancePage) {
		this.consignmentMaintenancePage = consignmentMaintenancePage;
	}


	@And("^Right click to Select Toggle Maintenance Mode$")
	public void Right_click_to_Select_Toggle_Maintenance_Mode() throws Throwable {
		consignmentMaintenancePage.selectToggleMaintenanceMode();
	}
	@When("^I click on Add button$")
	public void I_click_on_Add_button() throws Throwable {
		consignmentMaintenancePage.clickAdd();
	}
	@Then("^Enter consignment name$")
	public void Enter_consignment_name() throws Throwable {
		consignmentMaintenancePage.enterConsignment();
	}
	@And("^Select consignment Status$")
	public void Select_consignment_Status() throws Throwable {
		consignmentMaintenancePage.selectStatus();
	}
	@And("^Select Mode of transport$")
	public void Select_Mode_of_transport() throws Throwable {
		consignmentMaintenancePage.selectTransportmode();
	}
	@And("^Select trailer type to reflect Hazardous and Repack status$")
	public void Select_trailer_type_to_reflect_Hazardous_and_Repack_status() throws Throwable {
		consignmentMaintenancePage.checkPackStatus();
	}
	@And("^validate the record is saved$")
	public void validate_the_record_is_saved() throws Throwable {
		consignmentMaintenancePage.validateRecord();
	}
	@And("^Enter consignment$")
	public void Enter_consignment() throws Throwable {
		consignmentMaintenancePage.typeConsignment();
	}
	@And("^Enter chamber and Address Id$")
	public void Enter_chamber_and_Address_Id() throws Throwable {
		consignmentMaintenancePage.typeChamberAddressId();
	}
	@And("^Select Consignment$")
	public void Select_Consignment() throws Throwable {
		consignmentMaintenancePage.selectConsignment();
	}
	
}
