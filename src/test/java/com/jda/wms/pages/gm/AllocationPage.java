package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class AllocationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderId(String orderId) throws FindFailed, InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}
	
	public void clickClusteringCheckBox() throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/SystemAllocation/ClusteringUnCheck.png", timeoutInSec);
		screen.click("images/SystemAllocation/ClusteringUnCheck.png");
	}
}
