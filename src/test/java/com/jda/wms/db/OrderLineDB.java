package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderLineDB {
	private static Context context;
	private static Database database;

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
		rs.next();
		return rs.getString(1);
	}

	public String getQtyOrdered(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_ordered from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCaseRatio(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_6 from ORDER_LINE WHERE order_id ='" + orderID
				+ "' and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyTasked(String orderID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_tasked from ORDER_LINE WHERE order_id ='" + orderID + "' and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
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

	public ArrayList<String> getskuList(String orderId) throws SQLException, ClassNotFoundException {
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
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CONFIG_ID from ORDER_LINE WHERE order_id ='" + orderId + "' and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getOrderWithSku(String sku) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select order_id from order_line where sku_id='" + sku + "' soft_allocated is not null");
		rs.next();
		return rs.getString(1);
	}
	public void updateSoftAllocated(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update order_header set soft_allocated = 'Y' where order_id='" + orderId + "'");
		context.getConnection().commit();
		rs.next();
	}
	
	public void updateUserdef(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("update order_line set rule_id='FRANCHISE' where order_id='" + orderId + "'");
		ResultSet rs = stmt
				.executeQuery("update order_line set rule_id='FRANCHISE' where order_id='" + orderId + "'");
		ResultSet rs1 = stmt
				.executeQuery("update order_line set user_def_type_4='IntlFranchise' where order_id='" + orderId + "'");
		ResultSet rs2 = stmt
				.executeQuery("update order_line set user_def_type_6='Retail' where order_id='" + orderId + "'");
		context.getConnection().commit();
		rs.next();
		rs1.next();
		rs2.next();
	}
	public static String getStageRoute(String orderId,String sku) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select distinct stage_route_id from order_line where order_id='" + orderId + "' and sku_id='" + sku + "'");
		context.getConnection().commit();
		rs.next();
		return rs.getString(1);
	}
	
	
	public static String getOrderWithPallet(String PalletID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select distinct order_id from order_line where user_def_type_1 in (select user_def_type_1 from upi_receipt_line where pallet_id ='" + PalletID + "')");
		rs.next();
		return rs.getString(1);
	}
}
