package com.jda.wms.UI.pages;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.google.inject.Inject;

public class LocationZonePage {
	
	Screen screen = new Screen();
	int timeoutInSec = 20;
	

	@Inject
	public LocationZonePage() {
	}

	public String getLocationZone() throws FindFailed, InterruptedException {
		Match mFromLocation = screen.find("images/LocationZone/LocationZone.png");
		screen.click(mFromLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public void enterLocationZone(String locationZone) throws FindFailed {
		screen.wait("/images/LocationZone/LocationZone.png", timeoutInSec);
		screen.click("/images/LocationZone/LocationZone.png");
		screen.type(locationZone);
	}
	public boolean isRedLockApplied() throws FindFailed{
		if ((screen.exists("images/Putty/Receiving/RedStockReceive.png") != null)){
			Match mImageFrRedStock=screen.find("images/Putty/Receiving/RedStockReceive.png");
			screen.click(mImageFrRedStock.getCenter().offset(70,0));
			return true;
		}
			
		else
			return false;
	}
	
}
