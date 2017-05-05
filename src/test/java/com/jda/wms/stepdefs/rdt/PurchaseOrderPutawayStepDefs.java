package com.jda.wms.stepdefs.rdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import edu.emory.mathcs.backport.java.util.Arrays;

public class PurchaseOrderPutawayStepDefs {
	private final JdaHomePage jdaHomepage;
	private final InventoryQueryPage inventoryQueryPage;
	private final MoveTaskUpdatePage moveTaskUpdate;
	private final JDAFooter jdaFooter;
	private final PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private final PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private final JDALoginStepDefs jdaLoginStepDefs;
	private final InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private final LocationPage locationPage;
	private final CheckString checkString;
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
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.jdaFooter = jdaFooter;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.locationPage = locationPage;
		this.context = context;
		this.checkString = checkString;
	}

	@Given("^the tag id should be received$")
	public void the_tag_id_should_be_received() throws Throwable {
//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		context.setNoOfLines(1);
		// Create Hash map to store line items
		Map<String, Map<String, String>> purchaseOrderMap = new HashMap<String, Map<String, String>>();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			if (i == 1) {
				lineItemsMap.put("SKU", "21036013");
				/*lineItemsMap.put("QtyDue", "1500");
				lineItemsMap.put("RemainingQtyDue", "0");
				lineItemsMap.put("CaseRatio", "15");
				lineItemsMap.put("MaxQtyCanBeRcvd", "800");*/
			}
			/*if (i == 2) {
				lineItemsMap.put("SKU", "21036046");
				lineItemsMap.put("QtyDue", "1500");
				lineItemsMap.put("RemainingQtyDue", "1500");
				lineItemsMap.put("CaseRatio", "24");
				lineItemsMap.put("MaxQtyCanBeRcvd", "800");
			}*/
			purchaseOrderMap.put(String.valueOf(i), lineItemsMap);
		}
		context.setPurchaseOrderMap(purchaseOrderMap);
		jdaHomepage.navigateToInventoryQueryPage();
		
		Map<String, ArrayList<String>> tagIDMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> tagIDArrayList = new ArrayList<String>();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String skuID = purchaseOrderMap.get(String.valueOf(i)).get("SKU");
			for (int t = 0; t < 1; t++) {
				//tagIDArrayList.add("1000757676");
				tagIDArrayList.add("1000953190");
			}
			tagIDMap.put(skuID, tagIDArrayList);
			context.setTagIDMap(tagIDMap);
			for (String key : purchaseOrderMap.keySet()) {
				String sku = purchaseOrderMap.get(key).get("SKU");
				System.out.println("Tag ID : " + tagIDMap.get(sku).size());
				for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
					
					tagId = tagIDMap.get(sku).get(t);
					context.setTagId(tagId);
					jdaFooter.clickQueryButton();
					inventoryQueryPage.searchTagId(context.getTagId());
					jdaFooter.clickExecuteButton();
					Assert.assertEquals("Location Zone does not match", "INBOUND", inventoryQueryPage.getLocationZone());
					i_navigate_to_move_task_update();
					i_relase_the_tag();
					the_tag_id_should_be_released();
				}
			}
		}
	}

	@When("^I navigate to move task update$")
	public void i_navigate_to_move_task_update() throws Throwable {
		jdaHomepage.navigateToMoveTaskUpdate();
		moveTaskUpdate.enterTagId(context.getTagId());
		jdaFooter.clickNextButton();
	}

	@When("^I relase the tag$")
	public void i_relase_the_tag() throws Throwable {
		moveTaskUpdate.clickReleaseButton();
	}

	@Then("^the tag id should be released$")
	public void the_tag_id_should_be_released() throws Throwable {
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
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

	@When("^I enter the tag and check string$")
	public void i_enter_the_tag_and_check_string() throws Throwable {
		Map<String, String> locationPerTagMap = new HashMap<String, String>();
		List<String> locationCS = null;
		purchaseOrderMap = context.getPurchaseOrderMap();
		System.out.println(purchaseOrderMap);
		tagIDMap = context.getTagIDMap();
		System.out.println(tagIDMap);

		for (int s = 1; s <= tagIDMap.size(); s++) {
			String sku = purchaseOrderMap.get(String.valueOf(s)).get("SKU");
			for (int t = 0; t < tagIDMap.get(sku).size(); t++) {
				String currentTagId = tagIDMap.get(sku).get(t);
				purchaseOrderPutawayPage.enterTagId(currentTagId);
				Thread.sleep(3000);
				String location = purchaseOrderPutawayPage.getLocation();
				locationPerTagMap.put(currentTagId, location);
				i_proceed_to_complete();
				Thread.sleep(2000);
				purchaseOrderPutawayPage.mimimizePuty();
				Thread.sleep(2000);
				jdaHomepage.navigateToLocationPage();
				String cs = moveTaskUpdate.getCheckString();
				purchaseOrderPutawayPage.clickPuttyIcon();
				purchaseOrderPutawayPage.enterCheckString(cs);
				
				/*locationCS = checkString.getCheckString();
				System.out.println(locationCS.get(0));
				System.out.println(locationCS.get(2));
				System.out.println(locationCS.get(50));
				for (int i = 0; i < locationCS.size(); i++) {
					if (locationCS.get(i).contains(location)) {
						String cs = locationCS.get(i);
						String[] checkString = cs.split(":");
						purchaseOrderPutawayPage.enterCheckString(checkString[1]);
						break;
					}
				}*/
				
				
				i_should_be_directed_to_putent_page();
			}
		}
		context.setLocationPerTagMap(locationPerTagMap);
	}

	@When("^I proceed to complete$")
	public void i_proceed_to_complete() throws Throwable {
		purchaseOrderPutawayPage.completeProcess();
	}

	@Then("^I should be directed to putent page$")
	public void i_should_be_directed_to_putent_page() throws Throwable {
		Assert.assertTrue("Putaway not completed and Home page not displayed.", purchaseOrderPutawayPage.isPutEnt());
	}

	@When("^I navigate to location page$")
	public void i_navigate_to_location_page() throws Throwable {
//		jdaHomepage.navigateToLocationPage();
	}

	@Then("^I should see the location zone$")
	public void i_should_see_the_location_zone() throws Throwable {
		jdaHomepage.navigateToLocationPage();
		jdaFooter.clickQueryButton();
		locationTagMap = context.getLocationPerTagMap();
		for (String tag : locationTagMap.keySet()) {
			String location = locationTagMap.get(tag);
			context.setTagId(tag);
			System.out.println("Key :" + tag);
			System.out.println("Value : " + location);
			locationPage.enterLocation(location);
			jdaFooter.clickExecuteButton();
			String locationZone = locationPage.getLocationZone();
			context.setLocationZone(locationZone);
			/*
			 * if (locationZone.equals(null)) { Assert.assertNotNull(
			 * "Location zone should not be null", locationZone); }
			 */
			i_naviagate_to_inventory_query_page();
			i_should_see_the_location_zone_in_inventory_page();
			i_navigate_to_inventory_transaction_query_page();
			i_should_see_the_from_location_to_location_and_final_location_for_the_tag();
			i_should_see_the_uploaded_status_and_uploaded_file_in_the_miscellaneous2_tab();
		}
		/*
		 * locationPage.enterLocation(context.getLocation());
		 * jdaFooter.clickExecuteButton(); String locationZone =
		 * locationPage.getLocationZone();
		 * context.setLocationZone(locationZone); if (locationZone.equals(null))
		 * { Assert.assertNotNull("Location zone should not be null",
		 * locationZone); }
		 */
	}

	@Then("^I should see the location zone in inventory page$")
	public void i_should_see_the_location_zone_in_inventory_page() throws Throwable {
		jdaFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(context.getTagId());
		jdaFooter.clickExecuteButton();
		Assert.assertEquals("Location Zone does not match", context.getLocationZone(),
				inventoryQueryPage.getLocationZone());
	}

	@When("^I naviagate to inventory query page$")
	public void i_naviagate_to_inventory_query_page() throws Throwable {
		jdaHomepage.navigateToInventoryQueryPage();
	}

	@When("^I navigate to inventory transaction query page$")
	public void i_navigate_to_inventory_transaction_query_page() throws Throwable {
		jdaHomepage.navigateToInventoryTransactionPage();
		jdaFooter.clickQueryButton();
		inventoryTransactionQueryPage.selectCode("Putaway");
		inventoryTransactionQueryPage.enterTagId(context.getTagId());
		jdaFooter.clickExecuteButton();
	}

	@Then("^I should see the from location, to location and final location for the tag$")
	public void i_should_see_the_from_location_to_location_and_final_location_for_the_tag() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String fromLocation = inventoryTransactionQueryPage.getFromLocation();
		if (!fromLocation.equals("REC002")) {
			failureList.add(
					"Uploaded File Name not displayed as expected. Expected [REC002]but was [" + fromLocation + "]");
		}
		String finalLocation = inventoryTransactionQueryPage.getFinalLocation();
		String toLocation = inventoryTransactionQueryPage.getToLocation();

		if (!toLocation.equals(finalLocation)) {
			failureList.add("Uploaded File Name not displayed as expected. Expected [" + finalLocation + "]   but was ["
					+ toLocation + "]");
		}

		Assert.assertTrue(
				"Inventory general tab  not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^I should see the uploaded status and uploaded file in the miscellaneous2 tab$")
	public void i_should_see_the_uploaded_status_and_uploaded_file_in_the_miscellaneous2_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		inventoryTransactionQueryPage.navigateToMiscellaneous2Tab();
		String uploadedStatus = inventoryTransactionQueryPage.getUploaded();
		if (uploadedStatus.equals("Y")) {
			failureList.add("Uploaded status not displayed as expected. Expected [Y] but was " + uploadedStatus + "]");
		}
		String uploadedFileName = inventoryTransactionQueryPage.getUploadedFileName();
		if (!uploadedFileName.contains("I0809itlext.0000")) {
			failureList.add(
					"Uploaded File Name not displayed as expected. Expected [I809] but was [" + uploadedFileName + "]");
		}

		Assert.assertTrue("Inventory miscellaneous2 tab  not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
}
