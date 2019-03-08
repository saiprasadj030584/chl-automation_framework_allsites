package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""
			+ ""
			+ "@TC018_Reversion_of_stock_from_a_trailer_Wanted_stock"
			+ ""	
		     + ""
			+ "" 

})
public class AllTest {
}




















