package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DockSchedulerDB {
	
	private Context context;
	private Database database;

	@Inject
	public DockSchedulerDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	} 
	
	public String getConsignment(String orderID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select consignment from order_header where order_id = '"+orderID+"' ");
		rs.next();
		return rs.getString(1);
}

}