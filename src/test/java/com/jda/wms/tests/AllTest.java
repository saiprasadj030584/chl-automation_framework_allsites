package com.jda.wms.tests;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@goh_outbound_retail_order_till_despatch_order_multiple_pallet_in_the_single_trailer_when_unloading"})

public class AllTest {
}	

