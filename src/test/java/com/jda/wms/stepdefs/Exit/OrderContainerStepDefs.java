package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.PurchaseOrderReceivingPage;
import com.jda.wms.pages.Exit.OrderContainerPage;
import com.jda.wms.context.Context;
import com.jda.wms.hooks.Hooks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderContainerStepDefs {
	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAFooter jdaFooter;
	private OrderContainerPage orderContainerPage;
	private final Context context;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private Hooks hooks;


	@Inject
	public OrderContainerStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			OrderContainerPage orderContainerPage,JDAFooter jdaFooter,Context context,
			PurchaseOrderReceivingPage purchaseOrderReceivingPage,Hooks hooks) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.orderContainerPage=orderContainerPage;
		this.context=context;
		this.purchaseOrderReceivingPage=purchaseOrderReceivingPage;
		this.hooks=hooks;
	}
	
	@Then("^Site finds the stock physically$")
	public void site_finds_the_stock_physically() throws Throwable{
		jdaFooter.clickQueryButton();
		orderContainerPage.enterOrderId(context.getOrderId());
			jdaFooter.pressTab();
			orderContainerPage.enterPallet(context.getPalletId1());
			jdaFooter.pressTab();
			orderContainerPage.enterContainerId(context.getpalletIDforUPI());
			jdaFooter.clickExecuteButton();
	}
	
	 @Then("^I enter URN and ToPallet$")
	 public void i_enter_urn_and_toPallet() throws Throwable{
		 String palletIDforUPI = context.getpalletIDforUPI();
			System.out.println("palletID "+palletIDforUPI);
			purchaseOrderReceivingPage.EnterPalletIDForRepacking(palletIDforUPI);
			purchaseOrderReceivingPage.EnterToPallet(context.getPalletId1());	
			purchaseOrderReceivingPage.clickEnter();
			purchaseOrderReceivingPage.proceedToNextScreen();
			purchaseOrderReceivingPage.Combine();
			Assert.assertTrue("Page not as Expected",purchaseOrderReceivingPage.isRepackdone());
			hooks.logoutPutty();
			
			}
	 
	 @Then("^Verify the container ID to be changed$")
	 public void verify_the_container_id_to_be_changed() throws Throwable{
		 jdaFooter.clickQueryButton();
			orderContainerPage.enterOrderId(context.getOrderId());
			jdaFooter.clickExecuteButton();
		 Assert.assertNotEquals("Container id is not chnaged", context.getpalletIDforUPI(), orderContainerPage.getContainer());
	 }
	  
}
