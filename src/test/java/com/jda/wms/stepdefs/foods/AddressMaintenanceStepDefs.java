package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddressMaintenanceStepDefs {
	private final AddressMaintenancePage addressMaintenancePage;
	private JDAFooter jdaFooter;
	private Context context;
	private JDAHomeStepDefs jdaHomeStepDefs;

	@Inject
	public AddressMaintenanceStepDefs(AddressMaintenancePage addressMaintenancePage, JDAFooter jdaFooter, Context context,JDAHomeStepDefs jdaHomeStepDefs) {
		this.addressMaintenancePage = addressMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
	}

	@When("^I search the address id \"([^\"]*)\"$")
	public void i_search_the_address_id(String addressId) throws Throwable {
		jdaFooter.clickQueryButton();
		addressMaintenancePage.enterAddressID(addressId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the address type, name, address line 1 and country should be displayed$")
	public void the_address_type_name_address_line_1_and_country_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String addressType = addressMaintenancePage.getAddressType();
		if (addressType.equals(null)) {
			failureList.add("Address type is not as expected. Expected [Not NULL] but was [" + addressType + "]");
		}
		String name = addressMaintenancePage.getName();
		if (addressMaintenancePage.getName().equals(null)) {
			failureList.add("Name is not as expected. Expected [Not NULL] but was [" + name + "]");
		}
		String address1 = addressMaintenancePage.getAddress1();
		if (addressMaintenancePage.getAddress1().equals(null)) {
			failureList.add("Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
		}
		String country = addressMaintenancePage.getCountry();
		if (addressMaintenancePage.getCountry().equals(null)) {
			failureList.add("Country is not as expected. Expected [Not NULL] but was [" + country + "]");
		}
		Assert.assertTrue("Address maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@When("^I navigate to customs & excise tab	in address maintenance$")
	public void i_navigate_to_customs_excise_tab_in_address_maintenance() throws Throwable {
		addressMaintenancePage.clickCustomsExciseTab();
	}

	@Then("^the CE & warehouse type should be displayed$")
	public void the_CE_warehouse_type_should_be_displayed() throws Throwable {
		String ceWarehouseType = addressMaintenancePage.getCEWarehouseType();
		Assert.assertNotNull(
				"CE & Warehouse type should not be null. Expected [Not Null] but was [" + ceWarehouseType + "]",
				ceWarehouseType);
	}
	
	@Then("^the CE & warehouse type should be displayed as Excise$")
	public void the_CE_warehouse_type_should_be_displayed_as_excise() throws Throwable {
		String ceWarehouseType = addressMaintenancePage.getCEWarehouseType();
		Assert.assertEquals(
				"CE & Warehouse type should not be null. Expected [Excise] but was [" + ceWarehouseType + "]",
				"Excise",ceWarehouseType);
	}
	
	@Then("^the CE tax warehouse should be displayed$")
	public void the_CE_tax_warehouse_should_be_displayed() throws Throwable {
		String ceTaxWarehouse = addressMaintenancePage.getCETaxwarehouse();
		context.setCEWarehouseTax(ceTaxWarehouse);
		Assert.assertNotNull(
				"CE Tax Warehouse is not displayed as expected. Expected [Not Null] but was [" + ceTaxWarehouse + "]",
				ceTaxWarehouse);
	}

	@Then("^I should see the is site flag is updated as site$")
	public void i_should_see_the_is_site_flag_is_updated_as_site() throws Throwable {
		Assert.assertTrue("The address Id is not a site. Expected [checkbox selected] but was [not selected]",
				addressMaintenancePage.isIsSiteChecked());
	}

	@Then("^the site category should be displayed$")
	public void the_site_category_should_be_displayed() throws Throwable {
		String siteCategory = addressMaintenancePage.getSiteCategory();
		Assert.assertNotNull("site category should not be null. Expected [Not Null] but was [" + siteCategory + "]",
				siteCategory);
	}

	@Then("^I should see the is site flag is updated as vendor$")
	public void i_should_see_the_is_site_flag_is_updated_as_vendor() throws Throwable {
		Assert.assertTrue("The address Id is not a vendor.  Expected [checkbox selected] but was [not selected]",
				addressMaintenancePage.isIsSiteUnchecked());
	}
	
	@Then("^the supplier should have supplier pallet and customs excise details in the address maintenanace table$")
	public void the_supplier_should_have_supplier_pallet_details_and_customs_excise_details_in_the_address_maintenanace_table() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		jdaHomeStepDefs.i_navigate_to_address_maintenance_page();
		jdaFooter.clickQueryButton();
		addressMaintenancePage.enterAddressID(context.getSupplierID());
		jdaFooter.clickExecuteButton();

		String name = addressMaintenancePage.getName();
		if (!context.getName().equals(name)) {
			failureList.add("Name is not as expected. Expected [" + context.getName() + "]  but was [" + name + "]");
		}

		String address1 = addressMaintenancePage.getAddress1();
		if (!context.getAddress1().equals(address1)) {
			failureList.add(
					"Address1 is not as expected. Expected [" + context.getAddress1() + "] but was [" + address1 + "]");
		}

		String country = addressMaintenancePage.getCountry();
		context.setCountry(country);
		if (!context.getCountry().equals(country)) {
			failureList.add(
					"Country is not as expected. Expected [" + context.getCountry() + "] but was [" + country + "]");
		}
		
		addressMaintenancePage.clickUserDefinedTab();
		String defaultySupplierPallet = addressMaintenancePage.getDefaultSupplierPallet();
		if (!defaultySupplierPallet.equals("CHEP")) {
			failureList.add("Default Supplier Pallet is not as expected. Expected [CHEP] but was ["
					+ defaultySupplierPallet + "]");
		}
		
		i_navigate_to_customs_excise_tab_in_address_maintenance();
		the_CE_warehouse_type_should_be_displayed_as_excise();
		the_CE_tax_warehouse_should_be_displayed();

		Assert.assertTrue("Address details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I navigate to user defined tab in address maintenance page$")
	public void i_navigate_to_user_defined_tab_in_address_maintenance_page() throws Throwable {
		addressMaintenancePage.clickUserDefinedTab();
	}
}
