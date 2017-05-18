package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.OrderHeaderMaintenanceDB;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;
import com.jda.wms.pages.foods.OrderLineMaintenancePage;
import com.jda.wms.pages.foods.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderMaintenanceStepDefs {
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private Context context;
	private AddressMaintenancePage addressMaintenancePage;
	private Verification verification;
	private OrderLineMaintenancePage orderLineMaintenancePage;
	private OrderHeaderMaintenanceDB orderHeaderMaintenanceDB;


	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage, Verification verification,
			OrderLineMaintenancePage orderLineMaintenancePage,OrderHeaderMaintenanceDB orderHeaderMaintenanceDB
	) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.addressMaintenancePage = addressMaintenancePage;
		this.verification = verification;
		this.orderLineMaintenancePage = orderLineMaintenancePage;
		this.orderHeaderMaintenanceDB = orderHeaderMaintenanceDB;
	}

	@Given("^the bulk pick order \"([^\"]*)\" should be \"([^\"]*)\" status, \"([^\"]*)\" type, order details in the order header maintenance table$")
	public void the_bulk_pick_order_should_be_status_type_order_details_in_the_order_header_maintenance_table(
			String orderID, String status, String type) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		verification.verifyData("Bulk pick order status", status, orderHeaderMaintenancePage.getStatus(), failureList);
		verification.verifyData("Order date", "Not Null", orderHeaderMaintenancePage.getOrderDate(), failureList);
		verification.verifyData("Created By", "Not Null", orderHeaderMaintenancePage.getCreatedBy(), failureList);
		verification.verifyData("Order time", "Not Null", orderHeaderMaintenancePage.getOrderTime(), failureList);
		verification.verifyData("Creation date", "Not Null", orderHeaderMaintenancePage.getCreationDate(), failureList);
		verification.verifyData("Creation time", "Not Null", orderHeaderMaintenancePage.getCreationTime(), failureList);
		verification.verifyData("Move task status", "Not Null", orderHeaderMaintenancePage.getMoveTaskStatus(),
				failureList);
		verification.verifyData("From site id", "Not Null", orderHeaderMaintenancePage.getFromSiteId(), failureList);
		verification.verifyData("Type", "Not Null", orderHeaderMaintenancePage.getType(), failureList);
		verification.verifyData("Number of lines", "Not Null", orderHeaderMaintenancePage.getNumberOfLines(),
				failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have delivery address details in delivery address tab$")
	public void the_order_should_have_delivery_address_details_in_delivery_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryAddressTab();

		String customer = orderHeaderMaintenancePage.getCustomer();
		context.setCustomer(customer);
		verification.verifyData("Customer", "Not Null", customer, failureList);
		verification.verifyData("Name", "Not Null", orderHeaderMaintenancePage.getName(), failureList);
		verification.verifyData("Address1", "Not Null", orderHeaderMaintenancePage.getAddress1(), failureList);
		verification.verifyData("Country", "Not Null", orderHeaderMaintenancePage.getCountry(), failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have delivery details in delivery details and user defined tabs$")
	public void the_order_should_have_delivery_details_in_delivery_details_and_user_defined_tabs() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryDetailsTab();
		verification.verifyData("ShipByDate", "Not Null", orderHeaderMaintenancePage.getShipByDate(), failureList);
		verification.verifyData("DeliveryByTimeFromDeliveryDetailsTab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByTimeFromDeliveryDetailsTab(), failureList);
		verification.verifyData("DeliveryByDateFromDeliveryDetailsTab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByDateFromDeliveryDetailsTab(), failureList);
		verification.verifyData("ShipByTime", "Not Null", orderHeaderMaintenancePage.getShipByTime(), failureList);

		orderHeaderMaintenancePage.clickUserDefinedTab();
		verification.verifyData("DeliveryType", "Not Null", orderHeaderMaintenancePage.getDeliveryType(), failureList);
		verification.verifyData("DeliveryByTimeFromUserDefinedTab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByTimeFromUserDefinedTab(), failureList);
		verification.verifyData("DeliveryByDateFromUserDefinedtab", "Not Null",
				orderHeaderMaintenancePage.getDeliveryByDateFromUserDefinedtab(), failureList);
		verification.verifyData("IfosOrderNum", "Not Null", orderHeaderMaintenancePage.getIfosOrderNum(), failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the customer should have CSSM flag updated in address maintenance table$")
	public void the_customer_should_have_CSSM_flag_updated_in_address_maintenance_table() throws Throwable {

		jdaHomeStepDefs.i_navigate_to_address_maintenance_page();
		jdaFooter.clickQueryButton();
		addressMaintenancePage.enterAddressID(context.getCustomer());
		jdaFooter.clickExecuteButton();

		addressMaintenancePage.clickUserDefinedTab();
		Assert.assertTrue("The address Id is not a STR. Expected [checkbox selected] but was [not selected]",
				addressMaintenancePage.isCSSMChecked());
	}

	@Given("^the order should have hub details in hub address tab$")
	public void the_order_should_have_hub_details_in_hub_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickHubAddressTab();
		verification.verifyData("Hub", "Not Null", orderHeaderMaintenancePage.getHub(), failureList);
		verification.verifyData("HubName", "Not Null", orderHeaderMaintenancePage.getHubName(), failureList);
		verification.verifyData("HubCountry", "Not Null", orderHeaderMaintenancePage.getHubCountry(), failureList);

		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}
	@Given("^the order should be in \"([^\"]*)\" status$")
	
	public void the_order_should_be_in_status(String status) throws Throwable {
		String orderStatus =orderHeaderMaintenanceDB.getOrderStatus(context.getOrderId());
	}

	

	
	
}
