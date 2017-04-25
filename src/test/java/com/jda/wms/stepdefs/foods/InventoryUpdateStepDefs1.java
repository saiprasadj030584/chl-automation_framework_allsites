package com.jda.wms.stepdefs.foods;


import com.google.inject.Inject;
import com.jda.wms.pages.foods.InventoryQueryPage1;
import com.jda.wms.pages.foods.InventoryUpdatePage1;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.context.Context;

import cucumber.api.java.en.When;

public class InventoryUpdateStepDefs1 {
	private final InventoryUpdatePage1 inventoryUpdatePage1;
	private final JDAFooter jDAFooter;
	private final Context context;
	private final InventoryQueryPage1 inventoryQueryPage1; 
	@Inject
	public InventoryUpdateStepDefs1(InventoryUpdatePage1 inventoryUpdatePage1, JDAFooter jDAFooter,
			Context context,InventoryQueryPage1 inventoryQueryPage1) {
		this.inventoryUpdatePage1 = inventoryUpdatePage1;
		this.jDAFooter = jDAFooter;
		this.context=context;
		this.inventoryQueryPage1 = inventoryQueryPage1;
	}
	//@When("^I enter ABV value as \"([^\"]*)\"$")
	public void i_enter_ABV_value_as(String newAbv) throws Throwable {
	//	jDAFooter.clickNext();
		context.setABV(newAbv);
		inventoryUpdatePage1.enterABV(newAbv);
	}
	//@When("^I select the reason code as \"([^\"]*)\"$")
	public void i_select_the_reason_code_as(String reasonCode) throws Throwable {
		inventoryUpdatePage1.selectReasonCode(reasonCode);
		jDAFooter.clickDone();
	}


}
