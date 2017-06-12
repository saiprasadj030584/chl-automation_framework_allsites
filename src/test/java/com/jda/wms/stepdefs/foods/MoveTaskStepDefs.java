package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.db.MoveTaskDB;
import com.jda.wms.db.SkuConfigDB;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class MoveTaskStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private SkuConfigDB skuConfigDB;
	private OrderHeaderContext orderHeaderContext;
	private final JDAFooter jDAFooter;
	Map<Integer, Map<String, String>> replenishmentDetailsMap;

	@Inject
	public MoveTaskStepDefs(MoveTaskDB moveTaskDB, Context context, JDAFooter jDAFooter, SkuConfigDB skuConfigDB,OrderHeaderContext orderHeaderContext) {
		this.moveTaskDB = moveTaskDB;
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.skuConfigDB = skuConfigDB;
		this.orderHeaderContext = orderHeaderContext;
	}

	@Given("^the tagid, quantity to move details should be displayed for the sku \"([^\"]*)\" with \"([^\"]*)\" tasks$")
	public void the_tagid_quantity_to_move_details_should_be_displayed_for_the_sku_with_tasks(String sku, String taskId)
			throws Throwable {
		ArrayList<String> qtyToMove = new ArrayList<String>();
		ArrayList<String> tagID = new ArrayList<String>();
		Map<Integer, Map<String, String>> replenishmentDetailsMap = new HashMap<Integer, Map<String, String>>();

		context.setSkuId(sku);
		context.setTaskId(taskId);
		qtyToMove = moveTaskDB.getReplenishQtyToMoveList(sku);
		tagID = moveTaskDB.getReplenishTagIDList(sku);

		for (int i = 0; i < tagID.size(); i++) {
			HashMap<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("QtyToMove", qtyToMove.get(i));
			listDetailsMap.put("TagID", tagID.get(i));
			listDetailsMap.put("ListID", "");
			// FIXME why does key contains serial sequence number in the map?
			replenishmentDetailsMap.put(i + 1, listDetailsMap);
		}
		context.setReplenishmentDetailsMap(replenishmentDetailsMap);
	}

	@Then("^the list ids should be generated$")
	public void the_list_ids_should_be_generated() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		replenishmentDetailsMap = context.getReplenishmentDetailsMap();

		for (int r = 1; r <= replenishmentDetailsMap.size(); r++) {
			// FIXME this context contains only last value of tag id and sku id
			// in the context, why do you need this?
			// context.setTagId(replenishmentDetailsMap.get(r).get("TagID"));
			// context.setSkuId(replenishmentDetailsMap.get(r).get("SkuID"));
			String listID = moveTaskDB.getListID(replenishmentDetailsMap.get(r).get("TagID"),
					replenishmentDetailsMap.get(r).get("SkuID"));

			if (listID.equals(null)) {
				failureList.add("List ID not displayed as expected for Tag " + context.getTagId()
						+ " Expected [NOT Null] but was " + listID);
			} else {
				replenishmentDetailsMap.get(r).replace("ListID", listID);
			}
		}
		Assert.assertTrue("Replenish List IDs are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@When("^I get the pallet ids from the move task for all orders$")
	public void i_get_the_pallet_ids_from_the_move_task_for_all_orders() throws Throwable {
		ArrayList<String> palletListId = new ArrayList<String>();
		
		DataTable orderIDDatatable = orderHeaderContext.getOrderIDDataTable();
		for (Map<String, String> dataRow : orderIDDatatable.asMaps(String.class, String.class)) {
			palletListId.addAll(moveTaskDB.getPalletIdList(dataRow.get("OrderID"))); 
			
		}
		context.setPalletIDList(palletListId); 
		logger.debug(" Pallet ID List : " + context.getPalletIDList()); 
	}

	@Given("^the STO should have list id, quantity to move,to pallet, to container details from move task table$")
	public void the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details_from_move_task_table()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
		ArrayList<String> qtyToMoveList = new ArrayList<String>();
		ArrayList<String> toPalletIDList = new ArrayList<String>();
		ArrayList<String> toContainerIDList = new ArrayList<String>();
		ArrayList<String> skuIDList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> toLocationList = new ArrayList<String>();
		ArrayList<String> finalLocationList = new ArrayList<String>();

		listIDList = moveTaskDB.getListId(context.getOrderId());

		for (int l = 0; l < listIDList.size(); l++) {
			if (listIDList.get(l) == null) {
				failureList.add("List ID not generated as expected : List id " + l + "is null");
			}
		}
		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		qtyToMoveList = moveTaskDB.getQtyToMoveList(context.getOrderId());
		toPalletIDList = moveTaskDB.getToPalletIDList(context.getOrderId());
		toContainerIDList = moveTaskDB.getToContainerIDList(context.getOrderId());
		skuIDList = moveTaskDB.getSkuIDList(context.getOrderId());
		locationList = moveTaskDB.getLocationList(context.getOrderId());
		toLocationList = moveTaskDB.getToLocationList(context.getOrderId());
		finalLocationList = moveTaskDB.getFinalLocationList(context.getOrderId());

		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
			listDetailsMap.put("ToPalletID", toPalletIDList.get(i));
			listDetailsMap.put("ToContainerID", toContainerIDList.get(i));
			listDetailsMap.put("SkuId", skuIDList.get(i));
			listDetailsMap.put("Location", locationList.get(i));
			listDetailsMap.put("ToLocation", toLocationList.get(i));
			listDetailsMap.put("FinalLocation", finalLocationList.get(i));
			listDetailsMap.put("TagID", "");
			listIDMap.put(i + 1, listDetailsMap);
		}
		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}

	// FIXME to update after merging with Kiruthika's code
	/*
	 * @Given(
	 * "^the STO \"([^\"]*)\" should have list id, quantity to move,to pallet, to container details$"
	 * ) public void
	 * the_STO_should_have_list_id_quantity_to_move_to_pallet_to_container_details
	 * (String orderID) throws Throwable { Map<Integer, Map<String, String>>
	 * listIDMap = new HashMap<Integer, Map<String, String>>();
	 * ArrayList<String> listID = new ArrayList<String>(); ArrayList<String>
	 * qtyToMove = new ArrayList<String>(); ArrayList<String> toPalletID = new
	 * ArrayList<String>(); ArrayList<String> toContainerID = new
	 * ArrayList<String>(); context.setOrderId(orderID); listID =
	 * moveTaskDB.getListId(orderID); qtyToMove =
	 * moveTaskDB.getQtyToMove(orderID); toPalletID =
	 * moveTaskDB.getToPalletID(orderID); toContainerID =
	 * moveTaskDB.getToContainerID(orderID);
	 * 
	 * for (int i = 0; i < listID.size(); i++) { Map<String, String>
	 * listDetailsMap = new HashMap<String, String>();
	 * listDetailsMap.put("ListID", listID.get(i));
	 * listDetailsMap.put("QtyToMove", qtyToMove.get(i));
	 * listDetailsMap.put("ToPalletID", toPalletID.get(i));
	 * listDetailsMap.put("ToContainerID", toContainerID.get(i));
	 * listIDMap.put(i + 1, listDetailsMap); } context.setListIDMap(listIDMap);
	 * System.out.println(context.getListIDMap()); }
	 */

	@When("^I get the pallet ids from the move task$")
	public void i_get_the_pallet_ids_from_the_move_task() throws Throwable {
		ArrayList<String> palletIDList = new ArrayList<String>();
		Integer moveTaskRecordCount = moveTaskDB.getRecordCountByTaskID(context.getOrderId());
		context.setMoveTaskRecordCount(moveTaskRecordCount);

		palletIDList = moveTaskDB.getPalletIdList(context.getOrderId());
		context.setPalletIDList(palletIDList);
		logger.debug("No of Pallets to load : " + palletIDList.size());
		logger.debug("Move task Rec Count before loading : " + moveTaskRecordCount);
	}

	@Then("^the pallet id should be displayed$")
	public void the_pallet_id_should_be_displayed() throws Throwable {
		Assert.assertNotNull("No Pallet IDs were retrieved from DB", context.getPalletIDList());
	}

	@Then("^no record should exist for the Order ID$")
	public void no_record_should_exist_for_the_Order_ID() throws Throwable {
		Assert.assertEquals("Result is not as expected. ", "0",
				moveTaskDB.getRecordCountByTaskID(context.getOrderId()));
	}

	@Then("^the replenish STO should have list id,quantity to move, tagid, location details and case ratio$")
	public void the_replenish_STO_should_have_list_id_quantity_to_move_tagid_location_details_and_case_ratio()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = new HashMap<Integer, Map<String, String>>();
		ArrayList<String> listIDList = new ArrayList<String>();
		ArrayList<String> qtyToMoveList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> toLocationList = new ArrayList<String>();
		ArrayList<String> finalLocationList = new ArrayList<String>();
		ArrayList<String> tagIDList = new ArrayList<String>();

		listIDList = moveTaskDB.getReplenishListId(context.getSkuId());

		for (int j = 0; j < listIDList.size(); j++) {
			if (null == listIDList.get(j)) {
				failureList.add("List ID not generated as expected : List id " + j + "is null");
			}
		}

		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		qtyToMoveList = moveTaskDB.getReplenishQtyToMoveList(context.getSkuId());
		tagIDList = moveTaskDB.getReplenishTagIDList(context.getSkuId());
		locationList = moveTaskDB.getReplenishLocationList(context.getSkuId());
		toLocationList = moveTaskDB.getReplenishToLocationList(context.getSkuId());
		finalLocationList = moveTaskDB.getReplenishFinalLocationList(context.getSkuId());

		context.setCaseRatio(Integer.parseInt(skuConfigDB.getRatio1To2(moveTaskDB.getPackConfig(context.getSkuId()))));

		for (int i = 0; i < listIDList.size(); i++) {
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listIDList.get(i));
			listDetailsMap.put("QtyToMove", qtyToMoveList.get(i));
			listDetailsMap.put("Location", locationList.get(i));
			listDetailsMap.put("ToLocation", toLocationList.get(i));
			listDetailsMap.put("FinalLocation", finalLocationList.get(i));
			listDetailsMap.put("TagID", tagIDList.get(i));
			listIDMap.put(i + 1, listDetailsMap);
		}
		context.setListIDMap(listIDMap);
		logger.debug("List ID Map " + context.getListIDMap());
	}
}
