package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.JDAFooter;
import com.jda.wms.pages.Exit.PurchaseOrderReceivingPage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.ReportSelectionPage;
import com.jda.wms.pages.Exit.StoreTrackingOrderPickingPage;
import com.jda.wms.stepdefs.Exit.JDAExitUpiHeader;
import com.jda.wms.stepdefs.Exit.JDAExitputtyfunctionsStepDef;
import com.jda.wms.utils.Utilities;
import com.jda.wms.pages.Exit.JdaLoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ReportSelectionStepDefs {
	private Context context;
	private final ReportSelectionPage ReportSelectionPage;
	private final JDAFooter JDAFooter;
	private final JDAExitUpiHeader jDAExitUpiHeader;
	private final JDAExitputtyfunctionsStepDef JDAExitputtyfunctionsStepDef;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private PuttyFunctionsPage puttyFunctionsPage;
	private Hooks hooks;
	private final JdaLoginPage jdaLoginPage;
	
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public ReportSelectionStepDefs(Context context,JDAExitputtyfunctionsStepDef JDAExitputtyfunctionsStepDef,
			JDAExitUpiHeader jDAExitUpiHeader,PuttyFunctionsPage puttyFunctionsPage,
			ReportSelectionPage ReportSelectionPage,Hooks hooks,JdaLoginPage jdaLoginPage,
			PurchaseOrderReceivingPage purchaseOrderReceivingPage,JDAFooter JDAFooter,StoreTrackingOrderPickingPage storeTrackingOrderPickingPage)
	{
		this.context=context;
		this.ReportSelectionPage = ReportSelectionPage;
		this.JDAFooter=JDAFooter;
		this.jDAExitUpiHeader=jDAExitUpiHeader;
		this.JDAExitputtyfunctionsStepDef=JDAExitputtyfunctionsStepDef;
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.storeTrackingOrderPickingPage=storeTrackingOrderPickingPage;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.hooks=hooks;
		this.jdaLoginPage = jdaLoginPage;
		
	}
	
	@Then("^Select Print to screen and proceed next$")
	public void select_print_to_screen_and_proceed_next() throws Throwable {
		ReportSelectionPage.selectPrintToScreen();
		JDAFooter.clickNextButton();		
	}
	@Then("^Clear the previous search$")
	public void Clear_the_previous_search() throws Throwable {
	
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
	}
	
    @And("^Search for the M&S Identify URN$")
    public void search_for_the_IDENTIFY_URN() throws Throwable {
    	ReportSelectionPage.enterIdentifyUrn();
    	JDAFooter.clickNextButton();	
    }
    
    @And("^Search for the M&S Identify URN Report$")
    public void search_for_the_MnS_IDENTIFY_URN_Report() throws Throwable {
    	ReportSelectionPage.enterIdentifyUrnReport();
    	JDAFooter.clickNextButton();	
    }
    @And("^Search for the M&S Red Report$")
    public void search_for_the_MnS_Red_Report() throws Throwable {
    	ReportSelectionPage.enterRedReport();
    	JDAFooter.clickNextButton();	
    }
    @And("^Search for the M&S Load Label$")
    public void search_for_the_MnS_Load_Label() throws Throwable {
    	ReportSelectionPage.enterLoadLabel();
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
    @And("^Verify that the record is displayed for Identify Urn Report$")
    public void verify_that_the_record_displayed_for_identify_urn_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForUrnReport());
    }
    @And("^Verify that the record is displayed for BatchId and  BBE Report$")
    public void verify_that_the_record_displayed_for_BatchId_BBE_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforBatchIdReport());
    }
    
    @And("^Verify that the record is displayed for Red Location Report$")
    public void Verify_that_the_record_is_displayed_for_Red_Location_Report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforRedLocation());
    }
    @And("^Verify that the record is displayed for Black Stock Status Report$")
    public void Verify_that_the_record_is_displayed_for_Black_Stock_Status_Report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforBlackStock());
    }
    @And("^Verify that the record is displayed for Trusted Report$")
    public void verify_that_the_record_displayed_for_Trusted_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForTrustedReport());
    }
    @And("^Verify that the record is displayed for M&S - URNs on Pallet Report$")
    public void Verify_that_the_record_is_displayed_for_MnS_URNs_on_Pallet_Report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforURN());
    }
    @And("^Data to be inserted and received with PalletID with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for \"([^\"]*)\"$$")
    public void Data_to_be_inserted_and_received_with_palletID(String status,
			String type, String customer, String skuid) throws Throwable{
    	jDAExitUpiHeader.Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_with_for(status,
    	type,customer,skuid);
    	JDAExitputtyfunctionsStepDef.i_have_logged_in_as_warehouse_user_in_putty();
    	purchaseOrderReceivingPage.selectUserDirectedMenu();
    	storeTrackingOrderPickingPage.selectReceivingMenu();
    	System.out.println("After enter");
		Assert.assertTrue("Receiving Menu not displayed as expected",
		storeTrackingOrderPickingPage.isReceivingMenuDisplayed());
		storeTrackingOrderPickingPage.selectBasicReceivingMenu();
		Assert.assertTrue("Receiving Task Menu not displayed as expected",
		storeTrackingOrderPickingPage.isBasicReceivingMenuDisplayed());
		storeTrackingOrderPickingPage.selectGS1_128ReceiveMenu();
		Assert.assertTrue("GS128Receiving Task Menu not displayed as expected",
		storeTrackingOrderPickingPage.isRcvScnEANCMenuDisplayed());
		Thread.sleep(2000);
		GetTCData.getpoId();
//		String skuid = context.getSKUHang();
//		i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		Assert.assertTrue("RCVBli screen is not displayed as expected",
		storeTrackingOrderPickingPage.isRCVBLIMenuDisplayed());
		Thread.sleep(300);
		puttyFunctionsPage.I_generate_belcode_for_UPI(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		purchaseOrderReceivingPage.EnterBel(belCode);
		Thread.sleep(300);
		puttyFunctionsPage.singleTab();
		String ToPallet = null;
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		puttyFunctionsPage.nextScreen();
		String Expirydate= Utilities.getAddedSystemYear();
		purchaseOrderReceivingPage.EnterToExpirydate(Expirydate);
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
		jdaLoginPage.login();
    }
   
    @Then("^Proceed next and enter the required value of \"([^\"]*)\"$")
    public void proceed_next_and_enter_the_required_value_of(String Sku) throws Throwable {
    JDAFooter.clickNextButton(); 
    ReportSelectionPage.enterSku(Sku);  
    }
    @And("^Enter PalletId$")
    public void Enter_palletId() throws Throwable{
    	JDAFooter.clickNextButton();
    	String palletIDforUPI = context.getpalletIDforUPI();
    	ReportSelectionPage.enterPallet(palletIDforUPI);
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
 
    @Then("^Validate the confirmation page for Batch ID Report$")
    public void validate_the_confirmation_page_for_Batch_ID_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforBatchID());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @Then("^Validate the confirmation page for RedLocation Report$")
    public void Validate_the_confirmation_page_for_RedLocation_Report() throws Throwable {
    	JDAFooter.clickNextButton();
    	Thread.sleep(10000);
    	JDAFooter.clickNextButton();
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforRedLocation());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @Then("^Validate the confirmation page for Black Stock Status Report$")
    public void Validate_the_confirmation_page_for_Black_Stock_Status_Report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Thread.sleep(10000);
    	JDAFooter.clickNextButton();
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforBlackStock());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
   
    @Then("^Validate the confirmation page for M&S - URNs on Pallet Report$")
    public void Validate_the_confirmation_page_for_MnS_URNs_on_Pallet_Report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Thread.sleep(10000);
    	JDAFooter.clickNextButton();
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforURN());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @Then("^Validate the confirmation page for Trusted Report$")
    public void validate_the_confirmation_page_for_Trusted_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforTrustedReport());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
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
    
    @And("^Validate the report selection page for BatchId completion$")
    public void validate_the_report_selection_page_for_BatchId_completion() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionDoneBatchIdreport());
    	JDAFooter.clickDoneButton();	
    }
    
    @And("^Validate the report selection page for Trusted Report completion$")
    public void validate_the_report_selection_page_for_Trusted() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionTrustedReport());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Validate the report selection page for Red Location completion$")
    public void Validate_the_report_selection_page_for_Red_Location_completion() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionRedLocation());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Validate the report selection page for M&S - URNs on Pallet Report$")
    public void Validate_the_report_selection_page_for_MnS_URNs_on_Pallet_Report() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionURNReport());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Validate the report selection page for Black Stock Status completion$")
    public void Validate_the_report_selection_page_for_Black_Stock_Status_completion() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionBlackStock());
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
  

   
   
       @And("^Verify that the record is displayed for M&S Gains Or Loss Report$")
       public void verify_that_the_record_is_displayed_for_MnS_Gains_or_loss_report() throws Throwable {
       	
       	Assert.assertTrue("M&S Gains and Loss record not found", ReportSelectionPage.isRecordDissplayedAndSelectedForGainOrLossReport());
       	JDAFooter.clickDoneButton();	
       	}
    
    @Then("^Validate the confirmation page for Gains and Loss Report$")
    public void validate_the_confirmation_page_for_gains_and_loss_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForGainOrLossReport());
    	JDAFooter.clickDoneButton();	
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for Gains and Loss Report completed$")
    public void validate_the_report_selection_page_for_gains_and_loss_report_completed() throws Throwable {
    	
    	Assert.assertTrue("M&S Gains and Loss Report not found", ReportSelectionPage.isReportSelectionCompletedGainOrLoss());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Verify that the record is displayed M&S - Non-Shipped greater than 4 weeks Report$")
    public void verify_that_the_record_displayed_for_Non_shipped_greater_than_4_weeks_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForNonShipped());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Non-Shipped greater than 4 weeks Report$")
    public void validate_the_confirmation_page_for_Non_shipped_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmed());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for M&S - Non-Shipped greater than 4 weeks completed$")
    public void validate_the_report_selection_page_for_non_shipped() throws Throwable {
    	Assert.assertTrue("M&S Non-Shipped greater than 4 weeks Report not found", ReportSelectionPage.isReportSelectionDoneMissingUrn());
    	JDAFooter.clickDoneButton();	
    }
     
   
    @Then("Enter the status \"([^\"]*)\" as parameter$")
    public void enter_the_status_as_parameter(String status) throws Throwable{
    	ReportSelectionPage.enterStatus(status);
    	JDAFooter.clickNextButton();
    	
    }

    
    @And("^Verify that the record is displayed for M&S - Allocation vs Receipts across last 3 weeks Report$")
    public void verify_that_the_record_displayed_for_allocation_vs_receipts_across_last_3_weeks_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForAllocationvsReceipts());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Allocation vs Receipts across last 3 weeks Report$")
    public void validate_the_confirmation_page_for_allocation_vs_receipts_across_last_3_weeks_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForAllocationvsReceipts());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    }
    @And("^Validate the report selection page for M&S - Allocation vs Receipts across last 3 weeks completed$")
    public void validate_the_report_selection_page_for_allocation_vs_receipts_across_last_3_weeks_() throws Throwable {
    	Assert.assertTrue("M&S - Allocation vs Receipts across last 3 week report not found", ReportSelectionPage.isReportSelectionDoneAllocationvsReceipts());
    	JDAFooter.clickDoneButton();	
    }
   
 
    
    @And("^Verify that the record is displayed for M&S - Stock Status Report$")
    public void verify_that_the_record_displayed_for_stock_status_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForStockStatus());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Stock Status Report$")
    public void validate_the_confirmation_page_for_stock_status_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForStockStatus());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for M&S - Stock Status completed$")
    public void validate_the_report_selection_page_for_stock_status_completed() throws Throwable {
    	Assert.assertTrue("M&S - Allocation vs Receipts across last 3 week report not found", ReportSelectionPage.isReportSelectionDoneStockStatus());
    	JDAFooter.clickDoneButton();	
    }
    
   
    @And("^Verify that the record is displayed for M&S - Weekly Receipts and Despatches by Customer Report$")
    public void verify_that_the_record_displayed_for_weekly_receipts_and_despatches_by_customer_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForWeeklyReceiptsDespatches());
    	JDAFooter.clickNextButton();
    }
    @Then("Enter the date for commencing week as parameter$")
    public void enter_the_for_commencing_week_as_parameter() throws Throwable{
    	ReportSelectionPage.enterDate();
    	JDAFooter.clickNextButton();
    	
    }
    @Then("Enter the date for end of week as parameter$")
    public void enter_the_for_end_of_week_as_parameter() throws Throwable{
    	ReportSelectionPage.enterPresentDate();
    	JDAFooter.clickNextButton();
    	
    }
    @Then("^Validate the confirmation page for M&S - Weekly Receipts and Despatches by Customer Report$")
    public void validate_the_confirmation_page_for_weekly_receipts_and_despatches_by_customer_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForWeeklyReceiptsDespatches());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for M&S - Weekly Receipts and Despatches by Customer completed$")
    public void validate_the_report_selection_page_for_weekly_receipts_and_despatches_by_customer_completed() throws Throwable {
    	Assert.assertTrue("M&S - Allocation vs Receipts across last 3 week report not found", ReportSelectionPage.isReportSelectionDoneWeeklyReceiptsDespatches());
    	JDAFooter.clickDoneButton();	
    }
   
    @And("^Verify that the record is displayed for M&S - Prohibition Report$")
    public void verify_that_the_record_displayed_for_MnS_prohibition_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForProhibition());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Prohibition Report$")
    public void validate_the_confirmation_page_for_MnS_prohibition_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForProhibition());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for M&S - Prohibition completed$")
    public void validate_the_report_selection_page_for_MnS_prohibition_completed() throws Throwable {
    	Assert.assertTrue("M&S - Allocation vs Receipts across last 3 week report not found", ReportSelectionPage.isReportSelectionDoneProhibition());
    	JDAFooter.clickDoneButton();	
    }
    
    @And("^Search for \"([^\"]*)\"$")
    public void Search_for(String search) throws Throwable {
    	ReportSelectionPage.enterSearch(search);
    	JDAFooter.clickNextButton();	
    }
    @And("^Verify that the record is displayed for M&S - Outstanding Pallets to Load Report$")
    public void verify_that_the_record_displayed_for_outstanding_pallets_to_load_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForOutstandingPallets());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Outstanding Pallets to Load Report$")
    public void validate_the_confirmation_page_for_outstanding_pallets_to_load_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForOutstandingPallets());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    }
    @And("^Validate the report selection page for M&S - Outstanding Pallets to Load Report completed$")
    public void validate_the_report_selection_page_for_outstanding_pallets_to_load_report() throws Throwable {
    	Assert.assertTrue("M&S - Outstanding Pallets to Load Report not found", ReportSelectionPage.isReportSelectionDoneOutstandingPallets());
    	JDAFooter.clickDoneButton();	
    }
   
    @And("^Verify that the record is displayed for M&S - Customs Valuation for Consignment Report$")
    public void verify_that_the_record_displayed_for_customs_valuation_for_consignment_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForCustomValuation());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Customs Valuation for Consignment Report$")
    public void validate_the_confirmation_page_for_customs_valuation_for_consignment_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForCustomValuation());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    } 
    @Then("^Validate the confirmation page for M&S - Customs Inspection Report$")
    public void validate_the_confirmation_page_for_Customs_Inspection_Report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForCustomInspection());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    } 
    @Then("^Verify that the record is displayed for M&S - Customs Inspection Report$")
    public void validate_the_confirmation_page_for_customs_Inspection_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isRecordDissplayedAndSelectedForCustomInspection());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    }
    
    @And("^Validate the report selection page for M&S - Customs Valuation for Consignment Report completed$")
    public void validate_the_report_selection_page_for_customs_valuation_for_consignment_report_completed() throws Throwable {
    	Assert.assertTrue("M&S - Outstanding Pallets to Load Report not found", ReportSelectionPage.isReportSelectionDoneCustomValuation());
    	JDAFooter.clickDoneButton();	
    }

    @And("^Verify that the record is displayed for M&S - Weekly Summary Report$")
    public void verify_that_the_record_displayed_for_weekly_summary_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForWeeklySummary());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Weekly Summary Report$")
    public void validate_the_confirmation_page_for_weekly_summary_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForWeeklySummary());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    }
    @And("^Validate the report selection page for M&S - Weekly Summary Report completed$")
    public void validate_the_report_selection_page_for_weekly_summary_report_completed() throws Throwable {
    	Assert.assertTrue("M&S - Weekly Summary Report", ReportSelectionPage.isReportSelectionDoneWeeklySummary());
    	JDAFooter.clickDoneButton();	
    }
    
    @And("^Verify that the record is displayed for M&S - Pallet Built Report$")
    public void verify_that_the_record_displayed_for_pallet_built_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForPalletBuilt());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Pallet Built Report$")
    public void validate_the_confirmation_page_for_pallet_built_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForPalletBuilt());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    }
    @And("^Validate the report selection page for M&S - Pallet Built Report completed$")
    public void validate_the_report_selection_page_for_pallet_built_report_completed() throws Throwable {
    	Assert.assertTrue("M&S - Pallet Built Report", ReportSelectionPage.isReportSelectionDonePalletBuilt());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Verify that the record is displayed for M&S - Short Invoice for Container Report$")
    public void verify_that_the_record_displayed_for_short_invoice_for_container_report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedForPalletBuilt());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for M&S - Short Invoice for Container Report$")
    public void validate_the_confirmation_page_for_short_invoice_for_container_report() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedForPalletBuilt());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(20000);
    }
    @And("^Validate the report selection page for M&S - Short Invoice for Container Report completed$")
    public void validate_the_report_selection_page_for_short_invoice_for__completed() throws Throwable {
    	Assert.assertTrue("M&S - Pallet Built Report", ReportSelectionPage.isReportSelectionDonePalletBuilt());
    	JDAFooter.clickDoneButton();	
    }
    

    @And("^Verify that the record is displayed for Operative Performance Trusted Report$")
    public void Verify_that_the_record_is_displayed_for_Operative_Performance_Trusted_Report() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforOperativePerformance());
    	JDAFooter.clickNextButton();
    }
    @And("^Enter start and end date$")
    public void Enter_start_end_date() throws Throwable {
    	ReportSelectionPage.enterStartDate();
    	ReportSelectionPage.enterEndDate();
    	JDAFooter.clickNextButton();	
    }
    @Then("^Validate the confirmation page for Operative Performance Trusted Report$")
    public void validate_the_confirmation_page_for_Operative_Performance() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforOperativePerformance());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for Operative Performance Trusted Report$")
    public void Validate_the_report_selection_page_for_Operative_Performance() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionOperativePerformance());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Verify that the record is displayed for Green Stock Available to Pick Flow$")
    public void Verify_that_the_record_is_displayed_for_Green_Stock_Available_to_Pick_Flow() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforPickFlow());
    	JDAFooter.clickNextButton();
    }
    @And("^Enter customer id \"([^\"]*)\"$")
    public void EnterCustomerId(String Id) throws Throwable {
    	Thread.sleep(2000);
    	ReportSelectionPage.enterCustomerId(Id);
    	Thread.sleep(1000);
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for Green Stock Available to Pick Flow$")
    public void validate_the_confirmation_page_for_Pick_Flow() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforPickFlow());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for Green Stock Available to Pick Flow$")
    public void Validate_the_report_selection_page_for_PickFlow() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionPickFlow());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Verify that the record is displayed for Soiled and Damaged$")
    public void Verify_that_the_record_is_displayed_for_Soiled_and_Damaged() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforDamaged());
    	JDAFooter.clickNextButton();
    }
    @Then("^Validate the confirmation page for Soiled and Damaged$")
    public void validate_the_confirmation_page_for_Damaged() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforDamaged());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for Soiled and Damaged$")
    public void Validate_the_report_selection_page_for_Damaged() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionDamaged());
    	JDAFooter.clickDoneButton();	
    }
    
    @And("^Verify that the record is displayed for Pallet not Consigned Report$")
    public void Verify_that_the_record_is_displayed_for_Pallet_not_Consigned() throws Throwable {
    	Assert.assertTrue("Record not displayed", ReportSelectionPage.isRecordDissplayedAndSelectedforPalletNotConsigned());
    	JDAFooter.clickNextButton();
    }
    
    @Then("^Validate the confirmation page for Pallet not Consigned Report$")
    public void validate_the_confirmation_page_for_Pallet_not_Consigned() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforPalletNotConsigned());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for Pallet not Consigned Report$")
    public void Validate_the_report_selection_page_for_Pallet_not_Consigned() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionforPalletNotConsigned());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Verify that the record is displayed for Unpicked Not Relocated Stock$")
    public void Verify_that_the_record_is_displayed_for_Unpicked_Not_Relocated_Stock() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isRecordDissplayedAndSelected());
    	JDAFooter.clickDoneButton();	
    }
    @Then("^Validate the confirmation page for Unpicked Not Relocated Stock$")
    public void validate_the_confirmation_page_for_Unpicked_Not_Relocated_Stock() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforUnpicked());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(10000);
    }
    @And("^Validate the report selection page for Unpicked Not Relocated Stock$")
    public void Validate_the_report_selection_page_for_Unpicked_Not_Relocated_Stock() throws Throwable {
    	Thread.sleep(20000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionforUnpicked());
    	JDAFooter.clickDoneButton();	
    }
    
    @And("^Validate the report selection page for M&S - Customs Inspection Report completed$")
    public void validate_the_report_selection_page_for_Customs_Inspection_Report_completed() throws Throwable {
    	Assert.assertTrue("M&S - Outstanding Pallets to Load Report not found", ReportSelectionPage.isReportSelectionDoneCustomInspection());
    	JDAFooter.clickDoneButton();	
    }
    @And("^Verify that the record is displayed for Dangerous Goods$")
    public void Verify_that_the_record_is_displayed_for_Dangerous_Goods() throws Throwable {
    	Thread.sleep(1000);
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isRecordDissplayedAndSelectedforDangerousGoods());
    	JDAFooter.clickNextButton();	
    }
    @Then("^Validate the confirmation page for Dangerous Goods$")
    public void validate_the_confirmation_page_for_Dangerous_Goods() throws Throwable {
    	JDAFooter.clickNextButton();	
    	Assert.assertTrue("Process not confirmed", ReportSelectionPage.isProcessConfirmedforDangerousGoods());
    	JDAFooter.clickDoneButton();
    	Thread.sleep(1000);
    }
    @And("^Validate the report selection page for Dangerous Goods$")
    public void Validate_the_report_selection_page_for_Dangerous_Goods() throws Throwable {
    	Assert.assertTrue("M&S Identify URNS report not found", ReportSelectionPage.isReportSelectionDangerousGoods());
    	JDAFooter.clickDoneButton();	
    }
}
