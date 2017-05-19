package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.Verification;
/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.StockTransferOrderVehicleLoadingPage;*/
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.foods.JDAHomeStepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockTransferOrderVehicleLoadingStepDefs {

	private final PuttyFunctionsPage puttyFunctionsPage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final JDAFooter jdaFooter;
	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private Verification verification;
	private JdaHomePage jdaHomePage;

	@Inject
	public StockTransferOrderVehicleLoadingStepDefs(JdaHomePage jdaHomePage, PuttyFunctionsPage puttyFunctionsPage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			Verification verification) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.verification = verification;
		this.jdaHomePage = jdaHomePage;
	}

	@When("^I navigate to Order Header Page$")
	public void i_navigate_to_Order_Header_Page() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_order_header();
	}

	@When("^I search using Order id \"([^\"]*)\"$")
	public void i_search_using_Order_id(String orderID) throws Throwable {

		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

	}

	@Then("^Order status should be \"([^\"]*)\"$")
	public void order_status_should_be(String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData("Order Status", status, orderHeaderMaintenancePage.getStatus(), failureList);
	}

	@When("^I navigate to move task and get all the Pallet IDs$")
	public void i_navigate_to_move_task_and_get_all_the_Pallet_IDs() throws Throwable {
		i_navigate_to_Move_task_page();

	}

	@When("^I navigate to Load Menu$")
	public void i_navigate_to_Load_Menu() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I perform vehicle loading for all the pallets$")
	public void i_perform_vehicle_loading_for_all_the_pallets() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see the order status as \"([^\"]*)\" in Order Header page$")
	public void i_should_see_the_order_status_as_in_Order_Header_page(String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData("Order Status", status, orderHeaderMaintenancePage.getStatus(), failureList);
	}

	@When("^I navigate to Move task page$")
	public void i_navigate_to_Move_task_page() throws Throwable {
		jdaHomePage.navigateToMoveTaskQuery();
	}

	@Then("^no record should exists for the \"([^\"]*)\"$")
	public void no_record_should_exists_for_the(String orderID) throws Throwable {

		jdaFooter.clickQueryButton();
		// orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

	}

}
