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
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;

import cucumber.api.java.en.Given;

public class OrderHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private OrderHeaderDB orderHeaderDB;
	private InventoryDB inventoryDB;
	private OrderLineDB orderLineDB;

	@Inject
	public OrderHeaderStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			Context context, PreAdviceHeaderDB preAdviceHeaderDB, UPIReceiptHeaderDB upiReceiptHeaderDB,
			Verification verification, DeliveryDB deliveryDB, PreAdviceLineStepDefs preAdviceLineStepDefs,
			PreAdviceLineDB preAdviceLineDB, UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage,
			OrderHeaderDB orderHeaderDB,InventoryDB inventoryDB,OrderLineDB orderLineDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage = jdaHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.inventoryDB=inventoryDB;
		this.orderLineDB=orderLineDB;

	}

	@Given("^the order id \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_order_id_should_be_in_status(String orderNumber, String status) throws Throwable {
		System.out.println(orderNumber);
		System.out.println(status);
		context.setOrderId(orderNumber);
		context.setStatus(status);
		Assert.assertEquals("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber));
		//jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
	}
	
	@Given("^order header should be updated for picked stock$")
	public void order_header_should_be_updated_for_picked_stock() throws Throwable {
		Assert.assertEquals("Order header not displayed as expected", "Picked", orderHeaderDB.getStatus(context.getOrderId()));
	}
	
	@Given("^order header should be updated for unpicked stock$")
	public void order_header_should_be_updated_for_unpicked_stock() throws Throwable {
		Assert.assertEquals("Order header not displayed as expected", "Released", orderHeaderDB.getStatus(context.getOrderId()));
	}
	
	@Given("^the order id \"([^\"]*)\" of type \"([^\"]*)\" should be in \"([^\"]*)\" status and skus should be in \"([^\"]*)\" location$")
	public void the_order_id_of_type_should_be_in_status_and_sku_should_be_in_location(String orderNumber,String orderType, String status,String locationId) throws Throwable {
		System.out.println(orderNumber);
		System.out.println(status);
		context.setOrderId(orderNumber);
		ArrayList<String> failureList = new ArrayList<String>();
		context.setStatus(status);
		//suspense
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder=orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		System.out.println(skuFromOrder);
		for(int i=0;i<skuFromOrder.size();i++){
			System.out.println((inventoryDB.isSkuInSuspenseLocation((String)(skuFromOrder.get(i)))));
			System.out.println(!(inventoryDB.isSkuInSuspenseLocation((String)(skuFromOrder.get(i)))));
					if(!(inventoryDB.isSkuInSuspenseLocation((String)(skuFromOrder.get(i)))))
							{
						Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i), inventoryDB.isSkuInSuspenseLocation((String)(skuFromOrder.get(i))));
							}
		}
				//order type
				if(orderType.equalsIgnoreCase("International"))
				{
					verification.verifyData("Order Type Mismatch","Retail",
							 orderHeaderDB.getOrderType(orderNumber),
							failureList);
					verification.verifyData("Destination is not as expected","8468",
							 orderHeaderDB.getCustomer(orderNumber),
							failureList);
					verification.verifyData("User defined Type 4 is not as expected","Franchise",
							 orderHeaderDB.getUserDefinedType4(orderNumber),
							failureList);
				}
				else if(orderType.equalsIgnoreCase("Ecom"))
				{
					verification.verifyData("Order Type Mismatch","IDT",
							 orderHeaderDB.getOrderType(orderNumber),
							failureList);
					verification.verifyData("Destination is not as expected","4624",
							 orderHeaderDB.getCustomer(orderNumber),
							failureList);
				}
				else if(orderType.equalsIgnoreCase("Outlet"))
				{
					verification.verifyData("Order Type Mismatch","IDT",
							 orderHeaderDB.getOrderType(orderNumber),
							failureList);
					verification.verifyData("Destination is not as expected","2862",
							 orderHeaderDB.getCustomer(orderNumber),
							failureList);
				}
				else
				{
				verification.verifyData("Order Type Mismatch",orderType,
						 orderHeaderDB.getOrderType(orderNumber),
						failureList);
				}
				
//				//Order status
				verification.verifyData("Order Status not displayed as expected",status,
						 orderHeaderDB.getStatus(orderNumber),
						failureList);
				
				//jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
				Assert.assertTrue("Order Details is not as expected. ["
						+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());

	}
	
	@Given("^the order id \"([^\"]*)\" of type \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_order_id_of_type_should_be_in_status(String orderNumber,String orderType, String status) throws Throwable {
		System.out.println(orderNumber);
		System.out.println(status);
		context.setOrderId(orderNumber);
		context.setStatus(status);
		ArrayList<String> failureList = new ArrayList<String>();
		//suspense
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder=orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		for(int i=0;i<skuFromOrder.size();i++){
		//Assert.assertFalse("sku"+skuFromOrder.get(i)+" is in suspense location", inventoryDB.isSkuInSuspenseLocation((String)(skuFromOrder.get(i))));
			if((inventoryDB.isSkuInSuspenseLocation((String)(skuFromOrder.get(i)))))
					{
				failureList.add("Sku in suspense location " + (String) skuFromOrder.get(i));
					}
		}
		//order type
		if(orderType.equalsIgnoreCase("International"))
		{
			verification.verifyData("Order Type Mismatch","Retail",
					 orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected","8468",
					 orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected","Franchise",
					 orderHeaderDB.getUserDefinedType4(orderNumber),
					failureList);
		}
		else if(orderType.equalsIgnoreCase("Ecom"))
		{
			verification.verifyData("Order Type Mismatch","IDT",
					 orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected","4624",
					 orderHeaderDB.getCustomer(orderNumber),
					failureList);
		}
		else if(orderType.equalsIgnoreCase("Outlet"))
		{
			verification.verifyData("Order Type Mismatch","IDT",
					 orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected","2862",
					 orderHeaderDB.getCustomer(orderNumber),
					failureList);
		}
		else
		{
		verification.verifyData("Order Type Mismatch",orderType,
				 orderHeaderDB.getOrderType(orderNumber),
				failureList);
		}
		
		//Order status
//		verification.verifyData("Order Status not displayed as expected",status,
//				 orderHeaderDB.getStatus(orderNumber),
//				failureList);
//		
		//jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
}
