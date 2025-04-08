package com.mns.auto.cd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.memory.KeepInMemory;

public class Database {
	private final Configuration configuration;
	private KeepInMemory keepInMemory;
	private Connection connection;
	// public String statusRegion = System.getProperty("USE_DB");
	// public String region = System.getProperty("REGION");
	// public String metricsDB = System.getProperty("METRICSDB");

	@Inject
	public Database(KeepInMemory keepInMemory, Configuration configuration) {
		this.keepInMemory = keepInMemory;
		this.configuration = configuration;
	}

	@SuppressWarnings("unused")
	public void dbConnectionOpen(String databaseType) throws ClassNotFoundException {
		try {
			if (databaseType.equals("ORACLE")) {
				System.out.println("Inside ORACLE");
				boolean connectionSucessful = false;
				Class.forName("oracle.jdbc.driver.OracleDriver");
				if (keepInMemory.getUseDBForData().equalsIgnoreCase("N")) {
					if (keepInMemory.getRegion().equalsIgnoreCase("SIT")) {
						connection = DriverManager.getConnection(configuration.getStringProperty("sit-db-host"),
								configuration.getStringProperty("sit-db-username"),
								configuration.getStringProperty("sit-db-password"));
					} else if (keepInMemory.getRegion().equalsIgnoreCase("ST")) {
						connection = DriverManager.getConnection(configuration.getStringProperty("st-db-host"),
								configuration.getStringProperty("st-db-username"),
								configuration.getStringProperty("st-db-password"));
					}
				} else {
					System.out.println(
							"Get environment Details from NPS DB Host:-" + keepInMemory.getDBHost() + " DB UserName:-"
									+ keepInMemory.getDBUserName() + " DB Password:-" + keepInMemory.getDBPassword());
					connection = DriverManager.getConnection(keepInMemory.getDBHost(), keepInMemory.getDBUserName(),
							keepInMemory.getDBPassword());
				}
				connection.setAutoCommit(true);
				keepInMemory.setiARMSQLDBConnection(connection);
				connectionSucessful = true;
			} else if (databaseType.equals("iARMMSSQL")) {
				System.out.println("Inside iARMMSSQL");
				boolean connectionSucessful = false;

				String database = null;
				if (keepInMemory.getMetricsDB() == null || keepInMemory.getMetricsDB().equals("0")
						|| keepInMemory.getMetricsDB().equals("N")) {
					database = "NPS_DEV";
				} else if (keepInMemory.getMetricsDB().equals("Y")) {
					database = "NPS_TEST";
				}

				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(configuration.getStringProperty("sqlDB-host") + " ;user="
						+ configuration.getStringProperty("sqlDB-username") + ";password="
						+ configuration.getStringProperty("sqlDB-password") + ";database=" + database + "");

				connection.setAutoCommit(true);
				keepInMemory.setiARMSQLDBConnection(connection);
				connectionSucessful = true;
			} else if (databaseType.equals("CXMSQL")) {
				System.out.println("Inside CXMSQL");
				boolean connectionSucessful = false;
				String database = configuration.getStringProperty("cxm-DB-databaseName-st");
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				System.out.println("---------------");
				System.out.println(configuration.getStringProperty("cxm-DB-host-st") + " ;user="
						+ configuration.getStringProperty("cxm-DB-username-st") + ";password="
						+ configuration.getStringProperty("cxm-DB-password-st") + ";database=" + database + "");
				connection = DriverManager.getConnection(configuration.getStringProperty("cxm-DB-host-st") + " ;user="
						+ configuration.getStringProperty("cxm-DB-username-st") + ";password="
						+ configuration.getStringProperty("cxm-DB-password-st") + ";database=" + database + "");

				connection.setAutoCommit(true);
				keepInMemory.setCxmSQLDBConnection(connection);
				connectionSucessful = true;
			} else if (databaseType.equals("DB2")) {
				System.out.println("Write the code for DB2");
			}

			else if (databaseType.equals("MYSQL")) {
				System.out.println("Write the code for MYSQL");
			} else if (databaseType.equals("MONGO")) {
				System.out.println("Write the code for MONGO");
			}
		} catch (SQLException ex) {
			ex.getMessage();
		}
	}

