package com.jda.wms.UI.pages;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class DockSchedulerBookingsPage {
	Screen screen = new Screen();

	@Inject
	public DockSchedulerBookingsPage() {

	}

	public void enterBookingID(String bookingID) throws FindFailed, InterruptedException {
		screen.type(bookingID);
		Thread.sleep(2000);
	}

	public String getTrailerNo() throws FindFailed {
		Match mDescription = screen.find("/images/DockScheduleBookings/Trailer.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isNoRecords() throws FindFailed, InterruptedException {
		if (screen.exists("images/DockScheduleBookings/NoRecords.png") != (null))
			return true;
		else
			return false;
	}
}
