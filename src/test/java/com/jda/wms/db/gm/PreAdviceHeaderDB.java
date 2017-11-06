package com.jda.wms.db.gm;

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
		HashMap<String, String> preAdviceHeaderMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(
				"select status, due_dstamp, site_id, supplier_id, pre_advice_type,num_lines,name,address1, country  from pre_advice_header WHERE pre_advice_id = '"
						+ preAdviceID + "'");
		resultSet.next();
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
		System.out.println("select status from pre_advice_header WHERE pre_advice_id = '" + preAdviceId + "'");
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
				.executeQuery("select NUM_LINES from pre_advice_header WHERE pre_advice_id = '" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getPreAdviceId(String status) throws ClassNotFoundException {
		try {
			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select pre_advice_id from pre_advice_header WHERE status = '" + status + "' and num_lines='1'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public void updateStatus(String preAdviceId, String status) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update pre_advice_header set status = '" + status + "' where pre_advice_id = '" + preAdviceId + "'");
		context.getConnection().commit();
	}
	
	public void updateAdviceNumber(String adviceNumber,String preAdviceId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"update pre_advice_header set USER_DEF_TYPE_1= '" + adviceNumber + "' where pre_advice_id = '" + preAdviceId + "'");
		context.getConnection().commit();
	}

	public String getSupplierId(String preAdviceId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select supplier_id from pre_advice_header where pre_advice_id = '" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUserDefType5(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select user_def_type_5 from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUserDefType1(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select user_def_type_1 from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getUserDefType2(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(
				"select user_def_type_2 from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getSiteID(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select site_id from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
		rs.next();
		return rs.getString(1);
	}

	public String getQtyDueSum(String preAdviceId) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select sum(qty_due) from pre_advice_line where pre_advice_id = '" + preAdviceId + "' ");
		rs.next();
		return rs.getString(1);
	}
	
	public void updateAdviceForSku(String preAdviceId, String adviceId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("update pre_advice_header set user_def_type_1='" + adviceId
				+ "' where pre_advice_id='" + preAdviceId + "'");
		context.getConnection().commit();
	}

	public Object getPreAdviceIdForPO(String preAdviceId) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select pre_advice_id from pre_advice_header where pre_advice_id = '" + preAdviceId + "' ");
		rs.next();
		return rs.getString(1);
	}

}
