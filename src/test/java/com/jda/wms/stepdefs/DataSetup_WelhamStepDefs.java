package com.jda.wms.stepdefs;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.InsertDataIntoDB;
import com.jda.wms.dataload.InsertDataIntoWebservicelog;
import com.jda.wms.db.Database;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;


public class DataSetup_WelhamStepDefs {
private InsertDataIntoWebservicelog insertDataIntoWebservicelog;
private Context context;
private Database database;
@Inject
public DataSetup_WelhamStepDefs(InsertDataIntoWebservicelog insertDataIntoWebservicelog,Context context,Database database){
this.insertDataIntoWebservicelog=insertDataIntoWebservicelog;
this.context=context;
this.database=database;
}


@Given("^Data loaded into Webservices log$")
public void data_to_be_inserted() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateSkuData();
insertDataIntoWebservicelog.triggercrossdock();  
insertDataIntoWebservicelog.ReadFile(); 
Thread.sleep(5000);  
}

@Given("^Data loaded into Webservices log for crossdock$")
public void data_to_be_inserted_crossdock() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggercrossdock();  
insertDataIntoWebservicelog.ReadFile(); 
Thread.sleep(5000);  
}

@Given("^Multi Data loaded into Webservices log for crossdock$")
public void Multi_data_to_be_inserted_crossdock() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggercrossdock();  
insertDataIntoWebservicelog.ReadFileCrossDockMulti(); 
Thread.sleep(2000);  
}

@Given("Read Pallet Id Data from crossdock log and receive$")
public void Read_data_from_crossdocklog_to_receive() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.ReadFile(); 
Thread.sleep(1000);  
}

@Given("^Data setup for partial receiving$")
public void data_to_be_inserted_for_partial_receiving() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateMultiSkuDataForward_partial();
insertDataIntoWebservicelog.triggercrossdock();  
insertDataIntoWebservicelog.ReadFile(); 
Thread.sleep(5000);  
}
@Given("^Data setup for Overspill management$")
public void data_to_be_inserted_for_overspill_management() throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed
{
insertDataIntoWebservicelog.UpdateMultiSkuDataForward_Overspill();
insertDataIntoWebservicelog.triggercrossdock(); 
insertDataIntoWebservicelog.ReadFile();
insertDataIntoWebservicelog.getMultiOrder_Crossdock(); 
Thread.sleep(5000);  
}
@Given("^Data loaded into Webservices log with different destination$")
public void data_to_be_inserted_with_different_destination() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
Thread.sleep(2000); 
insertDataIntoWebservicelog.deleteFile();
Thread.sleep(5000);
insertDataIntoWebservicelog.UpdateSkuDataDestination();
Thread.sleep(2000);
insertDataIntoWebservicelog.triggercrossdock();
Thread.sleep(2000);
insertDataIntoWebservicelog.ReadFile1();
Thread.sleep(5000);
  
}
@Given("^Data loaded into Webservices log for reverse boxed$")
public void data_loaded_reverse_boxed() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataB();
insertDataIntoWebservicelog.triggercrossdockReverse();  
insertDataIntoWebservicelog.ReadFileReverse(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for reverse hanging$")
public void data_loaded_reverse_hanging() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataH();
insertDataIntoWebservicelog.triggercrossdockReverse();  
insertDataIntoWebservicelog.ReadFileReverse(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for reverse hanging and Boxed$")
public void data_loaded_reverse_hanging_Boxed() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataHB();
insertDataIntoWebservicelog.triggercrossdockReverse();  
insertDataIntoWebservicelog.ReadFileReverse(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
public void multidata_to_be_inserted(String SOURCE_ID, String HUB_ID,String CUSTOMER_ID,String SKU_ID,String LINE_ID,String QTY_DUE,String ODN,String URN_ADVICE,String STO,String ASN_ID,String MASTER_URN) throws InterruptedException, IOException, ClassNotFoundException, SQLException{
insertDataIntoWebservicelog.deleteSkuData();
//insertDataIntoWebservicelog.UpdateMultiSkuData(SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN);

Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for forward$")
public void data_loaded_forward() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateMultiSkuDataForward();
insertDataIntoWebservicelog.triggercrossdock();  
insertDataIntoWebservicelog.ReadFile(); 
Thread.sleep(3000);  
}

@Given("^Data loaded into Webservices log for IDT")
public void data_loaded_IDT() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataIDT();
insertDataIntoWebservicelog.triggerIDT();  
insertDataIntoWebservicelog.ReadFileIDT(); 
Thread.sleep(3000);  
}

@Given("^Data loaded into Webservices log for Direct")
public void data_loaded_Direct() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataDirect();
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}

@Given("^log for Direct expiry Date Validation$")
public void log_for_expiry_date_LIDATION() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateSkuDataDirectExpiry();
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for Direct Unexpected SKU")
public void data_loaded_Direct_unexpected_SKU() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataDirect_UnexpectedSKU();
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}



