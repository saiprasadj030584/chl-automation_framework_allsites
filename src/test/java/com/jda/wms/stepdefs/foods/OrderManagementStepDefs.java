package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderManagementPage;
import com.jda.wms.pages.foods.OrderPreparationPage;
import com.jda.wms.pages.foods.WarningPopUpPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class OrderManagementStepDefs {
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final Context context;
	private final OrderManagementPage orderManagementPage;
	private OrderPreparationPage orderPreparationPage;
	private final WarningPopUpPage warningPopUpPage;

	@Inject
	public OrderManagementStepDefs(JDAFooter jdaFooter, Context context, JdaHomePage jdaHomePage,
			OrderManagementPage orderManagementPage, OrderPreparationPage orderPreparationPage,
			WarningPopUpPage warningPopUpPage) {

		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.orderManagementPage = orderManagementPage;
		this.orderPreparationPage = orderPreparationPage;
		this.warningPopUpPage = warningPopUpPage;
	}

	@When("^I enter the site id as \"([^\"]*)\" and order id$")
	public void i_enter_the_site_id_as_and_order_id(String siteId) throws Throwable {
		orderManagementPage.selectSiteId(siteId);
		orderManagementPage.enterOrderId(context.getOrderId());
	}

	@Given("^I select the record in order management page$")
	public void i_select_the_record_in_order_management_page() throws Throwable {
		Assert.assertTrue("Record is not present in order management page", orderManagementPage.isRecordExist());
		orderManagementPage.selectRecord();
	}

	@Given("^I update the status as \"([^\"]*)\"$")
	public void i_update_the_status_as(String status) throws Throwable {
		orderManagementPage.clickUpdateStatus();
		orderManagementPage.selectStatus(status);
		orderManagementPage.clickOk();
	}

	@Given("^I proceed to complete the deallocation$")
	public void i_proceed_to_complete_the_deallocation() throws Throwable {
		warningPopUpPage.clickYes();
		jdaFooter.clickDoneButton();
	}

}