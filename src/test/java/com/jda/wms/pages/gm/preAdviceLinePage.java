package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class preAdviceLinePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectlockcode(String lockcode) throws FindFailed {
		screen.wait("/images/GMPreAdviceLine/LockCode.png", timeoutInSec);
		screen.click("/images/GMPreAdviceLine/LockCode.png");
		screen.type(lockcode);
		screen.type(Key.ENTER);
	}

}
