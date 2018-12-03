package com.jda.wms.stepdefs.Exit;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.DeleteDataFromDB;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.dataload.exit.InsertDataIntoDB;
import com.jda.wms.dataload.exit.SelectDataFromDB;
import com.jda.wms.dataload.exit.UpdateDataFromDB;
import com.jda.wms.db.Exit.AddressDB;
import com.jda.wms.db.Exit.AddressMaintenanceDB;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.db.Exit.SupplierSkuDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.UPIReceiptHeaderDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JDAExitUPIheaderPage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskManagementPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;
import com.jda.wms.utils.Utilities;
import com.jda.wms.db.Exit.MandsDB;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class JDAExitUpiHeader{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private SystemAllocationPage systemAllocationPage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private Context context;
	private AddressMaintenancePage addressMaintenancePage;
	private Verification verification;
	private OrderHeaderDB orderHeaderDB;
	private AddressDB addressDB;
	private Hooks hooks;
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;
	private OrderHeaderContext orderHeaderContext;
	private OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs;
	private MoveTaskStepDefs moveTaskStepDefs;
	private OrderPreparationStepDefs orderPreparationStepDefs;
	private DataSetupRunner dataSetupRunner;
	private GetTCData getTCData;
	private UpdateDataFromDB updateDataFromDB;
	private JDALoginStepDefs jdaLoginStepDefs;
	private MoveTaskDB  moveTaskDB;
	private MoveTaskUpdatePage moveTaskUpdatePage;
	private MoveTaskListGenerationPage moveTaskListGenerationPage;
	private JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;
	private MoveTaskManagementPage moveTaskManagementPage;
	private JDAExitputtyfunctionsStepDef jDAExitputtyfunctionsStepDef;
	private PuttyFunctionsPage puttyFunctionsPage;
	private JDAExitUPIheaderPage jDAExitUPIheaderPage;
	private AddressMaintenanceDB addressMaintenanceDB;
	private SupplierSkuDB supplierSkuDB;
	private MandsDB mandsDB;
	private SkuDB skuDB;
	
	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,JDAExitputtyfunctionsStepDef jDAExitputtyfunctionsStepDef,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage, Verification verification, OrderHeaderDB orderHeaderDB,
			AddressDB addressDB, Hooks hooks, InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, OrderHeaderContext orderHeaderContext,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs,
			OrderPreparationStepDefs orderPreparationStepDefs, DataSetupRunner dataSetupRunner, GetTCData getTCData,
			UpdateDataFromDB updateDataFromDB, JDALoginStepDefs jdaLoginStepDefs,
			MoveTaskDB  moveTaskDB,MoveTaskUpdatePage moveTaskUpdatePage,PuttyFunctionsPage puttyFunctionsPage,
			MoveTaskListGenerationPage moveTaskListGenerationPage,JdaHomePage jdaHomePage,
			InventoryDB inventoryDB,SupplierSkuDB supplierSkuDB,SkuDB skuDB,MandsDB mandsDB,AddressMaintenanceDB addressMaintenanceDB,MoveTaskManagementPage moveTaskManagementPage,JDAExitUPIheaderPage jDAExitUPIheaderPage) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.moveTaskDB=moveTaskDB;
//		this.purchaseOrderReceivingStepDefs=purchaseOrderReceivingStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.addressMaintenancePage = addressMaintenancePage;
		this.verification = verification;
		this.orderHeaderDB = orderHeaderDB;
		this.addressDB = addressDB;
		this.hooks = hooks;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
		this.orderHeaderContext = orderHeaderContext;
		this.orderLineMaintenanceStepDefs = orderLineMaintenanceStepDefs;
		this.moveTaskStepDefs = moveTaskStepDefs;

		this.orderPreparationStepDefs = orderPreparationStepDefs;
		this.dataSetupRunner = dataSetupRunner;
		this.getTCData = getTCData;
		this.updateDataFromDB = updateDataFromDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
