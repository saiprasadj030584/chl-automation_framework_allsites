package com.jda.wms.datasetup.gm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DbConnection {

	public Connection dbConnection = null;
	public static String dbURL = null;
	public static String userId = null;
	public static String pwd = null;
	public static Context context = new Context();
	public static DbConnection npsDataBase;

	
	

	public void connectAutomationDB() {

		Map<String, String> DBDetails = getJdaAutomationDbDetails();

		dbURL = DBDetails.get("dbURL");
		userId = DBDetails.get("userId");
		pwd = DBDetails.get("pwd");

		try {
			Class.forName(System.getProperty("dbDriver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")).newInstance();
			DriverManager.setLoginTimeout(30);
			dbConnection = DriverManager.getConnection(dbURL, userId, pwd);
			context.setDBConnection(dbConnection);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getSiteId(String uniqueTag) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			System.out.println("CHECK CONNECTION " + context.getDBConnection());
			if (null == context.getDBConnection() || context.getDBConnection().isClosed()) {
				connectAutomationDB();
			}
			// npsDataBase.connectAutomationDB();

			stmt = context.getDBConnection().createStatement();
			String selectQuery = "Select SITE_NO from JDA_GM_TEST_DATA where UNIQUE_TAG = '" + uniqueTag + "'";
			System.out.println(selectQuery);
			context.getDBConnection().createStatement().execute(selectQuery);
			rs = stmt.executeQuery(selectQuery);
			if (!rs.next()) {
				Assert.fail("Unique Tag Id is not found");

			} else {
				context.setSiteID(rs.getString("SITE_NO"));
			}

		}

		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getJdaSiteIdFromDB() {
		ResultSet resultSet = null;
		try {
			npsDataBase.connectAutomationDB();
			resultSet = npsDataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_REQUESTS where PARENT_REQUEST_ID='"
							+ context.getParentRequestId() + "'");

			while (resultSet.next()) {
				context.setSiteID(resultSet.getString("SITE_NO"));
			}
			npsDataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void disconnectAutomationDB() {
		System.out.println("IN DISCONNECTION AUTOMATION DB");
		try {
			if (this.dbConnection != null && !this.dbConnection.isClosed())
				this.dbConnection.close();
		} catch (SQLException ex) {
			System.out.println("Exception in db disconnection:" + ex.toString());
		}
	}

	public static Map<String, String> getJdaAutomationDbDetails() {
		String filePath = ".\\files";
		String FILENAME = "databaseDetails.txt";
		Map<String, String> DBDetails = new HashMap<String, String>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath + "\\" + FILENAME))) {
			String sCurrentLine;
			while (!((sCurrentLine = br.readLine()).contains("***end of file***"))) {
				if (sCurrentLine.contains("dbURL")) {
					String[] temp = sCurrentLine.split("::-");
					DBDetails.put("dbURL", temp[(temp.length) - 1].trim());
				}
				if (sCurrentLine.contains("userId")) {
					String[] temp = sCurrentLine.split("::-");
					DBDetails.put("userId", temp[temp.length - 1].trim());
				}
				if (sCurrentLine.contains("pwd")) {
					String[] temp = sCurrentLine.split("::-");
					DBDetails.put("pwd", temp[temp.length - 1].trim());
				}
			}
		} catch (IOException e) {
			e.getStackTrace().toString();
		}

		return DBDetails;
	}

}