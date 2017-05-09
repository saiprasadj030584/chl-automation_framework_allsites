package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.PickFaceMaintenancePage;
import com.jda.wms.pages.foods.WarningPopUpPage;

import cucumber.api.java.en.*;

public class PickFaceMaintenanceStepDefs {
	private final PickFaceMaintenancePage pickFaceMaintenancPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private LocationMaintenancePage locationMaintenancePage;
	private WarningPopUpPage warningPopUpPage;
	private final JdaHomePage jdaHomePage;

	@Inject
	public PickFaceMaintenanceStepDefs(PickFaceMaintenancePage pickFaceMaintenancPage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, LocationMaintenancePage locationMaintenancePage,
			WarningPopUpPage warningPopUpPage,JdaHomePage jdaHomePage) {
		this.pickFaceMaintenancPage = pickFaceMaintenancPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.locationMaintenancePage = locationMaintenancePage;
		this.warningPopUpPage = warningPopUpPage;
		this.jdaHomePage =jdaHomePage;
	}

	@Given("^the location id \"([^\"]*)\" is no more eixst in the location maintenance$")
	public void the_location_id_is_no_more_eixst_in_the_location_maintenance(String location) throws Throwable {
		jdaHomeStepDefs.i_am_on_to_pick_face_maintenance_page();
		jdaFooter.clickQueryButton();
		pickFaceMaintenancPage.enterLocation(location);
		jdaFooter.clickExecuteButton();

		if (pickFaceMaintenancPage.isNoRecordDisplayed()) {
			// pickFaceMaintenancPage.selectNoRecord();
			jdaFooter.clickCancelButton();
		} else {
			jdaFooter.clickDeleteButton();
		}
	}

	@When("^I add the location Id \"([^\"]*)\" with face type \"([^\"]*)\", sku \"([^\"]*)\", site id \"([^\"]*)\"$")
	public void i_add_the_location_Id_with_face_type_sku_site_id(String location, String facetype, String skuId,
			String SiteId) throws Throwable {
		jdaFooter.clickAddButton();
		pickFaceMaintenancPage.selectFaceType(facetype);
		context.setFaceType("Fixed");

		pickFaceMaintenancPage.enterLocation(location);
		context.setLocation("AA01E02");

		pickFaceMaintenancPage.enterSkuId(skuId);
		context.setSkuId("20001273");

		pickFaceMaintenancPage.selectSiteId(SiteId);
		context.setSiteId("9771");

		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
	}
	
	@Then("^the location id should be added$")
	public void the_location_id_should_be_added() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String faceType = pickFaceMaintenancPage.getFaceType();
		if (!context.getFaceType().equals(faceType)) {
			failureList.add("FaceType is not as expected. Expected [" + context.getFaceType() + "]  but was ["
					+ faceType + "]");
		}

		String skuId = pickFaceMaintenancPage.getSkuId();
		if (!context.getSkuId().equals(skuId)) {
			failureList.add("SkuId is not as expected. Expected [" + context.getSkuId() + "] but was [" + skuId + "]");
		}

		String loaction = pickFaceMaintenancPage.getLocation();
		if (!context.getLocation().equals(loaction)) {
			failureList.add(
					"Location is not as expected. Expected [" + context.getLocation() + "] but was [" + loaction + "]");
		}

		String siteId = pickFaceMaintenancPage.getSiteId();
		if (!context.getSiteId().equals(siteId)) {
			failureList
					.add("SiteId is not as expected. Expected [" + context.getSiteId() + "] but was [" + siteId + "]");
		}

		Assert.assertTrue("Pick face details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Then("^I search location Id  \"([^\"]*)\"$")
	public void i_search_location_Id(String location) throws Throwable {
		jdaHomePage.navigateToLocationMaintanence();
		jdaFooter.clickQueryButton();
		locationMaintenancePage.enterLocation(location);
		jdaFooter.clickExecuteButton();
	}

	@Then("^the pick face should be updated as \"([^\"]*)\"$")
	public void the_pick_face_should_be_updated_as(String fixed) throws Throwable {
		Assert.assertEquals("Pick Face is not displayed as expected", context.getFaceType(),
				locationMaintenancePage.getPickFace());
	}
}
