package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderHeaderMaintenanceDB {
	private Context context;
	private Database database;
	
	@Inject
	public OrderHeaderMaintenanceDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}

	public String getStatus(String orderId) throws SQLException, ClassNotFoundException {
		String status = null;
		if (context.getConnection()==null){
			database.connect();
		}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select STATUS from ORDER_HEADER where ORDER_ID = '" + orderId + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next())  {
				for(int j=1;j <= columns;j++){
					status = (rs.getString(j));
				}
			}
		return status;
	}
}
