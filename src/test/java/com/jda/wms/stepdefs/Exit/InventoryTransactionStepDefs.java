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

import cucumber.api.java.en.And;

public class InventoryTransactionStepDefs{
	private InventoryTransactionPage inventoryTransactionPage;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	
	@Inject
	public void InventoryTransactionStepDefs(InventoryTransactionPage inventoryTransactionPage,
			JdaHomePage jdaHomePage,PreAdviceHeaderPage preAdviceHeaderPage){
		this.inventoryTransactionPage=inventoryTransactionPage;
		this.jdaHomePage=jdaHomePage;
		this.preAdviceHeaderPage=preAdviceHeaderPage;
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
	@And("^check the Inventory Transaction for Receipt, Allocate and Pick for the Red lock code$")
	public void check_the_inventory_transaction_for_receipt_allocate_pick_for_red_lock_code() throws FindFailed, InterruptedException
	{
		jdaHomePage.navigateToInventory();
		inventoryTransactionPage.click_on_Query();		
		inventoryTransactionPage.enterSku();
		inventoryTransactionPage.clickExecuteButton();
		inventoryTransactionPage.Checktransaction();
		String LC=inventoryTransactionPage.checkLockcode();
		Assert.assertEquals("Lock code not as expected", "RED", LC);
		
		
	}
	
}