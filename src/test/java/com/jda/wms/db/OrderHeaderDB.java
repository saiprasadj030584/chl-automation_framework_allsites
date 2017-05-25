package com.jda.wms.db;

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
		String status = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
		rs.next();
		status = (rs.getString(1));
		System.out.println("Status in db " + status);
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
		String consignment = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select consignment from order_header where order_id='" + orderId + "'");
		rs.next();
		consignment = rs.getString(1);
		context.setSTOConsignment(consignment);
		return consignment;
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
		String shipByDate = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SHIP_BY_DATE from order_header where order_id='" + orderId + "'");
		rs.next();
		shipByDate = rs.getString(1);
		context.setSTOConsignment(shipByDate);
		return shipByDate;
	}

	public String getDeliverByDate(String orderId) throws ClassNotFoundException, SQLException {
		String deliveryByTime = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select DELIVER_BY_DATE from order_header where order_id='" + orderId + "'");
		rs.next();
		deliveryByTime = rs.getString(1);
		context.setSTOConsignment(deliveryByTime);
		return deliveryByTime;
	}

	public String getDeliveryType(String orderId) throws ClassNotFoundException, SQLException {
		String deliveryType = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_8 from order_header where order_id='" + orderId + "'");
		rs.next();
		deliveryType = rs.getString(1);
		context.setSTOConsignment(deliveryType);
		return deliveryType;
	}

	public String getIfosOrderNum(String orderId) throws ClassNotFoundException, SQLException {
		String IFOSNum = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_2 from order_header where order_id='" + orderId + "'");
		rs.next();
		IFOSNum = rs.getString(1);
		context.setSTOConsignment(IFOSNum);
		return IFOSNum;
	}

	public String getDeliveryByDateUserDefinedTab(String orderId) throws ClassNotFoundException, SQLException {
		String deliverbyDate = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_DATE_1 from order_header where order_id='" + orderId + "'");
		rs.next();
		deliverbyDate = rs.getString(1);
		context.setSTOConsignment(deliverbyDate);
		return deliverbyDate;
	}

	public String getHub(String orderId) throws ClassNotFoundException, SQLException {
		String hub = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select HUB_ADDRESS_ID from order_header where order_id='" + orderId + "'");
		rs.next();
		hub = rs.getString(1);
		context.setSTOConsignment(hub);
		return hub;
	}

	public String getHubName(String orderId) throws ClassNotFoundException, SQLException {
		String hubName = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select HUB_CONTACT from order_header where order_id='" + orderId + "'");
		rs.next();
		hubName = rs.getString(1);
		context.setSTOConsignment(hubName);
		return hubName;
	}

	public String getHubCountry(String orderId) throws ClassNotFoundException, SQLException {
		String hubCountry = "";
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select HUB_COUNTRY from order_header where order_id='" + orderId + "'");
		rs.next();
		hubCountry = rs.getString(1);
		context.setSTOConsignment(hubCountry);
		return hubCountry;
	}
}
