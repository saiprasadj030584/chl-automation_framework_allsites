/**
 * 
 */
/**
 * @author priyanka.selvaraj
 *
 */
package com.jda.wms.pages.Exit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.config.Constants;
import com.jda.wms.context.Context;

public class JdaExitLoginPage {
	WebElement webElement;
	public static RemoteWebDriver driver = null;
	private Configuration configuration;
	private Context context;
	public static String statusRegion = System.getProperty("USE_DB");
	//public static String region = System.getProperty("REGION");
	 public static String region ="ST";
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public JdaExitLoginPage(Configuration configuration, Context context) {
		this.configuration = configuration;
		this.context = context;
	}

	public void login() throws FindFailed, InterruptedException {
		System.out.println("**Login function");

		if (null == driver) {
			System.out.println("Driver is null");
			try {
				Process p = Runtime.getRuntime()
						.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
				p.waitFor(30, TimeUnit.SECONDS);
			} catch (IOException e) {

				e.printStackTrace();
			}

			setDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
			if (statusRegion == null) {
				statusRegion = "N";
			} else {
				System.out.println("LOGIN PAGE Status region---> " + statusRegion);
			}

			/*
			 * if (region == null) { region = "SIT";
			 * 
			 * } else { System.out.println("Region---> " + region); }
			 */
			if (statusRegion.equalsIgnoreCase("N")) {
				if (region.equals("SIT")) {
					System.out.println("We are using SIT Env -->" + configuration.getStringProperty("sit-Exit-url"));
					driver.navigate().to(configuration.getStringProperty("sit-Exit-url"));
				} else if (region.equals("ST")) {
					System.out.println("We are using ST Env -->" + configuration.getStringProperty("sit-Exit-url"));
					driver.navigate().to(configuration.getStringProperty("sit-Exit-url"));
				}
			} else {
				System.out.println("Get environment Details from NPS DB  " + "Foods URL:-" + context.getURL());
				driver.navigate().to(context.getURL());
			}
			int waitTime = 3;
			do {
				if (screen.exists("/images/JDALogin/username.png") != null) {
					break;
				} else {
					System.out.println("Login screen not found in loop");
				}

				Thread.sleep(5000);
				waitTime = waitTime + 3;
			} while (waitTime < 120);

			enterUsername();
			enterPassword();
			clickConnectButton();
			//
		}

		int waitTime = 3;
		do {
			if (screen.exists("/images/JDAHome/Welcomed.png") != null) {
				break;
			} else if (screen.exists("images/JDALogin/username.png") != null) {

				enterUsername();
				enterPassword();
				clickConnectButton();
				break;
			} else {
				System.out.println("Nothing found in the UI . Again goes in Loop >" + waitTime);
			}
			Thread.sleep(15000);
			waitTime = waitTime + 3;
		} while (waitTime < 60);

		if (screen.exists("/images/JDAHome/Welcomed.png") != null) {
			screen.rightClick("/images/JDAHome/Welcomed.png", 25);
			Thread.sleep(2000);
			screen.type(Key.UP);
			screen.type(Key.UP);
			screen.type(Key.UP);
			screen.type(Key.UP);
			screen.type(Key.UP);
			screen.type(Key.UP);
			screen.type(Key.UP);
			screen.type(Key.UP);
			Thread.sleep(2000);
			screen.type(Key.ENTER);
		}

		else if (screen.exists("/images/JDAHome/Welcome.png") != null) {
			screen.rightClick("/images/JDAHome/Welcome.png", 25);
			Thread.sleep(4000);
			screen.click("/images/JDAHome/CloseAll.png", 25);
		} else {
			System.out.println("Application Load issue. Hence IE driver is killed and initialized !!!");

			try {
				Process p = Runtime.getRuntime()
						.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
				p.waitFor(30, TimeUnit.SECONDS);
			} catch (IOException e) {

				e.printStackTrace();
			}
			driver = null;

			Assert.fail();
		}
	}

	public static void setDriver() {
		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("screen-resolution", "1364*766");
		// System.setProperty("org.apache.commons.logging.Log",
		// "org.apache.commons.logging.impl.Jdk14Logger");
		System.setProperty("webdriver.ie.driver", Constants.USER_DIR + "/bin/iedriver/x86/IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
	}

	/*
	 * public WebDriver get() { return launchWebDriver(); }
	 */

	public void enterUsername() throws FindFailed, InterruptedException {
		int waitTime = 15;
		do {
			if (screen.exists("images/JDALogin/username.png") != null) {
				break;
			}
			Thread.sleep(15000);
			waitTime = waitTime + 15;
		} while (waitTime < 300);

		screen.wait("images/JDALogin/username.png", 20);
		screen.click("images/JDALogin/username.png", 25);
		if (statusRegion.equalsIgnoreCase("N")) {
			if (region.equalsIgnoreCase("SIT")) {
				screen.type(configuration.getStringProperty("sit-username"));
			} else if (region.equalsIgnoreCase("ST")) {
				screen.type(configuration.getStringProperty("st-username"));
			}
		} else {
			screen.type(context.getAppUserName());
		}
	}

	public void enterPassword() throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
		if (statusRegion.equalsIgnoreCase("N")) {
			if (region.equalsIgnoreCase("SIT")) {
				screen.type(configuration.getStringProperty("sit-password"));
			} else if (region.equalsIgnoreCase("ST")) {
				screen.type(configuration.getStringProperty("st-password"));
			}
		} else {
			screen.type(context.getAppPassord());
		}
		Thread.sleep(1000);
	}

	public void clickConnectButton() throws FindFailed, InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(15000);
	}

	public boolean isHomePageDisplayed() throws FindFailed {
		if (screen.exists("/images/JDAHome/Welcomed.png") != null) {
			return true;
		} else {
			return false;
		}
	}
}