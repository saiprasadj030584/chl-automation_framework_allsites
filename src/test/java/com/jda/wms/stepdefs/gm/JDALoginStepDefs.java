package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.pages.gm.JdaLoginPage;

import cucumber.api.java.en.Given;

public class JDALoginStepDefs {
	private final JdaLoginPage jdaLoginPage;

	@Inject
	public JDALoginStepDefs(JdaLoginPage jdaLoginPage) {
		this.jdaLoginPage = jdaLoginPage;
	}

	@Given("^I have logged in as warehouse user in JDA dispatcher food application$")
	public void i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application() throws Throwable {
		//jdaLoginPage.login();
	}

	@Given("^I have logged in as warehouse user in JDA dispatcher GM application$")
	public void i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_GM_application() throws Throwable {
		jdaLoginPage.login();
	}
}