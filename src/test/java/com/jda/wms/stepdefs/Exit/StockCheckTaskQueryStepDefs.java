package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.JDAFooter;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.StockCheckTaskQueryPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StockCheckTaskQueryStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockCheckTaskQueryPage stockCheckTaskQueryPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final Hooks hooks;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;
	private InventoryDB inventoryDB;

	@Inject
	public StockCheckTaskQueryStepDefs(StockCheckTaskQueryPage stockCheckTaskQueryPage, JDAFooter jdaFooter,
			JdaHomePage jdaHomePage, Context context,InventoryDB inventoryDB,Hooks hooks){
		this.stockCheckTaskQueryPage = stockCheckTaskQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.inventoryDB=inventoryDB;
		this.hooks=hooks;
	}

	@When("^I navigate to stock check query page$")
	public void i_navigate_to_stock_check_query_page() throws Throwable {
		Thread.sleep(3000);
		screen.click(475,631);
		jdaHomePage.navigateToStockCheckQueryPage();
		Thread.sleep(3000);
	}

	@When("^I search the list by site id as \"([^\"]*)\", location and task date as current date$")
	public void i_search_the_list_by_site_id_as_location_as_and_task_date_as_current_date(String siteId) throws Throwable {
		jdaFooter.clickQueryButton();
		stockCheckTaskQueryPage.selectSiteId(siteId);
		stockCheckTaskQueryPage.enterLocation(context.getLocation());
		stockCheckTaskQueryPage.enterTaskDate();
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}

	@When("^I search the list by tag id as and task date as current date$")
	public void i_search_the_list_by_tag_id_as_and_task_date_as_current_date() throws Throwable {
		jdaFooter.clickQueryButton();
		Thread.sleep(1000);
		stockCheckTaskQueryPage.enterTagId(context.getTagId());
		stockCheckTaskQueryPage.enterTaskDate();
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}
	
	@Then("^I should see the record with list ID$")
	public void i_should_see_the_created_list() throws Throwable {
		String listId = stockCheckTaskQueryPage.getListIdnew();
		Assert.assertNotNull("List ID is not generated as expected. Expected [not Null] but was [" + listId + "]",
				listId);
		logger.debug("List ID: " + listId);
		Thread.sleep(3000);
	}
	
	@Then("^I should see the record with listID and check the workZone$")
	public void i_should_see_the_created_listID() throws Throwable {
		String listId = stockCheckTaskQueryPage.getListId();
		context.setListID(listId);
		Assert.assertNotNull("List ID is not generated as expected. Expected [not Null] but was [" + listId + "]",
				listId);
		logger.debug("List ID: " + listId);		
		Thread.sleep(3000);
		
		String workZone=stockCheckTaskQueryPage.getWorkZone();
		context.setworkZone(workZone);
	}
	

	@Then("^I validate the quantity on hand in inventory screen$")
	public void I_validate_the_quantity_on_hand_in_inventory_screen() throws Throwable {
		System.out.println("qty new"+inventoryDB.getQtyOnHandTag(context.getTagId()));
	
		Assert.assertEquals("Qty on hand is not as expected",Integer.toString(context.getQtyOnHandBeforeAdjustment()),inventoryDB.getQtyOnHandTag(context.getTagId()));
		hooks.logoutPutty();
		}
	
}
