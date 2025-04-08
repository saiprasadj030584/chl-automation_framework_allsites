package com.mns.auto.cd.guice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class WebDriverProvider implements Provider<WebDriver> {

//	private final Configuration configuration;
//	private static WebDriver driver;

	@Inject
	public WebDriverProvider( ) {
		 
	}

	@SuppressWarnings("deprecation")
	private WebDriver getWebDriver() {/*
		String browserName = configuration.getStringProperty("browser-name");
		String remoteHubURL = configuration.getStringProperty("remoteHubURL");
		
		switch (browserName) {

		case "chrome":
			
			DesiredCapabilities chro = DesiredCapabilities.chrome();
			chro.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
					UnexpectedAlertBehaviour.ACCEPT);
			chro.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
			chro.setCapability(CapabilityType.HAS_NATIVE_EVENTS,true);
			chro.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,true);
			chro.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
			chro.setCapability(CapabilityType.SUPPORTS_ALERTS,true);
			chro.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);
			
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			options.addArguments("disable-infobars");
			options.addArguments("--start-maximized");
			chro.setCapability(ChromeOptions.CAPABILITY, options);
            System.setProperty("webdriver.chrome.driver", Constants.USER_DIR + "/src/test/resources/driver/chromedriver/chromedriver.exe");
            return new ChromeDriver(chro);

		case "ie":
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
			desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			desiredCapabilities.setCapability("nativeEvents", false);
			System.setProperty("webdriver.ie.driver", Constants.USER_DIR + "/src/test/resources/driver/iedriver/x64/IEDriverServer.exe");
			return new InternetExplorerDriver();

		case "firefox":
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			File pathToBinary = new File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			System.setProperty("webdriver.gecko.driver",Constants.USER_DIR + "/src/test/resources/driver/geckodriver/x64/geckodriver.exe");       
			return new FirefoxDriver();
			
		
			
          case "phantomjs":
			
			System.out
			.println("---------------: Inside Phantom profile :--------------");
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);                
			caps.setCapability("takesScreenshot", true);  
			caps.setCapability(
			                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
			                        Constants.USER_DIR + "/bin/phantomjsdriver/phantomjs.exe"
			                    );
			WebDriver phantomDriver = new  PhantomJSDriver(caps);
			
			 System.setProperty("phantomjs.binary.path", Constants.USER_DIR + "src/test/resources/driver/phantomjsdriver/phantomjs.exe");
			 WebDriver phantomDriver = new  PhantomJSDriver();
			System.out
			.println("==========:::: got Phantom driver ::::===========");
			return phantomDriver;
			
          case "chrome_Remote":
			
			DesiredCapabilities chro_rmt = DesiredCapabilities.chrome();
			chro_rmt.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
					UnexpectedAlertBehaviour.ACCEPT);
			chro_rmt.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
			chro_rmt.setCapability(CapabilityType.HAS_NATIVE_EVENTS,true);
			chro_rmt.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,true);
			chro_rmt.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
			chro_rmt.setCapability(CapabilityType.SUPPORTS_ALERTS,true);
			chro_rmt.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);
			
			
			ChromeOptions options_rmt = new ChromeOptions();
			options_rmt.addArguments("--test-type");
			chro_rmt.setCapability(ChromeOptions.CAPABILITY, options_rmt);
			
            System.setProperty("webdriver.chrome.driver", Constants.USER_DIR + "/src/test/resources/driver/chromedriver/chromedriver.exe");
            
            try {
				driver = new RemoteWebDriver(new URL(remoteHubURL),chro_rmt);
				driver = new Augmenter().augment(driver);
				System.out.println("==========:::: got remote Chrome driver ::::===========");
				return driver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
          case "sauce":
        	  
        	  driver = new RemoteWebDriver(getRemoteURL(), capabilities());
  			return driver;

		default:
			System.setProperty("webdriver.chrome.driver", Constants.USER_DIR + "src/test/resources/driver/chromedriver/chromedriver.exe");
			return new ChromeDriver();*/ 
		
		return new InternetExplorerDriver();
		
	}

	public WebDriver get() {
		return getWebDriver();
	} 
	
	/*private URL getRemoteURL() {
		try {
			return new URL("https://" + configuration.getStringProperty("sauce.username") + ":"
					+ configuration.getStringProperty("sauce.accesskey") + "@ondemand.saucelabs.com:443/wd/hub");
		} catch (MalformedURLException e) {
			throw new WebDriverException(e);
		}

	}
	
	private DesiredCapabilities capabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platform", configuration.getStringProperty("platform"));
		capabilities.setCapability("browserName", configuration.getStringProperty("browser"));
//		capabilities.setCapability("version", configuration.getStringProperty("version"));
		return capabilities;
	}*/

}

