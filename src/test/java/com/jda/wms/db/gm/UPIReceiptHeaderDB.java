package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		System.out.println("Select status from upi_receipt_header where pallet_id ='"+upiId+"'");
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
	
	public String getNumberOfLines(ArrayList<String> upiList) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		int count=0;
		for(int i=0;i<upiList.size();i++)
		{
		ResultSet rs = stmt
				.executeQuery("select NUM_LINES from upi_receipt_header WHERE pallet_id = '" + upiList.get(i) + "'");
		rs.next();
		System.out.println("key"+rs.getString(1));
		count+=Integer.parseInt(rs.getString(1));
		}
		return Integer.toString(count);
	}
	
	public String getUserDefinedType7(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEFINED_TYPE_7 from upi_receipt_header where pallet_id='" + upiId
				+ "'");
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
	
	public void updateSSSCURN(String upiId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update upi_receipt_header set user_def_note_1='"+upiId+"' where pallet_id='"+upiId+"'");
		context.getConnection().commit();
	}
}
