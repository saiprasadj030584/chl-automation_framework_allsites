package com.mns.auto.cd.hooks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.config.Constants;
import com.mns.auto.cd.database.Database;
import com.mns.auto.cd.memory.KeepInMemory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	public static String PRQID = System.getProperty("ID");
	private KeepInMemory keepInMemory;
	public static int pass = 0;
	public static int fail = 0;
	private Database database;
	public static String useDBForData = System.getProperty("USE_DB");
	public static String region = System.getProperty("REGION");
	public static String BUILD_NUM = System.getProperty("BUILD_NUMBER");
	public String metricsDB = System.getProperty("METRICSDB");
	public static String getServerPingStatus = null;
	Screen screen = new Screen();
	public static RemoteWebDriver driver;
	private Configuration configuration;

	@Inject
	public Hooks(KeepInMemory keepInMemory, Configuration configuration, Database database) {

		this.keepInMemory = keepInMemory;
		this.configuration = configuration;
		this.database = database;

	}

	@Before()
	public void setup(Scenario scenario)
			throws ClassNotFoundException, IOException, SQLException, InterruptedException, FindFailed {
		System.out.println("Starting Execution : " + scenario.getName());
		keepInMemory.setScenario(scenario);
		//login();

	}

	@SuppressWarnings("unused")
	public void login() throws InterruptedException, FindFailed, IOException {
		System.out.println("Login function");
		Process p1 = Runtime.getRuntime().exec("cmd /c C:\\Automation\\BatFiles\\ExcelKillAdmin.lnk");
		p1.waitFor(30, TimeUnit.SECONDS);
		Process p2 = Runtime.getRuntime().exec("cmd /c C:\\Automation\\BatFiles\\puttykill.lnk");
		p2.waitFor(30, TimeUnit.SECONDS);
		if (driver == null) {
			System.out.println("Driver is null");
			try {
				Process p = Runtime.getRuntime().exec("cmd /c C:\\Automation\\BatFiles\\IEKill.lnk");
				p.waitFor(30, TimeUnit.SECONDS);

			} catch (IOException e) {

				e.printStackTrace();
			}

			setDriver();
			driver.manage().window().maximize();
			Thread.sleep(5000);
			driver.navigate().to(
					"http://huxc0561.unix.marksandspencercate.com:8880/hlxc00dc074.unix.marksandspencercate.com_wmcdsit/UserLogin.html?loggedout=Y");

			int waitTime = 3;
			do {
				if (screen.exists("/images/RDConnection.png") != null) {
					break;
				} else {
					System.out.println("Login screen not found in loop");
				}

				Thread.sleep(3000);
				waitTime = waitTime + 3;
			} while (waitTime < 300);

			enterUsername();
			enterPassword();

		}

		Runtime.getRuntime().exec("C:\\Program Files\\AntMAS-CATE-A\\startupClientCATE-A.bat");

		int waitTime = 3;
		do {
			if (screen.exists("/images/ClientCateA.png") != null) {
				screen.type("123456");
				break;
			} else {
				System.out.println("Login screen not found in loop");
			}

			Thread.sleep(3000);
			waitTime = waitTime + 3;
		} while (waitTime < 300);

		// C:\Program Files\AntMAS-CATE-A\startupClientCATE-A.bat

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

	public void enterUsername() throws FindFailed, InterruptedException {
		int waitTime = 15;
		do {
			if (screen.exists("images/Username.png") != null) {
				break;
			}
			Thread.sleep(2000);
			waitTime = waitTime + 15;
		} while (waitTime < 120);

		screen.wait("images/username.png", 20);
		screen.click("images/username.png", 25);
		screen.type("Auto1");
	}

	public void enterPassword() throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type("1234");
		Thread.sleep(1000);
		screen.type(Key.ENTER);

		int waitTime = 15;
		do {
			if (screen.exists("images/EcomClose.png") != null) {
				screen.wait("images/EcomClose.png", 20);
				screen.click("images/EcomClose.png", 25);
				break;
			}
			Thread.sleep(3000);
			waitTime = waitTime + 15;
		} while (waitTime < 120);
	}

	// @After()
	public void puttyKill() throws IOException, InterruptedException {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c " + Constants.PUTTY_KILL + "");
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pingServer(String serverName) throws IOException, ClassNotFoundException, SQLException {
		System.out.println("----------Pinging Server : " + serverName + "----------------");
		try {
			Runtime r1 = Runtime.getRuntime();
			Process pCmd = r1.exec("ping " + serverName);
			BufferedReader inPing = new BufferedReader(new InputStreamReader(pCmd.getInputStream()));
			String inputLine;
			while ((inputLine = inPing.readLine()) != null) {
				if (inputLine.contains("Destination host unreachable")
						|| inputLine.contains("Ping request could not find host")) {
					getServerPingStatus = "Server is Down";
				} else if (inputLine.contains("Approximate round trip times in milli-seconds:")) {
					getServerPingStatus = "Server is UP";
				}
			}
			System.out.println("----------Pinging Server " + serverName + " - Status : " + getServerPingStatus
					+ "----------------");
			inPing.close();
		} catch (Exception e) {
			keepInMemory.setErrorMessage(
					"Exception " + e.getMessage() + " occurred at function pingServer for server " + serverName);
			System.out.println(keepInMemory.getErrorMessage());
			Assert.fail(keepInMemory.getErrorMessage());
		}
	}

	public void insertDetails(String testName) {
		try {
			String startTime = getSystemTime();
			keepInMemory.setChildStartTime(startTime);
			readParentRequestIdFromTextFile();
			String query = "INSERT INTO [dbo].[NPS_AUTO_UI_RUN_STATUS] ([P_REQ_ID],[TC_NAME],[EXEC_START_DATE_TIME],[STATUS])VALUES ('"
					+ keepInMemory.getParentRequestId() + "','" + testName + "','" + startTime + "','INPROGRESS')";
			database.insertUpdateDeleteQueryToDB(query, "iARMMSSQL");
		} catch (Exception e) {
			keepInMemory.setErrorMessage("Exception " + e.getMessage() + " occurred at function insertDetails");
			System.out.println(keepInMemory.getErrorMessage());
			Assert.fail(keepInMemory.getErrorMessage());
		}
	}

	private void updateBuildNumberInRequestTable() {
		if (BUILD_NUM == null) {
			BUILD_NUM = "0";
		}
		try {
			String updateQuery = "UPDATE NPS_AUTO_UI_RUN_REQUEST set JENKINS_BUILD_NO='" + BUILD_NUM
					+ "' where P_REQ_ID='" + keepInMemory.getParentRequestId() + "'";
			database.insertUpdateDeleteQueryToDB(updateQuery, "iARMMSSQL");
		} catch (Exception e) {
			keepInMemory.setErrorMessage(
					"Exception " + e.getMessage() + " occurred at function updateBuildNumberInRequestTable");
			System.out.println(keepInMemory.getErrorMessage());
			Assert.fail(keepInMemory.getErrorMessage());
		}
	}

	public void getParentRequestID() throws ClassNotFoundException, IOException, SQLException {
		if (PRQID == null) {
			PRQID = "0";
		}
		keepInMemory.setParentRequestId(PRQID);
		if (keepInMemory.getParentRequestId().equals("0")) {
			try {
				insertReqID();
				String projName = configuration.getStringProperty("tbl-parentprojectname");
				String query = "SELECT top 1 (P_REQ_ID) FROM [dbo].[NPS_AUTO_UI_RUN_REQUEST] where Project_Name = '"
						+ projName + "' Order by [P_REQ_ID] desc";
				PRQID = database.retrieveQueryFromDB(query, "iARMMSSQL");
				keepInMemory.setParentRequestId(PRQID);
				System.setProperty("ID", PRQID);
				writeParentRequestIdToTextFile();
			} catch (SQLException e) {
				keepInMemory
						.setErrorMessage("Exception " + e.getMessage() + " occurred at function getParentRequestID");
				System.out.println(keepInMemory.getErrorMessage());
				Assert.fail(keepInMemory.getErrorMessage());
			}
		} else {
			keepInMemory.setParentRequestId(PRQID);
			System.setProperty("ID", PRQID);
			writeParentRequestIdToTextFile();
		}
	}

	public void parentStartTime() throws ClassNotFoundException, SQLException {
		String updateStartTime = getSystemTime();
		String parentStTime = System.setProperty("ParentStartTime", updateStartTime);
		String updateQuery = "Update NPS_AUTO_UI_RUN_REQUEST set EXEC_START_DATE_TIME = '" + updateStartTime
				+ "' where P_REQ_ID = '" + keepInMemory.getParentRequestId() + "'";
		database.insertUpdateDeleteQueryToDB(updateQuery, "iARMMSSQL");
	}

	public void updateParentTable() {
		try {
			String passCount = getStatusCount(keepInMemory.getParentRequestId(), "PASS");
			String failCount = getStatusCount(keepInMemory.getParentRequestId(), "FAIL");
			String totalTestCaseCount = Integer.toString(Integer.valueOf(failCount) + Integer.valueOf(passCount));
			String parentStTime = getParentStartTime();
			String totalTime = getTimeDifference(parentStTime, getSystemTime());

			String updateParent = "UPDATE DBO.NPS_AUTO_UI_RUN_REQUEST SET TOTAL_AUTO_TC_EXECUTED = '"
					+ totalTestCaseCount + "', TOTAL_NO_PASS = '" + passCount + "',TOTAL_NO_FAIL = '" + failCount
					+ "', EXEC_END_DATE_TIME='" + getSystemTime() + "', OVERALL_STATUS= 'COMPLETE',TOTAL_EXEC_TIME = '"
					+ totalTime + "' where P_REQ_ID=" + keepInMemory.getParentRequestId() + " ";

			database.insertUpdateDeleteQueryToDB(updateParent, "iARMMSSQL");

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	@SuppressWarnings("static-access")
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
			database.insertUpdateDeleteQueryToDB(insertQuery, "iARMMSSQL");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void readParentRequestIdFromTextFile() throws IOException {
		FileInputStream fis = new FileInputStream("src/test/resources/files/ParentRequestId.txt");
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String data;
		String result = new String();

		while ((data = br.readLine()) != null) {
			result = result.concat(data);
		}

		String parentReqId = result;
		keepInMemory.setParentRequestId(String.valueOf(parentReqId));
		fis.close();

	}

	public void writeParentRequestIdToTextFile() throws IOException {
		String fileName = "src/test/resources/files/ParentRequestId.txt";

		try {
			String parentReqId = System.getProperty("ID");
			System.out.println("Parent Request ID saved to file: " + parentReqId);
			byte[] buffer = parentReqId.getBytes();
			FileOutputStream outputStream = new FileOutputStream(fileName);
			outputStream.write(buffer);
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getParentStartTime() throws ClassNotFoundException, SQLException {
		String query = "SELECT EXEC_START_DATE_TIME FROM NPS_AUTO_UI_RUN_REQUEST where P_REQ_ID = '"
				+ keepInMemory.getParentRequestId() + "'";
		String parentStartTime = database.retrieveQueryFromDB(query, "iARMMSSQL");
		return parentStartTime;
	}

	private String getStatusCount(String parentRequestId, String status) throws ClassNotFoundException, SQLException {
		String query = "select count(*) as COUNT from dbo.NPS_AUTO_UI_RUN_STATUS where P_REQ_ID='"
				+ keepInMemory.getParentRequestId() + "' AND STATUS='" + status + "'";
		String count = database.retrieveQueryFromDB(query, "iARMMSSQL");
		return count;
	}

	public String getServerName() throws ClassNotFoundException, SQLException {
		String query = "SELECT SERVER FROM Table_Name WHERE P_REQ_ID = '" + keepInMemory.getParentRequestId() + "'";
		String serverName = database.retrieveQueryFromDB(query, "iARMMSSQL");
		return serverName;
	}

	public String getParentReqId() throws ClassNotFoundException, SQLException {
		String query = "SELECT top 1 (P_REQ_ID) FROM [dbo].[NPS_AUTO_UI_RUN_REQUEST] Order by [P_REQ_ID] desc";
		String parentRequestId = database.retrieveQueryFromDB(query, "iARMMSSQL");
		return parentRequestId;
	}

	public void updateExecutionStatusInAutomationDb_End(String status, String tagName)
			throws ClassNotFoundException, SQLException, ParseException {
		System.out.println(keepInMemory.getChildStartTime());
		String totalTime = getTimeDifference(keepInMemory.getChildStartTime(), getSystemTime());
		String updateQuery = "UPDATE DBO.Nps_Auto_UI_Run_Status SET EXEC_END_DATE_TIME='" + getSystemTime()
				+ "', STATUS= '" + status + "',TOTAL_TIME = '" + totalTime + "',Remarks = '"
				+ keepInMemory.getErrorMessage() + "' where P_REQ_ID= '" + keepInMemory.getParentRequestId()
				+ "' and TC_NAME='" + tagName + "' and STATUS = 'INPROGRESS'";
		database.insertUpdateDeleteQueryToDB(updateQuery, "iARMMSSQL");

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
		return dateFormat.format(date);
	}

}