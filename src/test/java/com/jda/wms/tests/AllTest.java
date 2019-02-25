package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""
			+ "@TC07_Validate_the_auto_picking_process_for_the_Cross_dock_FSV_order4					P1495257	Y"
			+ ""	
		     + ""
			+ "" 

})
public class AllTest {
}




















