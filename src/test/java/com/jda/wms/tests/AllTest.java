package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""



<<<<<<< HEAD

				+ "@TC23_FSV_Receiving_Unknown_Stock_Boxed_Article_Black_Stock_Process" 






=======
				+ "@TC06_Validate_Compliance_check_commodity_code_is_null_or_invalid" 






>>>>>>> refs/remotes/origin/Exit_TCs
				+ "@TC06_Validate_Compliance_check_commodity_code_is_null_or_invalid" 

<<<<<<< HEAD


=======


>>>>>>> refs/remotes/origin/Exit_TCs
})
public class AllTest {
}





















