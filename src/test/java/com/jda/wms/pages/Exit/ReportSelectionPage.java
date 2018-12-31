package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.JDAFooter;

public class ReportSelectionPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter JDAFooter;

	@Inject
	public ReportSelectionPage(JDAFooter JDAFooter) {
		this.JDAFooter=JDAFooter;
	}

	public void selectPrintToScreen() throws FindFailed, InterruptedException {
		screen.find("images/ReportSelection/printToScreen.png");
		Thread.sleep(1000);
		screen.click("images/ReportSelection/printToScreen.png");
	}

	public void enterIdentifyUrn() throws FindFailed, InterruptedException {
		 screen.type("Identify Urn");
		 Thread.sleep(1000);		
	}
	public void enterIdentifyUrnReport() throws FindFailed, InterruptedException {
		 screen.type("M&S Identify Urn Report");
		 Thread.sleep(1000);		
	}
	
	
	public void enterRedReport() throws FindFailed, InterruptedException {
		 screen.type("M&S Red Report");
		 Thread.sleep(1000);		
	}

	public void enterLoadLabel() throws FindFailed, InterruptedException {
		 screen.type("M&S - Load Label");
		 Thread.sleep(1000);		
	}
	
	public boolean isRecordDissplayedAndSelectedForMissingUrn() throws FindFailed, InterruptedException {
		
			if(screen.find("images/ReportSelection/selectedIdentifyReport.png")!=null)
		{
			Match mRecord=screen.find("images/ReportSelection/selectedIdentifyReport.png");
			screen.click(mRecord.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}
			else
			return false;
	}
	public boolean isRecordDissplayedAndSelectedForUrnReport() throws FindFailed, InterruptedException {
		
		if(screen.find("images/ReportSelection/selectedIdentifyUrnReport.png")!=null)
	{
		Match mRecord=screen.find("images/ReportSelection/selectedIdentifyUrnReport.png");
		screen.click(mRecord.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}
		else
		return false;
}
public boolean isRecordDissplayedAndSelectedforBatchIdReport() throws FindFailed, InterruptedException {
		
		if(screen.find("images/ReportSelection/selectedBatchIdReport.png")!=null)
	{
		Match mRecord=screen.find("images/ReportSelection/selectedBatchIdReport.png");
		screen.click(mRecord.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}
		else
		return false;
}
public boolean isRecordDissplayedAndSelectedforoutstanding() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/selectedBatchIdReport.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/selectedBatchIdReport.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedforRedLocation() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/selectedRedlocation.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/selectedRedlocation.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedforBlackStock() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/selectedBlackStockReport.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/selectedBlackStockReport.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}

public boolean isRecordDissplayedAndSelectedforURN() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/selectedURNReport.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/selectedURNReport.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedForTrustedReport() throws FindFailed, InterruptedException {
		
		if(screen.find("images/ReportSelection/selectedTrustedReport.png")!=null)
	{
		Match mRecord=screen.find("images/ReportSelection/selectedTrustedReport.png");
		screen.click(mRecord.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}
		else
		return false;
}
	public boolean isRecordDissplayedAndSelectedRedReport() throws FindFailed, InterruptedException {
		
		if(screen.find("images/ReportSelection/selectedRedReport.png")!=null)
	{
		Match mRecord=screen.find("images/ReportSelection/selectedRedReport.png");
		screen.click(mRecord.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}
		else
		return false;
}
	public boolean isRecordDissplayedAndSelectedForInt() throws FindFailed, InterruptedException {
		
			if(screen.find("images/ReportSelection/SelectedIntReport.png")!=null)
		{
			Match mRecord=screen.find("images/ReportSelection/SelectedIntReport.png");
			screen.click(mRecord.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}
			else
			return false;
	}

	public void enterSku(String sku) throws FindFailed, InterruptedException {
		 screen.type(sku);
		 Thread.sleep(1000);		
	}

	public boolean isProcessConfirmed() throws FindFailed, InterruptedException{
		
			if(screen.find("images/ReportSelection/IdentifyUrnConfirmed.png")!=null){
				
			Match mScreen=screen.find("images/ReportSelection/IdentifyUrnConfirmed.png");
			screen.click(mScreen.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
			}
			 
			
		else
		return false;
	}
	public boolean isProcessConfirmedforBatchID() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/BatchIdConfirmed.png")!=null){
			
		Match mScreen=screen.find("images/ReportSelection/BatchIdConfirmed.png");
		screen.click(mScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
		}
		 
		
	else
	return false;
}
public boolean isProcessConfirmedforRedLocation() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/RedLocationconfirmed.png")!=null){
			
		Match mScreen=screen.find("images/ReportSelection/RedLocationconfirmed.png");
		screen.click(mScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
		}
		 
		
	else
	return false;
}
public boolean isProcessConfirmedforBlackStock() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/BlackStockconfirmed.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/BlackStockconfirmed.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isProcessConfirmedforURN() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/URNconfirmed.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/URNconfirmed.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
	public boolean isProcessConfirmedforTrustedReport() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/TrustedreportConfirmed.png")!=null){
			
		Match mScreen=screen.find("images/ReportSelection/TrustedreportConfirmed.png");
		screen.click(mScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
		}
		 
		
	else
	return false;
}
	public boolean isProcessConfirmedForIdentifyUrnReport() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/IdentifyUrnReportConfirmed.png")!=null){
			
		Match mScreen=screen.find("images/ReportSelection/IdentifyUrnReportConfirmed.png");
		screen.click(mScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
		}
		 
		
	else
	return false;
}
	public boolean isProcessConfirmedForRedReport() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/RedReportConfirmed.png")!=null){
			
		Match mScreen=screen.find("images/ReportSelection/RedReportConfirmed.png");
		screen.click(mScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
		}
		 
		
	else
	return false;
}

	
	public boolean isProcessConfirmedForIntUrn() throws FindFailed, InterruptedException{
		
			if(screen.find("images/ReportSelection/InternationalURNConfirmed.png")!=null){
				
			Match mScreen=screen.find("images/ReportSelection/InternationalURNConfirmed.png");
			screen.click(mScreen.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
			}
		else
		return false;
	}

	public void clickOutputTab() throws FindFailed, InterruptedException{
		screen.find("images/ReportSelection/output.png");
		Thread.sleep(1000);
		screen.click("images/ReportSelection/output.png");	
	}

	public boolean isReportSelectionDoneMissingUrn() throws FindFailed, InterruptedException{
		
			if(screen.find("images/ReportSelection/IdentifyURN.png")!=null)
		{
			Match mFinishScreen=screen.find("images/ReportSelection/IdentifyURN.png");
			screen.click(mFinishScreen.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}			
		else
		return false;
	}
	public boolean isReportSelectionDoneBatchIdreport() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/BatchIdreport.png")!=null)
	{
		Match mFinishScreen=screen.find("images/ReportSelection/BatchIdreport.png");
		screen.click(mFinishScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}			
	else
	return false;
}
	public boolean isReportSelectionTrustedReport() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/TrustedReport.png")!=null)
	{
		Match mFinishScreen=screen.find("images/ReportSelection/TrustedReport.png");
		screen.click(mFinishScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}			
	else
	return false;
}
public boolean isReportSelectionRedLocation() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/RedLocationReport.png")!=null)
	{
		Match mFinishScreen=screen.find("images/ReportSelection/RedLocationReport.png");
		screen.click(mFinishScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}			
	else
	return false;
}

public boolean isReportSelectionURNReport() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/URNReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/URNReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isReportSelectionBlackStock() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/BlackStockReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/BlackStockReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
	public boolean isReportGeneration() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/RedComplete.png")!=null)
	{
		Match mFinishScreen=screen.find("images/ReportSelection/RedComplete.png");
		screen.click(mFinishScreen.getCenter().offset(70, 0));
		return true;
	}			
	else
	return false;
}
	public boolean isReportSelectionDoneRedReport() throws FindFailed, InterruptedException{
		
		if(screen.find("images/ReportSelection/RedReportGenerated.png")!=null)
	{
		Match mFinishScreen=screen.find("images/ReportSelection/RedReportGenerated.png");
		screen.click(mFinishScreen.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}			
	else
	return false;
}
	public boolean isReportSelectionDoneIntUrn() throws FindFailed, InterruptedException{
		
			
			if(screen.find("images/ReportSelection/InternationalURN.png")!=null){
				Match mFinishScreen=screen.find("images/ReportSelection/InternationalURN.png");
				screen.click(mFinishScreen.getCenter().offset(70, 0));
				Thread.sleep(1000);
				return true;				
			}			
		else
		return false;
	}
	

	public void enterInternationalReprint() throws FindFailed, InterruptedException {
		 screen.type("INT");
		 Thread.sleep(1000);		
	}

	

	public void enterPallet(String pallet) throws FindFailed, InterruptedException {
		 screen.type(pallet);
		 Thread.sleep(1000);		
	}

	public Object enterParameters() throws FindFailed, InterruptedException{
		screen.find("images/ReportSelection/Parameters.png");
		Thread.sleep(1000);
		screen.click("images/ReportSelection/Parameters.png");			
		return App.getClipboard();
		
	}
public boolean isRecordDissplayedAndSelectedForGainOrLossReport() throws FindFailed, InterruptedException {
		
		if(screen.find("images/ReportSelection/selectedGainsOrLossReport.png")!=null)
	{
		Match mRecord=screen.find("images/ReportSelection/selectedGainsOrLossReport.png");
		screen.click(mRecord.getCenter().offset(70, 0));
		Thread.sleep(1000);
		return true;
	}
		else
		return false;
}
public boolean isProcessConfirmedForGainOrLossReport() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/GainOrLossReportConfirmed.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/GainOrLossReportConfirmed.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}


public boolean isReportSelectionCompletedGainOrLoss() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedGainOrLoss.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedGainOrLoss.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}


public boolean isRecordDissplayedAndSelectedForNonShipped() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForNonShipped.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForNonShipped.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public void enterStatus(String status) throws FindFailed, InterruptedException{
	screen.type(status);
	Thread.sleep(1000);
}


public boolean isProcessConfirmedForNonShipped() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForNonShipped.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForNonShipped.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneNonShipped() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedNonShipped.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedNonShipped.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}


