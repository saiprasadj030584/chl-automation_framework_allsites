package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class SystemAllocationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public SystemAllocationPage() {

	}

	public void enterOrderId(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(2000);
	}

	public boolean isRecordExist() throws FindFailed {
		if (screen.exists("images/SystemAllocation/SiteId.png") != null) {
			return true;
		} else
			return false;
	}
	public boolean isShortagesTabDisplayed() throws FindFailed {
		if (screen.exists("images/SystemAllocation/QtyHardAllocated.png") != null) {
			return true;
		} else
			return false;
	}
	public String getShortagesTabDisplayed() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		screen.find("images/SystemAllocation/QtyHardAllocated.png");
		//screen.click(mLocation.getCenter().offset(70, 0));
		return App.getClipboard();
	}
	
}
