package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.MoveTaskListGenerationPage;
import com.jda.wms.pages.gm.TrailerShippingPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TrailerShippingStepDefs {
	private JDAFooter jdaFooter;
	private JdaHomePage jdaHomePage;
	private JdaLoginPage jdaLoginPage;
	private Context context;
	private TrailerShippingPage trailerShippingPage;
	private OrderHeaderDB orderHeaderDB;
	private Verification verification;

	@Inject
	public TrailerShippingStepDefs(JDAFooter jdaFooter, TrailerShippingPage trailerShippingPage,
			JdaHomePage jdaHomePage, JdaLoginPage jdaLoginPage, Context context, OrderHeaderDB orderHeaderDB,
			Verification verification) {
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.jdaLoginPage = jdaLoginPage;
		this.context = context;
		this.trailerShippingPage = trailerShippingPage;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
	}

	@Given("^I enter the siteId and shipping details$")
	public void i_enter_the_siteId_and_shipping_details() throws Throwable {
		jdaFooter.PressEnter();
		trailerShippingPage.enterSiteId("3366");
		jdaFooter.PressEnter();
		String trailerNo = "10229";
		trailerShippingPage.enterTrailer(trailerNo);
		jdaFooter.clickNextButton();
		Thread.sleep(8000);
		String SealNo = Utilities.getFiveDigitRandomNumber();
		trailerShippingPage.enterSealNo(SealNo);
		trailerShippingPage.clickOne();
		trailerShippingPage.clickOk();
		Assert.assertTrue("picking completion is not as expected", trailerShippingPage.isRecordDisplayed());
		jdaFooter.clickDoneButton();

	}

	@Then("^Shipping message should be displayed$")
	public void shipping_message_should_be_displayed() throws Throwable {
		Assert.assertTrue("Shipping completion is not as expected", trailerShippingPage.isConfirmationMsgDisplayed());
		jdaFooter.clickDoneButton();
	}
	@Then("^Multiple trailer should be shipped$")
	public void multiple_trailer_should_be_shipped() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaFooter.PressEnter();
		for (int i = 0; i < context.getTrailerList().size(); i++) {
			context.setTrailerNo(context.getTrailerList().get(i));
			Thread.sleep(2000);
			jdaFooter.pressTab();
			Thread.sleep(1000);
			jdaFooter.pressTab();
			Thread.sleep(1000);
			trailerShippingPage.enterSiteID(context.getSiteID());
			jdaFooter.pressTab();
			jdaFooter.deleteExistingContent();
			trailerShippingPage.enterTrailerNumber(context.getTrailerNo());
			jdaFooter.clickNextButton();
			String Sealno = Utilities.getFourDigitRandomNumber();
			trailerShippingPage.enterSealNo(Sealno);
			trailerShippingPage.clickOkButton();
			Thread.sleep(5000);
			jdaFooter.clickDoneButton();
		}
		Thread.sleep(3000);
		verification.verifyData("Order Status", "Shipped", orderHeaderDB.getStatus(context.getOrderId()), failureList);

		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
}
