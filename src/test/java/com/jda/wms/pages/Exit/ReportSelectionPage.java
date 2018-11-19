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

	public boolean isRecordDissplayedAndSelected() throws FindFailed, InterruptedException {
		if(screen.find("images/ReportSelection/selectedReport.png")!=null)
		{
			if(screen.find("images/ReportSelection/selectedIdentifyReport.png")!=null)
		{
			Match mRecord=screen.find("images/ReportSelection/selectedIdentifyReport.png");
			screen.click(mRecord.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}
		else
		{
			Match mRecord=screen.find("images/ReportSelection/SelectedIntReport.png");
			screen.click(mRecord.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}}
			else
			return false;
	}

	public void enterSku(String sku) throws FindFailed, InterruptedException {
		 screen.type(sku);
		 Thread.sleep(1000);		
	}

	public boolean isProcessConfirmed() throws FindFailed, InterruptedException{
		if(screen.find("images/ReportSelection/confirmationScreen.png")!=null)
		{
			if(screen.find("images/ReportSelection/IdentifyUrnConfirmed.png")!=null){
				
			Match mScreen=screen.find("images/ReportSelection/IdentifyUrnConfirmed.png");
			screen.click(mScreen.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
			}
			 
			else{
				Match mScreen=screen.find("images/ReportSelection/InternationalUrnConfirmed.png");
				screen.click(mScreen.getCenter().offset(70, 0));
				Thread.sleep(1000);
				return true;
				
			}
		}
		else
		return false;
	}

	public void clickOutputTab() throws FindFailed, InterruptedException{
		screen.find("images/ReportSelection/output.png");
		Thread.sleep(1000);
		screen.click("images/ReportSelection/output.png");	
	}

	public boolean isReportSelectionDone() throws FindFailed, InterruptedException{
		if(screen.find("images/ReportSelection/FinishScreen.png")!=null){
			if(screen.find("images/ReportSelection/IdentifyURN.png")!=null)
		{
			Match mFinishScreen=screen.find("images/ReportSelection/IdentifyURN.png");
			screen.click(mFinishScreen.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}
			else{
				Match mFinishScreen=screen.find("images/ReportSelection/InternationalUrn.png");
				screen.click(mFinishScreen.getCenter().offset(70, 0));
				Thread.sleep(1000);
				return true;				
			}
			}
		else
		return false;
	}
	

	public void enterInternationalReprint() throws FindFailed, InterruptedException {
		 screen.type("INT");
		 Thread.sleep(1000);		
	}

	public Object getPallet() throws FindFailed, InterruptedException{
		screen.find("images/ReportSelection/Pallet.png");
		Thread.sleep(1000);
		screen.click("images/ReportSelection/Pallet.png");			
		return App.getClipboard();
		
	}

	public Object getLabel()throws FindFailed, InterruptedException{
		screen.find("images/ReportSelection/Pallet.png");
		Thread.sleep(1000);
		screen.click("images/ReportSelection/Pallet.png");			
		return App.getClipboard();
		
	}

	public void enterPallet(String pallet) throws FindFailed, InterruptedException {
		 screen.type(pallet);
		 Thread.sleep(1000);		
	}
	}
	


