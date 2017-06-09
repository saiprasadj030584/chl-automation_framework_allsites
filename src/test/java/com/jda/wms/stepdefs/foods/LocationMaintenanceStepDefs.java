package com.jda.wms.stepdefs.foods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.LocationDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.LocationMaintenancePage;
import com.jda.wms.pages.foods.WarningPopUpPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LocationMaintenanceStepDefs {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final LocationMaintenancePage locationMaintenancePage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final WarningPopUpPage warningPopUpPage;
	private final LocationDB locationDB;
	private Context context;
	Map<String, Map<String, String>> purchaseOrderMap;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public LocationMaintenanceStepDefs(WarningPopUpPage warningPopUpPage,
			LocationMaintenancePage locationMaintenancePage, Context context, JDAFooter jdaFooter,
			JdaHomePage jdaHomePage,LocationDB locationDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.locationMaintenancePage = locationMaintenancePage;
		this.context = context;
		this.warningPopUpPage = warningPopUpPage;
		this.locationDB = locationDB;
	}

	@Given("^I navigate to location maintenance page$")
	public void i_navigate_to_location_maintenance_page() throws Throwable {
		jdaHomePage.navigateToLocationMaintanence();
		Thread.sleep(3000);
	}

	@Given("^I search with location ID \"([^\"]*)\"$")
	public void i_search_with_location_ID(String location) throws Throwable {
		jdaFooter.clickQueryButton();
		locationMaintenancePage.enterLocation(location);
		jdaFooter.clickExecuteButton();
		context.setlocationID(location);
	}

	@Then("^the location record should be displayed$")
	public void the_location_record_should_be_displayed() throws Throwable {
		Assert.assertTrue("Location details are not as expected.", locationMaintenancePage.isRecordfound());
		logger.debug(" Location :  " + context.getlocationID());
	}
	
	@Then("^the location record should not be displayed$")
	public void the_location_record_should_not_be_displayed() throws Throwable {
		Assert.assertTrue("Location details are not as expected.", (!locationMaintenancePage.isRecordfound()));
		logger.debug(" Location :  " + context.getlocationID());
	}

	@When("^I update the location lock status as \"([^\"]*)\"$")
	public void i_update_the_location_lock_status_as(String lockstatus) throws Throwable {
		context.setlocationLockStatus(lockstatus);
		jdaFooter.clickUpdateButton();
		locationMaintenancePage.selectLockStatus(lockstatus);
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
		warningPopUpPage.clickYesButtonOnSecondPopup();
	}

	@Then("^the updated lock status should be displayed$")
	public void the_updated_lock_status_should_be_displayed() throws Throwable {
		String currentLockStatus = locationMaintenancePage.getLocationLockStatus();
		logger.debug("Expected Lock Status: " + context.getlocationLockStatus());
		logger.debug("Actual Lock Status: " + currentLockStatus);

		Assert.assertEquals("REC Lane location lock status not changed as expected.", context.getlocationLockStatus(),
				currentLockStatus);
	}
	
	@Given("^I get the reserve location to putaway the stock$")
	public void I_get_the_reserve_location_to_putaway_the_stock() throws Throwable {
		Map<String, String> putawayLocationMap = new HashMap<String, String>();
		String location = null;

		List<String> locationList = locationDB.getLocation();
		purchaseOrderMap = context.getPurchaseOrderMap();

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			location = locationList.get(new Random().nextInt(locationList.size()));

			if (putawayLocationMap.containsValue(location)) {
				location = locationList.get(new Random().nextInt(locationList.size()));
			}
			putawayLocationMap.put(skuID, location);
		}
		context.setPutawayLocationMap(putawayLocationMap);
	}
}
