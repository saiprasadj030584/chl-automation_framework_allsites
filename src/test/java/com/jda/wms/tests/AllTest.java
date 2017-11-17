package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@hanging_outbound_order_till_despatch_store_order_store_order_with_pick_discrepancy_e_g_order_for_10_pick_and_despatch_8"})

public class AllTest {
}	


