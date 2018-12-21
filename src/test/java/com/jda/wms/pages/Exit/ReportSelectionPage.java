package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
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
	public void enterTrustedReport() throws FindFailed, InterruptedException {
		 screen.type("M&S - Trusted Report");
		 Thread.sleep(1000);		
	}
	public void enterBatchID() throws FindFailed, InterruptedException {
		 screen.type(" M&S - Batch ID & BBE Report");
		 Thread.sleep(1000);		
	}
	public void enterOutstanding() throws FindFailed, InterruptedException {
		 screen.type(" M&S - Outstanding Pallets to Load Report");
		 Thread.sleep(1000);		
	}
	public void enterRedLocation() throws FindFailed, InterruptedException {
		 screen.type(" M&S - Red Location Report");
		 Thread.sleep(1000);		
	}
	public void enterBlackStock() throws FindFailed, InterruptedException {
		 screen.type(" M&S - Black Stock Status Report");
		 Thread.sleep(1000);		
	}
	public void enterRedReport() throws FindFailed, InterruptedException {
		 screen.type("M&S Red Report");
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
	}
	


