package com.jda.wms.stepdefs.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.email.HtmlCreator;
import com.jda.wms.email.SendEmail;
import com.jda.wms.hooks.Hooks;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class EmailStepDefs {

	private final Context context;
	private final HtmlCreator htmlCreator;
	private final SendEmail sendEmail;
	private final Hooks hooks;
	private final Configuration configuration;
	public static String PRQID = null;
	String envVar = System.getProperty("user.dir");

	@Inject
	public EmailStepDefs(Configuration configuration, Hooks hooks, Context context,
			HtmlCreator htmlCreator, SendEmail sendEmail) {
		this.context = context;
		this.htmlCreator = htmlCreator;
		this.sendEmail = sendEmail;
		this.hooks = hooks;
		this.configuration = configuration;
	}

	@Given("^user has triggered an ANR automated email to end user with automation test result$")
	public void user_has_triggered_an_anr_automated_email_to_end_user_with_automation_test_result() throws Throwable {
		hooks.fileReadValueFromText();
		htmlCreator.htmlWriter(context.getParentRequestId());
		sendEmail.triggerEmailAutomatedTestResults();

	}

	@Given("^Insert the metrics details in automation metrics DB$")
	public void insert_the_metrics_details_in_automation_metrics_DB() throws Throwable {
		Statement stmt = null;
		hooks.fileReadValueFromText();
		System.out.println("PARENT REQ ID"+context.getParentRequestId());
		if (context.getSQLDBConnection() == null) {
			System.out.println("$$$$$$$$$$---SQL DATABASE CONNECTION NEED TO BE RE-ESTABLISHED");
			hooks.sqlConnectOpen();
		}

		stmt = context.getSQLDBConnection().createStatement();
		String query = "Select * from NPS_AUTO_UI_RUN_REQUEST where P_REQ_ID = '" + context.getParentRequestId() + "'";
		
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			String execStartDateTime = rs.getString("EXEC_START_DATE_TIME");
			String execEndDateTime = rs.getString("EXEC_END_DATE_TIME");
			String totalAutomationTestCaseExecuted = rs.getString("TOTAL_AUTO_TC_EXECUTED");

			String cluster = configuration.getStringProperty("tbl-parentcluster");
			String projectCode = configuration.getStringProperty("tbl-parentprojectcode");
			String projectName = configuration.getStringProperty("tbl-parentprojectname");
			String automationSuiteName = configuration.getStringProperty("tbl-parentautomationsuitname");
			String almProjectName = configuration.getStringProperty("alm-projectName");
			String testingPhase = configuration.getStringProperty("tbl-testingPhase");

			String almDomain = configuration.getStringProperty("alm-domain");
			String almExecution = configuration.getStringProperty("alm-executed");
			String manualExecutionTC = configuration.getStringProperty("test-caseExecutedManual");
			String newlyDevelopedCase = configuration.getStringProperty("test-newlyDevelopedCase");
			String requestedBy = configuration.getStringProperty("tbl-RequestBy");

			String query1 = "exec dbo.usp_AddExecDetailsFromUI '0','" + cluster + "','" + execStartDateTime + "','"
					+ execEndDateTime + "','" + almDomain + "','" + almProjectName + "','" + projectName + "','"
					+ projectCode + "','" + testingPhase + "','" + automationSuiteName + "','" + almExecution + "','"
					+ manualExecutionTC + "','" + totalAutomationTestCaseExecuted + "','"
					+ totalAutomationTestCaseExecuted + "','','" + newlyDevelopedCase + "','" + requestedBy + "','"
					+ requestedBy + "'";

			System.out.println("Stored Procedure ---> " + query1);
			System.out.println(context.getSQLDBConnection());
			context.getSQLDBConnection().createStatement().execute(query1);
		}
	}

	@Then("^I trigger email to all the stakeholders$")
	public void i_trigger_email_to_all_the_stakeholders() throws Throwable { 
		hooks.fileReadValueFromText();
		htmlCreator.htmlWriter(context.getParentRequestId());
		sendEmail.triggerEmailAutomatedTestResults();
	}
	
	@Then("^I update the cucumber reports for the js files$")
	public void i_update_the_cucumber_reports_for_the_js_files() throws Throwable { 
		//Replacing the JS code lines in HTML files to cloud URLs
		String replaceJqueryURL = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js";
		String replaceTableSorterURL = "https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.29.0/js/jquery.tablesorter.min.js";
		String replaceChartMinURL = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js";
		String replaceBootStrapURL = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js";
		Thread.sleep(5000);
		File reportPath = new File(configuration.getStringProperty("cucumber-path"));
		System.out.println(reportPath);
		File[] listOfFiles = reportPath.listFiles();
		int numberOfFiles = reportPath.listFiles().length;
		System.out.println("---------Cucumber Reports----------");
		if (numberOfFiles!=0){
			for (int f=0; f<numberOfFiles;f++){
				System.out.println("HTML Report "+f+" "+listOfFiles[f].getName());
				if (listOfFiles[f].isFile()&&listOfFiles[f].getName().contains(".html")){
					String repFilePath = reportPath+"\\"+listOfFiles[f].getName();
					File repFile=new File(repFilePath);
					String fileContent = null;
					FileReader fr=new FileReader(repFile);
					BufferedReader br=new BufferedReader(fr);
					String line;
						while((line=br.readLine())!=null){
							if (fileContent == null){
								fileContent = line;
							}
							else{
								fileContent+=line;
							}
						}
						//To replace jquery src to cloud urls
						fileContent = fileContent.replace("js/jquery.min.js", replaceJqueryURL);
						fileContent = fileContent.replace("js/jquery.tablesorter.min.js", replaceTableSorterURL);
						fileContent = fileContent.replace("js/Chart.min.js", replaceChartMinURL);
						fileContent = fileContent.replace("js/bootstrap.min.js", replaceBootStrapURL);
						PrintWriter outfile = new PrintWriter(repFilePath);
						outfile.println(fileContent);
						outfile.close();
				}
			}
		}
		System.out.println("cmd /c \"" + envVar + "\\bin\\zipCucumberReportAdmin.lnk\"");
		Thread.sleep(2000);
		Process p2 = Runtime.getRuntime().exec("cmd /c \"" + envVar + "\\bin\\zipCucumberReportAdmin.lnk\"");
		Thread.sleep(5000);
	}
}
