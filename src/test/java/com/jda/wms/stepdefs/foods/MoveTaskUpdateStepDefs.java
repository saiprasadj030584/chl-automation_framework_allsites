package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.MoveTaskUpdateDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.MoveTaskUpdatePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTaskUpdateStepDefs {

	private final JdaHomePage jdaHomepage;
	private final MoveTaskUpdatePage moveTaskUpdatePage;
	private final Context context;
	private final JDAFooter jdaFooter;
	private final MoveTaskUpdateDB moveTaskUpdateDB;

	@Inject
	public MoveTaskUpdateStepDefs(JdaHomePage jdaHomepage,MoveTaskUpdateDB moveTaskUpdateDB, MoveTaskUpdatePage moveTaskUpdatePage, Context context,
			JDAFooter jdaFooter) {
		this.jdaHomepage = jdaHomepage;
		this.moveTaskUpdatePage = moveTaskUpdatePage;
		this.context = context;
		this.jdaFooter = jdaFooter;
		this.moveTaskUpdateDB = moveTaskUpdateDB;
	}

	@Then("^the tag id should be released$")
	public void the_tag_id_should_be_released() throws Throwable {
		moveTaskUpdatePage.enterTagId(context.getTagId());
		jdaFooter.clickNextButton();
		moveTaskUpdatePage.clickReleaseButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
	}
	
	@When("^I release all the tags for the SKU in the move task update$")
	public void i_release_all_the_tags_for_the_SKU_in_the_move_task_update() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
		Map<String, ArrayList<String>> tagIDMap = context.getTagIDMap();
		System.out.println(tagIDMap);

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				System.out.println(tagIDMap.get(sku).get(t));
				moveTaskUpdateDB.releaseTagId(tagIDMap.get(sku).get(t));
			}
		} 
	}
	
	@When("^I release single tag for the SKU in the move task update$")
	public void i_release_single_tag_for_the_SKU_in_the_move_task_update() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
		Map<String, ArrayList<String>> tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			System.out.println(tagIDMap.get(sku).get(0));
			moveTaskUpdateDB.releaseTagId(tagIDMap.get(sku).get(0));
			break;
		}
		}

	@When("^I release all the tags in the move task update$")
	public void i_release_all_the_tags_in_the_move_task_update() throws Throwable {
		Map<Integer, Map<String, String>> listIDMap  = context.getListIDMap();

		for (Integer key : listIDMap.keySet()) {
			String tagId = listIDMap.get(key).get("TagID");
				moveTaskUpdateDB.releaseTagId(tagId);
		} 
	}
}
