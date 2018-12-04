package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		ResultSet rs = stmt.executeQuery("update mands.extended_udf set user_def_type_9 =(null) where sku_id='" + sku + "'");
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
	}

	
	
		
	


