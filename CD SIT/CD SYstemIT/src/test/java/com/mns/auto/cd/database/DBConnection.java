package com.mns.auto.cd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.memory.KeepInMemory;
import com.mns.auto.cd.utils.DataBase;

public class DBConnection {
	private final Configuration configuration;
	private KeepInMemory keepInMemory;
	private Connection connection;
	private DataBase dataBase;
	public String statusRegion = System.getProperty("USE_DB");
	public String region = System.getProperty("REGION");
	public String metricsDB = System.getProperty("METRICSDB");

	@Inject
	public DBConnection(KeepInMemory keepInMemory, Configuration configuration, DataBase dataBase) {
		this.keepInMemory = keepInMemory;
		this.configuration = configuration;
		this.dataBase = dataBase;
	}

	@SuppressWarnings("unused")
	public void connectDatabase(String databaseType, String application, String region) throws ClassNotFoundException {
		System.out.println("Entered hee");
		try {
			boolean connectionSucessful = false;
			String url = null;
			System.out.println("DATABASE Status region---> " + region);
			if (databaseType.equals("ORACLE")) {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				url = "jdbc:oracle:thin:@//" + dataBase.getCredentials(application, region, "SERVER") + ":"
						+ dataBase.getCredentials(application, region, "PORT") + "/"
						+ dataBase.getCredentials(application, region, "SERVICE_NAME");
				System.out.println(url);
				connection = DriverManager.getConnection(url, dataBase.getCredentials(application, region, "U_NAME"),
						dataBase.getPwd(application, region));
				System.out.println("DB coonection 1" + keepInMemory.getDBConnection());
			} else if (databaseType.equals("SQL")) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(dataBase.getCredentials(application, region, "SERVER")
						+ " ;user=" + dataBase.getCredentials(application, region, "U_NAME") + ";password="
						+ dataBase.getCredentials(application, region, "PWD") + ";database="
						+ dataBase.getCredentials(application, region, "DB_NAME") + "");
				System.out.println("DB coonection 2" + keepInMemory.getDBConnection());
			} else if (databaseType.equals("DB2")) {
				Class.forName("com.ibm.db2.jcc.DB2Driver");

				String DB_URL = "jdbc:db2://HLXC00DH011.UNIX.MARKSANDSPENCERCATE.COM:60000/IAGG";
				System.out.println("****************" + DB_URL);

				connection = DriverManager.getConnection(DB_URL, "ssauser1",
						"ENrIOQ30");
				/*connection = DriverManager.getConnection(DB_URL, "smsapp",
				"smsapp");*/

			} else if (databaseType.equals("MONGO")) {
			}
			connection.setAutoCommit(true);
			keepInMemory.setDBConnection(connection);
			connectionSucessful = true;
			System.out.println("DB coonection 3" + keepInMemory.getDBConnection());
		} catch (SQLException ex) {
			ex.getMessage();
		}
		System.out.println("DB coonection" + keepInMemory.getDBConnection());
		System.out.println("coonection" + keepInMemory.getConnection());
	}

	public void dbConnectionClose() throws SQLException {
		if (keepInMemory.getDBConnection() != null && keepInMemory.getDBConnection().isClosed()) {
			keepInMemory.getDBConnection().close();
		}
	}

	@SuppressWarnings("unused")
	public void dbConnectionOpen(String databaseType) throws ClassNotFoundException {
		try {
			if (databaseType.equals("ORACLE")) {
				boolean connectionSucessful = false;
				Class.forName("oracle.jdbc.driver.OracleDriver");
				if (statusRegion == null) {
					statusRegion = "N";
				} else {
					System.out.println("DATABASE Status region---> " + statusRegion);
				}
				if (statusRegion.equalsIgnoreCase("N")) {
					if (region.equalsIgnoreCase("SIT")) {
						connection = DriverManager.getConnection(configuration.getStringProperty("sit-db-host"),
								configuration.getStringProperty("sit-db-username"),
								configuration.getStringProperty("sit-db-password"));
					} else if (region.equalsIgnoreCase("ST")) {
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
				keepInMemory.setDBConnection(connection);
				connectionSucessful = true;

			} else if (databaseType.equals("iARMMSSQL")) {
				boolean connectionSucessful = false;

				String database = null;

				if (metricsDB == null || metricsDB.equals("0") || metricsDB.equals("N")) {
					database = "NPS_DEV";
				} else if (metricsDB.equals("Y")) {
					database = "NPS_TEST";
				}

				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(configuration.getStringProperty("sqlDB-host") + " ;user="
						+ configuration.getStringProperty("sqlDB-username") + ";password="
						+ configuration.getStringProperty("sqlDB-password") + ";database=" + database + "");

				connection.setAutoCommit(true);
				keepInMemory.setDBConnection(connection);
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

	// getting details from sql-server

	public void insertUpdateDeleteQueryToDB(String query, String dataBaseType, String application, String region)
			throws ClassNotFoundException, SQLException {
		try {
			if (keepInMemory.getDBConnection() == null) {
				connectDatabase(dataBaseType, application, region);
				// dbConnectionOpen(dataBaseType);
			}
			keepInMemory.getDBConnection().createStatement().execute(query);
			keepInMemory.getDBConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnectionClose();
		}
	}

	public String retrieveQueryFromDB(String query, String dataBaseType, String application, String region)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		try {
			if (keepInMemory.getDBConnection() == null) {
				connectDatabase(dataBaseType, application, region);
			}

			Statement stmt = keepInMemory.getDBConnection().createStatement();
			rs = stmt.executeQuery(query);
			if (!rs.next()) {
				System.out.println("Data not Found for given query-->" + query);
				Assert.fail("Data not Found for given query-->" + query);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnectionClose();
		}
		return rs.getString(1);
	}

	public ResultSet retrieveMapFromDB(String query, String dataBaseType, String application, String region)
			throws SQLException {
		ResultSet rs = null;

		try {
			if (keepInMemory.getDBConnection() == null) {
				connectDatabase(dataBaseType, application, region);
			}

			Statement stmt = keepInMemory.getDBConnection().createStatement();
			rs = stmt.executeQuery(query);
			if (!rs.next()) {
				System.out.println("Data row not Found for given query-->" + query);
				Assert.fail("Data row not Found for given query-->" + query);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnectionClose();
		}
		return rs;
	}

}
