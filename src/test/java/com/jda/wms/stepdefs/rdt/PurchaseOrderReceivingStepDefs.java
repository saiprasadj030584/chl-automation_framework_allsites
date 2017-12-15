package com.jda.wms.stepdefs.rdt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
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
import com.jda.wms.utils.DateUtils;
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
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private DeliveryDB deliveryDB;
	private PuttyFunctionsPage puttyFunctionsPage;
	private JDALoginStepDefs jdaLoginStepDefs;
	private SkuDB skuDb;
	private GetTcData getTcData;
	private SupplierSkuDB supplierSkuDB;
	private InventoryTransactionDB inventoryTransactionDB;
	private OrderHeaderDB orderHeaderDB;
private InventoryDB inventoryDb;


	@Inject
	public PurchaseOrderReceivingStepDefs(PurchaseOrderReceivingPage purchaseOrderReceivingPage, Context context,
			Hooks hooks, PuttyFunctionsStepDefs puttyFunctionsStepDefs, DeliveryStepDefs deliveryStepDefs,
			UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, UPIReceiptLineStepDefs upiReceiptLineStepDefs,
			Verification verification, PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs, JDAFooter jdaFooter, SkuDB skuDb,
			UPIReceiptHeaderDB uPIReceiptHeaderDB, UPIReceiptLineDB uPIReceiptLineDB,
			PuttyFunctionsPage puttyFunctionsPage, PreAdviceHeaderDB preAdviceHeaderDB, DeliveryDB deliveryDB,
			JDALoginStepDefs jdaLoginStepDefs, GetTcData getTcData, SupplierSkuDB supplierSkuDB,

			InventoryTransactionDB inventoryTransactionDB,OrderHeaderDB orderHeaderDB,InventoryDB inventoryDb) {

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
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.deliveryDB = deliveryDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.getTcData = getTcData;
		this.supplierSkuDB = supplierSkuDB;
		this.inventoryTransactionDB = inventoryTransactionDB;

		this.orderHeaderDB=orderHeaderDB;

		this.inventoryDb=inventoryDb;

	}

	@Given("^the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		System.out.println("ENTERED STEP3");
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
	@Given("^I receive all skus of single upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" for footwear$")
	public void i_receive_all_skus_of_single_upi_for_the_returns_order_at_with_perfect_condition_for_footwear(String location,
			String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_single_upi_for_the_returns_order_at_location_without_lockcode_for_footwear(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	@When("^I blind receive all skus of single upi for the returns order at location \"([^\"]*)\" without lockcode$")
	public void i_blind_receive_all_skus_of_single_upi_for_the_returns_order_at_location_without_lockcode(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();


		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_of_single_upi_and_perform_blind_receive_without_lockcode();
		hooks.logoutPutty();
	}
	@When("^I blind receive all skus of single upi for the returns order at location \"([^\"]*)\" without lockcode for footware$")
	public void i_blind_receive_all_skus_of_single_upi_for_the_returns_order_at_location_without_lockcode_for_footware(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();


		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_of_single_upi_and_perform_blind_receive_without_lockcode_for_footware();
		hooks.logoutPutty();
	}
	@When("^I blind receive all skus of single upi for the returns order at location \"([^\"]*)\" without lockcode for footwear$")
	public void i_blind_receive_all_skus_of_single_upi_for_the_returns_order_at_location_without_lockcode_for_footwear(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_of_single_upi_and_perform_blind_receive_without_lockcode_for_footwear();
		hooks.logoutPutty();
	}
	@When("^I enter details of single upi and perform blind receive without lockcode for footwear$")
	public void i_enter_details_of_single_upi_and_perform_blind_receive_without_lockcode_for_footwear() throws Throwable {
			for (int j = 0; j < context.getSkuFromUPI().size(); j++) {
				context.setSkuId(context.getSkuFromUPI().get(j));
				for (int i = 0; i < Integer.parseInt(context.getUPIMap().get(context.getSkuId()).get("QTY DUE")); i++) {
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getUPIMap().get(context.getSkuId()).get("UPC")+"01");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getUPIMap().get(context.getSkuId()).get("UPC")+"02");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getUPIMap().get(context.getSkuId()).get("SUPPLIER ID"));
					if(context.getUniqueTag().contains("non_rms")){
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPalletId(Utilities.getEightDigitRandomNumber()+Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Thread.sleep(2000);
					if(purchaseOrderReceivingPage.isURRNExists()){
						purchaseOrderReceivingPage.enterURNID(context.getUpiId());
						purchaseOrderReceivingPage.enterUPC1BEL(
								context.getUPIMap().get(context.getSkuId()).get("UPC")+"01");
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterUPC1BEL(
								context.getUPIMap().get(context.getSkuId()).get("UPC")+"02");
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterQuantity("1");
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
						purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
						jdaFooter.pressTab();
						jdaFooter.navigateToNextScreen();
						purchaseOrderReceivingPage.enterSupplierId(context.getUPIMap().get(context.getSkuId()).get("SUPPLIER ID"));
						if(context.getUniqueTag().contains("non_rms")){
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(Utilities.getEightDigitRandomNumber()+Utilities.getOneDigitRandomNumber());
						}
						jdaFooter.PressEnter();
						Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
								purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
						jdaFooter.PressEnter();
						Thread.sleep(1000);
					}
					else{
						Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
								purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
						jdaFooter.PressEnter();
						Thread.sleep(1000);
					}
				}
			}
	}
	

	@Given("^the pallet count should be updated in multiple delivery, asn list to be linked with upi header list and po to be linked with upi line$")
	public void the_pallet_count_should_be_updated_in_multiple_delivery_asn_list_to_be_linked_with_upi_header_list_and_po_to_be_linked_with_upi_line()
			throws Throwable {
		deliveryStepDefs.the_pallet_count_should_be_updated_in_multiple_delivery();
		upiReceiptHeaderStepDefs.multiple_asn_to_be_linked_with_multiple_upi_header();
		upiReceiptLineStepDefs.po_to_be_linked_with_upi_line_for_multiple_pallets();
	}


	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be in \"([^\"]*)\" status and locked with code \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_and_locked_with_code(String type, String status,
			String lockCode) throws Throwable {
		//
		// String preAdviceId = getTcData.getPo();
		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

		// String preAdviceId = "1010002230";
		// String upiId = "00050453000258618208";
		// String asnId = "0000003724";

		String preAdviceId = context.getPreAdviceId();
		String upiId = context.getUpiId();
		String asnId = context.getAsnId();

		context.setPreAdviceId(preAdviceId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		context.setLockCode(lockCode);

		logger.debug("PO ID: " + preAdviceId);
		logger.debug("UPI ID: " + upiId);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		// verification.verifyData("Pre-Advice Status", status,
		// preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		// verification.verifyData("UPI Status", status,
		// uPIReceiptHeaderDB.getStatus(upiId), failureList);
		// verification.verifyData("Delivery Status", status,
		// deliveryDB.getStatus(asnId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", uPIReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());

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
		for (int i = 0; i < context.getUpiList().size(); i++) {
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
		System.out.println("NUMOFLINES" + context.getNoOfLines());
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));

			System.out.println(context.getLockCode());
			// if (null == context.getLockCode()) {

			i_enter_urn_id(context.getUpiId());
			jdaFooter.PressEnter();
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			jdaFooter.PressEnter();

			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (null != context.getLockCode()) {
				i_enter_urn_id_for_locked_sku();
				jdaFooter.PressEnter();
				Thread.sleep(2000);
			} else {
				i_enter_urn_id();
				jdaFooter.PressEnter();
				Thread.sleep(2000);

			}}
		}
			@When("^I receive all skus for the purchase order at location \"([^\"]*)\" for  hanging$")
			public void i_receive_all_skus_for_the_purchase_order_at_location_for_hanging(String location) throws Throwable {
				ArrayList<String> failureList = new ArrayList<String>();
				context.setLocation(location);
				poMap = context.getPOMap();
				upiMap = context.getUPIMap();
				puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
				puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
				i_receive_the_po_with_basic_and_pre_advice_receiving();
				i_should_be_directed_to_pre_advice_entry_page();
				System.out.println("NUMOFLINES" + context.getNoOfLines());
				for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
					context.setSkuId(poMap.get(i).get("SKU"));
					context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
					context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));

					System.out.println(context.getLockCode());
					// if (null == context.getLockCode()) {

					i_enter_urn_id(context.getUpiId());
					jdaFooter.PressEnter();
					the_tag_and_upc_details_should_be_displayed();
					i_enter_the_location();
					jdaFooter.PressEnter();

					Assert.assertTrue("Rcv Pallet Entry Page not displayed",
							purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
					if (null != context.getLockCode()) {
						i_enter_urn_id_for_locked_sku();
						jdaFooter.PressEnter();
						Thread.sleep(2000);
					} else {
						i_enter_urn_id_damaged();
						jdaFooter.PressEnter();
						Thread.sleep(2000);

					}

			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}

			hooks.logoutPutty();
		}
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
		int m = 0;
		for (int j = 0; j < context.getUpiList().size(); j++) {
			context.setUpiId(context.getUpiList().get(j));
			for (int i = 1; i <= (context.getUpiNumLinesMap().get(context.getUpiId())); i++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));
				context.setSupplierID(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("SUPPLIER ID"));
				context.setPackConfig(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("PACK CONFIG"));
				context.setRcvQtyDue(Integer.parseInt(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("QTY DUE")));
				Thread.sleep(1000);
				System.out.println("RECEIVING" + context.getUpiId());
				System.out.println(context.getSkuId());
				System.out.println("MMM" + m);
				System.out.println("JJJJ" + j);
				System.out.println("111111111110000000000");
				if (context.getSKUType().equalsIgnoreCase("Boxed") || context.getSKUType().equalsIgnoreCase("Flatpack")
						|| context.getSKUType().equalsIgnoreCase("GOH")) {
					System.out.println("entered for GOH");
					i_enter_urn_id(context.getUpiId());
					jdaFooter.PressEnter();
					the_tag_and_upc_details_should_be_displayed_for_receiving();
				} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
					System.out.println("entered for Hang");
					i_enter_urn_id(context.getUpiId());
					puttyFunctionsPage.nextScreen();
					i_enter_asn(uPIReceiptHeaderDB.getAsnId(context.getUpiId()));
					i_enter_hanging_value();
					i_enter_trl();
					jdaFooter.PressEnter();
					the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
				}
				System.out.println("NONE");
				i_enter_the_location();
				jdaFooter.PressEnter();
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (null != context.getLockCode()) {
					i_enter_urn_id_for_locked_sku();
				} else {
					i_enter_urn_id();
				}
				System.out.println("RESULT " + purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (purchaseOrderReceivingPage.isRcvPalletEntPutGrpPageDisplayed()) {
					jdaFooter.PressEnter();
				}
				Thread.sleep(2000);

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
			i_enter_urn_id(context.getUpiId());
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

	@When("^I blind receive all skus of multiple upi for the returns order at location \"([^\"]*)\" without lockcode$")
	public void i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode(
			String location) throws Throwable {
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
	@When("^I blind receive all skus of multiple upi for the returns order at location \"([^\"]*)\" without lockcode for footware$")
	public void i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode_for_footware(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_of_multiple_upi_and_perform_blind_receive_without_lockcode_for_footware();
		hooks.logoutPutty();
	}
	
	@When("^I blind receive all skus for the returns order with mixed stock at location \"([^\"]*)\" without lockcode$")
	public void i_blind_receive_all_skus_for_the_returns_order_with_mixed_stock_at_location_without_lockcode(
			String location) throws Throwable {
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
			i_enter_urn_id(context.getUpiId());
			puttyFunctionsPage.pressEnter();

		}
		// hooks.logoutPutty();
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
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
			context.setRcvQtyDue(
					Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
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
	}

	@When("^I enter details and perform blind receive with partset$")
	public void i_enter_details_and_perform_blind_receive_with_partset() throws Throwable {
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
			context.setRcvQtyDue(
					Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
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
	}

	@When("^I enter details and perform blind receive without lockcode$")
	public void i_enter_details_and_perform_blind_receive_without_lockcode() throws Throwable {
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
			context.setRcvQtyDue(
					Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
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
		int m = 0;
		System.out.println("size of upi list" + context.getUpiList().size());
		for (int k = 0; k < context.getUpiList().size(); k++) {
			context.setUpiId(context.getUpiList().get(k));
			for (int j = 0; j < context.getUpiNumLinesMap().get(context.getUpiList().get(k)); j++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));

				for (int i = 0; i < Integer.parseInt(context.getMultipleUPIMap().get(context.getUpiId())
						.get(context.getSkuId()).get("QTY DUE")); i++) {
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					if (context.getUniqueTag().contains("non_rms")) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Thread.sleep(2000);

					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					if (context.getUniqueTag().contains("non_rms")) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
							purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
					jdaFooter.PressEnter();
					Thread.sleep(1000);

				}
			}
		}
	}
	@When("^I enter details of multiple upi and perform blind receive without lockcode for footware$")
	public void i_enter_details_of_multiple_upi_and_perform_blind_receive_without_lockcode_for_footware() throws Throwable {
		int m = 0;
		System.out.println("size of upi list" + context.getUpiList().size());
		for (int k = 0; k < context.getUpiList().size(); k++) {
			context.setUpiId(context.getUpiList().get(k));
			for (int j = 0; j < context.getUpiNumLinesMap().get(context.getUpiList().get(k)); j++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));

				for (int i = 0; i < Integer.parseInt(context.getMultipleUPIMap().get(context.getUpiId())
						.get(context.getSkuId()).get("QTY DUE")); i++) {
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					if (context.getUniqueTag().contains("non_rms")) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Thread.sleep(2000);

					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					if (context.getUniqueTag().contains("non_rms")) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
							purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
					jdaFooter.PressEnter();
					Thread.sleep(1000);

				}
			}
		}
	}
	@When("^I enter details of single upi and perform blind receive without lockcode$")
	public void i_enter_details_of_single_upi_and_perform_blind_receive_without_lockcode() throws Throwable {
		for (int j = 0; j < context.getSkuFromUPI().size(); j++) {
			context.setSkuId(context.getSkuFromUPI().get(j));
			for (int i = 0; i < Integer.parseInt(context.getUPIMap().get(context.getSkuId()).get("QTY DUE")); i++) {
				purchaseOrderReceivingPage.enterURNID(context.getUpiId());
				purchaseOrderReceivingPage.enterUPC1BEL(context.getUPIMap().get(context.getSkuId()).get("UPC"));
				jdaFooter.pressTab();
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterQuantity("1");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
				purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
				jdaFooter.pressTab();
				jdaFooter.navigateToNextScreen();
				purchaseOrderReceivingPage
						.enterSupplierId(context.getUPIMap().get(context.getSkuId()).get("SUPPLIER ID"));
				if (context.getUniqueTag().contains("non_rms")) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				} else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("goh"))) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				}
				else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				}
				else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("flatpack"))) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				}
				jdaFooter.PressEnter();
				Thread.sleep(2000);
				if (purchaseOrderReceivingPage.isURRNExists()) {
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(context.getUPIMap().get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage
							.enterSupplierId(context.getUPIMap().get(context.getSkuId()).get("SUPPLIER ID"));
					if (context.getUniqueTag().contains("non_rms")) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					} else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("goh"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("flatpack"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
							purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
					jdaFooter.PressEnter();
					Thread.sleep(1000);
				} else {
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
							purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
					jdaFooter.PressEnter();
					Thread.sleep(1000);
				}
			}
		}
	}
	@When("^I enter details of single upi and perform blind receive without lockcode for footware$")
	public void i_enter_details_of_single_upi_and_perform_blind_receive_without_lockcode_for_footware() throws Throwable {
		for (int j = 0; j < context.getSkuFromUPI().size(); j++) {
			context.setSkuId(context.getSkuFromUPI().get(j));
			for (int i = 0; i < Integer.parseInt(context.getUPIMap().get(context.getSkuId()).get("QTY DUE")); i++) {
				purchaseOrderReceivingPage.enterURNID(context.getUpiId());
				purchaseOrderReceivingPage.enterUPC1BEL(context.getUPIMap().get(context.getSkuId()).get("UPC")+"01");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterUPC1BEL(context.getUPIMap().get(context.getSkuId()).get("UPC")+"02");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterQuantity("1");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
				purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
				jdaFooter.pressTab();
				jdaFooter.navigateToNextScreen();
				purchaseOrderReceivingPage
						.enterSupplierId(context.getUPIMap().get(context.getSkuId()).get("SUPPLIER ID"));
				if (context.getUniqueTag().contains("non_rms")) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				} else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("goh"))) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				}
				else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				}
				else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("flatpack"))) {
					jdaFooter.pressTab();
					purchaseOrderReceivingPage
							.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
				}
				jdaFooter.PressEnter();
				Thread.sleep(2000);
				if (purchaseOrderReceivingPage.isURRNExists()) {
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(context.getUPIMap().get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage
							.enterSupplierId(context.getUPIMap().get(context.getSkuId()).get("SUPPLIER ID"));
					if (context.getUniqueTag().contains("non_rms")) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					} else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("goh"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage.enterPalletId(
								Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("hanging"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					else if ((context.getUniqueTag().contains("rms")) && (context.getUniqueTag().contains("flatpack"))) {
						jdaFooter.pressTab();
						purchaseOrderReceivingPage
								.enterPalletId(Utilities.getEightDigitRandomNumber() + Utilities.getOneDigitRandomNumber());
					}
					jdaFooter.PressEnter();
					Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
							purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
					jdaFooter.PressEnter();
					Thread.sleep(1000);
				} else {
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
			context.setProductGroup(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("DEPARTMENT"));
			System.out.println("UPI ID" + context.getUpiId());
			for (int i = 0; i < Integer
					.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")); i++) {

				// footwear
				if (context.getProductGroup().equalsIgnoreCase("T02")) {
					System.out.println("Inside T02");
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					if (context.getUpiId().length() != 32) {
						jdaFooter.pressTab();
					}
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
				// partset
				else if (Integer.parseInt(context.getPartset()) >= 2) {
					System.out.println("Inside Partset");
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					if (context.getUpiId().length() != 32) {
						jdaFooter.pressTab();
					}
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
				} else {
					System.out.println("Inside Normal");
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					if (context.getUpiId().length() != 32) {
						jdaFooter.pressTab();
					}
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
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
			context.setRcvQtyDue(
					Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
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
		System.out.println("KLJKL" + purchaseOrderReceivingPage.isPutAwayGroupExists());
		if (purchaseOrderReceivingPage.isPutAwayGroupExists()) {
			System.out.println("enterpp");
			String urn = null;
			String[] rcvLockSplit = purchaseOrderReceivingPage.getPutawayGroup().split("_");
			if (rcvLockSplit[0].contains("QA")) {
				urn = "QA" + Utilities.getFourDigitRandomNumber();
			} else if (rcvLockSplit[0].contains("FIREWALL")) {
				urn = "FW" + Utilities.getFourDigitRandomNumber();
			} else if (rcvLockSplit[0].contains("REWORK")) {
				urn = "RW" + Utilities.getFourDigitRandomNumber();
			} else if (rcvLockSplit[0].contains("MEZF2Z01")) {
				urn = "M2Z01" + Utilities.getFiveDigitRandomNumber();
			} else if (rcvLockSplit[0].contains("RACKING")) {
				urn = "RA" + Utilities.getFourDigitRandomNumber();
			} else if (rcvLockSplit[0].contains("LOC")) {
				urn = Utilities.getNineDigitRandomNumber();
			} else {
				urn = context.getUpiId();
			}
			purchaseOrderReceivingPage.enterPalletID(urn);
			context.setPalletID(urn);
		}
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
		System.out.println("with lock code");
		String urn = null;

		String[] rcvLockSplit = purchaseOrderReceivingPage.getPallet().split("_");

		if (rcvLockSplit[0].contains("QA")) {
			urn = "QA" + Utilities.getFourDigitRandomNumber() + Utilities.getThreeDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("FIREWALL")) {
			urn = "FW" + Utilities.getFourDigitRandomNumber() + Utilities.getThreeDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("REWORK")) {
			urn = "RW" + Utilities.getFourDigitRandomNumber() + Utilities.getThreeDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("MEZF2Z01")) {
			urn = "M2Z01" + Utilities.getFiveDigitRandomNumber();
		} else if (rcvLockSplit[0].contains("LOC")) {
			// urn = Utilities.getSixDigitRandomNumber();
			urn = Utilities.getSixDigitRandomNumber() + Utilities.getThreeDigitRandomNumber();
		} else {
			urn = context.getUpiId();
		}

		purchaseOrderReceivingPage.enterURNID(urn);
		context.setPalletID(urn);

	}

	@When("^I enter the loctn$")
	public void i_enter_the_loctn() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterLoc(context.getLocation());
	}

	@When("^the PO should be received at location \"([^\"]*)\"$")
	public void the_po_should_be_received_at_location(String location) throws Throwable {
		i_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@Then("^I should see the receiving completion$")
	public void i_should_see_the_receiving_completion() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
	}

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\" for qa build$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at_for_qa_build(String type, String location)
			throws Throwable {

		String preAdviceId = getTcData.getPo();
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		context.setUpiId(upiId);
		context.setPreAdviceId(preAdviceId);
		context.setAsnId(asnId);

		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");

		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the PO of type \"([^\"]*)\" with UPI containing \"([^\"]*)\" sku and ASN should be normal received at \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_containing_sku_and_ASN_should_be_normal_received_at(String type,
			String packConfig, String location) throws Throwable {
		String preAdviceId = getTcData.getPo();
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		context.setUpiId(upiId);
		context.setPreAdviceId(preAdviceId);
		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");

		preAdviceLineStepDefs.the_PO_should_have_mezz_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_receive_all_skus_for_the_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the UPI and ASN should be in \"([^\"]*)\" status$")
	public void the_UPI_and_ASN_should_be_in_status(String status) throws Throwable {
		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();
		String upiId = context.getUpiId();
		String asnId = context.getAsnId();
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		context.setNoOfLines(numLines);
	}

	@Given("^the UPI with \"([^\"]*)\" skus and ASN should be in \"([^\"]*)\" status$")
	public void the_UPI_with_skus_and_ASN_should_be_in_status(String skuType, String status) throws Throwable {
		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

		String upiId = context.getUpiId();
		String asnId = context.getAsnId();

		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(skuType);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		context.setNoOfLines(numLines);
	}

	@Given("^the UPI and ASN should be in \"([^\"]*)\" status for adjustment$")
	public void the_UPI_and_ASN_should_be_in_status_for_adjustment(String status) throws Throwable {

		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

		// String upiId = "95580085370650011050230212465758";
		// String asnId = "0000844973";

		String upiId = context.getUpiId();
		String asnId = context.getAsnId();

		System.out.println("CORRECT SCENARIO");
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC_qty();
		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
		context.setNoOfLines(numLines);
		// jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
	}

	@Given("^the multiple UPI of type \"([^\"]*)\" and ASN should be in \"([^\"]*)\" status$")
	public void the_multiple_UPI_of_type_and_ASN_should_be_in_status(String dataType, String status) throws Throwable {

		 String upiId1 = getTcData.getUpi();
		 String upiId2 = getTcData.getUpi2();
		 String asnId = getTcData.getAsn();

//		String upiId1 = "56490000369490536160009081600400";
//		String upiId2 = "56490000542760246410022061800100";
//		String asnId = "0000006398";

		context.setStatus(status);
		String upiId = upiId1 + "," + upiId2;
		System.out.println("upi id" + upiId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(dataType);
		preAdviceHeaderStepsDefs.the_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details();
		the_multiple_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		Map<String, Integer> upiNumLines = new HashMap<String, Integer>();
		int numLines = 0;
		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiNumLines.put(context.getUpiList().get(i),
					Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
			numLines += Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
		}
		context.setUpiNumLinesMap(upiNumLines);
		context.setNoOfLines(numLines);
	}

	@Given("^the normal UPI of type \"([^\"]*)\" and ASN should be in \"([^\"]*)\" status$")
	public void the_normal_UPI_of_type_and_ASN_should_be_in_status(String dataType, String status) throws Throwable {
		//
		 String upiId = getTcData.getUpi();
		 String asnId = getTcData.getAsn();
		 System.out.println("upi"+ upiId);
		 System.out.println("asn"+asnId );
		context.setStatus(status);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(dataType);
		preAdviceHeaderStepsDefs.the_normal_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details();
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line_for_non_rms();
		int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiId()));
		context.setNoOfLines(numLines);
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for NON RMS$")
	public void the_UPI_and_ASN_should_be_in_status_for_NON_RMS(String upiId, String asnId, String status)
			throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(status);
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

	@Given("^I receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_at_with_perfect_condition(String location, String condition)
			throws Throwable {
		context.setLocationID(location);
		context.setLocation(location);
		context.setPerfectCondition(condition);
		i_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@Given("^I receive all skus of multiple upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_of_multiple_upi_for_the_returns_order_at_with_perfect_condition(String location,
			String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	@Given("^I receive all skus of multiple upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" for footware$")
	public void i_receive_all_skus_of_multiple_upi_for_the_returns_order_at_with_perfect_condition_for_footware(String location,
			String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode_for_footware(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	@Given("^I receive all skus of single upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_of_single_upi_for_the_returns_order_at_with_perfect_condition(String location,
			String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_single_upi_for_the_returns_order_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	@Given("^I receive all skus of single upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" for footware$")
	public void i_receive_all_skus_of_single_upi_for_the_returns_order_at_with_perfect_condition_for_footware(String location,
			String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_single_upi_for_the_returns_order_at_location_without_lockcode_for_footware(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}
	@Given("^I receive all skus for the returns order with mixed stock at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_returns_order_with_mixed_stock_at_with_perfect_condition(String location,
			String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_for_the_returns_order_with_mixed_stock_at_location_without_lockcode(location);
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

	// FSV receiving

	@When("^I enter the location$")
	public void i_enter_the_location() throws FindFailed, InterruptedException {
		System.out.println("jsjclklnjckhkhlcndklc");
		System.out.println(context.getLocation());
		purchaseOrderReceivingPage.enterLocation(context.getLocation());
	}

	@When("^I enter tag id$")
	public void i_enter_tag_id() throws FindFailed, InterruptedException {
		
		purchaseOrderReceivingPage.eraseTagContent();
		String tagId = Utilities.getTenDigitRandomNumber() + Utilities.getTenDigitRandomNumber();
		context.setTagId(tagId);
		purchaseOrderReceivingPage.entertagId(tagId);
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

	@When("^I receive all skus for the FSV sampling purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_FSV_sampling_purchase_order_at_location(String location) throws Throwable {
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
			context.setUpiId(context.getPalletIDList().get(i - 1));
			i_enter_belCode(context.getBelCodeList().get(i - 1));
			puttyFunctionsPage.pressEnter();
			Thread.sleep(4000);
			Assert.assertFalse("No Valid Pre-Advice found", purchaseOrderReceivingPage.isNoValidPreAdviceFound());
			i_enter_the_location();
			puttyFunctionsPage.pressEnter();
			Thread.sleep(3000);
			i_enter_urn_id();
			jdaFooter.PressEnter();
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

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
			Assert.assertFalse("No Valid Pre-Advice found", purchaseOrderReceivingPage.isNoValidPreAdviceFound());
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

	@When("^I receive all skus for the FSV purchase order at location \"([^\"]*)\" for MEZZ putaway$")
	public void i_receive_all_skus_for_the_FSV_purchase_order_at_location_for_MEZZ_putaway(String location)
			throws Throwable {
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
			jdaFooter.PressEnter();
			Thread.sleep(2000);
			Assert.assertTrue("No Valid Pre-Advice found", purchaseOrderReceivingPage.isNoValidPreAdviceDisplayed());
			i_enter_the_location();
			jdaFooter.PressEnter();
			Thread.sleep(2000);
			i_enter_urn_id();
			jdaFooter.PressEnter();
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
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() - 1);
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			i_enter_urn_id(context.getUpiId());
			puttyFunctionsPage.pressEnter();
			the_tag_and_upc_details_should_be_displayed();
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			// i_enter_the_quantity(quantity);
			puttyFunctionsPage.pressEnter();
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
			// hooks.logoutPutty();
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
	
	@When("^I enter qty")
	public void i_enter_qty(String quantity) throws Throwable {
		// To navigate and enter the modified quantity
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.rightArrow();
		puttyFunctionsPage.backSpace();
		puttyFunctionsPage.backSpace();
		puttyFunctionsPage.backSpace();
		purchaseOrderReceivingPage.enterQuantity(quantity);
	}

	@When("^I blind receive the invalid upi$")
	public void i_blind_receive_the_invalid_upi() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
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

	public void the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line()
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
		int qtyDue = Integer.parseInt(uPIReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qtyDue);
		for (int i = 0; i < qtyDue; i++) {
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
		System.out.println("is location" + purchaseOrderReceivingPage.isLocationDisplayed());
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

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at(String type, String location) throws Throwable {

		// String preAdviceId = getTcData.getPo();
		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

		// context.setUpiId(upiId);
		// context.setPreAdviceId(preAdviceId);
		context.setLocation(location);

		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");

		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_receive_all_skus_for_the_purchase_order_at_location_for_hanging(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction_for_receiving();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\" with lock code damaged$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at_with_lock_code_damaged(String type,
			String location) throws Throwable {
		// String preAdviceId = getTcData.getPo();
		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

		// context.setUpiId(upiId);
		// context.setPreAdviceId(preAdviceId);
		context.setLocation(location);

		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");

		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_receive_all_skus_for_the_purchase_order_at_location_with_damaged(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction_for_receiving();
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

		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details("Released");

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

	@Given("^the UPI and ASN should be received at \"([^\"]*)\" for normal upc with perfect condition \"([^\"]*)\" and lockcode \"([^\"]*)\"$")
	public void the_UPI_and_ASN_should_be_received_at_for_normal_upc(String location, String condition, String lockcode)
			throws Throwable {
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		validate(lockcode);

		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details("Released");
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
		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");
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
			i_enter_urn_id(context.getUpiId());
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

	@Given("^the UPI and ASN of type \"([^\"]*)\" should be received at location \"([^\"]*)\" and \"([^\"]*)\" at site$")
	public void the_UPI_and_ASN_of_type_should_be_received_at_location_and_at_site(String type, String location,
			String condition) throws Throwable {
		String upiId = context.getUpiId();
		String asnId = context.getAsnId();
		String siteId = context.getSiteID();

		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setPerfectCondition(condition);
		context.setSiteID(siteId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details("Released");
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
		context.setSiteID(siteId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details("Released");
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		context.setLocation(location);
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
		context.setContainerId(uPIReceiptLineDB.getContainer(upiId));
		i_blind_receive_all_skus_for_the_purchase_order_returns_at_location(location);
		inventoryTransactionQueryStepDefs.the_inventory_transaction_should_be_updated();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as_for_blind_receive("Complete");
	}

	@Given("^the FSV PO of type \"([^\"]*)\" should be received at location \"([^\"]*)\" and site id$")
	public void the_FSV_PO_of_type_should_be_received_at_location_and_site_id(String type, String location)
			throws Throwable {

		String preAdviceId = getTcData.getPo();
		String siteId = context.getSiteID();

		preAdviceHeaderStepsDefs.the_FSV_PO_of_type_should_be_in_status_at_site_id(type, "Released");
		preAdviceLineStepDefs.the_FSV_PO_line_should_have_sku_quantity_due_details();
		preAdviceHeaderStepsDefs.the_PO_should_not_be_linked_with_UPI_line();
		preAdviceLineStepDefs.i_update_the_advice_id_for_all_line_items();
		i_receive_all_skus_for_the_FSV_purchase_order_at_location(location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received_for_fsv_po();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_fsv_PO_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_FSV_po_status_should_be_displayed_as("Complete");
	}

	@Given("^the UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status for multi sourced SKU$")
	public void the_UPI_and_ASN_should_be_in_status_for_multi_sourced_SKU(String upiId, String asnId, String status)
			throws Throwable {
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC_sourced_by_multi_supplier();

	}

	@When("^I perform \"([^\"]*)\" for all skus for the FSV purchase order at location \"([^\"]*)\"$")
	public void i_perform_for_all_skus_for_the_FSV_purchase_order_at_location(String receiveType, String location)
			throws Throwable {
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
			i_enter_pallet_id(context.getPalletIDList().get(i - 1));
			i_enter_belCode(context.getBelCodeList().get(i - 1));
			jdaFooter.PressEnter();
			i_enter_the_loctn();
			i_enter_the_quantity(quantity);
			i_enter_the_newpallet(context.enterNewPallet().get(i - 1));
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
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
	public void i_perform_for_all_skus_for_the_returns_order_at_location_with_perfect_condition_and_movement_label_enabled(
			String receiveType, String location, String condition) throws Throwable {

		context.setLocationID(location);
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
		for (int i = 0; i < context.getRcvQtyDue() + 1; i++) {
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity("1");
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			jdaFooter.navigateToNextScreen();
			// Press tab 7 times to navigate to movement label field
			for (int j = 0; j < 7; j++) {
				jdaFooter.pressTab();
			}
			purchaseOrderReceivingPage.enterMovementLabel(context.getUpiId());
			jdaFooter.PressEnter();
			jdaFooter.PressEnter();
			if (i < context.getRcvQtyDue()) {
				Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
						purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			} else {
				Assert.assertTrue("Over Receiving cant be done error not dispalyed for " + i,
						purchaseOrderReceivingPage.isOverReceiptErrorReturnsDisplayed());
			}
			jdaFooter.PressEnter();
			Thread.sleep(1000);
		}
		hooks.logoutPutty();
	}

	@When("^I perform \"([^\"]*)\" for all skus of the returns order for stock adjustment at location \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_perform_for_all_skus_for_the_returns_order_at_location_with_perfect_condition(String receiveType,
			String location, String condition) throws Throwable {

		context.setLocationID(location);
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
		for (int i = 0; i < context.getRcvQtyDue() + 1; i++) {
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

			if (i < context.getRcvQtyDue()) {
				Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + i,
						purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			} else {
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

	@When("^I receive all skus having different putaway group for the FSV purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_skus_having_different_putaway_group_for_the_FSV_purchase_order_at_location(
			String location) throws Throwable {
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
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (!purchaseOrderReceivingPage.getPallet().split("_").equals(null)) {
				String urn = null;
				String[] rcvPutSplit = purchaseOrderReceivingPage.getPallet().split("_");

				if (rcvPutSplit[0].contains("MEZF")) {
					urn = "M2Z01" + Utilities.getSevenDigitRandomNumber();
				} else if (rcvPutSplit[0].contains("RA")) {
					urn = "RA" + Utilities.getFourDigitRandomNumber();
				} else {
					urn = rcvPutSplit[0].substring(1, 2) + Utilities.getSevenDigitRandomNumber();
				}
				purchaseOrderReceivingPage.enterURNID(urn);
				puttyFunctionsPage.pressEnter();
				context.setPalletID(urn);
			}

			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@Given("^the UPI with \"([^\"]*)\" skus and ASN should be in \"([^\"]*)\" status for multi sourced SKU$")
	public void the_UPI_and_ASN_should_be_in_status_for_multi_sourced_SKU(String status) throws Throwable {

		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		context.setUpiId(upiId);
		context.setAsnId(asnId);
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(status);
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC_sourced_by_multi_supplier();

	}
	@Given("^the \"([^\"]*)\" PO and UPI and ASN and order should be in Released status$")
	public void the_UPI_and_ASN_and_order_should_be_in_status(String Modularity) throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		String status = "Released";
		String upiId =context.getUpiId();
		String asnId = context.getAsnId();
		String orderId = context.getOrderId();
		String preAdviceId = context.getPreAdviceId();
		
		System.out.println("upiId :"+upiId+" asnId:"+asnId+"orderId :"+orderId+" preAdviceId:"+preAdviceId);
		// ArrayList failureList = new ArrayList();
		// ArrayList skuFromUPI = new ArrayList();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();
		skuFromUPI = uPIReceiptLineDB.getSkuIdList(context.getUpiId());
		context.setSkuList(skuFromUPI);
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) skuFromUPI.get(i - 1));
						skuDb.getDatatype(context.getSkuId());
			
			
			// ArrayList failureList = new ArrayList();
			verification.verifyData("UPI Status", status, uPIReceiptHeaderDB.getStatus(upiId), failureList);
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
			verification.verifyData("Order Status", status, orderHeaderDB.getStatus(orderId), failureList);
			verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);

			int numLines = Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(upiId));
			Assert.assertEquals("No of Lines in PO and UPI Header do not match",
					uPIReceiptHeaderDB.getNumberOfLines(upiId), String.valueOf(numLines));
			context.setNoOfLines(numLines);
			Assert.assertTrue("UPI header , Delivery details,Order details not displayed as expected. ["
					+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());

		}
	}

	@When("^I receive all \"([^\"]*)\" skus for the purchase order at location \"([^\"]*)\"$")

	public void i_receive_all_skus_for_the_purchase_order_at_location(String type, String location) throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		// context.setLocationID(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		System.out.println("gdhgfsgh" + context.getSKUType());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();

		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			System.out.println("TYPE" + type);
			if (type.equalsIgnoreCase("Boxed") || type.equalsIgnoreCase("Flatpack") || type.equalsIgnoreCase("GOH")) {
				System.out.println("entered for GOH");
				i_enter_urn_id(context.getUpiId());
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_receiving();
			} else if (type.equalsIgnoreCase("Hanging")) {
				System.out.println("entered for Hang");
				i_enter_urn_id(context.getUpiId());
				puttyFunctionsPage.nextScreen();
				i_enter_asn(context.getAsnId());
				i_enter_hanging_value();
				i_enter_trl();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
			} else if (type.equalsIgnoreCase("GOH")) {
				i_enter_urn_id(context.getUpiId());
				puttyFunctionsPage.nextScreen();
				i_enter_asn(context.getAsnId());
				i_enter_hanging_value();
				i_enter_trl();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
			} else if (type.equalsIgnoreCase("Flatpack")) {
				i_enter_urn_id(context.getUpiId());
				puttyFunctionsPage.nextScreen();
				i_enter_asn(context.getAsnId());
				i_enter_hanging_value();
				i_enter_trl();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
			}
			System.out.println("NONE");
			i_enter_the_location();
			jdaFooter.PressEnter();
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (null != context.getLockCode()) {
				i_enter_urn_id_for_locked_sku();
			} else {
				i_enter_urn_id();
			}
			jdaFooter.PressEnter();
			Thread.sleep(2000);

			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@When("^the tag and upc details should be displayed for hanging sku$")
	public void the_tag_and_upc_details_should_be_displayed_for_hanging_sku() throws Throwable {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
				purchaseOrderReceivingPage.isLocationDisplayed());

		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
		String packConfig = packConfigSplit[0];
		// verification.verifyData("Pack Config", context.getPackConfig(),
		// packConfig, failureList);

		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
				failureList);

		// purchaseOrderReceivingPage.enterLocation(context.getLocationID());

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceive().split("_");
		String qtyToRcv = qtySplit[0];
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue()), qtyToRcv, failureList);

		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
		String upc = upcSplit[0];
		context.setUPC(upc);
		jdaFooter.pressTab();
		i_enter_tag_id_for_hanging();
		puttyFunctionsPage.upArrow();
		puttyFunctionsPage.upArrow();
		
		Assert.assertTrue(
				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the tag and upc details should be displayed for receiving$")
	public void the_tag_and_upc_details_should_be_displayed_for_receiving()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		ArrayList failureList = new ArrayList();
		// Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
		// purchaseOrderReceivingPage.isLocationDisplayed());

		// context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(),
		// "Receipt"));
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

	@When("^I enter asn$")
	public void i_enter_asn(String asn) throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterAsn(asn);
	}

	@When("^I enter hanging value$")
	public void i_enter_hanging_value() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterHangingValue();
	}

	@When("^I enter trl$")
	public void i_enter_trl() throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		String trlID = null;
		// First 2 digits - prefix check digit
		String checkDigit = "02";
		// 5 digit supplier Id
		String supplierId = supplierSkuDB.getSupplierId(context.getSkuId());
		String supplier = supplierSkuDB.getSupplierId(context.getSkuId()).replace("M", "");
		// UPC 8 digit
		String upc = supplierSkuDB.getSupplierSKU(context.getSkuId(), supplierId);
		// Qty : 4 digit

		String qty = "0";
		for (int i = 0; i < (4 - String.valueOf(context.getRcvQtyDue()).length() - 1); i++) {
			System.out.println("inside if");
			System.out.println("Qty" + qty);
			qty = qty + "0";
		}
		qty = qty + (String.valueOf(context.getRcvQtyDue()));

		System.out.println("Qty" + qty);

		// suffix check digit : 1 digit
		String suffix = "9";

		trlID = checkDigit + supplier + upc + qty + suffix;
		purchaseOrderReceivingPage.enterTrlId(trlID);
	}

	@When("^the PO of type \"([^\"]*)\" should be received at location \"([^\"]*)\"$")
	public void the_po_of_type_should_be_received_at_location(String type, String location) throws Throwable {
		i_receive_all_skus_for_the_purchase_order_at_location(type, location);

		if (type.contains("Hanging")) {
			inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received_of_hanging_type();
			inventoryTransactionQueryStepDefs
					.the_goods_receipt_should_be_generated_for_hanging_received_stock_in_inventory_transaction();
		} else {
			inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
			inventoryTransactionQueryStepDefs
					.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		}

		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@When("^I perform \"([^\"]*)\" for all \"([^\"]*)\" skus at location \"([^\"]*)\"$")
	public void i_perform_for_all_skus_at_location(String receiveType, String skuType, String location)
			throws Throwable {
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

			if (skuType.equalsIgnoreCase("Boxed")) {
				i_enter_urn_id(context.getUpiId());
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_receiving();
			} else if (skuType.equalsIgnoreCase("Hanging")) {
				i_enter_urn_id(context.getUpiId());
				puttyFunctionsPage.nextScreen();
				i_enter_asn(context.getAsnId());
				i_enter_hanging_value();
				i_enter_trl();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
			}

			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			jdaFooter.PressEnter();
			// i_enter_the_quantity(quantity);
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
		}
	}

	@When("^the tag and upc details should be displayed for receiving after adding stock$")
	public void the_tag_and_upc_details_should_be_displayed_for_receiving_after_adding_stock()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		ArrayList failureList = new ArrayList();
		// Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
		// purchaseOrderReceivingPage.isLocationDisplayed());

		// context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(),
		// "Receipt"));
		String[] tagSplit = purchaseOrderReceivingPage.getTagId().split("_");
		String tagID = tagSplit[0];

		verification.verifyData("Tag ID", context.getUpiId(), tagID, failureList);

		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
		String packConfig = packConfigSplit[0];
		verification.verifyData("Pack Config", context.getPackConfig(), packConfig, failureList);

		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
				failureList);

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceiveAfterStockAdd().split("_");
		String qtyToRcv = qtySplit[0];
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue() + 5), qtyToRcv, failureList);

		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
		String upc = upcSplit[0];
		context.setUPC(upc);
		Assert.assertTrue(
				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\" for hazardous putaway$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at_for_hazardous_putaway(String type,
			String location) throws Throwable {

		String preAdviceId = getTcData.getPo();
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		context.setLocation(location);
		context.setUpiId(upiId);
		context.setPreAdviceId(preAdviceId);
		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");
		preAdviceLineStepDefs.the_PO_should_have_hazardous_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		i_receive_all_hazardous_skus_for_the_purchase_order_at_location(type, location);
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}

	@When("^I receive all \"([^\"]*)\" hazardous skus for the purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_hazardous_skus_for_the_purchase_order_at_location(String type, String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		context.setLocationID(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		System.out.println("gdhgfsgh" + context.getSKUType());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			i_enter_urn_id(context.getUpiId());
			jdaFooter.PressEnter();
			the_tag_and_upc_details_should_be_displayed_for_receiving();
			i_enter_the_location();
			jdaFooter.PressEnter();
			Thread.sleep(2000);
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@When("^I enter random tag$")
	public void i_enter_random_tag() throws FindFailed, InterruptedException {
		context.setTagId(Utilities.getFourDigitRandomNumber());
		purchaseOrderReceivingPage.entertagId(context.getTagId());
	}

	@When("^the tag and upc details should be displayed for hanging$")
	public void the_tag_and_upc_details_should_be_displayed_for_hanging() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
				purchaseOrderReceivingPage.isLocationDisplayed());

		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
		String packConfig = packConfigSplit[0];
		// verification.verifyData("Pack Config", context.getPackConfig(),
		// packConfig, failureList);

		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
				failureList);

		// purchaseOrderReceivingPage.enterLocation(context.getLocationID());

		System.out.println(context.getRcvQtyDue());
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

	@Given("^the multiple UPI of type \"([^\"]*)\" and ASN should be in \"([^\"]*)\" status with partset$")
	public void the_multiple_UPI_of_type_and_ASN_should_be_in_status_with_partset(String dataType, String status)
			throws Throwable {

		String upiId = getTcData.getUpi();
		System.out.println("upiId " + upiId);
		String upiId2 = getTcData.getUpi2();
		System.out.println("upiId2 " + upiId2);
		String asnId = getTcData.getAsn();
		String upi = upiId + "," + upiId2;
		context.setUpiId(upi);

		context.setStatus(status);
		System.out.println("UPI - Multiple " + upi);
		System.out.println("asnId" + asnId);
		context.setAsnId(asnId);
		context.setSKUType(dataType);
		preAdviceHeaderStepsDefs.the_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details();
		the_multiple_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_upadted_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		Map<String, Integer> upiNumLines = new HashMap<String, Integer>();
		int numLines = 0;
		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiNumLines.put(context.getUpiList().get(i),
					Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
			numLines += Integer.parseInt(uPIReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
		}
		context.setUpiNumLinesMap(upiNumLines);
		context.setNoOfLines(numLines);
	}

	@Given("^I receive all skus of multiple upi for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\" and partset$")
	public void i_receive_all_skus_of_multiple_upi_for_the_returns_order_at_with_perfect_condition_and_partset(
			String location, String condition) throws Throwable {
		context.setLocationID(location);
		context.setPerfectCondition(condition);
		upiReceiptLineStepDefs.fetch_Qty_Details();
		i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode_for_partset(
				location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");
	}

	@When("^I blind receive all skus of multiple upi for the returns order at location \"([^\"]*)\" without lockcode for partset$")
	public void i_blind_receive_all_skus_of_multiple_upi_for_the_returns_order_at_location_without_lockcode_for_partset(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_of_multiple_upi_and_perform_blind_receive_without_lockcode_with_partset();
		hooks.logoutPutty();
	}

	@When("^I enter details of multiple upi and perform blind receive without lockcode with partset$")
	public void i_enter_details_of_multiple_upi_and_perform_blind_receive_without_lockcode_with_partset()
			throws Throwable {
		int m = 0;
		for (int k = 0; k < context.getUpiList().size(); k++) {
			context.setUpiId(context.getUpiList().get(k));
			for (int j = 0; j < context.getUpiNumLinesMap().get(context.getUpiList().get(k)); j++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));

				for (int i = 0; i < Integer.parseInt(context.getMultipleUPIMap().get(context.getUpiId())
						.get(context.getSkuId()).get("QTY DUE")); i++) {
					purchaseOrderReceivingPage.enterURNID(context.getUpiId());
					purchaseOrderReceivingPage.enterUPC1BEL(
							context.getMultipleUPIMap().get(context.getUpiId()).get(context.getSkuId()).get("UPC"));
					jdaFooter.pressTab();
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterQuantity("1");
					jdaFooter.pressTab();
					purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
					purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
					jdaFooter.pressTab();
					jdaFooter.navigateToNextScreen();
					purchaseOrderReceivingPage.enterSupplierId(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("SUPPLIER ID"));
					purchaseOrderReceivingPage.enterPartset(context.getMultipleUPIMap().get(context.getUpiId())
							.get(context.getSkuId()).get("PART SET"));
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

	@When("^I perform receiving for all skus at location for x dock$")
	public void i_perform_receiving_for_all_skus_at_location() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		// context.setLocation(location);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingPage.selectReceiveMenu();
		purchaseOrderReceivingPage.selectGroupReceive();
		purchaseOrderReceivingPage.selectUpiReceive();
		i_enter_urn_id_for_xdock();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		Thread.sleep(180000);

		if (!purchaseOrderReceivingPage.isReciveCmpEntryDisplayed()) {
			failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
			context.setFailureList(failureList);
		}

		hooks.logoutPutty();

	}
	@When("^I enter urn id for xdock$")
	private void i_enter_urn_id_for_xdock() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterURNID("5885121529080280317");}

		@When("^I receive all skus for the multiple purchase order with multiple upi after adding stock at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_multiple_purchase_order_with_multiple_upi_after_adding_stock_at_location(
			String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);

		upiMap = context.getUPIMap();
		multipleUpiMap = context.getMultipleUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();
		int m = 0;
		for (int j = 0; j < context.getUpiList().size(); j++) {
			context.setUpiId(context.getUpiList().get(j));
			for (int i = 1; i <= (context.getUpiNumLinesMap().get(context.getUpiId())); i++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));
				context.setSupplierID(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("SUPPLIER ID"));
				context.setPackConfig(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("PACK CONFIG"));
				context.setRcvQtyDue(Integer.parseInt(
						multipleUpiMap.get(context.getUpiList().get(j)).get(context.getSkuId()).get("QTY DUE")));
				Thread.sleep(1000);
				System.out.println("RECEIVING" + context.getUpiId());
				System.out.println(context.getSkuId());
				System.out.println("MMM" + m);
				System.out.println("JJJJ" + j);
				System.out.println("111111111110000000000");
				if (context.getSKUType().equalsIgnoreCase("Boxed") || context.getSKUType().equalsIgnoreCase("Flatpack")
						|| context.getSKUType().equalsIgnoreCase("GOH")) {
					System.out.println("entered for GOH");
					i_enter_urn_id(context.getUpiId());
					jdaFooter.PressEnter();
					if (j == 0) {
						the_tag_and_upc_details_should_be_displayed_for_receiving_after_adding_stock();
					} else {
						the_tag_and_upc_details_should_be_displayed_for_receiving();
					}
				} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
					System.out.println("entered for Hang");
					i_enter_urn_id(context.getUpiId());
					puttyFunctionsPage.nextScreen();
					i_enter_asn(uPIReceiptHeaderDB.getAsnId(context.getUpiId()));
					i_enter_hanging_value();
					i_enter_trl();
					jdaFooter.PressEnter();
					if (j == 0) {
						the_tag_and_upc_details_should_be_displayed_for_hanging_sku_after_adding_stock();
					} else {
						the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
					}

				}
				System.out.println("NONE");
				i_enter_the_location();
				jdaFooter.PressEnter();
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (null != context.getLockCode()) {
					i_enter_urn_id_for_locked_sku();
				} else {
					i_enter_urn_id();
				}
				jdaFooter.PressEnter();
				Thread.sleep(2000);

				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
					context.setFailureList(failureList);
				}
			}
		}
		hooks.logoutPutty();
	}

	@When("^the tag and upc details should be displayed for hanging sku after adding stock$")
	public void the_tag_and_upc_details_should_be_displayed_for_hanging_sku_after_adding_stock()
			throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
				purchaseOrderReceivingPage.isLocationDisplayed());

		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
		String packConfig = packConfigSplit[0];
		// verification.verifyData("Pack Config", context.getPackConfig(),
		// packConfig, failureList);

		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
				failureList);

		// purchaseOrderReceivingPage.enterLocation(context.getLocationID());

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceiveAfterStockAdd().split("_");
		String qtyToRcv = qtySplit[0];
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue() + 5), qtyToRcv, failureList);

		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
		String upc = upcSplit[0];
		context.setUPC(upc);
		Assert.assertTrue(
				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I receive all skus for the purchase order of type \"([^\"]*)\" at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_purchase_order_of_type_at_location(String dataType, String location)
			throws Throwable {
		context.setSKUType(dataType);
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		context.setLocationID(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String date = DateUtils.getCurrentSystemDateInDBFormat();
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			// context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(),
			// "Receipt", date));
			System.out.println("chk" + context.getRcvQtyDue());
			i_enter_urn_id(context.getUpiId());
			puttyFunctionsPage.nextScreen();
			i_enter_asn(context.getAsnId());
			i_enter_hanging_value();
			i_enter_trl();
			jdaFooter.PressEnter();
			the_tag_and_upc_details_should_be_displayed_for_hanging();
			i_enter_the_location();
			jdaFooter.pressTab();

			i_enter_random_tag();
			puttyFunctionsPage.pressEnter();
			Assert.assertTrue("Rcv Pallet Entry Page not displayed",
					purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
			if (null != context.getLockCode()) {
				i_enter_urn_id_for_locked_sku();
			} else {
				i_enter_urn_id();
			}
			jdaFooter.PressEnter();
			Thread.sleep(2000);
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
			context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(), "Receipt"));

			hooks.logoutPutty();
		}
	}
	
	@When("^I enter Tag Id for hanging$")
	public void i_enter_tag_id_for_hanging() throws Throwable {
		
		String tagId = Utilities.getSixDigitRandomNumber();
		while(inventoryDb.isTagExists(tagId))
		{
			tagId = Utilities.getSixDigitRandomNumber();
		}
		context.setTagId(tagId);
		purchaseOrderReceivingPage.entertagId(tagId);
		
	
	}
	
	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\" for maximum aisle$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at_for_maximum_aisle(String type, String location)
			throws Throwable {

		String preAdviceId = getTcData.getPo();
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		context.setUpiId(upiId);
		context.setPreAdviceId(preAdviceId);
		context.setAsnId(asnId);

		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");
		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
		i_perform_for_all_skus_at_location_for_maximum_aisle("Under Receiving","REC001");
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_received_in_split();
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_split_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_po_status_should_be_displayed_as("Complete");
	}
	
	@When("^I perform \"([^\"]*)\" for all skus at location \"([^\"]*)\" for maximum aisle$")
	public void i_perform_for_all_skus_at_location_for_maximum_aisle(String receiveType, String location) throws Throwable {
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
			context.setQtyOnHand(context.getRcvQtyDue());
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue());
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue()/120);
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue()%120);
			if(context.getQtyOnHand()>120)
			{
		for(int k=0;k<context.getQtyOnHand()/120;k++)
		{
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf("120");
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			if (context.getSKUType().equalsIgnoreCase("Boxed") || context.getSKUType().equalsIgnoreCase("Flatpack")
					|| context.getSKUType().equalsIgnoreCase("GOH")) {
				System.out.println("entered for GOH");
				i_enter_urn_id(context.getUpiId());
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check();
			} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
				System.out.println("entered for Hang");
				i_enter_urn_id(context.getUpiId());
				puttyFunctionsPage.nextScreen();
				i_enter_asn(uPIReceiptHeaderDB.getAsnId(context.getUpiId()));
				i_enter_hanging_value();
				i_enter_trl();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check();
				//the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
			}
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			// i_enter_the_quantity(quantity);
			puttyFunctionsPage.pressEnter();
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
			// hooks.logoutPutty();
		}
		
		System.out.println("COMES TO IF Part");
		System.out.println(context.getQtyOnHand());
		System.out.println(context.getQtyOnHand()%120);
		System.out.println((context.getQtyOnHand()%120)!=0);
		System.out.println((context.getQtyOnHand()%120)*120);
		if((context.getQtyOnHand()%120)!=0)
		{
			System.out.println("inside Loop after for");
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				System.out.println("REM Quant"+String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()%120)*120)));
				quantity = String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()/120)*120));
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			if (context.getSKUType().equalsIgnoreCase("Boxed") || context.getSKUType().equalsIgnoreCase("Flatpack")
					|| context.getSKUType().equalsIgnoreCase("GOH")) {
				System.out.println("entered for GOH");
				i_enter_urn_id(context.getUpiId());
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check();
			} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
				System.out.println("entered for Hang");
				i_enter_urn_id(context.getUpiId());
				puttyFunctionsPage.nextScreen();
				i_enter_asn(uPIReceiptHeaderDB.getAsnId(context.getUpiId()));
				i_enter_hanging_value();
				i_enter_trl();
				jdaFooter.PressEnter();
				the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check();
				//the_tag_and_upc_details_should_be_displayed_for_hanging_sku();
			}
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			// i_enter_the_quantity(quantity);
			puttyFunctionsPage.pressEnter();
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
		}
		
			}
		}
		hooks.logoutPutty();
	}
	
	@When("^the tag and upc details should be displayed for maximum aisle check$")
	public void the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		System.out.println("is location" + purchaseOrderReceivingPage.isLocationDisplayed());
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

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceiveUnderReceiving().split("_");
		String qtyToRcv = qtySplit[0];
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue()), qtyToRcv, failureList);

		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
		String upc = upcSplit[0];
		context.setUPC(upc);
		Assert.assertTrue(
				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^the FSV PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\" for maximum aisle$")
	public void the_fsv_PO_of_type_with_UPI_and_ASN_should_be_received_at_for_maximum_aisle(String type, String location)
			throws Throwable {

		preAdviceHeaderStepsDefs
				.the_FSV_PO_of_type_should_be_in_status_at_site_id(type, "Released");
		preAdviceLineStepDefs.the_FSV_PO_line_should_have_sku_quantity_due_details();
		preAdviceLineStepDefs.i_update_the_advice_id_for_all_line_items();
		preAdviceHeaderStepsDefs.the_PO_should_not_be_linked_with_UPI_line();
		context.setLocation(location);
		i_perform_for_all_skus_for_the_FSV_purchase_order_at_location_for_maximum_aisle("Under Receiving","REC001");
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_split_received_for_fsv_po();
		inventoryTransactionQueryStepDefs.the_goods_receipt_should_be_generated_for_fsv_PO_split_received_stock_in_inventory_transaction();
		
		preAdviceHeaderStepsDefs.the_FSV_po_status_should_be_displayed_as("Complete");
	}
	
	@When("^I perform \"([^\"]*)\" for all skus for the FSV purchase order at location \"([^\"]*)\" for maximum aisle$")
	public void i_perform_for_all_skus_for_the_FSV_purchase_order_at_location_for_maximum_aisle(String receiveType, String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		context.setReceiveType(receiveType);
		poMap = context.getPOMap();
		String quantity = null;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_pre_advice_receiving();
		i_should_be_directed_to_pre_advice_entry_page();
		System.out.println("INIT VALUES"+context.getPalletIDList());
		System.out.println(context.getBelCodeList());
		System.out.println(context.getBelCodeList());

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			context.setQtyOnHand(context.getRcvQtyDue());
			if(context.getQtyOnHand()>120)
			{
		for(int k=0;k<context.getQtyOnHand()/120;k++)
		{
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf("120");
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			System.out.println("VALUES"+context.getPalletIDList().get(i - 1));
			System.out.println(context.getBelCodeList().get(i - 1));
			System.out.println(context.enterNewPallet().get(i - 1));
			
			i_enter_pallet_id(context.getPalletIDList().get(i - 1));
			i_enter_belCode(context.getBelCodeList().get(i - 1));
			jdaFooter.PressEnter();
			i_enter_the_loctn();
			jdaFooter.pressTab();
			i_enter_tag_id();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			i_enter_qty(quantity);
			jdaFooter.PressEnter();
			i_enter_the_newpallet(context.enterNewPallet().get(i - 1));
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
					context.setFailureList(failureList);
				}
			}
		}
		System.out.println("COMES TO IF Part");
		System.out.println(context.getQtyOnHand());
		System.out.println(context.getQtyOnHand()%120);
		System.out.println((context.getQtyOnHand()%120)!=0);
		System.out.println((context.getQtyOnHand()%120)*120);
		if((context.getQtyOnHand()%120)!=0)
		{
			System.out.println("inside Loop after for");
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				System.out.println("REM Quant"+String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()%120)*120)));
				quantity = String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()/120)*120));
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			i_enter_pallet_id(context.getPalletIDList().get(i - 1));
			i_enter_belCode(context.getBelCodeList().get(i - 1));
			jdaFooter.PressEnter();
			i_enter_the_loctn();
			jdaFooter.pressTab();
			i_enter_tag_id();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			i_enter_qty(quantity);
			jdaFooter.PressEnter();
			i_enter_the_newpallet(context.enterNewPallet().get(i - 1));
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
					context.setFailureList(failureList);
				}
			
			
			}
		}
			}
		}
		hooks.logoutPutty();
	}
	
	@Given("^the IDT PO of type \"([^\"]*)\" with UPI and ASN should be received at \"([^\"]*)\" for maximum aisle$")
	public void the_idt_PO_of_type_with_UPI_and_ASN_should_be_received_at_for_maximum_aisle(String type, String location)
			throws Throwable {


		upiReceiptHeaderStepDefs
				.the_UPI_of_type_and_ASN_should_be_in_status_for_IDT(type, "Released");
		upiReceiptHeaderStepDefs.asn_and_container_to_be_linked_with_upi_header();
		upiReceiptLineStepDefs.the_UPI_should_have_sku_quantity_due_details();
		context.setLocation(location);
		i_perform_for_all_skus_at_location_for_IDT_for_maximum_aisle_check("Under Receiving","REC001");
		inventoryQueryStepDefs.the_inventory_should_be_displayed_for_all_tags_split_received_idt();
		inventoryTransactionQueryStepDefs.the_goods_receipt_should_be_generated_for_IDT_split_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_idt_status_should_be_displayed_as("Complete");
	}
	
	@When("^I perform \"([^\"]*)\" for all skus at location \"([^\"]*)\" for IDT maximum aisle$")
	public void i_perform_for_all_skus_at_location_for_idt_maximum_aisle(String receiveType, String location) throws Throwable {
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
			context.setQtyOnHand(context.getRcvQtyDue());
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue());
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue()/120);
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue()%120);
			if(context.getQtyOnHand()>120)
			{
		for(int k=0;k<context.getQtyOnHand()/120;k++)
		{
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf("120");
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			i_enter_urn_id(context.getUpiId());
			puttyFunctionsPage.pressEnter();
			the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check();
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			// i_enter_the_quantity(quantity);
			puttyFunctionsPage.pressEnter();
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
			// hooks.logoutPutty();
		}
		
		System.out.println("COMES TO IF Part");
		System.out.println(context.getQtyOnHand());
		System.out.println(context.getQtyOnHand()%120);
		System.out.println((context.getQtyOnHand()%120)!=0);
		System.out.println((context.getQtyOnHand()%120)*120);
		if((context.getQtyOnHand()%120)!=0)
		{
			System.out.println("inside Loop after for");
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				System.out.println("REM Quant"+String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()%120)*120)));
				quantity = String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()/120)*120));
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
			i_enter_urn_id(context.getUpiId());
			puttyFunctionsPage.pressEnter();
			the_tag_and_upc_details_should_be_displayed_for_maximum_aisle_check();
			i_enter_the_location();
			puttyFunctionsPage.pressTab();
			i_enter_tag_id();
			// i_enter_the_quantity(quantity);
			puttyFunctionsPage.pressEnter();
			if (receiveType.equalsIgnoreCase("Under Receiving")) {
				i_enter_urn_id();
				puttyFunctionsPage.pressEnter();
			}
		}
		
			}
		}
		hooks.logoutPutty();
	}
	
	@When("^I perform \"([^\"]*)\" for all skus at location \"([^\"]*)\" for IDT for maximum aisle check$")
	public void i_perform_for_all_skus_at_location_for_IDT_for_maximum_aisle_check(String receiveType, String location) throws Throwable {
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
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			context.setUPC(supplierSkuDB.getSupplierSKU(context.getSkuId()));

			context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setSupplierID(upiMap.get(context.getSkuId()).get("SUPPLIER ID"));
			context.setQtyOnHand(context.getRcvQtyDue());
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue());
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue()/120);
			System.out.println("RECEIVE QTY DUE"+context.getRcvQtyDue()%120);
			if(context.getQtyOnHand()>120)
			{
		for(int k=0;k<context.getQtyOnHand()/120;k++)
		{
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 5);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				quantity = String.valueOf("120");
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
		System.out.println("COMES TO IF Part");
		System.out.println(context.getQtyOnHand());
		System.out.println(context.getQtyOnHand()%120);
		System.out.println((context.getQtyOnHand()%120)!=0);
		System.out.println((context.getQtyOnHand()%120)*120);
		if((context.getQtyOnHand()%120)!=0)
		{
			System.out.println("inside Loop after for");
			if (receiveType.equalsIgnoreCase("Over Receiving")) {
				quantity = String.valueOf(context.getRcvQtyDue() + 1);
			} else if (receiveType.equalsIgnoreCase("Under Receiving")) {
				System.out.println("REM Quant"+String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()%120)*120)));
				quantity = String.valueOf(context.getQtyOnHand()-((context.getQtyOnHand()/120)*120));
			}
			context.setRcvQtyDue(Integer.valueOf(quantity));
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
			}
		}
			
		context.setFailureList(failureList);
		hooks.logoutPutty();
	}
	
	@Given("^the UPI of type \"([^\"]*)\" and ASN should be received at \"([^\"]*)\" for maximum aisle$")
	public void the_upi_of_type_and_ASN_should_be_received_at_for_maximum_aisle(String type, String location)
			throws Throwable {
//		String upiId = context.getUpiId();
//		String asnId = context.getAsnId();
//		String siteId = context.getSiteID();
		
		String upiId = "56490000532760246410022051700100";
		String asnId = "0000001234";
		String siteId = "5649";


		context.setUpiId(upiId);
		context.setLocationID(location);
		context.setAsnId(asnId);
		context.setSiteID(siteId);
		context.setSKUType(type);
		
		preAdviceHeaderStepsDefs.the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details("Released");
		the_pallet_count_should_be_updated_in_delivery_asn_userdefnote1_to_be_updated_in_upi_header_and_userdefnote2_containerid_to_be_upadted_in_upi_line();
		preAdviceLineStepDefs.the_upi_should_have_sku_quantity_due_details();
		context.setLocation(location);
		upiReceiptLineStepDefs.i_fetch_supplier_id_UPC();
		context.setContainerId(uPIReceiptLineDB.getContainer(upiId));
		context.setLocation(location);
		
		i_split_receive_all_skus_for_the_returns_order_at_with_perfect_condition("REC002","Y");
		inventoryTransactionQueryStepDefs.the_goods_receipt_should_be_generated_for_IDT_split_received_stock_in_inventory_transaction();
		preAdviceHeaderStepsDefs.the_idt_status_should_be_displayed_as("Complete");
	}
	
	@When("^I split blind receive all skus for the purchase order at location \"([^\"]*)\" without lockcode$")
	public void i_split_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode(String location)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_receive_the_po_with_basic_and_blind_receiving();
		i_should_be_directed_to_blind_entry_page();
		i_enter_details_and_perform_split_blind_receive_without_lockcode();
		hooks.logoutPutty();
	}
	
	@When("^I enter details and perform split blind receive without lockcode$")
	public void i_enter_details_and_perform_split_blind_receive_without_lockcode() throws Throwable {
		for (int j = 0; j < context.getNoOfLines(); j++) {
			context.setSupplierID(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("SUPPLIER ID"));
			context.setUPC(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("UPC"));
			context.setRcvQtyDue(
					Integer.parseInt(context.getUPIMap().get(context.getSkuFromUPI().get(j)).get("QTY DUE")));
			context.setQtyOnHand(context.getRcvQtyDue());
			if(context.getQtyOnHand()>120)
			{
		for(int k=0;k<context.getQtyOnHand()/120;k++)
		{
				purchaseOrderReceivingPage.enterURNID(context.getUpiId());
				purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
				jdaFooter.pressTab();
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterQuantity("120");
				jdaFooter.pressTab();
				purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
				purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
				jdaFooter.pressTab();
				puttyFunctionsPage.nextScreen();
				purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
				jdaFooter.PressEnter();
				Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " + k,
						purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
				jdaFooter.PressEnter();
				Thread.sleep(1000);
			}
		if((context.getQtyOnHand()%120)!=0)
		{
			purchaseOrderReceivingPage.enterURNID(context.getUpiId());
			purchaseOrderReceivingPage.enterUPC1BEL(context.getUPC());
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterQuantity(String.valueOf(context.getQtyOnHand()%120));
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterPerfectCondition(context.getPerfectCondition());
			purchaseOrderReceivingPage.enterLocationInBlindReceive(context.getLocation());
			jdaFooter.pressTab();
			purchaseOrderReceivingPage.enterSupplierId(context.getSupplierID());
			jdaFooter.PressEnter();
			Assert.assertTrue("Blind Receiving Unsuccessfull while receiving quantity " ,
					purchaseOrderReceivingPage.isBlindReceivingDoneWithoutLockCode());
			jdaFooter.PressEnter();
			Thread.sleep(1000);
		}
		}
		}
	}
	
	@Given("^I split receive all skus for the returns order at \"([^\"]*)\" with perfect condition \"([^\"]*)\"$")
	public void i_split_receive_all_skus_for_the_returns_order_at_with_perfect_condition(String location, String condition)
			throws Throwable {
		context.setLocationID(location);
		context.setLocation(location);
		context.setPerfectCondition(condition);
		i_split_blind_receive_all_skus_for_the_returns_order_at_location_without_lockcode(location);
		upiReceiptHeaderStepDefs.the_pallet_and_asn_status_should_be_displayed_as("Complete");

	}

}
