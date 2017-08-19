
package com.jda.wms.stepdefs.gm;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PreAdviceLineMaintenancePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PackConfigStepDefs {

	private Context context;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private JdaHomePage jdaHomePage;
	private SkuDB skuDB;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Verification verification;
	private PreAdviceLineDB preAdviceLineDB;
	private UPIReceiptLineDB upiReceiptLineDB;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDALoginStepDefs jdaLoginStepDefs;
	private PreAdviceLineMaintenancePage preAdviceLineMaintenancePage;
	private JDAFooter jdaFooter;

	@Inject
	public PackConfigStepDefs(Context context, Verification verification, PreAdviceLineDB preAdviceLineDB, JdaHomePage jdaHomePage,
			UPIReceiptLineDB upiReceiptLineDB, SkuDB skuDB, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, PreAdviceLineMaintenancePage preAdviceLineMaintenancePage,
			JDAFooter jdaFooter) {
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.verification = verification;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.skuDB = skuDB;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.preAdviceLineMaintenancePage = preAdviceLineMaintenancePage;
		this.jdaFooter = jdaFooter;
	}

	@Given("^the SKU \"([^\"]*)\" is loaded in warehouse$")
	public void the_SKU_is_loaded_in_warehouse(String skuId) throws Throwable {
		context.setSkuId(skuId);
		Assert.assertTrue("the SKU is loaded in warehouse", skuDB.validateSku(skuId));
		jdaHomePage.navigateToPackConfigPage();
	}

	@When("^I create config with TagVolume  \"([^\"]*)\" and TrackingLevel(\\d+)  \"([^\"]*)\"$")
	public void i_create_config_with_TagVolume_and_TrackingLevel(String arg1, int arg2, String arg3) throws Throwable {

	}

	@Then("^I link SKU \"([^\"]*)\" with configId in Pack Config Linking$")
	public void i_link_SKU_with_configId_in_Pack_Config_Linking(String arg1) throws Throwable {

	}

	@Then("^Pack Config should be displayed for SKU in Pack Config Setting$")
	public void pack_Config_should_be_displayed_for_SKU_in_Pack_Config_Setting() throws Throwable {

	}

}
