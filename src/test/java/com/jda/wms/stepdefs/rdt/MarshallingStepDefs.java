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
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderMarshallingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.When;

public class MarshallingStepDefs {
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

	@Inject
	public MarshallingStepDefs(PurchaseOrderMarshallingPage purchaseOrderMarshallingPage, Context context,
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

	

	@When("^I perfom marshalling for goh$")
	public void i_perfom_marshalling_goh() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderMarshallingPage.selectMarshallMenu();
		puttyFunctionsPage.pressTab();
		context.setPalletID(moveTaskDB.getPalletId(context.getOrderId()));
		// context.setPalletID(moveTaskDB.getPalletId("5104200542"));
		System.out.println(context.getPalletID());
		purchaseOrderMarshallingPage.enterPalletID(context.getPalletID());
		puttyFunctionsPage.pressEnter();
		System.out.println(context.getPalletID());
		Assert.assertTrue("Marshalling completion is not as expected",
				purchaseOrderMarshallingPage.ismarshallcompPageDisplayed());
		// puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}

	@When("^I perform marshalling for cross dock$")
	public void i_perfom_marshalling_for_cross_dock() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderMarshallingPage.selectMarshallMenu();
		purchaseOrderMarshallingPage.enterPalletID("5885121529080280317");
		Thread.sleep(6000);
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Marshalling completion is not as expected",
				purchaseOrderMarshallingPage.ismarshallcompPageDisplayed());
		puttyFunctionsPage.backscreen();
		Assert.assertTrue("Marshalling message is not as expected",
				purchaseOrderMarshallingPage.ismarshallcompMsgDisplayed());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Marshalling location change is not as expected",
				purchaseOrderMarshallingPage.ismarshalllocationMsgDisplayed());
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}

}
