package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderHeaderDB {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Database database;

	@Inject
	public OrderHeaderDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getOrderStatus(String orderID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderID + "'");
		rs.next();
		String orderStatus = rs.getString(1);
		context.setOrderStatus(orderStatus);
		return orderStatus;
	}

	public String getShipdock(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ship_dock from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getConsignment(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select consignment from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public HashMap<String, String> getGroupDetails(String orderId) throws SQLException, ClassNotFoundException {
		ResultSet resultSet = null;
		HashMap<String, String> orderGroupDetails = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				" select work_group, order_grouping_id, consignment_grouping_id from order_header WHERE order_id='"
						+ orderId + "'");
		resultSet.next();
		orderGroupDetails.put("WORKGROUP", resultSet.getString(1));
		orderGroupDetails.put("ORDERGROUPINGID", resultSet.getString(2));
		orderGroupDetails.put("CONSIGNMENTGROUPINGID", resultSet.getString(3));
		logger.debug("Order Group Details: " + orderGroupDetails);
		return orderGroupDetails;
	}
}
