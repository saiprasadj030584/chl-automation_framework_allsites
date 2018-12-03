package com.jda.wms.db.Exit;

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
	public AddressMaintenanceDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public HashMap<String, String> getAdressDetails(String addressId) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> addressDetails = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println(
				"select  Name,Address1,country,USER_DEF_TYPE_1, ce_warehouse_type, ce_tax_warehouse from address where address_id = '"
						+ addressId + "'");
		resultSet = stmt.executeQuery(
				"select  Name,Address1,country,USER_DEF_TYPE_1, ce_warehouse_type, ce_tax_warehouse from address where address_id = '"
						+ addressId + "'");
		resultSet.next();
		addressDetails.put("Name", resultSet.getString(1));
		addressDetails.put("Address1", resultSet.getString(2));
		addressDetails.put("Country", resultSet.getString(3));
		addressDetails.put("DefaultSupplierPallet", resultSet.getString(4));
		addressDetails.put("CEWarehouseType", resultSet.getString(5));
		addressDetails.put("CEWarehouseTax", resultSet.getString(6));
		return addressDetails;
	}

	public String getAddressType(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select address_type from address where address_id='" + addressID + "'");
		System.out.println("select address_type from address where address_id='" + addressID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getName(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select name from address where address_id='" + addressID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCountry(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select country from address where address_id='" + addressID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCEWarehouseType(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ce_warehouse_type from address where address_id= '" + addressID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSiteCategory(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_2 from address where address_id= '" + addressID + "'");
		rs.next();
		return rs.getString(1);
	}

	public boolean isIsSiteChecked(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_chk_2 from address where address_id='" + addressID + "'");
		rs.next();
		return rs.getString(1) != null;
	}

	public String getAddress1(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select address1 from address where address_id='" + addressID + "'");
		rs.next();
		return rs.getString(1);
	}

	public boolean isIsSiteUnchecked(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_chk_2 from address where address_id='" + addressID + "'");
		rs.next();
		return rs.getString(1) != null;
	}

	public String UpdateCountryForProhibition(String addressID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update address set country='ISR' from address where address_id='" + addressID + "'");
		context.getConnection().commit();
		return null;
	
}}
