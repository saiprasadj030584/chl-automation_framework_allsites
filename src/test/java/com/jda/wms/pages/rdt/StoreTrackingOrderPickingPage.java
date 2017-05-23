package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class StoreTrackingOrderPickingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectPickingMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPickMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/PickMenu.png") != null)
			return true;
		else
			return false;
	}

	public void selectPickingInPickMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPickTaskMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/PickTaskMenu.png") != null)
			return true;
		else
			return false;
	}

	public void selectContainerPickMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPickEntryDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		if ((screen.exists("images/Putty/Picking/PickEntry.png") != null))
			return true;
		else
			return false;
	}

	public void enterOrderID(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}
	
	public void pressTab() throws InterruptedException{
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}
	
	public void pressEnter() throws InterruptedException{
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void enterListID(String listId) throws InterruptedException {
		screen.type(listId);
		Thread.sleep(1000);
	}

	public String getListIDDisplayed() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ListID.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
}
