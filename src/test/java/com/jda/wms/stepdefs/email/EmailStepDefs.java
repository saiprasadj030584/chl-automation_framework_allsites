package com.jda.wms.stepdefs.email;

import java.sql.ResultSet;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.email.HtmlCreator;
import com.jda.wms.email.SendEmail;
import com.jda.wms.hooks.Hooks_autoUI;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class EmailStepDefs {

	private final Context context;
	private final HtmlCreator htmlCreator;
	private final SendEmail sendEmail;
	private final Hooks_autoUI hooks_autoUI;
	private final Configuration configuration;
	public static String PRQID = null;

	@Inject
	public EmailStepDefs(Configuration configuration, Hooks_autoUI hooks_autoUI, Context context,
			HtmlCreator htmlCreator, SendEmail sendEmail) {
		this.context = context;
		this.htmlCreator = htmlCreator;
		this.sendEmail = sendEmail;
		this.hooks_autoUI = hooks_autoUI;
		this.configuration = configuration;
	}

	@Given("^user has triggered an ANR automated email to end user with automation test result$")
	public void user_has_triggered_an_anr_automated_email_to_end_user_with_automation_test_result() throws Throwable {
		hooks_autoUI.fileReadValueFromText();
		htmlCreator.htmlWriter(context.getParentRequestId());
		sendEmail.triggerEmailAutomatedTestResults();

	}

	@Given("^Insert the metrics details in automation metrics DB$")
	public void insert_the_metrics_details_in_automation_metrics_DB() throws Throwable {
		Statement stmt = null;
		hooks_autoUI.fileReadValueFromText();
		System.out.println("PARENT REQ ID"+context.getParentRequestId());
		if (context.getSQLDBConnection() == null) {
			hooks_autoUI.sqlConnectOpen();
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
		hooks_autoUI.fileReadValueFromText();
		System.out.println("EMAIL CHeck 1");
		htmlCreator.htmlWriter(context.getParentRequestId());
		System.out.println("EMAIL CHeck 2");
		sendEmail.triggerEmailAutomatedTestResults();
		System.out.println("EMAIL CHeck 3");
	}
}
