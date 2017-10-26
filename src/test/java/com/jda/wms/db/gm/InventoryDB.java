package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

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
		ResultSet rs = stmt.executeQuery("select EXPIRY_DSTAMP from INVENTORY where TAG_ID = '" + tagId
				+ "' AND SKU_ID = '" + skuId + "' AND LOCATION_ID = '" + location + "' AND LOCK_STATUS = 'UnLocked'");
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
		ResultSet rs = stmt.executeQuery("select qty_on_hand from Inventory  where sku_id ='" + sku
				+ "' and location_id='" + location + "' and tag_id='" + tagId + "'");
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
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select tag_id from inventory where ZONE_1='" + zone + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getSkuId(String tagID, String zone) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select sku_id from inventory where tag_id='" + tagID + "' and zone_1 ='" + zone + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getTagIdWithStatus(String status) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select tag_id from inventory where lock_status='" + status + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getTagForExpiredSkuWithQtyOnHandMoreThanAllocatedQty() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.tag_id, inventory.sku_id,inventory.qty_on_hand,inventory.qty_allocated from inventory inner join sku on sku.allocation_group = 'EXPIRY' and sku.sku_id = inventory.sku_id and inventory.qty_on_hand>inventory.qty_allocated");
		rs.next();
		return rs.getString(1);
	}

	public String getTagIdWithBWSSku() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.tag_id,inventory.user_def_num_3 from inventory inner join sku on sku.product_group = 'F20' and sku.sku_id = inventory.sku_id and inventory.user_def_num_3 is not null");
		rs.next();
		return rs.getString(1);
	}

	public boolean isSkuInSuspenseLocation(String skuId) throws SQLException, ClassNotFoundException {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select sku_id from Inventory where location_id='SUSPENSE' and sku_id='" + skuId + "'");
			rs.next();
			if (rs.getString(1) != null) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}

	public boolean isSkuInMezz(String skuId) throws SQLException, ClassNotFoundException {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select sku_id from Inventory where sku_id='" + skuId + "' and config_id like '%MEZZ%'");
			rs.next();
			if (rs.getString(1) != null) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}

	public String getQtyOnHandreturns(String skuId, String containerID)

			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select QTY_ON_HAND from inventory where  receipt_id='" + containerID + "' and sku_id = '"

						+ skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getLocationAfterReceiveIdt(String skuId, String containerID)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Location_id from inventory where receipt_id='" + containerID
				+ "' and sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSkuId(String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from inventory where tag_id ='" + tagId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getTagID(String siteID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select tag_id from inventory Where site_id='" + siteID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getLocationAfterReceive(String skuId, String upiId, String date)
			throws SQLException, ClassNotFoundException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();

		ResultSet rs = stmt.executeQuery("select Location_id from inventory where tag_id='" + upiId + "' and sku_id = '"
				+ skuId + "' and RECEIPT_DSTAMP like '" + date + "%'");

		rs.next();
		return rs.getString(1);
	}

	public String getLocationAfterPOReceive(String skuId, String preAdviceId, String date)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("select Location_id from inventory where receipt_id='" + preAdviceId
				+ "' and sku_id = '" + skuId + "' and RECEIPT_DSTAMP like '" + date + "%'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Location_id from inventory where receipt_id='" + preAdviceId
				+ "' and sku_id = '" + skuId + "' and RECEIPT_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyOnHand(String skuId, String location, String upiId, String date)

			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select QTY_ON_HAND from inventory where tag_id='" + upiId + "' and sku_id = '"
				+ skuId + "' and location_id = '" + location + "' and RECEIPT_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyOnHandPO(String skuId, String location, String preAdviceId, String date)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select QTY_ON_HAND from inventory where receipt_id='" + preAdviceId + "' and sku_id = '" + skuId
						+ "' and location_id = '" + location + "' and RECEIPT_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocation(String skuId, String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from inventory where sku_id = '" + skuId
				+ "' and location_id  not like '" + location + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getLocationAfterPutaway(String skuId, String date) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select Location_id from inventory where sku_id = '" + skuId
				+ "' and MOVE_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}

	public String getagId(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select TAG_ID FROM inventory WHERE lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getsku(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID FROM inventory WHERE lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getlocation(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_ID FROM inventory WHERE lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public ArrayList getTagIdDetails(String lockStatus) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID,LOCATION_ID,TAG_ID from INVENTORY where lock_status='"
				+ lockStatus + "' order by sku_id desc");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList getTagIDDetails(String Expiry) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select SKU_ID,LOCATION_ID,TAG_ID from INVENTORY where expired='" + Expiry + "' order by sku_id desc");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList getTagIddetails(String origin) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.sku_id, inventory.LOCATION_ID, inventory.tag_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id and inventory.origin_id='"
						+ origin + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList gettagIddetails(String condition) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID,LOCATION_ID,TAG_ID from INVENTORY where condition_id='"
				+ condition + "' order by sku_id desc");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;

	}

	public ArrayList gettagIddetail(String pallet) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID,LOCATION_ID,TAG_ID from INVENTORY where  pallet_config='"
				+ pallet + "' order by sku_id desc");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList getStockDetails() throws ClassNotFoundException, SQLException {
		ArrayList<String> stockDetails = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.sku_id, inventory.config_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			stockDetails.add((rs.getString(j)));
		}
		return stockDetails;
	}

	public ArrayList getTagIddetail(String owner) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID,LOCATION_ID,TAG_ID from INVENTORY where owner_id=' " + owner
				+ "' order by sku_id desc='");

		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList getTagDetails(String owner) throws ClassNotFoundException, SQLException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select SKU_ID,LOCATION_ID,TAG_ID from INVENTORY where owner_id=' " + owner
				+ "' order by sku_id desc='");

		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList getSkuIdWithMoreThanOnePackConfig() throws ClassNotFoundException, SQLException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT sku_id FROM SKU_SKU_CONFIG GROUP BY sku_id HAVING COUNT( SKU_SKU_CONFIG.sku_id) > 1");

		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public ArrayList getInventoryDetailsForSku(String sku) throws ClassNotFoundException, SQLException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select tag_id,config_id from inventory where sku_id='" + sku + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			inventoryList.add((rs.getString(j)));
		}
		return inventoryList;
	}

	public String getPutawayLocation1(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from inventory where sku_id='" + skuId
				+ "' and location_id not like '%REC%' and location_id not like '%STAGE%' and location_id not like '%DEFAULTLANE%' and location_id not like '%SINBOX%'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocation2(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from inventory where sku_id='" + skuId
				+ "' and location_id not like '%REC%' and location_id not like '%STAGE%' and location_id not like '%DEFAULTLANE%' and location_id not like '%SINBOX%'and location_id not like'"
				+ context.getPutawayLocation1() + "'");

		rs.next();
		return rs.getString(1);
	}

	public String getQtyOnHand(String listID, String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"Select qty_on_hand from inventory where sku_id in (select sku_id from stock_check_tasks where list_id='"
						+ listID + "' ) and tag_id='" + tagId + "'");

		rs.next();
		return rs.getString(1);
	}

	public String getQty(String tagId, String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_on_hand from inventory where tag_id ='" + tagId + "' and location_id = '" + location + "'");

		rs.next();
		return rs.getString(1);
	}

	public String getlocation(String listID, String tagId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"Select location_id from inventory where sku_id in (select sku_id from stock_check_tasks where list_id='"
						+ listID + "' ) and tag_id='" + tagId + "'");

		rs.next();
		return rs.getString(1);
	}
	
	public String getNoOforigins(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from inventory where sku_id='"+skuId+"'");
		rs.next();
		return rs.getString(1);
	}
	
	public HashMap<Integer,List<String>> getQtyOnHandBySkuId(String skuId) throws SQLException, ClassNotFoundException {
		int i=0;
		if (context.getConnection() == null) {
			database.connect();
		}
		HashMap<Integer, List<String>> inventoryList = new HashMap<Integer, List<String>>();
		
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select origin_id,qty_on_hand from inventory where sku_id='"+skuId+"'");
		while (rs.next()) {
			i++;
			List<String> countList = new ArrayList<String>();
			countList.clear();
			countList.add(rs.getString("qty_on_hand"));
			countList.add(rs.getString("origin_id"));
			inventoryList.put(i,countList);
		}
		return inventoryList;
	}
	
	public  String getStockAvailablityRecords(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from inventory where sku_id='" + skuId + "' and location_id not like 'SUSPENSE'");
		rs.next();
		return rs.getString(1);
	}

	public String getPackConfig(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select config_id from inventory where sku_id='" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getLocationAfterPOReceiveForNewPalletID(String skuId, String newpallet, String date)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		
		ResultSet rs = stmt.executeQuery(
				"select inventory.Location_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id and "
						+ "inventory.tag_id ='" + newpallet + "' and RECEIPT_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getLocationAfterReceiveForRandomTag(String skuId,String tagId, String date)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.Location_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id and "
						+ "inventory.tag_id ='" + tagId + "' and RECEIPT_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getQtyOnHandForRandomTag(String skuId, String location, String tagId, String date)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.QTY_ON_HAND from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id and inventory.tag_id ='"
						+ tagId + "'  and inventory.Location_id ='" + location + "' and RECEIPT_DSTAMP like '" + date
						+ "%'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getLocationAfterPOReceiveForRandomTag(String skuId, String tagId, String date)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
System.out.println("select inventory.Location_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id and "
						+ "inventory.tag_id ='" + tagId + "' and RECEIPT_DSTAMP like '" + date + "%'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select inventory.Location_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id and "
						+ "inventory.tag_id ='" + tagId + "' and RECEIPT_DSTAMP like '" + date + "%'");
		rs.next();
		return rs.getString(1);
	}
	
	public int getQtyOnHandNotInSuspense(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> qtyOnHandNotInSuspense = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();	
		ResultSet rs = stmt.executeQuery(
				"SELECT QTY_ON_HAND FROM INVENTORY WHERE SKU_ID = '" + sku + "' and LOCATION_ID NOT IN ('SUSPENSE')");

		while (rs.next()) {
			qtyOnHandNotInSuspense.add(rs.getString(1));
		}
		int i;
		int totalQtyOnHand = 0;
		for (i = 0; i < qtyOnHandNotInSuspense.size(); i++) {
			totalQtyOnHand += Integer.parseInt(qtyOnHandNotInSuspense.get(i));
		}
		return totalQtyOnHand;
	}
	
	public String getToLocationForPutaway(String skuType,String department) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select location_id from location where Zone_1 like '"+skuType+"%' and lock_status='UnLocked'");
		ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '"+skuType+"%' and lock_status='UnLocked' and user_def_type_1='"+department+"'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getToLocationForPutawayBoxed(String skuType) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select location_id from location where Zone_1 like '"+skuType+"%' and lock_status='UnLocked'");
		ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '"+skuType+"%' and lock_status='UnLocked'");
		rs.next();
		return rs.getString(1);
	}
	
	public ArrayList getLocationsForSku(String skuId) throws SQLException, ClassNotFoundException {
		ArrayList<String> inventoryList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("select location_id from inventory where sku_id='"
				+ skuId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from inventory where sku_id='"
				+ skuId + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		System.out.println("Col"+columns);
		while(rs.next()) {
			System.out.println(rs.getString(columns));
			inventoryList.add((rs.getString(columns)));
		}
		return inventoryList;
	}
	
	public String getQtyForSkuInLocation(String skuId,String location) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select qty_on_hand from inventory where sku_id='"+skuId+"' and location_id='"+location+"'");
		rs.next();
		return rs.getString(1);
	}
}
