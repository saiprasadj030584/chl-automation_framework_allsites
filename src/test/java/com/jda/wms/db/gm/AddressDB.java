package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class AddressDB {
	private Context context;
	private Database database;

	@Inject
	public AddressDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String CSSMCheckedValue(String customer) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_CHK_3 from address WHERE ADDRESS_ID ='" + customer + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getLowerTagValue() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_6 from address where Address_id = '" + context.getCustomer() + "'");
		ResultSet rs = stmt
				.executeQuery("select user_def_type_6 from address where Address_id = '" + context.getCustomer() + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getHigherTagValue() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select user_def_type_7 from address where Address_id = '" + context.getCustomer() + "'");
		rs.next();
		return rs.getString(1);
	}
}
