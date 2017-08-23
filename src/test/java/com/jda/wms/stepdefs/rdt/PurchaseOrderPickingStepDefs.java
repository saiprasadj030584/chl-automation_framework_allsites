package com.jda.wms.stepdefs.rdt;

import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.PurchaseOrderPickingPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

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

	@Inject
	public PurchaseOrderPickingStepDefs(PurchaseOrderPickingPage purchaseOrderPickingPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			MoveTaskDB moveTaskDB) {
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

	}

	@Given("^I perform picking$")
	public void i_perform_picking() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		// puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("P2003");
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		String[] putawayLocation = purchaseOrderPickingPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		puttyFunctionsPage.pressEnter();
		i_enter_the_check_string_for_marshalling();
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("PutAway completion is not as expected", purchaseOrderPickingPage.isPickCmpPageDisplayed());

		hooks.logoutPutty();

	}

	@Then("^I enter the check string for marshalling$")
	public void i_enter_the_check_string_for_marshalling() throws Throwable {
		// Assert.assertTrue("Chk To Page not displayed to enter check string",
		// purchaseOrderPutawayPage.isChkToDisplayed());
		purchaseOrderPickingPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));

	}

}
