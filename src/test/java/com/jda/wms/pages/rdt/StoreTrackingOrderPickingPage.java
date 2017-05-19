package com.jda.wms.pages.rdt;

import org.sikuli.script.Key;
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
}
