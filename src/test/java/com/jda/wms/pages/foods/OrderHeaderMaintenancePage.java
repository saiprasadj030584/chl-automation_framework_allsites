package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderMaintenancePage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderHeaderMaintenancePage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void clickQueryButton() throws FindFailed {
		screen.wait("images/OrderHeaderQuery1.png", timeoutInSec);
		screen.click("images/OrderHeaderQuery1.png");
	}

	public void enterOrderNo(String OrderNo) throws FindFailed {
		screen.wait("images/OrderHeaderNo.png", timeoutInSec);
		screen.click("images/OrderHeaderNo.png");
		screen.type(OrderNo);
	}

	public void clickExecuteButton() throws FindFailed {
		screen.wait("images/OrderHeaderExecute.png", timeoutInSec);
		screen.click("images/OrderHeaderExecute.png");
	}

	public void clickLinesButton() throws FindFailed {
		screen.wait("images/OrderHeaderLines.png", timeoutInSec);
		screen.click("images/OrderHeaderLines.png");
	}

}
