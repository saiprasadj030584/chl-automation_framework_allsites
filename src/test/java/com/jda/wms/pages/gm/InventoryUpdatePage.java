package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class InventoryUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterTagID(String tagId) throws FindFailed, InterruptedException {
		// Match mtagId =
		// screen.find("/images/InventoryUpdate/Search/TagId.png");
		// screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
		Thread.sleep(1000);
	}

	public void enterSku(String skuId) throws FindFailed, InterruptedException {
		// Match skuID =
		// screen.find("images/InventoryUpdate/Search/GmSkuID.png");
		// screen.click(skuID.getCenter().offset(70, 0));
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void enterLocation(String locationID) throws FindFailed, InterruptedException {
		Match Location = screen.find("/images/InventoryUpdate/Search/locations.png");
		screen.click(Location.getCenter().offset(70, 0));
		screen.type(locationID);
		Thread.sleep(2000);
	}

	public boolean isRecordDisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/Results/OneRecord.png") != null) {
			return true;
		} else
			return false;
	}

	public void enterStatus(String status) throws FindFailed, InterruptedException {

		screen.type(status);
		Thread.sleep(1000);
	}

	public void enterSelectType(String status) throws FindFailed, InterruptedException {

		screen.type(status);
		Thread.sleep(1000);
	}

	public void enterExpiryDate(String futuredate) throws InterruptedException {
		screen.type(futuredate);
		Thread.sleep(1000);

	}

	public boolean isWarningPopUpPageExist() {
		if (screen.exists("images/InventoryUpdate/Finish/Warning.png") != null) {
			return true;
		} else
			return false;
	}

	public void enterCondition(String condition) throws InterruptedException {
		screen.type(condition);
		Thread.sleep(1000);

	}

	public void enterOwner(String owner) throws InterruptedException {
		screen.type(owner);
		Thread.sleep(1000);

	}

	public void enterPackConfig(String packConfig) throws InterruptedException {
		screen.type(packConfig);
		Thread.sleep(1000);
	}

	public void enterLockcode(String string) throws FindFailed, InterruptedException {
		screen.type(string);
		Thread.sleep(1000);
	}
}
