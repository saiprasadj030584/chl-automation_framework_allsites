package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ConsignmentDB {
	private Context context;
	private Database database;
	
	
	@Inject
	public  ConsignmentDB(Context context, Database database) {
		
		this.context = context;
		this.database = database;
		
	}
	
	
	/**
	 * @author Vedika.Vinod This function is to check if consignment exists
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isConsignmentExist(String consignment) throws SQLException, ClassNotFoundException {
		boolean isRecord = true;
		try {

			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			System.out.println("select * from consignment where consignment ='" + consignment + "'");
			ResultSet rs = stmt.executeQuery("select * from consignment where consignment ='" + consignment + "'");
			isRecord = rs.next();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return isRecord;
	}

	/**
	 * @author Vedika.Vinod This function is to get port of loading
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public String getPortOfLoading() throws ClassNotFoundException, SQLException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_2 from consignment where user_def_type_2 is not null  and rownum =1 ");
		ResultSet rs = stmt.executeQuery(
				"select user_def_type_2 from consignment where user_def_type_2 is not null  and rownum =1");
		rs.next();

		return rs.getString(1);
	}

	/**
	 * @author Vedika.Vinod This function is to get airway bill
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public String getAirwayBill() throws ClassNotFoundException, SQLException {

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select user_def_type_3 from consignment where user_def_type_3 is not null  and rownum =1 ");
		ResultSet rs = stmt.executeQuery(
				"select user_def_type_3 from consignment where user_def_type_3 is not null  and rownum =1");
		rs.next();

		return rs.getString(1);
	}
	public String getTrailerID(String consignment) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
		database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select trailer_id from consignment where consignment='" + consignment + "'");
		ResultSet rs = stmt.executeQuery("select trailer_id from consignment where consignment='" + consignment + "'");
		rs.next();
		return rs.getString(1);
		}

}
