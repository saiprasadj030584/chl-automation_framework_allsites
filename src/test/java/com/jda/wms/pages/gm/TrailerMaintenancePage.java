package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class TrailerMaintenancePage {
	Screen screen = new Screen();
	private final JDAFooter jdaFooter;

	@Inject
	public TrailerMaintenancePage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void enterTrailerNo(String trailerNo) throws FindFailed, InterruptedException {
		screen.type(trailerNo);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterTrailerType() throws FindFailed, InterruptedException {
		screen.type("TRAILER");
		Thread.sleep(2000);
	}

}
