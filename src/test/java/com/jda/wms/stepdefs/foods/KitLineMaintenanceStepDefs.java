package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.KitLineMaintenanceDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.KitLineMaintenancePage;
import com.jda.wms.pages.foods.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class KitLineMaintenanceStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final KitLineMaintenancePage kitLineMaintenancePage;
	private JDAFooter jdaFooter;
	private Context context;
	private Verification verification;
	private KitLineMaintenanceDB kitLineMaintenanceDB;

	@Inject
	public KitLineMaintenanceStepDefs(KitLineMaintenancePage kitLineMaintenancePage, JDAFooter jdaFooter,
			Verification verification, Context context, KitLineMaintenanceDB kitLineMaintenanceDB) {
		this.kitLineMaintenancePage = kitLineMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.verification = verification;
		this.kitLineMaintenanceDB = kitLineMaintenanceDB;
	}

	@Given("^I am on kit line maintenance page$")
	public void i_am_on_kit_line_maintenance_page() throws Throwable {
		kitLineMaintenancePage.navigateToKitLine();
	}

	@When("^I search with SKU id \"([^\"]*)\"$")
	public void i_search_with_SKU_id(String skuId) throws Throwable {
		jdaFooter.clickQueryButton();
		kitLineMaintenancePage.enterSKUId(skuId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the kit line details should be displayed for the given SKU id$")
	public void the_kit_line_details_should_be_displayed_for_the_given_SKU_id() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		// Get Kit ID Value
		String kitId = kitLineMaintenancePage.getKitId();
		if (kitId.equals(null)) {
			failureList.add("Kit Id is not displayed as expected. Expected [Not NULL value] but was [" + kitId + "]");
		}
		logger.debug("Kit ID: " + kitId);

		// Get Kit Quantity Value
		String kitQuantity = kitLineMaintenancePage.getQuantity();
		if (kitQuantity.equals(null)) {
			failureList.add(
					"Quantity is not displayed as expected. Expected [Not NULL value] but was [" + kitQuantity + "]");
		}
		logger.debug("Kit Quantity: " + kitQuantity);

		// Get Kit Line ID Value
		String kitLineId = kitLineMaintenancePage.getLineId();
		if (kitLineId.equals(null)) {
			failureList
					.add("Line Id is not displayed as expected. Expected [Not NULL value] but was [" + kitLineId + "]");
		}
		logger.debug("Kit Line ID: " + kitLineId);

		Assert.assertTrue(
				"Kit line maintenance details are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	// @Given("^the Sku id \"([^\"]*)\"$")
	// public void the_Sku_id(String SkuId) throws Throwable {
	// context.setSkuId(SkuId);
	// }

	@Then("^the kit line details should be displayed$")
	public void the_kit_line_details_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData(" kitId", "Not Null", kitLineMaintenanceDB.getkitId(context.getSkuId()), failureList);
		verification.verifyData(" Kit Quantity", "Not Null", kitLineMaintenanceDB.getKitQuantity(context.getSkuId()),
				failureList);
		verification.verifyData(" Kit Line ID ", "Not Null", kitLineMaintenanceDB.getKidLineID(context.getSkuId()),
				failureList);

		Assert.assertTrue(
				"Kit line maintenance details are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}
}
