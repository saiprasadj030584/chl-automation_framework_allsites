package com.jda.wms.tests;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {
				"@unique_boxed_order_management_retail_order_verify_order_status_updated_for_each_transaction_in_order_management_screen_and_order_header " })

public class AllTest {

}
