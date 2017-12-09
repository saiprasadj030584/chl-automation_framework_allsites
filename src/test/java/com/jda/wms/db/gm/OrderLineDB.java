package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderLineDB {
	private Context context;
	private Database database;

	@Inject
	public OrderLineDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}

	public String getTrackingLevel(String orderID, String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select TRACKING_LEVEL from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getQtyOrdered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_ordered from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getCaseRatio(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_6 from ORDER_LINE WHERE order_id ='" + orderID
				+ "' and sku_id = '" + skuID + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getQtyTasked(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_tasked from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getBackOrdered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String backOrdered = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select back_ordered from ORDER_LINE WHERE order_id = '" + orderID + "' and sku_id = '" + skuID + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);

	}

	public ArrayList<String> getskuList(String orderId) throws SQLException, ClassNotFoundException {
		System.out.println("select SKU_ID from ORDER_LINE where order_id = '" + orderId + "'");
		ArrayList<String> skuId = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		
		ResultSet rs = stmt.executeQuery("select SKU_ID from ORDER_LINE where order_id = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				skuId.add((rs.getString(j)));
			}
		}
		return skuId;
	}

	public String getsku(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID from ORDER_LINE where order_id = '" + orderId + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getPackConfig(String orderId, String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select CONFIG_ID from ORDER_LINE WHERE order_id ='" + orderId + "' and sku_id = '" + skuID + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getSkuId(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();

		ResultSet rs = stmt.executeQuery("select SKU_ID from order_line where order_id='" + orderId + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
	
	public ArrayList<String> getSkuList(String orderId) throws SQLException, ClassNotFoundException {
		ArrayList<String> skuId = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID from ORDER_LINE where order_id = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				skuId.add((rs.getString(j)));
			}
		}
		return skuId;
	}
	
	public Integer getRecordCountByOrderId(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from order_line where order_id = '" + orderId + "'");
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}
	
	public ArrayList<String> getLocationList(String orderId) throws SQLException, ClassNotFoundException {
		ArrayList<String> locationId = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select origin_id from ORDER_LINE where order_id = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				locationId.add((rs.getString(j)));
			}
		}
		return locationId;
	}
}









