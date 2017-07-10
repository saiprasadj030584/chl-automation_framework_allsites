package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;

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

	@Inject
	public InventoryQueryStepDefs(Context context, Verification verification, InventoryDB inventoryDB,
			JdaLoginPage jdaLoginPage, JDAFooter jDAFooter, InventoryQueryPage inventoryQueryPage) {
		this.context = context;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
	}

	@Then("^the inventory should be displayed for all tags received$")
	public void the_inventory_should_be_displayed_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
					inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getUpiId(), date), failureList);
			verification.verifyData("Qty on Hand for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getUpiId(), date),
					failureList);
		}
		Assert.assertTrue(
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
			verification.verifyData("Location for SKU after Putaway" + context.getSkuId(), context.getToLocation(),
					inventoryDB.getLocationAfterPutaway(context.getSkuId(), date), failureList);
			// verification.verifyData("Qty on Hand for SKU
			// "+context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
			// inventoryDB.getQtyOnHand(context.getSkuId(),
			// context.getLocation(), context.getUpiId(),date), failureList);
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
		System.out.println(context.getSkuId());
		System.out.println(context.getLocation());
		System.out.println(context.getTagId());
		// jdaLoginPage.login();
	}

	@Given("^I have tag in inventory with \"([^\"]*)\" status$")
	public void i_have_tag_in_inventory_with_status(String Expiry) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagIDDetails(Expiry);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
	}

	@Given("^I have a tag in inventory with \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with(String origin) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.getTagIddetails(origin);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		System.out.println(context.getSkuId());
		System.out.println(context.getLocation());
		System.out.println(context.getTagId());
	}

	@Then("^the origin should be updated$")
	public void the_origin_should_be_updated() throws Throwable {
		jDAFooter.clickQueryButton();
		inventoryQueryPage.enterTagId(context.getTagId());
		inventoryQueryPage.enterSkuId(context.getSkuId());
		inventoryQueryPage.enterlocation(context.getLocation());
		jDAFooter.clickExecuteButton();
		inventoryQueryPage.getOrigin();

	}

	@Given("^I have a tag in Inventory with\"([^\"]*)\"$")
	public void i_have_a_tag_in_Inventory_with(String Condition) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.gettagIddetails(Condition);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		// jdaLoginPage.login();
	}

	@Given("^I have a tag in Inventory with \"([^\"]*)\"$")
	public void I_have_a_tag_in_Inventory_with(String Pallet) throws Throwable {
		ArrayList inventoryDetailList = inventoryDB.gettagIddetail(Pallet);
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		// jdaLoginPage.login();
	}

	@Given("^I have a Skuid and pack config$")
	public void i_have_a_Skuid_and_pack_config() throws Throwable {
		ArrayList StockDetailList = inventoryDB.getStockDetails();
		if (!StockDetailList.isEmpty()) {
			context.setSkuId((String) StockDetailList.get(0));
			context.setPackConfig((String) StockDetailList.get(1));

		}

	}
}
