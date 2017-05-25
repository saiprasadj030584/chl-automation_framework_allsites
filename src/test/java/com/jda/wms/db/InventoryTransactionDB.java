package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;

public class InventoryTransactionDB {
	private Context context;
	private Database database;

	@Inject
	public InventoryTransactionDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public ArrayList<String> getVehicleLoadITLRecords() throws SQLException, ClassNotFoundException {
		ArrayList<String> palletIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select PALLET_ID from INVENTORY_TRANSACTION where REFERENCE_ID = '"
				+ context.getOrderId() + "' and code ='Vehicle Load'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();

		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				palletIdList.add((rs.getString(j)));
			}
		}
		context.setPalletIDList(palletIdList);
		return palletIdList;
	}

}
