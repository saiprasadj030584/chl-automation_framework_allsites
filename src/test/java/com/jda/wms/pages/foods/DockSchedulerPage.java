package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class DockSchedulerPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public DockSchedulerPage(){
		
	}
	
	public void selectCreateNewBooking() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Start/CreateNewBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Start/CreateNewBooking.png");
		Thread.sleep(2000);
	}
	
	public void enterSiteID() throws FindFailed, InterruptedException {
		Match msiteId = screen.find("/images/DockScheduler/Start/CreateNewBooking.png");
		screen.click(msiteId.getCenter().offset(70, 0));
		screen.type("9771");
		Thread.sleep(2000);
	}
	
	public void enterBookingType() throws FindFailed, InterruptedException {
		screen.type("Consignment");
		screen.type(Key.TAB);
	}
	
	public void enterConsignment() throws FindFailed, InterruptedException {
		String consignment="";
//		consignment  = context.getConsignment();
		consignment = "BARN----20170510" ;
		screen.type(consignment);
	}
	
	public void selectConsignment() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/SiteId.png", timeoutInSec);
		screen.doubleClick("images/DockScheduler/Build/SiteId.png");
		Thread.sleep(2000);
	}
	
	public void selectSlot() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/Slot.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/Slot.png");
		Thread.sleep(2000);
	}
	
	public boolean isSlotExists() throws FindFailed, InterruptedException {
		if (screen.exists("/images/DockScheduler/Schedule/Slot.png") != null) {
			return true;
		} else
			return false;
	}
	
	public void scroll() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/Scroll.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/Scroll.png");
		Thread.sleep(2000);
	}
	
	public void enterBookingId(String bookingID) throws FindFailed, InterruptedException {
		screen.type(bookingID);
		Thread.sleep(1000);
	}
	
	public void enterTrailerType() throws FindFailed, InterruptedException {
		screen.type("TRAILER");
		Thread.sleep(1000);
	}
	
	public void enterTrailerNo(String trailerNo) throws FindFailed, InterruptedException {
		screen.type(trailerNo);
		Thread.sleep(1000);
	}
	
	public void enterEstimatedPallets() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
	}
	
	public void enterEstimatedCartons() throws FindFailed, InterruptedException {
		enterEstimatedPallets();
	}
}

