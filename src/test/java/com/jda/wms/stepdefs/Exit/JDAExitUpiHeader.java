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
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskManagementPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;


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
	private MoveTaskUpdateStepDefs moveTaskUpdateStepDefs;
//	private PuttyFunctionsStepDefs  puttyFunctionsStepDefs;
//	private StoreTrackingOrderPickingStepDefs storeTrackingOrderPickingStepDefs;
//	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private MoveTaskDB  moveTaskDB;
	private MoveTaskUpdatePage moveTaskUpdatePage;
	private MoveTaskListGenerationPage moveTaskListGenerationPage;
	private JdaHomePage jdaHomePage;
	private InventoryDB inventoryDB;
	private JDAExitLoginStepDefs JDAExitLoginStepDefs;
	private MoveTaskManagementPage moveTaskManagementPage;
	private JDAExitputtyfunctionsStepDef jDAExitputtyfunctionsStepDef;
	
	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,JDAExitputtyfunctionsStepDef jDAExitputtyfunctionsStepDef,
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
		this.jDAExitputtyfunctionsStepDef=jDAExitputtyfunctionsStepDef;
	}
	@Given ("^Data to be inserted in preadvice header,order header and UPI receipt with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Data_to_be_inserted_in_preadvice_header_order_header_and_UPI_Receipt_with(String status,
			String type, String customer) throws Throwable {
		System.out.println("data");
		context.setStoType(type);
		context.setCustomer(customer);
//		dataSetupRunner.insertPreAdviceData();
		dataSetupRunner.insertPreAdviceData();
		String SAPvalue=Utilities.getEightDigitRandomNumber();
		context.setSAPvalue(SAPvalue);
		dataSetupRunner.insertOrderData2();
		GetTCData.getpoId();
		String skuid = "000000000021071852";
		jDAExitputtyfunctionsStepDef.i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
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
	
	@Given ("^Navigate to Move Task management Screen to verify Order Allocated status for ASN Crossdock$")
	public void Navigate_to_Move_Task_management_Screen_to_verify_Order_Allocated_status_for_ASN_Crossdock() throws Throwable{
		JDAExitLoginStepDefs.Logging_in_as_warehouse_user_in_Exit_application();
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
	
	}