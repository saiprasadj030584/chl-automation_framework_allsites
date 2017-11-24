package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.gm.InventoryQueryStepDefs;
import com.jda.wms.stepdefs.gm.InventoryTransactionQueryStepDefs;
import com.jda.wms.stepdefs.gm.JDAHomeStepDefs;
import com.jda.wms.stepdefs.gm.JDALoginStepDefs;
import com.jda.wms.stepdefs.gm.PreAdviceHeaderStepsDefs;
import com.jda.wms.stepdefs.gm.UPIReceiptHeaderStepDefs;
import com.jda.wms.stepdefs.gm.UPIReceiptLineStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private UPIReceiptHeaderDB uPIReceiptHeaderDB;
	private SupplierSkuDB supplierSkuDb;
	private Hooks hooks;
	private UPIReceiptHeaderStepDefs uPIReceiptHeaderStepDefs;
	private UPIReceiptLineStepDefs uPIReceiptLineStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private GetTcData getTcData;

	@Inject
	public IDTReceivingStepDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			Context context, PreAdviceHeaderDB preAdviceHeaderDB, UPIReceiptHeaderDB upiReceiptHeaderDB,
			Verification verification, DeliveryDB deliveryDB, UPIReceiptLineDB upiReceiptLineDB,
			PuttyFunctionsPage puttyFunctionsPage, PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs,
			PurchaseOrderReceivingPage purchaseOrderReceivingPage, UPIReceiptHeaderDB uPIReceiptHeaderDB,
			SupplierSkuDB supplierSkuDb, Hooks hooks, UPIReceiptHeaderStepDefs uPIReceiptHeaderStepDefs,
			UPIReceiptLineStepDefs uPIReceiptLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs,
			PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs, GetTcData getTcData) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.uPIReceiptHeaderDB = uPIReceiptHeaderDB;
		this.supplierSkuDb = supplierSkuDb;
		this.hooks = hooks;
		this.uPIReceiptHeaderStepDefs = uPIReceiptHeaderStepDefs;
		this.uPIReceiptLineStepDefs = uPIReceiptLineStepDefs;
		this.inventoryQueryStepDefs = inventoryQueryStepDefs;
		this.inventoryTransactionQueryStepDefs = inventoryTransactionQueryStepDefs;
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.getTcData = getTcData;
	}

	@Given("^the UPI and ASN should be in \"([^\"]*)\" status for IDT$")
	public void the_UPI_and_ASN_should_be_in_status_for_IDT(String status) throws Throwable {

		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

	//	String upiId = "56490001384579299100395756000210";
	//	String asnId = "0000003184";

		String upiId = context.getUpiId();
		String asnId = context.getAsnId();

		context.setUpiId(upiId);

		context.setAsnId(asnId);
		String ShippingType = "ZIDC";
		ArrayList failureList = new ArrayList();
		System.out.println("entered here");
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		verification.verifyData("Shipping Type", ShippingType, upiReceiptHeaderDB.getShippingType(upiId), failureList);

		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I perform \"([^\"]*)\" for all locked skus at location \"([^\"]*)\" for IDT$")
	public void i_perform_for_all_locked_skus_at_location_for_IDT(String receiveType, String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));
			context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setSupplierID(supplierSkuDb.getSupplierId((context.getSkuId())));
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			} else if (receiveType.equalsIgnoreCase("Full Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue());
			}
			context.setRcvQtyDue(Integer.parseInt(quantity));
			purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			// jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition("N");
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(quantity);
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition("N");
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			if (!purchaseOrderReceivingPage.isExcessReceiptErrorDisplayed()) {
				failureList.add(
						"Error message:You can not over receipt this sscc not displayed for sku " + context.getSkuId());
			}
			puttyFunctionsPage.pressEnter();
		}
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}

	@When("^I perform \"([^\"]*)\" for all skus at location \"([^\"]*)\" for IDT$")
	public void i_perform_for_all_skus_at_location_for_IDT(String receiveType, String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		context.setLocation(location);
		context.setLocationID(location);
		context.setReceiveType(receiveType);
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));

			context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setSupplierID(upiMap.get(context.getSkuId()).get("SUPPLIER ID"));

			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			} else if (receiveType.equalsIgnoreCase("Full Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue());
			}
			context.setRcvQtyDue(Integer.parseInt(quantity));
			purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			// jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(quantity);
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());

			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			if (!purchaseOrderReceivingPage.isExcessReceiptErrorDisplayed()) {
				failureList.add(
						"Error message:You can not over receipt this sscc not displayed for sku " + context.getSkuId());
			}
			puttyFunctionsPage.pressEnter();
		}
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}

	@When("^I perform receiving for all skus at location \"([^\"]*)\" for IDT$")
	public void i_perform_receiving_for_all_skus_at_location_for_IDT(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		context.setLocation(location);
		context.setLocationID(location);
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setContainerId(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("CONTAINER"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
			context.setRcvQtyDue(
					Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
			context.setRcvQtyDue((context.getRcvQtyDue()));
			if (context.getContainerId().length() >= 32) {
				purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			} else {
				purchaseOrderReceivingPage.enterURNID(context.getContainerId());
				jdaFooter.pressTab();
			}

			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(String.valueOf(context.getRcvQtyDue()));
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.navigateToNextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			if (!purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode()) {
				failureList.add("Error message:Receiving not completed " + context.getSkuId());
			}
			puttyFunctionsPage.pressEnter();
		}
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}

	@Then("^the error message should be displayed as excess over receipt$")
	public void the_error_message_should_be_displayed_as_excess_over_receipt() throws Throwable {
		Assert.assertTrue("Error message:Error in over receiving not as expected. ["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
	}

	@Given("^the UPI and ASN of type \"([^\"]*)\" should be received at location \"([^\"]*)\" for IDT$")

	public void the_UPI_and_ASN_of_type_should_be_received_at_location_for_IDT(String Type,
			String location) throws Throwable {
//		String upiId = getTcData.getUpi();
//		String asnId = getTcData.getAsn();
//		String upiId = "56490001389579299900395756884183";
//		String asnId = "PO0918778352";
		String upiId = context.getUpiId();
		String asnId = context.getAsnId();
		the_UPI_and_ASN_should_be_in_status_for_IDT("Released");
		uPIReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		uPIReceiptLineStepDefs.the_UPI_should_have_sku_quantity_due_details();
		i_perform_for_all_skus_at_location_for_IDT("Under Receiving", location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received_idt();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_IDT_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_idt_status_should_be_displayed_as("In Progress");
	}

	@When("^I perform \"([^\"]*)\" for all the skus at location \"([^\"]*)\" for IDT$")
	public void i_perform_for_all_the_skus_at_location_for_IDT(String receiveType, String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));
			context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setSupplierID(supplierSkuDb.getSupplierId((context.getSkuId())));
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			} else if (receiveType.equalsIgnoreCase("Full Receiving")) {
				System.out.println("qty" + String.valueOf(context.getRcvQtyDue()));
				quantity = String.valueOf(context.getRcvQtyDue());
			}
			context.setRcvQtyDue(Integer.parseInt(quantity));
			purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			// jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(quantity);
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition("N");
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			if (!purchaseOrderReceivingPage.isReceiptCompleteDisplayed()) {
				failureList.add("Error message:receipt completed-Locked Damaged not displayed " + context.getSkuId());
			}
			puttyFunctionsPage.pressEnter();
		}
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}

	@When("^I perform normal urn \"([^\"]*)\" for \"([^\"]*)\" at location \"([^\"]*)\" for IDT$")
	public void i_perform_normal_urn_for_all_the_skus_at_location_for_IDT(String receiveType, String type,
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));
			context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setSupplierID(supplierSkuDb.getSupplierId((context.getSkuId())));
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			} else if (receiveType.equalsIgnoreCase("Full Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue());
			}
			context.setRcvQtyDue(Integer.parseInt(quantity));
			purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			System.out.println("Type" + type);
			if (type.equalsIgnoreCase("multiple line item")) {
				jdaFooter.pressTab();
			}
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(quantity);
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			if (!purchaseOrderReceivingPage.isReceiptCompleteDisplayed()) {
				failureList.add("Error message:receipt completed-Locked Damaged not displayed " + context.getSkuId());
			}
			puttyFunctionsPage.pressEnter();
		}
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}
	

	@Given("^the UPI with \"([^\"]*)\" skus and ASN should be in \"([^\"]*)\" status for IDT$")
	public void the_UPI_with_skus_and_ASN_should_be_in_status_for_IDT(String skuType,String status)
			throws Throwable {
		
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(skuType);
		String ShippingType = "ZIDC";
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		verification.verifyData("Shipping Type", ShippingType, upiReceiptHeaderDB.getShippingType(upiId), failureList);

		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the UPI and ASN of \"([^\"]*)\" type should be in \"([^\"]*)\" status for IDT$")
	public void the_UPI_and_ASN_of_type_should_be_in_status_for_IDT(String type, String status)
			throws Throwable {
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		String ShippingType = "ZIDC";
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		verification.verifyData("Shipping Type", ShippingType, upiReceiptHeaderDB.getShippingType(upiId), failureList);

		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@When("^I perform \"([^\"]*)\" for all Locked skus at location \"([^\"]*)\" for IDT$")
	public void i_perform_for_all_Locked_skus_at_location_for_IDT(String receiveType, String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));
			context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setSupplierID(supplierSkuDb.getSupplierId((context.getSkuId())));
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			} else if (receiveType.equalsIgnoreCase("Full Receiving")) {
				System.out.println("qty" + String.valueOf(context.getRcvQtyDue()));
				quantity = String.valueOf(context.getRcvQtyDue());
			}
			context.setRcvQtyDue(Integer.parseInt(quantity));
			purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			// jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(quantity);
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition("N");
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(quantity);
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition("N");
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			if (!purchaseOrderReceivingPage.isExcessReceiptErrorDisplayed()) {
				failureList.add(
						"Error message:You can not over receipt this sscc not displayed for sku " + context.getSkuId());
			}
			puttyFunctionsPage.pressEnter();
		}
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}
	
	@When("^I perform idt receiving at location \"([^\"]*)\" for multiple URN$")
	public void i_perform_idt_receiving_at_location_for_multiple_URN(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		String quantity = null;
		System.out.println("upiMap "+upiMap);
		System.out.println("context.getUpiNumLinesMap() "+context.getUpiNumLinesMap());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		
		int m = 0;
		for (int k = 0; k < context.getUpiList().size(); k++) {
			context.setUpiId(context.getUpiList().get(k));
			System.out.println(context.getUpiNumLinesMap().get(context.getUpiId()));
			for (int j = 0; j < context.getUpiNumLinesMap().get(context.getUpiList().get(k)); j++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));

					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					context.setLocation(location);
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					//jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPalletId(Utilities.getFiveDigitRandomNumber()+Utilities.getFourDigitRandomNumber());;
					jdaFooter.PressEnter();
					Thread.sleep(2000);
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + j,
							purchaseOrderReceivingPage.isBlindReceivingDoneForIdt());
					jdaFooter.PressEnter();
					Thread.sleep(1000);
			}
		}
		hooks.logoutPutty();
	}
	
	@When("^I perform return receiving at location \"([^\"]*)\" for multiple URN$")
	public void i_perform_return_receiving_at_location_for_multiple_URN(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		String quantity = null;
		System.out.println("upiMap "+upiMap);
		System.out.println("context.getUpiNumLinesMap() "+context.getUpiNumLinesMap());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_blind_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_blind_entry_page();
		
		int m = 0;
		for (int k = 0; k < context.getUpiList().size(); k++) {
			context.setUpiId(context.getUpiList().get(k));
			System.out.println(context.getUpiNumLinesMap().get(context.getUpiId()));
			for (int j = 0; j < context.getUpiNumLinesMap().get(context.getUpiList().get(k)); j++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));

					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					context.setLocation(location);
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPalletId(Utilities.getFiveDigitRandomNumber()+Utilities.getFourDigitRandomNumber());;
					jdaFooter.PressEnter();
					Thread.sleep(2000);
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + j,
							purchaseOrderReceivingPage.isBlindReceivingDoneForIdt());
					jdaFooter.PressEnter();
					Thread.sleep(1000);
			}
		}
		hooks.logoutPutty();
	}

}