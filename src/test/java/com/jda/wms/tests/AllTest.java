package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {


		
		
		"pretty", "json:target/cucumber-reports/"
				+ "cucumber.json" }, tags = { "@goh_allocation_retail_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_disallowed"})


public class AllTest {
}

