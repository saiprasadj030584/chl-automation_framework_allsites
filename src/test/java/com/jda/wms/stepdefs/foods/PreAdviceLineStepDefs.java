package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.foods.PreAdviceLinePage;
import com.jda.wms.pages.foods.SKUMaintenancePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class PreAdviceLineStepDefs {

	private final PreAdviceLinePage preAdviceLinePage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final PackConfigMaintenancePage packConfigMaintenancePage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs;
	private Context context;
	private final SKUMaintenancePage skuMaintenancePage;

	@Inject
	public PreAdviceLineStepDefs(PreAdviceLinePage preAdviceLinePage, JDAFooter jdaFooter, JdaHomePage jdaHomePage,
			PackConfigMaintenancePage packConfigMaintenancePage, JDAHomeStepDefs jdaHomeStepDefs,
			PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs, Context context,
			SKUMaintenancePage skuMaintenancePage) {
		this.preAdviceLinePage = preAdviceLinePage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.packConfigMaintenancePage = packConfigMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.packConfigMaintenanceStepDefs = packConfigMaintenanceStepDefs;
		this.context = context;
		this.skuMaintenancePage = skuMaintenancePage;
	}

	@Given("^the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items$")
	public void the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_line_items()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String skuId = null, qtyDue = null, vintage = null, allocationGroup = null, productGroup = null, maxQty = null;
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		int caseRatio = 0;

		context.setNoOfLines(2);

		jdaHomeStepDefs.i_am_on_pack_config_maintenance_page();
		jdaHomePage.navigateToSKUMaintanence();

		jdaHomePage.navigateToPreAdviceLinePage();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceID("0030229923");
		jdaFooter.clickExecuteButton();

		if (context.getNoOfLines() != 1) {
			preAdviceLinePage.selectFirstRecord();
		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			skuId = preAdviceLinePage.getSkuId();
			qtyDue = preAdviceLinePage.getQtyDue();
			// String trackingLevel = preAdviceLinePage.getTrackingLevel();
			String packConfig = preAdviceLinePage.getPackConfig();
			// String underBond = preAdviceLinePage.getUnderBond();

			preAdviceLinePage.clickUserDefinedTab();
			vintage = preAdviceLinePage.getVintage();
			caseRatio = Utilities.convertStringToInteger(preAdviceLinePage.getCaseRatio());
			String baseUOM = preAdviceLinePage.getBaseUOM();

			if (baseUOM.isEmpty()) {
				failureList.add("Base UOM is not as expected for SKU (" + skuId + ") " + "Expected [Not Null] but was ["
						+ baseUOM + "]");
			}

			jdaFooter.clickPackConfig();
			packConfigMaintenanceStepDefs.i_search_pack_config_id(packConfig);
			packConfigMaintenanceStepDefs.i_navigate_to_tracking_levels_page();
			int ratio1To2 = Utilities.convertStringToInteger(packConfigMaintenancePage.getRatio1To2());
			Thread.sleep(2000);
			maxQty = packConfigMaintenancePage.getRatio2To3();
			packConfigMaintenancePage.clickGeneraltab();

			if (caseRatio != ratio1To2) {
				failureList.add("Case ratio is not as expected for SKU (" + skuId + ") " + "Expected [" + ratio1To2
						+ "] but was [" + caseRatio + "]");
			}

			// getting details from SKU table
			jdaFooter.clickSku();
			skuMaintenancePage.searchSKUid(skuId);
			productGroup = skuMaintenancePage.getProductGroup();
			allocationGroup = skuMaintenancePage.getAllocationGroup();
			skuMaintenancePage.clickUserDefinedTab();
			String currentVintage = skuMaintenancePage.getCurrentVintage();
			skuMaintenancePage.clicksettings1Tab();

			if (!vintage.equals(null)) {
				if (!skuMaintenancePage.isCurrentVintage(currentVintage) == true) {
					failureList.add("Current Vintage should not be null in SKU table for(" + skuId + ") ");
				}
			}

			// map
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuId);
			lineItemsMap.put("QtyDue", qtyDue);
			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			lineItemsMap.put("MaxQtyCanBeRcvd", maxQty);
			lineItemsMap.put("Allocation group", allocationGroup);
			lineItemsMap.put("Product group", productGroup);
			lineItemsMap.put("Vintage", vintage);
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
			context.setPurchaseOrderMap(purchaseOrderMap);

			jdaFooter.clickPreAdiceLine();
			jdaFooter.clickNextRecord();
			preAdviceLinePage.clickGeneralTab();
		}
		Assert.assertTrue("Purchase Order line detailes are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

		System.out.println("Map " + context.getPurchaseOrderMap());
	}
}
