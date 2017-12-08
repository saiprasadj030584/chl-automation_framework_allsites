package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

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
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("Select BOOKING_DSTAMP from booking_in_diary_log where bookref_id='"
				+ bookingId + "' order by key desc FETCH FIRST 1 ROWS ONLY");
		 rs = stmt
				.executeQuery("Select BOOKING_DSTAMP from booking_in_diary_log where bookref_id='"
						+ bookingId + "' order by key desc FETCH FIRST 1 ROWS ONLY");
		 if (!rs.next()) {
				context.setErrorMessage("BOOKING_DSTAMP not found for the bookingId :");
				Assert.fail("BOOKING_DSTAMP not found for the bookingId :");
			}else
			{
				System.out.println("BOOKING_DSTAMP Found for bookingId :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! BOOKING_DSTAMP not found for the bookingId :");
				Assert.fail("Exception Caught !!! BOOKING_DSTAMP not found for the bookingId :" );
				

			}
			return rs.getString(1);
		}


	
	public String getChangedDockId(String bookingId) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("Select LOCATION_ID from booking_in_diary_log where bookref_id='" + bookingId
				+ "' order by key desc FETCH FIRST 1 ROWS ONLY");
		 rs = stmt
				.executeQuery("Select LOCATION_ID from booking_in_diary_log where bookref_id='" + bookingId
						+ "' order by key desc FETCH FIRST 1 ROWS ONLY");
		 if (!rs.next()) {
				context.setErrorMessage("LOCATION_ID not found for the bookingId :");
				Assert.fail("LOCATION_ID not found for the bookingId :");
			}else
			{
				System.out.println("LOCATION_ID Found for bookingId :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! LOCATION_ID not found for the bookingId :");
				Assert.fail("Exception Caught !!! LOCATION_ID not found for the bookingId :" );
				

			}
			return rs.getString(1);
		}
}
