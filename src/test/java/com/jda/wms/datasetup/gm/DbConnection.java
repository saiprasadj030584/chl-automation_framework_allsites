package com.jda.wms.datasetup.gm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbConnection {

	public Connection dbConnection = null;

	public static String dbURL = null;
	public static String userId = null;
	public static String pwd = null;

	public void connectAutomationDB() {

		Map<String, String> DBDetails = getJdaAutomationDbDetails();

		dbURL = DBDetails.get("dbURL");
		userId = DBDetails.get("userId");
		pwd = DBDetails.get("pwd");

		try {
			Class.forName(System.getProperty("dbDriver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")).newInstance();
			DriverManager.setLoginTimeout(30);
			dbConnection = DriverManager.getConnection(dbURL, userId, pwd);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void disconnectDB() {
		try {
			if (this.dbConnection != null && !this.dbConnection.isClosed())
				this.dbConnection.close();
		} catch (SQLException ex) {
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
					System.err.println("temp[temp.length - 1].trim()  " + temp[temp.length - 1].trim());
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