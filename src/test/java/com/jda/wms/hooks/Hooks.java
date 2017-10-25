package com.jda.wms.hooks;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.DbReporting.UpdateRequestToAutomationDb;
import com.jda.wms.DbReporting.UpdateTcToAutomationDb;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.DataSetupRunner;
import com.jda.wms.datasetup.gm.DbConnection;
import com.jda.wms.db.gm.Database;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final WebDriver webDriver;
	Screen screen = new Screen();
	private Context context;
	String envVar = System.getProperty("user.dir");

	private DataSetupRunner dataSetupRunner;
	public static DbConnection NPSdataBase;
	static UpdateTcToAutomationDb updateTcToAutomationDb;
	static UpdateRequestToAutomationDb updateRequestToAutomationDb;
	private Database jdaJdatabase;

	@Inject
	public Hooks(WebDriver webDriver, Context context, DataSetupRunner dataSetupRunner, DbConnection dataBase,
			UpdateTcToAutomationDb updateTcToAutomationDb, UpdateRequestToAutomationDb updateRequestToAutomationDb,
			Database jdaJdatabase) {
		this.webDriver = webDriver;
		this.context = context;
		this.dataSetupRunner = dataSetupRunner;
		this.NPSdataBase = dataBase;
		this.updateRequestToAutomationDb = updateRequestToAutomationDb;
		this.updateTcToAutomationDb = updateTcToAutomationDb;
		this.jdaJdatabase = jdaJdatabase;

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

	// @Before
	public void updateAutoDBRequestStart(Scenario scenario) throws IOException {

		updateRequestToAutomationDb.updateRequestStartTime();

		updateRequestToAutomationDb.updateRequestTcCount();

		updateRequestToAutomationDb.updateRequestStatus("IN_PROGRESS");
	}

	@Before
	public void iniatateDataSetup(Scenario scenario) throws Exception {

		logger.debug(
				"###########################################################################################################################");
		logger.debug("Iniatate Data Setup ");
		logger.debug(
				"###########################################################################################################################");

		ArrayList<String> tagListForScenario = (ArrayList<String>) scenario.getSourceTagNames();
		//context.setSiteId(System.getProperty("SITEID"));
		 dataSetupRunner.getTagListFromAutoDb();
		 context.setSiteId("5885");
		// dataSetupRunner.getParentRequestIdFromDB();
		// dataSetupRunner.getJdaSiteIdFromDB();
		if (!(scenario.getName().contains("Triggering automation email"))) {
			dataSetupRunner.insertDataToJdaDB(tagListForScenario);
		}
	}

	// @Before
	public void updateAutoDBTcStart(Scenario scenario) throws IOException {

		updateTcToAutomationDb.updateTcStartTime();

		// updateTcToAutomationDb.updateTcStatus("IN_PROGRESS");

	}

	// @After()
	public void tearDown(Scenario scenario) {
		// attaching the screenshot in cucumber report
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		// clearing down webdriver object
		if (webDriver != null) {
			System.out.println("WEBDRIVER CLOSE");
			// webDriver.close();
			webDriver.quit();
		}
	}

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

	// @After
	public void killBrowser() throws IOException {

		// Process killIE = Runtime.getRuntime()
		// .exec("cmd /c taskkill /F /IM iexplore.exe /FI \"USERNAME eq
		// %username%\"");
		Process killChrome = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM chrome.exe /FI \"USERNAME eq %username%\"");
		Process killFirefox = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM firefox.exe /FI \"USERNAME eq %username%\"");

		Process killGeckoDriver = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM geckodriver.exe /FI \"USERNAME eq %username%\"");
		Process killChromeDriver = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM chromedriver.exe /FI \"USERNAME eq %username%\"");

		Process killIeDriver = Runtime.getRuntime()
				.exec("cmd /c taskkill /F /IM IEDriverServer.exe /FI \"USERNAME eq %username%\"");
	}

	@After
	public void closeDBConnection() throws SQLException {
		if (!context.getConnection().equals(null)) {
			context.getConnection().close();
			logger.debug("DB Connection closed");
		}
	}

	// @After
	public void clickSignoutButton() throws FindFailed {
		screen.wait("/images/JDAHeader/HeaderIcons.png", 20);
		screen.click("images/JDAHeader/Singout.png", 25);
		logger.debug("Signed off JDA WMS Application");
	}

	// @After
	public void updateAutoDBTcEnd(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			updateTcToAutomationDb.updateTcComments("Comments");
			updateTcToAutomationDb.updateTcStatus("FAIL");

		} else {
			updateTcToAutomationDb.updateTcStatus("PASS");
		}
		updateTcToAutomationDb.updateTcEndTime();
		updateTcToAutomationDb.updateTcTimeTaken();

		logger.debug(
				"###########################################################################################################################");
		logger.debug("End of Scenario: " + scenario.getName());
		logger.debug(
				"###########################################################################################################################");
	}

	// @After
	public void updateAutoDBRequestEnd() throws IOException {
		updateRequestToAutomationDb.updateRequestFailCountExcScripErrors();
		updateRequestToAutomationDb.updateRequestFailCountIncScripErrors();
		updateRequestToAutomationDb.updateRequestPassCount();
		updateRequestToAutomationDb.updateRequestEndTime();
		updateRequestToAutomationDb.updateRequestTimeTaken();
		updateRequestToAutomationDb.updateRequestStatus("Request Status");
	}

}