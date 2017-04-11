package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JdaLoginPage;

import cucumber.api.java.en.Given;

public class JDALoginStepDefs {
	private final JdaLoginPage JdaLoginPage;

	@Inject
	public JDALoginStepDefs(JdaLoginPage jdaLoginPage) {
		this.JdaLoginPage = jdaLoginPage;
	}

	@Given("^I have logged in as store agent in JDA dispatcher food application$")
	public void i_have_logged_in_as_store_agent_in_JDA_dispatcher_food_application() throws Throwable {
		JdaLoginPage.login();
	}
}