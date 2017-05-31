package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DockSchedulerPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final Context context;

	@Inject
	public DockSchedulerPage(Context context) {
		this.context = context;
	}

	public void selectCreateNewBooking() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Start/CreateNewBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Start/CreateNewBooking.png");
		Thread.sleep(2000);
	}

	public void enterSiteID() throws FindFailed, InterruptedException {
		Match msiteId = screen.find("/images/DockScheduler/Start/SiteID.png");
		screen.click(msiteId.getCenter().offset(70, 0));
		screen.type("9771");
		Thread.sleep(2000);
	}

	public void enterBookingType() throws FindFailed, InterruptedException {
		screen.type("Consignment");
		screen.type(Key.TAB);
	}

	public void enterConsignment() throws FindFailed, InterruptedException {
		screen.type(context.getSTOConsignment());
	}

	public void enterRandomConsignment() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/SearchIcon.png", timeoutInSec);
		screen.click("images/DockScheduler/Build/SearchIcon.png");
		Thread.sleep(2000);

		screen.type(Key.ENTER);
		screen.type(Key.ENTER);
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

	public void enterBookingId(String bookingID) throws FindFailed, InterruptedException {
		screen.type(bookingID);
		Thread.sleep(1000);
	}
	
	public boolean isBookingIdDisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("/images/DockScheduler/Schedule/Out.png") != null) {
			return true;
		} else
			return false;
		
		
		
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

	public void clickStartTab() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Start.png", timeoutInSec);
		screen.click("images/DockScheduler/Start.png");
		Thread.sleep(2000);
	}

	public void enterNotes(String trailerNo) throws FindFailed, InterruptedException {
		context.setDockSchedulerNotes(trailerNo);
		screen.type(trailerNo);
		Thread.sleep(1000);
	}

	public void selectViewExistingBookings() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Start/ViewExistingBookings.png", timeoutInSec);
		screen.click("images/DockScheduler/Start/ViewExistingBookings.png");
		Thread.sleep(2000);
	}

	public void selectDeleteBooking() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/DeleteBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/DeleteBooking.png");
		Thread.sleep(2000);
	}

	public void selectMoveBooking() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/MoveBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/MoveBooking.png");
		Thread.sleep(2000);
	}
	
	public boolean isConfirmBookingDeletionMessage() throws FindFailed, InterruptedException {
		if (!screen.find("images/DockScheduler/Schedule/DeleteBookingConfirmation.png").equals(null)) {
			return false;
		}
		return true;
	}
	
	public void enterNotesToSearch() throws FindFailed, InterruptedException {
		screen.type(context.getDockSchedulerNotes());
		Thread.sleep(1000);
	}
	
	public void selectBookingDetails() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/BookingDetails.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/BookingDetails.png");
		Thread.sleep(2000);
	}

	public String getBookingID() throws FindFailed {
		Match mBookingID = screen.find("images/DockScheduler/Schedule/BookingDetails/BookingID.png");
		screen.click(mBookingID.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getTrailerNo() throws FindFailed {
		Match mTrailerNo = screen.find("images/DockScheduler/Schedule/BookingDetails/Trailer.png");
		screen.click(mTrailerNo.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public boolean isBookedSlotExists() throws FindFailed, InterruptedException {
		if (!screen.find("images/DockScheduler/Schedule/12005.png").equals(null)) {
			return false;
		}
		return true;
	}

	public void selectBookedSlot() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/12005.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/12005.png");
		Thread.sleep(2000);
	}
	
	public boolean isErrorMessageDisplayed() throws FindFailed, InterruptedException {
		if (!screen.find("images/DockScheduler/Schedule/OverrunsExistingBooking.png").equals(null)) {
			return false;
		}
		return true;
	}
	
}
