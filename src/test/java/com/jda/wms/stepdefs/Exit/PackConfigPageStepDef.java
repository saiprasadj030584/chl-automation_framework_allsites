package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.pages.Exit.PackConfigMaintenancePage;
import com.jda.wms.pages.Exit.SiteQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PackConfigPageStepDef {

	private final PackConfigMaintenancePage packConfigMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PackConfigPageStepDef(PackConfigMaintenancePage packConfigMaintenancePage) {
		this.packConfigMaintenancePage = packConfigMaintenancePage;
	}

	@When("^Execute for verifying the fields$")
	public void execute_for_verifying_the_fields() throws  Throwable{
		packConfigMaintenancePage.clickQuery();
		packConfigMaintenancePage.execute();		
	}
	
	@Then("^Verify tag volume and tracking levels is auto-populated$")
	public void verify_tag_volume_tracking_levels_is_auto_populated() throws  Throwable{
		String TagVolume = packConfigMaintenancePage.getTagVolume();
		System.out.println("TagVolume is : " + TagVolume);
		Assert.assertEquals("TagVolume is auto-populated: ", TagVolume,TagVolume);
		packConfigMaintenancePage.clickTrackingLevelsTab();
		String TrackingLevels=packConfigMaintenancePage.getTrackingLevel1();
		Assert.assertEquals("TrackingLevels is auto-populated: ", TrackingLevels,TrackingLevels);
	}
    @Then("^Verify pack config is \"([^\"]*)\"$")
    public void verify_pack_config_is(String packConfig) throws  Throwable{
    	packConfigMaintenancePage.clickGeneraltab();
    	String PackConfig = packConfigMaintenancePage.getPackConfig();
    	System.out.println("PackConfig is:"+PackConfig);
    	Assert.assertEquals("packconfig not as expected", packConfig, PackConfig);
    }
}
