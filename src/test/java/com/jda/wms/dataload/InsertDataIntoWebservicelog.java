package com.jda.wms.dataload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.config.Constants;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;
import com.jda.wms.db.MoveTaskDB;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

public class InsertDataIntoWebservicelog {
	private Context context;
	private Database database;
	private MoveTaskDB moveTaskDB;
	private Constants constants;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public InsertDataIntoWebservicelog(Context context, Database database, Constants constants,MoveTaskDB moveTaskDB) {
		this.context = context;
		this.database = database;
		this.constants = constants;
		this.moveTaskDB = moveTaskDB;
	}

	public void UpdateSkuData() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275663','10','1','1','1','1','1','1')";
		// String updatequery2 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('5649','7401','0054','000000000022275664','20','1','1','1','1','1','1')";
		// String updatequery3 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('5649','7401','0054','000000000022163522','30','1','1','2','1','1','1')";
		// String updatequery4 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('5649','7401','0054','000000000022201450','40','1','1','2','1','1','1')";
		//

		System.out.println(deletequery);
		System.out.println(updatequery1);
		// System.out.println(updatequery2);
		// System.out.println(updatequery3);
		// System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		// stmt.executeQuery(updatequery2);
		// stmt.executeQuery(updatequery3);
		// stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void triggercrossdock() throws InterruptedException, IOException {

		@SuppressWarnings("static-access")

