package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class BookingInDiaryDetails {
	private Context context;
	private Database database;

	@Inject
	public BookingInDiaryDetails(Context context, Database database) {
		this.context = context;
		this.database = database;
	}
	
	/**
	 * @author Vedika.Vinod
	 * This function is to get the booking id based on consignment and trailer id
	 */
	public String getBookingID(String consignment,String trailer_id) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select bookref_id from booking_in_diary_details where reference_id='"+consignment+"' and trailer_id='"+trailer_id+"'");
		ResultSet rs = stmt.executeQuery(
				"select bookref_id from booking_in_diary_details where reference_id='"+consignment+"' and trailer_id='"+trailer_id+"'");
		rs.next();
		return rs.getString(1);
	}
}
