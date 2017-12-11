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


	public void enterTrailer(String trailerNo) throws InterruptedException, FindFailed {
		Match skuID = screen.find("images/TrailerShipping/Trailer.png");
		screen.click(skuID.getCenter().offset(70, 0));
		screen.type(trailerNo);
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

	}
	

	public void enterTrailerNumber(String trailerNo) throws InterruptedException {
		screen.type(trailerNo);

		Thread.sleep(1000);

	}



	public void clickOne() throws InterruptedException, FindFailed {
		Match skuID = screen.find("images/TrailerShipping/One.png");
		screen.click(skuID.getCenter().offset(70, 0));
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
