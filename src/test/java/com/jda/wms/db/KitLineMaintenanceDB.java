package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class KitLineMaintenanceDB {
	private final Context context;
	private final Database database;

	@Inject
	public KitLineMaintenanceDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}

	public String getKitId(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select kit_id from kit_line where sku_id='" + skuId + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getKitQuantity(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select quantity from kit_line where sku_id='" + skuId + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getKidLineID(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select line_id from kit_line where sku_id='" + skuId + "' ");
		rs.next();
		return rs.getString(1);
	}
}
