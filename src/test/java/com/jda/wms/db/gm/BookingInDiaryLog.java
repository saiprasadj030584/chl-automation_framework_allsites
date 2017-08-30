package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class BookingInDiaryLog {
	private Context context;
	private Database database;

	@Inject
	public BookingInDiaryLog(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getChangedBookingTime(String bookingId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select BOOKING_DSTAMP from booking_in_diary_log where bookref_id='"
				+ bookingId + "' order by key desc FETCH FIRST 1 ROWS ONLY");

		rs.next();
		return rs.getString(1);
	}

	public String getChangedDockId(String bookingId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select LOCATION_ID from booking_in_diary_log where bookref_id='" + bookingId
				+ "' order by key desc FETCH FIRST 1 ROWS ONLY");

		rs.next();
		return rs.getString(1);
	}

}
