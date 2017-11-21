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
	private String host = null;
	private String port = null;

	@Inject
	public PuttyFunctionsStepDefs(PuttyFunctionsPage puttyFunctionsPage, Configuration configuration, Context context) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.configuration = configuration;
		this.context = context;
	}

	@Given("^I have logged in as warehouse user in putty$")
	public void i_have_logged_in_as_warehouse_user_in_putty() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		puttyFunctionsPage.invokePutty();
		//String port=null;
		if (context.getSiteId().equals("5649")) {
			System.out.println(port);
			if(context.isVehicleLoadRequired()){
			port = configuration.getStringProperty("wst-putty-gm-port-vehicle-load");
			}
			else{
				System.out.println("inside port if condition");
			port = configuration.getStringProperty("wst-putty-gm-port");
			System.out.println(port);
			}
			host = configuration.getStringProperty("wst-putty-gm-host");
		}

		else if (context.getSiteId().equals("5885")) {
			host = configuration.getStringProperty("stk-putty-gm-host");
			port = configuration.getStringProperty("stk-putty-gm-port");
		}
		else {
			System.out.println("Site Id is not found");
			Assert.fail("Site Id is not found");
		}
		puttyFunctionsPage.loginPutty(host, port);
		Thread.sleep(2000);

		if (puttyFunctionsPage.isLoginFailureExists()) {
			puttyFunctionsPage.pressEnter();
		}
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