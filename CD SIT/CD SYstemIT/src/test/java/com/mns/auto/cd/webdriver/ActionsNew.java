package com.mns.auto.cd.webdriver;


import static com.mns.auto.cd.webdriver.Synchronizer.DEFAULT_TIME_OUT_IN_SECONDS;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import com.mns.auto.cd.exception.LocatorException;
import com.mns.auto.cd.pages.PageObject;
import com.mns.auto.cd.utils.DateUtils;

import org.openqa.selenium.By;
public class ActionsNew {

	private final WebDriver webDriver;
	private static Logger log = Logger.getLogger(PageObject.class);
	private final Locator locator;

	@Inject
	public ActionsNew(WebDriver webDriver, Locator locator) {
		this.webDriver = webDriver;
		this.locator = locator;
	}

	public WebElement getElement(String locatorKey) {
		return getElement(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getElement(String locatorKey, long timeoutInSeconds) {
		return getElement(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public WebElement getElement(String locatorKey, Conditions conditions) {
		return getElement(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getElement(String locatorKey, Tag tag, Conditions conditions) {
		return getElement(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getElement(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (WebElement) wait.until(
					ExpectedConditions.locateElement(this.locator.getBy(locatorKey, Scope.ELEMENT, tag), conditions));
		} catch (LocatorException e) {
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}

	public List<WebElement> getElements(String locatorKey) {
		return getElements(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getElements(String locatorKey, long timeoutInSeconds) {
		return getElements(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public List<WebElement> getElements(String locatorKey, Conditions conditions) {
		return getElements(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getElements(String locatorKey, Tag tag, Conditions conditions) {
		return getElements(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getElements(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (List<WebElement>) wait.until(ExpectedConditions
					.locateAllElements(this.locator.getBy(locatorKey, Scope.ELEMENT, tag), conditions));
		} catch (LocatorException e) {
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}

	public WebElement getChild(String locatorKey) {
		return getChild(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getChild(String locatorKey, long timeoutInSeconds) {
		return getChild(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public WebElement getChild(String locatorKey, Conditions conditions) {
		return getChild(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getChild(String locatorKey, Tag tag, Conditions conditions) {
		return getChild(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getChild(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (WebElement) wait.until(
					ExpectedConditions.locateElement(this.locator.getBy(locatorKey, Scope.CHILDREN, tag), conditions));
		} catch (LocatorException e) {
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}

	public List<WebElement> getChildren(String locatorKey) {
		return getChildren(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getChildren(String locatorKey, long timeoutInSeconds) {
		return getChildren(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public List<WebElement> getChildren(String locatorKey, Conditions conditions) {
		return getChildren(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getChildren(String locatorKey, Tag tag, Conditions conditions) {
		return getChildren(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getChildren(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (List<WebElement>) wait.until(ExpectedConditions
					.locateAllElements(this.locator.getBy(locatorKey, Scope.CHILDREN, tag), conditions));
		} catch (LocatorException e) {
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}

	public WebElement getDescendant(String locatorKey) {
		return getDescendant(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getDescendant(String locatorKey, long timeoutInSeconds) {
		return getDescendant(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public WebElement getDescendant(String locatorKey, Conditions conditions) {
		return getDescendant(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getDescendant(String locatorKey, Tag tag, Conditions conditions) {
		return getDescendant(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public WebElement getDescendant(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (WebElement) wait.until(ExpectedConditions
					.locateElement(this.locator.getBy(locatorKey, Scope.DESCENDANTS, tag), conditions));
		} catch (LocatorException e) {
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}

	public List<WebElement> getDescendants(String locatorKey) {
		return getDescendants(locatorKey, Tag.GENERIC, new Conditions(), DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getDescendants(String locatorKey, long timeoutInSeconds) {
		return getDescendants(locatorKey, Tag.GENERIC, new Conditions(), timeoutInSeconds);
	}

	public List<WebElement> getDescendants(String locatorKey, Conditions conditions) {
		return getDescendants(locatorKey, Tag.GENERIC, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getDescendants(String locatorKey, Tag tag, Conditions conditions) {
		return getDescendants(locatorKey, tag, conditions, DEFAULT_TIME_OUT_IN_SECONDS);
	}

	public List<WebElement> getDescendants(String locatorKey, Tag tag, Conditions conditions, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.webDriver, timeoutInSeconds);
		try {
			return (List<WebElement>) wait.until(ExpectedConditions
					.locateAllElements(this.locator.getBy(locatorKey, Scope.DESCENDANTS, tag), conditions));
		} catch (LocatorException e) {
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}

	public void mouseHover(WebElement element) {
		Locatable hoverItem = (Locatable) element;
		Mouse mouse = ((HasInputDevices) webDriver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());
	}

	public void doubleClick(WebElement addProductLinkElement) {
		ActionsNew oAction1 = new ActionsNew(webDriver, locator);
		oAction1.doubleClick(addProductLinkElement);
	}

	/*
	 * public Action1 contextClick1(WebElement elements) { Action1 action1 = new
	 * Action1(webDriver); action1.contextClick1(elements);
	 * 
	 * //return action1.contextClick1(elements);
	 */

	public void contextClick(WebElement addProductLinkElement) {

		// Actions oAction2 = new Actions(webDriver, locator);

		org.openqa.selenium.interactions.Actions oAction2 = new org.openqa.selenium.interactions.Actions(webDriver);
		oAction2.contextClick(addProductLinkElement).build().perform();

	}

	public void rightClickPerform(WebElement element1) {
		ActionsNew action = new ActionsNew(webDriver, locator);
		action.contextClick(element1);

	}

	private Object build() {
		// TODO Auto-generated method stub
		return null;
	}

	public void enterStartDate(WebElement element) throws InterruptedException {
		element.sendKeys(DateUtils.getCurrentSystemDate());
		Thread.sleep(3000L);

	}

	public void enterEndDate(WebElement element) throws InterruptedException {
		element.sendKeys(DateUtils.getCurrentSystemDate());
		Thread.sleep(3000L);
	}

	public void multipleswitchtabs(String tabname) {
		Set<String> tab_handles = webDriver.getWindowHandles();
		int number_of_tabs = tab_handles.size();
		int new_tab_index = number_of_tabs - 1;
		int old_tab_index = number_of_tabs - 2;
		int parent_tab_index = number_of_tabs - 3;
		switch (tabname) {
		case "new_tab":
			webDriver.switchTo().window(tab_handles.toArray()[new_tab_index].toString());
			break;
		case "old_tab":
			webDriver.switchTo().window(tab_handles.toArray()[old_tab_index].toString());
			break;
		case "parent_tab":
			webDriver.switchTo().window(tab_handles.toArray()[parent_tab_index].toString());
			break;
		}

	}
	public void setCurrency(WebElement element2,String currency) {
		
	}
	
	

}
