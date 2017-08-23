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

	public String getPutawayLocation() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterCheckString(String checkString) throws InterruptedException {
		screen.type(checkString);
		Thread.sleep(2000);
	}

	public boolean isPickCmpPageDisplayed() {
		if (screen.exists("images/Putty/Putaway/PutCmp.png") != null)
			return true;
		else
			return false;
	}

}
