package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.google.inject.Inject;

public class LocationPage {
	
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public LocationPage() {
	}

	public String getLocationZone() throws FindFailed, InterruptedException {
		Match mFromLocation = screen.find("images/Location/LocationZone.png");
		screen.click(mFromLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public void enterLocation(String location) {
		screen.type(location);
	}
}
