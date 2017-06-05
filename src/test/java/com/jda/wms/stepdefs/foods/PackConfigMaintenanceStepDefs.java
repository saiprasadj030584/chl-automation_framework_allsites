package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.PackConfigMaintenanceDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.foods.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;;

public class PackConfigMaintenanceStepDefs {
	private final PackConfigMaintenancePage packConfigMaintenancePage;
	private final JDAFooter jdaFooter;
	private Context context;
	private Verification verification;
	private PackConfigMaintenanceDB packConfigMaintenanceDB;

	@Inject
	public PackConfigMaintenanceStepDefs(JDAFooter jdaFooter, PackConfigMaintenancePage packConfigMaintenancePage,
			Context context, Verification verification, PackConfigMaintenanceDB packConfigMaintenanceDB) {
		this.packConfigMaintenancePage = packConfigMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.verification = verification;
		this.packConfigMaintenanceDB = packConfigMaintenanceDB;
	}

	@When("^I search pack config id \"([^\"]*)\"$")
	public void i_search_pack_config_id(String packConfigId) throws Throwable {
		jdaFooter.clickQueryButton();
		packConfigMaintenancePage.enterPackConfigId(packConfigId);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the tag volume, volume at each details should be displayed in pack config$")
	public void the_tag_volume_volume_at_each_details_should_be_displayed_in_pack_config() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		if (packConfigMaintenancePage.getTagVolume().equals(null)) {
			failureList.add("Tag Volume is not as expected.");
		}

		// if (!packConfigMaintenancePage.checkVolumeAtEach()) {
		// failureList.add(" Volume at Each is not checked.");
		// }

		Assert.assertTrue("Pack Config general details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I navigate to tracking levels page$")
	public void i_navigate_to_tracking_levels_page() throws Throwable {
		packConfigMaintenancePage.clickTrackingLevelsTab();
	}

	@Then("^the tracking levels and ratios should be displayed in tracking level tab$")
	public void the_tracking_levels_and_ratios_should_be_displayed_tracking_level_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String ration1To2 = packConfigMaintenancePage.getRatio1To2();
		if (!ration1To2.equals("0")) {
			failureList.add("Ratio 1 to 2 is not as expected. Expected [greater than 0] but was [" + ration1To2 + "]");
		}

		String trackingLevel1 = packConfigMaintenancePage.getTrackingLevel1();
		if (!trackingLevel1.equals("EA")) {
			failureList.add("Tracking Level 1 is not as expected. Expectecd [EA] but was " + trackingLevel1 + "]");
		}

		String trackingLevel2 = packConfigMaintenancePage.getTrackingLevel2();
		if (!trackingLevel2.equals("CASE")) {
			failureList.add("Tracking Level 2 is not as expected. Expectecd [CASE] but was " + trackingLevel2 + "]");
		}

		Assert.assertTrue(
				"Pack Config tracking level details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I navigate to RDT page$")
	public void i_navigate_to_RDT_page() throws Throwable {
		packConfigMaintenancePage.clickRDTTab();
	}

	@Then("^the RDT tracking levels 1 and 2 should be displayed in RDT$")
	public void the_RDT_tracking_levels_1_and_2_should_be_displayed_in_rdt() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String rdtTrackingLevel1 = packConfigMaintenancePage.getRDTTrackingLevel1();
		if (!rdtTrackingLevel1.equals("E")) {
			failureList
					.add("RDT Tracking Level 1 is not as expected. Expectecd [E] but was " + rdtTrackingLevel1 + "]");
		}

		String rdtTrackingLevel2 = packConfigMaintenancePage.getRDTTrackingLevel2();
		if (!rdtTrackingLevel2.equals("C")) {
			failureList
					.add("RDT Tracking Level 1 is not as expected. Expectecd [E] but was " + rdtTrackingLevel2 + "]");
		}

		Assert.assertTrue(
				"Pack Config RDT Tracking Levels details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the pack config id \"([^\"]*)\"$")
	public void the_pack_config_id(String packConfigID) throws Throwable {
		context.setPackConfigID(packConfigID);
	}

	@Then("^the tag volume, volume at each details should be displayed$")
	public void the_tag_volume_volume_at_each_details_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData("Tag volume", "Not Null",
				packConfigMaintenanceDB.getTagvolume(context.getPackConfigID()), failureList);
		verification.verifyData("Volume at each", "Y",
				packConfigMaintenanceDB.getIsVolumeAtEachChecked(context.getPackConfigID()), failureList);
		Assert.assertTrue("Pack Config general details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the tracking levels and ratios should be displayed$")
	public void the_tracking_levels_and_ratios_should_be_displayed() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		int ration1To2 = Integer.parseInt(packConfigMaintenanceDB.getRatio1To2(context.getPackConfigID()));
		if (ration1To2 <= 0) {
			failureList.add("Ratio 1 to 2 is not as expected. Expected [greater than 0] but was [" + ration1To2 + "]");
		}
		verification.verifyData("TrackingLevel1", "EA",
				packConfigMaintenanceDB.getTrackingLevel1(context.getPackConfigID()), failureList);
		verification.verifyData("TrackingLeve2", "CASE",
				packConfigMaintenanceDB.getTrackingLevel2(context.getPackConfigID()), failureList);
		Assert.assertTrue(
				"Pack Config tracking level details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^the RDT tracking levels (\\d+) and (\\d+) should be displayed$")
	public void the_RDT_tracking_levels_and_should_be_displayed(int arg1, int arg2) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		verification.verifyData(" rdtTrackingLevel1", "E",
				packConfigMaintenanceDB.getRdtTrackingLevel1(context.getPackConfigID()), failureList);
		verification.verifyData(" rdtTrackingLevel2", "C",
				packConfigMaintenanceDB.getRdtTrackingLevel2(context.getPackConfigID()), failureList);
		Assert.assertTrue(
				"Pack Config RDT Tracking Levels details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

	}
}