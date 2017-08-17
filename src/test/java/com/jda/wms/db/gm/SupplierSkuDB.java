package com.jda.wms.db.gm;

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

	public String getSupplierSKU(String skuId, String supplierID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where sku_id='" + skuId
				+ "' AND supplier_id ='" + supplierID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSupplierId(String upc) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Supplier_Id from supplier_sku where sku_id='" + upc + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getUPC(String skuId) {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Supplier_sku_Id from supplier_sku where sku_id='" + skuId + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getSupplierIdWithSku(String skuId) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Supplier_sku_Id from supplier_sku where sku_id='" + skuId + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getSupplierSKU(String skuId) {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Supplier_sku_Id from supplier_sku where sku_id='" + skuId + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}