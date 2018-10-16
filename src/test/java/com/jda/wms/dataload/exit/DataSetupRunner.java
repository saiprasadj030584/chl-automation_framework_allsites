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

	public void insertPreAdviceData() throws ClassNotFoundException, SQLException, InterruptedException {
		String poId = newPoId();
		String Preadvice= Advice();
		insertDataIntoDB.insertPreAdviceHeader(poId,Preadvice);
		insertDataIntoDB.insertPreAdviceline(poId);
		
		Thread.sleep(7000);
		System.out.println("pre advice Id = " + poId);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(poId));
		getTCData.setpoId(poId);
	}
	public void insertUPIReceiptData() throws ClassNotFoundException, SQLException, InterruptedException {
		String palletId = palletId();
		insertDataIntoDB.insertUPIReceiptHeader(palletId);
		insertDataIntoDB.insertUPIReceiptline(palletId);
		
		Thread.sleep(3000);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isUpiRecordExists(palletId));
		getTCData.setPo(palletId);
	}
	
	

	public void insertOrderData() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
//		String stoId = "8800004368";
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLine(stoId);
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		//Bhuban
		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
			
			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
			Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	public void insertOrderData2() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
//		getTCData.getpoId();
//		context.getpoId();
//		String poId = newPoId();
//		String stoId = "8800004368";
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLine2(stoId,getTCData.getpoId());
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		//Bhuban
//		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
//		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
//			
//			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
			Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	
//	public void insert2OrderData() throws ClassNotFoundException, SQLException, InterruptedException {
//		String stoId = newStoId();
////		String stoId = "8800004368";
//		System.out.println(stoId);
//		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
//		insertDataIntoDB.insert2OrderLine(stoId);
//		Thread.sleep(10000);
//		selectDataFromDB.isOrderHeaderRecordExists(stoId);
//		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
//			
//		//Bhuban
//		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
//		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
//			
//			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
//			Thread.sleep(3000);	
//		
//		
//		
//		Assert.assertTrue("Test Data not available - Issue in Data loading",
//				selectDataFromDB.isOrderRecordExists(stoId));
//		getTCData.setSto(stoId);
//		System.out.println("Order Id from Interface table is:"+ stoId );
//	}
	public void insertOrderDataForContainer() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
//		String stoId = "8800004368";
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLineForContainer(stoId);
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		//Bhuban
		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
			
			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
			Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	
	public void insertOrderDataForConsolidation() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
//		String stoId = "8800004368";
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLineForConsolidation(stoId);
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		//Bhuban
		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
			
			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
			Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	
	public void insertOrderDataForReplenish() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
//		String stoId = "8800004368";
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLineForReplenish(stoId);
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		//Bhuban
		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
			
			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
			Thread.sleep(3000);	
		
		
		
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isOrderRecordExists(stoId));
		getTCData.setSto(stoId);
		System.out.println("Order Id from Interface table is:"+ stoId );
	}
	public void insertOrderDataForLockingLocations() throws ClassNotFoundException, SQLException, InterruptedException {
		String stoId = newStoId();
//		String stoId = "8800004368";
		System.out.println(stoId);
		insertDataIntoDB.insertOrderHeader(stoId,context.getStoType(),context.getCustomer());
		insertDataIntoDB.insertOrderLineForLockingLocations(stoId);
		Thread.sleep(10000);
		selectDataFromDB.isOrderHeaderRecordExists(stoId);
		System.out.println(selectDataFromDB.isOrderRecordExists(stoId));
			
		//Bhuban
		updateDataFromDB.updateMoveTaskStatusInMoveTask(stoId);
		 //updateDataFromDB.updateMoveTaskStatusInOrderHeader(stoId);
			
			updateDataFromDB.updateAddressIntPalletType(context.getCustomer());
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
	
	
}
