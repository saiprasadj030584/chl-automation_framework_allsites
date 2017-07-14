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
	private PuttyFunctionsPage puttyFunctionsPage;

	@Inject
	public PurchaseOrderReceivingPage(PuttyFunctionsPage puttyFunctionsPage) {
	this.puttyFunctionsPage = puttyFunctionsPage;
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
			puttyFunctionsPage.pressEnter();
			return true;
		}
		return false;
	}
	
	
	public boolean validate_no_asn_error() throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		if ((screen.exists("images/Putty/Receiving/No_ASN_Error.png") != null)){
			return true;
		}
//		else if ((screen.exists("images/Putty/Receiving/PreAdvComplete.png") != null)) {
//			puttyFunctionsPage.pressEnter();
//			return true;
//		}
		else
		return false;
		
	}

	public void enterPreAdvId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.type(preAdviceId);
		Thread.sleep(5000);
	}

	public void enterSKUId(String skuID) throws FindFailed, InterruptedException {
		screen.type(skuID);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
		Thread.sleep(13000);
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
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterLocation(String location) throws InterruptedException, FindFailed {
		screen.wait("images/Putty/Receiving/Location.png", timeoutInSec);
		screen.click("images/Putty/Receiving/Location.png");
		screen.type(location);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(3000);
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
			puttyFunctionsPage.pressEnter();
			return true;
		} else
			return false;
	}

	public void enterYes() throws InterruptedException {
		screen.type("y");
		Thread.sleep(3000);
		screen.type(Key.ENTER);
	}

	public boolean isMorePercentageAbv() {
		if (screen.exists("images/Putty/Receiving/MorePercentageAbv.png") != null)
			return true;
		else
			return false;
	}

	public boolean isPreAdviceCompletedDisplayed() {
		if (screen.exists("images/Putty/Receiving/PreAdvComplete.png") != null)
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

	public void enterURNID(String urn) throws FindFailed, InterruptedException {
//		screen.wait("images/Putty/Receiving/URN.png", timeoutInSec);
		screen.type(urn);
		Thread.sleep(2000);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(4000);
	}

	public boolean isLocationDisplayed() {
		if (screen.exists("images/Putty/Receiving/Location.png") != null)
			return true;
		else
			return false;
	}

	public String getTagId() throws FindFailed, InterruptedException {
		Match mTagId = screen.find("images/Putty/Receiving/TagId.png");
		screen.click(mTagId.getCenter().offset(50, 0));
		screen.doubleClick(mTagId.getCenter().offset(50, 0));
		String tag1 = App.getClipboard();
		screen.click(mTagId.below(5));
		screen.doubleClick(mTagId.below(1));
		String tag2 = App.getClipboard();
		Thread.sleep(1000);
		return (tag1+tag2);
	}

	public String getPackConfig() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/PackConfig.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public String getUPC() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/UPC.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public boolean isRcvPalletEntPageDisplayed() {
		if (screen.exists("images/Putty/Receiving/RcvPalletEnt.png") != null)
			return true;
		else
			return false;
	}

	public String getQtyToReceive() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/QtyToReceive.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public String getPallet() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/PutawayForLock.png");
		screen.click(mStatus.below(5));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		return App.getClipboard();
	}
}