public boolean isRecordDissplayedAndSelectedForAllocationvsReceipts() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForAllocationvsReceipts.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForAllocationvsReceipts.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForAllocationvsReceipts() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForAllocationvsReceipts.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForAllocationvsReceipts.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneAllocationvsReceipts() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedAllocationvsReceipts.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedAllocationvsReceipts.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public boolean isRecordDissplayedAndSelectedForStockStatus() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForStockStatus.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForStockStatus.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForStockStatus() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForStockStatus.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForStockStatus.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneStockStatus() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedStockStatus.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedStockStatus.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public boolean isRecordDissplayedAndSelectedForWeeklyReceiptsDespatches() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForWeeklyReceiptsDespatches.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForWeeklyReceiptsDespatches.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForWeeklyReceiptsDespatches() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForWeeklyReceiptsDespatches.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForWeeklyReceiptsDespatches.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneWeeklyReceiptsDespatches() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedWeeklyReceiptsDespatches.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedWeeklyReceiptsDespatches.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public void enterDate() {
	screen.type("-30");
	screen.type(Key.ENTER);
	
}
public void enterPresentDate() {
	screen.type("0");
	screen.type(Key.ENTER);
	
}

public boolean isRecordDissplayedAndSelectedForProhibition() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForProhibition.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForProhibition.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForProhibition() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForProhibition.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForProhibition.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneProhibition() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedProhibition.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedProhibition.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;}


