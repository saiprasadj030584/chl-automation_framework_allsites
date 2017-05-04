package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.CEConsignmentLinkingPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;

import cucumber.api.java.en.Given;

public class CEConsignmentLinkingStepDefs {
	
	private final CEConsignmentLinkingPage ceConsignmentLinkingPage;
	private final Context context;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;

	@Inject
	public CEConsignmentLinkingStepDefs(CEConsignmentLinkingPage ceConsignmentLinkingPage, Context context, JdaHomePage jdaHomePage, JDAFooter jdaFooter, AddressMaintenancePage addressMaintenancePage,AddressMaintenanceStepDefs addressMaintenanceStepDefs) {
		this.ceConsignmentLinkingPage = ceConsignmentLinkingPage;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
	}
	
	@Given("^I link the consignment to the pre-advice ID$")
	public void i_link_the_consignment_to_the_pre_advice_ID() throws Throwable {
		jdaHomePage.navigateToConsignmentLinkingPage();
		ceConsignmentLinkingPage.enterSiteID("9771");
		ceConsignmentLinkingPage.enterPreAdviceId(context.getPreAdviceId());
		ceConsignmentLinkingPage.enterConsignmentID(context.getConsignmentID());
	}
}
