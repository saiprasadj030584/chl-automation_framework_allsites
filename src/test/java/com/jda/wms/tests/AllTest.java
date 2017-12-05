package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {
				"@unique_boxed_allocation_idt_validate_whether_stocks_are_automatically_allocated_to_orders_auto_allocation"
				+ " " })

public class AllTest {
	
	
}


