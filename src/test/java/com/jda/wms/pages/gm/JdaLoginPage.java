package com.jda.wms.pages.gm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.pages.PageObject;

public class JdaLoginPage extends PageObject {
	WebElement webElement;
	private final WebDriver webDriver;
	private final Configuration configuration;
	Screen screen = new Screen();

	@Inject
	public JdaLoginPage(WebDriver webDriver, Configuration configuration) {
		super(webDriver);
		this.webDriver = webDriver;
		this.configuration = configuration;
	}

	public void login() throws FindFailed, InterruptedException {
		webDriver.manage().window().maximize();
		webDriver.navigate().to(configuration.getStringProperty("gm-jda-url"));

		enterUsername();
		enterPassword();
		clickConnectButton();
		Thread.sleep(5000);
	}

	private void enterUsername() throws FindFailed, InterruptedException {
		int waitTime = 15;
		do {
			if (screen.exists("images/JDALogin/username.png") != null) {
				break;
			}
			Thread.sleep(15000);
			waitTime = waitTime + 15;
		} while (waitTime < 120);

		screen.wait("images/JDALogin/username.png", 20);
		screen.click("images/JDALogin/username.png", 25);
		screen.type(configuration.getStringProperty("username"));
	}

	private void enterPassword() throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(configuration.getStringProperty("password"));
		Thread.sleep(1000);
	}

	private void clickConnectButton() throws FindFailed, InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(15000);
	}
}