	public void insertUpdateDeleteQueryToDB(String query, String dataBaseType)
			throws ClassNotFoundException, SQLException {
		try {
			if (dataBaseType.equals("iARMMSSQL")) {
				connection = keepInMemory.getiARMSQLDBConnection();
			} else if (dataBaseType.equals("CXMSQL")) {
				connection = keepInMemory.getCxmSQLDBConnection();
			}

			if (connection == null) {
				dbConnectionOpen(dataBaseType);
			}
			System.out.println(query);
			connection.createStatement().execute(query);
			connection.commit();
		} catch (Exception e) {
			keepInMemory.setErrorMessage(
					"Exception  occurred at function insertUpdateDeleteQueryToDB");
			System.out.println(keepInMemory.getErrorMessage());
			Assert.fail(keepInMemory.getErrorMessage());
		} finally {
			dbConnectionClose(dataBaseType);
		}
	}

	public String retrieveQueryFromDB(String query, String dataBaseType) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		try {
			if (dataBaseType.equals("iARMMSSQL")) {
				connection = keepInMemory.getiARMSQLDBConnection();
			} else if (dataBaseType.equals("CXMSQL")) {
				connection = keepInMemory.getCxmSQLDBConnection();
			}

			if (connection == null) {
				dbConnectionOpen(dataBaseType);
			}

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (!rs.next()) {
				System.out.println("Data not Found for given query-->" + query);
				Assert.fail("Data not Found for given query-->" + query);
			}

		} catch (Exception e) {
			keepInMemory.setErrorMessage("Exception " + e.getMessage() + " occurred for query-->" + query);
			System.out.println(keepInMemory.getErrorMessage());
			Assert.fail("Exception " + e.getMessage() + " occurred for query-->" + query);
		} finally {
			dbConnectionClose(dataBaseType);
		}
		return rs.getString(1);
	}

	public ArrayList retrieveListFromDB(String query, String dataBaseType) throws ClassNotFoundException, SQLException {
		ArrayList resultSetList = new ArrayList();
		ResultSet rs = null;
		try {

			if (dataBaseType.equals("iARMMSSQL")) {
				connection = keepInMemory.getiARMSQLDBConnection();
			} else if (dataBaseType.equals("CXMSQL")) {
				connection = keepInMemory.getCxmSQLDBConnection();
			}

			if (connection == null) {
				dbConnectionOpen(dataBaseType);
			}

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (!rs.next()) {
				System.out.println("Data not Found for given query-->" + query);
				Assert.fail("Data not Found for given query-->" + query);
			}
			while (rs.next()) {
				resultSetList.add(rs.getString(1));
			}

		} catch (Exception e) {
			keepInMemory.setErrorMessage("Exception " + e.getMessage() + " occurred for query-->" + query);
			System.out.println(keepInMemory.getErrorMessage());
			Assert.fail("Exception " + e.getMessage() + " occurred for query-->" + query);
		} finally {
			dbConnectionClose(dataBaseType);
		}
		return resultSetList;
	}

	public ResultSet retrieveMapFromDB(String query, String dataBaseType) throws SQLException {
		ResultSet rs = null;
		try {
			if (dataBaseType.equals("iARMMSSQL")) {
				connection = keepInMemory.getiARMSQLDBConnection();
			} else if (dataBaseType.equals("CXMSQL")) {
				connection = keepInMemory.getCxmSQLDBConnection();
			}

			if (connection == null) {
				dbConnectionOpen(dataBaseType);
			}
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (!rs.next()) {
				System.out.println("Data row not Found for given query-->" + query);
				Assert.fail("Data row not Found for given query-->" + query);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnectionClose(dataBaseType);
		}
		return rs;
	}

	public void dbConnectionClose(String dataBaseType) throws SQLException {
		
		if (dataBaseType.equals("iARMMSSQL")) {
			connection = keepInMemory.getiARMSQLDBConnection();
		} else if (dataBaseType.equals("CXMSQL")) {
			connection = keepInMemory.getCxmSQLDBConnection();
		}
		if (connection != null && connection.isClosed()) {
			connection.close();
		}
	}

}
