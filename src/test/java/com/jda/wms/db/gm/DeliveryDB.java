package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.err.println("IK ASNID"+asnId);
		ResultSet rs = stmt.executeQuery("Select status from delivery where asn_id ='" + asnId + "'");

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
}
