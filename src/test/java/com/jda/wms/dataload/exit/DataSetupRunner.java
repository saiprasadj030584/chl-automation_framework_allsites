package com.jda.wms.dataload.exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.Database;

public class DataSetupRunner {
	private Context context;
	private Database database;
	private InsertDataIntoDB insertDataIntoDB;
	private SelectDataFromDB selectDataFromDB;
	private GetTCData getTCData;
	private UpdateDataFromDB updateDataFromDB;

	
	@Inject
	public DataSetupRunner(Context context,Database database,InsertDataIntoDB insertDataIntoDB,SelectDataFromDB selectDataFromDB,GetTCData getTCData,UpdateDataFromDB updateDataFromDB) {
		this.context = context;
		this.database = database;
		this.insertDataIntoDB = insertDataIntoDB;
		this.selectDataFromDB = selectDataFromDB;
		this.getTCData = getTCData;
		this.updateDataFromDB = updateDataFromDB;
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
			System.out.println("temp value"+tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}
	
	public String palletId() throws ClassNotFoundException, SQLException, InterruptedException {
		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		do {
			value = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(9);
			tempValue = String.valueOf(value) + String.valueOf(tempInt);
			HashMap<String, Boolean> presenceMap = validatePoPresenceinJdaTable(tempValue);
			System.out.println("temp value"+tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}
	public String Advice() throws ClassNotFoundException, SQLException, InterruptedException {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900000) + 100000);
	}
	
	
	public HashMap<String, Boolean> validatePoPresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			database.connect();
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

	public void insertPreAdviceDataforUPI() throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = newPoId();
		getTCData.setpoId(poId);
		String Preadvice= Advice();
		insertDataIntoDB.insertPreAdviceHeaderforUPI(poId,Preadvice);
		insertDataIntoDB.insertPreAdviceline(poId,Preadvice);
		Thread.sleep(7000);
		System.out.println("pre advice Id = " + poId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(poId));
		getTCData.setpoId(poId);
	}
	
	public void insertPreAdviceDataforUPIForRedStock() throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = newPoId();
		getTCData.setpoId(poId);
		String Preadvice= Advice();
		insertDataIntoDB.insertPreAdviceHeaderforUPI(poId,Preadvice);
		insertDataIntoDB.insertPreAdvicelineForRedStock(poId,Preadvice);
		Thread.sleep(7000);
		System.out.println("pre advice Id = " + poId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(poId));
		getTCData.setpoId(poId);
	}
	public void insertPreAdviceData() throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = newPoId();
		getTCData.setpoId(poId);
		String Preadvice= Advice();
		insertDataIntoDB.insertPreAdviceHeader(poId,Preadvice);
		insertDataIntoDB.insertPreAdviceline(poId,Preadvice);
		Thread.sleep(7000);
		System.out.println("pre advice Id = " + poId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(poId));
		getTCData.setpoId(poId);
	}
	public void insertPreAdviceData1(String POtype) throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = newPoId();
		getTCData.setpoId(poId);
		String Preadvice= Advice();
		insertDataIntoDB.insertPreAdviceHeader1(poId,Preadvice);
		insertDataIntoDB.insertPreAdviceline1(poId,Preadvice);
		Thread.sleep(7000);
		System.out.println("pre advice Id = " + poId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(poId));
		getTCData.setpoId(poId);
	}
	public void insertUPIReceiptData() throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = GetTCData.getpoId();
		String Preadvice= Advice();
		String palletIDforUPI = context.getpalletIDforUPI();
		Thread.sleep(1000);
		insertDataIntoDB.insertUPIReceiptHeader(poId,Preadvice);
		insertDataIntoDB.insertUPIReceiptLine(poId);
		Thread.sleep(3000);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isUpiRecordExists(palletIDforUPI));
		getTCData.setPo(palletIDforUPI);
	}
	public void insertUPIReceiptDataForRedStock() throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = GetTCData.getpoId();
		String Preadvice= Advice();
		String palletIDforUPI = context.getpalletIDforUPI();
		Thread.sleep(1000);
		insertDataIntoDB.insertUPIReceiptHeader(poId,Preadvice);
		insertDataIntoDB.insertUPIReceiptLineForRedStock(poId);
		Thread.sleep(3000);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isUpiRecordExists(palletIDforUPI));
		getTCData.setPo(palletIDforUPI);
	}
	
	

	public void insertOrderData() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLine3(stoId);
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
		updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
			Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	public void insertOrderDataforUPI() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
		System.out.println(stoId);
		insertDataIntoDB.insertOrderforUPI(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertorderlineforUPI(stoId,getTCData.getpoId());
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
		Thread.sleep(3000);	
		Assert.assertTrue("Test Data not available - Issue in Data loading",
		selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	
	public void insertOrderDataforUPIForRedStock() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
		System.out.println(stoId);
		insertDataIntoDB.insertOrderforUPI(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertorderlineforUPIForRedStock(stoId,getTCData.getpoId());
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
		Thread.sleep(3000);	
		Assert.assertTrue("Test Data not available - Issue in Data loading",
		selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	public void insertDelivery() throws ClassNotFoundException, SQLException, InterruptedException {
		String ASN=context.getASN();
		insertDataIntoDB.insertdeliverydata();
		Thread.sleep(10000);
		selectDataFromDB.isASNRecordExists(ASN);
		System.out.println(selectDataFromDB.isASNRecordExists(ASN));
		Thread.sleep(3000);	
		Assert.assertTrue("Test Data not available - Issue in Data loading",
		selectDataFromDB.isASNRecordExists(ASN));
		System.out.println("ASN ID from Delivery table is :"+ ASN );
	}
	public void insertOrderData2() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLine2(stoId,getTCData.getpoId());
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
		Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	
	public String newStoId() throws ClassNotFoundException, SQLException, InterruptedException {

		long value, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
		if (context.getConnection() == null) {
			database.connect();
		}
		do {
			value = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(9);
			tempValue = String.valueOf(value) + String.valueOf(tempInt);
			//tempValue="AUTO123456";
			System.out.println("tempvalue"+tempValue);
			HashMap<String, Boolean> presenceMap = validateStoPresenceinJdaTable(tempValue);
			mainTable = presenceMap.get("mainTable");
			interfaceTable = presenceMap.get("interfaceTable");
		} while (mainTable || interfaceTable);
		return tempValue;
	}
	
	public HashMap<String, Boolean> validateStoPresenceinJdaTable(String tempValue)
			throws ClassNotFoundException, SQLException, InterruptedException {
		if (context.getConnection() == null) {
			database.connect();
		}
		HashMap<String, Boolean> presenceMap = new HashMap<String, Boolean>();
		boolean mainTable = true, interfaceTable = true;
		Statement stmt = context.getConnection().createStatement();
		System.out.println("select count(*) from order_header where order_id ='" + tempValue + "'");
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
//	public void insertPreAdviceDataforUPITrial() throws ClassNotFoundException, SQLException, InterruptedException {
//		String poId = newPoId();
//		getTCData.setpoId(poId);
//		String Preadvice= Advice();
//		insertDataIntoDB.insertPreAdviceHeaderforUPI(poId,Preadvice);
//		insertDataIntoDB.insertPreAdvicelineTrial(poId,Preadvice);
//		Thread.sleep(7000);
//		System.out.println("pre advice Id = " + poId);
//		Assert.assertTrue("Test Data not available - Issue in Data loading",
//				selectDataFromDB.isPreAdviceRecordExists(poId));
//		getTCData.setpoId(poId);
//	}
//	
	
}
