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
		String consignment = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select consignment from order_header where order_id='" + orderId + "'");
		rs.next();
		consignment = rs.getString(1);
		return consignment;
	}

	public HashMap<String, String> getGroupDetails(String orderId) throws SQLException, ClassNotFoundException {
		ResultSet resultSet = null;
		HashMap<String, String> orderGroupDetails = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(this.getOrderHeaderQuery(orderId));
		resultSet.next();
		orderGroupDetails.put("WORKGROUP", resultSet.getString(1));
		orderGroupDetails.put("ORDERGROUPINGID", resultSet.getString(2));
		orderGroupDetails.put("CONSIGNMENTGROUPINGID", resultSet.getString(3));
		logger.debug("Order Group Details: " + orderGroupDetails);
		return orderGroupDetails;
	}

	public String getOrderHeaderQuery(String orderId) {
		return " select work_group, order_grouping_id, consignment_grouping_id from order_header WHERE order_id='"
				+ orderId + "'";
	}
}