		// Process p = Runtime.getRuntime().exec("cmd /c
		// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
		// Process p = Runtime.getRuntime().exec("cmd /c " +
		// constants.DATASETUP_CROSSDOCK + "");
		Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/Crossdock_Hemel.lnk");

		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");
	

	}

	public void triggercrossdockForward() throws InterruptedException, IOException {

		@SuppressWarnings("static-access")

		// Process p = Runtime.getRuntime().exec("cmd /c
		// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
		// Process p = Runtime.getRuntime().exec("cmd /c " +
		// constants.DATASETUP_CROSSDOCK + "");
		Process p = Runtime.getRuntime()
				.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Crossdock_Forward_SC.lnk");

		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public void ReadFile() throws IOException {
		// String path = Constants.OUT_CROSSDOCK ;
		String path = "C:/Automation_supporting_files/LnkFiles/Crossdocklog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {			
			line = line.trim();
			
			// System.out.print(line + "\n");
			StringTokenizer st = new StringTokenizer(line);
			if (line.startsWith("ORDER_ID    :")) {
					st.nextToken();
					String OrderID = st.nextToken().replaceAll(":", "");
					System.out.println("ORDERID is " + OrderID);
					context.setOrderId(OrderID);
					System.out.println("The orderID after setting " + context.getOrderId());					
				}
			else if (line.startsWith("UPC        :")) {
				st.nextToken();
				String UPC = st.nextToken().replaceAll(":", "");
				System.out.println("UPC is " + UPC);
				context.setupc(UPC);
		    } else if (line.startsWith("MASTER_URN :")) {
			    st.nextToken();
				String MASTER_URN = st.nextToken().replaceAll(":", "");
				System.out.println("URN is " + MASTER_URN);
				context.setMasterURN(MASTER_URN);				
			}  else if (line.startsWith("ASN_ID     :")) {
				st.nextToken();
				String ASN_ID = st.nextToken().replaceAll(":", "");
				System.out.println("ASN is " + ASN_ID);
				context.setASN(ASN_ID);
			} else if (line.startsWith("QUANTITY    :")) {
			    st.nextToken();
				String QTY_RECV = st.nextToken().replaceAll(":", "");
				System.out.println("QUANTITY    is " + QTY_RECV);
				context.setQtyOredered(QTY_RECV);
			} else if (line.startsWith("SKU_ID     :")) {
				st.nextToken();
				String SKU_ID = st.nextToken().replaceAll(":", "");
				System.out.println("SKU is " + SKU_ID);
				context.setSkuId(SKU_ID);
			} else if (line.startsWith("PALLET_ID  :")) {
				st.nextToken();
				String PALLET_ID = st.nextToken().replaceAll(":", "");
				System.out.println("Pallet is " + PALLET_ID);
				context.setPalletID(PALLET_ID);
				line = br.readLine();
			}
			
		}

		br.close();
		
	
	}

	 public void Read_Multi_Crossdock_Receiving() throws SQLException, ClassNotFoundException, InterruptedException, FindFailed, IOException {
	 String path = "C:/Automation_supporting_files/LnkFiles/Crossdocklog.txt";
	  BufferedReader br = new BufferedReader(new FileReader(path));
	  ArrayList<String> ORDER_ID = new ArrayList<String>();
	  ArrayList<String> SKU_ID = new ArrayList<String>();
	  ArrayList<String> UPC =  new ArrayList<String>();
	  ArrayList<String> MASTER_URN = new ArrayList<String>();
	  ArrayList<String> PALLET_ID = new ArrayList<String>();
	  ArrayList<String> ASN_ID = new ArrayList<String>();
	  ArrayList<String> QTY = new ArrayList<String>();
	  
	  try {
			for (String line; (line = br.readLine()) != null;) {
				StringTokenizer st = new StringTokenizer(line);
			    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
	          
			    for(int i=0;i<myList.size();i++){
			    	if(myList.get(i).contains("SKU_ID")){
			    		st.nextToken();
			    		String SKUID =st.nextToken().replaceAll(":", "");;
			    		SKU_ID.add(SKUID);
			    	}
			    	else if(myList.get(i).contains("ORDER_ID")){
			    		st.nextToken();
			    		String Order =st.nextToken().replaceAll(":", "");;
			    		ORDER_ID.add(Order);
			    	}
			    	
			    	else if(myList.get(i).contains("UPC")){
			    		st.nextToken();
			    		String Upc =st.nextToken().replaceAll(":", "");;
			    		UPC.add(Upc);
			    		
			    	}
			    	else if(myList.get(i).contains("QTY_ORDERED")){
			    		st.nextToken();
			    		String Quantity =st.nextToken().replaceAll(":", "");;
			    		QTY.add(Quantity);
			    		
			    	}
			    	else if(myList.get(i).contains("MASTER_URN :")){
			    		 st.nextToken();
			    		 String Master_urn =st.nextToken().replaceAll(":", "");;
			    		 MASTER_URN.add(Master_urn);
			    		 }
			    		 else if(myList.get(i).contains("PALLET_ID  :")){
			    		 st.nextToken();
			    		 String Pallet_id =st.nextToken().replaceAll(":", "");;
			    		 PALLET_ID.add(Pallet_id);
			    		 }
			    		
			    		 else if(myList.get(i).contains("ASN_ID :")){
			    		 st.nextToken();
			    		 String Asn_id =st.nextToken().replaceAll(":", "");;
			    		 ASN_ID.add(Asn_id);
			    }
			    
			    }
			}
			
			ArrayList<String> UPClist = new ArrayList<String>(UPC);
			ArrayList<String> ORDERlist = new ArrayList<String>(ORDER_ID);
			ArrayList<String> SKUIDlist = new ArrayList<String>(SKU_ID);
			ArrayList<String> master_urn_list = new ArrayList<String>(MASTER_URN);
			ArrayList<String> pallet_id_list = new ArrayList<String>(PALLET_ID);
			ArrayList<String> QTYlist = new ArrayList<String>(QTY);
			 
			    context.setOrderIDList(ORDERlist);
			    context.setSkuIDList(SKUIDlist);
			    context.setUPCList(UPClist);
			    context.setQtyList(QTYlist);
			    context.setMasterurnList(master_urn_list);
			    context.setPalletList(pallet_id_list);
			    		
			    
			         /*   for(int j=0;j<SKUIDlist.size();j++){
					    	System.out.println("The sku value for "  +SKUIDlist.get(j));
					    }
					 
					    for(int k=0;k<UPClist.size();k++){
					    	System.out.println("UPC for "  +UPClist.get(k));
					    
					    }*/
					    for(int i=0;i<MASTER_URN.size();i++){
					   	 Thread.sleep(3000);
					   	 System.out.println("The MASTER_URN :"+context.getMasterurnList().get(i));
					   	 }
					   	 for(int l=0;l<PALLET_ID.size();l++){
					   	 Thread.sleep(3000);
					   	 System.out.println("The PALLET_ID :" +context.getPalletList().get(l));
					   	 }
					   	 for(int i=0;i<ASN_ID.size();i++){
					   	 Thread.sleep(3000);
					   	 System.out.println("The ASN_ID :" +context.getASNList().get(i));
					   	 }
				
	
	
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 
	}
	// public void getMultiOrder_Crossdock() throws SQLException,
	// ClassNotFoundException, InterruptedException, FindFailed, IOException {
	// ArrayList<String> OrderID = new ArrayList<String>();
	// if (context.getConnection() == null) {
	// database.connect();
	// }
	// Statement stmt = context.getConnection().createStatement();
	// ResultSet rs = stmt.executeQuery("select unique(order_line.order_id) from
	// upi_receipt_line,order_line where upi_receipt_line.user_def_type_5 = '" +
	// context.getASN()+"' and order_line.user_def_type_1 =
	// upi_receipt_line.user_def_type_1");

	public void getMultiOrder_Crossdock()
			throws SQLException, ClassNotFoundException, InterruptedException, FindFailed, IOException {

		if (context.getConnection() == null) {

			database.connect();

		}

		Thread.sleep(5000);

		Statement stmt = context.getConnection().createStatement();

		ResultSet rs = stmt.executeQuery(
				"select unique(order_line.order_id) OrderID from upi_receipt_line,order_line where upi_receipt_line.user_def_type_5 =  '"
						+ context.getASN()
						+ "' and order_line.user_def_type_1 = upi_receipt_line.user_def_type_1 order by orderid asc");

		int rowCount = 0;

		while (rs.next()) {

			rowCount++;

			if (rowCount == 2) {

				String orderid2 = rs.getString(1);

				// System.out.println("The 2ndOrderID is: "+orderid2);
				context.setOrderId_2(orderid2);
				System.out.println("The 2ndOrderID is: " + context.getOrderId_2());

			}

		}

	}

	// List<String> OrderID = new ArrayList<String>();
	// ArrayList<String> OrderID = (ArrayList<String>) new ArrayList<String>();
	// System.out.println("Quuueryyy" + rs));
	//
	// while (rs.next()) {
	// OrderID.add(rs.getString(1));
	//
	//
	//
	//
	// }
	// for(int i=0;i<OrderID.size();i++){
	//// OrderID.add(Order);
	// context.setOrderIDList(OrderID);
	// System.out.println("The MultiOrderID
	// is:"+context.getOrderIDList().get(i));
	// }

	// for(String l :OrderID ){
	// System.out.println("Order - " + l);
	// }

	public void UpdateSkuDataDestination() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0770','000000000022275663','10','1','1','1','1','1',null)";
		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery);

		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void ReadFile1() throws IOException {
		String path = "C:/Automation_supporting_files/LnkFiles/Crossdocklog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			// System.out.print(line + "\n");
			StringTokenizer st = new StringTokenizer(line);
			if (line.startsWith("ORDER_ID    :")) {
				st.nextToken();
				String OrderID = st.nextToken().replaceAll(":", "");
				System.out.println("ORDERID is " + OrderID);
				context.setOrderId1(OrderID);

			} else if (line.startsWith("MASTER_URN :")) {
				st.nextToken();
				String MASTER_URN = st.nextToken().replaceAll(":", "");
				System.out.println("URN is " + MASTER_URN);
				context.setMasterURN1(MASTER_URN);
			} else if (line.startsWith("PALLET_ID  :")) {
				st.nextToken();
				String PALLET_ID = st.nextToken().replaceAll(":", "");
				System.out.println("Pallet is " + PALLET_ID);
				context.setPalletID1(PALLET_ID);
			} else if (line.startsWith("ASN_ID     :")) {
				st.nextToken();
				String ASN_ID = st.nextToken().replaceAll(":", "");
				System.out.println("ASN is " + ASN_ID);
				context.setASN1(ASN_ID);

			} else if (line.startsWith("SKU_ID     :")) {
				st.nextToken();
				String SKU_ID = st.nextToken().replaceAll(":", "");
				System.out.println("SKU is " + SKU_ID);
				context.setSkuId1(SKU_ID);
			} else if (line.startsWith("UPC        :")) {
				st.nextToken();
				String UPC = st.nextToken().replaceAll(":", "");
				System.out.println("UPC is " + UPC);
				context.setUpc1(UPC);
				line = br.readLine();
			}
		}

		br.close();
	}

	public void deleteFile() throws IOException {
		String path = "C:/Automation_supporting_files/LnkFiles/Crossdocklog.txt";
		try {
			File file = new File(path);

			if (file.delete()) {
				System.out.println(file.getName() + " Was deleted!");
			} else {
				System.out.println("Delete Operation Failed. Check: " + file);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void UpdateSkuDataReverseB() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000022275664','10','1','1','1','1','1',null)";
		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery);

		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdateSkuDataReverseH() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022225060002','10','1','1','1','1','1',null)";
		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery);

		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void triggercrossdockReverse() throws InterruptedException, IOException {

		@SuppressWarnings("static-access")

		// Process p = Runtime.getRuntime().exec("cmd /c
		// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
		// Process p = Runtime.getRuntime().exec("cmd /c " +
		// constants.DATASETUP_CROSSDOCK + "");
		Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/Crossdock_RE.lnk");

		Thread.sleep(3000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public void ReadFileReverse() throws IOException {
		// String path = Constants.OUT_CROSSDOCK ;
		String path = "C:/Automation_supporting_files/LnkFiles/CrossdockReverselog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			// System.out.print(line + "\n");
			StringTokenizer st = new StringTokenizer(line);
			if (line.startsWith("ORDER_ID    :")) {
				st.nextToken();
				String OrderID = st.nextToken().replaceAll(":", "");
				System.out.println("ORDERID is " + OrderID);
				context.setOrderId(OrderID);

			} else if (line.startsWith("MASTER_URN :")) {
				st.nextToken();
				String MASTER_URN = st.nextToken().replaceAll(":", "");
				System.out.println("URN is " + MASTER_URN);
				context.setMasterURN(MASTER_URN);
			} else if (line.startsWith("PALLET_ID  :")) {
				st.nextToken();
				String PALLET_ID = st.nextToken().replaceAll(":", "");
				System.out.println("Pallet is " + PALLET_ID);
				context.setPalletID(PALLET_ID);
			} else if (line.startsWith("ASN_ID     :")) {
				st.nextToken();
				String ASN_ID = st.nextToken().replaceAll(":", "");
				System.out.println("ASN is " + ASN_ID);
				context.setASN(ASN_ID);

			} else if (line.startsWith("SKU_ID     :")) {
				st.nextToken();
				String SKU_ID = st.nextToken().replaceAll(":", "");
				System.out.println("SKU is " + SKU_ID);
				context.setSkuId(SKU_ID);
			} else if (line.startsWith("UPC        :")) {
				st.nextToken();
				String UPC = st.nextToken().replaceAll(":", "");
				System.out.println("UPC is " + UPC);
				context.setupc(UPC);
				line = br.readLine();
			}
		}

		br.close();
	}

	public void deleteSkuData() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		System.out.println("SKU data deleted");

	}

	public void UpdateMultiSkuDataForward() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275663','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275664','20','1','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022163522','30','1','1','2','1','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022201450','40','1','1','2','1','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdateMultiSkuDataForward_partial() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275663','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275664','20','1','1','2','1','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);

		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdateMultiSkuDataForward_Overspill() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275663','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('5649','7401','0054','000000000022275664','20','1','2','2','2','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);

		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdatemultiSkuDataH() throws ClassNotFoundException, SQLException {
		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000060040345','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000060040346','20','1','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000060040347','30','1','1','2','1','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000060040348','40','1','1','2','1','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdatemultiSkuDataB() throws ClassNotFoundException, SQLException {
		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022296012001','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022296012002','20','1','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022296012003','30','1','1','2','1','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022296012004','40','1','1','2','1','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdatemultiSkuDataHB() throws ClassNotFoundException, SQLException {
		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000060040345','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000000060040346','20','1','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022296012001','30','1','1','2','1','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('0054','7401','5649','000000022296012002','40','1','1','2','1','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdatemultiSkuDataIDT() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('4624','7401','7401','000000000022275663','10','50','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('4624','7401','7401','000000000022275664','20','50','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('4624','7401','7401','000000000022163522','10','50','1','2','1','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('4624','7401','7401','000000000022201450','20','50','1','2','1','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void triggerIDT() throws InterruptedException, IOException {

		@SuppressWarnings("static-access")

		// Process p = Runtime.getRuntime().exec("cmd /c
		// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
		// Process p = Runtime.getRuntime().exec("cmd /c " +
		// constants.DATASETUP_CROSSDOCK + "");
		Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/IDT_Welham_SC.lnk");

		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public void ReadFileIDT() throws IOException {
		// String path = Constants.OUT_CROSSDOCK ;
		String path = "C:/Automation_supporting_files/LnkFiles/IDTlog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			// System.out.print(line + "\n");
			StringTokenizer st = new StringTokenizer(line);
			if (line.startsWith("ORDER_ID    :")) {
				st.nextToken();
				String OrderID = st.nextToken().replaceAll(":", "");
				System.out.println("ORDERID is " + OrderID);
				context.setOrderId(OrderID);

			} else if (line.startsWith("MASTER_URN :")) {
				st.nextToken();
				String MASTER_URN = st.nextToken().replaceAll(":", "");
				System.out.println("URN is " + MASTER_URN);
				context.setMasterURN(MASTER_URN);
			} else if (line.startsWith("PALLET_ID  :")) {
				st.nextToken();
				String PALLET_ID = st.nextToken().replaceAll(":", "");
				System.out.println("Pallet is " + PALLET_ID);
				context.setPalletID(PALLET_ID);
			} else if (line.startsWith("ASN_ID     :")) {
				st.nextToken();
				String ASN_ID = st.nextToken().replaceAll(":", "");
				System.out.println("ASN is " + ASN_ID);
				context.setASN(ASN_ID);

			} else if (line.startsWith("SKU_ID     :")) {
				st.nextToken();
				String SKU_ID = st.nextToken().replaceAll(":", "");
				System.out.println("SKU is " + SKU_ID);
				context.setSkuId(SKU_ID);
			} else if (line.startsWith("UPC        :")) {
				st.nextToken();
				String UPC = st.nextToken().replaceAll(":", "");
				System.out.println("UPC is " + UPC);
				context.setupc(UPC);
				line = br.readLine();
			}
		}

		br.close();
	}

	public void UpdatemultiSkuDataDirect() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201006','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201007','20','1','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201008','10','1','1','2','2','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201009','20','1','1','2','2','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdatemultiSkuDataDirect_UnexpectedSKU() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000022461054001','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000022461054002','20','1','1','1','1','1',null)";
		// String updatequery3 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('M02491','7401','7401','000000020115201008','10','1','1','2','2','1',null)";
		// String updatequery4 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('M02491','7401','7401','000000020115201009','20','1','1','2','2','1',null)";
		//

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		// System.out.println(updatequery3);
		// System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		// stmt.executeQuery(updatequery3);
		// stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void triggerDirect() throws InterruptedException, IOException {
		File file = new File("C:/Automation_supporting_files/LnkFiles/Directlog.txt");

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

		@SuppressWarnings("static-access")

		// Process p = Runtime.getRuntime().exec("cmd /c
		// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
		// Process p = Runtime.getRuntime().exec("cmd /c " +
		// constants.DATASETUP_CROSSDOCK + "");
		Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/Direct_SC.lnk");

		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public void triggerRMS() throws InterruptedException, IOException {
		File file = new File("C:/Automation_supporting_files/LnkFiles/RMSlog.txt");

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

		@SuppressWarnings("static-access")

		Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/RMS_SC.lnk");

		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public void ReadFileFSV() throws IOException {
		// String path = Constants.OUT_CROSSDOCK ;
		String path = "C:/Automation_supporting_files/LnkFiles/FSVlog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			// System.out.print(line + "\n");
			StringTokenizer st = new StringTokenizer(line);
			if (line.startsWith("PRE_ADV_ID ")) {
				st.nextToken();
				String PreAdvID = st.nextToken();
				System.out.println("PRE_ADV_ID is " + PreAdvID);
				context.setPreAdviceId(PreAdvID);

			} else if (line.startsWith("URN")) {
				st.nextToken();
				String MASTER_URN = st.nextToken();
				System.out.println("URN is " + MASTER_URN);
				context.setMasterURN(MASTER_URN);
			} else if (line.startsWith("BEL")) {
				st.nextToken();
				String BEL_ID = st.nextToken();
				System.out.println("Bel is " + BEL_ID);
				context.setBelCode(BEL_ID);
			}

			else if (line.startsWith("SKU_ID")) {
				st.nextToken();
				String SKU_ID = st.nextToken();
				System.out.println("SKU is " + SKU_ID);
				context.setSkuId(SKU_ID);
			} else if (line.startsWith("UPC")) {
				st.nextToken();
				String UPC = st.nextToken();
				System.out.println("UPC is " + UPC);
				context.setupc(UPC);
				line = br.readLine();
			}

		}

		br.close();
	}

	public void UpdateSkuFullPalletDirect() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201005','10','10','1','1','1','1',null)";
		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void UpdatemultiSkuDataFSV() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201001','10','1','1','1','1','1',null)";
		String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201002','20','1','1','1','1','1',null)";
		String updatequery3 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201003','10','1','1','2','2','1',null)";
		String updatequery4 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201004','20','1','1','2','2','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		System.out.println(updatequery2);
		System.out.println(updatequery3);
		System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		stmt.executeQuery(updatequery2);
		stmt.executeQuery(updatequery3);
		stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void overreceipt_UpdateSkuDataFSV() throws ClassNotFoundException, SQLException {

		String deletequery = "delete from sku_data";
		String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201001','10','1000','1','1','1','1',null)";
		// String updatequery2 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('M02491','7401','7401','000000020115201002','20','1','1','1','1','1',null)";
		// String updatequery3 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('M02491','7401','7401','000000020115201003','10','1','1','2','2','1',null)";
		// String updatequery4 = "Insert into SKU_DATA
		// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
		// values
		// ('M02491','7401','7401','000000020115201004','20','1','1','2','2','1',null)";

		System.out.println("Insert Pre Advice Header");

		System.out.println(deletequery);
		System.out.println(updatequery1);
		// System.out.println(updatequery2);
		// System.out.println(updatequery3);
		// System.out.println(updatequery4);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		stmt.executeQuery(updatequery1);
		// stmt.executeQuery(updatequery2);
		// stmt.executeQuery(updatequery3);
		// stmt.executeQuery(updatequery4);
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void triggerFSV() throws InterruptedException, IOException {

		@SuppressWarnings("static-access")

		// Process p = Runtime.getRuntime().exec("cmd /c
		// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
		// Process p = Runtime.getRuntime().exec("cmd /c " +
		// constants.DATASETUP_CROSSDOCK + "");
		Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/FSV_SC.lnk");

		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public void ReadFileMultiFSV() throws FileNotFoundException {
		String path = "C:/Automation_supporting_files/LnkFiles/FSVlog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		ArrayList<String> PRE_ADV_ID = (ArrayList<String>) new ArrayList<String>();
		ArrayList<String> sku = (ArrayList<String>) new ArrayList<String>();
		ArrayList<String> URN = (ArrayList<String>) new ArrayList<String>();
		ArrayList<String> BEL = (ArrayList<String>) new ArrayList<String>();
		try {
			for (String line; (line = br.readLine()) != null;) {
				StringTokenizer st = new StringTokenizer(line);
				ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(
						Arrays.asList(line.trim().split(":")));

				// System.out.print(line + "\n");
				line = line.trim();
				// System.out.print(line + "\n");
				// String SKUID

				for (int i = 0; i < myList.size(); i++) {
					if (myList.get(i).contains("SKU_ID")) {
						st.nextToken();
						String SKUID = st.nextToken();
						// System.out.println("The sku value for " +SKUID);
						sku.add(SKUID);
					} else if (myList.get(i).contains("PRE_ADV_ID")) {
						st.nextToken();
						String PreAdv = st.nextToken();
						PRE_ADV_ID.add(PreAdv);
					} else if (myList.get(i).contains("URN")) {
						st.nextToken();
						String Urn = st.nextToken();
						// System.out.println("The URN value for " +Urn);
						URN.add(Urn);
					}

					else if (myList.get(i).contains("BEL")) {
						st.nextToken();
						String Bel = st.nextToken();
						// System.out.println("The BEL value for " +Bel);
						BEL.add(Bel);
					}
				}
				context.setPalletIDList(URN);
				context.setBelList(BEL);

				for (int j = 0; j < sku.size(); j++) {
					// context.setSkuId(sku.get(j));
					System.out.println("The sku value for " + sku.get(j));
					// System.out.println("The URN value for " +URN.get(j));
					// System.out.println("The BEL value for " +BEL.get(j));
				}
				// for(int k=0;k<URN.size();k++){
				// context.setMasterURN(URN.get(k));
				// System.out.println("The URN value for " +URN.get(k));
				// System.out.println("The URN value for " +URN.get(k));

				// System.out.println("The BEL value for " +BEL.get(j));
				// }
				for (int l = 0; l < BEL.size(); l++) {
					// context.setBelCode(BEL.get(l));
					// System.out.println("The URN value for " +URN.get(k));
					// System.out.println("The URN value for " +URN.get(j));
					System.out.println("The BEL value for " + BEL.get(l));

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getMultiURN()
			throws SQLException, ClassNotFoundException, InterruptedException, FindFailed, IOException {
		String path = "C:/Automation_supporting_files/LnkFiles/FSVlog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));

		for (String line; (line = br.readLine()) != null;) {
			// System.out.print(line + "\n");
			line = line.trim();
			// System.out.print(line + "\n");
			// String SKUID
			StringTokenizer st = new StringTokenizer(line);
			ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim().split(":")));
			ArrayList<String> URN = (ArrayList<String>) new ArrayList<String>();
			ArrayList<String> BEL = (ArrayList<String>) new ArrayList<String>();
			for (int i = 0; i < myList.size(); i++) {
				if (myList.get(i).contains("URN")) {
					st.nextToken();
					String Urn = st.nextToken();
					// context.setMasterURN(Urn);
					URN.add(Urn);
				}

				else if (myList.get(i).contains("BEL")) {
					st.nextToken();
					String Bel = st.nextToken();
					// context.setBelCode(Bel);
					BEL.add(Bel);
				}
				for (int j = 0; j < 4; j++) {
					// for(int k=0;k<BEL.size();k++){
					System.out.println("The URN is" + URN.get(j));
					System.out.println("The BEL is" + BEL.get(j));
				}
			}
		}
	}

	public void ReadFileDirect() throws IOException {
		// String path = Constants.OUT_CROSSDOCK ;
		String path = "C:/Automation_supporting_files/LnkFiles/Directlog.txt";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			// System.out.print(line + "\n");
			StringTokenizer st = new StringTokenizer(line);
			if (line.startsWith("ORDER_ID    :")) {
				st.nextToken();
				String OrderID = st.nextToken().replaceAll(":", "");
				System.out.println("ORDERID is " + OrderID);
				context.setOrderId(OrderID);

			} else if (line.startsWith("MASTER_URN :")) {
				st.nextToken();
				String MASTER_URN = st.nextToken().replaceAll(":", "");
				System.out.println("URN is " + MASTER_URN);
				context.setMasterURN(MASTER_URN);
			} else if (line.startsWith("PALLET_ID  :")) {
				st.nextToken();
				String PALLET_ID = st.nextToken().replaceAll(":", "");
				System.out.println("Pallet is " + PALLET_ID);
				context.setPalletID(PALLET_ID);
			} else if (line.startsWith("ASN_ID     :")) {
				st.nextToken();
				String ASN_ID = st.nextToken().replaceAll(":", "");
				System.out.println("ASN is " + ASN_ID);
				context.setASN(ASN_ID);

			} else if (line.startsWith("SKU_ID     :")) {
				st.nextToken();
				String SKU_ID = st.nextToken().replaceAll(":", "");
				System.out.println("SKU is " + SKU_ID);
				context.setSkuId(SKU_ID);
			} else if (line.startsWith("UPC        :")) {
				st.nextToken();
				String UPC = st.nextToken().replaceAll(":", "");
				System.out.println("UPC is " + UPC);
				context.setupc(UPC);
				line = br.readLine();
			}
			
		}

		br.close();
	}

	// read file data from csv
	public void UpdatemultiSkuDataDirectFile()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		String path = "C:/Automation_supporting_files/LnkFiles/exportDirect.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			for (String line; (line = br.readLine()) != null;) {
				// System.out.print(line + "\n");
				line = line.trim();
				System.out.print(line + "\n");
				// String SKUID
				StringTokenizer st = new StringTokenizer(line);
				ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(
						Arrays.asList(line.trim().split(",")));

				for (int i = 0; i <= myList.size(); i++) {
					System.out.println("the CSV file has:" + myList.get(i));
				}
			}
		}
	}

	

	
	

	
public void UpdateSkuDataDirectExpiry() throws ClassNotFoundException, SQLException

{
			
			String deletequery = "delete from sku_data";
			   String updatequery = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201006','10','1','1','1','1','1',null)";			
			   System.out.println("Insert Pre Advice Header");
			
			System.out.println(deletequery);
			System.out.println(updatequery);
			if (context.getConnection() == null) {
				database.connect();
			}
			Statement stmt = context.getConnection().createStatement();
			System.out.println("statement");
			stmt.executeQuery(deletequery);
			stmt.executeQuery(updatequery);
			
			context.getConnection().commit();
			System.out.println("SKU_DATA table is updated");
			
}
public void UpdateSkuConfigPalletDirect() throws ClassNotFoundException, SQLException
{
	
	  String deletequery = "delete from sku_data";
	   String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000000021071851','10','10','1','1','1','1',null)";
	   System.out.println("Insert Pre Advice Header");
	   
	   System.out.println(deletequery);
	   System.out.println(updatequery1);
	   if (context.getConnection() == null) {
	    database.connect();
	   }
	   Statement stmt = context.getConnection().createStatement();
	   System.out.println("statement");
	   stmt.executeQuery(deletequery);
	   stmt.executeQuery(updatequery1);
	   context.getConnection().commit();
	   System.out.println("SKU_DATA table is updated");
			}
public void UpdatSkuDataFSVExpiryDate() throws ClassNotFoundException, SQLException
{
	
	  String deletequery = "delete from sku_data";
	   String updatequery = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201001','10','1','1','1','1','1',null)";
//	   String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201082','10','1','1','1','1','1',null)";

	   
	   System.out.println("Insert Pre Advice Header");
	   
	   System.out.println(deletequery);
	   System.out.println(updatequery);
//	   System.out.println(updatequery1);
	   if (context.getConnection() == null) {
	    database.connect();
	   }
	   Statement stmt = context.getConnection().createStatement();
	   System.out.println("statement");
	   stmt.executeQuery(deletequery);
	   stmt.executeQuery(updatequery);
//	   stmt.executeQuery(updatequery1);
	   context.getConnection().commit();
	   System.out.println("SKU_DATA table is updated");
			}
public void UpdatemultiSkuDataFSV_Two_SKUs() throws ClassNotFoundException, SQLException
{
	
	  String deletequery = "delete from sku_data";
	   String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201001','10','1','1','1','1','1',null)";
	    String updatequery2 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('M02491','7401','7401','000000020115201002','20','1','1','1','1','1',null)";
	   
	   System.out.println("Insert Pre Advice Header");
	   
	   System.out.println(deletequery);
	   System.out.println(updatequery1);
	    System.out.println(updatequery2);

	   if (context.getConnection() == null) {
	    database.connect();
	   }
	   Statement stmt = context.getConnection().createStatement();
	   System.out.println("statement");
	   stmt.executeQuery(deletequery);
	   stmt.executeQuery(updatequery1);
	   stmt.executeQuery(updatequery2);

	   context.getConnection().commit();
	   System.out.println("SKU_DATA table is updated");
			}


@SuppressWarnings("unused")
public void ReadFileMultiXDock() throws FileNotFoundException {
	 String path = "C:/Automation_supporting_files/LnkFiles/CrossdockForwardlog.txt";
    BufferedReader br = new BufferedReader(new FileReader(path));
    ArrayList<String> MasterURN = new ArrayList<String>();
    ArrayList<String> Pallet_id = new ArrayList<String>();
    ArrayList<String> Asn_id =  new ArrayList<String>();
    ArrayList<String> Sku_id = new ArrayList<String>();
    ArrayList<String> order_id = new ArrayList<String>();
    try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
            
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID     ")){
		    		st.nextToken();
		    		String SKUID =st.nextToken();
		    		Sku_id.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("MASTER_URN ")){
		    		st.nextToken();
		    		String masterURN =st.nextToken();
		    		MasterURN.add(masterURN);
		    	}
		    	else if(myList.get(i).contains("PALLET_ID  ")){
		    		st.nextToken();
		    		String palletID =st.nextToken();
		    		Pallet_id.add(palletID);
		    	}
		    	
		    	else if(myList.get(i).contains("ASN_ID     ")){
		    		st.nextToken();
		    		String asn =st.nextToken();
		    		Asn_id.add(asn);
		    		
		    	}
		    	else if(myList.get(i).contains("ORDER_ID    ")){
		    		st.nextToken();
		    		String Odn =st.nextToken();
		    		order_id.add(Odn);
		    		
		    	}
		    }
		    
		    }
		Set<String> ODNID = new HashSet<String>(order_id);
		Set<String> ASNID = new HashSet<String>(Asn_id);
		Set<String> PALLETID = new HashSet<String>(Pallet_id);
		Set<String> MASTERURN = new HashSet<String>(MasterURN);
		Set<String> SKUID = new HashSet<String>(Sku_id);
		
		ArrayList<String> ODNlist = new ArrayList<String>(ODNID);
		ArrayList<String> ASNlist = new ArrayList<String>(ASNID);

		ArrayList<String> PALLETlist = new ArrayList<String>(PALLETID);

		ArrayList<String> MASTERURNlist = new ArrayList<String>(MASTERURN);

		ArrayList<String> SKUIDlist = new ArrayList<String>(SKUID);
		 
		context.setPalletIDList(PALLETlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setMasterurnList(MASTERURNlist);
		    context.setASNList(ASNlist);
		    context.setOrderIDList(ODNlist);
		    for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int l=0;l<ODNlist.size();l++){
				    	System.out.println("The Unique Order value for "  +ODNlist.get(l));
				    	
				    
				    }
		    
				    for(int l=0;l<MASTERURNlist.size();l++){
				    	System.out.println("The Master URN for "  +MASTERURNlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<PALLETlist.size();l++){
				    	System.out.println("The Pallet list for "  +PALLETlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<ASNlist.size();l++){
				    	System.out.println("The ASN value for "  +ASNlist.get(l));
				    	
				    
				    }
		    
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

public void ReadFileMultiRMS() throws FileNotFoundException {
	 String path = "C:/Automation_supporting_files/LnkFiles/RMSlog.txt";
    BufferedReader br = new BufferedReader(new FileReader(path));
    ArrayList<String> MasterURN = new ArrayList<String>();
    ArrayList<String> Pallet_id = new ArrayList<String>();
    ArrayList<String> Asn_id =  new ArrayList<String>();
    ArrayList<String> Sku_id = new ArrayList<String>();
    ArrayList<String> upc = new ArrayList<String>();
    try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
            
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID     ")){
		    		st.nextToken();
		    		String SKUID =st.nextToken();
		    		Sku_id.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("MASTER_URN ")){
		    		st.nextToken();
		    		String masterURN =st.nextToken();
		    		MasterURN.add(masterURN);
		    	}
		    	else if(myList.get(i).contains("PALLET_ID  ")){
		    		st.nextToken();
		    		String palletID =st.nextToken();
		    		Pallet_id.add(palletID);
		    	}
		    	
		    	else if(myList.get(i).contains("ASN_ID     ")){
		    		st.nextToken();
		    		String asn =st.nextToken();
		    		Asn_id.add(asn);
		    		
		    	}
		    	
		    }
		    if(myList.contains("MASTER_URN ")){
		    	st.nextToken();
	    		String masterURN =st.nextToken();
	    		context.setMasterURN(masterURN);
	    	}
		    
		    }
		Set<String> UPC= new HashSet<String>(upc);
		Set<String> ASNID = new HashSet<String>(Asn_id);
		Set<String> PALLETID = new HashSet<String>(Pallet_id);
		Set<String> MASTERURN = new HashSet<String>(MasterURN);
		Set<String> SKUID = new HashSet<String>(Sku_id);
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ASNlist = new ArrayList<String>(ASNID);
		ArrayList<String> PALLETlist = new ArrayList<String>(PALLETID);
		ArrayList<String> MASTERURNlist = new ArrayList<String>(MASTERURN);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKUID);
		 
		    context.setPalletIDList(PALLETlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setMasterurnList(MASTERURNlist);
		    context.setASNList(ASNlist);
		    context.setUPCList(UPClist);
		    for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int l=0;l<UPClist.size();l++){
				    	System.out.println("UPC - "  +UPClist.get(l));
				    	
				    
				    }
		    
				    for(int l=0;l<MASTERURNlist.size();l++){
				    	System.out.println("The Master URN for "  +MASTERURNlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<PALLETlist.size();l++){
				    	System.out.println("The Pallet list for "  +PALLETlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<ASNlist.size();l++){
				    	System.out.println("The ASN value for "  +ASNlist.get(l));
				    	
				    
				    }
		    
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
public void ReadFileMultiDirect() throws FileNotFoundException {
	 String path = "C:/Automation_supporting_files/LnkFiles/Directlog.txt";
   BufferedReader br = new BufferedReader(new FileReader(path));
   ArrayList<String> MasterURN = new ArrayList<String>();
   ArrayList<String> Pallet_id = new ArrayList<String>();
   ArrayList<String> Asn_id =  new ArrayList<String>();
   ArrayList<String> Sku_id = new ArrayList<String>();
   ArrayList<String> upc = new ArrayList<String>();
   try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
           
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID     ")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		Sku_id.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("MASTER_URN ")){
		    		st.nextToken();
		    		String masterURN =st.nextToken().replaceAll(":", "");;
		    		MasterURN.add(masterURN);
		    	}
		    	else if(myList.get(i).contains("PALLET_ID  ")){
		    		st.nextToken();
		    		String palletID =st.nextToken().replaceAll(":", "");;
		    		Pallet_id.add(palletID);
		    	}
		    	
		    	else if(myList.get(i).contains("ASN_ID     ")){
		    		st.nextToken();
		    		String asn =st.nextToken().replaceAll(":", "");;
		    		Asn_id.add(asn);
		    		
		    	}
		    	else if(myList.get(i).contains("UPC    ")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		upc.add(Upc);
		    		
		    	}
		    }
		    
		    }
		Set<String> UPC= new HashSet<String>(upc);
		Set<String> ASNID = new HashSet<String>(Asn_id);
		Set<String> PALLETID = new HashSet<String>(Pallet_id);
		Set<String> MASTERURN = new HashSet<String>(MasterURN);
		Set<String> SKUID = new HashSet<String>(Sku_id);
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ASNlist = new ArrayList<String>(ASNID);
		ArrayList<String> PALLETlist = new ArrayList<String>(PALLETID);
		ArrayList<String> MASTERURNlist = new ArrayList<String>(MASTERURN);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKUID);
		 
		    context.setPalletIDList(PALLETlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setMasterurnList(MASTERURNlist);
		    context.setASNList(ASNlist);
		    context.setUPCList(UPClist);
		    for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int l=0;l<UPClist.size();l++){
				    	System.out.println("UPC - "  +UPClist.get(l));
				    	
				    
				    }
		    
				    for(int l=0;l<MASTERURNlist.size();l++){
				    	System.out.println("The Master URN for "  +MASTERURNlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<PALLETlist.size();l++){
				    	System.out.println("The Pallet list for "  +PALLETlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<ASNlist.size();l++){
				    	System.out.println("The ASN value for "  +ASNlist.get(l));
				    	
				    
				    }
		    
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

public void triggerOutboundDirect() throws InterruptedException, IOException {

	@SuppressWarnings("static-access")

	// Process p = Runtime.getRuntime().exec("cmd /c
	// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
	// Process p = Runtime.getRuntime().exec("cmd /c " +
	// constants.DATASETUP_CROSSDOCK + "");
	Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/Direct_Outbound_SC.lnk");

	Thread.sleep(5000);
	p.waitFor(30, TimeUnit.SECONDS);
	System.out.println("Script Executed successfully");

}

public void triggerOutboundInDirect() throws InterruptedException, IOException {

	@SuppressWarnings("static-access")

	// Process p = Runtime.getRuntime().exec("cmd /c
	// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
	// Process p = Runtime.getRuntime().exec("cmd /c " +
	// constants.DATASETUP_CROSSDOCK + "");
	Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/InDirect_Outbound_SC.lnk");

	Thread.sleep(5000);
	p.waitFor(30, TimeUnit.SECONDS);
	System.out.println("Script Executed successfully");

}
public void triggerNonRetail() throws InterruptedException, IOException {

	@SuppressWarnings("static-access")

	// Process p = Runtime.getRuntime().exec("cmd /c
	// C:/Automation_supporting_files/LnkFiles/Crosdock_SC.lnk");
	// Process p = Runtime.getRuntime().exec("cmd /c " +
	// constants.DATASETUP_CROSSDOCK + "");
	Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/OrderNonRetail_SC.lnk");

	Thread.sleep(5000);
	p.waitFor(30, TimeUnit.SECONDS);
	System.out.println("Script Executed successfully");

}


public void deleteFileDirectOut() throws IOException {
	String path = "C:/Automation_supporting_files/LnkFiles/OrderDirectOutBound.txt";

		File file = new File(path);

		if (file.delete()) {
			System.out.println(file.getName() + " Was deleted!");
		} else {
			System.out.println("Delete Operation Failed. Check: " + file);
		}
	}


public void deleteFileInDirectOut() throws IOException {


	String path = "C:/Automation_supporting_files/LnkFiles/OrderInDirectOutBound.txt";

		File file = new File(path);

		if (file.delete()) {
			System.out.println(file.getName() + " Was deleted!");
		} else {
			System.out.println("Delete Operation Failed. Check: " + file);
		}
	}
public void deleteFileNonRetail() throws IOException {
	String path = "C:/Automation_supporting_files/LnkFiles/OrderNonRetail.txt";

		File file = new File(path);

		if (file.delete()) {
			System.out.println(file.getName() + " Was deleted!");
		} else {
			System.out.println("Delete Operation Failed. Check: " + file);
		}
	}


public void ReadFileDirectOutbound() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
	 String path = "C:/Automation_supporting_files/LnkFiles/OrderDirectOutBound.txt";
  BufferedReader br = new BufferedReader(new FileReader(path));
  ArrayList<String> ORDER_ID = new ArrayList<String>();
  ArrayList<String> SKU_ID = new ArrayList<String>();
  ArrayList<String> UPC =  new ArrayList<String>();
  ArrayList<String> CONTAINER = new ArrayList<String>();
  ArrayList<String> QTY = new ArrayList<String>();
  
  try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
          
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		SKU_ID.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}
		    	
		    	else if(myList.get(i).contains("UPC")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		UPC.add(Upc);
		    		
		    	}
		    	else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}
		    }
		    
		    }
	
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ORDERlist = new ArrayList<String>(ORDER_ID);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKU_ID);
		ArrayList<String> QTYlist = new ArrayList<String>(QTY);
		 
		    context.setOrderIDList(ORDERlist);
		    context.setOrderIDList(ORDERlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setUPCList(UPClist);
		    context.setQtyList(QTYlist);
		    
		    		for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int k=0;k<UPClist.size();k++){
				    	System.out.println("UPC for "  +UPClist.get(k));
				    
				    }
		    
				    for(int l=0;l<ORDERlist.size();l++){
				    	System.out.println("The OrderID for "  +ORDERlist.get(l));
//				    	String container=moveTaskDB.getContainer(ORDERlist.get(l));
//				    	CONTAINER.add(container);
				    }
				    /*ArrayList<String> CONTAINERlist = new ArrayList<String>(CONTAINER);
				   
				    for(int i=0;i<CONTAINERlist.size();i++){
				    	System.out.println("Container for "  +CONTAINERlist.get(i));
				    
				    }*/
				    for(int m=0;m<QTYlist.size();m++){
				    	System.out.println("QTY for "  +QTYlist.get(m));
				    
				    }
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}


