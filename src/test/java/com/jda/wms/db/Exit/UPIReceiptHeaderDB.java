package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIReceiptHeaderDB {
	private static Context context;
	private static Database database;

	@Inject
	public UPIReceiptHeaderDB(Context context, Database database) {
		this.context = context;
		this.database = database;

	}
	public String getASN() throws SQLException, ClassNotFoundException {
		String palletID = context.getpalletIDforUPI();
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select ASN_ID from upi_receipt_header where pallet_id = '" + palletID + "'");
		rs.next();
		return rs.getString(1);
	}
	




}
