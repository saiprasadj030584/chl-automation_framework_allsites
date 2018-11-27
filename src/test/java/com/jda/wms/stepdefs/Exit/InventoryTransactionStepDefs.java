package com.jda.wms.stepdefs.Exit;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.pages.Exit.InventoryTransactionPage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.PreAdviceHeaderPage;
import com.jda.wms.pages.Exit.SupplierSKUMaintenancePage;

import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.MandsDB;
import com.jda.wms.db.Exit.SupplierSkuDB;

import cucumber.api.java.en.And;

public class InventoryTransactionStepDefs{
	private InventoryTransactionPage inventoryTransactionPage;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private Context context;
	private SkuDB skuDB;
	private SupplierSkuDB supplierSkuDB;
	private SupplierSKUMaintenancePage SupplierSKUMaintenancePage;
	private MandsDB mandsDB;
	@Inject
	public void InventoryTransactionStepDefs(SkuDB skuDB,MandsDB mandsDB,InventoryTransactionPage inventoryTransactionPage,
			JdaHomePage jdaHomePage,SupplierSKUMaintenancePage SupplierSKUMaintenancePage,PreAdviceHeaderPage preAdviceHeaderPage,SupplierSkuDB supplierSkuDB,Context context){
		this.inventoryTransactionPage=inventoryTransactionPage;
		this.jdaHomePage=jdaHomePage;
		this.preAdviceHeaderPage=preAdviceHeaderPage;
		this.context=context;
		this.skuDB=skuDB;
		this.supplierSkuDB=supplierSkuDB;
		this.SupplierSKUMaintenancePage=SupplierSKUMaintenancePage;
		this.mandsDB=mandsDB;
	}
	@And("^Enter Container_ID$")
	public void enter_container_id() throws FindFailed
	{
		inventoryTransactionPage.EnterContanierID();
		
	}
	@And("^check the Inventory Transaction for Receipt, Allocate and Pick$")
	public void check_the_inventory_transaction_for_receipt_allocate_pick() throws FindFailed, InterruptedException
	{
		inventoryTransactionPage.Checktransaction();
		
	}
	@And("^check Qty received is updated in Inventory$")
	public void check_qty_received_is_updated_in_inventory() throws FindFailed, InterruptedException
	{
		inventoryTransactionPage.CheckQtyreceived();
	}
	
	@And("^Check Qty received is updated in Pre-advice line$")
	public void Qty_received_is_updated_in_pre_advice_line() throws FindFailed, InterruptedException
	{
		jdaHomePage.navigateToPreAdviceLinePage(); 
		  preAdviceHeaderPage.Enterpreadvice();
		  preAdviceHeaderPage.quantity_validation();
	}
	@And("^Check the Orderline must be allocated$")
	public void check_the_orderline_must_be_allocated() throws FindFailed, ClassNotFoundException, InterruptedException, SQLException
	{
		inventoryTransactionPage.getstatus();
	}
	@And("^check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code$")
	public void check_the_inventory_transaction_for_receipt_inventorylock_putaway_for_red_lock_code() throws FindFailed, InterruptedException, ClassNotFoundException, SQLException
	{
		String sku= context.getSkuId2();
		String UserDEFChk3=SkuDB.getUserDefChck3(sku);
		Assert.assertEquals("User defined check 3 is not Y", "Y", UserDEFChk3);
		jdaHomePage.navigateToInventory();
		inventoryTransactionPage.click_on_Query();		
		inventoryTransactionPage.EnterContanierID();
		inventoryTransactionPage.clickExecuteButton();
		Thread.sleep(2000);
		String RC=inventoryTransactionPage.checkReasoncode();
		Assert.assertEquals("Reason code not as expected", "RED", RC);
		Thread.sleep(2000);
		inventoryTransactionPage.ChecktransactionForRedStock();
			
	}
	
	@And("^Supplier Declaration is validated to be null or in past$")
	public void supplier_declarartion_is_validated_to_be_null_or_in_past() throws Throwable,FindFailed{
		String supplierdeclaration= supplierSkuDB.getDeclarationValidity();
		Assert.assertNotNull("SD not null", supplierdeclaration);
	}
	
	@And("^commodity Code is validated as NULL$")
	public void commodity_code_is_validated_as_null() throws Throwable,FindFailed{
		String Sku= context.getSkuId2();
		String CommodityCode= mandsDB.getUserDefType9(Sku);
		Assert.assertNotSame("commodity code is not null", CommodityCode,"null");
	}
	
	@And("^stroke category is validated as NULL$")
	public void stroke_category_is_validated_as_null() throws Throwable,FindFailed{
		String Sku= context.getSkuId2();
		String StrokeCategory= mandsDB.getUserDefType11(Sku);
		Assert.assertNotSame("stroke category is not null", StrokeCategory,"null");
	}
	
	@And("^supplier record does not exist$")
	public void supplier_recod_does_not_exist() throws Throwable,FindFailed{
		jdaHomePage.navigateToSupplierSKUHome();
		inventoryTransactionPage.click_on_Query();		
		inventoryTransactionPage.enterSku();
		inventoryTransactionPage.clickExecuteButton();
		Assert.assertTrue("record exists", SupplierSKUMaintenancePage.isRecordExists());
		
	}
	
	
	
}