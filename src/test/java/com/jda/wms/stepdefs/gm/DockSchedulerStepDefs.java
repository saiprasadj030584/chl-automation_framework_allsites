package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.db.gm.BookingInDiaryLog;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
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
	private DockSchedulerBookingStepDefs dockSchedulerBookingStepDefs;
	private BookingInDiaryLog bookingInDiaryLog;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private GetTcData getTcData;
	private UPIReceiptLineStepDefs upiReceiptLineStepDefs;
	private UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs;
	private BookingInDiary bookingInDiary;
	private OrderHeaderDB orderHeaderDB;


	Screen screen = new Screen();

	@Inject
	public DockSchedulerStepDefs(DockSchedulerPage dockSchedulerPage, JDAFooter jdaFooter, JdaHomePage jdaHomePage,
			Context context, PreReceivingStepDefs preReceivingStepDefs,
			TrailerMaintenanceStepDefs trailerMaintenanceStepDefs, JDAHomeStepDefs jDAHomeStepDefs,
			DockSchedulerBookingStepDefs dockSchedulerBookingStepDefs, BookingInDiaryLog bookingInDiaryLog,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs,UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, GetTcData getTcData,BookingInDiary bookingInDiary,OrderHeaderDB orderHeaderDB,UPIReceiptLineStepDefs upiReceiptLineStepDefs) {


		this.dockSchedulerPage = dockSchedulerPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.preReceivingStepDefs = preReceivingStepDefs;
		this.trailerMaintenanceStepDefs = trailerMaintenanceStepDefs;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.dockSchedulerBookingStepDefs = dockSchedulerBookingStepDefs;
		this.bookingInDiaryLog = bookingInDiaryLog;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.getTcData = getTcData;
		this.bookingInDiary = bookingInDiary;
		this.orderHeaderDB =orderHeaderDB;
		this.upiReceiptLineStepDefs = upiReceiptLineStepDefs;
		this.upiReceiptHeaderStepDefs = upiReceiptHeaderStepDefs;

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

	// @When("^I select the slot$")
	// public void i_select_the_slot() throws Throwable {
	// Thread.sleep(3000);
	// // TODO Check the dock door where to book slot
	// if (context.getSiteId().equals("5649")) {
	// for (int i = 0; i < 3; i++) {
	// jdaHomePage.scrollRightBig();
	// }
	// }
	// dockSchedulerPage.selectSlot();
	// jdaFooter.clickNextButton();
	// while (dockSchedulerPage.isNoDockErrorExists()) {
	// jdaFooter.PressEnter();
	// dockSchedulerPage.selectSlot();
	// jdaFooter.clickNextButton();
	// }
	// }

	@When("^I select the slot$")
	public void i_select_the_slot() throws Throwable {
		Thread.sleep(3000);

		int count = 0;

		// TODO Check the dock door where to book slot
		if (context.getSiteID().equals("5649")) {
			for (int i = 0; i < 3; i++) {
				jdaHomePage.scrollRightBig();
			}
		}
		dockSchedulerPage.selectSlot();
		jdaFooter.clickNextButton();
		while (dockSchedulerPage.isNoDockErrorExists()) {
			count++;
			jdaFooter.PressEnter();
			dockSchedulerPage.selectSlot();
			jdaFooter.clickNextButton();

			if(count==7)
			{
				jdaFooter.PressEnter();
				for(int i=0;i<5;i++)
				{
				jdaHomePage.scrollLeft();
				}
				dockSchedulerPage.selectSlot();
				jdaFooter.clickNextButton();
				
			}
			else if(count==15)
			{

				break;
			}
		}
	}

	@When("^I move to the slot$")
	public void i_move_to_the_slot() throws Throwable {
		Thread.sleep(3000);
		// TODO Check the dock door where to book slot
		for (int i = 0; i < 3; i++) {
			jdaHomePage.scrollRightBig();
		}
	}


	@When("^I create a booking for the asn$")
	public void i_create_a_booking_for_the_asn() throws Throwable {
		int count=0;
		String bookingID = Utilities.getFiveDigitRandomNumber();
		while(bookingInDiary.isBookingExists(bookingID))
		{
			bookingID = Utilities.getFiveDigitRandomNumber();
		}
		System.out.println(" CHECKKK "+context.getTrailerNo());
		String trailerNo = context.getTrailerNo();
		System.out.println(" CHECKKK00000"+bookingID);
		context.setBookingID(bookingID);
		context.setCarrier("ALLPORT");
		context.setServiceLevel("AIR");
		dockSchedulerPage.enterBookingId(bookingID);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.pressTab();
		System.out.println("site id "+context.getSiteID());
		if (context.getSiteID().equals("5649")) {
		dockSchedulerPage.enterCarrier(context.getCarrier());
		}
		dockSchedulerPage.pressTab();
		if (context.getSiteID().equals("5649")) {
		dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
		}
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerType();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerNo(trailerNo);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
		if (dockSchedulerPage.isNoDockErrorExists()) {
			System.out.println("inside while - dock error");
			while (dockSchedulerPage.isNoDockErrorExists()) {
				count++;
				System.out.println("inside while - dock error");
				
		jdaFooter.PressEnter();
				
		dockSchedulerPage.selectSlot();
		jdaFooter.clickNextButton();
		bookingID = Utilities.getFiveDigitRandomNumber();
		while(bookingInDiary.isBookingExists(bookingID))
		{
			bookingID = Utilities.getFiveDigitRandomNumber();
		}
		context.setBookingID(bookingID);
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
		
		if(count==4)
		{
			jdaFooter.PressEnter();
			for(int i=0;i<5;i++)
			{
			jdaHomePage.scrollLeft();
			}
			dockSchedulerPage.selectSlot();
			jdaFooter.clickNextButton();
			bookingID = Utilities.getFiveDigitRandomNumber();
			while(bookingInDiary.isBookingExists(bookingID))
			{
				bookingID = Utilities.getFiveDigitRandomNumber();
			}
			context.setBookingID(bookingID);
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
		else if(count==7)
		{
			break;
		}
		
		}
		}
		else if(dockSchedulerPage.isBookingErrorExists())
		{
			System.out.println("inside if - booking error");
		while (dockSchedulerPage.isBookingErrorExists()) {
			System.out.println("inside while - booking error");
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
		}
	


	
	@When("^I create a booking$")
	public void i_create_a_booking() throws Throwable {
		String bookingID = Utilities.getFiveDigitRandomNumber();
		String trailerNo = context.getTrailerNo();
		context.setBookingID(bookingID);
		System.out.println(context.getSiteID());
		context.setCarrier("ALLPORT");
		context.setServiceLevel("AIR");
		System.out.println(context.getCarrier());
		System.out.println(context.getServiceLevel());
		dockSchedulerPage.enterBookingId(bookingID);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.pressTab();
		System.out.println(context.getSiteID().equals("5649"));
		if (context.getSiteID().equals("5649")) {
			System.out.println("in carrier");
			dockSchedulerPage.enterCarrier(context.getCarrier());
		}
		dockSchedulerPage.pressTab();
		if (context.getSiteID().equals("5649")) {
			System.out.println("in service");
			dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
		}
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerType();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerNo(trailerNo);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
		if (dockSchedulerPage.isNoDockErrorExists()) {
			System.out.println("inside while - dock error");
			while (dockSchedulerPage.isNoDockErrorExists()) {
				System.out.println("inside while - dock error");
				jdaFooter.PressEnter();
				for (int k = 0; k < 3; k++) {
					dockSchedulerPage.clickLeftArrowSlide();
				}
				dockSchedulerPage.selectSlot();
				jdaFooter.clickNextButton();
				// bookingID = Utilities.getFiveDigitRandomNumber();
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
		} else if (dockSchedulerPage.isBookingErrorExists()) {
			System.out.println("inside if - booking error");
			while (dockSchedulerPage.isBookingErrorExists()) {
				System.out.println("inside while - booking error");
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
	@When("^I create multiple dock booking at site \"([^\"]*)\"$")
	public void i_create_multiple_dock_booking_at_site(String site) throws Throwable {
		ArrayList<String> bookingIdList = new ArrayList<String>();
		for (int i = 0; i < context.getTrailerList().size(); i++) {
			context.setTrailerNo(context.getTrailerList().get(i));
			jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
			i_create_new_dock_booking_at_site(site);
			i_select_the_booking_type_for_consignment();
			i_select_the_slot();
			i_create_a_booking_for_the_asn_with_trailer(context.getTrailerNo());
			bookingIdList.add(context.getBookingID());
			dockSchedulerBookingStepDefs.the_booking_details_should_appear();

		}
		context.setBookingList(bookingIdList);
	}
	@When("^I create new dock booking at site \"([^\"]*)\"$")
	public void i_create_new_dock_booking_at_site(String site) throws Throwable {
		dockSchedulerPage.selectCreateNewBooking();
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(site);
			context.setSiteID(site);
		}
		jdaFooter.clickNextButton();
	}
	@When("^I create a booking for the asn with trailer \"([^\"]*)\"$")
	public void i_create_a_booking_for_the_asn_with_trailer(String trailer) throws Throwable {
		int count = 0;
		String bookingID = Utilities.getFiveDigitRandomNumber();
		while (bookingInDiary.isBookingExists(bookingID)) {
			bookingID = Utilities.getFiveDigitRandomNumber();
		}
		System.out.println(" CHECKKK " + trailer);
		String trailerNo = trailer;
		System.out.println(" CHECKKK00000" + bookingID);
		context.setBookingID(bookingID);
		context.setCarrier("ALLPORT");
		context.setServiceLevel("AIR");

		dockSchedulerPage.enterBookingId(bookingID);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterBookingId("In Progress");
		dockSchedulerPage.pressTab();

		System.out.println("site id " + context.getSiteID());
		if (context.getSiteID().equals("5649")) {
			dockSchedulerPage.enterCarrier(context.getCarrier());
		}
		dockSchedulerPage.pressTab();
		if (context.getSiteID().equals("5649")) {
			dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
		}
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerType();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterTrailerNo(trailerNo);
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedPallets();
		dockSchedulerPage.pressTab();
		dockSchedulerPage.enterEstimatedCartons();
		jdaFooter.PressEnter();
		if (dockSchedulerPage.isNoDockErrorExists() || dockSchedulerPage.isBookingErrorExists()) {
			System.out.println("inside while - dock error");
			while (dockSchedulerPage.isNoDockErrorExists() || dockSchedulerPage.isBookingErrorExists()) {
				count++;
				System.out.println("inside while - dock error");

				jdaFooter.PressEnter();

				dockSchedulerPage.selectSlot();
				jdaFooter.clickNextButton();
				bookingID = Utilities.getFiveDigitRandomNumber();
				while (bookingInDiary.isBookingExists(bookingID)) {
					bookingID = Utilities.getFiveDigitRandomNumber();
				}
				context.setBookingID(bookingID);
				jdaFooter.deleteExistingContent();
				dockSchedulerPage.enterBookingId(bookingID);
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterBookingId("In Progress");
				dockSchedulerPage.pressTab();

				System.out.println("site id " + context.getSiteID());
				if (context.getSiteID().equals("5649")) {
					jdaFooter.deleteExistingContent();
					dockSchedulerPage.enterCarrier(context.getCarrier());
				}
				dockSchedulerPage.pressTab();
				if (context.getSiteID().equals("5649")) {
					jdaFooter.deleteExistingContent();
					dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
				}
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterTrailerType();
				dockSchedulerPage.pressTab();
				jdaFooter.deleteExistingContent();
				dockSchedulerPage.enterTrailerNo(trailerNo);
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterEstimatedPallets();
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterEstimatedCartons();
				jdaFooter.PressEnter();

				if (count == 4) {
					jdaFooter.PressEnter();
					for (int i = 0; i < 5; i++) {
						jdaHomePage.scrollLeft();
					}
					dockSchedulerPage.selectSlot();
					jdaFooter.clickNextButton();
					bookingID = Utilities.getFiveDigitRandomNumber();
					while (bookingInDiary.isBookingExists(bookingID)) {
						bookingID = Utilities.getFiveDigitRandomNumber();
					}
					context.setBookingID(bookingID);
					jdaFooter.deleteExistingContent();
					dockSchedulerPage.enterBookingId(bookingID);
					dockSchedulerPage.pressTab();
					dockSchedulerPage.enterBookingId("In Progress");
					dockSchedulerPage.pressTab();

					System.out.println("site id " + context.getSiteID());
					if (context.getSiteID().equals("5649")) {
						jdaFooter.deleteExistingContent();
						dockSchedulerPage.enterCarrier(context.getCarrier());
					}
					dockSchedulerPage.pressTab();
					if (context.getSiteID().equals("5649")) {
						jdaFooter.deleteExistingContent();
						dockSchedulerPage.enterServiceLevel(context.getServiceLevel());
					}
					dockSchedulerPage.pressTab();
					dockSchedulerPage.enterTrailerType();
					dockSchedulerPage.pressTab();
					jdaFooter.deleteExistingContent();
					dockSchedulerPage.enterTrailerNo(trailerNo);
					dockSchedulerPage.pressTab();
					dockSchedulerPage.enterEstimatedPallets();
					dockSchedulerPage.pressTab();
					dockSchedulerPage.enterEstimatedCartons();
					jdaFooter.PressEnter();

				} else if (count == 7) {
					break;
				}

			}
		} else if (dockSchedulerPage.isBookingErrorExists()) {
			System.out.println("inside if - booking error");
			while (dockSchedulerPage.isBookingErrorExists()) {
				System.out.println("inside while - booking error");
				jdaFooter.PressEnter();
				jdaFooter.clickNextButton();
				bookingID = Utilities.getFiveDigitRandomNumber();
				jdaFooter.deleteExistingContent();
				dockSchedulerPage.enterBookingId(bookingID);
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterBookingId("In Progress");
				dockSchedulerPage.pressTab();
				System.out.println("site id " + context.getSiteID());
				dockSchedulerPage.pressTab();
				dockSchedulerPage.pressTab();
				dockSchedulerPage.pressTab();
				jdaFooter.deleteExistingContent();
				dockSchedulerPage.enterTrailerNo(trailerNo);
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterEstimatedPallets();
				dockSchedulerPage.pressTab();
				dockSchedulerPage.enterEstimatedCartons();
				jdaFooter.PressEnter();
			}
		}
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

	@When("^I create new dock booking at site$")
	public void i_create_new_dock_booking_at_site() throws Throwable {
		String site = context.getSiteID();
		dockSchedulerPage.selectCreateNewBooking();
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(site);
			context.setSiteID(site);
		}
		jdaFooter.clickNextButton();
	}

	@Given("^I have done the dock scheduler booking with the PO, UPI, ASN of type \"([^\"]*)\" at site$")
	public void i_have_done_the_dock_scheduler_booking_with_the_PO_UPI_ASN_of_type_at_site(String type)
			throws Throwable {

		String preAdviceId = getTcData.getPo();
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		String site = context.getSiteID();

//		 String preAdviceId = getTcData.getPo();
//		 String upiId = getTcData.getUpi();
//		 String asnId = getTcData.getAsn();
		
		
	//String site = "5649";
		

		
		
		

		preReceivingStepDefs.the_PO_UPI_ASN_of_type_details_should_be_displayed(type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn();
		dockSchedulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@Given("^I have done the dock scheduler booking with the UPI, ASN of type \"([^\"]*)\" at site$")
	public void i_have_done_the_dock_scheduler_booking_with_the_UPI_ASN_of_type_at_site(String type) throws Throwable {

		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		String site = context.getSiteID();

		context.setSiteID(site);
		context.setSKUType(type);
		purchaseOrderReceivingStepDefs.the_UPI_and_ASN_should_be_in_status("Released");
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn();
		dockSchedulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@Given("^I have done the dock scheduler booking with the UPI, ASN of type \"([^\"]*)\" at site for NON RMS$")
	public void i_have_done_the_dock_scheduler_booking_with_the_UPI_ASN_of_type_at_site_for_NON_RMS(String type)
			throws Throwable {

		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		String site = context.getSiteID();
		context.setSiteID(site);
		context.setSKUType(type);
		purchaseOrderReceivingStepDefs.the_UPI_and_ASN_should_be_in_status_for_NON_RMS(upiId, asnId, "Released");
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn();
		dockSchedulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@Given("^I have done the dock scheduler booking with the PO of type \"([^\"]*)\" at site$")
	public void i_have_done_the_dock_scheduler_booking_with_the_PO_of_type_at_site(String type) throws Throwable {
		// String preAdviceId = getTcData.getPo();
		String preAdviceId = context.getPreAdviceId();
		context.setSiteID("5649");
		String site = context.getSiteID();
		preReceivingStepDefs.the_PO_of_type_details_should_be_displayed(type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site();
		i_select_the_booking_type_preadvice();
		i_select_the_slot();
		i_create_a_booking_for_the_asn();
		dockSchedulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@Given("^I have done the dock scheduler booking with the BookingId \"([^\"]*)\", PO \"([^\"]*)\", UPI \"([^\"]*)\", ASN \"([^\"]*)\" of type \"([^\"]*)\" at site \"([^\"]*)\"$")
	public void i_have_done_the_dock_scheduler_booking_with_the_BookingId_PO_UPI_ASN_of_type(String BookingId,
			String preAdviceId, String upiId, String asnId, String type, String site) throws Throwable {

		preReceivingStepDefs.the_PO_UPI_ASN_of_type_details_should_be_displayed(type);
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();
		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn_with_bookingid(BookingId);
		dockSchedulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}

	@When("^I select view existing bookings$")
	public void i_select_view_existing_bookings() throws Throwable {
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(context.getSiteID());
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
		while (dockSchedulerPage.isNoDockErrorExists()) {
			int count = 0;
			count++;
			jdaFooter.PressEnter();
			dockSchedulerPage.selectSlot();
			jdaFooter.clickNextButton();

			if(count==7)
			{
				jdaFooter.PressEnter();
				for(int i=0;i<5;i++)
				{
				jdaHomePage.scrollLeft();
				}
				dockSchedulerPage.selectSlot();
				jdaFooter.clickNextButton();
				
			}
			else if(count==15)
			{

				break;
			}
		}
	}

	

	@When("^I change the booking time to different date$")
	public void i_change_the_booking_time_to_different_date() throws Throwable {
		dockSchedulerPage.changeBookingTimeToDifferentDate();
		//dockSchedulerPage.selectSlot();
		 i_select_the_slot();
		// i_move_to_the_slot();
		jdaFooter.clickNextButton();
	}

	@When("^I change the status of booking to BookingStatus \"([^\"]*)\"$")
	public void i_change_the_status_of_booking(String bookingStatus) throws Throwable {
		dockSchedulerPage.changeBookingStatus(bookingStatus);
	}

	@Then("^the booking id details with updated status \"([^\"]*)\" should be displayed on the page$")
	public void the_booking_id_details_with_updated_status_should_be_displayed_on_the_page(String bookingStatus)
			throws Throwable {
		jdaHomePage.navigateToDockSchedulerPage();
		if (dockSchedulerPage.isSiteExists()) {
			dockSchedulerPage.enterSiteID(context.getSiteID());
		}
		jdaFooter.clickNextButton();
		dockSchedulerPage.enterBookingId(context.getBookingID());
		jdaFooter.PressEnter();
		Assert.assertTrue("Booking ID is not as expected. ", dockSchedulerPage.isBookingIdDisplayedIn());
		dockSchedulerPage.checkBookingStatusUpdated();
		Assert.assertTrue("Booking ID is not as expected. ", dockSchedulerPage.isBookingStatusUpdated(bookingStatus));
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

	@Given("^the UPI , ASN of type \"([^\"]*)\" details should be displayed$")
	public void the_UPI_ASN_of_type_details_should_be_displayed(String type) throws Throwable {
//		String upiId = getTcData.getUpi();
//		String asnId = getTcData.getAsn();
		String site = context.getSiteID();

//		String site = context.getSiteId();
		String upiId = context.getUpiId();
		String asnId = context.getAsnId();
		context.setSKUType(type);
		purchaseOrderReceivingStepDefs.the_UPI_and_ASN_should_be_in_status("Released");
	}

	@Given("^I have done the dock scheduler booking with the UPI, ASN of type \"([^\"]*)\" at site for IDT$")
	public void i_have_done_the_dock_scheduler_booking_with_the_UPI_ASN_of_type_at_site_for_idt(String type)
			throws Throwable {
		String site = context.getSiteID();
		//String upiId = getTcData.getUpi();
		// asnId = getTcData.getAsn();
		//context.setSKUType(datatype);
		context.setSiteID(site);
		context.setSKUType(type);
		System.out.println( "check" + context.getSKUType());
		upiReceiptHeaderStepDefs.the_UPI_and_ASN_of_type_should_be_in_status_for_IDT(type, "Released");
		upiReceiptLineStepDefs.the_UPI_should_have_sku_quantity_due_details();
		upiReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		trailerMaintenanceStepDefs.i_create_a_trailer_to_receive_at_the_dock_door();

		jDAHomeStepDefs.i_navigate_to_dock_scheduler_start_page();
		i_create_new_dock_booking_at_site();
		i_select_the_booking_type_and_ASN();
		i_select_the_slot();
		i_create_a_booking_for_the_asn();
		dockSchedulerBookingStepDefs.the_booking_details_should_appear_in_the_dock_scheduler_booking();
	}
	
	@When("^I select the booking type for consignment$")
	public void i_select_the_booking_type_for_consignment() throws Throwable {
		dockSchedulerPage.enterBookingType("Consignment");
		String cons=orderHeaderDB.selectConsignment(context.getOrderId());
		context.setConsignmentID(cons);
		Thread.sleep(2000);
		jdaFooter.pressTab();
		dockSchedulerPage.enterConsignmentID(context.getConsignmentID());
		jdaFooter.clickSearch();
		dockSchedulerPage.selectConsignment();
		jdaFooter.clickNextButton();
	}
	@When("^I select the booking type for flatpack consignment$")
	public void i_select_the_booking_type_for_flatpack_consignment() throws Throwable {
		//context.setOrderId("5104628740");
		dockSchedulerPage.enterBookingType("Consignment");
		jdaFooter.pressTab();
		dockSchedulerPage.enterConsignmentID(context.getConsignmentID());
		jdaFooter.clickSearch();
		dockSchedulerPage.selectConsignment();
		jdaFooter.clickNextButton();
	}
}