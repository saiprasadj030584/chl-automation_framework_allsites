package com.jda.wms.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditions {

	public static ExpectedCondition<Boolean> conditionsSatisfied(final By by, final Conditions conditions) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					List<WebElement> elements = conditions.matches(driver.findElements(by));
					return elements == null ? false : true;
				} catch (NoSuchElementException e) {
					return false;
				} catch (StaleElementReferenceException e) {
					return false;
				} catch (InvalidElementStateException e) {
					return false;
				}
			}
		};
	}

	public static ExpectedCondition<WebElement> locateElement(final By by, final Conditions conditions) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				try {
					List<WebElement> elements = conditions.matches(driver.findElements(by));
					return elements == null ? null : elements.get(0);
				} catch (NoSuchElementException e) {
					return null;
				} catch (StaleElementReferenceException e) {
					return null;
				} catch (InvalidElementStateException e) {
					return null;
				}
			}
		};
	}

	public static ExpectedCondition<List<WebElement>> locateAllElements(final By by, final Conditions conditions) {
		return new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {
				try {
					List<WebElement> elements = conditions.matches(driver.findElements(by));
					return elements == null ? null : elements;
				} catch (NoSuchElementException e) {
					return null;
				} catch (StaleElementReferenceException e) {
					return null;
				} catch (InvalidElementStateException e) {
					return null;
				}
			}

		};
	}

	public static ExpectedCondition<String> textForElementLocated(final By by, final Conditions conditions) {
		return new ExpectedCondition<String>() {
			@Override
			public String apply(WebDriver driver) {
				try {
					List<WebElement> elements = conditions.matches(driver.findElements(by));
					return elements == null ? null : elements.get(0).getText();
				} catch (NoSuchElementException e) {
					return null;
				} catch (StaleElementReferenceException e) {
					return null;
				} catch (InvalidElementStateException e) {
					return null;
				}
			}
		};
	}

}
