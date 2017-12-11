package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.hooks.Hooks_autoUI;

public class DeliveryDB {

	private Context context;
	private Database database;
	private Hooks_autoUI hooks_autoUI;

	@Inject
	public DeliveryDB(Context context, Database database, Hooks_autoUI hooks_autoUI) {
		this.context = context;
		this.database = database;
		this.hooks_autoUI = hooks_autoUI;
	}

	public String getStatus(String asnId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("Status of ASN");
		System.out.println("Select status from delivery where asn_id ='" + asnId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select status from delivery where asn_id ='" + asnId + "'");
		rs.next();
		System.out.println(rs.getString(1));
		return rs.getString(1);
	}

	public String getAsnId(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select asn_id from delivery where status ='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public void updatePalletCount(String asnId, int numLines) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update delivery set pallet_count='" + numLines + "' where asn_id='" + asnId + "'");
		context.getConnection().commit();
	}

	public ArrayList getAsnidList() throws SQLException, ClassNotFoundException {
		ArrayList<String> AsnidList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select asn_id from delivery where status ='" + "In Progress" + "'");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		rs.next();
		for (int j = 1; j <= columns; j++) {
			AsnidList.add((rs.getString(j)));
		}
		return AsnidList;
	}

	public boolean isRecordExistsForAsnId(String asnId, String status) {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select status from delivery where asn_id ='" + asnId + "'");
			rs.next();
			if (rs.getString(1).equals(status)) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}

	public String getSupplier(String asnId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("Select supplier_id from delivery where asn_id ='" + asnId + "'");
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select supplier_id from delivery where asn_id ='" + asnId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getAsnId(String type, String status) throws SQLException, ClassNotFoundException {
		String Type = null;
		switch (type) {
		case "Hanging":
			Type = "H";
			break;
		case "Boxed":
			Type = "B";
			break;
		case "Flatpack":
			Type = "P";
			break;
		case "GOH":
			Type = "C";
			break;
		}
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select A.asn_id,B.sku_id from upi_receipt_header A  "
				+ "inner join upi_receipt_line B on A.pallet_id = B.pallet_id inner join sku C on B.sku_id=c.sku_id "
				+ "and a.status='" + status + "' and C.user_def_type_8= '" + Type + "' and a.asn_id!='null'");
		rs.next();
		return rs.getString(1);
	}

	public Object getAsnIdForASN(String asn) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			System.out.println("Select asn_id from delivery where asn_id ='" + asn + "'");
			Statement stmt = context.getConnection().createStatement();
			rs = stmt.executeQuery("Select asn_id from delivery where asn_id ='" + asn + "'");
			if (!rs.next()) {
				context.setErrorMessage("Datasetup is not completed due to application issue or windows pop up");
			} else {
				System.out.println("ASN ID -->" + rs.getString(1));
			}

		} catch (Exception e) {
			hooks_autoUI.updateExecutionStatusInAutomationDb_End("FAIL", context.getUniqueTag());
			Assert.fail("Datasetup is not completed due to application issue or windows pop up");
			e.printStackTrace();

		}
		return rs.getString(1);
	}

}
