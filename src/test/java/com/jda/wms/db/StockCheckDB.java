package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class StockCheckDB {

	private Context context;
	private Database database;

	@Inject
	public StockCheckDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	public String getWorkZone(String workZone) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select work_zone from stock_check_tasks where work_zone not in '" + workZone + "'");
		ResultSet rs = stmt
				.executeQuery("select work_zone from stock_check_tasks where work_zone not in '" + workZone + "'");
		rs.next();
		return rs.getString(1); 
	}
	public String getListId(String tagid) throws SQLException, ClassNotFoundException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select distinct list_id from stock_check_tasks where tag_id = '" + tagid + "' order by list_id desc");
		rs.next();
		return rs.getString(1);
	}
}
