package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SkuDB {
	private Context context;
	private Database database;

	@Inject
	public SkuDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}

	public String getAllocationGroup(String sku) throws SQLException, ClassNotFoundException {
		String allocationGroup = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ALLOCATION_GROUP from SKU WHERE SKU_ID ='" + sku + "'");
		rs.next();
		allocationGroup = (rs.getString(1));
		return allocationGroup;
	}

	public String getDescription(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select description from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getVintage(String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_NUM_3 from SKU where sku_id = '" + skuID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getProductGroup(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select product_group from sku where sku_id  = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getEAN(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ean from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUPC(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select UPC from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getEachQuantity(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  each_quantity  from sku where sku_id  = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getQuantity(String skuId, String pallet_id) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		// ResultSet rs = stmt.executeQuery("select (qty_due - qty_received) as
		// qty from upi_receipt_line where sku_id = '" + skuId + "' and
		// pallet_id='"+ pallet_id + "'");
		ResultSet rs = stmt.executeQuery("select qty_due from upi_receipt_line where sku_id = '" + skuId
				+ "' and pallet_id='" + pallet_id + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getIsTagMergeUnchecked(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  tag_merge from sku where sku_id  = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String isNewProductChecked(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  new_product from sku where sku_id= '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCEWarehouseType(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ce_warehouse_type from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCEVATCode(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  ce_vat_code from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCESKUcheckedValue(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select ce_customs_excise FROM sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCEAlcoholicStrength(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CE_ALCOHOLIC_STRENGTH FROM sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String ExpiryRequiredUncheckedValue(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select expiry_reqd FROM sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getBaseUOM(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select USER_DEF_TYPE_2 FROM sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSAPCreationStatus(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select USER_DEF_TYPE_7 FROM sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSKUType(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_8 from sku where sku_id = '" + skuId + "' ");
		rs.next();
		return rs.getString(1);
	}

	public String getNewProductCheckValue(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select new_product from sku where sku_id = '" + skuId + "' ");
		rs.next();
		return rs.getString(1);
	}

	public boolean validateSku(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from sku where sku_id = '" + skuId + "'");
		if (rs.next()) {
			return true;
		} else {
			return false;
		}

	}
}
