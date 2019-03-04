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
	private final SiteDB siteDB;
	

	@Inject
	public SiteQueryStepDefs(JdaHomePage jdaHomepage, Context context,JDAFooter jdaFooter,SiteQueryPage siteQueryPage,SiteDB siteDB) 
			
	{
		this.jdaHomepage = jdaHomepage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.siteQueryPage=siteQueryPage;
		this.siteDB=siteDB;
	}

	
	@And("^Specify the SITE ID \"([^\"]*)\"$")
	public void specify_the_SITE_ID(String siteID) throws Throwable {
		siteQueryPage.enterSiteID(siteID);
	}
	
	@Then("^Verify whether the required fields been populated \"([^\"]*)\"$")
	public void validation_of_fields(String siteID) throws Throwable {
		
		String Timezone = siteQueryPage.getTimeZone();
		System.out.println("Timezone "+Timezone);
		String TimeZoneDB=siteDB.getTimeZoneDB(siteID);
		System.out.println("TimeZoneDB "+TimeZoneDB);
		Assert.assertNotNull(TimeZoneDB);//assertEquals("TimeZone Validated",Timezone,TimeZoneDB);
		siteQueryPage.UDT3_Validation(siteID);
		
		String UDT3 = siteQueryPage.getUDT3();
		System.out.println("UDT3 "+UDT3);
		String UDT3DB=siteDB.getUDT3DB(siteID);
		System.out.println("UDT3DB"+UDT3DB);
		Assert.assertEquals("TimeZone Validated",UDT3,UDT3DB);
		
		String UDT4 = siteQueryPage.getUDT4();
		System.out.println("UDT4 "+UDT4);
		String UDT4DB=siteDB.getUDT4DB(siteID);
		System.out.println("UDT4DB "+UDT4DB);
		Assert.assertEquals("TimeZone Validated",UDT4,UDT4DB);
		
		String UDN1 = siteQueryPage.getUDN1();
		System.out.println("UDN1 "+UDN1);
		String UDN1DB=siteDB.getUDN1DB(siteID);
		System.out.println("UDN1DB "+UDN1DB);
		Assert.assertEquals("TimeZone Validated",UDN1,UDN1DB);
	}
	@Then("^Verify the SiteID \"([^\"]*)\" displayed is tagged$")
	public void verify_the_SiteID_displayed_is_tagged(String siteID) throws Throwable {
		String currentSiteID = siteQueryPage.getSiteID();
		System.out.println("tagged sited: " + currentSiteID);
		Assert.assertNotNull("tha tagged site ID is as expected.",currentSiteID);
		
	}
}
