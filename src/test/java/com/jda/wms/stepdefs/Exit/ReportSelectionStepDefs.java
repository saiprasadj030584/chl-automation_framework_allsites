package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.pages.Exit.PackConfigMaintenancePage;
import com.jda.wms.pages.Exit.SiteQueryPage;
import com.jda.wms.pages.Exit.ReportSelectionPage;
import com.jda.wms.pages.Exit.JDAFooter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReportSelectionStepDefs {
	private final ReportSelectionPage ReportSelectionPage;
	private final JDAFooter JDAFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public ReportSelectionStepDefs(ReportSelectionPage ReportSelectionPage,JDAFooter JDAFooter)
	{
		
		this.ReportSelectionPage = ReportSelectionPage;
		this.JDAFooter=JDAFooter;
	}
	
	@Then("^Select Print to screen and proceed next$")
	public void select_print_to_screen_and_proceed_next() throws Throwable {
		ReportSelectionPage.selectPrintToScreen();
		JDAFooter.clickNextButton();		
	}
	
    @And("^Search for the M&S Identify URN$")
    public void search_for_the_IDENTIFY_URN() throws Throwable {
    	ReportSelectionPage.enterIdentifyUrn();
    	JDAFooter.clickNextButton();	
    }
			
    @And("^Verify that the record is displayed$")
    public void verify_that_the_record_displayed() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelected());
    }
    
    @Then("^Proceed next and enter the \"([^\"]*)\"$")
    public void proceed_next_and_enter_the(String Sku) throws Throwable {
    	JDAFooter.clickNextButton();	
    	ReportSelectionPage.enterSku(Sku);  	
    }
	
    @Then("^Validate the confirmation page$")
    public void validate_the_confirmation_page() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmed());
    }
    
    @Then("^Proceed next to Output tab for the report$")
    public void proceed_next_to_Output_tab_for_the_report() throws Throwable {
    	JDAFooter.clickDoneButton();	
    	ReportSelectionPage.clickOutputTab();  	
    }
    
    @And("^Validate the report selection page for completion$")
    public void validate_the_report_selection_page_for_completion() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionDone());
    	JDAFooter.clickDoneButton();	
}
			
}
