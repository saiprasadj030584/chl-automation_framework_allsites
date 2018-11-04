package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SKUQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class SKUQueryStepDefs{
	
	private final SKUQueryPage sKUQueryPage;
	private final SkuDB skuDB;
	
	@Inject
	public SKUQueryStepDefs(SKUQueryPage sKUQueryPage,SkuDB skuDB){
		this.sKUQueryPage=sKUQueryPage;
		this.skuDB=skuDB;
	}
	
	@And("^Specify the SKU \"([^\"]*)\"$")
	public void specify_the_SKU(String SKU) throws Throwable {
		sKUQueryPage.enterSKU(SKU);
	}
	@Then("^Verify whether the required fields been populated \"([^\"]*)\" in SKU table$")
	public void validation_of_fields_in_SKU(String SKU) throws Throwable {
		//sKUQueryPage.CommodityCode_Validation(SKU);
		System.out.println("CommodityCodeDB "+ skuDB.getCommodityCode(SKU));
		//System.out.println("CommodityCode "+sKUQueryPage.getCommodityCode(SKU));
		Assert.assertEquals("Commodity code Validated",sKUQueryPage.getCommodityCode(SKU),skuDB.getCommodityCode(SKU));
		Assert.assertNotEquals("",sKUQueryPage.getSKUDesc());
		Assert.assertNotEquals("",sKUQueryPage.getStroke());
		Assert.assertNotEquals("",sKUQueryPage.getPrimarySizeDesc());
		Assert.assertNotEquals("",sKUQueryPage.getHandlingUnitInd());
		
	}
}