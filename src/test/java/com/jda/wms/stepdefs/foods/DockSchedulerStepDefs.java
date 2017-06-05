package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.DockSchedulerBookingsPage;
import com.jda.wms.pages.foods.DockSchedulerPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DockSchedulerStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final Context context;
	private final DockSchedulerPage dockSchedulerPage;
	private final DockSchedulerBookingsPage dockSchedulerBookingsPage;
	private final TrailerMaintenanceStepDefs trailerMaintenanceStepDefs;
	private final InventoryUpdateStepDefs inventoryUpdateStepDefs;
	private final Verification verification;
	private final JDALoginStepDefs jdaLoginStepDefs;

	Screen screen = new Screen();

	@Inject
	public DockSchedulerStepDefs(DockSchedulerPage dockSchedulerPage, JdaHomePage jdaHomePage, JDAFooter jdaFooter,
			Context context, DockSchedulerBookingsPage dockSchedulerBookingsPage,
			TrailerMaintenanceStepDefs trailerMaintenanceStepDefs, InventoryUpdateStepDefs inventoryUpdateStepDefs,
			Verification verification,JDALoginStepDefs jdaLoginStepDefs) {
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.dockSchedulerPage = dockSchedulerPage;
		this.dockSchedulerBookingsPage = dockSchedulerBookingsPage;
		this.trailerMaintenanceStepDefs = trailerMaintenanceStepDefs;
		this.inventoryUpdateStepDefs = inventoryUpdateStepDefs;
		this.verification = verification;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
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
		String trailerNo = context.getTrailerNo();

		context.setBookingID(bookingID);

		dockSchedulerPage.enterBookingId(bookingID);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);

		dockSchedulerPage.enterTrailerType();
		screen.type(Key.TAB);
		dockSchedulerPage.enterTrailerNo(trailerNo);
		screen.type(Key.TAB);
		dockSchedulerPage.enterEstimatedPallets();
		screen.type(Key.TAB);
		dockSchedulerPage.enterEstimatedCartons();
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		dockSchedulerPage.enterNotes(trailerNo);

		jdaFooter.PressEnter();
	}
	
	
	public void i_enter_booking_details_with_bookingID(String bookingID)throws Throwable {
		String trailerNo = context.getTrailerNo();
		context.setBookingID(bookingID);

		dockSchedulerPage.enterBookingId(bookingID);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);

		dockSchedulerPage.enterTrailerType();
		screen.type(Key.TAB);
		dockSchedulerPage.enterTrailerNo(trailerNo);
		screen.type(Key.TAB);
		dockSchedulerPage.enterEstimatedPallets();
		screen.type(Key.TAB);
		dockSchedulerPage.enterEstimatedCartons();
		dockSchedulerPage.enterNotes(trailerNo);

		jdaFooter.PressEnter();
	}

	@Then("^the booking details should be appeared in the dock scheduler booking$")
	public void the_booking_details_should_be_appeared_in_the_dock_scheduler_booking() throws Throwable {
		jdaHomePage.navigateToDockSchedulerBookingsPage();
		jdaFooter.clickQueryButton();

		dockSchedulerBookingsPage.enterBookingID(context.getBookingID());
		jdaFooter.clickExecuteButton();

		Assert.assertEquals("Trailer number is not as expected.", context.getTrailerNo(),
				dockSchedulerBookingsPage.getTrailerNo());
	}

	@Given("^I have done the dock scheduler booking for the consignment$")
	public void i_have_done_the_dock_scheduler_booking_for_the_consignment() throws Throwable {

//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		
		trailerMaintenanceStepDefs.i_create_a_trailer_in_trailer_Maintenance_page();

		i_create_new_dock_booking();
		i_select_booking_type_and_a_consignment();
		i_select_the_slot();
		i_enter_booking_details();
		inventoryUpdateStepDefs.i_proceed_to_complete_the_process();
		the_booking_details_should_be_appeared_in_the_dock_scheduler_booking();
	}
	
	public void i_have_done_the_dock_scheduler_booking_with_booking_id(String bookingID) throws Throwable {

//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		
		trailerMaintenanceStepDefs.i_create_a_trailer_in_trailer_Maintenance_page();

		i_create_new_dock_booking();
		i_select_booking_type_and_a_consignment();
		i_select_the_slot();
		i_enter_booking_details_with_bookingID(bookingID);
		inventoryUpdateStepDefs.i_proceed_to_complete_the_process();
		the_booking_details_should_be_appeared_in_the_dock_scheduler_booking();
	}
	
	@When("^I select booking type and a consignment$")
	public void i_select_booking_type_and_a_consignment() throws Throwable {
		dockSchedulerPage.enterBookingType();
		dockSchedulerPage.enterRandomConsignment();
		jdaFooter.clickSearch();
		dockSchedulerPage.selectConsignment();
		jdaFooter.clickNextButton();
	}

	@When("^I navigate to dock scheduler start page$")
	public void i_navigate_to_dock_scheduler_start_page() throws Throwable {
		jdaHomePage.navigateToDockSchedulerPage();
	}

	@When("^I select view existing bookings$")
	public void i_select_view_existing_bookings() throws Throwable {
		dockSchedulerPage.enterSiteID();
		jdaFooter.clickNextButton();
	}

	@When("^I search the booking id$")
	public void i_search_the_booking_id() throws Throwable {
		dockSchedulerPage.enterBookingId(context.getBookingID());
		screen.type(Key.ENTER);
	}
	
	@When("^I search the first booking id$")
	public void i_search_the_first_booking_id() throws Throwable {
		String[] bookingID = context.getDockSchedulerBookingID();
		dockSchedulerPage.enterBookingId(bookingID[0]);
		screen.type(Key.ENTER);
	}

	@Then("^the booking id details should be displayed$")
	public void the_booking_id_details_should_be_displayed() throws Throwable {
		Assert.assertTrue("Booking ID is not as expected. ",dockSchedulerPage.isBookingIdDisplayed());
	}
	
	@When("^I delete the booking$")
	public void i_delete_the_booking() throws Throwable {
		screen.rightClick();
		dockSchedulerPage.selectDeleteBooking();
		Assert.assertTrue("Delete confirmation message is not as expected",
				dockSchedulerPage.isDeleteBookingConfirmationMessageDisplayed());
		screen.type(Key.ENTER);
	}

	@Then("^the booking details should be deleted in the dock scheduler booking$")
	public void the_booking_details_should_be_deleted_in_the_dock_scheduler_booking() throws Throwable {
		jdaHomePage.navigateToDockSchedulerBookingsPage();
		jdaFooter.clickQueryButton();

		dockSchedulerBookingsPage.enterBookingID(context.getBookingID());
		jdaFooter.clickExecuteButton();

		Assert.assertTrue("Records are not as expected", dockSchedulerBookingsPage.isNoRecords());
	}

	@When("^I search the booking id by notes$")
	public void i_search_the_booking_id_by_notes() throws Throwable {
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		dockSchedulerPage.enterNotes();
		screen.type(Key.ENTER);
	}

	@Then("^the booking details should be displayed$")
	public void the_booking_details_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		
		screen.rightClick();
		dockSchedulerPage.selectBookingDetails();

		verification.verifyData("Booking ID", context.getBookingID(), dockSchedulerPage.getBookingID(), failureList);
		verification.verifyData("Trailer ", context.getTrailerNo(), dockSchedulerPage.getTrailerNo(), failureList);
		screen.type(Key.ENTER);

		Assert.assertTrue("Dock Scheduler Booking details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	
	@When("^I proceed to view the booking details$")
	public void i_proceed_to_view_the_booking_details() throws Throwable {
		screen.rightClick();
		dockSchedulerPage.selectBookingDetails();
	}

	@Then("^I should see the booking details$")
	public void i_should_see_the_booking_details() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		verification.verifyData("Booking ID", context.getBookingID(), dockSchedulerPage.getBookingID(), failureList);
		verification.verifyData("Trailer ", context.getTrailerNo(), dockSchedulerPage.getTrailerNo(), failureList);
		screen.type(Key.ENTER);

		Assert.assertTrue("Dock Scheduler Booking details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^I have done the two dock scheduler booking for the consignment$")
	public void i_have_done_the_two_dock_scheduler_booking_for_the_consignment() throws Throwable {
		String[] bookingID = {"65945","12005"};
		context.setDockSchedulerBookingID(bookingID);
		
		for (int i = 0; i < 2; i++) {
			i_have_done_the_dock_scheduler_booking_with_booking_id(bookingID[i]);
		}
	}

	@When("^I move the booking$")
	public void i_move_the_booking() throws Throwable {
		screen.rightClick();
		dockSchedulerPage.selectMoveBooking();
	}

	@When("^I select the second booked slot$")
	public void i_select_the_second_booked_slot() throws Throwable {

		while(!dockSchedulerPage.isBookedSlotExists()){
			jdaHomePage.scrollDown();
		}
		dockSchedulerPage.selectBookedSlot();
			
//		if (dockSchedulerPage.isBookedSlotExists()) {
//			dockSchedulerPage.selectBookedSlot();
//		} else {
//			jdaHomePage.scrollDown();
//				dockSchedulerPage.selectBookedSlot();
//		}
		jdaFooter.clickNextButton();
	}

	 @Then("^the booking ovverrun error message should be displayed$")
	 public void the_booking_error_message_should_be_displayed() throws Throwable {
		 Assert.assertTrue("Error message is not as expected",dockSchedulerPage.isErrorMessageDisplayed());
		 screen.type(Key.ENTER);
		 
		 //Delete the loaded booking id
		 String [] bookingID = context.getDockSchedulerBookingID();
		 
		 for (int i=0;i<bookingID.length;i++){
			 i_navigate_to_dock_scheduler_start_page();
			 i_select_view_existing_bookings();
			 i_search_the_booking_id();
			 the_booking_id_details_should_be_displayed();
			 i_delete_the_booking();
			 the_booking_details_should_be_deleted_in_the_dock_scheduler_booking();
		 }
	 }
}
