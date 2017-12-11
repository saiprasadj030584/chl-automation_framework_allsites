package com.jda.wms.tests;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@unique_boxed_returns_returns_rms_validate_the_returns_rms_normal_urn_single_line_item"

				+ ""})


public class AllTest {

	
}




