package com.jda.wms.db.gm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class LocationDB {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Database database;
	private SkuDB skuDB;
	@Inject
	public LocationDB(Context context, Database database,SkuDB skuDB) {
		this.context = context;
		this.database = database;
		this.skuDB= skuDB;
	}

	public String getCheckString(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select check_string from location where location_id = '" + location + "'");
		ResultSet rs = stmt.executeQuery("select check_string from location where location_id = '" + location + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);

	}

	public String getLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();

		System.out.println("select zone_1 from location where location_id = '" + location + "'");

		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "'");
		rs.next();
		String temp = rs.getString(1);
		rs.close();
		return temp;
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
	public boolean getHangingLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select zone_1 from location where location_id = '" + location + "' and zone_1 = 'HANG' and user_def_type_2 = 'HANG' and user_def_type_3 = 'HANG'and lock_status = 'UnLocked'");
		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "' and zone_1 = 'HANG' and user_def_type_2 = 'HANG' and user_def_type_3 = 'HANG'and lock_status = 'UnLocked'");
		if(rs.next())
			
			return true;
		else
			return false;
		
	}
	public boolean checkpreflocation(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select distinct(Zone_1) from location where  location_id = '" + location + "' and Zone_1 like 'BOXPREFF%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'BOX%' and lock_status='UnLocked' ");
		ResultSet rs = stmt.executeQuery("select distinct(Zone_1) from location where  location_id = '" + location + "' and Zone_1 like 'BOXPREFF%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'BOX%' and lock_status='UnLocked'");
		if(rs.next())
			
			return true;
		else
			return false;
		
	}
	public boolean getBoxedLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select zone_1 from location where location_id = '" + location + "' and zone_1 like 'BOX%' and  user_def_type_2 like 'BOX%' and user_def_type_3 = 'BOX' and lock_status = 'UnLocked'");
		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "' and zone_1 like 'BOX%' and  user_def_type_2 like 'BOX%'  and user_def_type_3 = 'BOX' and lock_status = 'UnLocked'");
		if(rs.next())
			
			return true;
		else
			return false;
		
	}
	public boolean getFlatpackLocationZone(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		System.out.println("select zone_1 from location where location_id = '" + location + "' and zone_1 in ('HANG','BOX') and user_def_type_2 in ('HANG','BOX') and user_def_type_3 = 'FLAT' and lock_status = 'UnLocked'");
		ResultSet rs = stmt.executeQuery("select zone_1 from location where location_id = '" + location + "' and user_def_type_3 = 'FLAT' and lock_status = 'UnLocked' and zone_1 like '%HANG%' and user_def_type_2 like ('%HANG%') and user_def_type_1 = '"+skuDB.getProductGroup(context.getSkuId())+"' union select zone_1 from location where location_id = '" + location + "' and user_def_type_3 = 'FLAT' and lock_status = 'UnLocked' and zone_1 like '%BOX%' and user_def_type_2 like '%BOX%'");
			
		if(rs.next())
			
			return true;
		else
			return false;
		
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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
	
	public ArrayList<String> getLocationForZone(String zone) throws SQLException, ClassNotFoundException {
		ArrayList<String> locationList = new ArrayList<String>();
		if (context.getConnection() == null) {
			database.connect();
		}


		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select location_id from location where zone_1 LIKE '%"+zone+"%' and lock_status='UnLocked'");
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				locationList.add((rs.getString(j)));
			}
		}
		return locationList;
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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
	public String getUserDefType3(String location) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select user_def_type_3 from location where location_id = '" + location + "'");
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
	
	public String getToLocationForPutaway(String skuType,String department) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		
		System.out.println("select location_id from location where Zone_1 like '"+skuType+"%' and user_def_type_2 like '"+skuType+"%' and user_def_type_3 like '"+skuType+"%' and lock_status='UnLocked' and user_def_type_1='"+department+"' and current_volume='0'");
		ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '"+skuType+"%' and user_def_type_2 like '"+skuType+"%' and user_def_type_3 like '"+skuType+"%' and lock_status='UnLocked' and user_def_type_1='"+department+"' and current_volume='0'");
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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}

	public String getPutawayLocationForGoh(String type)
			throws SQLException, ClassNotFoundException {
		System.out.println(
				"select location_id from location  where zone_1 like '" + type + "%' and user_def_type_2 like '" + type
						+ "%' and user_def_type_3 like '" + type + "%' and current_volume='0'" + "and lock_status='UnLocked'");
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		// ResultSet rs = stmt.executeQuery(
		// "select location_id from location where lock_status='" + status + "'
		// and user_def_type_1='"+prodGrp+"'");
		ResultSet rs = stmt.executeQuery(
				"select location_id from location  where zone_1 like '" + type + "%' and user_def_type_2 like '" + type

						+ "%' and user_def_type_3 like '" + type + "%' and current_volume='0'" + "and lock_status='UnLocked'");
		
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);

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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);

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
		if (!rs.next()) {context.setErrorMessage("Record not found in DB");Assert.fail("Record not found in DB");} else{System.out.println("Record found in DB");}return rs.getString(1);
	}
	
