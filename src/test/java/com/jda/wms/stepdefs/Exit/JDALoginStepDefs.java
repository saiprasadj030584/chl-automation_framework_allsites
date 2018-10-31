package com.jda.wms.stepdefs.Exit;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.JdaLoginPage;

import cucumber.api.java.en.Given;

public class JDALoginStepDefs {
	private final JdaLoginPage jdaLoginPage;

	@Inject
	public JDALoginStepDefs(JdaLoginPage jdaLoginPage) {
		this.jdaLoginPage = jdaLoginPage;
	}

	@Given("^I have logged in as warehouse user in JDA Exit application$")	
	public void i_have_logged_in_as_warehouse_user_in_JDA_Exit_application() throws Throwable {		
		jdaLoginPage.login();
}
	@Given("^Login to JDA Dispatcher web screen$")
	public void login_to_JDA_Dispatcher_web_screen() throws Throwable {
		jdaLoginPage.login();
	}
}