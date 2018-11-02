package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.SiteQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class SiteQueryStepDefs {

	private final JdaHomePage jdaHomepage;
	private final Context context;
	private final JDAFooter jdaFooter;
	private final SiteQueryPage siteQueryPage;
//	private SiteDB siteDB;
	

	@Inject
	public SiteQueryStepDefs(JdaHomePage jdaHomepage, Context context,JDAFooter jdaFooter,SiteQueryPage siteQueryPage
//			,SiteDB siteDB 
			) {
		this.jdaHomepage = jdaHomepage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.siteQueryPage=siteQueryPage;
//		this.siteDB=siteDB;
	}

	@And("^Click on Query$")
	public void click_on_Query() throws Throwable {
		jdaFooter.clickQueryButton();
	}
	
	@And("^Specify the SITE ID \"([^\"]*)\"$")
	public void specify_the_SITE_ID(String siteID) throws Throwable {
		siteQueryPage.enterSiteID(siteID);
	}
	
	@And("^click execute$")
	public void click_execute() throws Throwable {
		jdaFooter.clickExecuteButton();
	}
	@Then("^Verify whether the required fields been populated \"([^\"]*)\"$")
	public void validation_of_fields(String siteID) throws Throwable {
		siteQueryPage.TimeZone_Validation(siteID);
		siteQueryPage.UDT3_Validation(siteID);
		siteQueryPage.UDT4_Validation(siteID);
		siteQueryPage.UDN1_Validation(siteID);
		
	
		
		
		
	}
}
