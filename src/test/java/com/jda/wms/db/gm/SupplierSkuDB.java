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

	
	
	
	public String getSupplierId(String upc) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Supplier_Id from supplier_sku where supplier_sku_id='" + upc + "' and rownum=1");
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
	
	public String getSupplierIdWithSku(String sku) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			System.out.println("select Supplier_Id from supplier_sku where sku_id='" + sku + "'");
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Supplier_Id from supplier_sku where sku_id='" + sku + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String getSupplierSKU(String skuId, String supplierID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select supplier_sku_id from supplier_sku where sku_id='" + skuId
				+ "' AND supplier_id ='" + supplierID + "'");
		ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where sku_id='" + skuId
				+ "' AND supplier_id ='" + supplierID + "'");
		rs.next();
		return rs.getString(1);
	}

}