package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.ReportSelectionPage;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReportSelectionStepDefs {
	private Context context;
	private JDAFooter jDAFooter;
	private ReportSelectionPage reportSelectionPage;
	private JdaLoginPage jdaLoginPage;
	private JdaHomePage jdaHomePage;

	@Inject
	public ReportSelectionStepDefs(Context context, JDAFooter jDAFooter, ReportSelectionPage reportSelectionPage,JdaLoginPage jdaLoginPage,JdaHomePage jdaHomePage) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.reportSelectionPage = reportSelectionPage;
		this.jdaLoginPage =jdaLoginPage;
		this.jdaHomePage = jdaHomePage;
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

	@When("^I enter the siteID$")
	public void i_enter_the_siteID() throws Throwable {
		String siteID = context.getSiteId();
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
	
	@Given("^I am on report selection page$")
	public void i_am_on_report_seletion_page() throws Throwable {
		jdaLoginPage.login();
		jdaHomePage.navigateToReportSelectionPage();
	}
	
	@When("^I choose the print to screen option$")
	public void i_choose_the_print_to_screen_option() throws Throwable {
		reportSelectionPage.choosePrintToScreen();
		jDAFooter.clickNextButton();
	}
	
	@When("^I search for \"([^\"]*)\" report$")
	public void i_search_for_report(String reportType) throws Throwable {
		reportSelectionPage.searchReportType(reportType);
		jDAFooter.clickNextButton();
	}
	
	@When("^I choose M&S-Receiving summary as report type at site for \"([^\"]*)\" type$")
	public void i_choose_M_S_Receiving_summary_as_report_type_at_site_(String dataType) throws Throwable {
		String site = context.getSiteId();
		reportSelectionPage.chooseReport();
		jDAFooter.clickNextButton();
		reportSelectionPage.chooseSiteId(site);
		reportSelectionPage.chooseStartDate(DateUtils.getPrevSystemMonth());
		reportSelectionPage.chooseEndDate(DateUtils.getCurrentSystemDate());
		jDAFooter.pressTab();
		reportSelectionPage.chooseModularity(dataType);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}
	
	@Then("^the receiving progress report should be generated$")
	public void the_receiving_progress_report_should_be_generated() throws Throwable {
		Assert.assertTrue("Report generation is not as expected", reportSelectionPage.isReportGeneratedExist());;
		Thread.sleep(4000);
		jDAFooter.clickDoneButton();
	}
}
