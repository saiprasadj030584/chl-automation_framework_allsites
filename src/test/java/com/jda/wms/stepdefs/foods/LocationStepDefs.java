package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.LocationPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LocationStepDefs {
	private final InventoryQueryPage inventoryQueryPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final LocationPage locationPage;
	private final Context context;

	@Inject
	public LocationStepDefs(InventoryQueryPage inventoryQueryPage, JDAFooter jdaFooter, LocationPage locationPage,
			Context context, JdaHomePage jdaHomePage) {
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.locationPage = locationPage;
		this.context = context;
	}

	@When("^I navigate to location page$")
	public void i_navigate_to_location_page() throws Throwable {
		jdaHomePage.navigateToLocationPage();
	}

	@Then("^I should see the location zone in location page$")
	public void i_should_see_the_location_zone_in_location_page() throws Throwable {
		jdaFooter.clickQueryButton();
		locationPage.enterLocation(context.getLocation());
		jdaFooter.clickExecuteButton();
		String locationZone = locationPage.getLocationZone();
		context.setLocationZone(locationZone);
		if (!locationZone.equals(null)) {

		}
	}

}
