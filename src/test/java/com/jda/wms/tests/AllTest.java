
package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
<<<<<<< HEAD
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { "@group_1" })
=======
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { "@inventory_update" })
>>>>>>> c1fbb8a43c9f1609121997ca2ba813b4bea35177
public class AllTest {
}
