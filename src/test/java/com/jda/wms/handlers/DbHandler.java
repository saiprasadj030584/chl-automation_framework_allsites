package com.jda.wms.handlers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class DbHandler {
	private static Context context;
	private static Database database;

	@Inject
	public DbHandler(Context context, Database database) {
		this.context = context;
		this.database = database;

	}
	public String getASN() throws SQLException, ClassNotFoundException {
		String palletID = context.getpalletIDforUPI();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ASN_ID from upi_receipt_header where pallet_id = '" + palletID + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getLineID() throws SQLException, ClassNotFoundException {
		String palletID = context.getpalletIDforUPI();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select line_Id from upi_receipt_header where pallet_id = '" + palletID + "'");
		rs.next();
		return rs.getString(1);
	}
	public String getStatus(String masterURN) throws SQLException, ClassNotFoundException {
		//String URN = context.getMasterURN();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select status from upi_receipt_header where pallet_id = '" + masterURN + "'");
		rs.next();
		return rs.getString(1);
	}
	public String getUpc(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where  length(supplier_sku_id) <9 and sku_id = '" + SKU + "' and rownum=1");
		rs.next();
		return rs.getString(1);
	}
	public String getQty(String Pallet, String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from upi_receipt_line where pallet_id = '" + Pallet + "' and sku_id = '" +SKU + "'");
		rs.next();
		return rs.getString(1);
	}
	public static String getSku(String Pallet) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from upi_receipt_line where pallet_id = '" + Pallet + "' and rownum=1");
		rs.next();
		return rs.getString(1);
	}
	public String getSupplier(String Pallet,String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select supplier_id from upi_receipt_line where pallet_id = '" + Pallet + "' and sku_id = '" +SKU + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public ArrayList<String> getSkuList(String PalletID) throws SQLException, ClassNotFoundException {
		ArrayList<String> SkuList = new ArrayList<String>();
				if (context.getConnection() == null) {
					database.connect();
				}
				Statement stmt = context.getConnection().createStatement();
				System.out.println("select sku_id from upi_receipt_line where pallet_id = '" + PalletID + "'");
				ResultSet rs = stmt
						.executeQuery("select sku_id from upi_receipt_line where pallet_id = '" + PalletID + "'");
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rsmd.getColumnCount();
				while (rs.next()) {
					for (int j = 1; j <= columns; j++) {
						SkuList.add((rs.getString(j)));
					}
				}
				return SkuList;
		
		}
	public boolean tagidcheck(String tagid) throws SQLException, ClassNotFoundException {
		boolean isRecord = true;
		try{
		if (context.getConnection() == null) {
		database.connect();
		}

		 Statement stmt = context.getConnection().createStatement();
		
		ResultSet rs = stmt.executeQuery(
				"select tag_id from move_task where tag_id = '" + tagid + "'");
		isRecord = rs.next();
		}catch(Exception e){
		e.printStackTrace();
		}
		return isRecord;
		}






}
