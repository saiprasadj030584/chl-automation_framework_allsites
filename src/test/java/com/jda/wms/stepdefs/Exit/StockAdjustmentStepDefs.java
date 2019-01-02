package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.StockAdjustmentsPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


public class StockAdjustmentStepDefs {
 private StockAdjustmentStepDefs stockAdjustmentStepDefs;
 private StockAdjustmentsPage stockAdjustmentsPage;
	@Inject
	public void StockAdjustmentStepDefs(StockAdjustmentStepDefs StockAdjustmentStepDefs,StockAdjustmentsPage StockAdjustmentsPage){
		this.stockAdjustmentStepDefs=stockAdjustmentStepDefs;
		this.stockAdjustmentsPage=stockAdjustmentsPage;
	}
	
	
	@Then("Navigate to Stock Adjustment Screen$")
	public void navigate_to_stock_adjustment_screen() throws Throwable{
		
	}
	@And("Query with sku id and tag id in BLACK area$")
	public void query_with_sku_id_and_tag_is_in_black_area() throws Throwable{
		
	}
	@Then("Decrease the stock form the sku - quantity in hand$")
	public void decrease_stock_from_sku_quantity_in_hand() throws Throwable{
		
	}
	
}
