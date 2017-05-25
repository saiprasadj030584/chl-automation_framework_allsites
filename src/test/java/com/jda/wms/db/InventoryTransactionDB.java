package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class InventoryTransactionDB {

	private final Context context;
	private final Database database;

	@Inject
	public InventoryTransactionDB(Context context, Database database) {
		this.context = context;
		this.database = database;
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
				"Select  a.final_loc_id, a.from_loc_id, a.to_loc_id,a.update_qty, a.reference_id, a.expiry_dstamp,a.user_id,a.work_group,a.rdt_user_mode,a.supplier_id, a.pallet_id,a.config_id, a.sku_id,a.uploaded,a.uploaded_filename,a.uploaded_dstamp,a.uploaded_tm  from inventory_transaction A   where A.tag_id = '"
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

		inventoryTransactionMap.put("Pallet Type", resultSet.getString(11));
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
		resultSet = stmt.executeQuery(this.getInventoryrTransactionUrn(tagId, code));
		resultSet.next();

		inventoryTransactionMap.put("Tag Id", resultSet.getString(1));
		inventoryTransactionMap.put("URN Child", resultSet.getString(2));

		return inventoryTransactionMap;
	}

	private String getInventoryrTransactionUrn(String tagId, String code) {
		return "Select  tag_id,user_def_note_2 from inventory_transaction    where tag_id = '" + tagId
				+ "' and A.code = '" + code + "'";
	}

	public HashMap<String, String> getInventoryTransactionCEUDDetails(String tagId, String code)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryTransactionMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(this.getInventoryrTransactionCEUD(tagId, code));
		resultSet.next();

		inventoryTransactionMap.put("Originator", resultSet.getString(1));
		inventoryTransactionMap.put("Originator Reference", resultSet.getString(2));
		inventoryTransactionMap.put("Consignment Id", resultSet.getString(3));
		inventoryTransactionMap.put("Document DueDate", resultSet.getString(4));

		inventoryTransactionMap.put("Original Rotation Id", resultSet.getString(5));
		inventoryTransactionMap.put("Rotation Id", resultSet.getString(6));
		inventoryTransactionMap.put("Receipt Type", resultSet.getString(7));
		inventoryTransactionMap.put("Under Bond", resultSet.getString(8));

		inventoryTransactionMap.put("ABV", resultSet.getString(9));
		inventoryTransactionMap.put("Vintage", resultSet.getString(10));

		inventoryTransactionMap.put("Storage Location", resultSet.getString(11));
		inventoryTransactionMap.put("Base UOM", resultSet.getString(12));
		inventoryTransactionMap.put("Case Ratio", resultSet.getString(13));
		inventoryTransactionMap.put("Into Destination Date", resultSet.getString(14));

		return inventoryTransactionMap;
	}

	private String getInventoryrTransactionCEUD(String tagId, String code) {
		return "Select ce_originator, ce_originator_reference, ce_consignment_id, ce_document_dstamp,ce_orig_rotation_id, ce_rotation_id, ce_receipt_type, ce_under_bond,user_def_num_3, user_def_type_7,user_def_type_3,user_def_type_5,user_def_type_6,user_def_date_1  from inventory_transaction  where tag_id = '"
				+ tagId + "' and A.code = '" + code + "'";
	}

}
