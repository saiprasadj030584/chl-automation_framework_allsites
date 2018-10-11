package com.jda.wms.db.Exit;

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

	public ArrayList<String> getListId(String orderID) throws SQLException, ClassNotFoundException {
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
//	public String getUPCDB() throws SQLException, ClassNotFoundException {
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery("select upc from sku where sku_id = '000000000021071852'");
//		rs.next();
//		return (rs.getString(1));
//	}

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
		System.out.println("select LIST_ID FROM move_task where tag_id ='" + tagId + "' and sku_id = '" + skuId + "'");
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
		ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where sku_id = '" + sku + "'and list_id is null");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			/*
			 * for (int j = 1; j <= columns; j++) {
			 * qtyToMove.add((rs.getString(1))); }
			 */
			qtyToMove.add((rs.getString(1)));

		}
		return qtyToMove;
	}

	public ArrayList<String> getReplenishTagIDList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> tagId = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select tag_id from MOVE_TASK where sku_id = '" + sku + "' and list_id is null");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			/*
			 * for (int j = 1; j <= columns; j++) {
			 * tagId.add((rs.getString(j))); }
			 */
			tagId.add((rs.getString(1)));
		}
		return tagId;
	}

	public ArrayList<String> getReplenishListId(String sku, String tagId) throws SQLException, ClassNotFoundException {
		ArrayList<String> replenishList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select list_id from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH' and TAG_ID ='" + tagId + "'");
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
		System.out.println("select config_id FROM move_task where sku_id = '" + skuId + "'");
		ResultSet rs = stmt.executeQuery("select config_id FROM move_task where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public ArrayList<String> getFromLocationList(String orderId) throws ClassNotFoundException, SQLException {
		ArrayList<String>fromLocation = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				fromLocation.add((rs.getString(j)));
			}
		}
		return fromLocation;
		}

	public String getfromLocation(String taskId) throws ClassNotFoundException, SQLException {
		
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + taskId + "'");
		ResultSet rs = stmt.executeQuery(
				"select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + taskId + "'");
		rs.next();
		return rs.getString(1);
	}


	public String getSkuID(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select SKU_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
		ResultSet rs = stmt.executeQuery(
				"select SKU_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
		rs.next();
		return rs.getString(1);
	}


	public String getContainer(String taskId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select container_ID from MOVE_TASK where TASK_ID = '" + taskId + "'");
		ResultSet rs = stmt.executeQuery(
				"select container_ID from MOVE_TASK where TASK_ID = '" + taskId + "'");
		rs.next();
		return rs.getString(1);	
}
	public String getToContainer(String taskId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select container_ID from MOVE_TASK where TASK_ID = '" + taskId + "'");
		ResultSet rs = stmt.executeQuery(
				"select TO_CONTAINER_ID from MOVE_TASK where TASK_ID = '" + taskId + "'");
		rs.next();
		return rs.getString(1);	
}
	public String getContainerid(String taskId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select container_ID from move_task where task_id ='" + taskId + "'");
		ResultSet rs = stmt.executeQuery(
				"select container_ID from move_task where task_id ='" + taskId + "'");
		rs.next();
		return rs.getString(1);	
}
	public String getPalletForTask(String Task) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select pallet_id from move_task where task_id ='" + Task + "'");
		ResultSet rs = stmt.executeQuery(
				"select pallet_id from move_task where task_id ='" + Task + "'");
		rs.next();
		return rs.getString(1);	
}
	public String getRecordForPallet(String Pallet) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select container_id,sku_id  from move_task where pallet_id='" + Pallet + "'");
		ResultSet rs = stmt.executeQuery(
				"select container_id,sku_id  from move_task where pallet_id='" + Pallet + "'");
		rs.next();
		return rs.getString(1);	
}
	public String getQuantity(String taskId) throws ClassNotFoundException, SQLException {
		
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select qty_to_move from MOVE_TASK where TASK_ID = '" + taskId + "'");
		ResultSet rs = stmt.executeQuery(
				"select qty_to_move from MOVE_TASK where TASK_ID = '" + taskId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getList(String taskId,String status) throws ClassNotFoundException, SQLException {
		
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select list_id from MOVE_TASK where TASK_ID ='" + taskId + "'and list_id is not null and status='"+ status +"'");
		ResultSet rs = stmt.executeQuery(
				"select list_id from MOVE_TASK where TASK_ID ='" + taskId + "'and list_id is not null and status='"+ status +"'");
		rs.next();
		return rs.getString(1);
	}
	
public String getList(String taskId) throws ClassNotFoundException, SQLException {
		
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select list_id from MOVE_TASK where TASK_ID ='" + taskId + "'");
		ResultSet rs = stmt.executeQuery(
				"select list_id from MOVE_TASK where TASK_ID ='" + taskId + "'");
		rs.next();
		return rs.getString(1);
	}
public String getPalletId(String taskId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select pallet_id from Move_task where task_id is not null and pallet_id is not null");
	ResultSet rs = stmt.executeQuery(
			"select pallet_id from Move_task where task_id is not null and pallet_id is not null");
	rs.next();
	return rs.getString(1);
}
public String getPallet(String taskId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select pallet_id from Move_task where task_id='" + taskId + "'");
	ResultSet rs = stmt.executeQuery(
			"select pallet_id from Move_task where task_id='" + taskId + "'");
	rs.next();
	return rs.getString(1);
}
public String getListForReleasedStatus(String tagId,String status) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and list_id is not null and status='"+ status +"'");
	ResultSet rs = stmt.executeQuery(
			"select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and list_id is not null and status='"+ status +"'");
	rs.next();
	return rs.getString(1);
}
public String getListForReleasedStatus(String tagId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and task_id='REPLENISH' and status='Released'");
	ResultSet rs = stmt.executeQuery(
			"select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and task_id='REPLENISH' and status='Released'");
	rs.next();
	return rs.getString(1);
}
public String getListForReleasedStatus1(String taskId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select list_id from MOVE_TASK where task_id ='" + taskId + "'");
	ResultSet rs = stmt.executeQuery(
			"select list_id from MOVE_TASK where task_id ='" + taskId + "'");
	rs.next();
	return rs.getString(1);
}
public String getListUsingStatus(String tagId,String Status) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and task_id='REPLENISH' and status='"+Status+"'");
	ResultSet rs = stmt.executeQuery(
			"select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and task_id='REPLENISH' and status='"+Status+"'");
	rs.next();
	return rs.getString(1);
}
public String getTag(String taskId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select tag_id from MOVE_TASK where TASK_ID ='" + taskId + "'");
	ResultSet rs = stmt.executeQuery(
			"select  tag_id from MOVE_TASK where TASK_ID ='" + taskId + "'");
	rs.next();
	return rs.getString(1);
}
public String getQuantityInEach(String taskId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select qty_to_move from MOVE_TASK where TASK_ID = '" + taskId + "'");
	ResultSet rs = stmt.executeQuery(
			"select qty_to_move from MOVE_TASK where TASK_ID = '" + taskId + "'");
	rs.next();
	return rs.getString(1);
}

