package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
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

	@Inject
	public InventoryTransactionQueryStepDefs(Context context, Verification verification,
			InventoryTransactionDB inventoryTransactionDB, InventoryTransactionQueryPage inventoryTransactionQueryPage,
			JDAFooter jDAFooter, JdaLoginPage jdaLoginPage, JDAHomeStepDefs jDAHomeStepDefs, JdaHomePage jdaHomePage,
			UPIReceiptLineDB uPIReceiptLineDB) {
		this.context = context;
		this.verification = verification;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.jDAFooter = jDAFooter;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.jdaHomePage = jdaHomePage;
		this.uPIReceiptLineDB = uPIReceiptLineDB;
	}

	@Then("^the goods receipt should be generated for received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();

		// jdaLoginPage.login();
		// jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		// jDAFooter.clickQueryButton();
		// inventoryTransactionQueryPage.selectCode("Receipt");
		// inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		// inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		// inventoryTransactionQueryPage.enterTransactionDate();
		// jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(), "Receipt"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getToLocation(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(), date, "Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getUpiId(), date, "Receipt"),
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

	@Then("^the goods receipt should be generated for IDT received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_IDT_received_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList skuFromUPI = new ArrayList();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();

		// jdaLoginPage.login();
		// jDAHomeStepDefs.i_navigate_to_inventory_transaction_query();
		// jDAFooter.clickQueryButton();
		// inventoryTransactionQueryPage.selectCode("Receipt");
		// inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		// inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		// inventoryTransactionQueryPage.enterTransactionDate();
		// jDAFooter.clickExecuteButton();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) context.getSkuList().get(i - 1));
			context.setTagId(inventoryTransactionDB.getTagId(context.getUpiId(), "Receipt"));
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

	@Then("^the goods receipt should be generated for FSV PO received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_fsv_PO_received_stock_in_inventory_transaction()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			// context.setTagId(inventoryTransactionDB.getTagId(upiId, code));
			verification.verifyData(
					"From Location for SKU " + context.getSkuId(), context.getLocation(), inventoryTransactionDB
							.getFromLocationPO(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
					failureList);
			verification.verifyData(
					"To Location for SKU " + context.getSkuId(), context.getLocation(), inventoryTransactionDB
							.getToLocationPO(context.getSkuId(), context.getPreAdviceId(), date, "Receipt"),
					failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQtyPO(context.getSkuId(), context.getPreAdviceId(), date,
							"Receipt"),
					failureList);
			verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
					inventoryTransactionDB.getReferenceIdPO(context.getSkuId(), context.getPalletIDList().get(i - 1),
							date, "Receipt"),
					failureList);
		}
		Assert.assertFalse("Inventory Transaction details are not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Then("^the goods receipt should be generated for putaway stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList skuFromUPI = new ArrayList();
		ArrayList<String> skuList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		skuList = context.getSkuList();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			for (int s = 0; s < skuList.size(); s++) {
				context.setSkuId(skuList.get(s));
				// context.setSkuId(poMap.get(i).get("SKU"));
				verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
						inventoryTransactionDB.getFromLocation(context.getSkuId(), context.getUpiId(), date, "Putaway"),
						failureList);
				verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
						inventoryTransactionDB.getToLocation(context.getSkuId(), context.getUpiId(), date, "Putaway"),
						failureList);
				verification.verifyData("Update Qty for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()),
						inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(), date, "Putaway"),
						failureList);
				verification.verifyData("Reference ID SKU " + context.getSkuId(), context.getPreAdviceId(),
						inventoryTransactionDB.getReferenceId(context.getSkuId(), context.getUpiId(), date, "Putaway"),
						failureList);
			}
			Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
					+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
		}
	}

	@Then("^the goods receipt should be generated for putaway stock in inventory transaction for override$")
	public void the_goods_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction_for_override()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		// poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			// context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getLocation(),
					inventoryTransactionDB.getFromLocatn(context.getTagId(), "Putaway", date), failureList);
			verification.verifyData("To Location for SKU " + context.getSkuId(), context.getToLocation(),
					inventoryTransactionDB.getToLocatn(context.getTagId(), "Putaway", date), failureList);
			verification.verifyData("Update Qty for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryTransactionDB.getUpdateQuantity(context.getTagId(), "Putaway", date), failureList);
			// verification.verifyData("Reference ID SKU " + context.getSkuId(),
			// context.getPreAdviceId(),
			// inventoryTransactionDB.getReferenceId(context.getUpiId(), date,
			// "Putaway"), failureList);
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

	@Then("^the iTL should be generated for putaway stock in inventory transaction$")
	public void the_iTL_receipt_should_be_generated_for_putaway_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		// ArrayList<String> skuList = new ArrayList<String>();
		upiMap = context.getUPIMap();
		// skuList = context.getSkuList();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		// for (int s = 0; s < skuList.size(); s++) {
		// context.setSkuId(skuList.get(s));

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			for (int m = 1; m <= 2; m++) {
				if (m == 1) {
					context.setToLocation(context.getToLocation());
					context.setRcvQtyDue((context.getRcvQtyDue() - 1));
				} else if (m == 2) {
					context.setToLocation(context.getToLocation2());
					context.setRcvQtyDue(1);
				}

				String isITLExists = inventoryTransactionDB.isITLExistsForRelocatedPutaway(context.getSkuId(),
						context.getUpiId(), date, "Putaway", context.getToLocation(), context.getRcvQtyDue());

			}
		}

		Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@When("^I choose the code as \"([^\"]*)\" and search the tag id$")
	public void i_choose_the_code_as_and_search_the_tag_id(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneousTab();
	}

	@Then("^the status should be updated$")
	public void the_status_should_be_updated() throws Throwable {
		Assert.assertEquals("updated inventory status are not as expected", context.getStatus(),
				inventoryTransactionQueryPage.getStatus());

	}

	@Then("^the expiry date should be updated$")
	public void the_expiry_date_should_be_updated() throws Throwable {
		Assert.assertEquals("updated inventory Expiry date are not as expected", context.getFutureExpiryDate(),
				inventoryTransactionQueryPage.getExpiryDate());

	}

	@When("^I choose the code as \"([^\"]*)\" and I search the tag id$")
	public void i_choose_the_code_as_and_I_search_the_tag_id(String code) throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();

	}

	@When("^I choose the code as config update and I search the sku id$")
	public void i_choose_the_code_as_config_update_and_I_search_the_sku_id() throws Throwable {
		String code = "Config Update";
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		inventoryTransactionQueryPage.clickMiscellaneous2Tab();
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
		Assert.assertEquals("updated inventory condition are not as expected", conditionToVerify,
				inventoryTransactionQueryPage.getCondition());
	}

	@Then("^the pallet should be updated$")
	public void the_pallet_should_be_updated() throws Throwable {
		Assert.assertEquals("updated inventory pallet are not as expected", context.getPalletType(),
				inventoryTransactionQueryPage.getPalletType());
	}

	@Then("^the owner should be updated$")
	public void the_owner_should_be_updated() throws Throwable {
		Assert.assertEquals("updated inventory pallet are not as expected", context.getOwner(),
				inventoryTransactionQueryPage.getPalletType());
	}

	@Then("^the pack config should be updated$")
	public void the_pack_config_should_be_updated() throws Throwable {
		Assert.assertEquals("updated inventory pack config are not as expected", context.getPackConfig(),
				inventoryTransactionQueryPage.getPackConfig());
		inventoryTransactionDB.getConfigId(context.getSkuId(), "Config Update");
		System.out.println(context.getTagId());
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

	// @When("^the inventory transaction should be updated with lock code
	// \"([^\"]*)\"$")
	// public void
	// the_inventory_transaction_should_be_updated_with_lockcode_imperfect(String
	// lockcode) throws Throwable {
	// Assert.assertFalse("No ITL for Inventory Lock", validate(lockcode));;
	// jDAFooter.clickQueryButton();
	// inventoryTransactionQueryPage.enterCode("Receipt");
	// inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
	// jDAFooter.clickExecuteButton();
	// }

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
		Assert.assertEquals("ITL Records received mismatch with total qty", context.getRcvQtyDue(),
				inventoryTransactionDB.getReceiptCount(context.getUpiId(), "Inv Lock"));
		Assert.assertTrue("ITL Lock codes are mismatching. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the inventory transaction should be updated with lockcode damaged$")
	public void the_inventory_transaction_should_be_updated_with_lockcode_damaged() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String lockCode = inventoryTransactionDB.getLockCode(context.getUpiId(), "Inv Lock");
		context.setLockCode(lockCode);
		Assert.assertTrue("Receipt Reversion failed",
				inventoryTransactionQueryPage.checkDamagedReceiptLockCode(lockCode));

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
		// jdaLoginPage.login();
		// jdaHomePage.navigateToInventoryTransactionPage();
		// jDAFooter.clickQueryButton();
		// inventoryTransactionQueryPage.enterCode("Receipt");
		// inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		// jDAFooter.clickExecuteButton();
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
		inventoryTransactionQueryPage.enterNotes("Custom ITL");
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
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
}
