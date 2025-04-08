package com.mns.auto.cd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.memory.KeepInMemory;


public class DataBase {

	private Configuration configuration;
	private KeepInMemory keepInMemory;

	public static Connection dbConnection = null;

	@Inject
	public DataBase(KeepInMemory keepInMemory, Configuration configuration) {
		this.configuration = configuration;
		this.keepInMemory = keepInMemory;
	}

	// connects Automation DB
	public void connectAutomationDB() {
		String dbURL = "jdbc:sqlserver://" + configuration.getStringProperty("server") + ";databaseName="
				+ configuration.getStringProperty("dataBase") + "";

		try {

			Class.forName(System.getProperty("dbDriver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")).newInstance();

			DriverManager.setLoginTimeout(30);
			dbConnection = DriverManager.getConnection(dbURL, configuration.getStringProperty("dbusername"),
					configuration.getStringProperty("dbpassword"));
			System.out.println("DB Connected");
			keepInMemory.setConnection(dbConnection);

		} catch (Exception Exception) {
			Exception.printStackTrace();

		}
	}

	// get credentials from automation DB
	public String getCredentials(String applicationName, String region, String column) {
		ResultSet rs = null;
		Statement stmt = null;
		String res = null;
		try {
			if (keepInMemory.getConnection() == null || keepInMemory.getConnection().isClosed()) {
				connectAutomationDB();
			}

			stmt = keepInMemory.getConnection().createStatement();
			System.out.println("select " + column + " from env_inventory WHERE APPLICATION_NAME ='" + applicationName
					+ "' and REGION ='" + region + "'");
			rs = stmt.executeQuery("select " + column + " from env_inventory WHERE APPLICATION_NAME ='"
					+ applicationName + "' and REGION ='" + region + "'");
			rs.next();
			res = rs.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Assert.fail("Exception in getting DB Credentials" + e.getMessage());
		}
		return res;

	}

	// get password from automation DB
	public String getPwd(String applicationname, String region) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;
		if (keepInMemory.getConnection() == null || keepInMemory.getConnection().isClosed()) {
			connectAutomationDB();
		}
		try {
			stmt = keepInMemory.getConnection().createStatement();
			rs = stmt.executeQuery(
					"select convert(nvarchar,DECRYPTBYPASSPHRASE(application_name,PWD)) as D_PWD from DBO.env_inventory where  APPLICATION_NAME='"
							+ applicationname + "' and REGION ='" + region + "'");
			rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Assert.fail("Exception in getting DB Credentials-PWD" + e.getMessage());
		}
		return rs.getString(1);
	}

	// disconnects Automation DB
	public void disconnectAutomationDB() {
		try {
			if (keepInMemory.getConnection() != null && !keepInMemory.getConnection().isClosed())
				keepInMemory.getConnection().close();
		} catch (SQLException ex) {
			System.out.println("Exception in db disconnection:" + ex.toString());
		}
	}

}
