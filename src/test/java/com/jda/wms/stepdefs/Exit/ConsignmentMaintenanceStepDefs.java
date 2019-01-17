package com.jda.wms.stepdefs.Exit;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.CEConsignmentMaintenancePage;
import com.jda.wms.pages.Exit.JdaLoginPage;
import com.jda.wms.pages.Exit.LocationMaintenancePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class ConsignmentMaintenanceStepDefs {
	private final CEConsignmentMaintenancePage consignmentMaintenancePage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final JdaLoginPage jdaLoginPage;
	private JDAFooter jdaFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public ConsignmentMaintenanceStepDefs(CEConsignmentMaintenancePage consignmentMaintenancePage,JdaLoginPage jdaLoginPage,JDAHomeStepDefs jdaHomeStepDefs,JDAFooter jdaFooter) {
		this.consignmentMaintenancePage = consignmentMaintenancePage;
		this.jdaLoginPage =jdaLoginPage;
		this.jdaFooter=jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
	}


	@And("^Right click to Select Toggle Maintenance Mode$")
	public void Right_click_to_Select_Toggle_Maintenance_Mode() throws Throwable {
		
		consignmentMaintenancePage.selectToggleMaintenanceMode();
		Thread.sleep(1000);
	}
	@When("^I click on Add button$")
	public void I_click_on_Add_button() throws Throwable {
		Thread.sleep(3000);
		//consignmentMaintenancePage.clickAdd();
		jdaFooter.clickAddButton();
	}
	@Then("^Enter consignment name$")
	public void Enter_consignment_name() throws Throwable {
		Thread.sleep(1000);
		consignmentMaintenancePage.enterConsignment();
	}
	
	@Then("^Enter same consignment name$")
	public void Enter_same_consignment_name() throws Throwable {
		Thread.sleep(1000);
		consignmentMaintenancePage.enterSameConsignment();
	}
	@Then("^validate the consignment id is created$")
	public void validate_consignment_name() throws Throwable {
		consignmentMaintenancePage.verifyConsignment();
	}
	
	@And("^Select consignment Status$")
	public void Select_consignment_Status() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.selectStatus();
		Thread.sleep(3000);
	}
	@And("^Select Mode of transport$")
	public void Select_Mode_of_transport() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.selectTransportmode();
		Thread.sleep(3000);
	}
	@And("^Select trailer type to reflect Hazardous and Repack status$")
	public void Select_trailer_type_to_reflect_Hazardous_and_Repack_status() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.checkPackStatus();
	}
	@And("^validate the record is saved$")
	public void validate_the_record_is_saved() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.validateRecord();
	}
	@And("^Enter consignment$")
	public void Enter_consignment() throws Throwable {
		consignmentMaintenancePage.typeConsignment();
		Thread.sleep(3000);
		
	}
	@And("^Enter chamber and Address Id$")
	public void Enter_chamber_and_Address_Id() throws Throwable {
		//Thread.sleep(3000);
		consignmentMaintenancePage.typeChamberAddressId();
		//Thread.sleep(3000);
	}
	@And("^Enter dock door$")
	public void Enter_chamber_and_dock_Id() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.typeChamberDockId();
		Thread.sleep(3000);
	}
	
	
	@And("^Select Consignment$")
	public void Select_Consignment() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.selectConsignment();
	}
	@And("^validate Error message is displayed$")
	public void validate_Error_message_is_displayed() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.consignmentError();
	}
	@And("Select consignment to close$")
	public void close_consignment() throws Throwable {
		Thread.sleep(3000);
		consignmentMaintenancePage.closeConsignment();
	}
	@And("I create a consignment$")
	public void create_consignment() throws Throwable {
		
		jdaHomeStepDefs.Go_to_consignment_maintainance();
		Right_click_to_Select_Toggle_Maintenance_Mode();
		I_click_on_Add_button();
		Enter_consignment_name();
		Select_consignment_Status();
		jdaFooter.clickExecuteButton();
		Select_Mode_of_transport();
		jdaFooter.clickExecuteButton();
		///consignmentMaintenancePage.closeConsignment();
	}
	@And("drop the same consignment$")
	public void drop_consignment() throws Throwable {
		Thread.sleep(3000);
		///consignmentMaintenancePage.closeConsignment();
		jdaHomeStepDefs.Go_to_consignment_drop_maintainance_screen();
		Right_click_to_Select_Toggle_Maintenance_Mode();
		jdaFooter.clickAddButton();
		Enter_consignment();
		Enter_chamber_and_Address_Id();
		jdaFooter.clickExecuteButton();
		//jdaLoginPage.logOut();
	}
	@And("I close the consignment$")
	public void closeConsignment() throws Throwable {
		Thread.sleep(3000);
		jdaHomeStepDefs.Go_to_close_consignment();
		Enter_same_consignment_name();
		jdaHomeStepDefs.click_next();
		close_consignment();
		jdaHomeStepDefs.click_done();
	}
	
}
