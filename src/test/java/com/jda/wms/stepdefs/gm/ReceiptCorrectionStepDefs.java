package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;
import com.jda.wms.stepdefs.rdt.PurchaseOrderPutawayStepDefs;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.stepdefs.gm.InventoryTransactionQueryStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class ReceiptCorrectionStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private InventoryQueryPage inventoryQueryPage;
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private DeliveryStepDefs deliveryStepDefs;
	private UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs;
	private UPIReceiptLineStepDefs upiReceiptLineStepDefs;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private Hooks hooks;
	private LocationDB locationDB;
	private InventoryTransactionDB inventoryTransactionDB;
	private PurchaseOrderPutawayStepDefs purchaseOrderPutawayStepDefs;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private JDAHomeStepDefs JDAHomeStepDefs;
	private StockAdjustmentStepDefs stockAdjustmentStepDefs;
	private GetTcData getTcData;

	@Inject
	public ReceiptCorrectionStepDefs(Context context, Verification verification,
			InventoryDB inventoryDB, JdaLoginPage jdaLoginPage, JDAFooter jDAFooter,
			InventoryQueryPage inventoryQueryPage, PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs, DeliveryStepDefs deliveryStepDefs,
			UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, UPIReceiptLineStepDefs upiReceiptLineStepDefs,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, PurchaseOrderPutawayPage purchaseOrderPutawayPage,
			Hooks hooks, LocationDB locationDB, PurchaseOrderPutawayStepDefs purchaseOrderPutawayStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs, JDAHomeStepDefs JDAHomeStepDefs,
			StockAdjustmentStepDefs stockAdjustmentStepDefs, InventoryTransactionDB inventoryTransactionDB,GetTcData getTcData) {
		this.context = context;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.inventoryQueryStepDefs = inventoryQueryStepDefs;
		this.inventoryTransactionQueryStepDefs = inventoryTransactionQueryStepDefs;
		this.deliveryStepDefs = deliveryStepDefs;
		this.upiReceiptHeaderStepDefs = upiReceiptHeaderStepDefs;
		this.upiReceiptLineStepDefs = upiReceiptLineStepDefs;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.hooks = hooks;
		this.locationDB = locationDB;
		this.purchaseOrderPutawayStepDefs = purchaseOrderPutawayStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.JDAHomeStepDefs = JDAHomeStepDefs;
		this.stockAdjustmentStepDefs = stockAdjustmentStepDefs;
		this.getTcData = getTcData;
	}

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be received at location \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_received_at_location(String type,String location) throws Throwable {
//		String preAdviceId = getTcData.getPo();
//		String upiId = getTcData.getUpi();
//		String asnId = getTcData.getAsn();
//		
//		context.setPreAdviceId(preAdviceId);
//		context.setUpiId(upiId);
//		context.setAsnId(asnId);
		
		preAdviceHeaderStepsDefs.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type,"Released");
		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		purchaseOrderReceivingStepDefs
				.the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		context.setLocation(location);
	}

	@When("^I do normal putaway for all tags received into putaway location$")
	public void i_do_normal_putaway_for_all_tags_received_into_putaway_location() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		boolean validatePutLoc=false;

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPutawayStepDefs.i_select_normal_putaway();
		purchaseOrderPutawayStepDefs.i_should_be_directed_to_putent_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			purchaseOrderPutawayStepDefs.i_enter_urn_id_in_putaway();
			purchaseOrderPutawayStepDefs.the_tag_details_for_putaway_should_be_displayed();
			
		do{
			context.setToLocation(inventoryDB.getPutawayLocation(context.getSkuId(), context.getLocation()));
			purchaseOrderPutawayStepDefs.i_enter_to_location(context.getToLocation());
		         if(purchaseOrderPutawayPage.isChkToDisplayed()){
			purchaseOrderPutawayStepDefs.i_enter_the_check_string();
			validatePutLoc=true;
		          }
		}while(!validatePutLoc);
		
			if (!purchaseOrderPutawayPage.isPutEntDisplayed()) {
				failureList.add("Putaway not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@Then("^the inventory should be displayed for all putaway tags received$")
	public void the_inventory_should_be_displayed_for_all_putaway_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		String getQtyonHand;
		String recQty;
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after Putaway" + context.getSkuId(), context.getToLocation(),
					inventoryDB.getLocationAfterPutaway(context.getSkuId(), date), failureList);
			verification.verifyData("Qty on Hand for SKU" + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryDB.getQtyOnHand(context.getSkuId(), context.getToLocation(), context.getUpiId(), date),
					failureList);
			getQtyonHand = inventoryDB.getQtyOnHand(context.getSkuId(), context.getToLocation(), context.getUpiId(),
					date);
			recQty = String.valueOf(context.getRcvQtyDue());
			if (Integer.parseInt(recQty) > Integer.parseInt(getQtyonHand)) {
				Assert.assertTrue("Inventory details are not displayed as expected. ["
						+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
			}
		}

	}

	@Then("^the goods receipt ITL should be generated for putaway stock in inventory transaction$")
	public void the_goods_receipt_ITL_should_be_generated_for_putaway_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I do stock adjustments after putaway for receipt reversal with siteId and PO$")
	public void i_do_stock_adjustments_after_putaway_for_receipt_reversal_with_siteId_and_PO() throws Throwable {
		String siteID = context.getSiteId();
		String preAdviceId = context.getPreAdviceId();
		JDAHomeStepDefs.i_navigate_to_stock_adjustments_page();
		String putawayTagId = inventoryTransactionDB.getPutawayTagId(siteID, preAdviceId);
		stockAdjustmentStepDefs.i_select_a_existing_stock_with_siteid_location_and_tag_id(siteID,
				context.getToLocation(), putawayTagId);
	}
}
