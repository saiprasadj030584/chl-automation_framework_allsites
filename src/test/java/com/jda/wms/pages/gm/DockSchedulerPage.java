package com.jda.wms.pages.gm;

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
//	private JdaHomePage jdaHomePage;

	@Inject
	public DockSchedulerPage(Context context ) {
		this.context = context;
		//this.jdaHomePage = jdaHomePage;
	}

	public void selectCreateNewBooking() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Start/CreateNewBooking.png", timeoutInSec);
		screen.click("images/DockScheduler/Start/CreateNewBooking.png");
		Thread.sleep(2000);
	}

	public void enterSiteID(String site) throws FindFailed, InterruptedException {
		Match msiteId = screen.find("/images/DockScheduler/Start/SiteID.png");
		screen.click(msiteId.getCenter().offset(70, 0));
		screen.type(site);

		Thread.sleep(2000);
	}

	public void enterBookingType(String bookingType) throws FindFailed, InterruptedException {
		screen.type(bookingType);
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
		Thread.sleep(1000);
		screen.type(Key.ENTER);
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
		//
		// for (int i = 0; i < 25; i++) {
		// jdaHomePage.scrollRight();
		// }
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

	public boolean isBookingIdDisplayedIn() throws FindFailed, InterruptedException {
		if (screen.exists("/images/DockScheduler/Schedule/In.png") != null) {
			return true;
		} else
			return false;
	}

	public void checkBookingStatusUpdated() throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		screen.rightClick();
		Thread.sleep(2000);
		selectBookingDetails();
	}

	public boolean isBookingStatusUpdated(String bookingStatus) throws FindFailed, InterruptedException {

		if (bookingStatus.equalsIgnoreCase("Complete")) {

			if (screen.exists("/images/DockScheduler/Schedule/BookingDetails/StatusComplete.png") != null) {
				return true;
			} else
				return false;

		} else {
			if (screen.exists("/images/DockScheduler/Schedule/BookingDetails/StatusInProgress.png") != null) {
				return true;
			} else
				return false;
		}

	}

	public void enterTrailerType() throws FindFailed, InterruptedException {
		screen.type("TRAILER");
		Thread.sleep(1000);
	}

	public void changeBookingTime() throws FindFailed, InterruptedException {

//		screen.wait("images/DockScheduler/Schedule/In.png", timeoutInSec);
//		screen.click("images/DockScheduler/Schedule/In.png");
		screen.rightClick();
		Thread.sleep(2000);
		screen.click("images/DockScheduler/Schedule/MoveBooking.png");

		Thread.sleep(3000);

	}

	public void changeBookingTimeToDifferentDate() throws FindFailed, InterruptedException {

//		screen.wait("images/DockScheduler/Schedule/In.png", timeoutInSec);
//		screen.click("images/DockScheduler/Schedule/In.png");
		screen.rightClick();
		Thread.sleep(2000);
		//screen.wait("images/DockScheduler/Schedule/In.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/MoveBooking.png");

		Thread.sleep(3000);
		screen.click("images/DockScheduler/Schedule/SelectDate1.png");
		Thread.sleep(2000);
	}

	public boolean isBookingTimeUpdated() throws FindFailed, InterruptedException {
		if (context.getBookingTime().contains(context.getUpdatedBookingTime().substring(9))) {
			if ((context.getDockId().contains(context.getUpdatedDockId()))) {
				return false;
			} else {
				return true;
			}
		} else
			return true;
	}

	public void changeBookingStatus(String bookingStatus) throws FindFailed, InterruptedException {

//		screen.wait("images/DockScheduler/Schedule/In.png", timeoutInSec);
//		screen.click("images/DockScheduler/Schedule/In.png");
		screen.rightClick();

		if (bookingStatus.equalsIgnoreCase("Complete")) {
			screen.click("images/DockScheduler/Schedule/CompleteBooking.png");
			Thread.sleep(1000);
			screen.type(context.getTrailerNo());
			pressTab();
			pressTab();
			pressTab();
			screen.type("1");
			pressTab();
			screen.type("1");
			screen.click("images/DockScheduler/Schedule/BookingDetails/Ok.png");
			Thread.sleep(2000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);
		} else {
			screen.click("images/DockScheduler/Schedule/ChangeBookingStatus.png");
			Thread.sleep(1000);
			screen.type("i");
			Thread.sleep(1000);
			pressTab();
			pressTab();
			screen.type(Key.ENTER);
			// screen.click("images/DockScheduler/Schedule/ChangeBookingStatusOKButton.png");
		}

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

	public boolean isNoRecords() throws FindFailed, InterruptedException {
		if (screen.exists("images/DockScheduleBookings/NoRecords.png") != null)
			return true;
		else
			return false;
	}

	public void enterNotes() throws FindFailed, InterruptedException {
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
		if (screen.exists("images/DockScheduler/Schedule/OverrunsExistingBooking.png") != (null))
			return true;
		else
			return false;
	}

	public void clickElseWhere() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/ShowLines.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/ShowLines.png");
		Thread.sleep(2000);
	}

	public void enterASNId(String asnId) throws InterruptedException {
		screen.type(asnId);
		Thread.sleep(1000);
	}

	public void selectASN() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/ASNID.png", timeoutInSec);
		Match mASN = screen.find("images/DockScheduler/Build/ASNID.png");
		Thread.sleep(2000);
		screen.click(mASN.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mASN.below(10));
	}

	public void selectPreAdviceId() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Build/PreAdviceId.png", timeoutInSec);
		Match mASN = screen.find("images/DockScheduler/Build/PreAdviceId.png");
		Thread.sleep(2000);
		screen.click(mASN.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mASN.below(10));
	}

	public void pressTab() throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}
	public void enterStatus(String string) throws InterruptedException {
		screen.type(string);
		Thread.sleep(1000);
	}
	public void enterCarrier(String carrier) throws InterruptedException {
		screen.type(carrier);
		Thread.sleep(1000);
	}

	public void enterServiceLevel(String serviceLevel) throws InterruptedException {
		screen.type(serviceLevel);
		Thread.sleep(1000);
	}

	public boolean isSiteExists() {
		if (screen.exists("/images/DockScheduler/Start/SiteID.png") != null) {
			return true;
		} else
			return false;
	}

	public boolean isDockDoorExists() {
		if (screen.exists("/images/DockScheduler/Schedule/DD010.png") != null) {
			return true;
		} else
			return false;
	}

	public boolean isBookingErrorExists() {
		if (screen.exists("/images/DockScheduler/Schedule/BookingError.png") != null) {
			return true;
		} else
			return false;
	}

	public boolean isNoDockErrorExists() {
		if (screen.exists("/images/DockScheduler/Schedule/NoDockError.png") != null) {
			return true;
		} else
			return false;
	}

	public void clickLeftArrowSlide() throws FindFailed, InterruptedException {
		screen.wait("images/DockScheduler/Schedule/LeftArrow.png", timeoutInSec);
		screen.click("images/DockScheduler/Schedule/LeftArrow.png");
		Thread.sleep(2000);
	}
	
	public void enterConsignmentID(String consignmentID) throws InterruptedException {
		screen.type(consignmentID);
		Thread.sleep(1000);
	}

	public boolean dockSchedulerHomePage() {
		if (screen.exists("images/JDAHome/DockSchedulerHomePage.png") != null)
			return true;
		else
			return false;
	}
}