@Given("^Data loaded into Webservices log for full pallet Direct")
public void data_loaded_Direct_fullPallet() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateSkuFullPalletDirect();
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for FSV")
public void data_loaded_FSV() throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed
{
insertDataIntoWebservicelog.UpdatemultiSkuDataFSV();
insertDataIntoWebservicelog.triggerFSV();  
insertDataIntoWebservicelog.ReadFileFSV(); 
Thread.sleep(3000);  
}
@Given("^FSV Data loaded into Webservices log for FSV Two SKUs")
public void data_loaded_Multi_Sku_FSV_Two_SKUs() throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed
{  
insertDataIntoWebservicelog.UpdatemultiSkuDataFSV_Two_SKUs();
insertDataIntoWebservicelog.triggerFSV();  
insertDataIntoWebservicelog.ReadFileFSV(); 
Thread.sleep(3000);  
}
@Given("^Data setup in Webservices log for overreceipt")
public void data_loaded_FSV_over_receipt() throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed
{
insertDataIntoWebservicelog.overreceipt_UpdateSkuDataFSV();
insertDataIntoWebservicelog.triggerFSV();  
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for full pallet FSV")
public void data_loaded_Direct_fullPallet_FSV() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateSkuFullPalletDirect();
insertDataIntoWebservicelog.triggerFSV();  
insertDataIntoWebservicelog.ReadFileFSV(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log from CSVfile")
public void data_loaded_Direct_file() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataDirectFile();
//insertDataIntoWebservicelog.triggerDirect();  
//insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}
@Given("^Data loaded into Webservices log for config pallet Direct")
public void data_loaded_Direct_configPallet() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdateSkuConfigPalletDirect();
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}

@Given("^Data triggered into Webservices log for config pallet FSV")
public void data_triggered_configPallet() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerFSV();  
insertDataIntoWebservicelog.ReadFileFSV();  
Thread.sleep(3000);  
}
@Given("^Data triggered into Webservices log for config pallet IDT")
public void data_triggered_configPallet_forIDT() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerIDT();  
insertDataIntoWebservicelog.ReadFileIDT();  
Thread.sleep(3000);  
}

@Given("^load data using datatable$")
public void user_enters_testuser_and_Test(DataTable usercredentials) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
String deletequery = "delete from sku_data";
if (context.getConnection() == null) {
    database.connect();
   }
  Statement stmt= context.getConnection().createStatement();
 System.out.println("statement");
 stmt.executeQuery(deletequery);
for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) { 
context.setSkuId(data.get("SKU_ID"));
context.setQtyToMovePck(data.get("QTY_DUE"));
context.setQtyOredered(data.get("QTY_DUE"));
context.setupc(data.get("UPC"));
context.setSupplierID(data.get("SUPPLIER_ID")); 
String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,UPC,SUPPLIER_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('"+data.get("SOURCE_ID")+"','"+data.get("HUB_ID")+"','"+data.get("CUSTOMER_ID")+"','"+data.get("SKU_ID")+"','"+data.get("UPC")+"','"+data.get("SUPPLIER_ID")+"','"+data.get("LINE_ID")+"','"+data.get("QTY_DUE")+"','"+data.get("ODN")+"','"+data.get("URN_ADVICE")+"','"+data.get("STO")+"','"+data.get("ASN_ID")+"',"+data.get("MASTER_URN")+")";
stmt.executeQuery(updatequery1);
   System.out.println(updatequery1);
}
context.getConnection().commit();
System.out.println("SKU_DATA table is updated");
 
Thread.sleep(3000); 
}

@Given("^load data using datatable for multi \"([^\"]*)\"$")
public void user_enters_testuser_and_Test_Multi(String Type,DataTable usercredentials) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	String deletequery = "delete from sku_data";
context.setSiteId(Type);
	 if (context.getConnection() == null) {
		    database.connect();
		   }
		   Statement stmt = context.getConnection().createStatement();
		   System.out.println("statement");
		   stmt.executeQuery(deletequery);
			ArrayList<String> skuList = new ArrayList<String>();
			ArrayList<String> QtyList = new ArrayList<String>();
			ArrayList<String> CustmList = new ArrayList<String>();

