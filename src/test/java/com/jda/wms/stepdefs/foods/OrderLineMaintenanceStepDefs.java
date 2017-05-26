package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.OrderLineDB;
import com.jda.wms.db.SkuConfigDB;
import com.jda.wms.db.SkuDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.OrderLineMaintenancePage;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.foods.Verification;
import com.jda.wms.utils.Utilities;
import cucumber.api.java.en.*;
import org.junit.Assert;

public class OrderLineMaintenanceStepDefs {
	private OrderLineDB orderLineDB;
	private SkuConfigDB skuConfigDB;
	private SkuDB skuDB;
	private Context context;
	private Verification verification;
	private OrderLineMaintenancePage orderLineMaintenancePage;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Map<Integer, Map<String, String>> stockTransferOrderMap;
	private int qtyOrdered;

	@Inject
	public OrderLineMaintenanceStepDefs(OrderLineMaintenancePage orderLineMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JdaHomePage jdaHomePage, JDAFooter jdaFooter, Context context,
			PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs,
			PackConfigMaintenancePage packConfigMaintenancePage, OrderLineDB orderLineDB, Verification verification,SkuDB skuDB,SkuConfigDB skuConfigDB) {
		this.orderLineMaintenancePage = orderLineMaintenancePage;
		this.context = context;
		this.orderLineDB = orderLineDB;
		this.verification = verification;
		this.skuConfigDB = skuConfigDB;
		this.skuDB = skuDB;
		this.skuConfigDB = skuConfigDB;
	}

	@When("^I select the SKU line$")
	public void i_select_the_SKU_line() throws Throwable {
		orderLineMaintenancePage.navigatedOrderLinePage();
	}

	@When("^I allocate the product$")
	public void i_allocate_the_product() throws Throwable {
		orderLineMaintenancePage.allocateOrder();
	}

	@Given("^the STO should have the SKU,pack config, quantity ordered, quantity tasked,case ratio details for each line items from order line table$")
	public void the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items_from_order_line_table()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String qtyOrdered = null, qtyTasked = null, trackingLevel = null, allocationGroup = null;
		Map<Integer, Map<String, String>> stockTransferOrderMap = new HashMap<Integer, Map<String, String>>();
		int caseRatio = 0;
		ArrayList<String> skuID = new ArrayList<String>();
		
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			skuID = orderLineDB.getskuList(context.getOrderId());
			qtyOrdered = orderLineDB.getQtyOrdered(context.getOrderId(),skuID.get(i-1));
			qtyTasked = orderLineDB.getQtyTasked(context.getOrderId(),skuID.get(i-1));
			trackingLevel = orderLineDB.getTrackingLevel(context.getOrderId(),skuID.get(i-1));
			String packConfig = orderLineDB.getPackConfig(context.getOrderId(),skuID.get(i-1));
			caseRatio = Integer.parseInt(orderLineDB.getCaseRatio(context.getOrderId(),skuID.get(i-1)));
			
			verification.verifyData("Type", "CASE", trackingLevel,failureList);
			int ratio1To2 = Utilities.convertStringToInteger(skuConfigDB.getRatio1To2(packConfig));
			verification.verifyData("Case Ratio", String.valueOf(ratio1To2), String.valueOf(caseRatio),failureList);

			allocationGroup = skuDB.getAllocationGroup(skuID.get(i-1));
			
