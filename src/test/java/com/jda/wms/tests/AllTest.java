package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {



		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@unique_boxed_picking_picking_validate_whether_retail_order_is_picked_from_preferred_aisle_and_non_retail_order_from_normal_storage_aisle"
				+ ""})


public class AllTest {
}
