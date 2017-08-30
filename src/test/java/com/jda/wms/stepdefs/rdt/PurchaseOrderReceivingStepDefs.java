package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.gm.DeliveryStepDefs;
import com.jda.wms.stepdefs.gm.InventoryQueryStepDefs;
import com.jda.wms.stepdefs.gm.InventoryTransactionQueryStepDefs;
import com.jda.wms.stepdefs.gm.JDALoginStepDefs;
import com.jda.wms.stepdefs.gm.PreAdviceHeaderStepsDefs;
import com.jda.wms.stepdefs.gm.PreAdviceLineStepDefs;
import com.jda.wms.stepdefs.gm.UPIReceiptHeaderStepDefs;
import com.jda.wms.stepdefs.gm.UPIReceiptLineStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class PurchaseOrderReceivingStepDefs {
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	ArrayList<String> failureList = new ArrayList<String>();
	private Hooks hooks;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	Map<String, Map<String, Map<String, String>>> multipleUpiMap;
	private DeliveryStepDefs deliveryStepDefs;
	private UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs;
	private UPIReceiptLineStepDefs upiReceiptLineStepDefs;
	private UPIReceiptHeaderDB uPIReceiptHeaderDB;
	private UPIReceiptLineDB uPIReceiptLineDB;
	private Verification verification;
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private JDAFooter jdaFooter;
	private PuttyFunctionsPage puttyFunctionsPage;
	private JDALoginStepDefs jdaLoginStepDefs;
	private SkuDB skuDb;

	@Inject
	public PurchaseOrderReceivingStepDefs(PurchaseOrderReceivingPage purchaseOrderReceivingPage, Context context,
			Hooks hooks, PuttyFunctionsStepDefs puttyFunctionsStepDefs, DeliveryStepDefs deliveryStepDefs,
			UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, UPIReceiptLineStepDefs upiReceiptLineStepDefs,
			Verification verification, PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs, JDAFooter jdaFooter, SkuDB skuDb,
			UPIReceiptHeaderDB uPIReceiptHeaderDB, UPIReceiptLineDB uPIReceiptLineDB,
			PuttyFunctionsPage puttyFunctionsPage, JDALoginStepDefs jdaLoginStepDefs) {
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.context = context;
		this.hooks = hooks;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.deliveryStepDefs = deliveryStepDefs;
		this.upiReceiptHeaderStepDefs = upiReceiptHeaderStepDefs;
		this.upiReceiptLineStepDefs = upiReceiptLineStepDefs;
		this.verification = verification;
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.inventoryQueryStepDefs = inventoryQueryStepDefs;
		this.inventoryTransactionQueryStepDefs = inventoryTransactionQueryStepDefs;
		this.jdaFooter = jdaFooter;
		this.skuDb = skuDb;
		this.uPIReceiptHeaderDB = uPIReceiptHeaderDB;
		this.uPIReceiptLineDB = uPIReceiptLineDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
	}

	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line();
	}

	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_list_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_multiple_upi_header();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line_for_multiple_pallets();
	}

	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_as_in_delivery(1);
		upiReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		upiReceiptHeaderStepDefs.SSSC_URN_to_be_updated_with_upi_header();
		upiReceiptLineStepDefs.container_to_be_updated_with_upi_line();
		upiReceiptLineStepDefs.urn_to_be_updated_with_upi_line();
		Assert.assertEquals("UPI Header is not as expected,Expected ZRET", "ZRET",
				uPIReceiptHeaderDB.getUserDefinedType7(context.getUpiId()));
		Assert.assertEquals("UPI Line is not as expected,Expected ZRET", "ZRET",
				uPIReceiptLineDB.getUserDefinedType7(context.getUpiId()));
	}

	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line_for_non_rms()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_as_in_delivery(1);
		upiReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		upiReceiptHeaderStepDefs.SSSC_URN_to_be_updated_with_upi_header();
		upiReceiptLineStepDefs.container_to_be_updated_with_upi_line();
		upiReceiptLineStepDefs.urn_to_be_updated_with_upi_line();

	}

	@Given("^the pallet count should be updated in delivery, po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_po_to_be_linked_with_upi_line() throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line();
	}

	@When("^I receive all skus for the purchase order with multiple upi at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_purchase_order_with_multiple_upi_at_location(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		multipleUpiMap = context.getMultipleUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int j = 0; j <= context.getUpiList().size(); j++) {
			for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
				context.setSkuId(poMap.get(i).get("SKU"));
				context.setPackConfig(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("PACK CONFIG"));
				context.setRcvQtyDue(Integer.parseInt(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("QTY DUE")));
				Thread.sleep(1000);
				i_enter_urn_id(context.getUpiList().get(j));
				the_tag_and_upc_details_should_be_displayed();
				i_enter_the_location();
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (context.getLockCode().equals(null)) {
					i_enter_urn_id();
				} else {
					i_enter_urn_id_for_locked_sku();
				}

				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
					context.setFailureList(failureList);
				}
			}
		}
		hooks.logoutPutty();
	}

	@When("^I receive all skus for the purchase order at location \"([^\"]*)\" with damaged$")
	public void i_receive_all_skus_for_the_purchase_order_at_location_with_damaged(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			i_enter_urn_id();
			jdaFooter.PressEnter();
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			jdaFooter.PressEnter();
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			i_enter_urn_id_damaged();
			jdaFooter.PressEnter();

			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@When("^I blind receive all skus for the returns order at location \"([^\"]*)\"$")
	public void i_blind_receive_all_skus_for_the_returns_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive();
		hooks.logoutPutty();
	}

	@When("^I blind receive all skus for the purchase order at location \"([^\"]*)\" with partset$")
	public void i_blind_receive_all_skus_for_the_returns_order_at_location_with_partset(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_with_partset();
		hooks.logoutPutty();
	}

	@When("^I blind receive all skus for the purchase order at location \"([^\"]*)\" without lockcode$")
	public void i_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_without_lockcode();
		hooks.logoutPutty();
	}

	@When("^I blind receive all skus for the purchase order at location \"([^\"]*)\" with movement label field$")
	public void i_blind_receive_all_skus_for_the_returns_order_at_location_with_movement_label_field(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_with_movement_label_field();
		hooks.logoutPutty();
	}

	@When("^I blind receive all skus for the purchase order at location \"([^\"]*)\" with incorrect quantity$")
	public void i_blind_receive_all_skus_for_the_returns_order_at_location_with_incorrect_quantity(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_with_incorrect_qty_and_without_lockcode();

	}

	@When("^I receive all skus for the purchase order with no asn at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_purchase_order_with_no_asn_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			i_enter_urn_id();

		}
		hooks.logoutPutty();
	}

	@Given("^Error message should be displayed on the page$")
	public void error_message_should_be_displayed_on_the_page() throws Throwable {
		Assert.assertTrue("Error message not displayed as expected",
				purchaseOrderReceivingPage.validate_no_asn_error());

		hooks.logoutPutty();

	}

	@Given("^I receive the PO with basic and pre-advice receiving$")
	public void i_receive_the_po_with_basic_and_pre_advice_receiving() throws Throwable {
		purchaseOrderReceivingPage.selectReceiveMenu();
		Assert.assertTrue("Receive Menu not displayed as expected",
				purchaseOrderReceivingPage.isReceiveMenuDisplayed());

		purchaseOrderReceivingPage.selectBasicReceiveMenu();
		Assert.assertTrue("Basic Receive Menu not displayed as expected",
				purchaseOrderReceivingPage.isBasicReceiveMenuDisplayed());

		purchaseOrderReceivingPage.selectPreAdviceReceive();
	}

	@Given("^I receive the PO with basic and blind receiving$")
	public void i_receive_the_po_with_basic_and_blind_receiving() throws Throwable {
		purchaseOrderReceivingPage.selectReceiveMenu();
		Assert.assertTrue("Receive Menu not displayed as expected",
				purchaseOrderReceivingPage.isReceiveMenuDisplayed());

		purchaseOrderReceivingPage.selectBasicReceiveMenu();
		Assert.assertTrue("Basic Receive Menu not displayed as expected",
				purchaseOrderReceivingPage.isBasicReceiveMenuDisplayed());

		purchaseOrderReceivingPage.selectBlindReceive();
	}

	@Then("^I should be directed to pre-advice entry page$")
	public void i_should_be_directed_to_pre_advice_entry_page() throws Throwable {
		Assert.assertTrue("Receive pre-Advice entry not displayed as expected.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
	}

	@When("^I enter details and perform blind receive with partset$")
	public void i_enter_details_and_perform_blind_receive_with_partset() throws Throwable {
		for (int i = 0; i < context.getRcvQtyDue(); i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());

			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");

			jdaFooter.pressTab();
			jdaFooter.pressTab();

			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			purchaseOrderReceivingPage.enterPartset(context.getPartset());
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDone());
			Thread.sleep(1000);
			if (i != 0) {
				Assert.assertTrue("verification of no of singles failed",
						purchaseOrderReceivingPage.checkNoOfSingles());
			}
		}
	}

	@When("^I enter details and perform blind receive without lockcode$")
	public void i_enter_details_and_perform_blind_receive_without_lockcode() throws Throwable {

		for (int i = 0; i < context.getRcvQtyDue(); i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			jdaFooter.PressEnter();
			Thread.sleep(1000);
			if (i != 0) {
				Assert.assertTrue("verification of no of singles failed",
						purchaseOrderReceivingPage.checkNoOfSingles());
			}
		}
	}

	@When("^I enter details and perform blind receive with movement label field$")
	public void i_enter_details_and_perform_blind_receive_with_movement_label_field() throws Throwable {
		context.setPerfectCondition("Y");
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		jdaFooter.pressTab();
		jdaFooter.pressTab();

		purchaseOrderReceivingPage.enterQuantity("1");
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.doConfigMovementLabel();
		for (int i = 0; i < context.getRcvQtyDue(); i++) {
			jdaFooter.clickUpdateButton(); // Click F4
			// Press tab 7 times to navigate to movement label field
			for (int j = 0; j < 7; j++) {
				jdaFooter.pressTab();
			}
			purchaseOrderReceivingPage.enterMovementLabel(context.getUpiId());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			jdaFooter.PressEnter();
			Thread.sleep(1000);
			if (i != 0) {
				Assert.assertTrue("Verification of no of singles failed",
						purchaseOrderReceivingPage.checkNoOfSingles());
			}
		}
	}

	@When("^I enter details and perform blind receive with incorrect qty and without lockcode$")
	public void i_enter_details_and_perform_blind_receive_with_incorrect_qty_and_without_lockcode() throws Throwable {

		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterQuantity(Integer.toString(context.getQtyReceivedFromPutty()));
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
		jdaFooter.PressEnter();

	}

	@When("^the invalid quantity error message should be displayed on the screen$")
	public void the_invalid_quantity_error_message_should_be_displayed_on_the_screen() throws Throwable {

		Assert.assertTrue("Quantity cannot be more than one", purchaseOrderReceivingPage.isQuantityError());
		jdaFooter.PressEnter();
		Thread.sleep(1000);
		hooks.logoutPutty();
	}

	@When("^I enter details and perform blind receive without lockcode with partset$")
	public void i_enter_details_and_perform_blind_receive_without_lockcode_with_partset() throws Throwable {

		for (int i = 0; i < context.getRcvQtyDue(); i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());

			purchaseOrderReceivingPage.enterPartset(context.getPartset());
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			jdaFooter.PressEnter();
			Thread.sleep(1000);
			if (i != 0) {
				Assert.assertTrue("verification of no of singles failed",
						purchaseOrderReceivingPage.checkNoOfSingles());
			}
		}
	}

	@When("^I enter details and perform blind receive without lockcode and verify no of singles per UPC$")
	public void i_enter_details_and_perform_blind_receive_without_lockcode_and_verify_no_of_singles_per_UPC()
			throws Throwable {
		int qty_Due = Integer.parseInt(uPIReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		for (int i = 0; i < qty_Due; i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "02");
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull.",
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			jdaFooter.PressEnter();
		}
	}

	@When("^I blind receive all skus for the purchase order at location \"([^\"]*)\" without lockcode with partset$")
	public void i_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode_with_partset(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_without_lockcode_with_partset();
		hooks.logoutPutty();
	}

	public void i_blind_receive_all_skus_for_the_purchase_order_at_location_perfect_condition() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_perfect_condition();
		hooks.logoutPutty();
	}

	public void i_blind_receive_all_skus_for_the_purchase_order_at_location_footwear_digit_valdiation(String location,
			String condition) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_footwear();

	}

	@Then("^I should be directed to blind entry page$")
	public void i_should_be_directed_to_blind_entry_page() throws Throwable {
		Assert.assertTrue("Blind Receive entry not displayed as expected.",
				purchaseOrderReceivingPage.isBlindEntryDisplayed());
	}

	@When("^I enter details and perform blind receive$")
	public void i_enter_details_and_perform_blind_receive() throws Throwable {
		for (int i = 0; i < context.getRcvQtyDue(); i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			if (context.getLockCode().equalsIgnoreCase("IMPERFECT")
					|| context.getLockCode().equalsIgnoreCase("SINGLESHOE")
					|| context.getLockCode().equalsIgnoreCase("DMGD")) {
				purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");
			}
			jdaFooter.pressTab();
			if (context.getLockCode().equalsIgnoreCase("IMPERFECT")) {
				purchaseOrderReceivingPage.enterUPC2(context.getUPC() + "01");
			} else if (context.getLockCode().equalsIgnoreCase("DMGD")) {
				purchaseOrderReceivingPage.enterUPC2(context.getUPC() + "02");
			} else if (context.getLockCode().equalsIgnoreCase("SINGLESHOE")) {
				jdaFooter.pressTab();
			}
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDone());
			Thread.sleep(1000);
			if (i != 0) {
				Assert.assertTrue("verification of no of singles failed",
						purchaseOrderReceivingPage.checkNoOfSingles());
			}
		}
	}

	public void i_enter_details_and_perform_blind_receive_perfect_condition() throws Throwable {

		String qtyString = skuDb.getQuantity(context.getSkuId(), context.getUpiId());

		purchaseOrderReceivingPage.enterURNID(context.getUpiId());

		for (int qtyTemp = 0; qtyTemp < Integer.valueOf(qtyString); qtyTemp++) {

			if (qtyTemp > 0) {
				jdaFooter.pressTab();

			}
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());

			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocationID());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull.",
					purchaseOrderReceivingPage.isBlindReceivingDoneperfectCondition());
		}
	}

	public void i_enter_details_and_perform_blind_receive_footwear() throws Throwable {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
		jdaFooter.pressTab();
		// Digit Validation of FootWear UPC is currently under Defect
		jdaFooter.pressTab();

		purchaseOrderReceivingPage.enterQuantity("1");
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterPerfectCondition("Y");

		purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
		Thread.sleep(2000);
		jdaFooter.PressEnter();

	}

	@When("^I enter urn id$")
	public void i_enter_urn_id() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
	}

	@When("^I enter urn id \"([^\"]*)\"$")
	public void i_enter_urn_id(String urn) throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterURNID(urn);
	}

	public void i_enter_urn_id_damaged() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterURNID("SD" + Utilities.getFourDigitRandomNumber());
	}

	@When("^I enter urn id for locked sku$")
	public void i_enter_urn_id_for_locked_sku() throws FindFailed, InterruptedException {
		String urn = null;
		String[] rcvLockSplit = purchaseOrderReceivingPage.getPallet().split("_");

		if (rcvLockSplit[0].contains("QA")) {
			urn = "QA" + Utilities.getFourDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("FIREWALL")) {
			urn = "FWL" + Utilities.getFourDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("REWORK")) {
			urn = "RW" + Utilities.getFourDigitRandomNumber();
		}
		purchaseOrderReceivingPage.enterURNID(urn);
		context.setPalletID(urn);
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_UPI_and_ASN_should_be_in_status(String upiId, String asnId, String status) throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for NON RMS$")
	public void the_UPI_and_ASN_should_be_in_status_for_NON_RMS(String upiId, String asnId, String status)
			throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line_for_non_rms();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
	}

	@When("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and lockcode \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition_and_lockcode(String location,
			String condition, String lockcode) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		context.setLockCode(lockcode);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@When("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and partset \"([^\"]*)\" and lockcode \"([^\"]*)\"$")

	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition_and_partset_and_lockcode(
			String location, String condition, String partset, String lockcode) throws Throwable {

		context.setLocationID(location);
		context.setPerfectCondition(condition);
		context.setLockCode(lockcode);
		context.setPartset(partset);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_with_partset(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with movement label enabled$")
	public void i_receive_all_skus_for_the_returns_order_at_with_movement_label_enabled(String location)
			throws Throwable {
		context.setLocationID(location);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_with_movement_label_field(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with incorrect quantity \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_incorrect_quantity(String location, String quantity)
			throws Throwable {
		context.setLocationID(location);
		context.setQtyReceivedFromPutty(Integer.parseInt(quantity));
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_with_incorrect_quantity(location);
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and partset \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition_and_partset(String location,
			String condition, String partset) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		context.setPartset(partset);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode_with_partset(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	// FSV receiving

	@When("^I receive all skus for the FSV purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_FSV_purchase_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			i_enter_pallet_id(context.getPalletIDList().get(i - 1));
			i_enter_belCode(context.getBelCodeList().get(i - 1));

			puttyFunctionsPage.pressEnter();
			i_enter_the_location();
			puttyFunctionsPage.pressEnter();

			i_enter_the_newpallet(context.enterNewPallet().get(i - 1));
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	// FSV receiving

	@When("^I enter pallet id$")
	public void i_enter_pallet_id(String pallet) throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterPalletId(pallet);
	}

	@When("^I enter belCode$")
	private void i_enter_belCode(String belCode) throws InterruptedException {
		purchaseOrderReceivingPage.enterBelCode(belCode);
	}

	@When("^I enter the newpallet$")
	private void i_enter_the_newpallet(String newPallet) throws InterruptedException, FindFailed {
		purchaseOrderReceivingPage.enterNewPallet(newPallet);
	}

	@When("^I enter the location$")
	public void i_enter_the_location() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterLocation(context.getLocation());
	}

	@When("^I enter tag id$")
	public void i_enter_tag_id() throws FindFailed, InterruptedException {
		String tagId = Utilities.getTenDigitRandomNumber() + Utilities.getTenDigitRandomNumber();
		context.setTagId(tagId);
		purchaseOrderReceivingPage.entertagId(tagId);
	}

	@Then("^I should see the receiving completion$")
	public void i_should_see_the_receiving_completion() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
	}

	@Then("^I proceed to complete the receiving$")
	public void i_proceed_to_complete_the_receiving() throws Throwable {
		purchaseOrderReceivingPage.enterYes();
	}

	@Then("^the receiving should be completed$")
	public void the_receiving_should_be_completed() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
		Thread.sleep(3000);
		hooks.logoutPutty();
	}

	@Then("^the error message should be displayed$")
	public void the_error_message_should_be_displayed() throws Throwable {
		Assert.assertTrue(
				"Appropriate error is not displayed. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	@When("^I perform \"([^\"]*)\" for all skus at location \"([^\"]*)\"$")
	public void i_perform_for_all_skus_at_location(String receiveType, String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			}
			i_enter_urn_id();
			puttyFunctionsPage.pressEnter();
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			i_enter_the_quantity(quantity);
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
		}
	}

	@When("^I enter the quantity")
	public void i_enter_the_quantity(String quantity) throws Throwable {
		// To navigate and enter the modified quantity
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.backSpace();
		puttyFunctionsPage.backSpace();
		puttyFunctionsPage.backSpace();
		purchaseOrderReceivingPage.enterQuantity(quantity);
		puttyFunctionsPage.pressEnter();
	}

	@When("^I receive all skus for the purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_purchase_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if (null == context.getLockCode()) {
				i_enter_urn_id();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed();
				i_enter_the_location();
				jdaFooter.PressEnter();
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (null != context.getLockCode()) {
					i_enter_urn_id_for_locked_sku();
				} else {
					i_enter_urn_id();
					jdaFooter.PressEnter();
					Thread.sleep(2000);
				}

				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
					context.setFailureList(failureList);
				}
			}
			hooks.logoutPutty();
		}
	}

	@When("^I blind receive the invalid upi \"([^\"]*)\"$")
	public void i_blind_receive_the_invalid_upi(String upiId) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setUpiId(upiId);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		purchaseOrderReceivingPage.enterURRN(context.getUpiId());
		jdaFooter.PressEnter();
	}

	@Then("^the URN status should be displayed as URRN does not exist$")
	public void the_URN_status_should_be_displayed_as_URRN_does_not_exist() throws Throwable {
		Assert.assertTrue("URRN Status not displayed as expected",
				purchaseOrderReceivingPage.isURRNNotExistDisplayed());
	}

	@Then("^the error message should be displayed as cannot over receipt$")
	public void the_error_message_should_be_displayed_as_cannot_over_receipt() throws Throwable {
		Assert.assertTrue("Error message:You can not over receipt this sscc",
				purchaseOrderReceivingPage.isOverReceiptErrorDisplayed());
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}

	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_as_in_delivery(1);
		upiReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		upiReceiptHeaderStepDefs.SSSC_URN_to_be_updated_with_upi_header();
		upiReceiptLineStepDefs.container_to_be_updated_with_upi_line();
		upiReceiptLineStepDefs.urn_to_be_updated_with_upi_line();
	}

	@When("^I blind receive all skus for the purchase order at location \"([^\"]*)\"$")
	public void i_blind_receive_all_skus_for_the_purchase_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive();
		hooks.logoutPutty();
	}

	@When("^I blind receive all normal skus for the purchase order at location \"([^\"]*)\"$")
	public void i_blind_receive_all_normal_skus_for_the_purchase_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		upiMap = context.getUPIMap();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receiving_normal_upc();
		hooks.logoutPutty();
	}

	@When("^I enter details and perform blind receiving normal upc$")
	public void i_enter_details_and_perform_blind_receiving_normal_upc() throws Throwable {
		int qtyDue = Integer.parseInt(uPIReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		for (int i = 0; i < qtyDue; i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			Assert.assertFalse("Blind Receiving Unsuccessfull.", purchaseOrderReceivingPage.isBlindReceivingDone());
			jdaFooter.PressEnter();

		}
	}

	@When("^I blind receive all skus for the purchase order returns at location \"([^\"]*)\"$")
	public void i_blind_receive_all_skus_for_the_purchase_order_returns_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		upiMap = context.getUPIMap();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receiving();
		hooks.logoutPutty();
	}

	@When("^I enter details and perform blind receiving$")
	public void i_enter_details_and_perform_blind_receiving() throws Throwable {
		// int numLines =
		// Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiId()));
		int qtyDue = Integer.parseInt(uPIReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qtyDue);
		for (int i = 0; i < qtyDue; i++) {
			// for (int i = 0; i < numLines; i++) {
			purchaseOrderReceivingPage.enterURNID(context.getContainerId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			puttyFunctionsPage.nextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplier());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			if (purchaseOrderReceivingPage.isReceiveCompleted()) {
				jdaFooter.PressEnter();
			}
			Thread.sleep(1000);
		}
	}

	@When("^the tag and upc details should be displayed$")
	public void the_tag_and_upc_details_should_be_displayed() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
				purchaseOrderReceivingPage.isLocationDisplayed());
		String[] tagSplit = purchaseOrderReceivingPage.getTagId().split("_");
		String tagID = tagSplit[0];

		verification.verifyData("Tag ID", context.getUpiId(), tagID, failureList);

		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
		String packConfig = packConfigSplit[0];
		verification.verifyData("Pack Config", context.getPackConfig(), packConfig, failureList);

		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
				failureList);

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceive().split("_");
		String qtyToRcv = qtySplit[0];
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue()), qtyToRcv, failureList);

		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
		String upc = upcSplit[0];
		context.setUPC(upc);
		Assert.assertTrue(
				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I enter pre advice id \"([^\"]*)\" and SKU id \"([^\"]*)\"$")
	public void i_enter_pre_advice_id_and_SKU_id(String preAdviceId, String skuId) throws Throwable {
		purchaseOrderReceivingPage.enterPreAdvId(preAdviceId);
		purchaseOrderReceivingPage.enterSKUId(skuId);
	}

	@When("^the PO should be received at location \"([^\"]*)\"$")
	public void the_po_should_be_received_at_location(String location) throws Throwable {
		i_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("complete");
	}

	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be received at \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at(String preAdviceId, String type, String upiId,
			String asnId, String location) throws Throwable {
		context.setUpiId(upiId);
		context.setPreAdviceId(preAdviceId);
		preAdviceHeaderStepsDefs.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
				preAdviceId, type, upiId, asnId, "Released");

		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_receive_all_skus_for_the_purchase_order_at_location_with_damaged(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be received at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and lockcode \"([^\"]*)\"$")
	public void the_UPI_and_ASN_should_be_received_at(String upiId, String asnId, String location, String condition,
			String lockcode) throws Throwable {
		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		context.setLockCode(lockcode);

		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				"Released");

		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		context.setLocation(location);
		// TODO Supplier SKU Table
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();

		i_blind_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	// Returns receiving with normal upc

	@When("^I provide eight digit UPC while receiving all skus at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_provide_eight_digit_UPC_while_receiving_all_skus_at_with_perfect_condition(String location,
			String condition) throws Throwable {

		i_blind_receive_all_skus_for_the_purchase_order_at_location_footwear_digit_valdiation(location, condition);

	}

	@When("^I perform receiving for all skus at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_perform_receiving_for_all_skus_at_with_perfect_condition(String location, String condition)
			throws Throwable {
		context.setPerfectCondition(condition);
		context.setLocationID(location);
		i_blind_receive_all_skus_for_the_purchase_order_at_location_perfect_condition();
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	@Then("^footer UPC validation error message should be displayed$")
	public void footer_UPC_validation_error_message_should_be_displayed() throws Throwable {

		Assert.assertFalse("UPC Digit Validation for FootWear Failed.",
				purchaseOrderReceivingPage.isFootWearDigitValdiationDone());
		hooks.logoutPutty();
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be received at \"([^\"]*)\" for normal upc with perfect condition \"([^\"]*)\" and lockcode \"([^\"]*)\"$")

	public void the_UPI_and_ASN_should_be_received_at_for_normal_upc(String upiId, String asnId, String location,
			String condition, String lockcode) throws Throwable {
		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		validate(lockcode);

		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				"Released");
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		context.setLocation(location);
		// Supplier SKU Tables
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
		i_blind_receive_all_normal_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_upi_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	private boolean validate(String lockcode) {
		boolean isLockcodeExists = false;
		try {
			if (lockcode.equals("DMGD")) {
				context.setLockCode(lockcode);
				isLockcodeExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset"))
				isLockcodeExists = false;
			return isLockcodeExists;
		}
		return isLockcodeExists;
	}

	@Then("^I should see that no valid preadvices found message$")
	public void i_should_see_that_no_valid_preadvices_found_message() throws Throwable {
		Assert.assertTrue("No Valid Pre advices message is not displayed. ["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
		hooks.logoutPutty();
	}

	@Given("the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be locked with code \"([^\"]*)\" and received at location \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_locked_with_code_and_received_at_location(String preAdviceId,
			String type, String upiId, String asnId, String lockCode, String location) throws Throwable {
		preAdviceHeaderStepsDefs.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
				preAdviceId, type, upiId, asnId, "Released");
		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_upi_header();
		preAdviceLineStepDefs.i_lock_the_product_with_lock_code(lockCode);
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line();
		i_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@When("^I receive all skus for the purchase order at location \"([^\"]*)\" with full pallet \"([^\"]*)\"$")

	public void i_receive_all_skus_for_the_purchase_order_at_location_with_full_pallet(String location,
			String fullPallet) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			i_enter_urn_id();
			puttyFunctionsPage.nextScreen();

			// To give tabs 5 times to navigate to FP line
			for (int t = 0; t < 5; t++) {
				puttyFunctionsPage.pressTab();
			}
			purchaseOrderReceivingPage.enterFullPallet(fullPallet);
			puttyFunctionsPage.pressEnter();
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			puttyFunctionsPage.pressEnter();

			if (fullPallet.equals("N")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			} else if (fullPallet.equals("Y")) {
				Assert.assertTrue("Rcv Pre Entry Page not displayed",
						purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
			}

			hooks.logoutPutty();
		}
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" of type \"([^\"]*)\" should be received at location \"([^\"]*)\" and \"([^\"]*)\" at site \"([^\"]*)\"$")
	public void the_UPI_and_ASN_of_type_should_be_received_at_location_and_at_site(String upiId, String asnId,
			String type, String location, String condition, String siteId) throws Throwable {
		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		context.setSiteId(siteId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				"Released");
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		context.setLocation(location);
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
		context.setContainerId(uPIReceiptLineDB.getContainer(upiId));
		i_blind_receive_all_skus_for_the_purchase_order_returns_at_location(location);
		inventoryTransactionQueryStepDefs.the_inventory_transaction_should_be_updated();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" of type \"([^\"]*)\" should  received at location \"([^\"]*)\" and \"([^\"]*)\" at site \"([^\"]*)\"$")
	public void the_UPI_and_ASN_of_type_should_received_at_location_and_at_site_(String upiId, String asnId,
			String type, String location, String condition, String siteId) throws Throwable {
		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		context.setSiteId(siteId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				"Released");
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		context.setLocation(location);
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
		context.setContainerId(uPIReceiptLineDB.getContainer(upiId));
		i_blind_receive_all_skus_for_the_purchase_order_returns_at_location(location);
		inventoryTransactionQueryStepDefs.the_inventory_transaction_should_be_updated();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	@Given("^the FSV PO \"([^\"]*)\" of type \"([^\"]*)\" should be received at location \"([^\"]*)\" and site id \"([^\"]*)\"$")
	public void the_FSV_PO_of_type_should_be_received_at_location_and_site_id(String preAdviceId, String type,
			String location, String siteId) throws Throwable {
		preAdviceHeaderStepsDefs.the_FSV_PO_of_type_should_be_in_status_at_site_id(preAdviceId, type, "Released",
				siteId);
		preAdviceLineStepDefs.the_FSV_PO_line_should_have_sku_quantity_due_details();
		preAdviceHeaderStepsDefs.the_PO_should_not_be_linked_with_UPI_line(preAdviceId);
		i_receive_all_skus_for_the_FSV_purchase_order_at_location(location);
		// inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received_for_fsv_po();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_fsv_PO_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_FSV_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for multi sourced SKU$")
	public void the_UPI_and_ASN_should_be_in_status_for_multi_sourced_SKU(String upiId, String asnId, String status)
			throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC_sourced_by_multi_supplier();

	}

}
