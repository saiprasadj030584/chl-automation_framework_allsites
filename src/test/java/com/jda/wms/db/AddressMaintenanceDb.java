package com.jda.wms.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class AddressMaintenanceDb {
	private Context context;
	private Connection connection;

	@Inject
	public AddressMaintenanceDb(Context context) {
		this.context = context;
	}

	public String getName(String supplierId) {
		String name = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select status from pre_advice_header where pre_advice_id='" + supplierId + "'");
			rs.next();
			name = rs.getString(1);

		} catch (SQLException ex) {
		}
		return name;
	}

	public String getAddress1(String supplierId) {
		String address1 = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select status from pre_advice_header where pre_advice_id='" + supplierId + "'");
			rs.next();
			address1 = rs.getString(1);

		} catch (SQLException ex) {
		}
		return address1;
	}

}
