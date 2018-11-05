package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class LocationMaintenancePage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jDAFooter;

	@Inject
	public LocationMaintenancePage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

	public void enterLocation(String location) throws FindFailed {
		screen.wait("/images/LocationMaintenance/Location.png", timeoutInSec);
		screen.click("/images/LocationMaintenance/Location.png");
		screen.type(location);
	} 
	public String getLocation() throws FindFailed, InterruptedException {
		Match mLocationLockStatus = screen.find("/images/LocationMaintenance/Location.png");
		screen.click(mLocationLockStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();
	} 
	public void clickGeneralTab() throws FindFailed {
		screen.wait("/images/LocationMaintenance/GeneralTab.png", timeoutInSec);
		screen.click("/images/LocationMaintenance/GeneralTab.png");
	}

	public boolean isRecordfound() throws FindFailed {
		if (screen.exists("/images/LocationMaintenance/1RecordFound.png", timeoutInSec) != null)
			return true;
		else
			return false;
	}

	public void selectLockStatus(String lockStatus) throws FindFailed, InterruptedException {
		screen.wait("/images/LocationMaintenance/Status.png", timeoutInSec);
		screen.click("/images/LocationMaintenance/Status.png");
		screen.type(lockStatus);
		screen.type(Key.ENTER);
	}

	public String getLocationLockStatus() throws InterruptedException, FindFailed {
		Match mLocationLockStatus = screen.find("/images/LocationMaintenance/Status.png");
		screen.click(mLocationLockStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();
	}
}
