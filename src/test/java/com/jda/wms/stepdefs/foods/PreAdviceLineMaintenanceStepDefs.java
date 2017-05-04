package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaLoginPage;
import com.jda.wms.pages.foods.PopUpPage;
import com.jda.wms.pages.foods.PreAdviceLinePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceLineMaintenanceStepDefs {
	private final PreAdviceLinePage preAdviceLinePage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private PopUpPage popUpPage;
	private Context context;
	private JdaLoginPage jdaLoginPage;

	@Inject
	public PreAdviceLineMaintenanceStepDefs(PreAdviceLinePage preAdviceLinePage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs, PopUpPage popUpPage, Context context) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.popUpPage = popUpPage;
		this.context = context;
		this.preAdviceLinePage = preAdviceLinePage;
	}

	@When("^I search the pre-advice id \"([^\"]*)\" and SKU id \"([^\"]*)\" in pre-advice line maintenance page$")
	public void i_search_pre_advice_id_and_sku_id(String preAdviceId, String skuId) throws Throwable {
		// jdaLoginPage.login();
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceId(preAdviceId);
		preAdviceLinePage.enterSKUId(skuId);
		jdaFooter.clickExecuteButton();
	}

	@When("^I lock the record with lockcode as \"([^\"]*)\"$")
	public void i_lock_the_record_with_lockcode_as(String lockCode) throws Throwable {
		jdaFooter.clickUpdateButton();
		preAdviceLinePage.enterLockCode(lockCode);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
		popUpPage.clickYes();
	}

	@Then("^the record should be locked$")
	public void the_record_should_be_locked() throws Throwable {
		Assert.assertEquals("Lock code is not displayed as expected.", context.getLockCode(),
				preAdviceLinePage.getLockCode());
	}
}
