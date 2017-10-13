
package com.jda.wms.tests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)


@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""
				+ "@boxed_stock_adjustment_returns_verify_reason_code_available_for_store_has_sent_greater_quantity_than_the_expected_volume_for_a_product_within_the_urrn_and_has_a_movement_label_over_receipt"})


public class AllTest {
}




