package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.CEConsignmentLinkingPage;
import com.jda.wms.pages.Exit.JdaHomePage;

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
		
		jdaFooter.clickNextButton();
		Thread.sleep(1000);
		jdaFooter.clickNextButton();
		Thread.sleep(1000);
		boolean isNoRowsSelected = ceConsignmentLinkingPage.isNoRowsDisplayed();
		Assert.assertFalse("Line items not displayed. No rows displayed.", isNoRowsSelected);
		if (!isNoRowsSelected){
		ceConsignmentLinkingPage.enterConsignmentID(context.getConsignmentID());
		jdaFooter.clickDoneButton();
		Thread.sleep(2000);
		}
	}
}
