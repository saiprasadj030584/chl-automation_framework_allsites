package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.ReportSelectionPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReportSelectionStepDefs {
	private Context context;
	private JDAFooter jDAFooter;
	private ReportSelectionPage reportSelectionPage;

	@Inject
	public ReportSelectionStepDefs(Context context, JDAFooter jDAFooter, ReportSelectionPage reportSelectionPage) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.reportSelectionPage = reportSelectionPage;
	}

	@When("^I select print to screen and I search for the stock$")
	public void i_select_print_to_screen_and_I_search_for_the_stock() throws Throwable {
		reportSelectionPage.selectPrintToScreen();
		jDAFooter.clickNextButton();
		reportSelectionPage.enterStock("stock");
		jDAFooter.clickNextButton();
		reportSelectionPage.clickResult();
		jDAFooter.clickNextButton();
	}

	@When("^I enter the siteID \"([^\"]*)\"$")
	public void i_enter_the_siteID(String siteID) throws Throwable {
		reportSelectionPage.enterSiteID(siteID);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}

	@Then("^the report should be generated for stock in inventory")
	public void the_report_should_be_generated_for_stock_in_inventory() throws Throwable {
		Assert.assertTrue("Report not displayed as expected for stock in Inventory",
				reportSelectionPage.isReportDisplayed());
		jDAFooter.clickDoneButton();
	}
}
