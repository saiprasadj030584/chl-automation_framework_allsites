package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.pages.gm.DockSchedulerBookingsPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;

public class DockSchedulerBookingStepDefs {
	private Verification verification;
	private Context context;
	private BookingInDiary bookingInDiary;
	private JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private DockSchedulerBookingsPage dockSchedulerBookingsPage;

	@Inject
	public DockSchedulerBookingStepDefs(Verification verification, Context context, BookingInDiary bookingInDiary,
			JdaHomePage jdaHomePage, JDAFooter jdaFooter, DockSchedulerBookingsPage dockSchedulerBookingsPage) {
		this.verification = verification;
		this.context = context;
		this.bookingInDiary = bookingInDiary;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.dockSchedulerBookingsPage = dockSchedulerBookingsPage;
	}

	@Then("^the booking details should appear in the dock scheduler booking$")
	public void the_booking_details_should_appear_in_the_dock_scheduler_booking() throws Throwable {
		ArrayList failureList = new ArrayList();
		jdaHomePage.navigateToDockSchedulerBookingsPage();
		jdaFooter.clickQueryButton();
		dockSchedulerBookingsPage.enterBookingID(context.getBookingID());
		jdaFooter.clickExecuteButton();
		verification.verifyData("Trailer ID", context.getTrailerNo(),
				bookingInDiary.getTrailerID(context.getBookingID()), failureList);
		verification.verifyData("Carrier", context.getCarrier(), bookingInDiary.getCarrier(context.getBookingID()),
				failureList);
		verification.verifyData("Service Level", context.getServiceLevel(),
				bookingInDiary.getServiceLevel(context.getBookingID()), failureList);
	}
}
