package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class PopUpPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void clickYes() throws FindFailed, InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

}
