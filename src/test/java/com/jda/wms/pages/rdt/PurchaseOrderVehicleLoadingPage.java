
package com.jda.wms.pages.rdt;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PurchaseOrderVehicleLoadingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PurchaseOrderVehicleLoadingPage() {
	}

	public void selectMultiPalletLoadMenu() throws InterruptedException {
		screen.type("6");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);

	}

	public void enterDockDoor(String dockdoor) throws InterruptedException {
		screen.type(dockdoor);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);

	}

	public void enterDockDoorForFlatpack(String dockdoor) throws InterruptedException {
		screen.type(dockdoor);
		Thread.sleep(1000);

	}

	public void enterSiteId(String siteid) throws InterruptedException {
		screen.type(siteid);
		Thread.sleep(1000);

	}

	public void enterConsignment(String consignmentID) throws InterruptedException {
		screen.type(consignmentID);
		Thread.sleep(1000);

	}

	public void selectRecord() throws FindFailed, InterruptedException {
		Match mRecord = screen.find("images/putty/VehicleUnloading/selectToUnload.png");
		Thread.sleep(2000);
		screen.doubleClick(mRecord.below(10));
		Thread.sleep(3000);

	}

	public void enterURN(String urn) throws InterruptedException {
		screen.type(urn);
		Thread.sleep(1000);
		// screen.type(Key.ENTER);

	}

	public void selectVehicleLoadMenu() throws InterruptedException {
		screen.type("7");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);

	}

	public void enterInvaidPalletId(String pallet) throws InterruptedException {
		screen.type(pallet);
		Thread.sleep(1000);

	}

	public void selectVehicleUnloadMenu() throws InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);

	}

	public void enterTrailer(String trailerNo) throws InterruptedException {
		screen.type(trailerNo);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterPalletID(String urn) throws InterruptedException {
		screen.type(urn);
		Thread.sleep(1000);

	}
	public boolean isQueryMsgDisplayed() {
		if (screen.exists("images/Putty/VehicleLoading/QueryMsg.png") != null)
			return true;
		else
			return false;
	}

	public boolean isConsignmentLoadingMsgDisplayed() {
		if (screen.exists("images/Putty/VehicleLoading/ConsignmentMsg.png") != null)
			return true;
		else
			return false;
	}

}