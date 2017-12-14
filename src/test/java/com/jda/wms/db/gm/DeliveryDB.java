package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;


public class DeliveryDB {

	private Context context;
	private Database database;


	@Inject
	public DeliveryDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	
	public String getStatus(String asnId) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("Select status from delivery where asn_id ='" + asnId + "'");
		 rs = stmt
				.executeQuery("Select status from delivery where asn_id ='" + asnId + "'");
		 if (!rs.next()) {
				context.setErrorMessage("status not found for the asnId :");
				Assert.fail("status not found for the asnId :");
			}else
			{
				System.out.println("status Found for Customer :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! status not found for the asnId :");
				Assert.fail("Exception Caught !!! status not found for the asnId :" );
				

			}
			return rs.getString(1);
		}

		


	
	public String getAsnId(String status) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("Select asn_id from delivery where status ='" + status + "'");
		 rs = stmt
				.executeQuery("Select asn_id from delivery where status ='" + status + "'");
		 if (!rs.next()) {
				context.setErrorMessage("asn_id not found for the status :");
				Assert.fail("asn_id not found for the status :");
			}else
			{
				System.out.println("asn_id Found for asn_id :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! asn_id not found for the status :");
				Assert.fail("Exception Caught !!! asn_id not found for the status :" );
				

			}
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
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("Select supplier_id from delivery where asn_id ='" + asnId + "'");
		 rs = stmt
				.executeQuery("Select supplier_id from delivery where asn_id ='" + asnId + "'");
		 if (!rs.next()) {
				context.setErrorMessage("supplier_id not found for the asnId :");
				Assert.fail("supplier_id not found for the asnId :");
			}else
			{
				System.out.println("supplier_id Found for asnId :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! supplier_id not found for the asnId :");
				Assert.fail("Exception Caught !!! supplier_id not found for the asnId :");
				

			}
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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
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
				Assert.fail("Datasetup is not completed due to application issue or windows pop up");
			} else {
				System.out.println("ASN ID -->" + rs.getString(1));
			}

		} catch (Exception e) {

			Assert.fail("Datasetup is not completed due to application issue or windows pop up");
			e.printStackTrace();

		}
		return rs.getString(1);
	}

}












