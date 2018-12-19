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
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskManagementPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;


public class JDAExitorderHeaderDB{
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
//	private PuttyFunctionsStepDefs  puttyFunctionsStepDefs;
//	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;
//	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private MoveTaskDB  moveTaskDB;
	private MoveTaskUpdatePage moveTaskUpdatePage;
	private MoveTaskListGenerationPage moveTaskListGenerationPage;
	private JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;
	private MoveTaskManagementPage moveTaskManagementPage;
	
	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage, Verification verification, OrderHeaderDB orderHeaderDB,
			AddressDB addressDB, Hooks hooks, InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB,
			SelectDataFromDB selectDataFromDB, OrderHeaderContext orderHeaderContext,
			OrderLineMaintenanceStepDefs orderLineMaintenanceStepDefs,
			OrderPreparationStepDefs orderPreparationStepDefs, DataSetupRunner dataSetupRunner, GetTCData getTCData,
			UpdateDataFromDB updateDataFromDB, JDALoginStepDefs jdaLoginStepDefs,
			MoveTaskDB  moveTaskDB,MoveTaskUpdatePage moveTaskUpdatePage,
			MoveTaskListGenerationPage moveTaskListGenerationPage,JdaHomePage jdaHomePage,
			InventoryDB inventoryDB,MoveTaskManagementPage moveTaskManagementPage) {
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
	}
	
	@Given ("^Order Status should be \"([^\"]*)\", Type should be \"([^\"]*)\", Customer should be \"([^\"]*)\" for SKU \"([^\"]*)\"$")
	public void Order_Status_should_be_Type_should_be_Customer_should_be(String status,
			String type,String customer,String SKU) throws Throwable {
		context.setStoType(type);
		context.setCustomer(customer);
		context.setSKUHang(SKU);
		String UPC=SkuDB.getUPCDB(SKU);
		context.setupc(UPC);
		dataSetupRunner.insertOrderData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(10000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status : "+orderstatus);
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		if(orderstatus.equals(status))
		{
			Thread.sleep(15000);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status1"+orderstatus1);
				//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
			}
		
		}
		
	}
	
	
	@Given ("^Order Status should be \"([^\"]*)\", Type should be \"([^\"]*)\", Customer should be \"([^\"]*)\" for IDT \"([^\"]*)\"$")
	public void Order_Status_should_be_Type_should_be_Customer_should_be_for_IDT(String status,
			String type, String customer, String sku) throws Throwable {
		context.setStoType(type);
		context.setCustomer(customer);
		context.setSKUHang(sku);
		String UPC=SkuDB.getUPCDB(sku);
		context.setupc(UPC);
		dataSetupRunner.insertOrderData();
		String orderID = getTCData.getSto();
		System.out.println("New Order ID : " + orderID);
		Thread.sleep(3000);
		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
		System.out.println("status : "+orderstatus);
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		if(orderstatus.equals(status))
		{
			Thread.sleep(1500);
			String orderstatus1=orderHeaderDB.getStatus(context.getOrderId());
			if(!orderstatus1.equals("orderstatus"))
			{
				System.out.println("status1"+orderstatus1);
				//systemAllocationStepDefs.i_system_allocate_the_order(); // should be confirmed as it was manual franchise to be manually allocated
			}
		
		}
	
	}
	
	
	
	
	}
		
	

