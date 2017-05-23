package com.jda.wms.pages.rdt;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockTransferOrderVehicleLoadingPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public StockTransferOrderVehicleLoadingPage() {

	}

	public void navigateToLoadMenu() throws FindFailed {
		screen.wait("images/Putty/UserMenu.png", timeoutInSec);
		screen.type("7");
		screen.type(Key.ENTER);
	}

	public void navigateToVehicleLoad() throws FindFailed {
		screen.wait("images/Putty/VehicleLoading/LodMnu.png", timeoutInSec);
		screen.type("1");
		screen.type(Key.ENTER);
	}

	public void enterPalletID(String palletID) throws FindFailed {
		screen.wait("images/Putty/VehicleLoading/LodEnt.png", timeoutInSec);
		screen.type(palletID);
	}

	public void enterTrailer(String trailer) throws FindFailed {
		screen.wait("images/Putty/VehicleLoading/LodEnt.png", timeoutInSec);
		screen.type(trailer);
	}

	public boolean isVehicleLoadComplete() throws FindFailed {
		if (screen.exists("images/Putty/VehicleLoading/VehicleLoadComplete.png") != null) {
			return true;
		} else {
			return false;
		}
	}

}
