
package com.jda.wms.tests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)


@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { "@boxed_allocation_international_validate_whether_all_the_stocks_are_allocated_allocation_rules"})


public class AllTest {
}




