package com.mns.auto.cd.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.config.Constants;
import com.mns.auto.cd.memory.KeepInMemory;;

public class CDPage {

	int timeoutInSec = 20;

	public static RemoteWebDriver driver;
	private Configuration configuration;
	private KeepInMemory keepInMemory;
	public static String statusRegion = System.getProperty("USE_DB");
	public static String region = "ST";

	@Inject
	public CDPage(Configuration configuration, KeepInMemory keepInMemory) {
		this.configuration = configuration;
		this.keepInMemory = keepInMemory;
	}

	public void login() throws InterruptedException {
		if (driver == null) {
			try {
				killBrowser();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setDriver() {
		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("screen-resolution", "1364*766");
		System.setProperty("webdriver.ie.driver",
				Constants.USER_DIR + "/src/test/resources/driver/iedriver/x64/IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);

	}

	public void killBrowser() throws IOException, InterruptedException {
		String browser = configuration.getStringProperty("browser-name");
		Process p = null;
		if (browser.equals("ie")) {
			p = Runtime.getRuntime().exec("cmd /c taskkill /F /FI \"USERNAME eq %username%\" /IM ie*");
		} else if (browser.equals("chrome")) {
			p = Runtime.getRuntime().exec("cmd /c taskkill /F /FI \"USERNAME eq %username%\" /IM chrome*");
		}
		p.waitFor(30, TimeUnit.SECONDS);
	}

}
