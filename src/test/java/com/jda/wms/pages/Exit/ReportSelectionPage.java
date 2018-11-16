package com.jda.wms.pages.Exit;

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
			Match mRecord=screen.find("images/ReportSelection/selectedReport.png");
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
		if(screen.find("images/ReportSelection/confirmationScreen.png")!=null)
		{
			Match mScreen=screen.find("images/ReportSelection/confirmationScreen.png");
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

	public boolean isReportSelectionDone() throws FindFailed, InterruptedException{
		if(screen.find("images/ReportSelection/FinishScreen.png")!=null)
		{
			Match mFinishScreen=screen.find("images/ReportSelection/FinishScreen.png");
			screen.click(mFinishScreen.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}
		else
		return false;
	}
	}


