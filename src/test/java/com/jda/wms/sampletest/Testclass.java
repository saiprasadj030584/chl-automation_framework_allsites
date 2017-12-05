package com.jda.wms.sampletest;

import java.io.IOException;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;

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
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;

public class Testclass {

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
	private static Testclass test ;
	static int minqty;

	@Inject
	public Testclass( Context context,LocationDB locationDB,SkuDB skudb,OrderLineDB orderLineDB,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			StockCheckPage stockCheckPage,Testclass test,Hooks hooks,OrderHeaderDB orderHeaderDB,AddressDB addressDB,PuttyFunctionsPage puttyFunctionsPage,PurchaseOrderPickingPage purchaseOrderPickingPage,MoveTaskDB moveTaskDB,GetTcData getTcData,SupplierSkuDB supplierSkuDB,InventoryDB inventoryDB,ProductGroupDB productGroupDB) {
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
		this.test= test;
	}
	


public  void execute_trial() throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException
{
	Thread.sleep(3000);

	context.setOrderId("5771454787");
	purchaseOrderPickingPage.enterListId("HGMS2757473");
	String customer = orderHeaderDB.getCustomer("5771454787");
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
	String QTYtasked =orderLineDB.getQtyTasked(context.getOrderId(), "000000022321243007");
	 minqty=(Integer.parseInt(QTYtasked))/2;
	 int i=0;
	 while(i<10){
	 if (purchaseOrderPickingPage.enterskuidscreenavailable())
	 {
	purchaseOrderPickingPage.enterMinimumQty(String.valueOf(minqty));
	 }
	 else
	 {
		 puttyFunctionsPage.pressEnter();
		 Thread.sleep(1000);
	 
	 }
	 }
	puttyFunctionsPage.pressEnter();
	purchaseOrderPickingPage.selectReason();
	purchaseOrderPickingPage.enterContainerId(String.valueOf(tagid));
	puttyFunctionsPage.pressEnter();
	context.setSkuId(orderLineDB.getsku(context.getOrderId()));
	locationDB.getToLocationForPutaway("HANG",skudb.getProductGroup("000000022321243007"));
	puttyFunctionsPage.pressEnter();
	//Assert.assertTrue("Picking completion is not as expected",     purchaseOrderPickingPage.isPickEntPageDisplayed());
	hooks.logoutPutty();
}

	public static void main(String[] args) throws ClassNotFoundException, FindFailed, InterruptedException, SQLException, IOException {


test.execute_trial();// TODO Auto-generated method stub

	}

}
