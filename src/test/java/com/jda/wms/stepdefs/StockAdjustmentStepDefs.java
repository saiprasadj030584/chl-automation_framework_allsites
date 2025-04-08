package com.jda.wms.stepdefs;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.StockAdjustmentsPage;
import com.jda.wms.context.Context;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StockAdjustmentStepDefs {
// private StockAdjustmentStepDefs stockAdjustmentStepDefs;
 private StockAdjustmentsPage stockAdjustmentsPage;
 private Context context;
	@Inject
	public StockAdjustmentStepDefs(
			Context context,StockAdjustmentsPage stockAdjustmentsPage){
//		this.stockAdjustmentStepDefs=stockAdjustmentStepDefs;
		this.stockAdjustmentsPage=stockAdjustmentsPage;
		this.context=context;
	}
	
	
	@When("^Query with sku id and tag id in BLACK area$")
	public void query_with_sku_id_and_tag_id_in_blak_area() throws Throwable {
		
			System.out.println(context.getTagId());
//			stockAdjustmentsPage.clickGeneral();
		stockAdjustmentsPage.enterTagId(context.getTagId());
		stockAdjustmentsPage.enterSkuId(context.getSkuId());
		System.out.println(context.getSkuId());
		stockAdjustmentsPage.enterLocation(context.getlocationID());
		System.out.println(context.getlocationID());
		
		
	}
	
	@Then("Adjust the stock form the sku - quantity in hand$")
	public void Adjust_stock_from_sku_quantity_in_hand() throws Throwable{
		
//		int Quantity=Integer.parseInt(context.getQuantity());
		
		String Quantity=stockAdjustmentsPage.getQtyOnHand();
		int Quan=Integer.parseInt(Quantity);
		int AdjustQuantity= Quan - 10;
		stockAdjustmentsPage.adjustQtyOnHand(Integer.toString(AdjustQuantity));
		/*String quan=stockAdjustmentsPage.getNewQtyOnHand();
		context.setQtyOnHand(Integer.parseInt(quan));
		System.out.println("quantity="+quan);*/
		i_choose_the_reason_code_as("Black stock");
	}
	@Given("^I choose the reason code as \"([^\"]*)\"$")
	public void i_choose_the_reason_code_as(String reasonCode) throws Throwable {
//		stockAdjustmentsPage.isShortageImageExists();
//		stockAdjustmentsPage.clickOk();
		stockAdjustmentsPage.chooseReasonCode(reasonCode);
	}

	@Then("Decrease the quantity in hand")
	public void decrease_the_quantity_in_hand() throws Throwable{
	 System.out.println("Quan="+stockAdjustmentsPage.getQtyOnHand());
	 context.setQuantity(stockAdjustmentsPage.getQtyOnHand());
		//int Quantity=Integer.parseInt(context.getQuantity());
		String Quantity=(context.getQuantity());
		int AdjustQuantity= 15;
		stockAdjustmentsPage.adjustQtyOnHand(Integer.toString(AdjustQuantity));
		//String quan=stockAdjustmentsPage.getNewQtyOnHand();
		//context.setQtyOnHand(Integer.parseInt(quan));
		//System.out.println("quantity="+quan);
		i_choose_the_reason_code_as("3Damaged");
	}
	
}
