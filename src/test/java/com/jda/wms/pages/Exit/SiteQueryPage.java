package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class SiteQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterSiteID(String sietID) throws FindFailed {
		screen.wait("images/SiteQuery/SiteId.png", timeoutInSec);
		screen.click("images/SiteQuery/SiteId.png");
		screen.type(sietID);
	}
}
