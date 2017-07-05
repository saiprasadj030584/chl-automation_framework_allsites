package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class InventoryUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void entertagID(String tagId) throws FindFailed {
		// TODO Change image
		Match mtagId = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}

	public void entersku(String skuId) throws FindFailed {
		// TODO Change image
		Match skuID = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(skuID.getCenter().offset(70, 0));
		screen.type(skuId);
	}

	public void enterLocation(String locationID) throws FindFailed {
		// TODO Change image
		Match Location = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(Location.getCenter().offset(70, 0));
		screen.type(locationID);
	}

	public boolean isRecordDdisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/Results/OneRecord.png") != null) {
			return true;
		} else
			return false;
	}

	public void enterStatus(String status) throws FindFailed, InterruptedException {
		// Match Status =
		// screen.find("/images/InventoryUpdate/Search/TagId.png");
		// screen.click(Status.getCenter().offset(70, 0));
		screen.type(status);
		Thread.sleep(1000);
	}

	public void enterselectType(String status) throws FindFailed, InterruptedException {
		// TODO change image
		// Match Status =
		// screen.find("/images/InventoryUpdate/Search/TagId.png");
		// screen.click(Status.getCenter().offset(70, 0));
		screen.type(status);
		Thread.sleep(1000);
	}
}
