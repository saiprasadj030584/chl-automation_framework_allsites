package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class WarningPopUpPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void clickYes() throws FindFailed, InterruptedException {
		if (screen.exists("images/PreAdviceLine/Yes.png") != null) {
			screen.click("images/PreAdviceLine/Yes.png");
			Thread.sleep(1000);
		}
	}

	public void clickYesButtonOnSecondPopup() throws FindFailed, InterruptedException {

		if (screen.exists("/images/LocationMaintenance/WarningSecondPopup.png") != null) {
			screen.click("/images/LocationMaintenance/YESButton.png");
			Thread.sleep(1000);
		}
	}

	public void clickOk() throws FindFailed {
		screen.wait("images/OrderPreparation/Ok.png", timeoutInSec);
		screen.click("images/OrderPreparation/Ok.png");
	}
}
