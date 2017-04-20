package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockAdjustmentsPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jDAFooter;

	@Inject
	public StockAdjustmentsPage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

	public void clickNext() throws InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}

	public boolean isRecordExists() {
		if (screen.exists("images/StockAdjustment/Results/ResultsRecord.png") != null)
			return true;
		else
			return false;
	}

	public String getSkuId() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void updateQtyOnHand(String updateQty) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/CreateModify/SAQtyOnHand.png", timeoutInSec);
		screen.click("images/StockAdjustment/CreateModify/SAQtyOnHand.png");
		screen.type(updateQty);
		Thread.sleep(1000);
		clickNext();
	}

	public void chooseReasonCode(String reasonCode) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Finish/ReasonCode.png", timeoutInSec);
		screen.click("images/StockAdjustment/Finish/ReasonCode.png");
		screen.type(reasonCode);
		Thread.sleep(2000);
		clickNext();
	}

	public boolean isStockAdjustmentHomeDisplayed() throws FindFailed {
		if (screen.exists("images/StockAdjustment/Start/StockAdjustmentHome.png.png") != null)
			return true;
		else
			return false;
	}

	public void enterTagId(String tagId) throws InterruptedException {
		// screen.wait("images/StockAdjustment/Search/TagID.png",timeoutInSec);
		// screen.click("images/StockAdjustment/Search/TagID.png");
		screen.type(tagId);
		Thread.sleep(2000);
	}

	public String getStatus() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModify/Status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
