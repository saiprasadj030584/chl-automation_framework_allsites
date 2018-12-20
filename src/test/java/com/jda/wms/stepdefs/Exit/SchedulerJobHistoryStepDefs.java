package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SKUQueryPage;
import com.jda.wms.pages.Exit.SchedulerJobHistoryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class SchedulerJobHistoryStepDefs{
	
	private Context context;
	private PreAdviceLineDB preAdviceLineDB;
	private SchedulerJobHistoryPage schedulerJobHistoryPage;
	@Inject
	public SchedulerJobHistoryStepDefs(SchedulerJobHistoryPage schedulerJobHistoryPage){
		this.schedulerJobHistoryPage=schedulerJobHistoryPage;	
		}

	
	@And("^Search for the Job \"([^\"]*)\"$")
	public void search_for_the_job(String Job) throws Throwable{
		schedulerJobHistoryPage.clickQuery();
		schedulerJobHistoryPage.enterSchedulerJob(Job);
		schedulerJobHistoryPage.clickExcecute();
	}
	
	@Then("^Validate the status as \"([^\"]*)\"$")
	public void validate_the_status_as(String status) throws Throwable{
		String Status=schedulerJobHistoryPage.getStatus();
		Assert.assertEquals("status not as expected", status, Status);
	}
}
