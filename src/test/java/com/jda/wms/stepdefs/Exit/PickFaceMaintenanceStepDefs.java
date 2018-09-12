package com.jda.wms.stepdefs.Exit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.LocationDB;
import com.jda.wms.db.Exit.PickFaceTableDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.PickFaceMaintenancePage;
import com.jda.wms.pages.Exit.WarningPopUpPage;

//import com.jda.wms.db.InventoryDB;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class PickFaceMaintenanceStepDefs {
	private final PickFaceMaintenancePage pickFaceMaintenancPage;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private LocationMaintenancePage locationMaintenancePage;
	private WarningPopUpPage warningPopUpPage;
	private final JdaHomePage jdaHomePage;
	private final PickFaceTableDB pickFaceTableDB;
	private final LocationDB locationDB;
	Map<String, Map<String, String>> purchaseOrderMap;
	private InventoryDB inventoryDB;
	private SystemAllocationStepDefs systemAllocationStepDefs;
	Screen screen = new Screen();
	//private LocationDB locationDB;

	@Inject
	public PickFaceMaintenanceStepDefs(PickFaceMaintenancePage pickFaceMaintenancPage, JDAFooter jdaFooter,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, LocationMaintenancePage locationMaintenancePage,
			WarningPopUpPage warningPopUpPage, JdaHomePage jdaHomePage, PickFaceTableDB pickFaceTableDB,
			InventoryDB inventoryDB, LocationDB locationDB, SystemAllocationStepDefs systemAllocationStepDefs) { {

		this.pickFaceMaintenancPage = pickFaceMaintenancPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.locationMaintenancePage = locationMaintenancePage;
		this.warningPopUpPage = warningPopUpPage;
		this.jdaHomePage = jdaHomePage;
		this.pickFaceTableDB = pickFaceTableDB;
		 this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.systemAllocationStepDefs=systemAllocationStepDefs;

			}
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
			warningPopUpPage.clickYes();
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
		warningPopUpPage.clickYes();
	}

	@When("^I add the location Id with face \"([^\"]*)\",sku,site id \"([^\"]*)\"$")
	public void i_add_the_location_Id_with_face_sku_site_id(String facetype,String siteId) throws Throwable {
		context.setFaceType(facetype);
		//context.setSkuId(skuId);
		context.setSiteId(siteId);
		String skuId=pickFaceTableDB.getSku();
		
		
		jdaFooter.clickAddButton();
		
		pickFaceMaintenancPage.selectFaceType(facetype);
		pickFaceMaintenancPage.selectOwnerId();
		pickFaceMaintenancPage.EnterClientID();
		pickFaceMaintenancPage.enterLocationInLocationMaint(context.getlocationID());
		// String fromLocationString =
		// locationDB.getCheckString(context.getlocationID());
		// purchaseOrderPutawayPage.enterCheckString(fromLocationString);
		pickFaceMaintenancPage.enterSkuId(skuId);
		pickFaceMaintenancPage.selectSiteId(siteId);
		
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
		warningPopUpPage.clickYes();
		jdaFooter.clickExecuteButton();
//	/	pickFaceMaintenancPage.getSkuId();
//		pickFaceTableDB.deleteRecord(context.getSkuId());
		
	}

	@When("^I select ownerID and clientID$")
	public void i_select_ownerID_and_clientID() throws FindFailed, InterruptedException{
		pickFaceMaintenancPage.selectOwnerId();
		pickFaceMaintenancPage.EnterClientID();
	}
