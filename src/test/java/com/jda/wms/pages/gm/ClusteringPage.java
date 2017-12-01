package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class ClusteringPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterSiteID(String siteid) throws FindFailed, InterruptedException {
		Match mSiteId = screen.find("images/Clustering/siteid.png");
		screen.click(mSiteId.getCenter().offset(70, 0));
		screen.type(siteid);
		Thread.sleep(1000);
	}


	public void enterGroupId(String grpid) throws InterruptedException {
		screen.type(grpid);
		Thread.sleep(1000);

	}
}