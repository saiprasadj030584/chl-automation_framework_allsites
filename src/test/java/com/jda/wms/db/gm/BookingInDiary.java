package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class BookingInDiary {
	private Context context;
	private Database database;

	@Inject
	public BookingInDiary(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getTrailerID(String bookingId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select trailer_id from booking_in_diary where bookref_id='" + bookingId + "'");
		ResultSet rs = stmt
				.executeQuery("select trailer_id from booking_in_diary where bookref_id='" + bookingId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getCarrier(String bookingID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select CARRIER_ID from booking_in_diary where bookref_id='" + bookingID + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getServiceLevel(String bookingID) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select SERVICE_LEVEL from booking_in_diary where bookref_id='" + bookingID + "'");
		rs.next();
		return rs.getString(1);
	}
}
