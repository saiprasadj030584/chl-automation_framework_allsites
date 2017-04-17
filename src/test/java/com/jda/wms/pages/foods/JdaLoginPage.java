package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.pages.PageObject;

public class JdaLoginPage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	WebElement webElement;
	private final WebDriver webDriver;
	private final Configuration configuration;
	Screen screen = new Screen();

	@Inject
	public JdaLoginPage(WebDriver webDriver,Configuration configuration) {
		super(webDriver);
		this.webDriver = webDriver;
		this.configuration = configuration;
	}

	public void login() throws FindFailed, InterruptedException {
		webDriver.manage().window().maximize();
		webDriver.navigate().to(configuration.getStringProperty("gm-foods-url"));
		Thread.sleep(60000);

		enterUsername();
		enterPassword();
		clickConnectButton();
	}

	private void enterUsername() throws FindFailed {
		screen.wait("images/JDALogin/username.png", 20);
		screen.click("images/JDALogin/username.png", 25);
		screen.type(configuration.getStringProperty("username"));
	}

	private void enterPassword() throws FindFailed {
		screen.wait("images/JDALogin/password.png", 20);
		screen.click("images/JDALogin/password.png", 25);
		screen.type(configuration.getStringProperty("password"));
	}

	private void clickConnectButton() throws FindFailed, InterruptedException {
		/*screen.wait("images/JDALogin/Connect.png", 20);
		screen.click("images/JDALogin/Connect.png", 25);*/
		screen.type(Key.ENTER);
		Thread.sleep(15000);
		
	}
}