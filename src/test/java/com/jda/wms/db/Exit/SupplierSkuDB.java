package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SupplierSkuDB {
	private final Context context;
	private final Database database;

	@Inject
	public SupplierSkuDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}

	public String getDescription(String skuId, String supplierID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ORDER_DATE from ORDER_HEADER where sku_id='" + skuId
				+ "' AND supplier_id ='" + supplierID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSupplierSKU(String skuId, String supplierID) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where sku_id='" + skuId
					+ "' AND supplier_id ='" + supplierID + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}