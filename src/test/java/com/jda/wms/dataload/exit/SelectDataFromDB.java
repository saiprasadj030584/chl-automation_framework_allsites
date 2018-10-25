package com.jda.wms.dataload.exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.Database;

public class SelectDataFromDB {
	private Context context;
	private Database database;

	@Inject
	public SelectDataFromDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public boolean isPreAdviceRecordExists(String poId) throws ClassNotFoundException, InterruptedException {
		boolean isRecordExists = false;
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			int waitTime = 2;
			do {
				Statement stmt = context.getConnection().createStatement();
				rs = stmt.executeQuery("SELECT PRE_ADVICE_ID FROM PRE_ADVICE_HEADER WHERE PRE_ADVICE_ID = '" + poId + "'");
				if (rs.next()) {
					break;
				}
				Thread.sleep(2000);
				waitTime = waitTime + 2;
			} while (waitTime < 120);

			String getpoIdfromDB = rs.getString(1);
			if (poId.equals(getpoIdfromDB)) {
				isRecordExists = true;
			}
		} catch (SQLException e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				System.out.println("Validating STO");
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}
	public boolean isUpiRecordExists(String palletID) throws ClassNotFoundException {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT PALLET_ID FROM upi_receipt_header WHERE pallet_id = '" + palletID + "'");
			rs.next();
			if (rs.getString(1).equals(palletID)) {
				isRecordExists = true;
			}
		} catch (SQLException e) {
			
			if (e.getMessage().contains("Exhausted Resultset")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}
	public boolean isOrderRecordExists(String orderId) throws ClassNotFoundException, InterruptedException {
		boolean isRecordExists = false;
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println("SELECT ORDER_ID FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
			rs = stmt.executeQuery("SELECT ORDER_ID FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
			rs.next();

			String getOrderIdfromDB = rs.getString(1);
			if (orderId.equals(getOrderIdfromDB)) {
				isRecordExists = true;
			}
		} catch (SQLException e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				System.out.println("Validating STO");
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}

	public boolean isOrderHeaderRecordExists(String orderId) throws ClassNotFoundException, InterruptedException {
		boolean isRecordExists = false;
		ResultSet rs = null;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			int waitTime = 2;
			do {
				Statement stmt = context.getConnection().createStatement();
				rs = stmt.executeQuery("SELECT ORDER_ID FROM ORDER_HEADER WHERE ORDER_ID = '" + orderId + "'");
				if (rs.next()) {
					break;
				}
				Thread.sleep(2000);
				waitTime = waitTime + 2;
			} while (waitTime < 120);

			String getOrderIdfromDB = rs.getString(1);
			if (orderId.equals(getOrderIdfromDB)) {
				isRecordExists = true;
			}
		} catch (SQLException e) {
			if (e.getMessage().contains("Exhausted Resultset")) {
				System.out.println("Validating STO");
				isRecordExists = false;
			}
		}
		return isRecordExists;
	}
	
		
}
