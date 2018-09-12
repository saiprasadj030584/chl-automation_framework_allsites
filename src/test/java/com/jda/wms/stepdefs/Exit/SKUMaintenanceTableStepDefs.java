package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SKUMaintenancePage;
import com.jda.wms.pages.Exit.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class SKUMaintenanceTableStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final SKUMaintenancePage sKUMaintenancePage;
	private Context context;
	private Utilities utilities;
	private Verification verification;
	private SkuDB skuDB;

	@Inject
	public SKUMaintenanceTableStepDefs(SKUMaintenancePage sKUMaintenancePage, Context context, Utilities utilities,
			Verification verification, SkuDB skuDB) {
		this.sKUMaintenancePage = sKUMaintenancePage;
		this.context = context;
		this.utilities = utilities;
		this.verification = verification;
		this.skuDB = skuDB;
	}

	 @When("^I search for the SKU id \"([^\"]*)\"$")
	 public void i_search_for_the_SKU_id(String skuId) throws Throwable {
	 sKUMaintenancePage.searchSKUid(skuId);
	 context.setSkuId(skuId);
	 }
	
	 @Then("^the SKU id and SKU description fields should be displayed$")
	 public void the_SKU_id_and_SKU_description_fields_should_be_displayed()
	 throws Throwable {
	 ArrayList<String> failureList = new ArrayList<String>();
	
	 // Get SKU ID
	 String skuid = sKUMaintenancePage.getSKUid();
	 if (!context.getSkuId().equals(skuid)) {
	 failureList.add("SKU ID is not displayed as expected. Expected [" +
	 context.getSkuId() + "] but was ["
	 + skuid + "]");
	 }
	 logger.debug("SKU ID: " + skuid);
	
	 // Get SKU Description
	 String skuDesc = sKUMaintenancePage.getSKUDescription();
	 if (skuDesc.equals(null)) {
	 failureList.add("SKU Description s not displayed as expected. Expected [Not NULL value] but was [" + skuDesc + "]");
	 }
	 logger.debug("SKU Description: " + skuDesc);
	
	 Assert.assertTrue("SKU Attributes are not as expected. [" +
	 Arrays.asList(failureList.toArray()) + "].",
	 failureList.isEmpty());
	 }
	
	 @Then("^the product group, EAN, UPC, AllocationGroup, each quantity, tagmerge fields should be displayed in settings1 tab$")
	 public void the_product_group_EAN_UPC_allocation_group_each_quantity_tag_merge_fields_should_be_displayed_in_settings1_tab()
	 throws Throwable {
	 ArrayList<String> failureList = new ArrayList<String>();
	
	 // Get Product Group Value
	 String productGroup = sKUMaintenancePage.getProductGroup();
	 context.setProductGroup(productGroup);
	 if (productGroup.equals(null)) {
	 failureList.add("Product Group is not displayed as expected. Expected [Not NULL value] but was [" + productGroup + "]");
	 }
	 logger.debug("Product Group: " + productGroup);
	
	 // Get AllocationGroup Value
	 String allocationGroup = sKUMaintenancePage.getAllocationGroup();
	 context.setAllocationGroup(allocationGroup);
	 if (allocationGroup.equals(null)) {failureList.add("AllocationGroup is not displayed as expected.Expected [Not NULL value] but was [" + allocationGroup + "]");
	 }
	 logger.debug("AllocationGroup: " + allocationGroup);
	
	 // Get EAN Value
	 String ean = sKUMaintenancePage.getEAN();
	 context.setEAN(ean);
	 if (ean.equals(null)) {
	 failureList.add("EAN is not displayed as expected. Expected [Not NULL value] but was [" + ean + "]");
	 }
	 logger.debug("EAN: " + ean);
	
	 // Get UPC Value
	 String upc = sKUMaintenancePage.getUPC();
	 if (upc.equals(null)) {
	 failureList.add("UPC is not displayed as expected. Expected [Not NULL value] but was [" + upc + "]");
	 }
	 logger.debug("UPC: " + upc);
	
	 // Get Each Quantity value
	 int eachQtyInt =
	 utilities.convertStringToInteger(sKUMaintenancePage.getEachQuantity());
	 if (eachQtyInt <= 0) {
	 failureList.add("Each Quantity is not displayed as expected. Expected [>0] but was [" + eachQtyInt + "]");
	 }
	 logger.debug("Each Quantity: " + eachQtyInt);
	
	 // Validate Tag Merge check box
	 boolean isTagMergeUnchecked = sKUMaintenancePage.isTagMergeUnchecked();
	 if (!isTagMergeUnchecked) {
	 failureList.add("Tag Merge is not displayed as expected. Expected [Not Checked] but was [Checked]");
	 }
	 logger.debug("Tag Merge Unchecked?: " + isTagMergeUnchecked);
	
	 Assert.assertTrue("Setting 1 attributes are not as expected. [" +
	 Arrays.asList(failureList.toArray()) + "].",
	 failureList.isEmpty());
	 }
	
	 @When("^I navigate to setting4 tab$")
	 public void i_navigate_to_setting4_tab() throws Throwable {
	 sKUMaintenancePage.navigateToSettings4();
	 }
	
	 @Then("^the new product field should be displayed in page$")
	 public void the_new_product_Field_should_be_displayed_in_page() throws Throwable {
	 boolean isNewProductChecked = sKUMaintenancePage.isNewProductChecked();
	 Assert.assertTrue("New Product is not displayed as expected. Expected [Checked] but was [Not Checked]",
	 isNewProductChecked);
	 logger.debug("New Product Checked?: " + isNewProductChecked);
	 }
	
	 @When("^I navigate to customs & excise tab$")
	 public void i_navigate_to_customs_excise_tab() throws Throwable {
	 sKUMaintenancePage.clickCustomsAndExcise();
	 }
	
	 @Then("^the C&E warehouse type, C&E VAT code, C&E SKU, C&E alcoholic strength fields should be displayed in page$")
	 public void the_C_E_warehouse_type_C_E_VAT_code_C_E_SKU_C_E_alcoholic_strength_fields_should_be_displayed_in_page()
	 throws Throwable {
	 ArrayList<String> failureList = new ArrayList<String>();
	
	 // Validate C&E Warehouse Type value
	 String cewarehousetype = sKUMaintenancePage.getCEWarehouseType();
	 context.setCEWarehouseType(cewarehousetype);
	 if ((context.getProductGroup().equalsIgnoreCase("F20")) ||
	 (context.getProductGroup().equalsIgnoreCase("F21"))
	 || (context.getProductGroup().equalsIgnoreCase("F23"))
	 || (context.getProductGroup().equalsIgnoreCase("F07"))) {
	 if (!cewarehousetype.equalsIgnoreCase("Excise")) {
	 failureList.add("C&E Warehouse Type is not displayed as expected for BWS.Expected [E] but was [" + cewarehousetype + "]");
	 }
	 } else {
	 if (!cewarehousetype.equalsIgnoreCase("Neither")) {
	 failureList
	 .add("C&E Warehouse Type is not displayed as expected for Ambient.Expected [Neither] but was ["+ cewarehousetype + "]");
	 }
	 }
	 logger.debug("C&E Warehouse Type: " + cewarehousetype);
	
	 // Get C&E VAT Code
	 String cevatcode = sKUMaintenancePage.getCEVatCode();
	 if (cevatcode.equals(null)) {
	 failureList.add("EAN is not displayed as expected. Expected [Not NULL value] but was [" + cevatcode + "]");
	 }
	 logger.debug("C&E VAT Code: " + cevatcode);
	
	 // Validate C&E SKU check box
	 boolean isCESKUChecked = sKUMaintenancePage.isCESKUChecked();
	 if ((context.getProductGroup().equalsIgnoreCase("F20")) ||
	 (context.getProductGroup().equalsIgnoreCase("F21"))
	 || (context.getProductGroup().equalsIgnoreCase("F23"))
	 || (context.getProductGroup().equalsIgnoreCase("F07"))) {
	 if (!isCESKUChecked) {
	 failureList
	 .add("C&E SKU is not displayed as expected for BWS. Expected [Checked] but was [Not Checked]");
	 }
	 } else {
	 if (isCESKUChecked) {
	 failureList.add("C&E SKU is not displayed as expected for Ambient. Expected [Not Checked] but was [Checked]");
	 }
	 }
	 logger.debug("CE SKU Checked?: " + isCESKUChecked);
	
	 // Get C&E Alcoholic Strength
	 String ceAlcoholicStrength = sKUMaintenancePage.getCEAlcoholicStrength();
	 if (ceAlcoholicStrength.equals(null)) {
	 failureList.add("C&E Alcoholic Strength is not displayed as expected. Expected [Not NULL value] but was ["
	 + ceAlcoholicStrength + "]");
	 }
	 logger.debug("C&E Alcoholic Strength: " + ceAlcoholicStrength);
	
	 Assert.assertTrue(
	 "Customs & Excise attributes are not as expected. [" +
	 Arrays.asList(failureList.toArray()) + "].",
	 failureList.isEmpty());
	
	 }
	
	 @When("^I navigate to linking tab$")
	 public void i_navigate_to_linking_tab() throws Throwable {
	 sKUMaintenancePage.navigateToLinking();
	 }
	
	 @Then("^the site id should be displayed$")
	 public void the_site_id_should_be_displayed() throws Throwable {
	 boolean isSiteIdExist = sKUMaintenancePage.isSiteIdExist();
	 Assert.assertTrue("Site ID is not displayed as expected. Expected [9771] but was [Not 9771]",
	 sKUMaintenancePage.isSiteIdExist());
	 logger.debug("Site ID Exists?: " + isSiteIdExist);
	 }
	
	 @When("^I navigate to batch & expiry tab$")
	 public void i_navigate_to_batch_expiry_tab() throws Throwable {
	 sKUMaintenancePage.navigateToBatchExpiry();
	 }
	
	 @Then("^the expiry required should be displayed in page$")
	 public void the_expiry_required_should_be_displayed_in_page() throws Throwable {
	 boolean isExpiryRequiredUnchecked =sKUMaintenancePage.isExpiryRequiredUnchecked();
	
	 if (context.getAllocationGroup().equalsIgnoreCase("NONEXPIRY")) {
	 Assert.assertTrue("Expiry Required is not displayed as expected. Expected [NOT Checked] but was [Checked]",
	 sKUMaintenancePage.isExpiryRequiredUnchecked());
	 } 
	 else if (context.getAllocationGroup().equalsIgnoreCase("EXPIRY")) {
	 Assert.assertFalse("Expiry Required is not displayed as expected. Expected [Checked] but was [Not Checked]",
	 sKUMaintenancePage.isExpiryRequiredUnchecked());
	 }
	 logger.debug("Expiry Required?: " + isExpiryRequiredUnchecked);
	 }
	
	 @When("^I navigate to user defined tab$")
	 public void i_navigate_to_user_defined_tab() throws Throwable {
	 sKUMaintenancePage.clickUserDefined();
	 }
	
	 @Then("^the base UOM, SAP creation status should be displayed in page$")
	 public void the_base_UOM_SAP_creation_status_should_be_displayed_in_page() throws
	 Throwable {
	 ArrayList<String> failureList = new ArrayList<String>();
	
	 // Get Base UOM value
	 String baseUom = sKUMaintenancePage.getBaseUOM();
	 if (baseUom.equals(null)) {
	 failureList.add("Base UOM is not displayed as expected. Expected [Not NULL value] but was [" + baseUom + "]");
	 }
	 logger.debug("Base UOM: " + baseUom);
	
	 // Get SAP Creation Status value
	 int sapCreationStatus =
	 utilities.convertStringToInteger(sKUMaintenancePage.getSAPCreationStatus());
	 if ((sapCreationStatus != 3) || (sapCreationStatus != 4) ||
	 (sapCreationStatus != 5)
	 || (sapCreationStatus != 6)) {
	 failureList.add("SAP Creation Status is not displayed as expected.Expected [3 or 4 or 5 or 6] but was ["
	 + sapCreationStatus + "]");
	 }
	 Assert.assertTrue(
	 "User defined attributes are not as expected. [" +
	 Arrays.asList(failureList.toArray()) + "].",
	 failureList.isEmpty());
	 logger.debug("SAP Creation Status: " + sapCreationStatus);
	 }
	
	 @When("^I navigate to supplier SKU tab$")
	 public void i_navigate_to_supplier_SKU_tab() throws Throwable {
	 sKUMaintenancePage.navigateToSupplierSKU();
	 }
	
	 @Then("^the supplier SKU id should be displayed$")
	 public void the_supplier_SKU_id_should_be_displayed() throws Throwable {
	 String supplierSKUId = sKUMaintenancePage.getSupplierSKUId();
	 Assert.assertEquals("Supplier SKU ID is not displayed as expected",
	 context.getEAN(),
	 sKUMaintenancePage.getSupplierSKUId());
	 logger.debug("Supplier SKU ID: " + supplierSKUId);
	 }
	
	 @Then("^the product AllocationGroup field should be displayed$")
	 public void the_product_allocation_group_field_should_be_displayed()
	 throws Throwable {
	 Assert.assertEquals("Allocation grouo does not match", "EXPIRY",
	 sKUMaintenancePage.getAllocationGroup());
	 }
	
	@Given("^the Sku id \"([^\"]*)\"$")
	public void the_Sku_id(String SkuId) throws Throwable {
		context.setSkuId(SkuId);
	}

	@Then("^the SKU description,product group, EAN, UPC, allocation group, each quantity, tag merge fields should be displayed in settings(\\d+) tab$")
	public void the_SKU_description_product_group_EAN_UPC_allocation_group_each_quantity_tag_merge_fields_should_be_displayed_in_settings_tab(
			int arg1) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData("description", "Not Null", skuDB.getDescription(context.getSkuId()), failureList);
		context.setProductGroup(skuDB.getProductGroup(context.getSkuId()));
		verification.verifyData("product group", "Not Null", context.getProductGroup(), failureList);
		context.getEAN();
		verification.verifyData("EAN", "Not Null", skuDB.getEAN(context.getSkuId()), failureList);
		verification.verifyData("UPC", "Not Null", skuDB.getUPC(context.getSkuId()), failureList);
		context.setAllocationGroup(skuDB.getAllocationGroup(context.getSkuId()));
		verification.verifyData("allocation group", "Not Null", context.getAllocationGroup(), failureList);
		int eachQtyInt = utilities.convertStringToInteger(skuDB.getEachQuantity(context.getSkuId()));
		// double eachQuantity =
		// Double.parseDouble(sKUMaintenancePage.getEachQuantity());
		// int eachQtyInt = (int) eachQuantity;
		if (eachQtyInt <= 0) {
			failureList.add("Each Quantity is not displayed as expected. Expected [>0] but was [" + eachQtyInt + "]");
		}

		verification.verifyData("tag merge", "N", skuDB.getIsTagMergeUnchecked(context.getSkuId()), failureList);

		Assert.assertTrue("Setting 1 attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the new product field should be displayed$")
	public void the_new_product_field_should_be_displayed() throws Throwable {
		Assert.assertEquals("The new product is not checked.", "Y", skuDB.isNewProductChecked(context.getSkuId()));
	}

	@Then("^the C&E warehouse type, C&E VAT code, C&E SKU, C&E alcoholic strength fields should be displayed$")
	public void the_C_E_warehouse_type_C_E_VAT_code_C_E_SKU_C_E_alcoholic_strength_fields_should_be_displayed()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String cewarehousetype = skuDB.getCEWarehouseType(context.getSkuId());
		context.setCEWarehouseType(cewarehousetype);
		if ((context.getProductGroup().equalsIgnoreCase("F20")) || (context.getProductGroup().equalsIgnoreCase("F21"))
				|| (context.getProductGroup().equalsIgnoreCase("F23"))
				|| (context.getProductGroup().equalsIgnoreCase("F07"))) {
			if (!cewarehousetype.equalsIgnoreCase("E")) {
				failureList.add("C&E Warehouse Type is not displayed as expected for BWS. Expected [Excise/E] but was ["
						+ cewarehousetype + "]");
			}
		} else {
			if (!cewarehousetype.equalsIgnoreCase("N")) {
				failureList
						.add("C&E Warehouse Type is not displayed as expected for Ambient. Expected [Neither/N] but was ["
								+ cewarehousetype + "]");
			}

			verification.verifyData("C&E VAT code", "Not Null", skuDB.getCEVATCode(context.getSkuId()), failureList);
			verification.verifyData("CE SKU", "Y", skuDB.getCESKUcheckedValue(context.getSkuId()), failureList);
			verification.verifyData("C&E alcoholic strength ", "Not Null",
					skuDB.getCEAlcoholicStrength(context.getSkuId()), failureList);

			Assert.assertTrue(
					"Customs & Excise attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
					failureList.isEmpty());
		}

	}

	@Then("^the expiry required should be displayed$")
	public void the_expiry_required_should_be_displayed() throws Throwable {
		String expiryRequiredUnchecked = skuDB.ExpiryRequiredUncheckedValue(context.getSkuId());
		if (context.getAllocationGroup().equalsIgnoreCase("NONEXPIRY")) {
			Assert.assertEquals("Expiry Required is not displayed as expected. Expected", "N",
					skuDB.ExpiryRequiredUncheckedValue(context.getSkuId()));
		} else if (context.getAllocationGroup().equalsIgnoreCase("EXPIRY")) {
			Assert.assertEquals("Expiry Required is not displayed as expected.", "Y",
					skuDB.ExpiryRequiredUncheckedValue(context.getSkuId()));
		}
	}

	@Then("^the base UOM, SAP creation status should be displayed$")
	public void the_base_UOM_SAP_creation_status_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData(" base UOM ", "Not Null", skuDB.getBaseUOM(context.getSkuId()), failureList);

		int sapCreationStatus = utilities.convertStringToInteger(skuDB.getSAPCreationStatus(context.getSkuId()));
		if ((sapCreationStatus != 3) && (sapCreationStatus != 4) && (sapCreationStatus != 5)
				&& (sapCreationStatus != 6)) {
			failureList.add("SAP Creation Status is not displayed as expected. Expected [3 or 4 or 5 or 6] but was ["
					+ sapCreationStatus + "]");
		}
		Assert.assertTrue(
				"User defined attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
}
