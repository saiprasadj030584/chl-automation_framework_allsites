package com.jda.wms.dataload.gm;

import java.sql.SQLException;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DataSetUp {
	private static InsertDataIntoDB insertDataIntoDB;
	private static DeleteDataFromDB deleteDataFromDB;
	private static SelectDataFromDB selectDataFromDB;
	private static UpdateDataFromDB updateDataFromDB;
	private static Context context;

	@Inject
	public DataSetUp(InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, UpdateDataFromDB updateDataFromDB, Context context) {
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.updateDataFromDB = updateDataFromDB;
		this.context = context;
	}

	public static void setPOData() throws ClassNotFoundException, SQLException, InterruptedException {
		deleteDataFromDB.deletePreAdviceHeader(context.getPreAdviceId());
		insertDataIntoDB.insertPreAdviceHeader(context.getPreAdviceId());
		Thread.sleep(3000);
		Thread.sleep(3000);
	}

	public static void setSTOData() {
	}

}
