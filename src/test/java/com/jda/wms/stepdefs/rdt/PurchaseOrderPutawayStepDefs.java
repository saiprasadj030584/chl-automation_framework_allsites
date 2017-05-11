package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataObject.CheckString;
import com.jda.wms.pages.foods.InventoryQueryPage;
import com.jda.wms.pages.foods.InventoryTransactionQueryPage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.pages.foods.LocationPage;
import com.jda.wms.pages.foods.MoveTaskUpdatePage;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.stepdefs.foods.JDALoginStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPutawayStepDefs {
	private final JdaHomePage jdaHomepage;
	private final InventoryQueryPage inventoryQueryPage;
	private final MoveTaskUpdatePage moveTaskUpdate;
	private final JDAFooter jdaFooter;
	private final PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private final PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;

	private Context context;
	String tagId = null;
	Map<String, ArrayList<String>> tagIDMap;
	Map<String, Map<String, String>> purchaseOrderMap;
	Map<String, String> locationTagMap;

	@Inject
	public PurchaseOrderPutawayStepDefs(JdaHomePage jdaHomepage, InventoryQueryPage inventoryQueryPage,
			MoveTaskUpdatePage moveTaskUpdate, JDAFooter jdaFooter,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs,
			PurchaseOrderPutawayPage purchaseOrderPutawayPage,
			InventoryTransactionQueryPage inventoryTransactionQueryPage, JDALoginStepDefs jdaLoginStepDefs,
			Context context, LocationPage locationPage, CheckString checkString) {
		this.jdaHomepage = jdaHomepage;
		this.moveTaskUpdate = moveTaskUpdate;
		this.inventoryQueryPage = inventoryQueryPage;
		this.jdaFooter = jdaFooter;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
	}

	@Given("^the tag id should be received$")
	public void the_tag_id_should_be_received() throws Throwable {
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> tagIDArrayList = new ArrayList<String>();

		// TODO to be deleted once integrated with receiving scenarios
		context.setNoOfLines(1);
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			if (i == 1) {
				lineItemsMap.put("SKU", "21036013");
			}
			/*
			 * if (i == 2) { lineItemsMap.put("SKU", "21036046"); }
			 */
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
		}
		context.setPurchaseOrderMap(purchaseOrderMap);

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");

			for (int t = 0; t < 2; t++) {
				tagIDArrayList.clear();
				tagIDArrayList.add("1000493118");
				tagIDArrayList.add("1000757676");
			}

			tagIDMap.put(skuID, tagIDArrayList);
			context.setTagIDMap(tagIDMap);

			jdaHomepage.navigateToInventoryQueryPage();

			for (String key : purchaseOrderMap.keySet()) {
				String sku = purchaseOrderMap.get(key).get("SKU");
				System.out.println("Tag ID : " + tagIDMap.get(sku).size());

				for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
					tagId = tagIDMap.get(sku).get(t);
					context.setTagId(tagId);

					jdaFooter.clickQueryButton();
					inventoryQueryPage.searchTagId(context.getTagId());
					jdaFooter.clickExecuteButton();

					Assert.assertEquals("Location Zone does not match", "INBOUND",
							inventoryQueryPage.getLocationZone());
				}
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
		purchaseOrderReceivingStepDefs.i_have_logged_in_as_warehouse_user_in_Putty_with_host_and_port(host, port);
	}

	@When("^I select normal putaway$")
	public void i_select_normal_putaway() throws Throwable {
		purchaseOrderPutawayPage.selectPutawayMenu();
		purchaseOrderPutawayPage.selectNormalPutawayMenu();
	}

	@When("^I do putaway for all the tags$")
	public void i_do_putaway_for_all_the_tags() throws Throwable {
		Map<String, String> locationPerTagMap = new HashMap<String, String>();
		// List<String> locationCS = null;

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

				purchaseOrderPutawayPage.mimimizePuty();
				Thread.sleep(2000);

				jdaHomepage.navigateToLocationPage();
				jdaFooter.clickQueryButton();
				moveTaskUpdate.enterLocation(context.getLocation());
				jdaFooter.clickExecuteButton();
				String cString = moveTaskUpdate.getCheckString();

				purchaseOrderPutawayPage.clickPuttyIcon();
				purchaseOrderPutawayPage.enterCheckString(cString);

				/*
				 * locationCS = checkString.getCheckString();
				 * System.out.println(locationCS.get(0));
				 * System.out.println(locationCS.get(2));
				 * System.out.println(locationCS.get(50)); for (int i = 0; i <
				 * locationCS.size(); i++) { if
				 * (locationCS.get(i).contains(location)) { String cs =
				 * locationCS.get(i); String[] checkString = cs.split(":");
				 * purchaseOrderPutawayPage.enterCheckString(checkString[1]);
				 * break; } }
				 */

				/*
				 * CheckStringDetailsJsonDao checkStringDetailsJsonDao = new
				 * CheckStringDetailsJsonDao(); List<String> cString =
				 * checkStringDetailsJsonDao.getCheckStringDetails().get(0).
				 * getCheckString(); System.out.println(cString.size());
				 */

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
