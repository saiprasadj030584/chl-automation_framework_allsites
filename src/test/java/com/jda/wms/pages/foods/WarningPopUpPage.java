package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class WarningPopUpPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	


	public void clickYesButtonOnSecondPopup() throws FindFailed, InterruptedException {

		if (screen.exists("/images/LocationMaintenance/WarningSecondPopup.png") != null) {
			screen.click("/images/LocationMaintenance/YESButton.png");
			Thread.sleep(1000);
		}
	}
}
