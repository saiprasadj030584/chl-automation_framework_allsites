package com.jda.wms.stepdefs.foods;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JdaLoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;

public class JDAHomeStepDefs {
	public Scenario myScenario;
	private WebDriver webDriver;
	private ScreenCaptureStepDefs Screenshot;
	private final JdaLoginPage jdaHomePage;

	// @Before()
	// public void embedScreenshotStep(Scenario scenario) {
	//
	// myScenario = scenario;
	// }

	@Inject
	public JDAHomeStepDefs(WebDriver webDriver, ScreenCaptureStepDefs screenCaptureStepDefs, JdaLoginPage jdaHomePage) {
		this.webDriver = webDriver;
		this.Screenshot = screenCaptureStepDefs;
		this.jdaHomePage = jdaHomePage;
	}

	@Given("^user is in JDA Dispatcher Food application$")
	public void user_is_in_JDA_Dispatcher_Food_application() throws Throwable {

		try {

			jdaHomePage.login();
			Thread.sleep(5000);
			Screenshot.screenshotcapture();
			String filePath = "C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img_New.png";
			Path path = Paths.get(filePath);
			myScenario.embed(Files.readAllBytes(path), "image/png");
		} catch (WebDriverException WDE) {
			myScenario.write("Embed Failed " + WDE.getMessage());
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
	}

	// @After
	public void tearDown() throws Throwable {
		try {
			if (myScenario.isFailed()) {
				Screenshot.screenshotcapture();
				String filePath = "C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img_New.png";
				Path path = Paths.get(filePath);
				myScenario.embed(Files.readAllBytes(path), "image/png");
			}
		} catch (WebDriverException WDE) {
			myScenario.write("Embed Failed " + WDE.getMessage());
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}

	}
}
