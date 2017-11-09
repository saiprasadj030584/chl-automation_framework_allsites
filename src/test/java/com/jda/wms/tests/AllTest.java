package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@hanging_pre_receiving_master_pack_config_validate_whether_pack_config_can_be_created_for_sku_s_&_amend_existing_pack_config"})

public class AllTest {
}	



