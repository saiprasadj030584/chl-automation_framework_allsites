package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class ShipDockReassignmentPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderNo(String orderId) throws FindFailed {
		screen.wait("images/ShipDockReassignment/Order.png", timeoutInSec);
		screen.click("images/ShipDockReassignment/Order.png");
		screen.type(orderId);
	}

	public void enterSiteID(String siteId) throws FindFailed {
		screen.wait("images/ShipDockReassignment/FromSiteID.png", timeoutInSec);
		screen.click("images/ShipDockReassignment/FromSiteID.png");
		screen.type(siteId);
	}

	public boolean is1RecordDisplayed() throws FindFailed {
		if (!screen.find("images/ShipDockReassignment/1Record.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public void enterNewShipDockName(String shipDockName) throws FindFailed {
		screen.wait("images/ShipDockReassignment/NewShipDock.png", timeoutInSec);
		screen.click("images/ShipDockReassignment/NewShipDock.png");
		screen.type(shipDockName);
	}
}
