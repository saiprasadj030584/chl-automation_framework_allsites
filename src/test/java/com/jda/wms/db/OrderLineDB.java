package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderLineDB {
	private  Context context;
	private Database database;
	
	@Inject
	public OrderLineDB(Context context,Database database) {
		this.context = context;
		this.database = database;

	}
	public  String getTrackingLevel(String orderID ,String skuID) throws ClassNotFoundException, SQLException {
		String trackingLevel = null;
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select TRACKING_LEVEL from ORDER_LINE WHERE order_id ='" + orderID + "','" + skuID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			rs.next();
			trackingLevel= (rs.getString(1));	
			return trackingLevel;
				}
			
		
	
	public String getQtyordered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String qtyOrdered = null; 
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select qty_ordered from ORDER_LINE WHERE order_id ='" + orderID + "','" + skuID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					qtyOrdered= (rs.getString(j));
				}
			}
		return qtyOrdered;

}
	public String getCaseratio(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String caseRatio = null;
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select user_def_type_6 from ORDER_LINE WHERE order_id ='" + orderID + "','" + skuID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					caseRatio= (rs.getString(j));
				}
			}
		return caseRatio;

}
	public String getQtytasked(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String qtytasked = null;
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select qty_tasked from ORDER_LINE WHERE order_id ='" + orderID + "','" + skuID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					qtytasked= (rs.getString(j));
				}
			}
		return qtytasked;

	}
	
	public String getBackOrdered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String backOrdered = null;
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select back_ordered from ORDER_LINE WHERE order_id = '"+orderID+"' and sku_id = '"+skuID+"'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					backOrdered= (rs.getString(j));
				}
			}
		return backOrdered;
	}
}
