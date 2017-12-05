package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.TrailerDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.TrailerMaintenancePage;
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
	public TrailerShippingStepDefs(JDAFooter jdaFooter,TrailerShippingPage trailerShippingPage,
			JdaHomePage jdaHomePage, JdaLoginPage jdaLoginPage, Context context,OrderHeaderDB orderHeaderDB,
			Verification verification) {
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.jdaLoginPage = jdaLoginPage;
		this.context = context;
		this.trailerShippingPage=trailerShippingPage;
		this.orderHeaderDB=orderHeaderDB;
		this.verification = verification;
	}

	@Then("^I proceed for trailer shipping$")
	public void I_proced_for_trailer_shipping() throws Throwable {
		jdaFooter.PressEnter();
		Thread.sleep(2000);
		jdaFooter.pressTab();
		Thread.sleep(1000);
		jdaFooter.pressTab();
		Thread.sleep(1000);
		trailerShippingPage.enterSiteID(context.getSiteID());
		jdaFooter.pressTab();
		trailerShippingPage.enterTrailerNumber(context.getTrailerNo());
		jdaFooter.clickNextButton();
		String Sealno=Utilities.getFourDigitRandomNumber();
		trailerShippingPage.enterSealNo(Sealno);
		trailerShippingPage.clickOkButton();
		Thread.sleep(5000);
		jdaFooter.clickDoneButton();
		
	}
	@Then("^trailer shipping will be In Progress$")
	public void trailer_shipping_will_be_In_Progress() throws Throwable {
		jdaFooter.PressEnter();
		Thread.sleep(2000);
		jdaFooter.pressTab();
		Thread.sleep(1000);
		jdaFooter.pressTab();
		Thread.sleep(1000);
		trailerShippingPage.enterSiteID(context.getSiteID());
		jdaFooter.pressTab();
		trailerShippingPage.enterTrailerNumber(context.getTrailerNo());
		jdaFooter.clickNextButton();
		String Sealno=Utilities.getFourDigitRandomNumber();
		trailerShippingPage.enterSealNo(Sealno);
		trailerShippingPage.clickOkButton();
		Thread.sleep(5000);
		jdaFooter.clickDoneButton();
		
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			verification.verifyData("Order Status", "In Progress", orderHeaderDB.getStatus(context.getOrderId()),
					failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

}

