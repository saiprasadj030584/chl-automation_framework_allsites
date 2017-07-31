package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PickFaceTableDB {

	private Context context;
	private Database database;

	@Inject
	public PickFaceTableDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getPickfaceLocation(String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from pick_face where sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getQuantityOnHand(String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select QTY_ON_HAND from pick_face where sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

}
