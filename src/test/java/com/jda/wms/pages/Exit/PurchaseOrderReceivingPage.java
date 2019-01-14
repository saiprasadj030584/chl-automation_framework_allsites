package com.jda.wms.pages.Exit;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.inject.Inject;


public class PurchaseOrderReceivingPage<EnterPalletID> {
	static Screen screen = new Screen();
	int timeoutInSec = 20;
	private JdaHomePage imageCheckFunction;
	
	@Inject
	public PurchaseOrderReceivingPage(JdaHomePage imageCheckFunction) {
		this.imageCheckFunction = imageCheckFunction;
		
	}

	public void selectUserDirectedMenu() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectvehicleDirectedMenu() throws FindFailed, InterruptedException {
		screen.type("7");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectvehicleloadMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectMultiPalletloadMenu() throws FindFailed, InterruptedException {
		screen.type("6");
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
		screen.type(skuID);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
		Thread.sleep(13000);
	}

	public String getPreAdvId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/Pre-AdviceDisplayed.png");
		screen.click(mStatus.getCenter().offset(50, 4));
//		screen.doubleClick(mStatus.getCenter().offset(0, 4));
		screen.doubleClick(mStatus.getCenter().offset(0, 4));
		System.out.println(mStatus.getCenter().offset(0, 4));
		Location actualUPC =mStatus.getCenter().offset(0, 4);
//		String prefixlist=StringUtils.substring(actualUPC, 0, 4);
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String getUPC2() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/UPC2.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.doubleClick(mStatus.getCenter().offset(70,0));		
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String get() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/UPC2.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.doubleClick(mStatus.getCenter().offset(70,0));		
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String getSupplier() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Supplier.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.doubleClick(mStatus.getCenter().offset(70,0));		
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String getQTY() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/QTY.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.doubleClick(mStatus.getCenter().offset(70,0));		
		Thread.sleep(2000);
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
		Match mSupplier = screen.find("images/Putty/Receiving/SuppDisplayed.png");
		screen.click(mSupplier.getCenter().offset(50, 0));
		screen.doubleClick(mSupplier.getCenter().offset(50, 0));
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
	public void EnterPalletID(String palletID) throws InterruptedException {
		screen.type(palletID);
		Thread.sleep(1000);
		screen.type(Key.TAB);
	}
	
	public void EnterUPC(String UPC) throws InterruptedException {
		screen.type(Key.TAB);
		screen.type(UPC);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
	}
	public void Entertag(String tag) throws InterruptedException {
	screen.type(tag);
		Thread.sleep(1000);
		
	}
	public void EnterToPallet(String ToPallet) throws InterruptedException {
		screen.type(ToPallet);
		Thread.sleep(1000);
		screen.type(Key.TAB);

	}
	
	public void EnterToExpirydate(String Expirydate) throws InterruptedException {
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Expirydate);
		
		Thread.sleep(1000);
		screen.type(Key.TAB);

	}
	public void EnterBel(String belCode) throws InterruptedException {
		screen.type(belCode);
		Thread.sleep(1000);

	}

	public void enterTagId(String uniqueId) throws InterruptedException {
		screen.type(uniqueId);
		screen.type(Key.TAB);
//		screen.type("x", Key.CTRL);
//		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void enterQtyToReceive(String qtyToReceive) throws InterruptedException {
		if (Integer.parseInt(qtyToReceive) > 999) {
			screen.type(qtyToReceive);
			screen.type(Key.TAB);
			Thread.sleep(1000);
		} else {
			screen.type(qtyToReceive);
			screen.type(Key.TAB);
			Thread.sleep(1000);
		} 
		
	}

	public void enterCaseRatio(String caseRatio) throws InterruptedException {
		screen.type(caseRatio);
		Thread.sleep(3000); 
		System.out.println("Why Enter");
//		screen.type("x", Key.CTRL);
//		screen.type(Key.NUM4);
//		Thread.sleep(5000);
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
	public boolean isErrorMsgDisplayed() {
		if (screen.exists("images/Putty/Picking/sortingError.png") != null)
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
	public boolean isProhibition() throws FindFailed {
		if (screen.exists("images/Putty/Receiving/FSVProhibition.png") != null){
			Match mSupplier = screen.find("images/Putty/Receiving/FSVProhibition.png");
			screen.click(mSupplier.getCenter().offset(50, 0));
			return true;
			
		}
			
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
	
	public boolean isInfDisplayed() {
		if (screen.exists("images/Putty/Receiving/1Inf.png") != null)
			return true;
		else
			return false;
	} 
	
	

	public String getSKUId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/SKUDisplayed.png");
		screen.click(mStatus.below(5));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		return App.getClipboard();
	}

	public void enterVintage(String vintage) throws InterruptedException {
//		screen.type(Key.TAB);
		screen.type(Key.TAB);
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
		screen.type("Y");
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

	public void pressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	
	public void pressTab() throws InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(2000);
	}
	public void proceedToNextScreen() throws InterruptedException {
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(5000);
	}

	public void enterYesOrNo()throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Putaway/YesNo.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type("N");
		
	}

	public String getExpiryDate()  throws FindFailed {
		Match mStatus = screen.find("images/Putty/Receiving/ExpDate1.png");
		screen.doubleClick(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	}

