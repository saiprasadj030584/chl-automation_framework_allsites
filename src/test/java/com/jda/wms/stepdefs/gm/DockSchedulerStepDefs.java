package com.jda.wms.stepdefs.gm;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.BookingInDiaryLog;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DockSchedulerStepDefs {
	private DockSchedulerPage dockSchedulerPage;
	private JDAFooter jdaFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private PreReceivingStepDefs preReceivingStepDefs;
	private TrailerMaintenanceStepDefs trailerMaintenanceStepDefs;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private DockScehdulerBookingStepDefs dockScehdulerBookingStepDefs;
	private BookingInDiaryLog bookingInDiaryLog;

	Screen screen = new Screen();

	@Inject
	public DockSchedulerStepDefs(DockSchedulerPage dockSchedulerPage, JDAFooter jdaFooter, JdaHomePage jdaHomePage,
			Context context, PreReceivingStepDefs preReceivingStepDefs,
			TrailerMaintenanceStepDefs trailerMaintenanceStepDefs, JDAHomeStepDefs jDAHomeStepDefs,
			DockScehdulerBookingStepDefs dockScehdulerBookingStepDefs, BookingInDiaryLog bookingInDiaryLog) {
		this.dockSchedulerPage = dockSchedulerPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.preReceivingStepDefs = preReceivingStepDefs;
		this.trailerMaintenanceStepDefs = trailerMaintenanceStepDefs;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.dockScehdulerBookingStepDefs = dockScehdulerBookingStepDefs;
		this.bookingInDiaryLog = bookingInDiaryLog;

	}

	@When("^I select the booking type and ASN$")
	public void i_select_the_booking_type_and_ASN() throws Throwable {
		dockSchedulerPage.enterBookingType("Delivery");
		dockSchedulerPage.enterASNId(context.getAsnId());
		jdaFooter.clickSearch();
		dockSchedulerPage.selectASN();
		jdaFooter.clickNextButton();
	}

	@When("^I select the booking type preadvice$")
	public void i_select_the_booking_type_preadvice() throws Throwable {
		dockSchedulerPage.enterBookingType("Pre-Advice");
		dockSchedulerPage.enterASNId(context.getPreAdviceId());
		jdaFooter.clickSearch();
		dockSchedulerPage.selectPreAdviceId();
		jdaFooter.clickNextButton();
	}

	@When("^I select the slot$")
	public void i_select_the_slot() throws Throwable {
		Thread.sleep(3000);
		// TODO Check the dock door where to book slot
		for (int i = 0; i < 2; i++) {
			jdaHomePage.scrollRightBig();
		}
		dockSchedulerPage.selectSlot();
		jdaFooter.clickNextButton();
	}

	@When("^I create a booking$")
	public void i_create_a_booking() throws Throwable {
		String bookingID = Utilities.getFiveDigitRandomNumber();
		String trailerNo = context.getTrailerNo();
		context.setBookingID(bookingID);
		context.setCarrier("ALLPORT");
		context.setServiceLevel("AIR");
		dockSchedulerPage.enterBookingId(bookingID);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterCarrier(context.getCarrier());
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerType();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerNo("1234");
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
		System.out.println("Check2");
		if (dockSchedulerPage.isBookingErrorExists()) {
			System.out.println("Check2");
			jdaFooter.PressEnter();
			jdaFooter.clickNextButton();
			bookingID = Utilities.getFiveDigitRandomNumber();
			jdaFooter.deleteExistingContent();
			dockSchedulerPage.enterBookingId(bookingID);
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.enterEstimatedPallets();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.enterEstimatedCartons();
			jdaFooter.PressEnter();
		} else if (dockSchedulerPage.isNoDockErrorExists()) {
			System.out.println("Check");
			jdaFooter.PressEnter();
			dockSchedulerPage.selectSlot();
			System.out.println("Check1");
			jdaFooter.clickNextButton();
			bookingID = Utilities.getFiveDigitRandomNumber();
			jdaFooter.deleteExistingContent();
			dockSchedulerPage.enterBookingId(bookingID);
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.enterEstimatedPallets();
			dockSchedulerPage.pressTab();
			dockSchedulerPage.enterEstimatedCartons();
			jdaFooter.PressEnter();
		}
	}

	public void i_create_a_booking_for_the_asn_with_bookingid(String bookingid) throws Throwable {
		String bookingID = bookingid;
		String trailerNo = context.getTrailerNo();
		context.setBookingID(bookingID);
		context.setCarrier("ALLPORT");
		context.setServiceLevel("AIR");
		dockSchedulerPage.enterBookingId(bookingID);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterCarrier(context.getCarrier());
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
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

	public void i_enter_booking_details_with_bookingID(String bookingID) throws Throwable {
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

	@When("^I create new dock booking at site \"([^\"]*)\"$")
	public void i_create_new_dock_booking_at_site(String site) throws Throwable {
		dockSchedulerPage.selectCreateNewBooking();
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(site);
			context.setSiteId(site);
		}
		jdaFooter.clickNextButton();
	}

	@Given("^I have done the dock scheduler booking with the PO \"([^\"]*)\", UPI \"([^\"]*)\", ASN \"([^\"]*)\" of type \"([^\"]*)\" at site \"([^\"]*)\"$")
	public void i_have_done_the_dock_scheduler_booking_with_the_PO_UPI_ASN_of_type_at_site(String preAdviceId,
			String upiId, String asnId, String type, String site) throws Throwable {
		preReceivingStepDefs.the_PO_UPI_ASN_of_type_details_should_be_displayed(preAdviceId, upiId, asnId, type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site(site);
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking();
		dockScehdulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@Given("^I have done the dock scheduler booking with the PO \"([^\"]*)\" of type \"([^\"]*)\" at site \"([^\"]*)\"$")
	public void i_have_done_the_dock_scheduler_booking_with_the_PO_of_type_at_site(String preAdviceId, String type,
			String site) throws Throwable {
		preReceivingStepDefs.the_PO_of_type_details_should_be_displayed(preAdviceId, type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site(site);
		i_select_the_booking_type_preadvice();
		i_select_the_slot();
		i_create_a_booking();
		dockScehdulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@Given("^I have done the dock scheduler booking with the BookingId \"([^\"]*)\", PO \"([^\"]*)\", UPI \"([^\"]*)\", ASN \"([^\"]*)\" of type \"([^\"]*)\" at site \"([^\"]*)\"$")
	public void i_have_done_the_dock_scheduler_booking_with_the_BookingId_PO_UPI_ASN_of_type(String BookingId,
			String preAdviceId, String upiId, String asnId, String type, String site) throws Throwable {

		preReceivingStepDefs.the_PO_UPI_ASN_of_type_details_should_be_displayed(preAdviceId, upiId, asnId, type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site(site);
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn_with_bookingid(BookingId);
		dockScehdulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@When("^I select view existing bookings$")
	public void i_select_view_existing_bookings() throws Throwable {
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(context.getSiteId());
		}
		jdaFooter.clickNextButton();
	}

	@When("^I search the booking id$")
	public void i_search_the_booking_id() throws Throwable {
		dockSchedulerPage.enterBookingId(context.getBookingID());
		jdaFooter.PressEnter();
	}

	@Then("^the booking id details should be displayed$")
	public void the_booking_id_details_should_be_displayed() throws Throwable {
		Assert.assertTrue("Booking ID is not as expected. ", dockSchedulerPage.isBookingIdDisplayedIn());
	}

	@Then("^the booking id details should be displayed on the page$")
	public void the_booking_id_details_should_be_displayed_on_the_page() throws Throwable {
		Assert.assertTrue("Booking ID is not as expected. ", dockSchedulerPage.isBookingIdDisplayedIn());
		String bookedtime = bookingInDiaryLog.getChangedBookingTime(context.getBookingID());
		context.setBookingTime(bookedtime);
		String dockid = bookingInDiaryLog.getChangedDockId(context.getBookingID());
		context.setDockId(dockid);
	}

	@When("^I delete the booking$")
	public void i_delete_the_booking() throws Throwable {
		jdaFooter.rightClick();
		dockSchedulerPage.selectDeleteBooking();
		Assert.assertTrue("Delete confirmation message is not as expected",
				dockSchedulerPage.isDeleteBookingConfirmationMessageDisplayed());
		jdaFooter.PressEnter();
	}

	@When("^I change the booking time$")
	public void i_change_the_booking_time() throws Throwable {
		dockSchedulerPage.changeBookingTime();
		dockSchedulerPage.selectSlot();
		jdaFooter.clickNextButton();
	}

	@When("^I change the status of booking$")
	public void i_change_the_status_of_booking() throws Throwable {
		dockSchedulerPage.changeBookingStatus();
	}

	@Then("^the booking id details with updated status should be displayed on the page$")
	public void the_booking_id_details_with_updated_status_should_be_displayed_on_the_page() throws Throwable {
		jdaHomePage.navigateToDockSchedulerPage();
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(context.getSiteId());
		}
		jdaFooter.clickNextButton();
		dockSchedulerPage.enterBookingId(context.getBookingID());
		jdaFooter.PressEnter();
		Assert.assertTrue("Booking ID is not as expected. ", dockSchedulerPage.isBookingIdDisplayedIn());
		dockSchedulerPage.checkBookingStatusUpdated();
		Assert.assertTrue("Booking ID is not as expected. ", dockSchedulerPage.isBookingStatusUpdated());
		jdaFooter.PressEnter();
	}

	@Then("^the booking details should be deleted in the dock scheduler booking$")
	public void the_booking_details_should_be_deleted_in_the_dock_scheduler_booking() throws Throwable {
		jdaHomePage.navigateToDockSchedulerBookingsPage();
		jdaFooter.clickQueryButton();
		dockSchedulerPage.enterBookingId(context.getBookingID());
		jdaFooter.clickExecuteButton();
		Assert.assertTrue("Records are not as expected", dockSchedulerPage.isNoRecords());
	}
}
