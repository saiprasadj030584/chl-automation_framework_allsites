package com.jda.wms.stepdefs.Exit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.SupplierSkuDB;

import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskManagementPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.OrderHeaderPage;
import com.jda.wms.pages.Exit.PreAdviceHeaderPage;
import com.jda.wms.pages.Exit.PurchaseOrderReceivingPage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import edu.emory.mathcs.backport.java.util.Arrays;


public class JDAExitPreAdviceHeader{
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
	private String poId;
	private OrderHeaderPage orderheaderpage;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private SkuDB skuDB;
	private SupplierSkuDB supplierSkuDB;
	private PuttyFunctionsPage puttyFunctionsPage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	
	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,PreAdviceHeaderPage preAdviceHeaderPage,
			SupplierSkuDB supplierSkuDB,PuttyFunctionsPage puttyFunctionsPage,
			SkuDB skuDB,PurchaseOrderReceivingPage purchaseOrderReceivingPage,OrderHeaderPage orderheaderpage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage, Verification verification, OrderHeaderDB orderHeaderDB,
			AddressDB addressDB, Hooks hooks, InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, OrderHeaderContext orderHeaderContext,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs,
			OrderPreparationStepDefs orderPreparationStepDefs, DataSetupRunner dataSetupRunner, GetTCData getTCData,
			UpdateDataFromDB updateDataFromDB, JDALoginStepDefs jdaLoginStepDefs,
			MoveTaskDB  moveTaskDB,MoveTaskUpdatePage moveTaskUpdatePage,
			MoveTaskListGenerationPage moveTaskListGenerationPage,JdaHomePage jdaHomePage,
			InventoryDB inventoryDB,MoveTaskManagementPage moveTaskManagementPage,PreAdviceHeaderDB preAdviceHeaderDB) {
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
		this.orderheaderpage=orderheaderpage;
		this.skuDB=skuDB;
		this.supplierSkuDB=supplierSkuDB;
		this.preAdviceHeaderPage=preAdviceHeaderPage;
		this.preAdviceHeaderDB=preAdviceHeaderDB;
	}
	@Then("^Verify PO type in Pre Advice header screen$")
	  public void verify_PO_type_in_pre_advice_header_screen() throws ClassNotFoundException, SQLException, InterruptedException, FindFailed
	  {
		  jdaHomePage.navigateToPreAdviceHeaderPage(); 
		  preAdviceHeaderPage.Enterpreadvice();
		  preAdviceHeaderPage.isTypeExist();
		 		  
	  }
	@Then("^Verify PreAdvice header loaded successfully$")
	public void verify_preadvice_header_loaded_successfully() throws FindFailed, InterruptedException
	{
		  jdaHomePage.navigateToPreAdviceHeaderPage(); 
		  preAdviceHeaderPage.Enterpreadvice();
		  preAdviceHeaderPage.getStatus();
		  Thread.sleep(2000);
//		  jdaHomePage.navigateToPreAdviceLinePage();
		 // preAdviceHeaderPage.validation_of_PreAdviceLine();  
	}
	@Then("^Verify PreAdvice header loaded successfully for Two PO$")
	public void verify_preadvice_header_loaded_successfully_for_two_PO() throws FindFailed, InterruptedException
	{
		  jdaHomePage.navigateToPreAdviceHeaderPage(); 
		  preAdviceHeaderPage.Enterpreadvice();
		  preAdviceHeaderPage.getStatus();
		  Thread.sleep(2000);
		  preAdviceHeaderPage.Enterpreadvice2();
		  preAdviceHeaderPage.getStatus();
		  Thread.sleep(2000);
//		  jdaHomePage.navigateToPreAdviceLinePage();
		 // preAdviceHeaderPage.validation_of_PreAdviceLine();  
	}
	@Then("^Verify the status of the PO$")
	public void verify_the_status_of_the_PO() throws FindFailed, InterruptedException
	{
		  preAdviceHeaderPage.validation_of_Status();
	}
	@Then("^Verify Supplier is populated in the Pre-advice header table$")
	public void verify_supplier_is_populated_in_the_pre_advice_table()throws FindFailed, InterruptedException
	{
		 preAdviceHeaderPage.validation_of_supplier();
		 Thread.sleep(3000);
		
	}
	@Then("^Verify the Type  is populated as \"([^\"]*)\"$")
	public void verify_the_Type_is_populated_as(String type) throws Throwable {
		String oderType= preAdviceHeaderPage.getOrderType();
		Assert.assertEquals(type, oderType);
		 Thread.sleep(3000);  
	}
	@Then("^Verify PreAdvice line loaded successfully$")
	public void verify_preadvice_line_loaded_successfully() throws FindFailed, InterruptedException
	{
		  preAdviceHeaderPage.clickPreadviceLine();
		  preAdviceHeaderPage.validation_of_PreAdviceLine();  
	}
	@Then("^Verify quantity and advice number is loaded in Pre-Advice line table$")
	public void verify_quantity_to_be_received_is_loaded() throws FindFailed, InterruptedException{
		 preAdviceHeaderPage.validation_of_quantity();
		 preAdviceHeaderPage.validation_of_advice();
		 
		 
	}
	
	@Given ("^Data to be inserted in preadvice header and order header with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for \"([^\"]*)\"$")
	public void Data_to_be_inserted_in_preadvice_header_and_order_header_with(String status,
			String type, String customer, String sku) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
		context.setSKUHang(sku);
		String UPC=SkuDB.getUPCDB(sku);
		context.setupc(UPC);
		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertOrderData2();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(orderID);
		System.out.println("status : "+orderstatus);			
	}
	
	@Given ("^Data to be inserted twice in preadvice header and one in order header with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for \"([^\"]*)\"$")
	public void Data_to_be_inserted_twice_in_preadvice_header_and_one_in_order_header_with(String status,
			String type, String customer, String sku) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
		context.setSKUHang(sku);
		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertPreAdviceData2();
		dataSetupRunner.insertOrderData2();
		dataSetupRunner.insertOrderData1();
		String orderID = getTCData.getSto();
		String orderID2= getTCData.getSto2();
		System.out.println("New Order ID : " + orderID);
		System.out.println("New Order ID : " + orderID2);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		String orderstatus2=orderHeaderDB.getStatus(context.getOrderId2());
		System.out.println("status : "+orderstatus);
		System.out.println("status : "+orderstatus2);
	}
	
	@Given ("^I navigate to Order header screen to verify the status in Ready to Load$")
	public void I_navigate_to_Order_header_screen_to_verify_the_status_in_Ready_to_Load() throws Throwable{
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		Thread.sleep(3000);
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		jdaHomePage.navigateToOrderheaderPage();
		Thread.sleep(3000);
		jdaFooter.clickQueryButton();
		orderheaderpage.enterOrderNo(context.getOrderId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		Assert.assertEquals("Order Status", "Ready to Load", orderstatus);
	}
	
	@Given ("^I navigate to Order header screen to verify the status in Released$")
	public void I_navigate_to_Order_header_screen_to_verify_the_status_in_Released() throws Throwable{
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		Thread.sleep(6000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		Assert.assertEquals("Order Status", "Released", orderstatus);
	}
	@And ("^Validation of List Id generated with prefix as FSVB$")
	public void Validation_of_List_Id_generated_with_prefix_as_FSVB()throws Throwable{
			
			moveTaskManagementPage.validateListIDforIDT();
			
			//DB validation
			String actuallist = moveTaskDB.getListID(context.getOrderId());
			String prefixlist=StringUtils.substring(actuallist, 0, 4);
			Assert.assertEquals("List Id generated with prefix as FSVB", "FSVB", prefixlist);
			logger.debug("List Id generated with prefix as FSVB is : " + actuallist);
			System.out.println("List Id generated with prefix as FSVB is : " + actuallist);
		}
	
	@And ("^Validation of UPC,Qty details and Supplier$")
	public void Validation_of_UPC_Qty_details_and_Supplier() throws Throwable {
		String preAdviceID=GetTCData.getpoId();
		String skuid = context.getSKUHang();

		Thread.sleep(1000);
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		System.out.println("UPCValue= "+UPCValue);
		String UPCDB=SkuDB.getUPCDB(skuid);//from DB
		System.out.println("UPCDB= "+UPCDB);
		Assert.assertEquals("UPC validated", UPCDB, UPCValue);
		logger.debug("Validated UPC value : " + UPCValue);
		Thread.sleep(1000);
		String QTYValue=purchaseOrderReceivingPage.getQTY();//from screen
		System.out.println("QTYValue= "+QTYValue);
		String QTYDB=skuDB.getQTYDB(preAdviceID,skuid);//from DB
		System.out.println("QTYDB= "+QTYDB);
		Assert.assertEquals("UPC validated", QTYDB, QTYValue);
		logger.debug("Validated QTY value : " + QTYValue);
		Thread.sleep(1000);
		String SupplierValue=purchaseOrderReceivingPage.getSupplier();//from screen
		System.out.println("SupplierValue= "+SupplierValue);
		String SupplierDB = supplierSkuDB.getSupplierId(skuid);//from DB
		System.out.println("SupplierDB= "+SupplierDB);
		Assert.assertEquals("UPC validated", SupplierDB, SupplierValue);
		logger.debug("Validated Supplier Value: " + SupplierValue);
		Thread.sleep(1000);
		
	}
	@Given("^Insert Pre-advice data with PO type \"([^\"]*)\"$")
	public void insert_pre_advice(String POtype) throws ClassNotFoundException, SQLException, InterruptedException
	{
		context.setPOType(POtype);
		dataSetupRunner.insertPreAdviceData1(POtype);
		
		
	}
	
	
	
	}