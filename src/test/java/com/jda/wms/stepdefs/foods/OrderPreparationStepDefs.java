package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderPreparationPage;
import com.jda.wms.pages.foods.WarningPopUpPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderPreparationStepDefs {
	private OrderPreparationPage orderPreparationPage;
	private final WarningPopUpPage warningPopUpPage;
	private final JDAFooter jdaFooter;

	@Inject
	public OrderPreparationStepDefs(OrderPreparationPage orderPreparationPage, WarningPopUpPage warningPopUpPage,
			JDAFooter jdaFooter) {
		this.orderPreparationPage = orderPreparationPage;
		this.warningPopUpPage = warningPopUpPage;
		this.jdaFooter = jdaFooter;
	}

	@When("^I enter the Group type as \"([^\"]*)\"$")
	public void i_enter_the_Group_type_as(String arg1) throws Throwable {
		orderPreparationPage.enterGroupType();
	}

	@When("^I have the order id \"([^\"]*)\"$")
	public void i_have_the_order_id(String orderId) throws Throwable {
		orderPreparationPage.enterOrderId(orderId);
	}

	@Then("^I select the record$")
	public void i_select_the_record() throws Throwable {
		orderPreparationPage.selectRecord();
	}

	@When("^I select the Trailer type as\"([^\"]*)\" and validate the total orders$")
	public void i_select_the_Trailer_type_as_and_validate_the_total_orders(String trailerType) throws Throwable {
		orderPreparationPage.selectTrailerType(trailerType);
	}

	@Then("^the record should be displayed$")
	public void the_record_should_be_displayed() throws Throwable {
		Assert.assertTrue("Record is not present in order preparation screen", orderPreparationPage.isRecordExist());
	}

	@When("^I proceed to complete the processs$")
	public void i_proceed_to_complete_the_processs() throws Throwable {
		jdaFooter.clickDoneButton();
		warningPopUpPage.clickYes();
		orderPreparationPage.clickOk();
	}
}
