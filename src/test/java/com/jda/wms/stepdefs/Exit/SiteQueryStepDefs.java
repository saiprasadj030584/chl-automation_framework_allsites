package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

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
	

	@Inject
	public SiteQueryStepDefs(JdaHomePage jdaHomepage, Context context,JDAFooter jdaFooter,SiteQueryPage siteQueryPage) 
			
	{
		this.jdaHomepage = jdaHomepage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.siteQueryPage=siteQueryPage;
	}

	
	@And("^Specify the SITE ID \"([^\"]*)\"$")
	public void specify_the_SITE_ID(String siteID) throws Throwable {
		siteQueryPage.enterSiteID(siteID);
	}
	
	@Then("^Verify whether the required fields been populated \"([^\"]*)\"$")
	public void validation_of_fields(String siteID) throws Throwable {
		siteQueryPage.TimeZone_Validation(siteID);
		siteQueryPage.UDT3_Validation(siteID);
		siteQueryPage.UDT4_Validation(siteID);
		siteQueryPage.UDN1_Validation(siteID);
	}
	@Then("^Verify the SiteID \"([^\"]*)\" displayed is tagged$")
	public void verify_the_SiteID_displayed_is_tagged(String siteID) throws Throwable {
		String currentSiteID = siteQueryPage.getSiteID();
		System.out.println("tagged sited: " + currentSiteID);
		Assert.assertEquals("tha tagged site ID is as expected.", siteID,currentSiteID);
		
	}
}
