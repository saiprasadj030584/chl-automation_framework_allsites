package com.jda.wms.hooks;

import java.io.IOException;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.db.Exit.Database;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	// private final WebDriver webDriver;
	Screen screen = new Screen();
	private Context context;
	String envVar = System.getProperty("user.dir");
	private DataSetupRunner dataSetupRunner;
	private Database database;

	@Inject
	public Hooks(Context context, Database database) {
		// this.webDriver = webDriver;
		this.context = context;
		this.database = database;
	}

	@Before
	public void logScenarioDetails(Scenario scenario) throws Exception {
		String scenarioID = scenario.getId();
		String featureID = scenarioID.substring(0, scenarioID.lastIndexOf(";"));
		logger.debug(
				"###########################################################################################################################");
		logger.debug("featureID: " + featureID);
		logger.debug("Start of Scenario: " + scenario.getName());
		logger.debug(
				"###########################################################################################################################");
	}

	// @After()
	/*
	 * public void tearDown(Scenario scenario) { // attaching the screenshot in
	 * cucumber report if (scenario.isFailed()) { final byte[] screenshot =
	 * ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
	 * scenario.embed(screenshot, "image/png"); } // clearing down webdriver
	 * object if (webDriver != null) { webDriver.close(); webDriver.quit(); } }
	 */

	@After
	public void logoutPutty() throws FindFailed, InterruptedException, IOException {
		if (context.isPuttyLoginFlag() == true) {
			// context.getPuttyProcess().waitFor();
			while (screen.exists("/images/Putty/3Logout.png") == null) {
				screen.type(Key.F12);
			}
			screen.type("3");
			Thread.sleep(1000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);

			Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
			// //Process p = Runtime.getRuntime().exec("cmd /c
			// C:\\Users\\kiruthika.srinivasan\\Desktop\\puttykill_Admin.lnk");
			// p.waitFor();

			screen.type(Key.F4, Key.ALT);
			Thread.sleep(2000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			context.setPuttyLoginFlag(false);
			// screen.wait("images/Putty/PuttyClose.png", 20);
			// screen.click("images/Putty/PuttyClose.png", 25);
			// Thread.sleep(1000);

			// screen.wait("images/Putty/PuttyCloseOK.png", 20);
			// screen.click("images/Putty/PuttyCloseOK.png", 25);
			// Thread.sleep(1000);
		}
	}

	@After
	public void afterDetails(Scenario scenario) {
		logger.debug(
				"###########################################################################################################################");
		logger.debug("End of Scenario: " + scenario.getName());
		logger.debug(
				"###########################################################################################################################");
	}

	@After
	public void closeDBConnection() throws SQLException, ClassNotFoundException {
		if (context.getConnection() != null) {
			context.getConnection().close();
			database.connect();
			logger.debug("DB Connection closed");
			System.out.println("DB Connection closed");
		} else {
			System.out.println("DB Connection not closed");

		}
	}

	/*
	 * @After public void clickSignoutButton() throws FindFailed {
	 * screen.wait("/images/JDAHeader/HeaderIcons.png", 20);
	 * screen.click("images/JDAHeader/Singout.png", 25); logger.debug(
	 * "Signed off JDA WMS Application"); }
	 */

	// @Before
	public void iniatateDataSetup(Scenario scenario) throws Exception {

		logger.debug(
				"###########################################################################################################################");
		logger.debug("Iniatate Data Setup ");
		logger.debug(
				"###########################################################################################################################");

		dataSetupRunner.insertPreAdviceData();

	}
	
	@When("^I logout from putty$")
	public void i_logout_from_putty() throws FindFailed, InterruptedException, IOException {
		if (context.isPuttyLoginFlag() == true) {
			// context.getPuttyProcess().waitFor();
			while (screen.exists("/images/Putty/3Logout.png") == null) {
				screen.type(Key.F12);
			}
			screen.type("3");
			Thread.sleep(1000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);

			Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
			// //Process p = Runtime.getRuntime().exec("cmd /c
			// C:\\Users\\kiruthika.srinivasan\\Desktop\\puttykill_Admin.lnk");
			// p.waitFor();

			screen.type(Key.F4, Key.ALT);
			Thread.sleep(2000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			context.setPuttyLoginFlag(false);
			// screen.wait("images/Putty/PuttyClose.png", 20);
			// screen.click("images/Putty/PuttyClose.png", 25);
			// Thread.sleep(1000);

			// screen.wait("images/Putty/PuttyCloseOK.png", 20);
			// screen.click("images/Putty/PuttyCloseOK.png", 25);
			// Thread.sleep(1000);
		}
	}
	@After
	public void logoutJDA() throws FindFailed, InterruptedException, IOException {
		if (context.isJDALoginFlag() == true) {
			while (screen.exists("/images/JDAHome/JdaHomeLogout.png") != null) {
				screen.click("/images/JDAHome/JdaHomeLogout.png");
			}
	}
	}
}


