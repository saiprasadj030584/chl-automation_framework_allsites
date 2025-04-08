package com.jda.wms.UI.pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.utils.Utilities;

public class TrailerShippingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	private final Context context;
	private final JDAFooter jdaFooter;

	@Inject
	public TrailerShippingPage(JDAFooter jdaFooter, Context context) {
		this.context = context;
		this.jdaFooter = jdaFooter;
	}

	// FIXME move this method into JDAHomePage.java
	public void clickOk() throws FindFailed, InterruptedException {
		jdaFooter.clickNextButton();
	}

	public void enterSiteID(String siteId) throws FindFailed, InterruptedException {
		Match mSiteID = screen.find("images/TrailerShipping/SiteId.png");
		screen.click(mSiteID.getCenter().offset(70, 0));
		screen.type(siteId);
		screen.type(Key.ENTER);
		screen.type(Key.TAB);
	}

	public void enterTrailerNo(String trailerNo) throws FindFailed, InterruptedException {
		screen.type(trailerNo);
	}

	public void enterSealNo() throws FindFailed, InterruptedException {
		Match mSiteID = screen.find("images/TrailerShipping/SealNo.png");
		screen.click(mSiteID.getCenter().offset(70, 0).below(15));
		screen.type(Utilities.getFiveDigitRandomNumber());
		Thread.sleep(3000);
	}

}
