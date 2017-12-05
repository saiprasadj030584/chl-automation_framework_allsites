package com.jda.wms.db.gm;

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

	public String getLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
//		System.out.println("select zone_1 from location where location_id = '" + location + "'");
		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "'");
		rs.next();
		String temp = rs.getString(1);
		rs.close();
		return temp;
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

	public String getLocation(String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where lock_status = '" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String checkBoxZone() throws ClassNotFoundException, SQLException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT COUNT(LOC_TYPE) as loc_count FROM LOCATION WHERE LOC_TYPE != 'Tag-FIFO' AND ZONE_1 LIKE 'BOXPREF%'");
		rs.next();
		return rs.getString("loc_count");

	}

	public String getUserDefType2(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_2 from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUserDefType3(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_3 from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocationForFlatpack(String type, String prodGrp)
			throws SQLException, ClassNotFoundException {
		System.out.println("select location_id from location  where zone_1='" + type + "' and user_def_type_2='" + type
				+ "' and user_def_type_3='" + type + "' and current_volume='0'" + "and user_def_type_1='" + prodGrp
				+ "'");
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		// ResultSet rs = stmt.executeQuery(
		// "select location_id from location where lock_status='" + status + "'
		// and user_def_type_1='"+prodGrp+"'");
		ResultSet rs = stmt.executeQuery("select location_id from location  where zone_1='" + type
				+ "' and user_def_type_2='" + type + "' and user_def_type_3='" + type + "' and current_volume='0'"
				+ "and user_def_type_1='" + prodGrp + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocationForBoxed(String type, String type2, String status)
			throws SQLException, ClassNotFoundException {
		System.out.println("select location_id from location  where zone_1='" + type + "' and user_def_type_2='" + type
				+ "' and user_def_type_3='" + type + "' and current_volume='0'" + "and lock_status='" + status + "'");
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location  where zone_1='" + type
				+ "' and user_def_type_2='" + type2 + "' and user_def_type_3='" + type2 + "' and current_volume='0'"
				+ "and lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocationForHanging(String type, String prodGrp, String status)
			throws SQLException, ClassNotFoundException {
		System.out.println("select location_id from location  where zone_1='" + type + "' and user_def_type_2='" + type
				+ "' and user_def_type_3='" + type + "' and current_volume='0'" + "and user_def_type_1='" + prodGrp
				+ "'");
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location  where zone_1='" + type
				+ "' and user_def_type_2='" + type + "' and user_def_type_3='" + type + "' and current_volume='0'"
				+ "and user_def_type_1='" + prodGrp + "' and lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPutawayLocationForGoh(String type, String prodGrp, String status)
			throws SQLException, ClassNotFoundException {
		System.out.println(
				"select location_id from location  where zone_1 like '" + type + "%' and user_def_type_2 like '" + type
						+ "%' and user_def_type_3 like '" + type + "%' and current_volume='0'" + "and user_def_type_1='"
						+ prodGrp + "' and lock_status='" + status + "'");
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		// ResultSet rs = stmt.executeQuery(
		// "select location_id from location where lock_status='" + status + "'
		// and user_def_type_1='"+prodGrp+"'");
		ResultSet rs = stmt.executeQuery(
				"select location_id from location  where zone_1 like '" + type + "%' and user_def_type_2 like '" + type
						+ "%' and user_def_type_3 like '" + type + "%' and current_volume='0'" + "and user_def_type_1='"
						+ prodGrp + "' and lock_status='" + status + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getToLocationForPutawayBoxed(String skuType) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select location_id from location where Zone_1 like '" + skuType
				+ "%' and user_def_type_2 like '" + skuType + "%' and user_def_type_3 like '" + skuType
				+ "%' lock_status='UnLocked' and current_volume='0'");
		ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '" + skuType
				+ "%' and user_def_type_2 like '" + skuType + "%' and user_def_type_3 like '" + skuType
				+ "%' and lock_status='UnLocked' and current_volume='0'");
		rs.next();
		return rs.getString(1);
	}

	public String getToLocationForPutawayFlatpack(String department) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println(
				"select location_id from location where (zone_1 like 'BOX%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'FLAT%' and lock_status='UnLocked' and current_volume='0')"
						+ "or (zone_1 like 'HANG%' and user_def_type_2 like 'HANG%' and user_def_type_3 like 'FLAT%' and user_def_type_1='"
						+ department + "' and lock_status='UnLocked' and current_volume='0')");
		ResultSet rs = stmt.executeQuery(
				"select location_id from location where (zone_1 like 'BOX%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'FLAT%' and lock_status='UnLocked' and current_volume='0')"
						+ "or (zone_1 like 'HANG%' and user_def_type_2 like 'HANG%' and user_def_type_3 like 'FLAT%' and user_def_type_1='"
						+ department + "' and lock_status='UnLocked' and current_volume='0')");
		rs.next();
		return rs.getString(1);
	}

	public String getUserDefType1(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_1 from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getToLocationForPutaway(String skuType, String department)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select location_id from location where Zone_1 like '" + skuType
				+ "%' and user_def_type_2 like '" + skuType + "%' and user_def_type_3 like '" + skuType
				+ "%' and lock_status='UnLocked' and user_def_type_1='" + department + "' and current_volume='0'");
		ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '" + skuType
				+ "%' and user_def_type_2 like '" + skuType + "%' and user_def_type_3 like '" + skuType
				+ "%' and lock_status='UnLocked' and user_def_type_1='" + department + "' and current_volume='0'");
		rs.next();
		return rs.getString(1);
	}

	public boolean getunlockedlocation(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out
				.println("select * from location where location_id = '" + location + "'  and lock_status = 'UnLocked'");
		ResultSet rs = stmt.executeQuery("select location_id from location where location_id = '" + location
				+ "'  and lock_status = 'UnLocked'");
		if (rs.next())
			return true;
		else
			return false;
	}

	public boolean getunlocked_GOH_location(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select * from location where location_id = '" + location
				+ "' and zone_1 = 'HANG' and user_def_type_2 = 'HANG' and user_def_type_3 = 'HANG' and lock_status = 'UnLocked'");
		ResultSet rs = stmt.executeQuery("select location_id from location where location_id = '" + location
				+ "' and zone_1 = 'HANG' and user_def_type_2 = 'HANG' and user_def_type_3 = 'HANG' and lock_status = 'UnLocked'");
		if (rs.next())
			return true;
		else
			return false;
	}

	public String getToLocationForPutawayGOH(String skuType, String department)
			throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();

		System.out.println("select location_id from location where Zone_1 like '" + skuType
				+ "%' and user_def_type_2 like '" + skuType + "%' and user_def_type_3 like '" + skuType
				+ "%' and lock_status='UnLocked' a and current_volume='0'");
		ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '" + skuType
				+ "%' and user_def_type_2 like '" + skuType + "%' and user_def_type_3 like '" + skuType
				+ "%' and lock_status='UnLocked' and  current_volume='0'");

		rs.next();
		return rs.getString(1);
	}

	
}
