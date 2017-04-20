package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import cucumber.api.java.en.*;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryTransactionQueryStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;

	@Inject
	public InventoryTransactionQueryStepDef(InventoryTransactionQueryPage inventoryTransactionQueryPage, Context context) {
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
	}
	
	@When("^I search tag id \"([^\"]*)\" and code as \"([^\"]*)\"$")
	public void i_search_tag_id_and_code_as(String tagId, String code) throws Throwable {
		context.setCode(code);
		inventoryTransactionQueryPage.searchTagID(tagId,code);
	}
	
	@Then("^I should see the original quantity and updated quantity in the general tab$")
	public void i_should_see_the_original_quantity_and_updated_quantity_in_the_general_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		
		String originalQty = inventoryTransactionQueryPage.getOriginalQty();
		if (originalQty.equals(context.getqtyOnHandBfrAdjustment())) {
			failureList.add("Original Quantity before Adjustment is not displayed as expected. Expected ["+context.getqtyOnHandBfrAdjustment()+"] but was ["
					+ originalQty + "]");
		}
		logger.debug("Original Quantity: " + originalQty);
		
		String updateQty = inventoryTransactionQueryPage.getUpdateQty();
		if (context.getAdjustmentType().equalsIgnoreCase("Decrement")){
			
			if (updateQty.equals("-"+context.getCaseRatio())) {
				failureList.add("Update Quantity is not displayed as expected. Expected ["+"-"+context.getCaseRatio()+"] but was ["
						+ updateQty + "]");
			}
		}
		else if (context.getAdjustmentType().equalsIgnoreCase("Increment")){
			
			if (updateQty.equals("+"+context.getCaseRatio())) {
				failureList.add("Update Quantity is not displayed as expected. Expected ["+"+"+context.getCaseRatio()+"] but was ["
						+ updateQty + "]");
			}
		}
		
		logger.debug("Update Quantity: " + updateQty);
		Assert.assertTrue("Stock Adjustment attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@When("^I navigate to miscellaneous tab$")
	public void i_navigate_to_miscellaneous_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}
	
		@Then("^I should see the readon code as \"([^\"]*)\"$")
		public void i_should_see_the_readon_code_as(String reasonCode) throws Throwable {
		String reasonCodeFromScreen = inventoryTransactionQueryPage.getReasonCode();
		
		if (reasonCode.equalsIgnoreCase("Damaged by Warehouse")){
			Assert.assertEquals("Reason Code not displayed as expected", "DW",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Expired Stock")){
			Assert.assertEquals("Reason Code not displayed as expected", "EX",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Head Office")){
			Assert.assertEquals("Reason Code not displayed as expected", "HO",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Hampers Stock")){
			Assert.assertEquals("Reason Code not displayed as expected", "HS",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Receiving Correction")){
			Assert.assertEquals("Reason Code not displayed as expected", "IE",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Infestation")){
			Assert.assertEquals("Reason Code not displayed as expected", "IF",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Outlets Stock")){
			Assert.assertEquals("Reason Code not displayed as expected", "OS",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Returns from RDC")){
			Assert.assertEquals("Reason Code not displayed as expected", "RT",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Stock Count")){
			Assert.assertEquals("Reason Code not displayed as expected", "SC",reasonCodeFromScreen);
		}
		else if (reasonCode.equalsIgnoreCase("Returns to Supplier")){
			Assert.assertEquals("Reason Code not displayed as expected", "ST",reasonCodeFromScreen);
		}
		logger.debug("Reason code: " + reasonCodeFromScreen);
	}
	
	@When("^I navigate to miscellaneous2 tab$")
	public void i_navigate_to_miscellaneous2_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneous2Tab();
	}
	
	@Then("^I should see the uploaded filename$")
	public void i_should_see_the_uploaded_filename() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		
		String uploadedValue = inventoryTransactionQueryPage.getUploaded();
		String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
		if (uploadedValue.equalsIgnoreCase("N")&&(uploadedFileName.equals(null))) {
			failureList.add("Uploaded File Name is not displayed as expected. Expected [NULL] but was ["
					+ uploadedFileName + "]");
		}
		else if (uploadedValue.equalsIgnoreCase("Y")&&(!uploadedFileName.equals(null))) {
			failureList.add("Uploaded File Name is not displayed as expected. Expected [Not NULL Value] but was ["
					+ uploadedFileName + "]");
		}
		logger.debug("Uploaded File Name: " + uploadedFileName);
		Assert.assertTrue("Miscellaneous 2 attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
}
