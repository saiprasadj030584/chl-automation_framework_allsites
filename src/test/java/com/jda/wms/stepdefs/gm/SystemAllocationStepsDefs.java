package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.SystemAllocationPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;

import cucumber.api.java.en.Given;

public class SystemAllocationStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB  upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private OrderHeaderStepsDefs orderHeaderStepsDefs;
	private JDALoginStepDefs jDALoginStepDefs;
	private SystemAllocationPage systemAllocationPage;
	private OrderHeaderDB orderHeaderDB;
	private OrderLineDB orderLineDB;
	

	@Inject
	public SystemAllocationStepsDefs(JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,UPIReceiptHeaderDB  upiReceiptHeaderDB,Verification verification,DeliveryDB deliveryDB,PreAdviceLineStepDefs preAdviceLineStepDefs,PreAdviceLineDB preAdviceLineDB,UPIReceiptLineDB upiReceiptLineDB,JdaHomePage jdaHomePage,OrderHeaderStepsDefs orderHeaderStepsDefs,JDALoginStepDefs jDALoginStepDefs,SystemAllocationPage systemAllocationPage,OrderHeaderDB orderHeaderDB,OrderLineDB orderLineDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs=preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage=jdaHomePage;
		this.orderHeaderStepsDefs=orderHeaderStepsDefs;
		this.jDALoginStepDefs=jDALoginStepDefs;
		this.systemAllocationPage=systemAllocationPage;
		this.orderHeaderDB=orderHeaderDB;
		this.orderLineDB=orderLineDB;
		
	}
	@Given("^the order number \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_order_number_should_be_in_status(String orderNumber, String status) throws Throwable {
		context.setStatus(status);
		context.setOrderId(orderNumber);
//		orderHeaderStepsDefs.the_order_id_should_be_in_status(orderNumber,
//				status);
		//jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
	}
	
	
	
	@Given("^I allocate the stocks$")
	public void i_allocate_the_stocks() throws Throwable {
		jdaFooter.clickNextButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
	}
	
	@Given("^the stock should not get allocated")
	public void the_stock_should_not_get_allocated() throws Throwable {
		ArrayList failureList = new ArrayList();
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickExecuteButton();
		jdaHomePage.navigateToOrderLineMaintenance();
		jdaFooter.clickQueryButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickExecuteButton();
		ArrayList skuFromOrder = new ArrayList();
		verification.verifyData("Order Status", "Released", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		for(int i=0;i<skuFromOrder.size();i++)
		{
		if (orderLineDB.getQtyTasked(context.getOrderId(),(String) skuFromOrder.get(i))!=null) {
			failureList.add("Quantity Tasked updated " + (String) skuFromOrder.get(i));
			//context.setFailureList(failureList);
		}
		}
		Assert.assertTrue("Allocation of stock in suspense location is not as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the stock should get allocated")
	public void the_stock_should_get_allocated() throws Throwable {
		ArrayList failureList = new ArrayList();
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickExecuteButton();
		jdaHomePage.navigateToOrderLineMaintenance();
		jdaFooter.clickQueryButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickExecuteButton();
		
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder=orderLineDB.getskuList(context.getOrderId());
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		for(int i=0;i<skuFromOrder.size();i++)
		{
			context.setRcvQtyDue(Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(),(String) skuFromOrder.get(i))));
		if (!(orderLineDB.getQtyTasked(context.getOrderId(),(String) skuFromOrder.get(i)).equals(String.valueOf(context.getRcvQtyDue())))) {
			failureList.add("Quantity Tasked not updated " + (String) skuFromOrder.get(i));
			//context.setFailureList(failureList);
		}
		}
		Assert.assertTrue("Allocation of stock is not as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	}
