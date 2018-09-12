package com.jda.wms.db.Exit;

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
}
