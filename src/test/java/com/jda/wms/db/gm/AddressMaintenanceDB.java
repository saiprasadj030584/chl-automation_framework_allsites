package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.junit.Assert;

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
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select address_type from address where address_id='" + addressID + "'");
		 rs = stmt
				.executeQuery("select address_type from address where address_id='" + addressID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("address type not found for the customer :");
				Assert.fail("address type not found for the customer :");
			}else
			{
				System.out.println("address type not found for the customer :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! address type not found for the customer :");
				Assert.fail("Exception Caught !!! addresss typa not found for the customer :" );
				

			}
			return rs.getString(1);
		}

		


	public String getName(String addressID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("select name from address where address_id='" + addressID + "'");
			 rs = stmt
					.executeQuery("select name from address where address_id='" + addressID + "'");
			 if (!rs.next()) {
					context.setErrorMessage("name not found for the address id :");
					Assert.fail("name not found for the address id :");
				}else
				{
					System.out.println("name found for the address id :");
				}
			}
			 catch (Exception e) {
					context.setErrorMessage("Exception Caught !!! name not found for the address id " );
					Assert.fail("Exception Caught !!! name not found for the address id "  );
					

				}
				return rs.getString(1);
			}
	
			

	
	public String getCountry(String addressID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select country from address where address_id='" + addressID + "'");
		 rs = stmt
				.executeQuery("select country from address where address_id='" + addressID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("country not found for the address :");
				Assert.fail("country not found for the address :");
			}else
			{
				System.out.println("Country Found for address :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! country not found for the address :");
				Assert.fail("Exception Caught !!! country not found for the address :" );
				

			}
			return rs.getString(1);
		}

	
	public String getCEWarehouseType(String addressID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select ce_warehouse_type from address where address_id= '" + addressID + "'");
		 rs = stmt
				.executeQuery("select ce_warehouse_type from address where address_id= '" + addressID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("ce_warehouse_type not found for the addressID :");
				Assert.fail("ce_warehouse_type not found for the addressID :");
			}else
			{
				System.out.println("Record Found for Customer :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! ce_warehouse_type not found for the addressID");
				Assert.fail("Exception Caught !!! ce_warehouse_type not found for the addressID" );
				

			}
			return rs.getString(1);
		}

	public String getSiteCategory(String addressID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_2 from address where Address_id = '"+addressID+ "'");
		 rs = stmt
				.executeQuery("select user_def_type_2 from address where Address_id = '"+addressID+ "'");
		 if (!rs.next()) {
				context.setErrorMessage("user_def_type_2 not found for the addressID :");
				Assert.fail("user_def_type_2 not found for the addressID :");
			}else
			{
				System.out.println("user_def_type_2 Found for addressID :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! user_def_type_2 not found for the addressID :");
				Assert.fail("Exception Caught !!! user_def_type_2 not found for the addressID :" );
				

			}
			return rs.getString(1);
		}
	
	public boolean isIsSiteChecked(String addressID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_chk_2 from address where address_id='" + addressID + "'");
		 rs = stmt
				.executeQuery("select user_def_chk_2 from address where address_id='" + addressID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("user_def_chk_2 not found for the addressID :");
				Assert.fail("user_def_chk_2 not found for the addressID :");
			}else
			{
				System.out.println("user_def_chk_2 Found for Customer :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! user_def_chk_2 not found for the addressID :");
				Assert.fail("Exception Caught !!! user_def_chk_2 not found for the addressID :" );
				

			}
		return rs.getString(1) != null;
		}


	
	public String getAddress1(String addressID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select address1 from address where address_id='" + addressID + "'");
		 rs = stmt
				.executeQuery("select address1 from address where address_id='" + addressID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("address1 not found for the addressID :");
				Assert.fail("address1 not found for the addressID :");
			}else
			{
				System.out.println("address1 Found for Customer :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! address1 not found for the addressID :");
				Assert.fail("Exception Caught !!! address1 not found for the addressID :" );
				

			}
			return rs.getString(1);
		}

		

	public boolean isIsSiteUnchecked(String addressID) throws SQLException, ClassNotFoundException {
	ResultSet rs = null;
	try {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	System.out.println("select user_def_chk_2 from address where address_id='" + addressID + "'");
	 rs = stmt
			.executeQuery("select user_def_chk_2 from address where address_id='" + addressID + "'");
	 if (!rs.next()) {
			context.setErrorMessage("user_def_chk_2 not found for the addressID :");
			Assert.fail("user_def_chk_2 not found for the addressID :");
		}else
		{
			System.out.println("user_def_chk_2 Found for addressID :");
		}
	}
	 catch (Exception e) {
			context.setErrorMessage("Exception Caught !!! user_def_chk_2 not found for the addressID :");
			Assert.fail("Exception Caught !!! user_def_chk_2 not found for the addressID :" );
			

		}
	return rs.getString(1) != null;
	}
}
