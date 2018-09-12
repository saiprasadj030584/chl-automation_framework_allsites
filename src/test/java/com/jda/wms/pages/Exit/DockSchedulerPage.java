package com.jda.wms.pages.Exit;

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
	

	public void enterBookingType(String bookingType) {
		screen.type(bookingType);
		screen.type(Key.TAB);
	}
	
	public void addAllOrders() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/AddButtonEnabled.png", timeoutInSec);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type("a", Key.CTRL);
		screen.click("images/DockScheduler/Build/AddButtonEnabled.png");
		Thread.sleep(3000);
	}

	public void enterConsignment(String consignment) throws FindFailed, InterruptedException {
		screen.type(consignment);
	}
	
	public void enterConsignmnet(String consignment) throws InterruptedException {
		screen.type(consignment);
		Thread.sleep(1000);
	}

	public void enterRandomConsignment() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/SearchIcon.png", timeoutInSec);
		screen.click("images/DockScheduler/Build/SearchIcon.png");
		Thread.sleep(2000);

		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);//clickFromSiteId
	} 
	
	public void clickFromSiteId() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/clickFromSiteId.png", timeoutInSec);
		screen.click("images/DockScheduler/Build/clickFromSiteId.png");
	}

	public void selectConsignment() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/FromSiteID.png", timeoutInSec);
		Match mStatus = screen.find("images/DockScheduler/Build/FromSiteID.png");
		Thread.sleep(2000);
		screen.click(mStatus.below(10));
		Thread.sleep(2000);
		Match mStatuscode = screen.find("images/DockScheduler/Build/FromSiteID1.png");
		screen.doubleClick(mStatuscode.below(1));
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
		screen.wait("images/DockScheduler/Schedule/Notes.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/Notes.png");
		screen.type(trailerNo);
		Thread.sleep(2000);
	}

	public void selectViewExistingBookings() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Start/ViewExistingBookings.png", timeoutInSec);
		screen.click("images/DockScheduler/Start/ViewExistingBookings.png");
		Thread.sleep(2000);
	}

	public void selectDeleteBooking() throws FindFailed, InterruptedException {
		Thread.sleep(2000);
		screen.wait("images/DockScheduler/Schedule/DeleteBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/DeleteBooking.png");
		Thread.sleep(2000);
	}

	public void selectMoveBooking() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/MoveBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/MoveBooking.png");
		Thread.sleep(2000);
	}

	public boolean isDeleteBookingConfirmationMessageDisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("images/DockScheduler/Schedule/DeleteBookingConfirmation.png") != null)
			return true;
		else
			return false;
	}

	public void enterNotes() throws FindFailed, InterruptedException {
		screen.type(context.getDockSchedulerNotes());
		Thread.sleep(1000);
	}

	public void selectBookingDetails() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
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
		if (screen.exists("images/DockScheduler/Schedule/12005.png") != (null))
			return true;
		else
			return false;
	}

	public void selectBookedSlot() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/12005.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/12005.png");
		Thread.sleep(2000);
	}

	public boolean isErrorMessageDisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("images/DockScheduler/Schedule/OverrunsExistingBooking.png")!=(null))
			return true;
		else
		return false;
	}

	public void clickElseWhere() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/ShowLines.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/ShowLines.png");
		Thread.sleep(2000);
	}
}
