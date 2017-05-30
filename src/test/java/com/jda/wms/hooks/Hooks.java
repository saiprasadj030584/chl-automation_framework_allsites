package com.jda.wms.hooks;

import java.sql.SQLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final WebDriver webDriver;
	Screen screen = new Screen();
	private Context context;

	@Inject
	public Hooks(WebDriver webDriver, Context context) {
		this.webDriver = webDriver;
		this.context = context;
	}

	@Before
	public void logScenarioDetails(Scenario scenario) throws Exception {
		String scenarioID = scenario.getId();
		String featureID = scenarioID.substring(0, scenarioID.lastIndexOf(";"));
		logger.debug(
				"###########################################################################################################################");
		logger.debug("featureID: " + featureID);
		logger.debug("Start of Scenario: " + scenario.getName());
		logger.debug(
				"###########################################################################################################################");
	}

	// @After()
	public void tearDown(Scenario scenario) {

		// attaching the screenshot in cucumber report
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}

		// clearing down webdriver object
		if (webDriver != null) {
			webDriver.close();
			webDriver.quit();
		}
	}

	//@After("@purchase_order")
	public void logoutPutty() throws FindFailed, InterruptedException {
		while (screen.exists("/images/Putty/3Logout.png") == null) {
			screen.type(Key.F12);
		}
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);

		screen.wait("images/Putty/PuttyClose.png", 20);
		screen.click("images/Putty/PuttyClose.png", 25);
		Thread.sleep(1000);

		screen.wait("images/Putty/PuttyCloseOK.png", 20);
		screen.click("images/Putty/PuttyCloseOK.png", 25);
		Thread.sleep(1000);
	}

	@After
	public void afterDetails(Scenario scenario) {
		logger.debug(
				"###########################################################################################################################");
		logger.debug("End of Scenario: " + scenario.getName());
		logger.debug(
				"###########################################################################################################################");
	}

	@After
	public void closeDBConnection() throws SQLException {
		if  (context.getConnection() != null) {
			context.getConnection().close();
			logger.debug("DB Connection closed");
		}
	}

	// @After
	public void clickSignoutButton() throws FindFailed {
		screen.wait("/images/JDAHeader/HeaderIcons.png", 20);
		screen.click("images/JDAHeader/Singout.png", 25);
		logger.debug("Signed off JDA WMS Application");
	}
}
