package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

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
		if (!rs.next()) {context.setErrorMessage("Queried data from JDA DB not found");Assert.fail("Queried data from JDA DB not found");} else{System.out.println("Queried data from JDA DB found");}return rs.getString(1);
	}
	
	public boolean isTrailerExists(String trailerNo) throws SQLException, ClassNotFoundException {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select trailer_id from trailer where trailer_id='" + trailerNo + "'");
			rs.next();
			if (rs.getString(1) != null) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}
}









