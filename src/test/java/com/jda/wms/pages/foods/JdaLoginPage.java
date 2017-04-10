package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public JdaLoginPage(WebDriver webDriver, OrderHeaderPage orderHeaderPage, Configuration configuration) {
		super(webDriver);
		this.webDriver = webDriver;
		this.configuration = configuration;
	}

	public void login() {
		try {
			webDriver.manage().window().maximize();
			webDriver.navigate().to(configuration.getStringProperty("gm-foods-url"));
			Thread.sleep(30000);

			enterUsername();
			enterPassword();
			clickSubmitButton();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enterUsername() {
		try {
			screen.wait("images/username.png", 20);
			screen.click("images/username.png", 25);
			screen.type(configuration.getStringProperty("username"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enterPassword() {
		try {
			screen.wait("images/password.png", 20);
			screen.click("images/password.png", 25);
			screen.type(configuration.getStringProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clickSubmitButton() {
		try {
			screen.wait("images/Submit.png", 20);
			screen.click("images/Submit.png", 25);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}