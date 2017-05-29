package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.MoveTaskDB;
import com.jda.wms.pages.foods.JDAFooter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class MoveTaskStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;
	private final JDAFooter jDAFooter;
	Map<Integer, Map<String, String>> replenishmentDetailsMap;

	@Inject
	public MoveTaskStepDefs(MoveTaskDB moveTaskDB, Context context, JDAFooter jDAFooter) {
		this.moveTaskDB = moveTaskDB;
		this.context = context;
		this.jDAFooter = jDAFooter;
	}

	@Given("^the tagid, skuid, quantity to move details should be displayed for \"([^\"]*)\" tasks without list ids$")
	public void the_tagid_skuid_quantity_to_move_details_should_be_displayed_for_tasks_without_list_ids(String taskId)
			throws Throwable {
		ArrayList<String> skuID = new ArrayList<String>();
		ArrayList<String> qtyToMove = new ArrayList<String>();
		ArrayList<String> tagID = new ArrayList<String>();

		context.setTaskId(taskId);
		qtyToMove = moveTaskDB.getQtyToMove(context.getTaskId());
		skuID = moveTaskDB.getSkuID(context.getTaskId());
		tagID = moveTaskDB.getTagID(context.getTaskId());

		Map<Integer, Map<String, String>> replenishmentDetailsMap = new HashMap<Integer, Map<String, String>>();

		for (int i = 0; i < skuID.size(); i++) {
			HashMap<String, String> listDetailsMap = new HashMap<String, String>();
			listDetailsMap.put("QtyToMove", qtyToMove.get(i));
			listDetailsMap.put("SkuID", skuID.get(i));
			listDetailsMap.put("TagID", tagID.get(i));
			replenishmentDetailsMap.put(i + 1, listDetailsMap);
		}
		context.setReplenishmentDetailsMap(replenishmentDetailsMap);
	}

	@Then("^the list ids should be generated$")
	public void the_list_ids_should_be_generated() throws Throwable {
		replenishmentDetailsMap = context.getReplenishmentDetailsMap();
		ArrayList<String> failureList = new ArrayList<String>();
		for (int r = 1; r < replenishmentDetailsMap.size(); r++) {
			context.setTagId(replenishmentDetailsMap.get(r).get("TagID"));
			context.setSkuId(replenishmentDetailsMap.get(r).get("SkuID"));
			String listID = moveTaskDB.getListID(context.getTagId(), context.getSkuId());
			if (listID.equals(null)){
				failureList.add("List ID not displayed as expected for Tag "+context.getTagId()+" Expected [NOT Null] but was "+listID);
			}
		}
		Assert.assertTrue(
				"Replenish List IDs are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

}
