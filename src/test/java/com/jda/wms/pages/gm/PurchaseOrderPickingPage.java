package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class PurchaseOrderPickingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectPickingMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectPickingMenu2() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectContainerPick() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterListId(String ListID) throws InterruptedException {
		screen.type(ListID);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void enterPrinterNO(String string) throws InterruptedException {
		screen.type(string);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public String getPickingLocation() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Picking/ToLoctn.png");
		Thread.sleep(2000);
		screen.doubleClick((mSupplierId.below(10)));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterCheckString(String checkString) throws InterruptedException {
		screen.type(checkString);
		Thread.sleep(2000);
	}

	public boolean isPickCmpPageDisplayed() {
		if (screen.exists("images/Putty/Picking/pickcmp.png") != null)
			return true;
		else
			return false;
	}

}
