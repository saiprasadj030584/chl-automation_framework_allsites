package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PurchaseOrderPutawayPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;

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
		Thread.sleep(2000);
	}

	public String getLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		System.out.println(screen.doubleClick(mStatus.getCenter().offset(50, 0)));
		/*screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);*/
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void completeProcess() throws InterruptedException {
		Thread.sleep(2500);
		screen.type(Key.ENTER);
	}

	public boolean isPutEnt() {
		if (screen.exists("images/Putty/Putaway/PutEnt.png") != null)
			return true;
		else
			return false;
	}
	
	public void enterCheckString(String chkString) throws InterruptedException {
		screen.type(chkString);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
}