package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.PickFaceTableDB;
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
	private final PickFaceTableDB pickFaceTableDB;
	Map<String, Map<String, String>> purchaseOrderMap;

	@Inject
	public PickFaceMaintenanceStepDefs(PickFaceMaintenancePage pickFaceMaintenancPage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, LocationMaintenancePage locationMaintenancePage,
			WarningPopUpPage warningPopUpPage, JdaHomePage jdaHomePage,PickFaceTableDB pickFaceTableDB) {
		this.pickFaceMaintenancPage = pickFaceMaintenancPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.locationMaintenancePage = locationMaintenancePage;
		this.warningPopUpPage = warningPopUpPage;
		this.jdaHomePage = jdaHomePage;
		this.pickFaceTableDB = pickFaceTableDB;
	}

	@Given("^the location id \"([^\"]*)\" is no more exist in the location maintenance$")
	public void the_location_id_is_no_more_exist_in_the_location_maintenance(String location) throws Throwable {
		jdaHomeStepDefs.i_am_on_to_pick_face_maintenance_page();
		context.setLocation(location);
		jdaFooter.clickQueryButton();
		pickFaceMaintenancPage.enterLocation(location);
		jdaFooter.clickExecuteButton();

		if (pickFaceMaintenancPage.isNoRecordDisplayed()) {
			jdaFooter.clickCancelButton();
		} else {
			jdaFooter.clickDeleteButton();
		}
	}

	@When("^I add the location Id \"([^\"]*)\" with face type \"([^\"]*)\", sku \"([^\"]*)\", site id \"([^\"]*)\"$")
	public void i_add_the_location_Id_with_face_type_sku_site_id(String location, String facetype, String skuId,
			String siteId) throws Throwable {
		context.setFaceType(facetype);
		context.setSkuId(skuId);
		context.setSiteId(siteId);
		
		jdaFooter.clickAddButton();
		pickFaceMaintenancPage.selectFaceType(facetype);
		pickFaceMaintenancPage.enterLocation(location);
		pickFaceMaintenancPage.enterSkuId(skuId);
		pickFaceMaintenancPage.selectSiteId(siteId);
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
	
	@Given("^the inventory exists for pick face location$")
	public void the_inventory_exists_for_pick_face_location() throws Throwable {
		int QtyOnHand = 0;
		ArrayList<String> failureList = new ArrayList<String>();
		purchaseOrderMap = context.getPurchaseOrderMap();

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			QtyOnHand = Integer.parseInt(pickFaceTableDB.getQuantityOnHand(skuID));
			if (!(QtyOnHand > 0)) {
				failureList.add(
						"QuantityOn Hand is not as expected, Expected Qty to be greater than 0 but was " + QtyOnHand);
			}
			Assert.assertTrue("QuantityOn is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
					failureList.isEmpty());
		}
	}
	
	@Given("^I get the pick face location$")
	public void i_get_the_pick_face_location() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		Map<String, String> putawayLocationMap = new HashMap<String, String>();

		purchaseOrderMap = context.getPurchaseOrderMap();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			putawayLocationMap.put(skuID, pickFaceTableDB.getPickfaceLocation(skuID));
		}
		context.setPutawayLocationMap(putawayLocationMap);
	}
}
