package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.utils.Utilities;

public class PreAdviceLineDB {

	private static Context context;
	private static Database database;

	@Inject
	public PreAdviceLineDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public HashMap<String, String> getPreAdviceLineDetails(String preAdviceID)
			throws SQLException, ClassNotFoundException {
		ResultSet resultSet = null;
		HashMap<String, String> preAdviceLineDetailsValue = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"select  Name,Address1,country,USER_DEF_TYPE_1, ce_warehouse_type, ce_tax_warehouse from address where address_id = '"
						+ preAdviceID + "'");
		resultSet.next();

		preAdviceLineDetailsValue.put("Name", resultSet.getString(1));
		preAdviceLineDetailsValue.put("Address1", resultSet.getString(2));
		preAdviceLineDetailsValue.put("Country", resultSet.getString(3));
		preAdviceLineDetailsValue.put("SupplierId", resultSet.getString(4));
		preAdviceLineDetailsValue.put("DefaultSupplierPallet", resultSet.getString(5));
		preAdviceLineDetailsValue.put("CEWarehouseType", resultSet.getString(6));
		preAdviceLineDetailsValue.put("CEWarehouseTax", resultSet.getString(7));

		return preAdviceLineDetailsValue;
	}

	public ArrayList<String> getSkuId(String preAdviceID) throws SQLException, ClassNotFoundException {
		ArrayList<String> skuIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select sku_id from pre_advice_line where pre_advice_id ='" + preAdviceID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				skuIdList.add((rs.getString(j)));
			}
		}
		return skuIdList;
	}

	public String getVintage(String preAdviceID, String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_7 from pre_advice_line where pre_advice_id = '"
				+ preAdviceID + "' and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCaseRatio(String preAdviceID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_6 from pre_advice_line where pre_advice_id = '"
				+ preAdviceID + "'  and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getBaseUOM(String preAdviceID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_5 from pre_advice_line where pre_advice_id = '"
				+ preAdviceID + "'  and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPackConfig(String preAdviceID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select config_id from pre_advice_line where pre_advice_id = '" + preAdviceID
				+ "'  and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public  String getQtyDue(String preAdviceID,String skuid) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from pre_advice_line where pre_advice_id = '" + preAdviceID
				+ "'   and sku_id = '" + skuid + "' ");
		rs.next();
		return rs.getString(1);
	} 
	public String getUpc(String skuid) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where sku_id='" + skuid + "' ");
		if (!rs.next())
		{context.setErrorMessage("Queried data from JDA DB not found");Assert.fail("Queried data from JDA DB not found");} else{System.out.println("Queried data from JDA DB found");}return rs.getString(1);
	}
	
	
	
	public String getLockCode(String preAdviceID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select LOCK_CODE from pre_advice_line where pre_advice_id = '" + preAdviceID
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	} 
	
	
	
	public ArrayList<String> getConsignmentID(String preAdviceID) throws SQLException, ClassNotFoundException {
		ArrayList<String> consignmentIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select CE_CONSIGNMENT_ID from pre_advice_line where pre_advice_id = '" + preAdviceID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				consignmentIdList.add((rs.getString(j)));
			}
		}
		return consignmentIdList;
	}

	public String getPalletType(String preAdviceID, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement(); 
		System.out.println("OPConnection" + context.getConnection());
		System.out.println("select PALLET_CONFIG from pre_advice_line where pre_advice_id = '" + preAdviceID
				+ "'   and sku_id = '" + skuID + "'");
		ResultSet rs = stmt.executeQuery("select PALLET_CONFIG from pre_advice_line where pre_advice_id = '" + preAdviceID
				+ "'   and sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public static String getUserDefType2(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select user_def_type_2 from pre_advice_line where pre_advice_id='" + preAdviceId + "'");
		if (!rs.next()) {context.setErrorMessage("Queried data from JDA DB not found");Assert.fail("Queried data from JDA DB not found");} else{System.out.println("Queried data from JDA DB found");}return rs.getString(1);
	}
	
}
