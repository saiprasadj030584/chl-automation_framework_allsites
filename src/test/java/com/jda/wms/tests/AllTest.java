package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@boxed_picking_picking_validate_whether_only_one_upc_is_picked_in_tote_for_the_following_order_conventry_tesam_external_reprocessing_international_franchises"
				+ ""})

public class AllTest {
}	


