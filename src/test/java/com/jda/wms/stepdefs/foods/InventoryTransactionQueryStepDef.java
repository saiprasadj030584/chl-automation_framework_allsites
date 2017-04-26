package com.jda.wms.stepdefs.foods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class InventoryTransactionQueryStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private final JDAFooter jdaFooter;

	@Inject
	public InventoryTransactionQueryStepDef(InventoryTransactionQueryPage inventoryTransactionQueryPage,
			JDAFooter jdaFooter) {
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.jdaFooter = jdaFooter;
	}

	@When("^I navigate to miscellaneous2 tab$")
	public void i_navigate_to_miscellaneous2_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneous2Tab();
	}

	@Given("^I select the code as \"([^\"]*)\" and enter the tag id \"([^\"]*)\"$")
	public void i_select_the_code_as_and_enter_the_tag_id(String code, String tagId) throws Throwable {
		inventoryTransactionQueryPage.selectCode(code);
		inventoryTransactionQueryPage.enterTagId(tagId);
		jdaFooter.clickExecuteButton();
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}
}
