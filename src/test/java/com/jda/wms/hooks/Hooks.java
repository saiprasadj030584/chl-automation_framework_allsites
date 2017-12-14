package com.jda.wms.hooks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.DataSetupRunner;
import com.jda.wms.datasetup.gm.DbConnection;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.Database;
import com.jda.wms.pages.gm.JdaLoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	Screen screen = new Screen();
	private Context context;
	private final Configuration configuration;
	private final JdaLoginPage jdaLoginPage;
	public static String PRQID = System.getProperty("ID");
	public static String BUILD_NUM = System.getProperty("BUILD_NUM");
	String envVar = System.getProperty("user.dir");
	private DataSetupRunner dataSetupRunner;
	public static DbConnection NPSdataBase;
	public static String statusRegion = System.getProperty("USE_DB");
	public static String region = System.getProperty("REGION");
//	public static String region = "ST";
	private Database jdaJdatabase;
	private GetTcData gettcdata;

	@Inject
	public Hooks(Context context, DataSetupRunner dataSetupRunner, DbConnection dataBase, Database jdaJdatabase,
			GetTcData gettcdata, Configuration configuration,JdaLoginPage jdaLoginPage) {

		this.context = context;
		this.dataSetupRunner = dataSetupRunner;
		this.NPSdataBase = dataBase;
		this.jdaJdatabase = jdaJdatabase;
		this.gettcdata = gettcdata;
		this.configuration = configuration;
		this.jdaLoginPage = jdaLoginPage;
	}

	// @Before
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

	@Before({ "~@Email" })

	public void iniatateDataSetup(Scenario scenario) throws Exception {
		context.setScenario(scenario);
		System.out.println(
				"*************" + context.getScenario().getId() + "-----------" + context.getScenario().getName());
		System.out.println("1st Before start");
		ArrayList<String> tagListForScenario = (ArrayList<String>) scenario.getSourceTagNames();
		System.out.println("Uniq Tag --->" + tagListForScenario);
		System.out.println("Starting Execution" + scenario.getName()); 
		if (statusRegion != null) {
			getAppInventoryDetails(region, "Foods");
		} else {
			System.out.println("Environment details get from config file");
		}
		getParentRequestID();
		System.out.println("PREQ_ID " + context.getParentRequestId());
		insertDetails(scenario.getName());
		System.out.println("tagListForScenario" + tagListForScenario);
		for (String tag : tagListForScenario) {
			System.out.println("TAG" + tag);
			if (tag.contains("@ds")) {

				dataSetupRunner.getTagListFromAutoDb();

				if (!(scenario.getName().contains("Triggering automation email"))) {
					System.out.println("Datasetup Started");
					dataSetupRunner.insertDataToJdaDB(tagListForScenario);
					System.out.println("Datasetup completed");
				} else {
					System.out.println("Datasetup not require for email scenario");
				}
				System.out.println("DS" + context.getTestData());
			}
			System.out.println("NO DS");
		}
		System.out.println("1st Before end");
	}

	/*
	 * private void getSiteID() throws ClassNotFoundException { try { if
	 * (context.getSQLDBConnection() == null) { hooksautoUI.sqlConnectOpen(); }
	 * Statement stmt = null; stmt =
	 * context.getSQLDBConnection().createStatement(); System.out.println(
	 * "SELECT SITE_ID FROM [dbo].[JDA_SITE_ID] where P_REQ_ID='" +
	 * context.getParentRequestId() + "'"); String query =
	 * "SELECT SITE_ID FROM [dbo].[JDA_SITE_ID] where P_REQ_ID='" +
	 * context.getParentRequestId() + "'"; ResultSet rs =
	 * stmt.executeQuery(query);
	 * 
	 * while (rs.next()) { context.setSiteID(rs.getString("SITE_ID"));
	 * System.out.println("" + context.getSiteID()); } } catch (SQLException e)
	 * { e.printStackTrace(); } }
	 * 
	 * private void insertSiteID() { try { System.out.println(
	 * "INSERT INTO JDA_SITE_ID (P_REQ_ID,SITE_ID) VALUES ('" +
	 * context.getParentRequestId() + "','" + context.getSiteID() + "')");
	 * String insertQuery =
	 * "INSERT INTO JDA_SITE_ID (P_REQ_ID,SITE_ID) VALUES ('" +
	 * context.getParentRequestId() + "','" + context.getSiteID() + "')";
	 * context.getSQLDBConnection().createStatement().execute(insertQuery);
	 * 
	 * } catch (Exception exception) { exception.printStackTrace(); } }
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
		Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
		p.waitFor();
		System.out.println("Kill Completed");
	}  
	
	public void getAppInventoryDetails(String region, String cluster) {
		ResultSet resultSet = null;
		try {
			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}
			resultSet = context.getSQLDBConnection().createStatement().executeQuery(
					"Select * from APP_INVENTORY Where environment = '" + region + "' and cluster = 'GM'");
			while (resultSet.next()) {
				String url = resultSet.getString("url");
				String siteId = resultSet.getString("site_id");
				String puttyHost = resultSet.getString("putty_host");
				String puttyPort = resultSet.getString("putty_port");
				String appUsername = resultSet.getString("app_username");
				String appPassword = resultSet.getString("app_pwd");
				String dBHost = resultSet.getString("db_host");
				String dBUsername = resultSet.getString("db_username");
				String dBPassword = resultSet.getString("db_pwd");
				context.setURL(url);
				context.setSiteId(siteId);
				context.setPuttyHost(puttyHost);
				context.setPuttyPort(puttyPort);
				context.setAppUserName(appUsername);
				context.setAppPassord(appPassword);
				context.setDBHost(dBHost);
				context.setDBUserName(dBUsername);
				context.setDBPassword(dBPassword);

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	private void updateTestDataIntoRunStatusTable(String tcName) {
		try {
			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}
			System.out.println(
					"update dbo.NPS_AUTO_UI_RUN_STATUS set TEST_DATA ='" + context.getTestData() + "' WHERE P_REQ_ID='"
							+ context.getParentRequestId() + "' and status='INPROGRESS' and TC_NAME='" + tcName + "'");
			String query = "update dbo.NPS_AUTO_UI_RUN_STATUS set TEST_DATA ='" + context.getTestData()
					+ "' WHERE P_REQ_ID='" + context.getParentRequestId() + "' and status='INPROGRESS' and TC_NAME='"
					+ tcName + "'";
			context.getSQLDBConnection().createStatement().execute(query);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void getChildRequestID() {
		try {
			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}
			Statement stmt = null;
			stmt = context.getSQLDBConnection().createStatement();

			String selectQuery = "select C_REQ_ID from NPS_AUTO_UI_RUN_REQUEST where P_REQ_ID='"
					+ context.getParentRequestId() + "' and STATUS='INPROGRESS'";
			System.out.println(selectQuery);
			context.getSQLDBConnection().createStatement().execute(selectQuery);
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				context.setChildRequestId(rs.getString("C_REQ_ID"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void updateBuildNumberInRequestTable() {
		try {
			String insertQuery = "UPDATE NPS_AUTO_UI_RUN_REQUEST set JENKINS_BUILD_NO='" + BUILD_NUM
					+ "' where P_REQ_ID='" + context.getParentRequestId() + "'";
			System.out.println(insertQuery);
			context.getSQLDBConnection().createStatement().execute(insertQuery);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@After("~@Email")
	public void tearDown(Scenario scenario) throws IOException {
		// attaching the screenshot in cucumber report
		
		System.out.println("After class----> Count" + scenario.getId());
		if (scenario.isFailed()) {
			System.out.println(context.getTestData());
			System.out.println("After class----> FAIL" + scenario.isFailed());
			System.out.println("*****************" + context.getTestData());
			if (screen.exists("images/EJBError.png") != null){
//				System.out.println("EJB Error Found - Application issue . please check with Non Prod Team and Try again");
				context.setErrorMessage("EJB Error Found - Application issue . please check with Non Prod Team and Try again");
			}
			updateExecutionStatusInAutomationDb_End("FAIL", scenario.getName());
			updateParentTable();
			System.out.println("Entering teardown if scenario is failed");
			try {

				if (jdaLoginPage.driver != null) {
					final byte[] screenshot = ((TakesScreenshot) jdaLoginPage.driver).getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenshot, "image/png");
				}
			} catch (WebDriverException e) {

				if (!(jdaLoginPage.driver instanceof TakesScreenshot)) {
					logger.error(
							"Could not capture screenshot - selected web driver does not support taking screenshots");
					return;
				}
				e.printStackTrace();
			}
		}

		else {
			try {
				System.out.println("After class----> PASS" + scenario.isFailed());
				updateExecutionStatusInAutomationDb_End("PASS", scenario.getName());
				updateParentTable();

				// final byte[] screenshot = ((TakesScreenshot)
				// webDriver).getScreenshotAs(OutputType.BYTES);
				// scenario.embed(screenshot, "image/png");
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block
				if (!(jdaLoginPage.driver instanceof TakesScreenshot)) {
					logger.error(
							"Could not capture screenshot - selected web driver does not support taking screenshots");
					return;
				}
			}
		}
	}


	public void insertDetails(String testName) {
		try {
			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}

			String startTime = getSystemTime();
			context.setChildStartTime(startTime);
			fileReadValueFromText();
			System.out.println("Parent Id --->" + context.getParentRequestId());
			String query = "INSERT INTO [dbo].[NPS_AUTO_UI_RUN_STATUS] ([P_REQ_ID],[TC_NAME],[EXEC_START_DATE_TIME],[STATUS])VALUES ('"
					+ context.getParentRequestId() + "','" + testName + "','" + startTime + "','INPROGRESS')";

			System.out.println("Insert Query" + query);
			context.getSQLDBConnection().createStatement().execute(query);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void sqlConnectOpen() throws ClassNotFoundException {
		boolean connectionSucessful = false;
		try {

			String host = configuration.getStringProperty("sqlDB-host");
			String userName = configuration.getStringProperty("sqlDB-username");
			String password = configuration.getStringProperty("sqlDB-password");
			String database = configuration.getStringProperty("sqlDB-databaseName");

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connectionMain = DriverManager.getConnection(
					host + " ;user=" + userName + ";password=" + password + ";database=" + database + "");

			connectionMain.setAutoCommit(true);
			context.setSQLDBConnection(connectionMain);
			connectionSucessful = true;

		} catch (SQLException ex) {
			System.out.println("Exception " + ex.getMessage());
		}
	}

	public void getParentRequestID() throws ClassNotFoundException, IOException, SQLException {
		if (PRQID == null) {
			PRQID = "0";
		}
		context.setParentRequestId(PRQID);
		if (context.getParentRequestId().equals("0")) {
			Statement stmt = null;

			try {
				if (context.getSQLDBConnection() == null) {
					sqlConnectOpen();
				}

				insertReqID();
				String projName = configuration.getStringProperty("tbl-parentprojectname");
				stmt = context.getSQLDBConnection().createStatement();
				String query = "SELECT top 1 (P_REQ_ID) FROM [dbo].[NPS_AUTO_UI_RUN_REQUEST] where Project_Name = '"
						+ projName + "' Order by [P_REQ_ID] desc";
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					PRQID = rs.getString("P_REQ_ID");
					System.setProperty("ID", PRQID);
				}
				fileSaveValueInText();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			context.setParentRequestId(PRQID);
			System.setProperty("ID", PRQID);
			// parentStartTime();
			fileSaveValueInText();
		}

	}

	public void parentStartTime() throws ClassNotFoundException, SQLException {
		if (context.getSQLDBConnection() == null) {
			sqlConnectOpen();
		}
		String updateStartTime = getSystemTime();
		String updateQuery = "Update NPS_AUTO_UI_RUN_REQUEST set EXEC_START_DATE_TIME = '" + updateStartTime
				+ "' where P_REQ_ID = '" + context.getParentRequestId() + "'";
		context.getSQLDBConnection().createStatement().execute(updateQuery);
		String parentStTime = System.setProperty("ParentStartTime", updateStartTime);

	}

	public void updateParentTable() {
		try {

			String passCount = getStatusCount(context.getParentRequestId(), "PASS");
			String failCount = getStatusCount(context.getParentRequestId(), "FAIL");
			System.out.println("Value Of Pass" + passCount + ",Value Of fail " + failCount);

			String totalTestCaseCount = Integer.toString(Integer.valueOf(failCount) + Integer.valueOf(passCount));

			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}
			// String parentStTime = System.getProperty("ParentStartTime");
			String parentStTime = getParentStartTime();
			String totalTime = getTimeDifference(parentStTime, getSystemTime());
			String updateParent = "UPDATE DBO.NPS_AUTO_UI_RUN_REQUEST SET TOTAL_AUTO_TC_EXECUTED = '"
					+ totalTestCaseCount + "', TOTAL_NO_PASS = '" + passCount + "',TOTAL_NO_FAIL = '" + failCount
					+ "', EXEC_END_DATE_TIME='" + getSystemTime() + "', OVERALL_STATUS= 'COMPLETE',TOTAL_EXEC_TIME = '"
					+ totalTime + "' where P_REQ_ID=" + context.getParentRequestId() + " ";

			context.getSQLDBConnection().createStatement().execute(updateParent);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public String getParentStartTime() throws ClassNotFoundException, SQLException {
		if (context.getSQLDBConnection() == null) {
			sqlConnectOpen();
		}
		Statement stmt = context.getSQLDBConnection().createStatement();
		String query = "SELECT EXEC_START_DATE_TIME FROM NPS_AUTO_UI_RUN_REQUEST where P_REQ_ID = '"
				+ context.getParentRequestId() + "'";
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		return rs.getString(1);
	}

	private String getStatusCount(String parentRequestId, String status) {
		String Count = null;
		ResultSet resultSet = null;
		try {
			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}
			resultSet = context.getSQLDBConnection().createStatement()
					.executeQuery("select count(*) as COUNT from dbo.NPS_AUTO_UI_RUN_STATUS where P_REQ_ID='"
							+ context.getParentRequestId() + "' AND STATUS='" + status + "'");
			while (resultSet.next()) {
				Count = resultSet.getString("COUNT");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return Count;
	}

	public void insertReqID() {

		try {

			String cluster = configuration.getStringProperty("tbl-parentcluster");
			String projectCode = configuration.getStringProperty("tbl-parentprojectcode");
			String projectName = configuration.getStringProperty("tbl-parentprojectname");
			String automationSuiteName = configuration.getStringProperty("tbl-parentautomationsuitname");
			String region = configuration.getStringProperty("tbl-parentregion");
			String testingPhase = configuration.getStringProperty("tbl-testingPhase");
			String appDetails = configuration.getStringProperty("url");

			String requestedBy = configuration.getStringProperty("tbl-RequestBy");
			String requestedMail = configuration.getStringProperty("tbl-RequestedEmail");
			String parentStartTime = getSystemTime();
			System.setProperty("ParentStartTime", parentStartTime);

			String insertQuery = "INSERT INTO NPS_Auto_UI_Run_Request (CLUSTER,PROJECT_CODE,PROJECT_NAME,AUTOMATION_SUITE_NAME,REGION,EXEC_START_DATE_TIME,OVERALL_STATUS,REQUESTED_BY,EMAIL_LIST,TESTING_PHASE_fk,APP_DETAILS) VALUES ( '"
					+ cluster + "','" + projectCode + "','" + projectName + "','" + automationSuiteName + "','" + region
					+ "', '" + parentStartTime + "' ,'IN PROGRESS','" + requestedBy + "','" + requestedMail + "','"
					+ testingPhase + "','" + appDetails + "')";
			context.getSQLDBConnection().createStatement().execute(insertQuery);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void fileReadValueFromText() throws IOException {
		FileInputStream fis = new FileInputStream("files/ParentRequestId.txt");
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String data;
		String result = new String();

		while ((data = br.readLine()) != null) {
			result = result.concat(data);
		}

		String parentReqId = result;
		context.setParentRequestId(String.valueOf(parentReqId));
		System.out.println(result);
		fis.close();

	}

	public void fileSaveValueInText() throws IOException {
		String fileName = "files/ParentRequestId.txt";

		try {

			// String parentReqId = getParentReqId();
			String parentReqId = System.getProperty("ID");
			System.out.println("File save value" + parentReqId);
			byte[] buffer = parentReqId.getBytes();
			FileOutputStream outputStream = new FileOutputStream(fileName);
			outputStream.write(buffer);
			outputStream.close();
			context.setParentRequestId(parentReqId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getParentReqId() throws ClassNotFoundException, SQLException {
		Statement stmt = null;
		if (context.getSQLDBConnection() == null) {
			sqlConnectOpen();
		}
		stmt = context.getSQLDBConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT top 1 (P_REQ_ID) FROM [dbo].[NPS_AUTO_UI_RUN_REQUEST] Order by [P_REQ_ID] desc");
		rs.next();
		return rs.getString(1);

	}

	public void updateExecutionStatusInAutomationDb_End(String status, String tagName) {

		try {
			if (context.getSQLDBConnection() == null) {
				sqlConnectOpen();
			}
			String totalTime = getTimeDifference(context.getChildStartTime(), getSystemTime());
			System.out.println("UPDATE DBO.Nps_Auto_UI_Run_Status SET EXEC_END_DATE_TIME='" + getSystemTime()
					+ "', STATUS= '" + status + "',TOTAL_TIME = '" + totalTime + "',REMARKS='NA' where P_REQ_ID= '"
					+ context.getParentRequestId() + "' and TC_NAME='" + tagName + "' and STATUS = 'INPROGRESS'");
			String updateQuery = "UPDATE DBO.Nps_Auto_UI_Run_Status SET EXEC_END_DATE_TIME='" + getSystemTime()
					+ "', STATUS= '" + status + "',TOTAL_TIME = '" + totalTime + "',REMARKS= '"
					+ context.getErrorMessage() + "',TEST_DATA ='" + context.getTestData() + "' where P_REQ_ID= '"
					+ context.getParentRequestId() + "' and TC_NAME='" + tagName + "' and STATUS = 'INPROGRESS'";

			context.getSQLDBConnection().createStatement().execute(updateQuery);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public String getTimeDifference(String startTime, String endTime) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format1.parse(startTime);
			d2 = format1.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		diffHours += diffDays * 24;
		return diffHours + ":" + diffMinutes + ":" + diffSeconds;
	}

	public static String getSystemTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("systemTime: " + dateFormat.format(date));
		return dateFormat.format(date);
	}

	@After
	public void closeDBConnection() throws SQLException {
		// if (!context.getConnection().equals(null)) {
		if (!(null == context.getConnection())) {
			context.getConnection().close();
			logger.debug("DB Connection closed");
		}
	}

}