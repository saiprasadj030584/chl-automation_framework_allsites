package com.jda.wms.datasetup.gm;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class GetTcData {
	public static DbConnection dataBase = new DbConnection();
	private Context context;

	@Inject
	public GetTcData(Context context) {
		this.context = context;
	}

	public String getPo() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "' and uniqueTag='" + context.getUniqueTag() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("PO_ID");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getSto() {

		ResultSet resultSet = null;
		String value = null;

		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "' and uniqueTag='" + context.getUniqueTag() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("STO_ID");

			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getUpi() {

		ResultSet resultSet = null;
		String value = null;

		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "' and uniqueTag='" + context.getUniqueTag() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("PALLET_ID");

			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getAsn() {

		ResultSet resultSet = null;
		String value = null;

		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "' and uniqueTag='" + context.getUniqueTag() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("ASN_ID");

			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getSkuList() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID ='"
							+ context.getParentRequestId() + "' and uniqueTag='" + context.getUniqueTag() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("SKU_ID");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

}
// SKU_ID,QTY,LOCK_CODE,LOCATION,PERFECT_CONDITION,REASON_CODE,SUPPLIER_ID,FROM_SITE,TO_SITE,DEPT,ADDRESS_ID