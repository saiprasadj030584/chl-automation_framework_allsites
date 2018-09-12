package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.DeleteDataFromDB;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.dataload.exit.InsertDataIntoDB;
import com.jda.wms.dataload.exit.SelectDataFromDB;
import com.jda.wms.db.Exit.Database;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.PreAdviceHeaderPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final PreAdviceHeaderPage preAdviceHeaderPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private AddressMaintenancePage addressMaintenancePage;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private final Database database;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private DataSetupRunner dataSetupRunner;
	private GetTCData getTCData;

	@Inject
	public PreAdviceHeaderStepsDefs(PreAdviceHeaderPage preAdviceHeaderPage, JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			AddressMaintenancePage addressMaintenancePage, Context context, PreAdviceHeaderDB preAdviceHeaderDB,
			Database database, InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, DataSetupRunner dataSetupRunner, GetTCData getTCData) {
		this.preAdviceHeaderPage = preAdviceHeaderPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.addressMaintenancePage = addressMaintenancePage;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.database = database;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.dataSetupRunner = dataSetupRunner;
		this.getTCData = getTCData;
	}

	@Given("^the PO \"([^\"]*)\" with \"([^\"]*)\" category should be \"([^\"]*)\" status and have future due date, site id, number of lines in the pre-advice header maintenance table$")
	public void the_PO_with_category_should_be_status_and_have_future_due_date_site_id_number_of_lines_in_the_pre_advice_header_maintenance_table(
			String preAdviceId, String productCategory, String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setPreAdviceId(preAdviceId);
		context.setProductCategory(productCategory);
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		jdaHomeStepDefs.i_am_on_to_pre_advice_header_maintenance_page();
		i_search_the_pre_advice_id(preAdviceId);

		String statusPreAdviceHeader = preAdviceHeaderPage.getStatus();
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

		boolean isType = preAdviceHeaderPage.isTypeExist();
		if (!isType) {
			failureList.add("Type is not displayed as PO");
		}

		int numberOfLines = Integer.parseInt(preAdviceHeaderPage.getNumberOfLines());
		context.setNoOfLines(numberOfLines);
		if (numberOfLines < 0) {
			failureList.add("Numberoflines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}

		Assert.assertTrue("PreAdvice Header details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the PO with \"([^\"]*)\" category should be \"([^\"]*)\" status and have future due date, site id, no of lines in the pre-advice header maintenance table$")
	public void the_PO_with_category_should_be_status_and_have_future_due_date_site_id_no_of_lines_in_the_pre_advice_header_maintenance_table(
			String productCategory, String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		// ------------------Data Set up Modified---------------
		context.setProductCategory(productCategory);
//		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertPreAdviceDataTrial();
		String preAdviceId = getTCData.getPO();
		System.out.println("New Pre Advice ID " + preAdviceId);
		// ------------------Data Set up Modified---------------

		// ------------Data Setup OLD-----------
		// deleteDataFromDB.deletePreAdviceHeader(preAdviceId);
		// insertDataIntoDB.insertPreAdviceHeader(preAdviceId);
		// insertDataIntoDB.insertPreAdviceLine(preAdviceId, productCategory);
		// Thread.sleep(3000);
		// Assert.assertTrue("Test Data not available - Issue in Data loading",
		// selectDataFromDB.isPreAdviceRecordExists(preAdviceId));
		// ------------Data Setup-----------
		context.setPreAdviceId(preAdviceId);
		HashMap<String, String> preAdviceHeaderDbDetails = preAdviceHeaderDB.getPreAdviceHeaderDetails(preAdviceId);

		String preAdviceOrderStatus = preAdviceHeaderDbDetails.get("STATUS");
		if (!preAdviceOrderStatus.equals(status)) {
			failureList
					.add("Status is not as expected. Expected [" + status + "] but was [" + preAdviceOrderStatus + "]");
		}

		String dueDate = preAdviceHeaderDbDetails.get("DUEDATE");
		if (dueDate.equals(null)) {
			failureList.add("Duedate is not as expected. Expected [Not NULL] but was [" + dueDate + "]");
		}

		String siteId = preAdviceHeaderDbDetails.get("SITEID");
		if (siteId.equals(null)) {
			failureList.add("SiteId is not as expected. Expected [Not NULL] but was [" + siteId + "]");
		}

		String supplier = preAdviceHeaderDbDetails.get("SUPPLIERID");
		context.setSupplierID(supplier);
		logger.debug("Supplier Id: " + supplier);
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}

		String type = preAdviceHeaderDbDetails.get("PREADVICETYPE");
		if (type.equals(null)) {
			failureList.add("Type is not displayed as expected. Expected [PO] but was [" + type + "]");
		}

		int numberOfLines = Integer.parseInt(preAdviceHeaderDbDetails.get("NUMLINES"));
		System.out.println("*****************************" + numberOfLines);
		context.setNoOfLines(numberOfLines);
		if (numberOfLines < 0) {
			failureList.add("Numberoflines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}

		Assert.assertTrue("PreAdvice Header details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the \"([^\"]*)\" category PO in \"([^\"]*)\" status with more than one line items and have future due date, site id, number of lines in the pre-advice header maintenance table$")
	public void the_category_PO_in_status_with_more_than_one_line_items_and_have_future_due_date_site_id_number_of_lines_in_the_pre_advice_header_maintenance_table(
			String productCategory,String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		//------------------Data Set up Modified---------------
		context.setProductCategory(productCategory);
		dataSetupRunner.insert2PreAdviceData();
		String preAdviceId = getTCData.getPO();
		System.out.println("New Pre Advice ID " + preAdviceId);
		// ------------------Data Set up Modified---------------
		
//		// ------------Data Setup-----------
//		deleteDataFromDB.deletePreAdviceHeader(preAdviceId);
//		insertDataIntoDB.insertPreAdviceHeader(preAdviceId);
//		insertDataIntoDB.insertPreAdviceLine(preAdviceId, productCategory);
//		Thread.sleep(3000);
//		Assert.assertTrue("Test Data not available - Issue in Data loading",
//				selectDataFromDB.isPreAdviceRecordExists(preAdviceId));
//		// ------------Data Setup-----------

		context.setPreAdviceId(preAdviceId);
		context.setProductCategory(productCategory);

//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
//		jdaHomeStepDefs.i_am_on_to_pre_advice_header_maintenance_page();
//		i_search_the_pre_advice_id(preAdviceId);

		String statusPreAdviceHeader = preAdviceHeaderDB.getStatus(context.getPreAdviceId());
		if (!statusPreAdviceHeader.equals(status)) {
			failureList.add(
					"Status is not as expected. Expected [" + status + "] but was [" + statusPreAdviceHeader + "]");
		}
		String duedate = preAdviceHeaderDB.getDueDate(context.getPreAdviceId());
		if (duedate.equals(null)) {
			failureList.add("Duedate is not as expected. Expected [Not NULL] but was [" + duedate + "]");
		}
		String siteId = preAdviceHeaderDB.getSiteId(context.getPreAdviceId());
		if (siteId.equals(null)) {
			failureList.add("SiteId is not as expected. Expected [Not NULL] but was [" + siteId + "]");
		}

		String supplier =preAdviceHeaderDB.getSupplierId(context.getPreAdviceId());
		context.setSupplierID(supplier);
		logger.debug("Supplier Id: " + supplier);
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}

		String orderType = preAdviceHeaderDB.getPOType(context.getPreAdviceId());
		
		if (!orderType.contains("PO")) {
			failureList.add("Order Type is not displayed as PO");
		}

//		int numberOfLines = Integer.parseInt(preAdviceHeaderPage.getNumberOfLines());
		int numberOfLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(context.getPreAdviceId()));
		context.setNoOfLines(numberOfLines);
		if (numberOfLines <= 1) {
			failureList.add("Number of lines is not as expected. Expected [>1] but was [" + numberOfLines + "]");
		}

		Assert.assertTrue("PreAdvice Header details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I should see the type is updated as PO$")
	public void i_should_see_the_type_is_updated_as_PO() throws Throwable {
		Assert.assertTrue("The TYPE is not a PO expected, [PO] but was [not PO]", preAdviceHeaderPage.isTypeExist());
	}

	@Then("^the pre advice status should be displayed as \"([^\"]*)\"$")
	public void the_pre_advice_status_should_be_displayed_as(String status) throws Throwable {
		Assert.assertEquals("PO Status does not match", status, preAdviceHeaderDB.getStatus(context.getPreAdviceId()));
	}

	@Given("^the PO should have address details$")
	public void the_PO_should_have_address_details() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
//		i_navigate_to_address_tab_in_pre_advice_header_maintenance_page();

		String name = preAdviceHeaderDB.getName(context.getPreAdviceId());
		context.setName(name);
		if (name.equals(null)) {
			failureList.add("Name is not as expected. Expected [Not NULL] but was [" + name + "]");
		}

		String address1 =  preAdviceHeaderDB.getAddress(context.getPreAdviceId());
		context.setAddress1(address1);
		if (address1.equals(null)) {
			failureList.add(" Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
		}

		String country = preAdviceHeaderDB.getCountry(context.getPreAdviceId());
		context.setCountry(country);
		if (country.equals(null)) {
			failureList.add(" Country is not as expected. Expected [Not NULL] but was [" + country + "]");
		}

		Assert.assertTrue("Address details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the PO should have address detail$")
	public void the_PO_should_have_address_detail() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		HashMap<String, String> preAdviceHeaderDbDetails = preAdviceHeaderDB
				.getPreAdviceHeaderDetails(context.getPreAdviceId());

		String name = preAdviceHeaderDbDetails.get("NAME");
		context.setName(name);
		if (name.equals(null)) {
			failureList.add("Name is not as expected. Expected [Not NULL] but was [" + name + "]");
		}

		String address1 = preAdviceHeaderDbDetails.get("ADDRESS1");
		context.setAddress1(address1);
		if (address1.equals(null)) {
			failureList.add(" Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
		}

		String country = preAdviceHeaderDbDetails.get("COUNTRY");
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

	@Given("^the supplier should have supplier pallet details$")
	public void the_supplier_should_have_supplier_pallet_details() throws Throwable {
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
		if (!context.getCountry().equals(country)) {
			failureList.add(
					"Country is not as expected. Expected [" + context.getCountry() + "] but was [" + country + "]");
		}
		addressMaintenancePage.clickUserDefinedTab();
		String defaultySuppleirPallet = addressMaintenancePage.getDefaultSupplierPallet();
		if (!defaultySuppleirPallet.equals("CHEP")) {
			failureList.add("Default Supplier Pallet is not as expected. Expected [CHEP] but was ["
					+ defaultySuppleirPallet + "]");
		}

		Assert.assertTrue("Address details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the PO \"([^\"]*)\" should be \"([^\"]*)\" status and have line items$")
	public void the_PO_should_be_status_and_have_line_items(String preAdviceId, String status) throws Throwable {
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
	}

	@Given("^the PO with \"([^\"]*)\" category should be \"([^\"]*)\" status$")
	public void the_PO_should_be_status(String productCategory, String status) throws Throwable {
		// ------------------Data Set up Modified---------------
		context.setProductCategory(productCategory);
		dataSetupRunner.insertPreAdviceData();
		String preAdviceId = getTCData.getPO();
		System.out.println("New Pre Advice ID " + preAdviceId);
		// ------------------Data Set up Modified---------------

//		// ------------Data Setup-----------
//		deleteDataFromDB.deletePreAdviceHeader(preAdviceId);
//		insertDataIntoDB.insertPreAdviceHeader(preAdviceId);
//		insertDataIntoDB.insertPreAdviceLine(preAdviceId, "Ambient");
//		// Assert.assertTrue("Test Data not available - Issue in Data loading",
//		// selectDataFromDB.isPreAdviceRecordExists(preAdviceId));
//		Thread.sleep(5000);
//		// ------------Data Setup-----------
		context.setPreAdviceId(preAdviceId);
		String statusPreAdviceHeader = preAdviceHeaderDB.getStatus(preAdviceId);
		if (!statusPreAdviceHeader.equals(status)) {
			preAdviceHeaderDB.updateStatus(preAdviceId, status);
		}

		int numberOfLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		Assert.assertNotNull("Number of lines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]",
				numberOfLines);
		context.setNoOfLines(numberOfLines);
	}

	@Given("^a PO should be \"([^\"]*)\" status$")
	public void a_PO_should_be_status(String status) throws Throwable {
		ArrayList failureList = new ArrayList();
		int numberOfLines = 0;
		String preAdviceId = preAdviceHeaderDB.getPreAdviceId(status);
		if (preAdviceId.contains("Exhausted Resultset")) {
			failureList.add("No Completed PO Data available. Exception message " + preAdviceId);
		} else {
			context.setPreAdviceId(preAdviceId);
			numberOfLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
			Assert.assertNotNull(
					"Number of lines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]",
					numberOfLines);
			context.setNoOfLines(numberOfLines);
		}
		Assert.assertTrue("Test Data issue. [" + Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the status should be diaplayed as \"([^\"]*)\"$")
	public void the_status_should_be_displayed(String status) throws Throwable {
		Assert.assertEquals("PO Status does not match", status, preAdviceHeaderDB.getStatus(context.getPreAdviceId()));
	}
	

	@Given("^the PO with \"([^\"]*)\" category should be \"([^\"]*)\" status and have future due date, site id, no of lines in the pre-advice header maintenance table trail$")
	public void the_PO_with_category_should_be_status_and_have_future_due_date_site_id_no_of_lines_in_the_pre_advice_header_maintenance_table_trial(
			String productCategory, String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		// ------------------Data Set up Modified---------------
		context.setProductCategory(productCategory);
		dataSetupRunner.insertPreAdviceDataTrial();
		String preAdviceId = getTCData.getPO();
		System.out.println("New Pre Advice ID " + preAdviceId);
		// ------------------Data Set up Modified---------------

		// ------------Data Setup OLD-----------
		// deleteDataFromDB.deletePreAdviceHeader(preAdviceId);
		// insertDataIntoDB.insertPreAdviceHeader(preAdviceId);
		// insertDataIntoDB.insertPreAdviceLine(preAdviceId, productCategory);
		// Thread.sleep(3000);
		// Assert.assertTrue("Test Data not available - Issue in Data loading",
		// selectDataFromDB.isPreAdviceRecordExists(preAdviceId));
		// ------------Data Setup-----------
		context.setPreAdviceId(preAdviceId);
		HashMap<String, String> preAdviceHeaderDbDetails = preAdviceHeaderDB.getPreAdviceHeaderDetails(preAdviceId);

		String preAdviceOrderStatus = preAdviceHeaderDbDetails.get("STATUS");
		if (!preAdviceOrderStatus.equals(status)) {
			failureList
					.add("Status is not as expected. Expected [" + status + "] but was [" + preAdviceOrderStatus + "]");
		}

		String dueDate = preAdviceHeaderDbDetails.get("DUEDATE");
		if (dueDate.equals(null)) {
			failureList.add("Duedate is not as expected. Expected [Not NULL] but was [" + dueDate + "]");
		}

		String siteId = preAdviceHeaderDbDetails.get("SITEID");
		if (siteId.equals(null)) {
			failureList.add("SiteId is not as expected. Expected [Not NULL] but was [" + siteId + "]");
		}

		String supplier = preAdviceHeaderDbDetails.get("SUPPLIERID");
		context.setSupplierID(supplier);
		logger.debug("Supplier Id: " + supplier);
		if (supplier.equals(null)) {
			failureList.add("Supplier is not as expected. Expected [Not NULL] but was [" + supplier + "]");
		}

		String type = preAdviceHeaderDbDetails.get("PREADVICETYPE");
		if (type.equals(null)) {
			failureList.add("Type is not displayed as expected. Expected [PO] but was [" + type + "]");
		}

		int numberOfLines = Integer.parseInt(preAdviceHeaderDbDetails.get("NUMLINES"));
		System.out.println("*****************************" + numberOfLines);
		context.setNoOfLines(numberOfLines);
		if (numberOfLines < 0) {
			failureList.add("Numberoflines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}

		Assert.assertTrue("PreAdvice Header details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}}
	
	