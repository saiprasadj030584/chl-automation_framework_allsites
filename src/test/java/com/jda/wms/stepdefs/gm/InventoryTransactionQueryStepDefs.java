package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryTransactionQueryStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryTransactionDB inventoryTransactionDB;
	private InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private JDAFooter jDAFooter;
	private JdaLoginPage jdaLoginPage;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private JdaHomePage jdaHomePage;
	private UPIReceiptLineDB uPIReceiptLineDB;
	private UpiReceiptHeaderPage upiReceiptHeaderPage;
	private InventoryDB inventoryDB;

	@Inject
	public InventoryTransactionQueryStepDefs(Context context, Verification verification,
			InventoryTransactionDB inventoryTransactionDB, InventoryTransactionQueryPage inventoryTransactionQueryPage,
			JDAFooter jDAFooter, JdaLoginPage jdaLoginPage, JDAHomeStepDefs jDAHomeStepDefs, JdaHomePage jdaHomePage,
			UPIReceiptLineDB uPIReceiptLineDB, UpiReceiptHeaderPage upiReceiptHeaderPage,InventoryDB inventoryDB) {
		this.context = context;
		this.verification = verification;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.jDAFooter = jDAFooter;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.jdaHomePage = jdaHomePage;
		this.uPIReceiptLineDB = uPIReceiptLineDB;
		this.upiReceiptHeaderPage = upiReceiptHeaderPage;
		this.inventoryDB=inventoryDB;

	}

	@Then("^the goods receipt should be generated for received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();


		//jdaLoginPage.login();
//		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
//		jDAFooter.clickQueryButton();
//		inventoryTransactionQueryPage.selectCode("Receipt");
//		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
//		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
//		inventoryTransactionQueryPage.enterTransactionDate();
//		jDAFooter.clickExecuteButton();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(), "Receipt"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(),
					String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);

			if (null != context.getLockCode()) {
				verification.verifyData(
						"Lock Code SKU " + context.getSkuId(), context.getLockCode(), inventoryTransactionDB
								.getLockCodeWithPORef(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	@When("^the inventory transaction should be updated for single upi$")
	public void the_inventory_transaction_should_be_updated_for_single_upi() throws Throwable {
			jDAFooter.clickQueryButton();
			inventoryTransactionQueryPage.enterCode("Receipt");
			inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
			jDAFooter.clickExecuteButton();
			String code = "Receipt";
			Assert.assertEquals("ITL not generated for Returns Receiving", 1 , inventoryTransactionDB.getReceiptCount(context.getUpiId(), code));

	}
	@Then("^the goods receipt should be generated for received stock in inventory transaction for receiving$")
	public void the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction_for_receiving()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();

		// jdaLoginPage.login();
		// jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		// jDAFooter.clickQueryButton();
		// inventoryTransactionQueryPage.selectCode("Receipt");
		// inventoryTransactionQueryPage.enterTagId(context.getTagId());
		// inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		// inventoryTransactionQueryPage.enterTransactionDate();
		// jDAFooter.clickExecuteButton();
		System.out.println("Tag ID " + context.getTagId());

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(), "Receipt"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);

			if(context.getReceiveType()!=null)
			{
			if(context.getReceiveType().equalsIgnoreCase("Under Receiving"))
			{
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()-5),

					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			}
			}
			else
			{
				verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
						inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
			
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);

			if (null != context.getLockCode()) {

				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getLockCode(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),

						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the ITL should be generated for putaway stock in inventory transaction for override$")
	public void the_ITL_should_be_generated_for_putaway_stock_in_inventory_transaction_for_override() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			verification.verifyData("From Location for SKU ", context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU ", context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getPreAdviceId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			//context.setTagId(
				//	inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway stock in inventory transaction for override$")
	public void the_goods_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction_for_override()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocatn(context.getTagId(), "Putaway", date), failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocatn(context.getTagId(), "Putaway", date), failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQuantity(context.getTagId(), "Putaway", date), failureList);
		}

		Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the ITL should be generated for putaway relocated stock in inventory transaction$")
	public void the_ITL_should_be_generated_for_putaway_relocated_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		String date = DateUtils.getCurrentSystemDateInDBFormat();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			for (int m = 1; m <= 2; m++) {
				context.setSkuId(poMap.get(i).get("SKU"));
				if (m == 1) {
					context.setToLocation(context.getPutawayLocation1());
					context.setRcvQtyDue((context.getRcvQtyDue() - 2));
				} else if (m == 2) {
					context.setToLocation(context.getPutawayLocation2());
					context.setRcvQtyDue(2);
				}

				String isITLExists = inventoryTransactionDB.isITLExistsForRelocatedPutaway(context.getSkuId(),
						context.getUpiId(), date, "Putaway", context.getToLocation(), context.getRcvQtyDue());

			}
		}

		Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the ITL should be generated for putaway stock in inventory transaction$")
	public void the_ITL_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		skuList = context.getSkuList();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));

			for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
				for (int m = 1; m <= 2; m++) {
					if (m == 1) {
						context.setToLocation(context.getToLocation());
						context.setRcvQtyDue((context.getRcvQtyDue() - 10));
					} else if (m == 2) {
						context.setToLocation(context.getToLocation2());
						context.setRcvQtyDue(5);
					}

					String isITLExists = inventoryTransactionDB.isITLExistsForRelocatedPutaway(context.getSkuId(),
							context.getUpiId(), date, "Putaway", context.getToLocation(), context.getRcvQtyDue());

				}
			}
			Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
					+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
		}
	}

	@When("^the inventory is unlocked and the return stock is over received$")
	public void the_inventory_is_unlocked_and_the_return_stock_is_over_received()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Adjustment");
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterSkuIdExisting(context.getSkuId());
		inventoryTransactionQueryPage.enterTransactionDate();

		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		if (!inventoryTransactionQueryPage.isNoRecords()) {
			failureList.add("No Records available for " + context.getUpiId() + "in adjustment");
		}
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		context.setReasonCode(inventoryTransactionQueryPage.getReasonCode());

		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Inventory Unlock");
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterSkuIdExisting(context.getSkuId());
		//inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterTransactionDate();

		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		if (!inventoryTransactionQueryPage.isNoRecords()) {
			failureList.add("No Records available for " + context.getUpiId() + "in adjustment");
		}

		verification.verifyData("Quantity not over received", String.valueOf(context.getQtyOnHand()),
				inventoryTransactionDB.getUpdateQtyUnlocked(context.getSkuId(), context.getUpiId(), date), failureList);
		verification.verifyData("Inventory not unlocked", "UnLocked",
				inventoryTransactionDB.getLockStatus(context.getUpiId(), "Inv UnLock", date), failureList);
		if (context.getReasonCode().equalsIgnoreCase("RMS - Over receipt with movement label")) {
			verification.verifyData("Inventory lock code mismatch after adjustment", "OVERHUMOV",
					inventoryTransactionDB.getReasonCode(context.getUpiId(), "Adjustment", "DMGD", date), failureList);
		} else if (context.getReasonCode().equalsIgnoreCase("RMS - Over receipt without movement label")) {
			verification.verifyData("Inventory lock code mismatch after adjustment", "OVERHUNOMV",
					inventoryTransactionDB.getReasonCode(context.getUpiId(), "Adjustment", "DMGD", date), failureList);
		}
		Assert.assertTrue(
				"Reason code and inventory lock is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for the multiple stock received in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_the_multiple_stock_received_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int j = 0; j < context.getPreAdviceList().size(); j++) {
			context.setPreAdviceId(context.getPreAdviceList().get(j));
			context.setUpiId(context.getUpiList().get(j));

			for (int i = context.getLineItem(); i <= Integer
					.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(j))); i++) {
				context.setRcvQtyDue(Integer.parseInt(
						context.getMultiplePOMap().get(context.getPreAdviceList().get(j)).get(i).get("QTY DUE")));
				context.setSkuId(context.getMultiplePOMap().get(context.getPreAdviceList().get(j)).get(i).get("SKU"));
				verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
						inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getUpiId(), date, "Receipt"),
						failureList);
				verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
						inventoryTransactionDB.getToLocation(context.getSkuId(), context.getUpiId(), date, "Receipt"),
						failureList);
				verification.verifyData("Update Qty for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()),
						inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(), date, "Receipt"),
						failureList);
				verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getUpiId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for FSV PO received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_fsv_PO_received_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(), "Receipt"));
			verification.verifyData(
					"From Location for SKU " + context.getSkuId(), context.getLocation(), inventoryTransactionDB
							.getFromLocationPO(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
					failureList);
			verification.verifyData(
					"To Location for SKU " + context.getSkuId(), context.getLocation(), inventoryTransactionDB
							.getToLocationPO(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
					failureList);
			System.out.println("qty " +context.getRcvQtyDue());
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyPO(context.getSkuId(), context.getPreAdviceId(), date,
							"Receipt"),
					failureList);
			
			
			
			System.out.println("qty " +context.getRcvQtyDue());
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdPO(context.getSkuId(), context.getPalletIDList().get(i - 1),
							date, "Receipt"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway returns stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_returns_stock_in_inventory_transaction()
			throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(context.getSkuFromUPI().get(i - 1));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(), "Receipt", context.getSkuId(), date));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getFromLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), "1",
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getUpiId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway IDT stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_idt_stock_in_inventory_transaction()
			throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(context.getSkuFromUPI().get(i - 1));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(), "Receipt", context.getSkuId(), date));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getFromLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getUpiId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway FSV stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_fsv_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getFromLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I choose the code as \"([^\"]*)\" and search the tag id$")
	public void i_choose_the_code_as_and_search_the_tag_id(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTransactionTime(DateUtils.getCurrentSystemTimeLessThan2Minutes());
		jDAFooter.clickExecuteButton();
		// inventoryTransactionQueryPage.clickMiscellaneousTab();
	}

	@Then("^the status should be updated$")
	public void the_status_should_be_updated() throws Throwable {
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		Thread.sleep(3000);
		Assert.assertEquals("updated inventory status are not as expected", context.getStatus(),
				// inventoryTransactionQueryPage.getStatus());
				inventoryTransactionDB.getlockstatus(date, context.getTagId()));

	}

	@Then("^the expiry date should be updated$")
	public void the_expiry_date_should_be_updated() throws Throwable {
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		Thread.sleep(1000);
		Assert.assertEquals("updated inventory Expiry date are not as expected", context.getFutureExpiryDate(),
				// inventoryTransactionQueryPage.getExpiryDate());
				inventoryTransactionDB.getExpiryDate(date, context.getTagId()));
	}

	@When("^I choose the code as \"([^\"]*)\" and I search the tag id$")
	public void i_choose_the_code_as_and_I_search_the_tag_id(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTransactionTime(DateUtils.getCurrentSystemTimeLessThan2Minutes());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickUserDefinedTab();
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();
	}

	@When("^I choose the code as config update and I search the sku id$")
	public void i_choose_the_code_as_config_update_and_I_search_the_sku_id() throws Throwable {
		String code = "Config Update";
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTransactionTime(DateUtils.getCurrentSystemTimeLessThan2Minutes());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();
	}
	@When("^I choose the code as \"([^\"]*)\" and search the sku id at specific transaction time$")      
	public void i_choose_the_code_as_and_search_the_sku_id_at_particular_transaction_time(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		String dstamp=inventoryDB.getDstamp(context.getSkuId());
		String transactionTime=dstamp.replace(':','.').substring(10,19);
		context.setTransactionTime(transactionTime);
		System.out.println("transactionTime"+transactionTime);
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterTransactionTime(transactionTime);
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		inventoryTransactionQueryPage.getReasonCode();
	}
	

	@When("^I choose the code as \"([^\"]*)\" and search the sku id$")
	public void i_choose_the_code_as_and_search_the_sku_id(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterUpdateQuantity(String.valueOf(context.getQtyOnHand()));
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		inventoryTransactionQueryPage.getReasonCode();
	}

	@When("^I choose the code as \"([^\"]*)\" and search the sku id and tag id$")
	public void i_choose_the_code_as_and_search_the_sku_id_and_tag_id(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterUpdateQuantity(String.valueOf(context.getQtyOnHand()));
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		context.setReasonCode(inventoryTransactionQueryPage.getReasonCode());
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();
		context.setUploaded(inventoryTransactionQueryPage.getUploaded());

	}

	@Then("^the condition should be updated$")
	public void the_condition_should_be_updated() throws Throwable {
		String conditionToVerify = null;
		switch (context.getCondition()) {
		case "Black condition code":
			conditionToVerify = "BLACK";
			break;
		case "Blue condition code":
			conditionToVerify = "BLUE";
			break;
		case "Green condition code":
			conditionToVerify = "GREEN";
			break;
		case "Pink condition code":
			conditionToVerify = "PINK";
			break;
		case "Red condition code":
			conditionToVerify = "RED";
			break;

		}

		String date = DateUtils.getCurrentSystemDateInDBFormat();
		Assert.assertEquals("updated inventory condition are not as expected", conditionToVerify,
				// inventoryTransactionQueryPage.getCondition());
				inventoryTransactionDB.getConditionfromDB(date, context.getTagId()));

	}

	@Then("^the pallet should be updated$")
	public void the_pallet_should_be_updated() throws Throwable {

		String date = DateUtils.getCurrentSystemDateInDBFormat();
		Assert.assertEquals("updated inventory pallet are not as expected", context.getPalletType(),
				// inventoryTransactionQueryPage.getPalletType());
				inventoryTransactionDB.getPalletfromDB(date, context.getTagId()));
	}

	@Then("^the owner should be updated$")
	public void the_owner_should_be_updated() throws Throwable {
		Assert.assertEquals("updated inventory pallet are not as expected", context.getOwner(),
				inventoryTransactionQueryPage.getPalletType());
	}

	@Then("^the pack config should be updated$")
	public void the_pack_config_should_be_updated() throws Throwable {

		String date = DateUtils.getCurrentSystemDateInDBFormat();
		Assert.assertEquals("Updated inventory pack config are not as expected", context.getPackConfig(),
				// inventoryTransactionQueryPage.getPackConfig());
				inventoryTransactionDB.getPackConfigfromDB(date, context.getTagId()));


	}

	@Then("^the reason code should be updated$")
	public void the_reason_code_should_be_updated() throws Throwable {
		String execDate = DateUtils.getCurrentSystemDateInDBFormat();
		String updatedQty = String.valueOf(context.getQtyOnHand());
		boolean isRecordExists = inventoryTransactionDB.isRecordExistsForReasonCode(context.getSkuId(), "Adjustment",
				execDate, context.getReasonCode(), updatedQty);
		Assert.assertTrue("ITL does not exist for the adjusted stock with reason code " + context.getReasonCode(),
				isRecordExists);
	}
	@Then("^the reason code should be updated for specific transaction time$")
	public void the_reason_code_should_be_updated_for_specific_transaction_time() throws Throwable {
		String execDate = DateUtils.getCurrentSystemDateInDBFormat();
	    String transactionTime=context.getTransactionTime();
		boolean isRecordExists = inventoryTransactionDB.isRecordExistsForReasonCodeForTransactionTime(context.getSkuId(), "Adjustment",
				transactionTime);
		Assert.assertTrue("ITL does not exist for the adjusted stock with reason code " + context.getReasonCode(),
				isRecordExists);

	}
	
	

	@When("^the inventory transaction should be updated for SKU with single supplier$")
	public void the_inventory_transaction_should_be_updated_for_sku_with_single_supplier() throws Throwable {
		ArrayList failureList = new ArrayList();
		if (context.getPerfectCondition().equals("N")) {
			jDAFooter.clickQueryButton();
			inventoryTransactionQueryPage.enterCode("Inventory Lock");
			inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
			jDAFooter.clickExecuteButton();
			String lockCode = inventoryTransactionDB.getLockCode(context.getUpiId(), "Inv Lock");
			if (!lockCode.equalsIgnoreCase("DMGD"))
				failureList.add("ITL not generated with lock code DMGD");
		} else if (context.getPerfectCondition().equals("Y")) {
			if (!inventoryTransactionDB.getCode(context.getUpiId(), "Receipt"))
				failureList.add("ITL not generated with Receipt code");
		}
		Assert.assertTrue("ITL not generated as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
	}

	private boolean validate(String perfectCondition) throws FindFailed, InterruptedException {
		boolean isLockcodeExists = false;
		try {
			if (perfectCondition.equals("N")) {
				jDAFooter.clickQueryButton();
				inventoryTransactionQueryPage.enterCode("Inventory Lock");
				inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
				jDAFooter.clickExecuteButton();
				String lockCode = inventoryTransactionDB.getLockCode(context.getUpiId(), "Inv Lock");
				isLockcodeExists = true;
			} else if (perfectCondition.equals("Y")) {
				String lockCode = inventoryTransactionDB.getLockCode(context.getUpiId(), "Inv Lock");
				isLockcodeExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset"))
				isLockcodeExists = false;
		}
		return isLockcodeExists;
	}

	@Then("^the reason code and uploaded should be updated$")
	public void the_reason_code_and_uploaded_should_be_updated() throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
//		context.setUploaded(inventoryTransactionDB.getUploadedValueUnlocked(context.getUpiId(), context.getTagId(),
//				"Adjustment", date));
		// if(!(context.getUploaded().equalsIgnoreCase("Y")))
		// {
		// failureList.add("Uploaded field not updated as expected
		// "+context.getUpiId());
		// }
		//
		if (context.getReasonCode().equalsIgnoreCase("RMS - Unexpected receipt with movement label")) {
			verification.verifyData("Reason code not updated as expected", "NADVMVNOHU", inventoryTransactionDB
					.getReasonCodeUnlocked(context.getTagId(), "Adjustment", date), failureList);
		}

		else if (context.getReasonCode().equalsIgnoreCase("RMS - Unexpected receipt without movement label")) {
			verification.verifyData("Reason code not updated as expected", "NANOHUNMV", inventoryTransactionDB
					.getReasonCodeUnlocked(context.getTagId(), "Adjustment", date), failureList);
		}

		else if (context.getReasonCode().equalsIgnoreCase("RMS  Non advised receipt with movement label")) {
			verification.verifyData("Reason code not updated as expected", "NADVHUMOV", inventoryTransactionDB
					.getReasonCodeUnlocked(context.getTagId(), "Adjustment", date), failureList);
		}

		//
		else if (context.getReasonCode().equalsIgnoreCase("RMS - Non advised receipt without movement label")) {
			verification.verifyData("Reason code not updated as expected", "NADVHUNOMV", inventoryTransactionDB
					.getReasonCodeUnlocked(context.getTagId(), "Adjustment", date), failureList);
		}
		Assert.assertTrue(
				"Reason code and inventory lock is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the inventory transaction is updated with locked status$")
	public void the_inventory_transaction_is_updated_with_locked_status() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		Thread.sleep(2000);
		ArrayList failureList = new ArrayList();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		ArrayList lockCodeListForAllQuantity = inventoryTransactionDB.getLockCodeList(context.getUpiId(), "Inv Lock");
		for (int l = 0; l < lockCodeListForAllQuantity.size(); l++) {
			if (!((String) lockCodeListForAllQuantity.get(l)).equalsIgnoreCase("DMGD")) {
				failureList.add("ITL Lock code does not match for Quantity number " + l);
			}
		}
		// TODO
		Assert.assertTrue("ITL is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the inventory transaction should be updated with lock code \"([^\"]*)\"$")
	public void the_inventory_transaction_should_be_updated_with_lockcode_imperfect(String lockcode) throws Throwable {
		ArrayList failureList = new ArrayList();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		ArrayList lockCodeListForAllQuantity = inventoryTransactionDB.getLockCodeList(context.getUpiId(), "Inv Lock");
		for (int l = 0; l < lockCodeListForAllQuantity.size(); l++) {
			if (!((String) lockCodeListForAllQuantity.get(l)).equalsIgnoreCase(lockcode)) {
				failureList.add("ITL Lock code does not match for Quantity number " + l);
			}
		}
		if (context.getRcvQtyDue() == inventoryTransactionDB.getReceiptCount(context.getUpiId(), "Inv Lock")) {
			failureList.add("ITL Records received mismatch with total qty");
		}
		Assert.assertTrue("ITL Lock codes are mismatching. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for IDT received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_IDT_received_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList skuFromUPI = new ArrayList();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		String dstamp=inventoryDB.getDstamp(context.getSkuId());
		String transactionTime=dstamp.replace(':','.').substring(10,19);
		context.setTransactionTime(transactionTime);
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterTransactionTime(transactionTime);
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) context.getSkuList().get(i - 1));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocationIDT(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocationIDT(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyIDT(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getUpiId(),
					inventoryTransactionDB.getReferenceIdIDT(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);

			if (null != context.getLockCode()) {
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getUpiId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^the inventory transaction should be updated with lockcode damaged for fsv po$")
	public void the_inventory_transaction_should_be_updated_with_lockcode_damaged_fsv_po() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		inventoryTransactionQueryPage.enterReferenceId(context.getPreAdviceId());
		jDAFooter.clickExecuteButton();
		String lockCode = inventoryTransactionDB.getLockCode(context.getPreAdviceId(), "Inv Lock");
		context.setLockCode(lockCode);
		System.out.println("Lock code - "+lockCode);
		Assert.assertEquals("Lock code not displayed as expected in ITL", "DMGD", lockCode);
	}
	
	@When("^the inventory transaction should be updated with lockcode damaged$")
	public void the_inventory_transaction_should_be_updated_with_lockcode_damaged() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		inventoryTransactionQueryPage.enterReferenceId(context.getPreAdviceId());
		jDAFooter.clickExecuteButton();
		String lockCode = inventoryTransactionDB.getLockCode(context.getPreAdviceId(), "Inv Lock");
		context.setLockCode(lockCode);
		System.out.println("Lock code - "+lockCode);
		Assert.assertEquals("Lock code not displayed as expected in ITL", "DMGD", lockCode);
	}

	@When("^the inventory transaction should be updated with reversed receipt tag$")
	public void the_inventory_transaction_should_be_updated_with_reversed_receipt_tag() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt Reversal");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt Reverse";
		String reference_Id = inventoryTransactionDB.getReferenceId(context.getUpiId(), code);
		Assert.assertEquals("ITL not displayed for Receipt reversal without Lock Code", reference_Id,
				context.getPreAdviceId());
	}

	@When("^the inventory transaction should be updated with reversed receipt tag with lockcode$")
	public void the_inventory_transaction_should_be_updated_with_reversed_receipt_tag_with_lockcode() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt Reversal");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt Reverse";
		String reference_Id = inventoryTransactionDB.getReferenceId(context.getUpiId(), code);
		String lockcode = inventoryTransactionDB.getLockCode(context.getPreAdviceId(), code);
		System.out.println("LOCKKK CODE"+context.getLockCode());
		boolean isItlExists = false;
		if (reference_Id.equalsIgnoreCase(context.getPreAdviceId())
				&& (lockcode.equalsIgnoreCase(context.getLockCode()))) {
			isItlExists = true;
		}
		Assert.assertTrue("ITL not displayed for Receipt reversal with Lock Code", isItlExists);
	}
	@When("^the inventory transaction should be updated with reversed receipt tag with lockcode for random tag received$")
	public void the_inventory_transaction_should_be_updated_with_reversed_receipt_tag_with_lockcode_for_random_tag_received() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt Reversal");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt Reverse";
		String reference_Id = inventoryTransactionDB.getReferenceId(context.getUpiId(), code);
		String lockcode = inventoryTransactionDB.getLockCode(context.getUpiId(), code);
		boolean isItlExists = false;
		if (reference_Id.equalsIgnoreCase(context.getPreAdviceId())
				&& (lockcode.equalsIgnoreCase(context.getLockCode()))) {
			isItlExists = true;
		}
		Assert.assertTrue("ITL not displayed for Receipt reversal with Lock Code", isItlExists);
	}

	@When("^the inventory transaction should be updated$")
	public void the_inventory_transaction_should_be_updated() throws Throwable {
		jdaLoginPage.login();
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		context.setTagId(inventoryTransactionDB.getTagId(context.getUpiId(), "Receipt"));
		String code = "Receipt";
		Assert.assertEquals("ITL not updated", context.getRcvQtyDue(),
				inventoryTransactionDB.getReceiptCount(context.getUpiId(), code));
	}

	@Given("^the ITL should be generated for IDT received stock in inventory transaction$")
	public void the_ITL_should_be_generated_for_IDT_received_stock_in_inventory_transaction() throws Throwable {
		jdaLoginPage.login();
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterNotes("Custom ITL");
		//inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		jDAFooter.clickExecuteButton();
		Assert.assertEquals("ITL not updated", "Receiving Error",
				inventoryTransactionDB.getCodeIdt(context.getSkuId(), "Custom ITL"));
	}

	@Then("^the inventory transaction should be updated for multi sourced SKU receipt$")
	public void the_inventory_transaction_should_be_updated_for_multi_sourced_SKU_receipt() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		if (context.getPerfectCondition().equalsIgnoreCase("Y")) {
			Assert.assertTrue("ITL not generated for Recipt with Multi Supplier",
					inventoryTransactionDB.getCode(context.getUpiId(), "Receipt"));
		} else if (context.getPerfectCondition().equalsIgnoreCase("N")) {
			Assert.assertEquals("ITL Failed for Returns with Multi Supplier - no lock code generated", "DMGD",
					inventoryTransactionDB.getLockCode(context.getUpiId(), "Receipt"));
		}
	}

	@When("^the inventory transaction should be updated for multiple upi$")
	public void the_inventory_transaction_should_be_updated_for_multiple_upi() throws Throwable {
		int receiptCount = 0;
		int origQtyDue = 0;
		int m = 0;
		for (int i = 0; i < context.getUpiList().size(); i++) {
			jDAFooter.clickQueryButton();
			inventoryTransactionQueryPage.enterCode("Receipt");
			inventoryTransactionQueryPage.enterReferenceId(context.getUpiList().get(i));
			jDAFooter.clickExecuteButton();
			String code = "Receipt";
			receiptCount += inventoryTransactionDB.getReceiptCount(context.getUpiList().get(i), code);
			for (int j = 0; j < context.getUpiNumLinesMap().get(context.getUpiList().get(i)); j++) {
				m++;
				context.setSkuId(context.getSkuFromUPI().get(m - 1));
				origQtyDue += Integer.parseInt(context.getMultipleUPIMap().get(context.getUpiList().get(i))
						.get(context.getSkuId()).get("QTY DUE"));
			}
		}

		Assert.assertEquals("ITL not updated", origQtyDue, receiptCount);
	}

	@When("^I search with sku and reason code$")
	public void i_search_with_sku_and_reason_code() throws Throwable {
		jDAFooter.clickQueryButton();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterSkuIdExisting(context.getSkuId());
		//comment below line
		jDAFooter.pressTab();
		//inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterPalletId(context.getUpiId());
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		inventoryTransactionQueryPage.enterReasonCode("UPCOVERREC");
		jDAFooter.clickExecuteButton();
		Assert.assertTrue("Records are not as expected", inventoryTransactionQueryPage.isNoRecords());
	}

	@When("^I check the inventory for transaction update$")
	public void i_check_the_inventory_for_transaction_update() throws Throwable {
		jdaHomePage.navigateToUpiReceiptHeaderPage();
		upiReceiptHeaderPage.fetchRecord(context.getUpiId());
		context.setDueDate(upiReceiptHeaderPage.getDueDate());
		context.setReceiptDate(upiReceiptHeaderPage.getReceiptDate());
		jdaHomePage.navigateToInventoryTransactionPage();
		Thread.sleep(2000);
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt";
		Assert.assertEquals("ITL not updated", context.getRcvQtyDue(),
				inventoryTransactionDB.getReceiptCount(context.getUpiId(), code));

	}

	@Given("^the ITL should be generated for IDT received with lock code stock in inventory transaction$")
	public void the_ITL_should_be_generated_for_IDT_received_with_lock_code_stock_in_inventory_transaction()
			throws Throwable {
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		Assert.assertEquals("ITL not updated", "DMGD",
				inventoryTransactionDB.getLockCodebyUpid(context.getUpiId(), context.getSkuId(), date, "Receipt"));
	}

	@Given("^the ITL should be generated for IDT received in inventory transaction$")
	public void the_ITL_should_be_generated_for_IDT_received_in_inventory_transaction() throws Throwable {
		ArrayList failureList = new ArrayList();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		verification.verifyData("ITL", "Not Null",
				inventoryTransactionDB.getUpdateQtyIDT(context.getSkuId(), context.getUpiId(), date, "Receipt"),
				failureList);
		Assert.assertTrue("ITL not updated. [" + Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^verify the status as \"([^\"]*)\" in ITL$")
	public void verify_the_status_as_in_itl(String notes) throws Throwable {
		String itlNotes = inventoryTransactionDB.getNotes(context.getOrderId());
		Assert.assertEquals("Order Status is not Cancelled as expected", notes, itlNotes);
	}

	@Then("^the goods receipt should be generated for received stock in inventory transaction for two putaway group$")
	public void the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction_for_two_putaway_group()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getPreAdviceId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			verification.verifyData(
					"From Location for SKU " + context.getSkuId(), context.getLocation(), inventoryTransactionDB
							.getFromLocationWithPo(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
					failureList);
			verification.verifyData(
					"To Location for SKU " + context.getSkuId(), context.getLocation(), inventoryTransactionDB
							.getToLocationWithPo(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyWithPo(context.getSkuId(), context.getPreAdviceId(), date,
							"Receipt"),
					failureList);

			if (null != context.getLockCode()) {
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getUpiId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I choose the code as \"([^\"]*)\" and search the sku id with IE reason code$")
	public void i_choose_the_code_as_and_search_the_sku_id_with_IE_reason_code(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterSkuIdExisting(context.getSkuId());
		inventoryTransactionQueryPage.enterToLocation(context.getToLocation());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		inventoryTransactionQueryPage.getReasonCode();
	}

	@When("^the inventory transaction should be updated for random palletId with reversed receipt tag$")
	public void the_inventory_transaction_should_be_updated_for_random_palletId_with_reversed_receipt_tag()
			throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt Reversal");
		inventoryTransactionQueryPage.enterTagId(context.getPalletIDList().get(0));
		jDAFooter.clickExecuteButton();
		String code = "Receipt Reverse";
		String reference_Id = inventoryTransactionDB.getReferenceId(context.getPalletIDList().get(0), code);
		Assert.assertEquals("ITL not displayed for Receipt reversal without Lock Code", reference_Id,
				context.getPreAdviceId());
	}

	@When("^the inventory transaction should be updated with reversed receipt tag for random tags received$")
	public void the_inventory_transaction_should_be_updated_with_reversed_receipt_tag_for_random_tags_received()
			throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt Reversal");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt Reverse";
		String reference_Id = inventoryTransactionDB.getReferenceId(context.getTagId(), code);
		Assert.assertEquals("ITL not displayed for Receipt reversal without Lock Code", reference_Id,
				context.getPreAdviceId());
	}

	@Then("^the goods receipt should be generated for random tags received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_random_tags_received_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		context.setTagId(context.getTagId());
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);

			if (null != context.getLockCode()) {
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the ITL should be generated for putaway in inventory transaction for override$")
	public void the_ITL_should_be_generated_for_putaway_in_inventory_transaction_for_override() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			verification.verifyData("From Location for SKU ", context.getRelocateLoctn(), inventoryTransactionDB
					.getFromLocation(context.getPreAdviceId(), context.getUpiId(), date, "Putaway"), failureList);
			verification.verifyData("To Location for SKU ", context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getPreAdviceId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway stock after relocation in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_stock_after_relocation_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getFromLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I choose the code as \"([^\"]*)\" and I search the tag id for condition$")
	public void i_choose_the_code_as_and_I_search_the_tag_id_for_condition(String code) throws Throwable {
		// String execDate = DateUtils.getCurrentSystemDate();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTransactionTime(DateUtils.getCurrentSystemTimeLessThan2Minutes());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickUserDefinedTab();
	}
	
	
	
	
	
	
	@Then("^the goods receipt should be generated for GOH received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_GOH_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	
	@Then("^the goods receipt should be generated for hanging received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_hanging_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		///jdaLoginPage.login();
//		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
//		jDAFooter.clickQueryButton();
//		inventoryTransactionQueryPage.selectCode("Receipt");
//		jDAFooter.pressTab();
//		jDAFooter.pressTab();
//		inventoryTransactionQueryPage.enterTagId(context.getTagId());
//		inventoryTransactionQueryPage.enterTransactionDate();
//		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getLockCode(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for GOH putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_GOH_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocationWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocationWithpalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for GOH type$")
	public void the_goods_receipt_should_be_generated_for_GOH_type() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for flatpack received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_flatpack_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for flatpack putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_flatpack_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocationWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocationWithpalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for boxed received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_boxed_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		//jdaLoginPage.login();
		System.out.println("ITL SCREEN NAVIGATION");
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for boxed putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_boxed_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getFromLocation(),
					inventoryTransactionDB.getFromLocationWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocationWithpalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the inventory should be displayed after stock adjustment for flatpack$")
	public void the_inventory_should_be_displayed_after_stock_adjustment_for_flatpack() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		System.out.println("upi map" + upiMap);
		// multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			System.out.println("qty due ahter stock" + upiMap.get(context.getSkuId()).get("QTY DUE"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getToLocation(),
						inventoryDB.getLocationAfterReceiveForFlatpack(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getQtyonhandafteradjustment()),
						inventoryDB.getQtyOnHandForFlatpack(context.getSkuId(), context.getTagId(), date), failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Then("^the inventory should be displayed after stock adjustment for fsv po boxed$")
	public void the_inventory_should_be_displayed_after_stock_adjustment_for_fsv_po_boxed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			System.out.println("qty due ahter stock" + poMap.get(i).get("QTY DUE"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getToLocation(),
						inventoryDB.getLocationAfterReceiveForFlatpack(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getQtyonhandafteradjustment()),
						inventoryDB.getQtyOnHandForFlatpack(context.getSkuId(), context.getTagId(), date), failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for hanging stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_hanging_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		//jdaLoginPage.login();
		System.out.println("ITL SCREEN NAVIGATION");
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for hanging putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_hanging_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocationWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocationWithpalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for goh received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_goh_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		//jdaLoginPage.login();
		System.out.println("ITL SCREEN NAVIGATION");
		jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Receipt");
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		inventoryTransactionQueryPage.enterTransactionDate();
		jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
		verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getTagId(), date, "Receipt"),
					failureList);
				
			
			if (null!=context.getLockCode()){
				verification.verifyData("Lock Code SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getLockCode(context.getSkuId(), context.getTagId(), date, "Receipt"),
						failureList);
			}
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Then("^the goods receipt should be generated for goh putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_goh_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocationWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocationWithpalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdWithPalletId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
					failureList);
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
}