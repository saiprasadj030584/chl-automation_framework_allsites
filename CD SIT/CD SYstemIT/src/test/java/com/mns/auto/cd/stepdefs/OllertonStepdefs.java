package com.mns.auto.cd.stepdefs;

import com.google.inject.Inject;
import com.mns.auto.cd.pages.Ollerton2;
import com.mns.auto.cd.pages.OllertonAr;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OllertonStepdefs {

	private final OllertonAr ollertonAr;
	private final Ollerton2 ollerton2;

	@Inject
	public OllertonStepdefs(OllertonAr ollertonAr, Ollerton2 ollerton2) {
		this.ollertonAr = ollertonAr;
		this.ollerton2 = ollerton2;
	}

	@Given("^A received hanging data sku is to be putaway to final location and to create a transfer order and to despatch the Transfer order through donington receiving$")
	public void a_received_hanging_data_sku_is_to_be_putaway_to_final_location_and_to_create_a_transfer_order_and_to_despatch_the_Transfer_order_through_donington_receiving()
			throws Throwable {

		ollertonAr.TC().RunRoutine("CommonFunctions.LaunchPutty");
		
	}

//	Postal & URRN Receiving
	@Given("^As User to launch RP WMS and User done the data setup for the given sku$")
	public void as_User_to_launch_RP_WMS_and_User_done_the_data_setup_for_the_given_sku() throws Throwable {
		ollerton2.TC().RunRoutine("");
	    
	}

	@Given("^As user login to HHT$")
	public void as_user_login_to_HHT() throws Throwable {
	   
	}

	@When("^User perform postal receiving$")
	public void user_perform_postal_receiving() throws Throwable {
	    
	}

	@Then("^Messageid should be generated once postal receiving completed$")
	public void messageid_should_be_generated_once_postal_receiving_completed() throws Throwable {
	   
	}

	@Given("^User done the data setup for box sku$")
	public void user_done_the_data_setup_for_box_sku() throws Throwable {
	    
	}

	@When("^User perform URRN receiving$")
	public void user_perform_URRN_receiving() throws Throwable {
	    
	}

	@Then("^Messageid should be generated once URRN receiving completed$")
	public void messageid_should_be_generated_once_URRN_receiving_completed() throws Throwable {
	    
	}

	@Given("^User done the data setup for postal & URRN receiving$")
	public void user_done_the_data_setup_for_postal_URRN_receiving() throws Throwable {
	   
	}

	@When("^User perform Postal and URRN receiving$")
	public void user_perform_Postal_and_URRN_receiving() throws Throwable {
	}

	@Then("^Messageid should be generated for postal and URRN once receiving completed$")
	public void messageid_should_be_generated_for_postal_and_URRN_once_receiving_completed() throws Throwable {
	    
	}

	@Given("^User done the data setup for oversize$")
	public void user_done_the_data_setup_for_oversize() throws Throwable {
	    
	}

	@When("^User perform receiving for oversize sku$")
	public void user_perform_receiving_for_oversize_sku() throws Throwable {
	    
	}

	@Then("^Sku should reach the oversize location after putaway$")
	public void sku_should_reach_the_oversize_location_after_putaway() throws Throwable {
	    
	}

//Receiving shoe functionalities
	
	
}