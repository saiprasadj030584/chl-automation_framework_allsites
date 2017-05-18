package com.jda.wms.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PreAdviceHeaderPage {
	private Context context;
	private Connection connection;

	@Inject
	public PreAdviceHeaderPage(Context context) {
		this.context = context;
	}

	public String getStatus(String preAdviceId) {
		String statusPreAdviceHeader = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select status from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			statusPreAdviceHeader = rs.getString(1);

		} catch (SQLException ex) {
		}
		return statusPreAdviceHeader;
	}

	public String getSiteId(String preAdviceId) {
		String siteId = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select site_id from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			siteId = rs.getString(1);

		} catch (SQLException ex) {
		}
		return siteId;
	}

	public String getSupplier(String preAdviceId) {
		String supplier = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select supplier_id from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			supplier = rs.getString(1);

		} catch (SQLException ex) {
		}
		return supplier;
	}

	public String getNumberOfLines(String preAdviceId) {
		String numberOfLines = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select num_lines from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			numberOfLines = rs.getString(1);

		} catch (SQLException ex) {
		}
		return numberOfLines;
	}

	public String getName(String preAdviceId) {
		String name = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select name from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			name = rs.getString(1);

		} catch (SQLException ex) {
		}
		return name;
	}

	public String getAddress1(String preAdviceId) {
		String address1 = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select address1 from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			address1 = rs.getString(1);

		} catch (SQLException ex) {
		}
		return address1;

	}

	public String getCountry(String preAdviceId) {
		String country = "";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("select country from pre_advice_header where pre_advice_id='" + preAdviceId + "'");
			rs.next();
			country = rs.getString(1);

		} catch (SQLException ex) {
		}
		return country;

	}
}
