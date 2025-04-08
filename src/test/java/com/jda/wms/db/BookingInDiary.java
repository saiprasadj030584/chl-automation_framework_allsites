package com.jda.wms.db;

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
		System.out.println("select trailer_id from booking_in_diary where bookref_id='"+bookingId+"'");
		ResultSet rs = stmt.executeQuery(
				"select trailer_id from booking_in_diary where bookref_id='"+bookingId+"'");
		rs.next();
		return rs.getString(1);
	}
	/**
	 * @author Vedika.Vinod
	 * @param bookingId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * This function is to get the Bay based on the booking ID
	 */
	public String getBay(String bookingId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select location_id from booking_in_diary where bookref_id='"+bookingId+"'");
		ResultSet rs = stmt.executeQuery(
				"select location_id from booking_in_diary where bookref_id='"+bookingId+"'");
		rs.next();
		return rs.getString(1);
	}
}
