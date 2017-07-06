package com.jda.wms.pages.gm;

import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DockSchedulerBookingsPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final Context context;

	@Inject
	public DockSchedulerBookingsPage(Context context) {
		this.context = context;
	}

	public void enterBookingID(String bookingID) throws InterruptedException {
		screen.type(bookingID);
		Thread.sleep(1000);
	}
	
}
