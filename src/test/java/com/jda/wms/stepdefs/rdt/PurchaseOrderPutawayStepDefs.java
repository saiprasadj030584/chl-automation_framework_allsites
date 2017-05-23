package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;
import com.jda.wms.db.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.MoveTaskUpdatePage;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.foods.PreAdviceHeaderStepsDefs;
import com.jda.wms.stepdefs.foods.PreAdviceLineMaintenanceStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPutawayStepDefs {
	private final JdaHomePage jdaHomepage;
	private final MoveTaskUpdatePage moveTaskUpdate;
	private final JDAFooter jdaFooter;
	private final PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private final PuttyFunctionsPage puttyFunctionsPage;
	private final PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private final PreAdviceLineMaintenanceStepDefs preAdviceLineMaintenanceStepDefs;
	private final PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private final PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private final Database database;
	private final LocationDB locationDB;
	private final Hooks hooks;
	private Context context;
	String tagId = null;
	Map<String, ArrayList<String>> tagIDMap;
	Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, String> locationTagMap;

	@Inject
	public PurchaseOrderPutawayStepDefs(JdaHomePage jdaHomepage, MoveTaskUpdatePage moveTaskUpdate, JDAFooter jdaFooter,
			PurchaseOrderPutawayPage purchaseOrderPutawayPage, Context context, PuttyFunctionsPage puttyFunctionsPage,
			PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs, Database database, Hooks hooks,
			PreAdviceLineMaintenanceStepDefs preAdviceLineMaintenanceStepDefs, LocationDB locationDB,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs) {
		this.jdaHomepage = jdaHomepage;
		this.moveTaskUpdate = moveTaskUpdate;
		this.jdaFooter = jdaFooter;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.preAdviceLineMaintenanceStepDefs = preAdviceLineMaintenanceStepDefs;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.database = database;
		this.locationDB = locationDB;
		this.hooks = hooks;
	}

	@Given("^the pre advice id \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" should be received$")
	public void the_pre_advice_id_and_should_be_received(String preAdviceId, String category, String status,
			String location, String finalStatus) throws Throwable {

		preAdviceHeaderStepsDefs
				.the_PO_with_category_should_be_status_and_have_future_due_date_site_id_number_of_lines_in_the_pre_advice_header_maintenance_table(
						preAdviceId, category, status);
		preAdviceLineMaintenanceStepDefs
				.the_PO_should_have_the_SKU_Qty_due_Tracking_level_Pack_config_Under_bond_case_ratio_base_UOM_details_for_each_pre_advice_line_items();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		purchaseOrderReceivingStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_pre_advice_receiving();
		purchaseOrderReceivingStepDefs.i_receive_all_skus_for_the_purchase_order_at_location(location);
		purchaseOrderReceivingStepDefs.i_should_see_the_receiving_completion();
		hooks.logoutPutty();
	}

	@When("^I navigate to move task update and release all the tags for the SKU$")
	public void i_navigate_to_move_task_update_and_relase_all_the_tags_for_the_SKU() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = context.getPurchaseOrderMap();
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

		for (int skuIndex = 1; skuIndex <= tagIDMap.size(); skuIndex++) {
			String sku = purchaseOrderMap.get(String.valueOf(skuIndex)).get("SKU");

			for (int tagIndex = 0; tagIndex < tagIDMap.get(sku).size(); tagIndex++) {
				String currentTagId = tagIDMap.get(sku).get(tagIndex);
				purchaseOrderPutawayPage.enterTagId(currentTagId);
				purchaseOrderPutawayPage.completeProcess();
				String location = purchaseOrderPutawayPage.getLocation();
				locationPerTagMap.put(currentTagId, location);
				Thread.sleep(2000);

				database.connect();
				String checkString = locationDB.geCheckString(location);
				purchaseOrderPutawayPage.enterCheckString(checkString);
			}

			Thread.sleep(5000);
			i_should_be_directed_to_putent_page();
			hooks.logoutPutty();
			Thread.sleep(2000);
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

	@Then("^I enter the tag and check string$")
	public void i_enter_the_tag_and_check_string() throws Throwable {
		purchaseOrderPutawayPage.enterTagId(context.getTagId());
		purchaseOrderPutawayPage.completeProcess();

		String location = purchaseOrderPutawayPage.getLocation();
		context.setLocation(location);

		database.connect();
		String checkString = locationDB.geCheckString(location);
		purchaseOrderPutawayPage.enterCheckString(checkString);
	}
}
