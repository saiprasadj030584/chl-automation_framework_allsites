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
	private SelectDataFromDB selectDataFromDB;

	@Inject
	public DeleteDataFromDB(Context context,Database database,SelectDataFromDB selectDataFromDB) {
		this.context = context;
		this.database = database;
		this.selectDataFromDB = selectDataFromDB;
	}
	public void deletePreAdviceHeader(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (selectDataFromDB.isPreAdviceRecordExists(preAdviceId)){
			if (context.getConnection() == null) {
				database.connect();
			}
			System.out.println("delete FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '"+preAdviceId+"'");
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("delete FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '"+preAdviceId+"'");
			context.getConnection().commit();
			System.out.println("Indelete fun");
		}
	}
	
	public void deleteOrderHeader(String orderId) throws ClassNotFoundException, SQLException {
		if (selectDataFromDB.isPreAdviceRecordExists(orderId)){
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("delete FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '"+orderId+"'");
			context.getConnection().commit();
		}
	}
}
