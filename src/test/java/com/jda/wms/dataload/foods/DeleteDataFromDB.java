package com.jda.wms.dataload.foods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class DeleteDataFromDB {

	private Context context;
	private Database database;

	@Inject
	public DeleteDataFromDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}
	public void deletePreAdviceHeader(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (isRecordExists(preAdviceId)){
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("delete FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '"+preAdviceId+"'");
			context.getConnection().commit();
		}
	}
	private boolean isRecordExists(String preAdviceId) throws ClassNotFoundException {
		boolean isRecordExists = false;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT PRE_ADVICE_ID FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '"+preAdviceId+"'");
		rs.next();
		if (rs.getString(1).equals(preAdviceId)){
			isRecordExists = true;
		}
		}
		catch(SQLException e){
			if (e.getMessage().contains("Exhausted Resultset")){
				isRecordExists=false;
			}
		}
		return isRecordExists;
	}
}
