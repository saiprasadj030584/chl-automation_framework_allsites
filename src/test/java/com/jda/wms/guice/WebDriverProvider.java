package com.jda.wms.guice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jda.wms.config.Configuration;

public class WebDriverProvider implements Provider<WebDriver> { //implements Provider<WebDriver>

	private final Configuration configuration;

	@Inject
	public WebDriverProvider(Configuration configuration) {
		this.configuration = configuration;
	}

	private WebDriver getWebDriver() {
		/*String browserName = configuration.getStringProperty("browser-name");
		switch (browserName) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver", Constants.USER_DIR + "/bin/chromedriver/chromedriver.exe");
			return new ChromeDriver();

		case "ie":
			DesiredCapabilities capabilities = null;
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("screen-resolution", "1364*768");
			System.setProperty("webdriver.ie.driver", Constants.USER_DIR + "/bin/iedriver/x86/IEDriverServer.exe");
			return new InternetExplorerDriver();

		case "firefox":
			System.setProperty("webdriver.firefox.marionette",
					Constants.USER_DIR + "/bin/geckodriver/x86/geckodriver.exe");
			return new FirefoxDriver();

		default:
			System.setProperty("webdriver.chrome.driver", Constants.USER_DIR + "/bin/chromedriver/chromedriver.exe");
			return new ChromeDriver();
		}*/ 
		return new InternetExplorerDriver();
	}

	public WebDriver get() {
		return getWebDriver();
	}

}
