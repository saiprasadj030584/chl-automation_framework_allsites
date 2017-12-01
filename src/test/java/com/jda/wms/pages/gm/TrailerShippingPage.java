package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class TrailerShippingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterSiteId(String string) throws InterruptedException, FindFailed {
		Match skuID = screen.find("images/TrailerShipping/SiteID.png");
		screen.click(skuID.getCenter().offset(70, 0));
		screen.type(string);
		Thread.sleep(1000);
	}

	public void enterTrailer(String trailerNo) throws InterruptedException, FindFailed {
		Match skuID = screen.find("images/TrailerShipping/Trailer.png");
		screen.click(skuID.getCenter().offset(70, 0));
		screen.type(trailerNo);
		Thread.sleep(1000);
	}

	public void enterSealNo(String sealNo) throws FindFailed, InterruptedException {
		Match skuID = screen.find("images/TrailerShipping/SealNO.png");
		screen.click(skuID.getCenter().offset(70, 0));
		screen.type(sealNo);
		Thread.sleep(1000);
	}

	public boolean isRecordDisplayed() {
		if (screen.exists("images/Putty/Picking/pickcmp.png") != null)
			return true;
		else
			return false;
	}

	public boolean isConfirmationMsgDisplayed() {
		if (screen.exists("images/Putty/Picking/pickcmp.png") != null)
			return true;
		else
			return false;
	}

	public void clickOk() throws FindFailed, InterruptedException {
		Match skuID = screen.find("images/TrailerShipping/ok.png");
		screen.click(skuID.getCenter().offset(70, 0));
		Thread.sleep(1000);

	}

	public void clickOne() throws InterruptedException, FindFailed {
		Match skuID = screen.find("images/TrailerShipping/One.png");
		screen.click(skuID.getCenter().offset(70, 0));
		Thread.sleep(1000);

	}
	
}
