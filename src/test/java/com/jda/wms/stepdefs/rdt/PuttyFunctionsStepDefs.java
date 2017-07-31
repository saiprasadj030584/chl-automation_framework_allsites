package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class PuttyFunctionsStepDefs {
	private PuttyFunctionsPage puttyFunctionsPage;
	private Configuration configuration;
	private Context context;

	@Inject
	public PuttyFunctionsStepDefs(PuttyFunctionsPage puttyFunctionsPage, Configuration configuration,Context context) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.configuration = configuration;
		this.context = context;
	}

	@Given("^I have logged in as warehouse user in putty$")
	public void i_have_logged_in_as_warehouse_user_in_putty() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		puttyFunctionsPage.invokePutty();

		String host = configuration.getStringProperty("putty-gm-host");
		String port = configuration.getStringProperty("putty-gm-port");
		puttyFunctionsPage.loginPutty(host, port);
		Thread.sleep(2000);
		Assert.assertTrue("Putty Login page not displayed as expected", puttyFunctionsPage.isLoginScreenDisplayed());
		puttyFunctionsPage.enterJdaLogin(configuration.getStringProperty("username"),
				configuration.getStringProperty("password"));
		Thread.sleep(3000);

		if (!(puttyFunctionsPage.isMainMenuDisplayed())) {
			failureList.add("Main Menu not displayed as expected");
		}

		Assert.assertTrue("Putty Login not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
		
		context.setPuttyLoginFlag(true);
	}
	
	@When("^I select user directed option in main menu$")
	public void i_select_user_directed_option_in_main_menu() throws Throwable {
		puttyFunctionsPage.selectUserDirectedMenu();
		Assert.assertTrue("User Menu not displayed as expected", puttyFunctionsPage.isUserMenuDisplayed());
	}

	@When("^I login as warehouse user in putty$")
	public void i_login_as_warehouse_user_in_putty() throws Throwable {
		i_have_logged_in_as_warehouse_user_in_putty();
	}
}