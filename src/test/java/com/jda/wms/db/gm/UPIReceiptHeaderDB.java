package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.hooks.Hooks_autoUI;

public class UPIReceiptHeaderDB {

	private Context context;
	private Database database;
	private Hooks_autoUI hooks_autoUI;

	@Inject
	public UPIReceiptHeaderDB(Context context, Database database, Hooks_autoUI hooks_autoUI) {
		this.context = context;
		this.database = database;
		this.hooks_autoUI = hooks_autoUI;
	}

	public String getStatus(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("dfhsdfh");

		System.out.println("Select status from upi_receipt_header where pallet_id ='" + upiId + "'");
		Statement stmt = context.getConnection().createStatement();
		System.out.println("Select status from upi_receipt_header where pallet_id ='" + upiId + "'");
		ResultSet rs = stmt.executeQuery("Select status from upi_receipt_header where pallet_id ='" + upiId + "'");
		rs.next();
//		int iVal = 0;
//
//		if (rs.next()) {
//		    iVal = rs.getInt("status");
//		    if (rs.wasNull()) {
//		       System.out.println("Null Value returned !!! Testdat setup issue ! Please Rerun this scenario");
//		       Assert.fail();
//		    }
//		}
		return rs.getString(1);
	}

	public String getNumberOfLines(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select NUM_LINES from upi_receipt_header WHERE pallet_id = '" + upiId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getNumberOfLines(ArrayList<String> upiList) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		int count = 0;
		for (int i = 0; i < upiList.size(); i++) {
			ResultSet rs = stmt.executeQuery(
					"select NUM_LINES from upi_receipt_header WHERE pallet_id = '" + upiList.get(i) + "'");
			rs.next();
			count += Integer.parseInt(rs.getString(1));
		}
		return Integer.toString(count);
	}

	public String getUserDefinedType7(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select USER_DEF_TYPE_7 from upi_receipt_header where pallet_id='" + upiId + "'");
		rs.next();
		return rs.getString(1);
	}

	public void updateASN(String upiId, String asnId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update upi_receipt_header set asn_id='" + asnId + "' where pallet_id='" + upiId + "'");
		context.getConnection().commit();
	}

	public void updateSSSCURN(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update upi_receipt_header set user_def_note_1='" + upiId + "' where pallet_id='" + upiId + "'");
		context.getConnection().commit();
	}

	public String getShippingType(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT user_def_type_7 FROM upi_receipt_header WHERE pallet_id = '" + upiId + "'");
		rs.next();
		return rs.getString(1);
	}

	public boolean isRecordExistsForPalletId(String upiId) {
		boolean isRecordExists = false;
		try {

			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt

					.executeQuery("Select pallet_id from upi_receipt_header where pallet_id ='" + upiId + "'");
			rs.next();
			if (rs.getString(1).equals(upiId)) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}

	public Object getUpiIdForUPI(String upi) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println("** SELECT pallet_id FROM upi_receipt_header WHERE pallet_id = '" + upi + "'");
			rs = stmt.executeQuery("SELECT pallet_id FROM upi_receipt_header WHERE pallet_id = '" + upi + "'");
			System.out.println(" Query Executed");
//			int iVal = 0;
//
//			if (!rs.next()) {
//			    iVal = rs.getInt("pallet_id");
//			    if (rs.wasNull()) {
//			       System.out.println("Null Value returned !!! Testdat setup issue ! Please Rerun this scenario");
//			       Assert.fail();
//			    }
//			    else
//			    {
//			    	System.out.println("Value found in DB");
//			    }
//			}
//			else
//		    {
//		    	System.out.println("Value found in DB");
//		    }
			
			if (!rs.next()) {
				context.setErrorMessage("*Datasetup is not completed due to application issue or windows pop up");
			} else {
				System.out.println("UPI Receipt Header -->" + rs.getString(1));
			}
		} catch (Exception e) {
			hooks_autoUI.updateExecutionStatusInAutomationDb_End("FAIL", context.getUniqueTag());
			Assert.fail("Datasetup is not completed due to application issue or windows pop up");
		}
		return rs.getString(1);
	}

	public Object getUpiId(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT pallet_id FROM upi_receipt_header WHERE STATUS = '" + status + "'");
		rs.next();
		return rs.getString(1);
	}

}
