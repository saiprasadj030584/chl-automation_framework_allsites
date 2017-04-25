package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.StockCheckListGenerationPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckListGenerationStepDef {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockCheckListGenerationPage stockCheckListGenerationPage;
	private final JDAFooter jDAFooter;

	@Inject
	public StockCheckListGenerationStepDef(StockCheckListGenerationPage stockCheckListGenerationPage,
			JDAFooter jDAFooter) {
		this.stockCheckListGenerationPage = stockCheckListGenerationPage;
		this.jDAFooter = jDAFooter;
	}

	// Stock Check List Generation screen
	@When("^I navigate to stock check list Generation page$")
	public void i_navigate_to_stock_check_list_Generation_screen() throws Throwable {
		Thread.sleep(3000);
		stockCheckListGenerationPage.navigateToStockCheckListGeneration();
	}

	@When("^I select mode of list generation as 'Generate by location'$")
	public void i_select_mode_of_list_generation_as_Generate_by_location() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByLocation();
	}

	@When("^I select the site ID as \"([^\"]*)\" and from location as \"([^\"]*)\" on location tab$")
	public void i_select_the_site_ID_as_and_from_location_as_on_location_tab(String siteId, String fromLocation)
			throws Throwable {
		stockCheckListGenerationPage.setSiteIDandLocation(siteId, fromLocation);
	}

	@When("^I proceed to next$")
	public void i_proceed_to_next() throws Throwable {
		jDAFooter.clickExecuteButton();
	}

	@Then("^the available list should be displayed$")
	public void the_available_list_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		if (stockCheckListGenerationPage.isListAvailable() != true) {
			failureList.add("No records available to select");
		} else {
			logger.debug("Records available to generate list");
		}

		Assert.assertTrue("Available List not displayed." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@When("^I select the record from the available list$")
	public void i_select_the_record_from_the_available_list() throws Throwable {
		stockCheckListGenerationPage.clickAddButton();
		logger.debug("Record added to generate list");
	}

	@Then("^the record should be added in the selected list$")
	public void the_record_should_be_added_in_the_selected_list() throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		stockCheckListGenerationPage.navigateToSelectedTab();

		if (stockCheckListGenerationPage.isSelectedListAvailable() != true) {
			failureList.add("Records not displayed in selected tab");
		} else {
			logger.debug("Records added to selected list");
		}
		Assert.assertTrue("Stock Check List not generated." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

	}

	@Then("^I should see the confirmation for number of location checked$")
	public void i_should_see_the_confirmation_for_number_of_location_checked() throws Throwable {
		// stockCheckListGenerationPage
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		ArrayList<String> failureList = new ArrayList<String>();
		if (getSelectedListConfirmationText.contains("You have not selected any tasks")) {
			failureList.add("Stock Check list confirmation text not displayed");
		} else {
			logger.debug("Stock Check list Confirmation : " + getSelectedListConfirmationText);
		}
		Assert.assertTrue("Stock Check List not generated as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I should see the confirmation for number of items checked$")
	public void i_should_see_the_confirmation_for_number_of_items_checked() throws Throwable {
		// stockCheckListGenerationPage
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		ArrayList<String> failureList = new ArrayList<String>();
		if (getSelectedListConfirmationText.contains("You have not selected any tasks")) {
			failureList.add("Stock Check list confirmation text not displayed");
		} else {
			logger.debug("Stock Check list Confirmation : " + getSelectedListConfirmationText);
		}
		Assert.assertTrue("Stock Check List not generated as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@When("^I proceed to generate the stock check list$")
	public void i_proceed_to_generate_the_stock_check_list() throws Throwable {
		stockCheckListGenerationPage.clickDoneButton();
	}

	@Then("^I should see the created list$")
	public void i_should_see_the_created_list() throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		if (stockCheckListGenerationPage.isListIdPopupDisplayed()) {
			logger.debug("List ID Information Pop-up displayed");
		} else {
			failureList.add("Stock Check list List ID Information pop-up not displayed");
		}

		Assert.assertTrue("Stock Check List not generated as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

	}

	@When("^I select mode of list generation as 'Generate by inventory'$")
	public void i_select_mode_of_list_generation_as_Generate_by_inventory() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByInventory();
	}

	@When("^I enter the Tag ID as \"([^\"]*)\" on inventory tab$")
	public void i_enter_the_Tag_ID_as_on_inventory_tab(String tagId) throws Throwable {
		stockCheckListGenerationPage.enterTagId(tagId);
	}

	@When("^I search the list by tag id as \"([^\"]*)\"$")
	public void i_search_the_list_by_tag_id_as(String arg1) throws Throwable {
		jDAFooter.clickExecuteButton();
	}

}