public void ReadFileInDirectOutbound() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
	 String path = "C:/Automation_supporting_files/LnkFiles/OrderInDirectOutBound.txt";
 BufferedReader br = new BufferedReader(new FileReader(path));
 ArrayList<String> ORDER_ID = new ArrayList<String>();
 ArrayList<String> SKU_ID = new ArrayList<String>();
 ArrayList<String> UPC =  new ArrayList<String>();
 ArrayList<String> QTY = new ArrayList<String>();
 
 try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
         
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		SKU_ID.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}
		    	
		    	else if(myList.get(i).contains("UPC")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		UPC.add(Upc);
		    		
		    	}
		    	else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}
		    }
		    
		    }
	
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ORDERlist = new ArrayList<String>(ORDER_ID);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKU_ID);
		ArrayList<String> QTYlist = new ArrayList<String>(QTY);
		 
		    context.setOrderIDList(ORDERlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setUPCList(UPClist);
		    context.setQtyList(QTYlist);
		    
		    		for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int k=0;k<UPClist.size();k++){
				    	System.out.println("UPC for "  +UPClist.get(k));
				    
				    }
		    
				    for(int l=0;l<ORDERlist.size();l++){
				    	System.out.println("The OrderID for "  +ORDERlist.get(l));

				    	Thread.sleep(2000);
				    }
				    for(int m=0;m<QTYlist.size();m++){
				    	System.out.println("QTY for "  +QTYlist.get(m));
				    
				    }
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
public void ReadFileInDirect_Method() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	 String path = "C:/Automation_supporting_files/LnkFiles/OrderInDirectOutBound.txt";
BufferedReader br = new BufferedReader(new FileReader(path));
ArrayList<String> ORDER_ID = new ArrayList<String>();
ArrayList<String> SKU_ID = new ArrayList<String>();
ArrayList<String> UPC =  new ArrayList<String>();
ArrayList<String> QTY = new ArrayList<String>();


		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
        
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		SKU_ID.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}
		    	
		    	else if(myList.get(i).contains("UPC")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		UPC.add(Upc);
		    		
		    	}
		    	else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}
		    }
		    
		    }
	context.setSkuIDListIN(SKU_ID);
	context.setQtyList_IN(QTY);
	context.setOrderIDList_IN(ORDER_ID);
