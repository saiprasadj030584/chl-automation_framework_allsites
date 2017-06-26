package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class LocationDB {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Database database;

	@Inject
	public LocationDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getCheckString(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select check_string from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
		 
	}
	
	public String getLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
	} 
	
	public ArrayList<String> getLocation() throws SQLException, ClassNotFoundException {
		ArrayList<String> locationList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where zone_1 LIKE 'A%RESV'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				locationList.add((rs.getString(j)));
			}
		}
		return locationList;
	}

	public String getLocation(String status) throws SQLException, ClassNotFoundException {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select location_id from location where lock_status = '" + status + "'");
			rs.next();
			return rs.getString(1);
	}
}
