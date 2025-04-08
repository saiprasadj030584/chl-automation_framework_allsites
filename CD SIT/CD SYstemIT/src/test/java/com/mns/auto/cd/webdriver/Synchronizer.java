package com.mns.auto.cd.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import com.mns.auto.cd.exception.LocatorException;

public class Synchronizer {
	private final WebDriver webDriver;
	private final Locator locator;
	private final JavascriptExecutor jse;

	public final static Long DEFAULT_TIME_OUT_IN_SECONDS = 60L;

	@Inject
	public Synchronizer(WebDriver webDriver, Locator locator) {
		this.webDriver = webDriver;
		this.locator = locator;
		this.jse = ((JavascriptExecutor) webDriver);
	}

	public boolean waitForElement(String locatorKey) {
		return waitForElement(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForElement(String locatorKey, long timeoutInSeconds) {
		return waitForElement(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public boolean waitForElement(String locatorKey, Conditions conditions) {
		return waitForElement(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForElement(String locatorKey, Tag tag, Conditions conditions) {
		return waitForElement(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForElement(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (Boolean) wait.until(ExpectedConditions
					.conditionsSatisfied(this.locator.getBy(locatorKey, Scope.ELEMENT, tag), conditions));
		} catch (LocatorException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForChild(String locatorKey) {
		return waitForChild(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForChild(String locatorKey, long timeoutInSeconds) {
		return waitForChild(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public boolean waitForChild(String locatorKey, Conditions conditions) {
		return waitForChild(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForChild(String locatorKey, Tag tag, Conditions conditions) {
		return waitForChild(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForChild(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (Boolean) wait.until(ExpectedConditions
					.conditionsSatisfied(this.locator.getBy(locatorKey, Scope.CHILDREN, tag), conditions));
		} catch (LocatorException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForDescendant(String locatorKey) {
		return waitForDescendant(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForDescendant(String locatorKey, long timeoutInSeconds) {
		return waitForDescendant(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public boolean waitForDescendant(String locatorKey, Conditions conditions) {
		return waitForDescendant(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForDescendant(String locatorKey, Tag tag, Conditions conditions) {
		return waitForDescendant(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public boolean waitForDescendant(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (boolean) wait.until(ExpectedConditions
					.conditionsSatisfied(this.locator.getBy(locatorKey, Scope.DESCENDANTS, tag), conditions));
		} catch (LocatorException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void waitUntilPageLoaded() {
		waitUntilPageLoaded(DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public void waitUntilPageLoaded(long timeOut) {
		waitForJStoLoad(timeOut);
		// waitForJQtoLoad(timeOut);
	}

	private boolean waitForJStoLoad(long timeOut) {

		WebDriverWait wait = new WebDriverWait(webDriver, timeOut);

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jse.executeScript("return (document.readyState);").toString().equals("complete");
			}
		};

		return wait.until(jsLoad);
	}

	private boolean waitForJQtoLoad(Long timeOut) {

		WebDriverWait wait = new WebDriverWait(webDriver, timeOut);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jse.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		return wait.until(jQueryLoad);
	}

}
