package com.jda.wms.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class InventoryQueryDb {
	private Context context;
	private Connection connection;
	private Database database;

	@Inject
	public InventoryQueryDb(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getABV(String tagId) throws ClassNotFoundException, SQLException {
		String abv = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_num_3 from inventory where tag_id='" + tagId + "'");
		rs.next();
		abv = rs.getString(1);
		return abv;
	}

}
