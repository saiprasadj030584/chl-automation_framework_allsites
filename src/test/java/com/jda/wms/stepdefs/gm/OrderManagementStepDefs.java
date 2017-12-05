package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.OrderManagementPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderManagementStepDefs {
	private OrderManagementPage orderManagementPage;
	private JDAFooter jdaFooter;
	private Context context;

	@Inject

	public OrderManagementStepDefs(OrderManagementPage orderManagementPage, JDAFooter jdaFooter,Context context) {
		this.orderManagementPage = orderManagementPage;
		this.jdaFooter = jdaFooter;
		this.context = context;
	}

	@When("^I search the order$")
	public void i_search_the_order() throws Throwable {
		Thread.sleep(3000);
		orderManagementPage.clickOrderManagement();
		jdaFooter.clickNextButton();
		jdaFooter.clickQueryButton();
		orderManagementPage.enterOrderId(context.getOrderId());
		jdaFooter.clickExecuteButton();
	}

	@Then("^the record should be displayed$")
	public void the_record_should_be_displayed() throws Throwable {
		Assert.assertTrue("Record is not displayed as expected", orderManagementPage.isRecordDisplayed());
	}

	@When("^I proceed to check the status$")
	public void i_proceed_to_check_the_status() throws Throwable {
		orderManagementPage.navigateOrderHeader();
	}

	@When("^update the status as cancelled$")
	public void update_the_status_as_cancelled() throws Throwable {
		orderManagementPage.clickAvailable();
		jdaFooter.clickNextButton();
		context.setOrderId("7274417277");
		orderManagementPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickNextButton();
		orderManagementPage.clickHeaderTable();
		orderManagementPage.clickUpdateButton();
		orderManagementPage.clickDropDown();
		orderManagementPage.selectCancelled();
		Thread.sleep(1000);
		orderManagementPage.clickOk();
		Thread.sleep(2000);
		orderManagementPage.clickYes();
		Thread.sleep(2000);
	}

}
