package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.pages.gm.DeliveryManagementPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeliveryManagementStepDefs {
	private Context context;
	private JDAFooter jDAFooter;
	private DeliveryDB deliveryDB;
	private JdaHomePage jDAHomePage;
	private DeliveryManagementPage deliveryManagementPage;

	@Inject
	public DeliveryManagementStepDefs(Context context, JDAFooter jDAFooter, JdaHomePage jDAHomePage,
			DeliveryDB deliveryDB, DeliveryManagementPage deliveryManagementPage) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.jDAHomePage = jDAHomePage;
		this.deliveryDB = deliveryDB;
		this.deliveryManagementPage = deliveryManagementPage;
	}

	@When("^I search ASN ID to update status")
	public void i_search_ASN_ID_to_update_status() throws Throwable {
		deliveryManagementPage.clickStart();
		jDAFooter.clickNextButton();
		deliveryManagementPage.enterAsnId(context.getAsnId());
		jDAFooter.clickNextButton();
		deliveryManagementPage.clickDeliveryRecord();
		deliveryManagementPage.isUpdateStatusExistAndClick();
	}

	@When("^I choose the delivery status as \"([^\"]*)\"$")
	public void i_choose_the_delivery_status_as(String status) throws Throwable {
		deliveryManagementPage.chooseDeliveryStatus(status);
		jDAFooter.pressTab();
		jDAFooter.PressEnter();
		deliveryManagementPage.isWarningPopUpExistsAndClickYes();
	}
}
