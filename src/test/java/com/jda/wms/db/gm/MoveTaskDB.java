package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class MoveTaskDB {
	private Context context;
	private Database database;

	@Inject
	public MoveTaskDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public ArrayList<String> getTagIDList(String taskId) throws SQLException, ClassNotFoundException {
		ArrayList<String> tagID = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT tag_id from move_task where task_id = '" + taskId + "' AND list_id is null");
		while (rs.next()) {
			tagID.add((rs.getString(1)));
		}
		return tagID;
	}

	public String getListID(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("select list_id from move_task where task_id ='" + orderId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select list_id from move_task where task_id ='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public ArrayList<String> getListIdList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> listId = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select LIST_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				listId.add((rs.getString(j)));
			}
		}
		return listId;
	}

	public String getListId(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> listId = new ArrayList<String>();
		System.out.println("list id" + "select LIST_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select LIST_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		rs.next();
		return rs.getString(1);
	}

	public ArrayList<String> getQtyToMoveList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> qtyToMove = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				qtyToMove.add((rs.getString(j)));
			}
		}
		return qtyToMove;
	}

	public ArrayList<String> getToPalletIDList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> toPalletID = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TO_PALLET_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				toPalletID.add((rs.getString(j)));
			}
		}
		return toPalletID;
	}

	public ArrayList<String> getPalletIdList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> PalletID = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select PALLET_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				PalletID.add((rs.getString(j)));
			}
		}
		return PalletID;
	}

	public ArrayList<String> getToContainerIDList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> toContainerID = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TO_CONTAINER_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				toContainerID.add((rs.getString(j)));
			}
		}
		return toContainerID;
	}

	public ArrayList<String> getSkuIDList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> skuID = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				skuID.add((rs.getString(j)));
			}
		}
		return skuID;
	}

	public ArrayList<String> getLocationList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> location = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				location.add((rs.getString(j)));
			}
		}
		return location;
	}

	public ArrayList<String> getToLocationList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> toLocation = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TO_LOC_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				toLocation.add((rs.getString(j)));
			}
		}
		return toLocation;
	}

	public ArrayList<String> getFinalLocationList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> finalLocation = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select FINAL_LOC_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				finalLocation.add((rs.getString(j)));
			}
		}
		return finalLocation;
	}

	public Integer getRecordCountByTaskID(String taskID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from MOVE_TASK where TASK_ID = '" + taskID + "'");
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}

	public String getListID(String tagId, String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select LIST_ID FROM move_task where tag_id ='" + tagId + "' and sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public ArrayList<String> getReplenishQtyToMoveList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> qtyToMove = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where sku_id = '" + sku + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				qtyToMove.add((rs.getString(j)));
			}
		}
		return qtyToMove;
	}

	public ArrayList<String> getReplenishTagIDList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> tagId = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select tag_id from MOVE_TASK where sku_id = '" + sku + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				tagId.add((rs.getString(j)));
			}
		}
		return tagId;
	}

	public ArrayList<String> getReplenishListId(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> replenishList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select list_id from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				replenishList.add((rs.getString(j)));
			}
		}
		return replenishList;
	}

	public ArrayList<String> getReplenishLocationList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> location = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select FROM_LOC_ID from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				location.add((rs.getString(j)));
			}
		}
		return location;
	}

	public ArrayList<String> getReplenishToLocationList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> toLocation = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select TO_LOC_ID from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				toLocation.add((rs.getString(j)));
			}
		}
		return toLocation;
	}

	public ArrayList<String> getReplenishFinalLocationList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> finalLocation = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select FINAL_LOC_ID from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				finalLocation.add((rs.getString(j)));
			}
		}
		return finalLocation;
	}

	public String getPackConfig(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select config_id FROM move_task where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPalletId(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select pallet_id from move_task where task_id ='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getTaskType(String date) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Task_TYPE from move_task where DSTAMP like'" + date + "%'");
		rs.next();
		return rs.getString(1);
	}

	public ArrayList getListIDList(String taskId, String skuId) throws ClassNotFoundException {
		ArrayList<String> listIDList = new ArrayList<String>();
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select LIST_ID FROM move_task where task_id ='" + taskId + "' and sku_id = '" + skuId + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while (rs.next()) {
				for (int j = 1; j <= columns; j++) {
					listIDList.add((rs.getString(j)));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in fetching List ID " + e.getMessage());
		} finally {
			return listIDList;
		}
	}

	public Integer getMoveTaskRecordCountBySkuID(String taskID, String skuId)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select count(*) from MOVE_TASK where TASK_ID = '" + taskID + "' AND sku_id = '" + skuId + "'");
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}

	public String getQtyTasked(String orderId, String skuId, String listID)
			throws SQLException, ClassNotFoundException {
		String qty = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where TASK_ID = '" + orderId
					+ "' and sku_id ='" + skuId + "' and list_id='" + listID + "'");
			rs.next();
			qty = rs.getString(1);
			if (null == qty) {
				qty = "0";
			}
		} catch (Exception e) {
			qty = "0";
		} finally {
			return qty;
		}
	}

	public void updateStatus(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update move_task set status = 'Released' where task_id = '" + orderId + "'");
		context.getConnection().commit();
		rs.next();
	}

	public String getTaskType(String date, String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Task_id from move_task where DSTAMP like'" + date + "%' and tag_id='"
				+ tagId + "' and status='Released'");
		rs.next();
		return rs.getString(1);
	}

	public String getListIdString(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> listId = new ArrayList<String>();
		System.out.println("list id" + "select LIST_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select LIST_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		rs.next();
		return rs.getString(1);
	}

	public String getContainerId(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		// ResultSet rs = stmt.executeQuery("select TO_CONTAINER_ID FROM
		// move_task where task_id = '" + orderId + "'");
		ResultSet rs = stmt.executeQuery("SELECT CONTAINER_ID FROM move_task  where task_id = '" + orderId + "'");
		context.getConnection().commit();
		rs.next();
		return rs.getString(1);

	}

	public String selectPalletId(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select pallet_id from move_task where task_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String selectURN(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select container_id from move_task where task_id='" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPalletId(String skuId, String transactionTime, String type) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select pallet_id from move_task where sku_id='" + skuId + "' and task_type='"
				+ type +"' and dstamp like '%" +transactionTime+ "%'" );
		rs.next();
		return rs.getString(1);

	}
	
	public void updateMoveTaskStatus(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("update move_task set status='Released' where task_id='" + orderId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update move_task set status='Released' where task_id='" + orderId + "'");
		context.getConnection().commit();
	}
}
