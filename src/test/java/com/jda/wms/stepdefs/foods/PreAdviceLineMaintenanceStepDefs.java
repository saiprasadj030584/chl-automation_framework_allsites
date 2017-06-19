package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.foods.DeleteDataFromDB;
import com.jda.wms.dataload.foods.InsertDataIntoDB;
import com.jda.wms.dataload.foods.SelectDataFromDB;
import com.jda.wms.db.PackConfigMaintenanceDB;
import com.jda.wms.db.PreAdviceLineDB;
import com.jda.wms.db.SkuMaintenanceDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.foods.PreAdviceLineMaintenancePage;
import com.jda.wms.pages.foods.SKUMaintenancePage;
import com.jda.wms.pages.foods.WarningPopUpPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PreAdviceLineMaintenanceStepDefs {
	private final PreAdviceLineMaintenancePage preAdviceLineMaintenancePage;
	private final PreAdviceLineDB preAdviceLineDB;
	private WarningPopUpPage warningPopUpPage;
	private final JDAFooter jdaFooter;
	private final JdaHomePage jdaHomePage;
	private final PackConfigMaintenancePage packConfigMaintenancePage;
	private final PackConfigMaintenanceDB packConfigMaintenanceDB;
	private final JDAHomeStepDefs jdaHomeStepDefs;
	private final PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs;
	private Context context;
	private final SKUMaintenancePage skuMaintenancePage;
	private final SkuMaintenanceDB skuMaintenanceDB;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	Date date = new Date();
	private InsertDataIntoDB insertDataIntoDB;
	private DeleteDataFromDB deleteDataFromDB;
	private SelectDataFromDB selectDataFromDB;

	@Inject

	public PreAdviceLineMaintenanceStepDefs(PreAdviceLineMaintenancePage preAdviceLineMaintenancePage,
			JDAFooter jdaFooter, JdaHomePage jdaHomePage, PackConfigMaintenancePage packConfigMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, PackConfigMaintenanceStepDefs packConfigMaintenanceStepDefs,
			Context context, PackConfigMaintenanceDB packConfigMaintenanceDB, SKUMaintenancePage skuMaintenancePage,
			WarningPopUpPage warningPopUpPage, PreAdviceLineDB preAdviceLineDB, SkuMaintenanceDB skuMaintenanceDB,
			InsertDataIntoDB insertDataIntoDB, DeleteDataFromDB deleteDataFromDB, SelectDataFromDB selectDataFromDB) {
		this.jdaFooter = jdaFooter;
		this.packConfigMaintenancePage = packConfigMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.warningPopUpPage = warningPopUpPage;
		this.context = context;
		this.preAdviceLineMaintenancePage = preAdviceLineMaintenancePage;
		this.jdaHomePage = jdaHomePage;
		this.packConfigMaintenanceStepDefs = packConfigMaintenanceStepDefs;
		this.context = context;
		this.skuMaintenancePage = skuMaintenancePage;
		this.preAdviceLineDB = preAdviceLineDB;
		this.packConfigMaintenanceDB = packConfigMaintenanceDB;
		this.skuMaintenanceDB = skuMaintenanceDB;
		this.insertDataIntoDB = insertDataIntoDB;
		this.deleteDataFromDB = deleteDataFromDB;
		this.selectDataFromDB = selectDataFromDB;
	}

	@Given("^the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items$")
	public void the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_line_items()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String currentVintage = null, skuId = null, abv = null, qtyDue = null, vintage = null, allocationGroup = null,
				productGroup = null, maxQty = null;
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		int caseRatio = 0;

		jdaHomeStepDefs.i_am_on_pack_config_maintenance_page();
		jdaHomePage.navigateToSKUMaintanence();

		jdaHomePage.navigateToPreAdviceLineMaintenance();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();

		if (context.getNoOfLines() != 1) {
			preAdviceLineMaintenancePage.selectFirstRecord();
		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			skuId = preAdviceLineMaintenancePage.getSkuId();
			qtyDue = preAdviceLineMaintenancePage.getQtyDue();
			String packConfig = preAdviceLineMaintenancePage.getPackConfig();

			preAdviceLineMaintenancePage.clickUserDefinedTab();
			if (!context.getProductCategory().equals("Ambient")) {
				vintage = preAdviceLineMaintenancePage.getVintage();
			}

			caseRatio = Utilities.convertStringToInteger(preAdviceLineMaintenancePage.getCaseRatio());
			String baseUOM = preAdviceLineMaintenancePage.getBaseUOM();
			// to be used for BWS PO processing
			// String underBond = preAdviceLinePage.getUnderBond();
			// String trackingLevel = preAdviceLinePage.getTrackingLevel();

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
						&& (!productGroup.equals("F13"))) {
					failureList
							.add("Product Group not displayed as expected for BWS. Expected [F20 or F21 or F23 or F07] but was ["
									+ productGroup);
				}
			}

			skuMaintenancePage.clickUserDefined();
			if (!context.getProductCategory().equals("Ambient")) {
				currentVintage = skuMaintenancePage.getCurrentVintage();

				skuMaintenancePage.clickCustomsAndExcise();
				abv = skuMaintenancePage.getCEAlcoholicStrength();

				// comparing vintage with Pre-advcie line & sku table
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
			lineItemsMap.put("AllocationGroup", allocationGroup);
			lineItemsMap.put("Product Group", productGroup);
			lineItemsMap.put("Vintage", vintage);
			lineItemsMap.put("ABV", abv);
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
			jdaFooter.clickPreAdviceLine();
			jdaFooter.clickNextRecord();

			preAdviceLineMaintenancePage.clickGeneralTab();

			logger.debug("Pre-Advice Line level information of SKU : " + skuId);
			logger.debug("Quantity Due: " + qtyDue);
			logger.debug("Pack Config : " + packConfig);
			logger.debug("CaseRatio: " + caseRatio);
			logger.debug("Product group: " + productGroup);
			logger.debug("Vintage in Pre-Advice Line : " + vintage);
			logger.debug("Current Vintage in SKU table: " + currentVintage);

			preAdviceLineMaintenancePage.clickGeneralTab();
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
			int quantitytyDue = Utilities
					.convertStringToInteger((purchaseOrderMap.get(String.valueOf(i)).get("QtyDue")));
			int maxQtyRcv = Utilities
					.convertStringToInteger((purchaseOrderMap.get(String.valueOf(i)).get("MaxQtyCanBeRcvd")));
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
		logger.debug("Map: " + purchaseOrderMap.toString());
	}

	@Given("^the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items$")
	public void the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_lines_items()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuID = new ArrayList<String>();
		String currentVintage = null, skuId = null, abv = null, qtyDue = null, vintage = null, allocationGroup = null,
				productGroup = null, maxQty = null;
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		int caseRatio = 0;

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			skuID = preAdviceLineDB.getSkuId(context.getPreAdviceId());
			String sKuId = skuID.get(i - 1);
			context.setSkuId(sKuId);
			qtyDue = preAdviceLineDB.getQtyDue(context.getPreAdviceId(), skuID.get(i - 1));
			String packConfig = preAdviceLineDB.getPackConfig(context.getPreAdviceId(), skuID.get(i - 1));

			if (!context.getProductCategory().equals("Ambient")) {
				vintage = preAdviceLineDB.getVintage(context.getPreAdviceId(), skuID.get(i - 1));
				currentVintage = skuMaintenanceDB.getCurrentVintage(skuID.get(i - 1));
				abv = skuMaintenanceDB.getCEAlcoholicStrength(skuID.get(i - 1));

				if (!vintage.equals(null)) {
					if (null==currentVintage) {
						failureList.add(
								"Current Vintage should not be null in SKU table for(" + context.getSkuId() + ") ");
					}
				}
			}

			caseRatio = Utilities
					.convertStringToInteger(preAdviceLineDB.getCaseRatio(context.getPreAdviceId(), context.getSkuId()));
			String baseUOM = preAdviceLineDB.getBaseUOM(context.getPreAdviceId(), skuID.get(i - 1));

			if (baseUOM.isEmpty()) {
				failureList.add("Base UOM is not as expected for SKU (" + skuId + ") " + "Expected [Not Null] but was ["
						+ baseUOM + "]");
			}

			int ratio1To2 = Utilities.convertStringToInteger(packConfigMaintenanceDB.getRatio1To2(packConfig));
			Thread.sleep(2000);
			maxQty = packConfigMaintenanceDB.getRatio2To3(packConfig);

			if (caseRatio != ratio1To2) {
				failureList.add("Case ratio is not as expected for SKU (" + context.getSkuId() + ") " + "Expected ["
						+ ratio1To2 + "] but was [" + caseRatio + "]");
			}

			productGroup = skuMaintenanceDB.getProductGroup(skuID.get(i - 1));
			allocationGroup = skuMaintenanceDB.getAllocationGroup(skuID.get(i - 1));

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

			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuID.get(i - 1));
			lineItemsMap.put("QtyDue", qtyDue);
			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			lineItemsMap.put("MaxQtyCanBeRcvd", maxQty);
			lineItemsMap.put("Allocation Group", allocationGroup);
			lineItemsMap.put("Product Group", productGroup);
			lineItemsMap.put("Vintage", vintage);
			lineItemsMap.put("ABV", abv);
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);

			logger.debug(purchaseOrderMap.toString());

		}
		context.setPurchaseOrderMap(purchaseOrderMap);
		Assert.assertTrue("Purchase Order line detailes are not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

		// To get the number of tagIds for every SKU
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		int totalTagsPerPO = 0;
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			ArrayList<String> tagIDArrayList = new ArrayList<String>();
			String skuIDs = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			int quantitytyDue = Utilities
					.convertStringToInteger((purchaseOrderMap.get(String.valueOf(i)).get("QtyDue")));
			int maxQtyRcv = Utilities
					.convertStringToInteger((purchaseOrderMap.get(String.valueOf(i)).get("MaxQtyCanBeRcvd")));
			int noOfTagID;
			if (quantitytyDue % maxQtyRcv > 0) {
				noOfTagID = (quantitytyDue / maxQtyRcv) + 1;
			} else {
				noOfTagID = quantitytyDue / maxQtyRcv;
			}

			for (int t = 0; t < noOfTagID; t++) {
				totalTagsPerPO++;
				Thread.sleep(1000);
				tagIDArrayList.add(Utilities.getTenDigitRandomNumber());
			}
			tagIDMap.put(skuIDs, tagIDArrayList);

		}
		context.setTagIDMap(tagIDMap);

		// To get the qty to receive for each tag
		Map<String, Integer> qtyReceivedPerTagMap = new HashMap<String, Integer>();

		for (int s = 1; s <= tagIDMap.size(); s++) {
			String skuCurrent = purchaseOrderMap.get(String.valueOf(s)).get("SKU");
			for (int t = 0; t < tagIDMap.get(skuCurrent).size(); t++) {
				qtyReceivedPerTagMap.put(tagIDMap.get(skuCurrent).get(t), 0);
			}
		}
		context.setQtyReceivedPerTagMap(qtyReceivedPerTagMap);
		logger.debug("Map: " + purchaseOrderMap.toString());
	}

	@Then("^the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM and vintage details for selected pre-advice line item$")
	public void the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_and_vintage_details_for_selected_pre_advice_line_item()
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String currentVintage = null, skuId = null, abv = null, qtyDue = null, vintage = null, allocationGroup = null,
				productGroup = null, maxQty = null;
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		int caseRatio = 0;
		Boolean firstRecordSelected = false;

		jdaHomeStepDefs.i_am_on_pack_config_maintenance_page();
		jdaHomePage.navigateToSKUMaintanence();

		jdaHomePage.navigateToPreAdviceLineMaintenance();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();
		int NoOfLines = context.getNoOfLines();

		if (NoOfLines > 1) {
			preAdviceLineMaintenancePage.selectFirstRecord();
			firstRecordSelected = true;
		} else {
			failureList.add("Number of Line items is not as expected. Expected [>1] but was [" + NoOfLines + "]");
		}

		// Receive only one line item. Runs loop only for first record
		if (firstRecordSelected) {
			skuId = preAdviceLineMaintenancePage.getSkuId();
			qtyDue = preAdviceLineMaintenancePage.getQtyDue();
			String packConfig = preAdviceLineMaintenancePage.getPackConfig();

			preAdviceLineMaintenancePage.clickUserDefinedTab();

			if (!context.getProductCategory().equals("Ambient")) {
				vintage = preAdviceLineMaintenancePage.getVintage();
			}

			caseRatio = Utilities.convertStringToInteger(preAdviceLineMaintenancePage.getCaseRatio());
			String baseUOM = preAdviceLineMaintenancePage.getBaseUOM();

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
			if (!context.getProductCategory().equals("Ambient")) {
				currentVintage = skuMaintenancePage.getCurrentVintage();

				skuMaintenancePage.clickCustomsAndExcise();
				abv = skuMaintenancePage.getCEAlcoholicStrength();

				// comparing vintage with Pre-advcie line & sku table
				if (!vintage.equals(null)) {
					if (!skuMaintenancePage.isCurrentVintage(currentVintage) == true) {
						failureList.add("Current Vintage should not be null in SKU table for(" + skuId + ") ");
					}
				}
			}

			// Map for line item
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuId);
			lineItemsMap.put("QtyDue", qtyDue);
			lineItemsMap.put("CaseRatio", String.valueOf(caseRatio));
			lineItemsMap.put("MaxQtyCanBeRcvd", maxQty);
			lineItemsMap.put("Allocation Group", allocationGroup);
			lineItemsMap.put("Product Group", productGroup);
			lineItemsMap.put("Vintage", vintage);
			lineItemsMap.put("ABV", abv);
			purchaseOrderMap.put(String.valueOf(1), lineItemsMap);

			// Print values in log file
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

		// To get the number of tagIds for first SKU
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		if (firstRecordSelected) {
			ArrayList<String> tagIDArrayList = new ArrayList<String>();
			String skuID = purchaseOrderMap.get(String.valueOf(1)).get("SKU");


			int quantityDue = Utilities.convertStringToInteger((purchaseOrderMap.get(String.valueOf(1)).get("QtyDue")));
			int maxQtyRcv = Utilities
					.convertStringToInteger((purchaseOrderMap.get(String.valueOf(1)).get("MaxQtyCanBeRcvd")));
			int noOfTagID;
			if (quantityDue % maxQtyRcv > 0) {
				noOfTagID = (quantityDue / maxQtyRcv) + 1;
			} else {
				noOfTagID = quantityDue / maxQtyRcv;
			}

			for (int t = 0; t < noOfTagID; t++) {
				Thread.sleep(1000);
				tagIDArrayList.add(Utilities.getTenDigitRandomNumber());
			}
			tagIDMap.put(skuID, tagIDArrayList);
		}
		context.setTagIDMap(tagIDMap);

		// To get the qty to receive for each tag
		Map<String, Integer> qtyReceivedPerTagMap = new HashMap<String, Integer>();

		for (int s = 1; s <= tagIDMap.size(); s++) {
			String skuCurrent = purchaseOrderMap.get(String.valueOf(1)).get("SKU");
			for (int t = 0; t < tagIDMap.get(skuCurrent).size(); t++) {
				String currentTag = tagIDMap.get(skuCurrent).get(t);
				qtyReceivedPerTagMap.put(currentTag, 0);
			}
		}
		context.setQtyReceivedPerTagMap(qtyReceivedPerTagMap);

		logger.debug("Map: " + purchaseOrderMap.toString());
	}

	@Given("^the PO should have pre-advice line items$")
	public void the_PO_should_have_pre_advice_line_items() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		ArrayList skuList = new ArrayList();
		skuList = preAdviceLineDB.getSkuId(context.getPreAdviceId());
		Assert.assertEquals("Line items does not match with SKu items", context.getNoOfLines(), skuList.size());
		for (int i = 0; i < context.getNoOfLines(); i++) {
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", (String) skuList.get(i));
			purchaseOrderMap.put(String.valueOf(i + 1), lineItemsMap);
		}
		context.setPurchaseOrderMap(purchaseOrderMap);
	}

	@When("^I search the pre-advice id \"([^\"]*)\" and SKU id \"([^\"]*)\" in pre-advice line maintenance page$")
	public void i_search_pre_advice_id_and_sku_id(String preAdviceId, String skuId) throws Throwable {
		// ------------Data Setup-----------
		deleteDataFromDB.deletePreAdviceHeader(preAdviceId);
		insertDataIntoDB.insertPreAdviceHeader(preAdviceId);
		insertDataIntoDB.insertPreAdviceLine(preAdviceId, "Ambient");
		Thread.sleep(3000);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(preAdviceId));
		// ------------Data Setup-----------

		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceID(preAdviceId);
		preAdviceLineMaintenancePage.enterSKUId(skuId);
		jdaFooter.clickExecuteButton();
	}

	@When("^I lock the record with lockcode as \"([^\"]*)\"$")
	public void i_lock_the_record_with_lockcode_as(String lockCode) throws Throwable {
		jdaFooter.clickUpdateButton();
		preAdviceLineMaintenancePage.enterLockCode(lockCode);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
		warningPopUpPage.clickYes();
	}

	@Then("^the record should be locked$")
	public void the_record_should_be_locked() throws Throwable {
		Assert.assertEquals("Lock code is not displayed as expected.", context.getLockCode(),
				preAdviceLineMaintenancePage.getLockCode());
	}

	@Given("^the sku \"([^\"]*)\" of pre-advice id \"([^\"]*)\" have the pallet type as \"([^\"]*)\"$")
	public void the_sku_of_pre_advice_id_have_the_pallet_type_as(String preAdviceId, String sku,
			String existingPalletType) throws Throwable {
		// ------------Data Setup-----------
		deleteDataFromDB.deletePreAdviceHeader(preAdviceId);
		insertDataIntoDB.insertPreAdviceHeader(preAdviceId);
		insertDataIntoDB.insertPreAdviceLine(preAdviceId, "Ambient");
		Thread.sleep(3000);
		Assert.assertTrue("Test Data not available - Issue in Data loading",
				selectDataFromDB.isPreAdviceRecordExists(preAdviceId));
		// ------------Data Setup-----------
		context.setPreAdviceId(preAdviceId);
		context.setSkuId(sku);
		preAdviceLineDB.getPalletType(preAdviceId, sku);
		if (!existingPalletType.equals(preAdviceLineDB.getPalletType(preAdviceId, sku))) {
			jdaFooter.clickUpdateButton();
			preAdviceLineMaintenancePage.enterPalletType(existingPalletType);
			jdaFooter.clickExecuteButton();
			warningPopUpPage.clickYes();
			Thread.sleep(3000);
		}
	}

	@When("^I update the pallet type as \"([^\"]*)\"$")
	public void i_update_the_pallet_type_as(String palletType) throws Throwable {
		context.setPalletType(palletType);
		jdaFooter.clickUpdateButton();
		preAdviceLineMaintenancePage.enterPalletType(palletType);
		jdaFooter.clickExecuteButton();
		warningPopUpPage.clickYes();
		Thread.sleep(3000);
	}

	@Then("^the pallet type should be updated")
	public void the_pallet_type_should_be_updated() throws Throwable {
		Assert.assertEquals("Pallet type is not as expected", context.getPalletType(),
				preAdviceLineDB.getPalletType(context.getPreAdviceId(), context.getSkuId()));
	}

	@Then("^the pre-advice line items should be linked with the consignment$")
	public void the_pre_advice_line_items_should_be_linked_with_theconsignment() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			ArrayList<String> consignmentID = preAdviceLineDB.getConsignmentID(context.getPreAdviceId());
			consignmentID.forEach(consignment -> {
				if (!context.getConsignmentID().equals(consignment)) {
					failureList.add("Consignment ID in line is not displayed as expected. Expected ["
							+ context.getConsignmentID() + "] but was [" + consignmentID + ") ");
				}
			});
		}

		Assert.assertTrue("Consignment ID is not as expected" + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

	}

	@Given("^the PO should have the Sku,location,TagId,pallet type,vintage,Exp date,ABV,Case Ratio,qty due for the pre-advice line items$")
	public void the_PO_should_have_the_Sku_location_TagId_pallet_type_vintage_Exp_date_ABV_Case_Ratio_qty_due_for_the_pre_advice_line_items()
			throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		String skuId = null, abv = null, qtyDue = null, location = "REC002", palletType = "CHEP";
		int caseRatio = 0;

		context.setlocationID(location);
		context.setPalletType(palletType);
		jdaHomePage.navigateToSKUMaintanence();
		jdaHomePage.navigateToPreAdviceLineMaintenance();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();

		if (context.getNoOfLines() != 1) {
			preAdviceLineMaintenancePage.selectFirstRecord();
		}

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			skuId = preAdviceLineMaintenancePage.getSkuId();
			qtyDue = preAdviceLineMaintenancePage.getQtyDue();

			preAdviceLineMaintenancePage.clickUserDefinedTab();
			caseRatio = Utilities.convertStringToInteger(preAdviceLineMaintenancePage.getCaseRatio());

			jdaFooter.clickSku();
			jdaFooter.clickQueryButton();
			skuMaintenancePage.enterSKUID(skuId);
			jdaFooter.clickExecuteButton();
			skuMaintenancePage.clickCustomsAndExcise();
			abv = skuMaintenancePage.getCEAlcoholicStrength();

			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("SKU", skuId);
			lineItemsMap.put("QtyDue", qtyDue);
			lineItemsMap.put("ABV", abv);
			// lineItemsMap.put("UpdatedABV", updatedAbv);
			// lineItemsMap.put("CaseRatio", caseRatio);
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
			jdaFooter.clickPreAdviceLine();
			jdaFooter.clickNextRecord();
		}
		context.setPurchaseOrderMap(purchaseOrderMap);
		Assert.assertNotNull("SKU is not displayed as expected. Expected [Not Null] but was [" + skuId, skuId);
	}

	@Given("^I have the vintage for each line item$")
	public void i_have_the_vintage_for_each_line_item() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		purchaseOrderMap = context.getPurchaseOrderMap();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String vintage = purchaseOrderMap.get(String.valueOf(i)).get("Vintage");
			String newVintage = vintage + 1;
			context.setVintage(newVintage);
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("new Vintage", newVintage);

		}

	}
}
