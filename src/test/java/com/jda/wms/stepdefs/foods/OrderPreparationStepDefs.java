package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.foods.DeleteDataFromDB;
import com.jda.wms.dataload.foods.InsertDataIntoDB;
import com.jda.wms.dataload.foods.SelectDataFromDB;
import com.jda.wms.db.OrderHeaderDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderPreparationPage;
import com.jda.wms.pages.foods.WarningPopUpPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderPreparationStepDefs {
	private OrderPreparationPage orderPreparationPage;
	private final WarningPopUpPage warningPopUpPage;
	private final JDAFooter jdaFooter;
	private Context context;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private SystemAllocationStepDefs systemAllocationStepDefs;
	private ClusteringStepDefs clusteringStepDefs;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private OrderHeaderDB orderHeaderDB;

	@Inject
	public OrderPreparationStepDefs(OrderPreparationPage orderPreparationPage, WarningPopUpPage warningPopUpPage,
			JDAFooter jdaFooter, Context context,InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB,SystemAllocationStepDefs systemAllocationStepDefs,ClusteringStepDefs clusteringStepDefs,JDAHomeStepDefs jdaHomeStepDefs,OrderHeaderDB orderHeaderDB) {
		this.orderPreparationPage = orderPreparationPage;
		this.warningPopUpPage = warningPopUpPage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.systemAllocationStepDefs =systemAllocationStepDefs;
		this.clusteringStepDefs = clusteringStepDefs;
		this.jdaHomeStepDefs=jdaHomeStepDefs;
		this.orderHeaderDB = orderHeaderDB;
	}

	@When("^I select the group type as \"([^\"]*)\"$")
	public void i_select_the_group_type_as(String groupType) throws Throwable {
		orderPreparationPage.selectGroupType(groupType);
	}

	@When("^I enter the order id \"([^\"]*)\" for the customer \"([^\"]*)\"$")
	public void i_enter_the_order_id_for_the_customer(String orderId, String customer) throws Throwable {
		// ------------Data Setup-----------
		deleteDataFromDB.deleteOrderHeader(orderId);
		insertDataIntoDB.insertOrderHeader(orderId, "STR", customer);
		insertDataIntoDB.insertOrderLine(orderId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(orderId));
		systemAllocationStepDefs.i_system_allocate_the_order();
		clusteringStepDefs.i_create_list_ids_manually_in_clustering();
		// ------------Data Setup-----------

		context.setOrderId(orderId);
		orderPreparationPage.enterOrderId(orderId);
	}

	@When("^I enter the order id \"([^\"]*)\"$")
	public void i_enter_the_order_id(String orderId) throws Throwable {
		context.setOrderId(orderId);
		orderPreparationPage.enterOrderId(orderId);
	}
	
	@When("^I create consignment$")
	public void i_create_consignment() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_order_preparation_page();
		i_select_the_group_type_as("Consignment");
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		i_enter_the_order_id(context.getOrderId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		i_select_the_record();
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		the_total_orders_should_be_displayed_as("1");
		i_select_the_trailer_type_as_("TRAILER");
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		the_record_should_be_displayed_for_consignment_preparation_process();
		i_proceed_to_complete_the_process();
		Assert.assertNotNull("consignment is not displayed as expected",
				orderHeaderDB.getConsignment(context.getOrderId()));
	}
	
	@Then("^I select the record$")
	public void i_select_the_record() throws Throwable {
		orderPreparationPage.selectRecord();
	}

	@When("^I select the trailer type as \"([^\"]*)\"$")
	public void i_select_the_trailer_type_as_(String trailerType) throws Throwable {
		orderPreparationPage.selectTrailerType(trailerType);
	}

	@Then("^the record should be displayed for consignment preparation process$")
	public void the_record_should_be_displayed_for_consignment_preparation_process() throws Throwable {
		Assert.assertTrue("Record is not present in order preparation page", orderPreparationPage.isRecordExist());
	}

	@When("^I proceed to create the consignment$")
	public void i_proceed_to_complete_the_process() throws Throwable {
		jdaFooter.clickDoneButton();
		warningPopUpPage.clickYes();
		warningPopUpPage.clickOk();
	}

	@Then("^the total orders should be displayed as \"([^\"]*)\"$")
	public void the_total_orders_should_be_displayed_as(String order) throws Throwable {
		Assert.assertEquals("Total orders is not as expected", order, orderPreparationPage.getTotalOrder());
	}
}
