package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

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
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		allocationGroup = (rs.getString(1));
		return allocationGroup;
	}
	public String getDatatype(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select hanging_garment FROM SKU WHERE sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDescription(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select description from sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getVintage(String skuID) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_NUM_3 from SKU where sku_id = '" + skuID + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getProductGroup(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("select product_group from sku where sku_id  = '" + skuId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select product_group from sku where sku_id  = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getEAN(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ean from sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getUPC(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select UPC from sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getEachQuantity(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  each_quantity  from sku where sku_id  = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getQuantity(String skuId, String pallet_id) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from upi_receipt_line where sku_id = '" + skuId
				+ "' and pallet_id='" + pallet_id + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getIsTagMergeUnchecked(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  tag_merge from sku where sku_id  = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String isNewProductChecked(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  new_product from sku where sku_id= '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getCEWarehouseType(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ce_warehouse_type from sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getCEVATCode(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  ce_vat_code from sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getCESKUcheckedValue(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select ce_customs_excise FROM sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getCEAlcoholicStrength(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select CE_ALCOHOLIC_STRENGTH FROM sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String ExpiryRequiredUncheckedValue(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select expiry_reqd FROM sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getBaseUOM(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select USER_DEF_TYPE_2 FROM sku where sku_id = '" + skuId + "'");
		//rs.next();
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getSAPCreationStatus(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select USER_DEF_TYPE_7 FROM sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getSKUType(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_8 from sku where sku_id = '" + skuId + "' ");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getNewProductCheckValue(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select new_product from sku where sku_id = '" + skuId + "' ");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public String getPartSet(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_NUM_2 from sku where sku_id = '" + skuId + "' ");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}

	public boolean validateSku(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from sku where sku_id = '" + skuId + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
			return false;
		} else {
			System.out.println("Record found in DB");
			return true;
		}
		

	}
	
	public String getSKU(String skuType) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from sku where user_def_type_8='" + skuType + "' and new_product='N'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
		} else {
			System.out.println("Record found in DB");
		}
		return rs.getString(1);
	}
	
	public boolean isSkuExistsForPackConfig(String skuId,String packConfig) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select sku_id from sku_sku_config where sku_id='" + skuId + "' and config_id='" + packConfig + "'");
		ResultSet rs = stmt.executeQuery("select sku_id from sku_sku_config where sku_id='" + skuId + "' and config_id='" + packConfig + "'");
		System.out.println("select sku_id from sku_sku_config where sku_id='" + skuId + "' and config_id='" + packConfig + "'");
		if (!rs.next()) {
			context.setErrorMessage("Record not found in DB");
			Assert.fail("Record not found in DB");
			return false;
		} else {
			System.out.println("Record found in DB");
			return true;
		}
}
	
	public String isHazmatSku(String skuId) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out
					.println("select hazmat from sku where sku_id = '" + skuId + "'");
			rs = stmt.executeQuery(
					"select hazmat from sku where sku_id = '" + skuId + "'");
			if (!rs.next()) {
				context.setErrorMessage("hazmat not found for the skuId :");
				Assert.fail("hazmat not found for the skuId :");
			} else {
				System.out.println("hazmat Found for Customer :" + rs.getString(1));
			}
		} catch (Exception e) {
			context.setErrorMessage("Exception Caught !!! hazmat not found for the skuId :");
			Assert.fail("Exception Caught !!! hazmat not found for the skuId :");

		}
		return rs.getString(1);
	}

	
	public String getPutawayGroup(String skuId) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out
					.println("select putaway_group from sku where sku_id = '" + skuId + "'");
			rs = stmt.executeQuery(
					"select putaway_group from sku where sku_id = '" + skuId + "'");
			if (!rs.next()) {
				context.setErrorMessage("putaway_group not found for the skuId :");
				Assert.fail("putaway_group not found for the skuId :");
			} else {
				System.out.println("putaway_group Found for skuId :" + rs.getString(1));
			}
		} catch (Exception e) {
			context.setErrorMessage("Exception Caught !!! putaway_group not found for the skuId :");
			Assert.fail("Exception Caught !!! putaway_group not found for the skuId :");

		}
		return rs.getString(1);
	}
}









