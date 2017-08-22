package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryQueryStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private InventoryQueryPage inventoryQueryPage;
	private SkuSkuConfigDB skuSkuConfigDB;
	private JDAHomeStepDefs jDAHomeStepDefs;

	@Inject
	public InventoryQueryStepDefs(Context context, Verification verification, InventoryDB inventoryDB,
			JdaLoginPage jdaLoginPage, JDAFooter jDAFooter, InventoryQueryPage inventoryQueryPage,
			SkuSkuConfigDB skuSkuConfigDB, JDAHomeStepDefs jDAHomeStepDefs) {
		this.context = context;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
		this.skuSkuConfigDB = skuSkuConfigDB;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
	}

	@Then("^the inventory should be displayed for all tags received$")
	public void the_inventory_should_be_displayed_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		String tagId = Utilities.getTenDigitRandomNumber() + Utilities.getTenDigitRandomNumber();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterPOReceive(context.getSkuId(), context.getUpiId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getUpiId(), date),
						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all tags received idt$")
	public void the_inventory_should_be_displayed_for_all_tags_received_idt() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList<String> skuList = new ArrayList<String>();
		skuList = context.getSkuList();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		String tagId = Utilities.getTenDigitRandomNumber() + Utilities.getTenDigitRandomNumber();
		context.setContainerId(upiMap.get(context.getSkuId()).get("CONTAINER"));
		for (int s = 0; s < skuList.size(); s++) {
			context.setSkuId(skuList.get(s));
			if (context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceiveIdt(context.getSkuId(), context.getContainerId()),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue()),
						inventoryDB.getQtyOnHandreturns(context.getSkuId(), context.getContainerId()), failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	// FSV PO receiving

	@Then("^the inventory should be displayed for all tags received for FSV PO$")
	public void the_inventory_should_be_displayed_for_all_tags_received_for_fsv_po() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
					inventoryDB.getLocationAfterPOReceive(context.getSkuId(), context.getPreAdviceId(), date),
					failureList);
			verification.verifyData(
					"Qty on Hand for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()), inventoryDB
							.getQtyOnHandPO(context.getSkuId(), context.getLocation(), context.getPreAdviceId(), date),
					failureList);
		}
		Assert.assertFalse(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all putaway tags$")
	public void the_inventory_should_be_displayed_for_all_putaway_tags() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));

			// To validate during over or under receiving
			if (null != context.getReceiveType()) {
				if (context.getReceiveType().equalsIgnoreCase("Over Receiving")) {
					context.setRcvQtyDue(context.getRcvQtyDue() + 5);
				} else if (context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
					context.setRcvQtyDue(context.getRcvQtyDue() - 5);
				}
			}

			verification.verifyData("Location for SKU after Putaway" + context.getSkuId(), context.getToLocation(),
					inventoryDB.getLocationAfterPutaway(context.getSkuId(), date), failureList);
			verification.verifyData("Qty on Hand for SKU" + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getUpiId(), date),
					failureList);
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^I have tagid\"([^\"]*)\",sku\"([^\"]*)\",locationid \"([^\"]*)\"in inventory with the status \"([^\"]*)\"$")
	public void i_have_tagid_sku_locationid_in_inventory_with_the_status(String tagId, String skuId, String locationID,
			String status) throws Throwable {
		inventoryDB.getagId(status);
		inventoryDB.getsku(status);
		inventoryDB.getlocation(status);
		context.setTagId(tagId);
		context.setSkuId(skuId);
		context.setlocationID(locationID);

	}

	@Given("^I have a tag in inventory with \"([^\"]*)\" status$")
	public void i_have_a_tag_in_inventory_with_status(String lockStatus) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagIdDetails(lockStatus);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();
	}

	@Given("^I have tag in inventory with expiry \"([^\"]*)\" status$")
	public void i_have_tag_in_inventory_with_expiry_status(String Expiry) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagIDDetails(Expiry);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();
	}

	@Given("^I have a tag in inventory with origin \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_origin(String origin) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagIddetails(origin);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();

	}

	@Then("^the origin should be updated$")
	public void the_origin_should_be_updated() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(context.getTagId());
		inventoryQueryPage.enterSkuId(context.getSkuId());
		inventoryQueryPage.enterLocation(context.getLocation());
		jDAFooter.clickExecuteButton();
		inventoryQueryPage.getOrigin();

	}

	@Given("^I have a tag in inventory with condition \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_condition(String Condition) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.gettagIddetails(Condition);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();
	}

	@Given("^I have a tag in inventory with pallet type as \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_pallet_type_as(String pallet) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.gettagIddetail(pallet);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();
	}

	@Given("^I have a tag in inventory with owner as \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_owner_as(String owner) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagIddetail(owner);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
	}

	@Given("^I have a tag in inventory with pack config as \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_pack_config_as(String packconfig) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagDetails(packconfig);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
	}

	@Given("^I have a sku to adjust the stock$")
	public void i_have_a_sku_to_adjust_the_stock() throws Throwable {
		ArrayList StockDetailList = inventoryDB.getStockDetails();
		if (!StockDetailList.isEmpty()) {
			context.setSkuId((String) StockDetailList.get(0));
			context.setPackConfig((String) StockDetailList.get(1));
		}
		// jdaLoginPage.login();
	}

	@Given("^I have a sku in inventory with more than one pack config$")
	public void i_have_a_sku_in_inventory_with_more_than_one_packConfig() throws Throwable {
		String sku = skuSkuConfigDB.getSkuIdWithMoreThanOnePackConfig();
		context.setSkuId(sku);
		// System.out.println("list " + context.getPackConfigList().size());

		context.setPackConfigList(skuSkuConfigDB.getPackConfigList(sku));
		System.out.println("list " + context.getPackConfigList().size());
		ArrayList inventoryDetails = inventoryDB.getInventoryDetailsForSku(sku);
		if (!inventoryDetails.isEmpty()) {
			context.setTagId((String) inventoryDetails.get(0));
			context.setPackConfig((String) inventoryDetails.get(1));
		}
		System.out.println(context.getPackConfig());
		// jdaLoginPage.login();
	}

	@Then("^the inventory should be generated$")
	public void the_inventory_should_be_generated() throws Throwable {

		jDAHomeStepDefs.i_am_on_inventory_query_page();
		jDAFooter.clickQueryButton();
		// inventoryQueryPage.enterTagId(context.getTagId());
		inventoryQueryPage.enterTagId("11079");
		inventoryQueryPage.enterLocation(context.getLocation());
		jDAFooter.clickExecuteButton();
		inventoryQueryPage.getQtyOnHand();
		context.setQtyOnHand(Integer.parseInt(inventoryDB.getQty("11079", context.getLocation())));
		Assert.assertEquals("updated quantity on hand is not as expected", context.getQtyOnHand(),
				Integer.parseInt(inventoryQueryPage.getQtyOnHand()));

	}
}
