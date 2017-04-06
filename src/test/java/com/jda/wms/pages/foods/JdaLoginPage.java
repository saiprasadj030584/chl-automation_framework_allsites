package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;
import com.jda.wms.stepdefs.foods.ScreenCaptureStepDefs;

public class JdaLoginPage extends PageObject {

	WebElement webElement;
	// WebDriverWait wait = new WebDriverWait(driver, 90);
	private final WebDriver webDriver;
	Screen screen = new Screen();

	// public static final String environment = getData("Environment");
	public static final String environment = "http://hlxc0dc023.unix.marksandspencercate.com:8880/wmsbac2/UserLogin.html";
	// public static final String environment = "https://www.google.co.in/";

	@Inject
	public JdaLoginPage(WebDriver webDriver, OrderHeaderPage orderHeaderPage,
			ScreenCaptureStepDefs screenCaptureStepDefs) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void login() {

		try {
			webDriver.manage().window().maximize();
			webDriver.navigate().to(environment);
			try {
				Thread.sleep(30000);
			} catch (Exception e) {
			}

			// waitForImage("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Use.png",
			// 20);
			screen.wait("Constants.IMAGE_PATH\\Use.png", 100);
			screen.click("Constants.IMAGE_PATH\\\\Use.png", 10);
			screen.type("P9124783");
			screen.wait("Constants.IMAGE_PATH\\PWD.png", 10);
			screen.click("Constants.IMAGE_PATH\\PWD.png", 10);
			screen.type("12345");
			screen.wait("Constants.IMAGE_PATH\\Submit.png", 10);
			screen.click("Constants.IMAGE_PATH\\Submit.png", 10);

			// screen.wait("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Use.png",
			// 100);
			// screen.click("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Use.png",
			// 10);
			// screen.type("P9124783");
			// screen.wait("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\PWD.png",
			// 10);
			// screen.click("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\PWD.png",
			// 10);
			// screen.ype("12345");
			// screen.wait("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Submit.png",
			// 10);
			// screen.click("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Submit.png",
			// 10);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void waitForImage(String image, int time) throws InterruptedException {
		for (int i = 0; i < time; i++) {
			if (isImagePresent(image)) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}

	public boolean isImagePresent(String image) {
		boolean status = false;
		try {
			status = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
