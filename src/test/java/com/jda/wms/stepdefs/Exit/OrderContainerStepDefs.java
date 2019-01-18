package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.OrderContainerPage;
import com.jda.wms.context.Context;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderContainerStepDefs {
	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAFooter jdaFooter;
	private OrderContainerPage orderContainerPage;
	private final Context context;


	@Inject
	public OrderContainerStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			OrderContainerPage orderContainerPage,JDAFooter jdaFooter,Context context) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.orderContainerPage=orderContainerPage;
		this.context=context;
	}
	
	@Then("^Site finds the stock physically$")
	public void site_finds_the_stock_physically() throws Throwable{
		jdaFooter.clickQueryButton();
		orderContainerPage.enterOrderId(context.getOrderId());
		
			jdaFooter.pressTab();
			orderContainerPage.enterPallet(context.getpalletIDforUPI());
			jdaFooter.pressTab();
			orderContainerPage.enterContainerId(context.getpalletIDforUPI());
			jdaFooter.clickExecuteButton();
			
		
		
	}
}
