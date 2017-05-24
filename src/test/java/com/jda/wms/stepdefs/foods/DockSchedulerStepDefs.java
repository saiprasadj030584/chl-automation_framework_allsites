package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.DockSchedulerBookingsPage;
import com.jda.wms.pages.foods.DockSchedulerPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.utils.Utilities;

import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class DockSchedulerStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final Context context;
	private final DockSchedulerPage dockSchedulerPage;
	private final DockSchedulerBookingsPage dockSchedulerBookingsPage;
	Screen screen = new Screen();

	@Inject
	public DockSchedulerStepDefs(DockSchedulerPage dockSchedulerPage, JdaHomePage jdaHomePage, JDAFooter jdaFooter,
			Context context, DockSchedulerBookingsPage dockSchedulerBookingsPage) {
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.dockSchedulerPage = dockSchedulerPage;
		this.dockSchedulerBookingsPage = dockSchedulerBookingsPage;
	}

	@When("^I create new dock booking$")
	public void i_create_new_dock_booking() throws Throwable {
		jdaHomePage.navigateToDockSchedulerPage();
		dockSchedulerPage.selectCreateNewBooking();
		dockSchedulerPage.enterSiteID();
		jdaFooter.clickNextButton();
	}

	@When("^I select booking type and consignment$")
	public void i_select_booking_type_and_consignment() throws Throwable {
		dockSchedulerPage.enterBookingType();
		dockSchedulerPage.enterConsignment();
		jdaFooter.clickSearch();
		dockSchedulerPage.selectConsignment();
		jdaFooter.clickNextButton();
	}

	@When("^I select the slot$")
	public void i_select_the_slot() throws Throwable {
		if (dockSchedulerPage.isSlotExists()) {
			dockSchedulerPage.selectSlot();
		} else {
			jdaHomePage.scrollDown();
			dockSchedulerPage.selectSlot();
		}
		jdaFooter.clickNextButton();
	}

	@When("^I enter booking details$")
	public void i_enter_booking_details() throws Throwable {
		String bookingID = Utilities.getFiveDigitRandomNumber();
		context.setBookingID(bookingID);
		
		dockSchedulerPage.enterBookingId(bookingID);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		
		dockSchedulerPage.enterTrailerType();
		screen.type(Key.TAB);
		dockSchedulerPage.enterTrailerNo(context.getTrailerNo());
		screen.type(Key.TAB);
		dockSchedulerPage.enterEstimatedPallets();
		screen.type(Key.TAB);
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
	}

	@Then("^the booking details should be appeared in the dock scheduler booking$")
	public void the_booking_details_should_be_appeared_in_the_dock_scheduler_booking() throws Throwable {
		jdaHomePage.navigateToDockSchedulerBookingsPage();
		jdaFooter.clickQueryButton();
		
		dockSchedulerBookingsPage.enterBookingID(context.getBookingID());
		jdaFooter.clickExecuteButton();
		
		Assert.assertEquals("Trailer number is not as expected.",context.getTrailerNo(), dockSchedulerBookingsPage.getTrailerNo());
	}
}
