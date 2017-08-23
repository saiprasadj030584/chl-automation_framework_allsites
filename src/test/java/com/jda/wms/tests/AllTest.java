	
package com.jda.wms.tests;


import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)



@CucumberOptions(monochrome = true, features = "src/test"
		+ "/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { "@boxed_pre_receiving_fsv_po_validate_complinace_flag_uploaded"
				+ ""
				+ ""
				+ ""
				+ ""
				 })	

public class AllTest {
}














