package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import com.jda.wms.pages.foods.InventoryUpdatePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.SKUMaintenancePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryUpdateStepDefs {

	private final SKUMaintenancePage skuMaintenancePage;
	private final InventoryQueryPage inventoryQueryPage;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final InventoryUpdatePage inventoryUpdatePage;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private Context context;

	@Inject
	public InventoryUpdateStepDefs(SKUMaintenancePage skuMaintenancePage, InventoryQueryPage inventoryQueryPage,
			JDAFooter jdaFooter, InventoryTransactionQueryPage inventoryTransactionQueryPage, Context context,
			JdaHomePage jdaHomePage, InventoryUpdatePage inventoryUpdatePage) {
		this.skuMaintenancePage = skuMaintenancePage;
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaHomePage = jdaHomePage;
		this.inventoryUpdatePage = inventoryUpdatePage;
		this.jdaFooter = jdaFooter;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
	}

	@Given("^the inventory should be expired for SKU id \"([^\"]*)\"$")
	public void the_inventory_should_be_expired_for_SKU_id(String skuId) throws Throwable {
		jdaHomePage.navigateToSKUMaintanence();
		skuMaintenancePage.searchSKUid(skuId);
		Assert.assertEquals("Inventory is not expired", "EXPIRY", skuMaintenancePage.getAllocationGroup());
	}

	@Given("^the quantity on hand should be more than allocated quantity for tag id \"([^\"]*)\"$")
	public void the_quantity_on_hand_should_be_more_than_allocated_quantity_for_tag_id(String tagId) throws Throwable {
		jdaHomePage.navigateToInventoryQueryPage();
		inventoryQueryPage.searchTagId(tagId);
		int qtyOnHand = inventoryQueryPage.getQtyOnhand();
		int qtyAllocated = inventoryQueryPage.getQtyAllocated();
		if (qtyOnHand < qtyAllocated) {
			Assert.fail("Quantity Allocated should not be greater than quantity on hand. Quanitity on hand: "
					+ qtyOnHand + ", Quantity Allocated: " + qtyAllocated);
		}
	}

	@Given("^I select the type of invetnotry status as \"([^\"]*)\"$")
	public void i_select_the_type_of_invetnotry_status_as(String inventoryStatus) throws Throwable {
		inventoryUpdatePage.enterExpiryDateUpdate(inventoryStatus);
	}

	@Given("^I proceed to next$")
	public void i_proceed_to_next() throws Throwable {
		jdaFooter.clickNextButton();
	}

	@Given("^I select the expiry date in future and reason code as \"([^\"]*)\"$")
	public void i_select_the_expiry_date_in_future_and_reason_code_as(String reasonCode) throws Throwable {
		jdaFooter.clickNextButton();
		String FutureDate = inventoryUpdatePage.enterFutureDate();
		inventoryUpdatePage.selectReasonCode(reasonCode);
		context.setFutureExpiryDate(FutureDate);
	}

	@Given("^I proceed to complete the process$")
	public void i_proceed_to_complete_the_process() throws Throwable {
		jdaFooter.clickDoneButton();
	}

	@When("^I navigate to inventory transaction query$")
	public void i_navigate_to_inventory_transaction_query() throws Throwable {
		jdaHomePage.navigateToInventoryTransactionPage();
	}

	@Then("^I should see the future expiry date and reason code in the miscellaneous tab$")
	public void i_should_see_the_future_expiry_date_and_reason_code_in_the_miscellaneous_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		// Get Expiry Date
		String expiryDate = inventoryTransactionQueryPage.getExpiryDate();
		if (!context.getFutureExpiryDate().equals(expiryDate)) {
			failureList.add("Expiry Date is not displayed as expected. Expected [" + context.getFutureExpiryDate()
					+ "] but was [" + expiryDate + "]");
		}

		String reasonCode = inventoryTransactionQueryPage.getReasonCode();
		if (!reasonCode.equals("MINEXPIRY")) {
			failureList
					.add("Reason Code is not displayed as expected. Expected [MINEXPIRY] but was [" + reasonCode + "]");
		}

		Assert.assertTrue(
				"Inventory Transaction details are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I navigate to inventory update page$")
	public void i_navigate_to_inventory_update_page() throws Throwable {
		jdaHomePage.navigateToInventoryUpdate();
	}

	@When("^I navigate to search tab$")
	public void i_navigate_to_search_tab() throws Throwable {
		jdaFooter.clickNextButton();
	}

	@When("^I search the tag id \"([^\"]*)\"$")
	public void i_search_the_tag_id(String tagId) throws Throwable {
		inventoryUpdatePage.enterTagId(tagId);
	}

}
