package com.jda.wms.db;

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

	public void updateTagIdAsReleaseStatus(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update move_task set status = 'Released' where tag_id = '" + tagId + "'");
		context.getConnection().commit();
		rs.next();
	}
}
