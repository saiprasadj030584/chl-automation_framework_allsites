package com.jda.wms.stepdefs.gm;

import org.sikuli.script.Screen;
import org.junit.Assert;
import org.sikuli.script.Key;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.DockSchedulerBookingsPage;
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
	
	Screen screen = new Screen();


	@Inject
	public DockSchedulerStepDefs(DockSchedulerPage dockSchedulerPage,JDAFooter jdaFooter,JdaHomePage jdaHomePage,Context context,PreReceivingStepDefs preReceivingStepDefs,TrailerMaintenanceStepDefs trailerMaintenanceStepDefs,JDAHomeStepDefs jDAHomeStepDefs,DockScehdulerBookingStepDefs dockScehdulerBookingStepDefs) {
		this.dockSchedulerPage = dockSchedulerPage;
		this.jdaFooter=jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context =context;
		this.preReceivingStepDefs=preReceivingStepDefs;
		this.trailerMaintenanceStepDefs=trailerMaintenanceStepDefs;
		this.jDAHomeStepDefs=jDAHomeStepDefs;
		this.dockScehdulerBookingStepDefs=dockScehdulerBookingStepDefs;
		
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
		Thread.sleep(3000);
		//TODO Check the dock door where to book slot
//		System.out.println(dockSchedulerPage.isDockDoorExists());
//		while(!dockSchedulerPage.isDockDoorExists()){
			for (int i=0;i<3;i++){
			jdaHomePage.scrollRightBig();
			}
//		}
		dockSchedulerPage.selectSlot();
		jdaFooter.clickNextButton();
	}

	@When("^I create a booking for the asn$")
	public void i_create_a_booking_for_the_asn() throws Throwable {
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
		dockSchedulerPage.enterTrailerNo(trailerNo);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
		
		if (dockSchedulerPage.isBookingErrorExists()){
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
		if (dockSchedulerPage.isSiteExists()){
		dockSchedulerPage.enterSiteID("5649");
		}
		jdaFooter.clickNextButton();
	}
	
	@Given("^I have done the dock scheduler booking with the PO \"([^\"]*)\", UPI \"([^\"]*)\", ASN \"([^\"]*)\" of type \"([^\"]*)\"$")
	public void i_have_done_the_dock_scheduler_booking_with_the_PO_UPI_ASN_of_type(String preAdviceId, String upiId, String asnId,
			String type) throws Throwable {
		
		preReceivingStepDefs.the_PO_UPI_ASN_of_type_details_should_be_displayed(preAdviceId,upiId,asnId,type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn();
		dockScehdulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
		
		

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
	
	@Given("^I have done the dock scheduler booking with the BookingId \"([^\"]*)\", PO \"([^\"]*)\", UPI \"([^\"]*)\", ASN \"([^\"]*)\" of type \"([^\"]*)\"$")
	public void i_have_done_the_dock_scheduler_booking_with_the_BookingId_PO_UPI_ASN_of_type(String BookingId,String preAdviceId, String upiId, String asnId,
			String type) throws Throwable {
		
		preReceivingStepDefs.the_PO_UPI_ASN_of_type_details_should_be_displayed(preAdviceId,upiId,asnId,type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn_with_bookingid(BookingId);
		dockScehdulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}
	
	@When("^I select view existing bookings$")
	public void i_select_view_existing_bookings() throws Throwable {
		dockSchedulerPage.enterSiteID(context.getSiteId());
	jdaFooter.clickNextButton();
	}

	@When("^I search the booking id$")
	public void i_search_the_booking_id() throws Throwable {
		dockSchedulerPage.enterBookingId(context.getBookingID());
		screen.type(Key.ENTER);
	}
	
	@Then("^the booking id details should be displayed$")
	public void the_booking_id_details_should_be_displayed() throws Throwable {
		Assert.assertTrue("Booking ID is not as expected. ",dockSchedulerPage.isBookingIdDisplayed());
	}
	
	@Then("^the booking id details with updated time should be displayed on the page$")
	public void the_booking_id_details_with_updated_time_should_be_displayed_on_the_page() throws Throwable {
		Assert.assertTrue("Booking ID is not as expected. ",dockSchedulerPage.isBookingIdDisplayed());
	}
	
	@Then("^the booking id details should be displayed on the page$")
	public void the_booking_id_details_should_be_displayed_on_the_page() throws Throwable {
		Assert.assertTrue("Booking ID is not as expected. ",dockSchedulerPage.isBookingIdDisplayedIn());
	}
	
	
	@When("^I delete the booking$")
	public void i_delete_the_booking() throws Throwable {
		screen.rightClick();
		dockSchedulerPage.selectDeleteBooking();
	Assert.assertTrue("Delete confirmation message is not as expected",
				dockSchedulerPage.isDeleteBookingConfirmationMessageDisplayed());
		screen.type(Key.ENTER);
	}
	
	@When("^I change the booking time$")
	public void i_change_the_booking_time() throws Throwable {
		dockSchedulerPage.changeBookingTime();
		jdaFooter.clickNextButton();
	
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
