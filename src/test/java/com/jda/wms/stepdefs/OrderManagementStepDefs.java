package com.jda.wms.stepdefs;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.JdaHomePage;
import com.jda.wms.UI.pages.OrderManagementPage;
import com.jda.wms.UI.pages.OrderPreparationPage;
import com.jda.wms.UI.pages.WarningPopUpPage;
import com.jda.wms.context.Context;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
	@And ("^Navigate to Order Container screen$")
	public void Navigate_to_Order_Container_screen() throws Throwable {
		jdaHomePage.navigateToOrderContainerPage();
	}
	@And ("^Enter Order Id$")
	public void Enter_Order_Id() throws Throwable {
		orderManagementPage.typeOrderId(context.getOrderId());
		Thread.sleep(2000);
	}
	@Then("^Validate the 32 digit URN is generated$")
	public void Validate_the_32_digit_URN_is_generated() throws Throwable {
		String Urn = orderManagementPage.getURN();
		System.out.println("The URN :" +Urn);
		if(Urn.length()==32){
			Assert.assertTrue("The 32 digit URN is validated", true);
		}else{
			Assert.assertFalse("The 32 digit URN is validated", false);
		}
	}
}