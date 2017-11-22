package com.jda.wms.stepdefs.rdt;


import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.ProductGroupDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.pages.rdt.PurchaseOrderPickingPage;

import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.pages.rdt.StockCheckPage;

import cucumber.api.java.en.Given;

public class StockCheckStepDefs {

	private StockCheckPage stockCheckPage;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private SupplierSkuDB supplierSkuDB;
	private InventoryDB inventoryDB;
	private ProductGroupDB productGroupDB;
	private GetTcData getTcData;

	@Inject
	public StockCheckStepDefs( Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			StockCheckPage stockCheckPage,GetTcData getTcData,SupplierSkuDB supplierSkuDB,InventoryDB inventoryDB,ProductGroupDB productGroupDB) {
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.supplierSkuDB=supplierSkuDB;
		this.inventoryDB=inventoryDB;
		this.productGroupDB=productGroupDB;
		this.stockCheckPage=stockCheckPage;
		this.getTcData=getTcData;
	}
	
	
	@Given ("^I have to datasetup for restrict quantity$")
	public void I_have_to_datasetup_for_restrict_quantity()throws Throwable {
		
		
		System.out.println("Location ID: "+context.getLocationID());
		context.setSkuId(inventoryDB.getSkuIdFromLocation(context.getLocationID()));
		
		context.setUPC(supplierSkuDB.getUPC(context.getSkuId()));
		 productGroupDB.updatenotes(context.getSkuId());
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
		
		stockCheckPage.enterCheckString(context.getLocationID());
		
		stockCheckPage.enterQty(context.getUPC());
		if(stockCheckPage.checkStock()){
			System.out.println(" UPC Value was restricted");
		}else{
			Assert.fail("Stock allows UPC");
		}
				
		
	}

}
