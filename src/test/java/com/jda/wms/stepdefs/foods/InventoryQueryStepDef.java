package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InventoryQueryStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private Context context;

	@Inject
	public InventoryQueryStepDef(InventoryQueryPage inventoryQueryPage, Context context, JdaHomePage jdaHomePage,
			JDAFooter jdaFooter) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
	}

	/*@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		// context.setTagId(tagId);
		// context.setStatus(status);

		inventoryQueryPage.searchTagId(tagId);
		logger.debug("Tag ID: " + tagId);

		String inventorySKUId = inventoryQueryPage.getInventorySKUId();
		context.setSkuId(inventorySKUId);
		logger.debug("SKU ID: " + inventorySKUId);

		String productStatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("Status is not displayed as expected", status, productStatus);
		logger.debug("Inventory Query - Status: " + productStatus);

		String qtyOnHandBfrAdjustment = inventoryQueryPage.getQtyOnHand();
		// context.setqtyOnHandBfrAdjustment(qtyOnHandBfrAdjustment);
		logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

		String caseRatio = inventoryQueryPage.getcaseRatio();
		// context.setCaseRatio(caseRatio);
		logger.debug("Case Ratio: " + caseRatio);
	}*/

	
	

}