for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) { 
context.setSkuId(data.get("SKU_ID"));
context.setQtyOredered(data.get("QTY_DUE"));
context.setAddressID(data.get("CUSTOMER_ID"));
skuList.add((data.get("SKU_ID")));
QtyList.add((data.get("QTY_DUE")));
CustmList.add((data.get("CUSTOMER_ID")));
String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,UPC,SUPPLIER_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('"+data.get("SOURCE_ID")+"','"+data.get("HUB_ID")+"','"+data.get("CUSTOMER_ID")+"','"+data.get("SKU_ID")+"','"+data.get("UPC")+"','"+data.get("SUPPLIER_ID")+"','"+data.get("LINE_ID")+"','"+data.get("QTY_DUE")+"','"+data.get("ODN")+"','"+data.get("URN_ADVICE")+"','"+data.get("STO")+"','"+data.get("ASN_ID")+"',"+data.get("MASTER_URN")+")";
stmt.executeQuery(updatequery1);
   System.out.println(updatequery1);
}
context.getConnection().commit();
System.out.println("SKU_DATA table is updated");
context.setSkuIDList(skuList);
context.setQtyList(QtyList);
context.setCustmList(CustmList);
Thread.sleep(3000); 
}

@Given("^load data using datatable for multi records$")
public void user_enters_testuser_and_Test_Multi_records(DataTable usercredentials) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	String deletequery = "delete from sku_data";
	
	 if (context.getConnection() == null) {
		    database.connect();
		   }
		   Statement stmt = context.getConnection().createStatement();
		   System.out.println("statement");
		   stmt.executeQuery(deletequery);
			ArrayList<String> skuList = new ArrayList<String>();
			ArrayList<String> QtyList = new ArrayList<String>();
			ArrayList<String> CustmList = new ArrayList<String>();

for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) { 
context.setSkuId(data.get("SKU_ID"));
context.setQtyOredered(data.get("QTY_DUE"));
skuList.add((data.get("SKU_ID")));
QtyList.add((data.get("QTY_DUE")));
CustmList.add((data.get("CUSTOMER_ID")));
String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('"+data.get("SOURCE_ID")+"','"+data.get("HUB_ID")+"','"+data.get("CUSTOMER_ID")+"','"+data.get("SKU_ID")+"','"+data.get("LINE_ID")+"','"+data.get("QTY_DUE")+"','"+data.get("ODN")+"','"+data.get("URN_ADVICE")+"','"+data.get("STO")+"','"+data.get("ASN_ID")+"',"+data.get("MASTER_URN")+")";
stmt.executeQuery(updatequery1);
   System.out.println(updatequery1);
}
context.getConnection().commit();
System.out.println("SKU_DATA table is updated");
context.setSkuListForInventory(skuList);
context.getSkuIDList().addAll(skuList);
context.setQtyListForInventory(QtyList);
context.getQtyList().addAll(QtyList);
context.getCustmList().addAll(CustmList);
Thread.sleep(3000); 
}

@Given("^Load multiple SKU data records$")
public void load_SkuData_Multi_records(DataTable usercredentials) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	String deletequery = "delete from sku_data";
	
	 if (context.getConnection() == null) {
		    database.connect();
		   }
		   Statement stmt = context.getConnection().createStatement();
		   System.out.println("statement");
		   stmt.executeQuery(deletequery);
			ArrayList<String> skuList = new ArrayList<String>();
			ArrayList<String> upclist = new ArrayList<String>();
			ArrayList<String> QtyList = new ArrayList<String>();
			ArrayList<String> CustmList = new ArrayList<String>();
			ArrayList<String> SuppList = new ArrayList<String>();

