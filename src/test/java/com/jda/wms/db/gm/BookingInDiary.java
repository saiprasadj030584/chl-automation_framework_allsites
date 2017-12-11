package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

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
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select trailer_id from booking_in_diary where bookref_id='" + bookingId + "'");
		 rs = stmt
				.executeQuery("select trailer_id from booking_in_diary where bookref_id='" + bookingId + "'");
		 if (!rs.next()) {
				context.setErrorMessage("trailer_id not found for the bookingID :");
				Assert.fail("trailer_id not found for the bookingID :");
			}else
			{
				System.out.println("trailer_id Found for bookingID :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! trailer_id not found for the bookingID :");
				Assert.fail("Exception Caught !!! trailer_id not found for the bookingID :" );
				


			}
			return rs.getString(1);
		}
	
	public String getCarrier(String bookingID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select CARRIER_ID from booking_in_diary where bookref_id='" + bookingID + "'");
		 rs = stmt
				.executeQuery("select CARRIER_ID from booking_in_diary where bookref_id='" + bookingID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("CARRIER_ID not found for the bookingID :");
				Assert.fail("CARRIER_ID not found for the bookingID :");
			}else
			{
				System.out.println("CARRIER_ID Found for bookingID :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! CARRIER_ID not found for the bookingID :");
				Assert.fail("Exception Caught !!! CARRIER_ID not found for the bookingID :" );
				

			}
			return rs.getString(1);
		}

	public String getServiceLevel(String bookingID) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select SERVICE_LEVEL from booking_in_diary where bookref_id='" + bookingID + "'");
		 rs = stmt
				.executeQuery("select SERVICE_LEVEL from booking_in_diary where bookref_id='" + bookingID + "'");
		 if (!rs.next()) {
				context.setErrorMessage("SERVICE_LEVEL not found for the bookingID :");
				Assert.fail("SERVICE_LEVEL not found for the bookingID :");
			}else
			{
				System.out.println("SERVICE_LEVEL Found for bookingID :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! SERVICE_LEVEL not found for the bookingID :");
				Assert.fail("Exception Caught !!! SERVICE_LEVEL not found for the bookingID :" );
				

			}
			return rs.getString(1);
		}

		


	public boolean isBookingExists(String bookingNo) throws SQLException, ClassNotFoundException {
		boolean isRecordExists = false;
		try {
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();

			ResultSet rs = stmt.executeQuery("select BOOKREF_ID from trailer where BOOKREF_ID='" + bookingNo + "'");
			if (!rs.next()) {
				context.setErrorMessage("BOOKREF_ID not found for the bookingNo :");
				Assert.fail("BOOKREF_ID not found for the bookingNo :");
			}else
			{
				System.out.println("LOCATION_ID Found for bookingNo :");
			}
		

			rs.next();
			if (rs.getString(1) != null) {
				isRecordExists = true;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("BOOKREF_ID not found for the bookingNo :")) {
				isRecordExists = false;
			}
		}
		return isRecordExists;
}
	
	
	public String selectDockDoor(String bookingID) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		try {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select LOCATION_ID from booking_in_diary where bookref_id='"+bookingID+"'");
		 rs = stmt
				.executeQuery("select LOCATION_ID from booking_in_diary where bookref_id='"+bookingID+"'");
		 if (!rs.next()) {
				context.setErrorMessage("LOCATION_ID not found for the bookingID :");
				Assert.fail("LOCATION_ID not found for the bookingID :");
			}else
			{
				System.out.println("LOCATION_ID Found for bookingID :");
			}
		}
		 catch (Exception e) {
				context.setErrorMessage("Exception Caught !!! LOCATION_ID not found for the bookingID :");
				Assert.fail("Exception Caught !!! LOCATION_ID not found for the bookingID :" );
				

			}
			return rs.getString(1);
		}
}
		













