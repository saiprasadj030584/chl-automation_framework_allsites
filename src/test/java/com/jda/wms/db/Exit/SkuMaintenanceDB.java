package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SkuMaintenanceDB {
	
	private final Context context;
	private final Database database;
	
	@Inject
	public SkuMaintenanceDB( Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	
	public String getProductGroup(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select product_group from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getAllocationGroup(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select allocation_group from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getCurrentVintage(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_num_3 from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getCEAlcoholicStrength(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CE_ALCOHOLIC_STRENGTH from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	

}
