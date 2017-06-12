package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
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
		if (!screen.find("images/SystemAllocation/SiteId.png").equals(null)) {
			return true;
		} else
			return false;
	}
}
