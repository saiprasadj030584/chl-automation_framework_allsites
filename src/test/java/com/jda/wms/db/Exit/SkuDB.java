package com.jda.wms.db.Exit;

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
		}}

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

	public String getUPC(String skuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select UPC from sku where sku_id = '" + skuId + "'");
		rs.next();
		return rs.getString(1);
	}

	public static  String getUPCDB(String SkuId) throws SQLException, ClassNotFoundException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select upc from sku where sku_id = '"+SkuId+"'");
		rs.next();
		return (rs.getString(1));
	}
	
	public String getUPCDB1(String SkuId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select upc from sku where sku_id = '"+SkuId+"'");
		rs.next();
		return (rs.getString(1));
	}
	public  String getQTYDB(String preAdviceID,String skuid) throws SQLException, ClassNotFoundException {
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


	public static String getUserDefChck3(String sku)throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs1= stmt.executeQuery("update sku set user_def_chk_3='Y' where sku_id = '" + sku + "'");
		context.getConnection().commit();
		ResultSet rs = stmt.executeQuery("select user_def_chk_3 from sku where sku_id = '" + sku + "'");
		rs.next();
		return rs.getString(1);
	}

	public String updatePackedweight(String sKU) throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sKU);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set packed_weight=(null) where sku_id='" + sKU + "'");
		context.getConnection().commit();
		
		return null;
	}

	public String updateproductgroup(String sKU) throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sKU);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set product_group=(null) where sku_id='" + sKU + "'");
		context.getConnection().commit();
		
		return null;
	}
	

	public String UpdateOriginal(String packweight, String sku) throws SQLException, ClassNotFoundException {
		System.out.println(packweight);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update sku set packed_weight='" + packweight + "' where sku_id='" + sku + "'");
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
	public String UpdateproductgroupOriginal(String ProductGroup1, String sku) throws SQLException, ClassNotFoundException {
		System.out.println(ProductGroup1);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update supplier_sku set supplier_sku_id='" + ProductGroup1 + "' where sku_id='" + sku + "'");
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

	public String getBoxedOrHanging(String skuid)throws SQLException, ClassNotFoundException {
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
	}
		
	
