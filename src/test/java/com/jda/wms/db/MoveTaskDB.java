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
	public MoveTaskDB(Context context,Database database) {
		this.context = context;
		this.database = database;
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

	public ArrayList<String> getQtyToMove(String orderID) throws SQLException, ClassNotFoundException{
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

	public ArrayList<String> getToPalletID(String orderID) throws SQLException, ClassNotFoundException {
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

	public ArrayList<String> getToContainerID(String orderID) throws SQLException, ClassNotFoundException {
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

	public ArrayList<String> getSkuID(String orderID) throws ClassNotFoundException, SQLException {
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

	public ArrayList<String> getLocation(String orderID) throws ClassNotFoundException, SQLException {
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

	public ArrayList<String> getToLocation(String orderID) throws ClassNotFoundException, SQLException {
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

	public ArrayList<String> getFinalLocation(String orderID) throws ClassNotFoundException, SQLException {
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
}
