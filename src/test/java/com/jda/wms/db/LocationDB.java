package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class LocationDB {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Database database;

	@Inject
	public LocationDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String geCheckString(String location) throws SQLException, ClassNotFoundException {
		String result = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select check_string from location where location_id = '" + location + "'");
		rs.next();
		result = rs.getString(1);
		return result;
	}
}
