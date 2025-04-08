package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SkuDB {
	private static Context context;
	private static Database database;

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

	public String getSupplierSKU(String skuId) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select supplier_sku_id from supplier_sku where sku_id='" + skuId + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
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

	public static String getProductGroup(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select product_group from sku where sku_id = '" + SKU + "'");
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

	public String getSkuId(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select sku_id from sku where sku_id = '" + skuId + "'");
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

	public static String getUPCDB(String SkuId) throws SQLException, ClassNotFoundException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();

		ResultSet rs = stmt.executeQuery("select upc from sku where sku_id = '" + SkuId + "'");
		rs.next();
		return (rs.getString(1));
	}

	public String getUPCDB1(String SkuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select upc from sku where sku_id = '" + SkuId + "'");
		ResultSet rs = stmt.executeQuery("select upc from sku where sku_id = '" + SkuId + "'");
		rs.next();
		return (rs.getString(1));
	}

	public String getQTYDB(String preAdviceID, String skuid) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select qty_due from pre_advice_line where pre_advice_id = '" + preAdviceID
				+ "'   and sku_id = '" + skuid + "' ");
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

	public String getProductGroupNew(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select product_group from sku where sku_id = '" + SKU + "'");
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

	public String getCommodityCode(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select COMMODITY_CODE FROM SKU where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getusergroup(String usergroup) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select group_id from user_group where group_id= '" + usergroup + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCompositionDesc(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select COMMODITY_CODE FROM SKU where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public static String getpackedweight(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select packed_weight from sku where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getStroke(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_1 from sku where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCOO(String SKU) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(" select CE_COO from sku where sku_id = '" + SKU + "'");
		rs.next();
		return rs.getString(1);
	}

	public static String getUserDefChck3(String sku) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs1 = stmt.executeQuery("update sku set user_def_chk_3='Y' where sku_id = '" + sku + "'");
		context.getConnection().commit();
		ResultSet rs = stmt.executeQuery("select user_def_chk_3 from sku where sku_id = '" + sku + "'");
		rs.next();
		return rs.getString(1);
	}

	public String updatePackedweight(String sKU) throws SQLException, ClassNotFoundException {
		System.out.println("sku=" + sKU);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set packed_weight=(null) where sku_id='" + sKU + "'");
		context.getConnection().commit();

		return null;
	}

	public String updateproductgroup(String sKU) throws SQLException, ClassNotFoundException {
		System.out.println("sku=" + sKU);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set product_group=null where sku_id='" + sKU + "'");
		context.getConnection().commit();

		return null;
	}

	public String UpdateTDeptForNonCompliance(String PdtGp, String sKU) throws SQLException, ClassNotFoundException {
		System.out.println("sku=" + sKU);
		System.out.println("PdtGp=" + PdtGp);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set product_group='" + PdtGp + "' where sku_id='" + sKU + "'");
		context.getConnection().commit();

		return null;
	}

	public String UpdateOriginal(String packweight, String sku) throws SQLException, ClassNotFoundException {
		System.out.println(packweight);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update sku set packed_weight='" + packweight + "' where sku_id='" + sku + "'");
		context.getConnection().commit();

		return null;
	}

	public String Updateproductgroup(String ProductGroup1, String sku) throws SQLException, ClassNotFoundException {
		System.out.println(ProductGroup1);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update supplier_sku set supplier_sku_id='null' where sku_id='" + sku + "'");
		context.getConnection().commit();

		return null;
	}

	public String UpdateproductgroupOriginal(String ProductGroup1, String sku)
			throws SQLException, ClassNotFoundException {
		System.out.println(ProductGroup1);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update supplier_sku set supplier_sku_id='" + ProductGroup1 + "' where sku_id='" + sku + "'");
		context.getConnection().commit();

		return null;
	}

	public String getPutawayGroup(String skuid) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select putaway_group from sku where sku_id='" + skuid + "'");
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

	public String getUserDefChck3new(String skuHang) throws SQLException, ClassNotFoundException {
		System.out.println(skuHang);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_chk_3 from sku where sku_id='" + skuHang + "'");
		rs.next();
		System.out.println(rs);
		return rs.getString(1);

	}

	public String getOwnerId(String sku) throws SQLException, ClassNotFoundException {
		System.out.println("SKUID IS" + sku);
		String OwnerId = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1.executeQuery("select CLIENT_ID from SKU where SKU_ID= '" + sku + "'");
		rs1.next();
		OwnerId = (rs1.getString(1));
		System.out.println("OwnerId is" + OwnerId);
		return OwnerId;

	}

	public String getSiteId(String sku) throws SQLException, ClassNotFoundException {

		String SiteId = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1
				.executeQuery("select SITE_ID from site where site_id!='TEMPLATE' and site_id!='INTRANSIT'");
		rs1.next();
		SiteId = (rs1.getString(1));
		System.out.println("SiteId is" + SiteId);
		return SiteId;

	}

	public String getConfigId(String sku) throws SQLException, ClassNotFoundException {
		String ConfigId = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1.executeQuery(
				"select CONFIG_ID from sku_sku_config where main_config_id='Y' and  SKU_ID= '" + sku + "'");
		rs1.next();
		ConfigId = (rs1.getString(1));
		System.out.println("configid is" + ConfigId);
		return ConfigId;

	}

	public String getClientId(String sku) throws SQLException, ClassNotFoundException {
		System.out.println(sku);
		String ClientId = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1.executeQuery("select CLIENT_ID from sku where SKU_ID= '" + sku + "'");
		rs1.next();
		ClientId = (rs1.getString(1));
		System.out.println("clientid is" + ClientId);
		return ClientId;

	}

	public Boolean validateSYW(String upc) throws SQLException, ClassNotFoundException {

		Boolean exists = true;

		System.out.println("upc=" + upc);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();

		ResultSet rs1 = stmt.executeQuery(
				"SELECT ss.supplier_sku_id FROM MANDS.REPO_STROKE_PARENT_DC MRSPD JOIN DCSDBA.SKU S ON S.USER_DEF_TYPE_1 = MRSPD.STROKE AND S.PRODUCT_GROUP = MRSPD.PRODUCT_GROUP AND S.COLOUR = MRSPD.COLOUR JOIN SUPPLIER_SKU SS ON SS.SKU_ID = S.SKU_ID JOIN ADDRESS A ON A.ADDRESS_ID = MRSPD.SOURCE_DC and  SS.supplier_sku_id='"
						+ upc + "'");
		exists = rs1.next();

		return exists;
	}

	public String getuser_def_chk_4(String upc) throws SQLException, ClassNotFoundException {

		String user_def_chk_4 = null;
		// String user_def_type_7=null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1.executeQuery(
				"select s.user_def_chk_4 from sku s Left join supplier_sku ss on ss.sku_id=s.sku_id where ss.supplier_sku_id='"
						+ upc + "'");
		rs1.next();
		user_def_chk_4 = (rs1.getString(1));

		return user_def_chk_4;

	}

	public String getuser_def_type_7(String upc) throws SQLException, ClassNotFoundException {

		String user_def_type_7 = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1.executeQuery(
				"select s.user_def_type_7 from sku s Left join supplier_sku ss on ss.sku_id=s.sku_id where ss.supplier_sku_id='"
						+ upc + "'");
		rs1.next();
		user_def_type_7 = (rs1.getString(1));

		return user_def_type_7;

	}

	public String getProductgroup(String upc) throws SQLException, ClassNotFoundException {

		String Productgroup = null;
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();

		ResultSet rs1 = stmt1.executeQuery(
				"select s.product_group from sku s Left join supplier_sku ss on ss.sku_id=s.sku_id where ss.supplier_sku_id='"
						+ upc + "'");
		rs1.next();
		Productgroup = (rs1.getString(1));
		System.out.println("Productgroup is" + Productgroup);
		return Productgroup;

	}

}
