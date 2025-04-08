package com.mns.auto.cd.email;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.database.Database;
import com.mns.auto.cd.hooks.Hooks;
import com.mns.auto.cd.memory.KeepInMemory;

public class RequestDetailsRetriever {
	public static String[] mailAddressTo;
	private final KeepInMemory context;
	private final Hooks hooks;
	private final Database database;
	private final Configuration configuration;

	@Inject
	public RequestDetailsRetriever(KeepInMemory context, Hooks hooks, Configuration configuration, Database database) {
		this.context = context;
		this.hooks = hooks;
		this.configuration = configuration;
		this.database = database;

	}

	@SuppressWarnings("unused")
	public Map<String, Object> getRequestparentDetails(String parentRequestId) {
		Map<String, Object> requestSummaryDetailsMap = new HashMap<String, Object>();
		String columnsList = null;
		ArrayList<?> columnArrayList = new ArrayList<Object>();

		try {

			String query = "Select * from NPS_AUTO_UI_RUN_REQUEST where p_Req_Id='" + parentRequestId + "'";
			ResultSet rs = database.retrieveMapFromDB(query,"iARMMSSQL");

			if (rs.next()) {
				requestSummaryDetailsMap.put("ParentRequestId", rs.getString("P_REQ_ID"));
				requestSummaryDetailsMap.put("requestSubmittedby", rs.getString("REQUESTED_BY"));
				requestSummaryDetailsMap.put("RequestSubmittedOn", rs.getString("EXEC_START_DATE_TIME"));
				requestSummaryDetailsMap.put("totalTestsExecuted", rs.getString("TOTAL_AUTO_TC_EXECUTED"));
				requestSummaryDetailsMap.put("Region", rs.getString("REGION"));
				requestSummaryDetailsMap.put("Server/URL", rs.getString("APP_DETAILS"));
				String testingPhase = getTestingPhase(rs.getString("TESTING_PHASE_fk"));
				requestSummaryDetailsMap.put("TestingPhase", testingPhase);
				// requestSummaryDetailsMap.put("AutomationSuite",
				// resultSet.getString("AUTOMATION_SUITE_NAME"));
				requestSummaryDetailsMap.put("totalTimeTaken", rs.getString("TOTAL_EXEC_TIME"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return requestSummaryDetailsMap;
	}

	public String getTestingPhase(String masterId) throws ClassNotFoundException, SQLException {

		if (context.getiARMSQLDBConnection() == null) {
			database.dbConnectionOpen("iARMMSSQL");
		}
		String query = "sELECT master_desc FROM  tbl_autoMasterData Where master_id = '" + masterId + "'";

		Statement stmt = context.getiARMSQLDBConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		resultSet.next();
		return resultSet.getString(1);
	}

	@SuppressWarnings({ "unused" })
	public Map<String, Object> getRequestFailedInterfaceDetails(Map<String, Object> requestSummaryDetailsMap)
			throws ClassNotFoundException, SQLException {
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

	private String getStatusCount(String parentRequestId, String status) throws ClassNotFoundException, SQLException {
		String query = "select count(*) as COUNT from dbo.NPS_AUTO_UI_RUN_STATUS where P_REQ_ID='" + parentRequestId
				+ "' AND STATUS='" + status + "'";
		String count = database.retrieveQueryFromDB(query,"MSSQL");
		return count;
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
		ResultSet rs = null;

		try {
			String query = "select * from dbo.NPS_AUTO_UI_RUN_STATUS where P_REQ_ID='" + parentRequestId + "'";
			rs = database.retrieveMapFromDB(query,"iARMMSSQL");
			while (rs.next()) {

				if (rs.getString("STATUS").equalsIgnoreCase("FAIL")
						|| rs.getString("STATUS").equalsIgnoreCase("PASS")) {
					testCaseNameList.add(rs.getString("TC_NAME"));
				}

				if (rs.getString("STATUS").equalsIgnoreCase("FAIL")
						|| rs.getString("STATUS").equalsIgnoreCase("PASS")) {
					statusList.add(rs.getString("STATUS"));
				}

				if (rs.getString("STATUS").equalsIgnoreCase("FAIL")
						|| rs.getString("STATUS").equalsIgnoreCase("PASS")) {
					remarksList.add(rs.getString("REMARKS"));
				}

				if (rs.getString("STATUS").equalsIgnoreCase("FAIL")
						|| rs.getString("STATUS").equalsIgnoreCase("PASS")) {
					String st = rs.getString("EXEC_START_DATE_TIME");
					startTime.add(rs.getString("EXEC_START_DATE_TIME"));
				}

				if (rs.getString("STATUS").equalsIgnoreCase("FAIL")
						|| rs.getString("STATUS").equalsIgnoreCase("PASS")) {
					endTime.add(rs.getString("EXEC_END_DATE_TIME"));
				}

				if (rs.getString("STATUS").equalsIgnoreCase("FAIL")
						|| rs.getString("STATUS").equalsIgnoreCase("PASS")) {
					totalTime.add(rs.getString("TOTAL_TIME"));
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
			String query = "Select server from jda_anr_run_status Where req_id = '" + context.getParentRequestId() + "'";
			rs = database.retrieveMapFromDB(query,"iARMMSSQL");
			while (rs.next()) {
				envDetailsList = rs.getString("SERVER");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		requestSummaryDetailsMap.put("envDetailsList", envDetailsList);
		return requestSummaryDetailsMap;
	}

	public String[] getmailAddress(String parentRequestId) {
		ResultSet resultSet = null;
		String emails = null;
		ArrayList<String> emailList = new ArrayList<String>();
		try {
			if (context.getiARMSQLDBConnection() == null) {
				database.dbConnectionOpen("MSSQL");
			}

			String getEmailList = configuration.getStringProperty("emailList");

			resultSet = context.getiARMSQLDBConnection().createStatement().executeQuery(
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
