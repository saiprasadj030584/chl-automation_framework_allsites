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
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.foods.PopUpPage;
import com.jda.wms.pages.foods.PreAdviceLinePage;
import com.jda.wms.pages.foods.SKUMaintenancePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceLineMaintenanceStepDefs {

	private final PreAdviceLinePage preAdviceLinePage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final PackConfigMaintenancePage packConfigMaintenancePage;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs;
	private Context context;
	private final SKUMaintenancePage skuMaintenancePage;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private PopUpPage popUpPage;

	@Inject
	public PreAdviceLineMaintenanceStepDefs(PreAdviceLinePage preAdviceLinePage, JDAFooter jdaFooter,
			JdaHomePage jdaHomePage, PackConfigMaintenancePage packConfigMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs,
			Context context, SKUMaintenancePage skuMaintenancePage, PopUpPage popUpPage) {
		this.preAdviceLinePage = preAdviceLinePage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.packConfigMaintenancePage = packConfigMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.packConfigMaintenanceStepDefs = packConfigMaintenanceStepDefs;
		this.context = context;
		this.skuMaintenancePage = skuMaintenancePage;
		this.popUpPage = popUpPage;
	}

	@Given("^the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items$")
	public void the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_line_items()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		
		String currentVintage =null, skuId = null, abv = null, qtyDue = null, vintage = null, allocationGroup = null, productGroup = null, maxQty = null;
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		int caseRatio = 0;

		jdaHomeStepDefs.i_am_on_pack_config_maintenance_page();
		jdaHomePage.navigateToSKUMaintanence();

		jdaHomePage.navigateToPreAdviceLineMaintenance();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();

		if (context.getNoOfLines() != 1) {
			preAdviceLinePage.selectFirstRecord();
		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			skuId = preAdviceLinePage.getSkuId();
			qtyDue = preAdviceLinePage.getQtyDue();
			String packConfig = preAdviceLinePage.getPackConfig();

			preAdviceLinePage.clickUserDefinedTab();
			if (!context.getProductCategory().equals("Ambient")){
			vintage = preAdviceLinePage.getVintage();
			}

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
			jdaFooter.clickQueryButton();
			skuMaintenancePage.enterSKUID(skuId);
			jdaFooter.clickExecuteButton();
			productGroup = skuMaintenancePage.getProductGroup();
			allocationGroup = skuMaintenancePage.getAllocationGroup();
			
			if (context.getProductCategory().equals("Ambient")) {
				if ((productGroup.equals("F20")) || (productGroup.equals("F21")) || (productGroup.equals("F23"))
						|| (productGroup.equals("F07"))) {
					failureList
							.add("Product Group not displayed as expected for Ambient. Expected [Not F20 or F21 or F23 or F07] but was ["
									+ productGroup);
				}
			} else if (context.getProductCategory().contains("BWS")) {
				if ((!productGroup.equals("F20")) && (!productGroup.equals("F21")) && (!productGroup.equals("F23"))
						&& (!productGroup.equals("F07"))) {
					failureList
							.add("Product Group not displayed as expected for BWS. Expected [F20 or F21 or F23 or F07] but was ["
									+ productGroup);
				}
			}
			
			skuMaintenancePage.clickUserDefined();
			if (!context.getProductCategory().equals("Ambient")){
			currentVintage = skuMaintenancePage.getCurrentVintage();
			
			skuMaintenancePage.clickCustomsAndExcise();
			abv = skuMaintenancePage.getCEAlcoholicStrength();
			
			//comparing vintage with Pre-advcie line & sku table
			if (!vintage.equals(null)) {
				if (!skuMaintenancePage.isCurrentVintage(currentVintage) == true) {
					failureList.add("Current Vintage should not be null in SKU table for(" + skuId + ") ");
				}
			}
			}
			
			skuMaintenancePage.clicksettings1Tab();

			// map
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuId);
			lineItemsMap.put("QtyDue", qtyDue);
			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			lineItemsMap.put("MaxQtyCanBeRcvd", maxQty);
			lineItemsMap.put("Allocation Group", allocationGroup);
			lineItemsMap.put("Product Group", productGroup);
			lineItemsMap.put("Vintage", vintage);
			lineItemsMap.put("ABV", abv);
			
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
			
			jdaFooter.clickPreAdiceLine();
			jdaFooter.clickNextRecord();
			preAdviceLinePage.clickGeneralTab();
			logger.debug("Pre-Advice Line level information of SKU : " + skuId);
			logger.debug("Quantity Due: " + qtyDue);
			logger.debug("Pack Config : " + packConfig);
			logger.debug("CaseRatio: " + caseRatio);
			logger.debug("Product group: " + productGroup);
			logger.debug("Vintage in Pre-Advice Line : " + vintage);
			logger.debug("Current Vintage in SKU table: " + currentVintage);
		}
		context.setPurchaseOrderMap(purchaseOrderMap);
		Assert.assertTrue("Purchase Order line detailes are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
		
		// To get the number of tagIds for every SKU
				Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
				int totalTagsperPo = 0;
				for (int i = 1; i <= context.getNoOfLines(); i++) {
					ArrayList<String> tagIDArrayList = new ArrayList<String>();
					String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
					int quantitytyDue = Utilities.convertStringToInteger((purchaseOrderMap.get(String.valueOf(i)).get("QtyDue")));
					int maxQtyRcv = Utilities.convertStringToInteger((purchaseOrderMap.get(String.valueOf(i)).get("MaxQtyCanBeRcvd")));
					int noOfTagID;
					if (quantitytyDue % maxQtyRcv > 0) {
						noOfTagID = (quantitytyDue / maxQtyRcv) + 1;
					} else {
						noOfTagID = quantitytyDue / maxQtyRcv;
					}
					
					for (int t = 0; t < noOfTagID; t++) {
						totalTagsperPo++;
						Thread.sleep(1000);
						tagIDArrayList.add(Utilities.getTenDigitRandomNumber());
					}
					tagIDMap.put(skuID, tagIDArrayList);
				}
				context.setTagIDMap(tagIDMap);
				
				// To get the qty to receive for each tag
				Map<String, Integer> qtyReceivedPerTagMap = new HashMap<String, Integer>();
				
				for (int s = 1; s <= tagIDMap.size(); s++) {
					String skuCurrent = purchaseOrderMap.get(String.valueOf(s)).get("SKU");
					for (int t = 0; t < tagIDMap.get(skuCurrent).size(); t++) {
						String currentTag = tagIDMap.get(skuCurrent).get(t);
						qtyReceivedPerTagMap.put(currentTag, 0);
					}
				}
				context.setQtyReceivedPerTagMap(qtyReceivedPerTagMap);

		System.out.println("Purchase order Map " + context.getPurchaseOrderMap());
		System.out.println("Tag ID Map "+context.getTagIDMap());
		System.out.println("Quantity Received Per Tag Map "+context.getQtyReceivedPerTagMap());
	}


	@Given("^the PO should have pre-advice line items$")
	public void the_PO_should_have_pre_advice_line_items() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		String skuId =null;

