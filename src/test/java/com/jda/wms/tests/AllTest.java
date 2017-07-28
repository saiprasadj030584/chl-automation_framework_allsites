package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty",
		"json:target/cucumber-reports/cucumber.json" }, tags = { "@returns_receiving_footwear_digit_validation" })

public class AllTest {

}
