package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = { ""





				+ "@TC24_FSV_Receiving_Over_receipt_PO_stock_in_more_than_one_URN_Boxed_Black_Stock_Process" 



})
public class AllTest {
}





















