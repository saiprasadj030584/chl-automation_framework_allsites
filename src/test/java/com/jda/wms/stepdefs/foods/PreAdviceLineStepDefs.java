package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.foods.PreAdviceLinePage;

import cucumber.api.java.en.Given;

public class PreAdviceLineStepDefs {

	private final PreAdviceLinePage preAdviceLinePage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final PackConfigMaintenancePage packConfigMaintenancePage;

	@Inject
	public PreAdviceLineStepDefs(PreAdviceLinePage preAdviceLinePage, JDAFooter jdaFooter, JdaHomePage jdaHomePage,
			PackConfigMaintenancePage packConfigMaintenancePage) {
		this.preAdviceLinePage = preAdviceLinePage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.packConfigMaintenancePage = packConfigMaintenancePage;
	}

	@Given("^the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items$")
	public void the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_line_items()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		int numberOfRecords = 2;
		
		jdaHomePage.navigateToPreAdviceLinePage();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceID("8050004526");
		jdaFooter.clickExecuteButton();

		preAdviceLinePage.selectFirstRecord();

		for (int i = 1; i < numberOfRecords; i++) {

			String skuId = preAdviceLinePage.getSkuId();
			String qtyDue = preAdviceLinePage.getQtyDue();
			String trackingLevel = preAdviceLinePage.getTrackingLevel();
			String packConfig = preAdviceLinePage.getPackConfig();
			String underBond = preAdviceLinePage.getUnderBond();

			preAdviceLinePage.ClickUserDefinedTab();
			String caseRatio = preAdviceLinePage.getCaseRatio();
			String baseUOM = preAdviceLinePage.getBaseUOM();
			String maxQty = packConfigMaintenancePage.getRatio2To3();
			String ratio1To2 = packConfigMaintenancePage.getRatio1To2();
			
			if (!caseRatio.equals(ratio1To2)) {
				failureList.add("Case ratio is not as expected for SKU (" + skuId + ") " + "Expected [" + caseRatio
						+ "] but was [" + ratio1To2 + "]");
			}

			if (baseUOM.isEmpty()) {
				failureList.add("Base UOM is not as expected for SKU (" + skuId + ") " + "Expected [Not Null] but was ["
						+ baseUOM + "]");
			}

			jdaFooter.clickNextRecord();
		}
		Assert.assertTrue("Purchase Order line detailes are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

}
