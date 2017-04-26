package com.jda.wms.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final WebDriver webDriver;
	Screen screen = new Screen();

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
	
	@After()
	public void logoutPutty() throws FindFailed, InterruptedException{
		while (screen.exists("/images/Putty/3Logout.png") == null){
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
}
