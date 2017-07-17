package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Then;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryTransactionQueryStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryTransactionDB inventoryTransactionDB;
	
	@Inject
	public InventoryTransactionQueryStepDefs(Context context,Verification verification,InventoryTransactionDB inventoryTransactionDB) {
		this.context = context;
		this.verification =verification;
		this.inventoryTransactionDB = inventoryTransactionDB;
	}
	
	@Then("^the goods receipt should be generated for received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU "+context.getSkuId(), context.getLocation(), inventoryTransactionDB.getFromLocation(context.getSkuId(),context.getUpiId(),date,"Receipt"), failureList);
			verification.verifyData("To Location for SKU "+context.getSkuId(), context.getLocation(), inventoryTransactionDB.getToLocation(context.getSkuId(),context.getUpiId(),date,"Receipt"), failureList);
			verification.verifyData("Update Qty for SKU "+context.getSkuId(), String.valueOf(context.getRcvQtyDue()), inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(), date, "Receipt"), failureList);
			verification.verifyData("Reference ID SKU "+context.getSkuId(), context.getPreAdviceId(), inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getUpiId(), date, "Receipt"), failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for PO received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_PO_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU "+context.getSkuId(), context.getLocation(), inventoryTransactionDB.getFromLocationPO(context.getSkuId(),context.getPreAdviceId(),date,"Receipt"), failureList);
			verification.verifyData("To Location for SKU "+context.getSkuId(), context.getLocation(), inventoryTransactionDB.getToLocationPO(context.getSkuId(),context.getPreAdviceId(),date,"Receipt"), failureList);
			verification.verifyData("Update Qty for SKU "+context.getSkuId(), String.valueOf(context.getRcvQtyDue()), inventoryTransactionDB.getUpdateQtyPO(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"), failureList);
			verification.verifyData("Reference ID SKU "+context.getSkuId(), context.getPreAdviceId(), inventoryTransactionDB.getReferenceIdPO(context.getSkuId(), context.getPalletIDList().get(i-1), date, "Receipt"), failureList);
		}
		Assert.assertFalse("Inventory Transaction details are not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU "+context.getSkuId(), context.getLocation(), inventoryTransactionDB.getFromLocation(context.getSkuId(),context.getUpiId(),date,"Putaway"), failureList);
			verification.verifyData("To Location for SKU "+context.getSkuId(), context.getToLocation(), inventoryTransactionDB.getToLocation(context.getSkuId(),context.getUpiId(),date,"Putaway"), failureList);
			verification.verifyData("Update Qty for SKU "+context.getSkuId(), String.valueOf(context.getRcvQtyDue()), inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(), date, "Putaway"), failureList);
			verification.verifyData("Reference ID SKU "+context.getSkuId(), context.getPreAdviceId(), inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getUpiId(), date, "Putaway"), failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());
	}
}
