package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SkuConfigDB {
	private Context context;
	private Database database;

	@Inject
	public SkuConfigDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}
	public String getRatio1To2(String packConfig) throws SQLException, ClassNotFoundException {
		String trackingLevel = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select RATIO_1_TO_2 from SKU_CONFIG WHERE CONFIG_ID ='" + packConfig + "'");
		rs.next();
		trackingLevel = (rs.getString(1));
		return trackingLevel;
	}

}
