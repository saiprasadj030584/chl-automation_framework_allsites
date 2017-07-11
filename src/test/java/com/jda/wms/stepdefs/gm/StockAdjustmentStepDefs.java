package com.jda.wms.stepdefs.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PopUpPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;

import cucumber.api.java.en.When;

public class StockAdjustmentStepDefs {
	private Context context;
	// private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private PopUpPage popUpPage;
	private JdaHomePage jDAHomePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

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
	public void i_create_a_new_stock_with_siteid_and_location(String siteId, String location)
			throws FindFailed, InterruptedException {
		String owner = "M+S";
		String clientid = "M+S";
		String quantity = "100";
		String pallet = "PALLET";

		stockAdjustmentsPage.selectNewStock();
		jDAFooter.clickNextButton();
		stockAdjustmentsPage.enterSkuId(context.getSkuId());
		stockAdjustmentsPage.enterOwnerId(owner);
		stockAdjustmentsPage.enterClientId(clientid);
		stockAdjustmentsPage.enterSiteId(siteId);
		stockAdjustmentsPage.enterLocation(location);
		stockAdjustmentsPage.enterQuantityOnHand(quantity);
		stockAdjustmentsPage.enterPackConfig(context.getPackConfig());
		stockAdjustmentsPage.clickMiscellaneousTab();
		stockAdjustmentsPage.enterPallet(pallet);
		jDAFooter.clickNextButton();
	}

	@When("^I choose the reason code as \"([^\"]*)\"$")
	public void I_choose_the_reason_code_as(String reasonCode) throws Throwable {
		stockAdjustmentsPage.chooseReasonCode(reasonCode);
		jDAFooter.clickDoneButton();
		jDAFooter.PressEnter();
		jDAFooter.PressEnter();
		context.setReasonCode(reasonCode);
	}
}
