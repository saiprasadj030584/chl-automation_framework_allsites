package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.SiteQueryPage;

import cucumber.api.java.en.And;

public class SiteQueryStepDefs {

	private final JdaHomePage jdaHomepage;
	private final Context context;
	private final JDAFooter jdaFooter;
	private final SiteQueryPage siteQueryPage;

	@Inject
	public SiteQueryStepDefs(JdaHomePage jdaHomepage, Context context,JDAFooter jdaFooter,SiteQueryPage siteQueryPage ) {
		this.jdaHomepage = jdaHomepage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.siteQueryPage=siteQueryPage;
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
}
