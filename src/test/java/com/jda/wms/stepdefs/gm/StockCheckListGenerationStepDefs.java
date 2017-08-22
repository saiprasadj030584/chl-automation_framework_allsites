package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.StockCheckListGenerationPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckListGenerationStepDefs {
	private StockCheckListGenerationPage stockCheckListGenerationPage;
	private JDAFooter jdaFooter;
	private JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;
	private Context context;
	private LocationDB locationDB;

	@Inject
	public StockCheckListGenerationStepDefs(StockCheckListGenerationPage stockCheckListGenerationPage,
			JDAFooter jdaFooter, JdaHomePage jdaHomePage, InventoryDB inventoryDB, Context context,
			LocationDB locationDB) {
		this.stockCheckListGenerationPage = stockCheckListGenerationPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.inventoryDB = inventoryDB;
		this.context = context;
		this.locationDB = locationDB;
	}

	@When("^I select 'Generate by inventory'$")
	public void i_select_Generate_by_inventory() throws Throwable {
		stockCheckListGenerationPage.selectGenerateByInventory();
		Thread.sleep(1000);
		jdaFooter.clickNextButton();
	}

	@When("^I enter total quantity in miscelloneus tab$")
	public void i_enter_total_quantity_in_miscelloneus_tab() throws Throwable {
		String quantity = String.valueOf(Utilities.randIntBetween2Values(1, 100));
		stockCheckListGenerationPage.clickMiscelloneousTab();
		stockCheckListGenerationPage.enterquantity(quantity);
		jdaFooter.clickNextButton();
	}

	@When("^I enter the tag ID as on inventory tab$")
	public void i_enter_the_tag_ID_as_on_inventory_tab() throws Throwable {
		context.setTagId(inventoryDB.getTagId());
		// stockCheckListGenerationPage.enterTagId(context.getTagId());
		stockCheckListGenerationPage.enterTagId("11079");
		jdaFooter.clickNextButton();
	}

	@Then("^the available list should be displayed$")
	public void the_available_list_should_be_displayed() throws Throwable {
		Assert.assertTrue("Available List is not displayed.", stockCheckListGenerationPage.isListAvailable());
		Thread.sleep(1000);
	}

	@When("^I select the record from the available list$")
	public void i_select_the_record_from_the_available_list() throws Throwable {
		stockCheckListGenerationPage.clickAddButton();
		Thread.sleep(1000);
	}

	@Then("^the record should be added in the selected list$")
	public void the_record_should_be_added_in_the_selected_list() throws Throwable {
		stockCheckListGenerationPage.navigateToSelectedTab();
		Assert.assertTrue("Stock Check List not generated.", stockCheckListGenerationPage.isSelectedListAvailable());
		Thread.sleep(1000);
	}

	@When("^I proceed to next tab$")
	public void i_proceed_to_next_tab() throws Throwable {
		jdaFooter.clickNextButton();
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for number of items checked$")
	public void i_should_see_the_confirmation_for_number_of_items_checked() throws Throwable {
		String getSelectedListConfirmationText = stockCheckListGenerationPage.getSelectedListConfirmationText();
		Assert.assertTrue("Stock Check List not generated as expected. " + getSelectedListConfirmationText,
				getSelectedListConfirmationText.contains("You have selected:"));
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
}
