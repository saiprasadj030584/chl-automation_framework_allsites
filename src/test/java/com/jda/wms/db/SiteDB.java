package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SiteDB {
	private  Context context;
	private  Database database;

	@Inject
	public SiteDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}


public String getTimeZoneDB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select time_zone_name from site where site_id='" + siteID +  "'");
	rs.next();
	return (rs.getString(1));
}
public  String getUDT3DB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select user_def_type_3 from site where site_id= '"+siteID+"'");
	rs.next();
	return (rs.getString(1));
}
public  String getUDT4DB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select user_def_type_4 from site where site_id= '"+siteID+"' ");
	rs.next();
	return (rs.getString(1));
}
public  String getUDN1DB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select user_def_note_1 from site where site_id= '"+siteID+"' ");
	rs.next();
	return (rs.getString(1));
}

}