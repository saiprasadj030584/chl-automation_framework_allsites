package com.jda.wms.stepdefs;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.AddressMaintenancePage;
import com.jda.wms.UI.pages.JdaHomePage;
import com.jda.wms.UI.pages.SiteQueryPage;
import com.jda.wms.context.Context;
import com.jda.wms.db.SiteDB;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
 
public class AddressQueryStefDefs {
	
	private final AddressMaintenancePage addressMaintenancePage;
	
	@Inject
	public AddressQueryStefDefs(AddressMaintenancePage addressMaintenancePage){
	this.addressMaintenancePage=addressMaintenancePage;}
	
	
	@And("^Specify the SITE ID in Addresstable \"([^\"]*)\"$")
	public void specify_the_SITE_ID(String siteID) throws Throwable {
		addressMaintenancePage.enterAddressID(siteID);
	}
	@Then("^Verify the Address ID \"([^\"]*)\" displayed$")
	public void verify_the_Address_ID_displayed(String addressID) throws Throwable {
		String currentAddressid = addressMaintenancePage.getAddressID();
		System.out.println("Address ID is : " + addressID);
		Assert.assertEquals("Address ID is as expected: ", addressID,currentAddressid);
	}
	
	@When("^I query, execute and process further$")
	public void i_query_execute_and_process_further() throws  Throwable{
		addressMaintenancePage.clickQuery();
		addressMaintenancePage.execute();		
	}
	
	@Then("^Verify address and site details are loaded into address screen$")
	public void verify_address_and_site_details_are_loaded_into_address_screen() throws Throwable {
		String currentAddressid = addressMaintenancePage.getAddressID();
		System.out.println("Address ID is : " + currentAddressid);
		Assert.assertTrue("record available", addressMaintenancePage.isRecordDisplayed());
	}
	
}