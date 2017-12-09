package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.ConsignmentDB;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.OrderPreparationPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.stepdefs.rdt.PurchaseOrderStockCheckStepDefs;
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private OrderHeaderDB orderHeaderDB;
	private InventoryDB inventoryDB;
	private OrderLineDB orderLineDB;
	private OrderHeaderPage orderHeaderPage;
	private SkuDB skuDB;
	private LocationDB locationDb;
	private OrderPreparationPage orderPreparationPage;
	private ConsignmentDB consignmentDB;

	private SupplierSkuDB supplierSkuDb;
	private InventoryQueryPage inventoryQueryPage;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private SkuSkuConfigDB skuSkuConfigDB;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PuttyFunctionsPage puttyFunctionsPage;
	private PurchaseOrderStockCheckStepDefs purchaseOrderStockCheckStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private AddressDB addressDB;

	@Inject
	public OrderHeaderStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			Context context, PreAdviceHeaderDB preAdviceHeaderDB, UPIReceiptHeaderDB upiReceiptHeaderDB,
			Verification verification, DeliveryDB deliveryDB, PreAdviceLineStepDefs preAdviceLineStepDefs,
			PreAdviceLineDB preAdviceLineDB, UPIReceiptLineDB upiReceiptLineDB, OrderHeaderDB orderHeaderDB,
			InventoryDB inventoryDB, OrderLineDB orderLineDB, OrderHeaderPage orderHeaderPage, SkuDB skuDB,
			LocationDB locationDb, OrderPreparationPage orderPreparationPage, ConsignmentDB consignmentDB,
			JdaHomePage jdaHomePage, InventoryQueryPage inventoryQueryPage, StockAdjustmentsPage stockAdjustmentsPage,
			SkuSkuConfigDB skuSkuConfigDB, PuttyFunctionsStepDefs puttyFunctionsStepDefs,
			PuttyFunctionsPage puttyFunctionsPage, PurchaseOrderStockCheckStepDefs purchaseOrderStockCheckStepDefs,
			SupplierSkuDB supplierSkuDb, InventoryQueryStepDefs inventoryQueryStepDefs, AddressDB addressDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage = jdaHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.inventoryDB = inventoryDB;
		this.orderLineDB = orderLineDB;
		this.orderHeaderPage = orderHeaderPage;
		this.skuDB = skuDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.locationDb = locationDb;
		this.orderPreparationPage = orderPreparationPage;
		this.consignmentDB = consignmentDB;

		this.inventoryQueryPage = inventoryQueryPage;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.skuSkuConfigDB = skuSkuConfigDB;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderStockCheckStepDefs = purchaseOrderStockCheckStepDefs;
		this.supplierSkuDb = supplierSkuDb;
		this.inventoryQueryStepDefs = inventoryQueryStepDefs;
		this.addressDB = addressDB;

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
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickExecuteButton();
		Assert.assertEquals("Order header not displayed as expected", "Picked",
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Given("^order header should be updated for unpicked stock$")
	public void order_header_should_be_updated_for_unpicked_stock() throws Throwable {
		Thread.sleep(2000);
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickExecuteButton();

		Assert.assertEquals("Order header not displayed as expected", "Released",
				orderHeaderDB.getStatus(context.getOrderId()));
	}

	@Given("^the order id of type \"([^\"]*)\" should be in \"([^\"]*)\" status and \"([^\"]*)\" skus should be in \"([^\"]*)\" location$")
	public void the_order_id_of_type_should_be_in_status_and_skus_should_be_in_location(String orderType, String status,
			String skuType, String locationId) throws Throwable {

		String orderNumber = context.getOrderId();

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
			context.setSkuId((String) skuFromOrder.get(i));

			if (context.getLocationID() != null && context.getLocationID().equalsIgnoreCase("suspense")) {
				if (!inventoryDB.isSkuInSuspenseLocation(context.getSkuId())) {
					Assert.assertTrue("Sku is not in suspense", false);
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

		// //Order status
		verification.verifyData("Order Status not displayed as expected", status, orderHeaderDB.getStatus(orderNumber),
				failureList);

		// jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	@Given("^the order id of type \"([^\"]*)\" should be in \"([^\"]*)\" status and \"([^\"]*)\" skus should be applicable for \"([^\"]*)\" disallowed$")
	public void the_order_id_of_type_should_be_in_status_and_skus_should_be_applicable_for_disallowed(String orderType,
			String status, String skuType, String locationId) throws Throwable {

		String orderNumber = context.getOrderId();

		context.setOrderId(orderNumber);
		context.setSKUType(skuType);
		ArrayList<String> failureList = new ArrayList<String>();
		context.setStatus(status);
		context.setLocationID(locationId);

		// calculate qty available for allocation
		ArrayList skuFromOrder = new ArrayList();
		skuFromOrder = orderLineDB.getskuList(context.getOrderId()); // sku from
																		// db
																		// column
		context.setSkuFromOrder(skuFromOrder);
		if (!(context.getSkuList().containsAll(context.getSkuFromOrder()))) {
			Assert.assertTrue("Sku mismatch in order and sku details in NPS DB", false);
		}
		for (int i = 0; i < skuFromOrder.size(); i++) {
			context.setSkuId((String) skuFromOrder.get(i));
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
			context.setSKUType(type);

			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		}

		// Prohibited
		if (context.getLocationID() != null && context.getLocationID().equalsIgnoreCase("PROHIBITION")) {
			String custNum = orderHeaderDB.getCustomer(context.getOrderId());
			if (!(addressDB.getUserDefType5(custNum).equalsIgnoreCase("PROHIBITED"))) {
				Assert.assertTrue("Order Origin values are not as expected", false);
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

		// jDALoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		Assert.assertTrue("Order Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	

	@Given("^I create a consignment for order$")
	public void i_create_a_consignment_for_order() throws Throwable {
		jdaHomePage.navigateToOrderPreparationPage();
		jdaFooter.clickNextButton();
		orderPreparationPage.enterOrderId(context.getOrderId());
		jdaFooter.clickNextButton();
		String consignment = "CON" + Utilities.getThreeDigitRandomNumber();
		orderPreparationPage.createNewConsignment(consignment);
		orderPreparationPage.selectRecord();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickNextButton();
		jdaFooter.clickDoneButton();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		context.setConsignmentID(consignment);
	}

	@Given("^I create a consignment for multiple order$")
	public void i_create_a_consignment_for_multiple_order() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		String consignment = "CON" + Utilities.getThreeDigitRandomNumber();
		while (consignmentDB.isConsignmentExists(consignment)) {
			consignment = "CON" + Utilities.getThreeDigitRandomNumber();
		}

		context.setConsignmentID(consignment);
		for (int k = 0; k < context.getOrderList().size(); k++) {
			context.setOrderId(context.getOrderList().get(k));
			orderHeaderDB.removeConsignment(context.getOrderId());
			if (k == 0) {
				jdaHomePage.navigateToOrderPreparationPage();
				jdaFooter.clickNextButton();
				orderPreparationPage.enterOrderId(context.getOrderId());
				jdaFooter.clickNextButton();
				orderPreparationPage.createNewConsignment(context.getConsignmentID());
				orderPreparationPage.selectRecord();
				jdaFooter.clickNextButton();
				jdaFooter.clickNextButton();
				jdaFooter.clickNextButton();
				jdaFooter.clickDoneButton();
				jdaFooter.PressEnter();
				jdaFooter.PressEnter();
			} else {
				jdaHomePage.navigateToOrderPreparationPage();
				orderPreparationPage.selectExisting();
				jdaFooter.clickNextButton();
				orderPreparationPage.enterConsignment(context.getConsignmentID());
				orderPreparationPage.enterOrderId(context.getOrderId());
				jdaFooter.clickNextButton();
				orderPreparationPage.selectRecord();
				jdaFooter.clickNextButton();
				orderPreparationPage.enterTrailerType("Trailer");
				jdaFooter.clickNextButton();
				jdaFooter.clickNextButton();
				jdaFooter.clickDoneButton();
				jdaFooter.PressEnter();
				jdaFooter.PressEnter();
			}
			verification.verifyData("consignment not updated" + context.getOrderId(), context.getConsignmentID(),
					orderHeaderDB.getConsignment(context.getOrderId()), failureList);
		}
		Assert.assertTrue("Consignment Details is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the OrderID of type \"([^\"]*)\" for sku \"([^\"]*)\" should be in \"([^\"]*)\" status at site \"([^\"]*)\"$")
	public void the_OrderID_of_type_for_sku_should_be_in_status(String orderType, String skuType, String status,
			String siteId) throws Throwable {
		// jdaLoginPage.login();

		String orderId = context.getOrderId();

		context.setOrderId(orderId);
		context.setOrderType(orderType);
		context.setSiteID(siteId);
		String Type = null;
		switch (skuType) {
		case "GOH":
			Type = "C";
			break;
		case "flatpack":
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
		jdaHomePage.navigateToOrderHeaderMaintenance();
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderID(orderId);
		jdaFooter.clickExecuteButton();
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

	@Then("^the order stock modularity should be visible$")
	public void the_Order_stock_modularity_should_be_visible() throws Throwable {
		inventoryQueryPage.clickUserDefinedTab();

		if (context.getSKUType().equalsIgnoreCase("Boxed")) {
			Assert.assertEquals("Stock Modularity does not match", "BOX",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
			Assert.assertEquals("Stock Modularity does not match", "HANG",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		} else if (context.getSKUType().equalsIgnoreCase("Flatpack")) {
			Assert.assertEquals("Stock Modularity does not match", "HANG",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		} else if (context.getSKUType().equalsIgnoreCase("GOH")) {
			Assert.assertEquals("Stock Modularity does not match", "HANG",
					orderHeaderDB.getStockModularity(context.getOrderId()));
		}

	}

	@Then("^I query with Order Id$")
	public void i_query_with_order_id() throws Throwable {
		jdaFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jdaFooter.clickExecuteButton();
	}

	@Then("^the order should be Ready to Load$")
	public void the_order_should_be_Ready_to_Load() throws Throwable {
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}
	@Then("^the multiple order should be Ready to Load$")
	public void the_multile_order_should_be_Ready_to_Load() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList<String> Orderlist=context.getOrderList();
		for (int z=0;z<Orderlist.size();z++){
		context.setOrderId(Orderlist.get(z));
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
		}
	}
}
