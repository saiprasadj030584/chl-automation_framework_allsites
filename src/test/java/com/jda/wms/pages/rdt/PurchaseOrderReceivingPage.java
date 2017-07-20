package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PurchaseOrderReceivingPage {
	Screen screen = new Screen();
	Context context=new Context();
	int timeoutInSec = 20;
	private PuttyFunctionsPage puttyFunctionsPage;

	@Inject
	public PurchaseOrderReceivingPage(PuttyFunctionsPage puttyFunctionsPage,Context context) {
	this.puttyFunctionsPage = puttyFunctionsPage;
	this.context=context;
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
	
	public void selectBlindReceive() throws FindFailed, InterruptedException {
		screen.type("1");
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
	public boolean isBlindEntryDisplayed() throws FindFailed, InterruptedException {
		Thread.sleep(10000);
		if ((screen.exists("images/Putty/Receiving/BlindReceivingEntry.png") != null)){
			return true;
				}
		return false;
	}
	
	
	public boolean isBlindReceivingDone() throws FindFailed, InterruptedException {
		if ((screen.exists("images/Putty/Receiving/Imperfect_error.png") != null) || (screen.exists("images/Putty/Receiving/Singleshoe_error.png") != null)){
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null) || (screen.exists("images/Putty/Receiving/ReturnsCompletedImperfect_N.png") != null)||(screen.exists("images/Putty/Receiving/ReturnsCompletedSingleshoe_N.png") != null) ){
				puttyFunctionsPage.pressEnter();
			return true;
				}
		}
		return false;
	}
	
	
	public boolean validate_no_asn_error() throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		if ((screen.exists("images/Putty/Receiving/No_ASN_Error.png") != null)){
			return true;
		}
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
	public void enterQuantity(String count) throws InterruptedException {
		screen.type(count);
		
		Thread.sleep(2000);
	}
	public void enterPerfectCondition(String condition) throws InterruptedException {
		screen.type(condition);
		
		Thread.sleep(2000);
	}
	public void enterLocationInBlindReceive(String location) throws InterruptedException {
		screen.type(location);
		Thread.sleep(2000);
	}
	public void enterSupplierId(String id) throws InterruptedException {
		screen.type(id);
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
		screen.type(urn);
		Thread.sleep(2000);
	}
	
	public void enterUPC1BEL(String upc) throws FindFailed, InterruptedException {
		screen.type(upc);
		Thread.sleep(2000);
	}
	public void enterUPC2(String upc) throws FindFailed, InterruptedException {
		screen.type(upc);
		Thread.sleep(2000);
	}
	
	public void enterPalletId(String palletID) throws InterruptedException {
		screen.type(palletID);
		Thread.sleep(2000);
		Thread.sleep(4000);		
	}
	
	public void enterBelCode(String getbelCode) throws InterruptedException {
		screen.type(getbelCode);
		Thread.sleep(2000);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(4000);
	}

	public void enterNewPallet(String getnewpallet) throws InterruptedException {
		screen.type(getnewpallet);
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