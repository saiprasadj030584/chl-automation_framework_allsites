package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIReceiptHeaderDB {
	private static Context context;
	private static Database database;

	@Inject
	public UPIReceiptHeaderDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}

	public String getASN() throws SQLException, ClassNotFoundException {
		String palletID = context.getpalletIDforUPI();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ASN_ID from upi_receipt_header where pallet_id = '" + palletID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getLineID() throws SQLException, ClassNotFoundException {
		String palletID = context.getpalletIDforUPI();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select line_Id from upi_receipt_header where pallet_id = '" + palletID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getStatus(String masterURN) throws SQLException, ClassNotFoundException {
		// String URN = context.getMasterURN();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select status from upi_receipt_header where pallet_id = '" + masterURN + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUpc(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select supplier_sku_id from supplier_sku where  length(supplier_sku_id) <9 and sku_id = '" + SKU
						+ "' and rownum=1");
		rs.next();
		return rs.getString(1);
	}

	public String getQty(String Pallet, String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_due from upi_receipt_line where pallet_id = '" + Pallet + "' and sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public static String getSku(String Pallet) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select sku_id from upi_receipt_line where pallet_id = '" + Pallet + "' and rownum=1");
		rs.next();
		return rs.getString(1);
	}

	public String getSupplier(String Pallet, String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select supplier_id from upi_receipt_line where pallet_id = '" + Pallet
				+ "' and sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public ArrayList<String> getSkuList(String PalletID) throws SQLException, ClassNotFoundException {
		ArrayList<String> SkuList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select sku_id from upi_receipt_line where pallet_id = '" + PalletID + "'");
		ResultSet rs = stmt.executeQuery("select sku_id from upi_receipt_line where pallet_id = '" + PalletID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				SkuList.add((rs.getString(j)));
			}
		}
		return SkuList;

	}

	public ArrayList<String> getUrnList(String ASN) throws SQLException, ClassNotFoundException {
		ArrayList<String> URNList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select pallet_id from upi_receipt_header where asn_id = '" + ASN + "'");
		ResultSet rs = stmt.executeQuery("select pallet_id from upi_receipt_header where asn_id = '" + ASN + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				URNList.add((rs.getString(j)));
			}
		}
		return URNList;
	}

	public String getSupplier1(String Pallet, String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select supplier_id from supplier_sku where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getU_D_8Status(String SKU) throws SQLException, ClassNotFoundException {
		// String URN = context.getMasterURN();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_8 from sku where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getBoxedOrHanging(String skuid) throws SQLException, ClassNotFoundException {
		System.out.println(skuid);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_8 from sku where sku_id='" + skuid + "'");
		rs.next();
		System.out.println(rs);
		return rs.getString(1);

	}

	public String getSupplierId(String skuid) throws SQLException, ClassNotFoundException {
		System.out.println(skuid);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select supplier_id from supplier_sku where sku_id='" + skuid + "'");
		ResultSet rs = stmt.executeQuery("select supplier_id from supplier_sku where sku_id='" + skuid + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getskufromUpc(String Upc) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select sku_id from supplier_sku where supplier_sku_id = '" + Upc + "' and rownum='1'");
		rs.next();
		return rs.getString(1);
	}

	public String getboxorhang(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_8 from sku where sku_id='" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSupplierfromupc(String upc) throws SQLException, ClassNotFoundException {
		System.out.println(upc);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select supplier_id from supplier_sku where supplier_sku_id='" + upc + "' and rownum='1'");
		ResultSet rs = stmt.executeQuery(
				"select supplier_id from supplier_sku where supplier_sku_id='" + upc + "' and rownum='1'");
		rs.next();
		return rs.getString(1);
	}

	public String getEAN(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select supplier_sku_id from supplier_sku where  length(supplier_sku_id) >9 and sku_id = '" + SKU
						+ "' ");
		rs.next();
		return rs.getString(1);
	}

}
