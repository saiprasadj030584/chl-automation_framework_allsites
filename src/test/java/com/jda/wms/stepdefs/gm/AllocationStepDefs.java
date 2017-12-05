package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.pages.gm.AllocationPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class AllocationStepDefs {
	private AllocationPage allocationPage;
	private OrderHeaderDB orderHeaderDB;
	private OrderLineDB orderLineDB;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private JDAFooter jDAFooter;
	private JdaHomePage jdaHomePage;
	private MoveTaskDB movetask;
	private LocationDB locationDB;

	@Inject
	public AllocationStepDefs(JDAFooter jDAFooter, AllocationPage allocationPage, OrderHeaderDB orderHeaderDB,
			Verification verification,MoveTaskDB movetask,LocationDB locationDB, OrderLineDB orderLineDB, Context context, InventoryDB inventoryDB) {
		this.jDAFooter = jDAFooter;
		this.allocationPage = allocationPage;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
		this.orderLineDB = orderLineDB;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.movetask= movetask;
		this.locationDB=locationDB;
	}


	
	@When("^I enter OrderID for clustering$")
	public void i_enter_OrderID_for_clustering() throws Throwable {
		String orderid = context.getOrderId();
		jDAFooter.clickNextButton();
		allocationPage.enterOrderId(orderid);
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		allocationPage.clickClusteringCheckBox();
		Thread.sleep(2000);
		jDAFooter.clickDoneButton();
		Thread.sleep(9000);
		jDAFooter.clickDoneButton();
	}
	@When("^I validate the aisle for the sku$")
	public void i_validate_the_aisle_for_the_sku() throws Throwable {
		ArrayList<String> Orderlist=context.getOdnList();
		for (int i=0;i<Orderlist.size();i++){
		context.setOrderId(Orderlist.get(i));
		if (orderHeaderDB.getStatus(context.getOrderId()).contains("Allocated"))
		{
			if(orderHeaderDB.getType(context.getOrderId()).equalsIgnoreCase("RETAIL"))
				{
				System.out.println("RETAIL");
				if(!locationDB.checkpreflocation(movetask.getLocationId(context.getOrderId())))
				Assert.fail("Retail Order got allocated from Non Preferred Location");
					}
			else
			{
				System.out.println("NON RETAIL");
				if(locationDB.checkpreflocation(movetask.getLocationId(context.getOrderId())))
				Assert.fail("NON Retail Order got allocated from Preferred Location");
						
			}
		}
		else
			Assert.fail("Order status not in Allocated status");
		}
	}
}
