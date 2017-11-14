package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@goh_picking_retail_validate_whether_clustering_is_done_manually"})

public class AllTest {
}	



