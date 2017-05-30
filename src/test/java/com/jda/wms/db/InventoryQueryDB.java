package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class InventoryQueryDB {

	private final Context context;
	private final Database database;

	@Inject
	public InventoryQueryDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getSiteId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select site_id from inventory where tag_id = '" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}

	public HashMap<String, String> getInventoryQueryDetails(String tagId) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryQueryMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"select site_id, location_id, lock_status, qty_on_hand, zone_1, expiry_dstamp, pallet_config, receipt_id, supplier_id, created_inventory,user_def_type_3, user_def_type_5, user_def_type_8  from inventory where tag_id =  '"
						+ tagId + "'");
		resultSet.next();

		inventoryQueryMap.put("SiteId", resultSet.getString(1));
		inventoryQueryMap.put("Location", resultSet.getString(2));
		inventoryQueryMap.put("Lock Status", resultSet.getString(3));
		inventoryQueryMap.put("QtyOnHand", resultSet.getString(4));
		inventoryQueryMap.put("Location Zone", resultSet.getString(5));

		inventoryQueryMap.put("Expiry Date", resultSet.getString(6));
		inventoryQueryMap.put("Pallet Type", resultSet.getString(7));
		inventoryQueryMap.put("Receipt Id", resultSet.getString(8));
		inventoryQueryMap.put("Supplier Id", resultSet.getString(9));
		inventoryQueryMap.put("Created By", resultSet.getString(10));

		inventoryQueryMap.put("Storage Location", resultSet.getString(11));
		inventoryQueryMap.put("Base UOM", resultSet.getString(12));
		inventoryQueryMap.put("Product Group", resultSet.getString(13));

		return inventoryQueryMap;
	}

	public String getABV(String tagID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_num_3 from inventory where tag_id='" + tagID + "'");
		rs.next();
		return rs.getString(1);
	}
}
