package com.jda.wms.dataload;

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
	public void deletePreAdviceHeader(String poId) throws ClassNotFoundException, SQLException {
		try {
			if (selectDataFromDB.isPreAdviceRecordExists(poId)){
				if (context.getConnection() == null) {
					database.connect();
				}
				Statement stmt = context.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("delete FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '"+poId+"'");
				context.getConnection().commit();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteOrderHeader(String orderId) throws ClassNotFoundException, SQLException {
		try {
			if (selectDataFromDB.isPreAdviceRecordExists(orderId)){
				if (context.getConnection() == null) {
					database.connect();
				}
				Statement stmt = context.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("delete  from order_header where order_id = '"+orderId+"'");
				context.getConnection().commit();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
