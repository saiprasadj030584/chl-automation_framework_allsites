package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderContainerDB {
	private Context context;
	private Database database;

	@Inject
	public OrderContainerDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getContainerId(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select container_id from order_container where order_id ='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}
}
