package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class JdaHomePage extends PageObject {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;
	//private WebDriver webDriver;

	@Inject
	public JdaHomePage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigateToOrderHeader() throws FindFailed, InterruptedException {
		clickDatamenu();
		hoverOrder();
		clickOrderheader();
	}

	private void clickDatamenu() throws FindFailed {
		screen.wait("images/DataMenu.png", timeoutInSec);
		screen.click("images/DataMenu.png");
	}

	private void hoverOrder() throws FindFailed {
		screen.wait("images/OrderSubmenu.png", timeoutInSec);
		screen.click("images/OrderSubmenu.png");
	}

	private void clickOrderheader() throws FindFailed {
		screen.wait("images/OrderHeader.png", timeoutInSec);
		screen.click("images/OrderHeader.png");
	}
}
