package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.MoveTaskUpdateDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.PurchaseOrderPickingPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderVehicleLoadingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PickingStepDefs {
	private PurchaseOrderPickingPage purchaseOrderPickingPage;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private Verification verification;
	private InventoryDB inventoryDB;
	private LocationDB locationDB;
	private Hooks hooks;
	private JDAFooter jdaFooter;
	private PuttyFunctionsPage puttyFunctionsPage;
	private MoveTaskDB moveTaskDB;
	private AddressDB addressDB;
	private MoveTaskUpdateDB moveTaskUpdateDB;
	private OrderHeaderDB orderHeaderDB;
	private PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage;
	private BookingInDiary bookingInDiary;

	@Inject
	public PickingStepDefs(PurchaseOrderPickingPage purchaseOrderPickingPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			MoveTaskDB moveTaskDB, AddressDB addressDB, MoveTaskUpdateDB moveTaskUpdateDB, OrderHeaderDB orderHeaderDB,
			BookingInDiary bookingInDiary, PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage) {
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderPickingPage = purchaseOrderPickingPage;
		this.moveTaskDB = moveTaskDB;
		this.addressDB = addressDB;
		this.moveTaskUpdateDB = moveTaskUpdateDB;
		this.orderHeaderDB = orderHeaderDB;
		this.bookingInDiary = bookingInDiary;
		this.purchaseOrderVehicleLoadingPage = purchaseOrderVehicleLoadingPage;

	}

//	@Given("^I perform picking$")
	public void i_perform_picking() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("P2003");
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();

		String[] putawayLocation = purchaseOrderPickingPage.getPickingLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		puttyFunctionsPage.pressEnter();
		i_enter_the_check_string_for_marshalling();
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("PutAway completion is not as expected", purchaseOrderPickingPage.isPickCmpPageDisplayed());

		hooks.logoutPutty();

	}

//	@Given("^I perform picking for hanging$")
	public void i_perform_picking_for_hanging() throws Throwable {
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		Assert.assertNotNull("List ID is not generated as expected", context.getListID());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		purchaseOrderPickingPage.enterListId(context.getListID());
		// purchaseOrderPickingPage.enterListId("HGMS2749711");
		puttyFunctionsPage.pressEnter();

		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		String tagValueL = addressDB.getLowerTagValue();
		String tagValueH = addressDB.getHigherTagValue();
		int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
				+ Integer.parseInt(tagValueL);
		context.setTagId(String.valueOf(tag));
		System.out.println(String.valueOf(tag));
		purchaseOrderPickingPage.enterTagId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();

		// String[] putawayLocation =
		// purchaseOrderPickingPage.getPickingLocation().split("_");
		// String toLocation = putawayLocation[0];
		// context.setToLocation(toLocation);
		// puttyFunctionsPage.pressEnter();
		// i_enter_the_check_string_for_marshalling();
		// puttyFunctionsPage.pressEnter();
		Assert.assertTrue("picking completion is not as expected", purchaseOrderPickingPage.isPickCmpPageDisplayed());

		hooks.logoutPutty();

	}

	///@Given("^I enter the ivalid UPC for hanging$")
	public void i_enter_the_invalid_UPC_for_hanging() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		// context.setListID(moveTaskDB.getListID("5103200521"));
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		// moveTaskUpdateDB.releaseOrderId("5103200521");
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		// puttyFunctionsPage.pressEnter();
		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		String tagValueL = addressDB.getLowerTagValue();
		String tagValueH = addressDB.getHigherTagValue();
		int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
				+ Integer.parseInt(tagValueL);
		context.setTagId(String.valueOf(tag));
		System.out.println(String.valueOf(tag));
		purchaseOrderPickingPage.enterTagId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		i_enter_the_UPC();
		puttyFunctionsPage.pressEnter();
		if (!purchaseOrderPickingPage.isInvalidSkuDetailsDisplayed()) {
			failureList.add("Error message:Invalid Clientsku");
		}
		// puttyFunctionsPage.pressEnter();

		context.setFailureList(failureList);
		hooks.logoutPutty();

	}

//	@Then("^I enter the check string for marshalling$")
	public void i_enter_the_check_string_for_marshalling() throws Throwable {
		purchaseOrderPickingPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));

	}

//	@When("^I enter the  invalid  UPC$")
	public void i_enter_the_invalid_UPC() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("P2003");
		puttyFunctionsPage.pressEnter();
		i_enter_the_UPC();
		puttyFunctionsPage.pressEnter();
		if (!purchaseOrderPickingPage.isInvalidSkuDetailsDisplayed()) {
			failureList.add("Error message:Invalid Clientsku");
		}
		// puttyFunctionsPage.pressEnter();

		context.setFailureList(failureList);
		hooks.logoutPutty();

	}

//	@Then("^the error message should be displayed as invalid details$")
	public void the_error_message_should_be_displayed_as_invalid_details() throws Throwable {
		Assert.assertTrue(
				"Error message:Invalid Clientsku. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	//@Then("^I enter the UPC$")
	public void i_enter_the_UPC() throws Throwable {
		for (int i = 0; i < 18; i++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int i = 0; i < 1; i++) {
			jdaFooter.pressBackSpace();
		}

	}

	@Given("^I proceed for multi pallet load with dock door$")
	public void i_proceed_for_for_multi_pallet_load_with_dock_door() throws Throwable {
		// context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectMultiPalletLoadMenu();
		// context.setBookingID("10658");
		// String dockdoor =
		// bookingInDiary.selectDockDoor(context.getBookingID());
		String dockdoor = bookingInDiary.selectDockDoor("10510");
		purchaseOrderVehicleLoadingPage.enterDockDoor(dockdoor);
		jdaFooter.pressTab();

		String cons = orderHeaderDB.getConsignment("6612008734");
		purchaseOrderVehicleLoadingPage.enterConsignment(cons);
		jdaFooter.pressTab();
		// String trailerNo = context.getTrailerNo();
		String trailerNo = "10229";
		purchaseOrderVehicleLoadingPage.enterTrailer(trailerNo);
		jdaFooter.PressEnter();

		Thread.sleep(6000);
		purchaseOrderVehicleLoadingPage.enterPalletID(context.getUpiId());
		// purchaseOrderVehicleLoadingPage.enterPalletID("5885121529080280317");
		Assert.assertTrue("Query message is not as expected", purchaseOrderVehicleLoadingPage.isQueryMsgDisplayed());
		puttyFunctionsPage.pressEnter();
		Thread.sleep(6000);
		Assert.assertTrue("Consignment loading message is not as expected",
				purchaseOrderVehicleLoadingPage.isConsignmentLoadingMsgDisplayed());
		puttyFunctionsPage.pressEnter();
		// Assert.assertTrue("Vehicle loading page is not as expected",
		// purchaseOrderVehicleLoadingPage.isVehicleLoadingPageDisplayed());
		// context.setOrderId("5104627450");
		// String urn = moveTaskDB.selectURN(context.getOrderId());
		// purchaseOrderVehicleLoadingPage.enterURN(urn);
		// purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed();
		// Assert.assertTrue("Vehicle Loading is not as expected",
		// purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());
		hooks.logoutPutty();
		// context.setVehicleLoadRequired(false);

	}
}