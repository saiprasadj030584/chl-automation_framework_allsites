package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class TrailerShippingPage {
	Screen screen = new Screen();
	private final JDAFooter jdaFooter;

	@Inject
	public TrailerShippingPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void enterSiteID(String siteID) throws InterruptedException {
		screen.type(siteID);
		Thread.sleep(1000);
	}

	public void enterTrailerNumber(String trailerNo) throws InterruptedException {
		screen.type(trailerNo);
		Thread.sleep(1000);

	}

	public void enterSealNo(String sealno) throws FindFailed, InterruptedException {
		Match mSeal = screen.find("images/TrailerShipping/SealNO.png");
		screen.click("images/TrailerShipping/SealNO.png");	
		Thread.sleep(1000);
		screen.type(sealno);
		Thread.sleep(1000);
	}

	public void clickOkButton() throws InterruptedException, FindFailed {
		Match mSeal = screen.find("images/TrailerShipping/ok.png");
		screen.click("images/TrailerShipping/ok.png");	
		Thread.sleep(1000);
		
	}

}
