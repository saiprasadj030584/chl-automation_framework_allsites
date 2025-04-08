package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class MandsDB {
	private static Context context;
	private static Database database;

	@Inject
	public MandsDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}


	public String getUserDefType9(String sku) throws SQLException, ClassNotFoundException {
		System.out.println(sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_9 from mands.extended_udf where sku_id='" + sku + "'");
		ResultSet rs = stmt
				.executeQuery("select user_def_type_9 from mands.extended_udf where sku_id='" + sku + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getUserDefType11(String sku) throws SQLException, ClassNotFoundException {
		System.out.println(sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_11 from mands.extended_udf where sku_id='" + sku + "'");
		ResultSet rs = stmt
				.executeQuery("select user_def_type_11 from mands.extended_udf where sku_id='" + sku + "'");
		rs.next();
		return rs.getString(1);
	}


	public String updateStroke(String sku) throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update mands.extended_udf set user_def_type_11=(null) where sku_id='" + sku + "'");
		context.getConnection().commit();
		
		return null;
	}


	public String UpdateToOriginalStroke(String strokeCt, String sku) throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update mands.extended_udf set user_def_type_11='" + strokeCt + "' where sku_id='" + sku + "'");
		context.getConnection().commit();
		
		return null;
	}


	public String updateCommodityCode(String sku)throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update mands.extended_udf set user_def_type_9 =null where sku_id='" + sku + "'");
		context.getConnection().commit();
		
		return null;
	}
	public String UpdateToOriginalCommodity(String CommodityCd, String sku) throws SQLException, ClassNotFoundException {
		System.out.println("sku="+sku);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update mands.extended_udf set user_def_type_11='" + CommodityCd + "' where sku_id='" + sku + "'");
		context.getConnection().commit();
		
		return null;
	}

	public String getProductGroup(String customer)throws SQLException, ClassNotFoundException {
		System.out.println(customer);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select product_group FROM mands.trusted_user_mode where customer_id='" + customer + "'");
		ResultSet rs = stmt
				.executeQuery("select product_group FROM mands.trusted_user_mode where customer_id='" + customer + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getTrustedStock(String customer_id, String pdtGp)throws SQLException, ClassNotFoundException {
		System.out.println(customer_id);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select trusted FROM mands.trusted_user_mode where customer_id='"+customer_id+"' and product_group='"+pdtGp+"'");
		ResultSet rs = stmt
				.executeQuery("select trusted FROM mands.trusted_user_mode where customer_id='"+customer_id+"' and product_group='"+pdtGp+"'");
		rs.next();
		return rs.getString(1);
	}

	public String getNotTrustedStock(String customer_id, String pdtGp, String siteId)throws SQLException, ClassNotFoundException {
		System.out.println(customer_id);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select trusted FROM mands.trusted_user_mode where customer_id='"+customer_id+"' and product_group='"+pdtGp+"' and site_id='"+siteId+"'");
		ResultSet rs = stmt
				.executeQuery("select trusted FROM mands.trusted_user_mode where customer_id='"+customer_id+"' and product_group='"+pdtGp+"' and site_id='"+siteId+"'");
		rs.next();
		return rs.getString(1);
	}


	public void updateAllowedToNot(String customer, String pdtGp) throws SQLException, ClassNotFoundException {
		
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update mands.trusted_user_mode set trusted='N' where customer_id='" + customer + "' and product_group='"+pdtGp+"'");
		context.getConnection().commit();
	
	}
	
	public void updateQRIdRandom(String Pallet,String urn,String QRId,String key) throws SQLException, ClassNotFoundException {
		
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("update mands.qr_req_resp_log set qr_id='" + QRId + "' where pallet_id='" + Pallet + "' and urn='"+urn+"' and key= '" +key+ "'");
		ResultSet rs = stmt.executeQuery("update mands.qr_req_resp_log set qr_id='" + QRId + "' where pallet_id='" + Pallet + "' and urn='"+urn+"' and key= '" +key+ "'");
		context.getConnection().commit();
	
	}


		public String getMandspallet(String urn) throws SQLException, ClassNotFoundException {
			System.out.println(urn);
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("select distinct pallet_id from mands.qr_req_resp_log where urn='" + urn + "'");
			ResultSet rs = stmt
					.executeQuery("select distinct pallet_id from mands.qr_req_resp_log where urn='" + urn + "'");
			rs.next();
			return rs.getString(1);
	
	}
		
		public int getMandsurncount(String urn,String PalletID) throws SQLException, ClassNotFoundException {
			System.out.println(urn);
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("select count(*) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet='" + PalletID + "'");
			ResultSet rs = stmt
					.executeQuery("select count(*) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet='" + PalletID + "'");
			rs.next();
			return rs.getInt(1);
	
	}
		public int getMandsupccount(String urn,String PalletID,String upc) throws SQLException, ClassNotFoundException {
			System.out.println(urn);
			//int totcnt;
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("select count(*) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "' and  UPC='" + upc + "'");
			ResultSet rs = stmt
					.executeQuery("select count(*) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "' and  UPC='" + upc + "'");
			rs.next();
			return rs.getInt(1);
	
	}
		
		public int getMandslinecount(String urn,String PalletID) throws SQLException, ClassNotFoundException {
			System.out.println(urn);
			//int totcnt;
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("select count(*) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "'");
			ResultSet rs = stmt
					.executeQuery("select count(*) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "'");
			rs.next();
			return rs.getInt(1);
	
	}
		
		public ArrayList<String> MandsgetKeyList(String PalletID,String urn) throws SQLException, ClassNotFoundException {
			ArrayList<String> KeyList = new ArrayList<String>();
					if (context.getConnection() == null) {
						database.connect();
					}
					Statement stmt = context.getConnection().createStatement();
					System.out.println("select key from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "' and qr_id is null");
					ResultSet rs = stmt
							.executeQuery("select key from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "' and qr_id is null");
					ResultSetMetaData rsmd = rs.getMetaData();
					int columns = rsmd.getColumnCount();
					while (rs.next()) {
						for (int j = 1; j <= columns; j++) {
							KeyList.add((rs.getString(j)));
						}
					}
					return KeyList;
			
			}

		
		public ArrayList<String> MandsgetDMIdList(String PalletID,String urn,String upc) throws SQLException, ClassNotFoundException {
			ArrayList<String> DMIdList = new ArrayList<String>();
					if (context.getConnection() == null) {
						database.connect();
					}
					Statement stmt = context.getConnection().createStatement();
					System.out.println("select substr(QR_ID,1,31) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "' and upc='"+upc+"'");
					ResultSet rs = stmt
							.executeQuery("select substr(QR_ID,1,31) from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "' and upc='"+upc+"'");
					ResultSetMetaData rsmd = rs.getMetaData();
					int columns = rsmd.getColumnCount();
					while (rs.next()) {
						for (int j = 1; j <= columns; j++) {
							DMIdList.add((rs.getString(j)));
						}
					}
					return DMIdList;
			
			}
		
		
		
public ArrayList<String> MandsgetUPCList(String PalletID,String urn) throws SQLException, ClassNotFoundException {
	ArrayList<String> UPCList = new ArrayList<String>();
			System.out.println(PalletID);
			System.out.println(urn);
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("select distinct UPC from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "'");
			ResultSet rs = stmt
					.executeQuery("select distinct UPC from mands.qr_req_resp_log where urn='" + urn + "' and Pallet_id='" + PalletID + "'");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while (rs.next()) {
				for (int j = 1; j <= columns; j++) {
					UPCList.add((rs.getString(j)));
				}
			}
			return UPCList;
	
	}
/**
 * @author Vedika.Vinod
 * This function is to get the mode of transport
 * @throws ClassNotFoundException
 * @throws SQLException
 */

public String getModeOfTransport(String addressId) throws ClassNotFoundException, SQLException {

	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	System.out
			.println("select mode_of_transport from MANDS.sortation_group where customer_id= '" + addressId + "'");
	ResultSet rs = stmt.executeQuery(
			"select mode_of_transport from MANDS.sortation_group where customer_id= '" + addressId + "'");
	rs.next();

	return rs.getString(1);
}
	}



	
	
		
	


