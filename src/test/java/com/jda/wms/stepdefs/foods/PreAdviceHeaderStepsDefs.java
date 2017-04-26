package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.PreAdviceHeaderPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceHeaderStepsDefs {
	private final PreAdviceHeaderPage preAdviceHeaderPage;
	private JDAFooter jdaFooter;
	private JDALoginStepDefs jdaLoginStepDefs;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private AddressMaintenancePage addressMaintenancePage;

	@Inject
	public PreAdviceHeaderStepsDefs(PreAdviceHeaderPage preAdviceHeaderPage, JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			AddressMaintenancePage addressMaintenancePage) {
		this.preAdviceHeaderPage = preAdviceHeaderPage;
		this.jdaFooter = jdaFooter;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.addressMaintenancePage = addressMaintenancePage;
	}

	@When("^the PO should be \"([^\"]*)\" status and have future due date, site id, number of lines$")
	public void the_PO_should_be_status_and_have_future_due_date_site_id_number_of_lines(String preAdviceId)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		//jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		jdaHomeStepDefs.i_am_on_to_pre_advice_header_maintenance_page();
		i_search_the_pre_advice_id(preAdviceId);

		String status = preAdviceHeaderPage.getStatus();
		if (!status.equals("In Progress")) {
			failureList.add("Status is not as expected. Expected [In Progress ] but was [" + status + "]");
		}
		String duedate = preAdviceHeaderPage.getDuedate();
		if (duedate.equals(null)) {
			failureList.add("Duedate is not as expected. Expected [Not NULL] but was [" + duedate + "]");
		}
		String siteId = preAdviceHeaderPage.getSiteId();
		if (siteId.equals(null)) {
			failureList.add("SiteId is not as expected. Expected [Not NULL] but was [" + siteId + "]");
		}
		String type = preAdviceHeaderPage.getType();
		if (!type.equals("PO")) {
			failureList.add("Type is not as expected. Expected [PO] but was [" + type + "]");
		}
		String supplier = preAdviceHeaderPage.getSupplier();
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}
		String numberoflines = preAdviceHeaderPage.getNumberoflines();
		if (numberoflines.equals(null)) {
			failureList.add("Numberoflines is not as expected. Expected [Not NULL] but was [" + numberoflines + "]");
		}
		Assert.assertTrue("PreAdvice Header details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the PO should have address details in the pre-advice header maintenance table$")
	public void the_PO_should_have_address1_name_and_country_in_the_pre_advice_header_maintenance_table()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String name = preAdviceHeaderPage.getName();
		{
			if (name.equals(null)) {
				failureList.add("Name is not as expected. Expected [Not NULL] but was [" + name + "]");
			}
			String address1 = preAdviceHeaderPage.getAddress1();
			if (address1.equals(null)) {
				failureList.add(" Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
			}
			String country = preAdviceHeaderPage.getCountry();
			if (country.equals(null)) {
				failureList.add(" Country is not as expected. Expected [Not NULL] but was [" + country + "]");
			}
			Assert.assertTrue("Address details are not as expected." + Arrays.asList(failureList.toString()),
					failureList.isEmpty());
		}
	}

	@When("^I search the pre advice id \"([^\"]*)\"$")
	public void i_search_the_pre_advice_id(String preAdviceId) throws Throwable {
		jdaFooter.clickQueryButton();
		preAdviceHeaderPage.enterPreAdviceID(preAdviceId);
		jdaFooter.clickExecuteButton();
	}

	@When("^I navigate to address tab in pre-advice header maintenance page$")
	public void i_navigate_to_address_tab_in_pre_advice_header_maintenance_page() throws Throwable {
		preAdviceHeaderPage.clickAddressTab();
	}

	@Then("^the supplier should have supplier pallet details in the address maintenanace table$")
	public void the_supplier_should_have_supplier_pallet_details_in_the_address_maintenanace_table() throws Throwable {
		Assert.assertEquals("The default supplier pallet is not as expected.", "CHEP",
				addressMaintenancePage.getDefaultSupplierPallet());
	}

	@Then("^I navigate to user defined tab in address maintenance page$")
	public void i_navigate_to_user_defined_tab_in_address_maintenance_page() throws Throwable {
		addressMaintenancePage.clickUserDefinedTab();
	}
}
	

