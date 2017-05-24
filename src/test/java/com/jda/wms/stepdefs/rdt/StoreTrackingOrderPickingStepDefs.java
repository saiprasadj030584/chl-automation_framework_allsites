package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.InventoryDB;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.pages.rdt.StoreTrackingOrderPickingPage;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class StoreTrackingOrderPickingStepDefs {

	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private InventoryDB inventoryDB;
	private PuttyFunctionsPage puttyFunctionsPage;
	private Map<Integer, Map<String, String>> listIDMap;
	private Map<Integer, Map<String, String>> stockTransferOrderMap;
	private Context context;

	@Inject
	public StoreTrackingOrderPickingStepDefs(StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			Context context,InventoryDB inventoryDB,PuttyFunctionsPage puttyFunctionsPage) {
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
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
		stockTransferOrderMap = context.getStockTransferOrderMap();
		
		for (int i = 1; i <= context.getListIDMap().size(); i++) {
			
			context.setListID(listIDMap.get(i).get("ListID"));
			
			i_enter_task_id_and_list_id();
			
			the_list_id_should_be_displayed();
			
			puttyFunctionsPage.pressEnter();
			
			String skuId = storeTrackingOrderPickingPage.getSkuId();
			Assert.assertNotNull("SKU ID is not displayed as expected",skuId);
			context.setSkuId(skuId);
			
			for (int j = 1; j <= context.getListIDMap().size(); j++) {
				if (listIDMap.get(j).get("SkuId").equals(context.getSkuId())){
					context.setLocation(listIDMap.get(j).get("Location"));
					context.setToPallet((listIDMap.get(j).get("ToPalletID")));
					context.setToLocation((listIDMap.get(j).get("ToLocation")));
					context.setFinalLocation((listIDMap.get(j).get("FinalLocation")));
					context.setQtytoMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
				}
			}
			the_location_should_be_displayed();
			i_enter_SKU_id_quantity_and_stock_details();

			listIDMap.get(i).replace("TagID", context.getTagId());
			context.setListIDMap(listIDMap);
			
			the_to_pallet_to_location_and_destination_should_be_displayed();
			
			if (!storeTrackingOrderPickingPage.isPickEntryDisplayed()){
				failureList.add("Picking not completed and Home page not displayed for List ID "+context.getListID());
				context.setFailureList(failureList);
			}
		}
//		puttyFunctionsPage.minimisePutty();
	}
	
	@When("^the list id should be displayed$")
	public void the_list_id_should_be_displayed() throws Throwable {
		String listId = storeTrackingOrderPickingPage.getListIDDisplayed();
		Assert.assertEquals("List ID in trolley Pick info is not displayed as expected.", context.getListID(),listId);
	}
	
	@When("^I enter task id and list id$")
	public void i_enter_task_id_and_list_id() throws Throwable {
//		storeTrackingOrderPickingPage.enterTaskID(context.getOrderId());
		puttyFunctionsPage.pressTab();
		storeTrackingOrderPickingPage.enterListID(context.getListID());
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the location should be displayed$")
	public void the_location_should_be_displayed() throws Throwable {
		String location = storeTrackingOrderPickingPage.getLocation();
		Assert.assertEquals("Location is not displayed as expected.", context.getLocation(),location);
	}

	@When("^I enter SKU id, quantity and stock details$")
	public void i_enter_SKU_id_quantity_and_stock_details() throws Throwable {
		int caseRatio = 0, qtyToPick = 0;
		storeTrackingOrderPickingPage.enterSkuId(context.getSkuId());
		puttyFunctionsPage.navigateToNextPage();
		
		String quantity = storeTrackingOrderPickingPage.getQuantity();
		stockTransferOrderMap = context.getStockTransferOrderMap();
		for (int s=1;s<stockTransferOrderMap.size();s++){
			if (context.getSkuId().equals(stockTransferOrderMap.get(s).get("SKU"))){
				caseRatio = Integer.parseInt(stockTransferOrderMap.get(s).get("CaseRatio"));
				break;
			}
		}
		
		qtyToPick = context.getQtytoMove() / caseRatio;
		Assert.assertEquals("Quantity to pick is not displayed as expected.", String.valueOf(qtyToPick)+"C", quantity);
		
		puttyFunctionsPage.navigateToNextPage();
		Thread.sleep(1000);
		puttyFunctionsPage.navigateToNextPage();
		puttyFunctionsPage.pressEnter();
		
		String tagId = storeTrackingOrderPickingPage.getTagId();
		context.setTagId(tagId);
		
		puttyFunctionsPage.navigateToNextPage();
		Thread.sleep(1000);
		puttyFunctionsPage.pressEnter();
		
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();
		
		storeTrackingOrderPickingPage.enterQuantity(String.valueOf(qtyToPick)+"C");
//		storeTrackingOrderPickingPage.enterQuantity("4C");
		
		stockTransferOrderMap = context.getStockTransferOrderMap();
		String allocationGroup = null;
		for (int s=1;s<stockTransferOrderMap.size();s++){
			if (context.getSkuId().equals(stockTransferOrderMap.get(s).get("SKU"))){
				allocationGroup = stockTransferOrderMap.get(s).get("AllocationGroup");
				break;
			}
		}
		
		if (allocationGroup.equalsIgnoreCase("Expiry")){
			puttyFunctionsPage.pressTab();
			String manufactureDate = DateUtils.getPrevSystemYear();
			storeTrackingOrderPickingPage.enterManufactureDate(manufactureDate);
			// TODO Convert date from DB
			String expDate = inventoryDB.getExpDate(context.getSkuId(),tagId,context.getLocation());
			storeTrackingOrderPickingPage.enterExpiryDate(expDate);
		}
		
		puttyFunctionsPage.navigateToNextPage();
		Thread.sleep(1000);
		puttyFunctionsPage.navigateToNextPage();
	}

	@Then("^the to pallet, to location and destination should be displayed$")
	public void the_to_pallet_to_location_and_destination_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		
		String toPallet = storeTrackingOrderPickingPage.getToPallet();
		if (context.getToPallet().equals(toPallet)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToPallet()
			+ "] but was [" + toPallet + "]");
		}
		
		String toLocation = storeTrackingOrderPickingPage.getToLocation();
		if (context.getToLocation().equals(toLocation)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToLocation()
			+ "] but was [" + toLocation + "]");
		}
		
		String destination = storeTrackingOrderPickingPage.getDestination();
		if (context.getFinalLocation().equals(destination)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getFinalLocation()
			+ "] but was [" + destination + "]");
		}
		
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking - List Id, SKU, Location are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Then("^I should see the picking completion$")
	public void i_should_see_the_picking_completion() throws Throwable {
		Assert.assertTrue("Picking not completed and Home page not displayed.[" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}
}
