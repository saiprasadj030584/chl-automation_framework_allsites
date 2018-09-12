package com.jda.wms.stepdefs.Exit;

import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTaskListGenerationStepDefs {
	private final MoveTaskListGenerationPage moveTaskListGenerationPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private Context context;
	Map<Integer, Map<String, String>> replenishmentDetailsMap;

	@Inject
	public MoveTaskListGenerationStepDefs(MoveTaskListGenerationPage moveTaskListGenerationPage, JDAFooter jdaFooter,
			JdaHomePage jdaHomePage, Context context) {
		this.moveTaskListGenerationPage = moveTaskListGenerationPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
	}

	@When("^I enter the Tag ID and sku ID$")
	public void i_enter_the_Tag_ID_and_sku_ID() throws Throwable {
		moveTaskListGenerationPage.enterTagID(context.getTagId());
		moveTaskListGenerationPage.enterSkuID(context.getSkuId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}

	@When("^I select the record from  available list$")
	public void i_select_the_record_from_available_list() throws Throwable {
		moveTaskListGenerationPage.clickAddButton();
		Thread.sleep(1000);
	}

	@Then("^the record should be added in selected list$")
	public void the_record_should_be_added_in_selected_list() throws Throwable {
		moveTaskListGenerationPage.navigateToSelectedTab();
		Assert.assertTrue("MoveTask List not generated.", moveTaskListGenerationPage.isSelectedListAvailable());
		Thread.sleep(1000);
	}

	@Then("^I should see the confirmation for number of items selected$")
	public void i_should_see_the_confirmation_for_number_of_items_selected() throws Throwable {
		String getSelectedListConfirmationText = moveTaskListGenerationPage.getSelectedListConfirmationText();
		Assert.assertTrue("MoveTask List not generated as expected. " + getSelectedListConfirmationText,
				getSelectedListConfirmationText.contains("You have selected:"));
		Thread.sleep(1000);
	}

	@When("^I proceed to generate the move task  list$")
	public void i_proceed_to_generate_the_move_task_list() throws Throwable {
		jdaFooter.clickDoneButton();
		Thread.sleep(6000);
	}

	@Then("^I should see the  list created$")
	public void i_should_see_the_list_created() throws Throwable {
		Assert.assertTrue("Stock Check List not generated as expected.",
				moveTaskListGenerationPage.isListIdPopupDisplayed());
	}
//Modified by Bhuban
	@When("^I generate move task lists for all tags$")
	public void i_generate_move_task_lists_for_all_tags() throws Throwable {
		replenishmentDetailsMap = context.getReplenishmentDetailsMap();
		Thread.sleep(4000);
			for (int r = 1; r <= replenishmentDetailsMap.size(); r++) {
			context.setTagId(replenishmentDetailsMap.get(r).get("TagID"));
			//context.setSkuId(replenishmentDetailsMap.get(r).get("SkuID"));
			Thread.sleep(4000);
			jdaFooter.clickNextButton();
			Thread.sleep(4000);

			i_enter_the_Tag_ID_and_sku_ID();
			i_select_the_record_from_available_list();
			the_record_should_be_added_in_selected_list();
			jdaFooter.clickNextButton();
			Thread.sleep(2000);

			i_should_see_the_confirmation_for_number_of_items_selected();
			i_proceed_to_generate_the_move_task_list();
			i_should_see_the_list_created();
			moveTaskListGenerationPage.closeAllScreens();
			jdaHomePage.navigateToMoveTaskListGenerationPage();
			
		}
	}
}
