
package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class PurchaseOrderReceivingStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private Context context;
	private Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, ArrayList<String>> tagIDMap;
	Map<String, Integer> qtyReceivedPerTagMap;
	static private boolean isFirstTagForLineItem = true;
	private PuttyFunctionsPage puttyFunctionsPage;
	private boolean puttyFlag = true;
	ArrayList<String> failureList = new ArrayList<String>();
	private Hooks hooks;

	@Inject
	public PurchaseOrderReceivingStepDefs(PurchaseOrderReceivingPage purchaseOrderReceivingPage, Context context,
			PuttyFunctionsPage puttyFunctionsPage, Hooks hooks) {
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.context = context;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.hooks = hooks;
	}

	@Given("^I want to receive the purchase order$")
	public void i_want_to_receive_the_purchase_order() throws InterruptedException {

		purchaseOrderMap = context.getPurchaseOrderMap();

		// To get the number of tagIds for every SKU
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		int totalTagsperPo = 0;
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			ArrayList<String> tagIDArrayList = new ArrayList<String>();
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			logger.debug("SKU " + skuID);
			int qtyDue = Integer.parseInt(purchaseOrderMap.get(String.valueOf(i)).get("QtyDue"));
			int maxQtyRcv = Integer.parseInt(purchaseOrderMap.get(String.valueOf(i)).get("MaxQtyCanBeRcvd"));
			int noOfTagID;
			if (qtyDue % maxQtyRcv > 0) {
				noOfTagID = (qtyDue / maxQtyRcv) + 1;
			} else {
				noOfTagID = qtyDue / maxQtyRcv;
			}
			logger.debug("No of tags" + noOfTagID);

			for (int t = 0; t < noOfTagID; t++) {
				totalTagsperPo++;
				Thread.sleep(1000);
				tagIDArrayList.add(Utilities.getTenDigitRandomNumber());
			}
			tagIDMap.put(skuID, tagIDArrayList);
		}
		context.setTagIDMap(tagIDMap);
		logger.debug("Tag Id Map: " + tagIDMap);
		logger.debug("totalTagsperPo: " + totalTagsperPo);

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
		logger.debug(qtyReceivedPerTagMap.toString());
	}

	@When("^I select user directed option in main menu$")
	public void i_select_user_directed_option_in_main_menu() throws Throwable {
		purchaseOrderReceivingPage.selectUserDirectedMenu();
		Assert.assertTrue("User Menu not displayed as expected", purchaseOrderReceivingPage.isUserMenuDisplayed());
	}

	@Given("^I receive the PO with basic and pre-advice receiving$")
	public void i_receive_the_po_with_basic_and_pre_advice_receiving() throws Throwable {
		purchaseOrderReceivingPage.selectReceiveMenu();
		Assert.assertTrue("Receive Menu not displayed as expected",
				purchaseOrderReceivingPage.isReceiveMenuDisplayed());

		purchaseOrderReceivingPage.selectBasicReceiveMenu();
		Assert.assertTrue("Basic Receive Menu not displayed as expected",
				purchaseOrderReceivingPage.isBasicReceiveMenuDisplayed());

		purchaseOrderReceivingPage.selectPreAdviceReceive();
	}

	@Then("^I should be directed to pre-advice entry page$")
	public void i_should_be_directed_to_pre_advice_entry_page() throws Throwable {
		Assert.assertTrue("Receive pre-Advice entry not displayed as expected.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
	}

	@When("^I enter pre-advice id \"([^\"]*)\" and SKU id")
	public void i_enter_pre_advice_id_and_SKU_id(String preAdviceId) throws Throwable {
		purchaseOrderReceivingPage.enterPreAdvId(preAdviceId);
		purchaseOrderReceivingPage.enterSKUId(context.getSkuId());
	}

	public void i_enter_pre_advice_id_and_SKU_id(String preAdviceId, String skuId) throws Throwable {
		purchaseOrderReceivingPage.enterPreAdvId(preAdviceId);
		purchaseOrderReceivingPage.enterSKUId(skuId);
	}

	@Then("^the pre-advice id and supplier id should be displayed in the receive pre-advice page$")
	public void the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		int timeInSec = 1;

		do {
			if (purchaseOrderReceivingPage.isSearchInfoDisplayed() != false) {
				Thread.sleep(1000);
				timeInSec++;
			}
		} while (timeInSec > 10);

		String preAdvId = purchaseOrderReceivingPage.getPreAdvId();
		logger.debug("Putty Advice ID : " + preAdvId);
		logger.debug("Web Advice ID : " + context.getPreAdviceId());

		if (!preAdvId.equalsIgnoreCase(context.getPreAdviceId())) {
			failureList.add("Pre-Advice ID not displayed as expected. Expected [" + context.getPreAdviceId()
					+ "] but was [" + preAdvId + "]");
		}

		String supplierId = purchaseOrderReceivingPage.getSupplierId();
		if (!supplierId.equalsIgnoreCase(context.getSupplierID())) {
			failureList.add("Supplier ID not displayed as expected. Expected [" + context.getSupplierID()
					+ "] but was [" + supplierId + "]");
		}

		String skuID = purchaseOrderReceivingPage.getSKUId();
		if (!skuID.equalsIgnoreCase(context.getSkuId())) {
			failureList.add("Supplier ID not displayed as expected. Expected [" + context.getSkuId() + "] but was ["
					+ skuID + "]");
		}

		Assert.assertTrue("Pre-Adv cmp page not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I enter the location \"([^\"]*)\" and tag$")
	public void i_enter_the_location_and_tag(String location) throws Throwable {
		purchaseOrderReceivingPage.enterLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();
		String skuID = purchaseOrderMap.get(String.valueOf(context.getLineItem())).get("SKU");
		String tagId = tagIDMap.get(skuID).get(context.getTagIdIndex());
		purchaseOrderReceivingPage.enterTagId(tagId);

		Assert.assertTrue("RcvPreCmp page 2 not displayed as expected",
				purchaseOrderReceivingPage.isRcvPreCmp2Displayed());
	}

	@When("^I enter the quantity to receive and case ratio$")
	public void i_enter_the_quantity_to_receive_and_case_ratio() throws Throwable {
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();
		qtyReceivedPerTagMap = context.getQtyReceivedPerTagMap();

		String lineItem = String.valueOf(context.getLineItem());
		int rcvQtyDue = 0;

		String currentSku = purchaseOrderMap.get(lineItem).get("SKU");
		String tagId = tagIDMap.get(currentSku).get(context.getTagIdIndex());
		context.setTagIdIndex(context.getTagIdIndex() + 1);

		if (isFirstTagForLineItem == true) {
			context.setRcvQtyDue(Utilities.convertStringToInteger(purchaseOrderMap.get(lineItem).get("QtyDue")));
			rcvQtyDue = context.getRcvQtyDue();
			isFirstTagForLineItem = false;
		} else {
			rcvQtyDue = context.getRcvQtyDue();
		}

		int maxQtyCanBeRcvd = Integer.parseInt(purchaseOrderMap.get(lineItem).get("MaxQtyCanBeRcvd"));
		int caseRatio = Integer.parseInt(purchaseOrderMap.get(lineItem).get("CaseRatio"));
		int qtyToReceive;
		Thread.sleep(2000);

		if (rcvQtyDue > maxQtyCanBeRcvd) {
			qtyToReceive = maxQtyCanBeRcvd;
			rcvQtyDue = rcvQtyDue - maxQtyCanBeRcvd;
		} else {
			qtyToReceive = rcvQtyDue;
			rcvQtyDue = 0;
			context.setLineItem(Integer.parseInt(lineItem) + 1);
			context.setTagIdIndex(0);
			isFirstTagForLineItem = true;
		}
		// To set the Qty received for every tag and to enter the quantity to be
		// received
		qtyReceivedPerTagMap.replace(tagId, qtyToReceive);
		context.setQtyReceivedPerTagMap(qtyReceivedPerTagMap);
		context.setRcvQtyDue(rcvQtyDue);

		purchaseOrderReceivingPage.enterQtyToReceive(String.valueOf(qtyToReceive));
		purchaseOrderReceivingPage.enterCaseRatio(String.valueOf(caseRatio));

		Assert.assertTrue("RcvPreCmp page 3 not displayed as expected",
				purchaseOrderReceivingPage.isRcvPreCmp3Displayed());
	}

	@When("^I enter the expiry  and vintage details$")
	public void i_enter_the_expiry_and_vintage_details() throws Throwable {
		if (context.getProductCategory().contains("BWS")) {
			purchaseOrderReceivingPage.enterVintage(context.getVintage());
			purchaseOrderReceivingPage.enterABV(context.getABV());
			puttyFunctionsPage.nextScreen();

			if (context.getAllocationGroup().equalsIgnoreCase("Expiry")) {
				String expDate = DateUtils.getAddedSystemYear();
				context.setFutureExpiryDate(expDate);
				puttyFunctionsPage.pressTab();
				puttyFunctionsPage.pressTab();
				purchaseOrderReceivingPage.enterExpiryDate(expDate);
				Thread.sleep(10000);
			} else {
				purchaseOrderReceivingPage.pressEnter();
			}
		} else if (context.getProductCategory().contains("Ambient")) {
			if (context.getAllocationGroup().equalsIgnoreCase("Expiry")) {
				puttyFunctionsPage.nextScreen();
				String expDate = DateUtils.getAddedSystemYear();
				context.setFutureExpiryDate(expDate);
				puttyFunctionsPage.pressTab();
				puttyFunctionsPage.pressTab();
				purchaseOrderReceivingPage.enterExpiryDate(expDate);
				Thread.sleep(10000);
			} else {
				purchaseOrderReceivingPage.pressEnter();
			}
		}
	}

	@When("^I enter the expiry ABV and vintage details$")
	public void i_enter_the_expiry_ABV_and_vintage_details() throws Throwable {
		if (context.getProductCategory().contains("BWS")) {
			String vintageValue = context.getVintage();

			if (!vintageValue.isEmpty()) {
				purchaseOrderReceivingPage.enterVintage(vintageValue);
				Thread.sleep(1000);
			} else {
				puttyFunctionsPage.pressTab();
			}

			String abvValue = context.getABV();
			if (!abvValue.isEmpty()) {
				purchaseOrderReceivingPage.enterABV(abvValue);
			}
			puttyFunctionsPage.nextScreen();
		}

		if (context.getProductCategory().contains("Ambient")) {
			puttyFunctionsPage.nextScreen();
		}

		if (context.getAllocationGroup().equalsIgnoreCase("Expiry")) {
			String expDate = DateUtils.getAddedSystemYear();
			context.setFutureExpiryDate(expDate);
			puttyFunctionsPage.pressTab();
			puttyFunctionsPage.pressTab();
			purchaseOrderReceivingPage.enterExpiryDate(expDate);
			Thread.sleep(10000);
		} else {
			puttyFunctionsPage.pressEnter();
		}
		Thread.sleep(1000);
	}

	@Then("^I should see the receiving completion$")
	public void i_should_see_the_receiving_completion() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
		Thread.sleep(5000);
		Assert.assertTrue("Receive not completed and Home page not displayed.["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
	}

	@When("^I receive all the skus for the purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_skus_for_the_purchase_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setSkuId(currentSku);
			context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(i)).get("Allocation Group"));
			context.setABV(purchaseOrderMap.get(String.valueOf(i)).get("ABV"));
			context.setVintage(purchaseOrderMap.get(String.valueOf(i)).get("Vintage"));
			for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
				i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId(), context.getSkuId());
				the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
				i_enter_the_location_and_tag(context.getLocation());
				i_enter_the_quantity_to_receive_and_case_ratio();
				i_enter_the_expiry_and_vintage_details();
				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for Sku id " + currentSku);
					context.setFailureList(failureList);
				}
				Thread.sleep(5000);
			}
		}
		puttyFunctionsPage.minimisePutty();
	}

	@When("^I receive the first sku of the purchase order at location \"([^\"]*)\"$")
	public void i_receive_the_first_sku_of_the_purchase_order_at_location(String location) throws Throwable {

		context.setLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		String currentSku = purchaseOrderMap.get(String.valueOf(1)).get("SKU");
		context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(1)).get("AllocationGroup"));
		context.setABV(purchaseOrderMap.get(String.valueOf(1)).get("ABV"));
		context.setVintage(purchaseOrderMap.get(String.valueOf(1)).get("Vintage"));
		context.setSkuId(currentSku);

		for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
			i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId(), context.getSkuId());
			the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
			i_enter_the_location_and_tag(context.getLocation());
			i_enter_the_quantity_to_receive_and_case_ratio();
			i_enter_the_expiry_ABV_and_vintage_details();
			i_should_see_the_receiving_completion();
		}
		puttyFunctionsPage.mimimizePuty();
	}

	@When("^I receive all the skus for the purchase order$")
	public void i_receive_all_the_skus_for_the_purchase_order() throws Throwable {
		purchaseOrderMap = context.getPurchaseOrderMap();
		ArrayList<String> failureList = new ArrayList<String>();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setSkuId(currentSku);
			i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId(), context.getSkuId());
			if (!purchaseOrderReceivingPage.isNoValidPreAdviceDisplayed()) {
				failureList.add("No Valid Pre advices message is not displayed for SKU " + currentSku);
			}
			purchaseOrderReceivingPage.pressEnter();
			Thread.sleep(5000);
		}
		context.setFailureList(failureList);
		puttyFunctionsPage.minimisePutty();
	}

	@Then("^I should see that no valid preadvices found message$")
	public void i_should_see_that_no_valid_preadvices_found_message() throws Throwable {
		Assert.assertTrue("No Valid Pre advices message is not displayed. ["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
	}

	@When("^I receive  the skus for each line item$")
	public void i_receive_the_skus_for_each_line_item() throws Throwable {
		purchaseOrderMap = context.getPurchaseOrderMap();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setSkuId(currentSku);
			i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
			String currentAbv = purchaseOrderMap.get(String.valueOf(i)).get("UpdatedABV");
			purchaseOrderReceivingPage.enterABV(currentAbv);
		}
		puttyFlag = false;
		puttyFunctionsPage.minimisePutty();
	}

	@Then("^I proceed to complete the receiving$")
	public void i_proceed_to_complete_the_receiving() throws Throwable {
		purchaseOrderReceivingPage.enterYes();
	}

	@Then("^the receiving should be completed$")
	public void the_receiving_should_be_completed() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
		Thread.sleep(3000);
		hooks.logoutPutty();
	}

	@Then("^the error message should be displayed$")
	public void the_error_message_should_be_displayed() throws Throwable {
		Assert.assertTrue(
				"Appropriate error is not displayed. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	@When("^I receive all the skus at location \"([^\"]*)\" with additional \"([^\"]*)\" % of its actual value$")
	public void i_receive_all_the_skus_at_location_with_additional_percentage_of_its_actual_value(String location,
			String percentage) throws Throwable {
		context.setABVPercentage(percentage);
		context.setLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(i)).get("Allocation Group"));

			String currentAbv = purchaseOrderMap.get(String.valueOf(i)).get("ABV");
			double abvFloat = (Float.parseFloat(currentAbv)
					+ (float) (Float.parseFloat(currentAbv) * (Float.parseFloat(context.getABVPercentage()) / 100.0f)));

			String highAbv = String.valueOf(Utilities.getRoundOffToTwoDecimal(abvFloat));
			context.setABV(highAbv);
			context.setVintage(purchaseOrderMap.get(String.valueOf(i)).get("Vintage"));

			for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
				i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
				the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
				i_enter_the_location_and_tag(context.getLocation());
				i_enter_the_quantity_to_receive_and_case_ratio();
				i_enter_the_expiry_and_vintage_details();

				if (Integer.parseInt(percentage) > 10) {
					if (!purchaseOrderReceivingPage.isMorePercentageAbv()) {
						failureList.add(
								"The difference in ABV is more than 10 % of the default ABV.Are you sure the value entered is correct message is not displayed.");
					}
					context.setFailureList(failureList);

				}
			}
		}
	}

	@When("^I receive all the skus at location \"([^\"]*)\" with incorrect vintage$")
	public void i_receive_all_the_skus_at_location_with_incorrect_vintage(String location) throws Throwable {
		context.setLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(i)).get("Allocation Group"));
			context.setABV(purchaseOrderMap.get(String.valueOf(i)).get("ABV"));
			context.setVintage(
					String.valueOf(Integer.parseInt(purchaseOrderMap.get(String.valueOf(i)).get("Vintage")) + 1));

			for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
				i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
				the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
				i_enter_the_location_and_tag(context.getLocation());
				i_enter_the_quantity_to_receive_and_case_ratio();
				i_enter_the_expiry_and_vintage_details();

				if (!purchaseOrderReceivingPage.isVintageNotExpectedDisplayed()) {
					failureList.add(
							"Vintage entered is not the same as expected in the PO. Please check and re-enter message is not displayed.");
				}
				context.setFailureList(failureList);
			}
		}
		puttyFlag = false;
		hooks.logoutPutty();
	}

	@When("^I receive  the skus for each line item at location \"([^\"]*)\" with no vintage$")
	public void i_receive_the_skus_for_each_line_item_at_location_with_no_vintage(String location) throws Throwable {
		context.setLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(i)).get("Allocation Group"));
			context.setABV(purchaseOrderMap.get(String.valueOf(i)).get("ABV"));

			for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
				i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
				the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
				i_enter_the_location_and_tag(context.getLocation());
				i_enter_the_quantity_to_receive_and_case_ratio();
				purchaseOrderReceivingPage.pressTab();
				i_enter_the_ABV_details();
				i_enter_the_expiry_details();

				if (!purchaseOrderReceivingPage.isEnterVintageForUpcDisplayed()) {
					failureList.add("Enter vintage for the UPC not displayed.");
				}
				context.setFailureList(failureList);
			}
		}
		puttyFlag = false;
		hooks.logoutPutty();
	}

	@When("^I enter the expiry details$")
	public void i_enter_the_expiry_details() throws Throwable {

		if (context.getAllocationGroup().equalsIgnoreCase("Expiry")) {
			String expDate = DateUtils.getAddedSystemYear();
			context.setFutureExpiryDate(expDate);
			purchaseOrderReceivingPage.pressTab();
			purchaseOrderReceivingPage.pressTab();
			purchaseOrderReceivingPage.enterExpiryDate(expDate);
			Thread.sleep(10000);
		}
	}

	@When("^I enter the ABV details$")
	public void i_enter_the_ABV_details() throws Throwable {
		if (context.getProductCategory().contains("BWS")) {
			purchaseOrderReceivingPage.enterABV(context.getABV());
			puttyFunctionsPage.nextScreen();
		} else if (context.getProductCategory().contains("Ambient")) {
			purchaseOrderReceivingPage.pressTab();
			Thread.sleep(10000);
			puttyFunctionsPage.nextScreen();
		}
	}

	@When("^I enter the vintage details$")
	public void i_enter_the_vintage_details() throws Throwable {
		if (context.getProductCategory().contains("BWS")) {
			purchaseOrderReceivingPage.enterVintage(context.getVintage());
			purchaseOrderReceivingPage.pressTab();
		} else if (context.getProductCategory().contains("Ambient")) {
			purchaseOrderReceivingPage.pressTab();
			Thread.sleep(10000);
		}
	}

	@When("^I receive all the skus for the purchase order at location \"([^\"]*)\" with no ABV$")
	public void i_receive_all_the_skus_for_the_purchase_order_at_location_with_no_ABV(String location)
			throws Throwable {
		context.setLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			String currentSku = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			context.setAllocationGroup(purchaseOrderMap.get(String.valueOf(i)).get("Allocation Group"));
			context.setVintage(purchaseOrderMap.get(String.valueOf(i)).get("Vintage"));

			for (int j = 0; j < tagIDMap.get(currentSku).size(); j++) {
				i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
				the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
				i_enter_the_location_and_tag(context.getLocation());
				i_enter_the_quantity_to_receive_and_case_ratio();
				i_enter_the_vintage_details();
				puttyFunctionsPage.nextScreen();
				i_enter_the_expiry_details();

				if (!purchaseOrderReceivingPage.isEnterABVForUpcDisplayed()) {
					failureList.add("Enter the ABV for the UPC being received");
				}
				context.setFailureList(failureList);

			}
		}
		puttyFlag = false;
		hooks.logoutPutty();
	}
}
