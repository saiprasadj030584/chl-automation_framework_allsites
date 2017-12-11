package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

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
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println(	"select consignment from order_header where order_id = '" + orderID + "' ");
		 rs = stmt
				.executeQuery(	"select consignment from order_header where order_id = '" + orderID + "' ");
		 if (!rs.next()) {
				context.setErrorMessage("consignment not found for the orderID :");
				Assert.fail("consignment not found for the orderID :");
			}else
			{
				System.out.println("consignment Found for Customer :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! consignment not found for the orderID :");
				Assert.fail("Exception Caught !!! consignment not found for the orderID :" );
				

			}
			return rs.getString(1);
		}
}












