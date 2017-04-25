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
	public InventoryTransactionQueryStepDef(InventoryTransactionQueryPage inventoryTransactionQueryPage,
			Context context) {
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.context = context;
	}

	@When("^I search tag id \"([^\"]*)\", code as \"([^\"]*)\" and lock code as \"([^\"]*)\"$")
	public void i_search_tag_id_and_code_as(String tagId, String code, String lockCode) throws Throwable {
		context.setCode(code);
		inventoryTransactionQueryPage.searchTagID(tagId, code, lockCode);
	}

	@Then("^I should see the status as \"([^\"]*)\" in the transaction query$")
	public void the_I_should_see_the_status_as_and_lock_code_as_in_the_transaction_query(String status)
			throws Throwable {

		Assert.assertTrue("Status in Inventory Transaction screen is not as expected",
				inventoryTransactionQueryPage.verifyStatus(status));

	}

	@When("^I navigate to miscellaneous tab$")
	public void i_navigate_to_miscellaneous_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneousTab();
	}

	@Then("^I should see the readon code as \"([^\"]*)\"$")
	public void i_should_see_the_readon_code_as(String abv) throws Throwable {
		String actualReasonCode = inventoryTransactionQueryPage.getReasonCode();
		Assert.assertEquals("Reason Code not displayed as expected", "ABV", actualReasonCode);
		
	}

	@When("^I navigate to miscellaneous2 tab$")
	public void i_navigate_to_miscellaneous2_tab() throws Throwable {
		inventoryTransactionQueryPage.navigateToMiscellaneous2Tab();
	}

	@Then("^I should see the uploaded filename$")
	public void i_should_see_the_uploaded_filename() throws Throwable {
		String uploadedValue = inventoryTransactionQueryPage.getUploaded();
		String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
		if (uploadedValue.equalsIgnoreCase("N")) {
			Assert.assertNull("Uploaded File Name is not displayed as expected. Expected [NULL] but was ["
					+ uploadedFileName + "]", uploadedFileName);
		} else if (uploadedValue.equalsIgnoreCase("Y") && (!uploadedFileName.equals(null))) {
			Assert.assertNotNull("Uploaded File Name is not displayed as expected. Expected [Not NULL] but was ["
					+ uploadedFileName + "]", uploadedFileName);
		}
		logger.debug("Uploaded File Name: " + uploadedFileName);
	}
	@When("^I search tag id \"([^\"]*)\" with transaction code as \"([^\"]*)\"$")
	public void i_search_tag_id_with_transaction_code_as(String tagId, String selectType) throws Throwable {
		inventoryTransactionQueryPage.giveTagID(tagId,selectType);
	}
	@Then("^the SKU Id and Reference should be displayed$")
	public void the_SKU_Id_and_Reference_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String skuId = inventoryTransactionQueryPage.getSkuId();
		if (skuId.equals(null)) {
			failureList.add("Address type is not as expected. Expected [Not NULL] but was [" + skuId + "]");
		}
		String reference = inventoryTransactionQueryPage.getReference();
		if (reference.equals(null)) {
			failureList.add("Address type is not as expected. Expected [Not NULL] but was [" + reference + "]");
		}
	}

	@Then("^I should see the updated ABV in the inventory transaction query page$")
	public void i_should_see_the_updated_ABV_in_the_inventory_transaction_query_page() throws Throwable {
		String actualAbv=inventoryTransactionQueryPage.getAbv();
		if(!context.getABV().equals(actualAbv)){
		ArrayList<String> failureList = new ArrayList<String>();
		failureList.add("ABV is not as expected. Expected [Both should be same] but was [" + actualAbv + "]");
		
		}
	}

}
