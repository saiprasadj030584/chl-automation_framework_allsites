package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderHeaderMaintenanceDB {
	private Context context;
	private Database database;

	@Inject
	public OrderHeaderMaintenanceDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getStatus(String orderId) throws SQLException, ClassNotFoundException {
		String status = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		status = (rs.getString(1));
		return status;
	}

	public String getOrderDate(String orderId) throws SQLException, ClassNotFoundException {
		String orderDate = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ORDER_DATE from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		orderDate = (rs.getString(1));
		return orderDate;
	}

	public String getCreatedBy(String orderId) throws ClassNotFoundException, SQLException {
		String createdBy = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CREATED_BY from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		createdBy = (rs.getString(1));
		return createdBy;
	}

	public String getCreationDate(String orderId) throws ClassNotFoundException, SQLException {
		String creationDate = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CREATION_DATE from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		creationDate = (rs.getString(1));
		return creationDate;
	}

	public String getMoveTaskStatus(String orderId) throws SQLException, ClassNotFoundException {
		String moveTaskStatus = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select MOVE_TASK_STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		moveTaskStatus = (rs.getString(1));
		return moveTaskStatus;
	}

	public String getFromSiteId(String orderId) throws ClassNotFoundException, SQLException {
		String fromSiteId = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select FROM_SITE_ID from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		fromSiteId = (rs.getString(1));
		return fromSiteId;
	}

	public String getType(String orderId) throws ClassNotFoundException, SQLException {
		String type = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ORDER_TYPE from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		type = (rs.getString(1));
		return type;
	}

	public String getNumberOfLines(String orderId) throws SQLException, ClassNotFoundException {
		String noOfLines = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select NUM_LINES from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		noOfLines = (rs.getString(1));
		return noOfLines;
	}

	public String getCustomer(String orderId) throws SQLException, ClassNotFoundException {
		String customer = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT CUSTOMER_ID FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		customer = (rs.getString(1));
		return customer;
	}

	public String getName(String orderId) throws ClassNotFoundException, SQLException {
		String name = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT NAME FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		name = (rs.getString(1));
		return name;
	}

	public String getAddress1(String orderId) throws ClassNotFoundException, SQLException {
		String address1 = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ADDRESS1 FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		address1 = (rs.getString(1));
		return address1;
	}

	public String getCountry(String orderId) throws ClassNotFoundException, SQLException {
		String country = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNTRY FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		country = (rs.getString(1));
		return country;
	}
}
