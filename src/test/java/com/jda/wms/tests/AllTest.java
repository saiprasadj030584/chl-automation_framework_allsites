package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""
			+ "@TC017_Negative_Path_Scan_incorrect_To_Location_during_Manual_order_Picking" 

})
public class AllTest {
}





















