
package com.jda.wms.tests;


import org.junit.runner.RunWith;

import com.jda.wms.stepdefs.rdt.PurchaseOrderPutawayStepDefs;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


 
 
 
@RunWith(Cucumber.class)



@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		
		"pretty", "json:target/cucumber-reports/"
				+ "cucumber.json" }, tags = { "@boxed_inbound_receiving_direct_po_multiple_trailer_multiple_urn_single_po"})


public class AllTest {
}


