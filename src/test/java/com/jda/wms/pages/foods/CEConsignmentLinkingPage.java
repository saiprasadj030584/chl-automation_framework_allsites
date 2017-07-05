package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import com.google.inject.Inject;

public class CEConsignmentLinkingPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	//private final JDAFooter jdaFooter;

	/*@Inject
	public CEConsignmentLinkingPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}*/
	public void enterSiteID(String siteID) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentLinking/SiteID.png", timeoutInSec);
		screen.type(siteID);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}
	public void enterPreAdviceId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentLinking/PreAdviceID.png", timeoutInSec);
		screen.type(preAdviceId);
		Thread.sleep(1000);
	}
	public void enterConsignmentID(String consignmentID) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentLinking/ConsignmentID.png", timeoutInSec);
		screen.type(consignmentID);
		Thread.sleep(1000);
	}
	public boolean isNoRowsDisplayed() {
		if (screen.exists("images/CEConsignmentLinking/NoRowsSelected.png") != null)
			return true;
		else
			return false;
	}
}
