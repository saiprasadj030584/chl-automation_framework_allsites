package com.jda.wms.pages.rdt;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.jda.wms.utils.Utilities;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class PurchaseOrderRelocatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectRelocateMenu() throws InterruptedException {
		screen.type("4");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectExistingRelocateMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterPalletId(String pallet) throws InterruptedException {
		screen.type(pallet);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void enterLocation(String location) throws InterruptedException, FindFailed {
		screen.wait("images/Putty/Receiving/Location.png", timeoutInSec);
		screen.click("images/Putty/Receiving/Location.png");
		screen.type(location);
		Thread.sleep(1000);
	}

	public void enterChks(String chk) throws InterruptedException, FindFailed {

		screen.type(chk);
		Thread.sleep(1000);
	}

	public boolean isRelPalCmpDisplayed() {
		if (screen.exists("images/Putty/Relocation/RelPalCmp.png") != null)
			return true;
		else
			return false;
	}

	public boolean isChkToDisplayed() {
		if (screen.exists("images/Putty/Relocation/ChkTo.png") != null)
			return true;
		else
			return false;
	}

	public boolean isNoRelocationDisplayed() {
		if (screen.exists("images/Putty/Relocation/NoRelocation.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRelEntDisplayed() {
		if (screen.exists("images/Putty/Relocation/RelEnt.png") != null)
			return true;
		else
			return false;
	}

	public void enterUPC(String upc) throws InterruptedException {
		screen.type(upc);
		Thread.sleep(1000);
		Thread.sleep(5000);
	}

	public void enterlocation(String location) throws InterruptedException {
		screen.type(location);
		Thread.sleep(1000);
		Thread.sleep(5000);
	}

	public String getRelocateLocation() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

}
