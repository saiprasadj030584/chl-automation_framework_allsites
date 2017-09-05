package com.jda.wms.DbReporting;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.DbConnection;

public class UpdateTcToAutomationDb {
	private Context context;
	public static DbConnection dataBase;

	@Inject
	public UpdateTcToAutomationDb(Context context, DbConnection dataBase) {
		this.context = context;
		this.dataBase = dataBase;

	}

	public void updateTcStartTime() {

		try {
			dataBase.connectAutomationDB();

			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET TC_START_TIME=CURRENT_TIMESTAMP "
							+ "WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateTcEndTime() {

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET TC_END_TIME=CURRENT_TIMESTAMP "
							+ "WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateTcTimeTaken() {

		ResultSet resultSet = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement().executeQuery(
					"SELECT (TC_END_TIME - TC_START_TIME) AS TC_TOTAL_TIME FROM DBO.JDA_GM_RUN_STATUS WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='"
							+ context.getUniqueTag() + "'");
			while (resultSet.next()) {

				String ODifference = resultSet.getString("TC_TOTAL_TIME").substring(8, 19);

				String newDiff = Integer
						.toString((Integer.valueOf(ODifference.substring(0, 2)) * 24)
								+ Integer.valueOf(ODifference.substring(3, 5)))
						+ " HH " + ODifference.substring(6, 8) + " mm " + ODifference.substring(9, 11) + " ss";

				dataBase.dbConnection.createStatement()
						.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET TC_TOTAL_TIME = '" + newDiff
								+ "' WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
								+ "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			}
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateTcComments(String comments) {

		comments = comments.trim().toUpperCase();

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET COMMENTS='" + comments + "'"
							+ "WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateTcStatus(String status) {

		status = status.trim().toUpperCase();

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET TC_STATUS='" + status + "'"
							+ "WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void updateTcTestData(String testData) {

		testData = testData.trim().toUpperCase();

		try {
			dataBase.connectAutomationDB();
			dataBase.dbConnection.createStatement()
					.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET TEST_DATA_REFERNCE='" + testData + "'"
							+ "WHERE DBO.JDA_GM_RUN_STATUS.PARENT_REQUEST_ID ='" + context.getParentRequestId()
							+ "' AND DBO.JDA_GM_RUN_STATUS.UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			dataBase.dbConnection.commit();
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
