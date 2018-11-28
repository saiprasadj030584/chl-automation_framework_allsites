package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class MandsDB {
	private static Context context;
	private static Database database;

	@Inject
	public MandsDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}


	public String getUserDefType9(String sku) throws SQLException, ClassNotFoundException {
		System.out.println(sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_9 from mands.extended_udf where sku_id='" + sku + "'");
		ResultSet rs = stmt
				.executeQuery("select user_def_type_9 from mands.extended_udf where sku_id='" + sku + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getUserDefType11(String sku) throws SQLException, ClassNotFoundException {
		System.out.println(sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_11 from mands.extended_udf where sku_id='" + sku + "'");
		ResultSet rs = stmt
				.executeQuery("select user_def_type_11 from mands.extended_udf where sku_id='" + sku + "'");
		rs.next();
		return rs.getString(1);
	}
		
	}