for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) { 
context.setSkuId(data.get("SKU_ID"));
context.setQtyOredered(data.get("QTY_DUE"));
skuList.add((data.get("SKU_ID")));
upclist.add((data.get("UPC")));
QtyList.add((data.get("QTY_DUE")));
CustmList.add((data.get("CUSTOMER_ID")));
SuppList.add((data.get("SUPPLIER_ID")));
String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,UPC,SUPPLIER_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('"+data.get("SOURCE_ID")+"','"+data.get("HUB_ID")+"','"+data.get("CUSTOMER_ID")+"','"+data.get("SKU_ID")+"','"+data.get("UPC")+"','"+data.get("SUPPLIER_ID")+"','"+data.get("LINE_ID")+"','"+data.get("QTY_DUE")+"','"+data.get("ODN")+"','"+data.get("URN_ADVICE")+"','"+data.get("STO")+"','"+data.get("ASN_ID")+"',"+data.get("MASTER_URN")+")";
//String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('"+data.get("SOURCE_ID")+"','"+data.get("HUB_ID")+"','"+data.get("CUSTOMER_ID")+"','"+data.get("SKU_ID")+"','"+data.get("LINE_ID")+"','"+data.get("QTY_DUE")+"','"+data.get("ODN")+"','"+data.get("URN_ADVICE")+"','"+data.get("STO")+"','"+data.get("ASN_ID")+"',"+data.get("MASTER_URN")+")";
stmt.executeQuery(updatequery1);
   System.out.println(updatequery1);
}
context.getConnection().commit();
System.out.println("SKU_DATA table is updated");
context.setSkuListForInventory(skuList);
context.setSkuIDList(skuList);
context.getSkuIDList().addAll(skuList);
context.setQtyListForInventory(QtyList);
context.setQtyList(QtyList);
context.getQtyList().addAll(QtyList);
context.setCustmList(CustmList);
context.getCustmList().addAll(CustmList);
Thread.sleep(3000); 
}

@Given("^Load multiple SKU data records from CSV")
public void load_SkuData_Multi_records_from_CSV() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.UpdatemultiSkuDataDirectFile();
//insertDataIntoWebservicelog.ReadFileDirect(); 
//insertDataIntoWebservicelog.ReadFileMultiDirect();// to Test multi sku direct
Thread.sleep(60000);  
}

@Given("^Data Triggered into Webservices log for Direct")
public void data_triggered_Direct_configPallet() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerDirect();  
//insertDataIntoWebservicelog.ReadFileDirect(); 
insertDataIntoWebservicelog.ReadFileMultiDirect();// to Test multi sku direct
Thread.sleep(60000);  
}
@Given("^Data Triggered into Webservices log for Single Sku Direct")
public void data_triggered_Direct_configPallet_Single_sku() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); 
Thread.sleep(3000);  
}
@Given("^Data Triggered for Direct multiPallet")
public void data_for_Direct_multiPallet() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerDirect();  
insertDataIntoWebservicelog.ReadFileDirect(); // to Test multi sku direct
//insertDataIntoWebservicelog.ReadFileMultiDirect();
Thread.sleep(3000);  
}
@Given("^Data Triggered into Webservices log for RMS")
public void data_triggered_RMS() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerRMS();  
insertDataIntoWebservicelog.ReadFileMultiRMS(); 
Thread.sleep(3000);  
}
@Given("^Data triggered into Webservices log for crossDock multiSku")
public void data_triggered_crossDock_multiSku() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggercrossdockForward();  
insertDataIntoWebservicelog.ReadFileMultiXDock(); 
Thread.sleep(3000);  
}

@Given("^Data Triggered into Webservices log for IDT")
public void data_triggered_IDT() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerIDT(); 
insertDataIntoWebservicelog.ReadFileIDT(); 
Thread.sleep(3000);  
}

@Given("^Data for validating Expiry date in FSV")
public void data_for_validating_expiry_date_in_FSV() throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed
{
insertDataIntoWebservicelog.UpdatSkuDataFSVExpiryDate();
insertDataIntoWebservicelog.triggerFSV(); 
}

@Given("^Data Triggered into Webservices log for FSV")
public void data_triggered_FSV() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggerFSV(); 
insertDataIntoWebservicelog.ReadFileFSV(); 
Thread.sleep(3000);  
}

