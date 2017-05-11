package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;

import cucumber.api.java.en.Given;

public class OrderHeaderMaintenanceStepDefs {
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private Context context;
	private AddressMaintenancePage addressMaintenancePage;

	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.addressMaintenancePage = addressMaintenancePage;
	}

	@Given("^the bulk pick order \"([^\"]*)\" should be \"([^\"]*)\" status, order details in the order header maintenance table$")
	public void the_bulk_pick_order_should_be_status_order_details_in_the_order_header_maintenance_table(String orderID,
			String actualstatus) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String status = orderHeaderMaintenancePage.getStatus();
		System.out.println(status);
		if (!status.equals(actualstatus)) {
			failureList.add("Status is not as expected. Expected [" + actualstatus + "] but was [" + status + "]");
		}

		String orderDate = orderHeaderMaintenancePage.getOrderDate();
		if (orderDate.equals(null)) {
			failureList.add("OrderDate is not as expected. Expected [Not NULL] but was [" + orderDate + "]");
		}

		String createdBy = orderHeaderMaintenancePage.getCreatedBy();
		if (createdBy.equals(null)) {
			failureList.add("CreatedBy is not as expected. Expected [Not NULL] but was [" + createdBy + "]");
		}

		String orderTime = orderHeaderMaintenancePage.getOrderTime();
		if (orderTime.equals(null)) {
			failureList.add("OrderTime is not as expected. Expected [Not NULL] but was [" + orderTime + "]");
		}

		String creationDate = orderHeaderMaintenancePage.getCreationDate();
		if (creationDate.equals(null)) {
			failureList.add("Creation Date is not as expected. Expected [Not NULL] but was [" + creationDate + "]");
		}
		String creationTime = orderHeaderMaintenancePage.getCreationTime();
		if (creationTime.equals(null)) {
			failureList.add("Creation Time  is not as expected. Expected [Not NULL] but was [" + creationTime + "]");
		}

		String moveTaskStatus = orderHeaderMaintenancePage.getMoveTaskStatus();
		if (moveTaskStatus.equals(null)) {
			failureList
					.add("Move Task Status is not as expected. Expected [Not NULL] but was [" + moveTaskStatus + "]");
		}
		String fromSiteId = orderHeaderMaintenancePage.getFromSiteId();
		if (fromSiteId.equals(null)) {
			failureList.add("From SiteId is not as expected. Expected [Not NULL] but was [" + fromSiteId + "]");
		}
		String type = orderHeaderMaintenancePage.getType();
		if (!type.equals("RDC")) {
			failureList.add("Type is not as expected. Expected [RDC] but was [" + type + "]");
		}
		String numberOfLines = orderHeaderMaintenancePage.getNumberOfLines();
		if (numberOfLines.equals(null)) {
			failureList.add("Number Of Lines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}
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
		if (customer.equals(null)) {
			failureList.add("Customer is not as expected. Expected [Not NULL] but was [" + customer + "]");
		}
		String name = orderHeaderMaintenancePage.getName();
		if (name.equals(null)) {
			failureList.add("Name is not as expected. Expected [Not NULL] but was [" + name + "]");
		}
		String address1 = orderHeaderMaintenancePage.getAddress1();
		if (address1.equals(null)) {
			failureList.add("Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
		}
		String country = orderHeaderMaintenancePage.getCountry();
		if (country.equals(null)) {
			failureList.add("Country is not as expected. Expected [Not NULL] but was [" + country + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have delivery details in delivery details and user defined tabs$")
	public void the_order_should_have_delivery_details_in_delivery_details_and_user_defined_tabs() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryDetailsTab();

		String shipByDate = orderHeaderMaintenancePage.getShipByDate();
		if (shipByDate.equals(null)) {
			failureList.add("Ship By Date is not as expected. Expected [Not NULL] but was [" + shipByDate + "]");
		}
		String shipByTime = orderHeaderMaintenancePage.getShipByTime();
		if (shipByTime.equals(null)) {
			failureList.add("Ship By Time is not as expected. Expected [Not NULL] but was [" + shipByTime + "]");
		}
		String deliveryByTimeFromDeliveryDetailsTab = orderHeaderMaintenancePage
				.getDeliveryByTimeFromDeliveryDetailsTab();
		if (deliveryByTimeFromDeliveryDetailsTab.equals(null)) {
			failureList.add("Delivery By Time is not as expected. Expected [Not NULL] but was ["
					+ deliveryByTimeFromDeliveryDetailsTab + "]");
		}
		String deliveryByDateFromDeliveryDetailsTab = orderHeaderMaintenancePage
				.getDeliveryByDateFromDeliveryDetailsTab();
		if (deliveryByDateFromDeliveryDetailsTab.equals(null)) {
			failureList.add("Delivery By Date is not as expected. Expected [Not NULL] but was ["
					+ deliveryByDateFromDeliveryDetailsTab + "]");
		}

		orderHeaderMaintenancePage.clickUserDefinedTab();

		String deliveryType = orderHeaderMaintenancePage.getDeliveryType();
		if (deliveryType.equals(null)) {
			failureList.add("Delivery Type is not as expected. Expected [Not NULL] but was [" + deliveryType + "]");
		}
		String deliveryByTimeFromUserDefinedTab = orderHeaderMaintenancePage.getDeliveryByTimeFromUserDefinedTab();
		if (deliveryByTimeFromUserDefinedTab.equals(null)) {
			failureList.add("Delivery By Time From User Defined  tab is not as expected. Expected [Not NULL] but was ["
					+ deliveryByTimeFromUserDefinedTab + "]");
		}
		String deliveryByDateFromUserDefinedTab = orderHeaderMaintenancePage.getDeliveryByDateFromUserDefinedtab();
		if (deliveryByDateFromUserDefinedTab.equals(null)) {
			failureList.add("Delivery By Date From User Defined  tab is not as expected. Expected [Not NULL] but was ["
					+ deliveryByDateFromUserDefinedTab + "]");
		}
		String ifosOrderNum = orderHeaderMaintenancePage.getIfosOrderNum();
		if (ifosOrderNum.equals(null)) {
			failureList.add("Ifos Order Num is not as expected. Expected [Not NULL] but was [" + ifosOrderNum + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the STO \"([^\"]*)\" should be \"([^\"]*)\" status, order details in the order header maintenance table$")
	public void the_STO_should_be_status_order_details_in_the_order_header_maintenance_table(String orderID,
			String actualstatus) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String status = orderHeaderMaintenancePage.getStatus();
		System.out.println(status);
		if (!status.equals(actualstatus)) {
			failureList.add("Status is not as expected. Expected [" + actualstatus + "] but was [" + status + "]");
		}

		String orderDate = orderHeaderMaintenancePage.getOrderDate();
		if (orderDate.equals(null)) {
			failureList.add("OrderDate is not as expected. Expected [Not NULL] but was [" + orderDate + "]");
		}

		String createdBy = orderHeaderMaintenancePage.getCreatedBy();
		if (createdBy.equals(null)) {
			failureList.add("CreatedBy is not as expected. Expected [Not NULL] but was [" + createdBy + "]");
		}

		String orderTime = orderHeaderMaintenancePage.getOrderTime();
		if (orderTime.equals(null)) {
			failureList.add("OrderTime is not as expected. Expected [Not NULL] but was [" + orderTime + "]");
		}

		String creationDate = orderHeaderMaintenancePage.getCreationDate();
		if (creationDate.equals(null)) {
			failureList.add("Creation Date is not as expected. Expected [Not NULL] but was [" + creationDate + "]");
		}
		String creationTime = orderHeaderMaintenancePage.getCreationTime();
		if (creationTime.equals(null)) {
			failureList.add("Creation Time is not as expected. Expected [Not NULL] but was [" + creationTime + "]");
		}

		String moveTaskStatus = orderHeaderMaintenancePage.getMoveTaskStatus();
		if (moveTaskStatus.equals(null)) {
			failureList
					.add("Move Task Status is not as expected. Expected [Not NULL] but was [" + moveTaskStatus + "]");
		}
		String fromSiteId = orderHeaderMaintenancePage.getFromSiteId();
		if (fromSiteId.equals(null)) {
			failureList.add("From SiteId is not as expected. Expected [Not NULL] but was [" + fromSiteId + "]");
		}
		String type = orderHeaderMaintenancePage.getType();
		if (!type.equals("STR")) {
			failureList.add("Type is not as expected. Expected [STR] but was [" + type + "]");
		}
		String numberOfLines = orderHeaderMaintenancePage.getNumberOfLines();
		if (numberOfLines.equals(null)) {
			failureList.add("Number Of Lines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}
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

	@Given("^the INT \"([^\"]*)\" should be \"([^\"]*)\" status, order details in the order header maintenance table$")
	public void the_INT_should_be_status_order_details_in_the_order_header_maintenance_table(String orderID,
			String actualstatus) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String status = orderHeaderMaintenancePage.getStatus();
		System.out.println(status);
		if (!status.equals(actualstatus)) {
			failureList.add("Status is not as expected. Expected [" + actualstatus + "] but was [" + status + "]");
		}

		String orderDate = orderHeaderMaintenancePage.getOrderDate();
		if (orderDate.equals(null)) {
			failureList.add("OrderDate is not as expected. Expected [Not NULL] but was [" + orderDate + "]");
		}

		String createdBy = orderHeaderMaintenancePage.getCreatedBy();
		if (createdBy.equals(null)) {
			failureList.add("CreatedBy is not as expected. Expected [Not NULL] but was [" + createdBy + "]");
		}

		String orderTime = orderHeaderMaintenancePage.getOrderTime();
		if (orderTime.equals(null)) {
			failureList.add("OrderTime is not as expected. Expected [Not NULL] but was [" + orderTime + "]");
		}

		String creationDate = orderHeaderMaintenancePage.getCreationDate();
		if (creationDate.equals(null)) {
			failureList.add("Creation Date is not as expected. Expected [Not NULL] but was [" + creationDate + "]");
		}
		String creationTime = orderHeaderMaintenancePage.getCreationTime();
		if (creationTime.equals(null)) {
			failureList.add("CreationTime is not as expected. Expected [Not NULL] but was [" + creationTime + "]");
		}

		String moveTaskStatus = orderHeaderMaintenancePage.getMoveTaskStatus();
		if (moveTaskStatus.equals(null)) {
			failureList
					.add("Move Task Status  is not as expected. Expected [Not NULL] but was [" + moveTaskStatus + "]");
		}
		String fromSiteId = orderHeaderMaintenancePage.getFromSiteId();
		if (fromSiteId.equals(null)) {
			failureList.add("From SiteId is not as expected. Expected [Not NULL] but was [" + fromSiteId + "]");
		}
		String type = orderHeaderMaintenancePage.getType();
		if (!type.equals("INT SEA")) {
			failureList.add("Type is not as expected. Expected [INT SEA] but was [" + type + "]");
		}
		String numberOfLines = orderHeaderMaintenancePage.getNumberOfLines();
		if (numberOfLines.equals(null)) {
			failureList.add("Number Of Lines is not as expected. Expected [Not NULL] but was [" + numberOfLines + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have hub details in hub address tab$")
	public void the_order_should_have_hub_details_in_hub_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickHubAddressTab();

		String hub = orderHeaderMaintenancePage.getHub();
		if (hub.equals(null)) {
			failureList.add("Hub is not as expected. Expected [Not NULL] but was [" + hub + "]");
		}
		String hubName = orderHeaderMaintenancePage.getHubName();
		if (hubName.equals(null)) {
			failureList.add("Hub Name is not as expected. Expected [Not NULL] but was [" + hubName + "]");
		}
		String hubCountry = orderHeaderMaintenancePage.getHubCountry();
		if (hubCountry.equals(null)) {
			failureList.add("Hub Country is not as expected. Expected [Not NULL] but was [" + hubCountry + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

}