//		ArrayList<String> UPClist = new ArrayList<String>(UPC);
//		ArrayList<String> ORDERlist = new ArrayList<String>(ORDER_ID);
//		ArrayList<String> SKUIDlist = new ArrayList<String>(SKU_ID);
//		ArrayList<String> QTYlist = new ArrayList<String>(QTY);
//		 
//		    context.setOrderIDList(ORDERlist);
//		    context.setSkuIDList(SKUIDlist);
//		    context.setUPCList(UPClist);
//		    context.setQtyList(QTYlist);
		    
		    		for(int j=0;j<SKU_ID.size();j++){
				    	System.out.println("The sku value for "  +SKU_ID.get(j));
				    }
				 
//				    for(int k=0;k<UPClist.size();k++){
//				    	System.out.println("UPC for "  +UPClist.get(k));
//				    
//				    }
//		    
				    for(int l=0;l<ORDER_ID.size();l++){
				    	System.out.println("The OrderID for "  +ORDER_ID.get(l));

				    	Thread.sleep(2000);
				    }
				    for(int m=0;m<QTY.size();m++){
				    	System.out.println("QTY for "  +QTY.get(m));
				    
				    }
		
	
}
public void ReadFileDirect_Method() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	 String path = "C:/Automation_supporting_files/LnkFiles/OrderDirectOutBound.txt";
 BufferedReader br = new BufferedReader(new FileReader(path));
 ArrayList<String> ORDER_ID = new ArrayList<String>();
 ArrayList<String> SKU_ID = new ArrayList<String>();
 ArrayList<String> UPC =  new ArrayList<String>();
 ArrayList<String> QTY = new ArrayList<String>();

		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
         
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		SKU_ID.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}
		    	
		    	else if(myList.get(i).contains("UPC")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		UPC.add(Upc);
		    		
		    	}
		    	else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}
		    }
		    
		    }
	context.setSkuIDListDirect(SKU_ID);
	context.setOrderIDList_Direct(ORDER_ID);
	context.setQtyList_Direct(QTY);
		    
		    		for(int j=0;j<SKU_ID.size();j++){
				    	System.out.println("The sku value for "  +SKU_ID.get(j));
				    }   
				    for(int l=0;l<ORDER_ID.size();l++){
				    	System.out.println("The OrderID for "  +ORDER_ID.get(l));
				    }
				     for(int m=0;m<QTY.size();m++){
				    	System.out.println("QTY for "  +QTY.get(m));
				    }
	
	}
