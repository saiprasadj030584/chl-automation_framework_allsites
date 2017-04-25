package com.jda.wms.stepdefs.foods;

import org.junit.Assert;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.StockCheckTaskQueryPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckTaskQueryStepDef {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockCheckTaskQueryPage stockCheckTaskQueryPage;
	private final JDAFooter jDAFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public StockCheckTaskQueryStepDef(StockCheckTaskQueryPage stockCheckTaskQueryPage, JDAFooter jDAFooter) {
		this.stockCheckTaskQueryPage = stockCheckTaskQueryPage;
		this.jDAFooter = jDAFooter;
	}

	// Stock check Task Query Screen

	@When("^I navigate to stock check query page$")
	public void i_navigate_to_stock_check_query_page() throws Throwable {
		// stockCheckTaskQueryPage//
		stockCheckTaskQueryPage.navigateToStockCheckQueryPage();
	}

	@When("^I search the list by site id as \"([^\"]*)\", location as \"([^\"]*)\" and task date as current date$")
	public void i_search_the_list_by_site_id_as_location_as_and_task_date_as_current_date(String siteId,
			String location) throws Throwable {
		jDAFooter.clickQueryButton();
		stockCheckTaskQueryPage.selectSiteId(siteId);
		stockCheckTaskQueryPage.enterLocation(location);
		stockCheckTaskQueryPage.enterTaskDate();
		jDAFooter.clickExecuteButton();
	}

	@Then("^I should see the record with list ID$")
	public void i_should_see_the_created_list() throws Throwable {
		String listId = stockCheckTaskQueryPage.getListId();
		Assert.assertNotNull("List ID is not generated as expected. Expected [not Null] but was [" + listId + "]",
				listId);
		logger.debug("List ID: " + listId);
	}

}
