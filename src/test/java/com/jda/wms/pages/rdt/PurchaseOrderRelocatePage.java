package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class PurchaseOrderRelocatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectRelocateMenu() throws InterruptedException {
		screen.type("4");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectExistingRelocateMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterPalletId(String pallet) throws InterruptedException {
		screen.type(pallet);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void enterUPC(String upc) throws InterruptedException {
		screen.type(upc);
		Thread.sleep(1000);
		Thread.sleep(5000);
	}

	public void enterlocation(String location) throws InterruptedException {
		screen.type(location);
		Thread.sleep(1000);
		Thread.sleep(5000);
	}

	public String getRelocateLocation() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

}
