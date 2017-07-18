package com.jda.wms.stepdefs.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.pages.gm.DeliveryManagementPage;
import com.jda.wms.pages.gm.DeliveryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeliveryManagementStepDefs {
	private Context context;
	// private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private DeliveryDB deliveryDB;
	private JdaHomePage jDAHomePage;
	private DeliveryManagementPage deliveryManagementPage;
	private DeliveryPage deliveryPage;

	@Inject
	public DeliveryManagementStepDefs(Context context, JDAFooter jDAFooterockAdjustmentsPage,
			 JdaHomePage jDAHomePage,DeliveryDB deliveryDB) {
		this.context = context;
		// this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.jDAHomePage = jDAHomePage;
		this.deliveryDB=deliveryDB;
	}
	
	@When("^I search ASN ID to update status")
	public void i_search_ASN_ID_to_update_status() throws Throwable {
		System.out.println("hey");
		//deliveryManagementPage.clickNextButton();
		jDAFooter.clickNextButton();
		System.out.println("ASN2=" + context.getAsnId());
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

	@When("^I enter an ASN ID$")
	public void I_enter_an_ASN_ID() throws Throwable {
		deliveryPage.enterAsnId(context.getAsnId());
	}

	@Then("^the delivery status should be \"([^\"]*)\"$")
	public void the_delivery_status_should_be_update(String status) throws Throwable {
		boolean isRecordExists = deliveryDB.isRecordExistsForAsnId(context.getAsnId(),
				status);
		Assert.assertTrue("ITL does not exist for the ASN Closure " + status,
				isRecordExists);
	}
}
