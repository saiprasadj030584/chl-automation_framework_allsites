package com.jda.wms.email;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.hooks.Hooks;


public class RequestDetailsRetriever {
	public static String[] mailAddressTo;
	private final Context context;
	private final Hooks hooks;
	private final Configuration configuration;

	@Inject
	public RequestDetailsRetriever(Context context, Hooks hooks, Configuration configuration) {
		this.context = context;
		this.hooks = hooks;
		this.configuration = configuration;

	}

	@SuppressWarnings("unused")
	public Map<String, Object> getRequestparentDetails(String parentRequestId) {
		Map<String, Object> requestSummaryDetailsMap = new HashMap<String, Object>();
		String columnsList = null;
		ArrayList<?> columnArrayList = new ArrayList<Object>();

		try {
			if (context.getSQLDBConnection() == null) {
				hooks.sqlConnectOpen();
			}

			String query = "Select * from NPS_AUTO_UI_RUN_REQUEST where p_Req_Id='" + parentRequestId + "'";

			Statement stmt = context.getSQLDBConnection().createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				requestSummaryDetailsMap.put("ParentRequestId", resultSet.getString("P_REQ_ID"));
				requestSummaryDetailsMap.put("requestSubmittedby", resultSet.getString("REQUESTED_BY"));
				requestSummaryDetailsMap.put("RequestSubmittedOn", resultSet.getString("EXEC_START_DATE_TIME"));
				requestSummaryDetailsMap.put("totalTestsExecuted", resultSet.getString("TOTAL_AUTO_TC_EXECUTED"));
				requestSummaryDetailsMap.put("Region", resultSet.getString("REGION"));
				requestSummaryDetailsMap.put("Server/URL", resultSet.getString("APP_DETAILS"));
				String testingPhase = getTestingPhase(resultSet.getString("TESTING_PHASE_fk"));
				requestSummaryDetailsMap.put("TestingPhase", testingPhase);
				// requestSummaryDetailsMap.put("AutomationSuite",
				// resultSet.getString("AUTOMATION_SUITE_NAME"));
				requestSummaryDetailsMap.put("totalTimeTaken", resultSet.getString("TOTAL_EXEC_TIME"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return requestSummaryDetailsMap;
	}

	public String getTestingPhase(String masterId) throws ClassNotFoundException, SQLException {

		if (context.getSQLDBConnection() == null) {
			hooks.sqlConnectOpen();
		}
		String query = "sELECT master_desc FROM  tbl_autoMasterData Where master_id = '" + masterId + "'";

		Statement stmt = context.getSQLDBConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		resultSet.next();
		return resultSet.getString(1);
	}

	@SuppressWarnings({ "unused" })
	public Map<String, Object> getRequestFailedInterfaceDetails(Map<String, Object> requestSummaryDetailsMap) {
		String parentRequestId = context.getParentRequestId();
		String region = (String) requestSummaryDetailsMap.get("region");
		ResultSet resultSet = null;
		String columnsList = null;

		String passCount = getStatusCount(parentRequestId, "PASS");
		String failCount = getStatusCount(parentRequestId, "FAIL");
		String noRunCount = getStatusCount(parentRequestId, "NORUN");
		String inProgressCount = getStatusCount(parentRequestId, "INPROGRESS");

		failCount = Integer.toString(Integer.valueOf(failCount) + Integer.valueOf(inProgressCount));
		requestSummaryDetailsMap.put("passCount", passCount);
		requestSummaryDetailsMap.put("failCount", failCount);
		requestSummaryDetailsMap.put("noRunCount", noRunCount);
		return requestSummaryDetailsMap;

	}

	private String getStatusCount(String parentRequestId, String status) {
		String Count = null;
		ResultSet resultSet = null;
		try {
			if (context.getSQLDBConnection() == null) {
				hooks.sqlConnectOpen();
			}
			resultSet = context.getSQLDBConnection().createStatement()
					.executeQuery("select count(*) as COUNT from dbo.NPS_AUTO_UI_RUN_STATUS where P_REQ_ID='"
							+ parentRequestId + "' AND STATUS='" + status + "'");
			while (resultSet.next()) {
				Count = resultSet.getString("COUNT");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return Count;
	}

	public Map<String, Object> getFailedList(String parentRequestId) {

		Map<String, Object> requestSummaryDetailsMap = new HashMap<String, Object>();
		ArrayList<String> remarksList = new ArrayList<String>();
		ArrayList<String> testCaseNameList = new ArrayList<String>();
		ArrayList<String> jobNameList = new ArrayList<String>();
		ArrayList<String> batFileNameList = new ArrayList<String>();
		ArrayList<String> startTime = new ArrayList<String>();
		ArrayList<String> endTime = new ArrayList<String>();
		ArrayList<String> executionDate = new ArrayList<String>();
		ArrayList<String> totalTime = new ArrayList<String>();
		ArrayList<String> statusList = new ArrayList<String>();
		ArrayList<String> dataTypeList = new ArrayList<String>();
		ArrayList<String> subScenarioList = new ArrayList<String>();
		ResultSet resultSet = null;

		try {
			if (context.getSQLDBConnection() == null) {
				hooks.sqlConnectOpen();
			}
			resultSet = context.getSQLDBConnection().createStatement()
					.executeQuery("select * from dbo.NPS_AUTO_UI_RUN_STATUS where P_REQ_ID='" + parentRequestId + "'");
			while (resultSet.next()) {

				if (resultSet.getString("STATUS").equalsIgnoreCase("FAIL")
						|| resultSet.getString("STATUS").equalsIgnoreCase("PASS")) {
					testCaseNameList.add(resultSet.getString("TC_NAME"));
				}

				if (resultSet.getString("STATUS").equalsIgnoreCase("FAIL")
						|| resultSet.getString("STATUS").equalsIgnoreCase("PASS")) {
					statusList.add(resultSet.getString("STATUS"));
				}

				if (resultSet.getString("STATUS").equalsIgnoreCase("FAIL")
						|| resultSet.getString("STATUS").equalsIgnoreCase("PASS")) {
					remarksList.add(resultSet.getString("REMARKS"));
				}

				if (resultSet.getString("STATUS").equalsIgnoreCase("FAIL")
						|| resultSet.getString("STATUS").equalsIgnoreCase("PASS")) {
					String st = resultSet.getString("EXEC_START_DATE_TIME");
					startTime.add(resultSet.getString("EXEC_START_DATE_TIME"));
				}

				if (resultSet.getString("STATUS").equalsIgnoreCase("FAIL")
						|| resultSet.getString("STATUS").equalsIgnoreCase("PASS")) {
					endTime.add(resultSet.getString("EXEC_END_DATE_TIME"));
				}

				if (resultSet.getString("STATUS").equalsIgnoreCase("FAIL")
						|| resultSet.getString("STATUS").equalsIgnoreCase("PASS")) {
					totalTime.add(resultSet.getString("TOTAL_TIME"));
				}
			}

			requestSummaryDetailsMap.put("totalStepList", testCaseNameList);
			requestSummaryDetailsMap.put("commentsList", remarksList);
			requestSummaryDetailsMap.put("jobNameList", jobNameList);
			requestSummaryDetailsMap.put("batFileNameList", batFileNameList);
			requestSummaryDetailsMap.put("startTime", startTime);
			requestSummaryDetailsMap.put("endTime", endTime);
			requestSummaryDetailsMap.put("executionDate", executionDate);
			requestSummaryDetailsMap.put("totalTime", totalTime);
			requestSummaryDetailsMap.put("statusList", statusList);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return requestSummaryDetailsMap;
	}

	public Map<String, Object> getENVListDetails(Map<String, Object> requestSummaryDetailsMap) {
		String envDetailsList = null;
		ResultSet rs = null;
		try {
			if (context.getSQLDBConnection() == null) {
				hooks.sqlConnectOpen();
			}

			rs = context.getSQLDBConnection().createStatement().executeQuery(
					"Select server from jda_anr_run_status Where req_id = '" + context.getParentRequestId() + "'");

			while (rs.next()) {
				envDetailsList = rs.getString("SERVER");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		requestSummaryDetailsMap.put("envDetailsList", envDetailsList);
		return requestSummaryDetailsMap;
	}

	/*
	 * public String getRegion(String parentRequestId) { ResultSet rs = null;
	 * String region = null; try { if (context.getSQLDBConnection() == null) {
	 * dataBaseConnection.sqlConnectOpen(); } rs =
	 * context.getSQLDBConnection().createStatement().executeQuery(
	 * "Select Distinct [region] from jda_anr_run_status Where req_id = '" +
	 * context.getParentRequestId() + "'"); while (rs.next()) { region =
	 * rs.getString("REGION"); } } catch (Exception exception) {
	 * exception.printStackTrace(); } return region; }
	 */
	public String[] getmailAddress(String parentRequestId) {
		ResultSet resultSet = null;
		String emails = null;
		ArrayList<String> emailList = new ArrayList<String>();
		try {
			if (context.getSQLDBConnection() == null) {
				hooks.sqlConnectOpen();
			}

			String getEmailList = configuration.getStringProperty("emailList");

			resultSet = context.getSQLDBConnection().createStatement().executeQuery(
					"Select Email_List from nps_auto_ui_run_request where P_REQ_ID='" + parentRequestId + "'");
			while (resultSet.next()) {
				emails = resultSet.getString(1);
				System.out.println("Actual value->" + emails);
				if (emails.contains("@mnscorp.net") || emails.contains("@marks-and-spencer.com")) {
					emails = emails + "," + getEmailList;
				}

				else {
					emails = getEmailList;
				}
			}

			String[] mailHardCode = emails.split(",");

			System.out.println("--->" + mailHardCode);

			for (int index = 0; index < mailHardCode.length; index++) {
				emailList.add(mailHardCode[index]);
			}

			mailAddressTo = new String[emailList.size()];
			for (int count = 0; count < emailList.size(); count++) {
				mailAddressTo[count] = (String) emailList.get(count);
			}
			// dataBase.disconnectDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return mailAddressTo;
	}
}
