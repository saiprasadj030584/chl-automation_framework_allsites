package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		rs.next();
		return rs.getString(1);
	}

	public ArrayList getPackConfigList(String sku) throws SQLException, ClassNotFoundException {
		ArrayList<String> packConfigList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select config_id from sku_sku_config where sku_id='" + sku + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			packConfigList.add((rs.getString(j)));
		}
		return packConfigList;

	}
}
