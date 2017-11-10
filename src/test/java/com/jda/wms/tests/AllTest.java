
package com.jda.wms.tests;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


 
 
 
@RunWith(Cucumber.class)



@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		
		"pretty", "json:target/cucumber-reports/"
				+ "cucumber.json" }, tags = { "@hanging_inbound_receiving_receipt_reversal_perform_a_receipt_correction_stock_adjustment_function_after_putaway_of_receipt"})


public class AllTest {
}


