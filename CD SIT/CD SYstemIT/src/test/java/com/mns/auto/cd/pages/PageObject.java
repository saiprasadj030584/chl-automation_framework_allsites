package com.mns.auto.cd.pages;

import java.awt.AWTException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.inject.Inject;
import com.mns.auto.cd.webdriver.ActionsNew;
import com.mns.auto.cd.webdriver.Locator;
import com.mns.auto.cd.webdriver.Synchronizer;

public class PageObject {

	private static Logger log = Logger.getLogger(PageObject.class);

	public static final int IMPLICIT_TIMEOUT_VALUE = 60; // seconds
	public static final int EXPLICIT_TIMEOUT_VALUE = 60; // seconds
	public static final int PAGELOAD_TIMEOUT_VALUE = 60; // seconds

	protected WebDriver webDriver;

	private final Locator locator;

	@Inject
	public PageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.locator = new Locator();
	}

	public ActionsNew actionsnew() {
		return new ActionsNew(webDriver, locator);
	}

	

	public Synchronizer synchronizer() {
		return new Synchronizer(webDriver, locator);
	} 
	
	public void plmSideFrame() throws InterruptedException, AWTException {
		webDriver.switchTo().defaultContent();
		sleeper(2000);
		webDriver.switchTo().frame("sidebarframe");
	}
	
	public void plmContentFrame() throws InterruptedException, AWTException {
		webDriver.switchTo().defaultContent();
		sleeper(2000);
		webDriver.switchTo().frame("contentframe");
	}

	public void back() {
		this.webDriver.navigate().back();
	}

	public void forward() {
		this.webDriver.navigate().forward();
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

	///// Added By Bhuban

	public void implicitTimeout() {

		webDriver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);
	}

	public void maximizeBrowser() {

		webDriver.manage().window().maximize();
		this.sleeper(7000);
	}

	public void sleeper(int timeInMilliSeconds) {

		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void open(String url) {

		Assert.assertNotNull(webDriver);
		webDriver.get(url);
		sleeper(10000);
		System.out.println("Go to URL : " + url);
		// webDriver.get("javascript:document.getElementById('overridelink').click()");
		maximizeBrowser();
		implicitTimeout();
		System.out.println("Page title is : " + webDriver.getTitle());
	}

	/**
	 * method highlights the element which is being selected.
	 * 
	 * @param element
	 */
	public void highlightElement(WebElement element) {

		for (int i = 0; i < 1; i++) {

			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: yellow; border: 3px solid yellow;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

		}

	}

	public void mouseOver(String locator, String value) {

		WebElement toElement = null;

		if (locator.equalsIgnoreCase("xpath")) {

			toElement = webDriver.findElement(By.xpath(value));
		} else if (locator.equalsIgnoreCase("css")) {
			toElement = webDriver.findElement(By.cssSelector(value));
		} else if (locator.equalsIgnoreCase("id")) {
			toElement = webDriver.findElement(By.id(value));
		} else if (locator.equalsIgnoreCase("linkText")) {
			toElement = webDriver.findElement(By.linkText(value));
		} else if (locator.equalsIgnoreCase("name")) {
			toElement = webDriver.findElement(By.name(value));
		} else if (locator.equalsIgnoreCase("className")) {
			toElement = webDriver.findElement(By.className(value));
		}

		new Actions(webDriver).moveToElement(toElement).build().perform();

	}

	public void pressTab() {

		new Actions(webDriver).sendKeys(Keys.TAB).build().perform();

	}

	public boolean checkAlertText(String textValue) {

		this.sleeper(3000);
		boolean result = false;

		Alert alert = webDriver.switchTo().alert();

		if (true) {

			alert.accept();
		}

		return true;

	}

	public void rightClickOn(WebElement element) {

		this.sleeper(2000);
		new Actions(webDriver).contextClick(element).build().perform();
	}

	public void rightClick() {

		new Actions(webDriver).contextClick().build().perform();
	}

	public WebElement waitForPresenceOfElement(final By locator) {

		final long startTime = System.currentTimeMillis();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);
		int tries = 0;
		boolean found = false;
		WebElement elem = null;
		while ((System.currentTimeMillis() - startTime) < 91000) {
			log.info("Searching for element. Try number " + (tries++));
			try {
				elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				log.info("Stale element: \n" + e.getMessage() + "\n");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			log.info("Found element after waiting for " + totalTime + " milliseconds.");
		} else {
			log.info("Failed to find element after " + totalTime + " milliseconds.");
		}
		return elem;
	}

	// public boolean waitForVisibilityOfElement(final WebElement element){
	// boolean element_check_status = false;
	//
	// for (int element_check = 0; element_check < 120; element_check++) {
	//
	// if (element_check_status = element.isDisplayed()) {
	// break;
	// } else {
	// sleeper(1000);
	// }
	// }
	// System.out.println(element_check_status);
	// return element_check_status;
	// }

	public WebElement waitForVisibilityOfElement(final WebElement element) {

		final long startTime = System.currentTimeMillis();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);
		int tries = 0;
		boolean found = false;
		WebElement elem = null;
		while ((System.currentTimeMillis() - startTime) < 91000) {

			// log.info("Searching for element. Try number " + (tries++));
			try {
				elem = wait.until(ExpectedConditions.visibilityOf(element));
				found = true;

				break;
			} catch (StaleElementReferenceException e) {
				log.info("Stale element: \n" + e.getMessage() + "\n");
				log.info("Failed to find element" + element);
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			// log.info("Found element after waiting for " + totalTime+
			// " milliseconds.");

		} else {
			close();
			log.info("Failed to find element after " + totalTime + " milliseconds.");
		}
		return elem;
	}

	public void close() {

		webDriver.quit();
		log.info("**********************  END OF Acceptance Criteria************************************ \n");
		System.out
				.println(" **********************  END OF Acceptance Criteria *********************************** \n");
		Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
	}

	public class BrowserCleanup implements Runnable {
		public void run() {
			stop();
		}
	}

	public void stop() {
		try {
			if (webDriver != null)
				webDriver.quit();

			log.info("closing the browser");

		} catch (UnreachableBrowserException e) {
			log.error("cannot close browser: unreachable browser");
		}
	}

	public boolean validateMandatoryByXpath(String xPath) {

		if (webDriver.findElement(By.xpath(xPath)).getText().equals("*")) {

			return true;

		} else {

			return false;
		}

	}

	public String getTitle() {

		return this.webDriver.getTitle();
	}

	public void doubleClickOn(WebElement element) {

		Actions act = new Actions(webDriver);
		act.moveToElement(element).doubleClick().build().perform();
	}

}