//	@When("^I add the location Id with face \"([^\"]*)\", sku \"([^\"]*)\", site id \"([^\"]*)\"$")
//	public void i_add_the_location_Id_with_face_sku_site_id(String facetype, String skuId,
//			String siteId) throws Throwable {
//		context.setFaceType(facetype);
//		context.setSkuId(skuId);
//		context.setSiteId(siteId);
//
//		jdaFooter.clickAddButton();
//		pickFaceMaintenancPage.selectFaceType(facetype);
//
//		
//	//	pickFaceMaintenancPage.enterLocationList(context.getLocationList());
//		// String fromLocationString =
//		// locationDB.getCheckString(context.getlocationID());
//		// purchaseOrderPutawayPage.enterCheckString(fromLocationString);
//
//		pickFaceMaintenancPage.enterLocationInLocationMaint(context.getlocationID());
//		//String fromLocationString = locationDB.getCheckString(context.getlocationID());
//		//purchaseOrderPutawayPage.enterCheckString(fromLocationString);
//
//		pickFaceMaintenancPage.enterSkuId(skuId);
//		pickFaceMaintenancPage.selectSiteId(siteId);
//		jdaFooter.clickExecuteButton();
//		warningPopUpPage.clickYes();
//		warningPopUpPage.clickYes();
//		jdaFooter.clickExecuteButton();
//	}
//

		@Given("^I add another primary pick face with location Id sku,site id \"([^\"]*)\",face type \"([^\"]*)\" and primary pick face$")
		public void i_add_another_primary_pick_face_with_location_Id_sku_site_id_face_type_and_primary_pick_face(String siteId, String facetype) throws Throwable {
		String notFixedlocation=locationDB.getNotFixedLocation();
		context.setFaceType(facetype);
		//context.setSkuId(skuId);
		context.setSiteId(siteId);
		jdaFooter.clickAddButton();
		
		
		
		
		
		
		pickFaceMaintenancPage.selectFaceType(facetype);
		Thread.sleep(1000);
		i_select_ownerID_and_clientID();
		pickFaceMaintenancPage.enterLocationInLocation12(notFixedlocation);
		
		pickFaceMaintenancPage.selectPrimaryPickFace();
		pickFaceMaintenancPage.enterSkuId(context.getSkuId());
		pickFaceMaintenancPage.selectSiteId(siteId);
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
	}
	
	@When("^I add the location Id with face type \"([^\"]*)\", sku, site id \"([^\"]*)\" and primary pick face$")
	public void i_add_the_location_Id_with_face_type_sku_site_id_and_primary_pick_face(String facetype,
			String siteId) throws Throwable {

		context.setFaceType(facetype);
		//context.setSkuId(skuId);
		context.setSiteId(siteId);
		jdaFooter.clickAddButton();
		String skuId=pickFaceTableDB.getSkuWithoutPickFace();
		context.setSkuId(skuId);
		pickFaceMaintenancPage.selectFaceType(facetype);
		i_select_ownerID_and_clientID();
//		pickFaceMaintenancPage.selectOwnerId();
//		pickFaceMaintenancPage.EnterClientID();
		pickFaceMaintenancPage.enterLocationInLocationMaint(context.getlocationID());
		pickFaceMaintenancPage.selectPrimaryPickFace();
		pickFaceMaintenancPage.enterSkuId(skuId);
		pickFaceMaintenancPage.selectSiteId(siteId);
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
		warningPopUpPage.clickYes();
		jdaFooter.clickExecuteButton();
	}

	@When("^I add the location Id with face \"([^\"]*)\", sku, site id \"([^\"]*)\",and trigger qty$")
	public void i_add_the_location_Id_with_face_sku_site_id_and_trigger_qty(String facetype,
			String siteId) throws Throwable {
		context.setFaceType(facetype);
		context.setSiteId(siteId);
	    String skuId=pickFaceTableDB.getSku();
	    context.setSkuId(skuId);
		jdaFooter.clickAddButton();
		pickFaceMaintenancPage.selectFaceType(facetype);
//		pickFaceMaintenancPage.selectOwnerId();
//		pickFaceMaintenancPage.EnterClientID();
		i_select_ownerID_and_clientID();
		locationMaintenancePage.clickGeneral();
		pickFaceMaintenancPage.enterLocationInPickFace(context.getlocationID());

		pickFaceMaintenancPage.selectSiteId(siteId);
		locationMaintenancePage.clickGeneral();
		pickFaceMaintenancPage.enterSkuId(skuId);
		pickFaceMaintenancPage.navigateToQuantitiesTab();
		Thread.sleep(2000);
		pickFaceMaintenancPage.checkReplenishWithoutOrder();
		System.out.println("trigger qty" + context.getTriggerQty());
		
		String triggerqty=inventoryDB.getCaseRatioFromSku(skuId);
		pickFaceMaintenancPage.enterTriggerQty(triggerqty);
		pickFaceMaintenancPage.navigateTouserDefinedTab();
		pickFaceMaintenancPage.enterCaseRatio(triggerqty);
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
		Thread.sleep(1000);
		pickFaceMaintenancPage.clickOk();
		
	}

	@Then("^I validate the error message for two pick face records in same location$")
	public void I_validate_the_error_message_for_two_pick_face_records_in_same_location() throws Throwable {
		Assert.assertTrue("Two pick face records in same location error message is not displayed as expected.",
				pickFaceMaintenancPage.isTwoPickFaceRecordsErrorDisplayed());
		Thread.sleep(1000);
		pickFaceMaintenancPage.clickOk();
	}

	@Then("^the error message should be displayed as A primary pick face already exists for this location$")
	public void the_error_message_should_be_displayed_as_A_primary_pick_face_already_exists_for_this_location()
			throws Throwable {
		Assert.assertTrue("Error message is not displayed as expected",
				pickFaceMaintenancPage.isPrimaryPickFaceErrorDisplayed());
	}

	@Then("^the location id should be added$")
	public void the_location_id_should_be_added() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		/*
		 * String faceType = pickFaceMaintenancPage.getFaceType(); if
		 * (!context.getFaceType().equals(faceType)) { failureList.add(
		 * "FaceType is not as expected. Expected [" + context.getFaceType() +
		 * "]  but was [" + faceType + "]"); }
		 * 
		 * String skuId = pickFaceMaintenancPage.getSkuId(); if
		 * (!context.getSkuId().equals(skuId)) { failureList.add(
		 * "SkuId is not as expected. Expected [" + context.getSkuId() +
		 * "] but was [" + skuId + "]"); }
		 * 
		 * String loaction = pickFaceMaintenancPage.getLocation(); if
		 * (!context.getLocation().equals(loaction)) { failureList.add(
		 * "Location is not as expected. Expected [" + context.getLocation() +
		 * "] but was [" + loaction + "]"); }
		 * 
		 * String siteId = pickFaceMaintenancPage.getSiteId(); if
		 * (!context.getSiteId().equals(siteId)) { failureList .add(
		 * "SiteId is not as expected. Expected [" + context.getSiteId() +
		 * "] but was [" + siteId + "]"); }
		 * 
		 * Assert.assertTrue("Pick face details are not as expected." +
		 * Arrays.asList(failureList.toString()), failureList.isEmpty());
		 */
	}

	@Then("^I search location Id$")
	public void i_search_location_Id() throws Throwable {
		jdaHomePage.navigateToLocationMaintanence();
		locationMaintenancePage.clickGeneral();
		jdaFooter.clickQueryButton();
		locationMaintenancePage.enterLocation(context.getlocationID());
		jdaFooter.clickExecuteButton();
	}

	@Then("^the pick face should be updated as \"([^\"]*)\"$")
	public void the_pick_face_should_be_updated_as(String fixed) throws Throwable {
		Assert.assertEquals("Pick Face is not displayed as expected","F",
				locationDB.getPickFace(context.getlocationID()));
//		pickFaceTableDB.deleteRecord(context.getlocationID());
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

	@Given("^the location id \"([^\"]*)\" is not fixed for other SKU$")
	public void the_location_id_is_not_fixed_for_other_SKU(String location) throws Throwable {
		jdaHomePage.navigateToLocationMaintanence();
		context.setLocation(location);
		locationMaintenancePage.clickGeneral();
		jdaFooter.clickQueryButton();
		pickFaceMaintenancPage.enterLocationInLocationMaint(location);
		jdaFooter.clickExecuteButton();
		System.out.println("pick face type value" + locationMaintenancePage.getPickFace());
		if (null != locationMaintenancePage.getPickFace()) {
			System.out.println("pick face type is null");

		} else {
			context.setErrorMessage("Pick face is not displayed as expected");
			System.out.println(context.getErrorMessage());
			Assert.fail(context.getErrorMessage());

		}
		// Assert.assertNull("Pick Face is not displayed as expected",
		// locationMaintenancePage.getPickFace());

	}

	@Given("^the location id is not fixed for other SKU$")
	public void the_location_id_is_not_fixed_for_other_SKU() throws Throwable {
		String notFixedlocation=locationDB.getNotFixedLocation();
		// jdaHomePage.navigateToLocationMaintanence();
		// context.setLocation(location);
		// locationMaintenancePage.clickGeneral();
		// jdaFooter.clickQueryButton();
		// pickFaceMaintenancPage.enterLocationInLocationMaint(location);
		// jdaFooter.clickExecuteButton();
		// System.out.println("pick face type value"+locationMaintenancePage.getPickFace());
		context.setlocationID(notFixedlocation);
		   
		
	}
	@Given("^the location id is fixed for other SKU$")
	public void the_location_id_is__fixed_for_other_SKU() throws Throwable {
		String fixedlocation=locationDB.getFixedLocation();
		// jdaHomePage.navigateToLocationMaintanence();
		// context.setLocation(location);
		// locationMaintenancePage.clickGeneral();
		// jdaFooter.clickQueryButton();
		// pickFaceMaintenancPage.enterLocationInLocationMaint(location);
		// jdaFooter.clickExecuteButton();
		// System.out.println("pick face type value"+locationMaintenancePage.getPickFace());
		context.setlocationID(fixedlocation);
		System.out.println(fixedlocation);
		   
		
	}

	@Given("^the location id \"([^\"]*)\" is fixed for other SKU$")
	public void the_location_id_is_fixed_for_other_SKU(String location) throws Throwable {
		jdaHomePage.navigateToLocationMaintanence();
		context.setLocation(location);
		locationMaintenancePage.clickGeneral();
		jdaFooter.clickQueryButton();
		pickFaceMaintenancPage.enterLocationInLocationMaint(location);
		jdaFooter.clickExecuteButton();
		System.out.println("pick face" + locationMaintenancePage.getPickFace());
		System.out.println("pick face type value" + locationMaintenancePage.getPickFace());
		if (locationMaintenancePage.getPickFace().equals("Fixed")) {
			System.out.println("pick face type is fixed");
		} else {
			context.setErrorMessage("Pick face is not displayed as expected");
			System.out.println(context.getErrorMessage());
			Assert.fail(context.getErrorMessage());
		}
	}
//	@Given("^the location id is not fixed for other SKU$")
//	public void the_location_id_is_not_fixed_for_other_SKU() throws Throwable {
//		String notFixedlocation=locationDB.getNotFixedLocation();
////		jdaHomePage.navigateToLocationMaintanence();
////		context.setLocation(location);
////		locationMaintenancePage.clickGeneral();
////		jdaFooter.clickQueryButton();
////		pickFaceMaintenancPage.enterLocationInLocationMaint(location);
////		jdaFooter.clickExecuteButton();
////		System.out.println("pick face type value"+locationMaintenancePage.getPickFace());
//		
//	context.setlocationID(notFixedlocation);
//		
//	   
//	}
//	
//	
//	@Given("^the location id is fixed for other SKU$")
//	public void the_location_id_is_fixed_for_other_SKU() throws Throwable {
////		jdaHomePage.navigateToLocationMaintanence();
////		context.setLocation(location);
////		locationMaintenancePage.clickGeneral();
////		jdaFooter.clickQueryButton();
////		pickFaceMaintenancPage.enterLocationInLocationMaint(location);
////		jdaFooter.clickExecuteButton();
////		System.out.println("pick face"+locationMaintenancePage.getPickFace());
////		System.out.println("pick face type value"+locationMaintenancePage.getPickFace());
////		if(locationMaintenancePage.getPickFace().equals("Fixed"))
////		{
////		System.out.println("pick face type is fixed");
////		}
////		else
////		{
////		context.setErrorMessage("Pick face is not displayed as expected");
////		System.out.println(context.getErrorMessage());
////		Assert.fail(context.getErrorMessage());
////		}
//
//		String fixedlocation=locationDB.getFixedLocation();
//		context.setlocationID(fixedlocation);
//	   
//	}
		@When("^I add the location Id with face \"([^\"]*)\", sku \"([^\"]*)\", site id \"([^\"]*)\",and trigger qty$")
		public void i_add_the_location_Id_with_face_sku_site_id_and_trigger_qty(String facetype,String sku,String siteId) throws Throwable {
			systemAllocationStepDefs.the_location_should_be_changed_to_for_the_sku("UnLocked","21036245");
			context.setSkuId(sku);
//			pickFaceTableDB.deleteRecord(context.getSkuId());/---commented for checking--/
			Thread.sleep(3000);
			jdaFooter.clickAddButton();
			context.setFaceType(facetype);		
			context.setSiteId(siteId);
			
			the_location_id_is_not_fixed_for_other_SKU();
			//pickFaceMaintenancPage.enterLocationInPickFace(context.getlocationID());		
			Thread.sleep(2000);
			
			pickFaceMaintenancPage.selectFaceType(facetype);
			//locationMaintenancePage.clickGeneral();
			pickFaceMaintenancPage.selectSiteId(siteId);
			
			locationMaintenancePage.clickGeneral();
			
		pickFaceMaintenancPage.enterLocationInPickFace(context.getlocationID());
		
		System.out.println("sku");
		pickFaceMaintenancPage.enterSkuId(context.getSkuId());
		
			pickFaceMaintenancPage.navigateToQuantitiesTab();
			Thread.sleep(2000);
			pickFaceMaintenancPage.checkReplenishWithoutOrder();
			System.out.println("trigger qty" + context.getTriggerQty());
			//String tagId=inventoryDB.getTagId()
			String triggerqty=inventoryDB.getCaseRatioFromSku(sku);
			pickFaceMaintenancPage.enterTriggerQty(triggerqty);
			pickFaceMaintenancPage.navigateTouserDefinedTab();
			pickFaceMaintenancPage.enterCaseRatio(triggerqty);
			jdaFooter.clickExecuteButton();
			warningPopUpPage.clickYes();
			Thread.sleep(2000);
			if(screen.exists("images/PickFaceMaintenance/Error.png") != null){
				warningPopUpPage.clickOk();
			}
					
			warningPopUpPage.clickYes();
			
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        String PickFaceTime=sdf.format(cal.getTime());
	        context.setTime(PickFaceTime);
	        System.out.println("time="+PickFaceTime);		
		}

	}


