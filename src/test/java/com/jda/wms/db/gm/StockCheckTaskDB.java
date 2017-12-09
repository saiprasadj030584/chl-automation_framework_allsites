package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class StockCheckTaskDB {
	private final Context context;
	private final Database database;

	@Inject
	public StockCheckTaskDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getListId(String TagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select list_id from stock_check_tasks where tag_id ='" + TagId + "' ");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

}









