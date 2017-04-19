package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class JdaSignout extends PageObject {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	public JdaSignout(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

public void clickSignoutButton() throws FindFailed{
	screen.wait("/images/JDAHeader/HeaderIcons.png", 20);
	screen.click("images/JDAHeader/Singout.png", 25);
	logger.debug("User signed out of JDA WMS Application");
}

}