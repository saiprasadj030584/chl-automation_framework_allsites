package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.MoveTaskUpdateDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPickingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPickingStepDefs {
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
	private MoveTaskUpdateDB moveTaskUpdateDB;
	private OrderHeaderDB orderHeaderDB;
	private AddressDB addressDB;

	@Inject
	public PurchaseOrderPickingStepDefs(PurchaseOrderPickingPage purchaseOrderPickingPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			MoveTaskDB moveTaskDB, MoveTaskUpdateDB moveTaskUpdateDB, OrderHeaderDB orderHeaderDB,
			AddressDB addressDB) {
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
		this.moveTaskUpdateDB = moveTaskUpdateDB;
		this.orderHeaderDB = orderHeaderDB;
		this.addressDB = addressDB;

	}

	@Given("^I perform picking$")
	public void i_perform_picking() throws Throwable {
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		Assert.assertNotNull("List ID is not generated as expected", context.getListID());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		if (context.getListID().contains("PICK")) {
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
		} else if (context.getListID().contains("DOL")) {
			if (context.getSKUType().equalsIgnoreCase("Boxed")) {
				moveTaskUpdateDB.releaseOrderId(context.getOrderId());
				purchaseOrderPickingPage.enterListId(context.getListID());
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				String containerId = moveTaskDB.getContainerId(context.getOrderId());
				purchaseOrderPickingPage.enterContainerId(containerId);
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
			} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
				moveTaskUpdateDB.releaseOrderId(context.getOrderId());
				// moveTaskUpdateDB.releaseOrderId("5104200528");
				purchaseOrderPickingPage.enterListId(context.getListID());

				puttyFunctionsPage.pressEnter();
				String customer = orderHeaderDB.getCustomer(context.getOrderId());
				context.setCustomer(customer);
				String tagValueL = addressDB.getLowerTagValue();
				String tagValueH = addressDB.getHigherTagValue();
				int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
						+ Integer.parseInt(tagValueL);
				context.setTagId(String.valueOf(tag));
				System.out.println(String.valueOf(tag));
				purchaseOrderPickingPage.enterTagId(context.getTagId());
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
				puttyFunctionsPage.pressEnter();
			}
		} else if (context.getListID().contains("HNRT")) {
			moveTaskUpdateDB.releaseOrderId(context.getOrderId());
			System.out.println("entered zoom");
			purchaseOrderPickingPage.enterListId(context.getListID());
			System.out.println("entered zoom1");
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
		}
		// Assert.assertTrue("Picking Entry is not as expected",
		// purchaseOrderPickingPage.isPckEntPageDisplayed());
		Assert.assertTrue("picking completion is not as expected", purchaseOrderPickingPage.isPickCmpPageDisplayed());
		hooks.logoutPutty();

	}

	@Then("^I enter the check string for marshalling$")
	public void i_enter_the_check_string_for_marshalling() throws Throwable {
		purchaseOrderPickingPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));
	}

	@When("^I enter the invalid  UPC$")
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

	@Then("^the error message should be displayed as invalid details$")
	public void the_error_message_should_be_displayed_as_invalid_details() throws Throwable {
		Assert.assertTrue(
				"Error message:Invalid Clientsku. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	@Then("^I enter the UPC$")
	public void i_enter_the_UPC() throws Throwable {
		for (int i = 0; i < 18; i++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int i = 0; i < 1; i++) {
			jdaFooter.pressBackSpace();
		}

	}

	@Then("^I proceed with picking$")
	public void i_proceed_with_picking() throws Throwable {
		moveTaskDB.updateStatus(context.getOrderId());
		Thread.sleep(4000);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("PR003");
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the picking should be completed$")
	public void the_picking_should_be_completed() throws Throwable {
		String[] putawayLocation = purchaseOrderPickingPage.getPickingLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		puttyFunctionsPage.pressEnter();
		i_enter_the_check_string_for_marshalling();
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking Entry is not as expected", purchaseOrderPickingPage.isPckEntPageDisplayed());
		hooks.logoutPutty();
	}

	@Then("^the part set warning should be displayed$")
	public void the_part_set_warning_should_be_displayed() throws Throwable {
		purchaseOrderPickingPage.isPartSetQtyDisplayed();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the part set instruction should be displayed$")
	public void the_part_set_instruction_should_be_displayed() throws Throwable {
		Assert.assertTrue("Message Menu page not displayed as expected", purchaseOrderPickingPage.isMsgMenuDisplayed());
		Assert.assertTrue("Part set Instruction page is not displayed as expected",
				purchaseOrderPickingPage.isPartSetInstructionDisplayed());
	}

	@Then("^I proceed with picking to validate multi part set instruction$")
	public void i_proceed_with_picking_to_validate_multi_part_set_instruction() throws Throwable {
		moveTaskDB.updateStatus(context.getOrderId());
		Thread.sleep(4000);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		the_part_set_instruction_should_be_displayed();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("PR003");
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^I should be directed to pickent page$")
	public void i_should_be_directed_to_pickent_page() throws Throwable {
		Assert.assertTrue("Picking Process not completed and Home page not displayed.",
				purchaseOrderPickingPage.isPickEnt());
	}

	@Given("^I select container picking$")
	public void i_select_container_picking() throws Throwable {
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
	}

	@Given("^I perform picking for discrepancy$")
	public void i_perform_picking_for_discrepancy() throws Throwable {
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		purchaseOrderPickingPage.getQuantity();
		purchaseOrderPickingPage.enterMinimumQty();
		purchaseOrderPickingPage.getTagId();
		purchaseOrderPickingPage.selectReason();
		purchaseOrderPickingPage.enterContainerId();
		String[] putawayLocation = purchaseOrderPickingPage.getPickingLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		puttyFunctionsPage.pressEnter();
		i_enter_the_check_string_for_marshalling();
		puttyFunctionsPage.pressEnter();
	}
}