public void ReadFileNonRetailOrder() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	 String path = "C:/Automation_supporting_files/LnkFiles/OrderNonRetail.txt";
BufferedReader br = new BufferedReader(new FileReader(path));
ArrayList<String> ORDER_ID = new ArrayList<String>();
ArrayList<String> SKU_ID = new ArrayList<String>();
ArrayList<String> UPC =  new ArrayList<String>();
ArrayList<String> QTY = new ArrayList<String>();

		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
        
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		SKU_ID.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}
		    	
		    	else if(myList.get(i).contains("UPC")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		UPC.add(Upc);
		    		
		    	}
		    	else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}
		    }
		    
		    }
	
		
		context.setSkuIDListNonRetail(SKU_ID);
		context.setOrderIDList_NonRetail(ORDER_ID);
		context.setQtyList_NonRetail(QTY);
			    
			    		for(int j=0;j<SKU_ID.size();j++){
					    	System.out.println("The sku value for "  +SKU_ID.get(j));
					    }   
					    for(int l=0;l<ORDER_ID.size();l++){
					    	System.out.println("The OrderID for "  +ORDER_ID.get(l));
					    }
					     for(int m=0;m<QTY.size();m++){
					    	System.out.println("QTY for "  +QTY.get(m));
					    }
	}
public void readCommonFile() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {

	context.getSkuIDListDirect().addAll(context.getSkuIDListIN());
	context.getSkuIDListDirect().addAll(context.getSkuIDListNonRetail());
	context.getQtyList_Direct().addAll(context.getQtyList_IN());
	context.getQtyList_Direct().addAll(context.getQtyList_NonRetail());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_IN());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_NonRetail());
	context.setQtyList(context.getQtyList_Direct());
	context.setSkuIDList(context.getSkuIDListDirect());	
	context.setOrderIDList(context.getOrderIDList_Direct());
	for(String g : context.getSkuIDList()){
		System.out.println(g);
	}
	for(String g : context.getQtyList()){
		System.out.println(g);
	}
	for(String g : context.getOrderIDList()){
		System.out.println(g);
	}
}
public void club_direct_and_indirect() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
	  
	  
	context.getSkuIDListDirect().addAll(context.getSkuIDListIN());
	context.getQtyList_Direct().addAll(context.getQtyList_IN());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_IN());
