package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class AddressMaintenanceDB {
	
	private final Context context;
	private final Database database;
	
	@Inject
	public AddressMaintenanceDB( Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	
	public HashMap<String, String> getAdressDetails(String preAdviceID) {
		ResultSet resultSet = null;
		HashMap<String, String> addressDetailValue = new HashMap<String, String>();

		try {
			database.connect();
			Statement stmt = context.getConnection().createStatement();
			resultSet = stmt.executeQuery(this.getAddressrQuery(preAdviceID));
			resultSet.next();
			addressDetailValue.put("Name", resultSet.getString(1));
			addressDetailValue.put("Address1", resultSet.getString(2));
			addressDetailValue.put("Country", resultSet.getString(3));
			addressDetailValue.put("DefaultSupplierPallet", resultSet.getString(4));
			addressDetailValue.put("CEWarehouseType", resultSet.getString(5));
			addressDetailValue.put("CEWarehouseTax", resultSet.getString(6));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return addressDetailValue;
	}
	
	public String getAddressrQuery(String addressId) {
		return "select  Name,Address1,country,USER_DEF_TYPE_1, ce_warehouse_type, ce_tax_warehouse from address where address_id = '"
				+ addressId + "'";
	}

}
