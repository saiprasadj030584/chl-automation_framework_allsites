package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
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

	@Inject
	public MoveTaskUpdateStepDefs(JdaHomePage jdaHomepage, MoveTaskUpdatePage moveTaskUpdatePage, Context context,
			JDAFooter jdaFooter) {
		this.jdaHomepage = jdaHomepage;
		this.moveTaskUpdatePage = moveTaskUpdatePage;
		this.context = context;
		this.jdaFooter = jdaFooter;
	}


	@Then("^the tag id should be released$")
	public void the_tag_id_should_be_released() throws Throwable {
		moveTaskUpdatePage.enterTagId(context.getTagId());
		jdaFooter.clickNextButton();
		moveTaskUpdatePage.clickReleaseButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
	}

}
