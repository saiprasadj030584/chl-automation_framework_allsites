
package com.jda.wms.stepdefs;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.DeliveryPage;
import com.jda.wms.UI.pages.JdaHomePage;
import com.jda.wms.context.Context;

import cucumber.api.java.en.Then;

public class DeliveryTableStepDefs{
	private JdaHomePage jdaHomePage;
	private DeliveryPage deliveryPage;
	
	@Inject
	public void DeliveryTableStepDefs(JdaHomePage jdaHomePage,DeliveryPage deliveryPage)
	{
		this.jdaHomePage=jdaHomePage;
		this.deliveryPage=deliveryPage;
	}
	
	 @Then("^Verify ASN in Delivery screen$")
	  public void verify_ASN_in_Delivery_screen() throws FindFailed, InterruptedException{
		  
		  jdaHomePage.navigateToDelivery();
		  deliveryPage.EnterASNID();
		  deliveryPage.ASN_Validation();
		 	 
	 }
	 @Then("^Verify Export criteria for ASN details$")
	 public void verify_export_criteria_for_ASN_details() throws FindFailed, InterruptedException
	 {
		 jdaHomePage.navigateToDelivery();
		  deliveryPage.EnterASNID();
		  deliveryPage.Supplier_Validation();
		  
	 }
	 @Then("^Verify Trailer content in Delivery screen$")
	 public void verify_trailer_content_in_delivery_screen() throws FindFailed, InterruptedException
	 {
		 jdaHomePage.navigateToDelivery();
		  deliveryPage.EnterASNID();
		  deliveryPage.ASN_Validation();
		 
	 }
	 @Then("^Verify the status of ASN in Delivery screen$")
	 public void verify_the_status_of_ASN_in_Delivery_screen() throws FindFailed, InterruptedException
	 {
		 jdaHomePage.navigateToDelivery();
	     deliveryPage.EnterASNID();
		 
	 }
	 
	 @Then("^verify Status as completed$")
	 public void  verify_Status_as_completed() throws FindFailed, InterruptedException
	 {
		 
	     deliveryPage.verifyStatus();
		 
	 }
	
}