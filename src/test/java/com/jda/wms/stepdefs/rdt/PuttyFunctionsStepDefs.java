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
	public static String statusRegion = System.getProperty("USE_DB");
 public static String region = System.getProperty("REGION");
//	public static String region = "ST";

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
		System.out.println("PUTTY LAUNCH");
		if (statusRegion == null) {
			statusRegion = "N";
		} else {
			System.out.println("DATABASE Status region---> " + statusRegion);
		}
		if (statusRegion.equalsIgnoreCase("N")) {
			if (region.equalsIgnoreCase("SIT")) {
				if (context.getSiteID().equals("5649")) {
					System.out.println(port);
					if (context.isVehicleLoadRequired()) {
						port = configuration.getStringProperty("sit-wst-putty-gm-port-vehicle-load");
					} else {
						System.out.println("inside port if condition");
						port = configuration.getStringProperty("sit-wst-putty-gm-port");
						System.out.println(port);
					}
					host = configuration.getStringProperty("sit-wst-putty-gm-host");
				} else if (context.getSiteID().equals("5885")) {
					host = configuration.getStringProperty("stk-putty-gm-host");
					port = configuration.getStringProperty("stk-putty-gm-port");
				} else {
					System.out.println("Site Id is not found");
					Assert.fail("Site Id is not found");
				}
			}

			else if (region.equalsIgnoreCase("ST")) {
				if (context.getSiteID().equals("5649")) {
					System.out.println(port);
					if (context.isVehicleLoadRequired()) {
						port = configuration.getStringProperty("st-wst-putty-gm-port-vehicle-load");
					} else {
						System.out.println("inside port if condition");
						port = configuration.getStringProperty("st-wst-putty-gm-port");
						System.out.println(port);
					}
					host = configuration.getStringProperty("st-wst-putty-gm-host");
				} else if (context.getSiteID().equals("5885")) {
					host = configuration.getStringProperty("st-stk-putty-gm-host");
					port = configuration.getStringProperty("st-stk-putty-gm-port");
				} else {
					System.out.println("Site Id is not found");
					Assert.fail("Site Id is not found");
				}
			}
		}

		else {
			System.out.println("Get environment Details from NPS DB Putty Host:-" + context.getPuttyHost()
					+ "Putty Port:-" + context.getPuttyPort());
			if (region.equalsIgnoreCase("ST")) {
				if (context.getSiteID().equals("5649")) {
					if (context.isVehicleLoadRequired()) {
						port = configuration.getStringProperty("st-wst-putty-gm-port-vehicle-load");
					} else {
						port = context.getPuttyPort();
					}
					host = context.getPuttyHost();
				}
			}

			else if (region.equalsIgnoreCase("SIT")) {
				if (context.getSiteID().equals("5649")) {
					if (context.isVehicleLoadRequired()) {
						port = configuration.getStringProperty("sit-wst-putty-gm-port-vehicle-load");
					} else {
						port = context.getPuttyPort();
					}
					host = context.getPuttyHost();
				}
			}
		}

		puttyFunctionsPage.loginPutty(host, port);
		Thread.sleep(5000);

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