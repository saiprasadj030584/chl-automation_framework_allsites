package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		String trackingLevel = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select TRACKING_LEVEL from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		rs.next();
		trackingLevel = (rs.getString(1));
		return trackingLevel;
	}

	public String getQtyOrdered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String qtyOrdered = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_ordered from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		rs.next();
		qtyOrdered = (rs.getString(1));
		return qtyOrdered;
	}

	public String getCaseRatio(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String caseRatio = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_6 from ORDER_LINE WHERE order_id ='" + orderID
				+ "' and sku_id = '" + skuID + "'");
		rs.next();
		caseRatio = (rs.getString(1));
		return caseRatio;
	}

	public String getQtyTasked(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String qtytasked = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_tasked from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		rs.next();
		qtytasked = rs.getString(1);
		return qtytasked;
	}

	public String getBackOrdered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		String backOrdered = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select back_ordered from ORDER_LINE WHERE order_id = '" + orderID + "' and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);

	}

	public ArrayList<String> getsku(String orderId) throws SQLException, ClassNotFoundException {
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

	public String getPackConfig(String orderId, String skuID) throws ClassNotFoundException, SQLException {
		String packConfig = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CONFIG_ID from ORDER_LINE WHERE order_id ='" + orderId + "' and sku_id = '" + skuID + "'");
		rs.next();
		packConfig = (rs.getString(1));
		return packConfig;
	}
		
}
