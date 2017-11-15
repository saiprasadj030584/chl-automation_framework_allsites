package com.jda.wms.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.jda.wms" }, plugin = {

		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@hanging_pre_receiving_idt_validate_whether_booking_can_be_moved_to_different_time_on_the_same_day"})

public class AllTest {
}	


