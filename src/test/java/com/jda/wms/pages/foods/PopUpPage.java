package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class PopUpPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void clickYes() throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/Yes.png", timeoutInSec);
		screen.click("images/PreAdviceLine/Yes.png");
		Thread.sleep(4000);
	}

}
