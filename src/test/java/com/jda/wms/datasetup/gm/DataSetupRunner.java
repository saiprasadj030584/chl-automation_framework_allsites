package com.jda.wms.datasetup.gm;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DataSetupRunner {
	public static DbConnection dataBase = new DbConnection();

	public void getTagList() {

		ResultSet resultSet = null;
		String columnsList = null;
		ArrayList tagList = new ArrayList();

		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement().executeQuery(
					"Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID in (SELECT TOP 1 PARENT_REQUEST_ID FROM DBO.JDA_GM_RUN_REQUESTS where REQUEST_STATUS='NO_RUN' ORDER BY PARENT_REQUEST_ID DESC )");
			while (resultSet.next()) {
				System.err.println(resultSet.getString("PARENT_REQUEST_ID"));
				tagList.add(resultSet.getString("PARENT_REQUEST_ID"));

			}
			dataBase.disconnectDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
