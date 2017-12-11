package com.jda.wms.stepdefs.gm;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.ClusteringPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClusteringStepDefs {
	private JDAFooter jDAFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private Verification verification;
	private ClusteringPage clusteringPage;

	@Inject
	public ClusteringStepDefs(JDAFooter jDAFooter, Verification verification, Context context,ClusteringPage clusteringPage) {
		this.jDAFooter = jDAFooter;
		this.verification = verification;
		this.context = context;
		this.clusteringPage=clusteringPage;
	}

	@When("^I proceed with clustering$")
	public void i_proceed_with_clustering() throws Throwable {
		clusteringPage.enterSiteID(context.getSiteID());
		jDAFooter.PressEnter();
		jDAFooter.pressTab();
		clusteringPage.enterGroupId("RETAIL");
		Thread.sleep(1000);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}

	public void i_proceed_with_clustering_for(String string) throws Throwable {
		clusteringPage.enterSiteID(context.getSiteID());
		jDAFooter.PressEnter();
		jDAFooter.pressTab();
		clusteringPage.enterGroupId(string);
		Thread.sleep(1000);
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
	}
}