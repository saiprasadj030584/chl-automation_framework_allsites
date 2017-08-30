package com.jda.wms.pages.rdt;

import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class PurchaseOrderMarshallingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectMarshallMenu() throws InterruptedException {
		screen.type("6");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterPalletID(String palletID) throws InterruptedException {
		screen.type(palletID);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public boolean ismarshallcompPageDisplayed() {
		if (screen.exists("images/Putty/Putaway/PutCmp.png") != null)
			return true;
		else
			return false;
	}

}
