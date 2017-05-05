
package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
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
	private Configuration configuration;
	private Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, ArrayList<String>> tagIDMap;
//	private int tagCountForEachLineItem = 1;
//	ArrayList<String> tagIDArrayList = new ArrayList<String>();
//	ArrayList<Integer> tagCountPerSku = new ArrayList<Integer>();

	@Inject
	public PurchaseOrderReceivingStepDefs(PurchaseOrderReceivingPage purchaseOrderReceivingPage, Context context,
			 Configuration configuration) {
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.context = context;
//		this.utilities = utilities;
		this.configuration = configuration;
	}

	@Given("^I have logged in as warehouse user in Putty with host \"([^\"]*)\" and port \"([^\"]*)\"$")
	public void i_have_logged_in_as_warehouse_user_in_Putty_with_host_and_port(String host, String port)
			throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		purchaseOrderReceivingPage.invokePutty();
		purchaseOrderReceivingPage.loginPutty(host, port);
		Assert.assertTrue("Login page not displayed as expected", purchaseOrderReceivingPage.isLoginScreenDisplayed());

		purchaseOrderReceivingPage.enterJdaLogin(configuration.getStringProperty("username"),
				configuration.getStringProperty("password"));
		Thread.sleep(2000);

		if (!(purchaseOrderReceivingPage.isMainMenuDisplayed())) {
			failureList.add("Main Menu not displayed as expected");
		}

		Assert.assertTrue("Putty Login not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^I want to receive the purchase order \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void i_want_to_receive_the_purchase_order(String preAdviceId, String noOfLines, String supplierID) {
		context.setPreAdviceId(preAdviceId);
		context.setNoOfLines(Integer.parseInt(noOfLines));
		context.setSupplierID(supplierID);

		// Create Hash map to store line items
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			if (i == 1) {
				lineItemsMap.put("SKU", "21036013");
				lineItemsMap.put("QtyDue", "1500");
				lineItemsMap.put("RemainingQtyDue", "0");
				lineItemsMap.put("CaseRatio", "15");
				lineItemsMap.put("MaxQtyCanBeRcvd", "800");
			}
			if (i == 2) {
				lineItemsMap.put("SKU", "21036046");
				lineItemsMap.put("QtyDue", "1500");
				lineItemsMap.put("RemainingQtyDue", "1500");
				lineItemsMap.put("CaseRatio", "24");
				lineItemsMap.put("MaxQtyCanBeRcvd", "800");
			}
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
		}
		context.setPurchaseOrderMap(purchaseOrderMap);
		
		//To get the number of tagIds for every SKU
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		
		for (int i = 1; i <= context.getNoOfLines(); i++){
			ArrayList<String> tagIDArrayList = new ArrayList<String>();
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			int qtyDue = Integer.parseInt(purchaseOrderMap.get(String.valueOf(i)).get("QtyDue"));
			int maxQtyRcv = Integer.parseInt(purchaseOrderMap.get(String.valueOf(i)).get("MaxQtyCanBeRcvd"));
			int noOfTagID;
			if (qtyDue % maxQtyRcv>0){
				noOfTagID = (qtyDue / maxQtyRcv) + 1;
			}
			else {
				noOfTagID = qtyDue / maxQtyRcv;
			}
			for (int t=0;t<noOfTagID;t++){
				tagIDArrayList.add(Utilities.getTenDigitRandomNumber());
			}
			tagIDMap.put(skuID, tagIDArrayList);
		}
		context.setTagIDMap(tagIDMap);
		
		String lineItem = String.valueOf(context.getLineItem());
		context.setRcvQtyDue(Integer.parseInt(purchaseOrderMap.get(lineItem).get("RemainingQtyDue")));
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
		context.setPreAdviceId(preAdviceId);

		// Get SKU for corresponding line item
		purchaseOrderMap = context.getPurchaseOrderMap(); 
		String lineItem = String.valueOf(context.getLineItem());
		String skuID = purchaseOrderMap.get(lineItem).get("SKU");
		context.setSkuId(skuID);
		purchaseOrderReceivingPage.enterPreAdvId(preAdviceId);
		purchaseOrderReceivingPage.enterSKUId(skuID);
		logger.debug("Line Item: " + skuID);
		logger.debug("SKU ID: " + lineItem);
	}

	@Then("^the pre-advice id and supplier id should be displayed in the receive pre-advice page$")
	public void the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		String preAdvId = purchaseOrderReceivingPage.getPreAdvId();
		System.out.println("Preadv from screen: " + preAdvId);
		if (!preAdvId.equalsIgnoreCase(context.getPreAdviceId())) {
			failureList.add("Pre-Advice ID not displayed as expected. Expected [" + context.getPreAdviceId()
					+ "] but was [" + preAdvId + "]");
		}

		String supplierId = purchaseOrderReceivingPage.getSupplierId();
		System.out.println("Supplier from screen: " + supplierId);
		if (!supplierId.equalsIgnoreCase(context.getSupplierID())) {
			failureList.add("Supplier ID not displayed as expected. Expected [" + context.getSupplierID()
					+ "] but was [" + supplierId + "]");
		}

		String skuID = purchaseOrderReceivingPage.getSKUId();
		System.out.println("SKU from screen: " + skuID);
		if (!skuID.equalsIgnoreCase(context.getSkuId())) {
			failureList.add("Supplier ID not displayed as expected. Expected [" + context.getSkuId() + "] but was ["
					+ skuID + "]");
		}

		Assert.assertTrue("Pre-Adv cmp page not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^I enter the location \"([^\"]*)\" and tag$")
	public void i_enter_the_location_and_tag(String location) throws Throwable {
		context.setLocation(location);
		purchaseOrderReceivingPage.enterLocation(location);
		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();
		
		String lineItem = String.valueOf(context.getLineItem());
		System.out.println(lineItem);
		String skuID = purchaseOrderMap.get(lineItem).get("SKU");
		System.out.println(skuID);
		String tagId = tagIDMap.get(skuID).get(context.getTagIdIndex());
		System.out.println(tagId);
		purchaseOrderReceivingPage.enterTagId(tagId);
		context.setTagIdIndex(context.getTagIdIndex()+1);
		logger.debug("Tag ID: " + tagId);
		
		Assert.assertTrue("RcvPreCmp page 2 not displayed as expected",
				purchaseOrderReceivingPage.isRcvPreCmp2Displayed());
	}

	@When("^I enter the quantity to receive and case ratio$")
	public void i_enter_the_quantity_to_receive_and_case_ratio() throws Throwable {
		// Get QtyDue, Max qty that can be received & case ratio for the line
		// item
		purchaseOrderMap = context.getPurchaseOrderMap();
		String lineItem = String.valueOf(context.getLineItem());
		System.out.println(purchaseOrderMap);
		int rcvQtyDue = context.getRcvQtyDue();
		int maxQtyCanBeRcvd = Integer.parseInt(purchaseOrderMap.get(lineItem).get("MaxQtyCanBeRcvd"));
		int caseRatio = Integer.parseInt(purchaseOrderMap.get(lineItem).get("CaseRatio"));
		int qtyToReceive;

		if (rcvQtyDue > maxQtyCanBeRcvd) {
			qtyToReceive = maxQtyCanBeRcvd;
			rcvQtyDue = rcvQtyDue - maxQtyCanBeRcvd;
		} else {
			qtyToReceive = rcvQtyDue;
			rcvQtyDue = 0;
			context.setLineItem(Integer.parseInt(lineItem) + 1);
			context.setTagIdIndex(0);
		}
		
		context.setRcvQtyDue(rcvQtyDue);
		
		Map<String, String> lineItemsMap = new HashMap<String, String>();
		lineItemsMap.put("RemainingQtyDue", String.valueOf(rcvQtyDue));
		purchaseOrderMap.put(String.valueOf(lineItem), lineItemsMap);
		System.out.println("Current line item "+context.getLineItem());
		System.out.println("Qty To Receive: " + qtyToReceive);
		System.out.println("Remaining Qty Due: " + rcvQtyDue);
		logger.debug("Qty To Receive: " + qtyToReceive);
		logger.debug("Remaining Qty Due: " + rcvQtyDue);

		purchaseOrderReceivingPage.enterQtyToReceive(String.valueOf(qtyToReceive));
		purchaseOrderReceivingPage.enterCaseRatio(String.valueOf(caseRatio));

		Assert.assertTrue("RcvPreCmp page 3 not displayed as expected",
				purchaseOrderReceivingPage.isRcvPreCmp3Displayed());
	}

	@When("^I enter the expiry details$")
	public void i_enter_the_expiry_details() throws Throwable {
		String expDate = DateUtils.getAddedSystemYear();
		purchaseOrderReceivingPage.enterExpiryDate(expDate);
		Thread.sleep(12000);
	}

	@Then("^I should see the receiving completion$")
	public void i_should_see_the_receiving_completion() throws Throwable {
		Assert.assertTrue("Receive not completed and Home page not displayed.",
				purchaseOrderReceivingPage.isPreAdviceEntryDisplayed());
	}

	@When("^I receive remaining skus$")
	public void i_receive_the_remaining_skus() throws Throwable {
		System.out.println(context.getLineItem());
		if (context.getLineItem() <= context.getNoOfLines()) {
			i_enter_pre_advice_id_and_SKU_id(context.getPreAdviceId());
			the_pre_advice_id_and_supplier_id_should_be_displayed_in_the_pre_advice_page();
			i_enter_the_location_and_tag(context.getLocation());
			i_enter_the_quantity_to_receive_and_case_ratio();
			i_enter_the_expiry_details();
			i_should_see_the_receiving_completion();
		}
	}
}