public String getStatus(String task,String tag) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select status from MOVE_TASK where TASK_ID ='" + task + "' and tag_id='" + tag + "'");
	ResultSet rs = stmt.executeQuery(
			"select status from MOVE_TASK where TASK_ID ='" + task + "' and tag_id='" + tag + "'");
	rs.next();
	return rs.getString(1);
}
public String getStatus(String task) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select status from MOVE_TASK where TASK_ID ='" + task + "'");
	ResultSet rs = stmt.executeQuery(
			"select status from MOVE_TASK where TASK_ID ='" + task + "'");
	rs.next();
	return rs.getString(1);
}


public String getStatusWithTask(String task) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select status from MOVE_TASK where TASK_ID ='" + task + "'");
	ResultSet rs = stmt.executeQuery(
			"select status from MOVE_TASK where TASK_ID ='" + task + "'");
	rs.next();
	return rs.getString(1);
}

public String getUpdatedStatus(String task,String tag) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	
	System.out.println("update move_task set status='Released' where TASK_ID ='" + task + "'and tag_id='" + tag + "'");
	ResultSet rs = stmt.executeQuery(
			"update move_task set status='Released' where TASK_ID ='" + task + "' and tag_id='" + tag + "'");
	context.getConnection().commit();
	System.out.println("select status from MOVE_TASK where TASK_ID ='" + task + "' and tag_id='" + tag + "'");
	ResultSet Rs = stmt.executeQuery("select status from MOVE_TASK where TASK_ID ='" + task + "' and tag_id='" + tag + "'");
	Rs.next();
	return Rs.getString(1);
}

public String getconsignment(String task) throws ClassNotFoundException, SQLException {
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select consignment from MOVE_TASK where TASK_ID ='" + task + "'");
	ResultSet rs = stmt.executeQuery(
			"select consignment from MOVE_TASK where TASK_ID ='" + task + "'");
	rs.next();
	return rs.getString(1);
}


public String getDate()  throws ClassNotFoundException, SQLException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	System.out.println("select dstamp from move_task where task_id='REPLENISH'");
	ResultSet rs = stmt.executeQuery(
			"select dstamp from move_task where task_id='REPLENISH'");
	rs.next();
	return rs.getString(1);
}

