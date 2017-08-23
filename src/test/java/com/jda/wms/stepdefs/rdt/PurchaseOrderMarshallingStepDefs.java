package com.jda.wms.stepdefs.rdt;

import java.util.Map;

import org.junit.Assert;

import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderMarshallingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.When;

public class PurchaseOrderMarshallingStepDefs {
	private PurchaseOrderMarshallingPage purchaseOrderMarshallingPage;
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

	public PurchaseOrderMarshallingStepDefs(PurchaseOrderMarshallingPage purchaseOrderMarshallingPage, Context context,
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
		this.purchaseOrderMarshallingPage = purchaseOrderMarshallingPage;
		this.moveTaskDB = moveTaskDB;

	}

	@When("^I Perfom marshalling$")
	public void i_Perfom_marshalling() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderMarshallingPage.selectMarshallMenu();
		puttyFunctionsPage.pressTab();
		context.setPalletID(moveTaskDB.getPalletId(context.getOrderId()));
		purchaseOrderMarshallingPage.enterPalletID(context.getPalletID());
		Assert.assertTrue("PutAway completion is not as expected",
				purchaseOrderMarshallingPage.ismarshallcompPageDisplayed());
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}

}
