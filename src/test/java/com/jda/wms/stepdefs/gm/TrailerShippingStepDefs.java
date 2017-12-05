package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.MoveTaskListGenerationPage;
import com.jda.wms.pages.gm.TrailerShippingPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TrailerShippingStepDefs {
	private JDAFooter jDAFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private Verification verification;
	private TrailerShippingPage trailerShippingPage;

	@Inject
	public TrailerShippingStepDefs(JDAFooter jDAFooter, Verification verification, Context context,
			MoveTaskListGenerationPage moveTaskListGenerationPage, TrailerShippingPage trailerShippingPage) {
		this.jDAFooter = jDAFooter;
		this.verification = verification;
		this.context = context;
		this.trailerShippingPage = trailerShippingPage;
	}

	@Given("^I enter the siteId and shipping details$")
	public void i_enter_the_siteId_and_shipping_details() throws Throwable {
		jDAFooter.PressEnter();
		trailerShippingPage.enterSiteId("3366");
		jDAFooter.PressEnter();
		String trailerNo = "10229";
		trailerShippingPage.enterTrailer(trailerNo);
		jDAFooter.clickNextButton();
		Thread.sleep(8000);
		String SealNo = Utilities.getFiveDigitRandomNumber();
		trailerShippingPage.enterSealNo(SealNo);
		trailerShippingPage.clickOne();
		trailerShippingPage.clickOk();
		Assert.assertTrue("picking completion is not as expected", trailerShippingPage.isRecordDisplayed());
		jDAFooter.clickDoneButton();

	}

	@Then("^Shipping message should be displayed$")
	public void shipping_message_should_be_displayed() throws Throwable {
		Assert.assertTrue("Shipping completion is not as expected", trailerShippingPage.isConfirmationMsgDisplayed());
		jDAFooter.clickDoneButton();
	}
}
