package com.jda.wms.stepdefs.rdt;

import org.junit.Assert;
import com.jda.wms.pages.rdt.StoreTrackingOrderPage;
import cucumber.api.java.en.*;

public class StoreTrackingOrderPickingStepDefs {
	
	private StoreTrackingOrderPage storeTrackingOrderPage;
	
	public StoreTrackingOrderPickingStepDefs(StoreTrackingOrderPage storeTrackingOrderPage) {
		this.storeTrackingOrderPage = storeTrackingOrderPage;
	}

	@Given("^I select picking with container pick$")
	public void i_select_picking_with_container_pick() throws Throwable {
		storeTrackingOrderPage.selectPickingMenu();
		Assert.assertTrue("Picking Menu not displayed as expected",
				storeTrackingOrderPage.isPickMenuDisplayed());

		storeTrackingOrderPage.selectPickingInPickMenu();
		Assert.assertTrue("Pick Task Menu not displayed as expected",
				storeTrackingOrderPage.isPickTaskMenuDisplayed());

		storeTrackingOrderPage.selectContainerPickMenu();
	}
	
	@Then("^I should be directed to pick entry page$")
	public void i_should_be_directed_to_pick_entry_page() throws Throwable {
		Assert.assertTrue("Pick entry not displayed as expected.",
				storeTrackingOrderPage.isPickEntryDisplayed());
	}
}
