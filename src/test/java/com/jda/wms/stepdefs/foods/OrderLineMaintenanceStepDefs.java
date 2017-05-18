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
	private int qtyOrdered;

	@Inject
	public OrderLineMaintenanceStepDefs(OrderLineMaintenancePage orderLineMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JdaHomePage jdaHomePage, JDAFooter jdaFooter, Context context,
			PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs,
			PackConfigMaintenancePage packConfigMaintenancePage) {
		this.orderLineMaintenancePage = orderLineMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.packConfigMaintenanceStepDefs = packConfigMaintenanceStepDefs;
		this.packConfigMaintenancePage = packConfigMaintenancePage;

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
		Map<String, Map<String, String>> stockTransferOrderMap = new HashMap<String, Map<String, String>>();
		int caseRatio = 0;

		jdaHomePage.navigateToPackConfigPage();
		jdaHomePage.navigateToOrderLineMaintenance();
		jdaFooter.clickQueryButton();
		orderLineMaintenancePage.enterOrderID("5800002015");
		jdaFooter.clickExecuteButton();

		int lines = 4;
		if (lines != 1) {
			// if (context.getNoOfLines() != 1) {
			orderLineMaintenancePage.selectFirstRecord();
		}

		for (int i = 1; i <= lines; i++) {
			skuId = orderLineMaintenancePage.getSkuId();
			qtyOrdered = orderLineMaintenancePage.getQtyOrdered();
			qtyTasked = orderLineMaintenancePage.getQtyTasked();
			String packConfig = orderLineMaintenancePage.getPackConfig();

			orderLineMaintenancePage.clickUserDefinedTab();
			caseRatio = Integer.parseInt(orderLineMaintenancePage.getCaseRatio());

			jdaFooter.clickPackConfig();
			packConfigMaintenanceStepDefs.i_search_pack_config_id(packConfig);
			packConfigMaintenanceStepDefs.i_navigate_to_tracking_levels_page();
			int ratio1To2 = Utilities.convertStringToInteger(packConfigMaintenancePage.getRatio1To2());
			Thread.sleep(2000);
			packConfigMaintenancePage.clickGeneraltab();

			if (caseRatio != ratio1To2) {
				failureList.add("Case ratio is not as expected for SKU (" + skuId + ") " + "Expected [" + ratio1To2
						+ "] but was [" + caseRatio + "]");
			}

			// map
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuId);
			lineItemsMap.put("Qtyordered", qtyOrdered);
			lineItemsMap.put("QtyTasked", qtyTasked);
			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			lineItemsMap.put("PackConfig", packConfig);

			stockTransferOrderMap.put(String.valueOf(i), lineItemsMap);
			context.setstockTransferOrderMap(stockTransferOrderMap);

			jdaFooter.clickOrderLine();
			jdaFooter.clickNextRecord();
			orderLineMaintenancePage.clickGeneralTab();

		}
		Assert.assertTrue(
				"Stock Transfer Order line details are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		logger.debug("Map: " + stockTransferOrderMap.toString());
	}
	@Given("^the quantity tasked should be updated for each order lines$")
	public void the_quantity_tasked_should_be_updated_for_each_order_lines() throws Throwable {
		jdaFooter.clickQueryButton();
		OrderLineMaintenancePage.enterOrderNo("66661642250");
		jdaFooter.clickExecuteButton();
		orderLineMaintenancePage.selectFirstRecord();
	}
	

	

	@Then("^the tracking level, Qty Ordered, should be displayed$")
	public void the_tracking_level_Qty_Ordered_should_be_displayed() throws Throwable {
		orderLineMaintenancePage.getQtyOrdered();
		context.setQtyOrdered(qtyOrdered);
		orderLineMaintenancePage.getTrackinglevel();

	}

	@Then("^i navigate user defined tab$")
	public void i_navigate_user_defined_tab() throws Throwable {
		orderLineMaintenancePage.clickUserDefinedTab();
	}

	@Then("^the case ratio should be dislayed$")
	public void the_case_ratio_should_be_dislayed() throws Throwable {
		orderLineMaintenancePage.getCaseRatio();
		context.getCaseRatio();
	}

}