			// map
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuID.get(i-1));
			lineItemsMap.put("QtyOrdered", qtyOrdered);
			lineItemsMap.put("QtyTasked", qtyTasked);
			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			lineItemsMap.put("PackConfig", packConfig);
			lineItemsMap.put("TrackingLevel", trackingLevel);
			lineItemsMap.put("AllocationGroup", allocationGroup);

			stockTransferOrderMap.put(i, lineItemsMap);
			context.setstockTransferOrderMap(stockTransferOrderMap);
		}
		logger.debug("Map: " + stockTransferOrderMap.toString());
	}

	@Given("^the STO should have the SKU,pack config, quantity ordered, quantity tasked,case ratio details for each  line items$")
	public void the_STO_should_have_the_SKU_pack_config_quantity_ordered_quantity_tasked_case_ratio_details_for_each_line_items()
			throws Throwable {
		context.setOrderId("6666164803");
		ArrayList<String> failureList = new ArrayList<String>();
		String skuId = null, qtyOrdered = null, qtyTasked = null;
		Map<Integer, Map<String, String>> stockTransferOrderMap = new HashMap<Integer, Map<String, String>>();
		int caseRatio = 0;

		/*
		 * jdaHomePage.navigateToPackConfigPage();
		 * jdaHomePage.navigateToOrderLineMaintenance();
		 * jdaFooter.clickQueryButton();
		 * orderLineMaintenancePage.enterOrderID(context.getOrderId());
		 * jdaFooter.clickExecuteButton();
		 */

		context.setNoOfLines(2);

		// if (context.getNoOfLines() != 1) {
		// orderLineMaintenancePage.selectFirstRecord();
		// }

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			/*
			 * skuId = orderLineMaintenancePage.getSkuId(); qtyOrdered =
			 * orderLineMaintenancePage.getQtyOrdered(); qtyTasked =
			 * orderLineMaintenancePage.getQtyTasked(); String packConfig =
			 * orderLineMaintenancePage.getPackConfig();
			 * 
			 * orderLineMaintenancePage.clickUserDefinedTab(); caseRatio =
			 * Integer.parseInt(orderLineMaintenancePage.getCaseRatio());
			 * 
			 * jdaFooter.clickPackConfig();
			 * packConfigMaintenanceStepDefs.i_search_pack_config_id(packConfig)
			 * ;
			 * packConfigMaintenanceStepDefs.i_navigate_to_tracking_levels_page(
			 * ); int ratio1To2 =
			 * Utilities.convertStringToInteger(packConfigMaintenancePage.
			 * getRatio1To2()); Thread.sleep(2000);
			 * packConfigMaintenancePage.clickGeneraltab();
			 * 
			 * if (caseRatio != ratio1To2) { failureList.add(
			 * "Case ratio is not as expected for SKU (" + skuId + ") " +
			 * "Expected [" + ratio1To2 + "] but was [" + caseRatio + "]"); }
			 * 
			 * // map Map<String, String> lineItemsMap = new HashMap<String,
			 * String>(); lineItemsMap.put("SKU", skuId);
			 * lineItemsMap.put("QtyOrdered", qtyOrdered);
			 * lineItemsMap.put("QtyTasked", qtyTasked);
			 * lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			 * lineItemsMap.put("PackConfig", packConfig);
			 * 
			 * 
			 * stockTransferOrderMap.put(String.valueOf(i), lineItemsMap);
			 */
			if (i == 1) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				lineItemsMap.put("SKU", "20001991");
				lineItemsMap.put("QtyOrdered", "12");
				lineItemsMap.put("QtyTasked", "288");
				lineItemsMap.put("CaseRatio", "24");
				lineItemsMap.put("PackConfig", "20001991O01");
				stockTransferOrderMap.put(1, lineItemsMap);
			} else if (i == 2) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				lineItemsMap.put("SKU", "20089139");
				lineItemsMap.put("QtyOrdered", "12");
				lineItemsMap.put("QtyTasked", "288");
				lineItemsMap.put("CaseRatio", "24");
				lineItemsMap.put("PackConfig", "20089139O01");
				stockTransferOrderMap.put(2, lineItemsMap);
			}
			context.setstockTransferOrderMap(stockTransferOrderMap);

			/*
			 * jdaFooter.clickOrderLine(); jdaFooter.clickNextRecord();
			 * orderLineMaintenancePage.clickGeneralTab();
			 */
		}
		// Assert.assertTrue(
		// "Stock Transfer Order line details are not as expected" +
		// Arrays.asList(failureList.toString()),
		// failureList.isEmpty());
		logger.debug("Map: " + stockTransferOrderMap.toString());
	}

	@Given("^the quantity tasked should be updated for each order lines$")
	public void the_quantity_tasked_should_be_updated_for_each_order_lines() throws Throwable {
		stockTransferOrderMap = context.getStockTransferOrderMap();
		ArrayList<String> failureList = new ArrayList<String>();

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String sku = stockTransferOrderMap.get(i).get("SKU");

			verification.verifyData("Tracking level", "CASE", stockTransferOrderMap.get(i).get("TrackingLevel"),
					failureList);
			if (stockTransferOrderMap.get(i).get("QtyTasked") != null) {
				verification.verifyData("Quantity Tasked",
						String.valueOf(Integer.parseInt(stockTransferOrderMap.get(i).get("QtyOrdered"))
								* Integer.parseInt(stockTransferOrderMap.get(i).get("CaseRatio"))),
						String.valueOf(Integer.parseInt(stockTransferOrderMap.get(i).get("QtyTasked"))), failureList);
			} else {
				verification.verifyData("Back Ordered", "Y", orderLineDB.getBackOrdered(context.getOrderId(), sku),
						failureList);

				Assert.assertTrue("Order line Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
						failureList.isEmpty());
			}
		}
	}

}
