package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SupplierSkuDB {
	private static Context context;
	private static Database database;

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

	public String getSupplierSKU1(String skuId, String supplierID) throws ClassNotFoundException {
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
	public String getSupplierSKU(String skuId) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where sku_id='" + skuId + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	public  String getSupplierId(String skuid) throws SQLException, ClassNotFoundException {
		System.out.println(skuid);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select supplier_id from supplier_sku where sku_id='" + skuid + "'");
		ResultSet rs = stmt
				.executeQuery("select supplier_id from supplier_sku where sku_id='" + skuid + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDeclarationValidity() throws SQLException, ClassNotFoundException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select supplier_id from supplier_sku where sku_id='000000000021071851'");
		ResultSet rs = stmt
				.executeQuery("select supplier_id from supplier_sku where sku_id='000000000021071851'");
		rs.next();
		return rs.getString(1);
	}

	
	public String updatesupplierDeclaration(String sKU) throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sKU);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update supplier_sku set supplier_id=(null) where sku_id='" + sKU + "'");
		context.getConnection().commit();
		
		return null;
	}

	

	public String UpdateOriginal(String supplierdeclaration, String sku) throws SQLException, ClassNotFoundException {
		System.out.println(supplierdeclaration);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set supplier_id='" + supplierdeclaration + "' where sku_id='" + sku + "'");
		context.getConnection().commit();
		
		return null;
	}

	
	}
