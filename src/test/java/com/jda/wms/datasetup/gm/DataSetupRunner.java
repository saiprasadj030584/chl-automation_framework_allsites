package com.jda.wms.datasetup.gm;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class DataSetupRunner {
	private Context context;
	public static DbConnection dataBase;

	@Inject
	public DataSetupRunner(Context context, DbConnection dataBase) {
		this.context = context;
		this.dataBase = dataBase;
	}

	public void getTagListFromAutoDb() {
		ResultSet resultSet = null;
		ArrayList tagList = new ArrayList();
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement().executeQuery(
					"Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID in (SELECT TOP 1 PARENT_REQUEST_ID FROM DBO.JDA_GM_RUN_REQUESTS where REQUEST_STATUS='NO_RUN' ORDER BY PARENT_REQUEST_ID ASC )");
			while (resultSet.next()) {
				tagList.add(resultSet.getString("UNIQUE_TAG"));
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getParentRequestIdFromDB() {
		ResultSet resultSet = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement().executeQuery(
					"SELECT TOP 1 PARENT_REQUEST_ID FROM DBO.JDA_GM_RUN_REQUESTS where REQUEST_STATUS='NO_RUN' ORDER BY PARENT_REQUEST_ID ASC");
			while (resultSet.next()) {
				context.setParentRequestId(resultSet.getString("PARENT_REQUEST_ID"));
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getJdaSiteIdFromDB() {
		ResultSet resultSet = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_REQUESTS where PARENT_REQUEST_ID='"
							+ context.getParentRequestId() + "'");
			while (resultSet.next()) {
				context.setSiteId(resultSet.getString("SITE_NO"));
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void insertDataToJdaDB(ArrayList<String> tagListForScenario) {
		String uniqueTag = "";
		for (String tag : tagListForScenario) {
			if (tag.length() > uniqueTag.length()) {
				uniqueTag = tag;
			}
		}
		context.setUniqueTag(uniqueTag);
		Assert.assertTrue("UniqueTag Not Found in Test Data Table", validateUniqueTagInTestData());
	//	Assert.assertTrue("UniqueTag Not Found in Run Status Table", validateUniqueTagInRunStatus());
		
		insertData();
	}

	private boolean validateUniqueTagInTestData() {
		ResultSet resultSet = null;
		boolean UniqueTagInTestData = false;
		try {
			dataBase.connectAutomationDB();
			System.err.println("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
					+ "' AND SITE_NO='" + context.getSiteId() + "'");
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");
			while (resultSet.next()) {
				String temp = resultSet.getString("UNIQUE_TAG");
				UniqueTagInTestData = true;
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			UniqueTagInTestData = false;
		}
		return UniqueTagInTestData;
	}

	private boolean validateUniqueTagInRunStatus() {
		ResultSet resultSet = null;
		boolean UniqueTagInRunStatus = false;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID='"
							+ context.getParentRequestId() + "' and UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "' and TC_STATUS='NO_RUN' ; ");
			while (resultSet.next()) {
				String temp = resultSet.getString("UNIQUE_TAG");
				UniqueTagInRunStatus = true;
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			UniqueTagInRunStatus = false;
		}
		return UniqueTagInRunStatus;
	}

	public void insertData() {

		if (context.getUniqueTag().contains("direct")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_PRE_ADVICE_HEADER values ((Select max (Key) + 1 from Interface_Pre_advice_header),'M+S', '3070000861' ,'PO','5649','M+S', 'M00354' ,'Released',null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,'SEA',null, null, 'T86' ,null,null,'Direct', 'B' ,null,null,null,'N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,null,null,'N',null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))'");
				dataBase.dbConnection.commit();
				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_PRE_ADVICE_LINE values ((Select max (Key) + 1 from Interface_Pre_advice_line),'M+S', '3070000861', 10 ,null,null, '000000022417617001' ,null,null,null,null,null,null,null,null,null,null,40,null,null,null,'N', null, 'T86' ,null,null, '06240783' ,null,null, 'B' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,2017,22222,null,null,'EA',null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'NDC','A','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '100560' ,'5649', 'MX180160' ,'Released',null,'M+S', 'M00354' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '180160' ,null,null,null,null,null,null,'ZEDC', '180160' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '00050456011731234600' ,'5649',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '100560' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1',null, 'CN5314835',null,null,'MSX3645','ZEDC', '180160' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '00050456011731234600' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '00050456011731234600', 10 ,null,null, '00050456011731234600' ,'M+S','M+S', '000000022417617001', '22417617001EA' ,'EA',null,null,null,null, 'M00354' ,null,null,null,null, 20 ,'3070000861', 10 ,'N', '7112244962000010' ,'3070000861' ,null, '06240783', '100560' ,null,'ZEDC',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222,null, '00050456011731234600' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("fsv")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_PRE_ADVICE_HEADER values ((Select max (Key) + 1 from Interface_Pre_advice_header),'M+S', '3070000861' ,'PO','5649','M+S', 'M00354' ,'Released',null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,'SEA',null, null, 'T86' ,null,null,'FSV', 'B' ,null,null,null,'N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,null,null,'N',null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))'");
				dataBase.dbConnection.commit();
				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_PRE_ADVICE_LINE values ((Select max (Key) + 1 from Interface_Pre_advice_line),'M+S', '3070000861', 10 ,null,null, '000000022417617001' ,null,null,null,null,null,null,null,null,null,null,40,null,null,null,'N', null, 'T86' ,null,null, '06240783' ,null,null, 'B' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,2017,22222,null,null,'EA',null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'NDC','A','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '100560' ,'5649', 'MX180160' ,'Released',null,'M+S', 'M00354' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '180160' ,null,null,null,null,null,null,'ZEDC', '180160' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("idt") && context.getUniqueTag().contains("receiving")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '100560' ,'5649', 'MX180160' ,'Released',null,'M+S', 'M00354' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '180160' ,null,null,null,null,null,null,'ZEDC', '180160' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '00050456011731234600' ,'5649',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '100560' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1',null, 'CN5314835',null,null,'MSX3645','ZEDC', '180160' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '00050456011731234600' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '00050456011731234600', 10 ,null,null, '00050456011731234600' ,'M+S','M+S', '000000022417617001', '22417617001EA' ,'EA',null,null,null,null, 'M00354' ,null,null,null,null, 20 ,'3070000861', 10 ,'N', '7112244962000010' ,'3070000861' ,null, '06240783', '100560' ,null,'ZEDC',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222,null, '00050456011731234600' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("rms")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '400526' ,'5649', 'RMSMX390126' ,'Released',null,'M+S', '0054' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '0054300517390126' ,null,null,null,null,null,null,'ZRET', '0054300517390126' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '56490004068850005410011061702000' ,'5649',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '400526' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1','00000000002116229489', null,null,null,'000000000090200020','ZRET', '0054300517390126' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '56490004068850005410011061702000' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '56490004068850005410011061702000', 10 ,null,null, '56490004068850005410011061702000' ,'M+S','M+S', '000000022417617001', '22417617001EA' ,'EA',null,null,null,null, null ,null,null,null,null, 20 ,null, 10 ,'N', '9212244746000010' ,'8070000721' ,null, '06240783', '400526' ,null,'ZRET',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222, '56490004068850005410011061702000', '56490004068850005410011061702000' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("non") && context.getUniqueTag().contains("rms")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '400526' ,'5649', 'RMSMX390126' ,'Released',null,'M+S', '0054' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '0054300517390126' ,null,null,null,null,null,null,'ZRET', '0054300517390126' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '56490004068850005410011061702000' ,'5649',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '400526' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1','00000000002116229489', null,null,null,'000000000090200020','ZRET', '0054300517390126' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '56490004068850005410011061702000' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '56490004068850005410011061702000', 10 ,null,null, '56490004068850005410011061702000' ,'M+S','M+S', '000000022417617001', '22417617001EA' ,'EA',null,null,null,null, null ,null,null,null,null, 20 ,null, 10 ,'N', '9212244746000010' ,'8070000721' ,null, '06240783', '400526' ,null,'ZRET',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222, '56490004068850005410011061702000', '56490004068850005410011061702000' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("returns") && !context.getUniqueTag().contains("rms")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '400526' ,'5649', 'RMSMX390126' ,'Released',null,'M+S', '0054' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '0054300517390126' ,null,null,null,null,null,null,'ZRET', '0054300517390126' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '56490004068850005410011061702000' ,'5649',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '400526' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1','00000000002116229489', null,null,null,'000000000090200020','ZRET', '0054300517390126' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '56490004068850005410011061702000' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '56490004068850005410011061702000', 10 ,null,null, '56490004068850005410011061702000' ,'M+S','M+S', '000000022417617001', '22417617001EA' ,'EA',null,null,null,null, null ,null,null,null,null, 20 ,null, 10 ,'N', '9212244746000010' ,'8070000721' ,null, '06240783', '400526' ,null,'ZRET',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222, '56490004068850005410011061702000', '56490004068850005410011061702000' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("retail")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5171000678' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '0055' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, null ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null, '3366' ,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '5171000678', 10 ,null,null, '000000022417617001' ,null,null,'EA',null,'Y',null,null,null,null,null,null, 2 ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', '06240783' ,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		else if (context.getUniqueTag().contains("IDT") && context.getUniqueTag().contains("order")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		else if (context.getUniqueTag().contains("international")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		else if (context.getUniqueTag().contains("writeoff")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		else if (context.getUniqueTag().contains("external")) {

			try {
				dataBase.connectAutomationDB();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.dbConnection.createStatement().execute(
						"Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '5470100509' ,null,null,'Released','Hold','50',null,null,null,null,null,'5649',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF')) ");
				dataBase.dbConnection.commit();

				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}
	}
}
