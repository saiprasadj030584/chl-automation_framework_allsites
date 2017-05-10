package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.StockCheckListGenerationPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckListGenerationStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockCheckListGenerationPage stockCheckListGenerationPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	
	@Inject
	public StockCheckListGenerationStepDefs(StockCheckListGenerationPage stockCheckListGenerationPage,
			JDAFooter jdaFooter,JdaHomePage jdaHomePage) {
		this.stockCheckListGenerationPage = stockCheckListGenerationPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
	}

	@When("^I navigate to stock check list Generation page$")
	public void i_navigate_to_stock_check_list_Generation_screen() throws Throwable {
		Thread.sleep(3000);
		jdaHomePage.navigateToStockCheckListGeneration();
		Thread.sleep(3000);
	}

	@When("^I select mode of list generation as 'Generate by location'$")
	public void i_select_mode_of_list_generation_as_Generate_by_location() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByLocation();
		Thread.sleep(1000);
	}

	@When("^I select the site ID as \"([^\"]*)\" and from location as \"([^\"]*)\" on location tab$")
	public void i_select_the_site_ID_as_and_from_location_as_on_location_tab(String siteId, String fromLocation)
			throws Throwable {
		stockCheckListGenerationPage.selectSiteId(siteId);
		stockCheckListGenerationPage.enterLocation(fromLocation);
		Thread.sleep(1000);
	}

	@When("^I proceed to next tab$")
	public void i_proceed_to_next_tab() throws Throwable {
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}

	@Then("^the available list should be displayed$")
	public void the_available_list_should_be_displayed() throws Throwable {
		Assert.assertTrue("Available List is not displayed.", stockCheckListGenerationPage.isListAvailable());
		Thread.sleep(1000);
	}

	@When("^I select the record from the available list$")
	public void i_select_the_record_from_the_available_list() throws Throwable {
		stockCheckListGenerationPage.clickAddButton();
		logger.debug("Record added to generate list");
		Thread.sleep(1000);
	}

	@Then("^the record should be added in the selected list$")
	public void the_record_should_be_added_in_the_selected_list() throws Throwable {
		stockCheckListGenerationPage.navigateToSelectedTab();
		Assert.assertTrue("Stock Check List not generated.", stockCheckListGenerationPage.isSelectedListAvailable());
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for number of location checked$")
	public void i_should_see_the_confirmation_for_number_of_location_checked() throws Throwable {
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		Assert.assertTrue("Stock Check List not generated as expected. " + getSelectedListConfirmationText,
				getSelectedListConfirmationText.contains("You have selected:"));
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for number of items checked$")
	public void i_should_see_the_confirmation_for_number_of_items_checked() throws Throwable {
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		ArrayList<String> failureList = new ArrayList<String>();
		if (getSelectedListConfirmationText.contains("You have not selected any tasks")) {
			failureList.add("Stock Check list confirmation text not displayed");
		} else {
			logger.debug("Stock Check list Confirmation : " + getSelectedListConfirmationText);
		}
		Assert.assertTrue("Stock Check List not generated as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		Thread.sleep(1000);
	}

	@When("^I proceed to generate the stock check list$")
	public void i_proceed_to_generate_the_stock_check_list() throws Throwable {
		jdaFooter.clickDoneButton();
		Thread.sleep(1000);
	}

	@Then("^I should see the created list$")
	public void i_should_see_the_created_list() throws Throwable {
		Assert.assertTrue("Stock Check List not generated as expected.",
				stockCheckListGenerationPage.isListIdPopupDisplayed());
		Thread.sleep(1000);
	}

	@When("^I select mode of list generation as 'Generate by inventory'$")
	public void i_select_mode_of_list_generation_as_Generate_by_inventory() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByInventory();
	}

	@When("^I enter the Tag ID as \"([^\"]*)\" on inventory tab$")
	public void i_enter_the_Tag_ID_as_on_inventory_tab(String tagId) throws Throwable {
		stockCheckListGenerationPage.enterTagId(tagId);
	}
}
