package com.jda.wms.stepdefs.gm;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.MoveTaskDB;
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
private MoveTaskDB movetask;
private JDAHomeStepDefs jdaHomePagestepdef;


@Inject
public ClusteringStepDefs(JDAHomeStepDefs jdaHomePagestepdef, JDAFooter jDAFooter,MoveTaskDB movetask, Verification verification, Context context,ClusteringPage clusteringPage) {
this.jDAFooter = jDAFooter;
this.verification = verification;
this.context = context;
this.clusteringPage=clusteringPage;
this.movetask=movetask;
this.jdaHomePagestepdef=jdaHomePagestepdef;
}

@When("^I check clustering$")
public void I_check_clustering() throws Throwable {
	context.setFromLocation(movetask.getLocationId(context.getOrderId()));
	System.out.println("Allocated from location : "+context.getFromLocation());
	if(movetask.getListIdString(context.getOrderId())==null)
	{
		jdaHomePagestepdef.i_navigate_to_JDA_page("clustering");
		i_proceed_with_clustering();
	}
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