package com.jda.wms.stepdefs.foods;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JdaLoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;

public class JDALoginStepDefs {
	public Scenario myScenario;
	private WebDriver webDriver;
	private final JdaLoginPage jdaHomePage;

	@Inject
	public JDALoginStepDefs(WebDriver webDriver, JdaLoginPage jdaHomePage) {
		this.webDriver = webDriver;
		this.jdaHomePage = jdaHomePage;
	}

	@Given("^user is in JDA Dispatcher Food application$")
	public void user_is_in_JDA_Dispatcher_Food_application() throws Throwable {
		jdaHomePage.login();
	}
}