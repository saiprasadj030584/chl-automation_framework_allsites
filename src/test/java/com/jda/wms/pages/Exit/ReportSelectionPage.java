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
	


