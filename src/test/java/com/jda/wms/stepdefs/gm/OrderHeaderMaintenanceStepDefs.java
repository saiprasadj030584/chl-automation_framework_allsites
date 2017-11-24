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
		Thread.sleep(60000);
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

	

}