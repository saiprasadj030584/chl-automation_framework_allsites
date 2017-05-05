package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

import cucumber.api.java.en.Given;
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

	@Given("^I have logged in as warehouse user in Putty with host \"([^\"]*)\" and port \"([^\"]*)\"$")
	public void i_have_logged_in_as_warehouse_user_in_Putty_with_host_and_port(String host, String port)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		puttyFunctionsPage.invokePutty();
		puttyFunctionsPage.loginPutty(host, port);
		Assert.assertTrue("Login page not displayed as expected", puttyFunctionsPage.isLoginScreenDisplayed());

		puttyFunctionsPage.enterJdaLogin(configuration.getStringProperty("username"),
				configuration.getStringProperty("password"));
		Thread.sleep(2000);

		if (!(puttyFunctionsPage.isMainMenuDisplayed())) {
			failureList.add("Main Menu not displayed as expected");
		}

		Assert.assertTrue("Putty Login not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
}
