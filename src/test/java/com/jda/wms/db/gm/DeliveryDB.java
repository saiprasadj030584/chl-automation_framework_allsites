package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DeliveryDB {
	
	private Context context;
	private Database database;

	@Inject
	public DeliveryDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}

	public String getStatus(String asnId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("Select status from delivery where asn_id ='"+asnId+"'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getAsnId(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("Select asn_id from delivery where status ='"+status+"'");
		rs.next();
		return rs.getString(1);
	}

	public void updatePalletCount(String asnId, int numLines) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update delivery set pallet_count='"+numLines+"' where asn_id='"+asnId+"'");
		context.getConnection().commit();
	}

		public  ArrayList getAsnidList() throws SQLException, ClassNotFoundException {
		ArrayList<String> AsnidList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select asn_id from delivery where status ='"+"In Progress"+"'");
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
				ResultSet rs = stmt
						.executeQuery("select status from delivery where asn_id ='"+asnId+"'");
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

	}

