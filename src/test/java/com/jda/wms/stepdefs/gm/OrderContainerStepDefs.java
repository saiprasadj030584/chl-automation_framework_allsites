package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.OrderContainerDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.OrderContainerPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;

public class OrderContainerStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private OrderContainerPage orderContainerPage;
	private JDAFooter jDAFooter;
	private JdaLoginPage jdaLoginPage;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private OrderHeaderDB orderHeaderDB;
	private InventoryDB inventoryDB;
	private OrderLineDB orderLineDB;
	private OrderContainerDB orderContainerDB;

	@Inject

	public OrderContainerStepDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,
			UPIReceiptHeaderDB upiReceiptHeaderDB, Verification verification, DeliveryDB deliveryDB,
			PreAdviceLineStepDefs preAdviceLineStepDefs, PreAdviceLineDB preAdviceLineDB,
			UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage, OrderHeaderDB orderHeaderDB,
			JDAHomeStepDefs jDAHomeStepDefs, InventoryDB inventoryDB, JdaLoginPage jdaLoginPage, JDAFooter jDAFooter,
			OrderLineDB orderLineDB, OrderContainerPage orderContainerPage, OrderContainerDB orderContainerDB) {
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage = jdaHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.inventoryDB = inventoryDB;
		this.orderLineDB = orderLineDB;
		this.orderContainerPage = orderContainerPage;
		this.orderContainerDB = orderContainerDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.jdaHomePage = jdaHomePage;
		this.jDAFooter = jDAFooter;
	}

	@Then("^the container id should be generated$")
	public void the_container_id_should_be_generated() throws Throwable {
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_order_container_page();
		jDAFooter.clickQueryButton();
		orderContainerPage.enterOrderID(context.getOrderId());
		jDAFooter.clickExecuteButton();
		orderContainerDB.getContainerId(context.getOrderId());

	}

	@Given("^the urn id and pallet id should be updated in order container page$")
	public void the_urn_id_and_pallet_id_should_be_updated_in_order_container_page() throws Throwable {
		jDAFooter.clickQueryButton();
		orderContainerPage.queryWithOrderId(context.getOrderId());
		ArrayList<String> failureList = new ArrayList<String>();
		if (!orderContainerDB.getStatus(context.getOrderId()).equalsIgnoreCase("Open")) {
			failureList.add("Status field not updated as expected " + context.getOrderId());
		}
		if (orderContainerDB.getPalletId(context.getOrderId()) == null) {
			failureList.add("PalletID field not updated as expected " + context.getOrderId());
		}
		if (orderContainerDB.getContainerId(context.getOrderId()) == null) {
			failureList.add("32 digit urn  field not updated as expected " + context.getOrderId());
		}
		// order haeder picked
		Assert.assertTrue("Order Container is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	@Given("^the urn id should be updated in order container page$")
	public void the_urn_id_should_be_updated_in_order_container_page() throws Throwable {
		jDAFooter.clickQueryButton();
		orderContainerPage.queryWithOrderId(context.getOrderId());
		ArrayList<String> failureList = new ArrayList<String>();
		if (!orderContainerDB.getStatus(context.getOrderId()).equalsIgnoreCase("Open")) {
			failureList.add("Status field not updated as expected " + context.getOrderId());
		}
		if (orderContainerDB.getContainerId(context.getOrderId()) == null) {
			failureList.add("32 digit urn ID field not updated as expected " + context.getOrderId());
		}
		Assert.assertTrue("Order Container is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Then("^master URN should build$")
	public void master_URN_should_build() throws Throwable {
		jDAFooter.clickQueryButton();
		System.out.println("0rder" + context.getOrderId());
		orderContainerPage.enterOrderID(context.getOrderId());
		jDAFooter.clickExecuteButton();
		Thread.sleep(2000);
		//Assert.assertTrue("Master URN not displayed as expected", orderContainerPage.BuildMasterURN());
		orderContainerPage.getBuildMasterURN();
		Thread.sleep(2000);
	}

	 @Then("^URN should be sorted$")
		public void urn_should_be_sorted() throws Throwable {
		 jdaHomePage.navigateToOrderContainerPage();
		 jDAFooter.clickQueryButton();
		 orderContainerPage.enterOrderID(context.getOrderId());
		 jDAFooter.clickExecuteButton();
		 orderContainerPage.getBuildMasterURN();
		 Thread.sleep(2000);
		 Assert.assertEquals("Sortation is not as expected",context.getToPallet(),orderContainerDB.selectURN(context.getOrderId()) );
		}
}