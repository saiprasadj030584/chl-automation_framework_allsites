package com.jda.wms.stepdefs.foods;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.ReceiptReversalPage;
import com.jda.wms.pages.foods.WarningPopUpPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReceiptReversalStepDefs {
	private final InventoryQueryPage inventoryQueryPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final Context context;
	private final ReceiptReversalPage receiptReversalPage;
	private final WarningPopUpPage warningPopUpPage;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public ReceiptReversalStepDefs(InventoryQueryPage inventoryQueryPage, JDAFooter jdaFooter, Context context,
			JdaHomePage jdaHomePage, ReceiptReversalPage receiptReversalPage, WarningPopUpPage warningPopUpPage) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.receiptReversalPage = receiptReversalPage;
		this.warningPopUpPage = warningPopUpPage;
	}

	@Given("^I have received tag Id \"([^\"]*)\"$")
	public void i_have_received_tag_Id(String tagId) throws Throwable {
		jdaHomePage.navigateToInventoryQueryPage();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		context.setTagId(tagId);

		int qtyOnHand = Integer.parseInt(inventoryQueryPage.getQtyOnHand());
		context.setQtyOnHand(qtyOnHand);

		String sku = inventoryQueryPage.getSkuId();
		context.setSkuId(sku);

		int caseRatio = Integer.parseInt(inventoryQueryPage.getCaseRatio());
		context.setCaseRatio(caseRatio);

		Assert.assertEquals("Location is not as expected. ", "INBOUND", inventoryQueryPage.getLocationZone());
		logger.debug("Before performing Receipt reversal - TAG ID: " + tagId + ", SKU: " + sku + ", Qty on Hand : "
				+ qtyOnHand);
	}

	@When("^I select receipt type as \"([^\"]*)\" and enter the tag id as \"([^\"]*)\"$")
	public void select_Receipt_Type_as_and_enter_the_tag_id_as(String receiptType, String tagId) throws Throwable {
		receiptReversalPage.selectReceiptType(receiptType);
		receiptReversalPage.enterTagId(tagId);
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
		Assert.assertTrue("Quantity to reverse is not in multiples of case ratio",
				qtyReverse % context.getCaseRatio() == 0);

		receiptReversalPage.scrollNext();
		for (int i = 0; i < 2; i++) {
			boolean qtyToReverse = receiptReversalPage.isQtyToReverseExists();
			if (qtyToReverse == true) {
				receiptReversalPage.enterQtyToReverse(qtyReverse);
				jdaFooter.clickNextButton();
			} else {
				receiptReversalPage.scrollNext();
			}
		}
	}

	@When("^I proceed to reverse the quantity$")
	public void proceed_to_reverse_the_quantity() throws Throwable {
		jdaFooter.clickDoneButton();
		warningPopUpPage.clickYes();
	}
}
