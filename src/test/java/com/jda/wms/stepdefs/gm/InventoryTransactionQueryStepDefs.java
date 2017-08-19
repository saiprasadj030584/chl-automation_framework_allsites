package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;
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
	private UpiReceiptHeaderPage upiReceiptHeaderPage;
	

	@Inject
	public InventoryTransactionQueryStepDefs(Context context, Verification verification,
			InventoryTransactionDB inventoryTransactionDB, InventoryTransactionQueryPage inventoryTransactionQueryPage,
			JDAFooter jDAFooter,JdaHomePage jdaHomePage,UpiReceiptHeaderPage upiReceiptHeaderPage) {
		this.context = context;
		this.verification = verification;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.jDAFooter = jDAFooter;
		this.jdaHomePage = jdaHomePage;
		this.upiReceiptHeaderPage=upiReceiptHeaderPage;
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
	
	

	
	@Then("^the ITL should be generated for putaway relocated stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_putaway_relocated_stock_in_inventory_transaction()
			throws Throwable {
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
				// if (isITLExists == false) {
			}
		}

		Assert.assertTrue("Inventory Transaction details are not displayed as expected for putaway. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	
	@When("^the inventory is unlocked and the return stock is over received$")
	public void the_inventory_is_unlocked_and_the_return_stock_is_over_received()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
ArrayList<String> failureList = new ArrayList<String>();

		jdaHomePage.navigateToInventoryTransactionPage();
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Adjustment");
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
		inventoryTransactionQueryPage.enterUpdateQuantity(String.valueOf(context.getQtyOnHand()));
		inventoryTransactionQueryPage.enterTransactionDate();
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jDAFooter.clickExecuteButton();
		if (!inventoryTransactionQueryPage.isNoRecords()) {
			failureList.add("No Records available for "+context.getUpiId()+"in adjustment");
		}
		
		inventoryTransactionQueryPage.clickMiscellaneousTab();
		context.setReasonCode(inventoryTransactionQueryPage.getReasonCode());
		
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		verification.verifyData("Quantity not over received",String.valueOf(context.getQtyOnHand()),
				inventoryTransactionDB.getUpdateQtyUnlocked(context.getSkuId(),context.getUpiId(),date),
				failureList);
		verification.verifyData("Inventory not unlocked","UnLocked",
				inventoryTransactionDB.getLockStatus(context.getUpiId(),"Inv UnLock",date),
				failureList);
		if(context.getReasonCode().equalsIgnoreCase("RMS - Over receipt with movement label"))
		{
		verification.verifyData("Inventory lock code mismatch after adjustment","OVERHUMOV",
				inventoryTransactionDB.getReasonCode(context.getUpiId(),"Adjustment","DMGD", date),
				failureList);
		}
		else if(context.getReasonCode().equalsIgnoreCase("RMS - Over receipt without movement label"))
		{
			verification.verifyData("Inventory lock code mismatch after adjustment","OVERHUNOMV",
					inventoryTransactionDB.getReasonCode(context.getUpiId(),"Adjustment","DMGD", date),
					failureList);
		}
		Assert.assertTrue("Reason code and inventory lock is not as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}

	
	
		
		@Then("^the goods receipt should be generated for the multiple stock received in inventory transaction$")
		public void the_goods_receipt_should_be_generated_for_the_multiple_stock_received_in_inventory_transaction() throws Throwable {
			ArrayList<String> failureList = new ArrayList<String>();
			poMap = context.getPOMap();
			upiMap = context.getUPIMap();
			String date = DateUtils.getCurrentSystemDateInDBFormat();
			for(int j=0;j<context.getPreAdviceList().size();j++)
			{
				context.setPreAdviceId(context.getPreAdviceList().get(j));
				context.setUpiId(context.getUpiList().get(j));
				
				for (int i = context.getLineItem(); i <= Integer.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(j))); i++) {
					context.setRcvQtyDue(Integer.parseInt(context.getMultiplePOMap().get(context.getPreAdviceList().get(j)).get(i).get("QTY DUE")));
					context.setSkuId(context.getMultiplePOMap().get(context.getPreAdviceList().get(j)).get(i).get("SKU"));
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
			}
			Assert.assertTrue("Inventory Transaction details are not displayed as expected. ["
					+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
		}
	@Then("^the goods receipt should be generated for FSV PO received stock in inventory transaction$")
	public void the_goods_receipt_should_be_generated_for_fsv_PO_received_stock_in_inventory_transaction() throws Throwable {
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
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			verification.verifyData("From Location for SKU " + context.getSkuId(), context.getFromLocation(),
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
	
	@Then("^the reason code and uploaded should be updated$")
	public void the_reason_code_and_uploaded_should_be_updated() throws Throwable {
		
		ArrayList<String> failureList = new ArrayList<String>();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setUploaded(inventoryTransactionDB.getUploadedValueUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date));
		System.out.println(inventoryTransactionDB.getUploadedValueUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date));
		System.out.println(inventoryTransactionDB.getReasonCodeUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date));
//		if(!(context.getUploaded().equalsIgnoreCase("Y")))
//		{
//				failureList.add("Uploaded field not updated as expected "+context.getUpiId());
//		}
//		
		if(context.getReasonCode().equalsIgnoreCase("RMS - Unexpected receipt with movement label"))
		{
		verification.verifyData("Reason code not updated as expected","NADVMVNOHU",
				inventoryTransactionDB.getReasonCodeUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date),
				failureList);
		}
		
		else if(context.getReasonCode().equalsIgnoreCase("RMS - Unexpected receipt without movement label"))
		{
			verification.verifyData("Reason code not updated as expected","NANOHUNMV",
					inventoryTransactionDB.getReasonCodeUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date),
					failureList);
		}
		
		else if(context.getReasonCode().equalsIgnoreCase("RMS  Non advised receipt with movement label"))
		{
			verification.verifyData("Reason code not updated as expected","NADVHUMOV",
					inventoryTransactionDB.getReasonCodeUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date),
					failureList);
		}
		
		//
		else if(context.getReasonCode().equalsIgnoreCase("RMS - Non advised receipt without movement label"))
		{
			verification.verifyData("Reason code not updated as expected","NADVHUNOMV",
					inventoryTransactionDB.getReasonCodeUnlocked(context.getUpiId(),context.getTagId(),"Adjustment",date),
					failureList);
		}
		Assert.assertTrue("Reason code and inventory lock is not as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
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
		ArrayList lockCodeListForAllQuantity =inventoryTransactionDB.getLockCodeList(context.getUpiId(),"Inv Lock");
		for(int l=0;l<lockCodeListForAllQuantity.size();l++){
			if (!((String)lockCodeListForAllQuantity.get(l)).equalsIgnoreCase("DMGD")){
				failureList.add("ITL Lock code does not match for Quantity number "+l);
			}
		}
		//TODO
		Assert.assertTrue(
				"ITL is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
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
		if (context.getRcvQtyDue()==inventoryTransactionDB.getReceiptCount(context.getUpiId(),"Inv Lock")){
			failureList.add("ITL Records received mismatch with total qty");
		}
		Assert.assertTrue(
				"ITL is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
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
		Assert.assertTrue("Lock code not displayed as expected",inventoryTransactionQueryPage.checkDamagedReceiptLockCode(lockCode));
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
		System.out.println(context.getRcvQtyDue());
		System.out.println(context.getRcvQtyDue());
		inventoryTransactionDB.getReceiptCount(context.getUpiId(), code);
		Assert.assertEquals("ITL not updated",context.getRcvQtyDue(),inventoryTransactionDB.getReceiptCount(context.getUpiId(), code));
	}
	
	@When("^the inventory transaction should be updated for multiple upi$")
	public void the_inventory_transaction_should_be_updated_for_multiple_upi() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt");
		inventoryTransactionQueryPage.enterReferenceId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		String code = "Receipt";
		System.out.println(context.getRcvQtyDue());
		System.out.println(context.getRcvQtyDue());
		inventoryTransactionDB.getReceiptCount(context.getUpiId(), code);
		int receiptCount=0;
		
		for(int i=0;i<context.getUpiList().size();i++)
		{
			receiptCount+=inventoryTransactionDB.getReceiptCount(context.getUpiList().get(i), code);
		}
		Assert.assertEquals("ITL not updated",context.getRcvQtyDue(),receiptCount);
	}
	
	@When("^I search with sku and reason code$")
	public void i_search_with_sku_and_reason_code() throws Throwable {
		jDAFooter.clickQueryButton();
		jDAFooter.pressTab();
		inventoryTransactionQueryPage.enterSkuId(context.getSkuId());
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
