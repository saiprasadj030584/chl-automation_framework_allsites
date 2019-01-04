package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
//import com.jda.wms.stepdefs.foods.string;

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

		Pattern testPattern = new Pattern("images/StockAdjustment/CreateModify/SAQtyOnHand.png");
		testPattern.similar(0.95f);
		Match match = screen.find(testPattern);
		// Match mStatus =
		// screen.find("images/StockAdjustment/CreateModify/SAQtyOnHand.png");
		screen.click(match.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);
		screen.type(decrementQty);
		System.out.println("Quantity" + decrementQty);
		Thread.sleep(3000);
		jdaFooter.clickNextButton();
	}

	public void chooseReasonCode(String reasonCode) throws FindFailed, InterruptedException {
		screen.wait("images/StockAdjustment/Finish/ReasonCode.png", timeoutInSec);
		screen.click("images/StockAdjustment/Finish/ReasonCode.png");
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

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		screen.wait("images/StockAdjustment/Search/TagID.png", timeoutInSec);
		screen.click("images/StockAdjustment/Search/TagID.png");
		Thread.sleep(2000);
		screen.type(tagId);
		Thread.sleep(2000);
	}

	public void enterCEDutyStamp() throws InterruptedException, FindFailed {
		screen.wait("images/StockAdjustment/Search/CEDutyStamp.png", timeoutInSec);
		screen.click("images/StockAdjustment/Search/CEDutyStamp.png");
		Thread.sleep(2000);
		//screen.type("Yes");
		screen.type("No");
		Thread.sleep(2000);
	}

	public void enterLocation(String location) throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/Search/location.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		/*
		 * screen.wait("images/StockAdjustment/Search/location.png",
		 * timeoutInSec);
		 * screen.click("images/StockAdjustment/Search/location.png");
		 */
		Thread.sleep(2000);
		screen.type(location);
		Thread.sleep(2000);
	}

	public void selectStatus(String status) throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/Status1.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type(status);
		Thread.sleep(2000);
	}

	public String getTagId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/StockAdjustment/CreateModify/SATagId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getStatus() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/StockAdjustment/CreateModify/Status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);

		int i = 0;
		do {
			if (App.getClipboard() == null) {
				screen.type("c", Key.CTRL);
			} else {
				break;
			}
			i++;
		}

		while (i > 3);

		System.out.println("Status Value-->" + App.getClipboard());
		return App.getClipboard();
	}
	public String getQtyOnHand() throws FindFailed {
		Match mStatus = screen.find("images/StockAdjustment/CreateModify/SAQtyOnHand.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickLastRecords() throws FindFailed, InterruptedException {
		if (screen.exists("/images/StockAdjustment/TableTagID.png") != null) {
			Pattern testPattern = new Pattern("/images/StockAdjustment/TableTagID.png");
			Match mKitId = screen.find("/images/StockAdjustment/TableTagID.png");
			// screen.click(mKitId.getCenter().offset(70, 0));
//			screen.wait(testPattern, 120);
//			screen.click(testPattern);
			Thread.sleep(2000);
			screen.doubleClick(mKitId.below(10));
			Thread.sleep(2000);
		}

	else {
			Pattern testPattern = new Pattern("/images/StockAdjustment/MultipleTableTagID.png");
			testPattern.similar(0.95f);
			Match match = screen.find(testPattern);
			screen.wait(testPattern, 120);
			screen.click(testPattern);
			screen.doubleClick(match.below(10));
			Thread.sleep(2000);
			if (screen.exists("/images/StockAdjustment/TableTagID.png") != null) {
				Match mKitId = screen.find("/images/StockCheckQuery/ListId.png");
				screen.click(mKitId.getCenter().offset(70, 0));
				screen.type("a", Key.CTRL);
				screen.type("c", Key.CTRL);

			} else {
				System.out.println("Tag Id not found");
			}
		}}
	
	public void enterUnderBond() throws FindFailed, InterruptedException {
		
		Match mUnder = screen.find("images/StockAdjustment/UnderBond.png");
		screen.click(mUnder.getCenter().offset(70, 0));
		screen.type("Bonded - not in free circulation");	
	}

	public boolean isShortageImageExists() throws FindFailed {
		if (screen.exists("images/StockAdjustment/Finish/ShortageImage.png") != null)
			return true;
		else
			return false;
	}

	

	public void enterSkuId(String skuId) throws InterruptedException, FindFailed{
		screen.wait("images/InventoryTransactionQuery/SkuId.png", timeoutInSec);
		Match mSku=screen.find("images/InventoryTransactionQuery/SkuId.png");
		screen.click(mSku.getCenter().offset(70,0));
		Thread.sleep(1000);
		screen.type(skuId);
		
		
	}

	public void clickNextButton() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		
	}
	public void adjustQtyOnHand(String decrementQty) throws FindFailed, InterruptedException {

		screen.wait("images/InventoryTransactionQuery/qtyOnHandSa.png", timeoutInSec);
		Match mSku=screen.find("images/InventoryTransactionQuery/qtyOnHandSa.png");
		screen.click(mSku.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);
		screen.type(decrementQty);
		System.out.println("Quantity" + decrementQty);
		Thread.sleep(3000);
		jdaFooter.clickNextButton();
	}
	public String getNewQtyOnHand() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/qtyOnHandSa.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	}



