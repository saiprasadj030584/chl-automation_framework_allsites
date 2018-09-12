package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.pages.Exit.InventoryQueryPage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.ReceiptReversalPage;
import com.jda.wms.pages.Exit.WarningPopUpPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReceiptReversalStepDefs {
	private final InventoryQueryPage inventoryQueryPage;
	private final InventoryDB inventoryDB;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final Context context;
	private final ReceiptReversalPage receiptReversalPage;
	private final WarningPopUpPage warningPopUpPage;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public ReceiptReversalStepDefs(InventoryQueryPage inventoryQueryPage, JDAFooter jdaFooter, Context context,
			JdaHomePage jdaHomePage, ReceiptReversalPage receiptReversalPage, WarningPopUpPage warningPopUpPage,InventoryDB inventoryDB) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.receiptReversalPage = receiptReversalPage;
		this.warningPopUpPage = warningPopUpPage;
		this.inventoryDB =inventoryDB;
	}

	@Given("^I have a tag id received at \"([^\"]*)\" zone$")
	public void i_have_a_tag_id_received_at_zone(String zone) throws Throwable {
		ArrayList failureList = new ArrayList();
		String tagID = inventoryDB.getTagId(zone);
		System.out.println("tag ID is "+ tagID);
		if (!tagID.contains("Exhausted Resultset")){
		context.setTagId(tagID);
		context.setLocationZone(zone);
		
		int qtyOnHand_zone = Integer.parseInt(inventoryDB.getQtyOnHand_zone(tagID,zone));
		context.setQtyOnHand_zone(qtyOnHand_zone);

		String sku = inventoryDB.getSkuId(tagID,zone);
		context.setSkuId(sku);

		int caseRatio = Integer.parseInt(inventoryDB.getCaseRatio(tagID));
		context.setCaseRatio(caseRatio);

		logger.debug("Before performing Receipt reversal - TAG ID: " + tagID + ", SKU: " + sku + ", Qty on Hand : "
				+ qtyOnHand_zone);
		}
		else{
			failureList.add("No Tag ID found for "+zone+" zone");
		}
		Assert.assertTrue(
				"Test Data Issue. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I select receipt type as \"([^\"]*)\" and enter the tag id$")
	public void select_Receipt_Type_as_and_enter_the_tag_id_as(String receiptType) throws Throwable {
		receiptReversalPage.selectReceiptType(receiptType);
		receiptReversalPage.enterTagId(context.getTagId());
	}

	@When("^I navigate to next screen$")
	public void i_navigate_to_next_screen() throws Throwable {
		jdaFooter.clickNextButton();
	}

	@Then("^the inventory details should be displayed in reversals tab$")
	public void corresponding_inventory_information_should_be_displayed_in_reversals_screen() throws Throwable {
		Assert.assertTrue("Record is not present in reversal screen", receiptReversalPage.isRecordExists());
		Thread.sleep(1000);
	}

	@When("^I enter the quantity (\\d+) to reverse$")
	public void i_enter_the_Qty_to_reverse_as_and_navigate_to_finish_screen(int qtyReverse) throws Throwable {
		context.setQtyReverse(qtyReverse);
		System.out.println(qtyReverse % context.getCaseRatio() == 0); 
		System.out.println(qtyReverse);
		System.out.println(context.getCaseRatio());
		Assert.assertTrue("Quantity to reverse is not in multiples of case ratio",
				(qtyReverse % context.getCaseRatio() == 0));
		for (int i = 0; i < 2; i++) {
			receiptReversalPage.scrollNext();
		}
		receiptReversalPage.enterQtyToReverse(qtyReverse);
		jdaFooter.clickNextButton();
	}

	@When("^I proceed to reverse the quantity$")
	public void proceed_to_reverse_the_quantity() throws Throwable {
		jdaFooter.clickDoneButton();
		warningPopUpPage.clickYes();
	}
}
