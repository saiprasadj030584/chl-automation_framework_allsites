package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIReceiptHeaderDB {
	
	private Context context;
	private Database database;

	@Inject
	public UPIReceiptHeaderDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}

	public String getStatus(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("Select status from upi_receipt_header where pallet_id ='"+upiId+"'");
		rs.next();
		return rs.getString(1);
	}

	public String getNumberOfLines(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select NUM_LINES from upi_receipt_header WHERE pallet_id = '" + upiId + "'");
		rs.next();
		return rs.getString(1);
	}

	public void updateASN(String upiId, String asnId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update upi_receipt_header set asn_id='"+asnId+"' where pallet_id='"+upiId+"'");
		context.getConnection().commit();
	}

		public boolean isRecordExistsForPalletId(String upiId) {
		boolean isRecordExists = false;
		try{
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("Select * from upi_receipt_header where pallet_id ='"+upiId+"'");
		rs.next();
		if (rs.getString(1).equals(0)) {
			isRecordExists = true;
		}
		}
		catch (Exception e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
	
		return isRecordExists;
		
	}
}
