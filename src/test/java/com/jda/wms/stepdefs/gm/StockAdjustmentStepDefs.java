package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PopUpPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockAdjustmentStepDefs {
	private Context context;
	// private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private PopUpPage popUpPage;
	private JdaHomePage jDAHomePage;

	@Inject
	public StockAdjustmentStepDefs(Context context, JDAFooter jDAFooter, StockAdjustmentsPage stockAdjustmentsPage,
			PopUpPage popUpPage, JdaHomePage jDAHomePage) {
		this.context = context;
		// this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.popUpPage = popUpPage;
		this.jDAHomePage = jDAHomePage;
	}

	@When("^I create a new stock with siteid \"([^\"]*)\" and location \"([^\"]*)\"$")
	public void i_create_a_new_stock_with_siteid_and_location(String SiteId, String Location) throws Throwable {
		{
			stockAdjustmentsPage.selectNewStock();
			jDAFooter.clickNextButton();
			stockAdjustmentsPage.enterSkuId(context.getSkuId());
			stockAdjustmentsPage.enterOwnerId();
			stockAdjustmentsPage.enterClientId();
			stockAdjustmentsPage.enterSiteId(SiteId);
			stockAdjustmentsPage.enterLocation(Location);
			stockAdjustmentsPage.enterQuantityOnHand();
			stockAdjustmentsPage.enterPackConfig();
			stockAdjustmentsPage.clickMiscellaneousTab();
			stockAdjustmentsPage.enterPallet();
			jDAFooter.clickNextButton();
		}

	}

	@When("^I choose the reason code as \"([^\"]*)\"$")
	public void I_choose_the_reason_code_as(String ReasonCode) throws Throwable {

		stockAdjustmentsPage.chooseReasonCode(context.getReasonCode());
		jDAFooter.clickDoneButton();
		if (stockAdjustmentsPage.isPopUpDisplayed()) {

		}
	}

	@When("^I navigate to inventory transaction query page$")
	public void i_navigate_to_inventory_transaction_query_page() throws Throwable {
		jDAHomePage.navigateToInventoryTransactionQueryPage();
	}

	@When("^I choose the code as \"([^\"]*)\" and search the sku id$")
	public void i_choose_the_code_as_and_search_the_sku_id(String arg1) throws Throwable {

	}

	@Then("^the reason code should be updated\\$")
	public void the_reason_code_should_be_updated() throws Throwable {
		// Assert.assertEquals("inventory stock adjustment are not as expected",
		// context.getAdjustmentType()),
		// stockAdjustmentsPage.;
	}
}
