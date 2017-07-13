package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryTransactionDB;
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
	private InventoryTransactionDB inventoryTransactionDB;

	@Inject
	public ReceiptReversalStepDefs(ReceiptReversalPage receiptReversalPage, JDAFooter jDAFooter,
			UPIReceiptLineDB uPIReceiptLineDB, Context context, JdaHomePage jdaHomePage,
			InventoryTransactionQueryPage inventoryTransactionQueryPage,
			InventoryTransactionDB inventoryTransactionDB) {
		this.receiptReversalPage = receiptReversalPage;
		this.jDAFooter = jDAFooter;
		this.uPIReceiptLineDB = uPIReceiptLineDB;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.inventoryTransactionDB = inventoryTransactionDB;

	}

	@When("^I do receipt reversal for the tag received$")
	public void i_do_receipt_reversal_for_the_tag_received() throws Throwable {
		receiptReversalPage.selectReceiptType("Pre-Advice");
		Thread.sleep(1000);
		receiptReversalPage.enterTagId(context.getUpiId());
		jDAFooter.clickNextButton();
		receiptReversalPage.check_the_checkbox();
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		jDAFooter.clickDoneButton();
		Thread.sleep(2000);
		jDAFooter.PressEnter();
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
		Assert.assertTrue("Receipt Reversion failed",
				receiptReversalPage.check_RefeID_with_PreadviceID(reference_Id, context.getPreAdviceId()));
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
		System.out.println(reference_Id);
		System.out.println(context.getPreAdviceId());
		Assert.assertTrue("Receipt Reversion failed", receiptReversalPage.check_RefeID_with_PreadviceID_lockcode(
				reference_Id, context.getPreAdviceId(), lockcode, context.getLockCode()));
	}
}
