package com.jda.wms.pages.rdt;

import org.sikuli.script.Key;
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
}