//		jdaHomePage.navigateToPreAdviceLineMaintenance();
//		jdaFooter.clickQueryButton();
//		preAdviceLinePage.enterPreAdviceID(context.getPreAdviceId());
//		jdaFooter.clickExecuteButton();
//
//		if (context.getNoOfLines() != 1) {
//			preAdviceLinePage.selectFirstRecord();
//		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
//			skuId = preAdviceLinePage.getSkuId();
			Map<String, String> lineItemsMap = new HashMap<String, String>();
//			lineItemsMap.put("SKU", skuId);
			lineItemsMap.put("SKU", "21036013");
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
		}
		context.setPurchaseOrderMap(purchaseOrderMap);
//		Assert.assertNotNull("SKU is not displayed as expected. Expected [Not Null] but was ["+skuId, skuId);
	}
	
	@When("^I search the pre-advice id \"([^\"]*)\" and SKU id \"([^\"]*)\" in pre-advice line maintenance page$")
	public void i_search_pre_advice_id_and_sku_id(String preAdviceId, String skuId) throws Throwable {
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceId(preAdviceId);
		preAdviceLinePage.enterSKUId(skuId);
		jdaFooter.clickExecuteButton();
	}

	@When("^I lock the record with lockcode as \"([^\"]*)\"$")
	public void i_lock_the_record_with_lockcode_as(String lockCode) throws Throwable {
		jdaFooter.clickUpdateButton();
		preAdviceLinePage.enterLockCode(lockCode);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
		popUpPage.clickYes();
	}

	@Then("^the record should be locked$")
	public void the_record_should_be_locked() throws Throwable {
		Assert.assertEquals("Lock code is not displayed as expected.", context.getLockCode(),
				preAdviceLinePage.getLockCode());
	}

	@Then("^the pre-advice line items should be linked with the consignment$")
	public void the_pre_advice_line_items_should_be_linked_with_theconsignment() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setNoOfLines(1);
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLinePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();

		if (context.getNoOfLines() != 1) {
			preAdviceLinePage.selectFirstRecord();
		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String consignmentID = preAdviceLinePage.getConsignmentID();
			if (!context.getConsignmentID().equals(consignmentID)) {
				failureList.add("Consignment ID in line " + i + " is not displayed as expected. Expected ["
						+ context.getConsignmentID() + "] but was [" + consignmentID + ") ");
				jdaFooter.clickNextRecord();
			}
		}
		Assert.assertTrue("Consignment ID is not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

}
