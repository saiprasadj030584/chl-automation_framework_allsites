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
		String siteID = context.getSiteID();
		reportSelectionPage.enterSiteID(siteID);
		reportSelectionPage.chooseStartDate(DateUtils.getPrevSystemMonth());
		reportSelectionPage.chooseEndDate(DateUtils.getCurrentSystemDate());
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}
	@When("^I enter the siteID for hanging$")
	public void i_enter_the_siteID_for_hanging() throws Throwable {
		String siteID = context.getSiteID();
		reportSelectionPage.enterSiteID(siteID);
		//reportSelectionPage.chooseStartDate(DateUtils.getPrevSystemMonth());
	//	reportSelectionPage.chooseEndDate(DateUtils.getCurrentSystemDate());
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
		String site = context.getSiteID();
		reportSelectionPage.chooseReport();
		jDAFooter.clickNextButton();
		reportSelectionPage.chooseSiteId(site);
		reportSelectionPage.chooseStartDate(DateUtils.getPrevSystemMonth());
		reportSelectionPage.chooseEndDate(DateUtils.getCurrentSystemDate());
		jDAFooter.pressTab();
		reportSelectionPage.chooseModularity(dataType);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
		Thread.sleep(4000);
	}
	
	@Then("^the receiving progress report should be generated$")
	public void the_receiving_progress_report_should_be_generated() throws Throwable {
		Thread.sleep(20000);
		Assert.assertTrue("Report generation is not as expected", reportSelectionPage.isReportGeneratedExist());;
		Thread.sleep(2000);
		jDAFooter.clickDoneButton();
	}
	
	@When("^I select print to screen and I search for the pre receiving$")
	public void i_select_print_to_screen_and_I_search_for_the_pre_receiving() throws Throwable {
		reportSelectionPage.selectPrintToScreen();
		jDAFooter.clickNextButton();
		reportSelectionPage.enterStock("M&S");
		jDAFooter.pressTab();
		reportSelectionPage.enterStock("PRE-RECEIVING");
		jDAFooter.clickNextButton();
		reportSelectionPage.clickPreReceivingUpcFomResult();
		jDAFooter.clickNextButton();
	}
	
	@Then("^the report should be generated for pre receiving upc in inventory")
	public void the_report_should_be_generated_for_pre_receiving_upc__in_inventory() throws Throwable
	{
		Thread.sleep(10000);
		Assert.assertTrue("Report not displayed as expected for pre receiving upc in Inventory",
				reportSelectionPage.isReportDisplayedForPreReceivingUpc());
		jDAFooter.clickDoneButton();
	}
	
	@When("^I Select M&S Repack report$")
	public void i_select_M_S_Repack_report() throws Throwable {
		reportSelectionPage.chooseRepackingReport();
		jDAFooter.clickNextButton();
		reportSelectionPage.chooseSiteId();
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}
	
	@Then("^the repacking report should be generated$")
	public void the_repacking_report_should_be_generated() throws Throwable {
		Assert.assertTrue("Repacking Report is not generated",reportSelectionPage.isRepackingReportExist());
		Thread.sleep(4000);
		jDAFooter.clickDoneButton();
	}
	
	@When("^I choose M&S-Discrepancies summary as report type of type \"([^\"]*)\"$")
	public void i_choose_M_S_Discrpancies_summary_as_report_type_of_type(String type) throws Throwable {
		reportSelectionPage.chooseDiscrepanciesReport();
		jDAFooter.clickNextButton();
		reportSelectionPage.chooseSiteId();
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		reportSelectionPage.chooseModularityType(type);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}
	
	 @Then("^the trailor level discrepancy report should be generated$")
		public void the_trailor_level_discrepancy_report_should_be_generated() throws Throwable {
			Assert.assertTrue("Receiving Discrepancies Report not displayed as expected",reportSelectionPage.isReceivingDiscrepanciesReportExist());
			Thread.sleep(10000);
			jDAFooter.clickDoneButton();
		}
	 
	 @When("^I Select M&S internal exception report of type \"([^\"]*)\"$")
		public void i_select_M_S_internal_exception_report_of_type(String type) throws Throwable {
			reportSelectionPage.chooseInternalExceptionReport();
			jDAFooter.clickNextButton();
			reportSelectionPage.chooseSiteId();
			jDAFooter.pressTab();
			reportSelectionPage.chooseStartDate(DateUtils.getPrevSystemMonth());
			jDAFooter.pressTab();
			reportSelectionPage.chooseEndDate(DateUtils.getCurrentSystemDate());
			jDAFooter.pressTab();
			jDAFooter.pressTab();
			jDAFooter.pressTab();
			String Type="pick";
			reportSelectionPage.enterType(Type);
			jDAFooter.PressEnter();
			jDAFooter.pressTab();
			reportSelectionPage.chooseModularityType(type);
			jDAFooter.PressEnter();
			jDAFooter.clickNextButton();
			jDAFooter.clickDoneButton();
		}
	 
	 @Then("^the internal exception report should be generated$")
		public void the_internal_exception_report_should_be_generated() throws Throwable {
		 Assert.assertTrue("Internal exception report not displayed as expected",reportSelectionPage.isInternalExceptionReportExist());
			Thread.sleep(10000);
			jDAFooter.clickDoneButton();
		}
	 
	 @When("^I choose M&S-Receiving summary of type \"([^\"]*)\"$")
		public void i_choose_M_S_Receiving_summary_of_type(String type) throws Throwable {
			reportSelectionPage.chooseReportForType();
			jDAFooter.clickNextButton();
			reportSelectionPage.chooseSiteId();
			reportSelectionPage.chooseStartDate(DateUtils.getPrevSystemMonth());
			reportSelectionPage.chooseEndDate(DateUtils.getCurrentSystemDate());
			jDAFooter.pressTab();
			reportSelectionPage.chooseModularityType(type);
			jDAFooter.clickNextButton();
			jDAFooter.clickDoneButton();
		}
	 
	 @When("^I select M&S picking and replenish workload report for \"([^\"]*)\" type$")
		public void i_select_M_S_picking_and_replenish_workload_report_for_type(String type) throws Throwable {
			reportSelectionPage.choosePickingAndReplenishWorkloadReport();
			jDAFooter.clickNextButton();
			reportSelectionPage.chooseSiteId();
			jDAFooter.pressTab();
			reportSelectionPage.chooseModularityType(type);
			jDAFooter.clickNextButton();
			jDAFooter.clickDoneButton();
			Thread.sleep(4000);
		}
	 
	 @Then("^the picking and replenish workload report should be generated$")
		public void the_picking_and_replenish_workload_report_should_be_generated() throws Throwable {	
		 Assert.assertTrue("Picking & Replenishment Worload report not displayed as expected",reportSelectionPage.isPickingAndReplenishWorkloadReportExist());	
			Thread.sleep(10000);
			jDAFooter.clickDoneButton();
		}
	 @When("^I select M&S proactive allocation shortage report$")
		public void i_select_M_S_proactive_allocation_shortage_report() throws Throwable {
			reportSelectionPage.chooseProactiveAllocationShortageReport();
			jDAFooter.clickNextButton();
			reportSelectionPage.chooseSiteId();
			jDAFooter.clickNextButton();
			jDAFooter.clickDoneButton();
			Thread.sleep(4000);
		}
	 
	 @Then("^the proactive allocation shortage report should be generated$")
		public void the_proactive_allocation_shortage_report_should_be_generated() throws Throwable {
			reportSelectionPage.isAllocationShortageReportExist();
			jDAFooter.clickDoneButton();
		}
}
