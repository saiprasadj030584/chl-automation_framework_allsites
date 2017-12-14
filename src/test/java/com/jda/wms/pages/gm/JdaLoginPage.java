package com.jda.wms.pages.gm;

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

public class JdaLoginPage {
	WebElement webElement;

	Screen screen = new Screen();
	int timeoutInSec = 20;

	public static RemoteWebDriver driver;
	private Configuration configuration;
	private Context context;
	public static String statusRegion = System.getProperty("USE_DB");
	public static String region = System.getProperty("REGION");
	//public static String region = "ST";
	// Configuration configuration = new Configuration();
	// Context context = new Context();

	// @Inject
	// public JdaLoginPage(WebDriver webDriver, Configuration configuration) {
	// super(webDriver);
	// this.webDriver = webDriver;
	// this.configuration = configuration;
	// }
	@Inject
	public JdaLoginPage(Configuration configuration, Context context) {
		this.configuration = configuration;
		this.context = context;
	}

	public void login() throws FindFailed, InterruptedException {
		System.out.println("Login function");
		if (driver == null) {
			System.out.println("Driver is null");
			try {
				Process p = Runtime.getRuntime()
						.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
				p.waitFor(30, TimeUnit.SECONDS);
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("Site Id" + context.getSiteID());
			setDriver();
			driver.manage().window().maximize();
			Thread.sleep(5000);
			if (statusRegion == null) {
				statusRegion = "N";
			} else {
				System.out.println("DATABASE Status region---> " + statusRegion);
			}
			if (statusRegion.equalsIgnoreCase("N")) {
				if (region.equalsIgnoreCase("ST")) {
					if (context.getSiteID().equals("5649")) {
						driver.navigate().to(configuration.getStringProperty("st-wst-gm-jda-url"));
					}

					else if (context.getSiteID().equals("5885")) {
						driver.navigate().to(configuration.getStringProperty("st-stk-gm-jda-url"));
					} else {
						System.out.println("Site Id is not found");
						Assert.fail("Site Id is not found");
					}

				} else if (region.equalsIgnoreCase("SIT")) {
					if (context.getSiteID().equals("5649")) {
						driver.navigate().to(configuration.getStringProperty("sit-wst-gm-jda-url"));
					}

					else if (context.getSiteID().equals("5885")) {
						driver.navigate().to(configuration.getStringProperty("sit-stk-gm-jda-url"));
					} else {
						System.out.println("Site Id is not found");
						Assert.fail("Site Id is not found");
					}
				}
			}

			else {
				System.out.println("Get environment Details from NPS DB  " + "JDA GM URL:-" + context.getURL());
				if (context.getSiteID().equals("5649")) {
					driver.navigate().to(context.getURL());
				}

				else if (context.getSiteID().equals("5885")) {
					driver.navigate().to(context.getURL());
				} else {
					System.out.println("Site Id is not found");
					Assert.fail("Site Id is not found");
				}
			}

			int waitTime = 3;
			do {
				if (screen.exists("/images/JDALogin/username.png") != null) {
					break;
				} else {
					System.out.println("Login screen not found in loop");
				}

				Thread.sleep(3000);
				waitTime = waitTime + 3;
			} while (waitTime < 300);

			if (screen.exists("images/JDALogin/username.png") == null) {
				// Assert.fail("Login Not successful");
				if (screen.exists("images/JDALogin/JavaUpdateError.png") != null) {
					while (screen.exists("images/JDALogin/JavaUpdateError.png") != null) {

						screen.wait("images/JDALogin/DoNotAsk.png", timeoutInSec);
						screen.click("images/JDALogin/DoNotAsk.png");
						screen.wait("images/JDALogin/Later.png", timeoutInSec);
						screen.click("images/JDALogin/Later.png");

					}

				} else {
					Assert.fail("URL Not successful");
				}

			}

			enterUsername();
			enterPassword();
			clickConnectButton();
		}

		int waitTime = 3;
		do {
			if (screen.exists("/images/JDAHome/Welcomed.png") != null) {
				break;
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
		}
	}

	public static void setDriver() {
		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("screen-resolution", "1364*766");

		System.setProperty("webdriver.ie.driver", Constants.USER_DIR + "/bin/iedriver/x86/IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);

	}

	public void enterUsername() throws FindFailed, InterruptedException {
		int waitTime = 15;
		do {
			if (screen.exists("images/JDALogin/username.png") != null) {
				break;
			}
			Thread.sleep(15000);
			waitTime = waitTime + 15;
		} while (waitTime < 120);

		screen.wait("images/JDALogin/username.png", 20);
		screen.click("images/JDALogin/username.png", 25);
		screen.type(configuration.getStringProperty("username"));
	}

	public void enterPassword() throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(configuration.getStringProperty("password"));
		Thread.sleep(1000);
	}

	public void clickConnectButton() throws FindFailed, InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(15000);

		if (screen.exists("images/JDAHome/JDAHomePage.png") == null) {
			Assert.fail("Login Not successful");

			if (screen.exists("images/JDAHome/searchScreenButton.png") == null) {
				// Assert.fail("Login Not successful");
				if (screen.exists("images/JDALogin/JavaUpdateError.png") != null) {
					while (screen.exists("images/JDALogin/JavaUpdateError.png") != null) {

						screen.wait("images/JDALogin/DoNotAsk.png", timeoutInSec);
						screen.click("images/JDALogin/DoNotAsk.png");
						screen.wait("images/JDALogin/Later.png", timeoutInSec);
						screen.click("images/JDALogin/Later.png");

					}

				} else {

					Assert.fail("Login Not successful");
				}
			}
			context.setJdaLoginFlag(true);
		}
	}
}