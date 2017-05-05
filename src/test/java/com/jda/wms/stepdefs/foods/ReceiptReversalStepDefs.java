package com.jda.wms.stepdefs.foods;

import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.ReceiptReversalPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReceiptReversalStepDefs {

	private final InventoryQueryPage inventoryQueryPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final Context context;
	private final ReceiptReversalPage receiptReversalPage;

	@Inject
	public ReceiptReversalStepDefs(InventoryQueryPage inventoryQueryPage, JDAFooter jdaFooter, Context context,
			JdaHomePage jdaHomePage, ReceiptReversalPage receiptReversalPage) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.receiptReversalPage = receiptReversalPage;
	}

	@Given("^I have received tag Id \"([^\"]*)\"$")
	public void i_have_received_tag_Id(String tagId) throws Throwable {
		int qtyOnHand = inventoryQueryPage.getQtyOnhand();
		context.setQtyOnHand(qtyOnHand);
		jdaHomePage.navigateToInventoryQueryPage();
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();

		String sku = inventoryQueryPage.getSkuId();
		context.setSkuId(sku);
		
		int caseRatio = Utilities.convertStringToInteger(inventoryQueryPage.getCaseRatio());
		context.setCaseRatio(caseRatio);
		
		String locationZone = inventoryQueryPage.getLocationZone();
		Assert.assertEquals("Location is not an inbound location", "INBOUND", locationZone);
	}

	@When("^select Receipt Type as \"([^\"]*)\" and enter the tag id as \"([^\"]*)\"$")
	public void select_Receipt_Type_as_and_enter_the_tag_id_as(String receiptType, String tagId) throws Throwable {
		receiptReversalPage.selectReceiptType(receiptType);
		receiptReversalPage.enterTagId(tagId);

	}

	@When("^I navigate to next screen$")
	public void i_navigate_to_next_screen() throws Throwable {
		jdaFooter.clickNextButton();
	}

	@Then("^corresponding inventory information should be displayed in reversals screen$")
	public void corresponding_inventory_information_should_be_displayed_in_reversals_screen() throws Throwable {
		Assert.assertTrue("Record is not present in reversal screen", receiptReversalPage.isRecordExists());
	}

	@When("^I enter the Qty to reverse as \"([^\"]*)\" and navigate to finish screen$")
	 public void
	 i_enter_the_Qty_to_reverse_as_and_navigate_to_finish_screen(String qtyToReverse)
	 throws Throwable {
		 
		//To verify whether the quantity to reverse is in multiples of case ratio
		int caseRatio = context.getCaseRatio();
		Assert.assertTrue("Quantity to reverse is not in multiples of case ratio", receiptReversalPage.isQtyMultipleOfCaseRatio(caseRatio));
		 receiptReversalPage.scrollNext();
		 receiptReversalPage.enterQtyToReverse(qtyToReverse);
		 jdaFooter.clickNextButton();
		
	 }

	 @When("^I enter the reason code as \"([^\"]*)\"$")
	 public void i_enter_the_reason_code_as(String reasonCode) throws Throwable {
		 receiptReversalPage.enterReasonCode(reasonCode);
	 }
	
	 @When("^proceed to reverse the quantity$")
	 public void proceed_to_reverse_the_quantity() throws Throwable {
		 jdaFooter.clickDoneButton();
	 }
	
	// @Then("^Inventory should be updated with the new updated quantity$")
	// public void inventory_should_be_updated_with_the_new_updated_quantity()
	// throws Throwable {
	// }
	//
	// @Then("^I(\\d+) ITL should be generated for the receipt reversal$")
	// public void i_ITL_should_be_generated_for_the_receipt_reversal(int arg1)
	// throws Throwable {
	// }
	//
	//
	//
}
