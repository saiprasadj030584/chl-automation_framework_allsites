package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class InventoryQueryStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<Integer, Map<String, String>>> multiplePOMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private InventoryQueryPage inventoryQueryPage;
	private SkuSkuConfigDB skuSkuConfigDB;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private InventoryTransactionDB inventoryTransactionDB;
	private OrderLineDB orderLineDB;

	@Inject
	public InventoryQueryStepDefs(Context context, Verification verification, InventoryDB inventoryDB,
			JdaLoginPage jdaLoginPage, JDAFooter jDAFooter, InventoryQueryPage inventoryQueryPage,
			SkuSkuConfigDB skuSkuConfigDB, InventoryTransactionDB inventoryTransactionDB,
			JDAHomeStepDefs jDAHomeStepDefs, OrderLineDB orderLineDB) {
		this.context = context;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
		this.skuSkuConfigDB = skuSkuConfigDB;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.orderLineDB = orderLineDB;
	}

	@Then("^the inventory should be displayed for all tags received$")
	public void the_inventory_should_be_displayed_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() - 1),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),

						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),

						String.valueOf(context.getRcvQtyDue()),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),

						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all tags received of hanging type$")
	public void the_inventory_should_be_displayed_for_all_tags_received_of_hanging_type() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		// multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
		System.out.println("tag id" + context.getTagId());
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceiveForHanging(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHandForHanging(context.getSkuId(),
								context.getLocation(), context.getTagId(), date),
						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all multiple tags received$")
	public void the_inventory_should_be_displayed_for_all_multiple_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int j = 0; j < context.getPreAdviceList().size(); j++) {
			context.setUpiId(context.getUpiList().get(j));
			for (int i = context.getLineItem(); i <= Integer
					.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(j))); i++) {
				context.setSkuId(context.getMultiplePOMap().get(context.getPreAdviceList().get(j)).get(i).get("SKU"));

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
		// String tagId = Utilities.getTenDigitRandomNumber() +
		// Utilities.getTenDigitRandomNumber();
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

			verification.verifyData("Location for SKU after Putaway" + context.getSkuId(), context.getLocation(),
					inventoryDB.getLocationAfterPutaway(context.getSkuId(), date, context.getPreAdviceId()),
					failureList);
			verification.verifyData("Qty on Hand for SKU" + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryDB.getQtyOnHand(context.getSkuId(), context.getToLocation(), context.getTagId(), date),
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
		context.setLocationID(locationID);

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

	@Given("^I have a tag in inventory with \"([^\"]*)\" status for \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_status(String lockStatus, String dataType) throws Throwable {
		String type = null;
		switch (dataType) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		case "Flatpack":
			type = "P";
			break;
		case "GOH":
			type = "C";
			break;
		}

		ArrayList inventoryDetailList = inventoryDB.getTagIdDetailsForLockStatus(lockStatus, type);
		Assert.assertFalse("Test Data Not found - for " + dataType + " - " + lockStatus, inventoryDetailList.isEmpty());
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

		String updateOrigin = inventoryDB.getOrigin(context.getTagId());
		Assert.assertEquals("Origin Update is not as expeccted in Inventory", context.getOrigin(), updateOrigin);

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
		jdaLoginPage.login();
	}

	@Given("^I have a sku in inventory with more than one pack config$")
	public void i_have_a_sku_in_inventory_with_more_than_one_packConfig() throws Throwable {
		String sku = skuSkuConfigDB.getSkuIdWithMoreThanOnePackConfig();
		context.setSkuId(sku);
		context.setPackConfigList(skuSkuConfigDB.getPackConfigList(sku));
		ArrayList inventoryDetails = inventoryDB.getInventoryDetailsForSku(sku);
		if (!inventoryDetails.isEmpty()) {
			context.setTagId((String) inventoryDetails.get(0));
			context.setPackConfig((String) inventoryDetails.get(1));
		}
		jdaLoginPage.login();
	}

	@Then("^the inventory should be generated$")
	public void the_inventory_should_be_generated() throws Throwable {

		jDAHomeStepDefs.i_am_on_inventory_query_page();
		jDAFooter.clickQueryButton();

		inventoryQueryPage.enterTagId(context.getTagId());
		Thread.sleep(2000);
		inventoryQueryPage.enterLocation(context.getLocation());
		jDAFooter.clickExecuteButton();
		inventoryQueryPage.getQtyOnHand();
		context.setQtyOnHand(Integer.parseInt(inventoryDB.getQty(context.getTagId(), context.getLocation())));
		Assert.assertEquals("updated quantity on hand is not as expected", context.getQtyOnHand(),
				Integer.parseInt(inventoryQueryPage.getQtyOnHand()));
	}

	@When("^the inventory is available for the \"([^\"]*)\" and unavailable for \"([^\"]*)\"$")
	public void the_inventory_is_available_for_the_prohibited_country_and_unavailable_for_origin_location(
			String prohibitedCountry, String originCountry) throws Throwable {

		context.setSkuSize(orderLineDB.getSkuList(context.getOrderId()).size());
		HashMap<Integer, List<String>> locationAndQtyOnHand = new HashMap<Integer, List<String>>();

		int j = 0;
		int counter = 1;
		String noOfOrigins = null;
		for (int i = 0; i < context.getSkuSize(); i++) {
			locationAndQtyOnHand = inventoryDB.getQtyOnHandBySkuId(orderLineDB.getSkuList(context.getOrderId()).get(i));
			noOfOrigins = inventoryDB.getNoOforigins(orderLineDB.getSkuList(context.getOrderId()).get(i));

			for (j = 1; j <= Integer.parseInt(noOfOrigins); j++) {

				if (locationAndQtyOnHand.get(j).get(0).contains(originCountry))
					Assert.fail("Origin location inventory is present");
				if (locationAndQtyOnHand.get(j).get(0).contains(prohibitedCountry))
					counter++;
			}
			if (counter > 1)
				Assert.fail("Prohibited " + prohibitedCountry + " inventory is not present");

		}
	}

	@Then("^sku should be available in inventory$")
	public void sku_should_be_available_in_inventory() throws ClassNotFoundException, SQLException {
		String countrecordforSku = inventoryDB.getStockAvailablityRecords(context.getSkuId());
		int totalrecordforSku = Integer.parseInt(countrecordforSku);
		if (totalrecordforSku != 0) {
			boolean recordexist = true;
			Assert.assertTrue("inventory doesn't exist", recordexist);
		}
	}

	@Then("^the inventory should be displayed for all received tags for two putaway group$")
	public void the_inventory_should_be_displayed_for_all_received_tags_for_two_putaway_group() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
					inventoryDB.getLocationAfterPOReceive(context.getSkuId(), context.getPreAdviceId(), date),
					failureList);
			verification.verifyData("Qty on Hand for SKU " + context.getSkuId(), String.valueOf(context.getRcvQtyDue()),
					inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getUpiId(), date),
					failureList);
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all random pallet ID received for FSV PO$")
	public void the_inventory_should_be_displayed_for_all_random_pallet_ID_received_for_fsv_po() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
					inventoryDB.getLocationAfterPOReceiveForNewPalletID(context.getSkuId(),
							context.getPalletIDList().get(i - 1), date),
					failureList);
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all random tags received$")
	public void the_inventory_should_be_displayed_for_all_random_tags_received() throws Throwable {
		context.setLocation("REC001");
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		// context.setTagId(
		// inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt",
		// context.getSkuId(), date));
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));

			if ((null != context.getReceiveType()) && (context.getReceiveType().equalsIgnoreCase("Under Receiving"))) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceiveForRandomTag(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5), inventoryDB.getQtyOnHandForRandomTag(
								context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else {

				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterPOReceiveForRandomTag(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHandForRandomTag(context.getSkuId(),
								context.getLocation(), context.getTagId(), date),
						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the inventory is available for the given SKU$")
	public void the_inventory_is_available_for_the_given_SKU() throws Throwable {
		context.setSkuSize(orderLineDB.getskuList(context.getOrderId()).size());
		for (int i = 0; i < context.getSkuSize(); i++) {
			int qtyOnHandNotInSuspense = inventoryDB
					.getQtyOnHandNotInSuspense(orderLineDB.getskuList(context.getOrderId()).get(i));
			Assert.assertTrue(
					"Ordered Qty Not available for SKU_ID : " + orderLineDB.getskuList(context.getOrderId()).get(i),
					qtyOnHandNotInSuspense > Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(),
							orderLineDB.getskuList(context.getOrderId()).get(i))));
		}
	}

	@Given("^I have tag in inventory with expiry \"([^\"]*)\" status for \"([^\"]*)\" and siteId \"([^\"]*)\"$")
	public void i_have_tag_in_inventory_with_expiry_status_(String expiry, String dataType, String siteId)
			throws Throwable {

		String type = null;
		switch (dataType) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		case "Flatpack":
			type = "P";
			break;
		case "GOH":
			type = "C";
			break;
		}

		ArrayList inventoryDetailList = inventoryDB.getTagIdDetailsforExpiry(expiry, type, siteId);
		Assert.assertFalse("Test Data Not found - for " + dataType + " - Expiry date", inventoryDetailList.isEmpty());
		if (!inventoryDetailList.isEmpty()) {
			String tempData = (String) inventoryDetailList.get(0);
			if (!tempData.equalsIgnoreCase("Exhausted Resultset")) {
				context.setSkuId((String) inventoryDetailList.get(0));
				context.setLocation((String) inventoryDetailList.get(1));
				context.setTagId((String) inventoryDetailList.get(2));
			}

			else if (tempData.equalsIgnoreCase("Exhausted Resultset")) {
				Assert.fail("No records found for the site Id it is a valid failure");
			}

			else {
				ArrayList skuList = inventoryDB.getSKUFromInventoryFordDataType(type);
				if (!skuList.isEmpty()) {
					inventoryDB.updateExpiryForTag((String) skuList.get(0), (String) skuList.get(1));
					inventoryDetailList = inventoryDB.getTagIdDetailsforExpiry(expiry, type, siteId);
					if (!inventoryDetailList.isEmpty()) {
						context.setSkuId((String) inventoryDetailList.get(0));
						context.setLocation((String) inventoryDetailList.get(1));
						context.setTagId((String) inventoryDetailList.get(2));
					}
				}
			}
		}
		jdaLoginPage.login();
	}

	@Given("^I have a tag in inventory with origin \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_origin(String origin, String dataType) throws Throwable {
		String type = null;
		switch (dataType) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		case "Flatpack":
			type = "P";
			break;
		case "GOH":
			type = "C";
			break;
		}
		System.out.println("hello");
		ArrayList inventoryDetailList = inventoryDB.getTagIdDetailsForOrigin(origin, type);
		Assert.assertFalse("Test Data Not found - for " + dataType + " - Origin ", inventoryDetailList.isEmpty());
		if (!inventoryDetailList.isEmpty()) {
			String tempData = (String) inventoryDetailList.get(0);
			System.out.println(tempData);
			if (!tempData.equalsIgnoreCase("Exhausted Resultset")) {
				context.setSkuId((String) inventoryDetailList.get(0));
				System.out.println("SKU" + context.getSkuId());
				context.setLocation((String) inventoryDetailList.get(1));
				System.out.println("Location" + context.getLocation());
				context.setTagId((String) inventoryDetailList.get(2));
				System.out.println("Tag" + context.getTagId());
			} else {
				ArrayList skuList = inventoryDB.getSKUFromInventoryFordDataType(type);
				if (!skuList.isEmpty()) {
					inventoryDB.updateOriginForTag((String) skuList.get(0), (String) skuList.get(1), origin);
					inventoryDetailList = inventoryDB.getTagIdDetailsForOrigin(origin, type);
					if (!inventoryDetailList.isEmpty()) {
						context.setSkuId((String) inventoryDetailList.get(0));
						System.out.println(context.getSkuId());
						context.setLocation((String) inventoryDetailList.get(1));
						System.out.println(context.getLocation());
						context.setTagId((String) inventoryDetailList.get(2));
						System.out.println(context.getTagId());
					}
				}
			}
		}
		jdaLoginPage.login();
	}

	@Given("^I have a tag in inventory with condition \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_condition(String Condition, String dataType) throws Throwable {
		String type = null;
		switch (dataType) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		case "Flatpack":
			type = "P";
			break;

		case "GOH":
			type = "C";
			break;
		}
		ArrayList inventoryDetailList = inventoryDB.getTagIdDetailsForCondition(Condition, type);
		Assert.assertFalse("Test Data Not found - for " + dataType + " - Condition Code",
				inventoryDetailList.isEmpty());
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();
	}

	@Given("^I have a tag in inventory with pallet type as \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_have_a_tag_in_inventory_with_pallet_type_as(String pallet, String dataType) throws Throwable {
		String type = null;
		switch (dataType) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		case "Flatpack":
			type = "P";
			break;
		case "GOH":
			type = "C";
			break;

		}
		ArrayList inventoryDetailList = inventoryDB.getTagIdDetailsForPallet(pallet, type);
		Assert.assertFalse("Test Data Not found - for " + dataType + " - Pallet Type", inventoryDetailList.isEmpty());
		if (!inventoryDetailList.isEmpty()) {
			context.setSkuId((String) inventoryDetailList.get(0));
			context.setLocation((String) inventoryDetailList.get(1));
			context.setTagId((String) inventoryDetailList.get(2));
		}
		jdaLoginPage.login();
	}

	@Given("^I have a sku in inventory with more than one pack config for \"([^\"]*)\"$")
	public void i_have_a_sku_in_inventory_with_more_than_one_packConfig(String dataType) throws Throwable {
		String type = null;
		switch (dataType) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		case "GOH":
			type = "C";
			break;
		case "Flatpack":
			type = "P";
			break;
		}
		String sku = skuSkuConfigDB.getSkuIdWithMoreThanOnePackConfig(type);
		context.setSkuId(sku);
		context.setPackConfigList(skuSkuConfigDB.getPackConfigList(sku));
		ArrayList inventoryDetails = inventoryDB.getInventoryDetailsForSku(sku);
		Assert.assertFalse("Test Data Not found - for " + dataType + " - Pack Config", inventoryDetails.isEmpty());
		if (!inventoryDetails.isEmpty()) {
			context.setTagId((String) inventoryDetails.get(0));
			context.setPackConfig((String) inventoryDetails.get(1));
		}
		jdaLoginPage.login();
	}

	@Then("^the inventory should be displayed for all tags received for hazardous FSV PO$")
	public void the_inventory_should_be_displayed_for_all_tags_received_for_hazardous_fsv_po() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
					inventoryDB.getLocationAfterPOReceive(context.getSkuId(), context.getPalletID(), date),
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

	@Given("^I have a sku of type \"([^\"]*)\" to adjust the stock$")
	public void i_have_a_sku_of_type_to_adjust_the_stock(String type) throws Throwable {
		String typeModularity = null;
		if (type.contains("Boxed")) {
			typeModularity = "B";
		} else if (type.contains("Hanging")) {
			typeModularity = "H";
		} else if (type.contains("GOH")) {
			typeModularity = "C";
		} else if (type.contains("Flatpack")) {
			typeModularity = "P";
		}
		ArrayList StockDetailList = inventoryDB.getStockDetails(typeModularity);
		if (!StockDetailList.isEmpty()) {
			context.setSkuId((String) StockDetailList.get(0));
			context.setPackConfig((String) StockDetailList.get(1));
		}
		jdaLoginPage.login();
	}

	@Then("^the inventory should be displayed for all tags received of GOH type$")
	public void the_inventory_should_be_displayed_for_all_tags_received_of_GOH_type() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		// multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceiveForFlatpack(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHandForFlatpack(context.getSkuId(),
								context.getLocation(), context.getTagId(), date),
						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all GOH received tag$")
	public void the_inventory_should_be_displayed_for_all_GOH_received_tag() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		// multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
		System.out.println("tag id" + context.getTagId());
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceiveForHanging(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHandForHanging(context.getSkuId(),
								context.getLocation(), context.getTagId(), date),
						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the inventory should be displayed for all tags received of flatpack type$")
	public void the_inventory_should_be_displayed_for_all_tags_received_of_flatpack_type() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		// multiplePOMap = context.getMultiplePOMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if ((!(null == context.getReceiveType())) && context.getReceiveType().equalsIgnoreCase("Under Receiving")) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceive(context.getSkuId(), context.getTagId(), date), failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						Integer.toString(context.getRcvQtyDue() + 5),
						inventoryDB.getQtyOnHand(context.getSkuId(), context.getLocation(), context.getTagId(), date),
						failureList);
			} else if (null == context.getReceiveType()) {
				verification.verifyData("Location for SKU after receive" + context.getSkuId(), context.getLocation(),
						inventoryDB.getLocationAfterReceiveForFlatpack(context.getSkuId(), context.getTagId(), date),
						failureList);
				verification.verifyData("Qty on Hand for SKU " + context.getSkuId(),
						String.valueOf(context.getRcvQtyDue()), inventoryDB.getQtyOnHandForFlatpack(context.getSkuId(),
								context.getLocation(), context.getTagId(), date),
						failureList);
			}
		}
		Assert.assertTrue(
				"Inventory details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
}