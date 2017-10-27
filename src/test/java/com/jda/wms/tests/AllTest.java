
package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty",
		"json:target/cucumber-reports/cucumber.json" }, tags = {"@hanging_inbound_receiving_direct_po_over_receiving" })
public class AllTest {
}