public void enterSearch(String search) throws FindFailed, InterruptedException {
	 screen.type(search);
	 Thread.sleep(1000);		
}
public boolean isRecordDissplayedAndSelectedForCustomValuation() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForCustomValuation.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForCustomValuation.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedForshipManifest() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForshipManifest.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForshipManifest.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedForRedStock() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForRedStock.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForRedStock.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedForCustomInspection() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForCustomInspection.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForCustomInspection.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForCustomValuation() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForCustomValuation.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForCustomValuation.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isProcessConfirmedForshipManifest() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForshipManifest.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForshipManifest.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isProcessConfirmedForRedStock() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForRedStock.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForRedStock.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}

public boolean isProcessConfirmedForCustomInspection() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForCustominspection.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForCustominspection.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneCustomValuation() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedCustomValuation.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedCustomValuation.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isReportSelectionDoneshipManifest() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedshipManifest.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedshipManifest.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isReportSelectionDoneRedstock() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedRedstock.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedRedstock.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isReportSelectionDoneCustomInspection() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedCustomInspection.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedCustomInspection.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public boolean isRecordDissplayedAndSelectedForOutstandingPallets() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForOutstandingPallets.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForOutstandingPallets.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForOutstandingPallets() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForOutstandingPallets.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForOutstandingPallets.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneOutstandingPallets() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedOutstandingPallets.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedOutstandingPallets.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isRecordDissplayedAndSelectedforOperativePerformance() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForOperativePerformance.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForOperativePerformance.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public void enterStartDate() throws FindFailed, InterruptedException {
	screen.type("0"+Key.ENTER);
	 Thread.sleep(1000);	
}
public void enterEndDate() throws FindFailed, InterruptedException {
	screen.type(Key.TAB);
	screen.type("0"+Key.ENTER);
	 Thread.sleep(1000);	
}
public boolean isProcessConfirmedforOperativePerformance() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForOperativePerformance.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForOperativePerformance.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionOperativePerformance() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/OperativePerformanceReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/OperativePerformanceReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isRecordDissplayedAndSelectedforPickFlow() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/PickFlowReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/PickFlowReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public void enterCustomerId(String Id) throws FindFailed, InterruptedException {
	 screen.type(Id);
	 Thread.sleep(1000);		
}
public boolean isProcessConfirmedforPickFlow() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForPickFlow.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForPickFlow.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionPickFlow() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/PickFlowReport1.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/PickFlowReport1.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isRecordDissplayedAndSelectedforDamaged() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/DamagedReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/DamagedReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isProcessConfirmedforDamaged() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForDamaged.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForDamaged.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionDamaged() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ReportDamaged.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/ReportDamaged.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isRecordDissplayedAndSelectedforPalletNotConsigned() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/PalletNotConsignedReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/PalletNotConsignedReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isProcessConfirmedforPalletNotConsigned() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForPalletNotConsigned.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForPalletNotConsigned.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionforPalletNotConsigned() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ReportPalletNotConsigned.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/ReportPalletNotConsigned.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isRecordDissplayedAndSelected() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ReportUnpicked.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/ReportUnpicked.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isProcessConfirmedforUnpicked() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForUnpicked.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForUnpicked.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionforUnpicked() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ReportForUnpicked.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/ReportForUnpicked.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}



