package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		
		"pretty", "json:target/cucumber-reports/"
				+ "cucumber.json" }, tags = { "@unique_hanging_outbound_order_till_despatch_retail_type_order_split_shipment_and_multiple_vehicle_single_order"})
	
public class AllTest {
}


