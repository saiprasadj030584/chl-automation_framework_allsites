package com.jda.wms.db;

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
		ResultSet rs = stmt.executeQuery("SELECT tag_id from move_task where task_id = '"+taskId+"' AND list_id is null");
		while(rs.next())  {
			tagID.add((rs.getString(1)));
		}
		return tagID;
	}


	public ArrayList<String> getListId(String orderID) throws SQLException, ClassNotFoundException{
		ArrayList<String> listId = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select LIST_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					listId.add((rs.getString(j)));
				}
			}
		return listId;
	}
	
	
	
	public ArrayList<String> getQtyToMoveList(String orderID) throws SQLException, ClassNotFoundException{
		ArrayList<String> qtyToMove = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					qtyToMove.add((rs.getString(j)));
				}
			}
		return qtyToMove;
	}

	public ArrayList<String> getToPalletIDList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> toPalletID = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select TO_PALLET_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					toPalletID.add((rs.getString(j)));
				}
			}
		return toPalletID;
	}
	
	public ArrayList<String> getPalletIdList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> PalletID = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select PALLET_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					PalletID.add((rs.getString(j)));
				}
			}
		return PalletID;
	}

	public ArrayList<String> getToContainerIDList(String orderID) throws SQLException, ClassNotFoundException {
		ArrayList<String> toContainerID = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select TO_CONTAINER_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					toContainerID.add((rs.getString(j)));
				}
			}
		return toContainerID;
	}

	public ArrayList<String> getSkuIDList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> skuID = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select SKU_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					skuID.add((rs.getString(j)));
				}
			}
		return skuID;
	}

	public ArrayList<String> getLocationList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> location = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select FROM_LOC_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					location.add((rs.getString(j)));
				}
			}
		return location;
	}

	public ArrayList<String> getToLocationList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> toLocation = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select TO_LOC_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					toLocation.add((rs.getString(j)));
				}
			}
		return toLocation;
	}

	public ArrayList<String> getFinalLocationList(String orderID) throws ClassNotFoundException, SQLException {
		ArrayList<String> finalLocation = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select FINAL_LOC_ID from MOVE_TASK where TASK_ID = '" + orderID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
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
		ResultSet rs = stmt.executeQuery("select LIST_ID FROM move_task where tag_id ='" + tagId + "' and sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}
	

	public ArrayList<String> getReplenishQtyToMoveList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> qtyToMove = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select QTY_TO_MOVE from MOVE_TASK where sku_id = '" + sku + "' AND list_id is null");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					qtyToMove.add((rs.getString(j)));
				}
			}
		return qtyToMove;
	}

	public ArrayList<String> getReplenishTagIDList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> tagId = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select tag_id from MOVE_TASK where sku_id = '" + sku + "' AND list_id is null");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					tagId.add((rs.getString(j)));
				}
			}
		return tagId;
	}
	

	public ArrayList<String> getReplenishListId(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> replenishList = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select list_id from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					replenishList.add((rs.getString(j)));
				}
			}
		return replenishList;
	}

	public ArrayList<String> getReplenishLocationList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> location = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select FROM_LOC_ID from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					location.add((rs.getString(j)));
				}
			}
		return location;
	}

	public ArrayList<String> getReplenishToLocationList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> toLocation = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select TO_LOC_ID from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					toLocation.add((rs.getString(j)));
				}
			}
		return toLocation;
	}

	public ArrayList<String> getReplenishFinalLocationList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> finalLocation = new ArrayList<String>();
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select FINAL_LOC_ID from MOVE_TASK where sku_id = '" + sku + "' AND task_id = 'REPLENISH'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
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
}
		
