package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class TrailerDB {

	private Context context;
	private Database database;

	@Inject
	public TrailerDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getTrailerDetails(String trailerID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TRAILER_TYPE from trailer where trailer_id = '" + trailerID + "'");
		rs.next();
		return rs.getString(1);
	}
}
