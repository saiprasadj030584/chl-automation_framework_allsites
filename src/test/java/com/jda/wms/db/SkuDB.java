package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SkuDB {
	private Context context;
	private Database database;

	@Inject
	public SkuDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}
	
	public String getAllocationGroup(String sku) throws SQLException, ClassNotFoundException {
		String allocationGroup = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select ALLOCATION_GROUP from SKU WHERE SKU_ID ='" + sku + "'");
		rs.next();
		allocationGroup = (rs.getString(1));
		return allocationGroup;
	}
}
