package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class PuttyFunctionsStepDefs {
	private PuttyFunctionsPage puttyFunctionsPage;
	private Configuration configuration;
	
	@Inject
	public PuttyFunctionsStepDefs(PuttyFunctionsPage puttyFunctionsPage,
			Configuration configuration) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.configuration = configuration;
	}

	@Given("^I have logged in as warehouse user in Putty$")
	public void i_have_logged_in_as_warehouse_user_in_Putty()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		puttyFunctionsPage.invokePutty();
		
		String host= configuration.getStringProperty("putty-foods-host");
		String port= configuration.getStringProperty("putty-foods-port");
		puttyFunctionsPage.loginPutty(host, port);
		Assert.assertTrue("Login page not displayed as expected", puttyFunctionsPage.isLoginScreenDisplayed());

		puttyFunctionsPage.enterJdaLogin(configuration.getStringProperty("username"),
				configuration.getStringProperty("password"));
		Thread.sleep(4000);

		if (!(puttyFunctionsPage.isMainMenuDisplayed())) {
			failureList.add("Main Menu not displayed as expected");
		}

		Assert.assertTrue("Putty Login not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@When("^I login as warehouse user in Putty$")
	public void i_login_as_warehouse_user_in_Putty() throws Throwable {
		i_have_logged_in_as_warehouse_user_in_Putty();
	}
	
}
