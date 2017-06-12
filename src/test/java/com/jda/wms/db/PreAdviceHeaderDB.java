package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PreAdviceHeaderDB {
	private final Context context;
	private final Database database;

	@Inject
	public PreAdviceHeaderDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public HashMap<String, String> getPreAdviceHeaderDetails(String preAdviceID)
			throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;
		HashMap<String, String> preAdviceHeaderMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}
		System.out.println("select status, due_dstamp, site_id, supplier_id, pre_advice_type,num_lines,name,address1, country  from pre_advice_header WHERE pre_advice_id = '"
						+ preAdviceID + "'");
		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery("select status, due_dstamp, site_id, supplier_id, pre_advice_type,num_lines,name,address1, country  from pre_advice_header WHERE pre_advice_id = '"
						+ preAdviceID + "'");
		resultSet.next();
		System.out.println(resultSet.getString(1));
		preAdviceHeaderMap.put("STATUS", resultSet.getString(1));
		preAdviceHeaderMap.put("DUEDATE", resultSet.getString(2));
		preAdviceHeaderMap.put("SITEID", resultSet.getString(3));
		preAdviceHeaderMap.put("SUPPLIERID", resultSet.getString(4));
		preAdviceHeaderMap.put("PREADVICETYPE", resultSet.getString(5));
		preAdviceHeaderMap.put("NUMLINES", resultSet.getString(6));

		preAdviceHeaderMap.put("NAME", resultSet.getString(7));
		preAdviceHeaderMap.put("ADDRESS1", resultSet.getString(8));
		preAdviceHeaderMap.put("COUNTRY", resultSet.getString(9));

		return preAdviceHeaderMap;
	}

	public String getStatus(String preAdviceId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select status from pre_advice_header WHERE pre_advice_id = '" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getNumberOfLines(String preAdviceId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select status from pre_advice_header WHERE pre_advice_id = '" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPreAdviceId(String status) throws ClassNotFoundException {
		try{
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select pre_advice_id from pre_advice_header WHERE status = '" + status + "'");
		rs.next();
		return rs.getString(1);
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

	public void updateStatus(String preAdviceId, String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("update pre_advice_header set status = '" + status + "' where pre_advice_id = '" + preAdviceId + "'");
		context.getConnection().commit();
	}
}
