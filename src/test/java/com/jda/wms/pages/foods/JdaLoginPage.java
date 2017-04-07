package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class JdaLoginPage extends PageObject {

	WebElement webElement;
	private final WebDriver webDriver;
	Screen screen = new Screen();

	public static final String environment = "http://hlxc0dc023.unix.marksandspencercate.com:8880/wmsbac2/UserLogin.html";

	@Inject
	public JdaLoginPage(WebDriver webDriver, OrderHeaderPage orderHeaderPage,
			ScreenShotCapture screenCaptureStepDefs) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void login() {

		try {
			webDriver.manage().window().maximize();
			webDriver.navigate().to(environment);
			Thread.sleep(30000);
			screen.wait("images/Use.png", 100);
			screen.click("images/Use.png", 25);
			screen.type("P9124783");

			screen.wait("images/PWD.png", 100);
			screen.click("images/PWD.png", 25);
			screen.type("12345");

			screen.wait("images/Submit.png", 100);
			screen.click("images/Submit.png", 25);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}