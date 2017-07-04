package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

public class InventoryDB {
	private Context context;
	private Database database;

	@Inject
	public InventoryDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getExpDate(String skuId, String tagId, String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select EXPIRY_DSTAMP from INVENTORY where TAG_ID = '"+tagId+"' AND SKU_ID = '"+skuId+"' AND LOCATION_ID = '"+location+"' AND LOCK_STATUS = 'UnLocked'");
		rs.next();
		return rs.getString(1);
}

	public String getCustomer(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select customer_id  from inventory_transaction where reference_id ='"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getConsignment(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select consignment  from inventory_transaction where reference_id ='"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getFromStatus(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select from_status from inventory_transaction where reference_id ='"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getToStatus(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select to_status from inventory_transaction where reference_id ='" + reference
				+ "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploadedDate(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  uploaded_dstamp from inventory_transaction where reference_id ='"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploadedFileName(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  uploaded_filename from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploaded(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  uploaded from inventory_transaction where reference_id = '"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDwsPalletRef(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_4 from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getIntoDestinationDate(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_DATE_1 from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getIfosOrderNum(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_2 from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSiteId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select site_id from inventory where tag_id = '" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}

	public HashMap<String, String> getInventoryQueryDetails(String tagId) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> inventoryQueryMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"select site_id, location_id, lock_status, qty_on_hand, zone_1, expiry_dstamp, pallet_config, receipt_id, supplier_id, created_inventory,user_def_type_3, user_def_type_5, user_def_type_8  from inventory where tag_id =  '"
						+ tagId + "'");
		resultSet.next();

		inventoryQueryMap.put("SiteId", resultSet.getString(1));
		inventoryQueryMap.put("Location", resultSet.getString(2));
		inventoryQueryMap.put("Lock Status", resultSet.getString(3));
		inventoryQueryMap.put("QtyOnHand", resultSet.getString(4));
		inventoryQueryMap.put("Location Zone", resultSet.getString(5));

		inventoryQueryMap.put("Expiry Date", resultSet.getString(6));
		inventoryQueryMap.put("Pallet Type", resultSet.getString(7));
		inventoryQueryMap.put("Receipt Id", resultSet.getString(8));
		inventoryQueryMap.put("Supplier Id", resultSet.getString(9));
		inventoryQueryMap.put("Created By", resultSet.getString(10));

		inventoryQueryMap.put("Storage Location", resultSet.getString(11));
		inventoryQueryMap.put("Base UOM", resultSet.getString(12));
		inventoryQueryMap.put("Product Group", resultSet.getString(13));

		return inventoryQueryMap;
	}

	public String getQtyOnHand(String sku, String location, String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_on_hand from Inventory  where sku_id ='" +sku+"' and location_id='"+location+"' and tag_id='"+tagId+"'");
		rs.next();
		return rs.getString(1);
	}

	public String getABV(String tagID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_num_3 from inventory where tag_id='" + tagID + "'");
		rs.next();
		return rs.getString(1);
	}
	
		public String getInventorySKUId(String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from inventory where tag_id='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getStatus(String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select lock_status from inventory where tag_id='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}
	
		public void updateStatus(String status, String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update inventory set lock_status = '" + status + "' where tag_id = '" + tagId + "'");
		context.getConnection().commit();
	}

	public String getLockCode(String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select lock_code from inventory where tag_id='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}

public String getLocation(String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Location_id from inventory where tag_id='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}
	
		public String getCaseRatio(String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_6 from inventory where tag_id='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getQtyOnHand(String tagId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_on_hand from inventory where tag_id='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getTagId(String zone) throws ClassNotFoundException {
		try{
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select tag_id from inventory where ZONE_1='" + zone + "'");
		rs.next();
		return rs.getString(1);
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

	public String getSkuId(String tagID, String zone) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from inventory where tag_id='" + tagID + "' and zone_1 ='"+ zone +"'");
		rs.next();
		return rs.getString(1);
	}

	public String getTagIdWithStatus(String status) throws ClassNotFoundException {
		try{
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select tag_id from inventory where lock_status='"+status+"'");
			rs.next();
			return rs.getString(1);
			}
			catch(Exception e){
				return e.getMessage();
			}
	}

	public String getTagForExpiredSkuWithQtyOnHandMoreThanAllocatedQty() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select inventory.tag_id, inventory.sku_id,inventory.qty_on_hand,inventory.qty_allocated from inventory inner join sku on sku.allocation_group = 'EXPIRY' and sku.sku_id = inventory.sku_id and inventory.qty_on_hand>inventory.qty_allocated");
		rs.next();
		return rs.getString(1);
	}

	public String getTagIdWithBWSSku() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select inventory.tag_id,inventory.user_def_num_3 from inventory inner join sku on sku.product_group = 'F20' and sku.sku_id = inventory.sku_id and inventory.user_def_num_3 is not null");
		rs.next();
		return rs.getString(1);
	}

	public String getSkuId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from inventory where tag_id ='"+tagId+"'");
		rs.next();
		return rs.getString(1);
	}

	public String getTagId() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select tag_id from inventory");
		rs.next();
		return rs.getString(1);
	}

	public String getLocationAfterReceive(String skuId, String upiId, String date) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery("select Location_id from inventory where sku_id = '"+skuId+"' and RECEIPT_DSTAMP like '"+date+"%'");
		ResultSet rs = stmt.executeQuery("select Location_id from inventory where tag_id='"+upiId+"' and sku_id = '"+skuId+"' and RECEIPT_DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyOnHand(String skuId, String location, String upiId, String date)  throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select QTY_ON_HAND from inventory where tag_id='"+upiId+"' and sku_id = '"+skuId+"' and location_id = '"+location+"' and RECEIPT_DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocation(String skuId, String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from inventory where sku_id = '"+skuId+"' and location_id  not like '"+location+"'");
		rs.next();
		return rs.getString(1);
	}

	public String getLocationAfterPutaway(String skuId, String date) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Location_id from inventory where sku_id = '"+skuId+"' and MOVE_DSTAMP like '"+date+"%'");
		rs.next();
		return rs.getString(1);
	}
}
