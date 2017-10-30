
package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty",
		"json:target/cucumber-reports/cucumber.json" }, tags = {"@boxed_inventory_inventory_validate_the_stock_available_in_any_location_check_empty_and_low_volume_slots" })
public class AllTest {
}

