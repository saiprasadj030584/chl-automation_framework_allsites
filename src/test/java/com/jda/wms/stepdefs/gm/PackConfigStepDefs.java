
package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PackConfigStepDefs {

	private Context context;

	@Inject
	public PackConfigStepDefs(Context context) {

		this.context = context;
	}

	@Given("^the SKU \"([^\"]*)\" is loaded in warehouse$")
	public void the_SKU_is_loaded_in_warehouse(String arg1) throws Throwable {

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
