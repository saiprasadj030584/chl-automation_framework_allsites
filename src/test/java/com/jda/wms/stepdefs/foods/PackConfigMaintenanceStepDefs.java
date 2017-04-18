package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.FooterPage;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;;

public class PackConfigMaintenanceStepDefs {
	private final PackConfigMaintenancePage packConfigMaintenancePage;
	private final FooterPage footerPage;

	@Inject
	public PackConfigMaintenanceStepDefs(FooterPage footerPage, PackConfigMaintenancePage packConfigMaintenancePage) {
		this.packConfigMaintenancePage = packConfigMaintenancePage;
		this.footerPage = footerPage;
	}

	@When("^I search pack config id as \"([^\"]*)\"$")
	public void i_search_pack_config_id_as(String packConfigId) throws Throwable {
		footerPage.clickQueryButton();
		packConfigMaintenancePage.enterPackConfigId(packConfigId);
		footerPage.clickExecuteButton();
	}

	@Then("^the tag volume, volume at each details should be displayed for  given pack config id$")
	public void the_tag_volume_volume_at_each_details_should_be_displayed_for_given_pack_config_id() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		if (packConfigMaintenancePage.getTagVolume().equals(null)) {
			failureList.add("Tag Volume is not as expected.");
		}

		if (!packConfigMaintenancePage.checkVolumeAtEach()) {
			failureList.add(" Volume at Each is not checked.");
		}
		Assert.assertTrue("Pack Config general details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I click tracking levels tab$")
	public void i_click_tracking_levels_tab() throws Throwable {
		packConfigMaintenancePage.clickTrackingLevelsTab();
	}

	@Then("^the ratios, tracking levels sholud be displayed for given pack config id$")
	public void the_ratios_tracking_levels_sholud_be_displayed_for_given_pack_config_id() throws Throwable {

		ArrayList<String> failureList = new ArrayList<String>();

		if (packConfigMaintenancePage.getratios().equals(null)) {
			failureList.add("Ratio 1 to 2 is not as expected.");
		}

		if (!packConfigMaintenancePage.getTrackingLevel1().equals("EA")) {
			failureList.add("Tracking Level 1 is not as expected.");
		}

		if (!packConfigMaintenancePage.getTrackingLevel2().equals("CASE")) {
			failureList.add("Tracking Level 2 is not as expected.");
		}

		Assert.assertTrue(
				"Pack Config tracking level details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I click RDT tab$")
	public void i_click_RDT_tab() throws Throwable {
		packConfigMaintenancePage.clickRDTTab();
	}

	@Then("^the RDT tracking levels sholud be displayed for given pack config id$")
	public void the_RDT_tracking_levels_sholud_be_displayed_for_given_pack_config_id() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		if (!packConfigMaintenancePage.getRDTTrackingLevel1().equals("E")) {
			failureList.add("RDT Tracking Level 1 is not as expected.");
		}

		if (!packConfigMaintenancePage.getRDTTrackingLevel2().equals("C")) {
			failureList.add("RDT Tracking Level 2 is not as expected.");
		}

		Assert.assertTrue(
				"Pack Config RDT Tracking Levels details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
}