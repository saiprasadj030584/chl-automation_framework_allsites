package com.jda.wms.pages.PageObject;

import org.openqa.selenium.WebDriver;

public class PageObject {

	protected WebDriver webDriver;

	public PageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void back() {
		this.webDriver.navigate().back();
	}

	public void forward() {
		this.webDriver.navigate().back();
	}

	public void refresh() {
		this.webDriver.navigate().refresh();
	}
}
