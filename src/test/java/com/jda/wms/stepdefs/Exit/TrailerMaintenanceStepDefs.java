package com.jda.wms.stepdefs.Exit;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.CEConsignmentMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.LocationMaintenancePage;
import com.jda.wms.pages.Exit.TrailerMaintenancePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class TrailerMaintenanceStepDefs {
	private final TrailerMaintenancePage trailerMaintenancePage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final JdaHomePage jdaHomePage;
	private final ConsignmentMaintenanceStepDefs consignmentMaintSD;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public TrailerMaintenanceStepDefs(TrailerMaintenancePage trailerMaintenancePage,JdaHomePage jdaHomePage,ConsignmentMaintenanceStepDefs consignmentMaintSD,JDAHomeStepDefs jdaHomeStepDefs) {
		this.trailerMaintenancePage = trailerMaintenancePage;
		this.jdaHomeStepDefs=jdaHomeStepDefs;
		this.consignmentMaintSD=consignmentMaintSD;
		this.jdaHomePage = jdaHomePage;
	}
	
	@And("^Enter seal ID$")
	public void Enter_Seal_number() throws Throwable {
	
		trailerMaintenancePage.enterSealId();
	}

	@And("^Enter Trailer number$")
	public void Enter_Trailer_number() throws Throwable {
	
		trailerMaintenancePage.enterTrailerNumber();
	}
	
	@And("^select trailer text tab$")
	public void Trailer_tab() throws Throwable {
	
		trailerMaintenancePage.enterTrailerTab();
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
	@And("^validate the error popup is displayed$")
	public void validate_error_popup_is_displayed() throws Throwable {
	
		trailerMaintenancePage.validateErrorpopup();
	}
	
	@And("^validate Consignment Trailer is linked$")
	public void validate_Consignment_Trailer_is_linked() throws Throwable {
	
		trailerMaintenancePage.validateTrailerLinked();
	}
	
	@And("^I click on trailer Add button")
	public void And_I_click_on_trailer_Add_button() throws Throwable {
	
		trailerMaintenancePage.clickTrailerAdd();
	}
	@And("^click on Trailer")
	public void I_click_on_trailer() throws Throwable {
	
		trailerMaintenancePage.clickTrailerAdd();
	}
	@And("^I create Trailer")
	public void I_create_trailer() throws Throwable {
		Thread.sleep(3000);
		jdaHomePage.navigateToTrailerMainteinancePage();
		Thread.sleep(3000);
		consignmentMaintSD.Right_click_to_Select_Toggle_Maintenance_Mode();
		Thread.sleep(3000);
		consignmentMaintSD.I_click_on_Add_button();
		Enter_Trailer_number();
		Select_Trailer_Type();
		jdaHomeStepDefs.click_execute();
		//trailerMaintenancePage.clickTrailerAdd();
	}
	@And("^I link consignment with trailer")
	public void I_link_trailer() throws Throwable {
		jdaHomeStepDefs.Go_to_Consignment_Trailer_Linking();
		Select_Trailer();
		consignmentMaintSD.Select_Consignment();
		jdaHomeStepDefs.click_next();
		jdaHomeStepDefs.click_next();
		And_I_click_on_trailer_Add_button();
		jdaHomeStepDefs.click_next();
		consignmentMaintSD.Enter_chamber_and_dock_Id();
		jdaHomeStepDefs.click_done();
		
		//trailerMaintenancePage.clickTrailerAdd();
	}
	
}
