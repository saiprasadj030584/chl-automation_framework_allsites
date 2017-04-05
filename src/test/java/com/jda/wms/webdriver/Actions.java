package com.jda.wms.webdriver;

import static com.jda.wms.webdriver.Synchronizer.DEFAULT_TIME_OUT_IN_SECONDS;

import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import com.jda.wms.exception.LocatorException;

public class Actions {

	private final WebDriver webDriver;

	private final Locator locator;

	@Inject
	public Actions(WebDriver webDriver, Locator locator) {
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
		Actions oAction1 = new Actions(webDriver, locator);
		oAction1.doubleClick(addProductLinkElement);
	}

	public void contextClick(WebElement addProductLinkElement) {
		Actions oAction1 = new Actions(webDriver, locator);
		oAction1.contextClick(addProductLinkElement);
	}

}