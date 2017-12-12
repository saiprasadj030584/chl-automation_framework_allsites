package com.jda.wms.stepdefs.rdt;


import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.ProductGroupDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.rdt.PurchaseOrderPickingPage;

import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.pages.rdt.StockCheckPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StockCheckStepDefs {

	private StockCheckPage stockCheckPage;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private SupplierSkuDB supplierSkuDB;
	private InventoryDB inventoryDB;
	private ProductGroupDB productGroupDB;
	private GetTcData getTcData;
	private MoveTaskDB moveTaskDB;
	private PurchaseOrderPickingPage purchaseOrderPickingPage;
	private PuttyFunctionsPage puttyFunctionsPage;
	private AddressDB addressDB;
	private OrderHeaderDB orderHeaderDB;
	private Hooks hooks;
	private LocationDB locationDB;
	private SkuDB skudb;
	private OrderLineDB orderLineDB;
	
	static int minqty;

	@Inject
	public StockCheckStepDefs( Context context,LocationDB locationDB,SkuDB skudb,OrderLineDB orderLineDB,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			StockCheckPage stockCheckPage,Hooks hooks,OrderHeaderDB orderHeaderDB,AddressDB addressDB,PuttyFunctionsPage puttyFunctionsPage,PurchaseOrderPickingPage purchaseOrderPickingPage,MoveTaskDB moveTaskDB,GetTcData getTcData,SupplierSkuDB supplierSkuDB,InventoryDB inventoryDB,ProductGroupDB productGroupDB) {
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.supplierSkuDB=supplierSkuDB;
		this.inventoryDB=inventoryDB;
		this.productGroupDB=productGroupDB;
		this.stockCheckPage=stockCheckPage;
		this.getTcData=getTcData;
		this.moveTaskDB=moveTaskDB;
		this.purchaseOrderPickingPage=purchaseOrderPickingPage;
		this.puttyFunctionsPage=puttyFunctionsPage;
		this.addressDB=addressDB;
		this.hooks=hooks;
		this.orderHeaderDB=orderHeaderDB;
		this.locationDB=locationDB;
		this.skudb=skudb;
		this.orderLineDB=orderLineDB;
	}
	
	
	@Given ("^I have to datasetup for restrict quantity$")
	public void I_have_to_datasetup_for_restrict_quantity()throws Throwable {
		String productgroup;
		context.setLocationID(inventoryDB.getLocation(context.getTagId()));
		System.out.println("Location ID: "+context.getLocationID());
		context.setSkuId(inventoryDB.getSkuIdFromLocation(context.getLocationID()));
		context.setProductGroup(skudb.getProductGroup(context.getSkuId()));
		context.setUPC(supplierSkuDB.getUPC(context.getSkuId()));
		if(productGroupDB.selectproductgroup())
		 productGroupDB.updatenotes();
		else
		 productGroupDB.insertproductgroup();
	}
	
	
	@Given ("^I have to check restrict quantity$")
	public void I_have_to_check_restrict_quantity() throws Throwable {
		
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		stockCheckPage.selectInventoryMenu();
		stockCheckPage.selectNewStockCheckMenu();
		stockCheckPage.enterLocation(context.getLocationID());
		if(stockCheckPage.existingStockError()){
			stockCheckPage.backmenu();
			stockCheckPage.selectExistingStockCheckMenu();
			stockCheckPage.enterLocation(context.getListID());
		}
		
	//	stockCheckPage.enterCheckString(context.getLocationID());
		
		stockCheckPage.enterQty(context.getUPC());
		if(stockCheckPage.checkStock()){
			System.out.println(" UPC Value was restricted");
		}else{
			Assert.fail("Stock allows UPC");
		}
				
		
	}
	@Given ("^I have to check remove stock check$")
	public void I_have_to_check_remove_stock_check() throws Throwable {
		
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		stockCheckPage.selectInventoryMenu();
		stockCheckPage.selectExistingStockCheckMenu();
		stockCheckPage.enterLocation(context.getListID());
		
		Thread.sleep(1000);
		//stockCheckPage.enterCheckString(context.getLocationID());
		//enter stock check quantity
		stockCheckPage.enterQty(String.valueOf(minqty));
		Thread.sleep(1000);
		puttyFunctionsPage.pressEnter();
			
			
			stockCheckPage.enterQty(String.valueOf(minqty));
			Thread.sleep(1000);
			stockCheckPage.enterQty("N");
			
		}
	
	@Given ("^I have to perform stock check for discrepancy$")
	public void I_have_to_perform_stock_check_for_discrepancy() throws Throwable {
		
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		stockCheckPage.selectInventoryMenu();
		stockCheckPage.selectExistingStockCheckMenu();
		stockCheckPage.enterLocation(context.getListID());
		
		Thread.sleep(1000);
		//if(stockCheckPage.checkCheckString())
		stockCheckPage.enterCheckString(locationDB.getCheckString(context.getFromLocation()));
		
		
		
			int stock_curr =	Integer.parseInt(inventoryDB.getQtynHandwithlocation(context.getTagId(),context.getFromLocation()))- minqty;
			stockCheckPage.enterQty(String.valueOf(stock_curr));
			System.out.println("stock_curr:"+stock_curr);	
		Thread.sleep(1000);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
			
			stockCheckPage.enterQty(String.valueOf(stock_curr));
			Thread.sleep(1000);
			stockCheckPage.enterQty("N");
			hooks.logoutPutty();
		}
				
		
	
	
	@Then("^The inventory should be in unlocked status$")
	public void the_inventory_should_be_in_unlocked_status() throws Throwable {
		if(inventoryDB.getLocationstatus(context.getTagId(),context.getFromLocation()).contains("UnLocked"))
		{
			System.out.println("Location "+context.getFromLocation()+" is in unlocked status");
		}
		else
		{
			Assert.fail("Location is in locked status");}
	}
	@Then("^The inventory should be in locked status$")
	public void the_inventory_should_be_in_locked_status() throws Throwable {
		if(inventoryDB.getLocationstatus(context.getTagId(),context.getFromLocation()).contains("Locked"))
		{
			System.out.println("Location "+context.getFromLocation()+" inventory is in locked status");
		}
		else
		{
			Assert.fail("Location is in unlocked status after picking discrepencies");}
	}
	
	
	@Given("^I perform picking for hanging discrepancy for stock check$")
	public void i_perform_picking_for_hanging_discrepancy_for_stock_check() throws Throwable {
	context.setListID(moveTaskDB.getListID(context.getOrderId()));
	
	purchaseOrderPickingPage.enterListId(context.getListID());
	String customer = orderHeaderDB.getCustomer(context.getOrderId());
	context.setCustomer(customer);
	String tagValueL = addressDB.getLowerTagValue();
	String tagValueH = addressDB.getHigherTagValue();
	int tagid = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
	+ Integer.parseInt(tagValueL);
	
	context.setTagId(String.valueOf(tagid));
	Thread.sleep(1000);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(3000);
			System.out.println("tag value is : "+ tagid);
	
	purchaseOrderPickingPage.enterPicToTagId(context.getTagId());
	Thread.sleep(1000);
		
	puttyFunctionsPage.pressEnter();
	//purchaseOrderPickingPage.getQuantity();
	String QTYtasked =moveTaskDB.getQtyTasked(context.getOrderId(), context.getSkuId(), context.getListID()) ;
	if(Integer.parseInt(QTYtasked)>1)
	 minqty=(Integer.parseInt(QTYtasked))/2;
	
	 int i=0;
	 while(i<10){
		 
		 if (purchaseOrderPickingPage.enterskuidscreenavailable())
		 {
		purchaseOrderPickingPage.enterMinimumQty(String.valueOf(minqty));
		break;
		 }
		 else
		 {
			 puttyFunctionsPage.pressEnter();
			 Thread.sleep(1000);
		 
		 }
		 i++;
		 }
	
	puttyFunctionsPage.pressEnter();
	purchaseOrderPickingPage.selectReason();
	purchaseOrderPickingPage.enterContainerId(String.valueOf(tagid));
	puttyFunctionsPage.pressEnter();
	context.setSkuId(orderLineDB.getsku(context.getOrderId()));
	locationDB.getToLocationForPutaway("HANG",skudb.getProductGroup(context.getSkuId()));
	puttyFunctionsPage.pressEnter();
	//Assert.assertTrue("Picking completion is not as expected",     purchaseOrderPickingPage.isPickEntPageDisplayed());
	hooks.logoutPutty();
	}
}
