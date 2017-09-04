package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;
import edu.emory.mathcs.backport.java.util.Arrays;

public class OrderLineMaintenanceStepDefs {
	ArrayList<String> failureList = new ArrayList<String>();
	private Context context;
	private JDAFooter jDAFooter;
	private JdaHomePage jDAHomePage;
	private Verification verification;
	private OrderLineDB orderLineDB;
	private OrderHeaderDB orderHeaderDB;
	
	@Inject
	public OrderLineMaintenanceStepDefs(Context context, JDAFooter jDAFooter,
			 JdaHomePage jDAHomePage,OrderLineDB orderLineDB, Verification verification,OrderHeaderDB orderHeaderDB) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.orderLineDB=orderLineDB;
		this.verification = verification;
		this.orderHeaderDB = orderHeaderDB;
	}
	
	@Given("^the order line details should be displayed$")
	public void quantity_tasked_should_be_populated_in_order_line() throws Throwable {
		verification.verifyData("Number of lines",String.valueOf(context.getNoOfLines()), String.valueOf(orderLineDB.getRecordCountByOrderId(context.getOrderId())), failureList);
		ArrayList skuList = orderLineDB.getSkuList(context.getOrderId());
		ArrayList qtyTaskedList = new ArrayList();
		context.setSkuList(skuList);
		
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) skuList.get(i-1));
			String qtyTasked=orderLineDB.getQtyTasked(context.getOrderId(),context.getSkuId());
			qtyTaskedList.add(qtyTasked);
			verification.verifyData("Quantity tasked for SKU"+context.getSkuId(),"NOT NULL", qtyTasked, failureList);
		}
		context.setQtyTaskedList(qtyTaskedList);
		Assert.assertTrue(
				"Order line details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^the order sku details are verified$")
	public void the_order_sku_details_are_verified() throws Throwable {
		String numLinesFromHeaderTable = orderHeaderDB.getNumberOfLines(context.getOrderId());
		int numLinesFromLineTable = orderLineDB.getSkuList(context.getOrderId()).size();
		Assert.assertTrue("Num lines are not matching in header and line tables",
				Integer.parseInt(numLinesFromHeaderTable) == numLinesFromLineTable); 
	}
}
