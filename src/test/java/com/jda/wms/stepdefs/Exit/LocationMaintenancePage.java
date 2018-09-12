package com.jda.wms.stepdefs.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class LocationMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public String getPickFace() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/LocationMaintenance/PickFace.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterLocation(String location) throws FindFailed {
		screen.wait("images/PickFaceMaintenance/LocationMaintenance/Location.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/LocationMaintenance/Location.png");
		screen.type(location);
	}  
	
	public void clickGeneral() throws FindFailed {
		screen.wait("images/LocationMaintenance/General.png", timeoutInSec);
		screen.click("images/LocationMaintenance/General.png");
	}

	public boolean isRecordfound() {
		// TODO Auto-generated method stub
		return false;
	} 
	
	

}
