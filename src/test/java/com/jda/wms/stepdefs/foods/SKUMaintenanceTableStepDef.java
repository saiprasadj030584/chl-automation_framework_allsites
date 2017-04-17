package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.SKUMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class SKUMaintenanceTableStepDef {

	private final SKUMaintenancePage sKUMaintenancePage;
	private Context context;

	@Inject
	public SKUMaintenanceTableStepDef(SKUMaintenancePage sKUMaintenancePage, Context context) {
		this.sKUMaintenancePage = sKUMaintenancePage;
		this.context = context;
	}

	@When("^I search for the SKU id \"([^\"]*)\"$")
	public void i_search_for_the_SKU_id(String skuId) throws Throwable {
		sKUMaintenancePage.searchSKUid(skuId);
		context.setSkuId(skuId);
	}

	@Then("^the SKU id and SKU description fields should be displayed$")
	public void the_SKU_id_and_SKU_description_fields_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String skuid = sKUMaintenancePage.getSKUid();
		if (!context.getSkuId().equals(skuid)) {
			failureList.add("SKU ID is not displayed as expected. Expected [" + context.getSkuId() + "] but was ["
					+ skuid + "]");
		}

		String skuDesc = sKUMaintenancePage.getSKUDescription();
		if (skuDesc.equals(null)) {
			failureList.add("SKU Description is not displayed as expected. Expected [Not NULL value] but was ["
					+ skuDesc + "]");
		}

		Assert.assertTrue("SKU Attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the product group, EAN, UPC, allocation group, each quantity, tag merge fields should be displayed in settings1 tab$")
	public void the_product_group_EAN_UPC_allocation_group_each_quantity_tag_merge_fields_should_be_displayed_in_settings1_tab() throws Throwable {

	}

	@When("^I navigate to setting4 tab$")
	public void i_navigate_to_setting4_tab() throws Throwable {

	}

	@Then("^the new product field should be displayed$")
	public void the_new_product_field_should_be_displayed() throws Throwable {

	}

	@When("^I navigate to customs & excise tab$")
	public void i_navigate_to_customs_excise_tab() throws Throwable {

	}

	@Then("^the C&E warehouse type, C&E VAT code, C&E SKU, C&E alcoholic strength fields should be displayed$")
	public void the_C_E_warehouse_type_C_E_VAT_code_C_E_SKU_C_E_alcoholic_strength_fields_should_be_displayed()
			throws Throwable {

	}

	@When("^I navigate to linking tab$")
	public void i_navigate_to_linking_tab() throws Throwable {

	}

	@Then("^the site id field value should be displayed$")
	public void the_site_id_field_value_should_be_displayed() throws Throwable {

	}

	@When("^I navigate to batch & expiry tab$")
	public void i_navigate_to_batch_expiry_tab() throws Throwable {

	}

	@Then("^the expiry required field should be displayed$")
	public void the_expiry_required_field_should_be_displayed() throws Throwable {

	}

	@When("^I navigate to user defined tab$")
	public void i_navigate_to_user_defined_tab() throws Throwable {

	}

	@Then("^the base UOM, SAP creation status fields should be displayed$")
	public void the_base_UOM_SAP_creation_status_fields_should_be_displayed() throws Throwable {

	}

	@When("^I navigate to supplier SKU tab$")
	public void i_navigate_to_supplier_SKU_tab() throws Throwable {

	}

	@Then("^the supplier SKU id field value should be displayed$")
	public void the_supplier_SKU_id_field_value_should_be_displayed() throws Throwable {

	}
}
