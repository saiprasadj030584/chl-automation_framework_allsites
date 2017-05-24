package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.MoveTaskDB;

import cucumber.api.java.en.Given;
import edu.emory.mathcs.backport.java.util.Arrays;

public class MoveTaskStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;
	

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
		ArrayList<String> listID = new ArrayList<String>();
		ArrayList<String> qtyToMove = new ArrayList<String>();
		ArrayList<String> toPalletID = new ArrayList<String>();
		ArrayList<String> toContainerID = new ArrayList<String>();
		ArrayList<String> skuID = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();
		ArrayList<String> toLocation = new ArrayList<String>();
		ArrayList<String> finalLocation = new ArrayList<String>();
		
		listID = moveTaskDB.getListId(context.getOrderId());
		
		for (int l=0;l<listID.size();l++){
			if (listID.get(l)==null){
				failureList.add("List ID not generated as expected : List id "+l+ "is null");
			}
		}
		Assert.assertTrue("List ID not generated as expected.[" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
		
		qtyToMove = moveTaskDB.getQtyToMove(context.getOrderId());
		toPalletID = moveTaskDB.getToPalletID(context.getOrderId());
		toContainerID = moveTaskDB.getToContainerID(context.getOrderId());
		skuID = moveTaskDB.getSkuID(context.getOrderId());
		location = moveTaskDB.getLocation(context.getOrderId());
		toLocation = moveTaskDB.getToLocation(context.getOrderId());
		finalLocation = moveTaskDB.getFinalLocation(context.getOrderId());
		
		
		for (int i=0;i<listID.size();i++){
			Map<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("ListID", listID.get(i));
			listDetailsMap.put("QtyToMove", qtyToMove.get(i));
			listDetailsMap.put("ToPalletID", toPalletID.get(i));
			listDetailsMap.put("ToContainerID", toContainerID.get(i));
			listDetailsMap.put("SkuId", skuID.get(i));
			listDetailsMap.put("Location", location.get(i));
			listDetailsMap.put("ToLocation", toLocation.get(i));
			listDetailsMap.put("FinalLocation", finalLocation.get(i));
			listDetailsMap.put("TagID", "");
			listIDMap.put(i+1, listDetailsMap);
		}
		context.setListIDMap(listIDMap);
		System.out.println(context.getListIDMap()); 
	}
}
