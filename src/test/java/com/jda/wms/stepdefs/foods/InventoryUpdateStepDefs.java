package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.InventoryUpdatePage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryUpdateStepDefs {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryUpdatePage inventoryUpdatePage;
	private final JDAFooter jDAFooter;
	private final Context context;
	private final InventoryQueryPage inventoryQueryPage;

	@Inject
	public InventoryUpdateStepDefs(InventoryUpdatePage inventoryUpdatePage, JDAFooter jDAFooter,
			Context context,InventoryQueryPage inventoryQueryPage) {
		this.inventoryUpdatePage = inventoryUpdatePage;
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
		this.context=context;
	}

	@When("^I choose the type of inventory property as \"([^\"]*)\"$")
	public void i_choose_the_type_of_inventory_property_as(String selectType) throws Throwable {
		inventoryUpdatePage.selectType(selectType);
		jDAFooter.clickNextButton();
	}

	@When("^I search the tag ID \"([^\"]*)\"$")
	public void i_search_the_tag_ID(String tagId) throws Throwable {
		inventoryUpdatePage.enterTagId(tagId);
		jDAFooter.clickNextButton();
	}

	@Then("^the record should be displayed in the search result$")
	public void the_record_should_be_displayed_in_the_search_result() throws Throwable {
		Assert.assertTrue("No records are present for this tag Id", inventoryUpdatePage.isRecordExists());
		jDAFooter.clickNextButton();
	}

	@When("^I proceed to lock the record$")
	public void i_proceed_to_lock_the_record() throws Throwable {
		inventoryUpdatePage.clickNext();
	}

	@When("^I select the status as \"([^\"]*)\", lock code as \"([^\"]*)\" and reason code as \"([^\"]*)\"$")
	public void i_select_the_status_as_lock_code_as_and_reason_code_as(String status, String lockCode,
			String reasonCode) throws Throwable {
		inventoryUpdatePage.selectStatus(status);
		inventoryUpdatePage.selectLockCode(lockCode);
		inventoryUpdatePage.selectReasonCode(reasonCode);
	}

	@When("^I proceed to complete the process$")
	public void i_proceed_to_complete_the_process() throws Throwable {
		jDAFooter.clickExecuteButton();
	}
	
	@Then("^the inventory update home page should be displayed$")
	public void the_inventory_update_home_page_should_be_displayed() throws Throwable {
		Assert.assertTrue("Inventory lock unsuccessful", inventoryUpdatePage.verifyHomePage());
	}
	@When("^I enter ABV value as \"([^\"]*)\"$")
	public void i_enter_ABV_value_as(String newAbv) throws Throwable {
		context.setABV(newAbv);
		inventoryUpdatePage.enterABV(newAbv);
	}
	@When("^I select the reason code as \"([^\"]*)\"$")
	public void i_select_the_reason_code_as(String reasonCode) throws Throwable {
		inventoryUpdatePage.selectReasonCode(reasonCode);
		jDAFooter.clickDone();
	}

}