public String getToLocation(String orderId) throws ClassNotFoundException, SQLException {

	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select TO_LOC_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
	ResultSet rs = stmt.executeQuery(
			"select TO_LOC_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
	rs.next();
	return rs.getString(1);
	
	
}
public String getFromLocation(String orderId) throws ClassNotFoundException, SQLException {

	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
	ResultSet rs = stmt.executeQuery(
			"select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + orderId + "'");
	rs.next();
	return rs.getString(1);
}

//public ArrayList<String> getTagID(String sku) throws ClassNotFoundException, SQLException {
//
//	if (context.getConnection() == null) {
//		database.connect();
//	}
//
//	Statement stmt = context.getConnection().createStatement();
//	System.out.println("select Tag_id from MOVE_TASK where sku_id ='" + sku + "' and TASK_ID='REPLENISH'" );
//	ResultSet rs = stmt.executeQuery(
//			"select Tag_id from MOVE_TASK where sku_id ='" + sku + "' and TASK_ID='REPLENISH'");
//	rs.next();
//	return rs.getString(1);
//}
public ArrayList<String> getTagID(String sku) throws SQLException, ClassNotFoundException {
	ArrayList<String> TagIdList = new ArrayList<String>();

	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select Tag_id from MOVE_TASK where sku_id ='" + sku + "' and TASK_ID='REPLENISH'" );
	ResultSet rs = stmt
			.executeQuery("select Tag_id from MOVE_TASK where sku_id ='" + sku + "' and TASK_ID='REPLENISH' ");
	ResultSetMetaData rsmd = rs.getMetaData();
	int columns = rsmd.getColumnCount();
	while (rs.next()) {
		for (int j = 1; j <= columns; j++) {
			TagIdList.add((rs.getString(j)));
		}
	}
	return TagIdList;
}

public void deleteRecord(String taskId) throws ClassNotFoundException, SQLException {

	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select * from move_task where task_id= '" + taskId + "'");
	ResultSet rs = stmt.executeQuery(
			"delete from move_task where task_id='" + taskId + "'");
	context.getConnection().commit();
	rs.next();
///	return rs.getString(1);
}

public ArrayList<String> getTask(String skuId) throws ClassNotFoundException, SQLException {
	ArrayList<String> TaskList = new ArrayList<String>();
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select task_id from MOVE_TASK where sku_id='" + skuId + "' and task_id='REPLENISH'");
	ResultSet rs = stmt.executeQuery(
			"select task_id from MOVE_TASK where sku_id='" + skuId + "' and task_id='REPLENISH'");
	ResultSetMetaData rsmd = rs.getMetaData();
	int columns = rsmd.getColumnCount();
	while (rs.next()) {
		for (int j = 1; j <= columns; j++) {
			TaskList.add((rs.getString(j)));
		}
	}
	return TaskList;

	
}

public String getTaskID(String skuId) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select task_id from MOVE_TASK where sku_id='" + skuId + "' and task_id='REPLENISH'");
	ResultSet rs = stmt.executeQuery(
			"select task_id from MOVE_TASK where sku_id='" + skuId + "' and task_id='REPLENISH'");
	context.getConnection().commit();
	rs.next();
	return rs.getString(1);

	
}
 

public String getReplenishQtyToMove(String sku, String tagID) throws ClassNotFoundException, SQLException {
	
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	System.out.println("select QTY_TO_MOVE from MOVE_TASK where sku_id = '" + sku + "'");
	ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where sku_id = '" + sku + "'and tag_id='" + tagID + "'");
	context.getConnection().commit();
	rs.next();
	return rs.getString(1);

}

public String getReplenishTagID(String sku) throws ClassNotFoundException, SQLException {
	if (context.getConnection() == null) {
	database.connect();
}

Statement stmt = context.getConnection().createStatement();
System.out.println("select tag_id from MOVE_TASK where sku_id='" + sku + "' and task_id='REPLENISH'");
ResultSet rs = stmt.executeQuery(
		"select tag_id from MOVE_TASK where sku_id='" + sku + "' and task_id='REPLENISH'");
context.getConnection().commit();
rs.next();
return rs.getString(1);
}

public String getReplenishList(String sku,String tagID) throws ClassNotFoundException, SQLException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt
			.executeQuery("select list_id from MOVE_TASK where sku_id = '" + sku + "' and tag_id='" + tagID + "' AND task_id = 'REPLENISH'");
	context.getConnection().commit();
	rs.next();
	return rs.getString(1);
}

public String getReplenishStatus(String tagId) throws ClassNotFoundException, SQLException {
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and list_id is not null");
	ResultSet rs = stmt.executeQuery(
			"select list_id from MOVE_TASK where TAG_ID ='" + tagId + "'and list_id is not null");
	rs.next();
	return rs.getString(1);
}

public String getListID(String taskId) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select LIST_ID FROM move_task where task_id ='" + taskId + "'");
	ResultSet rs = stmt.executeQuery(
			"select LIST_ID FROM move_task where task_id ='" + taskId + "' ");
	rs.next();
	return rs.getString(1);
}

}


//public String getTag(String task, String skuId) throws ClassNotFoundException, SQLException {
//	if (context.getConnection() == null) {
//		database.connect();
//	}
//
//	Statement stmt = context.getConnection().createStatement();
//	System.out.println("select Tag_id from MOVE_TASK where sku_id ='" + skuId + "' and TASK_ID='" + task + "'");
//	ResultSet rs = stmt.executeQuery(
//			"select Tag_id from MOVE_TASK where sku_id ='" + skuId + "' and TASK_ID='" + task + "'");
//	rs.next();
//	return rs.getString(1);
//}


	



	

