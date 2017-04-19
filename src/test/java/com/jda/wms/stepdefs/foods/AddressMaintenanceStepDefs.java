package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.AddressMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddressMaintenanceStepDefs {
	private final AddressMaintenancePage addressMaintenancePage;
	@Inject
	public AddressMaintenanceStepDefs(AddressMaintenancePage addressMaintenancePage) {
		this.addressMaintenancePage = addressMaintenancePage;
	}
	@When("^I search the address id\"([^\"]*)\"$")
	public void i_search_the_address_id(String addressId) throws Throwable {
		addressMaintenancePage.clickQueryButton();
		addressMaintenancePage.enterAddressID(addressId);
		addressMaintenancePage.clickExecuteButton(); 
	}
	@Then("^the address type, name, address(\\d+) and country should be displayed$")
	public void the_address_type_name_address_and_country_should_be_displayed(int arg1) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		if (!addressMaintenancePage.getAddressType().equals(null)) {
			failureList.add("Address type is not as expected.");
		}
		if (!addressMaintenancePage.getName().equals(null)) {
			failureList.add("Name is not as expected.");
		}
		if (!addressMaintenancePage.getAddress1().equals(null)) {
			failureList.add("Address1 is not as expected.");
		}
		if (!addressMaintenancePage.getCountry().equals(null)) {
			failureList.add("Country is not as expected.");
		}
		Assert.assertTrue("Address maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	@When("^I navigate to customs & excise tab$")
	public void i_navigate_to_customs_excise_tab() throws Throwable {
		addressMaintenancePage.clickCustomsExcisetab();
		
	}
	@Then("^the CE & warehouse type should be displayed$")
	public void the_CE_warehouse_type_should_be_displayed() throws Throwable {
		Assert.assertNotNull("CE & Warehouse type should not be null", addressMaintenancePage.getCEWarehouseType());
	}
	    
	@When("^I navigate to user defined tab$")
	public void i_navigate_to_user_defined_tab() throws Throwable {
		addressMaintenancePage.clickUserDefinedTab(); 
	}
	@Then("^is site flag should be updated as site$")
	public void is_site_flag_should_be_updated_as_site() throws Throwable {
		Assert.assertTrue("The address Id is not a site",addressMaintenancePage.checkIsSite());
	}

	@Then("^the site category should be displayed$")
	public void the_site_category_should_be_displayed() throws Throwable {
		Assert.assertNotNull("site category should not be null",addressMaintenancePage.getSiteCategory());
	    
	}





	

	

}
