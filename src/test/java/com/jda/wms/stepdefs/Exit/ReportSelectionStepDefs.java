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
	private Context context;
	private final ReportSelectionPage ReportSelectionPage;
	private final JDAFooter JDAFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public ReportSelectionStepDefs(Context context,ReportSelectionPage ReportSelectionPage,JDAFooter JDAFooter)
	{
		this.context=context;
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
    @And("^Search for the M&S Red Report$")
    public void search_for_the_MnS_Red_Report() throws Throwable {
    	ReportSelectionPage.enterRedReport();
    	JDAFooter.clickNextButton();	
    }
			
    @And("^Verify that the record is displayed for Missing Urn$")
    public void verify_that_the_record_displayed() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForMissingUrn());
    }
    @And("^Verify that the record is displayed for Red Report$")
    public void verify_that_the_record_displayed_for_red_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedRedReport());
    }
    @And("^Verify that the record is displayed for International Urn$")
    public void verify_that_the_record_displayed_for_international_urn() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForInt());
    }
   
    @Then("^Proceed next and enter the required value of \"([^\"]*)\"$")
    public void proceed_next_and_enter_the_required_value_of(String Sku) throws Throwable {
    JDAFooter.clickNextButton(); 
    ReportSelectionPage.enterSku(Sku);  
    }
    
    @Then("^Proceed next and enter the required value of pallet$")
    public void proceed_next_and_enter_the_required_value_of_pallet() throws Throwable {
    JDAFooter.clickNextButton(); 
    String Pallet=context.getpalletIDforUPI();  
    ReportSelectionPage.enterPallet(Pallet);  	
    
    }
    
	
    @Then("^Validate the confirmation page$")
    public void validate_the_confirmation_page() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmed());
    }
 
    @Then("^Validate the confirmation page for Red Report$")
    public void validate_the_confirmation_page_for_red_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForRedReport());
    }
 
    @Then("^Validate the confirmation page for International Urn$")
    public void validate_the_confirmation_page_for_international_urn() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForIntUrn());
    }
    
    @Then("^Proceed next to Output tab for the report$")
    public void proceed_next_to_Output_tab_for_the_report() throws Throwable {
    	JDAFooter.clickDoneButton();	
    	Thread.sleep(20000);
    	
    }
    
    @And("^Validate the report selection page for Identify URN completion$")
    public void validate_the_report_selection_page_for_completion() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionDoneMissingUrn());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Validate the report selection page for Red Report creation$")
    public void validate_the_report_selection_page_for_red_report_creation() throws Throwable {
    	
    	Assert.assertTrue("M&S Red Report not found", ReportSelectionPage.isReportSelectionDoneRedReport());
    	JDAFooter.clickDoneButton();	
    }
    
    @And("^Search for the M&S INT Reprint Label$")
    public void search_for_the_Reprint_label() throws Throwable {
    	ReportSelectionPage.enterInternationalReprint();
    	JDAFooter.clickNextButton();	
    }
    
    @And("^Validate the report selection page for URN international reprint completion$")
    public void validate_the_report_selection_page_for_URN_international_report_completion() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S INT REPRINT report not found", ReportSelectionPage.isReportSelectionDoneIntUrn());
    	JDAFooter.clickDoneButton();	
    }
    
  
			
}
