package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

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
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			rs = stmt.executeQuery("select USER_DEF_CHK_3 from address WHERE ADDRESS_ID ='" + customer + "'");
			if (!rs.next()) {
				context.setErrorMessage("Record not found for the customer :" + customer);
				Assert.fail("Record not found for the customer :" + customer);
			} else {
				System.out.println("Record Found for Customer :" + customer);
			}

		} catch (Exception e) {
			context.setErrorMessage("Exception Caught !!! Record not found for the customer :" + customer);
			Assert.fail("Exception Caught !!! Record not found for the customer :" + customer);

		}
		return rs.getString(1);
	}

	public String getLowerTagValue() throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out
					.println("select user_def_type_6 from address where Address_id = '" + context.getCustomer() + "'");
			rs = stmt.executeQuery(
					"select user_def_type_6 from address where Address_id = '" + context.getCustomer() + "'");
			if (!rs.next()) {
				context.setErrorMessage("Record not found for the customer :");
				Assert.fail("Record not found for the customer :");
			} else {
				System.out.println("Record Found for Customer :");
			}
		} catch (Exception e) {
			context.setErrorMessage("Exception Caught !!! Record not found for the customer :");
			Assert.fail("Exception Caught !!! Record not found for the customer :");

		}
		return rs.getString(1);
	}

	public String getHigherTagValue() throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out
					.println("select user_def_type_7 from address where Address_id = '" + context.getCustomer() + "'");
			rs = stmt.executeQuery(
					"select user_def_type_7 from address where Address_id = '" + context.getCustomer() + "'");
			if (!rs.next()) {
				context.setErrorMessage("Record not found in DB");
				Assert.fail("Record not found in DB");
			} else {
				System.out.println("Record not found in DB");
			}
		} catch (Exception e) {
			context.setErrorMessage("Exception Caught !!! Record not found for the customer :");
			Assert.fail("Exception Caught !!! Record not found for the customer :");

		}
		return rs.getString(1);
	}
}












