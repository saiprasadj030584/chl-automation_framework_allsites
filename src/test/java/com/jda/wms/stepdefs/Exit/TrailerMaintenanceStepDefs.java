package com.jda.wms.stepdefs.Exit;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
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
	private Context context;

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public TrailerMaintenanceStepDefs(TrailerMaintenancePage trailerMaintenancePage,
			Context context,JdaHomePage jdaHomePage,ConsignmentMaintenanceStepDefs consignmentMaintSD,JDAHomeStepDefs jdaHomeStepDefs) {
		this.trailerMaintenancePage = trailerMaintenancePage;
		this.jdaHomeStepDefs=jdaHomeStepDefs;
		this.consignmentMaintSD=consignmentMaintSD;
		this.jdaHomePage = jdaHomePage;
		this.context=context;
	}
	
	@And("^Enter seal ID$")
	public void Enter_Seal_number() throws Throwable {
	
		trailerMaintenancePage.enterSealId();
	}

	@And("^Enter Trailer number$")
	public void Enter_Trailer_number() throws Throwable {
	
		trailerMaintenancePage.enterTrailerNumber();
	}
	@And("^Enter Trailer number for report$")
	public void Enter_Trailer() throws Throwable {
	screen.type(Key.TAB);
	screen.type(Key.TAB);
		trailerMaintenancePage.enterTrailerNumber();
	}
	@And("^Enter Trailer number for the consignment$")
	public void enter_Trailer_number_for_the_consignment() throws Throwable {
		
		trailerMaintenancePage.enterTrailerNo(context.getTrailerNumber());
	}
	
	@And("^select trailer text tab$")
	public void Trailer_tab() throws Throwable {
	
		trailerMaintenancePage.enterTrailerTab();
	}
	@And("^Select Trailer Type$")
	public void Select_Trailer_Type() throws Throwable {
	
		trailerMaintenancePage.selectTrailerType();
	}
	@And("^Select Trailer Type as sea$")
	public void Select_Trailer_Type_Sea() throws Throwable {
	
		trailerMaintenancePage.selectTrailerTypeSea();
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
	
	@And("^I create DockDoor")
	public void I_create_Dockdoor() throws Throwable {
		Thread.sleep(2000);
		jdaHomePage.navigateToDockDoorScreen();
		Thread.sleep(2000);
		consignmentMaintSD.Right_click_to_Select_Toggle_Maintenance_Mode();
		Thread.sleep(1000);
		consignmentMaintSD.I_click_on_Add_button();
		trailerMaintenancePage.enterDockNumber();
		Thread.sleep(1000);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type("DONNSD");
		Thread.sleep(1000);
		jdaHomeStepDefs.click_execute();
	}
	@And("^I create Trailer")
	public void I_create_trailer() throws Throwable {
		
		Thread.sleep(1000);
		jdaHomePage.navigateToTrailerMainteinancePage();
		Thread.sleep(1000);
		consignmentMaintSD.Right_click_to_Select_Toggle_Maintenance_Mode();
		Thread.sleep(1000);
		consignmentMaintSD.I_click_on_Add_button();
		Enter_Trailer_number();
		Select_Trailer_Type();
		jdaHomeStepDefs.click_execute();
		//trailerMaintenancePage.clickTrailerAdd();
	}
	@And("^I link consignment with trailer")
	public void I_link_trailer() throws Throwable {
		Thread.sleep(2000);
		jdaHomePage.navigateToConsignmentTrailerLinking();
		Select_Trailer();
		consignmentMaintSD.Select_Consignment();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		And_I_click_on_trailer_Add_button();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		consignmentMaintSD.Enter_chamber_and_dock_Id();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_done();
		Thread.sleep(2000);
		/*Select_Trailer();
		consignmentMaintSD.Select_Consignment2();
		jdaHomeStepDefs.click_next();
		jdaHomeStepDefs.click_next();
		And_I_click_on_trailer_Add_button();
		jdaHomeStepDefs.click_next();
		consignmentMaintSD.Enter_chamber_and_dock_Id();
		jdaHomeStepDefs.click_done();*/
		
		//trailerMaintenancePage.clickTrailerAdd();
	}
	
	@And("^I link next consignment with trailer")
	public void I_link_next_trailer() throws Throwable {
		jdaHomeStepDefs.Go_to_Consignment_Trailer_Linking();
		Thread.sleep(2000);
		Select_Trailer();
		Thread.sleep(2000);
		consignmentMaintSD.Select_nextConsignment();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		And_I_click_on_trailer_Add_button();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		consignmentMaintSD.Enter_chamber_and_dock_Id();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_done();
		Thread.sleep(2000);
		
		//trailerMaintenancePage.clickTrailerAdd();
	}
   	
	@And("^I link multiconsignment with trailer")
	public void I_link_trailer1() throws Throwable {
		Thread.sleep(2000);
		jdaHomePage.navigateToConsignmentTrailerLinking();
		Select_Trailer();
		consignmentMaintSD.Select_Consignment();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		And_I_click_on_trailer_Add_button();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		consignmentMaintSD.Enter_chamber_and_dock_Id();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_done();
		Thread.sleep(2000);
		Select_Trailer();
		Thread.sleep(2000);
		consignmentMaintSD.Select_Consignment2();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		And_I_click_on_trailer_Add_button();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_next();
		Thread.sleep(2000);
		//consignmentMaintSD.Enter_chamber_and_dock_Id();
		Thread.sleep(2000);
		jdaHomeStepDefs.click_done();
		Thread.sleep(2000);
		//trailerMaintenancePage.clickTrailerAdd();
	}
}
