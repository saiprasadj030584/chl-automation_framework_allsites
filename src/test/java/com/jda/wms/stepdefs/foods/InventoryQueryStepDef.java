package com.jda.wms.stepdefs.foods;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class InventoryQueryStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final InventoryQueryPage inventoryQueryPage;
	private Context context;
	private Utilities utilities;
	
	@Inject
	public InventoryQueryStepDef(InventoryQueryPage inventoryQueryPage, Context context,Utilities utilities) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.context = context;
		this.utilities = utilities;
	}

	@Given("^I have the tag id \"([^\"]*)\" with \"([^\"]*)\" status$")
	public void i_have_the_tag_id_with_status(String tagId, String status) throws Throwable {
		context.setTagId(tagId);
		context.setStatus(status);

		inventoryQueryPage.searchTagId(tagId);
		logger.debug("Tag ID: " + tagId);

		String inventorySKUId = inventoryQueryPage.getInventorySKUId();
		context.setSkuId(inventorySKUId);
		logger.debug("SKU ID: " + inventorySKUId);

		String productStatus = inventoryQueryPage.getStatus();
		Assert.assertEquals("the given TAG id is not in " + status + " status", status, productStatus);
		logger.debug("Inventory Query - Status: " + productStatus);

		String qtyOnHandBfrAdjustment = inventoryQueryPage.getQtyOnHand();
		context.setqtyOnHandBeforeAdjustment(utilities.convertStringToInteger(qtyOnHandBfrAdjustment));
		logger.debug("Quantity on Hand before Adjustment: " + qtyOnHandBfrAdjustment);

		String caseRatio = inventoryQueryPage.getcaseRatio();
		context.setCaseRatio(utilities.convertStringToInteger(caseRatio));
		logger.debug("Case Ratio: " + caseRatio);
	}
}
