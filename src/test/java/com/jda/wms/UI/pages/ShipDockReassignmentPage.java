package com.jda.wms.UI.pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class ShipDockReassignmentPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderNo(String orderId) throws FindFailed {
		screen.wait("images/ShipDockReassignment/OrderNo.png", timeoutInSec);
		screen.click("images/ShipDockReassignment/OrderNo.png");
		screen.type(orderId);
	}

	public void enterSiteID(String siteId) throws FindFailed {
		screen.wait("images/ShipDockReassignment/FromSiteId.png", timeoutInSec);
		screen.click("images/ShipDockReassignment/FromSiteId.png");
		screen.type(siteId);
	}

	public void enterNewShipDockName(String shipDockName) throws FindFailed {
		screen.wait("images/ShipDockReassignment/NewShipDock.png", timeoutInSec);
		screen.click("images/ShipDockReassignment/NewShipDock.png");
		screen.type(shipDockName);
	}

	public boolean is1RecordDisplayed() throws FindFailed {
		if (!screen.find("images/ShipDockReassignment/1Record.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

}
