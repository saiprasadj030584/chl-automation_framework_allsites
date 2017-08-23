package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;

public class OrderHeaderStepDefs {
	private Context context;
	private JDAFooter jDAFooter;
	private OrderHeaderPage orderHeaderPage;
	private JdaHomePage jDAHomePage;
	private Verification verification;
	private OrderHeaderDB orderHeaderDB;
	// Screen screen = new Screen();
	// int timeoutInSec = 20;

	@Inject
	public OrderHeaderStepDefs(Context context, JDAFooter jDAFooter, OrderHeaderPage orderHeaderPage,
			JdaHomePage jDAHomePage, OrderHeaderDB orderHeaderDB, Verification verification) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.orderHeaderPage = orderHeaderPage;
		this.jDAHomePage = jDAHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
	}

	@Then("^the order should be allocated for the orderID \"([^\"]*)\"$")
	public void the_order_should_be_allocated_for_the_orderID(String orderid) throws Throwable {
		ArrayList failureList = new ArrayList();
		// String status="Allocated";
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		System.out.println("Divya allocation");
		System.out.println("allocation order" + orderid);

		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(orderid), failureList);
		Assert.assertTrue("Order Status not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

}
