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
import com.jda.wms.stepdefs.Exit.InventoryTransactionStepDefs;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.MandsDB;
import com.jda.wms.db.Exit.SupplierSkuDB;
import com.jda.wms.stepdefs.Exit.SKUQueryStepDefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

@SuppressWarnings("unused")
public class InventoryTransactionStepDefs{
	private InventoryTransactionPage inventoryTransactionPage;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private Context context;
	private SkuDB skuDB;
	private SupplierSkuDB supplierSkuDB;
	private SupplierSKUMaintenancePage SupplierSKUMaintenancePage;
	private MandsDB mandsDB;
	private InventoryTransactionStepDefs inventoryTransactionStepDefs;
	private SKUQueryStepDefs sKUQueryStepDefs;
	@Inject
	public void InventoryTransactionStepDefs(SkuDB skuDB,MandsDB mandsDB,InventoryTransactionPage inventoryTransactionPage,
			JdaHomePage jdaHomePage,InventoryTransactionStepDefs inventoryTransactionStepDefs,SKUQueryStepDefs sKUQueryStepDefs,SupplierSKUMaintenancePage SupplierSKUMaintenancePage,PreAdviceHeaderPage preAdviceHeaderPage,SupplierSkuDB supplierSkuDB,Context context){
		this.inventoryTransactionPage=inventoryTransactionPage;
		this.jdaHomePage=jdaHomePage;
		this.preAdviceHeaderPage=preAdviceHeaderPage;
		this.context=context;
		this.skuDB=skuDB;
		this.supplierSkuDB=supplierSkuDB;
		this.SupplierSKUMaintenancePage=SupplierSKUMaintenancePage;
		this.mandsDB=mandsDB;
		this.inventoryTransactionStepDefs=inventoryTransactionStepDefs;
		this.sKUQueryStepDefs=sKUQueryStepDefs;
	}
	@And("^Enter Container_ID$")
	public void enter_container_id() throws FindFailed
	{
		inventoryTransactionPage.EnterContanierID();
		
	}
	@And("^Enter Container_ID for FSV$")
	public void enter_container_id_for_FSV() throws FindFailed
	{
		inventoryTransactionPage.EnterContanierIDFSV();
		
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
	public void check_the_inventory_transaction_for_receipt_inventorylock_putaway_for_red_lock_code() throws Throwable
	{
		String sku= context.getSKUHang();
		
		jdaHomePage.navigateToInventory();
		inventoryTransactionPage.click_on_Query();		
		inventoryTransactionPage.EnterContanierID();
		inventoryTransactionPage.clickExecuteButton();
		Thread.sleep(2000);
		String RC=inventoryTransactionPage.checkReasoncode();
		Assert.assertEquals("Reason code not as expected", "RED", RC);
		Thread.sleep(2000);
		inventoryTransactionPage.ChecktransactionForRedStock();
		sKUQueryStepDefs.check_weight_is_validated_as_null();
		
		String UserDEFChk3=SkuDB.getUserDefChck3(sku);
		Assert.assertEquals("User defined check 3 is not Y", "Y", UserDEFChk3);
	}
	
	@And("^check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code for prohibition$")
	public void check_the_inventory_transaction_for_receipt_inventorylock_putaway_for_red_lock_code_for_prohibition() throws Throwable
	{
		String sku= context.getSKUHang();
		
		jdaHomePage.navigateToInventory();
		inventoryTransactionPage.click_on_Query();		
		inventoryTransactionPage.EnterContanierID();
		inventoryTransactionPage.clickExecuteButton();
		Thread.sleep(2000);
		String RC=inventoryTransactionPage.checkReasoncode();
		Assert.assertEquals("Reason code not as expected", "RED", RC);
		Thread.sleep(2000);
		inventoryTransactionPage.ChecktransactionForRedStock();

		
		String UserDEFChk3=SkuDB.getUserDefChck3(sku);
		Assert.assertEquals("User defined check 3 is not Y", "Y", UserDEFChk3);
	}
	
	@And("^Supplier Declaration is validated to be null or in past$")
	public void supplier_declarartion_is_validated_to_be_null_or_in_past() throws Throwable,FindFailed{
		String sku=context.getSkuId2();
		String supplierdeclaration= supplierSkuDB.getDeclarationValidity();
		Assert.assertNotNull("SD not null", supplierdeclaration);
		String supplierID= context.getSupplierID();
		System.out.println(supplierID);
		String OriginalSupplierID=supplierSkuDB.UpdateOriginal(supplierID,sku);
		
	}
	
	@Then("^Alter the commodity Code to make the stock as RED stock$")
	public void alter_the_commodity_code_to_make_the_stock_as_red_stock() throws Throwable,FindFailed{
		String Sku= context.getSKUHang();
		String CommodityCode= mandsDB.getUserDefType9(Sku);
		System.out.println("commo="+CommodityCode);
		context.setCommodityCd(CommodityCode);
		String UpdateCommodityCode=mandsDB.updateCommodityCode(Sku);
	}
	
	@And("^commodity Code is validated as NULL$")
	public void commodity_code_is_validated_as_null() throws Throwable,FindFailed{
		String Sku= context.getSKUHang();
		String CommodityCode= mandsDB.getUserDefType9(Sku);
		Assert.assertNull("commodity code is not null", CommodityCode);
		String OriginalCommodity=mandsDB.UpdateToOriginalCommodity(context.getCommodityCd(),Sku);
	}
	
	
	@Then("^Alter the stroke category to make the stock as RED stock$")
	public void alter_the_stroke_category_to_make_the_stock_as_red_stock() throws Throwable,FindFailed{
		String Sku= context.getSkuId2();
		String StrokeCategory= mandsDB.getUserDefType11(Sku);
		context.setStrokeCt(StrokeCategory);
		String UpdateStroke=mandsDB.updateStroke(Sku);
	}
	@And("^stroke category is validated as NULL$")
	public void stroke_category_is_validated_as_null() throws Throwable,FindFailed{
		String Sku= context.getSkuId2();
		String StrokeCategory= mandsDB.getUserDefType11(Sku);
		Assert.assertNull("stroke category is not null", StrokeCategory);
		String OriginalSroke=mandsDB.UpdateToOriginalStroke(context.getStrokeCt(),Sku);
	}
	
	@And("^supplier record does not exist$")
	public void supplier_recod_does_not_exist() throws Throwable,FindFailed{
		jdaHomePage.navigateToSupplierSKUHome();
		inventoryTransactionPage.click_on_Query();		
		inventoryTransactionPage.enterSku();
		inventoryTransactionPage.clickExecuteButton();
		Assert.assertTrue("record exists", SupplierSKUMaintenancePage.isRecordExists());
		
	}
	
	@And("^Take a sku having stock in BLACK area$")
	public void take_a_sku_having_stock_in_black_area() throws Throwable{
		
	}
	
	@And("^Verified in Inventory and ITL$")
	public void verified_in_inventory_and_ITL() throws Throwable{
		
	}
	@Then("^Stock is validated successfully$")
	public void stock_validated() throws Throwable{
		
	}
	
	
	
}