package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;


public class ProductGroupDB {
	private  Context context;
	private Database database;
	
	@Inject
	public ProductGroupDB( Context context,Database database) {
		this.context = context;
		this.database=database;

	}

	public void updatenotes(String skuid) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		//System.out.println("select zone_1 from location where location_id = '" + location + "'");
		stmt.executeUpdate("update PRODUCT_GROUP set notes = '100' where PRODUCT_GROUP in (select family_group from sku where sku_id = '"+skuid+"')");
		
	}

}
