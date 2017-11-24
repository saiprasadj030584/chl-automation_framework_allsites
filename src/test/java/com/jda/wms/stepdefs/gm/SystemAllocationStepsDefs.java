package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
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
	private OrderHeaderMaintenanceStepDefs orderHeaderMaintenanceStepsDefs;
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
		this.orderHeaderMaintenanceStepsDefs = orderHeaderMaintenanceStepsDefs;
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
		System.out.println("sku size....." + skuFromOrder.size());
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
	@When("^I enter multiple OrderID for allocation$")
	public void i_enter_multiple_OrderID_for_allocation() throws Throwable {
		jdaFooter.clickNextButton();
		for (int i = 0; i < context.getOrderList().size(); i++) {
		systemAllocationPage.enterOrderId(context.getOrderList().get(i));
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		jdaFooter.clickNextButton();
		Thread.sleep(2000);
		jdaFooter.clickDoneButton();
		Thread.sleep(2000);
		jdaFooter.clickDoneButton();
		Thread.sleep(8000);
		systemAllocationPage.deleteOrderId();
		jdaFooter.pressBackSpace();
		Thread.sleep(3000);
		}
	}
	
	@Then("^Allocation should be updated$")
	public void allocation_should_be_updated() throws Throwable {
		ArrayList failureList = new ArrayList();
		Thread.sleep(10000);
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		Assert.assertTrue("order status is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	@Then("^Allocation should be updated for multiple order$")
	public void allocation_should_be_updated_for_multiple_order() throws Throwable {
		ArrayList failureList = new ArrayList();
		Thread.sleep(10000);
		for (int i = 0; i < context.getOrderList().size(); i++) {
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderList().get(i)), failureList);
		}
		Assert.assertTrue("order status is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
}
