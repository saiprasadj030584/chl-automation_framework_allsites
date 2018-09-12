package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class MoveTaskUpdateDB {

	private Context context;
	private Database database;

	@Inject
	public MoveTaskUpdateDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public void releaseTagId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("update move_task set status = 'Released' where tag_id = '" + tagId + "'");
		ResultSet rs = stmt.executeQuery("update move_task set status = 'Released' where tag_id = '" + tagId + "'");
		context.getConnection().commit();
//		rs.next();
	}
	public void holdTagId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update move_task set status = 'Hold' where tag_id = '" + tagId + "'");
		context.getConnection().commit();
//		rs.next();
	}
	
	public void deleteTagId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("delete from move_task where tag_id = '" + tagId + "'");
		context.getConnection().commit();
//		rs.next();
	}

	public void deleteReplenishTask(String tag) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("delete from move_task where tag_id = '" + tag + "'");
		ResultSet rs = stmt.executeQuery("delete from move_task where tag_id = '" + tag + "'");
		context.getConnection().commit();

	}
}
