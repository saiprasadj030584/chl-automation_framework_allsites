package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.MoveTaskDB;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTaskStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public MoveTaskStepDefs(MoveTaskDB moveTaskDB, Context context) {
		this.moveTaskDB = moveTaskDB;
		this.context = context;
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
	 * ArrayList<String>();
	 * 
	 * context.setOrderId(orderID); listID = moveTaskDB.getListId(orderID);
	 * qtyToMove = moveTaskDB.getQtyToMove(orderID); toPalletID =
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

}