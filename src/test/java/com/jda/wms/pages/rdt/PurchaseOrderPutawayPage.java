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

	@Inject
	public PurchaseOrderPutawayPage() {
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
		/*
		 * reg.getBottomLeft(); screen.getBottomLeft();
		 */
		screen.wait("/images/Putty/ClickPuttyIcon.png", timeoutInSec);
		screen.click("/images/Putty/ClickPuttyIcon.png");
		Thread.sleep(2000);
	}

	public void completeProcess() throws InterruptedException {
		Thread.sleep(5000);
		screen.type(Key.ENTER);
	}

	public boolean isPutEnt() {
		if (screen.exists("images/Putty/Putaway/PutEnt.png") != null)
			return true;
		else
			return false;
	}

	public void enterCheckString(String chkString) throws InterruptedException {
		Thread.sleep(3000);
		screen.type(chkString);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void enterLocation(String location) throws InterruptedException {
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(location);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
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
}