package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""


				
				
				+ ""
				+ ""
				+ "@TC04_Validate_Pick_list_id_generated_for_a_cross_dock_ASN_order"
				+ ""
				+ ""

				
				
				+ ""

				+ ""

				
				+ ""

			


})
public class AllTest {
}





















