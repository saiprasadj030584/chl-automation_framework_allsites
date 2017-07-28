package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class DeliveryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	public void enterAsnId(String asnId) throws FindFailed, InterruptedException {
		screen.type(asnId);
		Thread.sleep(1000);
	}
	
	}

