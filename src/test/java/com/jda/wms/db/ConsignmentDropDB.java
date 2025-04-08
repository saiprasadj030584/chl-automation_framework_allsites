package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ConsignmentDropDB {

	private Context context;
	private Database database;

	@Inject
	public ConsignmentDropDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	
	public boolean isConsignmentDropExist(String consignment) throws SQLException, ClassNotFoundException {
		boolean isRecord = true;
		try{
		
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select * from consignment_drop where consignment='" + consignment + "'");
		ResultSet rs = stmt.executeQuery(
				"select * from consignment_drop where consignment='" + consignment + "'");
		isRecord = rs.next();
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return isRecord;
	}
	
	
}