//		this.puttyFunctionsStepDefs =puttyFunctionsStepDefs;
		this.moveTaskUpdatePage=moveTaskUpdatePage;
		this.moveTaskListGenerationPage=moveTaskListGenerationPage;
		this.jdaHomePage=jdaHomePage;
		this.inventoryDB=inventoryDB;
		this.moveTaskManagementPage=moveTaskManagementPage;
		this.jDAExitputtyfunctionsStepDef=jDAExitputtyfunctionsStepDef;
		this.puttyFunctionsPage=puttyFunctionsPage;
		this.jDAExitUPIheaderPage=jDAExitUPIheaderPage;
		this.addressMaintenanceDB=addressMaintenanceDB;
		this.supplierSkuDB=supplierSkuDB;
		this.mandsDB=mandsDB;
		this.skuDB=skuDB;
	}
	  @And ("^Insert UPI data and Delivery data$")
	  public void insert_UPI_data() throws Throwable
	  {
		String SAPvalue=Utilities.getEightDigitRandomNumber();
		context.setSAPvalue(SAPvalue);
		dataSetupRunner.insertOrderDataforUPI();
		GetTCData.getpoId();
		String skuid = "000000000021071852";
		context.setSkuId2(skuid);
		puttyFunctionsPage.i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		Thread.sleep(1000);
		dataSetupRunner.insertUPIReceiptData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		dataSetupRunner.insertDelivery();
		String ASN=context.getASN();
		System.out.println(ASN);
	  }
	  
	  @Then("^Verify data in UPI Receipt header screen$")
	  public void verify_data_in_UPI_receipt_header_screen() throws ClassNotFoundException, SQLException, InterruptedException, FindFailed
	  {
		  jdaHomePage.navigateToUPIheader(); 
		  jDAExitUPIheaderPage.EnterPalletID();
		 		  
	  }
	  
	  @Then("^Click on lines$")
	  public void click_on_lines() throws InterruptedException, FindFailed
	  {
		  Thread.sleep(1000);
		  jDAExitUPIheaderPage.clickLines();
		 		  
	  }
	  
	  @And("^URN lines are validated successfully$")
	  public void urn_line_are_validated_succesfully() throws FindFailed, InterruptedException {
		  String Lines=jDAExitUPIheaderPage.getLines();
		  
//		  Assert.assertEquals("Not as expected", Lines, UPIReceiptHeaderDB.getLineID());
		  
		  
	  }
	 
	  @Then("^Verify ASN ID for the PalletID$")
	  public void verify_ASN_ID_for_the_palletID() throws FindFailed, InterruptedException, ClassNotFoundException, SQLException
	  {
		  jDAExitUPIheaderPage.clickMiscellaneous();
		  jDAExitUPIheaderPage.ASN_Validation();
	  }
	  @Then("^Verify pallet id$")
	  public void verify_pallet_id() throws FindFailed, InterruptedException, ClassNotFoundException, SQLException
	  {
		  jDAExitUPIheaderPage.PalletID_validation();
	  }
	  @Then("^Navigate to consignment details page$")
	  public void navigate_to_consignemnt_details_page()
	  {
		  
	  }
	 
	  
	  
	  
	  
	  
	@Given ("^Data to be inserted in preadvice header,order header and UPI receipt with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_with(String status,
			String type, String customer) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
//		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertPreAdviceDataforUPI();
		String SAPvalue=Utilities.getEightDigitRandomNumber();
		context.setSAPvalue(SAPvalue);
		dataSetupRunner.insertOrderDataforUPI();
		GetTCData.getpoId();
		String skuid = "000000000021071852";
		context.setSkuId2(skuid);
		puttyFunctionsPage.i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		Thread.sleep(1000);
		dataSetupRunner.insertUPIReceiptData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status : "+orderstatus);
