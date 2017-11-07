package com.jda.wms.datasetup.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.Database;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.utils.Utilities;

public class DataSetupRunner {
	private Context context;
	public static DbConnection npsDataBase;
	private GetTcData gettcdata;
	private Database jdaJdatabase;
	private DataLoadFromUI dataLoadFromUI;
	private JdaLoginPage jdaLoginPage;

	@Inject
	public DataSetupRunner(Context context, DbConnection dataBase, Database jdaJdatabase, GetTcData gettcdata,
			DataLoadFromUI dataLoadFromUI, JdaLoginPage jdaLoginPage) {
		this.context = context;
		this.npsDataBase = dataBase;
		this.gettcdata = gettcdata;
		this.jdaJdatabase = jdaJdatabase;
		this.dataLoadFromUI = dataLoadFromUI;
		this.jdaLoginPage = jdaLoginPage;
	}

	public void getTagListFromAutoDb() {
		ResultSet resultSet = null;
		ArrayList tagList = new ArrayList();
		try {
			npsDataBase.connectAutomationDB();
			resultSet = npsDataBase.dbConnection.createStatement().executeQuery(
					"Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID in (SELECT TOP 1 PARENT_REQUEST_ID FROM DBO.JDA_GM_RUN_REQUESTS where REQUEST_STATUS='NO_RUN' ORDER BY PARENT_REQUEST_ID ASC )");
			while (resultSet.next()) {
				tagList.add(resultSet.getString("UNIQUE_TAG"));
			}
			npsDataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getParentRequestIdFromDB() {
		ResultSet resultSet = null;
		try {
			npsDataBase.connectAutomationDB();
			resultSet = npsDataBase.dbConnection.createStatement().executeQuery(
					"SELECT TOP 1 PARENT_REQUEST_ID FROM DBO.JDA_GM_RUN_REQUESTS where REQUEST_STATUS='NO_RUN' ORDER BY PARENT_REQUEST_ID ASC");
			while (resultSet.next()) {
				context.setParentRequestId(resultSet.getString("PARENT_REQUEST_ID"));
			}
			npsDataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void getJdaSiteIdFromDB() {
		ResultSet resultSet = null;
		try {
			npsDataBase.connectAutomationDB();
			resultSet = npsDataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_REQUESTS where PARENT_REQUEST_ID='"
							+ context.getParentRequestId() + "'");
			while (resultSet.next()) {
				context.setSiteId(resultSet.getString("SITE_NO"));
			}
			npsDataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void insertDataToJdaDB(ArrayList<String> tagListForScenario) throws ClassNotFoundException, SQLException {
		String uniqueTag = "";
		for (String tag : tagListForScenario) {
			if (tag.length() > uniqueTag.length()) {
				uniqueTag = tag;
			}
		}
		context.setUniqueTag(uniqueTag.toLowerCase());
		Assert.assertTrue("Unique Tag Not Found in Test Data Table", validateUniqueTagInTestData());
//		 insertData();
		createTestDataFromUI();
	}

	private void createTestDataFromUI() {
		if (context.getUniqueTag().contains("direct")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String asn = newAsnId();
				String po = newPoId();
				String upi = newPalletdId_directPO();
				String odn = newOdnId();

				// Fetching Reference Test Data from Test data table
				String asnReference = gettcdata.getAsnFromTestData();
				String poReference = gettcdata.getPoFromTestData();
				String upiReference = gettcdata.getUpiFromTestData();
				// String orderReference = gettcdata.getOdnFromTestData();

				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateASN(asnReference, asn);
				validateAsnDataSetup(asn);
				dataLoadFromUI.duplicateUPI(upiReference, upi);
				validateUpiDataSetup(upi);
				dataLoadFromUI.duplicatePO(poReference, po);
				// dataLoadFromUI.killBrowser();
				validatePoDataSetup(po);
				// dataLoadFromUI.killBrowser();
				gettcdata.setAsnId(asn);
				gettcdata.setPo(po);
				gettcdata.setPalletId(upi);
				context.setTestData("PO:" + po + ";UPI:" + upi + ";ASN:" + asn);
				// validateAsnDataSetup(asn);
				// validatePoDataSetup(po);
				// validateUpiDataSetup(upi);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("fsv")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String po = newPoId();

				// Fetching Refernce Test Data from Test data table

				String poReference = gettcdata.getPoFromTestData();

				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicatePO(poReference, po);
				// dataLoadFromUI.killBrowser();
				validatePoDataSetup(po);
				gettcdata.setPo(po);
				context.setTestData("PO:" + po);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("returns") && context.getUniqueTag().contains("rms")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String asn = newAsnId();
				// String upi = newPalletdId(); //32 digit number
				// Fetching Refernce Test Data from Test data table
				String upiReference = gettcdata.getUpiFromTestData();
				String asnReference = gettcdata.getAsnFromTestData();

				// To form the UPI ID for returns
				String supplierIdRef = getSupplierIDFromJDADB(upiReference);
				String qty = gettcdata.getQtyListFromTestData();
				String upi = formReturnsUPIID(supplierIdRef, qty);

				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateASN(asnReference, asn);
				validateAsnDataSetup(asn);
				dataLoadFromUI.duplicateUPI(upiReference, upi);
				// dataLoadFromUI.killBrowser();
				validateUpiDataSetup(upi);
				gettcdata.setAsnId(asn);
				gettcdata.setPalletId(upi);
				context.setTestData("UPI:" + upi + ";ASN:" + asn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("returns") && context.getUniqueTag().contains("rms")
				&& context.getUniqueTag().contains("non")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String asn = newAsnId();
				String upi = newPalletdId();
				// Fetching Refernce Test Data from Test data table
				String upiReference = gettcdata.getUpiFromTestData();

				String asnReference = gettcdata.getAsnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateASN(asnReference, asn);
				validateAsnDataSetup(asn);
				dataLoadFromUI.duplicateUPI(upiReference, upi);
				// dataLoadFromUI.killBrowser();
				validateUpiDataSetup(upi);
				gettcdata.setAsnId(asn);
				gettcdata.setPalletId(upi);
				context.setTestData("UPI:" + upi + ";ASN:" + asn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("returns") && !context.getUniqueTag().contains("rms")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String asn = newAsnId();
				String upi = newPalletdId();
				// Fetching Refernce Test Data from Test data table
				String upiReference = gettcdata.getUpiFromTestData();
				validateUpiDataSetup(upi);
				String asnReference = gettcdata.getAsnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateASN(asnReference, asn);
				validateAsnDataSetup(asn);
				dataLoadFromUI.duplicateUPI(upiReference, upi);
				validateUpiDataSetup(upi);
				gettcdata.setPalletId(upi);
				context.setTestData("UPI:" + upi + ";ASN:" + asn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("retail")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String odn = newOdnId();
				// Fetching Refernce Test Data from Test data table
				String odnReference = gettcdata.getOdnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateOdn(odnReference, odn);
				validateOdnDataSetup(odn);
				gettcdata.setOdn(odn);
				context.setTestData("STO:" + odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("idt") && context.getUniqueTag().contains("order")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String odn = newOdnId();
				// Fetching Refernce Test Data from Test data table
				String odnReference = gettcdata.getOdnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateOdn(odnReference, odn);
				validateOdnDataSetup(odn);
				gettcdata.setOdn(odn);
				context.setTestData("STO:" + odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("store") && context.getUniqueTag().contains("order")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String odn = newOdnId();
				// Fetching Refernce Test Data from Test data table
				String odnReference = gettcdata.getOdnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateOdn(odnReference, odn);
				validateOdnDataSetup(odn);
				gettcdata.setOdn(odn);
				context.setTestData("STO:" + odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("outlet") && context.getUniqueTag().contains("order")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String odn = newOdnId();
				// Fetching Refernce Test Data from Test data table
				String odnReference = gettcdata.getOdnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateOdn(odnReference, odn);
				validateOdnDataSetup(odn);
				gettcdata.setOdn(odn);
				context.setTestData("STO:" + odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("e_com")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String odn = newOdnId();
				// Fetching Refernce Test Data from Test data table
				String odnReference = gettcdata.getOdnFromTestData();
				// Call JDA Login
				System.out.println("Ecom Order creation");
				System.out.println("order "+odn);
				jdaLoginPage.login();
				dataLoadFromUI.duplicateOdn(odnReference, odn);
				validateOdnDataSetup(odn);
				gettcdata.setOdn(odn);
				context.setTestData("STO:" + odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("international")) {
			try {
				npsDataBase.connectAutomationDB();
				// Generate Random New values to load
				String odn = newOdnId();
				// Fetching Refernce Test Data from Test data table
				String odnReference = gettcdata.getOdnFromTestData();
				// Call JDA Login
				jdaLoginPage.login();
				dataLoadFromUI.duplicateOdn(odnReference, odn);
				validateOdnDataSetup(odn);
				gettcdata.setOdn(odn);
				context.setTestData("STO:" + odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private boolean validateUniqueTagInTestData() {
		ResultSet resultSet = null;
		boolean UniqueTagInTestData = false;
		try {
			npsDataBase.connectAutomationDB();
			System.out.println("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
					+ "' AND SITE_NO='" + context.getSiteId() + "'");
			// resultSet = npsDataBase.dbConnection.createStatement()
			// .executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE
			// UNIQUE_TAG ='" + context.getUniqueTag()
			// + "' AND SITE_NO='" + context.getSiteId() + "'");
			resultSet = npsDataBase.dbConnection.createStatement().executeQuery(
					"SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag() + "'");
			while (resultSet.next()) {
				String temp = resultSet.getString("UNIQUE_TAG");
				UniqueTagInTestData = true;
			}
			npsDataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			UniqueTagInTestData = false;
		}
		return UniqueTagInTestData;
	}

	boolean validateUniqueTagInRunStatus() {
		ResultSet resultSet = null;
		boolean UniqueTagInRunStatus = false;
		try {
			npsDataBase.connectAutomationDB();
			// resultSet = npsDataBase.dbConnection.createStatement()
			// .executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where
			// PARENT_REQUEST_ID='"
			// + context.getParentRequestId() + "' and UNIQUE_TAG ='" +
			// context.getUniqueTag()
			// + "' AND SITE_NO='" + context.getSiteId() + "' and
			// TC_STATUS='NO_RUN' ; ");
			resultSet = npsDataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.NPS_AUTO_UI_RUN_STATUS where PARENT_REQUEST_ID='"
							+ context.getParentRequestId() + "' and UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "' and TC_STATUS='NO_RUN' ; ");
			while (resultSet.next()) {
				String temp = resultSet.getString("UNIQUE_TAG");
				UniqueTagInRunStatus = true;
			}
			npsDataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			UniqueTagInRunStatus = false;
		}
		return UniqueTagInRunStatus;
	}

	public void insertData() throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		if (context.getUniqueTag().contains("direct")) {
			try {
				npsDataBase.connectAutomationDB();
				String asn = newAsnId();
				String po = newPoId();
				// String upi = newPalletdId();
				String upi = newPalletdId_directPO();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyFromTestData();
				context.setTestData("PO:" + po + ";UPI:" + upi + ";ASN:" + asn);
				String delivery_qry = "Insert into Interface_delivery values ((Select max (Key) from Interface_Delivery)+1, '"
						+ asn + "' ,'" + context.getSiteId()
						+ "', 'MX180160' ,'Released',null,'M+S',(Select SUPPLIER_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '180160' ,null,null,null,null,null,null,'ZEDC', '180160' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(delivery_qry);
				ResultSet rinsert = stmt.executeQuery(delivery_qry);
				context.getConnection().commit();
				gettcdata.setAsnId(asn);
				String upi_header_qry = "Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key)  from INTERFACE_UPI_RECEIPT_HEADER)+ 1, '"
						+ upi + "' ,'" + context.getSiteId()
						+ "',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '" + asn
						+ "' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1',null, 'CN5314835',null,null,'MSX3645','ZEDC', '180160' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '"
						+ upi
						+ "' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_header_qry);
				rinsert = stmt.executeQuery(upi_header_qry);
				context.getConnection().commit();
				String upi_line_qry = "Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key)  from INTERFACE_UPI_RECEIPT_LINE)+ 1, '"
						+ upi + "', 10 ,null,null, '" + upi + "' ,'M+S','M+S', '" + sku
						+ "', (Select CONFIG_ID from sku_sku_config where sku_id='" + sku
						+ "' and ROWNUM = 1 ),(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,null,null, (Select SUPPLIER_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1),null,null,null,null, " + qty + " ,'" + po
						+ "', 10 ,'N', '7112244962000010' ,'" + po
						+ "' ,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='" + sku
						+ "' and ROWNUM = 1), '" + asn
						+ "' ,null,'ZEDC',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222,null, '"
						+ upi
						+ "' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_line_qry);
				rinsert = stmt.executeQuery(upi_line_qry);
				context.getConnection().commit();
				String po_header_qry = "Insert into INTERFACE_PRE_ADVICE_HEADER values ((Select max (Key)  from Interface_Pre_advice_header)+ 1,'M+S', '"
						+ po + "' ,'PO','" + context.getSiteId()
						+ "','M+S', (Select SUPPLIER_ID from supplier_sku where sku_id='" + sku
						+ "' and ROWNUM = 1),'Released',null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,'SEA',null, null, (select product_group from sku where sku_id='"
						+ gettcdata.getSkuList()
						+ "' and ROWNUM = 1 ) ,null,null,'Direct', (select user_def_type_8 from sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,null,null,null,'N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,null,null,'N',null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(po_header_qry);
				rinsert = stmt.executeQuery(po_header_qry);
				gettcdata.setPo(po);
				context.getConnection().commit();
				String po_line_qry = "Insert into INTERFACE_PRE_ADVICE_LINE values ((Select max (Key) from Interface_Pre_advice_line) + 1,'M+S', '"
						+ po + "', 10 ,null,null, '" + sku + "' ,null,null,null,null,null,null,null,null,null,null,"
						+ qty + ",null,null,null,'N', null, (select product_group from sku where sku_id='" + sku
						+ "' and ROWNUM = 1 ) ,null,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1) ,null,null, (select user_def_type_8 from sku where sku_id='" + sku
						+ "' and ROWNUM = 1 ),null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,2017,22222,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'NDC','A','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(po_line_qry);
				rinsert = stmt.executeQuery(po_line_qry);
				context.getConnection().commit();
				gettcdata.setPalletId(upi);
				// gettcdata.setSkuQtySupplier();
				validateAsnDataSetup(asn);
				validatePoDataSetup(po);
				validateUpiDataSetup(upi);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("fsv")) {
			try {
				npsDataBase.connectAutomationDB();
				String po = newPoId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyFromTestData();
				System.out.println("sku " + sku);
				String po_header_qry = "Insert into INTERFACE_PRE_ADVICE_HEADER values ((Select max (Key)  from Interface_Pre_advice_header)+ 1,'M+S', '"
						+ po + "' ,'PO','" + context.getSiteId()
						+ "','M+S', (Select SUPPLIER_ID from supplier_sku where sku_id='" + sku
						+ "' and ROWNUM = 1),'Released',null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,'SEA',null, null, (select product_group from sku where sku_id='"
						+ gettcdata.getSkuListFromTestData()
						+ "' and ROWNUM = 1 ) ,null,null,'FSV', (select user_def_type_8 from sku where sku_id='" + sku
						+ "' and ROWNUM = 1) ,null,null,null,'N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,null,null,'N',null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(po_header_qry);
				ResultSet rinsert = stmt.executeQuery(po_header_qry);
				gettcdata.setPo(po);
				context.getConnection().commit();
				String po_line_qry = "Insert into INTERFACE_PRE_ADVICE_LINE values ((Select max (Key) from Interface_Pre_advice_line) + 1,'M+S', '"
						+ po + "', 10 ,null,null, '" + sku + "' ,null,null,null,null,null,null,null,null,null,null,"
						+ qty + ",null,null,null,'N', null, (select product_group from sku where sku_id='" + sku
						+ "' and ROWNUM = 1 ) ,null,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1) ,null,null, (select user_def_type_8 from sku where sku_id='" + sku
						+ "' and ROWNUM = 1 ),null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,2017,22222,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'NDC','A','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(po_line_qry);
				rinsert = stmt.executeQuery(po_line_qry);
				context.getConnection().commit();
				// gettcdata.setSkuQtySupplier();
				validatePoDataSetup(po);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (context.getUniqueTag().contains("returns") && context.getUniqueTag().contains("rms")) {
			try {
				npsDataBase.connectAutomationDB();
				String asn = newAsnId();
				String upi = newRmsPalletdId();
				String sku = gettcdata.getSkuListFromTestData();
				String delivery_qry = "Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '"
						+ asn + "' ,'" + context.getSiteId()
						+ "', 'RMSMX390126' ,'Released',null,'M+S', '0054' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '0054300517390126' ,null,null,null,null,null,null,'ZRET', '0054300517390126' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(delivery_qry);
				ResultSet rinsert = stmt.executeQuery(delivery_qry);
				context.getConnection().commit();
				gettcdata.setAsnId(asn);
				String upi_header_qry = "Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '"
						+ upi + "' ,'" + context.getSiteId()
						+ "',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '" + asn
						+ "' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1','00000000002116229489', null,null,null,'000000000090200020','ZRET', '0054300517390126' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '"
						+ upi
						+ "' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_header_qry);
				rinsert = stmt.executeQuery(upi_header_qry);
				context.getConnection().commit();
				String upi_line_qry = "Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '"
						+ upi + "', 10 ,null,null, '" + upi + "' ,'M+S','M+S', '" + sku
						+ "', (Select CONFIG_ID from sku_sku_config where sku_id='" + sku
						+ "' and ROWNUM = 1 ),(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,null,null, null ,null,null,null,null, 20 ,null, 10 ,'N', '9212244746000010' ,'8070000721' ,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1), '" + asn
						+ "' ,null,'ZRET',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222, '"
						+ upi + "', '" + upi
						+ "' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_line_qry);
				rinsert = stmt.executeQuery(upi_line_qry);
				context.getConnection().commit();
				gettcdata.setPalletId(upi);
				gettcdata.setSkuQtySupplier();
				validateAsnDataSetup(asn);
				validateUpiDataSetup(upi);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("returns") && context.getUniqueTag().contains("rms")
				&& context.getUniqueTag().contains("non")) {
			try {
				npsDataBase.connectAutomationDB();
				String asn = newAsnId();
				String upi = newRmsPalletdId();
				String sku = gettcdata.getSkuListFromTestData();
				String delivery_qry = "Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '"
						+ asn + "' ,'" + context.getSiteId()
						+ "', 'RMSMX390126' ,'Released',null,'M+S', '0054' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '0054300517390126' ,null,null,null,null,null,null,'ZRET', '0054300517390126' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(delivery_qry);
				ResultSet rinsert = stmt.executeQuery(delivery_qry);
				context.getConnection().commit();
				gettcdata.setAsnId(asn);
				String upi_header_qry = "Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '"
						+ upi + "' ,'" + context.getSiteId()
						+ "',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '" + asn
						+ "' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1','00000000002116229489', null,null,null,'000000000090200020','ZRET', '0054300517390126' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '"
						+ upi
						+ "' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_header_qry);
				rinsert = stmt.executeQuery(upi_header_qry);
				context.getConnection().commit();
				String upi_line_qry = "Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '"
						+ upi + "', 10 ,null,null, '" + upi + "' ,'M+S','M+S', '" + sku
						+ "', (Select CONFIG_ID from sku_sku_config where sku_id='" + sku
						+ "' and ROWNUM = 1 ),(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,null,null, null ,null,null,null,null, 20 ,null, 10 ,'N', '9212244746000010' ,'8070000721' ,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1), '" + asn
						+ "' ,null,'ZRET',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222, '"
						+ upi + "', '" + upi
						+ "' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_line_qry);
				rinsert = stmt.executeQuery(upi_line_qry);
				context.getConnection().commit();
				gettcdata.setPalletId(upi);
				gettcdata.setSkuQtySupplier();
				validateAsnDataSetup(asn);
				validateUpiDataSetup(upi);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("returns") && !context.getUniqueTag().contains("rms")) {
			try {
				npsDataBase.connectAutomationDB();
				String asn = newAsnId();
				String upi = newRmsPalletdId();
				String sku = gettcdata.getSkuListFromTestData();
				String delivery_qry = "Insert into Interface_delivery values ((Select max (Key) + 1 from Interface_Delivery), '"
						+ asn + "' ,'" + context.getSiteId()
						+ "', 'RMSMX390126' ,'Released',null,'M+S', '0054' ,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, '0054300517390126' ,null,null,null,null,null,null,'ZRET', '0054300517390126' ,null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,'Europe/London',null,null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(delivery_qry);
				ResultSet rinsert = stmt.executeQuery(delivery_qry);
				context.getConnection().commit();
				gettcdata.setAsnId(asn);
				String upi_header_qry = "Insert into INTERFACE_UPI_RECEIPT_HEADER values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_HEADER), '"
						+ upi + "' ,'" + context.getSiteId()
						+ "',to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null, '" + asn
						+ "' ,'M+S', null ,'PALLET',9999,160,null,null,null,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'SEA',null,'1','00000000002116229489', null,null,null,'000000000090200020','ZRET', '0054300517390126' ,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222, '"
						+ upi
						+ "' ,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_header_qry);
				rinsert = stmt.executeQuery(upi_header_qry);
				context.getConnection().commit();
				String upi_line_qry = "Insert into INTERFACE_UPI_RECEIPT_LINE values ((Select max (Key) + 1 from INTERFACE_UPI_RECEIPT_LINE), '"
						+ upi + "', 10 ,null,null, '" + upi + "' ,'M+S','M+S', '" + sku
						+ "', (Select CONFIG_ID from sku_sku_config where sku_id='" + sku
						+ "' and ROWNUM = 1 ),(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,null,null, null ,null,null,null,null, 20 ,null, 10 ,'N', '9212244746000010' ,'8070000721' ,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1), '" + asn
						+ "' ,null,'ZRET',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,1,1,null,22222, '"
						+ upi + "', '" + upi
						+ "' ,null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/London','London/Europe',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(upi_line_qry);
				rinsert = stmt.executeQuery(upi_line_qry);
				context.getConnection().commit();
				gettcdata.setPalletId(upi);
				gettcdata.setSkuQtySupplier();
				validateAsnDataSetup(asn);
				validateUpiDataSetup(upi);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("retail")) {
			try {
				npsDataBase.connectAutomationDB();
				String odn = newOdnId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyListFromTestData();
				String order_header_qry = "Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from interface_order_header),'M+S', '"
						+ odn + "' ,null,null,'Released','Hold','50',null,null,null,null,null,'" + context.getSiteId()
						+ "',null,'M+S', '0055' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, null ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null, '3366' ,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_header_qry);
				ResultSet rinsert = stmt.executeQuery(order_header_qry);
				context.getConnection().commit();
				gettcdata.setOdn(odn);
				String order_line_qry = "Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '"
						+ odn + "', 10 ,null,null, '" + sku
						+ "' ,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku + "' and ROWNUM = 1) and ROWNUM = 1),null,'Y',null,null,null,null,null,null, " + qty
						+ " ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_line_qry);
				rinsert = stmt.executeQuery(order_line_qry);
				context.getConnection().commit();
				gettcdata.setSkuQtySupplier();
				validateOdnDataSetup(odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("idt") && context.getUniqueTag().contains("order")) {
			try {
				npsDataBase.connectAutomationDB();
				String odn = newOdnId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyListFromTestData();
				npsDataBase.connectAutomationDB();
				String order_header_qry = "Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '"
						+ odn + "' ,null,null,'Released','Hold','50',null,null,null,null,null,'" + context.getSiteId()
						+ "',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_header_qry);
				ResultSet rinsert = stmt.executeQuery(order_header_qry);
				context.getConnection().commit();
				gettcdata.setOdn(odn);
				String order_line_qry = "Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '"
						+ odn + "', 10 ,null,null, '" + sku
						+ "' ,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku + "' and ROWNUM = 1) and ROWNUM = 1),null,'Y',null,null,null,null,null,null, " + qty
						+ "  ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_line_qry);
				rinsert = stmt.executeQuery(order_line_qry);
				context.getConnection().commit();
				gettcdata.setSkuQtySupplier();
				validateOdnDataSetup(odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("store") && context.getUniqueTag().contains("order")) {
			try {
				npsDataBase.connectAutomationDB();
				String odn = newOdnId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyListFromTestData();
				String order_header_qry = "Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from interface_order_header),'M+S', '"
						+ odn + "' ,null,null,'Released','Hold','50',null,null,null,null,null,'" + context.getSiteId()
						+ "',null,'M+S', '0055' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, null ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null, '3366' ,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_header_qry);
				ResultSet rinsert = stmt.executeQuery(order_header_qry);
				context.getConnection().commit();
				gettcdata.setOdn(odn);
				String order_line_qry = "Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '"
						+ odn + "', 10 ,null,null, '" + sku
						+ "' ,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku + "' and ROWNUM = 1) and ROWNUM = 1),null,'Y',null,null,null,null,null,null, " + qty
						+ " ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_line_qry);
				rinsert = stmt.executeQuery(order_line_qry);
				context.getConnection().commit();
				gettcdata.setSkuQtySupplier();
				validateOdnDataSetup(odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("outlet") && context.getUniqueTag().contains("order")) {
			try {
				npsDataBase.connectAutomationDB();
				String odn = newOdnId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyListFromTestData();
				String order_header_qry = "Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from interface_order_header),'M+S', '"
						+ odn + "' ,null,null,'Released','Hold','50',null,null,null,null,null,'" + context.getSiteId()
						+ "',null,'M+S', '0055' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, null ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null, '6596' ,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_header_qry);
				ResultSet rinsert = stmt.executeQuery(order_header_qry);
				context.getConnection().commit();
				gettcdata.setOdn(odn);
				String order_line_qry = "Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '"
						+ odn + "', 10 ,null,null, '" + sku
						+ "' ,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku + "' and ROWNUM = 1) and ROWNUM = 1),null,'Y',null,null,null,null,null,null, " + qty
						+ " ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_line_qry);
				rinsert = stmt.executeQuery(order_line_qry);
				context.getConnection().commit();
				gettcdata.setSkuQtySupplier();
				validateOdnDataSetup(odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("e_com")) {
			try {
				npsDataBase.connectAutomationDB();
				String odn = newOdnId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyListFromTestData();
				npsDataBase.connectAutomationDB();
				String order_header_qry = "Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from Interface_order_header),'M+S', '"
						+ odn + "' ,null,null,'Released','Hold','50',null,null,null,null,null,'" + context.getSiteId()
						+ "',null,'M+S', '4624' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, 362101 ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_header_qry);
				ResultSet rinsert = stmt.executeQuery(order_header_qry);
				context.getConnection().commit();
				gettcdata.setOdn(odn);
				String order_line_qry = "Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '"
						+ odn + "', 10 ,null,null, '" + sku
						+ "' ,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku + "' and ROWNUM = 1) and ROWNUM = 1),null,'Y',null,null,null,null,null,null, " + qty
						+ "  ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,'REPLEN',null,'IDT','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_line_qry);
				rinsert = stmt.executeQuery(order_line_qry);
				context.getConnection().commit();
				gettcdata.setSkuQtySupplier();
				validateOdnDataSetup(odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("international")) {
			try {
				npsDataBase.connectAutomationDB();
				String odn = newOdnId();
				String sku = gettcdata.getSkuListFromTestData();
				String qty = gettcdata.getQtyListFromTestData();
				String order_header_qry = "Insert into INTERFACE_ORDER_HEADER values ((Select max (Key) + 1 from interface_order_header),'M+S', '"
						+ odn + "' ,null,null,'Released','Hold','50',null,null,null,null,null,'" + context.getSiteId()
						+ "',null,'M+S', '0055' ,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,'N','N',null,null,null,null,null,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null, null ,null,null,22222,null,null,null,null,null,null,null,null,null,null,null,'N','N','N',null, '5542' ,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N','N',null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,null,null,null,null,'N','N','N',null,'N',null,null,'N',null,null,'N','Europe/London','Europe/London',null,'NDC','U','Pending','null',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_header_qry);
				ResultSet rinsert = stmt.executeQuery(order_header_qry);
				context.getConnection().commit();
				gettcdata.setOdn(odn);
				String order_line_qry = "Insert into INTERFACE_ORDER_LINE values ((Select max (Key) + 1 from Interface_order_line),'M+S', '"
						+ odn + "', 10 ,null,null, '" + sku
						+ "' ,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku + "' and ROWNUM = 1) and ROWNUM = 1),null,'Y',null,null,null,null,null,null, " + qty
						+ " ,'Y','N','Y',null,null,null,null,null,null,'N',null,null,null,null,'0001', (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) ,'Store','Cross Dock','Retail','ZN8',null,'N','N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";
				System.out.println(order_line_qry);
				rinsert = stmt.executeQuery(order_line_qry);
				context.getConnection().commit();
				gettcdata.setSkuQtySupplier();
				validateOdnDataSetup(odn);
				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public String newPoId() throws ClassNotFoundException, SQLException, InterruptedException {
		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		do {
			value = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(9);
			tempValue = String.valueOf(value) + String.valueOf(tempInt);
			HashMap<String, Boolean> presenceMap = validatePoPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}

	public String newAsnId() throws ClassNotFoundException, SQLException, InterruptedException {
		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		do {
			value = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(9);
			tempValue = String.valueOf(value) + String.valueOf(tempInt);
			HashMap<String, Boolean> presenceMap = validateAsnPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}

	public String newOdnId() throws ClassNotFoundException, SQLException, InterruptedException {
		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		do {
			value = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(9);
			tempValue = String.valueOf(value) + String.valueOf(tempInt);
			HashMap<String, Boolean> presenceMap = validateStoPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}

	public String newPalletdId() throws ClassNotFoundException, SQLException, InterruptedException {
		long value1, value2, value3, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		do {
			value1 = ThreadLocalRandom.current().nextLong(100000000, max);
			value2 = ThreadLocalRandom.current().nextLong(100000000, max);
			value3 = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(10000, 99999);
			tempValue = String.valueOf(value1) + String.valueOf(value2) + String.valueOf(value3)
					+ String.valueOf(tempInt);
			HashMap<String, Boolean> presenceMap = validateUpiPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}

	public String newPalletdId_directPO() throws ClassNotFoundException, SQLException, InterruptedException {
		long value1, value2, value3, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		do {
			value1 = ThreadLocalRandom.current().nextLong(100000000, max);
			System.out.println("value 1 " + value1);
			value2 = ThreadLocalRandom.current().nextLong(100000000, max);
			System.out.println("value 2 " + value2);
			// value3 = ThreadLocalRandom.current().nextLong(100000000, max);
			// System.out.println("value 3 "+value3);
			int tempInt = ThreadLocalRandom.current().nextInt(10, 99);
			// tempValue = String.valueOf(value1) + String.valueOf(value2) +
			// String.valueOf(value3)
			// + String.valueOf(tempInt);
			tempValue = String.valueOf(value1) + String.valueOf(value2) + String.valueOf(tempInt);
			System.out.println("temp value " + tempValue);
			HashMap<String, Boolean> presenceMap = validateUpiPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}

	public String newRmsPalletdId() throws ClassNotFoundException, SQLException, InterruptedException {
		long value1, value2, value3, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		do {
			value1 = ThreadLocalRandom.current().nextLong(100000000, max);
			value2 = ThreadLocalRandom.current().nextLong(100000000, max);
			value3 = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(0, 9);
			tempValue = context.getSiteId() + String.valueOf(value1) + String.valueOf(value2) + String.valueOf(value3)
					+ String.valueOf(tempInt);
			HashMap<String, Boolean> presenceMap = validateUpiPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}

	public HashMap<String, Boolean> validateAsnPresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from delivery where asn_id='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt.executeQuery("select count(*) from interface_delivery where asn_id='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	public HashMap<String, Boolean> validateStoPresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from order_header where order_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt.executeQuery("select count(*) from interface_order_header where order_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	public HashMap<String, Boolean> validatePoPresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select count(*) from pre_advice_header where pre_advice_id='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt.executeQuery(
				"select count(*) from interface_pre_advice_header where pre_advice_id='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	private void validateAsnDataSetup(String asn) throws InterruptedException, ClassNotFoundException, SQLException {
		boolean mainTable = true;
		boolean finalAsnStatus = false;
		int count = 0;
		if (!(finalAsnStatus)) {
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted ASN in Delivery : " + asn);
				HashMap<String, Boolean> presenceMap = validateAsnPresenceinJdaTable(asn);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err.println("ASN Data Not inserted till now - Slow Insertion - Failing : " + asn);
					Assert.assertFalse("ASN Data Not inserted till now - Slow Insertion - Failing : " + asn,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted ASN : " + asn);
			}
		}
	}

	private void validatePoDataSetup(String po) throws InterruptedException, ClassNotFoundException, SQLException {
		boolean mainTable = true;
		boolean finalPoStatus = false, finalPoLineStatus = false;

		if (!(finalPoStatus)) {
			int count = 0;
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted PO in Pre Advice Header : " + po);
				HashMap<String, Boolean> presenceMap = validatePoPresenceinJdaTable(po);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err
							.println("Pre Advice Header Data Not inserted till now - Slow Insertion - Failing : " + po);
					Assert.assertFalse(
							"Pre Advice Header Data Not inserted till now - Slow Insertion - Failing : " + po,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted PO : " + po);
			}
		}
		if (!(finalPoLineStatus)) {
			int count = 0;
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted PO in Pre Advice Line : " + po);
				HashMap<String, Boolean> presenceMap = validatePoLinePresenceinJdaTable(po);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err.println("Pre Advice Line Data Not inserted till now - Slow Insertion - Failing : " + po);
					Assert.assertFalse("Pre Advice Line Data Not inserted till now - Slow Insertion - Failing : " + po,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted PO : " + po);
			}
		}
	}

	private void validateUpiDataSetup(String upi) throws InterruptedException, ClassNotFoundException, SQLException {
		boolean mainTable = true;
		boolean finalUpiStatus = false, finalUpiLineStatus = false;

		if (!(finalUpiStatus)) {
			int count = 0;
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted Pallet_Id in  UPI Header : " + upi);
				HashMap<String, Boolean> presenceMap = validateUpiPresenceinJdaTable(upi);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err.println("UPI Header Data Not inserted till now - Slow Insertion - Failing : " + upi);
					Assert.assertFalse("UPI Header Data Not inserted till now - Slow Insertion - Failing : " + upi,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted Pallet_Id : " + upi);
			}
		}
		if (!(finalUpiLineStatus)) {
			int count = 0;
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted Pallet_Id in UPI Line : " + upi);
				HashMap<String, Boolean> presenceMap = validateUpiLinePresenceinJdaTable(upi);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err.println("UPI Line Data Not inserted till now - Slow Insertion - Failing : " + upi);
					Assert.assertFalse("UPI Line Data Not inserted till now - Slow Insertion - Failing : " + upi,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted Pallet_Id : " + upi);
			}
		}
	}

	private void validateOdnDataSetup(String odn) throws InterruptedException, ClassNotFoundException, SQLException {
		boolean mainTable = true;
		boolean finalStoStatus = false, finalStoLineStatus = false;
		if (!(finalStoStatus)) {
			int count = 0;
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted ODN in  Order Header : " + odn);
				HashMap<String, Boolean> presenceMap = validateStoPresenceinJdaTable(odn);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err.println("Order Header Data Not inserted till now - Slow Insertion - Failing : " + odn);
					Assert.assertFalse("Order Header Data Not inserted till now - Slow Insertion - Failing : " + odn,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted Pallet_Id : " + odn);
			}
		}
		if (!(finalStoLineStatus)) {
			int count = 0;
			do {
				count++;
				Thread.sleep(3000);
				System.out.println("Validating Inserted ODN in Order Line : " + odn);
				HashMap<String, Boolean> presenceMap = validateStoLinePresenceinJdaTable(odn);
				mainTable = presenceMap.get("mainTable");
				if (count > 30) {
					System.err.println("Order Line Data Not inserted till now - Slow Insertion - Failing : " + odn);
					Assert.assertFalse("Order Line Data Not inserted till now - Slow Insertion - Failing : " + odn,
							count == 31);
					// break;
				}
			} while (!(mainTable));
			if (count < 30) {
				System.err.println("Found Inserted Pallet_Id : " + odn);
			}
		}
	}

	public HashMap<String, Boolean> validateUpiPresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select count(*) from upi_receipt_header where pallet_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt
				.executeQuery("select count(*) from interface_upi_receipt_header where pallet_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	public HashMap<String, Boolean> validateUpiLinePresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from upi_receipt_line where pallet_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt.executeQuery("select count(*) from interface_upi_receipt_line where pallet_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	public HashMap<String, Boolean> validatePoLinePresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select count(*) from pre_advice_line where pre_advice_id='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt
				.executeQuery("select count(*) from interface_pre_advice_line where pre_advice_id='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	public HashMap<String, Boolean> validateStoLinePresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from order_line where order_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			mainTable = true;
		} else {
			mainTable = false;
		}
		rs = null;
		rs = stmt.executeQuery("select count(*) from interface_order_line where order_id ='" + tempValue + "'");
		rs.next();
		if (Integer.valueOf(rs.getString(1)) > 0) {
			interfaceTable = true;
		} else {
			interfaceTable = false;
		}
		presenceMap.put("mainTable", mainTable);
		presenceMap.put("interfaceTable", interfaceTable);
		return presenceMap;
	}

	private String getSupplierIDFromJDADB(String upiReference) throws SQLException, ClassNotFoundException {
		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}
		String supplierId = null;
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt
				.executeQuery("select SUPPLIER_ID from upi_receipt_header where pallet_id='" + upiReference + "'");
		while (rs.next()) {
			supplierId = rs.getString("SUPPLIER_ID");
		}
		return supplierId;
	}

	private String formReturnsUPIID(String supplierIdRef, String qty)
			throws ClassNotFoundException, SQLException, InterruptedException {

		// manipulate supplier
		String[] supplierSplit = supplierIdRef.split("M");
		String supplier = supplierSplit[1];

		// manipulate quantity
		int sumLength = qty.length();
		if (sumLength == 1) {
			qty = "00" + qty;
		} else if (sumLength == 2) {
			qty = "0" + qty;
		}
		qty = qty;
		String upi = context.getSiteId() + "000" + Utilities.getSixDigitRandomNumber() + supplier + "100"
				+ Utilities.getSixDigitRandomNumber() + qty + "00";
		return upi;
	}
}