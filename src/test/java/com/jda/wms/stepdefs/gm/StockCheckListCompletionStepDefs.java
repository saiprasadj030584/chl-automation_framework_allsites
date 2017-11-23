package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.StockCheckListCompletionPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckListCompletionStepDefs {
	private StockCheckListCompletionPage stockCheckListCompletionPage;
	private JDAFooter jdaFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private InventoryDB inventoryDB;

	@Inject

	public StockCheckListCompletionStepDefs(StockCheckListCompletionPage stockCheckListCompletionPage,
			JDAFooter jdaFooter, JdaHomePage jdaHomePage, Context context, InventoryDB inventoryDB) {
		this.stockCheckListCompletionPage = stockCheckListCompletionPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.inventoryDB = inventoryDB;

	}

	@When("^I enter the list id and update the quantity$")
	public void i_enter_the_list_id_and_update_the_quantity() throws Throwable {
		stockCheckListCompletionPage.enterListID(context.getListID());
		jdaFooter.clickNextButton();
		System.out.println("loat" + context.getLocation());
		context.setQtyOnHand(Integer.parseInt(inventoryDB.getQtyOnHand(context.getListID(), context.getTagId())));
		context.setLocation(inventoryDB.getlocation(context.getListID(), context.getTagId()));
		System.out.println("loat" + context.getLocation());
		stockCheckListCompletionPage.enterUpdateQty(String.valueOf(context.getQtyOnHand() + 1));
		System.out.println("loat" + context.getLocation());
		Thread.sleep(3000);
		jdaFooter.clickNextButton();

	}

	@Then("^I should see the confirmation for number of items generated$")
	public void i_should_see_the_confirmation_for_number_of_items_generated() throws Throwable {
		String getSelectedListConfirmationText = stockCheckListCompletionPage.getSelectedListConfirmationText();
		Assert.assertTrue("Stock Check List not generated as expected. " + getSelectedListConfirmationText,
				getSelectedListConfirmationText
						.contains("1 record(s) have been adjusted and 0 new inventory record(s) added"));
		jdaFooter.clickDoneButton();
	}

}
