package com.jda.wms.stepdefs.gm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.*;

public class SystemAllocationStepsDefs {
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

	private JDALoginStepDefs jDALoginStepDefs;
	private SystemAllocationPage systemAllocationPage;
	private OrderHeaderDB orderHeaderDB;
	private OrderLineDB orderLineDB;

	@Inject
	public SystemAllocationStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,
			UPIReceiptHeaderDB upiReceiptHeaderDB, Verification verification, DeliveryDB deliveryDB,
			PreAdviceLineStepDefs preAdviceLineStepDefs, PreAdviceLineDB preAdviceLineDB,
			UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage,
			OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepsDefs, JDALoginStepDefs jDALoginStepDefs,
			SystemAllocationPage systemAllocationPage, OrderHeaderDB orderHeaderDB, OrderLineDB orderLineDB) {
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
		
		this.jDALoginStepDefs = jDALoginStepDefs;
		this.systemAllocationPage = systemAllocationPage;
		this.orderHeaderDB = orderHeaderDB;
		this.orderLineDB = orderLineDB;

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
		skuFromOrder = context.getSkuFromOrder();
		verification.verifyData("Order Status", "Released", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		for (int i = 0; i < skuFromOrder.size(); i++) {
			context.setRcvQtyDue(
					Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), (String) skuFromOrder.get(i))));

			if ((orderLineDB.getQtyTasked(context.getOrderId(), (String) skuFromOrder.get(i)) != null)) {
				if (!(orderLineDB.getQtyTasked(context.getOrderId(), (String) skuFromOrder.get(i)).equals("0"))) {
					failureList.add("Quantity Tasked got updated " + (String) skuFromOrder.get(i));
					// context.setFailureList(failureList);
				}
			}
		}
		Assert.assertTrue("Allocation of stock is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
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
		skuFromOrder = context.getSkuFromOrder();
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		for (int i = 0; i < skuFromOrder.size(); i++) {
			context.setRcvQtyDue(
					Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), (String) skuFromOrder.get(i))));
			if (!(orderLineDB.getQtyTasked(context.getOrderId(), (String) skuFromOrder.get(i))
					.equals(String.valueOf(context.getRcvQtyDue())))) {
				failureList.add("Quantity Tasked not updated " + (String) skuFromOrder.get(i));
				context.setFailureList(failureList);
			}
		}
		Assert.assertTrue("Allocation of stock is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I enter OrderID for allocation$")
	public void i_enter_OrderID_for_allocation() throws Throwable {
		jdaFooter.clickNextButton();
		systemAllocationPage.enterOrderId(context.getOrderId());
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		jdaFooter.clickDoneButton();
		Thread.sleep(2000);
		jdaFooter.clickDoneButton();
		Thread.sleep(8000);
	}
	
	@Then("^Allocation should be updated$")
	public void allocation_should_be_updated() throws Throwable {
		ArrayList failureList = new ArrayList();
		Thread.sleep(10000);
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		//verification.verifyData("Quantity Tasked",orderHeaderDB.getOrderedQuantity(context.getOrderId()), orderHeaderDB.getQuantitytaskedStatus(context.getOrderId()), failureList);
		//verification.verifyData("Qty task", orderHeaderDB.getOrderedQuantityWithOrderId(context.getOrderId()), orderHeaderDB. getQtyTaskedWithOrderID(context.getOrderId()), failureList);
		Assert.assertTrue("Order Status not updated as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^I allocate the stocks using consignment in system allocation page$")
	public void i_allocate_the_stocks_using_consignment_in_system_allocation_page() throws Throwable {
		DateFormat sdf = new SimpleDateFormat(
			    "HH:mm:ss");
	String minimumTime="";
	Date first=new Date();
	ArrayList failureList = new ArrayList();
	

	for (int k = 0; k < context.getOrderList().size(); k++) {
		context.setOrderId(context.getOrderList().get(k));
	
		if(k==0)
		{
			minimumTime=orderHeaderDB.getCreationDate(context.getOrderId()).replace('.',':').substring(11, 19);
			first = sdf.parse(minimumTime);
			
		}
	System.out.println(orderHeaderDB.getCreationDate(context.getOrderId()).replace('.',':').substring(11, 19));
	if(!(first.before(sdf.parse(orderHeaderDB.getCreationDate(context.getOrderId()).replace('.',':').substring(11, 19)))))
			{
		minimumTime=orderHeaderDB.getCreationDate(context.getOrderId()).replace('.',':').substring(11, 19);
		first=sdf.parse(minimumTime);
			}
	}
	
	


	//In UI
	
	jdaHomePage.navigateToSystemAllocationPage();
	Thread.sleep(6000);

jdaFooter.clickNextButton();
//systemAllocationPage.enterOrderDate("0");
systemAllocationPage.enterOrderTime(">="+minimumTime);
systemAllocationPage.enterConsignmentID(context.getConsignmentID());
jdaFooter.clickNextButton();
systemAllocationPage.selectAllRecords();
jdaFooter.clickNextButton();
jdaFooter.clickDoneButton();

	}

	
	@Given("^the multiple stocks should get allocated")
	public void the_multiple_stocks_should_get_allocated() throws Throwable {
		
		
		ArrayList failureList = new ArrayList();
		for (int k = 0; k < context.getOrderList().size(); k++) {
			context.setOrderId(context.getOrderList().get(k));
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickExecuteButton();
		jdaHomePage.navigateToOrderLineMaintenance();
		jdaFooter.clickQueryButton();
		systemAllocationPage.enterOrderID();
		jdaFooter.clickExecuteButton();
		
		
			
			
			
			
			ArrayList<String> skuFromOrder = new ArrayList<String>();
			skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		for (int i = 0; i < skuFromOrder.size(); i++) {
			context.setRcvQtyDue(
					Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), (String) skuFromOrder.get(i))));

			System.out.println("QTY DUEEE11"+String.valueOf(context.getRcvQtyDue()));
			System.out.println(!(orderLineDB.getQtyTasked(context.getOrderId(), (String) skuFromOrder.get(i))
					.equals(String.valueOf(context.getRcvQtyDue()))));
			
			System.out.println("QTY DUEEE"+String.valueOf(context.getRcvQtyDue()));
			System.out.println((orderLineDB.getQtyTasked(context.getOrderId(), (String) skuFromOrder.get(i))));
			if (!(orderLineDB.getQtyTasked(context.getOrderId(), (String) skuFromOrder.get(i))
					.equals(String.valueOf(context.getRcvQtyDue())))) {
				failureList.add("Quantity Tasked not updated " + (String) skuFromOrder.get(i));
				// context.setFailureList(failureList);
			}
		}
		}
		Assert.assertTrue("Allocation of stock is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

}
