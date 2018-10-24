package com.jda.wms.stepdefs.Exit;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
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
import com.jda.wms.pages.Exit.PurchaseOrderReceivingPage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;


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
	private MoveTaskUpdateStepDefs moveTaskUpdateStepDefs;
	private MoveTaskDB  moveTaskDB;
	private MoveTaskUpdatePage moveTaskUpdatePage;
	private MoveTaskListGenerationPage moveTaskListGenerationPage;
	private JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;
	private JDAExitLoginStepDefs JDAExitLoginStepDefs;
	private MoveTaskManagementPage moveTaskManagementPage;
	private String poId;
	private OrderHeaderPage orderheaderpage;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private SkuDB skuDB;
	private SupplierSkuDB supplierSkuDB;
	private PuttyFunctionsPage puttyFunctionsPage;
	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			SupplierSkuDB supplierSkuDB,PuttyFunctionsPage puttyFunctionsPage,
			SkuDB skuDB,PurchaseOrderReceivingPage purchaseOrderReceivingPage,OrderHeaderPage orderheaderpage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage, Verification verification, OrderHeaderDB orderHeaderDB,
			AddressDB addressDB, Hooks hooks, InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, OrderHeaderContext orderHeaderContext,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs,
			OrderPreparationStepDefs orderPreparationStepDefs, DataSetupRunner dataSetupRunner, GetTCData getTCData,
			UpdateDataFromDB updateDataFromDB, JDALoginStepDefs jdaLoginStepDefs,MoveTaskUpdateStepDefs moveTaskUpdateStepDefs,
			MoveTaskDB  moveTaskDB,MoveTaskUpdatePage moveTaskUpdatePage,
			MoveTaskListGenerationPage moveTaskListGenerationPage,JdaHomePage jdaHomePage,
			InventoryDB inventoryDB,JDAExitLoginStepDefs JdaExitLoginPage,MoveTaskManagementPage moveTaskManagementPage) {
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
		this.moveTaskUpdateStepDefs=moveTaskUpdateStepDefs;
//		this.puttyFunctionsStepDefs =puttyFunctionsStepDefs;
		this.moveTaskUpdatePage=moveTaskUpdatePage;
		this.moveTaskListGenerationPage=moveTaskListGenerationPage;
		this.jdaHomePage=jdaHomePage;
		this.inventoryDB=inventoryDB;
		this.JDAExitLoginStepDefs= JdaExitLoginPage;
		this.moveTaskManagementPage=moveTaskManagementPage;
		this.orderheaderpage=orderheaderpage;
		this.skuDB=skuDB;
		this.supplierSkuDB=supplierSkuDB;
	}
	@Given ("^Data to be inserted in preadvice header and order header with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Data_to_be_inserted_in_preadvice_header_and_order_header_with(String status,
			String type, String customer) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertOrderData2();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status : "+orderstatus);			
	}
	@Given ("^I navigate to Order header screen to verify the status in Ready to Load$")
	public void I_navigate_to_Order_header_screen_to_verify_the_status_in_Ready_to_Load() throws Throwable{
		JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
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
		String skuid = "000000000021071852";
		Thread.sleep(1000);
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		System.out.println("UPCValue= "+UPCValue);
		String UPCDB=SkuDB.getUPCDB();//from DB
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
	
	
	
	}