package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import org.junit.Assert;
import org.sikuli.script.Screen;
import org.sikuli.script.Key;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.db.gm.BookingInDiaryLog;
import com.jda.wms.pages.gm.DockSchedulerBookingsPage;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;
import cucumber.api.java.en.Then;

public class DockSchedulerBookingStepDefs {
	Screen screen = new Screen();
	private Verification verification;
	private Context context;
	private BookingInDiary bookingInDiary;
	private BookingInDiaryLog bookingInDiaryLog;
	private DockSchedulerPage dockSchedulerPage;
	private JdaHomePage jdaHomePage;
	private DockSchedulerBookingsPage dockShedulerBookingPage;
	private JDAFooter jdaFooter;

	@Inject
	public DockSchedulerBookingStepDefs(Verification verification, Context context, BookingInDiary bookingInDiary,
			BookingInDiaryLog bookingInDiaryLog, DockSchedulerPage dockSchedulerPage, JdaHomePage jdaHomePage,
			JDAFooter jdaFooter, DockSchedulerBookingsPage dockShedulerBookingPage) {
		this.verification = verification;
		this.context = context;
		this.bookingInDiary = bookingInDiary;
		this.bookingInDiaryLog = bookingInDiaryLog;
		this.dockSchedulerPage = dockSchedulerPage;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.dockShedulerBookingPage = dockShedulerBookingPage;
	}
	
	@Then("^the booking details should appear in the dock scheduler booking$")
	public void the_booking_details_should_appear_in_the_dock_scheduler_booking() throws Throwable {
		ArrayList failureList = new ArrayList();
		jdaHomePage.navigateToDockSchedulerBookingsPage();
		jdaFooter.clickQueryButton();
		dockShedulerBookingPage.enterBookingID(context.getBookingID());
		jdaFooter.clickExecuteButton();
		verification.verifyData("Trailer ID", context.getTrailerNo(),
				bookingInDiary.getTrailerID(context.getBookingID()), failureList);
	if(context.getSiteId().equals("5649"))
		{
		verification.verifyData("Carrier", context.getCarrier(), bookingInDiary.getCarrier(context.getBookingID()),
				failureList);
		verification.verifyData("Service Level", context.getServiceLevel(),
				bookingInDiary.getServiceLevel(context.getBookingID()), failureList);
}
	}

	@Then("^the booking id details with updated time should be displayed on the page$")
	public void the_booking_id_details_with_updated_time_should_be_displayed_on_the_page() throws Throwable {
		String changedtime = bookingInDiaryLog.getChangedBookingTime(context.getBookingID());
		context.setUpdatedBookingTime(changedtime);
		String changeddock = bookingInDiaryLog.getChangedDockId(context.getBookingID());
		context.setUpdatedDockId(changeddock);
		Assert.assertTrue("Booking time not updated", dockSchedulerPage.isBookingTimeUpdated());
		jdaHomePage.navigateToDockSchedulerPage();
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(context.getSiteId());
		}
		jdaFooter.clickNextButton();
		dockSchedulerPage.enterBookingId(context.getBookingID());
		jdaFooter.PressEnter();
	}
}
