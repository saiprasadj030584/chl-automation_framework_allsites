package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PackConfigMaintenanceDB {
	
	private final Context context;
	private final Database database;
	
	@Inject
	public PackConfigMaintenanceDB( Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	
	public String getRatio1To2(String packConfig) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select RATIO_1_TO_2 from sku_config where config_id = '" + packConfig + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getRatio2To3(String packConfig) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select RATIO_2_TO_3 from sku_config where config_id = '" + packConfig + "'");
		rs.next();
		return rs.getString(1);
	}

}
