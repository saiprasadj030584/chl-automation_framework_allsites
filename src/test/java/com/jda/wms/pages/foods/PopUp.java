package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class PopUp {
	Screen screen = new Screen();
	int timeoutInSec = 20;


	public void clickYes() throws FindFailed {
		screen.find("images/PopUp/yes.png");
		screen.click("images/PopUp/yes.png");
}
	}
