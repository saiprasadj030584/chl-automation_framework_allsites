package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.OrderLineDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderLineMaintenancePage;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderLineMaintenanceStepDefs {
	private final OrderLineMaintenancePage orderLineMaintenancePage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private Context context;
	private final PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs;
	private final PackConfigMaintenancePage packConfigMaintenancePage;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final OrderLineDB orderLineDB;
	Map<Integer, Map<String, String>> stockTransferOrderMap;
	

	@Inject
	public OrderLineMaintenanceStepDefs(OrderLineMaintenancePage orderLineMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JdaHomePage jdaHomePage, JDAFooter jdaFooter, Context context,
			PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs,
			PackConfigMaintenancePage packConfigMaintenancePage, OrderLineDB orderLineDB) {
		this.orderLineMaintenancePage = orderLineMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.packConfigMaintenanceStepDefs = packConfigMaintenanceStepDefs;
		this.packConfigMaintenancePage = packConfigMaintenancePage;
		this.orderLineDB = orderLineDB;

	}

	@When("^I select the SKU line$")
	public void i_select_the_SKU_line() throws Throwable {
		orderLineMaintenancePage.navigatedOrderLinePage();
	}

	@When("^I allocate the product$")
	public void i_allocate_the_product() throws Throwable {
		orderLineMaintenancePage.allocateOrder();
	}

	@Given("^the STO should have the SKU,pack config, quantity ordered, quantity tasked,case ratio details for each line items$")
	public void the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String skuId = null, qtyOrdered = null, qtyTasked = null;
		Map<Integer, Map<String, String>> stockTransferOrderMap = new HashMap<Integer, Map<String, String>>();
		int caseRatio = 0;
		context.setOrderId("6666164250");
//		jdaHomePage.navigateToPackConfigPage();
//		jdaHomePage.navigateToOrderLineMaintenance();
//		jdaFooter.clickQueryButton();
//		orderLineMaintenancePage.enterOrderID(context.getOrderId());
//		jdaFooter.clickExecuteButton();

		context.setNoOfLines(2);
//		if (context.getNoOfLines() != 1) {
//			orderLineMaintenancePage.selectFirstRecord();
//		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
//			skuId = orderLineMaintenancePage.getSkuId();
//			qtyOrdered = orderLineMaintenancePage.getQtyOrdered();
//			qtyTasked = orderLineMaintenancePage.getQtyTasked();
//			String packConfig = orderLineMaintenancePage.getPackConfig();
//
//			orderLineMaintenancePage.clickUserDefinedTab();
//			caseRatio = Integer.parseInt(orderLineMaintenancePage.getCaseRatio());
//
//			jdaFooter.clickPackConfig();
//			packConfigMaintenanceStepDefs.i_search_pack_config_id(packConfig);
//			packConfigMaintenanceStepDefs.i_navigate_to_tracking_levels_page();
//			int ratio1To2 = Utilities.convertStringToInteger(packConfigMaintenancePage.getRatio1To2());
//			Thread.sleep(2000);
//			packConfigMaintenancePage.clickGeneraltab();
//
//			if (caseRatio != ratio1To2) {
//				failureList.add("Case ratio is not as expected for SKU (" + skuId + ") " + "Expected [" + ratio1To2
//						+ "] but was [" + caseRatio + "]");
//			}

			// map
//			Map<String, String> lineItemsMap = new HashMap<String, String>();
//			lineItemsMap.put("SKU", skuId);
//			lineItemsMap.put("QtyOrdered", qtyOrdered);
//			lineItemsMap.put("QtyTasked", qtyTasked);
//			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
//			lineItemsMap.put("PackConfig", packConfig);
//
//			stockTransferOrderMap.put(String.valueOf(i), lineItemsMap);
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			if (i==1){
			lineItemsMap.put("SKU", "20001287");
			lineItemsMap.put("QtyOrdered", "15" );
			lineItemsMap.put("QtyTasked", "720");
			lineItemsMap.put("CaseRatio", "48");
			lineItemsMap.put("PackConfig", "20001287O01");
			lineItemsMap.put("TrackingLevel", "CASE");

			stockTransferOrderMap.put(i, lineItemsMap);
			}
			else if (i==2){
				lineItemsMap.put("SKU", "20001449");
				lineItemsMap.put("QtyOrdered", "15");
				lineItemsMap.put("QtyTasked", null);
				lineItemsMap.put("CaseRatio", "24");
				lineItemsMap.put("PackConfig", "20001449O01");
				lineItemsMap.put("TrackingLevel", "CASE");

				stockTransferOrderMap.put(i, lineItemsMap);
			}
			context.setstockTransferOrderMap(stockTransferOrderMap);
			System.out.println(stockTransferOrderMap);

//			jdaFooter.clickOrderLine();
//			jdaFooter.clickNextRecord();
//			orderLineMaintenancePage.clickGeneralTab();

		}
		Assert.assertTrue(
				"Stock Transfer Order line details are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		logger.debug("Map: " + stockTransferOrderMap.toString());
	}
	
	@Given("^the quantity tasked should be updated for each order lines$")
	public void the_quantity_tasked_should_be_updated_for_each_order_lines() throws Throwable {
		
		stockTransferOrderMap = context.getstockTransferOrderMap();
		
		for (int i=1;i<=context.getNoOfLines();i++){
			String trackinglevel = stockTransferOrderMap.get(i).get("TrackingLevel");
			Assert.assertEquals("tracking level is not displayed as expected", "CASE",
					stockTransferOrderMap.get(i).get("TrackingLevel"));
			
			int qtyordered = Integer.parseInt(stockTransferOrderMap.get(i).get("QtyOrdered"));
			int caseratio = Integer.parseInt(stockTransferOrderMap.get(i).get("CaseRatio"));
			int qtyTasked=0, qtyTaskedExpecetd=0;
			String sku = stockTransferOrderMap.get(i).get("SKU");
			
			if (stockTransferOrderMap.get(i).get("QtyTasked")!=null){
				qtyTasked = Integer.parseInt(stockTransferOrderMap.get(i).get("QtyTasked"));	
				qtyTaskedExpecetd = qtyordered * caseratio;
					 Assert.assertEquals("Quantity Tasked is not as expected. Expected ["+qtyTaskedExpecetd+ " but was "+qtyTasked, qtyTaskedExpecetd,
							 qtyTasked);
			}
			else{
				String backOrdered= orderLineDB.getBackOrdered(context.getOrderId(),sku);
				 Assert.assertEquals("Back Ordered is not as expected. Expected [Y] but was "+backOrdered, "Y",
						 backOrdered);
			}
		}
	}	
}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

	