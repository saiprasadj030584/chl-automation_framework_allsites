package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.pages.gm.DeliveryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeliveryStepDefs {
	private DeliveryDB deliveryDB;
	private Context context;
	private DeliveryPage deliveryPage;
	private JdaLoginPage jdaLoginPage;
	private JDAFooter jdaFooter;

	@Inject
	public DeliveryStepDefs(DeliveryDB deliveryDB,Context context,DeliveryPage deliveryPage,JdaLoginPage jdaLoginPage,JDAFooter jdaFooter) {
		this.deliveryDB = deliveryDB;
		this.context = context;
		this.deliveryPage = deliveryPage;
		this.jdaLoginPage = jdaLoginPage;
		this.jdaFooter = jdaFooter;
	}

	@Given("^the pallet count should be updated in delivery$")
	public void the_pallet_count_should_be_updated_in_delivery() throws Throwable {
		deliveryDB.updatePalletCount(context.getAsnId(),context.getNoOfLines());
	}
	
	@Given("^I have an ASN Id with delivery status as \"([^\"]*)\"$")
	public void i_have_an_ASN_Id_with_delivery_status_as(String status) throws Throwable {
		context.setAsnId(deliveryDB.getAsnId(status));
		jdaLoginPage.login();
	}
	
	@When("^I enter an ASN ID$")
	public void I_enter_an_ASN_ID() throws Throwable {
		jdaFooter.clickQueryButton();
		deliveryPage.enterAsnId(context.getAsnId());
		jdaFooter.clickExecuteButton();
	}
	
	@Then("^the delivery status should be \"([^\"]*)\"$")
	public void the_delivery_status_should_be_update(String status) throws Throwable {
		boolean isRecordExists = deliveryDB.isRecordExistsForAsnId(context.getAsnId(),
				status);
		Assert.assertTrue("ITL does not exist for the ASN Closure " + status,
				isRecordExists);
	}
}
