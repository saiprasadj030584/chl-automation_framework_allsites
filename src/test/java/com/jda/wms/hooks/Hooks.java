package com.jda.wms.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {

	private final WebDriver webDriver;

	@Inject
	public Hooks(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@After()
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
	
}
