package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.gm.DeliveryStepDefs;
import com.jda.wms.stepdefs.gm.InventoryQueryStepDefs;
import com.jda.wms.stepdefs.gm.InventoryTransactionQueryStepDefs;
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
	private DeliveryStepDefs deliveryStepDefs;
	private UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs;
	private UPIReceiptLineStepDefs upiReceiptLineStepDefs;
	private Verification verification;
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private PuttyFunctionsPage puttyFunctionsPage;
	private JDAFooter jdaFooter;

	@Inject
	public PurchaseOrderReceivingStepDefs(PurchaseOrderReceivingPage purchaseOrderReceivingPage, Context context,
			Hooks hooks, PuttyFunctionsStepDefs puttyFunctionsStepDefs, DeliveryStepDefs deliveryStepDefs,
			UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, UPIReceiptLineStepDefs upiReceiptLineStepDefs,
			Verification verification, PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs, JDAFooter jdaFooter,
			PuttyFunctionsPage puttyFunctionsPage) {
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
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.jdaFooter = jdaFooter;
	}

	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_upi_header();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line();
	}

	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_as_in_delivery(1);
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_upi_header();
		upiReceiptHeaderStepDefs.SSSC_URN_to_be_updated_with_upi_header();
		upiReceiptLineStepDefs.container_to_be_updated_with_upi_line();
		upiReceiptLineStepDefs.urn_to_be_updated_with_upi_line();

	}

	@Given("^the pallet count should be updated in delivery, po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_po_to_be_linked_with_upi_line() throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line();
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
			i_enter_urn_id();
			puttyFunctionsPage.pressEnter();
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
		hooks.logoutPutty();
	}

	// @When("^I receive more quantity for all skus at location \"([^\"]*)\"$")
	// public void i_receive_more_quantity_for_all_skus_at_location(String
	// location) throws Throwable {
	// System.out.println(context.getRcvQtyDue());
	// System.out.println(context.getRcvQtyDue()+5);
	// // i_receive_all_skus_at_location_with_modified_quantity(location,
	// // (Integer.toString(context.getRcvQtyDue() + 5)));
	// }

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
			System.out.println(quantity);
			i_enter_urn_id();
			puttyFunctionsPage.pressEnter();
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();

			i_enter_the_quantity(quantity);
			i_enter_urn_id();
			puttyFunctionsPage.pressEnter();
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
		puttyFunctionsPage.backspace();
		puttyFunctionsPage.backspace();
		puttyFunctionsPage.backspace();
		purchaseOrderReceivingPage.enterQuantity(quantity);
		puttyFunctionsPage.pressEnter();
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
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			i_enter_urn_id_damaged();

			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
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

	@Then("^I should be directed to Blind entry page$")
	public void i_should_be_directed_to_blind_entry_page() throws Throwable {
		Assert.assertTrue("Receive pre-Advice entry not displayed as expected.",
				purchaseOrderReceivingPage.isBlindEntryDisplayed());
	}

	@When("^I enter details and perform blind receive$")
	public void i_enter_details_and_perform_blind_receive() throws Throwable {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");
		jdaFooter.pressTab();
		if (context.getLockCode().equalsIgnoreCase("IMPERFECT")) {
			purchaseOrderReceivingPage.enterUPC2(context.getUPC() + "01");
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
		jdaFooter.PressEnter();
		Assert.assertTrue("Blind Receiving Unsuccessfull.", purchaseOrderReceivingPage.isBlindReceivingDone());
	}

	@When("^I enter urn id$")

	public void i_enter_urn_id() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
	}

	public void i_enter_urn_id_damaged() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterURNID("SD" + Utilities.getFourDigitRandomNumber());
	}

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

	@When("^I enter the location$")
	public void i_enter_the_location() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterLocation(context.getLocation());
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

	@Then("^I should see the receiving completion$")
	public void i_should_see_the_receiving_completion() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
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
		context.setlocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		context.setLockCode(lockcode);

		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				"Released");

		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		context.setLocation(location);
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();

		i_blind_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	@Then("^I should see that no valid preadvices found message$")

	public void i_should_see_that_no_valid_preadvices_found_message() throws Throwable {
		Assert.assertTrue("No Valid Pre advices message is not displayed. ["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
		hooks.logoutPutty();
	}

	@Then("^I proceed to complete the receiving$")
	public void i_proceed_to_complete_the_receiving() throws Throwable {
		purchaseOrderReceivingPage.enterYes();
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

	@When("^I enter tag id$")
	public void i_enter_tag_id() throws FindFailed, InterruptedException {
		String upiId = Utilities.getTenDigitRandomNumber() + Utilities.getTenDigitRandomNumber();
		purchaseOrderReceivingPage.enterTagid(upiId);
		context.setTagId(upiId);

	}

	@Then("^the error message should be displayed as cannot over receipt$")
	public void the_error_message_should_be_displayed_as_cannot_over_receipt() throws Throwable {
		Assert.assertTrue("Error message:You can not over receipt this sscc",
				purchaseOrderReceivingPage.isOverReceiptErrorDisplayed());
		// jdaFooter.PressEnter();
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}
}
