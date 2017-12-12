package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SkuSkuConfigDB {

	private Context context;
	private Database database;

	@Inject
	public SkuSkuConfigDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getSkuIdWithMoreThanOnePackConfig() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"Select sku_id from inventory where sku_id in (select sku_id from sku_sku_config group by sku_id having count(sku_id) > 1)");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public ArrayList<String> getPackConfigList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> packConfigList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select config_id from sku_sku_config where sku_id='" + sku + "'");
		while (rs.next()) {
			packConfigList.add(rs.getString(1));
		}
		return packConfigList;
	}
	
	public String getSkuIdWithMoreThanOnePackConfig(String dataType) throws SQLException, ClassNotFoundException {
		String packConfig = null;
		try{
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"Select inventory.sku_id from inventory inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=inventory.sku_id where inventory.sku_id  in (select sku_sku_config.sku_id from sku_sku_config inner join sku on sku.NEW_PRODUCT='N' and sku.sku_id=sku_sku_config.sku_id and sku.user_def_type_8 ='"
						+ dataType + "' group by sku_sku_config.sku_id having count(sku_sku_config.sku_id) > 1 )");
		rs.next();
		packConfig = rs.getString(1);
		}
		catch(Exception e){
			if (e.getMessage().contains("Exhausted Resultset")){
				Assert.fail("No Test Data available - Pack Config update - "+dataType);
			}
		}
		return packConfig;
	}
}









