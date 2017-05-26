package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.MoveTaskDB;

import cucumber.api.java.en.Given;
import edu.emory.mathcs.backport.java.util.Arrays;

public class MoveTaskStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public MoveTaskStepDefs(MoveTaskDB moveTaskDB, Context context) {
		this.moveTaskDB = moveTaskDB;
		this.context = context;
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
		
		listIDList = moveTaskDB.getListIdList(context.getOrderId());
		
		for (int l=0;l<listIDList.size();l++){
			if (listIDList.get(l)==null){
				failureList.add("List ID not generated as expected : List id "+l+ "is null");
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
		
		
		for (int i=0;i<listIDList.size();i++){
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
			listIDMap.put(i+1, listDetailsMap);
		}
		context.setListIDMap(listIDMap);
		logger.debug("List ID Map "+context.getListIDMap());
	}
}
