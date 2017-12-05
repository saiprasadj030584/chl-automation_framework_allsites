package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jboss.netty.util.internal.SystemPropertyUtil;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIReceiptHeaderDB {

	private Context context;
	private Database database;

	@Inject
	public UPIReceiptHeaderDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getStatus(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("Status of UPI");

		System.out.println("Select status from upi_receipt_header where pallet_id ='" + upiId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select status from upi_receipt_header where pallet_id ='" + upiId + "'");
		rs.next();
		System.out.println(rs.getString(1));
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
		System.out.println("update upi_receipt_header set asn_id='" + asnId + "' where pallet_id='" + upiId + "'");
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
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("SELECT pallet_id FROM upi_receipt_header WHERE pallet_id = '" + upi + "'");
		ResultSet rs = stmt.executeQuery("SELECT pallet_id FROM upi_receipt_header WHERE pallet_id = '" + upi + "'");
		rs.next();
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
