package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class InventoryDB {
	private Context context;
	private Database database;

	@Inject
	public InventoryDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getExpDate(String skuId, String tagId, String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select EXPIRY_DSTAMP from INVENTORY where TAG_ID = '"+tagId+"' AND SKU_ID = '"+skuId+"' AND LOCATION_ID = '"+location+"' AND LOCK_STATUS = 'UnLocked'");
		rs.next();
		return rs.getString(1);
}

	public String getCustomer(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select customer_id  from inventory_transaction where reference_id ='"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getConsignment(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select consignment  from inventory_transaction where reference_id ='"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getFromStatus(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select from_status from inventory_transaction where reference_id ='"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getToStatus(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select to_status from inventory_transaction where reference_id ='" + reference
				+ "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploadedDate(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  uploaded_dstamp from inventory_transaction where reference_id ='"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploadedFileName(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  uploaded_filename from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUploaded(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select  uploaded from inventory_transaction where reference_id = '"
				+ reference + "' and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getDwsPalletRef(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_4 from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getIntoDestinationDate(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_DATE_1 from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getIfosOrderNum(String reference, String notes) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select USER_DEF_TYPE_2 from inventory_transaction where reference_id = '"
				+ reference + "'  and notes ='" + notes + "'");
		rs.next();
		return rs.getString(1);
	}
}
