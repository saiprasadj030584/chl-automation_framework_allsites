package com.jda.wms.datasetup.gm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.Database;
import com.jda.wms.utils.DateUtils;

import java.util.concurrent.ThreadLocalRandom;

public class DataSetupRunner {
	private Context context;
	public static DbConnection npsDataBase;
	private GetTcData gettcdata;
	private Database jdaJdatabase;

	@Inject
	public DataSetupRunner(Context context, DbConnection dataBase, Database jdaJdatabase, GetTcData gettcdata) {
		this.context = context;
		this.npsDataBase = dataBase;
		this.gettcdata = gettcdata;
		this.jdaJdatabase = jdaJdatabase;
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
		context.setUniqueTag(uniqueTag);
		Assert.assertTrue("UniqueTag Not Found in Test Data Table", validateUniqueTagInTestData());
		context.setUniqueTagInRunStatus(validateUniqueTagInRunStatus());
		// Assert.assertTrue("UniqueTag Not Found in Run Status Table",
		// context.setUniqueTagInRunStatus());
		insertData();
	}

	private boolean validateUniqueTagInTestData() {
		ResultSet resultSet = null;
		boolean UniqueTagInTestData = false;
		try {
			npsDataBase.connectAutomationDB();

			resultSet = npsDataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");
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
			String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
			resultSet = npsDataBase.dbConnection.createStatement()
					.executeQuery("Select * from dbo.JDA_GM_RUN_STATUS where PARENT_REQUEST_ID='"
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

		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();

		if (context.getConnection() == null) {
			jdaJdatabase.connect();
		}

		Statement stmt = context.getConnection().createStatement();

		if (context.getUniqueTag().contains("direct")) {

			try {
				npsDataBase.connectAutomationDB();

				String asn = newAsnId();
				String po = newPoId();
				String upi = newPalletdId();
				String sku = gettcdata.getSkuListFromTestData();

				// ResultSet rget = stmt
				// .executeQuery("Select SUPPLIER_ID from supplier_sku where
				// sku_id='" + sku + "' and ROWNUM = 1");
				// rget.next();
				// String supplier_id = rget.getString(1);

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
						+ sku + "' and ROWNUM = 1),null,null,null,null, 20 ,'" + po
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
						+ po + "', 10 ,null,null, '" + sku
						+ "' ,null,null,null,null,null,null,null,null,null,null,40,null,null,null,'N', null, (select product_group from sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1 ) ,null,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1) ,null,null, (select user_def_type_8 from sku where sku_id='" + sku
						+ "' and ROWNUM = 1 ),null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,2017,22222,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'NDC','A','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";

				System.out.println(po_line_qry);

				rinsert = stmt.executeQuery(po_line_qry);
				context.getConnection().commit();

				gettcdata.setPalletId(upi);
				gettcdata.setSkuQtySupplier();

				validateDirectPoDataSetup(po, asn, upi);

				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (context.getUniqueTag().contains("fsv")) {

			try {
				npsDataBase.connectAutomationDB();
				String po = newPoId();
				String sku = gettcdata.getSkuListFromTestData();

				String po_header_qry = "Insert into INTERFACE_PRE_ADVICE_HEADER values ((Select max (Key)  from Interface_Pre_advice_header)+ 1,'M+S', '"
						+ po + "' ,'PO','" + context.getSiteId()
						+ "','M+S', (Select SUPPLIER_ID from supplier_sku where sku_id='" + sku
						+ "' and ROWNUM = 1),'Released',null,to_timestamp(Sysdate+10,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,'SEA',null, null, (select product_group from sku where sku_id='"
						+ gettcdata.getSkuList()
						+ "' and ROWNUM = 1 ) ,null,null,'FSV', (select user_def_type_8 from sku where sku_id='" + sku
						+ "' and ROWNUM = 1) ,null,null,null,'N','N','N',to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,null,22222,null,null,null,null,null,'N',null,null,null,null,null,'Europe/London','Europe/London',null,'NDC','U','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";

				System.out.println(po_header_qry);

				ResultSet rinsert = stmt.executeQuery(po_header_qry);
				gettcdata.setPo(po);
				context.getConnection().commit();

				String po_line_qry = "Insert into INTERFACE_PRE_ADVICE_LINE values ((Select max (Key) from Interface_Pre_advice_line) + 1,'M+S', '"
						+ po + "', 10 ,null,null, '" + sku
						+ "' ,null,null,null,null,null,null,null,null,null,null,40,null,null,null,'N', null, (select product_group from sku where sku_id='"
						+ sku
						+ "' and ROWNUM = 1 ) ,null,null, (Select SUPPLIER_SKU_ID from supplier_sku where sku_id='"
						+ sku + "' and ROWNUM = 1) ,null,null, (select user_def_type_8 from sku where sku_id='" + sku
						+ "' and ROWNUM = 1 ),null,null,null,null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'),null,null,null,null,null,2017,22222,null,null,(Select TRACK_LEVEL_1 from sku_config where CONFIG_ID in (Select CONFIG_ID from sku_sku_config where sku_id='"
						+ sku
						+ "' and ROWNUM = 1) and ROWNUM = 1),null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'NDC','A','Pending',null,to_timestamp(Sysdate,'DD-MON-RR HH24.MI.SSXFF'))";

				System.out.println(po_line_qry);

				rinsert = stmt.executeQuery(po_line_qry);
				context.getConnection().commit();

				gettcdata.setSkuQtySupplier();

				validateDirectFsvDataSetup(po);

				npsDataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

	}

	private void validateDirectFsvDataSetup(String po)
			throws InterruptedException, ClassNotFoundException, SQLException {

		boolean mainTable = true;

		boolean finalPoStatus = false, finalPoLineStatus = false;

		if (!(finalPoStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted PO in Pre Advice Header : " + po);
				HashMap<String, Boolean> presenceMap = validatePoPresenceinJdaTable(po);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted PO : " + po);
		}

		if (!(finalPoLineStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted PO in Pre Advice Line : " + po);
				HashMap<String, Boolean> presenceMap = validatePoLinePresenceinJdaTable(po);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted PO : " + po);
		}

	}

	public String newPoId() throws ClassNotFoundException, SQLException, InterruptedException {

		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true, RecordAlreadyfound = false;
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
		boolean mainTable = true, interfaceTable = true, RecordAlreadyfound = false;
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

	public String newStoId() throws ClassNotFoundException, SQLException, InterruptedException {

		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true, RecordAlreadyfound = false;
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
		boolean mainTable = true, interfaceTable = true, RecordAlreadyfound = false;
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

	public void validateDirectPoDataSetup(String po, String asn, String upi)
			throws ClassNotFoundException, SQLException, InterruptedException {

		boolean mainTable = true;

		boolean finalPoStatus = false, finalAsnStatus = false, finalUpiStatus = false, finalPoLineStatus = false,
				finalUpiLineStatus = false;

		if (!(finalPoStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted PO in Pre Advice Header : " + po);
				HashMap<String, Boolean> presenceMap = validatePoPresenceinJdaTable(po);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted PO : " + po);
		}

		if (!(finalPoLineStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted PO in Pre Advice Line : " + po);
				HashMap<String, Boolean> presenceMap = validatePoLinePresenceinJdaTable(po);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted PO : " + po);
		}

		if (!(finalAsnStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted ASN in Delivery : " + asn);
				HashMap<String, Boolean> presenceMap = validateAsnPresenceinJdaTable(asn);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted ASN : " + asn);
		}

		if (!(finalUpiStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted Pallet_Id in  UPI Header : " + upi);
				HashMap<String, Boolean> presenceMap = validateUpiPresenceinJdaTable(upi);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted Pallet_Id : " + upi);
		}

		if (!(finalUpiLineStatus)) {
			do {
				Thread.sleep(3000);
				System.out.println("Validating Inserted Pallet_Id in UPI Line : " + upi);
				HashMap<String, Boolean> presenceMap = validateUpiLinePresenceinJdaTable(upi);
				mainTable = presenceMap.get("mainTable");

			} while (!(mainTable));
			System.err.println("Found Inserted Pallet_Id : " + upi);
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

}
