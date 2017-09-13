package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.MoveTaskListGenerationPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTaskListGenerationStepDefs {
	private JDAFooter jDAFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private Verification verification;
	private MoveTaskListGenerationPage moveTaskListGenerationPage;

	@Inject
	public MoveTaskListGenerationStepDefs(JDAFooter jDAFooter, Verification verification, Context context,
			MoveTaskListGenerationPage moveTaskListGenerationPage) {
		this.jDAFooter = jDAFooter;
		this.verification = verification;
		this.context = context;
		this.moveTaskListGenerationPage = moveTaskListGenerationPage;
	}

	@When("^I enter OrderID$")
	public void i_enter_OrderID_as() throws Throwable {
		jDAFooter.clickNextButton();
		moveTaskListGenerationPage.enterOrderId(context.getOrderId());
	}

	@Then("^I create the list Id$")
	public void i_create_the_list_Id() throws Throwable {
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		moveTaskListGenerationPage.clickAddButton();
		Thread.sleep(4000);
		jDAFooter.clickNextButton();
		Thread.sleep(4000);
		moveTaskListGenerationPage.enterMaxPickers("1");
		jDAFooter.clickDoneButton();
		Thread.sleep(2000);
		jDAFooter.PressEnter();
	}

}