@Given("^Data Triggered into Webservices log for Crossdock")
public void data_triggered_crossdock() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.triggercrossdock();  
insertDataIntoWebservicelog.ReadFile(); 
Thread.sleep(5000);  
}
@Given("^Data Triggered into Webservices log for direct outbound order")
public void data_triggered_direct_outbound() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.deleteFileDirectOut();
insertDataIntoWebservicelog.triggerOutboundDirect();  
insertDataIntoWebservicelog.ReadFileDirectOutbound(); 
Thread.sleep(5000);  
}
@Given("^Data Triggered into Webservices log for direct  order")
public void data_triggered_direct() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.deleteFileDirectOut();
insertDataIntoWebservicelog.triggerOutboundDirect();  
insertDataIntoWebservicelog.ReadFileDirect_Method(); 
Thread.sleep(60000);  //wait time to let the clustering happen
}
@Given("^Data Triggered into Webservices log for second direct  order")
public void data_triggered_second_direct() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.deleteFileDirectOut();
insertDataIntoWebservicelog.triggerOutboundDirect();  
insertDataIntoWebservicelog.ReadFile_Second_Direct_Method(); 
Thread.sleep(60000);  //wait time to let the clustering happen
}
@Given("^Read the common File")
public void read_the_common_file() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
insertDataIntoWebservicelog.readCommonFile(); 
Thread.sleep(5000);  
}
@Given("^Read the File For Direct and Non Retail")
public void read_the_common_file_for_direct_and_NonRetail() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
insertDataIntoWebservicelog.readCommonFileForDirectAndNonRetail(); 
Thread.sleep(5000);  
}
@Given("^Read the File InDirect and Direct")
public void read_the_common_file_for_direct_and_Direct() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
insertDataIntoWebservicelog.readCommonFileForDirectAndIndirect(); 
Thread.sleep(5000);  
}
@Given("^club the lists")
public void set_direct_and_indirect() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
	Thread.sleep(10000);  	
insertDataIntoWebservicelog.club_direct_and_indirect(); 

}
@Given("^club two direct and one indirect orders$")
public void set_two_direct_and_indirect() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
	Thread.sleep(10000);  	
insertDataIntoWebservicelog.club_two_direct_and_indirect(); 

}
@Given("^Data Triggered into Webservices log for indirect outbound order")
public void data_triggered_indirect_outbound() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.deleteFileInDirectOut();
insertDataIntoWebservicelog.triggerOutboundInDirect();  
insertDataIntoWebservicelog.ReadFileInDirectOutbound(); 
Thread.sleep(3000);  
}
@Given("^Data Triggered into Webservices log for indirect  order")
public void data_triggered_indirect() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
insertDataIntoWebservicelog.deleteFileInDirectOut();
insertDataIntoWebservicelog.triggerOutboundInDirect();  
insertDataIntoWebservicelog.ReadFileInDirect_Method(); 
Thread.sleep(60000);  //wait time to let the clustering happen
}

@Given("^Data Triggered into Webservices log for Non-Retail order")
public void data_triggered_nonRetail_order() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
insertDataIntoWebservicelog.deleteFileNonRetail();
insertDataIntoWebservicelog.triggerNonRetail();
insertDataIntoWebservicelog.ReadFileNonRetail(); 
Thread.sleep(5000);  
}
@Given("^Data Triggered into Webservices log for NON_RETAIL")
public void data_triggered_nonRetail_multiType() throws InterruptedException, IOException, ClassNotFoundException, SQLException
{ 
insertDataIntoWebservicelog.deleteFileNonRetail();
insertDataIntoWebservicelog.triggerNonRetail();
insertDataIntoWebservicelog.ReadFileNonRetailOrder(); 
Thread.sleep(5000);  
}
@Given("^Data Triggered into Webservices log for Cross Dock Receiving")
public void data_triggered_for_cross_dock_receiving() throws InterruptedException, IOException{
	insertDataIntoWebservicelog.triggercrossdock();  
	insertDataIntoWebservicelog.ReadFile(); 
	Thread.sleep(120000); 
}
@Given("^Data Triggered into Webservices log for Cross Dock Multi Receiving")
public void data_triggered_for_multiple_cross_dock_receiving() throws InterruptedException, IOException, ClassNotFoundException, FindFailed, SQLException{
	insertDataIntoWebservicelog.triggercrossdock();  
	insertDataIntoWebservicelog.Read_Multi_Crossdock_Receiving(); 
	System.out.println("wait time for the UPI line to load!!!");
	Thread.sleep(19000); 
}
@Given("^Data for RMS Receiving Has been triggered in Webservices log for \"([^\"]*)\"$")
public void data_for_rms_receiving_in_RDC_has_been_triggered(String Site) throws InterruptedException, IOException, ClassNotFoundException, SQLException
{
	if(Site.contains("Westthurrock")){
		insertDataIntoWebservicelog.triggerRMSNDC();  
		insertDataIntoWebservicelog.ReadFileMultiRMSNDC(); 
		Thread.sleep(3000);
	}
	else{
insertDataIntoWebservicelog.triggerRMSRDC();  
insertDataIntoWebservicelog.ReadFileMultiRMSRDC(); 
Thread.sleep(3000);  
	}
}
}


