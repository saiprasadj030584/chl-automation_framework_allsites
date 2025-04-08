package com.jda.wms.dataload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class UpdateDataFromDB {
	private Context context;
	private Database database;

	@Inject
	public UpdateDataFromDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}

	public void updateMoveTaskStatusInMoveTask(String orderId) 	throws SQLException, ClassNotFoundException {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("UPDATE MOVE_TASK SET STATUS = 'Released' where task_id='"+orderId+"'");
			context.getConnection().commit();
		}
	
	public void updateMoveTaskStatusInOrderHeader(String orderId) 	throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("UPDATE ORDER_HEADER SET  status = 'Allocated' where order_id='"+orderId+"'");
		context.getConnection().commit();
	}
	
	public void updateAddressIntPalletType(String customerId) 	throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update address set user_def_type_3 = '1.6M 1000KG '  where address_id = '"+customerId+"'");
		context.getConnection().commit();
	}
	
	}
