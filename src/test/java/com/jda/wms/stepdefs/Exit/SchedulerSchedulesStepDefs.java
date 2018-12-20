package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SKUQueryPage;
import com.jda.wms.pages.Exit.SchedulerJobHistoryPage;
import com.jda.wms.pages.Exit.SchedulerSchedulesPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class SchedulerSchedulesStepDefs{
	
	private Context context;
	private PreAdviceLineDB preAdviceLineDB;
	private SchedulerJobHistoryPage schedulerJobHistoryPage;
	private SchedulerSchedulesPage schedulerSchedulesPage;
	@Inject
	public SchedulerSchedulesStepDefs(SchedulerSchedulesPage SchedulerSchedulesPage){
		this.schedulerSchedulesPage=schedulerSchedulesPage;	
		}
	
	@And("^Search for the name \"([^\"]*)\"$")
	public void search_for_the_name(String name) throws Throwable{
		schedulerSchedulesPage.clickQuery();
		schedulerSchedulesPage.enterSchedulerName(name);
		schedulerSchedulesPage.clickExcecute();
	}
	@Then("^go to calender Preview$")
	public void go_to_calender_preview() throws Throwable{
		schedulerSchedulesPage.clickQuery();
		schedulerSchedulesPage.calenderPreview();
	}
	
}
