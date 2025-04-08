package com.mns.auto.cd.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)


@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = { "com.mns.auto" }, plugin = {
		"pretty", "json:target/cucumber-reports/cucumber.json" }, tags = {"@Singleshoe_LeftUPCReceiving" })

public class AllTest {

}


//mvn -q -U  clean install -Dmaven.repo.local=C:\\m2_local_repository\\.m2\\repository -DCucumber.options="--tags @GB_Ollerton_002",@GB_Ollerton_001"