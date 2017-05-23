package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.StoreTrackingOrderPickingPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StoreTrackingOrderPickingStepDefs {

	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private Map<Integer, Map<String, String>> listIDMap;
	private Context context;

	public StoreTrackingOrderPickingStepDefs(StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			Context context) {
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.context = context;
	}

	@Given("^I select picking with container pick$")
	public void i_select_picking_with_container_pick() throws Throwable {
		storeTrackingOrderPickingPage.selectPickingMenu();
		Assert.assertTrue("Picking Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickMenuDisplayed());

		storeTrackingOrderPickingPage.selectPickingInPickMenu();
		Assert.assertTrue("Pick Task Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickTaskMenuDisplayed());

		storeTrackingOrderPickingPage.selectContainerPickMenu();
	}

	@Then("^I should be directed to pick entry page$")
	public void i_should_be_directed_to_pick_entry_page() throws Throwable {
		Assert.assertTrue("Pick entry not displayed as expected.",
				storeTrackingOrderPickingPage.isPickEntryDisplayed());
	}

	@When("^I pick all the list ids for the store tracking order$")
	public void i_pick_all_the_list_ids_for_the_store_tracking_order() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		listIDMap = context.getListIDMap();

		for (int i = 1; i <= context.getListIDMap().size(); i++) {
			String listIDCurrent = listIDMap.get(i).get("ListID");
			context.setListID(listIDCurrent);
			// context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(i)).get("Allocation
			// Group"));
			// context.setABV(purchaseOrderMap.get(String.valueOf(i)).get("ABV"));
			// context.setVintage(purchaseOrderMap.get(String.valueOf(i)).get("Vintage"));
			// for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
			storeTrackingOrderPickingPage.enterOrderID(context.getOrderId());
			storeTrackingOrderPickingPage.enterListID(listIDCurrent);
			storeTrackingOrderPickingPage.pressEnter();
			String listIddispl = storeTrackingOrderPickingPage.getListIDDisplayed();
			Assert.assertEquals("List ID in trolley Pick info is not displayed as expected. Expected [" + listIDCurrent
					+ "] but was [" + listIddispl + "]", listIDCurrent, listIddispl);
			storeTrackingOrderPickingPage.pressEnter();
			
			i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
			the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
			i_enter_the_location_and_tag(context.getLocation());
			i_enter_the_quantity_to_receive_and_case_ratio();
			i_enter_the_expiry_and_vintage_details();
			if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
				failureList.add("Receive not completed and Home page not displayed.");
				context.setFailureList(failureList);
				// }
				Thread.sleep(5000);
			}
		}
		puttyFunctionsPage.minimisePutty();
	}
}
