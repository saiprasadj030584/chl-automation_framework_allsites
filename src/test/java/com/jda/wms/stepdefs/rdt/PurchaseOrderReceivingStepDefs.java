package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
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
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;

	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private UPIReceiptLineDB uPIReceiptLineDB;
	private UPIReceiptHeaderDB uPIReceiptHeaderDB;
	ArrayList<String> failureList = new ArrayList<String>();
	private Hooks hooks;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	Map<String, Map<String, Map<String, String>>> multipleUpiMap;
	private DeliveryStepDefs deliveryStepDefs;
	private UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs;
	private UPIReceiptLineStepDefs upiReceiptLineStepDefs;
	private Verification verification;
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private JDAFooter jdaFooter;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private DeliveryDB deliveryDB;
	private PuttyFunctionsPage puttyFunctionsPage;
	private JDALoginStepDefs jDALoginStepDefs;

	@Inject
	public PurchaseOrderReceivingStepDefs(PurchaseOrderReceivingPage purchaseOrderReceivingPage, Context context,
			Hooks hooks, PuttyFunctionsStepDefs puttyFunctionsStepDefs, DeliveryStepDefs deliveryStepDefs,
			UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, UPIReceiptLineStepDefs upiReceiptLineStepDefs,
			Verification verification, PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs, JDAFooter jdaFooter,
			UPIReceiptHeaderDB uPIReceiptHeaderDB, UPIReceiptLineDB uPIReceiptLineDB,PreAdviceHeaderDB preAdviceHeaderDB,DeliveryDB deliveryDB,PuttyFunctionsPage puttyFunctionsPage,JDALoginStepDefs jDALoginStepDefs) {
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
		this.uPIReceiptHeaderDB = uPIReceiptHeaderDB;
		this.uPIReceiptLineDB = uPIReceiptLineDB;
		this.preAdviceHeaderDB=preAdviceHeaderDB;
		this.deliveryDB=deliveryDB;
		this.puttyFunctionsPage=puttyFunctionsPage;
		this.jDALoginStepDefs = jDALoginStepDefs;
	}

	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_upi_header();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line();
	}

	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_list_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_multiple_upi_header();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line_for_multiple_pallets();
	}
	
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status and locked with code \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_and_locked_with_code(String preAdviceId,String type, String upiId,String asnId, String status, String lockCode) throws Throwable{
		context.setPreAdviceId(preAdviceId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		context.setLockCode(lockCode);
		
		logger.debug("PO ID: "+preAdviceId);
		logger.debug("UPI ID: "+upiId);
		logger.debug("ASN ID: "+asnId);
		logger.debug("Type: "+type);
		
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		
		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		verification.verifyData("UPI Status", status, uPIReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		
		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", uPIReceiptHeaderDB.getNumberOfLines(upiId),String.valueOf(numLines));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: "+numLines);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
		
		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		preAdviceLineStepDefs.i_lock_the_product_with_lock_code(lockCode);
	}
	
	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header list and multiple po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_list_and_multiple_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_delivery();
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_multiple_upi_header();
		upiReceiptLineStepDefs.multiple_po_to_be_linked_with_upi_line_for_multiple_pallets();
	}

	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_as_in_delivery(1);
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_upi_header();
		upiReceiptHeaderStepDefs.SSSC_URN_to_be_updated_with_upi_header();
		upiReceiptLineStepDefs.container_to_be_updated_with_upi_line();
		upiReceiptLineStepDefs.urn_to_be_updated_with_upi_line();
		Assert.assertEquals("UPI Header is not as expected,Expected ZRET", "ZRET",
				uPIReceiptHeaderDB.getUserDefinedType7(context.getUpiId()));
		Assert.assertEquals("UPI Line is not as expected,Expected ZRET", "ZRET",
				uPIReceiptLineDB.getUserDefinedType7(context.getUpiId()));
	}
	
	public void the_multiple_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_as_in_delivery(context.getUpiList().size());
		upiReceiptHeaderStepDefs.asn_to_be_linked_with_multiple_upi_header();
		upiReceiptHeaderStepDefs.SSSC_URN_to_be_updated_with_multiple_upi_header();
		upiReceiptLineStepDefs.container_to_be_updated_with_upi_line_in_multiple_upi();
		upiReceiptLineStepDefs.urn_to_be_updated_with_upi_line_in_multiple_upi();
		for(int i=0;i<context.getUpiList().size();i++)
		{
		Assert.assertEquals("UPI Header is not as expected,Expected ZRET", "ZRET",
				uPIReceiptHeaderDB.getUserDefinedType7(context.getUpiList().get(i)));
		Assert.assertEquals("UPI Line is not as expected,Expected ZRET", "ZRET",
				uPIReceiptLineDB.getUserDefinedType7(context.getUpiList().get(i)));
		}
	}
	
	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line_for_non_rms()
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
	
	
	@When("^I receive all skus for the multiple purchase order with multiple upi at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_multiple_purchase_order_with_multiple_upi_at_location(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		
		upiMap = context.getUPIMap();
		multipleUpiMap = context.getMultipleUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();
		int m=0;
		for (int j = 0; j <context.getUpiList().size(); j++) {
			context.setUpiId(context.getUpiList().get(j));
			for (int i = 1; i <=(context.getUpiNumLinesMap().get(context.getUpiId())); i++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m-1));
				context.setSupplierID(multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("SUPPLIER ID"));
				context.setPackConfig(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("PACK CONFIG"));
				context.setRcvQtyDue(Integer.parseInt(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("QTY DUE")));
				Thread.sleep(1000);
				i_enter_urn_id(context.getUpiList().get(j));
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed();
				i_enter_the_location();
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				
				//change this
				if (context.getLockCode()==null) {
					i_enter_urn_id();
				} else {
					i_enter_urn_id_for_locked_sku();
				}
					jdaFooter.PressEnter();
				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiList().get(j));
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
	
	@When("^I blind receive all skus of multiple upi for the returns order at location \"([^\"]*)\" without lockcode$")
	public void i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_of_multiple_upi_and_perform_blind_receive_without_lockcode();
		hooks.logoutPutty();
	}
	
	@When("^I blind receive all skus for the returns order with mixed stock at location \"([^\"]*)\" without lockcode$")
	public void i_blind_receive_all_skus_for_the_returns_order_with_mixed_stock_at_location_without_lockcode(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_blind_receive_of_mixed_stock_without_lockcode();
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

	@Then("^I should be directed to blind entry page$")
	public void i_should_be_directed_to_blind_entry_page() throws Throwable {
		Assert.assertTrue("Blind Receive entry not displayed as expected.",
				purchaseOrderReceivingPage.isBlindEntryDisplayed());
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

	@When("^I enter tag id$")
	public void i_enter_tag_id() throws FindFailed, InterruptedException {
		String tagId = Utilities.getTenDigitRandomNumber() + Utilities.getTenDigitRandomNumber();
		context.setTagId(tagId);
		purchaseOrderReceivingPage.entertagId(tagId);
	}
	
	@Then("^the error message should be displayed as cannot over receipt$")
	public void the_error_message_should_be_displayed_as_cannot_over_receipt() throws Throwable {
		Assert.assertTrue("Error message:You can not over receipt this sscc",
				purchaseOrderReceivingPage.isOverReceiptErrorDisplayed());
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}
	
	@Then("^the error message should be displayed as cannot over receipt failed$")
	public void the_error_message_should_be_displayed_as_cannot_over_receipt_failed() throws Throwable {
		Assert.assertFalse("Error message:You can not over receipt this sscc shown",
				purchaseOrderReceivingPage.isOverReceiptErrorDisplayed());
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
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
		
for(int j=0;j<context.getNoOfLines();j++)
{
	context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
	context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
	context.setRcvQtyDue(Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
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
	}
	
		
	@When("^I enter details of multiple upi and perform blind receive without lockcode$")
	public void i_enter_details_of_multiple_upi_and_perform_blind_receive_without_lockcode() throws Throwable {
		int m=0;
for(int k=0;k<context.getUpiList().size();k++)
{
	context.setUpiId(context.getUpiList().get(k));
	for(int j=0;j<context.getUpiNumLinesMap().get(context.getUpiList().get(k));j++)
	{
		m++;
		context.setSkuId(context.getSkuFromUPI().get(m-1));

		for (int i = 0; i < Integer.parseInt(context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("QTY DUE")); i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			jdaFooter.navigateToNextScreen();
			purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("SUPPLIER ID"));
			jdaFooter.PressEnter();
			Thread.sleep(2000);
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			jdaFooter.PressEnter();
			Thread.sleep(1000);
			
		}
}
	}
	}
	
	
	@When("^I enter details and perform blind receive of mixed stock without lockcode$")
	public void i_enter_details_and_perform_blind_receive_of_mixed_stock_without_lockcode() throws Throwable {
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setPartset(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("PART SET"));
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
		for (int i = 0; i < Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")); i++) {
			if(j==0)
			{
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
			
			}
			 if(j==1)
			{
				purchaseOrderReceivingPage.enterURNID(context.getUpiId());
				purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC() + "01");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterUPC2(context.getUPC() + "02");
				purchaseOrderReceivingPage.enterQuantity("1");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
				purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			}
			else if(j==2)
			{
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
			}
			
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

	@When("^I enter urn id$")
	public void i_enter_urn_id() throws FindFailed, InterruptedException {
		String urn = null;
		String[] rcvLockSplit =purchaseOrderReceivingPage.getPutawayGroup().split("_");
		if (rcvLockSplit[0].contains("QA")) {
			urn = "QA" + Utilities.getFourDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("FIREWALL")) {
			urn = "FA" + Utilities.getFourDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("REWORK")) {
			urn = "RW" + Utilities.getFourDigitRandomNumber();
		}else if (rcvLockSplit[0].contains("MEZF2Z01")) {
			urn = "M2Z01" + Utilities.getFiveDigitRandomNumber();
		}else
		{
			urn=context.getUpiId();
		}
		purchaseOrderReceivingPage.enterURNID(urn);
		context.setPalletID(urn);
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

	@When("^the tag and upc details should be displayed$")
	public void the_tag_and_upc_details_should_be_displayed() throws FindFailed, InterruptedException {
//		ArrayList failureList = new ArrayList();
//		Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
//				purchaseOrderReceivingPage.isLocationDisplayed());
//		String[] tagSplit = purchaseOrderReceivingPage.getTagId().split("_");
//		String tagID = tagSplit[0];
//
//		verification.verifyData("Tag ID", context.getUpiId(), tagID, failureList);
//
//		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
//		String packConfig = packConfigSplit[0];
//		verification.verifyData("Pack Config", context.getPackConfig(), packConfig, failureList);
//
//		System.out.println(context.getSupplierID()+"????????"+purchaseOrderReceivingPage.getSupplierId());
//		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
//				failureList);

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceive().split("_");
		String qtyToRcv = qtySplit[0];
		
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue()), qtyToRcv, failureList);

//		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
//		String upc = upcSplit[0];
//		context.setUPC(upc);
//		Assert.assertTrue(
//				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
//				failureList.isEmpty());
	}

	@When("^I enter the location$")
	public void i_enter_the_location() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterLocation(context.getLocation());
	}
	
	@When("^I enter the loctn$")
	public void i_enter_the_loctn() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterLoc(context.getLocation());
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
	
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be normal received at \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_normal_received_at(String preAdviceId, String type, String upiId,
			String asnId, String location) throws Throwable {
		context.setUpiId(upiId);
		context.setPreAdviceId(preAdviceId);
		preAdviceHeaderStepsDefs.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
				preAdviceId, type, upiId, asnId, "Released");

		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_UPI_and_ASN_should_be_in_status(String upiId, String asnId, String status) throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		//upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		context.setNoOfLines(numLines);
	}
	
	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for adjustment$")
	public void the_UPI_and_ASN_should_be_in_status_for_adjustment(String upiId, String asnId, String status) throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
				status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC_qty();
		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		context.setNoOfLines(numLines);
		jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
	}
	
	@Given("^the multiple UPI \"([^\"]*)\" of type \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_multiple_UPI_of_type_and_ASN_should_be_in_status(String upiId,String skuType,String asnId, String status) throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(skuType);
	preAdviceHeaderStepsDefs.the_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(upiId, asnId,
			status);
		the_multiple_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		Map<String,Integer> upiNumLines = new HashMap<String,Integer>();
		int numLines=0;
		for(int i=0;i<context.getUpiList().size();i++)
		{
			upiNumLines.put(context.getUpiList().get(i), Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
			numLines +=Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
		}
		context.setUpiNumLinesMap(upiNumLines);
		context.setNoOfLines(numLines);
	}
	
	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for NON RMS$")
	public void the_UPI_and_ASN_should_be_in_status_for_NON_RMS(String upiId, String asnId, String status) throws Throwable {
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
		context.setlocationID(location);
		context.setPerfectCondition(condition);
		context.setLockCode(lockcode);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@When("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and partset \"([^\"]*)\" and lockcode \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition_and_partset_and_lockcode(
			String location, String condition, String partset, String lockcode) throws Throwable {
		context.setlocationID(location);
		context.setPerfectCondition(condition);
		context.setLockCode(lockcode);
		context.setPartset(partset);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_with_partset(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition(String location, String condition)
			throws Throwable {
		context.setlocationID(location);
		context.setPerfectCondition(condition);
		//upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	
	@Given("^I receive all skus of multiple upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_of_multiple_upi_for_the_returns_order_at_with_perfect_condition(String location, String condition)
			throws Throwable {
		context.setlocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	
	@Given("^I receive all skus for the returns order with mixed stock at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_with_mixed_stock_at_with_perfect_condition(String location, String condition)
			throws Throwable {
		context.setlocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_with_mixed_stock_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with movement label enabled$")
	public void i_receive_all_skus_for_the_returns_order_at_with_movement_label_enabled(String location)
			throws Throwable {
		context.setlocationID(location);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_with_movement_label_field(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with incorrect quantity \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_incorrect_quantity(String location, String quantity)
			throws Throwable {
		context.setlocationID(location);
		context.setQtyReceivedFromPutty(Integer.parseInt(quantity));
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_with_incorrect_quantity(location);
	}

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and partset \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition_and_partset(String location,
			String condition, String partset) throws Throwable {
		context.setlocationID(location);
		context.setPerfectCondition(condition);
		context.setPartset(partset);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode_with_partset(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
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
			i_enter_pallet_id(context.getPalletIDList().get(i-1));
			i_enter_belCode(context.getBelCodeList().get(i-1));
			i_enter_the_location();
			i_enter_the_newpallet(context.enterNewPallet().get(i-1));
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
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
	
	@When("^I perform \"([^\"]*)\" for all skus for the FSV purchase order at location \"([^\"]*)\"$")
	public void i_perform_for_all_skus_for_the_FSV_purchase_order_at_location(String receiveType, String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		poMap = context.getPOMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 5);
			}
			i_enter_pallet_id(context.getPalletIDList().get(i-1));
			i_enter_belCode(context.getBelCodeList().get(i-1));
			i_enter_the_loctn();
			i_enter_the_quantity(quantity);
			i_enter_the_newpallet(context.enterNewPallet().get(i-1));
			if(receiveType.equalsIgnoreCase("Under Receiving"))
					{
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
			}
		}
		hooks.logoutPutty();
	}

	@When("^I perform \"([^\"]*)\" for all skus of the returns order for stock adjustment at location \"([^\"]*)\" with perfect condition \"([^\"]*)\" and movement label enabled$")
	public void i_perform_for_all_skus_for_the_returns_order_at_location_with_perfect_condition_and_movement_label_enabled(String receiveType, String location,String condition) throws Throwable {
		
		context.setlocationID(location);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		context.setPerfectCondition(condition);
		for (int i = 0; i <2; i++) {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		if(i==0){
			purchaseOrderReceivingPage.enterQuantity("1");
		}
		if(i==1){
			purchaseOrderReceivingPage.enterQuantity(String.valueOf(context.getRcvQtyDue()+5));
		}
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
		purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		jdaFooter.navigateToNextScreen();
//		if(i==0)
//		{
//		purchaseOrderReceivingPage.doConfigMovementLabel();
//		}
//			jdaFooter.clickUpdateButton(); // Click F4
			// Press tab 7 times to navigate to movement label field
			for (int j = 0; j < 7; j++) {
				jdaFooter.pressTab();
			}
			purchaseOrderReceivingPage.enterMovementLabel(context.getUpiId());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			if(i==0){
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			}
			else{
				Assert.assertTrue("Over Receiving cant be done error not dispalyed for " + i,
						purchaseOrderReceivingPage.isOverReceiptErrorReturnsDisplayed());
			}
			jdaFooter.PressEnter();
			Thread.sleep(1000);
		}
		hooks.logoutPutty();
	}
	
	@When("^I perform \"([^\"]*)\" for all skus of the returns order for stock adjustment at location \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_perform_for_all_skus_for_the_returns_order_at_location_with_perfect_condition(String receiveType, String location,String condition) throws Throwable {
		
		context.setlocationID(location);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		context.setPerfectCondition(condition);
		for (int i = 0; i <2; i++) {
		purchaseOrderReceivingPage.enterURNID(context.getUpiId());
		purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
		jdaFooter.pressTab();
		jdaFooter.pressTab();
		if(i==0)
		{
			purchaseOrderReceivingPage.enterQuantity("1");
		}
		if(i==1)
		{
			purchaseOrderReceivingPage.enterQuantity(String.valueOf(context.getRcvQtyDue()+5));
		}
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
		purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
		jdaFooter.pressTab();
		purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
		
		jdaFooter.PressEnter();
		
			if(i==0)
			{
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			}
			else
			{
				Assert.assertTrue("Over Receiving cant be done error not dispalyed for " + i,
						purchaseOrderReceivingPage.isOverReceiptErrorReturnsDisplayed());
			}
			jdaFooter.PressEnter();
			Thread.sleep(1000);
			
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
	
}