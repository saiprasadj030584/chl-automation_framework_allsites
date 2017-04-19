package com.jda.wms.stepdefs.foods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.KitLineMaintenancePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class KitLineMaintenanceStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final KitLineMaintenancePage kitLineMaintenancePage;

	@Inject
	public KitLineMaintenanceStepDefs(KitLineMaintenancePage kitLineMaintenancePage) {
		this.kitLineMaintenancePage = kitLineMaintenancePage;
	}

	@Given("^I am on kit line maintenance page$")
	public void i_am_on_kit_line_maintenance_page() throws Throwable {
		Thread.sleep(1000);
		kitLineMaintenancePage.navigateToKitLine();

	}

	@When("^I search with SKU id \"([^\"]*)\"$")
	public void i_search_with_SKU_id(String skuId) throws Throwable {
		kitLineMaintenancePage.clickQueryButton();
		kitLineMaintenancePage.enterSKUId(skuId);
		kitLineMaintenancePage.clickExecuteButton();
	}

	@Then("^the kit line details should be displayed for the given SKU id$")
	public void the_kit_line_details_should_be_displayed_for_the_given_SKU_id() throws Throwable {

	}

}
