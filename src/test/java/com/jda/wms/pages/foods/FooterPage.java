package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class FooterPage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public FooterPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void clickFooterButton() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderFooter.png", timeoutInSec);
		screen.click("images/OrderHeaderFooter.png");
		Thread.sleep(3000);
	}
}
