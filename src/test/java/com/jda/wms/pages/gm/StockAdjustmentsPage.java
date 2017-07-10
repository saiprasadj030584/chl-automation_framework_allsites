package com.jda.wms.pages.gm;

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
		Match mStatus = screen.find("images/StockAdjustment/CreateModify/SAQtyOnHand.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);
		screen.type(decrementQty);
		Thread.sleep(3000);
		jdaFooter.clickNextButton();
	}

	public void chooseReasonCode(String reasonCode) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Finish/ReasonCode.png", timeoutInSec);
		screen.type(reasonCode);
		Thread.sleep(2000);
		jdaFooter.clickDoneButton();
	}

	public boolean isStockAdjustmentHomeDisplayed() throws FindFailed {
		if (screen.exists("images/StockAdjustment/Start/StockAdjustmentHome.png") != null)
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

	public String enterSkuId(String skuId) throws FindFailed {

		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();

	}

	public String enterOwnerId() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String enterClientId() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String enterSiteId(String siteId) throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String enterLocation(String location) throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String enterQuantityOnHand() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String enterPackConfig() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();

	}

	public String clickMiscellaneousTab() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String enterPallet() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void selectNewStock() {
		// TODO Auto-generated method stub

	}

	public boolean isPopUpDisplayed() throws InterruptedException, FindFailed {
		if (screen.exists("images/PreAdviceLine/Yes.png") != null) {
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			return true;
		} else
			return false;
	}

}
