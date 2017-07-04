package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.*;

public class DockSchedulerStepDefs {
	private DockSchedulerPage dockSchedulerPage;
	private JDAFooter jdaFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	@Inject
	public DockSchedulerStepDefs(DockSchedulerPage dockSchedulerPage,JDAFooter jdaFooter,JdaHomePage jdaHomePage,Context context) {
		this.dockSchedulerPage = dockSchedulerPage;
		this.jdaFooter=jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context =context;
	}

	@When("^I select the booking type and ASN$")
	public void i_select_the_booking_type_and_ASN() throws Throwable {
		dockSchedulerPage.enterBookingType("Delivery");
		dockSchedulerPage.enterASNId(context.getAsnId());
		jdaFooter.clickSearch();
		dockSchedulerPage.selectASN();
		jdaFooter.clickNextButton();
	}

	@When("^I select the slot$")
	public void i_select_the_slot() throws Throwable {
		//TODO include while loop
		if (dockSchedulerPage.isSlotExists()) {
			dockSchedulerPage.selectSlot();
		} else {
			jdaHomePage.scrollDown();
			dockSchedulerPage.selectSlot();
		}
		jdaFooter.clickNextButton();
	}

	@When("^I create a booking for the asn$")
	public void i_create_a_booking_for_the_asn() throws Throwable {
		String bookingID = Utilities.getFiveDigitRandomNumber();
		String trailerNo = context.getTrailerNo();

		context.setBookingID(bookingID);

		dockSchedulerPage.enterBookingId(bookingID);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterCarrier("ALLPORT");
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterServiceLevel("AIR");
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerType();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerNo(trailerNo);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
	}
	
	
	public void i_enter_booking_details_with_bookingID(String bookingID)throws Throwable {
		String trailerNo = context.getTrailerNo();
		context.setBookingID(bookingID);

		dockSchedulerPage.enterBookingId(bookingID);

		dockSchedulerPage.enterTrailerType();
		dockSchedulerPage.enterTrailerNo(trailerNo);
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.enterEstimatedCartons();
		dockSchedulerPage.enterNotes(trailerNo);

		jdaFooter.PressEnter();
	}
	
	@When("^I create new dock booking$")
	public void i_create_new_dock_booking() throws Throwable {
		dockSchedulerPage.selectCreateNewBooking();
		dockSchedulerPage.enterSiteID("5649");
		jdaFooter.clickNextButton();
	}
	
	@Given("^I have done the dock scheduler booking for the consignment$")
	public void i_have_done_the_dock_scheduler_booking_for_the_consignment() throws Throwable {

//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
//		
//		trailerMaintenanceStepDefs.i_create_a_trailer_in_trailer_Maintenance_page();
//
//		i_create_new_dock_booking();
//		i_select_booking_type_and_a_consignment();
//		i_select_the_slot();
//		i_enter_booking_details();
//		inventoryUpdateStepDefs.i_proceed_to_complete_the_process();
//		the_booking_details_should_be_appeared_in_the_dock_scheduler_booking();
	}
	
	@When("^I select view existing bookings$")
	public void i_select_view_existing_bookings() throws Throwable {
//		dockSchedulerPage.enterSiteID();
//		jdaFooter.clickNextButton();
	}

	@When("^I search the booking id$")
	public void i_search_the_booking_id() throws Throwable {
//		dockSchedulerPage.enterBookingId(context.getBookingID());
//		screen.type(Key.ENTER);
	}
	
	@Then("^the booking id details should be displayed$")
	public void the_booking_id_details_should_be_displayed() throws Throwable {
//		Assert.assertTrue("Booking ID is not as expected. ",dockSchedulerPage.isBookingIdDisplayed());
	}
	
	@When("^I delete the booking$")
	public void i_delete_the_booking() throws Throwable {
//		screen.rightClick();
//		dockSchedulerPage.selectDeleteBooking();
//		Assert.assertTrue("Delete confirmation message is not as expected",
//				dockSchedulerPage.isDeleteBookingConfirmationMessageDisplayed());
//		screen.type(Key.ENTER);
	}

	@Then("^the booking details should be deleted in the dock scheduler booking$")
	public void the_booking_details_should_be_deleted_in_the_dock_scheduler_booking() throws Throwable {
//		jdaHomePage.navigateToDockSchedulerBookingsPage();
//		jdaFooter.clickQueryButton();
//
//		dockSchedulerBookingsPage.enterBookingID(context.getBookingID());
//		jdaFooter.clickExecuteButton();
//
//		Assert.assertTrue("Records are not as expected", dockSchedulerBookingsPage.isNoRecords());
	}
}
