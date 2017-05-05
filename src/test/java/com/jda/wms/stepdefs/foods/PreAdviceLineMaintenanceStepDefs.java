package com.jda.wms.stepdefs.foods;

import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.WarningPopUpPage;
import com.jda.wms.pages.foods.PreAdviceLineMaintenancePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceLineMaintenanceStepDefs {
	private final PreAdviceLineMaintenancePage preAdviceLineMaintenancePage;
	private final JdaHomePage jdaHomePage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private WarningPopUpPage warningPopUpPage;
	private Context context;

	@Inject
	public PreAdviceLineMaintenanceStepDefs(PreAdviceLineMaintenancePage preAdviceLineMaintenancePage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs, WarningPopUpPage warningPopUpPage, Context context,JdaHomePage jdaHomePage) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.warningPopUpPage = warningPopUpPage;
		this.context = context;
		this.preAdviceLineMaintenancePage = preAdviceLineMaintenancePage;
		this.jdaHomePage = jdaHomePage;
	}

	@When("^I search the pre-advice id \"([^\"]*)\" and SKU id \"([^\"]*)\" in pre-advice line maintenance page$")
	public void i_search_pre_advice_id_and_sku_id(String preAdviceId, String skuId) throws Throwable {
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceId(preAdviceId);
		preAdviceLineMaintenancePage.enterSKUId(skuId);
		jdaFooter.clickExecuteButton();
	}

	@When("^I lock the record with lockcode as \"([^\"]*)\"$")
	public void i_lock_the_record_with_lockcode_as(String lockCode) throws Throwable {
		jdaFooter.clickUpdateButton();
		preAdviceLineMaintenancePage.enterLockCode(lockCode);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
		warningPopUpPage.clickYes();
	}

	@Then("^the record should be locked$")
	public void the_record_should_be_locked() throws Throwable {
		Assert.assertEquals("Lock code is not displayed as expected.", context.getLockCode(),
				preAdviceLineMaintenancePage.getLockCode());
	}
	@Given("^the sku \"([^\"]*)\" of pre-advice id \"([^\"]*)\" have the pallet type as \"([^\"]*)\"$")
	public void the_sku_of_pre_advice_id_have_the_pallet_type_as(String sku, String preAdviceId,
			String existingPalletType) throws Throwable {
		jdaHomePage.navigateToPreAdviceLinePage();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceId(preAdviceId);
		preAdviceLineMaintenancePage.enterSKUId(sku);
		jdaFooter.clickExecuteButton();
		
		if (!existingPalletType.equals(preAdviceLineMaintenancePage.getPalletType())) {
			jdaFooter.clickUpdateButton();
			preAdviceLineMaintenancePage.enterPalletType(existingPalletType);
			jdaFooter.clickExecuteButton();
			warningPopUpPage.clickYes();
			Thread.sleep(3000);
		}
	}

	@When("^I update the pallet type as \"([^\"]*)\"$")
	public void i_update_the_pallet_type_as(String palletType) throws Throwable {
		context.setPalletType(palletType);
		jdaFooter.clickUpdateButton();
		preAdviceLineMaintenancePage.enterPalletType(palletType);
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
		Thread.sleep(3000);
	}

	@Then("^the pallet type should be updated")
	public void the_pallet_type_should_be_updated() throws Throwable {
		Assert.assertEquals("Pallet type is not as expected", context.getPalletType(),
				preAdviceLineMaintenancePage.getPalletType());
	}
}


