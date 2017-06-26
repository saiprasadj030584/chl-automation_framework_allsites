package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class InventoryTransactionDB {

	private final Context context;
	private final Database database;

	@Inject
	public InventoryTransactionDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public ArrayList<String> getVehicleLoadITLRecords() throws SQLException, ClassNotFoundException {
		ArrayList<String> palletIdList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select PALLET_ID from INVENTORY_TRANSACTION where REFERENCE_ID = '"
				+ context.getOrderId() + "' and code ='Vehicle Load'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();

		while (rs.next()) {
			for (int j = 1; j <= columns; j++) {
				palletIdList.add((rs.getString(j)));
			}
		}
		context.setPalletIDList(palletIdList);
		return palletIdList;
	}

	public String getDescription(String tagId, String code, String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				" Select  b.Description  from inventory_transaction A inner join sku B on A.Sku_id = B.Sku_id where A.tag_id = '"
						+ tagId + "' and A.code = '" + code + "' and A.sku_id = '" + skuId + "' ");

		rs.next();
		return rs.getString(1);
	}

	public HashMap<String, String> getInventoryTransactionDetails(String tagId, String code)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryTransactionMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"Select  a.final_loc_id, a.from_loc_id, a.to_loc_id,a.update_qty, a.reference_id, a.expiry_dstamp,a.user_id,a.station_id,a.rdt_user_mode,a.supplier_id, a.user_def_type_3,a.config_id, a.sku_id,a.uploaded,a.uploaded_filename,a.uploaded_dstamp,a.uploaded_tm  from inventory_transaction A   where A.tag_id = '"
						+ tagId + "' and A.code = '" + code + "'");
		resultSet.next();

		inventoryTransactionMap.put("Final Location", resultSet.getString(1));
		inventoryTransactionMap.put("From Location", resultSet.getString(2));
		inventoryTransactionMap.put("To Location", resultSet.getString(3));
		inventoryTransactionMap.put("Update Qty", resultSet.getString(4));
		inventoryTransactionMap.put("Reference", resultSet.getString(5));

		inventoryTransactionMap.put("Expiry Date", resultSet.getString(6));
		inventoryTransactionMap.put("User Id", resultSet.getString(7));
		inventoryTransactionMap.put("Work Station", resultSet.getString(8));
		inventoryTransactionMap.put("RDT User Mode", resultSet.getString(9));
		inventoryTransactionMap.put("Supplier", resultSet.getString(10));

		inventoryTransactionMap.put("Storage Location", resultSet.getString(11));
		inventoryTransactionMap.put("Pack Config", resultSet.getString(12));
		inventoryTransactionMap.put("Sku Id", resultSet.getString(13));

		inventoryTransactionMap.put("Uploaded Status", resultSet.getString(14));
		inventoryTransactionMap.put("Uploaded File Name", resultSet.getString(15));
		inventoryTransactionMap.put("Uploaded Date", resultSet.getString(16));
		inventoryTransactionMap.put("Uploaded Time", resultSet.getString(17));

		return inventoryTransactionMap;
	}

	public HashMap<String, String> getInventoryTransactionUrn(String tagId, String code)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryTransactionMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery("Select  tag_id,user_def_note_2 from inventory_transaction    where tag_id = '"
				+ tagId + "' and code = '" + code + "'");
		resultSet.next();

		inventoryTransactionMap.put("Tag Id", resultSet.getString(1));
		inventoryTransactionMap.put("URN Child", resultSet.getString(2));

		return inventoryTransactionMap;
	}

	public HashMap<String, String> getInventoryTransactionCEUDDetails(String tagId, String code)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryTransactionMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"Select ce_originator, ce_originator_reference, ce_consignment_id, ce_document_dstamp,ce_orig_rotation_id, ce_rotation_id, ce_receipt_type, ce_under_bond,user_def_num_3, user_def_type_7,user_def_type_3,user_def_type_5,user_def_type_6,user_def_date_1  from inventory_transaction  where tag_id = '"
						+ tagId + "' and code = '" + code + "'");
		resultSet.next();

		inventoryTransactionMap.put("Originator", resultSet.getString(1));
		inventoryTransactionMap.put("Originator Reference", resultSet.getString(2));
		inventoryTransactionMap.put("Consignment Id", resultSet.getString(3));
		inventoryTransactionMap.put("Document DueDate", resultSet.getString(4));

		inventoryTransactionMap.put("Original Rotation Id", resultSet.getString(5));
		inventoryTransactionMap.put("Rotation Id", resultSet.getString(6));
		inventoryTransactionMap.put("RecType", resultSet.getString(7));
		inventoryTransactionMap.put("Under Bond", resultSet.getString(8));

		inventoryTransactionMap.put("ABV", resultSet.getString(9));
		inventoryTransactionMap.put("Vintage", resultSet.getString(10));

		inventoryTransactionMap.put("Storage Location", resultSet.getString(11));
		inventoryTransactionMap.put("Base UOM", resultSet.getString(12));
		inventoryTransactionMap.put("Case Ratio", resultSet.getString(13));
		inventoryTransactionMap.put("Into Destination Date", resultSet.getString(14));

		return inventoryTransactionMap;
	}
	
	public HashMap<String, String> getInventoryTransactionInvLock(String tagId, String code)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryTransactionMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"Select lock_code, lock_status,uploaded,uploaded_filename from inventory_transaction where tag_id = '"+ tagId + "' and code = '" + code + "'");
		resultSet.next();

		inventoryTransactionMap.put("Lock Code", resultSet.getString(1));
		inventoryTransactionMap.put("Lock Status", resultSet.getString(2));
		inventoryTransactionMap.put("Uploaded Status", resultSet.getString(3));
		inventoryTransactionMap.put("Uploaded File Name", resultSet.getString(4));

		return inventoryTransactionMap;
}

	public ArrayList<String> getFromLocationForUnloading(String palletID) throws SQLException, ClassNotFoundException {
		ArrayList<String> fromLocationList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select FROM_LOC_ID from inventory_transaction where code = 'Vehicle UnLoad' and pallet_id ='"
						+ palletID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				fromLocationList.add((rs.getString(j)));
			}
		}
		return fromLocationList;
	}

	public ArrayList<String> getToLocationForUnloading(String palletID) throws SQLException, ClassNotFoundException {
		ArrayList<String> toLocationList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select TO_LOC_ID from inventory_transaction where code = 'Vehicle UnLoad' and pallet_id ='" + palletID
						+ "'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				toLocationList.add((rs.getString(j)));
			}
		}
		return toLocationList;
	}

	public ArrayList<String> getReferenceNo(String palletID) throws SQLException, ClassNotFoundException {
		ArrayList<String> referenceIdList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select REFERENCE_ID from inventory_transaction where code = 'Vehicle UnLoad' and pallet_id ='"
						+ palletID + "'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				referenceIdList.add((rs.getString(j)));
			}
		}
		return referenceIdList;
	}
	
	public String getLocation(String taskID, String tagID, String dstamp) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select from_loc_id from inventory_transaction where CODE = 'Replenish' and tag_id = '"+tagID+"' and DStamp like '"+dstamp+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getToLocation(String taskID, String tagID, String dstamp) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select to_loc_id from inventory_transaction where CODE = 'Replenish' and tag_id = '"+tagID+"' and DStamp like '"+dstamp+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getFinalLocation(String taskID, String tagID, String dstamp) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select final_loc_id from inventory_transaction where CODE = 'Replenish' and tag_id = '"+tagID+"' and DStamp like '"+dstamp+"%'");
		rs.next();
		return rs.getString(1);
	}


	public String getUpdateQty(String taskID, String tagID, String dstamp) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select update_qty from inventory_transaction where CODE = 'Replenish' and tag_id = '"+tagID+"' and DStamp like '"+dstamp+"%'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getStatus(String tagId, String code, String lockCode, String dstamp)
			throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select lock_status from inventory_transaction where tag_id='" + tagId
				+ "' and code='" + code + "' and lock_code ='" + lockCode + "' and dstamp like '" + dstamp + "%'");
		rs.next();
		return rs.getString(1);
	}
	
		public String getReasonCode(String tagId, String code, String lockCode, String dstamp)
			throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select reason_id from inventory_transaction where tag_id='" + tagId
				+ "' and code='" + code + "' and lock_code ='" + lockCode + "' and dstamp like '" + dstamp + "%'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getUploadedFileName(String tagId, String code, String lockCode, String dstamp)
			throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select uploaded_filename from inventory_transaction where tag_id='" + tagId
				+ "' and code='" + code + "' and lock_code ='" + lockCode + "' and dstamp like '" + dstamp + "%'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploadedValue(String tagId, String code, String lockCode, String dstamp)
			throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select uploaded from inventory_transaction where tag_id='" + tagId
				+ "' and code='" + code + "' and lock_code='" + lockCode + "' and dstamp like '" + dstamp + "%'");
		rs.next();
		return rs.getString(1);
	}
	
		public String getUpdateQty(String tagId, String code, String dstamp, String status, String reasonCode)
			throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select UPDATE_QTY from inventory_transaction where tag_id='" + tagId
				+ "' and code = '" + code + "' and dstamp like '" + dstamp + "%' and lock_status = '" + status
				+ "' and REASON_ID ='" + reasonCode + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getOriginalQty(String tagId, String code, String dstamp, String status, String reasonCode)
			throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ORIGINAL_QTY from inventory_transaction where tag_id='" + tagId
				+ "' and code = '" + code + "' and dstamp like '" + dstamp + "%' and lock_status = '" + status
				+ "' and REASON_ID ='" + reasonCode + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getFromLocation(String skuId, String upiId, String date, String code) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select from_loc_id from inventory_transaction where tag_id='"+upiId+"' and sku_id = '"+skuId+"' and code = '"+code+"' and DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getToLocation(String skuId, String upiId, String date, String code) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select to_loc_id from inventory_transaction where tag_id='"+upiId+"' and sku_id = '"+skuId+"' and code = '"+code+"' and DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getUpdateQty(String skuId, String upiId, String date, String code) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select UPDATE_QTY from inventory_transaction where tag_id='"+upiId+"' and sku_id = '"+skuId+"' and code = '"+code+"' and DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getReferenceId(String skuId, String upiId, String date, String code) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select REFERENCE_ID from inventory_transaction where tag_id='"+upiId+"' and sku_id = '"+skuId+"' and code = '"+code+"' and DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}
}
