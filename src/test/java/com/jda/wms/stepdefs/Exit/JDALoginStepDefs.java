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
	@Given("^I Launch and login the JDA application as Exit DC User$")
	public void i_launch_Jda_application() throws Throwable{
		jdaLoginPage.login();
		
	}
 
 
//	@Given("^Logging in as warehouse user in Exit application$")
//	public void Logging_in_as_warehouse_user_in_Exit_application() throws Throwable {
//		jdaLoginPage.login();
//	}
}