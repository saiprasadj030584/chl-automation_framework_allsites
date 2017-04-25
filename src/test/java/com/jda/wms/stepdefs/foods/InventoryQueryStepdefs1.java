package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.InventoryQueryPage1;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.context.Context;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryQueryStepdefs1 {
	private final InventoryQueryPage1 inventoryQueryPage1;
	private final JdaHomePage jdaHomePage;
	private final Context context;
	
	@Inject
	public InventoryQueryStepdefs1(InventoryQueryPage1 inventoryQueryPage1,JdaHomePage jdaHomePage, Context context) {
		this.inventoryQueryPage1 = inventoryQueryPage1;
		this.jdaHomePage=jdaHomePage;
		this.context=context;
	}
	
	//@Given("^I have SKU id, product group and ABV for the tag id \"([^\"]*)\"$")
	public void i_have_SKU_id_product_group_and_ABV_for_the_tag_id(String tagId) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		inventoryQueryPage1.clickQueryButton();
		inventoryQueryPage1.enterTagId(tagId);
		inventoryQueryPage1.clickExecuteButton();
		
		String skuId = inventoryQueryPage1.getSkuId();
		if (skuId.equals(null)) {
			failureList.add("Sku Id is not as expected. Expected [Not NULL] but was [" + skuId + "]");
		}
		inventoryQueryPage1.clickUserDefinedTab();
		
		String productGroup = inventoryQueryPage1.getProductGroup();
		if ((productGroup.equals("F20")||productGroup.equals("F21")||productGroup.equals("F22"))) {
			failureList.add("Product Group is not as expected. Expected [F20 or F21 or F21] but was [" + productGroup + "]");
		}
		
		String abv = inventoryQueryPage1.getABV();
		if (abv.equals(null)) {
			failureList.add("ABV is not as expected. Expected [NOT NULL] but was [" + abv + "]");
		}
	    
	}
	//@When("^I navigate to inventory query$")
	public void i_navigate_to_inventory_query_page() throws Throwable {
		jdaHomePage.clickInventorytab();
		inventoryQueryPage1.refreshInventoryQueryPage();
	   
	}
	//@Then("^I should see the updated ABV in the inventory query page$")
	public void i_should_see_the_updated_ABV_in_the_inventory_query_page() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		 String updatedabv=inventoryQueryPage1.checkUpdatedABV();
		 if(!(context.getABV()).equals(updatedabv)){
			 failureList.add("ABV is not as expected. Expected [Both value should be equal] but was [" + updatedabv + "]");
		
	}
	}	
}
