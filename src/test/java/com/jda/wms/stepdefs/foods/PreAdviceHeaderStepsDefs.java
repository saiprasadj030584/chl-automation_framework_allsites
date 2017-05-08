package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.PreAdviceHeaderPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final PreAdviceHeaderPage preAdviceHeaderPage;
	private JDAFooter jdaFooter;
	private JDALoginStepDefs jdaLoginStepDefs;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private AddressMaintenancePage addressMaintenancePage;
	private Context context;

	@Inject
	public PreAdviceHeaderStepsDefs(PreAdviceHeaderPage preAdviceHeaderPage, JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			AddressMaintenancePage addressMaintenancePage, Context context) {
		this.preAdviceHeaderPage = preAdviceHeaderPage;
		this.jdaFooter = jdaFooter;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.addressMaintenancePage = addressMaintenancePage;
		this.context = context;
	}

	@When("^the PO \"([^\"]*)\" should be \"([^\"]*)\" status and have future due date, site id, number of lines$")
	public void the_PO_should_be_status_and_have_future_due_date_site_id_number_of_lines(String purchaseOrder,
			String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		// jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		jdaHomeStepDefs.i_am_on_to_pre_advice_header_maintenance_page();
		i_search_the_pre_advice_id(purchaseOrder);

		String statusPreAdviceHeader = preAdviceHeaderPage.getStatus();
		System.out.println(statusPreAdviceHeader);
		if (!statusPreAdviceHeader.equals(status)) {
			failureList.add(
					"Status is not as expected. Expected [" + status + "] but was [" + statusPreAdviceHeader + "]");
		}
		String duedate = preAdviceHeaderPage.getDuedate();
		if (duedate.equals(null)) {
			failureList.add("Duedate is not as expected. Expected [Not NULL] but was [" + duedate + "]");
		}
		String siteId = preAdviceHeaderPage.getSiteId();
		if (siteId.equals(null)) {
			failureList.add("SiteId is not as expected. Expected [Not NULL] but was [" + siteId + "]");
		}

		String supplier = preAdviceHeaderPage.getSupplier();
		context.setSupplierID(supplier);
		logger.debug("Supplier Id: " + supplier);
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}
         
		
		int numberOfLines = Integer.parseInt(preAdviceHeaderPage.getNumberOfLines());
		context.setNoOfLines(numberOfLines);
		if (numberOfLines < 0) {
			failureList.add("Numberoflines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}

		Assert.assertTrue("PreAdvice Header details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I should see the type is updated as PO$")
	public void i_should_see_the_type_is_updated_as_PO() throws Throwable {
		Assert.assertTrue("The TYPE is not a PO expected, [PO] but was [not PO]", preAdviceHeaderPage.isTypeExist());
	}

	@Then("^the PO should have address details in the pre-advice header maintenance table$")
	public void the_PO_should_have_address1_name_and_country_in_the_pre_advice_header_maintenance_table()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		i_navigate_to_address_tab_in_pre_advice_header_maintenance_page();

		String name = preAdviceHeaderPage.getName();
		context.setName(name);
		if (name.equals(null)) {
			failureList.add("Name is not as expected. Expected [Not NULL] but was [" + name + "]");
		}

		String address1 = preAdviceHeaderPage.getAddress1();
		context.setAddress1(address1);
		if (address1.equals(null)) {
			failureList.add(" Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
		}

		String country = preAdviceHeaderPage.getCountry();
		context.setCountry(country);
		if (country.equals(null)) {
			failureList.add(" Country is not as expected. Expected [Not NULL] but was [" + country + "]");
		}

		Assert.assertTrue("Address details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
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
		ArrayList<String> failureList = new ArrayList<String>();

		jdaHomeStepDefs.i_navigate_to_address_maintenance_page();
		jdaFooter.clickQueryButton();
		// addressMaintenancePage.enterAddressID(context.getSupplierID());
		addressMaintenancePage.enterAddressID("F01946");
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
		if (!context.getCountry().equals(country)) {
			failureList.add(
					"Country is not as expected. Expected [" + context.getCountry() + "] but was [" + country + "]");
		}

		i_navigate_to_user_defined_tab_in_address_maintenance_page();
		String defaultySuppleirPallet = addressMaintenancePage.getDefaultSupplierPallet();
		if (!defaultySuppleirPallet.equals("CHEP")) {
			failureList.add("Default Supplier Pallet is not as expected. Expected [CHEP] but was ["
					+ defaultySuppleirPallet + "]");
		}

		Assert.assertTrue("Address details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I navigate to user defined tab in address maintenance page$")
	public void i_navigate_to_user_defined_tab_in_address_maintenance_page() throws Throwable {
		// jdaFooter.clickQueryButton();
		// addressMaintenancePage.enterAddressID(context.getSupplierID());
		// jdaFooter.clickExecuteButton();
		addressMaintenancePage.clickUserDefinedTab();
	}
}
