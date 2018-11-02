package com.jda.wms.db.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
//import com.jda.wms.pages.Exit.SiteQueryPage;
//import com.jda.wms.stepdefs.Exit.SiteQueryStepDefs;

public class SiteDB {
	private static Context context;
	private static Database database;
//	private SiteQueryPage siteQueryPage;
//	private SiteQueryStepDefs siteQueryStepDefs;

	@Inject
	public SiteDB(Context context, Database database
//			,SiteQueryPage siteQueryPage,SiteQueryStepDefs siteQueryStepDefs
			) {
		this.context = context;
		this.database = database;
//		this.siteQueryPage=siteQueryPage;
//		this.siteQueryStepDefs=siteQueryStepDefs;

	}
	


public static String getTimeZoneDB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select time_zone_name from site where site_id='" + siteID +  "'");
	rs.next();
	return (rs.getString(1));
}
public static String getUDT3DB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select user_def_type_3 from site where site_id= '"+siteID+"'");
	rs.next();
	return (rs.getString(1));
}
public static String getUDT4DB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select user_def_type_4 from site where site_id= '"+siteID+"' ");
	rs.next();
	return (rs.getString(1));
}
public static String getUDN1DB(String siteID) throws SQLException, ClassNotFoundException {
	if (context.getConnection() == null) {
		database.connect();
	}
	Statement stmt = context.getConnection().createStatement();
	ResultSet rs = stmt.executeQuery("select user_def_note_1 from site where site_id= '"+siteID+"' ");
	rs.next();
	return (rs.getString(1));
}

}