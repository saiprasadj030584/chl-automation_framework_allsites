package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockCheckTaskQueryPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public StockCheckTaskQueryPage() {
	}

	public void enterLocation(String location) throws FindFailed {
		screen.wait("images/StockCheckQuery/Location.png", timeoutInSec);
		screen.click("images/StockCheckQuery/Location.png");
		screen.type(location);
	}

	public void selectSiteId(String siteId) throws FindFailed {
		screen.wait("images/StockCheckQuery/SiteId.png", timeoutInSec);
		screen.click("images/StockCheckQuery/SiteId.png");
		screen.type(siteId);
		screen.type(Key.ENTER);
	}

	public void enterTaskDate() throws FindFailed {
		screen.wait("images/StockCheckQuery/TaskDate.png", timeoutInSec);
		screen.click("images/StockCheckQuery/TaskDate.png");
		screen.type("0");
		screen.type(Key.ENTER);
	}

	public String getListId() throws FindFailed {
		Match mKitId = screen.find("/images/StockCheckQuery/ListId.png");
		screen.click(mKitId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterTagId(String tagId) throws FindFailed {
		// screen.wait("images/StockCheckTask/TagId.png", timeoutInSec);
		// screen.click("images/StockCheckTask/TagId.png");
		screen.type(tagId);
	}

}
