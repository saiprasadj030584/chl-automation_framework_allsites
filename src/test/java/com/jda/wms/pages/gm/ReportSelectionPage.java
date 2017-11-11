package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ReportSelectionPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;
	
	@Inject
	public ReportSelectionPage(Context context) {
		this.context = context;
	}

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
		Thread.sleep(10000);
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
	
	public boolean isReportGeneratedExist() throws InterruptedException, FindFailed {
		if (screen.exists("images/ReportSelection/newReceivingSummaryReport.png") != null) {
			return true;
		} else
			return false;
    }
	
	public void chooseModularity(String dataType) throws InterruptedException {
		screen.type(dataType);
		Thread.sleep(2000);
	}
	
	public void clickPreReceivingUpcFomResult() throws FindFailed, InterruptedException {
		screen.wait("images/ReportGeneration/PreReceivingUpcDetails.png", timeoutInSec);
		screen.click("images/ReportGeneration/PreReceivingUpcDetails.png");
		Thread.sleep(3000);
	}
	
	public boolean isReportDisplayedForPreReceivingUpc() throws InterruptedException {
		Thread.sleep(11000);
		if (screen.exists("images/ReportGeneration/Reports.png") != null) {
			return true;
		} else
			return false;
	}
	
	public void chooseRepackingReport() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/selectRepackingReport.png");
		Thread.sleep(2000);
		screen.click(mReport.below(5));
		Thread.sleep(3000);
	}
	
	public void chooseSiteId() throws InterruptedException {
		screen.type(context.getSiteId());
		Thread.sleep(1000);
	}
	
	public boolean isRepackingReportExist() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		if (screen.exists("images/ReportSelection/generatedRepackingReport.png") != null) {
			return true;
		} else
			return false;
	}
	
	public void chooseDiscrepanciesReport() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/receivingDiscrepancies.png");
		Thread.sleep(2000);
		screen.click(mReport.getCenter().offset(70, 0));
		Thread.sleep(3000);
	}
	
	public void chooseModularityType(String type) throws InterruptedException {
		screen.type(type);
		Thread.sleep(2000);
	}
	
	public boolean isReceivingDiscrepanciesReportExist() throws InterruptedException, FindFailed {
		Thread.sleep(10000);
		if (screen.exists("images/ReportSelection/generatedDiscrepanciesReport.png") != null) {
			return true;
		} else
			return false;
    }
	
	public void chooseInternalExceptionReport() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/InternalException.png");
		Thread.sleep(2000);
		screen.click(mReport.below(5));
		Thread.sleep(3000);
	}
	
	public void enterType(String type) throws InterruptedException {
		screen.type(type);
		Thread.sleep(2000);
	}
	
	public boolean isInternalExceptionReportExist() throws FindFailed, InterruptedException {
		Thread.sleep(20000);
		if (screen.exists("images/ReportSelection/generatedInternalExceptions.png") != null) {
			return true;
		} else
			return false;
	}
	
	public void chooseReportForType() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/receivingsummary.png");		
		Thread.sleep(2000);
		screen.click(mReport.getCenter().offset(70, 0));
		Thread.sleep(2000);
	}
	
	public void choosePickingAndReplenishWorkloadReport() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/pickingAndReplen.png");
		Thread.sleep(2000);
		screen.click(mReport.below(5));
		Thread.sleep(3000);		
	}
	
	public boolean isPickingAndReplenishWorkloadReportExist() throws FindFailed, InterruptedException {
		Thread.sleep(10000);
		
		if (screen.exists("images/ReportSelection/replenWorkload.png") != null) {
			return true;
		} else
			return false;
	}
	
	public void chooseProactiveAllocationShortageReport() throws FindFailed, InterruptedException {
		Match mReport = screen.find("images/ReportSelection/proactiveShortage.png");
		Thread.sleep(2000);
		screen.click(mReport.below(5));
		Thread.sleep(3000);
	}
	
	public void isAllocationShortageReportExist() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		screen.wait("images/ReportSelection/proactiveShortageReport.png", timeoutInSec);
	}
}
