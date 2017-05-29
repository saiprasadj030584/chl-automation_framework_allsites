package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class TrailerContentsDB {

	private Context context;
	private Database database;

	@Inject
	public TrailerContentsDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getNoOfRecords(String trailerNo) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from trailer_contents where trailer_id = " + trailerNo + "'");
		rs.next();
		return rs.getString(1);
	}
}
