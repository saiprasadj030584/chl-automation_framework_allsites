package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIReceiptLineDB {

	private final Context context;
	private final Database database;

	@Inject
	public UPIReceiptLineDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public void updateContainerID(String upiId) throws SQLException, ClassNotFoundException {
		System.out.println("update upi_receipt_line set CONTAINER_ID = '" + upiId + "' where pallet_id = '" + upiId + "'");
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update upi_receipt_line set CONTAINER_ID = '" + upiId + "' where pallet_id = '" + upiId + "'");
		context.getConnection().commit();
	}

	public ArrayList<String> getSkuIdList(String upiId) throws SQLException, ClassNotFoundException {
		ArrayList<String> skuIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from upi_receipt_line where pallet_id ='" + upiId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				skuIdList.add((rs.getString(j)));
			}
		}
		return skuIdList;
	}

	public ArrayList<String> getSkuIdList(ArrayList<String> upiList) throws SQLException, ClassNotFoundException {
		ArrayList<String> skuIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		for (int i = 0; i < upiList.size(); i++) {
			ResultSet rs = stmt
					.executeQuery("select sku_id from upi_receipt_line where pallet_id ='" + upiList.get(i) + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while (rs.next()) {
				for (int j = 1; j <= columns; j++) {
					skuIdList.add((rs.getString(j)));
				}
			}
		}
		return skuIdList;
	}

	public String getLineId(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select line_id from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyDue(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyReceived(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getUserDefinedType7(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select USER_DEF_TYPE_7 from upi_receipt_line where pallet_id='" + upiId + "'");
		rs.next();
		return rs.getString(1);
	}

	public void updatePreAdviceID(String preAdviceId, String skuID, String upiId)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set PRE_ADVICE_ID = '" + preAdviceId
				+ "' where pallet_id = '" + upiId + "'   and sku_id = '" + skuID + "' ");
		context.getConnection().commit();
	}

	public void updateUserDefNote2(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update upi_receipt_line set user_def_note_2 = '" + upiId + "' where pallet_id = '" + upiId + "'");
		context.getConnection().commit();
	}

	public void updatePreAdviceLineID(String preAdviceLineId, String skuID, String upiId)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set PRE_ADVICE_LINE_ID = '" + preAdviceLineId
				+ "' where pallet_id = '" + upiId + "'   and sku_id = '" + skuID + "' ");
		context.getConnection().commit();

	}

	public String getPackConfig(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CONFIG_ID from upi_receipt_line where pallet_id = '" + upiId
				+ "'   and sku_id = '" + skuID + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getContainer(String upiId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT container_id FROM upi_receipt_line WHERE pallet_id = '" + upiId + "'");
		rs.next();
		return rs.getString(1);
	}

	public boolean isUPIRecordExists(String preAdviceId) throws ClassNotFoundException {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt
					.executeQuery("select Pre_advice_id from upi_receipt_line where pallet_id = '" + preAdviceId + "'");
			rs.next();
			if (rs.getString(1).equals(preAdviceId)) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset"))
				isRecordExists = false;
		}
		return isRecordExists;
	}

	public String fetchTagId(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TAG_ID from upi_receipt_line where pallet_id = '" + upiId + "'");
		rs.next();

		return rs.getString(1);
	}

	public String fetchUPC(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select User_def_type_4 from upi_receipt_line where pallet_id = '" + upiId + "'");
		rs.next();

		return rs.getString(1);
	}

	public String getSkuId(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from upi_receipt_line where pallet_id ='" + upiId + "'");
		rs.next();

		return rs.getString(1);
	}

	public String getRcvQty(String upiId, String skuID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select User_def_type_4 from upi_receipt_line where pallet_id = '" + upiId + "'");
		rs.next();

		return rs.getString(1);
	}

	// public String getSkuId(String upiId) throws SQLException,
	// ClassNotFoundException {
	// ResultSet rs = stmt.executeQuery("select qty_received from
	// upi_receipt_line where pallet_id = '" + upiId
	// + "' and sku_id = '" + skuID + "' ");
	// rs.next();
	// return rs.getString(1);
	// }

	public void updateuserdefnote2(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update upi_receipt_line set user_def_note_2 = '" + upiId + "' where pallet_id = '" + upiId + "'");
		context.getConnection().commit();
	}

	public String getShippingType(String upiId, String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT user_def_type_7 FROM upi_receipt_line WHERE pallet_id = '" + upiId
				+ "' and sku_id='" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public void updateContainerID(String upiId, String sku) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update upi_receipt_line set CONTAINER_ID = '" + upiId
				+ "' where pallet_id = '" + upiId + "' and sku_id='" + sku + "'");
		context.getConnection().commit();
	}
	
	public String getSkuID(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from upi_receipt_line where pallet_id= '"+ upiId +"'");
		rs.next();

		return rs.getString(1);
	}
}
