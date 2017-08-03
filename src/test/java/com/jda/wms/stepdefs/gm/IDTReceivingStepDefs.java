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
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.rdt.HHHHHJJJJJ;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class IDTReceivingStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private UPIReceiptLineDB upiReceiptLineDB;
	Map<String, Map<String, String>> upiMap;
	private PuttyFunctionsPage puttyFunctionsPage;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;

	@Inject
	public IDTReceivingStepDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			Context context, PreAdviceHeaderDB preAdviceHeaderDB, UPIReceiptHeaderDB upiReceiptHeaderDB,
			Verification verification, DeliveryDB deliveryDB, UPIReceiptLineDB upiReceiptLineDB,
			PuttyFunctionsPage puttyFunctionsPage, PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;

	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for IDT$")
	public void the_UPI_and_ASN_should_be_in_status_for_IDT(String upiId, String asnId, String status)
			throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		String ShippingType = "ZIDC";
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		verification.verifyData("Shipping Type", ShippingType, upiReceiptHeaderDB.getShippingType(upiId), failureList);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I perform \"([^\"]*)\" for all skus at location \"([^\"]*)\" for IDT$")
	public void i_perform_for_all_skus_at_location_for_IDT(String receiveType, String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		upiMap = context.getUPIMap();

		String quantity = null;
		if (receiveType.equalsIgnoreCase("Over Receiving")) {

		}
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {

		}
		i_enter_details_and_perform_blind_receive_for_IDT();
	}

	@When("^I enter details and perform blind receive for IDT$")
	public void i_enter_details_and_perform_blind_receive_for_IDT() throws Throwable {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		jdaFooter.pressTab();HHHHHJJJJJ
		purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		
		purchaseOrderReceivingPage.enterQuantity("25");
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		Assert.assertTrue("Blind Receiving Unsuccessfull.", purchaseOrderReceivingPage.isBlindReceivingDone());
	}
}
