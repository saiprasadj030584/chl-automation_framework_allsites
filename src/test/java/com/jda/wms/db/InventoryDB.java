package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class InventoryDB {
	private Context context;
	private Database database;

	@Inject
	public InventoryDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getExpDate(String skuId, String tagId, String location) throws SQLException, ClassNotFoundException {
		String expDate = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select EXPIRY_DSTAMP from INVENTORY where TAG_ID = '"+tagId+"' AND SKU_ID = '"+skuId+"' AND LOCATION_ID = '"+location+"' AND LOCK_STATUS = 'UnLocked'");
		rs.next();
		expDate = (rs.getString(1));
		return expDate;
	}
}
