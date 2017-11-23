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

	public void chooseReasonCode(String ReasonCode) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Finish/ReasonCode.png", timeoutInSec);
		screen.type(ReasonCode);
		//screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isStockAdjustmentHomeDisplayed() throws FindFailed {
		if (screen.exists("images/StockAdjustment/Start/StockAdjustmentHome.png") != null)
			return true;
		else
			return false;
	}

	public void enterTagId(String tagId) throws InterruptedException {

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

	// working for stock adjustment
	public void enterSku(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Search/General/SkuId.png", timeoutInSec);
		screen.click("images/StockAdjustment/Search/General/SkuId.png");
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void enterOwnerId(String owner) throws FindFailed, InterruptedException {
		Match mOwnerId = screen.find("images/StockAdjustment/Search/OwnerId.png");
		screen.click(mOwnerId.getCenter().offset(70, 0));
		screen.type(owner);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void enterClientId(String clientid) throws FindFailed, InterruptedException {
		Match mclientId = screen.find("images/StockAdjustment/Search/ClientId.png");
		screen.click(mclientId.getCenter().offset(70, 0));
		screen.type(clientid);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void enterSiteId(String siteId) throws FindFailed, InterruptedException {
		Match msiteId = screen.find("images/StockAdjustment/Search/SiteId.png");
		screen.click(msiteId.getCenter().offset(70, 0));
		screen.type(siteId);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void enterLocation(String location) throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		screen.type(location);
		Thread.sleep(1000);
	}

	public void enterQuantityOnHand(String quantity) throws FindFailed, InterruptedException {
		Match mquantity = screen.find("images/StockAdjustment/Search/Quantity.png");
		screen.click(mquantity.getCenter().offset(70, 0));
		screen.type(quantity);
		Thread.sleep(1000);
	}

	public void enterReasonCode(String reason) throws FindFailed, InterruptedException {

		screen.type(reason);
		Thread.sleep(1000);
	}

	public void enterPackConfig(String packConfig) throws FindFailed, InterruptedException {
		if(screen.exists("images/StockAdjustment/Search/PackConfig.png")!=null)
		{
		Match mconfigId = screen.find("images/StockAdjustment/Search/PackConfig.png");
		screen.click(mconfigId.getCenter().offset(70, 0));
		screen.type(packConfig);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		}
	}
	
	public void selectPackConfig() throws FindFailed, InterruptedException {
		Match mconfigId = screen.find("images/StockAdjustment/Search/PackConfig.png");
		screen.click(mconfigId.getCenter().offset(70, 0));
		jdaFooter.
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void clickMiscellaneousTab() throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Miscellaneous.png", timeoutInSec);
		Thread.sleep(1000);
		screen.doubleClick("images/StockAdjustment/Miscellaneous.png");
		Thread.sleep(2000);
	}

	public void enterPallet(String pallet) throws FindFailed, InterruptedException {
		Match mpallet = screen.find("images/StockAdjustment/Search/pallet.png");
		screen.click(mpallet.getCenter().offset(70, 0));
		screen.type(pallet);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void enterPalletId(String pallet) throws FindFailed, InterruptedException {
		Match mpallet = screen.find("images/StockAdjustment/CreateModify/PalletId.png");
		screen.click(mpallet.getCenter().offset(70, 0));
		screen.type(pallet);
		Thread.sleep(1000);
	}

	public void enterContainerId(String pallet) throws FindFailed, InterruptedException {
		Match mpallet = screen.find("images/StockAdjustment/CreateModify/ContainerId.png");
		screen.click(mpallet.getCenter().offset(70, 0));
		screen.type(pallet);
		Thread.sleep(1000);
	}

	public void enterPalletType(String palletType) throws FindFailed, InterruptedException {
//		Match mpallet = screen.find("images/StockAdjustment/CreateModify/PalletType.png");
//		screen.click(mpallet.getCenter().offset(70, 0));
		screen.type(palletType);
		Thread.sleep(1000);
	}

	public void enterReceiptId(String pallet) throws FindFailed, InterruptedException {
		Match mpallet = screen.find("images/StockAdjustment/Search/Miscellaneous/ReceiptId.png");
		screen.click(mpallet.getCenter().offset(70, 0));
		screen.type(pallet);
		Thread.sleep(1000);
	}

	public void selectNewStock() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/StockAdjustment/Start/NewStock.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		Thread.sleep(1000);
	}

	public void selectExistingStock() throws FindFailed, InterruptedException {
		// existing stock is pre selected
		screen.type(Key.F7);
		Thread.sleep(2000);
	}

	public boolean isPopUpDisplayed() throws InterruptedException, FindFailed {
		if (screen.exists("images/PreAdviceLine/Yes.png") != null) {
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			return true;
		} else
			return false;
	}
	
	public void enterOrigin(String origin) throws FindFailed, InterruptedException {
		//screen.wait("images/StockAdjustment/Search/origin.png", timeoutInSec);
	//	Thread.sleep(1000);
//		Match morigin = screen.find("images/StockAdjustment/Search/origin.png");
		screen.wait("images/StockAdjustment/Search/origin.png", timeoutInSec);
		Thread.sleep(1000);
		screen.click("images/StockAdjustment/Search/origin.png");
//		screen.click(morigin.getCenter().offset(70, 0));
		screen.type(origin);
		Thread.sleep(1000);
	}

	public void enterReasonCode() throws InterruptedException {
		screen.type("Found");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		
	}

	public void handlePopUp() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(3000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}
	
	public void enterSiteIdExisting(String siteId) throws FindFailed, InterruptedException {
		Match msiteId = screen.find("images/StockAdjustment/Search/siteIdExisting.png");
		screen.click(msiteId.getCenter().offset(70, 0));
		screen.type(siteId);
		//screen.type(Key.ENTER);
		Thread.sleep(1000);
	}
	
public void enterSkuIDExisting(String skuId) throws FindFailed, InterruptedException {
		screen.type(skuId);
		Thread.sleep(1000);
//		Match msiteId = screen.find("images/StockAdjustment/Search/skuId.png");
//		screen.click(msiteId.getCenter().offset(70, 0));
//		screen.type(skuId);
//		Thread.sleep(1000);
	}

	public void enterQuantityOnHand(int quantityAdj)throws FindFailed, InterruptedException {
		Match mquantity = screen.find("images/StockAdjustment/Search/Quantity.png");
		screen.click(mquantity.getCenter().offset(70, 0));
		screen.type("a",Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);
		screen.type(Integer.toString(quantityAdj));
		Thread.sleep(1000);
	}
	
	public void enterSiteIdForStock(String siteId) throws InterruptedException {
		screen.type(siteId);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		
	}
}
