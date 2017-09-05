package com.jda.wms.DbReporting;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.DbConnection;

import com.jda.wms.datasetup.gm.DbConnection;

public class UpdateRequestToAutomationDb {
	private Context context;
	public static DbConnection dataBase;

	@Inject
	public UpdateRequestToAutomationDb(Context context, DbConnection dataBase) {
		this.context = context;
		this.dataBase = dataBase;

	}

	public void updateRequestStartTime() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_REQUESTS SET START_TIME=CURRENT_TIMESTAMP "
							+ " WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestEndTime() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_REQUESTS SET END_TIME=CURRENT_TIMESTAMP "
							+ " WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestTimeTaken() {
		ResultSet resultSet = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement().executeQuery(
					"SELECT (END_TIME - START_TIME) AS TOTAL_TIME FROM DBO.JDA_GM_RUN_REQUESTS WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "'");
			while (resultSet.next()) {

				String ODifference = resultSet.getString("TOTAL_TIME").substring(8, 19);

				String newDiff = ODifference.substring(0, 2) + " Days " + ODifference.substring(3, 5) + " HH "
						+ ODifference.substring(6, 8) + " mm " + ODifference.substring(9, 11) + " ss";

				dataBase.dbConnection.createStatement()
						.execute("UPDATE DBO.JDA_GM_RUN_REQUESTS SET TOTAL_TIME = '" + newDiff
								+ "' WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
								+ "'");
			}
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestStatus(String status) {

		status = status.trim().toUpperCase();

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_REQUESTS SET REQUEST_STATUS='" + status + "'"
							+ " WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestTcCount() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement().execute(
					"UPDATE DBO.JDA_GM_RUN_REQUESTS SET TOTAL_TC_COUNT = (SELECT COUNT(*) FROM DBO.JDA_GM_RUN_STATUS WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "') WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestPassCount() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement().execute(
					"UPDATE DBO.JDA_GM_RUN_REQUESTS SET TOTAL_PASS = (SELECT COUNT(*) FROM DBO.JDA_GM_RUN_STATUS WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId()
							+ "' AND TC_STATUS = 'PASS' ) WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestFailCountExcScripErrors() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement().execute(
					"UPDATE DBO.JDA_GM_RUN_REQUESTS SET TOTAL_FAIL = (SELECT COUNT(*) FROM DBO.JDA_GM_RUN_STATUS WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId()
							+ "' AND TC_STATUS = 'FAIL' ) WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateRequestFailCountIncScripErrors() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement().execute("UPDATE DBO.JDA_GM_RUN_REQUESTS "
					+ " SET DBO.JDA_GM_RUN_REQUESTS.TOTAL_FAIL = (DBO.JDA_GM_RUN_REQUESTS.TOTAL_TC_COUNT - "
					+ "(SELECT COUNT(*) FROM DBO.JDA_GM_RUN_STATUS WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='"
					+ context.getParentRequestId() + "' AND TC_STATUS = 'PASS' ))"
					+ " WHERE DBO.JDA_GM_RUN_REQUESTS.PARENT_REQUEST_ID ='" + context.getParentRequestId() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
