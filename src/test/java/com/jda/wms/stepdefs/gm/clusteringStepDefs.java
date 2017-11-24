package com.jda.wms.stepdefs.gm;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.ClusteringPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class clusteringStepDefs {
	private JDAFooter jDAFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private Verification verification;
	private ClusteringPage clusteringPage;

	@Inject
	public clusteringStepDefs(JDAFooter jDAFooter, Verification verification, Context context,ClusteringPage clusteringPage) {
		this.jDAFooter = jDAFooter;
		this.verification = verification;
		this.context = context;
		this.clusteringPage=clusteringPage;
	}

	@When("^I proceed with clustering$")
	public void i_proceed_with_clustering() throws Throwable {
		clusteringPage.enterSiteID(context.getSiteId());
		jDAFooter.PressEnter();
		jDAFooter.pressTab();
		clusteringPage.enterGroupId("RETAIL");
		Thread.sleep(1000);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}
}