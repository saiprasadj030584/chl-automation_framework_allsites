package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PurchaseOrderPutawayPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	Region reg = new Region(0, 0, 2000, 800);
	private PuttyFunctionsPage puttyFunctionsPage;

	@Inject
	public PurchaseOrderPutawayPage(PuttyFunctionsPage puttyFunctionsPage) {
		this.puttyFunctionsPage = puttyFunctionsPage;
	}

	public void selectPutawayMenu() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectNormalPutawayMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterTagId(String tagId) throws InterruptedException {
		screen.type(tagId);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public String getLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void mimimizePuty() throws FindFailed, InterruptedException {
		screen.wait("/images/Putty/MinimizePutty.png", timeoutInSec);
		screen.click("/images/Putty/MinimizePutty.png");
		screen.rightClick();
		Thread.sleep(2000);
		screen.wait("/images/Putty/Minimize.png", timeoutInSec);
		screen.click("/images/Putty/Minimize.png");
	}

	public void clickPuttyIcon() throws FindFailed, InterruptedException {
		screen.wait("/images/Putty/ClickPuttyIcon.png", timeoutInSec);
		screen.click("/images/Putty/ClickPuttyIcon.png");
		Thread.sleep(2000);
	}

	public void completeProcess() throws InterruptedException {
		Thread.sleep(5000);
		screen.type(Key.ENTER);
	}

	public boolean isPutEntDisplayed() {
		if (screen.exists("images/Putty/Putaway/PutEnt.png") != null)
			return true;
		else
			return false;
	}

	public void enterCheckString(String chkString) throws InterruptedException {
		screen.type(chkString);
		Thread.sleep(2000);
	}

	public void enterLocation(String location) throws InterruptedException {
		screen.type(location);

		Thread.sleep(3000);
	}

	public boolean isSPWovrPageDisplayed() throws InterruptedException, FindFailed {
		if (!screen.find("/images/Putty/Putaway/SPWovr.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public void enterReasonToOverride() throws InterruptedException {
		Thread.sleep(3000);
		screen.type("2");
		Thread.sleep(2000);
	}

	public void enterToLocation(String location) throws InterruptedException {
		screen.type(Key.TAB);
		screen.type(location);
		Thread.sleep(2000);
	}

	public void enterURNID(String palletId) throws InterruptedException {
		screen.type(palletId);
		Thread.sleep(2000);
	}

	public boolean isPutCmpPageDisplayed() {
		if (screen.exists("images/Putty/Putaway/PutCmp.png") != null)
			return true;
		else
			return false;
	}

	public String getFromLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Putaway/FromLocation.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public String getToLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		String[] rcvLockSplit = App.getClipboard().split("_");
		if (!(rcvLockSplit.length > 0)) {
			return null;
		}
		return rcvLockSplit[0];
	}

	public String getTagId() throws FindFailed, InterruptedException {
		Match mTagId = screen.find("images/Putty/Putaway/TagId.png");
		screen.click(mTagId.below(5));
		screen.doubleClick(mTagId.below(2));
		Thread.sleep(1000);
		return App.getClipboard();
	}

	public boolean isChkToDisplayed() {
		if (screen.exists("images/Putty/Putaway/ChkTo.png") != null)
			return true;
		else
			return false;
	}

	public boolean isLocationErrorDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		if (screen.exists("images/Putty/Putaway/LocationError.png") != null) {
			return true;
		} else
			return false;
	}

	public boolean isQuantityErrorDisplayed() {
		if (screen.exists("images/Putty/Putaway/QuantityError.png") != null)
			return true;
		else
			return false;
	}

	public void navigateToBackScreen() throws InterruptedException {
		screen.type(Key.F12);

		Thread.sleep(1000);
	}

	public void enterQuantity(String quantity) throws InterruptedException {
		screen.type(quantity);
		Thread.sleep(2000);
	}

	public void selectLocationFullMenu() throws InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
	}

	public void selectOverride() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isLocationWarningrDisplayed() {
		if (screen.exists("images/Putty/Putaway/LocationWarningMsg.png") != null)
			return true;
		else
			return false;
	}

	public String getPutawayLocation() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public boolean isLocationFullDisplayed() {
		if (screen.exists("images/Putty/Putaway/LocationFull.png") != null)
			return true;
		else
			return false;
	}

	public boolean isSearchForNewPutawayDisplayed() {
		if (screen.exists("images/Putty/Putaway/SearchForNewPutawayLocation.png") != null)
			return true;
		else
			return false;
	}
}
