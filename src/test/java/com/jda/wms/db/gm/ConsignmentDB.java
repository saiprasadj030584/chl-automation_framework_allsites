package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ConsignmentDB {

	private Context context;
	private Database database;

	@Inject
	public ConsignmentDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}


	public boolean isConsignmentExists(String consignmentId) {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select consignment from consignment where consignmnet ='" + consignmentId + "'");
			rs.next();
			if (rs.getString(1).equals(consignmentId)) {
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
