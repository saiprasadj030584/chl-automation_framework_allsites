package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.SupplierSkuDB;
import com.jda.wms.pages.foods.SupplierSKUMaintenancePage;
import com.jda.wms.pages.foods.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SupplierSkuMaintenanceStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final SupplierSKUMaintenancePage supplierSKUMaintenancePage;
	private Context context;
	private Verification verification;
	private SupplierSkuDB supplierSkuDB;

	@Inject
	public SupplierSkuMaintenanceStepDefs(SupplierSKUMaintenancePage supplierSKUMaintenancePage, Context context,
			Verification verification, SupplierSkuDB supplierSkuDB) {
		this.supplierSKUMaintenancePage = supplierSKUMaintenancePage;
		this.context = context;
		this.verification = verification;
		this.supplierSkuDB = supplierSkuDB;
	}

	@When("^I search SKU id \"([^\"]*)\" and supplier \"([^\"]*)\"$")
	public void i_search_SKU_id_and_supplier(String skuId, String supplierId) throws Throwable {
		supplierSKUMaintenancePage.clickQueryButton();
		supplierSKUMaintenancePage.enterSkuId(skuId);
		supplierSKUMaintenancePage.enterSupplier(supplierId);
		supplierSKUMaintenancePage.clickExecuteButton();
	}

	@Then("^the description, supplier SKU details should be displayed for the given SKU id$")
	public void the_description_supplier_SKU_details_should_be_displayed_for_the_given_SKU_id() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String description = supplierSKUMaintenancePage.getDescription();
		if (description.isEmpty()) {
			failureList.add("Description is not as expected.");
		}
		logger.debug("SKU Description: " + description);

		String supplierSKU = supplierSKUMaintenancePage.getSupplierSKU();
		if (supplierSKU.isEmpty()) {
			failureList.add("Supplier SKU is not as expected.");
		}
		logger.debug("Supplier SKU: " + supplierSKU);

		Assert.assertTrue("Supplier SKU details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^No records should be displayed on Supplier SKU maintenance page$")
	public void no_records_should_be_displayed_on_Supplier_SKU_maintenance_page() throws Throwable {
		Assert.assertTrue("No records message exists", supplierSKUMaintenancePage.isNoRecords());
	}

	@Given("^the sku id \"([^\"]*)\" and supplier \"([^\"]*)\"$")
	public void the_SKU_id_and_supplier(String skuId, String supplierId) throws Throwable {
		context.setSkuId(skuId);
		context.setSupplierID(supplierId);
	}

	@Then("^the supplier SKU details should be displayed$")
	public void supplier_SKU_details_should_be_displayed() throws Throwable {
		String supplierSku = supplierSkuDB.getSupplierSKU(context.getSkuId(), context.getSupplierID());
		Assert.assertNotNull("Supplier SKU details are not as expected.", supplierSku);
	}

	@Then("^no records should be displayed$")
	public void no_records_should_be_displayed() throws Throwable {
		String queryResult = supplierSkuDB.getSupplierSKU(context.getSkuId(), context.getSupplierID());
		System.out.println();
		Assert.assertThat(queryResult, (CoreMatchers.containsString("Exhausted Resultset")));
	}
}
