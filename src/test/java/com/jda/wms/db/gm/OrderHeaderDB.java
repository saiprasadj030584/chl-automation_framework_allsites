package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

	public String getStatus(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getOrderDate(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ORDER_DATE from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCreatedBy(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CREATED_BY from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCreationDate(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CREATION_DATE from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getMoveTaskStatus(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select MOVE_TASK_STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getFromSiteId(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select FROM_SITE_ID from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getType(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ORDER_TYPE from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getNumberOfLines(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select NUM_LINES from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCustomer(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT CUSTOMER_ID FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getName(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT NAME FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getAddress1(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ADDRESS1 FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCountry(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNTRY FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
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

	public String getShipByDate(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SHIP_BY_DATE from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDeliverByDate(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select DELIVER_BY_DATE from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDeliveryType(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_8 from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getIfosOrderNum(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_2 from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDeliveryByDateUserDefinedTab(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_DATE_1 from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getHub(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select HUB_ADDRESS_ID from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getHubName(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select HUB_CONTACT from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getHubCountry(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select HUB_COUNTRY from order_header where order_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getSoftAllocated(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select soft_allocated from order_header where order_id='" + orderId + "'");
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
}
