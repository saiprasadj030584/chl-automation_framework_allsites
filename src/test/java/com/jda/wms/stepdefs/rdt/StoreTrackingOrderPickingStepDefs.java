package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.InventoryDB;
import com.jda.wms.db.LocationDB;
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
	private LocationDB locationDB;

	@Inject
	public StoreTrackingOrderPickingStepDefs(StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			Context context,InventoryDB inventoryDB,PuttyFunctionsPage puttyFunctionsPage, LocationDB locationDB) {
		this.storeTrackingOrderPickingPage = storeTrackingOrderPickingPage;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.locationDB = locationDB;
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
	
	@Given("^I select picking with replenish pick$")
	public void i_select_picking_with_replenish_pick() throws Throwable {
		storeTrackingOrderPickingPage.selectPickingMenu();
		Assert.assertTrue("Picking Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickMenuDisplayed());

		storeTrackingOrderPickingPage.selectPickingInPickMenu();
		Assert.assertTrue("Pick Task Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickTaskMenuDisplayed());

		storeTrackingOrderPickingPage.selectReplenishPickMenu();
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
					context.setQtyToMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
					context.setContainerId(listIDMap.get(j).get("ToContainerID"));
				}
			}
			the_location_should_be_displayed();
			i_enter_SKU_id_quantity_and_stock_details();
			
			listIDMap.get(i).replace("TagID", context.getTagId());
			context.setListIDMap(listIDMap);
			
			the_to_pallet_to_location_and_destination_should_be_displayed();
			Assert.assertTrue("Container ID entry page is not displayed as expected",storeTrackingOrderPickingPage.isContainerIDDisplayed());
			i_enter_container_id_and_check_strings();
			
			if (!storeTrackingOrderPickingPage.isPickEntryDisplayed()){
				failureList.add("Picking not completed and Home page not displayed for List ID "+context.getListID());
				context.setFailureList(failureList);
			}
			context.setPickedRecords(context.getPickedRecords()+1);
//			System.out.println("Record picked "+context.getPickedRecords());
		}
		puttyFunctionsPage.minimisePutty();
//		System.out.println("Total picked "+context.getPickedRecords());
	}
	
	@When("^the list id should be displayed$")
	public void the_list_id_should_be_displayed() throws Throwable {
		String listId = storeTrackingOrderPickingPage.getListIDDisplayed();
		Assert.assertEquals("List ID in trolley Pick info is not displayed as expected.", context.getListID(),listId);
	}
	
	@When("^I enter container id and check strings$")
	public void i_enter_container_id_and_check_strings() throws Throwable {
		storeTrackingOrderPickingPage.enterContainerID(context.getContainerId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		
		String chkStrings = locationDB.getCheckString(context.getToLocation());
		storeTrackingOrderPickingPage.enterCheckStrings(chkStrings);
		puttyFunctionsPage.pressEnter();
	}
	
	@When("^I enter task id and list id$")
	public void i_enter_task_id_and_list_id() throws Throwable {
		storeTrackingOrderPickingPage.enterTaskID(context.getOrderId());
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
		puttyFunctionsPage.nextScreen();
		
		String quantity = storeTrackingOrderPickingPage.getQuantity();
		stockTransferOrderMap = context.getStockTransferOrderMap();
		for (int s=1;s<=stockTransferOrderMap.size();s++){
			if (context.getSkuId().equals(stockTransferOrderMap.get(s).get("SKU"))){
				caseRatio = Integer.parseInt(stockTransferOrderMap.get(s).get("CaseRatio"));
				break;
			}
		}
		
		qtyToPick = context.getQtyToMove() / caseRatio;
		Assert.assertEquals("Quantity to pick is not displayed as expected.", String.valueOf(qtyToPick)+"C", quantity);
		
		puttyFunctionsPage.nextScreen();
		Thread.sleep(1000);

		String tagId = storeTrackingOrderPickingPage.getTagId();
		context.setTagId(tagId);
		
		puttyFunctionsPage.nextScreen();
		puttyFunctionsPage.pressEnter();
		
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();
		
		storeTrackingOrderPickingPage.enterQuantity(String.valueOf(qtyToPick)+"C");
		
		stockTransferOrderMap = context.getStockTransferOrderMap();
		String allocationGroup = null;
		for (int s=1;s<=stockTransferOrderMap.size();s++){
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
			String expDate = inventoryDB.getExpDate(context.getSkuId(),context.getTagId(),context.getLocation());
			storeTrackingOrderPickingPage.enterExpiryDate(expDate);
		}
		
		puttyFunctionsPage.nextScreen();
		Thread.sleep(1000);
		puttyFunctionsPage.nextScreen();
	}

	@Then("^the to pallet, to location and destination should be displayed$")
	public void the_to_pallet_to_location_and_destination_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		
		String destination = storeTrackingOrderPickingPage.getDestination();
		String [] dest = destination.split("_");
		destination = dest[0];
		
		if (!context.getFinalLocation().equals(destination)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getFinalLocation()
			+ "] but was [" + destination + "]");
		}
		
		String toPallet = storeTrackingOrderPickingPage.getToPallet();
		if (!context.getToPallet().equals(toPallet)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToPallet()
			+ "] but was [" + toPallet + "]");
		}
		
		String toLocation = storeTrackingOrderPickingPage.getToLocation();
		if (!context.getToLocation().equals(toLocation)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToLocation()
			+ "] but was [" + toLocation + "]");
		}
		
		storeTrackingOrderPickingPage.enterDestination(destination);
		
		puttyFunctionsPage.pressEnter();
		
		Assert.assertTrue("Picking - List Id, SKU, Location are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Then("^I should see the picking completion$")
	public void i_should_see_the_picking_completion() throws Throwable {
		Assert.assertTrue("Picking not completed and Home page not displayed.[" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}
	
	@When("^I pick all the list ids for the replenish task$")
	public void i_pick_all_the_list_ids_for_the_replenish_task() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		listIDMap = context.getListIDMap();
		
		for (int i = 1; i <= context.getListIDMap().size(); i++) {
			
			context.setListID(listIDMap.get(i).get("ListID"));
			i_enter_task_id_and_list_id();
			
			for (int j = 1; j <= context.getListIDMap().size(); j++) {
				if (listIDMap.get(j).get("ListID").equals(context.getListID())){
					context.setLocation(listIDMap.get(j).get("Location"));
					context.setTagId(listIDMap.get(j).get("TagID"));
					context.setToLocation((listIDMap.get(j).get("ToLocation")));
					context.setFinalLocation((listIDMap.get(j).get("FinalLocation")));
					context.setQtyToMove(Integer.parseInt(listIDMap.get(j).get("QtyToMove")));
				}
			}
			the_location_should_be_displayed();
			puttyFunctionsPage.nextScreen();
			
			Assert.assertEquals("SKU ID is not displayed as expected",context.getSkuId(),storeTrackingOrderPickingPage.getSkuId());
			the_quantity_and_to_and_destination_location_should_be_displayed();
			i_enter_SKU_id_quantity_and_stock_details();
			
			listIDMap.get(i).replace("TagID", context.getTagId());
			context.setListIDMap(listIDMap);
			
			the_to_pallet_to_location_and_destination_should_be_displayed();
			Assert.assertTrue("Container ID entry page is not displayed as expected",storeTrackingOrderPickingPage.isContainerIDDisplayed());
			i_enter_container_id_and_check_strings();
			
			if (!storeTrackingOrderPickingPage.isPickEntryDisplayed()){
				failureList.add("Picking not completed and Home page not displayed for List ID "+context.getListID());
				context.setFailureList(failureList);
			}
			context.setPickedRecords(context.getPickedRecords()+1);
//			System.out.println("Record picked "+context.getPickedRecords());
		}
		puttyFunctionsPage.minimisePutty();
//		System.out.println("Total picked "+context.getPickedRecords());
	}
	
	
	@When("^the quantity and to and destination location should be dispalyed$")
	public void the_quantity_and_to_and_destination_location_should_be_displayed() throws Throwable {
		int qtyToMove = 0;
		listIDMap = context.getListIDMap();
		
		String quantity = storeTrackingOrderPickingPage.getReplenishQuantity();
		String[] quantitySplit = quantity.split("_");
		quantity = quantitySplit[0];
		
		for (int s=1;s<=listIDMap.size();s++){
			if (context.getListID().equals(listIDMap.get(s).get("ListID"))){
				qtyToMove = Integer.parseInt(listIDMap.get(s).get("QtyToMove"));
				context.setQtyToMove(qtyToMove);
				break;
			}
		}
		
		qtyToMove = qtyToMove / context.getCaseRatio();
		Assert.assertEquals("Quantity to pick is not displayed as expected.", String.valueOf(qtyToMove)+"C", quantity);
		Assert.assertEquals("Destination is not displayed as expected.", context.getFinalLocation(), storeTrackingOrderPickingPage.getDestination());
		Assert.assertEquals("To location  is not displayed as expected.", context.getToLocation(), storeTrackingOrderPickingPage.getDestination());
		
		
		String toLocation = storeTrackingOrderPickingPage.getToLocation();
		String [] location = toLocation.split("_");
		toLocation =location[0];
		puttyFunctionsPage.pressEnter();
		Assert.assertEquals("To Location is not displayed as expected.", context.getToLocation(), storeTrackingOrderPickingPage.getToLocation());
		String toLocation = storeTrackingOrderPickingPage.getToLocation();
		if (!context.getToLocation().equals(toLocation)){
			failureList.add("To Pallet is not displayed as expected. Expected [" + context.getToLocation()
			+ "] but was [" + toLocation + "]");
		}

		//----------------
		puttyFunctionsPage.nextScreen();
		Thread.sleep(1000);

		String tagId = storeTrackingOrderPickingPage.getTagId();
		context.setTagId(tagId);
		
		puttyFunctionsPage.nextScreen();
		puttyFunctionsPage.pressEnter();
		
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();
		
		storeTrackingOrderPickingPage.enterQuantity(String.valueOf(qtyToMove)+"C");
		
		stockTransferOrderMap = context.getStockTransferOrderMap();
		String allocationGroup = null;
		for (int s=1;s<=stockTransferOrderMap.size();s++){
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
			String expDate = inventoryDB.getExpDate(context.getSkuId(),context.getTagId(),context.getLocation());
			storeTrackingOrderPickingPage.enterExpiryDate(expDate);
		}
		
		puttyFunctionsPage.nextScreen();
		Thread.sleep(1000);
		puttyFunctionsPage.nextScreen();
	}

}
