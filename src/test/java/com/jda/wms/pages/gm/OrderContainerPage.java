package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class OrderContainerPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderId(String orderId) throws FindFailed {
		screen.type(orderId);
	}

}
