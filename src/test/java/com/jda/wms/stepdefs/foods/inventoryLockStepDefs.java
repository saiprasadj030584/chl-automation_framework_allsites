package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.inventoryLockPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class inventoryLockStepDefs {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final inventoryLockPage inventoryLockPage;

	@Inject
	public inventoryLockStepDefs(inventoryLockPage inventoryLockPage) {
		this.inventoryLockPage = inventoryLockPage;
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		inventoryLockPage.clickQueryButton();
		inventoryLockPage.enterTagId(tagId);
		inventoryLockPage.clickExecuteButton();

		String istatus = inventoryLockPage.getStatus();
		if (!istatus.equals(status)) {
			failureList.add("Status is not unlocked.");
		}
		logger.debug("Status in Inventory screen : " + istatus);
	}

	/*
	 * @When("^I choose the type of inventory property as \"([^\"]*)\"$") public
	 * void i_choose_the_type_of_inventory_property_as(String arg1) throws
	 * Throwable {
	 * 
	 * }
	 * 
	 * @When("^I search the tag ID \"([^\"]*)\"$") public void
	 * i_search_the_tag_ID(String arg1) throws Throwable {
	 * 
	 * }
	 * 
	 * @Then("^the record should be displayed in the search result$") public
	 * void the_record_should_be_displayed_in_the_search_result() throws
	 * Throwable {
	 * 
	 * }
	 * 
	 * @When("^I proceed to lock the record$") public void
	 * i_proceed_to_lock_the_record() throws Throwable {
	 * 
	 * }
	 * 
	 * @When(
	 * "^I select the status as \"([^\"]*)\", lock code as \"([^\"]*)\" and reason code as \"([^\"]*)\"$"
	 * ) public void
	 * i_select_the_status_as_lock_code_as_and_reason_code_as(String arg1,
	 * String arg2, String arg3) throws Throwable {
	 * 
	 * }
	 * 
	 * @When("^I proceed to complete the process$") public void
	 * i_proceed_to_complete_the_process() throws Throwable {
	 * 
	 * }
	 * 
	 * @Then("^the inventory update home page should be displayed$") public void
	 * the_inventory_update_home_page_should_be_displayed() throws Throwable {
	 * 
	 * }
	 * 
	 * @When("^I navigate to inventory query$") public void
	 * i_navigate_to_inventory_query() throws Throwable {
	 * 
	 * }
	 * 
	 * @Then(
	 * "^the I should see the status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the inventory query$"
	 * ) public void
	 * the_I_should_see_the_status_as_and_lock_code_as_in_the_inventory_query(
	 * String arg1, String arg2) throws Throwable {
	 * 
	 * }
	 * 
	 * @When("^I navigate to inventory transaction query$") public void
	 * i_navigate_to_inventory_transaction_query() throws Throwable {
	 * 
	 * }
	 * 
	 * @When(
	 * "^I search tag id \"([^\"]*)\" and transaction date as \"([^\"]*)\"$")
	 * public void i_search_tag_id_and_transaction_date_as(String arg1, String
	 * arg2) throws Throwable {
	 * 
	 * }
	 * 
	 * @Then(
	 * "^the I should see the status as \"([^\"]*)\" and lock code as \"([^\"]*)\" in the transaction query$"
	 * ) public void
	 * the_I_should_see_the_status_as_and_lock_code_as_in_the_transaction_query(
	 * String arg1, String arg2) throws Throwable {
	 * 
	 * }
	 */

}
