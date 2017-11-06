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


import com.jda.wms.config.Configuration;
import com.jda.wms.config.Constants;
import com.jda.wms.context.Context;


public class JdaLoginPage {
	WebElement webElement;

	Screen screen = new Screen();
	int timeoutInSec = 20;

	public static RemoteWebDriver driver;
	Configuration configuration = new Configuration();
	Context context = new Context();

	// @Inject
	// public JdaLoginPage(WebDriver webDriver, Configuration configuration) {
	// super(webDriver);
	// this.webDriver = webDriver;
	// this.configuration = configuration;
	// }

	public JdaLoginPage() {
	}


		
	public void login() throws FindFailed, InterruptedException {

		if (driver == null) {

			try {
				Process p = Runtime.getRuntime()
						.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
				p.waitFor(30, TimeUnit.SECONDS);
			} catch (IOException e) {

				e.printStackTrace();
			}

			setDriver();
			driver.manage().window().maximize();

			driver.navigate().to(configuration.getStringProperty("gm-jda-url")); 
			Thread.sleep(30000);
			
			if (screen.exists("images/JDALogin/username.png") == null) {
				//Assert.fail("Login Not successful");
					if(screen.exists("images/JDALogin/JavaUpdateError.png")!=null)
					{
					while(screen.exists("images/JDALogin/JavaUpdateError.png")!=null)
					{
						
						screen.wait("images/JDALogin/DoNotAsk.png", timeoutInSec);
						screen.click("images/JDALogin/DoNotAsk.png");
						screen.wait("images/JDALogin/Later.png", timeoutInSec);
						screen.click("images/JDALogin/Later.png");
						
					}
					
					}
					else
					{
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

	private void enterUsername() throws FindFailed, InterruptedException {
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

	private void enterPassword() throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(configuration.getStringProperty("password"));
		Thread.sleep(1000);
	}

	private void clickConnectButton() throws FindFailed, InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(15000);

		if (screen.exists("images/JDAHome/JDAHomePage.png") == null) {
			Assert.fail("Login Not successful");

		if (screen.exists("images/JDAHome/searchScreenButton.png") == null) {
		//Assert.fail("Login Not successful");
			if(screen.exists("images/JDALogin/JavaUpdateError.png")!=null)
			{
			while(screen.exists("images/JDALogin/JavaUpdateError.png")!=null)
			{
				
				screen.wait("images/JDALogin/DoNotAsk.png", timeoutInSec);
				screen.click("images/JDALogin/DoNotAsk.png");
				screen.wait("images/JDALogin/Later.png", timeoutInSec);
				screen.click("images/JDALogin/Later.png");
				
			}
			
			}
			else
			{
			
				Assert.fail("Login Not successful");
			}
		}
		context.setJdaLoginFlag(true);
	}
}
}