package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class ReportSelectionPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectPrintToScreen() throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/ReportGeneration/PrintToScreen.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		Thread.sleep(1000);
	}

	public void enterStock(String stock) throws FindFailed, InterruptedException {
		screen.type(stock);
		Thread.sleep(1000);
	}

	public void clickResult() throws FindFailed, InterruptedException {
		screen.wait("images/ReportGeneration/StockChecks.png", timeoutInSec);
		screen.click("images/ReportGeneration/StockChecks.png");
		Thread.sleep(3000);
	}

	public void enterSiteID(String siteID) throws FindFailed, InterruptedException {
		screen.type(siteID);
		Thread.sleep(3000);
	}

	public boolean isReportDisplayed() throws InterruptedException {
		Thread.sleep(8000);
		if (screen.exists("images/ReportGeneration/Report.png") != null) {
			return true;
		} else
			return false;
	}
	
	public void choosePrintToScreen() throws FindFailed, InterruptedException {
		Match mPrintScreen = screen.find("images/ReportSelection/printToScreen.png");
		Thread.sleep(1000);
		screen.click(mPrintScreen.getCenter().offset(70, 0));
		Thread.sleep(2000);
	}
	
	public void searchReportType(String reportType) throws InterruptedException {
		screen.type(reportType);
		Thread.sleep(1000);
	}
	
	public void chooseReport() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/receivingsummary.png");
		Thread.sleep(2000);
		screen.click(mReport.getCenter().offset(70, 0));
		Thread.sleep(3000);
	}
	
	public void chooseStartDate(String startdate) throws FindFailed, InterruptedException {
		Match mstartdate = screen.find("images/ReportSelection/startDate.png");
		Thread.sleep(1000);
		screen.click(mstartdate.getCenter().offset(70, 0));
		screen.type(startdate);
		Thread.sleep(2000);
	}
	
	public void chooseSiteId(String siteId) throws InterruptedException {
		screen.type(siteId);
		Thread.sleep(1000);
	}
	
	public void siteId(String startdate) throws FindFailed, InterruptedException {
		Match mstartdate = screen.find("images/ReportSelection/startDate.png");
		Thread.sleep(1000);
		screen.click(mstartdate.getCenter().offset(70, 0));
		screen.type(startdate);
		Thread.sleep(2000);
	}
	
	public void chooseEndDate(String enddate) throws FindFailed, InterruptedException {
		Match menddate = screen.find("images/ReportSelection/endDate.png");
		Thread.sleep(1000);
		screen.click(menddate.getCenter().offset(70, 0));
		screen.type(enddate);
		Thread.sleep(2000);
	}
	
	public void isReportGeneratedExist() throws InterruptedException, FindFailed {
		Thread.sleep(3000);
		screen.wait("images/ReportSelection/generatedreport.png", timeoutInSec);
    }
	
	public void chooseModularity(String dataType) throws InterruptedException {
		screen.type(dataType);
		Thread.sleep(2000);
	}
}
