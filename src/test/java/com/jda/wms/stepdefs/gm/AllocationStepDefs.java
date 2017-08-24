package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
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

	@Inject
	public AllocationStepDefs(JDAFooter jDAFooter, AllocationPage allocationPage, OrderHeaderDB orderHeaderDB,
			Verification verification, OrderLineDB orderLineDB, Context context, InventoryDB inventoryDB) {
		this.jDAFooter = jDAFooter;
		this.allocationPage = allocationPage;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
		this.orderLineDB = orderLineDB;
		this.context = context;
		this.inventoryDB = inventoryDB;
	}

	@Given("^the OrderID \"([^\"]*)\" of type \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_OrderID_of_type_should_be_in_status(String orderId, String orderType, String status)
			throws Throwable {
		System.out.println("order=" + orderId);
		System.out.println("orderType=" + orderType);
		System.out.println("status=" + status);
		context.setOrderId(orderId);
		// context.setOrderType(orderType);
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", status, orderHeaderDB.getStatus(orderId), failureList);
		// System.out.println("db status " + orderHeaderDB.getStatus(orderId));
		// context.setSkuId(orderLineDB.getSkuId(orderId));
		// System.out.println("divya3");
		// System.out.println("skuid" + context.getSkuId());
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I enter OrderID as \"([^\"]*)\" for allocation$")
	public void i_enter_OrderID_as_for_allocation(String orderid) throws Throwable {
		jDAFooter.clickNextButton();
		allocationPage.enterOrderId(orderid);
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		jDAFooter.clickDoneButton();
		Thread.sleep(5000);
		jDAFooter.clickDoneButton();
	}

}
