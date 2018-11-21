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

import cucumber.api.java.en.And;

public class InventoryTransactionStepDefs{
	private InventoryTransactionPage inventoryTransactionPage;
	
	@Inject
	public void InventoryTransactionStepDefs(InventoryTransactionPage inventoryTransactionPage){
		this.inventoryTransactionPage=inventoryTransactionPage;
		
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
}