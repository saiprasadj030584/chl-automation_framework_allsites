package com.jda.wms.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.jda.wms.webdriver.Actions;
import com.jda.wms.webdriver.Locator;
import com.jda.wms.webdriver.Synchronizer;

public class PageObject {

	protected WebDriver webDriver;

	private final Locator locator;

	public PageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.locator = new Locator();
	}

	public Actions actions() {
		return new Actions(webDriver, locator);
	}

	public Synchronizer synchronizer() {
		return new Synchronizer(webDriver, locator);
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

	public String getCurrentWindowUrl() {
		Set<String> windowNames = webDriver.getWindowHandles();
		String windowName = "";
		for (String string : windowNames) {
			windowName = string;
		}
		webDriver.switchTo().window(windowName);
		return webDriver.getCurrentUrl().toString();
	}

	public void switchToWindow() {
		Set<String> windowNames = webDriver.getWindowHandles();
		String windowName = "";
		for (String string : windowNames) {
			windowName = string;
		}
		webDriver.switchTo().window(windowName);
	}
}
