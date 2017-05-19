package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String shipdock = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ship_dock from order_header where order_id='" + orderId + "'");
		rs.next();
		shipdock = rs.getString(1);
		return shipdock;
	}

	public String getconsignment(String orderId) throws ClassNotFoundException, SQLException {
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

	public String getWorkGroup(String orderId) throws SQLException, ClassNotFoundException {
		String workGroup = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select work_group from order_header where order_id='" + orderId + "'");
		rs.next();
		workGroup = rs.getString(1);
		return workGroup;

	}

	public String getorderGroupId(String orderId) throws ClassNotFoundException, SQLException {
		String orderGroupId = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select order_grouping_id from order_header where order_id='" + orderId + "'");
		rs.next();
		orderGroupId = rs.getString(1);
		return orderGroupId;

	}

	public String getConsignmentGroupId(String orderId) throws ClassNotFoundException, SQLException {
		String consignmentGroupId = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select consignment_grouping_id from order_header where order_id='" + orderId + "'");
		rs.next();
		consignmentGroupId = rs.getString(1);
		return consignmentGroupId;

	}

}