public boolean isRecordDissplayedAndSelectedForWeeklySummary() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForWeeklySummary.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForWeeklySummary.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForWeeklySummary() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForWeeklySummary.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForWeeklySummary.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneWeeklySummary() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedWeeklySummary.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedWeeklySummary.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public boolean isRecordDissplayedAndSelectedForPalletBuilt() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForPalletBuilt.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForPalletBuilt.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}

public boolean isProcessConfirmedForPalletBuilt() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForPalletBuilt.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForPalletBuilt.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDonePalletBuilt() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedPalletBuilt.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedPalletBuilt.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public boolean isRecordDissplayedAndSelectedForShortInvoice() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/SelectedForShortInvoice.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/SelectedForShortInvoice.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isProcessConfirmedForShortInvoice() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForShortInvoice.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForShortInvoice.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isProcessConfirmedForRedLocation() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForRedLocation.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForRedLocation.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isProcessConfirmedForRedputaway() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForRedputaway.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForRedputaway.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}
	 
	
else
return false;
}
public boolean isReportSelectionDoneShortInvoice() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedShortInvoice.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedShortInvoice.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public boolean isReportSelectionRedlocation() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedRedlocation.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedRedlocation.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isReportSelectionRedputaway() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/CompletedRedputaway.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/CompletedRedputaway.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public void enterMnSEmptyLocationRedStockReport() throws FindFailed, InterruptedException {
	 screen.type("M&S - Empty Red Locations Report");
	 Thread.sleep(1000);		
}
public boolean isRecordDissplayedAndSelectedForMnSEmptyRedLocationsReport() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/MnSRedStock.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/MnSRedStock.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
public boolean isRecordDissplayedAndSelectedForPutaway() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/MnSPutaway.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/MnSPutaway.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}

