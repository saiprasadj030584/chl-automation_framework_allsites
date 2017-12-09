package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderContainerDB {
	private Context context;
	private Database database;

	@Inject
	public OrderContainerDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}


	public String getStatus(String orderId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select status from order_container where order_id='"+orderId+"'");
		ResultSet rs = stmt
				.executeQuery("select status from order_container where order_id='"+orderId+"'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getPalletId(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select pallet_id from order_container where order_id='" + orderId + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getContainerId(String orderId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select container_id from order_container where order_id='" + orderId + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
	
	public String selectURN(String orderId) throws ClassNotFoundException, SQLException {
		System.out.println("select pallet_id from order_container where ORDER_ID = '" + orderId + "'");
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select pallet_id from order_container where order_id = '" + orderId + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
}










