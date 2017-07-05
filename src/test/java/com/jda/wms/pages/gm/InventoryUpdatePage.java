package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class InventoryUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void entertagID(String tagId) throws FindFailed {
		Match mtagId = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}

	public void entersku(String skuId) throws FindFailed {
		Match skuID = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(skuID.getCenter().offset(70, 0));
		screen.type(skuId);
	}

	public void enterLocation(String locationID) throws FindFailed {
		Match Location = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(Location.getCenter().offset(70, 0));
		screen.type(locationID);
	}

	public boolean isRecorddisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/Start/SelectType.png") != null) {
			return true;
		} else
			return false;
	}

	public void enterStatus(String status) throws FindFailed {
		Match Status = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(Status.getCenter().offset(70, 0));
		screen.type(status);
	}
}