context.setQtyList(context.getQtyList_Direct());
context.setSkuIDList(context.getSkuIDListDirect());	
context.setOrderIDList(context.getOrderIDList_Direct());
for(String g : context.getSkuIDList()){
	System.out.println(g);
}
for(String g : context.getQtyList()){
	System.out.println(g);
}
for(String g : context.getOrderIDList()){
	System.out.println(g);
}
}
public void readCommonFileForDirectAndNonRetail() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {

	context.getSkuIDListDirect().addAll(context.getSkuIDListNonRetail());
	context.getQtyList_Direct().addAll(context.getQtyList_NonRetail());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_NonRetail());
	context.setQtyList(context.getQtyList_Direct());
	context.setSkuIDList(context.getSkuIDListDirect());	
	context.setOrderIDList(context.getOrderIDList_Direct());
	for(String g : context.getSkuIDList()){
		System.out.println(g);
	}
	for(String g : context.getQtyList()){
		System.out.println(g);
	}
	for(String g : context.getOrderIDList()){
		System.out.println(g);
	}
}
public void readCommonFileForDirectAndIndirect() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {

	context.getSkuIDListDirect().addAll(context.getSkuIDListIN());
	context.getQtyList_Direct().addAll(context.getQtyList_IN());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_IN());
	context.setQtyList(context.getQtyList_Direct());
	context.setSkuIDList(context.getSkuIDListDirect());	
	context.setOrderIDList(context.getOrderIDList_Direct());
	for(String g : context.getSkuIDList()){
		System.out.println(g);
	}
	for(String g : context.getQtyList()){
		System.out.println(g);
	}
	for(String g : context.getOrderIDList()){
		System.out.println(g);
	}
}
public void ReadFileNonRetail() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
	 String path = "C:/Automation_supporting_files/LnkFiles/OrderNonRetail.txt";
 BufferedReader br = new BufferedReader(new FileReader(path));
 ArrayList<String> ORDER_ID = new ArrayList<String>();
 ArrayList<String> SKU_ID = new ArrayList<String>();
 ArrayList<String> UPC =  new ArrayList<String>();
 ArrayList<String> QTY = new ArrayList<String>();
 
 try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
         
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID")){
		    		st.nextToken();
		    		String SKUID =st.nextToken().replaceAll(":", "");;
		    		SKU_ID.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}
		    	
		    	else if(myList.get(i).contains("UPC")){
		    		st.nextToken();
		    		String Upc =st.nextToken().replaceAll(":", "");;
		    		UPC.add(Upc);
		    		
		    	}
		    	else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}
		    }
		    
		    }
	
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ORDERlist = new ArrayList<String>(ORDER_ID);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKU_ID);
		ArrayList<String> QTYlist = new ArrayList<String>(QTY);
		 
		    context.setOrderIDList(ORDERlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setUPCList(UPClist);
		    context.setQtyList(QTYlist);
		    
		    		for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int k=0;k<UPClist.size();k++){
				    	System.out.println("UPC for "  +UPClist.get(k));
				    
				    }
		    
				    for(int l=0;l<ORDERlist.size();l++){
				    	System.out.println("The OrderID for "  +ORDERlist.get(l));
				    	Thread.sleep(2000);
//				    	String container=moveTaskDB.getContainer(ORDERlist.get(l));
//				    	CONTAINER.add(container);
				    }
				    /*ArrayList<String> CONTAINERlist = new ArrayList<String>(CONTAINER);
				   
				    for(int i=0;i<CONTAINERlist.size();i++){
				    	System.out.println("Container for "  +CONTAINERlist.get(i));
				    
				    }*/
				    for(int m=0;m<QTYlist.size();m++){
				    	System.out.println("QTY for "  +QTYlist.get(m));
				    
				    }
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

