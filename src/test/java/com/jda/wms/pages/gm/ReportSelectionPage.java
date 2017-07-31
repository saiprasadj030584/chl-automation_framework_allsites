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
		// screen.wait("images/StockAdjustment/Search/SkuId.png", timeoutInSec);
		// screen.click("images/StockAdjustment/Search/SkuId.png");
		screen.type(stock);
		Thread.sleep(1000);
	}

	public void clickresult() throws FindFailed, InterruptedException {
		screen.wait("images/ReportGeneration/StockChecks.png", timeoutInSec);
		screen.click("images/ReportGeneration/StockChecks.png");
		Thread.sleep(3000);
	}

	public void enterSiteID(String siteID) throws FindFailed, InterruptedException {
		// screen.wait("images/StockAdjustment/Search/SkuId.png", timeoutInSec);
		// screen.click("images/StockAdjustment/Search/SkuId.png");
		screen.type(siteID);
		Thread.sleep(3000);
	}

	public boolean isReportDisplayed() throws InterruptedException {
		Thread.sleep(6000);
		if (screen.exists("images/ReportGeneration/Report.png") != null) {
			return true;
		} else
			return false;
	}

}
