package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class StockCheckTaskDB {
	private final Context context;
	private final Database database;

	@Inject
	public StockCheckTaskDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getListId(String TagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select list_id from stock_check_tasks where tag_id ='" + TagId + "' ");
		rs.next();
		return rs.getString(1);
	}

}