public void ReadFileCrossDockMulti() throws FileNotFoundException, ClassNotFoundException, SQLException, InterruptedException {
	 String path = "C:/Automation_supporting_files/LnkFiles/Crossdocklog.txt";
BufferedReader br = new BufferedReader(new FileReader(path));
ArrayList<String> ORDER_ID = new ArrayList<String>();
ArrayList<String> QTY = new ArrayList<String>();
ArrayList<String> URN = new ArrayList<String>();

try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
        
		    for(int i=0;i<myList.size();i++){
		    		if(myList.get(i).contains("ORDER_ID")){
		    		st.nextToken();
		    		String Order =st.nextToken().replaceAll(":", "");;
		    		ORDER_ID.add(Order);
		    	}else if(myList.get(i).contains("QTY_ORDERED")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		QTY.add(Quantity);
		    		
		    	}else if(myList.get(i).contains("MASTER_URN")){
		    		st.nextToken();
		    		String Quantity =st.nextToken().replaceAll(":", "");;
		    		URN.add(Quantity);		    		
		    	}
		    }
		    
		    }
	
		
		
		ArrayList<String> ORDERlist = new ArrayList<String>(ORDER_ID);		
		ArrayList<String> QTYlist = new ArrayList<String>(QTY);
		ArrayList<String> URNlist = new ArrayList<String>(URN);
		 
		    context.setOrderIDList(ORDERlist);
		    context.setQtyList(QTYlist);
		    context.setMasterurnList(URNlist);

		    
				    for(int l=0;l<ORDERlist.size();l++){
				    	System.out.println("The OrderID for "  +ORDERlist.get(l));
				    	Thread.sleep(2000);
				    }			
				    for(int m=0;m<QTYlist.size();m++){
				    	System.out.println("QTY for "  +QTYlist.get(m));
				    
				    }
				    for(int m=0;m<URNlist.size();m++){
				    	System.out.println("URN for "  +URNlist.get(m));
				    
				    }
}
				    

	        catch (IOException e) {
		e.printStackTrace();
	}
	}


public void ReadFile_Second_Direct_Method() throws IOException {
	String path = "C:/Automation_supporting_files/LnkFiles/OrderDirectOutBound.txt";
	 BufferedReader br = new BufferedReader(new FileReader(path));
	 ArrayList<String> ORDER_ID = new ArrayList<String>();
	 ArrayList<String> SKU_ID = new ArrayList<String>();
	 ArrayList<String> UPC =  new ArrayList<String>();
	 ArrayList<String> QTY = new ArrayList<String>();

			for (String line; (line = br.readLine()) != null;) {
				StringTokenizer st = new StringTokenizer(line);
			    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
	         
			    for(int i=0;i<myList.size();i++){
			    	if(myList.get(i).contains("SKU_ID")){
			    		st.nextToken();
			    		String SKUID =st.nextToken().replaceAll(":", "");;
			    		SKU_ID.add(SKUID);
			    	}
			    	else if(myList.get(i).contains("ORDER_ID")){
			    		st.nextToken();
			    		String Order =st.nextToken().replaceAll(":", "");;
			    		ORDER_ID.add(Order);
			    	}
			    	
			    	else if(myList.get(i).contains("UPC")){
			    		st.nextToken();
			    		String Upc =st.nextToken().replaceAll(":", "");;
			    		UPC.add(Upc);
			    		
			    	}
			    	else if(myList.get(i).contains("QTY_ORDERED")){
			    		st.nextToken();
			    		String Quantity =st.nextToken().replaceAll(":", "");;
			    		QTY.add(Quantity);
			    		
			    	}
			    }
			    
			    }
		context.setSkuIDList_Second_Direct(SKU_ID);
		context.setOrderIDList_Second_Direct(ORDER_ID);
		context.setQtyList_Second_Direct(QTY);
			    
			    		for(int j=0;j<SKU_ID.size();j++){
					    	System.out.println("The sku value for "  +SKU_ID.get(j));
					    }   
					    for(int l=0;l<ORDER_ID.size();l++){
					    	System.out.println("The OrderID for "  +ORDER_ID.get(l));
					    }
					     for(int m=0;m<QTY.size();m++){
					    	System.out.println("QTY for "  +QTY.get(m));
					    }
		
		}

