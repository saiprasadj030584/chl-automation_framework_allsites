package com.jda.wms.hooks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;*/
import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.DataSetupRunner;
import com.jda.wms.db.Database;
import com.jda.wms.tests.AllTest;

import au.com.bytecode.opencsv.CSVWriter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final RemoteWebDriver webDriver;
	Screen screen = new Screen();
	private Context context;
	String envVar = System.getProperty("user.dir");
	private DataSetupRunner dataSetupRunner;
	private Database database;
	public static String scenarioname = null;
	private Configuration configuration;
	public static int pass = 0;
	public static int fail = 0;
	
	public static String portfolioName = "C&H Logistics";
	public String projectID = "JDADC";
	public static String projectName = "JDA Dispatcher WMS";
	public String programName = "CH Supply Chain and Logistics Retail Program";
	public String testregion = "ST";
	public String sprintname = "Sprint Q1.6";
	public String usertype = "Cloud";
	public static String filename = portfolioName + "-" + projectName + "-CSVFile.csv";
	public static String clouduser_filepath = "C:\\Automation_supporting_files\\Report\\";
	public String onpremiseuser_filepath = "\\\\mshrdmnsukc0007\\D$\\AutomationResultFiles\\Cloud\\";
	public static String filepath;
	//public String onpremiseuser_filepath = "C:\\Automation_supporting_files\\Report\\";
	/*
	public static String connectStr = "DefaultEndpointsProtocol=https;AccountName=coreuntechdevmetrics;AccountKey=3Tgbhys0vBFjtehzv0HO8YOJmAXZnanKVBzQ+HDvygPbiqrRKsZJW3d7oSAe4ggo9jHHbejb04tJXrfOeEdHNw==;EndpointSuffix=core.windows.net";
	public static BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
	
	public static String containerName = "toolscontainer";
	// Create the container and return a container client object
	public static BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
	public static BlobClient blobClient = containerClient.getBlobClient(filename);
   */
	@Inject
	public Hooks(Context context, Database database, RemoteWebDriver webDriver, Configuration configuration) {
		this.webDriver = webDriver;
		this.context = context;
		this.database = database;
		this.configuration = configuration;
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

	@After // ("~@Email")
	public void tearDown(Scenario scenario) throws InterruptedException, IOException {
		// attaching the screenshot in cucumber report
		System.out.println("After class----> Count" + scenario.getId());
		if (scenario.isFailed()) {
			System.out.println("After class----> FAIL" + scenario.isFailed());
		
			System.out.println(scenario.getName());
			scenarioname = scenario.getName();

			System.out.println("Entering teardown if scenario is failed");
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				System.out.println("After class----> PASS" + scenario.isFailed());
				System.out.println(scenario.getName());
				
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block

			}

		}

		// clearing down webdriver object

	}
	@After()
	public void updateCSVFileAfterClass(Scenario scenario) throws IOException {
	System.out.println(usertype);
	if(AllTest.dryRun.equalsIgnoreCase("False"))
	{
	if (usertype.equals("Cloud")) {
	//FileDownload();
	 
	updateCSVFile(scenario);
 
	//FileUpload();
	} else if (usertype.equals("OnPremise")) {
	updateCSVFile(scenario);
	}
	}
	}
	 
	public void updateCSVFile(Scenario scenario) throws IOException {
	System.out.println("Test Region is" + testregion);
	LocalDate localDate = LocalDate.now();
	LocalTime localtime = LocalTime.now();
	String date = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
	String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(localtime);
	if (usertype.equals("Cloud")) {
	filepath = clouduser_filepath;
	 
	} else if (usertype.equals("OnPremise")) {
	 
	filepath = onpremiseuser_filepath;
	}
	 
	System.out.println("Local Path is" + filepath);
	 
	File csvfile = new File(filepath + filename);
	//File filecopy = new File(localfilecopypath + filename);
	 
	try {
	CSVWriter csvfilewriter = new CSVWriter(new FileWriter(csvfile, true));
	//CSVWriter csvfilewriter1 = new CSVWriter(new FileWriter(filecopy, true));
	String[] csvfileheader = { "Portfolio_Name", "Project_ID", "Project_Name", "Program_Name", "Sprint_Name", "Date", "Time", "Test-Region",
	"Feature_Name", "Scenario_Name", "Passed", "Failed" };
	if (csvfile.length() == 0) {
	 
	csvfilewriter.writeNext(csvfileheader);
	//csvfilewriter1.writeNext(csvfileheader);
	}
	 
	String[] csvfilevalue = new String[csvfileheader.length];
	String scenarionid = scenario.getId();
	String featurename = null;
	String[] arrOfStr = scenarionid.split(";");
	{
	for (int i = 0; i < arrOfStr.length; i++) {
	featurename = arrOfStr[0];
	}
	}
	 
	int passed = 0;
	int failed = 0;
	csvfilevalue[0] = portfolioName;
	csvfilevalue[1] = projectID;
	csvfilevalue[2] = projectName;
	csvfilevalue[3] = programName;
	csvfilevalue[4] = sprintname;
	csvfilevalue[5] = date;
	csvfilevalue[6] = time;
	csvfilevalue[7] = testregion;
	csvfilevalue[8] = featurename;
	csvfilevalue[9] = scenario.getName();
	if (scenario.isFailed()) {
	failed = 1;
	csvfilevalue[10] = String.valueOf(passed);
	csvfilevalue[11] = String.valueOf(failed);
	} else {
	passed = 1;
	csvfilevalue[10] = String.valueOf(passed);
	csvfilevalue[11] = String.valueOf(failed);
	}
	 
	csvfilewriter.writeNext(csvfilevalue);
	//csvfilewriter1.writeNext(csvfilevalue);
	csvfilewriter.close();
	//csvfilewriter1.close();
	} catch (IOException e) {
	System.out.println("Exception while writing file: " + e.getMessage());
	}
	 
	}
	/*public static void FileUpload() throws IOException
	{
	 
	System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());
	blobClient.uploadFromFile(filepath + filename,true);
	System.out.println("CSV file uploaded");
	 
	}*/
	/*
	public static void FileDownload() {
	 
	filepath = clouduser_filepath;
	File csvfile = new File(filepath+filename);
	 
	if(csvfile.exists())
	{
	csvfile.delete();
	System.out.println("File with the same name already exists in the path.So Will delete and Download the Feature File");
	}
	 
	blobClient.downloadToFile(filepath+filename);
	System.out.println("CSV file downloaded from the blob");
}*/
	
}
