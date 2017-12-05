package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderStockCheckStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class OrderHeaderMaintenanceStepDefs {
	ArrayList<String> failureList = new ArrayList<String>();
	private Context context;
	private JDAFooter jDAFooter;
	private OrderHeaderDB orderHeaderDB;
	private JdaHomePage jDAHomePage;
	private Verification verification;
	private OrderHeaderPage orderHeaderPage;
	private JdaLoginPage jdaLoginPage;
	private OrderLineDB orderLineDB;
	private GetTcData getTcData;
	private InventoryDB inventoryDB;
	private JDALoginStepDefs jdaLoginStepDefs;
	private LocationDB locationDb;
	private SkuDB skuDB;
	private InventoryQueryPage inventoryQueryPage;
	private LocationDB locationDB;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private SkuSkuConfigDB skuSkuConfigDB;
	private PurchaseOrderStockCheckStepDefs purchaseOrderStockCheckStepDefs;

	@Inject
	public OrderHeaderMaintenanceStepDefs(Context context, JDAFooter jDAFooter, JdaHomePage jDAHomePage,
			OrderHeaderDB orderHeaderDB, Verification verification, OrderHeaderPage orderHeaderPage,
			JdaLoginPage jdaLoginPage, OrderLineDB orderLineDB, GetTcData getTcData, InventoryDB inventoryDB,

			JDALoginStepDefs jdaLoginStepDefs, InventoryQueryPage inventoryQueryPage, LocationDB locationDb,
			SkuDB skuDB, LocationDB locationDB, StockAdjustmentsPage stockAdjustmentsPage,
			SkuSkuConfigDB skuSkuConfigDB,PurchaseOrderStockCheckStepDefs purchaseOrderStockCheckStepDefs) {

		this.context = context;
		this.jDAFooter = jDAFooter;
		this.jDAHomePage = jDAHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
		this.orderHeaderPage = orderHeaderPage;
		this.jdaLoginPage = jdaLoginPage;
		this.orderLineDB = orderLineDB;
		this.getTcData = getTcData;
		this.inventoryDB = inventoryDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.inventoryQueryPage = inventoryQueryPage;
		this.locationDb = locationDb;
		this.skuDB = skuDB;
		this.locationDB = locationDB;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.skuSkuConfigDB = skuSkuConfigDB;
		this.purchaseOrderStockCheckStepDefs = purchaseOrderStockCheckStepDefs ;
	}

	@Given("^the order of \"([^\"]*)\" should be in \"([^\"]*)\" status in order header maintenance$")
	public void the_order_of_of_type_should_be_in_status_in_order_header_maintenance(String orderType, String status)
			throws Throwable {
		// String orderId = getTcData.getSto();
		String orderId = context.getOrderId();

		Thread.sleep(8000);
		context.setOrderId(orderId);
		context.setOrderType(orderType);
		Assert.assertEquals("Status is not displayed as expected", status,
				orderHeaderDB.getStatus(context.getOrderId()));
		verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(context.getOrderId()),
				failureList);
		jdaLoginPage.login();
	}

	@Given("^the order should be in \"([^\"]*)\" status in order header maintenance$")
	public void the_order_should_be_in_status_in_order_header_maintenance(String status) throws Throwable {
		// String orderId = getTcData.getSto();
		Thread.sleep(20000);
		context.setOrderId(context.getOrderId());
		System.out.println(orderHeaderDB.getStatus(context.getOrderId()));
		Assert.assertEquals("Status is not displayed as expected", status,
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Then("^the order should be in \"([^\"]*)\" status$")
	public void the_order_should_be_in_status(String status) throws Throwable {
		Assert.assertEquals("Status is not displayed as expected", status,
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Given("^the order details should be displayed in order header$")
	public void the_order_details_should_be_displayed_after_allocation() throws Throwable {
		verification.verifyData("Site Id", "Not Null", orderHeaderDB.getFromSiteId(context.getOrderId()), failureList);
		// verification.verifyData("Consignment", "Not Null",
		// orderHeaderDB.getConsignment(context.getOrderId()),
		// failureList);
		verification.verifyData("Type", "Not Null", orderHeaderDB.getType(context.getOrderId()), failureList);
		verification.verifyData("Shipdock", "Not Null", orderHeaderDB.getShipdock(context.getOrderId()), failureList);
		verification.verifyData("Number of Lines", "Not Null", orderHeaderDB.getNumberOfLines(context.getOrderId()),
				failureList);
		context.setNoOfLines(Integer.parseInt(orderHeaderDB.getNumberOfLines(context.getOrderId())));

		Assert.assertTrue(
				"Order header details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the status should be displayed as \"([^\"]*)\" in order header$")
	public void the_status_should_be_displayed_as(String status) throws Throwable {
		Assert.assertEquals("Status is not displayed as expected", status, orderHeaderPage.getStatus());
	}

	@Then("^the status should be allocated for the orderID$")
	public void the_status_should_be_allocated_for_the_orderID() throws Throwable {
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue("Order Status not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^I check the status as \"([^\"]*)\" for given \"([^\"]*)\"$")
	public void i_check_the_status_as_for_given(String status, String orderId) throws Throwable {
		context.setOrderId(orderId);
		context.setStatus(status);
		if (status.equals("Released")) {
			String orderStatus = orderHeaderDB.getStatus(orderId);
			Assert.assertEquals("Order status does not match", status, orderStatus);
		}
	}

	@When("^the status should be turned as \"([^\"]*)\" in order header$")
	public void the_status_should_be_turned_as_in_order_header(String status) throws Throwable {
		Thread.sleep(15000);
		String orderStatus = orderHeaderDB.getStatus(context.getOrderId());
		Assert.assertEquals("Order status does not match", status, orderStatus);
	}

	@Then("^I verify the status as \"([^\"]*)\" in order header$")
	public void verify_the_status_as_in_order_header_page(String status) throws Throwable {
		String orderStatus = orderHeaderDB.getStatus(context.getOrderId());
		Assert.assertEquals("Order status does not match", status, orderStatus);
	}

	@Given("^the order is of type \"([^\"]*)\" and it is in \"([^\"]*)\" status$")
	public void the_order_status_is_in_status(String type, String status) throws Throwable {

		// String orderId = getTcData.getSto();
		String orderId = "5471000155";

		context.setOrderId(orderId);
		context.setSKUType(type);
		if (status.contains(orderHeaderDB.getStatus(orderId)) && orderHeaderDB.getType(orderId).contains(type)) {
			System.out.println("In Released Status...Waiting for 1 mins - Expecting Auto Daemon Job Run");
			TimeUnit.MINUTES.sleep(1);
		} else if (orderHeaderDB.getStatus(orderId).contains("Allocated")
				&& orderHeaderDB.getType(orderId).contains(type)) {
			Assert.assertTrue("Order is Auto Allocated", orderHeaderDB.getStatus(orderId) == "Allocated");
		} else {
			Assert.fail("This is not a <RETAIL> Order or the Order is not in <Released> status");
		}
	}

	@Given("^the order status is in \"([^\"]*)\" status raised for the country of origin \"([^\"]*)\"$")
	public void the_order_status_is_in_status_raised_for_the_country_of_origin(String status, String origin)
			throws Throwable {

		Assert.assertTrue("status is not in released status",
				status.equals(orderHeaderDB.getStatus(context.getOrderId())));
		Assert.assertTrue("origin " + origin + " is found in orderline",
				orderLineDB.getLocationList(context.getOrderId()).contains(origin));
	}

	@Then("^the order status should be in \"([^\"]*)\" status$")
	public void the_order_status_should_be_in_status(String status) throws Throwable {
		Assert.assertTrue("status is not in released status",
				status.equals(orderHeaderDB.getStatus(context.getOrderId())));
	}

	@Given("^the OrderID of type \"([^\"]*)\" should be in \"([^\"]*)\" status at site$")
	public void the_OrderID_of_type_should_be_in_status(String orderType, String status) throws Throwable {
		String orderId = getTcData.getSto();
		String siteId = context.getSiteID();

		/* Due to merge the fathima branch */

		// String orderId ="3170001592";
		// String siteId = "5649";
		// System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");

		jdaLoginPage.login();

		context.setOrderId(orderId);
		System.out.println(context.getOrderId());
		context.setOrderType(orderType);
		context.setSiteID(siteId);
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(orderId);
		jDAFooter.clickExecuteButton();
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", status, orderHeaderDB.getStatus(context.getOrderId()), failureList);
		verification.verifyData("Order Status", orderType, orderHeaderDB.getOrderType(context.getOrderId()),
				failureList);
		context.setSkuId(orderLineDB.getSkuId(orderId));
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the order should be allocated$")
	public void the_order_should_be_allocated() throws Throwable {
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();
		ArrayList failureList = new ArrayList();
		Thread.sleep(6000);
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue("Order Status not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the order status should be changed to \"([^\"]*)\" status$")
	public void the_order_status_should_be_changed_to_status(String status) throws Throwable {
		Assert.assertTrue("Order is not in expected status",
				status.equals(orderHeaderDB.getStatus(context.getOrderId())));
	}

	@Given("^the order id of type \"([^\"]*)\" should be in \"([^\"]*)\" status and skus should be in \"([^\"]*)\" location$")
	public void the_order_id_of_type_should_be_in_status_and_sku_should_be_in_location(String orderType, String status,
			String locationId) throws Throwable {
		String orderNumber = getTcData.getSto();

		context.setOrderId(orderNumber);
		ArrayList<String> failureList = new ArrayList<String>();
		context.setStatus(status);
		// suspense
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		for (int i = 0; i < skuFromOrder.size(); i++) {
			if (!(inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))))) {
				Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i),
						inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
			}
		}
		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}

		// //Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
				failureList);

		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the order id of type \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_order_id_of_type_should_be_in_status(String orderType, String status) throws Throwable {

		String orderNumber = getTcData.getSto();
		System.out.println("Order Number " + orderNumber);

		context.setOrderId(orderNumber);
		context.setStatus(status);
		ArrayList<String> failureList = new ArrayList<String>();
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		for (int i = 0; i < skuFromOrder.size(); i++) {
			if ((inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))))) {
				failureList.add("Sku in suspense location " + (String) skuFromOrder.get(i));
			}
		}
		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}
		// Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
				failureList);

		// jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the order id \"([^\"]*)\" should be in \"([^\"]*)\" status$")
	public void the_order_id_should_be_in_status(String orderNumber, String status) throws Throwable {
		context.setOrderId(orderNumber);
		context.setStatus(status);
		Assert.assertEquals("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber));
		// jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
	}

	@Given("^order header should be updated for picked stock$")
	public void order_header_should_be_updated_for_picked_stock() throws Throwable {
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();
		Assert.assertEquals("Order header not displayed as expected", "Picked",
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Given("^order header should be updated for unpicked stock$")
	public void order_header_should_be_updated_for_unpicked_stock() throws Throwable {
		Thread.sleep(2000);
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();

		Assert.assertEquals("Order header not displayed as expected", "Released",
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Given("^the order id of type \"([^\"]*)\" with \"([^\"]*)\" skus should be in \"([^\"]*)\" status1$")
	public void the_order_id_of_type_with_skus_should_be_in_status1(String orderType, String skuType, String status)
			throws Throwable {

		String orderNumber = getTcData.getSto();
		// String orderNumber = "4704313644";

		context.setOrderId(orderNumber);
		context.setSKUType(skuType);
		context.setStatus(status);
		ArrayList<String> failureList = new ArrayList<String>();
		// suspense
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		boolean allocation = false;
		for (int i = 0; i < skuFromOrder.size(); i++) {

			ArrayList<String> locationList = inventoryDB.getLocationsForSku((String) skuFromOrder.get(i));
			System.out.println(locationList);
			ArrayList<String> validLocations = new ArrayList<String>();
			int totalQtyOnHand = 0;
			for (int j = 0; j < locationList.size(); j++) {
				if (locationDb.getLocationZone(locationList.get(j)) != null) {
					if (locationDb.getLocationZone(locationList.get(j)).equalsIgnoreCase("HANG")
							|| locationDb.getLocationZone(locationList.get(j)).contains("BOX")) {
						System.out.println("entered" + locationList.get(j));
						validLocations.add(locationList.get(j));
						totalQtyOnHand += Integer.parseInt(
								inventoryDB.getQtyForSkuInLocation((String) skuFromOrder.get(i), locationList.get(j)));
					}

				}
			}
			System.out.println(validLocations);
			System.out.println("totalQtyOnHand" + totalQtyOnHand);
			if (totalQtyOnHand >= Integer
					.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), (String) skuFromOrder.get(i)))) {
				allocation = true;
			}

			if (context.getLocationID() != null) {
				if (context.getLocationID().equalsIgnoreCase("suspense")) {
					Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i),
							inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
					Assert.assertFalse("Stock is present in other locations", allocation);
				}
			} else {

				Assert.assertTrue("Stock is not present in other locations", allocation);

			}

			// To Validate Modularity,New Product Check for SKU

			String type = null;
			switch (context.getSKUType()) {
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
			// TODO Check for multiple skus

			verification.verifyData("SKU Type", type, skuDB.getSKUType((String) skuFromOrder.get(i)), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue((String) skuFromOrder.get(i)),
					failureList);
		}

		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}

		// Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
				failureList);

		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the order id of type \"([^\"]*)\" should be in \"([^\"]*)\" status and \"([^\"]*)\" skus should be in \"([^\"]*)\" location$")
	public void the_order_id_of_type_should_be_in_status_and_skus_should_be_in_location(String orderType, String status,
			String skuType, String locationId) throws Throwable {
		String orderNumber = getTcData.getSto();
		context.setOrderId(orderNumber);
		context.setSKUType(skuType);
		ArrayList<String> failureList = new ArrayList<String>();
		context.setStatus(status);
		context.setLocationID(locationId);

		// calculate qty available for allocation
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		boolean allocation = false;
		for (int i = 0; i < skuFromOrder.size(); i++) {

			ArrayList<String> locationList = inventoryDB.getLocationsForSku((String) skuFromOrder.get(i));
			ArrayList<String> validLocations = new ArrayList<String>();
			int totalQtyOnHand = 0;
			for (int j = 0; j < locationList.size(); j++) {
				if (locationDb.getLocationZone(locationList.get(j)) != null) {
					if (locationDb.getLocationZone(locationList.get(j)).equalsIgnoreCase("Hang")
							|| locationDb.getLocationZone(locationList.get(j)).contains("Box")) {
						validLocations.add(locationList.get(j));
						totalQtyOnHand += Integer.parseInt(
								inventoryDB.getQtyForSkuInLocation((String) skuFromOrder.get(i), locationList.get(j)));
					}

				}
			}
			System.out.println("totalQtyOnHand" + totalQtyOnHand);
			// if(totalQtyOnHand>=Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(),(String)skuFromOrder.get(i))))
			if (totalQtyOnHand != 0) {
				context.setTotQtyOnHand(String.valueOf(totalQtyOnHand));
				allocation = true;
			}

			// Suspense
			if (context.getLocationID() != null) {
				if (context.getLocationID().equalsIgnoreCase("suspense")) {
					Assert.assertTrue("Sku not in suspense location " + (String) skuFromOrder.get(i),
							inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
					Assert.assertFalse("Stock is present in other locations", allocation);
				}
			} else {
				Assert.assertFalse("Sku is in suspense location " + (String) skuFromOrder.get(i),
						inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i))));
				Assert.assertTrue("Stock is not present in other locations", allocation);
			}

			// To Validate Modularity,New Product Check for SKU

			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			// TODO Check for multiple skus
			context.setSKUType(type);

			verification.verifyData("SKU Type", type, skuDB.getSKUType((String) skuFromOrder.get(i)), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue((String) skuFromOrder.get(i)),
					failureList);
		}

		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}

		// Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
				failureList);

		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	@Then("^I query with Order Id$")
	public void i_query_with_order_id() throws Throwable {
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();
	}

	@Then("^the order stock modularity should be visible$")
	public void the_Order_stock_modularity_should_be_visible() throws Throwable {
		// inventoryQueryPage.clickUserDefinedTab();
		context.setSKUType(skuDB.getSKUType(orderLineDB.getSkuId(context.getOrderId())));
		if (context.getSKUType().equalsIgnoreCase("B")) {
			Assert.assertEquals("Order Stock Modularity is not as expected", "BOX",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		} else if (context.getSKUType().equalsIgnoreCase("H")) {
			Assert.assertEquals("Order Stock Modularity is not as expected", "HANG",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		} else if (context.getSKUType().equalsIgnoreCase("C")) {
			Assert.assertEquals("Order Stock Modularity is not as expected", "HANG",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		}
	}

	@Then("^the order should be picked$")
	public void the_order_should_be_picked() throws Throwable {
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", "Picked", orderHeaderDB.getStatus(context.getOrderId()), failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the OrderID of type \"([^\"]*)\" for sku \"([^\"]*)\" should be in \"([^\"]*)\" status at site$")
	public void the_OrderID_of_type_for_sku_should_be_in_status(String orderType, String skuType, String status)
			throws Throwable {
		String orderId = getTcData.getSto();
		String siteId = context.getSiteID();
		jdaLoginPage.login();
		context.setOrderId(orderId);
		context.setOrderType(orderType);
		context.setSiteID(siteId);
		String Type = null;
		switch (skuType) {
		case "GOH":
			Type = "C";
			break;
		case "Flatpack":
			Type = "P";
			break;
		case "Boxed":
			Type = "B";
			break;
		case "Hanging":
			Type = "H";
			break;
		}
		context.setSKUType(Type);
		context.setSkuId(orderHeaderDB.getSkuId(orderId));
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(orderId);
		jDAFooter.clickExecuteButton();
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", status, orderHeaderDB.getStatus(orderId), failureList);
		verification.verifyData("Order Type", orderType, orderHeaderDB.getOrderType(orderId), failureList);
		verification.verifyData("SKU Type", context.getSKUType(), skuDB.getSKUType(context.getSkuId()), failureList);
		verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		context.setSkuId(orderLineDB.getSkuId(orderId));
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the order should be Ready to Load$")
	public void the_order_should_be_Ready_to_Load() throws Throwable {
		verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the order should be allocated with prohibition flag$")
	public void the_order_should_be_allocated_for_prohibition_flag() throws Throwable {
		jDAHomePage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();
		ArrayList failureList = new ArrayList();
		Thread.sleep(6000);
		verification.verifyData("Order Status", "Allocated", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		verification.verifyData("Qty task", orderHeaderDB.getOrderedQuantityWithOrderId(context.getOrderId()),
				orderHeaderDB.getQtyTaskedWithOrderID(context.getOrderId()), failureList);
		Assert.assertTrue("Order Status not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
		String sku_id = orderLineDB.getSkuId(context.getOrderId());
		context.setSkuId(sku_id);
		Assert.assertEquals("Prohibition flag not displayed as expected", "PROHIBITION",
				inventoryDB.getProhibitionFlag(context.getSkuId()));
	}

	@Given("^check the loc type for the boxed preffered zones$")
	public void check_the_loc_type_for_the_boxed_preffered_zones() throws Throwable {
		System.out.println("Count of boxed preffered zones without tag-FIFO" + locationDB.checkBoxZone());
		int check = Integer.parseInt(locationDB.checkBoxZone());
		if (check != 0)
			Assert.fail("Box location is not boxed preferable location");
	}

	@Given("^the order id of type \"([^\"]*)\" with \"([^\"]*)\" skus should be in \"([^\"]*)\" status$")
	public void the_order_id_of_type_with_skus_should_be_in_status(String orderType, String skuType, String status)
			throws Throwable {
		String orderNumber = context.getOrderId();
		context.setOrderId(orderNumber);
		context.setSKUType(skuType);
		context.setStatus(status);
		ArrayList<String> failureList = new ArrayList<String>();
		// suspense
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skuFromOrder);
		boolean allocation = false;
		for (int i = 0; i < skuFromOrder.size(); i++) {
			context.setSkuId((String) skuFromOrder.get(i));
			ArrayList<String> locationList = inventoryDB.getLocationsForSku((String) skuFromOrder.get(i));
			System.out.println(locationList);
			ArrayList<String> validLocations = new ArrayList<String>();
			int totalQtyOnHand = 0;
			for (int j = 0; j < locationList.size(); j++) {
				if (locationDb.getLocationZone(locationList.get(j)) != null) {
					if (inventoryDB.getOriginId(locationList.get(j)) != null
							&& inventoryDB.getOriginId(locationList.get(j)).contains("NONE")) {
						if (context.getSKUType().equalsIgnoreCase("Hanging")
								|| context.getSKUType().equalsIgnoreCase("GOH")) {
							if (locationDb.getLocationZone(locationList.get(j)).equalsIgnoreCase("HANG")
									&& locationDb.getUserDefType2(locationList.get(j)).contains("HANG")
									&& locationDb.getUserDefType3(locationList.get(j)).contains("HANG")
									&& locationDb.getUserDefType1(locationList.get(j))
											.contains(skuDB.getProductGroup((String) skuFromOrder.get(i)))) {
								System.out.println(
										inventoryDB.getLockStatus(locationList.get(j), (String) skuFromOrder.get(i)));
								if (inventoryDB.getLockStatus(locationList.get(j), (String) skuFromOrder.get(i))
										.equalsIgnoreCase("UnLocked")
										&& Integer.parseInt(inventoryDB.getQtynHand((String) skuFromOrder.get(i),
												locationList.get(j))) != Integer.parseInt(
														inventoryDB.getAllocatedQty((String) skuFromOrder.get(i),
																locationList.get(j)))) {
									System.out.println("entered" + locationList.get(j));
									validLocations.add(locationList.get(j));
									totalQtyOnHand += Integer.parseInt(inventoryDB
											.getQtyForSkuInLocation((String) skuFromOrder.get(i), locationList.get(j)));
								}
							}
						} else if (context.getSKUType().equalsIgnoreCase("Boxed")) {
							if (locationDb.getLocationZone(locationList.get(j)).contains("BOX")
									&& locationDb.getUserDefType2(locationList.get(j)).contains("BOX")
									&& locationDb.getUserDefType3(locationList.get(j)).contains("BOX")) {
								System.out.println(
										inventoryDB.getLockStatus(locationList.get(j), (String) skuFromOrder.get(i)));
								if (inventoryDB.getLockStatus(locationList.get(j), (String) skuFromOrder.get(i))
										.equalsIgnoreCase("UnLocked")
										&& Integer.parseInt(inventoryDB.getQtynHand((String) skuFromOrder.get(i),
												locationList.get(j))) != Integer.parseInt(
														inventoryDB.getAllocatedQty((String) skuFromOrder.get(i),
																locationList.get(j)))) {
									System.out.println("entered" + locationList.get(j));
									validLocations.add(locationList.get(j));
									totalQtyOnHand += Integer.parseInt(inventoryDB
											.getQtyForSkuInLocation((String) skuFromOrder.get(i), locationList.get(j)));
								}
							}
						} else if (context.getSKUType().equalsIgnoreCase("Flatpack")) {
							if ((locationDb.getLocationZone(locationList.get(j)).contains("BOX")
									&& locationDb.getUserDefType2(locationList.get(j)).contains("BOX")
									&& locationDb.getUserDefType3(locationList.get(j)).contains("FLAT"))
									|| (locationDb.getLocationZone(locationList.get(j)).contains("HANG")
											&& locationDb.getUserDefType2(locationList.get(j)).contains("HANG")
											&& locationDb.getUserDefType3(locationList.get(j)).contains("FLAT")
											&& locationDb.getUserDefType1(locationList.get(j))
													.contains(skuDB.getProductGroup(context.getSkuId())))) {
								System.out.println(
										inventoryDB.getLockStatus(locationList.get(j), (String) skuFromOrder.get(i)));
								if (inventoryDB.getLockStatus(locationList.get(j), (String) skuFromOrder.get(i))
										.equalsIgnoreCase("UnLocked")
										&& Integer.parseInt(inventoryDB.getQtynHand((String) skuFromOrder.get(i),
												locationList.get(j))) != Integer.parseInt(
														inventoryDB.getAllocatedQty((String) skuFromOrder.get(i),
																locationList.get(j)))) {
									System.out.println("entered" + locationList.get(j));
									validLocations.add(locationList.get(j));
									totalQtyOnHand += Integer.parseInt(inventoryDB
											.getQtyForSkuInLocation((String) skuFromOrder.get(i), locationList.get(j)));
								}
							}
						}
					}
				}
			}
			System.out.println(validLocations);
			System.out.println("totalQtyOnHand" + totalQtyOnHand);
			if (totalQtyOnHand >= Integer
					.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), (String) skuFromOrder.get(i)))) {
				allocation = true;
			}
			if (context.getLocationID() != null) {
				System.out.println("1st *****************");
				if (context.getLocationID().equalsIgnoreCase("suspense")) {
					if (!inventoryDB.isSkuInSuspenseLocation((String) (skuFromOrder.get(i)))) {
						System.out.println("Sku not in suspense location " + (String) skuFromOrder.get(i));
						// do stock check
						purchaseOrderStockCheckStepDefs.i_do_new_stock_check_at_location_with_quantity(
								validLocations.get(0),
								String.valueOf(Integer.valueOf(
										inventoryDB.getQtynHand((String) skuFromOrder.get(i), validLocations.get(0)))
										- 1));
					}
					if (allocation) {
						System.out.println("Stock is present in other locations");
						System.out.println("Sku id :" + context.getSkuId());
						// update in inventory of valid locations to 0
						if (validLocations.size() != 0) {
							for (int j = 0; j < validLocations.size(); j++) {
								// inventoryDB.updateInventoryQty(validLocations.get(i),String.valueOf(0));
								purchaseOrderStockCheckStepDefs.i_do_new_stock_check_at_location_with_quantity(
										validLocations.get(i),
										inventoryDB.getQtynHand((String) skuFromOrder.get(i), validLocations.get(i)));
							}
						}
					}
				}
			} else {

				// Assert.assertTrue("Stock is not present in other locations" ,
				// allocation);
				System.out.println("Stock is not present in other locations");
				if (!(allocation)) {
					// default:stock adjustment to qty 500
					jDAHomePage.navigateToStockAdjustment();
					Thread.sleep(2000);
					stockAdjustmentsPage.selectNewStock();
					jDAFooter.clickNextButton();
					Thread.sleep(2000);
					stockAdjustmentsPage.enterSkuId(context.getSkuId());
					jDAFooter.pressTab();
					//
					if (validLocations.size() != 0) {
						stockAdjustmentsPage.enterLocation(validLocations.get(0));
					} else {
						if (context.getSKUType().equalsIgnoreCase("Boxed")) {
							stockAdjustmentsPage.enterLocation(locationDb.getToLocationForPutawayBoxed("BOX"));
						} else if (context.getSKUType().equalsIgnoreCase("GOH")
								|| context.getSKUType().equalsIgnoreCase("Hanging")) {
							stockAdjustmentsPage.enterLocation(locationDb.getToLocationForPutaway("HANG",
									skuDB.getProductGroup(context.getSkuId())));
						} else if (context.getSKUType().equalsIgnoreCase("Flatpack")) {
							stockAdjustmentsPage.enterLocation(locationDb
									.getToLocationForPutawayFlatpack(skuDB.getProductGroup(context.getSkuId())));
						}
					}
					stockAdjustmentsPage.enterSiteId(context.getSiteID());
					stockAdjustmentsPage.enterQuantityOnHand("500");
					stockAdjustmentsPage.enterOrigin("NONE");
					stockAdjustmentsPage.enterPackConfig((String)skuSkuConfigDB.getPackConfigList(context.getSkuId()).get(0));
					jDAFooter.clickNextButton();
					stockAdjustmentsPage.enterPalletType("PALLET");
					jDAFooter.clickNextButton();
					stockAdjustmentsPage.enterReasonCode();
					jDAFooter.clickDoneButton();
					stockAdjustmentsPage.handlePopUp();
				}

			}
			// To Validate Modularity,New Product Check for SKU

			String type = null;
			switch (context.getSKUType()) {
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
			// TODO Check for multiple skus

			verification.verifyData("SKU Type", type, skuDB.getSKUType((String) skuFromOrder.get(i)), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue((String) skuFromOrder.get(i)),
					failureList);
		}

		// order type
		if (orderType.equalsIgnoreCase("International")) {
			verification.verifyData("Order Type Mismatch", "Retail", orderHeaderDB.getOrderType(orderNumber),
					failureList);
			verification.verifyData("Destination is not as expected", "8468", orderHeaderDB.getCustomer(orderNumber),
					failureList);
			verification.verifyData("User defined Type 4 is not as expected", "Franchise",
					orderHeaderDB.getUserDefinedType4(orderNumber), failureList);
		} else if (orderType.equalsIgnoreCase("Ecom")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "4624", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else if (orderType.equalsIgnoreCase("Outlet")) {
			verification.verifyData("Order Type Mismatch", "IDT", orderHeaderDB.getOrderType(orderNumber), failureList);
			verification.verifyData("Destination is not as expected", "2862", orderHeaderDB.getCustomer(orderNumber),
					failureList);
		} else {
			verification.verifyData("Order Type Mismatch", orderType, orderHeaderDB.getOrderType(orderNumber),
					failureList);
		}

		// Order status
		// verification.verifyData("Order Status not displayed as expected",
		// status, orderHeaderDB.getStatus(orderNumber),
		// failureList);

		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	

}