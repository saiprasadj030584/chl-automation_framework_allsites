package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { "@flatpack_stock_adjustment_stock_adjustment_reason_code_do_stock_adjustment_by_selecting_reason_code_as_incomplete_"})

public class AllTest {
}	



