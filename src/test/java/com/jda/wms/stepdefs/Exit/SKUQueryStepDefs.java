package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.SKUQueryPage;
import com.jda.wms.pages.Exit.SiteQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class SKUQueryStepDefs{
	
	private final SKUQueryPage sKUQueryPage;
	
	@Inject
	public SKUQueryStepDefs(SKUQueryPage sKUQueryPage){
		this.sKUQueryPage=sKUQueryPage;
		
	}
	
	@And("^Specify the SKU \"([^\"]*)\"$")
	public void specify_the_SKU(String SKU) throws Throwable {
		sKUQueryPage.enterSKU(SKU);
	}
	@Then("^Verify whether the required fields been populated \"([^\"]*)\" in SKU table$")
	public void validation_of_fields_in_SKU(String SKU) throws Throwable {
		sKUQueryPage.CommodityCode_Validation(SKU);
	}
}