package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.Exit.TrailerShippingPage;

import cucumber.api.java.en.When;

public class TrailerShippingStepDefs {

	private final Context context;
	private final TrailerShippingPage trailerShippingPage;
	private final JDAFooter jdaFooter;

	@Inject
	public TrailerShippingStepDefs(JDAFooter jdaFooter, Context context, TrailerShippingPage trailerShippingPage) {
		this.context = context;
		this.trailerShippingPage = trailerShippingPage;
		this.jdaFooter = jdaFooter;
	}

	@When("^I enter the site id and trailer number$")
	public void I_enter_the_site_id_and_trailer_number() throws Throwable {
		trailerShippingPage.enterSiteID("9771");
		// trailerShippingPage.enterTrailerNo("14783");
		trailerShippingPage.enterTrailerNo(context.getTrailerNo());
		jdaFooter.clickNextButton();
	}

	@When("^I enter the seal number$")
	public void i_enter_the_seal_number() throws Throwable {
		Thread.sleep(5000);
		trailerShippingPage.enterSealNo();
		trailerShippingPage.clickOk();
		Thread.sleep(5000);
		jdaFooter.clickDoneButton();
	}

	

}