public boolean getunlocked_GOH_location(String location) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select * from location where location_id = '" + location + "' and zone_1 = 'HANG' and user_def_type_2 = 'HANG' and user_def_type_3 = 'HANG' and lock_status = 'UnLocked'");
	ResultSet rs = stmt.executeQuery("select location_id from location where location_id = '" + location + "' and zone_1 = 'HANG' and user_def_type_2 = 'HANG' and user_def_type_3 = 'HANG' and lock_status = 'UnLocked'");
	if(rs.next())
		return true;
	else
		return false;
}
public boolean getunlockedlocation(String location) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}

	Statement stmt = context.getConnection().createStatement();
	System.out.println("select * from location where location_id = '" + location + "'  and lock_status = 'UnLocked'");
	ResultSet rs = stmt.executeQuery("select location_id from location where location_id = '" + location + "'  and lock_status = 'UnLocked'");
	if(rs.next())
		return true;
	else
		return false;
}



public String getToLocationForPutawayGOH(String skuType,String department) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();

	}

	Statement stmt = context.getConnection().createStatement();
	
	System.out.println("select location_id from location where Zone_1 like '"+skuType+"%' and user_def_type_2 like '"+skuType+"%' and user_def_type_3 like '"+skuType+"%' and lock_status='UnLocked' a and current_volume='0'");
	ResultSet rs = stmt.executeQuery("select location_id from location where Zone_1 like '"+skuType+"%' and user_def_type_2 like '"+skuType+"%' and user_def_type_3 like '"+skuType+"%' and lock_status='UnLocked' and  current_volume='0'");
	
	rs.next();
	return rs.getString(1);
	}

	
public String getToLocationForPutawayBoxedPreferred() throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	System.out.println("select distinct(location_id) from location where Zone_1 like 'BOXPREFF%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'BOX%' and lock_status='UnLocked' and current_volume='0'");
	ResultSet rs = stmt.executeQuery("select distinct(location_id) from location where Zone_1 like 'BOXPREFF%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'BOX%' and lock_status='UnLocked' and current_volume='0'");
	rs.next();
	return rs.getString(1);
}
public String getToLocationForPutawayBoxedNormal() throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	System.out.println("select distinct(location_id) from location where Zone_1 like 'BOXF%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'BOX%' and lock_status='UnLocked' and current_volume='0'");
	ResultSet rs = stmt.executeQuery("select distinct(location_id) from location where Zone_1 like 'BOXF%' and user_def_type_2 like 'BOX%' and user_def_type_3 like 'BOX%' and lock_status='UnLocked' and current_volume='0'");
	rs.next();
	return rs.getString(1);
}

	

	public String getLockStatus(String location) throws SQLException, ClassNotFoundException {


		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select lock_status from location where location_id = '" + location + "'");
		rs.next();
		return rs.getString(1);

		}





}









