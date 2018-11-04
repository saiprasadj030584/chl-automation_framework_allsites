package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.SiteQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
 
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
}