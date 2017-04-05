package com.jda.wms.stepdefs.foods;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.context.TataCliqContext;
import com.jda.wms.pages.foods.BackOfficeLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class BackOfficeLoginPageStepDefs {
	private final BackOfficeLoginPage backOfficePage;
	private final TataCliqContext tataCliqContext;
	private final WebDriver webdriver;

	@Inject
	public BackOfficeLoginPageStepDefs(BackOfficeLoginPage backOfficePage, TataCliqContext tataCliqContext,
			WebDriver webdriver) {
		this.backOfficePage = backOfficePage;
		this.tataCliqContext = tataCliqContext;
		this.webdriver = webdriver;
	}

	// @When("^the agent log in as a admin in back-office$")
	// public void the_agent_log_in_as_a_admin_in_back_office() throws Throwable
	// {
	// backOfficePage.openBackOfficeLogin();
	// // backOfficePage.switchToWindow();
	// paymentpage.switchToWindow();
	// Thread.sleep(3000L);
	// backOfficePage.clickClosePopUpButton();
	// Thread.sleep(3000L);
	// backOfficePage.clickClosePopUpButton();
	// Thread.sleep(3000L);
	// backOfficePage.clickClosePopUpButton();
	// Thread.sleep(3000L);
	// backOfficePage.clickClosePopUpButton();
	//
	// }

	@When("^the agent log in as a admin in back-office$")
	public void the_agent_log_in_as_a_admin_in_back_office() throws Throwable {
		backOfficePage.openBackOfficeLoginPage();
		backOfficePage.loginAsUser();
		Thread.sleep(5000L);
		backOfficePage.clickClosePopUpButton();
		Thread.sleep(2000L);
		backOfficePage.switchToWindow();
		Thread.sleep(2000L);
		backOfficePage.clickClosePopUpButton();
		Thread.sleep(2000L);
		backOfficePage.switchToWindow();
		Thread.sleep(2000L);
		backOfficePage.clickClosePopUpButton();
		Thread.sleep(2000L);
		backOfficePage.switchToWindow();
		Thread.sleep(2000L);
		backOfficePage.clickClosePopUpButton();
		backOfficePage.clickNewWindowPopUpCloseButton();
		Thread.sleep(3000L);
		Thread.sleep(2000L);
		backOfficePage.clickClosePopUpButton();
		Thread.sleep(2000L);
		backOfficePage.switchToWindow();
		Thread.sleep(2000L);
		backOfficePage.clickClosePopUpButton();
		Thread.sleep(2000L);
		backOfficePage.switchToWindow();
		Thread.sleep(2000L);
		backOfficePage.clickClosePopUpButton();
	}

	@Given("^the agent log in as a admin in the back-office new$")
	public void the_agent_log_in_as_a_admin_in_the_back_office() throws Throwable {
		backOfficePage.openBackOfficeLogin_1();
		backOfficePage.switchToWindow_1();
		// backOfficeLoginPage.clickNewWindowPopUpCloseButton();
		Thread.sleep(3000L);
	}

	@When("^the agent logged in as a admin in back-office$")
	public void the_agent_logged_in_as_a_admin_in_back_office() throws Throwable {
		backOfficePage.openBackOfficeLoginPage();
		backOfficePage.backofficeLogin();
		Thread.sleep(3000L);
		backOfficePage.switchToWindow_1();
		Thread.sleep(3000L);
	}

}
