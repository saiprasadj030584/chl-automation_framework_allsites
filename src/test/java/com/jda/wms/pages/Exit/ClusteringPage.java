package com.jda.wms.pages.Exit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class ClusteringPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterSiteID(String siteId) throws FindFailed {
		screen.wait("images/Clustering/SiteId.png", timeoutInSec);
		screen.click("images/Clustering/SiteId.png");
		screen.type(siteId);
		screen.type(Key.ENTER);
	}

	public void enterGroupID(String groupId) throws FindFailed {
		screen.wait("images/Clustering/GroupId1.png", timeoutInSec);
		screen.click("images/Clustering/GroupId1.png");
		screen.type(groupId);

	}

	public boolean isRecordExists() {
		if (screen.exists("images/Clustering/Record.png") != null)
			return true;
		else
			return false;
	}

}
