package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PurchaseOrderReceivingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PurchaseOrderReceivingPage() {
	}

	public void selectUserDirectedMenu() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectReceiveMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectBasicReceiveMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectPreAdviceReceive() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPreAdviceEntryDisplayed() throws FindFailed, InterruptedException {
		Thread.sleep(10000);
		if ((screen.exists("images/Putty/Receiving/PreAdvEntry.png") != null))
			return true;
		else if ((screen.exists("images/Putty/Receiving/PreAdvComplete.png") != null)) {
			pressEnter();
			return true;
		}
		return false;
	}

	public void enterPreAdvId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.type(preAdviceId);
		Thread.sleep(5000);
	}

	public void enterSKUId(String skuID) throws FindFailed, InterruptedException {
		System.out.println("SKu in enter Skuid " + skuID);
		screen.type(skuID);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
	}

	public String getPreAdvId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/Pre-AdviceDisplayed.png");
		screen.click(mStatus.below(5));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isSearchInfoDisplayed() throws FindFailed, InterruptedException {
		if ((screen.exists("images/Putty/SearchInfo.png") != null)
				|| (screen.exists("images/Putty/Info - Po.png") != null))
			return true;
		else
			return false;
	}

	public String getSupplierId() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/SuppDisplayed.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterLocation(String location) throws InterruptedException, FindFailed {
		screen.wait("images/Putty/Receiving/Location.png", timeoutInSec);
		screen.click("images/Putty/Receiving/Location.png");
		screen.type(location);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterTagId(String uniqueId) throws InterruptedException {
		screen.type(uniqueId);
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void enterQtyToReceive(String qtyToReceive) throws InterruptedException {

		if (Integer.parseInt(qtyToReceive) > 999) {
			screen.type(qtyToReceive);
			Thread.sleep(1000);
		} else {
			screen.type(qtyToReceive);
			screen.type(Key.TAB);
			Thread.sleep(1000);
		}
	}

	public void enterCaseRatio(String caseRatio) throws InterruptedException {
		screen.type(caseRatio);
		// screen.type("x", Key.CTRL);
		// screen.type(Key.NUM4);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB); // to navigate to Vintage
		Thread.sleep(2000);
	}

	public void enterExpiryDate(String expDate) throws InterruptedException {
		screen.type(expDate);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
	}

	public boolean isUserMenuDisplayed() {
		if (screen.exists("images/Putty/UserMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isReceiveMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/ReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isBasicReceiveMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/BasicReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRcvPreCmp2Displayed() {
		if (screen.exists("images/Putty/Receiving/RcvPreCmp2.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRcvPreCmp3Displayed() {
		if (screen.exists("images/Putty/Receiving/RcvPreCmp3.png") != null)
			return true;
		else
			return false;
	}

	public String getSKUId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/SKUDisplayed.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	// TODO move this method to JDAHomePage.java
	public void pressTab() throws InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterVintage(String vintage) throws InterruptedException {
		screen.type(vintage);
		Thread.sleep(1000);
	}

	public void enterABV(String abv) throws InterruptedException {
		screen.type(abv);
		Thread.sleep(1000);
	}

	public boolean isNoValidPreAdviceDisplayed() throws InterruptedException {
		if (screen.exists("images/Putty/Receiving/NoValidPreAdvice.png") != null) {
			pressEnter();
			return true;
		} else
			return false;
	}

	public void enterYes() throws InterruptedException {
		screen.type("y");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
	}

	public boolean isMorePercentageAbv() {
		if (screen.exists("images/Putty/Receiving/MorePercentageAbv.png") != null)
			return true;
		else
			return false;
	}

	public boolean isReceivingInprogressDisplayed() {
		if (screen.exists("images/Putty/Receiving/ReceivingInProgress.png") != null)
			return true;
		else
			return false;
	}

	public boolean isEnterABVForUpcDisplayed() {
		if (screen.exists("images/Putty/Receiving/EnterTheAbv.png") != null)
			return true;
		else
			return false;
	}

	public boolean isVintageNotExpectedDisplayed() {
		if (screen.exists("images/Putty/Receiving/VintageNotExpected.png") != null)
			return true;
		else
			return false;
	}

	public boolean isEnterVintageForUpcDisplayed() {
		if (screen.exists("images/Putty/Receiving/EnterVintage.png") != null)
			return true;
		else
			return false;
	}

	public void pressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
}
