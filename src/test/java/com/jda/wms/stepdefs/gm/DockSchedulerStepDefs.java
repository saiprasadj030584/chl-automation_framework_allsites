package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.When;

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
}
