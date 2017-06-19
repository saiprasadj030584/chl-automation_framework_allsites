package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.ClusteringPage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClusteringStepDefs {
	private ClusteringPage clusteringPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	
	@Inject
	public ClusteringStepDefs(ClusteringPage clusteringPage, JDAFooter jdaFooter,JDAHomeStepDefs jdaHomeStepDefs,Context context) {
		this.jdaFooter = jdaFooter;
		this.clusteringPage = clusteringPage;
		this.jdaHomeStepDefs =jdaHomeStepDefs;
		this.context = context;
	}

	@When("^I enter the site id \"([^\"]*)\" and group id \"([^\"]*)\"$")
	public void i_enter_the_site_id_and_group_id(String siteId, String groupId) throws Throwable {
		clusteringPage.enterSiteID(siteId);
		clusteringPage.enterGroupID(groupId);
	}

	@Then("^the record should be displayed in clustering$")
	public void the_record_should_be_displayed_in_clustering() throws Throwable {
		boolean isRecordExists = clusteringPage.isRecordExists();
		Assert.assertTrue(" Record not displayed ", isRecordExists);
		Thread.sleep(5000);
		jdaFooter.clickDoneButton();

	}
	
	@When("^I create list ids manually in clustering$")
	public void i_create_list_ids_manually_in_clustering() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
		i_enter_the_site_id_and_group_id("9771",context.getStoType());
		jdaFooter.clickNextButton();
		Thread.sleep(3000);
		the_record_should_be_displayed_in_clustering();
	}
}
