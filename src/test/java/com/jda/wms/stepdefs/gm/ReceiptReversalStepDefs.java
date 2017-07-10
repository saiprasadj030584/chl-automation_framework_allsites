package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.jda.wms.context.Context;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.ReceiptReversalPage;

import cucumber.api.java.en.When;

public class ReceiptReversalStepDefs {
	private ReceiptReversalPage receiptReversalPage;
	private JDAFooter jDAFooter;
	private UPIReceiptLineDB uPIReceiptLineDB;
	private Context context;
	private JdaHomePage jdaHomePage;
	private InventoryTransactionQueryPage inventoryTransactionQueryPage;
	
	public ReceiptReversalStepDefs(ReceiptReversalPage receiptReversalPage,JDAFooter jDAFooter,UPIReceiptLineDB uPIReceiptLineDB,Context context,JdaHomePage jdaHomePage,InventoryTransactionQueryPage inventoryTransactionQueryPage) {
		this.receiptReversalPage = receiptReversalPage;
		this.jDAFooter=jDAFooter;
		this.uPIReceiptLineDB=uPIReceiptLineDB;
		this.context=context;
		this.jdaHomePage=jdaHomePage;
		this.inventoryTransactionQueryPage=inventoryTransactionQueryPage;
		
	}

	
	
	
	@When ("^I do normal receipt reversal for the tag received$")
	public void i_do_normal_receipt_reversal_for_the_tag_received() throws Throwable {
		
		
				//String tagId=uPIReceiptLineDB.fetchTagId(context.getUpiId());
				//context.setTagId(tagId);
		receiptReversalPage.selectReceiptType("Pre-Advice");
		Thread.sleep(1000);
		//receiptReversalPage.enterTagId(Context.getTagId());//create context tag id
		receiptReversalPage.enterTagId(context.getUpiId());
		jDAFooter.clickNextButton();
		receiptReversalPage.check_the_checkbox();
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
		Thread.sleep(2000);
		jDAFooter.PressEnter();
	}
	
	
	
	@When ("^the reversed receipt should be updated in inventory transaction page$")
	public void the_reversed_receipt_should_be_updated_in_inventory_transaction_page() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
		receiptReversalPage.checkReversalUpdationInventory(context.getUpiId());
		jDAFooter.clickQueryButton();
		inventoryTransactionQueryPage.enterCode("Receipt Reversal");
		inventoryTransactionQueryPage.enterTagId(context.getUpiId());
		jDAFooter.clickExecuteButton();
		Assert.assertTrue("Receipt Reversion failed", inventoryTransactionQueryPage.isNoRecords());
	}
}
