package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.PopUpPage;
import com.jda.wms.pages.foods.PreAdviceLinePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceLineStepDefs {
	private final PreAdviceLinePage preAdviceLinePage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final PopUpPage popUpPage;
	private final Context context;

	@Inject
	public PreAdviceLineStepDefs(PreAdviceLinePage preAdviceLinePage, JDAFooter jdaFooter, JdaHomePage jdaHomePage,
			JDAHomeStepDefs jdaHomeStepDefs, PopUpPage popUpPage, Context context) {
		this.preAdviceLinePage = preAdviceLinePage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.popUpPage = popUpPage;
		this.context = context;
	}

	@Given("^the sku \"([^\"]*)\" of pre-advice id \"([^\"]*)\" have the pallet type as \"([^\"]*)\"$")
	public void the_sku_of_pre_advice_id_have_the_pallet_type_as(String sku, String preAdviceId,
			String existingPalletType) throws Throwable {
		jdaHomePage.navigateToPreAdviceLinePage();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceID(preAdviceId);
		preAdviceLinePage.enterSkuID(sku);
		jdaFooter.clickExecuteButton();
		if (!existingPalletType.equals(preAdviceLinePage.getPalletType())) {
			jdaFooter.clickUpdateButton();
			preAdviceLinePage.enterPalletType(existingPalletType);
			jdaFooter.clickExecuteButton();
			popUpPage.clickYes();
			Thread.sleep(3000);
		}
	}

	@When("^I update the pallet type as \"([^\"]*)\"$")
	public void i_update_the_pallet_type_as(String palletType) throws Throwable {
		context.setPalletType(palletType);
		jdaFooter.clickUpdateButton();
		preAdviceLinePage.enterPalletType(palletType);
		jdaFooter.clickExecuteButton();
		popUpPage.clickYes();
		Thread.sleep(3000);
	}

	@Then("^the pallet type should be updated")
	public void the_pallet_type_should_be_updated() throws Throwable {
		Assert.assertEquals("Pallet type is not as expected", context.getPalletType(),
				preAdviceLinePage.getPalletType());
	}

}
