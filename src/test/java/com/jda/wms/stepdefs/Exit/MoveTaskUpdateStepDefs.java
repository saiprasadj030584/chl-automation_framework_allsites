package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;
import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.MoveTaskUpdateDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTaskUpdateStepDefs {

	private final JdaHomePage jdaHomepage;
	private final MoveTaskUpdatePage moveTaskUpdatePage;
	private final Context context;
	private final JDAFooter jdaFooter;
	private final MoveTaskUpdateDB moveTaskUpdateDB;
	private MoveTaskDB moveTaskDB;
	

	@Inject
	public MoveTaskUpdateStepDefs(MoveTaskDB moveTaskDB,JdaHomePage jdaHomepage,MoveTaskUpdateDB moveTaskUpdateDB, MoveTaskUpdatePage moveTaskUpdatePage, Context context,
			JDAFooter jdaFooter) {
		this.moveTaskDB=moveTaskDB;
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

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
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
	public void the_task_id_should_be_released(String taskId) throws Throwable {
		System.out.println("Task Id is   " + taskId);
		moveTaskUpdatePage.enterTaskId(taskId);
		jdaFooter.clickNextButton();
		moveTaskUpdatePage.clickReleaseButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
	}
	@When("^I update the status to hold for all the tags in the move task update$")
	public void i_update_the_status_to_hold_for_all_the_tags_in_the_move_task_update() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
		Map<String, ArrayList<String>> tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				moveTaskUpdateDB.holdTagId(tagIDMap.get(sku).get(t));
			}
		} 
	}
	@When("^I delete the record in move task for the tags in move task update$")
	public void I_delete_the_record_in_move_task_for_the_tags_in_move_task_update() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
		Map<String, ArrayList<String>> tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				moveTaskUpdateDB.deleteTagId(tagIDMap.get(sku).get(t));
			}
		} 
	}
//	@When("^I query the list id corresponding to task  \"([^\"]*)\" and change the status to hold$")
//	public void i_query_the_list_id_corresponding_to_task_and_change_the_status_to_hold(String taskId) throws Throwable {
//		context.setTaskId(taskId);
//		String List=moveTaskDB.getList(context.getTaskId());
//		moveTaskUpdatePage.enterListId(List);	
//		jdaFooter.clickNextButton();
//		moveTaskUpdatePage.selectTaskID();
//		moveTaskUpdatePage.clickHoldButton();
//		jdaFooter.clickNextButton();
//		Assert.assertTrue("Task Selected",
//				moveTaskUpdatePage.istaskSelected());
//	}
	
	
	
}
