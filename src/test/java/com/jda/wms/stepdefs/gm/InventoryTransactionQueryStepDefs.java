package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;

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
	private JdaHomePage jdaHomePage;

	@Inject
	public InventoryTransactionQueryStepDefs(Context context, Verification verification,
			InventoryTransactionDB inventoryTransactionDB, InventoryTransactionQueryPage inventoryTransactionQueryPage,
			JDAFooter jDAFooter,JdaHomePage jdaHomePage) {
		this.context = context;
		this.verification = verification;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.jDAFooter = jDAFooter;
		this.jdaHomePage = jdaHomePage;
	}

	@Then("^the goods receipt should be generated for received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_received_stock_in_inventory_transaction() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
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
		}
		Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
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
		Assert.assertEquals("updated inventory pallet are not as expected", context.getPalletType(),
				inventoryTransactionQueryPage.getPalletType());
	}

	@Then("^the reason code should be updated$")
	public void the_reason_code_should_be_updated() throws Throwable {
		String execDate = DateUtils.getCurrentSystemDateInDBFormat();
		boolean isRecordExists = inventoryTransactionDB.isRecordExistsForReasonCode(context.getSkuId(), "Adjustment",
				execDate, context.getReasonCode());
		Assert.assertTrue("ITL does not exist for the adjusted stock with reason code " + context.getReasonCode(),
				isRecordExists);

	}
	
	@When("^the inventory transaction should be updated with lock code \"([^\"]*)\"$")
	public void the_inventory_transaction_should_be_updated_with_lockcode_imperfect(String lockcode) throws Throwable {
		ArrayList failureList = new ArrayList();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		ArrayList lockCodeListForAllQuantity =inventoryTransactionDB.getLockCodeList(context.getUpiId(),"Inv Lock");
		for(int l=0;l<lockCodeListForAllQuantity.size();l++){
			if (!((String)lockCodeListForAllQuantity.get(l)).equalsIgnoreCase(lockcode)){
				failureList.add("ITL Lock code does not match for Quantity number "+l);
			}
		}
		Assert.assertEquals("ITL Records received mismatch with total qty",context.getRcvQtyDue(),inventoryTransactionDB.getReceiptCount(context.getUpiId(),"Inv Lock"));
		Assert.assertTrue(
				"ITL Lock codes are mismatching. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@When("^the inventory transaction should be updated with lockcode damaged$")
	public void the_inventory_transaction_should_be_updated_with_lockcode_damaged() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Inventory Lock");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String lockCode=inventoryTransactionDB.getLockCode(context.getUpiId(),"Inv Lock");
		context.setLockCode(lockCode);
		Assert.assertTrue("Receipt Reversion failed",inventoryTransactionQueryPage.checkDamagedReceiptLockCode(lockCode));
		
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
		Assert.assertEquals("ITL not displayed for Receipt reversal without Lock Code",reference_Id,context.getPreAdviceId());
	}
	
	@When("^the inventory transaction should be updated$")
	public void the_inventory_transaction_should_be_updated() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt";
		Assert.assertEquals("ITL not updated",context.getRcvQtyDue(),inventoryTransactionDB.getReceiptCount(context.getUpiId(), code));
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
		boolean isItlExists =false;
		if (reference_Id.equalsIgnoreCase(context.getPreAdviceId())&&(lockcode.equalsIgnoreCase(context.getLockCode()))){
			isItlExists = true;
		}
		Assert.assertTrue("ITL not displayed for Receipt reversal with Lock Code",isItlExists);
	}
}
