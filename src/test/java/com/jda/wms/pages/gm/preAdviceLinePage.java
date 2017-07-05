package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class preAdviceLinePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectlockcode(String lockcode) throws FindFailed {
		screen.type(Key.F2);
		screen.wait("/images/PreAdviceLine/LockCodegm.png", timeoutInSec);
		screen.click("/images/PreAdviceLine/LockCodegm.png");
		screen.type(lockcode);
		screen.type(Key.F7);
	}

}
