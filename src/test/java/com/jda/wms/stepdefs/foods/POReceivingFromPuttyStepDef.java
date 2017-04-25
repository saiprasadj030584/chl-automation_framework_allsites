package com.jda.wms.stepdefs.foods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.POReceivingFromPuttyPage;
import com.jda.wms.pages.foods.StockAdjustmentsPage;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.*;
import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;

import org.junit.Assert;

public class POReceivingFromPuttyStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private POReceivingFromPuttyPage poReceivingFromPuttyPage;
	private Context context;
	private Utilities utilities;
	private DateUtils dateUtils;

	@Inject
	public POReceivingFromPuttyStepDef(POReceivingFromPuttyPage poReceivingFromPuttyPage, Context context,
			Utilities utilities, DateUtils dateUtils) {
		this.poReceivingFromPuttyPage = poReceivingFromPuttyPage;
		this.context = context;
		this.utilities = utilities;
		this.dateUtils = dateUtils;
	}

	@Given("^I have logged in as warehouse user in Putty with host \"([^\"]*)\" and port \"([^\"]*)\"$")
	public void i_have_logged_in_as_warehouse_user_in_Putty_with_host_and_port(String host, String port)
			throws Throwable {
		poReceivingFromPuttyPage.invokePutty();
		poReceivingFromPuttyPage.loginPutty(host, port);
	}

	@When("^I select the user directed receiving option in main menu$")
	public void i_select_the_user_directed_receiving_option_in_main_menu() throws Throwable {
		poReceivingFromPuttyPage.selectUserDirectedMenu();
	}

	@Given("^I select the receive under user menu$")
	public void i_select_the_receive_under_user_menu() throws Throwable {
		poReceivingFromPuttyPage.selectReceiveMenu();
	}

	@Given("^I select the basic receiving$")
	public void i_select_the_basic_receiving() throws Throwable {
		poReceivingFromPuttyPage.selectBasicReceiveMenu();
	}

	@Given("^I select pre-advice receive$")
	public void i_select_pre_advice_receive() throws Throwable {
		poReceivingFromPuttyPage.selectPreAdviceReceive();
	}

	@Then("^pre-advice entry page should be displayed$")
	public void pre_advice_entry_page_should_be_displayed() throws Throwable {
		Assert.assertTrue("Receive pre-Advice entry not displayed as expected.",
				poReceivingFromPuttyPage.isPreAdviceEntryDisplayed());
	}

	@When("^I enter Pre-advice id \"([^\"]*)\" and SKU id \"([^\"]*)\" to receive from Supplier \"([^\"]*)\"$")
	public void i_enter_Pre_advice_id_and_SKU_id_to_receive_from_Supplier(String preAdviceId, String skuID,
			String supplierID) throws Throwable {
		context.setSupplierID(supplierID);
		context.setPreAdviceId(preAdviceId);
		poReceivingFromPuttyPage.enterPreAdvEntryDetails(preAdviceId, skuID);
	}

	@Then("^pre-advice cmp page should be displayed$")
	public void pre_advice_cmp_page_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String preAdvDisplayed = poReceivingFromPuttyPage.getPreAdvDisplayed();
		if (!preAdvDisplayed.equalsIgnoreCase(context.getPreAdviceId())) {
			failureList.add("Pre-Advice ID not displayed as expected. Expected [" + context.getPreAdviceId()
					+ "] but was [" + preAdvDisplayed + "]");
		}

		String supplierIDDisplayed = poReceivingFromPuttyPage.getSupplierDisplayed();
		if (!supplierIDDisplayed.equalsIgnoreCase(context.getSupplierID())) {
			failureList.add("Supplier ID not displayed as expected. Expected [" + context.getSupplierID()
					+ "] but was [" + supplierIDDisplayed + "]");
		}

		Assert.assertTrue("Pre-Adv cmp page not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I enter the location \"([^\"]*)\" and unique tag$")
	public void i_enter_the_location_and_unique_tag(String location) throws Throwable {
		poReceivingFromPuttyPage.enterLocation(location);

		String uniqueId = utilities.getTenDigitRandomNumber();
		poReceivingFromPuttyPage.enterUniqueTagId(uniqueId);
	}

	@When("^I enter the quantity to receive \"([^\"]*)\" and case ratio \"([^\"]*)\"$")
	public void i_enter_the_quantity_to_receive_and_case_ratio(String qtyToReceive, String caseRatio) throws Throwable {
		poReceivingFromPuttyPage.enterQtyToReceive(qtyToReceive);
		poReceivingFromPuttyPage.enterCaseRatio(caseRatio);
	}

	@When("^I specify expiry details$")
	public void i_specify_expiry_details() throws Throwable {
		String expDate = dateUtils.getAddedSystemYear();
		poReceivingFromPuttyPage.enterExpiryDate(expDate);
	}

	@Then("^the receiving should be complete$")
	public void the_receiving_should_be_complete() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed.",
				poReceivingFromPuttyPage.isPreAdviceEntryDisplayed());
	}
}
