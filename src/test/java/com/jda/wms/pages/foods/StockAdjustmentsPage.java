package com.jda.wms.pages.foods;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockAdjustmentsPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jdaFooter;

	@Inject
	public StockAdjustmentsPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
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

	public void updateQtyOnHand(String decrementQty) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/CreateModify/SAQtyOnHand.png", timeoutInSec);
		screen.click("images/StockAdjustment/CreateModify/SAQtyOnHand.png");
		screen.mouseMove(70, 0);
		screen.type("a", Key.CTRL);
		screen.type("a", Key.BACKSPACE);
		screen.type(decrementQty);
		Thread.sleep(3000);
		jdaFooter.clickNext();
	}

	public void chooseReasonCode(String reasonCode) throws FindFailed, InterruptedException {
	screen.wait("images/StockAdjustment/Finish/ReasonCode.png", timeoutInSec);
	screen.click("images/StockAdjustment/Finish/ReasonCode.png");
		
		switch (reasonCode) {
		case "Damaged by Warehouse":
			screen.wait("images/StockAdjustment/Finish/DW.png", timeoutInSec);
			screen.click("images/StockAdjustment/Finish/DW.png");
			break;
		case "Expired Stock":
			break;
		case "Head Office":
			break;
		case "Hampers Stock":
			break;
		case "Receiving Correction":
			break;
		case "Infestation":
			break;
		case "Outlets Stock":
			break;
		case "Returns from RDC":
			break;
		case "Stock Count":
			break;
		case "Returns to Supplier":
			break;
		}
		
		screen.type(reasonCode);
		Thread.sleep(2000);
		jdaFooter.clickDone();
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
