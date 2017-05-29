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

	public ArrayList<String> getTagID(String taskId) throws SQLException, ClassNotFoundException {
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

	public ArrayList<String> getSkuID(String taskId) throws SQLException, ClassNotFoundException {
		ArrayList<String> skuId = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT sku_ID from move_task where task_id = '"+taskId+"' AND list_id is null");
		while(rs.next())  {
			skuId.add((rs.getString(1)));
		}
		return skuId;

	}

	public ArrayList<String> getQtyToMove(String taskId) throws SQLException, ClassNotFoundException {
		ArrayList<String> qtyToMove = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT qty_to_move from move_task where task_id = '"+taskId+"' AND list_id is null");
		while(rs.next())  {
			qtyToMove.add((rs.getString(1)));
		}
		return qtyToMove;
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

}
