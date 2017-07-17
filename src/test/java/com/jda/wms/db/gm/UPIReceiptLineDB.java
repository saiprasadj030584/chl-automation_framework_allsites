package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIReceiptLineDB {
	
	private final Context context;
	private final Database database;

	@Inject
	public UPIReceiptLineDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public ArrayList<String> getSkuIdList(String upiId) throws SQLException, ClassNotFoundException {
		ArrayList<String> skuIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select sku_id from upi_receipt_line where pallet_id ='" + upiId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				skuIdList.add((rs.getString(j)));
			}
		}
		return skuIdList;
	}

	public String getLineId(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select line_id from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}
	
	public String getQtyDue(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}

	public void updatePreAdviceID(String preAdviceId, String skuID, String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set PRE_ADVICE_ID = '"+preAdviceId+"' where pallet_id = '" + upiId+ "'   and sku_id = '" + skuID + "' ");
		context.getConnection().commit();
	}
	
	public void update_user_def_note2(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set user_def_note_2 = '"+upiId+"' where pallet_id = '" + upiId+ "'");
		context.getConnection().commit();
	}
	
	public void updateContainerID(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set CONTAINER_ID = '"+upiId+"' where pallet_id = '" + upiId+ "'");
		context.getConnection().commit();
	}

	public void updatePreAdviceLineID(String preAdviceLineId, String skuID, String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set PRE_ADVICE_LINE_ID = '"+preAdviceLineId+"' where pallet_id = '" + upiId+ "'   and sku_id = '" + skuID + "' ");
		context.getConnection().commit();

	}

	public String getPackConfig(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CONFIG_ID from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}
	
	public String fetchTagId(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TAG_ID from upi_receipt_line where pallet_id = '" + upiId
				+ "'");
		rs.next();
		
		return rs.getString(1);
	}
	
	public String fetchUPC(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select User_def_type_4 from upi_receipt_line where pallet_id = '" + upiId
				+ "'");
		rs.next();
		
		return rs.getString(1);
	}
}
