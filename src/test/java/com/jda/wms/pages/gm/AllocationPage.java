package com.jda.wms.pages.gm;

import org.sikuli.script.Screen;

public class AllocationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderId(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);

	}

}