public void club_two_direct_and_indirect() {
	context.getSkuIDListDirect().addAll(context.getSkuIDList_Second_Direct());
	context.getQtyList_Direct().addAll(context.getQtyList_Second_Direct());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_Second_Direct());
	context.getSkuIDListDirect().addAll(context.getSkuIDListIN());
	context.getQtyList_Direct().addAll(context.getQtyList_IN());
	context.getOrderIDList_Direct().addAll(context.getOrderIDList_IN());
context.setQtyList(context.getQtyList_Direct());
context.setSkuIDList(context.getSkuIDListDirect());	
context.setOrderIDList(context.getOrderIDList_Direct());
for(String g : context.getSkuIDList()){
	System.out.println(g);
}
for(String g : context.getQtyList()){
	System.out.println(g);
}
for(String g : context.getOrderIDList()){
	System.out.println(g);
}	
}	

public void triggerRMSRDC() throws InterruptedException, IOException {
	File file = new File("C:/Automation_supporting_files/LnkFiles/RMSRDC_log.txt");

	if (file.delete()) {
		System.out.println("File deleted successfully");
	} else {
		System.out.println("Failed to delete the file");
	}

	@SuppressWarnings("static-access")

	Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/RMS_RDC_SC.lnk");

	Thread.sleep(5000);
	p.waitFor(30, TimeUnit.SECONDS);
	System.out.println("Script Executed successfully");

}

public void ReadFileMultiRMSRDC() throws FileNotFoundException {
	 String path = "C:/Automation_supporting_files/LnkFiles/RMSRDC_log.txt";
   BufferedReader br = new BufferedReader(new FileReader(path));
   ArrayList<String> MasterURN = new ArrayList<String>();
   ArrayList<String> Pallet_id = new ArrayList<String>();
   ArrayList<String> Asn_id =  new ArrayList<String>();
   ArrayList<String> Sku_id = new ArrayList<String>();
   ArrayList<String> upc = new ArrayList<String>();
   try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
           
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID     ")){
		    		st.nextToken();
		    		String SKUID =st.nextToken();
		    		Sku_id.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("MASTER_URN ")){
		    		st.nextToken();
		    		String masterURN =st.nextToken();
		    		MasterURN.add(masterURN);
		    	}
		    	else if(myList.get(i).contains("PALLET_ID  ")){
		    		st.nextToken();
		    		String palletID =st.nextToken();
		    		Pallet_id.add(palletID);
		    	}
		    	
		    	else if(myList.get(i).contains("ASN_ID     ")){
		    		st.nextToken();
		    		String asn =st.nextToken();
		    		Asn_id.add(asn);
		    		
		    	}
		    	
		    }
		    if(myList.contains("MASTER_URN ")){
		    	st.nextToken();
	    		String masterURN =st.nextToken();
	    		context.setMasterURN(masterURN);
	    	}
		    
		    }
		Set<String> UPC= new HashSet<String>(upc);
		Set<String> ASNID = new HashSet<String>(Asn_id);
		Set<String> PALLETID = new HashSet<String>(Pallet_id);
		Set<String> MASTERURN = new HashSet<String>(MasterURN);
		Set<String> SKUID = new HashSet<String>(Sku_id);
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ASNlist = new ArrayList<String>(ASNID);
		ArrayList<String> PALLETlist = new ArrayList<String>(PALLETID);
		ArrayList<String> MASTERURNlist = new ArrayList<String>(MASTERURN);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKUID);
		 
		    context.setPalletIDList(PALLETlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setMasterurnList(MASTERURNlist);
		    context.setASNList(ASNlist);
		    context.setUPCList(UPClist);
		    for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int l=0;l<UPClist.size();l++){
				    	System.out.println("UPC - "  +UPClist.get(l));
				    	
				    
				    }
		    
				    for(int l=0;l<MASTERURNlist.size();l++){
				    	System.out.println("The Master URN for "  +MASTERURNlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<PALLETlist.size();l++){
				    	System.out.println("The Pallet list for "  +PALLETlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<ASNlist.size();l++){
				    	System.out.println("The ASN value for "  +ASNlist.get(l));
				    	
				    
				    }
		    
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

public void triggerRMSNDC() throws InterruptedException, IOException {
	File file = new File("C:/Automation_supporting_files/NwNDCRMS/NDCRMS_log.txt");

	if (file.delete()) {
		System.out.println("File deleted successfully");
	} else {
		System.out.println("Failed to delete the file");
	}

	@SuppressWarnings("static-access")

	Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/NwNDCRMS/NDCRMS_SC.lnk");

	Thread.sleep(5000);
	p.waitFor(30, TimeUnit.SECONDS);
	System.out.println("Script Executed successfully");

}

public void ReadFileMultiRMSNDC() throws FileNotFoundException {
	 String path = "C:/Automation_supporting_files/NwNDCRMS/NDCRMSlog.txt";
   BufferedReader br = new BufferedReader(new FileReader(path));
   ArrayList<String> MasterURN = new ArrayList<String>();
   ArrayList<String> Pallet_id = new ArrayList<String>();
   ArrayList<String> Asn_id =  new ArrayList<String>();
   ArrayList<String> Sku_id = new ArrayList<String>();
   ArrayList<String> upc = new ArrayList<String>();
   try {
		for (String line; (line = br.readLine()) != null;) {
			StringTokenizer st = new StringTokenizer(line);
		    ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(Arrays.asList(line.trim()));
           
		    for(int i=0;i<myList.size();i++){
		    	if(myList.get(i).contains("SKU_ID     ")){
		    		st.nextToken();
		    		String SKUID =st.nextToken();
		    		Sku_id.add(SKUID);
		    	}
		    	else if(myList.get(i).contains("MASTER_URN ")){
		    		st.nextToken();
		    		String masterURN =st.nextToken();
		    		MasterURN.add(masterURN);
		    	}
		    	else if(myList.get(i).contains("PALLET_ID  ")){
		    		st.nextToken();
		    		String palletID =st.nextToken();
		    		Pallet_id.add(palletID);
		    	}
		    	
		    	else if(myList.get(i).contains("ASN_ID     ")){
		    		st.nextToken();
		    		String asn =st.nextToken();
		    		Asn_id.add(asn);
		    		
		    	}
		    	
		    }
		    if(myList.contains("MASTER_URN ")){
		    	st.nextToken();
	    		String masterURN =st.nextToken();
	    		context.setMasterURN(masterURN);
	    	}
		    
		    }
		Set<String> UPC= new HashSet<String>(upc);
		Set<String> ASNID = new HashSet<String>(Asn_id);
		Set<String> PALLETID = new HashSet<String>(Pallet_id);
		Set<String> MASTERURN = new HashSet<String>(MasterURN);
		Set<String> SKUID = new HashSet<String>(Sku_id);
		
		ArrayList<String> UPClist = new ArrayList<String>(UPC);
		ArrayList<String> ASNlist = new ArrayList<String>(ASNID);
		ArrayList<String> PALLETlist = new ArrayList<String>(PALLETID);
		ArrayList<String> MASTERURNlist = new ArrayList<String>(MASTERURN);
		ArrayList<String> SKUIDlist = new ArrayList<String>(SKUID);
		 
		    context.setPalletIDList(PALLETlist);
		    context.setSkuIDList(SKUIDlist);
		    context.setMasterurnList(MASTERURNlist);
		    context.setASNList(ASNlist);
		    context.setUPCList(UPClist);
		    for(int j=0;j<SKUIDlist.size();j++){
				    	System.out.println("The sku value for "  +SKUIDlist.get(j));
				    }
				 
				    for(int l=0;l<UPClist.size();l++){
				    	System.out.println("UPC - "  +UPClist.get(l));
				    	
				    
				    }
		    
				    for(int l=0;l<MASTERURNlist.size();l++){
				    	System.out.println("The Master URN for "  +MASTERURNlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<PALLETlist.size();l++){
				    	System.out.println("The Pallet list for "  +PALLETlist.get(l));
				    	
				    
				    }
				    for(int l=0;l<ASNlist.size();l++){
				    	System.out.println("The ASN value for "  +ASNlist.get(l));
				    	
				    
				    }
		    
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}

	
		
   
