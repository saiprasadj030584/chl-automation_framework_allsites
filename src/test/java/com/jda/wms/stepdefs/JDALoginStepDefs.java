package com.jda.wms.stepdefs;

import com.google.inject.Inject;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import com.jda.wms.UI.pages.JdaLoginPage;
import com.jda.wms.context.Context;
public class JDALoginStepDefs {
	private final JdaLoginPage jdaLoginPage;
    private Context context;
	@Inject
	public JDALoginStepDefs(JdaLoginPage jdaLoginPage,Context context) {
		this.jdaLoginPage = jdaLoginPage;
		this.context=context;
	}

	@Given("^I have logged in as warehouse user in JDA Exit application$")	
	public void i_have_logged_in_as_warehouse_user_in_JDA_Exit_application() throws Throwable {		
		jdaLoginPage.login();
}
	@Given("^Login to JDA Dispatcher web screen$")
	public void login_to_JDA_Dispatcher_web_screen() throws Throwable {
		Thread.sleep(5000);
		jdaLoginPage.login();
		
		
	}
	@And("^Refresh application$")
	public void refresh_to_JDA_Dispatcher_web_screen() throws Throwable {
		Thread.sleep(1000);
		jdaLoginPage.refreshPage();
	}

	@Given("^Login to JDA Dispatcher web screen as Basic User \"([^\"]*)\"$")
	public void login_to_JDA_Dispatcher_web_screen_as_BAsic_user(String user) throws Throwable {
		context.setBasicUser(user);
		jdaLoginPage.loginAsBasicUser();
	}
}