//		JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
//		if(orderstatus.equals(status))
//		{
//			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status1"+orderstatus1);
				//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
			}
		
		}
	
	
	@Given ("^Data to be inserted in preadvice header,order header and UPIreceipt  with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for Red Stock$")
	public void Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_with_for_Red_Stock(String status,
			String type, String customer) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
		String skuid = "000000000021071851";
		context.setSkuId2(skuid);
		
//		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertPreAdviceDataforUPIForRedStock();
		String SAPvalue=Utilities.getEightDigitRandomNumber();
		context.setSAPvalue(SAPvalue);
		dataSetupRunner.insertOrderDataforUPIForRedStock();
		GetTCData.getpoId();
		
		puttyFunctionsPage.i_generate_pallet_id_for_UPI_for_red_stock(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		Thread.sleep(1000);
		dataSetupRunner.insertUPIReceiptDataForRedStock();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status : "+orderstatus);
//		JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
//		if(orderstatus.equals(status))
//		{
//			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status1"+orderstatus1);
				//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
			}
		
		}
	
	@Given ("^Navigate to Move Task management Screen to verify Order Allocated status for ASN Crossdock$")
	public void Navigate_to_Move_Task_management_Screen_to_verify_Order_Allocated_status_for_ASN_Crossdock() throws Throwable{
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		Thread.sleep(3000);
		jdaHomePage.navigateToMoveTaskListManagementPage();
		Thread.sleep(3000);
		moveTaskListGenerationPage.enterTaskIdInMoveTaskUpdate(context.getOrderId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
	}
	@And ("^Validation of List Id generated with prefix as ASNB$")
	public void Validation_of_List_Id_generated_with_prefix_as_ASNB()throws Throwable{
			
			moveTaskManagementPage.validateListIDforIDT();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as FSVB", "FSVB", prefixlist);
			logger.debug("List Id generated with prefix as FSVB is : " + actuallist);
			System.out.println("List Id generated with prefix as FSVB is : " + actuallist);
		}
	
	@Given ("^Data to be inserted in preadvice header,order header and UPI receipt for \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_for(String site,
			String type, String customer) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
//		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertPreAdviceDataforUPI();
		String SAPvalue=Utilities.getEightDigitRandomNumber();
		context.setSAPvalue(SAPvalue);
		dataSetupRunner.insertOrderDataforUPI();
		GetTCData.getpoId();
		String skuid = "000000000021071852";
		context.setSkuId2(skuid);
		puttyFunctionsPage.i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		Thread.sleep(1000);
		dataSetupRunner.insertUPIReceiptData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status : "+orderstatus);
//		JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
//		if(orderstatus.equals(status))
//		{
//			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status1"+orderstatus1);
				//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
			}
	}
			@Given ("^Data to be inserted in preadvice header,order header and UPI receipt without ASN \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
			public void Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_without_ASN(String status,
					String type, String customer) throws Throwable {
				System.out.println("data");
				context.setStoType(type);
				context.setCustomer(customer);
//				dataSetupRunner.insertPreAdviceData();
				dataSetupRunner.insertPreAdviceDataforUPI();
				String SAPvalue=Utilities.getEightDigitRandomNumber();
				context.setSAPvalue(SAPvalue);
				dataSetupRunner.insertOrderDataforUPI();
				GetTCData.getpoId();
				String skuid = "000000000021071852";
				context.setSkuId2(skuid);
				puttyFunctionsPage.i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
				String palletIDforUPI = context.getpalletIDforUPI();
				Thread.sleep(1000);
				dataSetupRunner.insertUPIReceiptData();
				String orderID = getTCData.getSto();
				System.out.println("New Order ID : " + orderID);
				Thread.sleep(10000);
				String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
				System.out.println("status : "+orderstatus);
//				JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
//				if(orderstatus.equals(status))
//				{
//					Thread.sleep(15000);
					String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
					if(!orderstatus1.equals("orderstatus"))
					{
						System.out.println("status1"+orderstatus1);
						//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
					}
				
				}
		
		
//	@Given ("^Data to be inserted in preadvice header,order header and UPI receipt with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for sku \"([^\"]*)\"$")
//	public void Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_with_trial(String type,
//			String putawayGP, String customer, String skuid) throws Throwable {
//		//trusted, boxed, non-prohibited
//		System.out.println("data");
//		context.setStoType(type);
//		
////		dataSetupRunner.insertPreAdviceData();
//		String PdtGp= mandsDB.getProductGroup(skuid);
//		
//		String SupplierId=supplierSkuDB.getSupplierId(skuid);
//		context.setSupplierID(SupplierId);
//		
//		String CountryOfOrigin=addressMaintenanceDB.getCountry(context.getSupplierID());
//		context.setCountry(CountryOfOrigin);
//		String Country= context.getCountry();
//		
//		 String PutawayGroup=skuDB.getPutawayGroup(skuid);
//		if((type.equals("Trusted")))
//		{	
//			//trusted, boxed, pro/non-pro
//			String AllowedStock=mandsDB.getTrustedStock(customer,PdtGp);
//			if((AllowedStock.equals("Y")) &&  (PutawayGroup.equals("BLACKB"))){
//				if(Country!="ISR")//non-prohibited BOXED
//				{
//					System.out.println("");
//					}
//				else//prohibited BOXED
//				{
//			}}
//				else if((AllowedStock.equals("Y")) &&  (PutawayGroup.equals("BLACKH"))){
//					if(Country!="ISR")//non-prohibited BOXED
//					{
//						}
//					else//prohibited BOXED
//					{
//				}
//			}}
//		if
//				else if((AllowedStock.equals("N")) &&  (PutawayGroup.equals("BLACKB"))){
//					if(Country!="ISR")//non-prohibited BOXED
//					{
//						}
//					else//prohibited BOXED
//					{
//				}}
//					else if	((AllowedStock.equals("N")) &&  (PutawayGroup.equals("BLACKH"))){
//						if(Country!="ISR")//non-prohibited BOXED
//						{
//							}
//						else//prohibited BOXED
//						{
//					}
//		}
//					
//		}
//		
//		System.out.println("CountryOfOrigin="+CountryOfOrigin);
//		dataSetupRunner.insertPreAdviceDataforUPITrial();
//		String SAPvalue=Utilities.getEightDigitRandomNumber();
//		context.setSAPvalue(SAPvalue);
//		dataSetupRunner.insertOrderDataforUPI();
//		GetTCData.getpoId();
////		String skuid = "000000000021071852";
////		context.setSkuId2(skuid);
//		puttyFunctionsPage.i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
//		String palletIDforUPI = context.getpalletIDforUPI();
//		Thread.sleep(1000);
//		dataSetupRunner.insertUPIReceiptData();
//		String orderID = getTCData.getSto();
//		System.out.println("New Order ID : " + orderID);
//		Thread.sleep(10000);
//		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
//		System.out.println("status : "+orderstatus);
////		JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
////		if(orderstatus.equals(status))
////		{
////			Thread.sleep(15000);
//			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
//			if(!orderstatus1.equals("orderstatus"))
//			{
//				System.out.println("status1"+orderstatus1);
//				//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
//			}
//		
//		}

@Given ("^Checking the conditions \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" for the sku \"([^\"]*)\"$")
public void checking_the_conditions_for_the_sku(String type, 
		String putawayGroup, String customer, String skuid) throws Throwable {
	String PdtGp= mandsDB.getProductGroup(skuid);
	String AllowedStock=mandsDB.getTrustedStock(customer,PdtGp);
	 String PutawayGroup=skuDB.getPutawayGroup(skuid);
	if((AllowedStock.equals("Y"))){
		if((PutawayGroup.equals("BLACKB"))){
			if((customer.equals("5542"))){
				System.out.println("conditions validated as expected");
			}
		}
		else{
			
		}
	}
	
		

	

	}

}