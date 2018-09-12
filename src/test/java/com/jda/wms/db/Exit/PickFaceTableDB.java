package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PickFaceTableDB {

	private Context context;
	private Database database;

	@Inject
	public PickFaceTableDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getPickfaceLocation(String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from pick_face where sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}
	public String getPickfaceLocation() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from pick_face where sku_id is not null");
		rs.next();
		return rs.getString(1);
	}


	public String getLocklocation(String status) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select lock_status from location where lock_status not in = '" + status + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getQuantityOnHand(String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select QTY_ON_HAND from pick_face where sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	} 
	public String getSkuWithoutPickFace() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from sku where sku_id not in(select sku_id from pick_face where primary_face='Y')");
		rs.next();
		return rs.getString(1);
	} 
	public String getSku() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from sku where sku_id in(select sku_id from inventory) and  sku_id not in(select sku_id from pick_face where primary_face='Y')");
		rs.next();
		return rs.getString(1);
	} 
	
	public HashMap<String, String> getPickFaceDetails(String tagId, String code)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> pickFaceMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"Select  a.final_loc_id, a.from_loc_id, a.to_loc_id,a.update_qty, a.reference_id, a.expiry_dstamp,a.user_id,a.station_id,a.rdt_user_mode,a.supplier_id, a.user_def_type_3,a.config_id, a.sku_id,a.uploaded,a.uploaded_filename,a.uploaded_dstamp,a.uploaded_tm  from inventory_transaction A   where A.tag_id = '"
						+ tagId + "' and A.code = '" + code + "'");
		resultSet.next();

		pickFaceMap.put("Final Location", resultSet.getString(1));
		pickFaceMap.put("From Location", resultSet.getString(2));
		pickFaceMap.put("To Location", resultSet.getString(3));
		pickFaceMap.put("Update Qty", resultSet.getString(4));
		pickFaceMap.put("Reference", resultSet.getString(5));
		pickFaceMap.put("Expiry Date", resultSet.getString(6));
		pickFaceMap.put("User Id", resultSet.getString(7));
		pickFaceMap.put("Work Station", resultSet.getString(8));
		pickFaceMap.put("RDT User Mode", resultSet.getString(9));
		pickFaceMap.put("Supplier", resultSet.getString(10));

		pickFaceMap.put("Storage Location", resultSet.getString(11));
		pickFaceMap.put("Pack Config", resultSet.getString(12));
		pickFaceMap.put("Sku Id", resultSet.getString(13));

		pickFaceMap.put("Uploaded Status", resultSet.getString(14));
		pickFaceMap.put("Uploaded File Name", resultSet.getString(15));
		pickFaceMap.put("Uploaded Date", resultSet.getString(16));
		pickFaceMap.put("Uploaded Time", resultSet.getString(17));

		return pickFaceMap;
	}

	
	public void deleteRecord(String sku) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		System.out.println("delete from pick_face where sku_id='" + sku + "'");
		Statement stmt = context.getConnection().createStatement();

		ResultSet rs = stmt.executeQuery("delete from pick_face where sku_id='" + sku + "'");
		context.getConnection().commit();
//		rs.next();
	}


}
