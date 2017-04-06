package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.config.Constants;
import com.jda.wms.pages.PageObject;
import com.jda.wms.stepdefs.foods.ScreenCaptureStepDefs;

public class JdaLoginPage extends PageObject {

	WebElement webElement;
	private final WebDriver webDriver;
	Screen screen = new Screen();

	public static final String environment = "http://hlxc0dc023.unix.marksandspencercate.com:8880/wmsbac2/UserLogin.html";

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
			 /*try {
			 Thread.sleep(60000);
			 } catch (Exception e) {
			 }*/
			Thread.sleep(30000);
			screen.wait("images/Use.png",100);
			screen.click("images/Use.png",25);
			screen.type("P9124783");
			
			screen.wait("images/PWD.png",100);
			screen.click("images/PWD.png",25);
			screen.type("12345"); 
			
			screen.wait("images/Submit.png",100);
			screen.click("images/Submit.png",25); 
			/*screen.wait(
					"C:\\Users\\Santhaseelan.Shanmug\\JDA Project\\jda-wms-test-framework-master\\jda-wms-test-framework-master\\images\\Use.png",
					100);
			screen.click(
					"C:\\Users\\Santhaseelan.Shanmug\\JDA Project\\jda-wms-test-framework-master\\jda-wms-test-framework-master\\images\\Use.png",
					25);
			screen.type("P9124783");
			screen.wait(
					"C:\\Users\\Santhaseelan.Shanmug\\JDA Project\\jda-wms-test-framework-master\\jda-wms-test-framework-master\\images\\PWD.png",
					10);
			screen.click(
					"C:\\Users\\Santhaseelan.Shanmug\\JDA Project\\jda-wms-test-framework-master\\jda-wms-test-framework-master\\images\\PWD.png",
					10);
			screen.type("12345");
			screen.wait(
					"C:\\Users\\Santhaseelan.Shanmug\\JDA Project\\jda-wms-test-framework-master\\jda-wms-test-framework-master\\images\\Submit.png",
					10);
			screen.click(
					"C:\\Users\\Santhaseelan.Shanmug\\JDA Project\\jda-wms-test-framework-master\\jda-wms-test-framework-master\\images\\Submit.png",
					10);*/

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