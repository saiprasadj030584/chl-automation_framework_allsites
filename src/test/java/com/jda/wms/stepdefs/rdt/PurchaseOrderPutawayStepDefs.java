package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dao.GetDataFromJson;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.MoveTaskUpdatePage;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.foods.InventoryQueryStepDefs;
import com.jda.wms.stepdefs.foods.InventoryTransactionQueryStepDefs;
import com.jda.wms.stepdefs.foods.JDALoginStepDefs;
import com.jda.wms.stepdefs.foods.PreAdviceHeaderStepsDefs;
import com.jda.wms.stepdefs.foods.PreAdviceLineMaintenanceStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPutawayStepDefs {
	private final JdaHomePage jdaHomepage;
	private final InventoryQueryPage inventoryQueryPage;
	private final MoveTaskUpdatePage moveTaskUpdate;
	private final JDAFooter jdaFooter;
	private final PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private final PuttyFunctionsPage puttyFunctionsPage;
	private final GetDataFromJson getDataFromJson;
	private final PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private final PreAdviceLineMaintenanceStepDefs preAdviceLineMaintenanceStepDefs;
	private final PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private final PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private final JDALoginStepDefs jdaLoginStepDefs;
	private final InventoryQueryStepDefs inventoryQueryStepDefs;
	private final InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private Context context;
	String tagId = null;
	Map<String, ArrayList<String>> tagIDMap;
	Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, String> locationTagMap;

	@Inject
	public PurchaseOrderPutawayStepDefs(JdaHomePage jdaHomepage, InventoryQueryPage inventoryQueryPage,
			MoveTaskUpdatePage moveTaskUpdate, JDAFooter jdaFooter, PurchaseOrderPutawayPage purchaseOrderPutawayPage,
			Context context, PuttyFunctionsPage puttyFunctionsPage, GetDataFromJson getDataFromJson,
			PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			PreAdviceLineMaintenanceStepDefs preAdviceLineMaintenanceStepDefs,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs, JDALoginStepDefs jdaLoginStepDefs) {
		this.jdaHomepage = jdaHomepage;
		this.moveTaskUpdate = moveTaskUpdate;
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.getDataFromJson = getDataFromJson;
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.preAdviceLineMaintenanceStepDefs = preAdviceLineMaintenanceStepDefs;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.inventoryQueryStepDefs = inventoryQueryStepDefs;
		this.inventoryTransactionQueryStepDefs = inventoryTransactionQueryStepDefs;
	}

	@Given("^the pre advice id \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" should be received$")
	public void the_pre_advice_id_and_should_be_received(String preAdviceId, String category, String status,
			String location, String finalStatus) throws Throwable {

		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> tagIDArrayList = new ArrayList<String>();

		preAdviceHeaderStepsDefs
				.the_PO_with_category_should_be_status_and_have_future_due_date_site_id_number_of_lines_in_the_pre_advice_header_maintenance_table(
						preAdviceId, category, status);
		preAdviceLineMaintenanceStepDefs
				.the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_line_items();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_Putty();
		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_pre_advice_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_pre_advice_entry_page();
		purchaseOrderReceivingStepDefs.i_receive_all_skus_for_the_purchase_order_at_location(location);
		purchaseOrderReceivingStepDefs.i_should_see_the_receiving_completion();
		// jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		jdaHomepage.navigateToInventoryQueryPage();
		inventoryQueryStepDefs.the_inventory_details_should_be_displayed_for_all_the_tag_id();
		inventoryQueryStepDefs.i_navigate_to_pre_advice_header_maintenance_page();
		inventoryQueryStepDefs.the_status_should_be_displayed_as(finalStatus);
		inventoryTransactionQueryStepDefs
				.the_goods_receipt_should_be_generated_for_the_received_stock_in_inventory_transaction_table();

		/*
		 * // commented the hardcode values // TODO to be deleted once
		 * integrated with receiving scenarios context.setNoOfLines(1); for (int
		 * i = 1; i <= context.getNoOfLines(); i++) { Map<String, String>
		 * lineItemsMap = new HashMap<String, String>(); if (i == 1) {
		 * lineItemsMap.put("SKU", "21036013"); }
		 * 
		 * if (i == 2) { lineItemsMap.put("SKU", "21036046"); }
		 * 
		 * purchaseOrderMap.put(String.valueOf(i), lineItemsMap); }
		 * context.setPurchaseOrderMap(purchaseOrderMap);
		 * 
		 * for (int i = 1; i <= context.getNoOfLines(); i++) { String skuID =
		 * purchaseOrderMap.get(String.valueOf(i)).get("SKU");
		 * 
		 * for (int t = 0; t < 2; t++) { tagIDArrayList.clear();
		 * tagIDArrayList.add("1000493118"); tagIDArrayList.add("1000757676"); }
		 * 
		 * tagIDMap.put(skuID, tagIDArrayList); context.setTagIDMap(tagIDMap);
		 * context.getTagIDMap();
		 * 
		 */

		jdaHomepage.navigateToInventoryQueryPage();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");
			System.out.println("Tag ID : " + tagIDMap.get(sku).size());

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				tagId = tagIDMap.get(sku).get(t);
				context.setTagId(tagId);

				jdaFooter.clickQueryButton();
				inventoryQueryPage.enterTagId(context.getTagId());
				jdaFooter.clickExecuteButton();

				Assert.assertEquals("Location Zone does not match", "INBOUND", inventoryQueryPage.getLocationZone());
			}
		}
	}

	@When("^I navigate to move task update and release all the tags for the SKU$")
	public void i_navigate_to_move_task_update_and_relase_all_the_tags_for_the_SKU() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
		System.out.println(purchaseOrderMap);
		Map<String, ArrayList<String>> tagIDMap = context.getTagIDMap();
		jdaHomepage.navigateToMoveTaskUpdate();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				moveTaskUpdate.enterTagId(tagIDMap.get(sku).get(t));
				jdaFooter.clickNextButton();
				moveTaskUpdate.clickReleaseButton();
				jdaFooter.clickNextButton();
				jdaFooter.clickDoneButton();
			}
		}
	}

	@When("^I relase all the tags for the SKU$")
	public void i_relase_all_the_tags_for_the_SKU() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
		System.out.println(purchaseOrderMap);
		Map<String, ArrayList<String>> tagIDMap = context.getTagIDMap();

		for (String key : purchaseOrderMap.keySet()) {
			String sku = purchaseOrderMap.get(key).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {

				moveTaskUpdate.enterTagId(tagIDMap.get(sku).get(t));
				jdaFooter.clickNextButton();
				moveTaskUpdate.clickReleaseButton();
				jdaFooter.clickNextButton();
				jdaFooter.clickDoneButton();
			}
		}
	}

	@When("^I login as warehouse user in Putty with host \"([^\"]*)\" and port \"([^\"]*)\"$")
	public void i_login_as_warehouse_user_in_Putty_with_host_and_port(String host, String port) throws Throwable {
		puttyFunctionsPage.loginPutty(host, port);
	}

	@When("^I select normal putaway$")
	public void i_select_normal_putaway() throws Throwable {
		purchaseOrderPutawayPage.selectPutawayMenu();
		purchaseOrderPutawayPage.selectNormalPutawayMenu();
	}

	@When("^I do putaway for all the tags$")
	public void i_do_putaway_for_all_the_tags() throws Throwable {
		Map<String, String> locationPerTagMap = new HashMap<String, String>();

		purchaseOrderMap = context.getPurchaseOrderMap();
		tagIDMap = context.getTagIDMap();

		for (int s = 1; s <= tagIDMap.size(); s++) {
			String sku = purchaseOrderMap.get(String.valueOf(s)).get("SKU");

			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				String currentTagId = tagIDMap.get(sku).get(t);
				purchaseOrderPutawayPage.enterTagId(currentTagId);
				Thread.sleep(5000);

				String location = purchaseOrderPutawayPage.getLocation();
				String[] loc = location.split("_______");
				context.setLocation(loc[0]);
				locationPerTagMap.put(currentTagId, loc[0]);

				purchaseOrderPutawayPage.completeProcess();
				Thread.sleep(2000);

				List<String> chkString = getDataFromJson.getCheckString();
				for (int i = 0; i < chkString.size(); i++) {
					if (chkString.get(i).contains(location)) {
						String cs = chkString.get(i);
						String[] checkString = cs.split(":");
						purchaseOrderPutawayPage.enterCheckString(checkString[1]);
						break;
					}
				}

				i_should_be_directed_to_putent_page();
				purchaseOrderPutawayPage.mimimizePuty();
				Thread.sleep(2000);
			}
		}
		context.setLocationPerTagMap(locationPerTagMap);
	}

	@Then("^I should be directed to putent page$")
	public void i_should_be_directed_to_putent_page() throws Throwable {
		Assert.assertTrue("Putaway not completed and Home page not displayed.", purchaseOrderPutawayPage.isPutEnt());
	}

	@When("^I naviagate to inventory query page$")
	public void i_naviagate_to_inventory_query_page() throws Throwable {
		jdaHomepage.navigateToInventoryQueryPage();
	}

	@When("^I navigate to inventory transaction query page$")
	public void i_navigate_to_inventory_transaction_query_page() throws Throwable {
		jdaHomepage.navigateToInventoryTransactionPage();
	}

}
