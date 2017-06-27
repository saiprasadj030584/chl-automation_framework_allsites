package com.jda.wms.dataload.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class SelectDataFromDB {
	private Context context;
	private Database database;

	@Inject
	public SelectDataFromDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}
	
	public boolean isPreAdviceRecordExists(String preAdviceId) throws ClassNotFoundException {
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
	
	public boolean isOrderRecordExists(String orderId) throws ClassNotFoundException {
		boolean isRecordExists = false;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ORDER_ID FROM ORDER_HEADER WHERE ORDER_ID = '"+orderId+"'");
		rs.next();
		if (rs.getString(1).equals(orderId)){
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
