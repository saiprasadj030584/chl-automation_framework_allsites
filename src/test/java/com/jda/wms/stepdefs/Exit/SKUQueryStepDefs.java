package com.jda.wms.stepdefs.Exit;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SKUQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.jda.wms.context.Context;




public class SKUQueryStepDefs{
	
	private final SKUQueryPage sKUQueryPage;
	private final SkuDB skuDB;
	private Context context;
	
	@Inject
	public SKUQueryStepDefs(SKUQueryPage sKUQueryPage,SkuDB skuDB,Context context){
		this.sKUQueryPage=sKUQueryPage;
		this.skuDB=skuDB;
		this.context=context;
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
	@And("^verify the T-Dept \"([^\"]*)\"$")
	public void verify_the_TDept(String SKU) throws Throwable {
		String Tdept=sKUQueryPage.getProductGroup();
		String TdeptDB=skuDB.getProductGroup(SKU);
		Assert.assertEquals("TDept validated ",Tdept,TdeptDB);
		
	}
	@And("^verify the Commoditycode \"([^\"]*)\"$")
	public void verify_the_Commoditycode(String SKU) throws Throwable {
		sKUQueryPage.CommodityCode_Validation(SKU);
		
	}
	@And("^verify the Packedweight \"([^\"]*)\"$")
	public void verify_the_Packedweight(String SKU) throws Throwable {
		sKUQueryPage.packedweight_Validation(SKU);
		
	}
	@And("^verify the Stroke \"([^\"]*)\"$")
	public void verify_the_Stroke(String SKU) throws Throwable {
		sKUQueryPage.Stroke_Validation(SKU);
		
	}
	@And("^Validate the packedweight is in given range \"([^\"]*)\"$")
	public void validation_of_packed_weight_range(String SKU) throws Throwable {
		sKUQueryPage.validateweight(SKU);
		
	}
	@And("^Verify the country of origin \"([^\"]*)\"$")
	public void validation_of_country_origin(String SKU) throws FindFailed, ClassNotFoundException, InterruptedException, SQLException
	{
		sKUQueryPage.COO_Validation(SKU);
	}
	@When("^I go to user-defined tab$")
	public void i_go_to_user_defined_tab() throws Throwable {
		sKUQueryPage.clickUserDefinedTab();
	}
	
	@Then("^Verify stroke details Garment type for SKU \"([^\"]*)\"$")
	public void verify_stroke_details_garment_type(String sku) throws Throwable {
		System.out.println(sKUQueryPage.StrokeValidation(sku));
		Assert.assertNotNull("Stroke is found null", sKUQueryPage.getStroke()); //to verify the feild is not null
	}
	
	@Then("^I should be able to verify the description-article$")
	public void i_should_be_able_verify_the_description_article() throws Throwable {
		Assert.assertEquals("Description as expected ", sKUQueryPage.getArticleDescription(), sKUQueryPage.getArticleDescription()); //to equate the desired value		
		Assert.assertNotNull("Description not as expected", sKUQueryPage.getArticleDescription()); //to verify the feild is not null
	}
	@Then("^Verify Description$")
	public void verify_description() throws FindFailed, InterruptedException{
        Assert.assertEquals("Description as expected ", sKUQueryPage.getDescription(), sKUQueryPage.getArticleDescription());
		
		Assert.assertNotNull("Description not as expected", sKUQueryPage.getDescription());
	}
		
	
	

	@Then("^I should be able to verify the description-composition$")
	public void i_should_be_able_verify_the_description_composition() throws Throwable {
		
		//--for UDT-10 description-composition--//
		Assert.assertNotEquals("",sKUQueryPage.getPrimarySizeDesc());		
		Assert.assertNotNull("Description not as expected", sKUQueryPage.getPrimarySizeDesc());
	}
	@Then("^I should be able to verify the Knit/Woven indicator$")
	public void i_should_be_able_verify_the_knit_woven_indicator() throws Throwable {
		
		//--for UDT-11 Knit/Woven indicator--//
		Assert.assertNotEquals("",sKUQueryPage.getHandlingUnitInd());		
		Assert.assertNotNull("Description not as expected", sKUQueryPage.getHandlingUnitInd());
	}
	@Then("^Verify Supplier SKU from SKU table against Supplier SKU table \"([^\"]*)\"$")
	public void verify_supplier_SKU_from_SKU_table_against_supplier_sku_table(String SKU) throws FindFailed, ClassNotFoundException, InterruptedException, SQLException
	{
		sKUQueryPage.clickSupplierSkuFromSKU();
		sKUQueryPage.supplierid_Validation(SKU);
	}
	
	@Then("^check weight is validated as null$")
	public void check_weight_is_validated_as_null() throws Throwable{
		String sku=context.getSkuId2();
		String packedWeight = SkuDB.getpackedweight(sku);
		System.out.println("packed weight"+packedWeight);
		Assert.assertNull("Check Weight is not as expected", packedWeight);
		String Packweight= context.getPackWeight();
		System.out.println(Packweight);
		String OriginalPackWeight=skuDB.UpdateOriginal(Packweight,sku);
			
	}
	@Then("^Alter the check weight to make the stock as RED Stock$")
	public void alter_the_check_weight_to_make_the_stock_as_red_stock() throws Throwable{
		String SKU=context.getSkuId2();
		String PackWeight=skuDB.getpackedweight(SKU);
		System.out.println("packweight="+PackWeight);
		context.setPackWeight(PackWeight);
		
		String UpdatePackWeight=skuDB.updatePackedweight(SKU);
		System.out.println("UpdatePackWeight"+UpdatePackWeight);
	}
	
	
	}
	

