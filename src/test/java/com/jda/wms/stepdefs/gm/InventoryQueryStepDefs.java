package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Then;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryQueryStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	
	@Inject
	public InventoryQueryStepDefs(Context context,Verification verification,InventoryDB inventoryDB) {
		this.context = context;
		this.verification =verification;
		this.inventoryDB = inventoryDB;
	}
	

	@Then("^the inventory should be displayed for all tags received$")
	public void the_inventory_should_be_displayed_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after receive"+context.getSkuId(), context.getLocation(), inventoryDB.getLocationAfterReceive(context.getSkuId(),context.getUpiId(),date), failureList);
			verification.verifyData("Qty on Hand for SKU "+context.getSkuId(), String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getUpiId(),date), failureList);
		}
		Assert.assertTrue("Inventory details are not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());
		}
	
	@Then("^the inventory should be displayed for all putaway tags$")
	public void the_inventory_should_be_displayed_for_all_putaway_tags() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after Putaway"+context.getSkuId(), context.getToLocation(), inventoryDB.getLocationAfterPutaway(context.getSkuId(),date), failureList);
//			verification.verifyData("Qty on Hand for SKU "+context.getSkuId(), String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getUpiId(),date), failureList);
		}
		Assert.assertTrue("Inventory details are not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());
		}
}
