
package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderHeaderDB {
	private Context context;
	private Database database;

	@Inject
	public OrderHeaderDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getOrderStatus(String orderID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderID + "'");
		rs.next();
		String orderStatus = rs.getString(1);
		context.setOrderStatus(orderStatus);
		return orderStatus;
	}
}