public void enterRedStockParameter() throws FindFailed, InterruptedException {
screen.type("BA");
Thread.sleep(1000);

}
public void enterCustomer() throws FindFailed, InterruptedException {
	Match mRecord=screen.find("images/ReportSelection/customer.png");
	screen.click(mRecord.getCenter().offset(70, 0));
  screen.type("4624");
   Thread.sleep(1000);

}

public boolean isRecordDissplayedAndSelectedForRedLocationsParameter() throws FindFailed, InterruptedException {

if(screen.find("images/ReportSelection/RedStockParameter.png")!=null)
{
Match mRecord=screen.find("images/ReportSelection/RedStockParameter.png");
screen.click(mRecord.getCenter().offset(70, 0));
Thread.sleep(1000);
return true;
}
return false;}

public boolean isRecordDissplayedAndSelectedforDangerousGoods() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ReportDangerousGoods.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/ReportDangerousGoods.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isProcessConfirmedforDangerousGoods() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForDangerousGoods.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForDangerousGoods.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionDangerousGoods() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/DangerousGoodsReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/DangerousGoodsReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}

public void enterUPC(String supplierSku) throws InterruptedException {
	screen.type(supplierSku);
	clickTab();
}

public void clickTab() throws InterruptedException {
	screen.type(Key.TAB);
	Thread.sleep(3000);
	
}

public void enterSupplierId(String supplierId) throws InterruptedException {
	screen.type(supplierId);
	clickTab();
}


public void enterQuantity() throws InterruptedException {
	screen.type("20");
	clickTab();
}
public boolean isRecordDissplayedAndSelectedForIdentifyURN() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/selectedIdentifyUrnReport.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/selectedIdentifyUrnReport.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}

public boolean isProcessConfirmedforIdentifyURN() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForIdentifyURN.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForIdentifyURN.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionIdentifyURN() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/completedIdentifyURNReport.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/completedIdentifyURNReport.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}
public boolean isRecordDissplayedAndSelectedForUrnAuditTrail() throws FindFailed, InterruptedException {
	
	if(screen.find("images/ReportSelection/selectedUrnAuditTrail.png")!=null)
{
	Match mRecord=screen.find("images/ReportSelection/selectedUrnAuditTrail.png");
	screen.click(mRecord.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}

public void enterURN(String belCode) throws InterruptedException {
	screen.type(belCode);
	Thread.sleep(1000);

	
}
public boolean isProcessConfirmedForUrnAuditTrail() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/ConfirmedForUrnAuditTrail.png")!=null){
		
	Match mScreen=screen.find("images/ReportSelection/ConfirmedForUrnAuditTrail.png");
	screen.click(mScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
	}	
else
return false;
}
public boolean isReportSelectionDoneUrnAuditTrail() throws FindFailed, InterruptedException{
	
	if(screen.find("images/ReportSelection/completedUrnAuditTrail.png")!=null)
{
	Match mFinishScreen=screen.find("images/ReportSelection/completedUrnAuditTrail.png");
	screen.click(mFinishScreen.getCenter().offset(70, 0));
	Thread.sleep(1000);
	return true;
}			
else
return false;
}


	}


