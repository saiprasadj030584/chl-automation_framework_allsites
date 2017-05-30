package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
public class VehicleUnloadingPage {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public VehicleUnloadingPage(JdaHomePage jdaHomePage, JDAFooter jdafooter) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
	}

	public void enterSiteId() throws FindFailed, InterruptedException {
		screen.type("9771");
		screen.type(Key.TAB);
		screen.type(Key.TAB);
	}
	
	public void enterConsignment(String consignment) throws FindFailed, InterruptedException {
		screen.type(consignment);
		screen.type(Key.TAB);
	}
	
	public void enterPallet(String pallet) throws FindFailed, InterruptedException {
		screen.type(pallet);
	}
	
	
	public void selectPallet() throws FindFailed, InterruptedException {
		Match mSellectPallet = screen.find("images/VehicleUnloading/Finish/SelectPallet.png");
		Thread.sleep(2000);
		screen.doubleClick(mSellectPallet.below(10));
	}
}
