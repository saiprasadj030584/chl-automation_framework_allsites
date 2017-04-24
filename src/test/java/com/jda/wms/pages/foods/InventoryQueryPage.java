package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public InventoryQueryPage() {
	}

	public void enterTagId(String tagId) throws FindFailed {
		screen.type(tagId);
	}

	public String getStatus() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/Status.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void clickStatus() throws FindFailed {
		screen.wait("/images/InventoryQuery/Status.png", timeoutInSec);
		screen.click("/images/InventoryQuery/Status.png");
	}

	public void selectRefreshOptions() throws FindFailed {
		screen.wait("images/RefreshOption.png", timeoutInSec);
		screen.click("images/RefreshOption.png");
	}

	public void selectRefreshCurrentRecord() throws FindFailed {
		screen.wait("images/RefreshCurrentRecord.png", timeoutInSec);
		screen.click("images/RefreshCurrentRecord.png");
	}

	public String getLockCode() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/lockCode.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void refreshInventoryQueryPage() throws FindFailed, InterruptedException {
		clickStatus();
		screen.rightClick();
		selectRefreshOptions();
		selectRefreshCurrentRecord();
	}

}
