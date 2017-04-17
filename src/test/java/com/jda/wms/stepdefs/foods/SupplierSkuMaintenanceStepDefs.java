package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.SupplierSKUMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SupplierSkuMaintenanceStepDefs {

	private final SupplierSKUMaintenancePage supplierSKUMaintenancePage;

	@Inject
	public SupplierSkuMaintenanceStepDefs(SupplierSKUMaintenancePage supplierSKUMaintenancePage) {
		this.supplierSKUMaintenancePage = supplierSKUMaintenancePage;
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

		if (!supplierSKUMaintenancePage.getDescription().equals(null)) {
			failureList.add("Description is not as expected.");
		}

		if (!supplierSKUMaintenancePage.getSupplierSKU().equals(null)) {
			failureList.add("Supplier SKU is not as expected.");
		}
		Assert.assertTrue("Supplier SKU details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
}
