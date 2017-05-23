package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderHeaderMaintenanceDB {
	private Context context;
	private Database database;

	@Inject
	public OrderHeaderMaintenanceDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getOrderStatus(String orderID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER WHERE ORDER_ID = '" + orderID + "'");
		rs.next();
		return rs.getString(1);
	}

}
