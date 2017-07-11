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

		switch (reasonCode) {
		case "Dirty":
			screen.type("Dirty");
			Thread.sleep(2000);
			break;
		case "DMIT":
			screen.type("DMIT");
			Thread.sleep(2000);
			break;
		case "EXPD":
			screen.type("EXPD");
			Thread.sleep(2000);
			break;
		case "FOUND":
			screen.type("FOUND");
			Thread.sleep(2000);
			break;
		case "IE":
			screen.type("'IE");
			Thread.sleep(2000);
			break;
		case "INCOMPLETE":
			screen.type("INCOMPLETE");
			Thread.sleep(2000);
			break;
		case "LOST":
			screen.type("LOST");
			Thread.sleep(2000);
			break;
		case "SAMPLES":
			screen.type("SAMPLES");
			Thread.sleep(2000);
			break;

		}

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

	public void enterSkuId(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Search/SkuId.png", timeoutInSec);
		screen.click("images/StockAdjustment/Search/SkuId.png");
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void enterOwnerId(String owner) throws FindFailed, InterruptedException {
		Match mOwnerId = screen.find("images/StockAdjustment/Search/OwnerId.png");
		screen.click(mOwnerId.getCenter().offset(70, 0));
		screen.type(owner);
		Thread.sleep(1000);
	}

	public void enterClientId(String clientid) throws FindFailed, InterruptedException {
		Match mclientId = screen.find("images/StockAdjustment/Search/ClientId.png");
		screen.click(mclientId.getCenter().offset(70, 0));
		screen.type(clientid);
		Thread.sleep(1000);
	}

	public void enterSiteId(String siteId) throws FindFailed, InterruptedException {
		Match msiteId = screen.find("images/StockAdjustment/Search/SiteId.png");
		screen.click(msiteId.getCenter().offset(70, 0));
		screen.type(siteId);
		Thread.sleep(1000);
	}

	public void enterLocation(String location) throws FindFailed, InterruptedException {
		Match mlocationId = screen.find("images/StockAdjustment/Search/Location.png");
		screen.click(mlocationId.getCenter().offset(70, 0));
		screen.type(location);
		Thread.sleep(1000);

		/*
		 * screen.wait("images/StockAdjustment/Search/Location.png",
		 * timeoutInSec);
		 * screen.click("images/StockAdjustment/Search/Location.png");
		 * Thread.sleep(1000); screen.type(location); Thread.sleep(1000);
		 */
	}

	public void enterQuantityOnHand(String quantity) throws FindFailed, InterruptedException {
		Match mquantity = screen.find("images/StockAdjustment/Search/Quantity.png");
		screen.click(mquantity.getCenter().offset(70, 0));
		screen.type(quantity);
		Thread.sleep(1000);
	}

	public void enterPackConfig(String packConfig) throws FindFailed, InterruptedException {
		Match mconfigId = screen.find("images/StockAdjustment/Search/PackConfig.png");
		screen.click(mconfigId.getCenter().offset(70, 0));
		screen.type(packConfig);
		Thread.sleep(1000);
	}

	public void clickMiscellaneousTab() throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Miscellaneous.png", timeoutInSec);
		screen.click("images/StockAdjustment/Miscellaneous.png");
		Thread.sleep(2000);
	}

	public void enterPallet(String pallet) throws FindFailed, InterruptedException {
		Match mpallet = screen.find("images/StockAdjustment/Search/PalletId.png");
		screen.click(mpallet.getCenter().offset(70, 0));
		screen.type(pallet);
		Thread.sleep(1000);
	}

	public void selectNewStock() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/StockAdjustment/Start/NewStock.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		Thread.sleep(1000);
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
