package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class LocationDB {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Database database;

	@Inject
	public LocationDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public String getCheckString(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select check_string from location where location_id = '" + location + "'");
		ResultSet rs = stmt.executeQuery("select check_string from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
		 
	}
	public String getStatus(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select LOCK_STATUS from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
		 
	}
	
	public String getLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
	} 
	
	public ArrayList<String> getLocation() throws SQLException, ClassNotFoundException {
		ArrayList<String> locationList = new ArrayList<String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where zone_1 LIKE 'A%RESV'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				locationList.add((rs.getString(j)));
			}
		}
		return locationList;
	}
	
	public String getResvLocation() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where zone_1 LIKE 'A%RESV'");
		rs.next();
		return rs.getString(1);
}


	public String getLocation(String status) throws SQLException, ClassNotFoundException {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select location_id from location where lock_status = '" + status + "' and pick_face is null");
			rs.next();
			return rs.getString(1);
	}
	public String getLocationResv(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where lock_status = '" + status + "' and zone_1 LIKE 'A%RESV'");
		rs.next();
		return rs.getString(1);
}

	public String getFixedLocation() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where pick_face='F' and zone_1 LIKE 'ASRESV'");
		rs.next();
		return rs.getString(1);
//		ResultSet rs = stmt.executeQuery("select location_id from location where pick_face='F' and zone_1 is not null");
//		rs.next();
//		return rs.getString(1);
//		
	}
	public String getNotFixedLocation() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where pick_face is null and zone_1 LIKE 'A%RESV'");
		rs.next();
		return rs.getString(1);
	}
	public String getLocationForOverRide() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where pick_face is null and zone_1 LIKE 'A%RESV' and lock_status='UnLocked'");
		rs.next();
		return rs.getString(1);
	}
	public String getLocationForOverRideWithstatus(String status) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where pick_face is null and zone_1 LIKE 'A%RESV' and lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getLocationForOverRideWithstatusOutLocked(String status) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}
	
	public String getlocationID(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where lock_status = '" + location + "'");
		rs.next();
		return rs.getString(1);
}
	
	public String getPickFace(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select pick_face from location  where location_id='" + location + "'");
		rs.next();
		return rs.getString(1);
}
	
	public String getLocForRelocate() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where pick_face is null and zone_1 LIKE 'A%RESV' and lock_status='UnLocked'");
		rs.next();
		return rs.getString(1);
}
	
	


	public ArrayList<String> getLocationList() throws SQLException, ClassNotFoundException {
		ArrayList<String> locationList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where zone_1 LIKE 'A%RESV'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				locationList.add((rs.getString(j)));
			}
		}
		return locationList;
	}
	
	
	public String updateLocation(String fromLocationList,String status) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("update location set lock_status = '" + status + "' where location_id='" + fromLocationList + "'");
		ResultSet rs = stmt
				.executeQuery("update location set lock_status = '" + status + "' where location_id='" + fromLocationList + "'");
		context.getConnection().commit();
		return status;
	}
//	public void updateLocation(ArrayList<String> fromLocationList,String status) throws ClassNotFoundException, SQLException {
//
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery("select location_id from location where zone_1 LIKE 'A%RESV'");
//		ResultSetMetaData rsmd = rs.getMetaData();
//
//		while (rs.next()) {
//			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
//				locationList.add((rs.getString(j)));
//			}
//		}
//		return locationList;
//	}

	public String updateLocation(String[] LocationArray, String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("update location set lock_status = '" + status + "' where location_id='" + LocationArray + "'");
		ResultSet rs = stmt.executeQuery("update location set lock_status = '" + status + "' where location_id='" + LocationArray + "'");
		context.getConnection().commit();
		return status;
		}

	public String getRedLocation() throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select location_id from location where work_zone='REDB'");
		ResultSet rs = stmt
				.executeQuery("select location_id from location where work_zone='REDB'");
		context.getConnection().commit();
		return rs.getString(1);
	}
	}
	
		 
	
	




