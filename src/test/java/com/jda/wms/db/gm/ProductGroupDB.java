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
	public boolean selectproductgroup() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		ResultSet rs = null;
		Statement stmt = context.getConnection().createStatement();
		//System.out.println("select zone_1 from location where location_id = '" + location + "'");
		rs=stmt.executeQuery("select * from product_group where PRODUCT_GROUP = '"+context.getProductGroup()+"' ");
		if(rs.next())
			return true;
		else
			return false;
	}

	public void updatenotes() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		
		Statement stmt = context.getConnection().createStatement();
		//System.out.println("select zone_1 from location where location_id = '" + location + "'");
		stmt.executeUpdate("update PRODUCT_GROUP set notes = '100' where PRODUCT_GROUP = '"+context.getProductGroup()+"'");
		
	}
	public void insertproductgroup() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		
		Statement stmt = context.getConnection().createStatement();
		//System.out.println("select zone_1 from location where location_id = '" + location + "'");
		stmt.executeUpdate("INSERT INTO PRODUCT_GROUP (CLIENT_ID,PRODUCT_GROUP,SERIAL_UNIQUENESS,NOTES) VALUES ('M+S','"+context.getProductGroup()+"','N','100')");
		
}
}
