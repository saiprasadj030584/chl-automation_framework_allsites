package com.jda.wms.stepdefs.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;
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

public class ReceivingMaintainanceStepDefs {
	private Context context;
	// private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private DeliveryDB deliveryDB;
	private JdaHomePage jDAHomePage;
	private DeliveryManagementPage deliveryManagementPage;
	private DeliveryPage deliveryPage;

	@Inject
	public ReceivingMaintainanceStepDefs(Context context, JDAFooter jDAFooterockAdjustmentsPage,
			 JdaHomePage jDAHomePage,DeliveryDB deliveryDB) {
		this.context = context;
		// this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.jDAHomePage = jDAHomePage;
		this.deliveryDB=deliveryDB;
	}
	
	
	@Given("^I have an ASN_ID with Delivery status as \"([^\"]*)\"$")
	public void i_have_an_ASN_ID_with_Delivery_status_as(String arg1) throws Throwable {
		ArrayList AsnidList = deliveryDB.getAsnidList();
		if (!AsnidList.isEmpty()) {
			context.setAsnId((String) AsnidList.get(0));
		}
		//jdaLoginPage.login();
	}


	@When("^I enter an ASN_ID as \"([^\"]*)\"$")
	public void i_enter_an_ASN_ID_as(String arg1) throws Throwable {
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		deliveryManagementPage.enterAsnId(context.getAsnId());
		Thread.sleep(2000);
		

	}

	@When("^I choose the Delivery status as \"([^\"]*)\"$")
	public void i_choose_the_Delivery_status_as(String arg1) throws Throwable {
		deliveryManagementPage.ChooseDeliveryStatus();
		Thread.sleep(2000);

	}

	@When("^I enter an ASN_ID$")
	public void i_enter_an_ASN_ID() throws Throwable {
		deliveryPage.enterAsnId(context.getAsnId());
	}

	@Then("^the Delivery status should be update")
	public void the_Delivery_status_should_be_update(String arg1) throws Throwable {
		boolean isRecordExists = deliveryDB.isRecordExistsForAsnId(context.getAsnId(),
				context.getStatus());
		Assert.assertTrue("ITL does not exist for the adjusted stock with reason code " + context.getStatus(),
				isRecordExists);
	}


}
